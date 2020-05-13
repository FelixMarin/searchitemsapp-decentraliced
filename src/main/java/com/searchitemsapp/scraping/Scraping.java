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
 * expecializados para módulo de web scraping.
 * 
 * {@link Jsoup}
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
	private static String selectorPrecioECIOffer;
	private static String selectorPaginaSiguienteCarrefour;
	private static String accesoPopupPeso;
	private static String productLeftContainer;
	private static String selectorDescriptionText;
	
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

    	/**
    	 * Se 
    	 */
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
		
		List<String> listUrlsResultado = StringUtils.getNewListString();
		
		/**
		 * Si el identificador de empresa es el 
		 * que corresponde al diccionario, la
		 * ejecución termina. 
		 */
		if(getMapEmpresas().get(StringUtils.DICCIONARIO) == idEmpresa) {
			return StringUtils.getNewListString();
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
		
		/**
		 * Variables con los valores necesarios para 
		 * le proceso.
		 */
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

	/**
	 * Método que valida el resultado obtenido. 
	 * 
	 * @param arProducto
	 * @param nomProducto
	 * @param iIdEmpresa
	 * @param pattern
	 * @return boolean
	 */
	protected boolean validateContent(final String[] arProducto, 
			final String nomProducto, final int iIdEmpresa, final Pattern pattern) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Se comprueba que el nombre del producto no sea nulo.
		 */
		if(ClaseUtils.isNullObject(nomProducto) ||
				iIdEmpresa == ClaseUtils.ZERO_INT) {
			return Boolean.FALSE;
		} 
		
		/**
		 * Se elimina la marca de la descripción del producto
		 */
		String strProducto = filtroMarca(iIdEmpresa, nomProducto);
		
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
	
	/**
	 * Método que compone un patrón regex con el que se
	 * realizará el filtrado de productos a partir del 
	 * parámetro de entrada.
	 * 
	 * @param arProducto
	 * @return Pattern
	 */
	protected Pattern createPatternProduct(final String[] arProducto) {

		List<String> tokens = StringUtils.getNewListString();
		StringBuilder pattSb = StringUtils.getNewStringBuilder();
		
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
		if(StringUtils.isEmpty(cssSelector)) {
			return StringUtils.NULL_STRING;
		}
		
		List<String> lista = StringUtils.getNewListString();
		String strResult;
		
		/**
		 * Se extraen los selectores de la variable.
		 */
		StringTokenizer st = new StringTokenizer(cssSelector,StringUtils.PIPE);  
		
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
		if(getMapEmpresas().get(StringUtils.MERCADONA) == urlDto.getTbSiaEmpresa().getDid()) {
			
			strResult = scrapingMercadona.getResult(elem, cssSelector);
			
		} else if(getMapEmpresas().get(StringUtils.CONDIS) == urlDto.getTbSiaEmpresa().getDid() &&
				SCRIPT.equalsIgnoreCase(lista.get(0))) {	
			
			strResult = scrapingCondis.tratarTagScript(elem, lista.get(0));
			
		} else if(getMapEmpresas().get(StringUtils.ELCORTEINGLES) == urlDto.getTbSiaEmpresa().getDid() &&
				elem.select(getSelectorPrecioECIOffer()).size() > ClaseUtils.ZERO_INT) {
			
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
		if(iIdEmpresa == getMapEmpresas().get(StringUtils.HIPERCOR) ||
				iIdEmpresa == getMapEmpresas().get(StringUtils.DIA) ||
				iIdEmpresa == getMapEmpresas().get(StringUtils.ELCORTEINGLES)) {
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
							.toLowerCase(), 
							StringUtils.EMPTY_STRING)
							.trim();
				
				break;
			}
		}
		
		return strProducto;
	}
	
	/**
	 * Extrae la marca del principio de los resultado para
	 * no distorsionar el resultado.
	 * 
	 * @param nomProducto
	 * @return
	 */
	private String eliminarMarcaPrincipio(final String nomProducto) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		StringBuilder resultado = StringUtils.getNewStringBuilder();
		
		/**
		 * Se corta el nombre del producto y se añade e un array.
		 */
		String[] nomProdSeparado = nomProducto.trim().split(StringUtils.SPACE_STRING);
		
		
		/**
		 * Se valida el array. En caso de que sea nulo
		 * termina el proceso.
		 */
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
			if(mapEmpresas.size() == ClaseUtils.ZERO_INT) {
				
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
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
	}
	
	/**
	 * Este método se encarga de extraer cada uno de los datos 
	 * necesarios par componer un objeto de tipo resultado.
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
		if(idEmpresaActual == getMapEmpresas().get(StringUtils.MERCADONA)) {
			resDto.setNomUrlAllProducts(scrapingMercadona.getUrlAll(resDto));
			resDto.setImagen(resDto.getImagen().replace(StringUtils.COMMA_STRING, StringUtils.DOT_STRING));
		}else {
			resDto.setNomUrlAllProducts(urlDto.getNomUrl());
		}
		
		/**
		 * En este punto, el valor de la variable Precio/Kilo
		 * se trata para eliminar el caracter PIPE |.
		 */
		if(!StringUtils.isEmpty(resDto.getPrecioKilo()) &&
				resDto.getPrecioKilo().contains(StringUtils.PIPE)) {
			resDto.setPrecioKilo(resDto.getPrecioKilo().substring(resDto.getPrecioKilo()
					.indexOf(StringUtils.PIPE)+1,resDto.getPrecioKilo().length()).trim());
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
	 * Valida los datos extraidos de los elementos.
	 * 
	 * @param strResult
	 * @param strUrl
	 * @return String
	 * @throws MalformedURLException
	 */
	private String validaResultadoElementValue(String strResult, 
			final String strUrl) throws MalformedURLException {
		
		int iend = ClaseUtils.ONE_NEGATIVE_INT;
		String caracteres;
		
		/**
		 * Se valida el párametro de entrada.
		 */
		if(StringUtils.validateNull(strResult)){
			return strResult;
		} else {
			iend = strResult.indexOf(StringUtils.LEFT_PARENTHESIS_0);
		}
		
		/**
		 * Se crea un objeto URL a partir del párametro.
		 * Con los metodos del objeto url se compone la
		 * estructura de la URL de la empresa.
		 */		
		URL url = new URL(strUrl);
		String strUrlEmpresa = url.getProtocol().concat(StringUtils.PROTOCOL_ACCESSOR).concat(url.getHost());
		
		/**
		 * Si el valor contiene un paraentesis, se
		 * extrae el texto hasta dicho parentesis.
		 */
		if(iend != ClaseUtils.ONE_NEGATIVE_INT) {
			strResult = strResult.substring(ClaseUtils.ZERO_INT, 
					strResult.indexOf(StringUtils.LEFT_PARENTHESIS_0)-1);
		}
		
		/**
		 * En este punto se compone la URL de la imagen del producto.
		 */
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

	/**
	 * Este metodo carga todas las marcas de 
	 * productos desde la base de datos. Se
	 * cachea al principio y dura durante toda
	 * la ejecución del programa,
	 */
	protected void cargarTodasLasMarcas() {
		try {
			if(ClaseUtils.isNullObject(listTodasMarcas)) {
				setListTodasMarcas(iFMarcasImp.findAll());
			}
		}catch(IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
	}
	
	/**
	 * Establece el valor del selector en la variable de tipo UrlDTO.
	 * 
	 * @param urlDto
	 */
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
	
	protected static String getSelectorPrecioECIOffer() {
		return selectorPrecioECIOffer;
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

	private static void setListEmpresaDto(List<EmpresaDTO> listEmpresaDto) {
		Scraping.listEmpresaDto = listEmpresaDto;
	}
	
	protected static Map<Integer, Boolean> getMapDynScraping() {
		return mapDynScraping;
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
