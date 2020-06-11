package com.searchitemsapp.processdata.empresas;

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
import com.searchitemsapp.processdata.interfaces.IFProcessDataEmpresas;



/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Dia.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ProcessDataDia implements IFProcessDataEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataDia.class);   
	
	/*
	 * Constantes Globales
	 */
	private static final String PROTOCOL_ACCESSOR ="://";

	/*
	 * Constructor
	 */
	public ProcessDataDia() {
		super();
	}

	/**
	 * Compone una lista de URLs de la empresa Consum.
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
		 * Se obtiene la URL base. Esta es la URL principal 
		 * del conjunto de páginas obtenidas como resultado
		 * de la búsqueda del producto. A partir de esta URL 
		 * se generan las de paginación.
		 */
		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = urlDto.getSelectores().get("SEL_PAGINACION");
		int numresultados = NumberUtils.toInt(CommonsPorperties.getValue("flow.value.paginacion.url.dia"));

		/**
		 * Se divide el selector de paginación.
		 */
		StringTokenizer st = new StringTokenizer(selectorPaginacion, "|");
		List<String> liSelectorAtr = new ArrayList<>(NumberUtils.INTEGER_ONE);

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
		 * Se añade la URL base en la lista.
		 */
		listaUrls.add(urlBase);

		/**
		 * Se crea un objeto de tipo URL a partir de la URL base.
		 * y después se crea una cadena con una nueva URL. Esto
		 * se hace para componer la URL del producto a buscar.
		 * 
		 */
		URL url = new URL(urlBase);
		String strUrlEmpresa = url.getProtocol().concat(PROTOCOL_ACCESSOR).concat(url.getHost());

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
