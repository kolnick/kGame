package com.game.util.string;

import com.game.util.regex.RegexUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/4/27
 * @since 1.0.0
 */
public class StringUtil {
    public static final String SEPARATOR_DOUHAO = ",";
    public static final String SEPARATOR_MAOHAO = ":";
    public static final String SEPARATOR_SHUXIAN = "|";
    public static final String SEPARATOR_FANXIEZHAN = "\"";
    private final static String STRING_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final char UNDERLINE = '_';

    public static boolean isNullOrEmpty(String... strs) {
        if (strs == null) {
            return false;
        }
        for (String str : strs) {
            if (isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.length() == 0 || str.trim().isEmpty()) {
            return false;
        }
        return true;
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

    public static List<Integer> parseIntegerList(String str, String sep) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] splitArr = StringUtils.split(str, sep);
        List<Integer> list = new ArrayList<Integer>();
        for (String num : splitArr) {
            int parseInt = Integer.parseInt(num);
            list.add(parseInt);
        }
        return list;
    }


    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String Camel2Underline(String camel) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(camel);
        while (matcher.find()) {
            String w = matcher.group().trim();
            camel = camel.replace(w, "_" + w);
        }
        return camel.toUpperCase();
    }

    /**
     * 下划线转驼峰
     *
     * @param underline
     * @return
     */
    public static String underline2Camel(String underline) {
        Pattern pattern = Pattern.compile("[_]\\w");
        String camel = underline.toLowerCase();
        Matcher matcher = pattern.matcher(camel);
        while (matcher.find()) {
            String w = matcher.group().trim();
            camel = camel.replace(w, w.toUpperCase().replace("_", ""));
        }
        return camel;
    }


    public static String toClassName(String className) {
        if (className == null) {
            return "";
        }
        if (!className.startsWith("const_")) {
            className = "const_" + className;
        }
        int length = className.length();
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        for (int i = 0; i < length; i++) {
            char c = className.charAt(i);
            // 首字母大写
            if (i == 0) {
                char c1 = Character.toUpperCase(c);
                sb.append(c1);
                continue;
            }
            if (c == '_') {
                find = true;
                continue;
            }

            if (find && RegexUtil.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                find = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String gameServer = toClassName("file");
        System.out.println(gameServer);
        String s = underline2Camel(gameServer);
        System.out.println(s);
    }
}
