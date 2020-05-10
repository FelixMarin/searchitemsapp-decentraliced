package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.factory.ScrapingEmpFactory;
import com.searchitemsapp.impl.IFImplementacion;
import com.searchitemsapp.scraping.condis.ScrapingCondis;
import com.searchitemsapp.scraping.consum.ScrapingConsum;
import com.searchitemsapp.scraping.eroski.ScrapingEroski;
import com.searchitemsapp.scraping.mercadona.ScrapingMercadona;
import com.searchitemsapp.scraping.simply.ScrapingSimply;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Clase abstracta que cotiene métodos 
 * expecializados para el web scraping.
 * 
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("deprecation")
public abstract class Scraping {

	private static final String SCRIPT = "script";
	private static Map<String,Integer> mapEmpresas = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
	private static Map<Integer,Boolean> mapDynScraping = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
	private static List<MarcasDTO> listTodasMarcas;
	private static List<EmpresaDTO> listEmpresaDto;
	private static String selectorPrecio;
	private static String selectorPrecioLess;
	private static String selectorPrecioECIOffer;
	private static String selectorPaginaSiguienteCarrefour;
	private static String accesoPopupPeso;
	private static String productLeftContainer;
	private static String selectorDescriptionText;
	private int idEmpresaActual;
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFImplementacion<EmpresaDTO, CategoriaDTO> iFEmpresaImpl;
	
	@Autowired
	private IFImplementacion<MarcasDTO, CategoriaDTO> iFMarcasImp;
		
	@Autowired
	private DynScrapingUnit dynScrapingUnit;
	
	@Autowired
	private ScrapingMercadona scrapingMercadona;
				
	@Autowired
	private ScrapingCondis scrapingCondis;
	
	@Autowired
	private ScrapingEroski scrapingEroski;
	
	@Autowired
	private ScrapingSimply scrapingSimply;
	
	@Autowired
	private ScrapingConsum scrapingConsum;
		
	@Autowired
	private ScrapingEmpFactory scrapingEmpFactory;
	
	/*
	 * Constructor
	 */
	protected Scraping() {
		super();
	}
	
	/**
	 * Con esta metodo se comprueba el Status code de la respuesta que recibo al hacer
	 * la peticion EJM: 
	 * 		200 OK 300 Multiple Choices 301 Moved Permanently 305 Use Proxy .
	 * 		400 Bad Request 403 Forbidden 404 Not Found 500 Internal Server Error.
	 * 		502 Bad Gateway 503 Service Unavailable.
	 * 
	 * @param url
	 * @return Status Code
	 * @throws IOException
	 */
	protected int getStatusConnectionCode(final String url) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		int iResultado = ClaseUtils.ZERO_INT;

		try {
			/**
			 * Jsoup es una librería que permite conectarse a sitios web
			 * descargarlos y manipularlos a través de un DOM virtual.
			 */
			iResultado = Jsoup.connect(url)
					.userAgent(StringUtils.AGENT_ALL)
					.method(Connection.Method.GET)
					.referrer(StringUtils.REFFERER_GOOGLE)
					.header(StringUtils.ACCEPT_LANGUAGE, StringUtils.ES_ES)
					.header(StringUtils.ACCEPT_ENCODING, StringUtils.GZIP_DEFLATE_SDCH)
					.header(StringUtils.ACCEPT, StringUtils.ACCEPT_VALUE)
					.maxBodySize(ClaseUtils.ZERO_INT)
					.timeout(ClaseUtils.TIME_OUT)
					.ignoreHttpErrors(Boolean.TRUE)
					.execute()
					.statusCode();
		} catch (IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		return iResultado;
	}

	/**
	 * Este método devuelve un objeto de la clase Document con el contenido del
	 * HTML de la web que permitirá parsearlo con los métodos de la libreía
	 * JSoup.
	 * 
	 * @param url
	 * @return Documento con el HTML
	 * @throws IOException
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 */
	protected List<Document> getHtmlDocument(final UrlDTO urlDto, 
			final Map<String, String> mapLoginPageCookies,
			final String producto,
			final SelectoresCssDTO selectorCssDto) 
					throws IOException, URISyntaxException, InterruptedException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

    	List<Document> listDocuments = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
    	
		/**
		 * El identificador de la empresa se añade 
		 * a una variable.
		 */
		int idEmpresa = urlDto.getTbSiaEmpresa().getDid();			
    	
		/**
		 * Objeto Document con el contenido del
		 * HTML de la web listo para ser manipulado.
		 */
    	Document document = getDocument(urlDto.getNomUrl(), idEmpresa, 
    			producto, mapLoginPageCookies);

    	List<String> liUrlsPorEmpresaPaginacion = urlsPaginacion(document, urlDto, selectorCssDto, idEmpresa);
   		
   		if(!liUrlsPorEmpresaPaginacion.isEmpty()) {
	   		for (String url : liUrlsPorEmpresaPaginacion) {
	   			listDocuments.add(getDocument(url, idEmpresa, 
	   					producto, mapLoginPageCookies));
			}
   		} else {
   			listDocuments.add(document);
   		}
		      	
		return listDocuments;
	}
	
	/**
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @param idEmpresa
	 * @return
	 * @throws MalformedURLException
	 */
	protected List<String> urlsPaginacion(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto, 
			final int idEmpresa) throws MalformedURLException {
		
		List<String> listUrlsResultado = StringUtils.getNewListString();
		
		if(getMapEmpresas().get(StringUtils.DICCIONARIO) == idEmpresa) {
			return StringUtils.getNewListString();
		}
		
		listUrlsResultado.addAll(scrapingEmpFactory
				.getScrapingEmpresa(idEmpresa).getListaUrls(document, urlDto, selectorCssDto));
		
		return listUrlsResultado;
	}
	
	/**
	 * Este método devuelve un objeto de la clase Document con el contenido del
	 * HTML de la web.
	 * 
	 * @param strUrl
	 * @param didEmpresa
	 * @param producto
	 * @param mapLoginPageCookies
	 * @return Document
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private Document getDocument(final String strUrl, 
			final int didEmpresa, final String producto,
			final Map<String, String> mapLoginPageCookies) 
					throws InterruptedException, URISyntaxException, IOException {
		
		Document document = (Document) ClaseUtils.NULL_OBJECT;
		Connection connection  = (Connection) ClaseUtils.NULL_OBJECT;
		Response response = (Response) ClaseUtils.NULL_OBJECT;
		boolean isMercadona = didEmpresa == getMapEmpresas().get(StringUtils.MERCADONA);	
		boolean bDynScrap = mapDynScraping.get(didEmpresa);
		URL url = new URL(strUrl);
		
		if(bDynScrap) {
			LogsUtils.escribeLogDebug(DynScrapingUnit.class.toString(),Scraping.class);
			document = Jsoup.parse(dynScrapingUnit.getDynHtmlContent(strUrl, didEmpresa), 
					new URL(strUrl).toURI().toString());
		} else if(isMercadona) {
			LogsUtils.escribeLogDebug(ScrapingMercadona.class.toString(),Scraping.class);
			connection = scrapingMercadona.getConnection(strUrl, producto);	
			response = connection.execute();
		} else {
			LogsUtils.escribeLogDebug(ScrapingUnit.class.toString(),Scraping.class);

				connection = Jsoup.connect(strUrl)
						.userAgent(StringUtils.AGENT_ALL)
						.method(Connection.Method.GET)
						.referrer(url.getProtocol().concat(StringUtils.PROTOCOL_ACCESSOR).concat(url.getHost().concat("/")))
						.ignoreContentType(Boolean.TRUE)
						.validateTLSCertificates(Boolean.FALSE)
						.header(StringUtils.ACCEPT_LANGUAGE, StringUtils.ES_ES)
						.header(StringUtils.ACCEPT_ENCODING, StringUtils.GZIP_DEFLATE_SDCH)
						.header(StringUtils.ACCEPT, StringUtils.ACCEPT_VALUE)
						.maxBodySize(ClaseUtils.ZERO_INT)
						.timeout(ClaseUtils.TIME_OUT);
				response = connection.execute();
		}
		
		if(!bDynScrap && !ClaseUtils.isNullObject(mapLoginPageCookies)) {
			connection.cookies(mapLoginPageCookies);
		}
		
		if(bDynScrap) {
			return document;
		} else if(isMercadona) {
       		return scrapingMercadona.getDocument(strUrl, response.body());
       	} else {
			return response.parse();
		}
	}

	protected Elements selectScrapPattern(final Document document,
			final String strScrapPattern, final String strScrapNotPattern) {

		Elements entradas;

        if(StringUtils.validateNull(strScrapNotPattern)) {
        	entradas = document.select(strScrapPattern);
        } else {
        	entradas = document.select(strScrapPattern).not(strScrapNotPattern);
        }

        return entradas;
	}

	protected boolean validaURL(final String baseUri,final String url) {
		return url.equalsIgnoreCase(baseUri);
	}
	
	protected boolean validaSelector(Element elem) {
		return !ClaseUtils.isNullObject(elem.selectFirst(getSelectorPaginaSiguienteCarrefour())) ||
		!ClaseUtils.isNullObject(elem.selectFirst(getAccesoPopupPeso()));
	}	

	protected boolean validateContent(final String[] arProducto, 
			final String nomProducto, final int iIdEmpresa, final Pattern pattern) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String strProducto;
		
		if(ClaseUtils.isNullObject(nomProducto)) {
			return Boolean.FALSE;
		} 
		
		if(iIdEmpresa == ClaseUtils.ZERO_INT) {
			return Boolean.FALSE;
		}
		
		strProducto = filtroMarca(iIdEmpresa, nomProducto);
		
		if(ClaseUtils.isNullObject(strProducto)) {
			return Boolean.FALSE;
		}
		
		if(arProducto.length == ClaseUtils.ONE_INT &&
				StringUtils.eliminarTildes(strProducto)
				.toLowerCase().startsWith(StringUtils
						.eliminarTildes(arProducto[0].trim())
				.toLowerCase().concat(StringUtils.SPACE_STRING))) {
			return Boolean.TRUE;			
		} else if(arProducto.length > ClaseUtils.ONE_INT) {			
			
			return pattern.matcher(StringUtils
					.eliminarTildes(strProducto).toUpperCase()).find();
			
		} else {
			return Boolean.FALSE;
		}
	}
	
	protected Pattern createPatternProduct(final String[] arProducto) {
		
		List<String> tokens = StringUtils.getNewListString();
		StringBuilder pattSb = StringUtils.getNewStringBuilder();
		
		for (int i = 0; i < arProducto.length; i++) {
			tokens.add(arProducto[i].toUpperCase());
		}
		
		pattSb.append("(");
		for (String string : tokens) {
			pattSb.append(".*").append(string);
		}
		pattSb.append(")");
		
		Collections.reverse(tokens);
		
		pattSb.append("|(");
		for (String string : tokens) {
			pattSb.append(".*")
			.append(string);
		}
		pattSb.append(")");
		
		return Pattern.compile(pattSb.toString());
	}
	
	protected String elementoPorCssSelector(final Element elem, 
			final String cssSelector,
			final UrlDTO urlDto) throws MalformedURLException {
		
		if(StringUtils.isEmpty(cssSelector)) {
			return StringUtils.NULL_STRING;
		}
		
		StringTokenizer st = new StringTokenizer(cssSelector,StringUtils.PIPE);  
		List<String> lista = StringUtils.getNewListString();
		String strResult;
		
		while (st.hasMoreTokens()) {  
			lista.add(st.nextToken());
		}
		
		int listaSize = lista.size();
		
		if(getMapEmpresas().get(StringUtils.MERCADONA) == urlDto.getTbSiaEmpresa().getDid()) {
			strResult = scrapingMercadona.getResult(elem, cssSelector);
		} else if(getMapEmpresas().get(StringUtils.CONDIS) == urlDto.getTbSiaEmpresa().getDid() &&
				SCRIPT.equalsIgnoreCase(lista.get(0))) {	
			strResult = scrapingCondis.tratarTagScript(elem, lista.get(0));
		} else if(getMapEmpresas().get(StringUtils.ELCORTEINGLES) == urlDto.getTbSiaEmpresa().getDid() &&
				elem.select(getSelectorPrecioECIOffer()).size() > ClaseUtils.ZERO_INT) {
			strResult = elem.selectFirst(getSelectorPrecioECIOffer()).text();
		} else {
			switch (listaSize) {
				case 1:
					strResult = elem.select(lista.get(0)).text();
					break;			
				case 2:
					strResult = elem.select(lista.get(0)).attr(lista.get(1));
					break;
				default:
					strResult = elem.select(cssSelector).text();
					break;
			}
		}
		
		return validaResultadoElementValue(strResult, urlDto.getNomUrl());
	}
		
	protected String filtroMarca(final int iIdEmpresa, final String nomProducto) {
		
		String strProducto;
		
		if(iIdEmpresa == getMapEmpresas().get(StringUtils.HIPERCOR) ||
				iIdEmpresa == getMapEmpresas().get(StringUtils.DIA) ||
				iIdEmpresa == getMapEmpresas().get(StringUtils.ELCORTEINGLES)) {
			strProducto = eliminarMarcaPrincipio(nomProducto);
		} else {
			strProducto = nomProducto;
		}
		
		for (MarcasDTO marcaDto : listTodasMarcas) {
			if(strProducto.toLowerCase()
					.startsWith(marcaDto
							.getNomMarca()
							.toLowerCase())) {
				
				strProducto = strProducto.toLowerCase()
						.replaceAll(marcaDto.getNomMarca()
							.toLowerCase(), 
							StringUtils.EMPTY_STRING)
							.trim();
				
				break;
			}
		}
		
		return strProducto;
	}
	
	private String eliminarMarcaPrincipio(final String nomProducto) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String[] nomProdSeparado = nomProducto.trim().split(StringUtils.SPACE_STRING);
		StringBuilder resultado = StringUtils.getNewStringBuilder();
		
		if(ClaseUtils.isNullObject(nomProdSeparado)) {
			return StringUtils.NULL_STRING;
		}
		
		for (int i = 0; i < nomProdSeparado.length; i++) {
			if(!nomProdSeparado[i].equals(StringUtils.pasarAmayusculas(nomProdSeparado[i]))) {
				resultado.append(nomProdSeparado[i]).append(StringUtils.SPACE_STRING);
			}
		}
		
		return resultado.toString();
	}	
		
	protected void staticData() {
		
		try {
			if(mapEmpresas.size() == ClaseUtils.ZERO_INT) {
				
				setListEmpresaDto(iFEmpresaImpl.findAll());
	
				for (EmpresaDTO empresaDTO : listEmpresaDto) {
					mapEmpresas.put(empresaDTO.getNomEmpresa(),empresaDTO.getDid());
					mapDynScraping.put(empresaDTO.getDid(), empresaDTO.getBolDynScrap());
				}
				
				setSelectorPrecio(CommonsPorperties.getValue("flow.value.select.precio.oferta.ulabox"));
				setSelectorPrecioLess(CommonsPorperties.getValue("flow.value.pagina.precio.less"));
				setSelectorPrecioECIOffer(CommonsPorperties.getValue("flow.value.pagina.precio.eci.offer"));
				setSelectorPaginaSiguienteCarrefour(CommonsPorperties.getValue("flow.value.pagina.siguiente.carrefour"));
				setAccesoPopupPeso(CommonsPorperties.getValue("flow.value.pagina.acceso.popup.peso"));
			}
		}catch(IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
	}
	
	protected ResultadoDTO fillDataResultadoDTO(final Element elem, 
			final SelectoresCssDTO selectoresCssDto, 
			final UrlDTO urlDto, 
			final String ordenacion) throws IOException {
		
		idEmpresaActual = urlDto.getTbSiaEmpresa().getDid();
		ResultadoDTO resDto = new ResultadoDTO();
		resDto.setImagen(elementoPorCssSelector(elem, selectoresCssDto.getSelImage(), urlDto));
		resDto.setNomProducto(elementoPorCssSelector(elem, selectoresCssDto.getSelProducto(), urlDto));
		resDto.setDesProducto(elementoPorCssSelector(elem, selectoresCssDto.getSelProducto(), urlDto));
		resDto.setPrecio(elementoPorCssSelector(elem, selectoresCssDto.getSelPrecio(), urlDto));
		resDto.setPrecioKilo(elementoPorCssSelector(elem, selectoresCssDto.getSelPreKilo(), urlDto));
		resDto.setNomUrl(elementoPorCssSelector(elem, selectoresCssDto.getSelLinkProd(), urlDto));
		resDto.setDidEmpresa(urlDto.getTbSiaEmpresa().getDid());
		resDto.getTbSiaEmpresa().setDid(urlDto.getTbSiaEmpresa().getDid());
		resDto.getTbSiaEmpresa().setNomEmpresa(urlDto.getTbSiaEmpresa().getNomEmpresa());
		
		if(idEmpresaActual == getMapEmpresas().get(StringUtils.MERCADONA)) {
			resDto.setNomUrlAllProducts(scrapingMercadona.getUrlAll(resDto));
			resDto.setImagen(resDto.getImagen().replace(StringUtils.COMMA_STRING, StringUtils.DOT_STRING));
		}else {
			resDto.setNomUrlAllProducts(urlDto.getNomUrl());
		}
		
		if(!StringUtils.isEmpty(resDto.getPrecioKilo()) &&
				resDto.getPrecioKilo().contains(StringUtils.PIPE)) {
			resDto.setPrecioKilo(resDto.getPrecioKilo().substring(resDto.getPrecioKilo()
					.indexOf(StringUtils.PIPE)+1,resDto.getPrecioKilo().length()).trim());
		}
		
		resDto.setOrdenacion(Integer.parseInt(ordenacion));		
		
		return resDto;
	}
	
	private String validaResultadoElementValue(String strResult, 
			final String strUrl) throws MalformedURLException {
		
		String caracteres;
		
		if(StringUtils.validateNull(strResult)){
			return strResult;
		}
		
		int iend = ClaseUtils.ONE_NEGATIVE_INT;
		URL url = new URL(strUrl);
		String strUrlEmpresa = url.getProtocol().concat(StringUtils.PROTOCOL_ACCESSOR).concat(url.getHost());
		
		if(!StringUtils.validateNull(strResult)) {
			iend = strResult.indexOf(StringUtils.LEFT_PARENTHESIS_0);
		}
		
		if(iend != ClaseUtils.ONE_NEGATIVE_INT) {
			strResult = strResult.substring(ClaseUtils.ZERO_INT, 
					strResult.indexOf(StringUtils.LEFT_PARENTHESIS_0)-1);
		}
		
		if(!StringUtils.validateNull(strResult) && strResult.trim().startsWith(StringUtils.DOBLE_BARRA)) {
			caracteres = StringUtils.HTTPS.concat(strResult);
		} else if(!StringUtils.validateNull(strResult) && strResult.trim().startsWith(StringUtils.BARRA)) {
			caracteres = strUrlEmpresa.concat(strResult); 
		} else {
			caracteres = strResult;
		}
		
		return StringUtils.formatoCaracteres(caracteres);
	}	
	
	/**
	 * Funcionalidad que se encaga de corregir el nombre del producto
	 * en el caso de contenga espacios en blanco, caracteres especiales
	 * o palabras reservadas no permitidas.
	 * 
	 * @param producto
	 * @return String
	 * @throws IOException
	 */
	protected static String tratarProducto(final String producto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),Scraping.class);
		
		String productoTratado = StringUtils.sanitizeValue(producto);
		productoTratado=StringUtils.ajustarDerecha(productoTratado, ClaseUtils.ZERO_INT, Character.MIN_VALUE);
		productoTratado=StringUtils.ajustarIzquierda(productoTratado, ClaseUtils.ZERO_INT, Character.MIN_VALUE);
		
		Matcher matcher = StringUtils.matcher(StringUtils.REGEX_PERCENT_DOLAR, productoTratado);
		
		if(matcher.find()) {
			return productoTratado;
		} else {
			return URLEncoder.encode(productoTratado, StandardCharsets.UTF_8.toString());
		}
	}
	
	protected boolean validaNomProducto(final String nomProducto) {
		return StringUtils.isEmpty(nomProducto);
	}

	protected void cargarTodasLasMarcas() {
		try {
			if(ClaseUtils.isNullObject(listTodasMarcas)) {
				setListTodasMarcas(iFMarcasImp.findAll());
			}
		}catch(IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
	}
	
	protected void setTbSiaSelectoresCss(UrlDTO urlDto) {
		if(!ClaseUtils.isNullObject(urlDto) &&
				!ClaseUtils.isNullObject(urlDto.getTbSiaEmpresa())) {
			urlDto.setTbSiaSelectoresCsses(
					urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses());
		}
	}
	
	protected boolean isNullProducto(final String[] arProducto) {
		return ClaseUtils.isNullObject(arProducto) || 
				arProducto.length == ClaseUtils.ZERO_INT;
	}	
	
	protected static void setListTodasMarcas(List<MarcasDTO> listTodasMarcas) {
		Scraping.listTodasMarcas = listTodasMarcas;
	}	
	
	protected static List<MarcasDTO> getListTodasMarcas() {
		return listTodasMarcas;
	}

	protected static String getSelectorPrecio() {
		return selectorPrecio;
	}

	protected static void setSelectorPrecio(String selectorPrecio) {
		Scraping.selectorPrecio = selectorPrecio;
	}

	protected static String getSelectorPrecioLess() {
		return selectorPrecioLess;
	}
	
	protected static String getSelectorPrecioECIOffer() {
		return selectorPrecioECIOffer;
	}

	protected static void setSelectorPrecioECIOffer(String selectorPrecioECIOffer) {
		Scraping.selectorPrecioECIOffer = selectorPrecioECIOffer;
	}

	protected static void setSelectorPrecioLess(String selectorPrecioLess) {
		Scraping.selectorPrecioLess = selectorPrecioLess;
	}

	protected static String getSelectorPaginaSiguienteCarrefour() {
		return selectorPaginaSiguienteCarrefour;
	}

	protected static void setSelectorPaginaSiguienteCarrefour(
			String selectorPaginaSiguienteCarrefour) {
		
		Scraping.selectorPaginaSiguienteCarrefour = 
				selectorPaginaSiguienteCarrefour;
	}

	protected static String getAccesoPopupPeso() {
		return accesoPopupPeso;
	}

	protected static void setAccesoPopupPeso(String accesoPopupPeso) {
		Scraping.accesoPopupPeso = accesoPopupPeso;
	}

	protected static String getProductLeftContainer() {
		return productLeftContainer;
	}

	protected static void setProductLeftContainer(String productLeftContainer) {
		Scraping.productLeftContainer = productLeftContainer;
	}

	protected static String getSelectorDescriptionText() {
		return selectorDescriptionText;
	}

	protected static void setSelectorDescriptionText(String selectorDescriptionText) {
		Scraping.selectorDescriptionText = selectorDescriptionText;
	}	
	
	protected static Map<String, Integer> getMapEmpresas() {
		return mapEmpresas;
	}
	
	protected static List<EmpresaDTO> getListEmpresaDto() {
		return listEmpresaDto;
	}

	private static void setListEmpresaDto(List<EmpresaDTO> listEmpresaDto) {
		Scraping.listEmpresaDto = listEmpresaDto;
	}
	
	protected static Map<Integer, Boolean> getMapDynScraping() {
		return mapDynScraping;
	}
	
	protected int getIdEmpresaActual() {
		return idEmpresaActual;
	}

	protected void setIdEmpresaActual(int idEmpresaActual) {
		this.idEmpresaActual = idEmpresaActual;
	}
	
	protected String reeplazarTildesCondis(final String producto) {
		return scrapingCondis.eliminarTildesProducto(producto);
	}
	
	protected String reeplazarCaracteresCondis(final String producto) {
		return scrapingCondis.reemplazarCaracteres(producto);
	}
	
	protected String reemplazarCaracteresEroski(final String producto) {
		return scrapingEroski.reemplazarCaracteres(producto);
	}
	
	protected String reeplazarCaracteresSimply(final String producto) {
		return scrapingSimply.reemplazarCaracteres(producto);
	}
	
	protected String getHtmlContextConsum(final WebDriver webDriver, 
						final String strUrl) throws InterruptedException {
		return scrapingConsum.getHtmlContent(webDriver, strUrl);
	}
}
