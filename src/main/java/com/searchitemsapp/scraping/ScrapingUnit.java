package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.parsers.IFParser;

/**
 * Esta clase es la encargada de inicializar todo el proceso
 * de chequeo de los datos extraidos de las páginas web 
 * rastreadas. El proceso de consulta, extracción y 
 * refinamiento de datos se realiza en tiempo real, lo que
 * permite tener la información totalmente actualizada.
 * {@link Scraping}, {@link ScrapingLoginUnit}, 
 * {@link ScrapingDiccionario}, {@link UrlTreatment}
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
public class ScrapingUnit extends Scraping  implements Callable<List<ResultadoDTO>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingUnit.class);  
	
	/*
	 * Constantes Globales
	 */	
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";
	private static final String EMPTY_STRING = "";
	private static final String SEPARADOR_URL = "%20";
	private static final String SPACE_STRING = " ";
	
	/* 
	 * Variables Globales
	 */
	private static Map<Integer, Map<String, String>> mapaCookies = new HashMap<>(10);
	private UrlDTO urlDto; 
	private String producto;
	private String didPais; 
	private String didCategoria;
	private String ordenacion;
	
	@Autowired
	private ScrapingLoginUnit scrapingLoginUnit;
	
	@Autowired
	private ParserFactory parserFactory;	
	
	/*
	 * Constructor
	 */
	public ScrapingUnit(UrlDTO urlDto, String producto, 
			String didPais, String didCategoria, String ordenacion) {
		super();
		this.urlDto = urlDto;
		this.producto = producto;
		this.didPais = didPais;
		this.didCategoria = didCategoria;
		this.ordenacion = ordenacion;
		setTbSiaSelectoresCss(this.urlDto);
	}
	
	/**
	 * Método que realiza todo el proceso de web scraping.
	 * Primero obtiene los html de los sitios web, después
	 * Estrae la información relevante y la inserta en una 
	 * lista de objetos. Finalmente se devuelve una lista
	 * con todos los resultados obtenidos.
	 * 
	 * @return List<ResultadoDTO>
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */
	public  List<ResultadoDTO> checkHtmlDocument() throws IOException, URISyntaxException, InterruptedException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Se validan los valores de entrada. Si
		 * el resultado false, la ejecucion termina
		 * devolviendo nulo.
		 */
		if(validaUrlDto()) {
			return null;
		}
		
		/*
		 * Variables
		 */
		List<ResultadoDTO> lResultadoDto;
		Elements entradas;
		ResultadoDTO resDto;
		
		/**
		 * Se extraen los valores principales para validarlos
		 * y procesar según proceda.
		 */
		boolean bStatus = urlDto.getBolStatus();
		String[] arProducto = producto.split(SPACE_STRING);
		int iIdEmpresa = urlDto.getTbSiaEmpresa().getDid();
		Pattern pattern = createPatternProduct(arProducto);
		
		/**
		 * Si el arreglo que corresponde al nombre del
		 * producto solicitado es nulo, termina la 
		 * ejecucion.
		 */
		if(isNullProducto(arProducto)) {
			return null;
		}
		
		/**
		 * Si la página solicitada está disponible,
		 * se desencadenará todo el proceso, en otro
		 * caso la ejecución termina con nulo por
		 * respuesta.
		 */			
        if (getStatus(bStatus) == 200) {
        	
        	/**
        	 * Se obtienen los selectores que se usarán para
        	 * estraer la información de la página web.
        	 */
        	SelectoresCssDTO selectorCssDto = getParserS().toDTO(urlDto
        			.getTbSiaSelectoresCsses()
        			.get(0));
        	
        	/**
        	 * Se obtiene el listado de documentos de los que se
        	 * van a extraer los datos.
        	 */
        	List<Document> listDocuments = getHtmlDocument(urlDto, getCookies(iIdEmpresa), producto, selectorCssDto);
        	
        	lResultadoDto = new ArrayList<>(10);
        	
        	/**
        	 * Se itera sobre cada uno de los documentos
        	 * de los cuales se extraerá la información
        	 * que se necesita.
        	 */
        	for (Document document : listDocuments) {
        	
        		/**
        		 * Si el objeto es nulo se continua 
        		 * con la siguente iteración.
        		 */
	        	if(Objects.isNull(document)) {
	            	continue;
	            }
	            
	        	/**
	        	 * Se comprueba que la lista no esté vacía y que
	        	 * la URL sea válida. En el caso de que no se 
	        	 * cumpla la condición, termina el proceso y 
	        	 * returna nulo.
	        	 */
	            if(listDocuments.size() == 1 && 
	            		!validaURL(document.baseUri(),urlDto.getNomUrl()
	            				.replace(SPACE_STRING, SEPARADOR_URL))) {
	            	return null;
	            }
	            
	            /**
	             * Se extraen de la página los elementos
	             * que contiene los datos.
	             */
	            entradas = selectScrapPattern(document,
	            		selectorCssDto.getScrapPattern(), 
	            		selectorCssDto.getScrapNoPattern());

	            /**
	             * De cada elemento se extrae la
	             * información y se añade a un 
	             * objeto de tipo resultado.
	             */
	    		for (Element elem : entradas) {
	    			
	    			/**
	    			 * Si el elemento actual supera las
	    			 * validaciones, la operación 
	    			 * continua, en otro caso termina
	    			 * la iteración.
	    			 */
	    			if(validaSelector(elem)) {
	    				continue;
	    			}
	    			
	    			/**
	    			 * En este punto se extraen los datos del objeto
	    			 * element y se añaden en un objeto DTO.
	    			 */
	    			resDto = fillDataResultadoDTO(elem, selectorCssDto, urlDto, ordenacion);
	    			
	    			/**
	    			 * Se realiza la última comprovación y 
	    			 * se añade el resultado a la lista de
	    			 * resultados que será retornada.
	    			 */
	    			if(validaYCargaResultado(iIdEmpresa, arProducto, resDto,  pattern)) {
	    				lResultadoDto.add(resDto);
	    			}
		        }
        	}	
        } else {
        	return null;
        }   
        
        return lResultadoDto;
	}

	/**
	 * Método publico desde el que se inicializa el proceso.
	 * Devuelve una lista con los resultado obtenidos.
	 * 
	 * @return 
	 */
	@Override
	public List<ResultadoDTO> call() throws IOException, URISyntaxException, InterruptedException {
		return checkHtmlDocument();
	}
	
	//-- Métodos privados --//
	
	/**
	 * Devuelve el código del estado de la conexion a un recurso web.
	 * 
	 * @param bStatus
	 * @return int
	 */
	private int getStatus(final boolean bStatus) {
		return bStatus?getStatusConnectionCode(urlDto.getNomUrl()):200;
	}
	
	/**
	 * Método que realiza un login en el sitio web
	 * y devuelve las cookies de la sesión.
	 * 
	 * @param iIdEmpresa
	 * @return
	 * @throws IOException
	 */
	private Map<String, String> getCookies(final int iIdEmpresa) throws IOException {
		
		Map<String, String> mapLoginPageCookies = mapaCookies.get(iIdEmpresa);
    	
    	if(Objects.isNull(mapLoginPageCookies)) {
			mapLoginPageCookies = scrapingLoginUnit
					.checkingHtmlLoginDocument(didPais, didCategoria, iIdEmpresa, mapaCookies);
    	}
    	
    	return mapLoginPageCookies;
	}
	
	/**
	 * Valida el parámetro de entrada de tipo UrlDTO.
	 * 
	 * @return boolean
	 */
	private boolean validaUrlDto() {
		return Objects.isNull(urlDto) ||
				Objects.isNull(urlDto.getTbSiaEmpresa()) ||
				urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses().isEmpty();
	}
		
	/**
	 * Método que realiza validaciones de los valores
	 * obtenidos del objeto element. 
	 * 
	 * @param iIdEmpresa
	 * @param arProducto
	 * @param resDto
	 * @param pattern
	 * @return boolean
	 */
	private boolean validaYCargaResultado(final int iIdEmpresa, 
			final String[] arProducto, 
			final ResultadoDTO resDto, 
			final Pattern pattern) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Se comprueba que los principales valores
		 *  del producto no sean nulos.
		 */
		if(Objects.isNull(resDto.getNomProducto()) || iIdEmpresa == 0) {
			return Boolean.FALSE;
		} else if(EMPTY_STRING.contentEquals(resDto.getPrecio()) ||
				Objects.isNull(resDto.getPrecio()))  {
			return Boolean.FALSE; 
		} else if(Objects.isNull(resDto.getPrecioKilo()) || 
				EMPTY_STRING.contentEquals(resDto.getPrecioKilo())) {
			return Boolean.FALSE;
		}
		
		/**
		 * Se elimina la marca de la descripción del producto
		 */
		String strProducto = filtroMarca(iIdEmpresa, resDto.getNomProducto());
		
		if(Objects.isNull(strProducto)) {
			return Boolean.FALSE;
		}
		
		if(arProducto.length == 1 &&
				eliminarTildes(strProducto)
				.toLowerCase().startsWith(
						eliminarTildes(arProducto[0].trim())
				.toLowerCase().concat(SPACE_STRING))) {
			return Boolean.TRUE;			
		} else if(arProducto.length > 1) {			
			
			return pattern.matcher(eliminarTildes(strProducto).toUpperCase()).find();
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Método que retorna los selectores con los 
	 * que se accederá a las posiciones del html
	 * de las que se extraerán los datos.
	 * 
	 * @return IFParser<SelectoresCssDTO, TbSiaSelectoresCss> 
	 */
	private IFParser<SelectoresCssDTO, TbSiaSelectoresCss> getParserS() {
		return ((IFParser<SelectoresCssDTO, TbSiaSelectoresCss>) parserFactory.getParser(SELECTORES_PARSER));
	}	
}
