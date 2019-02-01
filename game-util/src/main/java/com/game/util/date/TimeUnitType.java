package com.game.util.date;

import java.util.concurrent.TimeUnit;

public enum TimeUnitType {
    /**
     * 毫秒
     */
    MILLISECONDS(0),
    /**
     * 秒
     */
    SECOND(1),
    /**
     * 分钟
     */
    MINUTES(2),
    /**
     * 小时
     */
    HOUR(3),
    /**
     * 天
     */
    DAY(4),
    /**
     * 星期
     */
    WEEK(5),
    /**
     * 月
     */
    MONTH(6),
    /**
     * 年
     */
    YEAR(7),
    /**
     * 本周最后一天
     */
    UNTIL_WEEK(8),
    /**
     * 本月最后一天
     */
    UNTIL_MONTH(9),
    /**
     * 不检测
     */
    NO_CHECK(10),

    UN_KNOW(-1);

    private int type;

    TimeUnitType(int type) {
        this.type = type;
    }

    public static TimeUnitType valueOf(int value) {
        switch (value) {
            case 0:
                return MILLISECONDS;
            case 1:
                return SECOND;
            case 2:
                return MINUTES;
            case 3:
                return HOUR;
            case 4:
                return DAY;
            case 5:
                return WEEK;
            case 6:
                return MONTH;
            case 7:
                return YEAR;
            case 8:
                return UNTIL_WEEK;
            case 9:
                return UNTIL_MONTH;
            case 10:
                return NO_CHECK;
        }
        return UN_KNOW;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TimeUnit toTimeUnit() {

        switch (this) {
            case MILLISECONDS: {
                return TimeUnit.MILLISECONDS;
            }
            case SECOND: {
                return TimeUnit.SECONDS;
            }
            case HOUR: {
                return TimeUnit.HOURS;
            }
            case MINUTES: {
                return TimeUnit.MINUTES;
            }
            case DAY: {
                return TimeUnit.DAYS;
            }
            default:
                return null;
        }
    }


}
