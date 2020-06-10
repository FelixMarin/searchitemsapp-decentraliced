package com.searchitemsapp.processdata.condis;

import org.jsoup.nodes.Element;

import com.searchitemsapp.processdata.IFProcessDataEmpresas;

public interface IFProcessDataCondis extends IFProcessDataEmpresas {

	String tratarTagScript(final Element elem, final String cssSelector);
	String eliminarTildesProducto(final String producto);
	String reemplazarCaracteres(final String producto);
}
