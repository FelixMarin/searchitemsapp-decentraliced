package com.searchitemsapp.processprice;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.UnsupportedAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.ResultadoDTO;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPrice.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String DECIMALES = ".00";
	private static final String DEFAULT_STR_PRICE = "1000.00";
	private static final String REGEX_NUMERO_DECIMAL = "(\\d*[,][0-9]*)|([0-9]{1,9})";
	private static final String REGEX_INTEGER = "\\d+";
	private static final String BARRA_KILO_GRAM = "/kg";
	private static final double DEFAULT_PRICE = 1000.00;
	
	private static final String PIPE_STRING = "|";
	private static final String COMMA_STRING = ",";
	private static final String DOT_STRING = ".";
	
	private static final String EURO_SYMBOL = "/Eur";
	
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
		
		int i= 0;

		/**
		 * Se comprueba que el valor de ordenación sea válido.
		 */
		boolean bCheck = a.getOrdenacion() == 1 || a.getOrdenacion() == 2;
		
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
			if(a.getOrdenacion() == 1) {
				
				/**
				 * En el caso de que el precio esté vacío,
				 * se le asigna un precio por defecto. Este precio 
				 * por defecto hará que el producto se vaya al final 
				 * de los resultados.
				 */
				if(StringUtils.EMPTY.contentEquals(a.getPrecio())) {
					a.setPrecio(String.valueOf(DEFAULT_PRICE));
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
				
				/**
				 * Se formatea el precio. Se quita la información
				 * no relevante y se deja estandarizado for all
				 * los productos en el mismo formato.
				 */
				estilizarPrecios(a,b);
				
			} else if(a.getOrdenacion() == 2) {
				
				/**
				 * En el caso de que el precio esté vacío,
				 * se le asigna un precio por defecto. Este precio 
				 * por defecto hará que el producto se vaya al final 
				 * de los resultados.
				 */
				if(Objects.isNull(a.getPrecioKilo()) || 
						StringUtils.EMPTY.contentEquals(a.getPrecioKilo())) {
					a.setPrecioKilo(String.valueOf(DEFAULT_PRICE));
				}
				
				/**
				 * en el caso de que no vaya informado el precio por
				 * kilo, se le pone el precio por unidad por defecto.
				 */
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
				
				/**
				 * Se formatea el precio. Se quita la información
				 * no relevante y se deja estandarizado for all
				 * los productos en el mismo formato.
				 */
				estilizarPrecios(a,b);
			}
		} else {
			UnsupportedAttributeException e = new UnsupportedAttributeException("Error en el parametro de ordenacion", String.valueOf(a.getOrdenacion()));
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
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
		if(StringUtils.EMPTY.contentEquals(strPrecioKilo)) {
			return Double.parseDouble(DECIMALES);
		}
		
		/**
		 * Extrae el valor numérico de la cadena de caracteres.
		 */
		String strPrecioKiloRes = extraerDecimal(strPrecioKilo);
		
		/**
		 * Si extraer decimal no ha resultado, 
		 * se saca el valor numérico entero.
		 */
		if(StringUtils.EMPTY.contentEquals(strPrecioKiloRes)) {
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
		if(StringUtils.EMPTY.contentEquals(cadena)) {
			return StringUtils.EMPTY;
		}else if(COMMA_STRING.equals(cadena)) {
			return DEFAULT_STR_PRICE;
		}
	     
	  String resultado = StringUtils.EMPTY;
	  
	  /**
	   * Se reemplazan los puntos por espacios vacíos.
	   */
	  String cadenaAux = cadena.replace(DOT_STRING, StringUtils.EMPTY);
	  
	  /**
	   * Si la cadena contiene un "|", se extrae de la misma
	   * la parte posterior de dicho "|" de la cadena. 
	   */
	  if(cadenaAux.contains(PIPE_STRING)) {
		  cadenaAux = cadenaAux.substring(
				  cadenaAux.lastIndexOf(PIPE_STRING)+1,
				  cadenaAux.length()).trim().
				  replaceAll(StringUtils.SPACE
						  .concat(StringUtils.SPACE),
						  StringUtils.SPACE);
	  }
	  
	  /**
	   * Se comprueba si el valor extraido de la cadena es válido
	   * aplicándole el patrón regex para un número decimal. 
	   */
	  Matcher mDecimal = Pattern.compile(
			  REGEX_NUMERO_DECIMAL, Pattern.MULTILINE).matcher(cadenaAux);
	  
	  /**
	   * Si hay match, se asigna el valor a la variable de retorno.
	   * Se puede dar el cado de que no haya resultado, con lo que
	   * el valor de resultado será vacío.
	   */
	  if (mDecimal.find()) {
	     resultado = mDecimal.group(0);
	  }
	  
	  /**
	   * Si el resultado no es vacío en este punto, se reemplazan las 
	   * comas por puntos. En otro caso, la cadena auxiliar se 
	   * procesará como número entero.
	   */
	  if(!StringUtils.EMPTY.contentEquals(resultado)) {	  
		  resultado = resultado.replace(COMMA_STRING, DOT_STRING);
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
		if(StringUtils.EMPTY.contentEquals(cadena)) {
			return StringUtils.EMPTY;
		}	
		
		String resultado = StringUtils.EMPTY;
		
		  /**
		   * Se comprueba si el valor extraido de la cadena es válido
		   * aplicándole el patrón regex para un número entero. 
		   */
		Matcher mEntero = Pattern.compile(REGEX_NUMERO_DECIMAL, Pattern.MULTILINE).matcher(cadena);
		
		/**
		 * El valor en esta punto no puede ser decimal,
		 * Si lo fuera, el precio no se estaría procesando
		 * correctamente con lo que se devuelve un string 
		 * con un cero en formato decimal. ("0.00")
		 * 
		 */
		  if(mEntero.find()) {
			  return DECIMALES;
		  }
		 
		  /**
		   * Se reemplazan los puntos pro espacios en blanco
		   */
		String cadenaAux = cadena.replace(DOT_STRING, StringUtils.EMPTY);
		
		  /**
		   * Se comprueba si el valor extraido de la cadena es decimal
		   * aplicándole el patrón regex. 
		   */
		mEntero = Pattern.compile(REGEX_NUMERO_DECIMAL, Pattern.MULTILINE).matcher(cadenaAux);
		
		/**
		 * En el caso de que haya habido match, se extrae
		 * el primer grupo de valores que representan la parte
		 * entera del precio, y se le añaden dos ceros decimales.
		 */
	    if(mEntero.find()) {
		   resultado = mEntero.group();
		   resultado = resultado.concat(DECIMALES);
	    }

		/**
		 * Si el resultado obtenido en el paso anterio es vacío,
		 * se le vuelve a aplicar un patrón regex pero esta vez
		 * para un numero entero.
		 */
		if(StringUtils.EMPTY.contentEquals(resultado)) {
			  mEntero = Pattern.compile(REGEX_INTEGER, Pattern.MULTILINE).matcher(cadenaAux);
				/**
				 * En el caso de que haya habido match, se extrae
				 * el primer grupo de valores que representan la parte
				 * entera del precio, y se le añaden dos ceros decimales.
				 */
			if(mEntero.find()) {
				  resultado = mEntero.group();
				  resultado = resultado.concat(DECIMALES);
			}
		}
		
		return resultado;
	}
	
	/**
	 * Si la variable de peso por kilo está vacía se le aplica
	 * el valor del precio por unidad.
	 * 
	 * @param a
	 * @param b
	 */
	private static void mismoPrecioYPrecioKilo(ResultadoDTO a, ResultadoDTO b) {
		
		if(StringUtils.EMPTY.contentEquals(a.getPrecioKilo()) &&
				!StringUtils.EMPTY.contentEquals(a.getPrecio())) {
			a.setPrecioKilo(extraerDecimal(a.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
		
		if(StringUtils.EMPTY.contentEquals(b.getPrecioKilo()) &&
				!StringUtils.EMPTY.contentEquals(b.getPrecio())) {
			b.setPrecioKilo(extraerDecimal(b.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
	}
	
	private static void estilizarPrecios(final ResultadoDTO a, final ResultadoDTO b) {
		
		/**
		 * Se formatea el precio. Se quita la información
		 * no relevante.
		 */
		a.setPrecio(extraerDecimal(a.getPrecio())
				.concat(EURO_SYMBOL)
				.replace(DOT_STRING,COMMA_STRING));	
		
		b.setPrecio(extraerDecimal(b.getPrecio())
				.concat(EURO_SYMBOL)
				.replace(DOT_STRING,COMMA_STRING));
		
		//precio Kilo
		a.setPrecioKilo(extraerDecimal(a.getPrecioKilo())
				.concat(BARRA_KILO_GRAM)
				.replace(DOT_STRING,COMMA_STRING));	
		
		b.setPrecioKilo(extraerDecimal(b.getPrecioKilo())
				.concat(BARRA_KILO_GRAM)
				.replace(DOT_STRING,COMMA_STRING));
	}
}
