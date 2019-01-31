package com.game.service.util;

public class PlayerCountUtil
{

	private static int getBaseCount(String key)
	{
		return 3;
	}

	private static int getVipCount(int vipLevel, String key)
	{
		return 1;
	}

	private static int getPlayerCount(String key, String vipKey, int vipLevel)
	{
		return getBaseCount(key) + getVipCount(vipLevel, vipKey);
	}

	public static int getProgressCount()
	{
		return getPlayerCount("progressBaseCount", "vipProgress", 3);
	}
}
