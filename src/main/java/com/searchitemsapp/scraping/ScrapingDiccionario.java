package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaSelectoresCss;

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
		if(validarParametros()) {
			return null;
		}
		
		/**
		 * Se inicializan todas las propiedes necesarias para
		 * la ejecución de la aplicación
		 */
		super.staticData();
		super.cargarTodasLasMarcas();
		super.setTbSiaSelectoresCss(urlDto);
		String strProductoCorregido;
		StringBuilder palabrasResultado = new StringBuilder(10);
				
		Element elem = null;
				
		Document document = getHtmlDocument(urlDto, null, producto, null).get(0);
			
		 if(Objects.nonNull(document)) {
            TbSiaSelectoresCss selectorCss = urlDto
            		.getTbSiaSelectoresCsses().get(0);
            
        Elements entrada = selectScrapPattern(document, selectorCss.getScrapPattern(), selectorCss.getScrapNoPattern());
            
            if(!entrada.isEmpty()) {
            	elem = entrada.get(0);
            }
            
            if(Objects.nonNull(elem)) {
            	strProductoCorregido = elementoPorCssSelector(elem, 
            			selectorCss.getSelProducto(),
            			urlDto);
            	if(!"".contentEquals(strProductoCorregido)) {
            		palabrasResultado.append(strProductoCorregido.concat(" "));
            	}else {
            		return "null";
            	}
            }
		 }
		
		return palabrasResultado.toString().trim();
	}
	
	public boolean validarParametros() {
		return "".contentEquals(urlDto.getNomUrl()) ||
				"".contentEquals(producto);
	}
}
