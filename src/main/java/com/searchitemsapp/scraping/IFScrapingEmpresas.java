package com.searchitemsapp.scraping;

import java.net.MalformedURLException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;

/**
 * Intefaz que implementan los módulos de raspado 
 * de las empresas.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFScrapingEmpresas {

	public abstract List<String> getListaUrls(final Document document, final UrlDTO urlDto,
			final SelectoresCssDTO selectorCssDto) throws MalformedURLException;
}
