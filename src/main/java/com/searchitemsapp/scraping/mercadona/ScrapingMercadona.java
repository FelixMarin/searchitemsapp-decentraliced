package com.searchitemsapp.scraping.mercadona;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.IFScrapingEmpresas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("deprecation")
public class ScrapingMercadona implements IFScrapingEmpresas {

	private static final String LT_EM_GT_CIERRE = "&lt;/em&gt;";
	private static final String LT_EM_GT = "&lt;em&gt;";
	private static final String PARAMS_QUERY = "{\"params\":\"query=";
	private static final String CLICK_ANALYTICS_TRUE = "&clickAnalytics=true\"}";
	private static final String TAG_FIN_ROOT = "</root>";
	private static final String TAG_INI_ROOT = "<root>";
	private static final String REFERRER_MERCADONA = "https://tienda.mercadona.es/";
	private static final String URL_ALL_PRODUCTS = CommonsPorperties
			.getValue("flow.value.url.mercadona.all.products");

	private ScrapingMercadona() {
		super();
	}
	
	@Override
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto, 
			final SelectoresCssDTO selectorCssDto)
			throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		return listaUrls;
	}	
	
	public Document getDocument(final String url, final String body) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		JSONObject json = new JSONObject(body);
		String xml = XML.toString(json);
		
		xml = xml.replace(StringUtils.DOT_STRING, StringUtils.COMMA_STRING);
		
		if(StringUtils.isEmpty(xml)) {
			return (Document) ClaseUtils.NULL_OBJECT;
		} else {
			xml = StringUtils.CABECERA_XML.concat(TAG_INI_ROOT).concat(xml).concat(TAG_FIN_ROOT);
			xml = xml.replace(LT_EM_GT, StringUtils.EMPTY_STRING);
			xml = xml.replace(LT_EM_GT_CIERRE, StringUtils.EMPTY_STRING);
		}
		
		Document doc = Jsoup.parse(xml, StringUtils.EMPTY_STRING, Parser.xmlParser());
		doc.setBaseUri(url);
		
		return doc;
	}
	
	public String getResult(final Element elem, final String cssSelector) {		
		return elem.selectFirst(cssSelector).text();
	}
	
	public Connection getConnection(final String strUrl, final String producto) {
		
		return Jsoup.connect(strUrl)
				.userAgent(StringUtils.AGENT_ALL)
				.method(Connection.Method.POST)
				.referrer(REFERRER_MERCADONA)
				.ignoreContentType(Boolean.TRUE)
				.validateTLSCertificates(Boolean.FALSE)
				.header(StringUtils.ACCEPT_LANGUAGE, StringUtils.ES_ES)
				.header(StringUtils.ACCEPT_ENCODING, StringUtils.GZIP_DEFLATE_SDCH)
				.header(StringUtils.ACCEPT, StringUtils.ACEPT_VALUE_JSON)
				.maxBodySize(ClaseUtils.ZERO_INT)
				.timeout(ClaseUtils.TIME_OUT)
				.requestBody(PARAMS_QUERY
						.concat(producto)
						.concat(CLICK_ANALYTICS_TRUE));
	}
	
	public String getUrlAll(final ResultadoDTO resDto) {
		
		String productoAux = StringUtils.EMPTY_STRING;
		
		if(!StringUtils.isEmpty(resDto.getNomProducto())) {
			productoAux= resDto.getNomProducto()
				.replace(StringUtils.SPACE_STRING, StringUtils.SEPARADOR_URL);
		}
		
		return URL_ALL_PRODUCTS.concat(productoAux);
	}
}
