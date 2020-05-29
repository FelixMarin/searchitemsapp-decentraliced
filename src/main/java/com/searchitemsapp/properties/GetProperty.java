package com.searchitemsapp.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Clases que gestiona el acceso a las propiedades
 * de un fichero properties. E constructor  recive 
 * como parámetro la ruta donde se ubica el 
 * fichero en formato String.
 * 
 * @author Felix Marin Ramirez
 * 
 */ 
public class GetProperty {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetProperty.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String NULL = "null";
	
	/*
	 * Variables Globales
	 */
	private static String propFileName;
	
	/*
	 * Constructor
	 */
	public GetProperty(String propFileName) {
		super();
		setPropFileName(propFileName);
	}

	/**
	 * El método devuelve una cadena que contiene el valor de la 
	 * propiedad. Si la propiedad no existe devuelve nulo.
	 * 
	 * @param key
	 * @return String
	 * @throws IOException
	 */
	public String getValue(String key) throws IOException {
			
		/**
		 * Valida el parámetro de entrada. Si es nulo
		 * termina el proceso y devuelve nulo.
		 * 
		 */
		if(Objects.isNull(key)) {
			return NULL;
		}
		
		StringBuilder debugMessage = new StringBuilder(NumberUtils.INTEGER_ONE);
		String value = NULL;
		
		/**
		 * Se leen la propiedades del fichero indicado.
		 */
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			Properties prop = new Properties();
 
			if (Objects.nonNull(inputStream)) {
				prop.load(inputStream);
			} else {				
				debugMessage.append("Error: ");
				debugMessage.append(propFileName);
				debugMessage.append(" not found in the classpath");
				
				throw new FileNotFoundException(debugMessage.toString());
			}
			
			value = prop.getProperty(key);
		}
		
		/**
		 * Se traza una entrada de log con 
		 * el valor del parámetro de entrada.
		 */
		debugMessage.append(StringUtils.EMPTY);
		debugMessage.append("value = ");
		debugMessage.append(value);
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),GetProperty.class);
		}
		
		return value;
	}

	/**
	 * Devuelve el nombre del fichero de propedades.
	 * 
	 * @return String
	 */
	public String getPropFileName() {
		return propFileName;
	}

	/**
	 * Establece el nombre del fichero de propiedades.
	 * 
	 * @param propFileName
	 */
	public void setPropFileName(String propFileName) {
		GetProperty.propFileName = propFileName;
	}
}
