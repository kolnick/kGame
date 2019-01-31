package com.game.convert;

import org.junit.Test;

import com.game.util.convert.ConvertUtil;

public class ConvertTest
{
	@Test
	public void StringChangeIntArrTest()
	{
		String str = "1,2,3,4";
		int[] intArr = ConvertUtil.getIntArr(str, ",");
		System.out.println(intArr);
	}
}
