package com.searchitemsapp.scraping.eroski;

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

public class ScrapingEroski implements IFScrapingEmpresas{
	

	private ScrapingEroski() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto,
			final SelectoresCssDTO selectorCssDto) throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(), this.getClass());
		
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		int numresultados = StringUtils.desformatearEntero(CommonsPorperties.getValue("flow.value.paginacion.url.eroski"));
		
		listaUrls.add(urlBase);
		
		for (int i = ClaseUtils.ONE_INT; i <= 20; i++) {
			listaUrls.add(urlBase.replace("=0&", "=".concat(String.valueOf(i).concat("&"))));
		}
		
		if(numresultados > ClaseUtils.ZERO_INT && numresultados <= listaUrls.size()) {
			listaUrls = listaUrls.subList(ClaseUtils.ZERO_INT, numresultados);
		}		
		
		return listaUrls;
	}

	public String reemplazarCaracteres(final String producto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingEroski.class);
		
		String productoTratado;
		String[] arrayTildesEroski;
		String[] arrayTildesNormales;
			
		arrayTildesEroski = StringUtils.arrayTildesEroski();
		arrayTildesNormales = StringUtils.arrayTildesNormales();
		
		productoTratado = producto
				.replace(StringUtils.STRING_ENIE_MIN, StringUtils.ENIE_EROSKI);
		
		for (int i = ClaseUtils.ZERO_INT; i < arrayTildesEroski.length; i++) {
			productoTratado = productoTratado
					.replace(arrayTildesNormales[i], arrayTildesEroski[i]);
		}
		
		return productoTratado;
	}
}
