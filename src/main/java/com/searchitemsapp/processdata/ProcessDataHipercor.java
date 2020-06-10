package com.searchitemsapp.processdata;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.UrlDTO;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Hipercor.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataHipercor implements IFProcessDataEmpresas {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataHipercor.class);  
	
	/*
	 * Constasntes Globales
	 */
	private static final String PATTERN = ".*de ([0-9]+)";

	/*
	 * Constructor
	 */
	public ProcessDataHipercor() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la web de Hipercor.
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
			final UrlDTO urlDto) throws MalformedURLException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
		String selectorPaginacion = urlDto.getSelectores().get("SEL_PAGINACION");
		
		/**
		 * Se obbtiene del documento el número de resultados. 
		 */
		String strPaginacion = document.select(selectorPaginacion).text();
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */		
		int numresultados = NumberUtils.toInt(CommonsPorperties.getValue("flow.value.paginacion.url.hipercor"));
		
		/**
		 * Si el elemento de paginación coincide con el patrón es
		 * que hay más de una página web que hay que solicitar al 
		 * sitio web.
		 */
		Matcher m = Pattern.compile(PATTERN).matcher(strPaginacion);		
		if(m.find()) {
			strPaginacion=m.group(1);
		}
		
		/**
		 * Se desformatea el número de páginas que se pueden solicitar.
		 */
		int intPaginacion = NumberUtils.toInt(strPaginacion.trim());
		
		/**
		 * Se añade la URL base a la lista en formato string.
		 */
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		listaUrls.add(urlBase);
		
		/**
		 * Se compone la lista de URLs que se van a solicitar 
		 * al sitio web.
		 * El primer elemento de la lista es la URL base, por
		 * lo que se itera a partir de la segunda posición.
		 */
		for (int i = 2; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("/1/", "/".concat(String.valueOf(i).concat("/"))));
		}
		
		/**
		 * Se eliminan las URLs que superen el número maximo
		 * de resultados permitidos indicados en la variable
		 * 'numeroresultados'
		 */
		if(numresultados > 0 && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(0, numresultados);
		}		
		
		return listaUrls;
	}
}
