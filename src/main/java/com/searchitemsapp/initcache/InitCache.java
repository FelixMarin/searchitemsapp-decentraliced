package com.searchitemsapp.initcache;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.config.CommonsPorperties;

/**
 * Servlet que se ejecuta al arrancar la aplicación y carga
 * en el contexto todas la propiedades de los ficheros.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class InitCache implements ServletContextListener {
	
	private static final String PROPERTIES_SIA = System.getenv("PROPERTIES_SIA");
	private static final String CURRENT_FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String[] PROPERTIES_FILES = {"flow.properties","db.properties","log4j.properties"};
	private static final Logger LOGGER = LoggerFactory.getLogger(InitCache.class);     
	
    public InitCache() {
        super();
    }

    /**
     * Método que inicializa todas las propiedades desde los ficheros.
     * 
     * @param ServletContextEvent
     */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		try {
			for (int i = 0; i < PROPERTIES_FILES.length; i++) {
				CommonsPorperties.loadPropertiesFile(PROPERTIES_SIA
						.concat(CURRENT_FILE_SEPARATOR)
						.concat(PROPERTIES_FILES[i]), PROPERTIES_FILES[i], sce);
			}

		} catch (IOException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}	
	}

	/**
	 * Método que se encarga de destruir eventos cerrando 
	 * los grupos de conexiones y vaciando las cachés.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {		

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		for (int i = 0; i < PROPERTIES_FILES.length; i++) {
			sce.getServletContext().removeAttribute(PROPERTIES_FILES[i]);
		}
	}
}
