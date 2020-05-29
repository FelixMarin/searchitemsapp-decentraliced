package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;

/**
 * Esta clase se encarga de realizar la consulta al sitio web
 * del diccionario para validar la palabra o palabras que 
 * componen el nombre del producto solicitado.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingDiccionario extends Scraping {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingDiccionario.class);  
	
	/*
	 * Variables Globales
	 */
	private static boolean isCached;
    private UrlDTO urlDto;
    private String producto;
	
    /*
     * Constructor
     */
	public ScrapingDiccionario(UrlDTO urlDto, String producto) {
		super();
		this.urlDto = urlDto;
		this.producto = producto;
	}
	
	/**
	 * Metodo que extrae de la web del diccionario online la palabra
	 * buscada. En el caso del que la palabra buscada exista, este 
	 * método devolverá la palabra con su acento si lo llevara o
	 * devolverá null si no existe.
	 * 
	 * @return String
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */
	public String checkingHtmlDocument() throws IOException, URISyntaxException, InterruptedException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Valida los parametros introducidos
		 *  a través del constructor.
		 */
		if(!validarParametros()) {
			return null;
		}
		
		/**
		 * Se cargan en cache los parametros usados por 
		 * la aplicación. solo se ejecuta una vez.
		 */
		if(!isCached) {
			staticData();
			cargarTodasLasMarcas();
			isCached=true;
		}
		
		StringBuilder palabrasResultado = new StringBuilder(NumberUtils.INTEGER_ONE);
		String strProductoCorregido;
		Element elem = null;
				
		Document document = getHtmlDocument(urlDto, null, producto).get(0);
			
		 if(Objects.nonNull(document)) {

            Elements entrada = selectScrapPattern(document, 
            		urlDto.getSelectores().get("SCRAP_PATTERN"), 
            		urlDto.getSelectores().get("SCRAP_NO_PATTERN"));
            
            if(!entrada.isEmpty()) {
            	elem = entrada.get(0);
            }
            
            if(Objects.nonNull(elem)) {
            	strProductoCorregido = elementoPorCssSelector(elem, 
            			urlDto.getSelectores().get("SEL_PRODUCTO"), urlDto);
            	if(!StringUtils.EMPTY.contentEquals(strProductoCorregido)) {
            		palabrasResultado.append(strProductoCorregido.concat(StringUtils.SPACE));
            	}else {
            		return "null";
            	}
            }
		 }
		
		return palabrasResultado.toString().trim();
	}
	
	private boolean validarParametros() {
		return !StringUtils.EMPTY.contentEquals(urlDto.getNomUrl()) ||
				!StringUtils.EMPTY.contentEquals(producto);
	}
}
