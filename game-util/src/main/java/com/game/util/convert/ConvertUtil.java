package com.game.util.convert;

import java.util.StringTokenizer;

public class ConvertUtil {

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
        if (i != null && i.floatValue() >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    public final static Double getNonNegativeDouble(final String str)
            throws NumberFormatException {
        Double i = Double.valueOf(str);
        if (i != null && i.doubleValue() >= 0) {
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
        if (i != null) {
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
        if (i != null && i.longValue() >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 解析参数
     *
     * @param param
     * @param sep
     * @return
     */
    public final static int[] getIntArr(final String param, String sep) {
        if (param == null || sep == null) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(param, sep);
        int[] items = new int[st.countTokens()];
        int i = 0;
        try {
            while (st.hasMoreTokens()) {
                items[i] = Integer.parseInt(st.nextToken());
                ++i;
            }
        } catch (NumberFormatException e) {
            items = null;
        }
        return items;
    }

    /**
     * 解析参数
     *
     * @param param
     * @param sep
     * @return
     */
    public final static String[] getStringArr(final String param, String sep) {
        if (param == null || sep == null) {
            return null;
        }
        return param.split(sep);
    }
}
