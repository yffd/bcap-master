package com.yffd.bcap.common.model.system;

import org.slf4j.Logger;

public class SysLogger {

    private static final String LOG_PREFIX = "[--系统日志--]";

    public static void info(Logger logger, String msg) {
        if (logger.isInfoEnabled())
            logger.info(LOG_PREFIX + msg);
    }

    public static void warn(Logger logger, String msg) {
        if (logger.isWarnEnabled())
            logger.warn(LOG_PREFIX + msg);
    }

    public static void warn(Logger logger, String msg, Throwable t) {
        if (logger.isWarnEnabled())
            logger.warn(LOG_PREFIX + msg, t);
    }

    public static void error(Logger logger, String msg) {
        if (logger.isErrorEnabled())
            logger.error(LOG_PREFIX + msg);
    }

    public static void error(Logger logger, String msg, Throwable t) {
        if (logger.isErrorEnabled())
            logger.error(LOG_PREFIX + msg, t);
    }
}
