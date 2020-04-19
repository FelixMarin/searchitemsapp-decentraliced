package com.searchitemsapp.scraping.simply;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class ScrapingSimply implements IFScrapingEmpresas {
	
	private ScrapingSimply() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto, 
			final SelectoresCssDTO selectorCssDto)
			throws MalformedURLException {
  
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingSimply.class);
		
		String urlBase = urlDto.getNomUrl();
		String urlAux;
		int fin = 30;
		int max = 10;
		int incremento = ClaseUtils.TWO_INT;
		List<String> listaUrls = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.simply"));
		
		listaUrls.add(urlBase);
		
		for (int i = 2; i <= max; i++) {
			urlAux = urlBase.replace("=1&", "=".concat(String.valueOf(i).concat("&")));
			
			if(i == 2) {
				urlAux = urlAux.replace("&fin=10", "&fin=" + fin);
			} else {
				urlAux = urlAux.replace("&fin=10", "&fin=" + fin*incremento++);
			}
			
			listaUrls.add(urlAux);
		}
		
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}
		
		return listaUrls;
	}

	public String reemplazarCaracteres(final String producto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingSimply.class);
		
		return producto.replace(StringUtils.STRING_ENIE_MIN, StringUtils.ENIE_URL);
		
	}
}
