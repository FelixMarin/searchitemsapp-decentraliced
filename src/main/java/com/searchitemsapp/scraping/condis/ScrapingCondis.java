package com.searchitemsapp.scraping.condis;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.scraping.mercadona.ScrapingMercadona;
import com.searchitemsapp.scraping.simply.ScrapingSimply;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class ScrapingCondis implements IFScrapingEmpresas {

	private static final String ENIE_CONDIS = "%D1";

	public ScrapingCondis() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto)
					throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		listaUrls.add(urlBase);
		
		return listaUrls;
	}
	
	public String tratarTagScript(final Element elem, final String cssSelector) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String resultado = StringUtils.NULL_STRING;
		Matcher matcher;
		
		if(ClaseUtils.isNullObject(elem) || StringUtils.isEmpty(cssSelector)) {
			return resultado;
		}
		resultado = elem.select(cssSelector).html().replace(StringUtils.DOT_STRING, StringUtils.COMMA_STRING);
		
		if(countLines(resultado).length > ClaseUtils.ONE_INT) {
			resultado = countLines(resultado)[ClaseUtils.ONE_INT].trim();
		
			matcher = StringUtils.matcher(StringUtils.REGEX_NUMERO_DECIMAL, resultado);
			
			if(matcher.find()) {
				resultado = matcher.group(ClaseUtils.ZERO_INT);
			}
		
		} else {
			resultado = resultado.substring(resultado.indexOf('\'')+1, resultado.length());
			resultado = resultado.substring(ClaseUtils.ZERO_INT, resultado.indexOf('\''));
			
			if(resultado.length() == 3 &&
					resultado.substring(resultado.indexOf(','), 
							resultado.length()).length()  == ClaseUtils.TWO_INT) {
				resultado += StringUtils.ZERO_STRING;
			}
		}
		
		if(resultado.startsWith(StringUtils.COMMA_STRING)) {
			resultado = StringUtils.ZERO_STRING.concat(resultado);
		}
		
		if(resultado.endsWith(StringUtils.COMMA_STRING)) {
			resultado = resultado.concat("00");
		}
		
		return resultado;
	}
	
	private String[] countLines(String str){
		   return str.split("\r\n|\r|\n");
	}
	
	public String eliminarTildesProducto(final String producto) {
		
		if(StringUtils.isEmpty(producto)) {
			return producto;
		}
		
		String[] vocalestildesMin = StringUtils.arrayTildesNormales();
		String[] vocalesMin = StringUtils.arrayVocalesMin();
		
		String productoAux = producto.toLowerCase();
		
		for (int i = 0; i < vocalesMin.length; i++) {
			productoAux = productoAux.replace(vocalestildesMin[i], vocalesMin[i]);
		}
		
		return productoAux;
	}
	
	public String reemplazarCaracteres(final String producto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingSimply.class);
		
		return producto.replace(StringUtils.STRING_ENIE_MIN, ENIE_CONDIS);
		
	}

}
