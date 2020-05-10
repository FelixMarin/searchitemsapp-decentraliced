package com.searchitemsapp.scraping.eci;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class ScrapingECI implements IFScrapingEmpresas {
	
	private static final String PATTERN = ".*de ([0-9]+)";

	public ScrapingECI() {
		super();
	}

	@Override
	public List<String> getListaUrls(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = selectorCssDto.getSelPaginacion();		
		String strPaginacion = document.select(selectorPaginacion).text();
		List<String> listaUrls = StringUtils.getNewListString();
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.eci"));
		
		Matcher m = StringUtils.matcher(PATTERN, strPaginacion);
		
		if(m.find()) {
			strPaginacion=m.group(ClaseUtils.ONE_INT);
		}
		
		int intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());
		
		listaUrls.add(urlBase);
		
		for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("/1/", "/".concat(String.valueOf(i).concat("/"))));
		}
		
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}	
		
		return listaUrls;
	}
}
