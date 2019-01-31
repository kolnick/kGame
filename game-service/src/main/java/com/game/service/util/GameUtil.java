package com.game.service.util;

import com.game.common.meta.progress.bean.Progress;

public class GameUtil
{
	public static Progress addProgress(int type,int subType)
	{
		Progress p = new Progress();
		p.setSubType(subType);
		p.setType(type);
		return p;
	}
}
