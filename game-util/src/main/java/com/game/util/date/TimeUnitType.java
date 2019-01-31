package com.game.util.date;

/**
 * 时间单位类型
 *
 * @Author cao chaojie
 * @Date 2018年5月8日 10:10:40
 */
public enum TimeUnitType
{
	SECOND(0), MINUTES(1), HOUR(2), DAY(3), WEEK(4), MONTH(5), NOCHECK(
			6), SYSTEM_INTERVAL(7), UNTILWEEK(
					8), UNITMONTH(9), FOREVER(10), MILLISECONDS(11), UNKNOW(-1);
	private int type;

	TimeUnitType(int type)
	{
		this.type = type;
	}

	public static TimeUnitType valueOf(int value)
	{
		switch (value)
		{
			case 0:
				return SECOND;
			case 1:
				return MINUTES;
			case 2:
				return HOUR;
			case 3:
				return DAY;
			case 4:
				return WEEK;
			case 5:
				return MONTH;
			case 6:
				return NOCHECK;
			case 7:
				return SYSTEM_INTERVAL;
			case 8:
				return UNTILWEEK;
			case 9:
				return UNITMONTH;
			case 10:
				return FOREVER;
			case 11:
				return MILLISECONDS;
		}
		return UNKNOW;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

}
