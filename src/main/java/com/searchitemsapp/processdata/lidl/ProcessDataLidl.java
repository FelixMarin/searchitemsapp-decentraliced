package com.searchitemsapp.processdata.lidl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.processdata.IFProcessDataEmpresas;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Lidl.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataLidl implements IFProcessDataEmpresas {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataLidl.class);  
	
	/*
	 * Constructor
	 */
	public ProcessDataLidl() {
		super();
	}

	
	/**
	 * Compone una lista de URLs de la empresa Lidl.
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
