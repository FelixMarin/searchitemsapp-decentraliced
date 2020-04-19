package com.searchitemsapp.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import com.searchitemsapp.initcache.InitCache;
import com.searchitemsapp.util.LogsUtils;

public class Log4jInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String LOG4J_APPENDER_FILE_FILE = "log4j.appender.file.File";
	private static final String LOG4J_INIT_FILE = "log4j-init-file";
	private static final String PROPERTIES_SIA = "PROPERTIES_SIA";	
	private static final String CURRENT_FILE_SEPARATOR = System.getProperty("file.separator");

	public Log4jInit() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),Log4jInit.class);
		
		String prefix = System.getenv(PROPERTIES_SIA);
		String file = config.getInitParameter(LOG4J_INIT_FILE);
		String filePath = prefix.concat(CURRENT_FILE_SEPARATOR).concat(file);
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			String logFile = prefix + props.getProperty(LOG4J_APPENDER_FILE_FILE);
			props.setProperty(LOG4J_APPENDER_FILE_FILE, logFile);
			PropertyConfigurator.configure(props);
		} catch (IOException e) {
			LogsUtils.escribeLogError("Could not read configuration file [" + filePath + "].",InitCache.class,e);
			LogsUtils.escribeLogError("Ignoring configuration file [" + filePath + "].",InitCache.class,e);
		}
	}
}
