package com.searchitemsapp.scraping.simply;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.AbsScrapingEmpresas;
import com.searchitemsapp.scraping.IFScrapingEmpresas;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Simply.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingSimply extends AbsScrapingEmpresas implements IFScrapingEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingSimply.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String STRING_ENIE_MIN = "ñ";
	private static final String ENIE_URL = "%F1";
	
	/*
	 * Constructor
	 */
	private ScrapingSimply() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la web de Simply.
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
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto, 
			final SelectoresCssDTO selectorCssDto)
			throws MalformedURLException {
  
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/*
		 * Variable locales
		 */
		String urlAux;
		int fin = 30;
		int max = 10;
		int incremento = 2;
		
		/**
		 * Se obtiene la URL base. Esta es la URL principal 
		 * del conjunto de páginas obtenidas como resultado
		 * de la búsqueda del producto. A partir de esta URL 
		 * se generan las de paginación.
		 */
		String urlBase = urlDto.getNomUrl();
		
		/**
		 * Se obtiene del fichero de propiedades el número máximo de
		 * páginas que se van a pedir al sitio web.
		 */	
		int numresultados = desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.simply"));
		
		/**
		 * Se asigna la url base a la lista.
		 */
		List<String> listaUrls = new ArrayList<>(10);
		listaUrls.add(urlBase);
		
		/**
		 * Bucle que compone la lista de URLs de las que se va a
		 * realizar la extracción de datos.
		 */
		for (int i = 2; i <= max; i++) {
			urlAux = urlBase.replace("=1&", "=".concat(String.valueOf(i).concat("&")));
			
			if(i == 2) {
				urlAux = urlAux.replace("&fin=10", "&fin=" + fin);
			} else {
				urlAux = urlAux.replace("&fin=10", "&fin=" + fin*incremento++);
			}
			
			listaUrls.add(urlAux);
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

	/**
	 * Reemplaza los caracteres especiales y los transforma en
	 * caracteres unicode.<br>
	 * Ejemplo: <b>"ñ" => "%F1"</b>
	 * 
	 * @param producto
	 * @return String
	 */
	public String reemplazarCaracteres(final String producto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return producto.replace(STRING_ENIE_MIN, ENIE_URL);
	}
}
