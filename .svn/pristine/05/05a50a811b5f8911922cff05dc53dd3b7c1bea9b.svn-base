package com.searchitemsapp.processprice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.UnsupportedAttributeException;

import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class ProcessPrice {

	private ProcessPrice() {
		super();
	}

	public static int processPrice(final ResultadoDTO a, final ResultadoDTO b) {
		
	int i= ClaseUtils.ZERO_INT;
		
		boolean bCheck = a.getOrdenacion() == ClaseUtils.ONE_INT || a.getOrdenacion() == ClaseUtils.TWO_INT;
		
		if(bCheck) {
			if(a.getOrdenacion() == ClaseUtils.ONE_INT) {
				
				if(StringUtils.isEmpty(a.getPrecio())) {
					a.setPrecio(String.valueOf(ClaseUtils.DEFAULT_PRICE));
				}
				
				i = convertirDouble(a.getPrecio())
						.compareTo(convertirDouble(b.getPrecio()));
				
			} else if(a.getOrdenacion() == ClaseUtils.TWO_INT) {
				
				if(StringUtils.isEmpty(a.getPrecioKilo())) {
					a.setPrecioKilo(String.valueOf(ClaseUtils.DEFAULT_PRICE));
				}
				
				comprobarPrecioKilo(a,b);
				mismoPrecioYPrecioKilo(a, b);
				
				i = convertirDouble(a.getPrecioKilo())
						.compareTo(convertirDouble(b.getPrecioKilo()));
			}
		} else {
			UnsupportedAttributeException e = new UnsupportedAttributeException("Error en el parametro de ordenacion", String.valueOf(a.getOrdenacion()));
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),ProcessPrice.class,e);
			throw e;
		}
		
		return i;
	}
	
private static Double convertirDouble(final String strPrecioKilo) {
		
		if(StringUtils.isEmpty(strPrecioKilo)) {
			return Double.parseDouble(StringUtils.DECIMALES);
		}
		
		String strPrecioKiloRes = extraerDecimal(strPrecioKilo);
		
		if(StringUtils.isEmpty(strPrecioKiloRes)) {
			strPrecioKiloRes = extraerEntero(strPrecioKilo);
		}
 
		strPrecioKiloRes = strPrecioKiloRes.trim();
		return Double.parseDouble(strPrecioKiloRes);
		
	}
	
	private static String extraerDecimal(final String cadena) {
		
		if(StringUtils.isEmpty(cadena)) {
			return StringUtils.NULL_STRING;
		}else if(StringUtils.COMMA_STRING.equals(cadena)) {
			return StringUtils.DEFAULT_STR_PRICE;
		}
	     
	  String resultado = StringUtils.NULL_STRING;
	  
	  String cadenaAux = cadena.replace(StringUtils.DOT_STRING, StringUtils.EMPTY_STRING);
	  
	  if(cadenaAux.contains(StringUtils.PIPE)) {
		  cadenaAux = cadenaAux.substring(cadenaAux.lastIndexOf(StringUtils.PIPE),cadenaAux.length());
	  }
	  
	  Matcher mDecimal = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadenaAux, Pattern.MULTILINE);
	  if (mDecimal.find()) {
	     resultado = mDecimal.group(ClaseUtils.ZERO_INT);
	  }
	  
	  if(!StringUtils.isEmpty(resultado)) {	  
		  resultado = resultado.replace(StringUtils.COMMA_STRING, StringUtils.DOT_STRING);
	  } else {
		  return extraerEntero(cadenaAux);
	  }
	  
	  return resultado;
	}
	
	private static String extraerEntero(final String cadena) {
		
		if(StringUtils.isEmpty(cadena)) {
			return StringUtils.NULL_STRING;
		}	
		
		String resultado = StringUtils.NULL_STRING;
		
		Matcher mEntero = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadena, Pattern.MULTILINE);
		  if(mEntero.find()) {
			  return StringUtils.DECIMALES;
		  }
		 
		String cadenaAux = cadena.replace(StringUtils.DOT_STRING, StringUtils.EMPTY_STRING);
		mEntero = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadenaAux, Pattern.MULTILINE);
	 
		  if(mEntero.find()) {
			  resultado = mEntero.group();
			  resultado = resultado.concat(StringUtils.DECIMALES);
		  }
		  
		  if(StringUtils.isEmpty(resultado)) {
			  mEntero = StringUtils.matcher(StringUtils.REGEX_INTEGER, cadenaAux, Pattern.MULTILINE);
			  
			  if(mEntero.find()) {
				  resultado = mEntero.group();
				  resultado = resultado.concat(StringUtils.DECIMALES);
			  }
		  }
		  
		  return resultado;
	}
	
	private static void comprobarPrecioKilo(ResultadoDTO a, ResultadoDTO b) {
		
		int iIndexOfA = ClaseUtils.ONE_NEGATIVE_INT;
		int iIndexOfB = ClaseUtils.ONE_NEGATIVE_INT;
		
		if(StringUtils.isEmpty(a.getPrecioKilo()) &&
				!StringUtils.isEmpty(a.getPrecio())) {
			iIndexOfA = a.getPrecio().toLowerCase().indexOf(StringUtils.KILO_GRAM);
		}
		
		if(StringUtils.isEmpty(b.getPrecioKilo()) &&
				!StringUtils.isEmpty(b.getPrecio())) {
			iIndexOfB = b.getPrecio().toLowerCase().indexOf(StringUtils.KILO_GRAM);
		}
		
		if(iIndexOfA != ClaseUtils.ONE_NEGATIVE_INT) {
			a.setPrecioKilo( a.getPrecio());
		}
		
		if(iIndexOfB != ClaseUtils.ONE_NEGATIVE_INT) {
			b.setPrecioKilo( b.getPrecio());
		}
	}
	
	private static void mismoPrecioYPrecioKilo(ResultadoDTO a, ResultadoDTO b) {
		
		if(StringUtils.isEmpty(a.getPrecioKilo()) &&
				!StringUtils.isEmpty(a.getPrecio())) {
			a.setPrecioKilo(extraerDecimal(a.getPrecio())
					.concat(StringUtils.BARRA_KILO_GRAM));
		}
		
		if(StringUtils.isEmpty(b.getPrecioKilo()) &&
				!StringUtils.isEmpty(b.getPrecio())) {
			b.setPrecioKilo(extraerDecimal(b.getPrecio())
					.concat(StringUtils.BARRA_KILO_GRAM));
		}
	}


}
