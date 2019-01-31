package com.game.util.check;

import java.util.regex.Pattern;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/5/21
 * @since 1.0.0
 */
public class CheckerUtil {
    private final static Pattern CHINESE_PATTERN = Pattern.compile("[\u4E00-\u9FCB]+$"); // 中文范围
    private final static Pattern INTERPUNCTION_PATTERN = Pattern.compile("-[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"); // 标题符号过滤器
    private final static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+"); // 数字
    private final static Pattern NUMBER_OR_CHARACTER_PATTERN = Pattern.compile("^[0-9a-zA-Z]*$"); // 英文或数字
    private final static Pattern IP_ADDESS_PATTERN = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");
    private final static Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");        // Email

    private final static Pattern LETTER_PATTERN = Pattern.compile("^[a-zA-Z]*$"); // words

    /**
     * 匹配字母或数字
     *
     * @param str
     * @return
     */
    public final static boolean isNumberOrCharacter(final String str) {
        return NUMBER_OR_CHARACTER_PATTERN.matcher(str).matches();
    }

    /**
     * 是不是不是一个数字 is not a number
     *
     * @return
     */
    public static boolean isNumber(String str) {
        boolean isNumber = str.matches(NUMBER_PATTERN.pattern());
        return isNumber;
    }


    /**
     *
     *
     * @return
     */
    public static boolean isLetter(String str) {
        return str.matches(LETTER_PATTERN.pattern());
    }


    /**
     * 是否全是匹配中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        return CHINESE_PATTERN.matcher(str).matches();
    }

    /**
     * 是否中文字符
     *
     * @param c
     * @return
     * @Author cao chaojie
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 是否存在标点符号
     *
     * @param str
     * @return
     */
    public static boolean hasInterpunction(String str) {
        return INTERPUNCTION_PATTERN.matcher(str).find();
    }

    /**
     * 是否是 IPv4 的 IP 地址
     *
     * @param str
     * @return
     */
    public final static boolean isIPv4Address(final String str) {
        if (str == null) {
            return false;
        }
        return IP_ADDESS_PATTERN.matcher(str).matches();
    }

    public static boolean checkPositive(int[] data) {
        if (data == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPositive(long[] data) {
        if (data == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断输入的字符串是否符合Email样式.
     *
     * @param str
     * @return
     */
    public static final boolean isEmail(final String str) {
        return EMAIL_PATTERN.matcher(str).matches();
    }

}
