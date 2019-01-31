package com.game.makecode.constant;

public interface GlobalConstant {
    int SKIP_ROW = 5;

    interface RowType {
        /**
         * 说明
         */
        int COMMENT = 0;
        /**
         * 是否不允许空值
         */
        int BLANK = 1;
        /**
         * 变量空间
         */
        int SPACE = 2;
        /**
         * 字段
         */
        int FIELD = 3;
        /**
         * 数据类型
         */
        int DATA_TYPE = 4;
    }

    interface CodeSpaceType {
        String SERVER = "s";
        String CLIENT = "c";
        String CS = "cs";
    }
}
