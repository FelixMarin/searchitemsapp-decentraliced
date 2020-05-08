package com.searchitemsapp.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

/**
 * 
 * @author Felix Marin Ramirez
 * 
 * Esta clase carga los ficheros de propiedades con todos los literales. 
 */
public class CommonsPorperties {
	
	private static Properties properties;
	private static StandardEnvironment environment;

	private CommonsPorperties() {
		super();
	}
	
	public static void loadPropertiesFile(final String pathToProperties, 
			final String propName, final ServletContextEvent sce) throws IOException {
		
        try (InputStream input = new FileInputStream(pathToProperties)) {
        	
        	if(ClaseUtils.isNullObject(properties)) {
        		properties = new Properties();
        	}
        	
        	if(ClaseUtils.isNullObject(environment)) {
        		environment = new StandardEnvironment();
        	}
        	
        	properties.load(input);
        	
        	environment.getPropertySources()
        	.addFirst(new PropertiesPropertySource(propName, properties));
        	
        	sce.getServletContext().setAttribute(propName, properties);
        }
    }
	
	public static String getValue(String value) {
		
		LogsUtils.escribeLogDebug("Valor: ".concat(value),CommonsPorperties.class);
		
		return properties.getProperty(value);
	}
}
