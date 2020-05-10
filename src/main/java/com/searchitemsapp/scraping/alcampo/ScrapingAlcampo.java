package com.searchitemsapp.scraping.alcampo;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class ScrapingAlcampo implements IFScrapingEmpresas{
	
	private static final String PATTERN = ".*&page=([0-9]+)";

	public ScrapingAlcampo() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(Document document, UrlDTO urlDto, 
			SelectoresCssDTO selectorCssDto) throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String urlBase = urlDto.getNomUrl();
		String selectorPaginacion = selectorCssDto.getSelPaginacion();	
		String strPaginacion = StringUtils.ZERO_STRING;
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.alcampo"));
		int intPaginacion;
		
		StringTokenizer st = new StringTokenizer(selectorPaginacion,StringUtils.PIPE);  
		List<String> liSelectorAtr = StringUtils.getNewListString();
		
		while (st.hasMoreTokens()) {  
			liSelectorAtr.add(st.nextToken());
		}
		
		Elements elements = document.select(liSelectorAtr.get(ClaseUtils.ZERO_INT));
		List<String> listaUrls = StringUtils.getNewListString();
		
		if(elements.isEmpty()) {
			listaUrls.add(urlBase);
			return listaUrls;
		}
		
		String ultimoElemento = elements.get(elements.size()-1).attr(liSelectorAtr.get(ClaseUtils.ONE_INT));
		
		Matcher m = StringUtils.matcher(PATTERN, ultimoElemento);
		
		if(m.find()) {
			strPaginacion=m.group(ClaseUtils.ONE_INT);
		}
		
		intPaginacion = StringUtils.desformatearEntero(strPaginacion.trim());
		
		for (int i = ClaseUtils.TWO_INT; i <= intPaginacion; i++) {
			listaUrls.add(urlBase.replace("&page=1", "&page=".concat(String.valueOf(i))));
		}
		
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}		
		
		return listaUrls;
	}

}
