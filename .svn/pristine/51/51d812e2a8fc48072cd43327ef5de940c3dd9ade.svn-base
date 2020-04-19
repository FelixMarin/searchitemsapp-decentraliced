package com.searchitemsapp.util;

import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class LogsUtils {

	private LogsUtils() {
		super();
	}

	public static void escribeLogDebug(final String log, Class clazz) {
		if(LoggerFactory.getLogger(clazz).isInfoEnabled()) {
			LoggerFactory.getLogger(clazz).info(log);
		}
	}

	public static void escribeLogWarn(final String log, Class clazz, Exception e) {
		if(LoggerFactory.getLogger(clazz).isWarnEnabled()) {
			LoggerFactory.getLogger(clazz).warn(log,e);
		}
	}	
	
	public static void escribeLogError(final String log, Class clazz, Exception e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}	
	
	public static void escribeLogError(final String log, Class clazz, InstantiationError e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}
	
	public static void escribeLogError(final String log, Class clazz, Throwable e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}
}
