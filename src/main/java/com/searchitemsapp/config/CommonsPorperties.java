package com.searchitemsapp.config;

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
import org.springframework.stereotype.Component;

/**
 * Esta clase carga los ficheros de propiedades 
 * que contienen los literales que serán utilizados 
 * durante el proceso.  
 * 
 * @author Felix Marin Ramirez
 * 
 */
@Component
public class CommonsPorperties implements IFCommonsProperties {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonsPorperties.class);     
	
	private static Properties properties;
	private static StandardEnvironment environment;

	private CommonsPorperties() {
		super();
	}
	
	public static void loadPropertiesFile(final String pathToProperties, 
			final String propName, final ServletContextEvent sce) throws IOException {
	
        try (InputStream input = new FileInputStream(pathToProperties)) {

        	if(Objects.isNull(properties)) {
        		properties = new Properties();
        	}
        	
         	if(Objects.isNull(environment)) {
        		environment = new StandardEnvironment();
        	}
        	
        	properties.load(input);
        	
        	environment.getPropertySources()
        	.addFirst(new PropertiesPropertySource(propName, properties));
        	
        	sce.getServletContext().setAttribute(propName, properties);
        }
    }
	
	public String getValue(String value) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("Valor: ".concat(value));
		}
		
		return properties.getProperty(value);
	}
}
