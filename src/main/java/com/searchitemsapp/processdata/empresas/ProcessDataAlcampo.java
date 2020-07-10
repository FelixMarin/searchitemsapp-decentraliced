package com.searchitemsapp.processdata.empresas;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.IFCommonsProperties;
import com.searchitemsapp.dto.UrlDTO;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Alcampo.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataAlcampo implements IFProcessDataEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataAlcampo.class);   
	
	/*
	 * Constantes Globales
	 */
	private static final String PATTERN = ".*&page=([0-9]+)";
	private static final String ZERO_STRING = "0";
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;

	/*
	 * Constructor
	 */
	public ProcessDataAlcampo() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la web de Alcampo.
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
	public List<String> getListaUrls(Document document, UrlDTO urlDto) 
			throws MalformedURLException {
		
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
		 * Se obbtiene del documento el número de resultados. 
		 */
		String selectorPaginacion = urlDto.getSelectores().get("SEL_PAGINACION");	
		String strPaginacion = ZERO_STRING;
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */		
		int numresultados = NumberUtils.toInt(iFCommonsProperties.getValue("flow.value.paginacion.url.alcampo"));
		
		/**
		 * Se crea una lista de Strings.
		 */
		List<String> liSelectorAtr = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Se divide el selector de paginación.
		 */
		StringTokenizer st = new StringTokenizer(selectorPaginacion,"|");  
		
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
		Elements elements = document.select(liSelectorAtr.get(0));
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
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
		String ultimoElemento = elements.get(elements.size()-1).attr(liSelectorAtr.get(1));
		
		/**
		 * Se comprueba si coincide con el patrón predefinido.
		 */
		Matcher m = Pattern.compile(PATTERN).matcher(ultimoElemento);
		
		/**
		 * Si hay coincidencia en la validación contra el patrón
		 * se asigna el valor a la variable de paginación.
		 */
		if(m.find()) {
			strPaginacion=m.group(1);
		}
		
		/**
		 * La variable de paginación es el número de páginas en
		 * las que se va a realizar el rastreo del producto en 
		 * el sitio web. Se formatea a numérico y se asigna a 
		 * una variable.
		 */
		int intPaginacion = NumberUtils.toInt(strPaginacion.trim());
		
		/**
		 * Se crean tantas URLs como indique el número de paginación.
		 */
		for (int i = 2; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("&page=1", "&page=".concat(String.valueOf(i))));
		}
		
		/**
		 * Se limita el número de sitios a los que realizar
		 * solicitudes html para optimizar el rendimiento.
		 * Este parámetro sed configura en el fichero de
		 * properties.
		 */
		if(numresultados > 0 && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(0, numresultados);
		}		
		
		return listaUrls;
	}
}
