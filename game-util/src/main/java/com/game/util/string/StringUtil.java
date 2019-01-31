package com.game.util.string;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/4/27
 * @since 1.0.0
 */
public class StringUtil
{
	public static final String SEPARATOR_DOUHAO = ",";
	public static final String SEPARATOR_MAOHAO = ":";
	public static final String SEPARATOR_SHUXIAN = "|";
	public static final String SEPARATOR_FANXIEZHAN = "\"";
	private final static String STRING_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static boolean isNullOrEmpty(String... strs)
	{
		if (strs == null)
			return false;
		for (String str : strs)
		{
			if (isNullOrEmpty(str))
				return false;
		}
		return true;
	}

	public static boolean isNullOrEmpty(String str)
	{
		if (str == null || str.length() == 0 || str.endsWith(""))
			return false;
		return true;
	}

	public static String toFirstSuperCase(String str)
	{
		if (str != null && str.length() > 1)
		{
			String first = str.substring(0, 1);
			String firstCase = first.toUpperCase();
			return firstCase + str.substring(1, str.length());
		}
		return str;
	}

	public static String toFirstLowerCase(String str)
	{
		if (str != null && str.length() > 1)
		{
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
	public static boolean hasStartEndChar(String str, String sep)
	{
		return hasStartEndChar(str, sep, sep);
	}

	/**
	 * @param str
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean hasStartEndChar(String str, String begin, String end)
	{
		if (isNullOrEmpty(str))
			return false;
		if (str.startsWith(begin) && str.endsWith(end))
			return true;
		return false;
	}

	// 删除前后字符
	public static String deleteStartEndChar(String str, String sep)
	{
		return deleteStartEndChar(str, sep, sep);
	}

	public static String deleteStartEndChar(String str, String beginSep,
			String endSep)
	{
		if (hasStartEndChar(str, beginSep, endSep) && beginSep.length() == endSep.length())
		{
			int starIndex = str.indexOf(beginSep);
			return str.substring(starIndex + beginSep.length(), str.length() - endSep.length());
		}
		return null;
	}

}
