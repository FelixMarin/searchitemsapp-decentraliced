package com.searchitemsapp.scraping.mercadona;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.scraping.AbsScrapingEmpresas;

/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Mercadona.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("deprecation")
public class ScrapingMercadona extends AbsScrapingEmpresas implements IFScrapingMercadona {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingMercadona.class);  

	/*
	 * Constantes Globales
	 */
	private static final String LT_EM_GT_CIERRE = "&lt;/em&gt;";
	private static final String SEPARADOR_URL = "%20";
	private static final String LT_EM_GT = "&lt;em&gt;";
	private static final String PARAMS_QUERY = "{\"params\":\"query=";
	private static final String CLICK_ANALYTICS_TRUE = "&clickAnalytics=true\"}";
	private static final String TAG_FIN_ROOT = "</root>";
	private static final String TAG_INI_ROOT = "<root>";
	
	private static final String CABECERA_XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	private static final String REFERRER_MERCADONA = "https://tienda.mercadona.es/";
	private static final String AGENT_ALL = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";	
	private static final String ES_ES = "es-ES,es;q=0.8";
	private static final String ACCEPT_ENCODING = "Accept-Encoding";
	private static final String ACCEPT = "Accept";
	private static final String ACEPT_VALUE_JSON = "application/json";
	private static final String GZIP_DEFLATE_SDCH = "gzip, deflate, sdch";
	
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
	public List<String> getListaUrls(final Document document, final UrlDTO urlDto)
			throws MalformedURLException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
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

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
		xml = xml.replace(".", ",");
		
		/**
		 * Si el XML no está vacío, se termina el proceso
		 * y retorna nulo. En otro caso, el XML es limpiado
		 * de caracteres especiales.
		 */
		if(StringUtils.EMPTY.contentEquals(xml)) {
			return null;
		} else {
			xml = CABECERA_XML.concat(TAG_INI_ROOT).concat(xml).concat(TAG_FIN_ROOT);
			xml = xml.replace(LT_EM_GT, StringUtils.EMPTY);
			xml = xml.replace(LT_EM_GT_CIERRE, StringUtils.EMPTY);
		}
		
		/**
		 * Se transforma el XML en formato objeto Document 
		 * y se retorna dicho objeto.
		 */
		Document doc = Jsoup.parse(xml, StringUtils.EMPTY, Parser.xmlParser());
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
				.userAgent(AGENT_ALL)
				.method(Connection.Method.POST)
				.referrer(REFERRER_MERCADONA)
				.ignoreContentType(Boolean.TRUE)
				.validateTLSCertificates(Boolean.FALSE)
				.header(ACCEPT_LANGUAGE, ES_ES)
				.header(ACCEPT_ENCODING, GZIP_DEFLATE_SDCH)
				.header(ACCEPT, ACEPT_VALUE_JSON)
				.maxBodySize(0)
				.timeout(100000)
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
		
		String productoAux = StringUtils.EMPTY;
				
		/**
		 * Se reemplazan los espacios en blanco por el caracter
		 * unicode que lo representa.<br> 
		 * "  " => "%20"
 		 */
		if(!StringUtils.EMPTY.contentEquals(resDto.getNomProducto())) {
			productoAux= resDto.getNomProducto()
				.replace(StringUtils.SPACE, SEPARADOR_URL);
		}
		
		return URL_ALL_PRODUCTS.concat(productoAux);
	}
}
