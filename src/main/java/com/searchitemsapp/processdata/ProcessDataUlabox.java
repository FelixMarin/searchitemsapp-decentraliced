package com.searchitemsapp.processdata;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.UrlDTO;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Ulabox.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataUlabox implements IFProcessDataEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataUlabox.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String PATTERN = ".*… ([0-9]+)";
	private static final String CHARSET = "…";

	/*
	 * Constructor
	 */
	public ProcessDataUlabox() {
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
		 * Se añade la URL base en la lista.
		 */
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		listaUrls.add(urlBase);
		
		/**
		 * Se obbtiene del documento el número de resultados. 
		 */
		String selectorPaginacion = urlDto.getSelectores().get("SEL_PAGINACION");		
		String strPaginacion = document.select(selectorPaginacion).text();
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */	
		int numresultados = NumberUtils.toInt(CommonsPorperties.getValue("flow.value.paginacion.url.ulabox"));
		
		/**
		 * Si la variable de paginación no está
		 * vacía, se continua con el proceso
		 */
		if(!StringUtils.EMPTY.contentEquals(strPaginacion)) {
			
			/**
			 * Con esta validación se comprueba si la 
			 * busqueda ha devuelto uno o más resultados.
			 */
			if(strPaginacion.contains(CHARSET)) {
				
				Matcher m = Pattern.compile(PATTERN).matcher(strPaginacion);
				
				if(m.find()) {
					strPaginacion=m.group(1);
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
			int intPaginacion = NumberUtils.toInt(strPaginacion.trim());

			/**
			 * Se crean tantas URLs como indique el número de paginación.
			 */
			for (int i = 2; i <= intPaginacion; i++) {
				listaUrls.add(urlBase.concat("&p=") + i);
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
		}
		
		return listaUrls;
	}
}