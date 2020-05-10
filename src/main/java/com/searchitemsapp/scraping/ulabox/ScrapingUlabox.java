package com.searchitemsapp.scraping.ulabox;

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

public class ScrapingUlabox implements IFScrapingEmpresas {
	
	private static final String PATTERN = ".*… ([0-9]+)";
	private static final String CHARSET = "…";

	public ScrapingUlabox() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		Matcher m;
		int intPaginacion;
		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = selectorCssDto.getSelPaginacion();		
		String strPaginacion = document.select(selectorPaginacion).text();
		List<String> listaUrls = StringUtils.getNewListString();
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.ulabox"));
		
		listaUrls.add(urlBase);
		
		if(!StringUtils.isEmpty(strPaginacion)) {
			
			if(strPaginacion.contains(CHARSET)) {
				
				m = StringUtils.matcher(PATTERN, strPaginacion);
				
				if(m.find()) {
					strPaginacion=m.group(ClaseUtils.ONE_INT);
				}
				
			} else {
				strPaginacion = strPaginacion.substring(strPaginacion.length()-1, strPaginacion.length());
			}
			
			intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());

			for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
				listaUrls.add(urlBase.concat("&p=") + i);
			}	
			
			if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
				listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
			}
		}
		
		return listaUrls;
	}
}
