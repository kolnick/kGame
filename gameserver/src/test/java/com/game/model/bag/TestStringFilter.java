package com.game.model.bag;

import com.game.common.meta.filter.StringFilterUtil;
import org.junit.Test;

public class TestStringFilter
{
	@Test
	public void stringFilter()
	{
		StringFilterUtil s = new StringFilterUtil();

		long startTime3 = System.currentTimeMillis();
		int time = 100000;
		for (int i = 0; i < time; i++)
		{
			String i_fuck_you_ = s.fiter("i fuck you ");
			// System.out.println(i_fuck_you_);
		}
		long endTime3 = System.currentTimeMillis();
		System.out.println(endTime3 - startTime3);
	}
}
