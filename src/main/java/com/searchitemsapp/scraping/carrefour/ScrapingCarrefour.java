package com.searchitemsapp.scraping.carrefour;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Carrefour.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingCarrefour implements IFScrapingEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingCarrefour.class);   

	/*
	 * Constantes Glogables
	 */
	private static final String PROTOCOL_ACCESSOR ="://";
	
	/*
	 * Constructor
	 */
	public ScrapingCarrefour() {
		super();
	}

	/**
	 * Compone una lista de URLs de la empresa Carrefour.
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
		 * Se obbtiene del documento el número de resultados. 
		 */
		String selectorPaginacion = urlDto.getSelectores().get("SEL_PAGINACION");	
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */	
		int numresultados = NumberUtils.toInt(CommonsPorperties.getValue("flow.value.paginacion.url.carrefour"));
		
		/**
		 * Se divide el selector de paginación.
		 */
		StringTokenizer st = new StringTokenizer(selectorPaginacion,"|");  
		List<String> liSelectorAtr = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Se añaden todos los tokens en la lista de selectores 
		 */
		while (st.hasMoreTokens()) {  
			liSelectorAtr.add(st.nextToken());
		}
		
		/**
		 * Se crea una lista de Strings.
		 */		
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Se divide el selector de paginación.
		 */
		Elements elements = document.select(liSelectorAtr.get(0));
		
		/**
		 * Se asigna la url base a la lista.
		 */
		listaUrls.add(urlBase);
		
		/**
		 * Se crea un objeto de tipo URL a partir de la URL base.
		 * y después se crea una cadena con una nueva URL. Esto
		 * se hace para componer la URL del producto a buscar.
		 * 
		 */
		URL url = new URL(urlBase);
		String strUrlEmpresa = url.getProtocol()
				.concat(PROTOCOL_ACCESSOR).concat(url.getHost());
		
		for (Element element : elements) {
			listaUrls.add(strUrlEmpresa.concat(element.attr(liSelectorAtr.get(1))));
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
