package com.game.util.date;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private final static SimpleDateFormat DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static Calendar getCalendar(String date) {
        Calendar calendar = null;
        try {
            Date date2 = DATETIME.parse(date);
            if (date2 != null) {
                calendar = Calendar.getInstance();
                calendar.setTime(date2);
                return calendar;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取时间间隔
     *
     * @param unit
     * @param startTime
     * @param endTime
     * @return
     * @Author cao chaojie
     */
    public static long getTimeDuration(TimeUnit unit, long startTime,
                                       long endTime) {
        if (unit == TimeUnit.SECONDS) {
            return (endTime - startTime) / DateUtils.MILLIS_PER_SECOND;
        } else if (unit == TimeUnit.DAYS) {
            return (endTime - startTime) / DateUtils.MILLIS_PER_DAY;
        } else if (unit == TimeUnit.HOURS) {
            return (endTime - startTime) / DateUtils.MILLIS_PER_HOUR;
        } else if (unit == TimeUnit.MINUTES) {
            return (endTime - startTime) / DateUtils.MILLIS_PER_MINUTE;
        } else if (unit == TimeUnit.MILLISECONDS) {
            return endTime - startTime;
        }
        return 0;
    }

    /**
     * 是否在周期内
     *
     * @param timeUnitType
     * @param startTime
     * @param interval
     * @return
     * @Author cao chaojie
     */
    public static boolean isInPeriod(TimeUnitType timeUnitType, long startTime,
                                     int interval) {
        long periodEndTime = addTimeUnit(timeUnitType, startTime, interval);
        if (startTime == 0) {
            return false;
        }
        return startTime < periodEndTime;
    }

    /**
     * 添加一段时间
     *
     * @param unit
     * @param timestamp
     * @param timeData
     * @return
     * @Author cao chaojie
     */
    public static long addTimeUnit(TimeUnitType unit, long timestamp,
                                   int timeData) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(timestamp);
        if (unit == TimeUnitType.MONTH) {
            now.add(Calendar.MONTH, timeData);
        } else if (unit == TimeUnitType.WEEK) {
            now.add(Calendar.DAY_OF_YEAR, 7 * timeData);
        } else if (unit == TimeUnitType.DAY) {
            now.add(Calendar.DAY_OF_YEAR, timeData);
        } else if (unit == TimeUnitType.HOUR) {
            now.add(Calendar.HOUR_OF_DAY, timeData);
        } else if (unit == TimeUnitType.SECOND) {
            now.add(Calendar.SECOND, timeData);
        } else if (unit == TimeUnitType.MINUTES) {
            now.add(Calendar.MINUTE, timeData);
        } else if (unit == TimeUnitType.UNITMONTH) {
            // 到本月最后一天23:59:59
            now.setTimeInMillis(getLastMonthDayTimestamp(now.getTimeInMillis()));
            // 到本周最后一天23:59:59
        } else if (unit == TimeUnitType.UNTILWEEK) {
            now.setTimeInMillis(getLastWeekDayTimestamp(now.getTimeInMillis()));
        } else if (unit == TimeUnitType.MILLISECONDS) {
            now.add(Calendar.MILLISECOND, timeData);
        }

        return now.getTimeInMillis();
    }

    public static long getLastWeekDayTimestamp(long now) {
        return getDayTimestamp(TimeUnitType.WEEK, now, false, 23);
    }

    public static long getLastMonthDayTimestamp(long now) {
        return getDayTimestamp(TimeUnitType.MONTH, now, false, 23);
    }

    public static long getDayTimestamp(TimeUnitType unit, long now,
                                       boolean first, int hour) {
        return getTimeUnitCalendar(unit, now, first, hour).getTimeInMillis();
    }

    /**
     * 获取周,月 第一天，最后一天时间戳
     *
     * @param unit
     * @param now
     * @return
     * @Author cao chaojie
     */
    public static Calendar getTimeUnitCalendar(TimeUnitType unit, long now,
                                               boolean first, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(now);
        if (unit == TimeUnitType.WEEK) {
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            if (first) {
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            } else {
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            }
        } else if (unit == TimeUnitType.MONTH) {
            if (first) {
                cal.set(Calendar.DAY_OF_MONTH, 1);
            } else {
                cal.set(Calendar.DAY_OF_MONTH, 0);
            }
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        if (first) {
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else {
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }
        return cal;
    }

    public static boolean isLeapYear(int year) {
        return (new GregorianCalendar()).isLeapYear(year);
    }

}
