package com.game.common.meta.filter;

public class TrieFilter extends TrieNode
{

	public void addKey(String key)
	{
		if (key.isEmpty())
		{
			return;
		}
		TrieNode node = this;
		for (int i = 0; i < key.length(); i++)
		{
			char c = key.charAt(i);
			TrieNode subnode = node.m_values.get(c);
			if (subnode == null)
			{
				subnode = new TrieNode();
				node.m_values.put(c, subnode);
			}
			node = subnode;
		}
		node.m_end = true;
	}

	/**
	 * 检查是否包含非法字符
	 *
	 * @param text
	 *            输入文本
	 * @return 找到的第1个非法字符.没有则返回false
	 */
	public boolean hasBadWord(String text)
	{
		for (int i = 0; i < text.length(); i++)
		{
			TrieNode node = m_values.get(text.charAt(i));
			if (node != null)
			{
				if (node.m_end)
				{
					// 单独字的敏感词
					return true;
				}
				for (int j = i + 1; j < text.length(); j++)
				{
					node = node.m_values.get(text.charAt(j));
					if (node != null)
					{
						if (node.m_end)
						{
							return true; // 第一个非法字符为:text.Substring(i, j + 1 -
										 // i);
						}
					}
					else
					{
						break;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 替换非法字符
	 *
	 * @param text
	 * @param c
	 *            用于代替非法字符
	 * @return 替换后的字符串
	 */
	public String replace(String text, char c)
	{
		char[] chars = null;
		for (int i = 0; i < text.length(); i++)
		{
			int start = -1;
			int end = -1;
			TrieNode subnode = m_values.get(text.charAt(i));
			if (subnode != null)
			{
				start = i;
				if (subnode.m_end)
				{
					end = i;
				}
				for (int j = i + 1; j < text.length(); j++)
				{
					subnode = subnode.m_values.get(text.charAt(j));
					if (subnode != null)
					{
						if (subnode.m_end)
						{
							end = Math.max(end, j);
						}
					}
					else
					{
						break;
					}
				}
			}
			if (end != -1)
			{
				if (chars == null)
					chars = text.toCharArray();
				for (int t = start; t <= end; t++)
				{
					chars[t] = c;
				}
				i = end + 1;
			}
		}
		return chars == null ? text : new String(chars);
	}
}
