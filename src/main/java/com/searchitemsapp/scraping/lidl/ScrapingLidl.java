package com.searchitemsapp.scraping.lidl;

import java.net.MalformedURLException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		return listaUrls;
	}

}
