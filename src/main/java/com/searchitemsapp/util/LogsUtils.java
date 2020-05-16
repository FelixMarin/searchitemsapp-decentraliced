package com.searchitemsapp.util;

import org.slf4j.LoggerFactory;

/**
 * Esta clase se encarga de pintar en las trazas de log
 * los mensajes del flujo de la apliación. Hay distintos
 * niveles de los: Debug, Warning y Error.
 * 
 * @author Felix Marin Ramirez
 * 
 */
@SuppressWarnings("rawtypes")
public class LogsUtils {

	/*
	 * Constructor
	 */
	private LogsUtils() {
		super();
	}

	/**
	 * Escribe una traza de log a nivel de debug. En la traza
	 * se indica el mensaje del párametro.
	 * 
	 * @param log
	 * @param clazz
	 */
	public static void escribeLogDebug(final String log, Class clazz) {
		if(LoggerFactory.getLogger(clazz).isInfoEnabled()) {
			LoggerFactory.getLogger(clazz).info(log);
		}
	}

	/**
	 * Escribe una traza de log a nivel de warn. En la traza
	 * se indica el mensaje del párametro.
	 * 
	 * @param log
	 * @param clazz
	 * @param e
	 */
	public static void escribeLogWarn(final String log, Class clazz, Exception e) {
		if(LoggerFactory.getLogger(clazz).isWarnEnabled()) {
			LoggerFactory.getLogger(clazz).warn(log,e);
		}
	}	
	
	/**
	 * Escribe una traza de log a nivel de error. En la traza
	 * se indica el mensaje del párametro.
	 * 
	 * @param log
	 * @param clazz
	 * @param Exception
	 */
	public static void escribeLogError(final String log, Class clazz, Exception e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}	
	
	/**
	 * Escribe una traza de log a nivel de error. En la traza
	 * se indica el mensaje del párametro.
	 * 
	 * @param log
	 * @param clazz
	 * @param InstantiationError
	 */
	public static void escribeLogError(final String log, Class clazz, InstantiationError e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}
	
	/**
	 * Escribe una traza de log a nivel de error. En la traza
	 * se indica el mensaje del párametro.
	 * 
	 * @param log
	 * @param clazz
	 * @param Throwable
	 */
	public static void escribeLogError(final String log, Class clazz, Throwable e) {
		if(LoggerFactory.getLogger(clazz).isErrorEnabled()) {
			LoggerFactory.getLogger(clazz).error(log,e);
		}
	}
}
