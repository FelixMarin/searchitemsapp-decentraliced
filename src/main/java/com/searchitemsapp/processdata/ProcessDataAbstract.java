package com.searchitemsapp.processdata;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.searchitemsapp.impl.IFImplementacion;
import com.searchitemsapp.processdata.interfaces.IFProcessDataCondis;
import com.searchitemsapp.processdata.interfaces.IFProcessDataEroski;
import com.searchitemsapp.processdata.interfaces.IFProcessDataMercadona;
import com.searchitemsapp.processdata.interfaces.IFProcessDataSimply;


/**
 * Clase abstracta que cotiene métodos 
 * expecializados para módulo de web scraping.
 * <br>
 * {@link Jsoup}
 * 
 * @author Felix Marin Ramirez
 *
 */
public abstract class ProcessDataAbstract {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataAbstract.class);   
	
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
	
	private static final String LEFT_PARENTHESIS_0 = " (";
	private static final String DOBLE_BARRA = "//";
	private static final String HTTPS = "https:";
	private static final String BARRA = "/";
	private static final String RIGTH_PARENTHESIS = "\\)";
	private static final String LEFT_PARENTHESIS = "\\(";
	private static final String REGEX_PERCENT_DOLAR = "(\\%|\\$00)";
	private static final String DOT_STRING = ".";
	private static final String COMMA_STRING = ",";
	private static final String PIPE_STRING = "|";
	private static final String SCRIPT = "script";
	
	/*
	 * Variables Globales
	 */
	@Resource(name="listTodasMarcas")
	private List<MarcasDTO> listTodasMarcas;
	
	@Resource(name="mapEmpresas")
	protected Map<String,EmpresaDTO> mapEmpresas;
	
	@Resource(name="mapDynEmpresas")
	private Map<Integer,Boolean> mapDynEmpresas;
	
	@Autowired 
	ProcessDataDynamic procesDataDynamic;
	
	@Autowired
	private IFImplementacion<SelectoresCssDTO, EmpresaDTO> selectoresCssImpl;

	@Autowired
	private IFImplementacion<EmpresaDTO, CategoriaDTO> iFEmpresaImpl;
	
	@Autowired
	private IFImplementacion<MarcasDTO, CategoriaDTO> iFMarcasImp;
		
	@Autowired
	private IFProcessDataMercadona scrapingMercadona;
				
	@Autowired
	private IFProcessDataCondis scrapingCondis;
	
	@Autowired
	private IFProcessDataEroski scrapingEroski;
	
	@Autowired
	private IFProcessDataSimply scrapingSimply;
		
	@Autowired
	private ProcessDataEmpresasFactory processDataEmpresasFactory;
	
	/*
	 * Constructor
	 */
	protected ProcessDataAbstract() {
		super();
	}
	
	/**
	 * Método que carga los datos estáticos usados en el proceso
	 * de las peticiones. Este método solo se ejecuta una vez,
	 * los datos se cachean mientras esté la aplicación activa.
	 */
	public void applicationData() {
		
		try {
			
			/**
			 * Se cargan todas las marcas de 
			 * productos desde la base de datos.
			 */
			listTodasMarcas = iFMarcasImp.findAll();
			
			/**
			 * Se establece la lista de empresas en la 
			 * variable global estática.
			 */
			List<EmpresaDTO> listEmpresaDto = iFEmpresaImpl.findAll();

			/**
			 * En este punto se establece la lista de empresas
			 * en la variable estática global y se indica en otro
			 * mapa el tipo de raspado que se va a utilizar en el
			 * proceso. 
			 */
			for (EmpresaDTO empresaDTO : listEmpresaDto) {
				mapEmpresas.put(empresaDTO.getNomEmpresa(), empresaDTO);
				mapDynEmpresas.put(empresaDTO.getDid(), empresaDTO.getBolDynScrap());
			}		
		}catch(IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
	}
	
	/**
	 * Este metodo carga todas las marcas de 
	 * productos desde la base de datos. Se
	 * cachea al principio y dura durante toda
	 * la ejecución del programa,
	 */
	public void cargarTodasLasMarcas() {
		try {
			if(Objects.isNull(listTodasMarcas)) {
				listTodasMarcas = iFMarcasImp.findAll();
			}
		}catch(IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
	}
	
	public List<SelectoresCssDTO> listSelectoresCssPorEmpresa(final String didEmpresas) throws IOException {

		String emp;
		
		if("ALL".equalsIgnoreCase(didEmpresas)) {
			emp = CommonsPorperties.getValue("flow.value.all.id.empresa");
		} else {
			emp = didEmpresas;
		}

		if(StringUtils.isAllEmpty(emp)) {
			return new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(emp, COMMA_STRING); 			
		SelectoresCssDTO selectoresCssDto = new SelectoresCssDTO();
		List<Integer> listaAux = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		while (st.hasMoreElements()) {
			listaAux.add(Integer.parseInt(String.valueOf(st.nextElement())));
			
		}
		
		List<SelectoresCssDTO> listaSelectoresResultado = new ArrayList<>();
		
		for (Integer didEmpresa : listaAux) {
			EmpresaDTO empresaDto = new EmpresaDTO();
			empresaDto.setDid(didEmpresa);			
			List<SelectoresCssDTO> lsel = selectoresCssImpl.findByTbSia(selectoresCssDto, empresaDto);
			listaSelectoresResultado.addAll(lsel);
		}

		
		return listaSelectoresResultado;
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
			final String producto) 
					throws IOException, URISyntaxException, InterruptedException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
    	List<Document> listDocuments = new ArrayList<>(NumberUtils.INTEGER_ONE);
    	
		/**
		 * El identificador de la empresa se añade 
		 * a una variable.
		 */
		int idEmpresa = urlDto.getDidEmpresa();			
    	
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
    	List<String> liUrlsPorEmpresaPaginacion = urlsPaginacion(document, urlDto, idEmpresa);
   		
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
	 * {@link ProcessDataEmpresasFactory#getScrapingEmpresa(int)}
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @param idEmpresa
	 * @return List<String>
	 * @throws MalformedURLException
	 */
	protected List<String> urlsPaginacion(final Document document, 
			final UrlDTO urlDto, final int idEmpresa) 
					throws MalformedURLException {
		
		List<String> listUrlsResultado = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Se obtiene el listado de URLs correspodientes
		 * a la empresa a la que se le va a realizar la
		 * solicitud.
		 */
		listUrlsResultado.addAll(processDataEmpresasFactory
				.getScrapingEmpresa(idEmpresa).getListaUrls(document, urlDto));
		
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
			return StringUtils.EMPTY;
		}
		
		if(cadena.indexOf(CHAR_ENIE_COD) != -1) {
			return cadena;
		}
		String resultado = cadena.replace(STRING_ENIE_MAY, UNICODE_ENIE);
		resultado = Normalizer.normalize(resultado.toLowerCase(), Normalizer.Form.NFD);
		resultado = resultado.replaceAll(REEMPLAZABLE_TILDES, StringUtils.EMPTY);
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

		List<String> tokens = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Se añaden todas las palabras que componen 
		 * el producto en una lista en mayúsculas.
		 */
		for (int i = 0; i < arProducto.length; i++) {
			tokens.add(arProducto[i].toUpperCase());
		}
		
		//INI - Bloque de código donde se forma el patrón.
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append("(");
		for (String string : tokens) {
			stringBuilder.append(".*").append(string);
		}
		stringBuilder.append(")");
		
		Collections.reverse(tokens);
		
		stringBuilder.append("|(");
		for (String string : tokens) {
			stringBuilder.append(".*")
			.append(string);
		}
		stringBuilder.append(")");
		//FIN - Bloque de código donde se forma el patrón.
		
		
		/**
		 * El resultado es un objeto de tipo 
		 * patrón creado a partir del patrón
		 * en formato String. 
		 */
		return Pattern.compile(stringBuilder.toString());
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
		if(Objects.isNull(cssSelector) || StringUtils.EMPTY.equals(cssSelector)) {
			return StringUtils.EMPTY;
		}
		
		List<String> lista = new ArrayList<>(NumberUtils.INTEGER_ONE);
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
		if(mapEmpresas.get(MERCADONA).getDid().equals(urlDto.getDidEmpresa())) {
			
			strResult = scrapingMercadona.getResult(elem, cssSelector);
			
		} else if(mapEmpresas.get(CONDIS).getDid().equals(urlDto.getDidEmpresa()) &&
				SCRIPT.equalsIgnoreCase(lista.get(0))) {	
			
			strResult = scrapingCondis.tratarTagScript(elem, lista.get(0));
			
		} else if(mapEmpresas.get(ELCORTEINGLES).getDid().equals(urlDto.getDidEmpresa())) {
			strResult = elem.selectFirst(CommonsPorperties.getValue("flow.value.pagina.precio.eci.offer")).text();
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
		if(iIdEmpresa == mapEmpresas.get(HIPERCOR).getDid() ||
				iIdEmpresa == mapEmpresas.get(DIA).getDid() ||
				iIdEmpresa == mapEmpresas.get(ELCORTEINGLES).getDid()) {
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
			final UrlDTO urlDto, 
			final String ordenacion) throws IOException {
		
		/**
		 * Variables utilizadas en el proceso.
		 */
		int idEmpresaActual = urlDto.getDidEmpresa();
		ResultadoDTO resDto = new ResultadoDTO();
		
		/**
		 * Se setean los valores recuperados de los elementos
		 * en la variable resultado. Los valores son todos los
		 * que devolverá el servicio.
		 */
		resDto.setImagen(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_IMAGE"), urlDto));
		resDto.setNomProducto(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_PRODUCTO"), urlDto));
		resDto.setDesProducto(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_PRODUCTO"), urlDto));
		resDto.setPrecio(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_PRECIO"), urlDto));
		resDto.setPrecioKilo(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_PRECIO_KILO"), urlDto));
		resDto.setNomUrl(elementoPorCssSelector(elem, urlDto.getSelectores().get("SEL_LINK_PROD"), urlDto));
		resDto.setDidEmpresa(urlDto.getDidEmpresa());
		resDto.setNomEmpresa(urlDto.getNomEmpresa());
		
		/**
		 * Dependiendo de la empresa, el tratamiento de las URLs 
		 * extraidas del elemento se realiza de diferente forma.
		 */
		if(idEmpresaActual == mapEmpresas.get(MERCADONA).getDid()) {
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
		
		String productoTratado=anadirCaracteres(producto, Character.MIN_VALUE, 0, 0);
		productoTratado=anadirCaracteres(productoTratado, Character.MIN_VALUE, 0, 1);
		
		Matcher matcher = Pattern.compile(REGEX_PERCENT_DOLAR).matcher(productoTratado);
		
		if(matcher.find()) {
			return productoTratado;
		} else {
			return URLEncoder.encode(productoTratado, StandardCharsets.UTF_8.toString());
		}
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
		boolean isMercadona = didEmpresa == mapEmpresas.get(MERCADONA).getDid();	
		boolean bDynScrap = mapDynEmpresas.get(didEmpresa);
		URL url = new URL(strUrl);
		 
		/**
		  * Si está activo el indicador de scraping dinámico asociado a la empresa
		  * la llamada a las webs se realizará mediante web driver. Si no, se usará
		  * la librería JSOUP.
		  */
		if(bDynScrap) {			
			document = Jsoup.parse(procesDataDynamic.getDynHtmlContent(strUrl, didEmpresa), 
					new URL(strUrl).toURI().toString());
		} else if(isMercadona) {			
			connection = scrapingMercadona.getConnection(strUrl, producto);	
			response = connection.execute();
		} else {			
			connection = Jsoup.connect(strUrl)
					.userAgent(AGENT_ALL)
					.method(Connection.Method.GET)
					.referrer(url.getProtocol().concat(PROTOCOL_ACCESSOR).concat(url.getHost().concat("/")))
					.ignoreContentType(Boolean.TRUE)
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
		
		/**
		 * Se corta el nombre del producto y se añade e un array.
		 */
		String[] nomProdSeparado = nomProducto.trim().split(StringUtils.SPACE);
		
		/**
		 * Se valida el array. En caso de que sea nulo
		 * termina el proceso.
		 */
		if(Objects.isNull(nomProdSeparado)) {
			return StringUtils.EMPTY;
		}
		
		StringBuilder stringBuilder = new StringBuilder(1);
		for (int i = 0; i < nomProdSeparado.length; i++) {
			
			String may = nomProdSeparado[i].toUpperCase();			
			if(nomProdSeparado[i].equals(may)) {
				continue;
			}
			
			stringBuilder.append(nomProdSeparado[i]).append(StringUtils.SPACE);
		}
		
		return stringBuilder.toString();
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
		String caracteres = StringUtils.EMPTY;
		if(Objects.nonNull(strResult) && strResult.trim().startsWith(DOBLE_BARRA)) {
			caracteres = HTTPS.concat(strResult);
		} else if(Objects.nonNull(strResult) && strResult.trim().startsWith(BARRA)) {
			caracteres = strUrlEmpresa.concat(strResult); 
		} else if(Objects.nonNull(strResult)){
			caracteres = strResult;
		}
		 
		String resultado = caracteres.replaceAll(LEFT_PARENTHESIS, StringUtils.EMPTY);
		resultado = resultado.replaceAll(RIGTH_PARENTHESIS, StringUtils.EMPTY);
		
		resultado = resultado.replace("€", " eur");
		resultado = resultado.replace("Kilo", "kg");
		resultado = resultado.replace(" / ", "/");
		resultado = resultado.replace(" \"", "\"");
		 
		return resultado;
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
		
		if(StringUtils.isAllEmpty(strCadena)) {
			return StringUtils.EMPTY;
		}
		
		StringBuilder stringBuilder = new StringBuilder(1);
		
		if (iTipo == 0) {
			stringBuilder.append(strCadena);
		}
		final int dif = iLongitud - strCadena.length();
		for (int i = 0; i < dif; i++) {
			stringBuilder.append(chrCaracter);
		}

		if (iTipo == 1) {
			stringBuilder.append(strCadena);
		}

		return stringBuilder.toString();
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
}
