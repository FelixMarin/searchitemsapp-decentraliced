package com.searchitemsapp.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;
 
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
	
	
	private String propFileName;
	
	public GetProperty(String propFileName) {
		super();
		this.propFileName = propFileName;
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
		if(StringUtils.validateNull(key)) {
			return StringUtils.NULL_STRING;
		}
		
		StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		String value = StringUtils.NULL_STRING;
		
		/**
		 * Se leen la propiedades del fichero indicado.
		 */
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			Properties prop = new Properties();
 
			if (!ClaseUtils.isNullObject(inputStream)) {
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
		debugMessage.append(StringUtils.EMPTY_STRING);
		debugMessage.append("value = ");
		debugMessage.append(value);
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),GetProperty.class);
		
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
		this.propFileName = propFileName;
	}
}
