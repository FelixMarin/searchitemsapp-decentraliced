package com.searchitemsapp.scraping.dia;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Dia.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingDia  implements IFScrapingEmpresas {

	/*
	 * Constructor
	 */
	public ScrapingDia() {
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
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto,
			final SelectoresCssDTO selectorCssDto) 
					throws MalformedURLException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(), this.getClass());

		/**
		 * Se obtiene la URL base. Esta es la URL principal 
		 * del conjunto de páginas obtenidas como resultado
		 * de la búsqueda del producto. A partir de esta URL 
		 * se generan las de paginación.
		 */
		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = selectorCssDto.getSelPaginacion();
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.dia"));

		/**
		 * Se divide el selector de paginación.
		 */
		StringTokenizer st = new StringTokenizer(selectorPaginacion, StringUtils.PIPE);
		List<String> liSelectorAtr = StringUtils.getNewListString();

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
		Elements elements = document.select(liSelectorAtr.get(ClaseUtils.ZERO_INT));
		List<String> listaUrls = StringUtils.getNewListString();

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
		String strUrlEmpresa = url.getProtocol().concat(StringUtils.PROTOCOL_ACCESSOR).concat(url.getHost());

		for (Element element : elements) {
			listaUrls.add(strUrlEmpresa.concat(element.attr(liSelectorAtr.get(ClaseUtils.ONE_INT))));
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
		
		return listaUrls;
	}
}
