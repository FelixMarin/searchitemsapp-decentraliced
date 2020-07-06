package com.searchitemsapp.scraping.eroski;

import java.net.MalformedURLException;
import java.util.List;

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
 * extracción de datos del sitio web de Eroski.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingEroski implements IFScrapingEmpresas{
	

	/*
	 * Constructor 
	 */
	private ScrapingEroski() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la empresa Eroski.
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
			final SelectoresCssDTO selectorCssDto) throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(), this.getClass());
		
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
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.eroski"));
		
		/**
		 * Se añade la URL base a la lista en formato string.
		 */
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		/**
		 * Se compone la lista de URLs que se van a solicitar 
		 * al sitio web.
		 */
		for (int i = ClaseUtils.ONE_INT; i <= 20; i++) {
			listaUrls.add(urlBase.replace("=0&", "=".concat(String.valueOf(i).concat("&"))));
		}
		
		/**
		 * Se eliminan las URLs que superen el número maximo
		 * de resultados permitidos indicados en la variable
		 * 'numeroresultados'.
		 */
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}		
		
		return listaUrls;
	}

	/**
	 * Reemplaza los caracteres especiales que pueda tener
	 * el nombre del producto por sus correspondientes unicode. 
	 * <br>Ejemplo: "ñ" -> "$00f1".
	 *  
	 * @param producto
	 * @return String
	 */
	public String reemplazarCaracteres(final String producto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingEroski.class);
		
		String productoTratado = producto
				.replace(StringUtils.STRING_ENIE_MIN, StringUtils.ENIE_EROSKI);
		
		for (int i = ClaseUtils.ZERO_INT; i < StringUtils.arrayTildesEroski().length; i++) {
			productoTratado = productoTratado
					.replace(StringUtils.arrayTildesNormales()[i], StringUtils.arrayTildesEroski()[i]);
		}
		
		return productoTratado;
	}
}
