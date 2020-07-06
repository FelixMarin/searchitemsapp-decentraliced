package com.searchitemsapp.scraping.condis;

import org.jsoup.nodes.Element;

import com.searchitemsapp.scraping.IFScrapingEmpresas;

public interface IFScracpingCondis extends IFScrapingEmpresas {

	String tratarTagScript(final Element elem, final String cssSelector);
	String eliminarTildesProducto(final String producto);
	String reemplazarCaracteres(final String producto);
}
