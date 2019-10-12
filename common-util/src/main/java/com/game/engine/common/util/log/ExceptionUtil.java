package com.game.engine.common.util.log;

import com.ym.server.engine.common.util.string.StringUtils;

/**
 * 异常工具类
 *
 * @author caochaojie
 * @date 2019/08/16
 */
public class ExceptionUtil {
    /**
     * 获得完整消息，包括异常名
     *
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Throwable e) {
        if (null == e) {
            return StringUtils.NULL;
        }
        return getStackMessage(e.getMessage(), e.getStackTrace());
    }

    /**
     * 获得完整消息，包括异常名
     *
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Exception e) {
        if (null == e) {
            return StringUtils.NULL;
        }
        return getStackMessage(e.getMessage(), e.getStackTrace());
    }

    /**
     * 获取堆栈错误
     *
     * @param stackTrace 堆栈
     */
    private static String getStackMessage(String exceptionMessage, StackTraceElement[] stackTrace) {
        if (stackTrace == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(exceptionMessage);
        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString()).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

}
