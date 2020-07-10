package com.searchitemsapp.processdata.empresas;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;


/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Caprabo.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataCaprabo implements IFProcessDataEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataCaprabo.class);   

	/*
	 * Constructor
	 */
	public ProcessDataCaprabo() {
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
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		listaUrls.add(urlBase);
		
		return listaUrls;
	}
	
	

}
