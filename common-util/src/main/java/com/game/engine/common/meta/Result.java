package com.game.engine.common.meta;


/**
 * 统一返回结果
 *
 * @Author cao chaojie
 * @Date 2017年9月30日
 */
public class Result {
    private static final long serialVersionUID = 1L;
    private int code; // 状态码
    private Object[] obj; // 数据

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public static Result getResult(int code) {
        return new Result(code);
    }

    public static Result getResult(int code, Object... objs) {
        return new Result(code, objs);
    }

    public Result(int code, Object... obj) {
        this.code = code;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getObj() {
        return obj;
    }

    public Object getFirstObject() {
        if (obj == null || obj.length == 0) {
            return null;
        }
        return obj[0];
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

}
