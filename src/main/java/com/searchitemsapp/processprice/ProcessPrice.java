package com.searchitemsapp.processprice;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static final String BARRA_KILO_GRAM = "/private static final Logger LOGGER.";
	private static final String KILO_GRAM = "private static final Logger LOGGER";
	private static final double DEFAULT_PRICE = 1000.00;
	private static final String XXX = "xxx";
	private static final String EMPTY_STRING = "";
	private static final String NULL_STRING = "null";
	private static final String PIPE_STRING = "|";
	private static final String COMMA_STRING = ",";
	private static final String DOT_STRING = ".";
	
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
				if(EMPTY_STRING.contentEquals(a.getPrecio())) {
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
				
			} else if(a.getOrdenacion() == 2) {
				
				/**
				 * En el caso de que el precio esté vacío,
				 * se le asigna un precio por defecto. Este precio 
				 * por defecto hará que el producto se vaya al final 
				 * de los resultados.
				 */
				if(Objects.isNull(a.getPrecioKilo()) && 
						EMPTY_STRING.contentEquals(a.getPrecioKilo()) || 
						XXX.contentEquals(a.getPrecioKilo())) {
					a.setPrecioKilo(String.valueOf(DEFAULT_PRICE));
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
				
				/**
				 * Se formatea el precio. Se quita la información
				 * no relevante.
				 */
				a.setPrecioKilo(extraerPrecioKilo(a.getPrecioKilo()));
				b.setPrecioKilo(extraerPrecioKilo(b.getPrecioKilo()));
				
				if(String.valueOf(DEFAULT_PRICE).equals(a.getPrecioKilo())) {
					a.setPrecioKilo(XXX);
				}
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
		if(EMPTY_STRING.contentEquals(strPrecioKilo)) {
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
		if(EMPTY_STRING.contentEquals(strPrecioKiloRes)) {
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
		if(EMPTY_STRING.contentEquals(cadena)) {
			return NULL_STRING;
		}else if(COMMA_STRING.equals(cadena)) {
			return DEFAULT_STR_PRICE;
		}
	     
	  String resultado = NULL_STRING;
	  
	  /**
	   * Se reemplazan los puntos por espacios vacíos.
	   */
	  String cadenaAux = cadena.replace(DOT_STRING, EMPTY_STRING);
	  
	  /**
	   * Si la cadena contiene un "|", se extrae de la misma
	   * la parte posterior de dicho "|" de la cadena. 
	   */
	  cadenaAux = extraerPrecioKilo(cadenaAux);
	  
	  /**
	   * Se comprueba si el valor extraido de la cadena es válido
	   * aplicándole el patrón regex para un número decimal. 
	   */
	  Matcher mDecimal = Pattern.compile(REGEX_NUMERO_DECIMAL, Pattern.MULTILINE).matcher(cadenaAux);
	  
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
	  if(!EMPTY_STRING.contentEquals(resultado)) {	  
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
		if(EMPTY_STRING.contentEquals(cadena)) {
			return NULL_STRING;
		}	
		
		String resultado = NULL_STRING;
		
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
		String cadenaAux = cadena.replace(DOT_STRING, EMPTY_STRING);
		
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
		if(EMPTY_STRING.contentEquals(resultado)) {
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
	 * Si el valor del precio por kilo está vacío se pone 
	 * el precio por unidad su lugar.
	 * 
	 * @param a
	 * @param b
	 */
	private static void comprobarPrecioKilo(ResultadoDTO a, ResultadoDTO b) {
		
		int iIndexOfA = -1;
		int iIndexOfB = -1;
		
		/**
		 * En el caso de que el valor del precio/kilo no esté vacío,
		 * se comprueba si dicho valor contiene el acronimo 'private static final Logger LOGGER'. Si
		 * contiene dicho acronimo, es que la variable viene informada
		 * con el valor correcto.
		 * 
		 */
		if(EMPTY_STRING.contentEquals(a.getPrecioKilo()) &&
				!EMPTY_STRING.contentEquals(a.getPrecio())) {
			iIndexOfA = a.getPrecio().toLowerCase().indexOf(KILO_GRAM);
		}
		if(EMPTY_STRING.contentEquals(b.getPrecioKilo()) &&
				!EMPTY_STRING.contentEquals(b.getPrecio())) {
			iIndexOfB = b.getPrecio().toLowerCase().indexOf(KILO_GRAM);
		}
		
		/**
		 * Si el valor index es negativo, se setea 
		 * el valor del precio por unidad al precio por kilo.
		 */
		if(iIndexOfA != -1) {
			a.setPrecioKilo( a.getPrecio());
		}		
		if(iIndexOfB != -1) {
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
		
		if(EMPTY_STRING.contentEquals(a.getPrecioKilo()) &&
				!EMPTY_STRING.contentEquals(a.getPrecio())) {
			a.setPrecioKilo(extraerDecimal(a.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
		
		if(EMPTY_STRING.contentEquals(b.getPrecioKilo()) &&
				!EMPTY_STRING.contentEquals(b.getPrecio())) {
			b.setPrecioKilo(extraerDecimal(b.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
	}
	
    /**
     * Si la cadena contiene un "|", se extrae de la misma
     * la parte posterior del "|" de la cadena. 
	 * 
	 * @param cadena
	 * @return String
	 */
	private static String extraerPrecioKilo(final String cadena) {

		String res;
		
		  if(cadena.contains(PIPE_STRING)) {
			  res = cadena.substring(cadena.lastIndexOf(PIPE_STRING)+1,cadena.length()).trim();
		  } else {
			  res = cadena;
		  }
		  
		  return res;
	}
}
