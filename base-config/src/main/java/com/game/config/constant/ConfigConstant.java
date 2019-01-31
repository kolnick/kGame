package com.game.config.constant;

public interface ConfigConstant {
    short SKIP_LINE = 3;

    interface TableRule {
        /**
         * 说明
         */
        int DESC_ROW = 0;
        /**
         * 是否允许为空
         */
        int CHECK_EMPTY = 1;
        /**
         * 代码空间
         */
        int CODE_SPACE = 2;
    }
}
