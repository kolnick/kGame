package com.game.engine.common.util.string;

import com.ym.server.engine.common.util.regex.RegexUtil;

import java.util.*;
import java.util.regex.Matcher;


public class StringUtils {

    public static final String NULL = "null";

    public static boolean isNullOrEmpty(String... strs) {
        if (strs == null) {
            return true;
        }
        for (String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }


    public static String toFirstSuperCase(String str) {
        if (str != null && str.length() > 1) {
            String first = str.substring(0, 1);
            String firstCase = first.toUpperCase();
            return firstCase + str.substring(1, str.length());
        }
        return str;
    }

    public static String toFirstLowerCase(String str) {
        if (str != null && str.length() > 1) {
            String first = str.substring(0, 1);
            String firstCase = first.toLowerCase();
            return firstCase + str.substring(1, str.length());
        }
        return str;
    }


    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuilder sb = new StringBuilder();
        Matcher matcher = RegexUtil.CODE_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = RegexUtil.CODE_UNDER_LINE.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 数组转换 成字符串中字加":"
     *
     * @param lists
     * @return
     */
    public static String listToString(List<Integer> lists, String operator) {
        if (lists == null || lists.isEmpty() || operator == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer list : lists) {
            sb.append(list);
            sb.append(operator);
        }
        sb.substring(sb.length() - 1);
        return sb.toString();
    }

    public static String listToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static boolean isBlank(String value) {
        if (value == null || value.isEmpty() || value.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean checkParas(String... value) {
        for (String s : value) {
            if (isBlank(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 转化首字母为小写
     *
     * @param field
     * @return
     */
    public static String lowerCase(String field) {
        byte[] names = field.getBytes();
        if (names[0] >= 'A' && names[0] <= 'Z') {
            names[0] = (byte) (names[0] + 32);
        }
        return new String(names);
    }

    /**
     * 转化首字母为大写
     *
     * @param field
     * @return
     */
    public static String upCase(String field) {
        byte[] names = field.getBytes();
        if (names[0] >= 'a' && names[0] <= 'z') {
            names[0] = (byte) (names[0] - 32);
        }
        return new String(names);
    }


    /**
     * 分割字符串
     *
     * @param param
     * @param sep
     * @return
     */
    public final static String[] split(final String param, String sep) {
        if (param == null || sep == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(param, sep);
        String[] items = new String[st.countTokens()];
        int i = 0;
        try {
            while (st.hasMoreTokens()) {
                items[i] = st.nextToken();
                ++i;
            }
        } catch (NumberFormatException e) {
            items = null;
        }
        st = null;
        return items;
    }

    /**
     * 转换成Int map
     *
     * @param params
     * @return
     */
    public final static Map<Integer, Integer> toMap(final String[] params) {
        if (params == null || params.length == 0 || params.length % 2 != 0) {
            return Collections.emptyMap();
        }
        int length = params.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i += 2) {
            String key = params[i];
            String value = params[i + 1];
            Integer keyInt = Integer.valueOf(key);
            Integer valueInt = Integer.valueOf(value);
            map.put(keyInt, valueInt);
        }
        return map;
    }

    /**
     * 合成string
     *
     * @param operator 分隔符
     * @param objs     数据
     */
    public final static String mergerString(String operator, Object... objs) {
        if (objs == null || objs.length == 0 || operator == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(obj);
            sb.append(operator);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    /**
     * 是否有前后字符
     *
     * @param str
     * @param sep
     * @return
     */
    public static boolean hasStartEndChar(String str, String sep) {
        return hasStartEndChar(str, sep, sep);
    }

    /**
     * @param str
     * @param begin
     * @param end
     * @return
     */
    public static boolean hasStartEndChar(String str, String begin, String end) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        if (str.startsWith(begin) && str.endsWith(end)) {
            return true;
        }
        return false;
    }

    // 删除前后字符
    public static String deleteStartEndChar(String str, String sep) {
        return deleteStartEndChar(str, sep, sep);
    }

    public static String deleteStartEndChar(String str, String beginSep,
                                            String endSep) {
        if (hasStartEndChar(str, beginSep, endSep) && beginSep.length() == endSep.length()) {
            int starIndex = str.indexOf(beginSep);
            return str.substring(starIndex + beginSep.length(), str.length() - endSep.length());
        }
        return null;
    }

    /**
     * 字符串头和尾添加字符串
     *
     * @param str 字符串
     * @param op  分隔符
     */
    public static String addFirstLast(String str, String op) {
        StringBuilder sb = new StringBuilder();
        sb.append(op);
        sb.append(str == null ? "" : str);
        sb.append(op);
        return sb.toString();
    }
}
