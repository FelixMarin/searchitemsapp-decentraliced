package com.searchitemsapp.processdata;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.UnsupportedAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
@Component
public class ProcessPriceModule implements IFProcessPrice {
	
	private transient final Logger LOGGER = LoggerFactory.getLogger(IFProcessPrice.class); 
	
	/*
	 * Constantes Globales
	 */
	private transient final String DECIMALES = ".00";
	private transient final String DEFAULT_STR_PRICE = "1000.00";
	private transient final String REGEX_NUMERO_DECIMAL = "(\\d*[,][0-9]*)|([0-9]{1,9})";
	private transient final String REGEX_INTEGER = "\\d+";
	private transient final String BARRA_KILO_GRAM = "/kg";
	private transient final double DEFAULT_PRICE = 1000.00;
	
	private transient final String PIPE_STRING = "|";
	private transient final String COMMA_STRING = ",";
	private transient final String DOT_STRING = ".";
	
	private transient final String EURO_SYMBOL = "/Eur";
	
	/*
	 * Variables Globales
	 */
	private int identificador;
	private String nomProducto;
	private String desProducto;
	private Integer didEmpresa;
	private String nomEmpresa;
	private String precioKilo;
	private String imagen;
	private String precio;
	private String nomUrl;
	private String loginUrl;
	private Integer didUrl;
	private boolean bolActivo;
	private boolean bolStatus;
	private Boolean bolLogin;	
	private String desUrl;
	private String nomUrlAllProducts;
	private int ordenacion;
	
	/*
	 * Constructor
	 */
	public ProcessPriceModule() {
		super();
	}
	
	/*
	 * Métodos Getters y Setters
	 */
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public String getImagen() {
		return imagen;	
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public Integer getDidEmpresa() {
		return didEmpresa;
	}
	public void setDidEmpresa(Integer didEmpresa) {
		this.didEmpresa = didEmpresa;
	}
	public String getPrecioKilo() {
		return precioKilo;
	}
	public void setPrecioKilo(String precioKilo) {
		this.precioKilo = precioKilo;
	}
	public String getNomUrl() {
		return nomUrl;
	}
	public void setNomUrl(String nomUrl) {
		this.nomUrl = nomUrl;
	}
	public Integer getDidUrl() {
		return didUrl;
	}
	public void setDidUrl(Integer didUrl) {
		this.didUrl = didUrl;
	}
	public boolean isBolActivo() {
		return bolActivo;
	}
	public void setBolActivo(boolean bolActivo) {
		this.bolActivo = bolActivo;
	}
	public boolean isBolStatus() {
		return bolStatus;
	}
	public void setBolStatus(boolean bolStatus) {
		this.bolStatus = bolStatus;
	}
	public Boolean isBolLogin() {
		return bolLogin;
	}
	public void setBolLogin(Boolean bolLogin) {
		this.bolLogin = bolLogin;
	}
	public String getDesUrl() {
		return desUrl;
	}
	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}
	public String getNomUrlAllProducts() {
		return nomUrlAllProducts;
	}
	public void setNomUrlAllProducts(String nomUrlAllProducts) {
		this.nomUrlAllProducts = nomUrlAllProducts;
	}
	public String getLoginnUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public int getOrdenacion() {
		return ordenacion;
	}
	public void setOrdenacion(int ordenacion) {
		this.ordenacion = ordenacion;
	}
	
	public String getNomEmpresa() {
		return nomEmpresa;
	}
	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
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
	public int processPrice(final IFProcessPrice a, final IFProcessPrice b) {
		
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
				if(StringUtils.isAllEmpty(a.getPrecio())) {
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
						StringUtils.isAllEmpty(a.getPrecioKilo())) {
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
	
	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public int compare(IFProcessPrice a, IFProcessPrice b) {
		return processPrice(a, b);
	}
	
	@Override
	public String toString() {
		return "IFProcessPrice [identificador=" + identificador + ", nomProducto=" + nomProducto + ", desProducto="
				+ desProducto + ", didEmpresa=" + didEmpresa + ", precioKilo=" + precioKilo + ", imagen=" + imagen
				+ ", precio=" + precio + ", nomEmpresa=" + nomEmpresa + ", nomUrl=" + nomUrl + ", loginUrl="
				+ loginUrl + ", didUrl=" + didUrl + ", bolActivo=" + bolActivo + ", bolStatus=" + bolStatus
				+ ", bolLogin=" + bolLogin + ", desUrl=" + desUrl + ", nomUrlAllProducts=" + nomUrlAllProducts
				+ ", ordenacion=" + ordenacion + "]";
	}
	
	/*
	 * Métodos privados 
	 */
	
	/**
	 * Extrae el valor númerico de la cadena y lo convierte en double.
	 * 
	 * @param strPrecioKilo
	 * @return Double
	 */
	private  Double convertirDouble(final String strPrecioKilo) {
		
		/**
		 * Extrae el valor numérico de la cadena de caracteres.
		 */
		String strPrecioKiloRes = extraerDecimal(strPrecioKilo);
		
		/**
		 * Si extraer decimal no ha resultado, 
		 * se saca el valor numérico entero.
		 */
		if(StringUtils.isAllEmpty(strPrecioKiloRes)) {
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
	private  String extraerDecimal(final String cadena) {
		
		/**
		 * Si la cadena de entrada está vacía o contiene una
		 * coma se retorna nulo.
		 */
		if(StringUtils.isAllEmpty(cadena)) {
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
	  if(!StringUtils.isAllEmpty(resultado)) {	  
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
	private  String extraerEntero(final String cadena) {
		
		/**
		 * Se comprueba que el parámetro de entrada no sea nulo.
		 * 
		 */
		if(StringUtils.isAllEmpty(cadena)) {
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
		if(StringUtils.isAllEmpty(resultado)) {
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
	private  void mismoPrecioYPrecioKilo(IFProcessPrice a, IFProcessPrice b) {
		
		if(StringUtils.isAllEmpty(a.getPrecioKilo()) &&
				!StringUtils.isAllEmpty(a.getPrecio())) {
			a.setPrecioKilo(extraerDecimal(a.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
		
		if(StringUtils.isAllEmpty(b.getPrecioKilo()) &&
				!StringUtils.isAllEmpty(b.getPrecio())) {
			b.setPrecioKilo(extraerDecimal(b.getPrecio())
					.concat(BARRA_KILO_GRAM));
		}
	}
	
	private  void estilizarPrecios(final IFProcessPrice a, final IFProcessPrice b) {
		
		/**
		 * Se formatea el precio. Se quita la información
		 * no relevante.
		 */
		if(!StringUtils.isAllEmpty(a.getPrecio()) &&
				!StringUtils.isAllEmpty(a.getPrecio())) {
		a.setPrecio(extraerDecimal(a.getPrecio())
				.concat(EURO_SYMBOL)
				.replace(DOT_STRING,COMMA_STRING));	
		
		b.setPrecio(extraerDecimal(b.getPrecio())
				.concat(EURO_SYMBOL)
				.replace(DOT_STRING,COMMA_STRING));
		}
		
		//precio Kilo
		if(!StringUtils.isAllEmpty(a.getPrecioKilo()) &&
				!StringUtils.isAllEmpty(a.getPrecioKilo())) {
				a.setPrecioKilo(extraerDecimal(a.getPrecioKilo())
				.concat(BARRA_KILO_GRAM)
				.replace(DOT_STRING,COMMA_STRING));	
		
				b.setPrecioKilo(extraerDecimal(b.getPrecioKilo())
				.concat(BARRA_KILO_GRAM)
				.replace(DOT_STRING,COMMA_STRING));	
		}
	}
	
	public List<IFProcessPrice> ordenarLista(List<IFProcessPrice> lista) {
		Collections.sort(lista, this);
		return lista;
	}
}
