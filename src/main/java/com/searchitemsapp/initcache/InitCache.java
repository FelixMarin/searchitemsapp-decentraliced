package com.searchitemsapp.initcache;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.util.LogsUtils;

/**
 * ServletContextListener implementation class InitCache
 */
public class InitCache implements ServletContextListener {
	
	private static final String PROPERTIES_SIA = System.getenv("PROPERTIES_SIA");
	private static final String CURRENT_FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String[] PROPERTIES_FILES = {"flow.properties","db.properties","log4j.properties"};
	       
    /**
     * @see ServletContextListener#ServletContextListener()
     */
    public InitCache() {
        super();
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),InitCache.class);
		
		try {
			for (int i = 0; i < PROPERTIES_FILES.length; i++) {
				CommonsPorperties.loadPropertiesFile(PROPERTIES_SIA
						.concat(CURRENT_FILE_SEPARATOR)
						.concat(PROPERTIES_FILES[i]), PROPERTIES_FILES[i], sce);
			}

		} catch (IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),InitCache.class,e);
		}	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),InitCache.class);
		
		for (int i = 0; i < PROPERTIES_FILES.length; i++) {
			sce.getServletContext().removeAttribute(PROPERTIES_FILES[i]);
		}
	}
}
