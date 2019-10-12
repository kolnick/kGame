package com.game.engine.common.util.convert;


import com.ym.server.engine.common.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: caochaojie
 * @Date: 2019/3/8
 */
public class ConvertUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtil.class);

    public static int[] toIntArray(String[] str) {
        if (str == null || str.length == 0) {
            return null;
        }
        return Stream.of(str).mapToInt(Integer::valueOf).toArray();
    }

    public static List<Integer> toIntList(String[] str) {
        if (str == null || str.length == 0) {
            return Collections.emptyList();
        }
        return Stream.of(str).map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Integer> toIntList(String str, String sep) {
        String[] splitArr = StringUtils.split(str, sep);
        return toIntList(splitArr);
    }

    public static List<Float> toFloatList(String str, String sep) {
        String[] splitArr = StringUtils.split(str, sep);
        return Stream.of(splitArr).map(Float::valueOf).collect(Collectors.toList());
    }

    public static Set<Integer> toIntSet(String str, String sep) {
        String[] splitArr = StringUtils.split(str, sep);
        return Stream.of(splitArr).map(Integer::valueOf).collect(Collectors.toSet());
    }

    public static List<Double> toDoubleList(String str, String sep) {
        String[] splitArr = StringUtils.split(str, sep);
        return Stream.of(splitArr).map(Double::valueOf).collect(Collectors.toList());
    }

    public static List<String> toStringList(String str, String sep) {
        String[] splitArr = StringUtils.split(str, sep);
        return Stream.of(splitArr).map(String::valueOf).collect(Collectors.toList());
    }

    /**
     * 将两个int值 转成成long类型
     * int类型占用4个字节用32二进制表示。 最高位为符高位，0表示正数，1表示负数
     * 4294967295L 正数32位的十进制范围数  0~4294967295 2^32-1
     * 4294967296L 负数32位的十进制范围数  -4294967296~-1 -2^32
     * 由于Java是采用无符号表示int 所以表示的范围是2^31次-1
     *
     * @param low  低位
     * @param high 高位
     */
    public static long combineInt2Long(int low, int high) {
        return (long) low & 4294967295L | (long) high << 32 & -4294967296L;
    }

    /**
     * 获取Long类型低位值
     *
     * @param val long值
     */
    public static int getLongLowInt(Long val) {
        return val == null ? 0 : (int) (4294967295L & val);
    }

    /**
     * 获取Long类型高位值
     *
     * @param val long值
     */
    public static int getLongHighInt(Long val) {
        return val == null ? 0 : (int) ((-4294967296L & val) >> 32);
    }


    /**
     * 获取布尔值当参数为Y|y时返回true,N|n时返回false.
     *
     * @param ch
     * @return
     */
    public final static Boolean getBoolean(final String ch) {
        if ("Y".equalsIgnoreCase(ch) || "1".equals(ch)) {
            return true;
        } else if ("N".equalsIgnoreCase(ch) || "0".equals(ch)) {
            return false;
        }
        return Boolean.valueOf(ch);
    }

    /**
     * 获取布尔值
     *
     * @param flag
     * @return
     */
    public final static boolean getBoolean(int flag) {
        return flag != 0;
    }

    /**
     * 获取布尔值当参数为Y|y时返回true,N|n时返回false.
     *
     * @param ch
     * @return
     */
    public final static Boolean getBoolean(final char ch) {
        if (ch == 'Y' || ch == 'y' || ch == '1') {
            return true;
        } else if (ch == 'N' || ch == 'n' || ch == '0') {
            return false;
        }
        return false;
    }

    /**
     * 获取正浮点型
     *
     * @param str
     * @return
     */
    public final static Float getNonNegativeFloat(final String str)
            throws NumberFormatException {
        Float i = Float.valueOf(str);
        if (i >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    public final static Double getNonNegativeDouble(final String str)
            throws NumberFormatException {
        Double i = Double.valueOf(str);
        if (i >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 获取正整数
     *
     * @param str
     * @return
     * @throws NumberFormatException
     */
    public final static Integer getPositiveInteger(final String str)
            throws NumberFormatException {
        Integer i = Integer.valueOf(str);
        if (i >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }


    /**
     * 获取正短整型
     *
     * @param str
     * @return
     */
    public final static Short getNonNegativeShort(final String str)
            throws NumberFormatException {
        Short i = Short.valueOf(str);
        if (i >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }




    /**
     * 获取正长整形
     *
     * @param str
     * @return
     */
    public final static Long getNonNegativeLong(final String str)
            throws NumberFormatException {
        Long i = Long.valueOf(str);
        if (i > 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 获取正浮点
     *
     * @param str
     * @return
     * @throws NumberFormatException
     */
    public final static Float getPositiveFloat(final String str)
            throws NumberFormatException {
        Float i = Float.valueOf(str);
        if (i > 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    public static Map<String, String> createStringMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        String[] split = StringUtils.split(str, ";");
        if (split == null || split.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();
        for (String sp : split) {
            String[] kv = StringUtils.split(sp, ":");
            if (kv == null || kv.length != 2) {
                LOGGER.info("数据" + str + "createStringMap 格式错误");
                continue;
            }
            String key = kv[0];
            String value = kv[1];
            map.put(key, value);
        }
        return map;
    }

    public static Map<Integer, Integer> createIntMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        String[] split = StringUtils.split(str, ";");
        if (split == null || split.length == 0) {
            return Collections.emptyMap();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (String sp : split) {
            String[] kv = StringUtils.split(sp, ":");
            if (kv == null || kv.length != 2) {
                LOGGER.info("数据" + str + "createIntMap 格式错误");
                continue;
            }
            String key = kv[0];
            String value = kv[1];
            Integer intKey = Integer.valueOf(key);
            Integer intValue = Integer.valueOf(value);
            map.put(intKey, intValue);
        }
        return map;
    }

    public static Map<Float, Float> createFloatMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        String[] split = StringUtils.split(str, ";");
        if (split == null || split.length == 0) {
            return Collections.emptyMap();
        }
        Map<Float, Float> map = new HashMap<>();
        for (String sp : split) {
            String[] kv = StringUtils.split(sp, ":");
            if (kv == null || kv.length != 2) {
                LOGGER.info("数据" + str + "createFloatMap 格式错误");
                continue;
            }
            String key = kv[0];
            String value = kv[1];
            Float intKey = Float.valueOf(key);
            Float intValue = Float.valueOf(value);
            map.put(intKey, intValue);
        }
        return map;
    }


    public static Map<Double, Double> createDoubleMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        String[] split = StringUtils.split(str, ";");
        if (split == null || split.length == 0) {
            return Collections.emptyMap();
        }
        Map<Double, Double> map = new HashMap<>();
        for (String sp : split) {
            String[] kv = StringUtils.split(sp, ":");
            if (kv == null || kv.length != 2) {
                LOGGER.info("数据" + str + "createDoubleMap 格式错误");
                continue;
            }
            String key = kv[0];
            String value = kv[1];
            Double intKey = Double.valueOf(key);
            Double intValue = Double.valueOf(value);
            map.put(intKey, intValue);
        }
        return map;
    }


    public static Map<Integer, Float> createIntegerFloatMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        String[] split = StringUtils.split(str, ";");
        if (split == null || split.length == 0) {
            return Collections.emptyMap();
        }
        Map<Integer, Float> map = new HashMap<>();
        for (String sp : split) {
            String[] kv = StringUtils.split(sp, ":");
            if (kv == null || kv.length != 2) {
                LOGGER.info("数据" + str + "createIntegerFloatMap 格式错误");
                continue;
            }
            String key = kv[0];
            String value = kv[1];
            Integer intKey = Integer.valueOf(key);
            Float intValue = Float.valueOf(value);
            map.put(intKey, intValue);
        }
        return map;
    }
}


