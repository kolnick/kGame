package com.game.config.util;

import com.game.config.bean.PropertyDesc;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射
 */
public class ReflectUtil {

    /**
     * 所有属性缓存 Class->PropertyDesc数组
     */
    private static final ConcurrentHashMap<Class<?>, PropertyDesc[]> propertyDescCache = new ConcurrentHashMap<Class<?>, PropertyDesc[]>();

    /**
     * 缓存单个属性信息 class.name->PropertyDesc
     */
    private static final ConcurrentHashMap<String, PropertyDesc> propertyDescSingleCache = new ConcurrentHashMap<String, PropertyDesc>();
    /**
     * 缓存单个方法信息class.name.parameter...-> Method
     */
    private static final ConcurrentHashMap<String, Method> methodCache = new ConcurrentHashMap<String, Method>();

    /**
     * 获取指定类的指定属性信息
     *
     * @param clazz 类
     * @param name  属性名称
     */
    public static PropertyDesc getPropertyDesc(Class<?> clazz, String name) {
        String key = clazz.getName() + "." + name;
        PropertyDesc pd = propertyDescSingleCache.get(key);
        if (pd == null) {
            PropertyDesc[] ps = getPropertyDecs(clazz);
            for (PropertyDesc tmp : ps) {
                if (name.equals(tmp.getName())) {
                    pd = tmp;
                    break;
                }
            }
            if (pd != null) {
                propertyDescSingleCache.putIfAbsent(key, pd);
            }
        } else if (pd == PropertyDesc.NULL) {
            pd = null;
        }
        return pd;
    }

    public static boolean hasField(Class<?> clazz, String fieldName) {
        String key = clazz.getName() + "." + fieldName;
        PropertyDesc pd = propertyDescSingleCache.get(key);
        if (pd == null) {
            PropertyDesc[] ps = getPropertyDecs(clazz);
            for (PropertyDesc tmp : ps) {
                if (fieldName.equals(tmp.getName())) {
                    pd = tmp;
                    break;
                }
            }
            if (pd != null) {
                propertyDescSingleCache.putIfAbsent(key, pd);
            }
        }
        return true;
    }



    /**
     * 获取指定类的所有属性列表
     *
     * @param clazz
     * @return
     */
    public static PropertyDesc[] getPropertyDecs(Class<?> clazz) {
        PropertyDesc[] ps = propertyDescCache.get(clazz);
        if (ps == null) {
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
            ps = new PropertyDesc[pds.length];
            for (int i = 0; i < pds.length; i++) {
                PropertyDescriptor pd = pds[i];
                PropertyDesc p = new PropertyDesc();

                String name = StringUtils.uncapitalize(pd.getName());
                p.setName(name);
                p.setPropertyType(pd.getPropertyType());
                p.setReadMethod(pd.getReadMethod());
                p.setWriteMethod(pd.getWriteMethod());

                ps[i] = p;
            }
            propertyDescCache.putIfAbsent(clazz, ps);
        }
        return ps;
    }

    /**
     * 获取指定Class的方法
     *
     * @param clazz
     * @param methodName     方法名称
     * @param parameterTypes 参数类型里列表，不定参数
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class<?> clazz, String methodName,
                                   Class<?>... parameterTypes)
            throws SecurityException, NoSuchMethodException {
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getName()).append(".").append(methodName);
        if (parameterTypes != null) {
            for (Class<?> pcls : parameterTypes) {
                sb.append(".").append(pcls.getName());
            }
        }
        String key = sb.toString();
        Method method = methodCache.get(key);
        if (method == null) {
            method = clazz.getMethod(methodName, parameterTypes);
            if (method != null) {
                methodCache.putIfAbsent(key, method);
            }
        }
        return method;
    }

    public static boolean isClassExtends(Class<?> targetClazz,
                                         Class<?> superClazz) {
        Class<?> clazz = targetClazz.getSuperclass();
        while (clazz != null) {
            if (clazz == superClazz) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
}
