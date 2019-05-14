package com.game.util.date;

/**
 * 重置时间类型
 */
public enum ResetTimeType {
    /**
     * 每天
     */
    DAY(0),
    /**
     * 每周
     */
    WEEK(1),
    /**
     * 每月
     */
    MONTH(2),
    /**
     * 不重置
     */
    NO_RESET(3);

    private int type;

    ResetTimeType(int type) {
        this.type = type;
    }

    public static ResetTimeType valueOf(int value) {
        switch (value) {
            case 0:
                return DAY;
            case 1:
                return WEEK;
            case 2:
                return MONTH;
            default:
                return NO_RESET;
        }
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
