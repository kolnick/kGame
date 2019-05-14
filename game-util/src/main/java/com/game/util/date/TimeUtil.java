package com.game.util.date;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 * @author caochaojie
 * @date 2019年4月9日
 */
public class TimeUtil {

    /**
     * 一分钟的毫秒时长
     */
    public static final long MILLIS_PER_MINUTE = 60000L;
    /**
     * 一小时的毫秒时长
     */
    public static final long MILLIS_PER_HOUR = 60L * MILLIS_PER_MINUTE;
    /**
     * 一天的毫秒时长
     */
    public static final long MILLIS_PER_DAY = 24L * MILLIS_PER_HOUR;
    /**
     * 一天的秒时长
     */
    public static final long SECOND_PRE_DAY = 24L * 60 * 60;

    /**
     * 1秒的时长
     */
    public static final long MILLIS_PER_SECOND = 1000L;

    /**
     * 一周的毫秒时长
     */
    public static final long ONE_WEEK_IN_MILLISECONDS = 7 * MILLIS_PER_DAY;


    private final static DateTimeFormatter DATEFORMATER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final static DateTimeFormatter DATEYMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter DATEYMDHMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter TIMEHM = DateTimeFormatter.ofPattern("HH:mm");


    /**
     * 设置系统时间
     *
     * @param date 日期
     * @param time 时间
     * @return
     */
    public static String setDatetime(String date, String time) {
        String osName = System.getProperty("os.name");
        String dateTimeMessage = date + " " + time;

        try {
            if (osName.matches("^(?i)Windows.*$")) {
                String cmd = " cmd /c date " + date;
                Runtime.getRuntime().exec(cmd);
                cmd = " cmd /c time " + time;
                Process var5 = Runtime.getRuntime().exec(cmd);
            } else if (osName.matches("^(?i)Linux.*$")) {
                Runtime.getRuntime().exec("date -s " + date);
                Runtime.getRuntime().exec("date -s " + time);
            }

            return dateTimeMessage;
        } catch (IOException var6) {
            return var6.getMessage();
        }
    }

    public static boolean isSameDay(long time) {
        return isSameDay(time, System.currentTimeMillis());
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static Calendar getCalendar(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return instance;
    }

    public static boolean isSameDay(long startTime, long endTime) {
        Calendar startCal = getCalendar(startTime);
        Calendar endCal = getCalendar(endTime);
        return isSameDay(startCal, endCal);
    }

    public static String timeFormat(long time) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return ldt.format(DATEYMDHMS);
    }

    public final static LocalDateTime getLocalDateTime(String date) {
        return LocalDateTime.parse(date, DATEYMDHMS);
    }

    /**
     * 根据long型 返回 XX小时XX分XX秒
     *
     * @return
     */
    public static String showDifferTimeString(long longTime) {
        StringBuilder sb = new StringBuilder();
        long between = longTime / 1000;

        long day = between / (24 * 3600);
        if (day > 0) {
            sb.append(day + "天");
        }
        long hour = between % (24 * 3600) / 3600;
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        long minute = between % 3600 / 60;
        if (minute > 0) {
            sb.append(minute + "分钟");
        }
        long second = between % 60;
        sb.append(second + "秒");

        return sb.toString();
    }
    /**
     * 时间单位转换
     *
     * @param sourceUnit 源时间单位
     * @param targetUnit 目标时间单位
     * @param timeData   时间
     * @return
     */
    public static long timeChange(TimeUnit sourceUnit, TimeUnit targetUnit, long timeData) {
        return targetUnit.convert(timeData, sourceUnit);
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
            return (endTime - startTime) / MILLIS_PER_SECOND;
        } else if (unit == TimeUnit.DAYS) {
            return (endTime - startTime) / MILLIS_PER_DAY;
        } else if (unit == TimeUnit.HOURS) {
            return (endTime - startTime) / MILLIS_PER_HOUR;
        } else if (unit == TimeUnit.MINUTES) {
            return (endTime - startTime) / MILLIS_PER_MINUTE;
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
     * @param endTime
     * @param interval
     * @return
     */
    public static boolean isInPeriod(TimeUnitType timeUnitType, long startTime, long endTime,
                                     int interval) {
        long periodEndTime = addTimeUnit(timeUnitType, startTime, interval);
        if (startTime == 0) {
            return false;
        }
        return endTime < periodEndTime;
    }

    /**
     * 当前时间添加一段时间
     *
     * @param unit     时间单位
     * @param timeData 时间数据
     */
    public static long addTimeUnit(TimeUnitType unit, int timeData) {
        long now = System.currentTimeMillis();
        return addTimeUnit(unit, now, timeData);
    }

    /**
     * 是否同一周
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 是否同一周
     */
    public static boolean isSameWeek(long beginTime, long endTime) {
        long startWeekTime = getWeekFirstDayZeroHourTimestamp(beginTime);
        long endWeekTime = getWeekFirstDayZeroHourTimestamp(endTime);
        return startWeekTime == endWeekTime;
    }

    /**
     * 添加一段时间
     *
     * @param unit
     * @param timestamp
     * @param timeData
     * @return
     */
    public static long addTimeUnit(TimeUnitType unit, long timestamp, int timeData) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        switch (unit) {
            case MONTH:
                localDateTime = localDateTime.plusMonths(timeData);
                break;
            case WEEK:
                localDateTime = localDateTime.plusWeeks(timeData);
                break;
            case DAY:
                localDateTime = localDateTime.plusDays(timeData);
                break;
            case HOUR:
                localDateTime = localDateTime.plusHours(timeData);
                break;
            case SECOND:
                localDateTime = localDateTime.plusSeconds(timeData);
                break;
            case MINUTES:
                localDateTime = localDateTime.plusMinutes(timeData);
                break;
            case UNTIL_MONTH:
                // 到本月最后一天23:59:59
                localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
                break;
            case UNTIL_WEEK:
                localDateTime = localDateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);
                break;
            case MILLISECONDS: {
                return timestamp + timeData;
            }
            default: {

            }
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 是否闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (new GregorianCalendar()).isLeapYear(year);
    }

    /**
     * 获取时间周期间隔次数
     *
     * @param unit      时间单位
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  周期间隔
     * @return
     * @Author cao chaojie
     */
    public static long getTimeIntervalCount(TimeUnit unit, long startTime, long endTime, int interval) {
        if (unit == TimeUnit.SECONDS) {
            return (endTime - startTime) / MILLIS_PER_SECOND / interval;
        } else if (unit == TimeUnit.DAYS) {
            return (endTime - startTime) / MILLIS_PER_DAY / interval;
        } else if (unit == TimeUnit.HOURS) {
            return (endTime - startTime) / MILLIS_PER_HOUR / interval;
        } else if (unit == TimeUnit.MINUTES) {
            return (endTime - startTime) / MILLIS_PER_MINUTE / interval;
        } else if (unit == TimeUnit.MILLISECONDS) {
            return (endTime - startTime) / interval;
        }
        return 0;
    }

    public static long getTimeSecond(TimeUnitType unit, int value) {
        if (unit == TimeUnitType.DAY) {
            return value * MILLIS_PER_DAY / 1000;
        } else if (unit == TimeUnitType.HOUR) {
            return value * MILLIS_PER_HOUR / 1000;
        } else if (unit == TimeUnitType.MINUTES) {
            return value * MILLIS_PER_MINUTE / 1000;
        }
        return 0;
    }

    /**
     * 获取修正时间
     *
     * @param now  时间搓
     * @param hour 修正小时点
     */
    public static long getFixTimeStamp(long now, int hour) {
        return toMillSecond(getFixTime(now, hour));
    }

    /**
     * 获取修正时间
     *
     * @param now  时间
     * @param hour 修正时间点
     */
    public static LocalDateTime getFixTime(long now, int hour) {
        Instant instant = Instant.ofEpochMilli(now);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        int currentHour = localDateTime.getHour();
        localDateTime.withHour(hour).withMinute(0).withSecond(0).withNano(0);
        if (currentHour < hour) {
            localDateTime = localDateTime.minusDays(1);
        }
        return localDateTime;
    }

    /**
     * 是否超过一星期
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static boolean isMoreThanOneWeek(long startTime, long endTime) {
        return endTime - startTime >= ONE_WEEK_IN_MILLISECONDS;
    }


    /**
     * 是否大于一天
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static boolean isMoreThanOneDay(long startTime, long endTime) {
        long timeDuration = getTimeDuration(TimeUnit.DAYS, startTime, endTime);
        return timeDuration >= 1;
    }

    /**
     * 是否大于一个月
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param hour      修正小时
     */
    public static boolean isMoreThanOneMonthWithHour(long startTime, long endTime, int hour) {
        long firstMonthDayFixHour = getFirstMonthDayFixHour(startTime, hour);
        int monthDays = getMonthDays(startTime);
        long startCdTime = addTimeUnit(TimeUnitType.DAY, firstMonthDayFixHour, monthDays);
        return endTime >= startCdTime;
    }

    /**
     * 根据时间点获取这个月有几天
     *
     * @param time 时间点
     */
    public static int getMonthDays(long time) {
        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(time);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return a.get(Calendar.DATE);
    }

    /**
     * 根据时间点获取周一时间
     *
     * @param now  时间点
     * @param hour 修正小时
     */
    public static long getWeekFirstDayFixHour(long now, int hour) {
        LocalDateTime fixTime = getFixTime(now, hour);
        return toMillSecond(fixTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
    }

    /**
     * 根据时间点获取每月第一天修正时间
     *
     * @param now  时间点
     * @param hour 修正小时
     */
    public static long getFirstMonthDayFixHour(long now, int hour) {
        LocalDateTime fixTime = getFixTime(now, hour);
        return toMillSecond(fixTime.with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 根据时间点获取每星期最后一天0点时间
     *
     * @param now 时间点
     */
    public static long getLastWeekDayTimestamp(long now) {
        LocalDateTime fixTime = getFixTime(now, 0);
        return toMillSecond(fixTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));
    }

    /**
     * 根据时间点获取每月最后一天0点时间
     *
     * @param now 时间点
     */
    public static long getLastMonthDayTimestamp(long now) {
        LocalDateTime fixTime = getFixTime(now, 0);
        return toMillSecond(fixTime.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 根据时间点获取每月第一天0点时间
     *
     * @param now 时间点
     */
    public static long getFirstMonthDayZeroHourTimestamp(long now) {
        return getFirstMonthDayFixHour(now, 0);
    }

    /**
     * 根据时间点获取周一0点时间
     *
     * @param now 时间点
     */
    public static long getWeekFirstDayZeroHourTimestamp(long now) {
        return getWeekFirstDayFixHour(now, 0);
    }

    /**
     * 获取重置时间
     *
     * @param resetTimeUnitType 重置时间类型
     * @param now               时间戳
     * @param hour              修正小时
     * @return
     */
    public static long getResetTime(ResetTimeType resetTimeUnitType, long now, int hour) {
        switch (resetTimeUnitType) {
            case DAY: {
                return getFixTimeStamp(now, hour);
            }
            case MONTH: {
                return TimeUtil.getFirstMonthDayFixHour(now, hour);
            }
            case WEEK: {
                return TimeUtil.getWeekFirstDayFixHour(now, hour);
            }
            default: {
                return 0;
            }
        }
    }

    /**
     * LocalDateTime转MillSecond
     */
    public static long toMillSecond(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * millSecond转LocalDateTime
     */
    public static LocalDateTime toLocalDate(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * 格式化日期 转 MillSecond
     */
    public static long getTimeFormatToMillSecond(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            return -1;
        }
        LocalDateTime localDateTime = getLocalDateTime(dateTime);
        Objects.requireNonNull(localDateTime);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
