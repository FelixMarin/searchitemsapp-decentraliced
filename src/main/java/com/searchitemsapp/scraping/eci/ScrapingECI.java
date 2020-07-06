package com.searchitemsapp.scraping.eci;

import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de ECI.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingECI implements IFScrapingEmpresas {
	
	/*
	 * Constasntes Globales
	 */
	private static final String PATTERN = ".*de ([0-9]+)";

	/*
	 * Constructor
	 */
	public ScrapingECI() {
		super();
	}

	/**
	 * Compone una lista de URLs de la empresa ECI.
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
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Se obtiene la URL base. Esta es la URL principal 
		 * del conjunto de páginas obtenidas como resultado
		 * de la búsqueda del producto. A partir de esta URL 
		 * se generan las de paginación.
		 */
		String urlBase = urlDto.getNomUrl();
		
		/**
		 * Se crea un string con el selector de paginación.
		 */
		String selectorPaginacion = selectorCssDto.getSelPaginacion();

		/**
		 * Se obbtiene del documento el número de resultados. 
		 */
		String strPaginacion = document.select(selectorPaginacion).text();
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.eci"));
		
		/**
		 * Si el elemento de paginación coincide con el patrón es
		 * que hay más de una página web que hay que solicitar al 
		 * sitio web.
		 */
		Matcher m = StringUtils.matcher(PATTERN, strPaginacion);		
		if(m.find()) {
			strPaginacion=m.group(ClaseUtils.ONE_INT);
		}
		
		/**
		 * Se desformatea el número de páginas que se pueden solicitar.
		 */
		int intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());
		
		/**
		 * Se añade la URL base a la lista en formato string.
		 */
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		/**
		 * Se compone la lista de URLs que se van a solicitar 
		 * al sitio web.
		 * El primer elemento de la lista es la URL base, por
		 * lo que se itera a partir de la segunda posición.
		 */
		for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("/1/", "/".concat(String.valueOf(i).concat("/"))));
		}
		
		/**
		 * Se eliminan las URLs que superen el número maximo
		 * de resultados permitidos indicados en la variable
		 * 'numeroresultados'
		 */
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}	
		
		return listaUrls;
	}
}
