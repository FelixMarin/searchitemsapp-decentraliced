package com.searchitemsapp.scraping.lidl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class ScrapingLidl implements IFScrapingEmpresas {

	public ScrapingLidl() {
		super();
	}

	@Override
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto, 
			final SelectoresCssDTO selectorCssDto)
			throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		listaUrls.add(urlBase);
		
		return listaUrls;
	}

}
