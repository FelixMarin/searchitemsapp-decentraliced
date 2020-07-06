package com.searchitemsapp.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * Esta clase carga los ficheros de propiedades 
 * que contienen los literales que serán utilizados 
 * durante el proceso.  
 * 
 * @author Felix Marin Ramirez
 * 
 */
public class CommonsPorperties {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonsPorperties.class);     
	
	/**
	 * Variables
	 */
	private static Properties properties;
	private static StandardEnvironment environment;

	/**
	 * Constructor
	 */
	private CommonsPorperties() {
		super();
	}
	
	/**
	 * Este método 
	 * 
	 * @param pathToProperties
	 * @param propName
	 * @param sce
	 * @throws IOException
	 */
	public static void loadPropertiesFile(final String pathToProperties, 
			final String propName, final ServletContextEvent sce) throws IOException {
		
		/**
		 * Se obtiene el fichero de propiedades.
		 */
        try (InputStream input = new FileInputStream(pathToProperties)) {
        	
        	/**
        	 * Se valida el objeto properties.
        	 */
        	if(Objects.isNull(properties)) {
        		properties = new Properties();
        	}
        	
        	/**
        	 * Se valida el objeto environment
        	 */
        	if(Objects.isNull(environment)) {
        		environment = new StandardEnvironment();
        	}
        	
        	/**
        	 * Lee el fichero de propiedades
        	 */
        	properties.load(input);
        	
        	/**
        	 * Se inserta el fichero de propiedades en 
        	 * como valores accesibles desde cualquier 
        	 * punto del sistema.
        	 */
        	environment.getPropertySources()
        	.addFirst(new PropertiesPropertySource(propName, properties));
        	
        	/**
        	 * Se añaden las propiedades en el contexto del sistema
        	 */
        	sce.getServletContext().setAttribute(propName, properties);
        }
    }
	
	/**
	 * A través de este método se obtienen los literales
	 * de los ficheros properties usados en todo el sistema.
	 * 
	 * @param String
	 * @return String
	 */
	public static String getValue(String value) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("Valor: ".concat(value));
		}
		
		return properties.getProperty(value);
	}
}
