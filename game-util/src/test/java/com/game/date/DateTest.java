package com.game.date;

import java.util.Calendar;

import org.junit.Test;

public class DateTest
{
	@Test
	public void testDay()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 10);
		//int natralDayFromTime = TimeUtil.getNatralDayFromTime(cal.getTimeInMillis());
		//System.out.println(natralDayFromTime);
	}
}
