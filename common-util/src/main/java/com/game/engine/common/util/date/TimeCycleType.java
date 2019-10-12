package com.game.engine.common.util.date;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 时间循环类型
 *
 * @Author Cinyky
 * @Date 15:34 2019-06-10
 */
public enum TimeCycleType {

    /**
     * 通用的传入开始时间 计算之后的时间
     */
    COMMON(0, "COMMON", false),
    /**
     * 建号
     */
    REGISTE(1, "REGISTE", false, true),
    /**
     * 每小时
     */
    EVER_HOUR(2, "EVER_HOUR", true),

    /**
     * 每天
     */
    EVER_DAY(3, "EVER_DAY", true),
    /**
     * 每周
     */
    EVER_WEEK(4, "EVER_WEEK", true),
    /**
     * 每月
     */
    EVERY_MONTH(5, "EVERY_MONTH", true),
    /**
     * 开服
     */
    SERVER_OPEN(6, "SERVER_OPEN"),
    /**
     * 合服
     */
    SERVER_MERGE(7, "SERVER_MERGE"),
    /**
     * 开服和合服
     */
    SERVER_OPEN_AND_MERGE(8, "SERVER_OPEN_AND_MERGE"),
    ;

    private int value;
    private boolean cycle;
    private String str;
    private boolean personal;

    TimeCycleType(int value, String str) {
        this.value = value;
        this.str = str;
        this.cycle = false;
        this.personal = false;
    }

    TimeCycleType(int value, String str, boolean cycle) {
        this.value = value;
        this.str = str;
        this.cycle = cycle;
        this.personal = false;
    }

    TimeCycleType(int value, String str, boolean cycle, boolean personal) {
        this.value = value;
        this.cycle = cycle;
        this.str = str;
        this.personal = personal;
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }

    public boolean isCycle() {
        return cycle;
    }

    public boolean isPersonal() {
        return personal;
    }

    private static List<TimeCycleType> list;

    static {
        list = Arrays.asList(TimeCycleType.values());
    }

    public static TimeCycleType getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        Optional<TimeCycleType> typeOptional = list.stream()
                .filter(exchangeType -> exchangeType.getValue() == value)
                .findFirst();

        if (typeOptional.isPresent()) {
            return typeOptional.get();
        }
        return null;
    }
}
