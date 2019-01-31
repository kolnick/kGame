package com.game.common.meta.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.game.util.file.FileUtil;

public class StringFilterUtil
{
	private static TrieFilter tf = new TrieFilter();

	public static void load(String file)
	{
		List<String> list = null;
		try
		{
			list = FileUtil.loadStringList(new File(file), "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null)
		{
			for (String key : list)
			{
				tf.addKey(key);
			}
		}
	}

	public static String fiter(String content)
	{
		return tf.replace(content, '*');
	}

	public static boolean isLegal(String content)
	{
		return tf.hasBadWord(content);
	}
}
