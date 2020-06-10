package com.searchitemsapp.processdata;

import org.jsoup.nodes.Element;

public interface IFProcessDataCondis extends IFProcessDataEmpresas {

	String tratarTagScript(final Element elem, final String cssSelector);
	String eliminarTildesProducto(final String producto);
	String reemplazarCaracteres(final String producto);
}
