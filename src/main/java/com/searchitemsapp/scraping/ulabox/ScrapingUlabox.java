package com.searchitemsapp.scraping.ulabox;

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
 * extracción de datos del sitio web de Ulabox.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingUlabox implements IFScrapingEmpresas {
	
	/*
	 * Constantes Globales
	 */
	private static final String PATTERN = ".*… ([0-9]+)";
	private static final String CHARSET = "…";

	/*
	 * Constructor
	 */
	public ScrapingUlabox() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la web de Ulabox.
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
		 * Se añade la URL base en la lista.
		 */
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		/**
		 * Se obbtiene del documento el número de resultados. 
		 */
		String selectorPaginacion = selectorCssDto.getSelPaginacion();		
		String strPaginacion = document.select(selectorPaginacion).text();
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */	
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.ulabox"));
		
		/**
		 * Si la variable de paginación no está
		 * vacía, se continua con el proceso
		 */
		if(!StringUtils.isEmpty(strPaginacion)) {
			
			/**
			 * Con esta validación se comprueba si la 
			 * busqueda ha devuelto uno o más resultados.
			 */
			if(strPaginacion.contains(CHARSET)) {
				
				Matcher m = StringUtils.matcher(PATTERN, strPaginacion);
				
				if(m.find()) {
					strPaginacion=m.group(ClaseUtils.ONE_INT);
				}
				
			} else {
				strPaginacion = strPaginacion.substring(strPaginacion.length()-1, strPaginacion.length());
			}
			
			/**
			 * La variable de paginación es el número de páginas en
			 * las que se va a realizar el rastreo del producto en 
			 * el sitio web. Se formatea a numérico y se asigna a 
			 * una variable.
			 */
			int intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());

			/**
			 * Se crean tantas URLs como indique el número de paginación.
			 */
			for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
				listaUrls.add(urlBase.concat("&p=") + i);
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
		}
		
		return listaUrls;
	}
}
