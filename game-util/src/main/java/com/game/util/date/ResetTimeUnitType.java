package com.game.util.date;

public enum ResetTimeUnitType {
    DAY(0), WEEK(1), MONTH(2), NO_RESET(3);
    private int type;

    ResetTimeUnitType(int type) {
        this.type = type;
    }

    public static ResetTimeUnitType valueOf(int value) {
        switch (value) {
            case 0:
                return DAY;
            case 1:
                return WEEK;
            case 2:
                return MONTH;
        }
        return NO_RESET;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
