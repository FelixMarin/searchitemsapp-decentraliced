package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


/**
 * Clase abstracta que cotiene métodos 
 * expecializados para módulo de web scraping.
 * <br>
 * {@link Jsoup}
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("deprecation")
public abstract class Scraping {
	
	private static final String DOT_STRING = ".";

	private static final String COMMA_STRING = ",";

	private static final String PIPE_STRING = "|";

	private static final Logger LOGGER = LoggerFactory.getLogger(Scraping.class);   
	
	/*
	 * Constantes Globales
	 */
	private static final String AGENT_ALL = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
	private static final String REFFERER_GOOGLE = "http://www.google.com";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";	
	private static final String ES_ES = "es-ES,es;q=0.8";
	private static final String ACCEPT_ENCODING = "Accept-Encoding";
	private static final String ACCEPT = "Accept";
	private static final String PROTOCOL_ACCESSOR ="://";
	private static final String GZIP_DEFLATE_SDCH = "gzip, deflate, sdch";
	private static final String ACCEPT_VALUE = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	private static final String DICCIONARIO = "DICCIONARIO";
	private static final String HIPERCOR = "HIPERCOR";
	private static final String ELCORTEINGLES = "ELCORTEINGLES";
	private static final String DIA = "DIA";
	private static final String CONDIS = "CONDIS";
	private static final String MERCADONA = "MERCADONA";
	private static final char CHAR_ENIE_COD = '\u00f1';
	private static final String STRING_ENIE_MIN = "ñ";
	private static final String STRING_ENIE_MAY = "Ñ";
	private static final String UNICODE_ENIE = "u00f1";
	private static final String REEMPLAZABLE_TILDES = "[\\p{InCombiningDiacriticalMarks}]";
	private static final String EMPTY_STRING = "";
	private static final String LEFT_PARENTHESIS_0 = " (";
	private static final String DOBLE_BARRA = "//";
	private static final String HTTPS = "https:";
	private static final String BARRA = "/";
	private static final String RIGTH_PARENTHESIS = "\\)";
	private static final String LEFT_PARENTHESIS = "\\(";
	private static final String REGEX_PERCENT_DOLAR = "(\\%|\\$00)";
	
	/*
	 * Variables Globales
	 */
	private static final String SCRIPT = "script";
	private static Map<String,Integer> mapEmpresas = new HashMap<>(10);
	private static Map<Integer,Boolean> mapDynScraping = new HashMap<>(10);
	private static List<MarcasDTO> listTodasMarcas;
	private static List<EmpresaDTO> listEmpresaDto;
	private static String selectorPrecioECIOffer;
	private static String selectorPaginaSiguienteCarrefour;
	private static String accesoPopupPeso;
	private static String productLeftContainer;
	private static String selectorDescriptionText;

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

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		int iResultado = 0;

		try {
			/**
			 * Jsoup es una librería que permite conectarse a sitios web
			 * descargarlos y manipularlos a través de un DOM virtual.
			 */
			iResultado = Jsoup.connect(url)
					.userAgent(AGENT_ALL)
					.method(Connection.Method.GET)
					.referrer(REFFERER_GOOGLE)
					.header(ACCEPT_LANGUAGE, ES_ES)
					.header(ACCEPT_ENCODING, GZIP_DEFLATE_SDCH)
					.header(ACCEPT, ACCEPT_VALUE)
					.maxBodySize(0)
					.timeout(100000)
					.ignoreHttpErrors(Boolean.TRUE)
					.execute()
					.statusCode();
		} catch (IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
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

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
    	List<Document> listDocuments = new ArrayList<>(10);
    	
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

    	/**
    	 * Se extrae el listado de URLs que se van a utlizar
    	 * para extraer los datos.
    	 */
    	List<String> liUrlsPorEmpresaPaginacion = urlsPaginacion(document, urlDto, selectorCssDto, idEmpresa);
   		
    	/**
    	 * Se obtienen los documets de la llamadas a las
    	 * URLs, se añaden a una lista y se retorna.
    	 */
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
	 * Devuelve un listado con de las URLs correspondientes
	 * a uno de los supermecados a los que se le van a 
	 * extraer los datos.
	 * 
	 * {@link ScrapingEmpFactory#getScrapingEmpresa(int)}
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @param idEmpresa
	 * @return List<String>
	 * @throws MalformedURLException
	 */
	protected List<String> urlsPaginacion(final Document document, 
			final UrlDTO urlDto, final SelectoresCssDTO selectorCssDto, 
			final int idEmpresa) throws MalformedURLException {
		
		List<String> listUrlsResultado = new ArrayList<>(10);
		
		/**
		 * Si el identificador de empresa es el 
		 * que corresponde al diccionario, la
		 * ejecución termina. 
		 */
		if(getMapEmpresas().get(DICCIONARIO) == idEmpresa) {
			return new ArrayList<>(10);
		}
		
		/**
		 * Se obtiene el listado de URLs correspodientes
		 * a la empresa a la que se le va a realizar la
		 * solicitud.
		 */
		listUrlsResultado.addAll(scrapingEmpFactory
				.getScrapingEmpresa(idEmpresa).getListaUrls(document, urlDto, selectorCssDto));
		
		return listUrlsResultado;
	}
	
	/**
	 * Este método extrae la informacíon a partir
	 * de un selector. Devuelve un conjunto de 
	 * elementos que coincidan con el patron de
	 * busqeda.
	 * 
	 * @param document
	 * @param strScrapPattern
	 * @param strScrapNotPattern
	 * @return Elements
	 */
	protected Elements selectScrapPattern(final Document document,
			final String strScrapPattern, final String strScrapNotPattern) {

		Elements entradas;

        if(Objects.isNull(strScrapNotPattern)) {
        	entradas = document.select(strScrapPattern);
        } else {
        	entradas = document.select(strScrapPattern).not(strScrapNotPattern);
        }

        return entradas;
	}

	/**
	 * Método que valida una URL.
	 * 
	 * @param baseUri
	 * @param url
	 * @return boolean
	 */
	protected boolean validaURL(final String baseUri,final String url) {
		return url.equalsIgnoreCase(baseUri);
	}
	
	protected boolean validaSelector(Element elem) {
		return Objects.nonNull(elem.selectFirst(getSelectorPaginaSiguienteCarrefour())) ||
		Objects.nonNull(elem.selectFirst(getAccesoPopupPeso()));
	}	

	/**
	 * Método que valida el resultado obtenido. 
	 * 
	 * @param arProducto
	 * @param nomProducto
	 * @param iIdEmpresa
	 * @param pattern
	 * @return boolean
	 */
	protected String eliminarTildes(final String cadena) {
		
		if(Objects.isNull(cadena)) {
			return null;
		}
		
		if(cadena.indexOf(CHAR_ENIE_COD) != -1) {
			return cadena;
		}
		String resultado = cadena.replace(STRING_ENIE_MAY, UNICODE_ENIE);
		resultado = Normalizer.normalize(resultado.toLowerCase(), Normalizer.Form.NFD);
		resultado = resultado.replaceAll(REEMPLAZABLE_TILDES, EMPTY_STRING);
		resultado = resultado.replace(UNICODE_ENIE, STRING_ENIE_MIN);
		return Normalizer.normalize(resultado, Normalizer.Form.NFC);
		
	}
	
	/**
	 * Método que compone un patrón regex con el que se
	 * realizará el filtrado de productos a partir del 
	 * parámetro de entrada.
	 * 
	 * @param arProducto
	 * @return Pattern
	 */
	protected Pattern createPatternProduct(final String[] arProducto) {

		List<String> tokens = new ArrayList<>(10);
		StringBuilder pattSb = new StringBuilder(10);
		
		/**
		 * Se añaden todas las palabras que componen 
		 * el producto en una lista en mayúsculas.
		 */
		for (int i = 0; i < arProducto.length; i++) {
			tokens.add(arProducto[i].toUpperCase());
		}
		
		//INI - Bloque de código donde se forma el patrón.
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
		//FIN - Bloque de código donde se forma el patrón.
		
		
		/**
		 * El resultado es un objeto de tipo 
		 * patrón creado a partir del patrón
		 * en formato String. 
		 */
		return Pattern.compile(pattSb.toString());
	}
	
	/**
	 * Método que extrae los datos del elemento a partir de
	 * un selector. Dependiendo de la empresa la técnica de
	 * extraccion de los datos es de una manera u otra.
	 * 
	 * @param elem
	 * @param cssSelector
	 * @param urlDto
	 * @return String
	 * @throws MalformedURLException
	 */
	protected String elementoPorCssSelector(final Element elem, 
			final String cssSelector,
			final UrlDTO urlDto) throws MalformedURLException {
		
		/**
		 * Se comprueba que los parametros de entrada no sean nulos.
		 * Si nulos retorna nulo.
		 */
		if(Objects.isNull(cssSelector) || "".equals(cssSelector)) {
			return "null";
		}
		
		List<String> lista = new ArrayList<>(10);
		String strResult;
		
		/**
		 * Se extraen los selectores de la variable.
		 */
		StringTokenizer st = new StringTokenizer(cssSelector,PIPE_STRING);  
		
		/**
		 * Se añaden los tokens a una lista.
		 */
		while (st.hasMoreTokens()) {  
			lista.add(st.nextToken());
		}
		
		/**
		 * Se obtiene el tamaño de la lista en una variable
		 */
		int listaSize = lista.size();
		
		/**
		 * El método de extracción de datos es diferente en cada empresa.
		 */
		if(getMapEmpresas().get(MERCADONA) == urlDto.getTbSiaEmpresa().getDid()) {
			
			strResult = scrapingMercadona.getResult(elem, cssSelector);
			
		} else if(getMapEmpresas().get(CONDIS) == urlDto.getTbSiaEmpresa().getDid() &&
				SCRIPT.equalsIgnoreCase(lista.get(0))) {	
			
			strResult = scrapingCondis.tratarTagScript(elem, lista.get(0));
			
		} else if(getMapEmpresas().get(ELCORTEINGLES) == urlDto.getTbSiaEmpresa().getDid() &&
				elem.select(getSelectorPrecioECIOffer()).size() > 0) {
			
			strResult = elem.selectFirst(getSelectorPrecioECIOffer()).text();
			
		} else {
			strResult = extraerValorDelElemento(listaSize, elem, lista, cssSelector);
		}
		
		/**
		 * se validan y se retorna el resultado.
		 */
		return validaResultadoElementValue(strResult, urlDto.getNomUrl());
	}
	
	/**
	 * Método encargado de filtra la marca del producto. 
	 * Elimina la marca de la descripción del producto.
	 * 
	 * @param iIdEmpresa
	 * @param nomProducto
	 * @return String
	 */
	protected String filtroMarca(final int iIdEmpresa, final String nomProducto) {
		
		String strProducto;
		
		/**
		 * Se comprueba de que marca es el producto, dependiendo
		 * de la misma, se ejecutará un proceso u otro.
		 */
		if(iIdEmpresa == getMapEmpresas().get(HIPERCOR) ||
				iIdEmpresa == getMapEmpresas().get(DIA) ||
				iIdEmpresa == getMapEmpresas().get(ELCORTEINGLES)) {
			strProducto = eliminarMarcaPrincipio(nomProducto);
		} else {
			strProducto = nomProducto;
		}
		
		/**
		 * Se comprueba que la descripción del producto no 
		 * comience por la marca. Si es así, esta se elimina.
		 */
		for (MarcasDTO marcaDto : listTodasMarcas) {
			if(strProducto.toLowerCase()
					.startsWith(marcaDto
							.getNomMarca()
							.toLowerCase())) {
				
				strProducto = strProducto.toLowerCase()
						.replaceAll(marcaDto.getNomMarca()
							.toLowerCase(), "").trim();
				
				break;
			}
		}
		
		return strProducto;
	}
	
	/**
	 * Método que carga los datos estáticos usados en el proceso
	 * de las peticiones. Este método solo se ejecuta una vez,
	 * los datos se cachean mientras esté la aplicación activa.
	 */
	protected void staticData() {
		
		try {
			
			/**
			 * Solo se ejecutará esta condición cuando el mapa 
			 * de empresas está vacío. Eso solo pasará una vez
			 * ya que se trata de una variable estática.
			 */
			if(mapEmpresas.size() == 0) {
				
				/**
				 * Se establece la lista de empresas en la 
				 * variable global estática.
				 */
				setListEmpresaDto(iFEmpresaImpl.findAll());
	
				/**
				 * En este punto se establece la lista de empresas
				 * en la variable estática global y se indica en otro
				 * mapa el tipo de raspado que se va a utilizar en el
				 * proceso. 
				 */
				for (EmpresaDTO empresaDTO : listEmpresaDto) {
					mapEmpresas.put(empresaDTO.getNomEmpresa(),empresaDTO.getDid());
					mapDynScraping.put(empresaDTO.getDid(), empresaDTO.getBolDynScrap());
				}
				
				/**
				 * Se setean algunas variables obtenidas de las propiedades.
				 * Esta variables se cargan de forma estática al arrancar la
				 * aplicación. Se utilizan durante el proceso de la solicitud.
				 */
		
				setSelectorPrecioECIOffer(CommonsPorperties.getValue("flow.value.pagina.precio.eci.offer"));
				setSelectorPaginaSiguienteCarrefour(CommonsPorperties.getValue("flow.value.pagina.siguiente.carrefour"));
				setAccesoPopupPeso(CommonsPorperties.getValue("flow.value.pagina.acceso.popup.peso"));
			}
		}catch(IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
	}
	
	/**
	 * Este método se encarga de extraer cada uno de los datos 
	 * del producto para componer un objeto de tipo ResultadoDTO.
	 * 
	 * @param elem
	 * @param selectoresCssDto
	 * @param urlDto
	 * @param ordenacion
	 * @return ResultadoDTO
	 * @throws IOException
	 */
	protected ResultadoDTO fillDataResultadoDTO(final Element elem, 
			final SelectoresCssDTO selectoresCssDto, 
			final UrlDTO urlDto, 
			final String ordenacion) throws IOException {
		
		/**
		 * Variables utilizadas en el proceso.
		 */
		int idEmpresaActual = urlDto.getTbSiaEmpresa().getDid();
		ResultadoDTO resDto = new ResultadoDTO();
		
		/**
		 * Se setean los valores recuperados de los elementos
		 * en la variable resultado. Los valores son todos los
		 * que devolverá el servicio.
		 */
		resDto.setImagen(elementoPorCssSelector(elem, selectoresCssDto.getSelImage(), urlDto));
		resDto.setNomProducto(elementoPorCssSelector(elem, selectoresCssDto.getSelProducto(), urlDto));
		resDto.setDesProducto(elementoPorCssSelector(elem, selectoresCssDto.getSelProducto(), urlDto));
		resDto.setPrecio(elementoPorCssSelector(elem, selectoresCssDto.getSelPrecio(), urlDto));
		resDto.setPrecioKilo(elementoPorCssSelector(elem, selectoresCssDto.getSelPreKilo(), urlDto));
		resDto.setNomUrl(elementoPorCssSelector(elem, selectoresCssDto.getSelLinkProd(), urlDto));
		resDto.setDidEmpresa(urlDto.getTbSiaEmpresa().getDid());
		resDto.getTbSiaEmpresa().setDid(urlDto.getTbSiaEmpresa().getDid());
		resDto.getTbSiaEmpresa().setNomEmpresa(urlDto.getTbSiaEmpresa().getNomEmpresa());
		
		/**
		 * Dependiendo de la empresa, el tratamiento de las URLs 
		 * extraidas del elemento se realiza de diferente forma.
		 */
		if(idEmpresaActual == getMapEmpresas().get(MERCADONA)) {
			resDto.setNomUrlAllProducts(scrapingMercadona.getUrlAll(resDto));
			resDto.setImagen(resDto.getImagen().replace(COMMA_STRING, DOT_STRING));
		}else {
			resDto.setNomUrlAllProducts(urlDto.getNomUrl());
		}
		
		/**
		 * Se establece el tipo de ordenación del resultado
		 * La oredenacion puede ser por por precio o por
		 * precio/kilo.
		 */
		resDto.setOrdenacion(Integer.parseInt(ordenacion));		
		
		return resDto;
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
	protected String tratarProducto(final String producto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		String productoTratado = sanitizeValue(producto);
		productoTratado=ajustarDerecha(productoTratado, 0, Character.MIN_VALUE);
		productoTratado=ajustarIzquierda(productoTratado, 0, Character.MIN_VALUE);
		
		Matcher matcher = Pattern.compile(REGEX_PERCENT_DOLAR).matcher(productoTratado);
		
		if(matcher.find()) {
			return productoTratado;
		} else {
			return URLEncoder.encode(productoTratado, StandardCharsets.UTF_8.toString());
		}
	}
	
	/**
	 * Este metodo carga todas las marcas de 
	 * productos desde la base de datos. Se
	 * cachea al principio y dura durante toda
	 * la ejecución del programa,
	 */
	protected void cargarTodasLasMarcas() {
		try {
			if(Objects.isNull(listTodasMarcas)) {
				setListTodasMarcas(iFMarcasImp.findAll());
			}
		}catch(IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
	}
	
	/**
	 * Establece el valor del selector en la variable de tipo UrlDTO.
	 * 
	 * @param urlDto
	 */
	protected void setTbSiaSelectoresCss(UrlDTO urlDto) {
		if(Objects.nonNull(urlDto) &&
				Objects.nonNull(urlDto.getTbSiaEmpresa())) {
			urlDto.setTbSiaSelectoresCsses(
					urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses());
		}
	}
	
	protected boolean isNullProducto(final String[] arProducto) {
		return Objects.isNull(arProducto) || 
				arProducto.length == 0;
	}	
	
	protected static void setListTodasMarcas(List<MarcasDTO> listTodasMarcas) {
		Scraping.listTodasMarcas = listTodasMarcas;
	}	

	protected static void setSelectorPrecioECIOffer(String selectorPrecioECIOffer) {
		Scraping.selectorPrecioECIOffer = selectorPrecioECIOffer;
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

	protected static Map<Integer, Boolean> getMapDynScraping() {
		return mapDynScraping;
	}

	protected static String getSelectorPrecioECIOffer() {
		return selectorPrecioECIOffer;
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
	
	/*
	 * Métodos privados
	 */
	
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
	
		Document document = null;
		Connection connection  = null;
		Response response = null;
		
		/**
		 * Variables con los valores necesarios para el proceso.
		 */
		boolean isMercadona = didEmpresa == getMapEmpresas().get(MERCADONA);	
		boolean bDynScrap = mapDynScraping.get(didEmpresa);
		URL url = new URL(strUrl);
		 
		/**
		  * Si está activo el indicador de scraping dinámico asociado a la empresa
		  * la llamada a las webs se realizará mediante web driver. Si no, se usará
		  * la librería JSOUP.
		  */
		if(bDynScrap) {
			
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(DynScrapingUnit.class.toString());
			}
			
			document = Jsoup.parse(dynScrapingUnit.getDynHtmlContent(strUrl, didEmpresa), 
					new URL(strUrl).toURI().toString());
		} else if(isMercadona) {
			
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(ScrapingMercadona.class.toString());
			}
			
			connection = scrapingMercadona.getConnection(strUrl, producto);	
			response = connection.execute();
		} else {
			
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(ScrapingUnit.class.toString(),Scraping.class);
			}
			
				connection = Jsoup.connect(strUrl)
						.userAgent(AGENT_ALL)
						.method(Connection.Method.GET)
						.referrer(url.getProtocol().concat(PROTOCOL_ACCESSOR).concat(url.getHost().concat("/")))
						.ignoreContentType(Boolean.TRUE)
						.validateTLSCertificates(Boolean.FALSE)
						.header(ACCEPT_LANGUAGE, ES_ES)
						.header(ACCEPT_ENCODING, GZIP_DEFLATE_SDCH)
						.header(ACCEPT, ACCEPT_VALUE)
						.maxBodySize(0)
						.timeout(100000);
				response = connection.execute();
		}
		
		/**
		 * Si el booleano de scraping dinamico no está activo y
		 * el mapa con las cookie para el login no es nulo, se
		 * añaden las cookies a la conexión.
		 */
		if(!bDynScrap && Objects.nonNull(mapLoginPageCookies)) {
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
	
	/**
	 * Extrae la marca del principio de los resultado para
	 * no distorsionar el resultado.
	 * 
	 * @param nomProducto
	 * @return
	 */
	private String eliminarMarcaPrincipio(final String nomProducto) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		StringBuilder resultado = new StringBuilder(10);
		
		/**
		 * Se corta el nombre del producto y se añade e un array.
		 */
		String[] nomProdSeparado = nomProducto.trim().split(" ");
		
		
		/**
		 * Se valida el array. En caso de que sea nulo
		 * termina el proceso.
		 */
		if(Objects.isNull(nomProdSeparado)) {
			return "null";
		}
		
		for (int i = 0; i < nomProdSeparado.length; i++) {
			if(!nomProdSeparado[i].equals(nomProdSeparado[i].toUpperCase())) {
				resultado.append(nomProdSeparado[i]).append(" ");
			}
		}
		
		return resultado.toString();
	}	
	
	/**
	 * Valida los datos extraidos de los elementos.
	 * 
	 * @param strResult
	 * @param strUrl
	 * @return String
	 * @throws MalformedURLException
	 */
	private String validaResultadoElementValue(String strResult, 
			final String strUrl) throws MalformedURLException {
		
		int iend = -1;
		String caracteres;
		
		/**
		 * Se valida el párametro de entrada.
		 */
		if(Objects.isNull(strResult)){
			return strResult;
		} else {
			iend = strResult.indexOf(LEFT_PARENTHESIS_0);
		}
		
		/**
		 * Se crea un objeto URL a partir del párametro.
		 * Con los metodos del objeto url se compone la
		 * estructura de la URL de la empresa.
		 */		
		URL url = new URL(strUrl);
		String strUrlEmpresa = url.getProtocol().concat(PROTOCOL_ACCESSOR).concat(url.getHost());
		
		/**
		 * Si el valor contiene un paraentesis, se
		 * extrae el texto hasta dicho parentesis.
		 */
		if(iend != -1) {
			strResult = strResult.substring(0, 
					strResult.indexOf(LEFT_PARENTHESIS_0)-1);
		}
		
		/**
		 * En este punto se compone la URL de la imagen del producto.
		 */
		if(Objects.nonNull(strResult) && strResult.trim().startsWith(DOBLE_BARRA)) {
			caracteres = HTTPS.concat(strResult);
		} else if(Objects.nonNull(strResult) && strResult.trim().startsWith(BARRA)) {
			caracteres = strUrlEmpresa.concat(strResult); 
		} else {
			caracteres = strResult;
		}
		
		return formatoCaracteres(caracteres);
	}	
	
	/**
	 * Realiza el ajuste de una cadena por la derecha, para proporcionarle una
	 * longitud determinada mediante la adicion de un tipo de caracter dado
	 *
	 * @param pstrCadena   cadena
	 * @param piLongitud   Longitud de la cadena
	 * @param pchrCaracter caracter
	 * @return cadena ajustada
	 * @modelguid {96426349-1D0C-49A4-AB02-A1AFC0B874CD}
	 */
	private String ajustarDerecha(String strCadena, int iLongitud, char chrCaracter) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return anadirCaracteres(strCadena, chrCaracter, iLongitud, 0);
	}
	
	private String ajustarIzquierda(String strCadena, int iLongitud, char chrCaracter) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return anadirCaracteres(strCadena, chrCaracter, iLongitud, 1);
	}
	
	/**
	 * Realiza la adicion de caracteres a una cadena para proporcionarle una
	 * longitud determinada
	 *
	 * @param pstrCadena   cadena
	 * @param piLongitud   Longitud de la cadena
	 * @param pchrCaracter caracter
	 * @param piTipo       Tipo de adicion (Izquierda o Derecha)
	 * @return cadena ajustada
	 * @modelguid {360E79DB-48FE-4795-9E71-37FCA8F0B22D}
	 */
	private String anadirCaracteres(String strCadena, char chrCaracter, int iLongitud, int iTipo) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		final StringBuilder sbResultado = new StringBuilder(10);
		if (iTipo == 0) {
			sbResultado.append(strCadena);
		}
		final int dif = iLongitud - strCadena.length();
		for (int i = 0; i < dif; i++) {
			sbResultado.append(chrCaracter);
		}

		if (iTipo == 1) {
			sbResultado.append(strCadena);
		}

		return sbResultado.toString();
	}
	
	private String extraerValorDelElemento(int l, Element elem, List<String> lista, String cssSelector) {
		
		switch (l) {
		case 1:
			return elem.select(lista.get(0)).text();
		case 2:
			return elem.select(lista.get(0)).attr(lista.get(1));
		default:
			return elem.select(cssSelector).text();
		}
	}
	
	private static void setListEmpresaDto(List<EmpresaDTO> listEmpresaDto) {
		Scraping.listEmpresaDto = listEmpresaDto;
	}
	
	private String formatoCaracteres(final String cadena) {
		
		if(Objects.isNull(cadena)) {
			return null;
		}
		
		String resultado = replaceParenthesis(cadena);
		
		if(Objects.isNull(resultado)) {
			return null;
		}
		
		resultado = formatSybol(resultado);
		
		return resultado;
	}
	
	private static String replaceParenthesis(String cadena) {

		if(Objects.isNull(cadena)) {
			return null;
		}
		
		String resultado;
		
		resultado = cadena.replaceAll(LEFT_PARENTHESIS, EMPTY_STRING);
		resultado = resultado.replaceAll(RIGTH_PARENTHESIS, EMPTY_STRING);
		
		return resultado;
	}
	
	/**
     * Metodo que contene combinaciones de palabras 
     * no permitidas. Las palabras no permitidas se
     * reemplazan por espacios en blanco.
     * 
     * @param value
     * @return String
     */
	private String sanitizeValue(String value) {
		
		if(Objects.isNull(value)) {
			return null;
		}
		
		value = value.replaceAll("\\*", EMPTY_STRING);
		value = value.replaceAll("/", EMPTY_STRING);
		value = value.replaceAll("//", EMPTY_STRING);
		value = value.replaceAll("<", EMPTY_STRING).replaceAll(">", EMPTY_STRING);
		value = value.replaceAll("\\{", EMPTY_STRING).replaceAll("\\}", EMPTY_STRING);
		value = value.replaceAll("\\[", EMPTY_STRING).replaceAll("\\]", EMPTY_STRING);
		value = value.replaceAll("\\(", EMPTY_STRING).replaceAll("\\)", EMPTY_STRING);
		value = value.replaceAll("'", EMPTY_STRING);
		value = value.replaceAll("eval\\((.*)\\)", EMPTY_STRING);
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", EMPTY_STRING);
		value = value.replaceAll("script", EMPTY_STRING);
		value = value.replaceAll("select", EMPTY_STRING).replaceAll("SELECT", EMPTY_STRING);
		value = value.replaceAll("insert", EMPTY_STRING).replaceAll("INSERT", EMPTY_STRING);
		value = value.replaceAll("delete", EMPTY_STRING).replaceAll("DELETE", EMPTY_STRING);
		value = value.replaceAll("alter", EMPTY_STRING).replaceAll("ALTER", EMPTY_STRING);
		value = value.replaceAll("drop", EMPTY_STRING).replaceAll("DROP", EMPTY_STRING);

		return value;
	}
	
	private String formatSybol(String cadena) {
		
		String resultado = EMPTY_STRING;
		
		if(Objects.isNull(cadena)) {
			return resultado;
		}
		resultado = cadena.replaceAll("€", "eur");
		resultado = resultado.replaceAll("Kilo", "private static final Logger LOGGER.");
		resultado = resultado.replaceAll(" / ", "/");
		resultado = resultado.replaceAll(" \"", "\"");
		return resultado;
	}
}
