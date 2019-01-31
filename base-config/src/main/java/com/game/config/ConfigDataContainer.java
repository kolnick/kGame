package com.game.config;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

import com.game.util.collection.CollectionUtil;
import com.game.util.log.Log;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.config.bean.PropertyDesc;
import com.game.config.bean.TableData;
import com.game.config.constant.ConfigConstant;
import com.game.config.util.ReflectUtil;
import com.game.config.util.XlsUtil;
import com.game.util.convert.ConvertUtil;
import com.game.util.string.StringUtil;

public class ConfigDataContainer<T extends IConfigData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDataContainer.class);

    /**
     * 该配置对应的Class
     */
    private Class<T> clazz;
    /**
     * 该配置对应的文件名
     */
    private String fileName;
    /**
     * 该配置的key
     */
    private String key;

    /**
     * 属性转换器
     */
    private Map<String, IConverter> converterMap;

    private List<T> list;

    /**
     * 缓存
     */
    private Map<Object, T> mapCaches;

    public static Set<String> errKey = new HashSet<String>();

    public ConfigDataContainer() {

    }

    public ConfigDataContainer(Class<T> clazz, String fileName,
                               Map<String, IConverter> converterMap, String key) {
        this.clazz = clazz;
        this.fileName = fileName;
        this.converterMap = converterMap;
        this.key = key;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, IConverter> getConverterMap() {
        return converterMap;
    }

    public void setConverterMap(Map<String, IConverter> converterMap) {
        this.converterMap = converterMap;
    }

    /**
     * 加载数据
     *
     * @param filePath
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public void load(String filePath)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        String file = filePath + File.separatorChar + fileName + ".xls";
        // 加载数据到list
        TableData tableData = XlsUtil.load(new File(file), ConfigConstant.SKIP_LINE);
        // 获取每行的结果
        assert tableData != null;
        this.list = parseTable(tableData);
        // 建立缓存
        Map<Object, T> buildCache = buildCache(this.list);
        if (buildCache != null) {
            this.mapCaches = new HashMap<>(buildCache);
        }
        // 回调afterLoad函数
        this.afterLoad();
        errKey.clear();
    }

    /**
     * @param dataList
     */
    private Map<Object, T> buildCache(List<T> dataList) throws InvocationTargetException, IllegalAccessException {
        if (CollectionUtils.isEmpty(dataList)) {
            return null;
        }
        String[] cacheKeys = ConvertUtil.getStringArr(this.key, "#");
        int length = cacheKeys.length;
        PropertyDesc[] descs = new PropertyDesc[cacheKeys.length];
        for (int i = 0; i < length; ++i) {
            String keyField = cacheKeys[i];
            PropertyDesc desc = ReflectUtil.getPropertyDesc(this.clazz, keyField);
            if (desc == null || desc.getReadMethod() == null) {
                LOGGER.error("配置表：" + this.fileName + "中的" + keyField + " 字段在" + this.clazz.getName() + "没有找到setter和getter方法");
                return Collections.emptyMap();
            }
            descs[i] = desc;
        }
        Map<Object, T> cache = new HashMap<>();
        for (T t : dataList) {
            // 拿到他的值
            Object value = this.buildKey(t, descs);
            cache.put(value, t);
        }
        return cache;
    }

    public Object buildKey(T value, PropertyDesc[] descs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (descs.length == 1) {
            return descs[0].getReadMethod().invoke(value);
        } else {
            StringBuilder key = new StringBuilder();
            PropertyDesc[] var4 = descs;
            int var5 = descs.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                PropertyDesc desc = var4[var6];
                Object v = desc.getReadMethod().invoke(value);
                key.append(v.toString());
                key.append("#");
            }
            if (key.length() > 0) {
                key.deleteCharAt(key.length() - 1);
            }

            return key.toString();
        }
    }

    /**
     * 验证缓存key是否存在
     *
     * @param list
     * @param cacheKey
     * @return
     */
    private boolean isValidCacheKey(List<T> list, String[] cacheKey) {
        T t = list.get(0);
        for (String key : cacheKey) {
            if (!ReflectUtil.hasField(t.getClass(), key)) {
                Log.logInfo(LOGGER, "表格《" + this.fileName + "》" + "缓存的key" + key + "不存在");
                return false;
            }
        }
        return true;
    }

    private List<T> parseTable(TableData tableData)
            throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        List<Map<String, String>> tableRows = tableData.getTableRows();
        for (Map<String, String> rows : tableRows) {
            T t = converterObject(rows);
            list.add(t);
        }
        return list;
    }

    private T converterObject(Map<String, String> data)
            throws IllegalAccessException, InstantiationException {
        T config = clazz.newInstance();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String attr = entry.getKey();
            String value = entry.getValue();
            // 检测类是否有此属性
            PropertyDesc desc = ReflectUtil.getPropertyDesc(clazz, attr);
            if (desc == null) {
                if (errKey.contains(key)) {
                    continue;
                }
                LOGGER.error("配置表：" + fileName + "中的" + key + "字段在" + clazz.getName() + "没有找到setter和getter方法");
                errKey.add(key);
            } else {
                try {
                    Object newValue = covertValue(desc, value);
                    Method method = desc.getWriteMethod();
                    method.invoke(config, newValue);
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
        }
        return config;
    }

    private Object covertValue(PropertyDesc desc, String oldValue) {
        Object value = oldValue;
        if (this.converterMap.containsKey(desc.getName())) {
            IConverter converter = this.converterMap.get(desc.getName());
            value = converter.convert(oldValue);
        } else if (desc.getPropertyType() == int.class || desc.getPropertyType() == Integer.class) {
            value = ConvertUtil.getPositiveInteger(oldValue);
        } else if (desc.getPropertyType() == long.class || desc.getPropertyType() == Long.class) {
            value = ConvertUtil.getNonNegativeLong(oldValue);
        } else if (desc.getPropertyType() == float.class || desc.getPropertyType() == Float.class) {
            if (StringUtils.isEmpty(oldValue)) {
                oldValue = "0.0";
            }
            value = Float.parseFloat(oldValue);
        } else if (desc.getPropertyType() == double.class || desc.getPropertyType() == Double.class) {
            value = ConvertUtil.getNonNegativeDouble(oldValue);
        } else if (desc.getPropertyType() == int[].class) {
            if (StringUtils.isEmpty(oldValue)) {
                value = new int[0];
            } else {
                value = ConvertUtil.getIntArr(oldValue, StringUtil.SEPARATOR_DOUHAO);
            }
        } else if (desc.getPropertyType() == String[].class) {
            if (StringUtils.isEmpty(oldValue)) {
                value = new String[0];
            } else {
                value = ConvertUtil.getStringArr(oldValue, StringUtil.SEPARATOR_DOUHAO);
            }
        }
        return value;
    }

    private void afterLoad() {
        for (IConfigData config : list) {
            config.afterLoad();
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public <T> T getCache(Object obj) {
        return (T) mapCaches.get(obj);
    }
}
