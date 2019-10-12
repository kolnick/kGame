package com.game.date;

import com.game.util.date.TimeUtil;
import org.junit.Test;

import java.util.Calendar;

public class DateTest {
    @Test
    public void testDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 10);
        //int natralDayFromTime = TimeUtil.getNatralDayFromTime(cal.getTimeInMillis());
        //System.out.println(natralDayFromTime);
    }

    @Test
    public void testNowToHourSecond() {
        int nowSecondToOneHour = TimeUtil.getNowSecondToOneHour(System.currentTimeMillis());
        System.out.println(nowSecondToOneHour);
        System.out.println(nowSecondToOneHour / 60);
        System.out.println(nowSecondToOneHour % 60);
    }

}
