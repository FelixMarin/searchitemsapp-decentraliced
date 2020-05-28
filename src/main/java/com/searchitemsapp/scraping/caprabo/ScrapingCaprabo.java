package com.searchitemsapp.scraping.caprabo;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.AbsScrapingEmpresas;
import com.searchitemsapp.scraping.IFScrapingEmpresas;


/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Caprabo.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingCaprabo extends AbsScrapingEmpresas implements IFScrapingEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingCaprabo.class);   

	/*
	 * Constructor
	 */
	public ScrapingCaprabo() {
		super();
	}

	/**
	 * Compone una lista de URLs de la empresa Caprabo.
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
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto)
			throws MalformedURLException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Se obtiene la URL y se añade en una lista que
		 * será retornada.
		 */
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = new ArrayList<>(10);
		listaUrls.add(urlBase);
		
		return listaUrls;
	}
	
	

}
