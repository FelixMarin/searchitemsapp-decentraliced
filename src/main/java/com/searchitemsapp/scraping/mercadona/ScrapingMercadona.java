package com.searchitemsapp.scraping.mercadona;

import java.net.MalformedURLException;
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

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Mercadona.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("deprecation")
public class ScrapingMercadona implements IFScrapingEmpresas {

	/*
	 * Constantes Globales
	 */
	private static final String LT_EM_GT_CIERRE = "&lt;/em&gt;";
	private static final String LT_EM_GT = "&lt;em&gt;";
	private static final String PARAMS_QUERY = "{\"params\":\"query=";
	private static final String CLICK_ANALYTICS_TRUE = "&clickAnalytics=true\"}";
	private static final String TAG_FIN_ROOT = "</root>";
	private static final String TAG_INI_ROOT = "<root>";
	private static final String REFERRER_MERCADONA = "https://tienda.mercadona.es/";
	private static final String URL_ALL_PRODUCTS = CommonsPorperties
			.getValue("flow.value.url.mercadona.all.products");

	/*
	 * Constructor
	 */
	private ScrapingMercadona() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la web de Mercadona.
	 * Con estas URLs se realizarán las peticiones al
	 * sitio web para extraer los datos. 
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @return List<String>
	 * @exception MalformedURLException
	 */
	@Override
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto, 
			final SelectoresCssDTO selectorCssDto)
			throws MalformedURLException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		/**
		 * Se obtiene la URL base. Esta es la URL principal 
		 * del conjunto de páginas obtenidas como resultado
		 * de la búsqueda del producto. A partir de esta URL 
		 * se generan las de paginación.
		 */
		String urlBase = urlDto.getNomUrl();
		
		/**
		 * Se asigna la url base a la lista.
		 */
		List<String> listaUrls = StringUtils.getNewListString();
		listaUrls.add(urlBase);
		
		return listaUrls;
	}	

	/**
	 * Metodo encargado de extraer la información util
	 * del documento web extraido del sitio de Mercadona.
	 * 
	 * @param url
	 * @param body
	 * @return Document
	 */
	public Document getDocument(final String url, final String body) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ScrapingMercadona.class);
		
		/**
		 * Se crea un objeto JSON a partir del body
		 * del documento web.
		 */
		JSONObject json = new JSONObject(body);
		
		/**
		 * Se transforma el objeto JSON en un XML formato cadena.
		 */
		String xml = XML.toString(json);
		
		/**
		 * Del XML se reemplazan los puntos por comas.
		 */
		xml = xml.replace(StringUtils.DOT_STRING, StringUtils.COMMA_STRING);
		
		/**
		 * Si el XML no está vacío, se termina el proceso
		 * y retorna nulo. En otro caso, el XML es limpiado
		 * de caracteres especiales.
		 */
		if(StringUtils.isEmpty(xml)) {
			return (Document) ClaseUtils.NULL_OBJECT;
		} else {
			xml = StringUtils.CABECERA_XML.concat(TAG_INI_ROOT).concat(xml).concat(TAG_FIN_ROOT);
			xml = xml.replace(LT_EM_GT, StringUtils.EMPTY_STRING);
			xml = xml.replace(LT_EM_GT_CIERRE, StringUtils.EMPTY_STRING);
		}
		
		/**
		 * Se transforma el XML en formato objeto Document 
		 * y se retorna dicho objeto.
		 */
		Document doc = Jsoup.parse(xml, StringUtils.EMPTY_STRING, Parser.xmlParser());
		doc.setBaseUri(url);
		
		return doc;
	}
	
	/**
	 * Retorna el texto de un elemento obtenido mediate
	 * el selector indicado el los argumentos.
	 * 
	 * @param elem
	 * @param cssSelector
	 * @return String
	 */
	public String getResult(final Element elem, final String cssSelector) {		
		return elem.selectFirst(cssSelector).text();
	}
	
	/**
	 * Retorna la conexión establecida con el sitio web.
	 * 
	 * @param strUrl
	 * @param producto
	 * @return
	 */
	public Connection getConnection(final String strUrl, final String producto) {
		
		/**
		 * Mediate la librería JSOUP se puede extablecer conexion con
		 * un sitio web. Utilizando los métodos de la librería, se puede
		 * configura la solicitud de la página web.
		 */
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
	
	/**
	 * Devuelve una cadena con la URL de la solicitud realizada 
	 * al sitio web donde se consulta el producto.
	 * 
	 * @param resDto
	 * @return  String
	 */
	public String getUrlAll(final ResultadoDTO resDto) {
		
		String productoAux = StringUtils.EMPTY_STRING;
				
		/**
		 * Se reemplazan los espacios en blanco por el caracter
		 * unicode que lo representa.<br> 
		 * " " => "%20"
 		 */
		if(!StringUtils.isEmpty(resDto.getNomProducto())) {
			productoAux= resDto.getNomProducto()
				.replace(StringUtils.SPACE_STRING, StringUtils.SEPARADOR_URL);
		}
		
		return URL_ALL_PRODUCTS.concat(productoAux);
	}
}
