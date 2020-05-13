package com.searchitemsapp.processprice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.UnsupportedAttributeException;

import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Clase que prepara los precios precios de los productos
 * para posteriormete compararlos. Elimina los
 * caracteres no numéricos y después los compara.
 * Finalmente, devuelve el resultado de la comparación.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessPrice {

	/*
	 * Constructor 
	 */
	private ProcessPrice() {
		super();
	}

	/**
	 * Recibe dos objetos de con los precios de los productos. Los precios
	 * en formato string son tratados para poder ser convertidos en numeros
	 * decimales. Se comparan y se devuelve el resultado de la comparación.
	 * 
	 * @param a
	 * @param b
	 * @return int
	 * @exception UnsupportedAttributeException
	 */
	public static int processPrice(final ResultadoDTO a, final ResultadoDTO b) {
		
		int i= ClaseUtils.ZERO_INT;
		

		/**
		 * Se comprueba que el valor de ordenación sea válido.
		 */
		boolean bCheck = a.getOrdenacion() == ClaseUtils.ONE_INT || a.getOrdenacion() == ClaseUtils.TWO_INT;
		

		/**
		 * Si el valor de la ordenación es válido, se ejecuta el algoritmo,
		 * en otro caso, se genera una excepcion de tipo 'UnsupportedAttributeException'.
		 */
		if(bCheck) {
			
			/**
			 * Dependiendo del tipo de ordenación (por precio 
			 * o por precio/kilo) se ejecutará una estructura u 
			 * otra.
			 * 
			 *  	1. Precio
			 *  	2. Precio/Kilo
			 */
			if(a.getOrdenacion() == ClaseUtils.ONE_INT) {
				
				/**
				 * En el caso de que el precio esté vacío,
				 * se le asigna un precio por defecto. Este precio 
				 * por defecto hará que el producto se vaya al final 
				 * de los resultados.
				 */
				if(StringUtils.isEmpty(a.getPrecio())) {
					a.setPrecio(String.valueOf(ClaseUtils.DEFAULT_PRICE));
				}
				
				/**
				 *	Compara esta instancia con un número de punto flotante de 
				 * precisión doble especificado y devuelve un entero que indica 
				 * si el valor de esta instancia es mayor, menor o igual que el 
				 * valor del número de punto flotante de precisión doble 
				 * especificado.
				 */
				i = convertirDouble(a.getPrecio())
						.compareTo(convertirDouble(b.getPrecio()));
				
			} else if(a.getOrdenacion() == ClaseUtils.TWO_INT) {
				
				/**
				 * En el caso de que el precio esté vacío,
				 * se le asigna un precio por defecto. Este precio 
				 * por defecto hará que el producto se vaya al final 
				 * de los resultados.
				 */
				if(StringUtils.isEmpty(a.getPrecioKilo())) {
					a.setPrecioKilo(String.valueOf(ClaseUtils.DEFAULT_PRICE));
				}
				
				/**
				 * 
				 */
				comprobarPrecioKilo(a,b);
				mismoPrecioYPrecioKilo(a, b);
				
				/**
				 *	Compara esta instancia con un número de punto flotante de 
				 * precisión doble especificado y devuelve un entero que indica 
				 * si el valor de esta instancia es mayor, menor o igual que el 
				 * valor del número de punto flotante de precisión doble 
				 * especificado.
				 */
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
	
	/**
	 * Extrae el valor númerico de la cadena y lo convierte en double.
	 * 
	 * @param strPrecioKilo
	 * @return Double
	 */
	private static Double convertirDouble(final String strPrecioKilo) {
		
		/**
		 * Comprueba que el parametro de entrada sea valido.
		 */
		if(StringUtils.isEmpty(strPrecioKilo)) {
			return Double.parseDouble(StringUtils.DECIMALES);
		}
		
		/**
		 * Extrae el valor numérico de la cadena de caracteres.
		 */
		String strPrecioKiloRes = extraerDecimal(strPrecioKilo);
		
		/**
		 * Si extraer decimal no ha resultado, 
		 * se saca el valor numérico entero.
		 */
		if(StringUtils.isEmpty(strPrecioKiloRes)) {
			strPrecioKiloRes = extraerEntero(strPrecioKilo);
		}
 
		/**
		 * se eliminan los espacios en blanco que puedan
		 * quedar y se convierte el valor de retorno a double.
		 */
		strPrecioKiloRes = strPrecioKiloRes.trim();
		return Double.parseDouble(strPrecioKiloRes);
		
	}
	
	/**
	 * Método que contiene el Algoritmo encargado de 
	 * extraer el valor decimal correspondiente al precio 
	 * de la cadena en la que va insertado. 
	 * 
	 * @param cadena
	 * @return String
	 */
	private static String extraerDecimal(final String cadena) {
		
		/**
		 * Si la cadena de entrada está vacía o contiene una
		 * coma se retorna nulo.
		 */
		if(StringUtils.isEmpty(cadena)) {
			return StringUtils.NULL_STRING;
		}else if(StringUtils.COMMA_STRING.equals(cadena)) {
			return StringUtils.DEFAULT_STR_PRICE;
		}
	     
	  String resultado = StringUtils.NULL_STRING;
	  
	  /**
	   * Se reemplazan los puntos por espacios vacíos.
	   */
	  String cadenaAux = cadena.replace(StringUtils.DOT_STRING, StringUtils.EMPTY_STRING);
	  
	  /**
	   * Si la cadena contiene un pipe, se extrae de la misma
	   * la parte posterior de dicho pipe de la cadena. 
	   */
	  if(cadenaAux.contains(StringUtils.PIPE)) {
		  cadenaAux = cadenaAux.substring(cadenaAux.lastIndexOf(StringUtils.PIPE),cadenaAux.length());
	  }
	  
	  /**
	   * Se comprueba si el valor extraido de la cadena es válido
	   * aplicándole el patrón regex para un número decimal. 
	   */
	  Matcher mDecimal = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadenaAux, Pattern.MULTILINE);
	  
	  /**
	   * Si hay match, se asigna el valor a la variable de retorno.
	   * Se puede dar el cado de que no haya resultado, con lo que
	   * el valor de resultado será vacío.
	   */
	  if (mDecimal.find()) {
	     resultado = mDecimal.group(ClaseUtils.ZERO_INT);
	  }
	  
	  /**
	   * Si el resultado no es vacío en este punto, se reemplazan las 
	   * comas por puntos. En otro caso, la cadena auxiliar se 
	   * procesará como número entero.
	   */
	  if(!StringUtils.isEmpty(resultado)) {	  
		  resultado = resultado.replace(StringUtils.COMMA_STRING, StringUtils.DOT_STRING);
	  } else {
		  return extraerEntero(cadenaAux);
	  }
	  
	  return resultado;
	}
	
	/**
	 * Método que contiene el Algoritmo encargado de 
	 * extraer el valor entero correspondiente al precio 
	 * de la cadena en la que va insertado. 
	 * 
	 * @param cadena
	 * @return String
	 */
	private static String extraerEntero(final String cadena) {
		
		/**
		 * Se comprueba que el parámetro de entrada no sea nulo.
		 * 
		 */
		if(StringUtils.isEmpty(cadena)) {
			return StringUtils.NULL_STRING;
		}	
		
		String resultado = StringUtils.NULL_STRING;
		
		  /**
		   * Se comprueba si el valor extraido de la cadena es válido
		   * aplicándole el patrón regex para un número entero. 
		   */
		Matcher mEntero = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadena, Pattern.MULTILINE);
		
		/**
		 * El valor en esta punto no puede ser decimal,
		 * Si lo fuera, el precio no se estaría procesando
		 * correctamente con lo que se devuelve un string 
		 * con un cero en formato decimal. ("0.00")
		 * 
		 */
		  if(mEntero.find()) {
			  return StringUtils.DECIMALES;
		  }
		 
		  /**
		   * Se reemplazan los puntos pro espacios en blanco
		   */
		String cadenaAux = cadena.replace(StringUtils.DOT_STRING, StringUtils.EMPTY_STRING);
		
		  /**
		   * Se comprueba si el valor extraido de la cadena es decimal
		   * aplicándole el patrón regex. 
		   */
		mEntero = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, cadenaAux, Pattern.MULTILINE);
		
		/**
		 * En el caso de que haya habido match, se extrae
		 * el primer grupo de valores que representan la parte
		 * entera del precio, y se le añaden dos ceros decimales.
		 */
	    if(mEntero.find()) {
		   resultado = mEntero.group();
		   resultado = resultado.concat(StringUtils.DECIMALES);
	    }

		/**
		 * Si el resultado obtenido en el paso anterio es vacío,
		 * se le vuelve a aplicar un patrón regex pero esta vez
		 * para un numero entero.
		 */
		if(StringUtils.isEmpty(resultado)) {
			  mEntero = StringUtils.matcher(StringUtils.REGEX_INTEGER, cadenaAux, Pattern.MULTILINE);
			  
				/**
				 * En el caso de que haya habido match, se extrae
				 * el primer grupo de valores que representan la parte
				 * entera del precio, y se le añaden dos ceros decimales.
				 */
			if(mEntero.find()) {
				  resultado = mEntero.group();
				  resultado = resultado.concat(StringUtils.DECIMALES);
			}
		}
		
		return resultado;
	}
	
	/**
	 * Si el valor del precio por kilo está vacío se pone 
	 * el precio por unidad su lugar.
	 * 
	 * @param a
	 * @param b
	 */
	private static void comprobarPrecioKilo(ResultadoDTO a, ResultadoDTO b) {
		
		int iIndexOfA = ClaseUtils.ONE_NEGATIVE_INT;
		int iIndexOfB = ClaseUtils.ONE_NEGATIVE_INT;
		
		/**
		 * En el caso de que el valor del precio/kilo no esté vacío,
		 * se comprueba si dicho valor contiene el acronimo 'kg'. Si
		 * contiene dicho acronimo, es que la variable viene informada
		 * con el valor correcto.
		 * 
		 */
		if(StringUtils.isEmpty(a.getPrecioKilo()) &&
				!StringUtils.isEmpty(a.getPrecio())) {
			iIndexOfA = a.getPrecio().toLowerCase().indexOf(StringUtils.KILO_GRAM);
		}
		if(StringUtils.isEmpty(b.getPrecioKilo()) &&
				!StringUtils.isEmpty(b.getPrecio())) {
			iIndexOfB = b.getPrecio().toLowerCase().indexOf(StringUtils.KILO_GRAM);
		}
		
		/**
		 * Si el valor index es negativo, se setea 
		 * el valor del precio por unidad al precio por kilo.
		 */
		if(iIndexOfA != ClaseUtils.ONE_NEGATIVE_INT) {
			a.setPrecioKilo( a.getPrecio());
		}		
		if(iIndexOfB != ClaseUtils.ONE_NEGATIVE_INT) {
			b.setPrecioKilo( b.getPrecio());
		}
	}
	
	/**
	 * Si la variable de peso por kilo está vacía se le aplica
	 * el valor del precio por unidad.
	 * 
	 * @param a
	 * @param b
	 */
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
