package com.game.engine.common.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志
 *
 * @author caochaojie
 * @date 2019/08/16
 */
public class LogUtil {
    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    private static final LogUtil INSTANCE = new LogUtil();

    public static LogUtil getInstance() {
        return INSTANCE;
    }

    private LogUtil() {
    }

    public static void trace(String message) {
        trace(LOG, message, null);
    }

    public static void trace(Logger log, String message) {
        trace(log, message, null);
    }

    public static void trace(Logger log, String message, Object... params) {
        if (!log.isTraceEnabled()) {
            return;
        }
        log.trace(message, params);
    }

    public static void trace(Logger logger, Throwable throwable) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(throwable);
        logger.trace(message);
    }

    public static void trace(Logger logger, Exception e) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(e);
        logger.trace(message);
    }

    public static void debug(String message) {
        debug(LOG, message, null);
    }

    public static void debug(Logger log, String message) {
        debug(log, message, null);
    }

    public static void debug(Logger log, String message, Object... params) {
        if (!log.isDebugEnabled()) {
            return;
        }
        log.debug(message, params);
    }

    public static void debug(Logger logger, Throwable throwable) {
        String message = ExceptionUtil.getMessage(throwable);
        logger.debug(message);
    }

    public static void debug(Logger logger, Exception e) {
        String message = ExceptionUtil.getMessage(e);
        logger.debug(message);
    }

    public static void info(String message) {
        info(LOG, message, null);
    }

    public static void info(Logger log, String message) {
        info(log, message, null);
    }

    public static void info(Logger log, String message, Object... params) {
        if (!log.isInfoEnabled()) {
            return;
        }
        log.info(message, params);
    }

    public static void info(Logger logger, Throwable throwable) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(throwable);
        logger.info(message);
    }

    public static void info(Logger logger, Exception e) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(e);
        logger.info(message);
    }


    public static void warn(String message) {
        warn(LOG, message, null);
    }

    public static void warn(Logger log, String message) {
        warn(log, message, null);
    }

    public static void warn(Logger log, String message, Object... params) {
        if (!log.isWarnEnabled()) {
            return;
        }
        log.warn(message, params);
    }

    public static void warn(Logger logger, Throwable throwable) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(throwable);
        logger.warn(message);
    }

    public static void warn(Logger logger, Exception e) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(e);
        logger.warn(message);
    }


    public static void error(String message) {
        error(LOG, message, null);
    }

    public static void error(Logger log, String message) {
        error(log, message, null);
    }

    public static void error(Logger log, String message, Object... params) {
        if (!log.isErrorEnabled()) {
            return;
        }
        log.error(message, params);
    }

    public static void error(Logger logger, Throwable throwable) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(throwable);
        logger.error(message);
    }

    public static void error(Logger logger, Exception e) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        String message = ExceptionUtil.getMessage(e);
        logger.error(message);
    }

}
