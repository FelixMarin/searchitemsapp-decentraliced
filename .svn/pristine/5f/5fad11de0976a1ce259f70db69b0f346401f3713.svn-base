package com.searchitemsapp.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;
 
/**
 * @author search Items Application
 * 
 */
 
public class GetProperty {
	
	
	private String propFileName;
	
	public GetProperty(String propFileName) {
		super();
		this.propFileName = propFileName;
	}

	public String getValue(String key) throws IOException {
			
		if(StringUtils.validateNull(key)) {
			return StringUtils.NULL_STRING;
		}
		
		StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		String value = StringUtils.NULL_STRING;
		
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
		
		debugMessage.append(StringUtils.EMPTY_STRING);
		debugMessage.append("value = ");
		debugMessage.append(value);
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),GetProperty.class);
		
		return value;
	}

	public String getPropFileName() {
		return propFileName;
	}

	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}
}
