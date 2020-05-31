package com.searchitemsapp.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ListaProductosValidator {
	
	public List<String> checkInputParams(List<String> input) {
		
		List<String> output = new ArrayList<String>(NumberUtils.INTEGER_ONE);
		
		for (String value : input) {
			value = value.replaceAll("\\*", StringUtils.EMPTY);
			value = value.replaceAll("/", StringUtils.EMPTY);
			value = value.replaceAll("//", StringUtils.EMPTY);
			value = value.replaceAll("<", StringUtils.EMPTY).replaceAll(">", StringUtils.EMPTY);
			value = value.replaceAll("\\{", StringUtils.EMPTY).replaceAll("\\}", StringUtils.EMPTY);
			value = value.replaceAll("\\[", StringUtils.EMPTY).replaceAll("\\]", StringUtils.EMPTY);
			value = value.replaceAll("\\(", StringUtils.EMPTY).replaceAll("\\)", StringUtils.EMPTY);
			value = value.replaceAll("'", StringUtils.EMPTY);
			value = value.replaceAll("eval\\((.*)\\)", StringUtils.EMPTY);
			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", StringUtils.EMPTY);
			value = value.replaceAll("script", StringUtils.EMPTY);
			value = value.replaceAll("select", StringUtils.EMPTY).replaceAll("SELECT", StringUtils.EMPTY);
			value = value.replaceAll("insert", StringUtils.EMPTY).replaceAll("INSERT", StringUtils.EMPTY);
			value = value.replaceAll("delete", StringUtils.EMPTY).replaceAll("DELETE", StringUtils.EMPTY);
			value = value.replaceAll("alter", StringUtils.EMPTY).replaceAll("ALTER", StringUtils.EMPTY);
			value = value.replaceAll("drop", StringUtils.EMPTY).replaceAll("DROP", StringUtils.EMPTY);
			output.add(value);
		}
		return output;
	}
	
	public boolean validate(List<String> input) {
		
		for (String value : input) {
			if(StringUtils.isBlank(value)) {
				return true;
			}
		}
		
		return false;
	}
}
