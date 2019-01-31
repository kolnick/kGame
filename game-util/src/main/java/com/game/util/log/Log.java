package com.game.util.log;

import org.slf4j.Logger;

public class Log
{
	public static void logDebug(Logger logger, String debug)
	{
		if (logger.isDebugEnabled())
			logger.debug(debug);
	}

	public static void logInfo(Logger logger, String info)
	{
		if (logger.isInfoEnabled())
			logger.info(info);
	}

	public static void logWarn(Logger logger, String warn)
	{
		if (logger.isWarnEnabled())
			logger.warn(warn);
	}

	public static void logWarn(Logger logger, String warn, Exception e)
	{
		if (logger.isWarnEnabled())
			logger.warn(warn, e);
	}

	public static void logError(Logger logger, String error)
	{
		if (logger.isErrorEnabled())
			logger.error(error);
	}

	public static void logError(Logger logger, String error, Exception e)
	{
		if (logger.isErrorEnabled())
			logger.error(error, e);
	}
}
