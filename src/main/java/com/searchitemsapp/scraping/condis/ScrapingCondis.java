package com.searchitemsapp.scraping.condis;

import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.scraping.mercadona.ScrapingMercadona;
import com.searchitemsapp.scraping.simply.ScrapingSimply;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Condis.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingCondis implements IFScrapingEmpresas {

	/*
	 * Constantes Globales
	 */
	private static final String ENIE_CONDIS = "%D1";

	/*
	 * Constructor
	 */
	public ScrapingCondis() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la empresa Condis.
	 * Con estas URLs se realizarán las peticiones al
	 * sitio web para extraer los datos. 
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @return List<String>
	 * @exception MalformedURLException
	 */
	@Override
	public List<String> getListaUrls(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto)
					throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		/**
		 * Se obtiene la URL y se añade en una lista que
		 * será retornada.
		 */
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		return listaUrls;
	}
	
	/**
	 * 
	 * @param elem
	 * @param cssSelector
	 * @return String
	 */
	public String tratarTagScript(final Element elem, final String cssSelector) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String resultado = StringUtils.NULL_STRING;
		Matcher matcher;
		
		if(ClaseUtils.isNullObject(elem) || StringUtils.isEmpty(cssSelector)) {
			return resultado;
		}
		resultado = elem.select(cssSelector).html().replace(StringUtils.DOT_STRING, StringUtils.COMMA_STRING);
		
		if(countLines(resultado).length > ClaseUtils.ONE_INT) {
			resultado = countLines(resultado)[ClaseUtils.ONE_INT].trim();
		
			matcher = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, resultado);
			
			if(matcher.find()) {
				resultado = matcher.group(ClaseUtils.ZERO_INT);
			}
		
		} else {
			resultado = resultado.substring(resultado.indexOf('\'')+1, resultado.length());
			resultado = resultado.substring(ClaseUtils.ZERO_INT, resultado.indexOf('\''));
			
			if(resultado.length() == 3 &&
					resultado.substring(resultado.indexOf(','), 
							resultado.length()).length()  == ClaseUtils.TWO_INT) {
				resultado += StringUtils.ZERO_STRING;
			}
		}
		
		if(resultado.startsWith(StringUtils.COMMA_STRING)) {
			resultado = StringUtils.ZERO_STRING.concat(resultado);
		}
		
		if(resultado.endsWith(StringUtils.COMMA_STRING)) {
			resultado = resultado.concat("00");
		}
		
		return resultado;
	}
	
	/**
	 * Devuelve un array con el contenido en el String párametro
	 * Cada elemento del array contiene una linea del texto.
	 * 
	 * @param str
	 * @return String[]
	 */
	private String[] countLines(String str){
		   return str.split("\r\n|\r|\n");
	}
	/**
	 * Reemplaza los caracteres con tilde por los mismos
	 * caracteres sin tilde.
	 * 
	 * @param producto
	 * @return String
	 */
	public String eliminarTildesProducto(final String producto) {
		
		if(StringUtils.isEmpty(producto)) {
			return producto;
		}
		
		String[] vocalestildesMin = StringUtils.arrayTildesNormales();
		String[] vocalesMin = StringUtils.arrayVocalesMin();
		
		String productoAux = producto.toLowerCase();
		
		for (int i = 0; i < vocalesMin.length; i++) {
			productoAux = productoAux.replace(vocalestildesMin[i], vocalesMin[i]);
		}
		
		return productoAux;
	}
	
	/**
	 * Metodo que reemplaza el caracter 'ñ'en la URL por
	 * un caracter especial codigficado.
	 * 
	 * @param producto
	 * @return String
	 */
	public String reemplazarCaracteres(final String producto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingSimply.class);
		
		return producto.replace(StringUtils.STRING_ENIE_MIN, ENIE_CONDIS);
		
	}

}
