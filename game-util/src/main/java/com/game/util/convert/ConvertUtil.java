package com.game.util.convert;

import java.util.concurrent.TimeUnit;

public class ConvertUtil {
    
    public static String toString(String sep, final String... param) {
        if (param == null || sep == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String str : param) {
            sb.append(str);
            sb.append(sep);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    /**
     * 获取布尔值当参数为Y|y时返回true,N|n时返回false.
     *
     * @param ch
     * @return
     */
    public final static Boolean getBoolean(final String ch) {
        if (ch.equalsIgnoreCase("Y") || ch.equals("1")) {
            return true;
        } else if (ch.equalsIgnoreCase("N") || ch.equals("0")) {
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
        return null;
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
    
    
    /**
     * 时间单位转换
     *
     * @param sourceUnit 源时间单位
     * @param targetUnit 目标时间单位
     * @param timeData   时间
     * @return
     */
    public static long timeChange(TimeUnit sourceUnit, TimeUnit targetUnit, long timeData) {
        return targetUnit.convert(timeData, sourceUnit);
    }
}
