package com.searchitemsapp.scraping.alcampo;

import java.net.MalformedURLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Alcampo.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingAlcampo implements IFScrapingEmpresas{
	
	/*
	 * Constantes Globales
	 */
	private static final String PATTERN = ".*&page=([0-9]+)";

	/*
	 * Constructor
	 */
	public ScrapingAlcampo() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la empresa Alcampo.
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
	public List<String> getListaUrls(Document document, UrlDTO urlDto, 
			SelectoresCssDTO selectorCssDto) throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Se establcen las variables necesarias para la ejecución.
		 */
		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = selectorCssDto.getSelPaginacion();	
		String strPaginacion = StringUtils.ZERO_STRING;
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.alcampo"));
		int intPaginacion;
		
		/**
		 * Se crea una lista de Strings.
		 */
		List<String> liSelectorAtr = StringUtils.getNewListString();
		
		/**
		 * Se divide el selector de paginación.
		 */
		StringTokenizer st = new StringTokenizer(selectorPaginacion,StringUtils.PIPE);  
		
		
		/**
		 * Se añaden todos los tokens en la lista de selectores 
		 */
		while (st.hasMoreTokens()) {  
			liSelectorAtr.add(st.nextToken());
		}
		
		/**
		 * Se obtienen todos los elementos que interesan del documento
		 * utilizando el selector css.
		 */
		Elements elements = document.select(liSelectorAtr.get(ClaseUtils.ZERO_INT));
		List<String> listaUrls = StringUtils.getNewListString();
		
		/**
		 * Si la lista de elementos no está vacía
		 * se devuelve la lista de URLs con solamente
		 * la URL base.
		 */
		if(elements.isEmpty()) {
			listaUrls.add(urlBase);
			return listaUrls;
		}
		
		/**
		 * Se obtiene el último elemento. Este último elemento
		 * contiene el número máximo de paginas de resultados.
		 */
		String ultimoElemento = elements.get(elements.size()-1).attr(liSelectorAtr.get(ClaseUtils.ONE_INT));
		
		/**
		 * Se comprueba si coincide con el patrón predefinido.
		 */
		Matcher m = StringUtils.matcher(PATTERN, ultimoElemento);
		
		/**
		 * Si hay coincidencia en la validación contra el patrón
		 * se asigna el valor a la variable de paginación.
		 */
		if(m.find()) {
			strPaginacion=m.group(ClaseUtils.ONE_INT);
		}
		
		/**
		 * La variable de paginación es el número de páginas en
		 * las que se va a realizar el rastreo del producto en 
		 * el sitio web. Se formatea a numérico y se asigna a 
		 * una variable.
		 */
		intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());
		
		/**
		 * Se crean tantas URLs como indique el número de paginación.
		 */
		for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("&page=1", "&page=".concat(String.valueOf(i))));
		}
		
		/**
		 * Se limita el número de sitios a los que realizar
		 * solicitudes html para optimizar el rendimiento.
		 * Este parámetro sed configura en el fichero de
		 * properties.
		 */
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}		
		
		return listaUrls;
	}

}
