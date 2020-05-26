package com.searchitemsapp.diccionario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.UrlImpl;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.scraping.ScrapingDiccionario;
import com.searchitemsapp.scraping.UrlTreatment;

/**
 * 
 * @author Felix Marin Ramirez
 * 
 * Clase empleada para validar el nombre del producto recibido en
 * la solicitud de servicio. Aquí se comprueba si la palabra existe o no
 * y si está bien escrita. Si la palabra no se puede comprobar
 * se dejará la pabra original. Si la palabra introducida debe
 * llevar tilde, esta clase se la añade.
 *
 */
public class Diccionario {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Diccionario.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String STRING_ENIE_MAY = "Ñ";
	private static final String UNICODE_ENIE = "u00f1";
	private static final char CHAR_ENIE_COD = '\u00f1';
	private static final String REEMPLAZABLE_TILDES = "[\\p{InCombiningDiacriticalMarks}]";
	private static final String STRING_ENIE_MIN = "ñ";
	
	
	/*
	 * Variables
	 */
	private static UrlDTO urlDTODiccionario;
	private static UrlDTO urlDto;

	@Autowired
	private UrlImpl urlImpl;
	
	@Autowired
	private FillSelectores fillSelectores;
	
	/**
	 * Constructor
	 */
	public Diccionario() {
		super();
	}

	/**
	 * Método capacitado para detectar si una palabra está
	 * mal escrita y corregirla siempre que sea posible.
	 * 
	 * @param producto
	 * @param urlTreatment
	 * @param listTodosSelectoresCss
	 * @param applicationContext
	 * @return String
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */
	public String corregirCaracter(final String producto, 
			final UrlTreatment urlTreatment, 
			final List<SelectoresCssDTO> listTodosSelectoresCss,
			final ApplicationContext applicationContext) 
			throws IOException, URISyntaxException, InterruptedException {
		
		/**
		 * Traza en el fichero de logs que indica por donde
		 * pasa el flujo del aplicación.
		 */
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Variables de ambito local
		 */
		ScrapingDiccionario scrapingDiccionario;		
		String urlAux;
		String strResultadoProducto;
		UrlDTO urlDtoAux;
		StringBuilder strResultado = new StringBuilder(10);
		
		/**
		 * Si el nombre del producto consta de más de una palabra,
		 * se divide y se añaden a un array.
		 */
		String[] arPalabras = producto.split(" ");
		
		/**
		 * En este loop, se realiza la comprobación de cada una
		 * de las palabras que componen el nombre del producto.
		 */
		for (int i = 0; i < arPalabras.length; i++) {
			
			/**
			 * En este punto,  cuando el flujo de la aplicación pasa la
			 * primera vez, la URL del diccionario es nula. El objeto
			 * se carga una vez y ya se queda cargado mientras la aplicacion
			 * esté en funcionamiento.
			 */
			if(Objects.isNull(urlDTODiccionario)) {
				urlDtoAux = new UrlDTO();
				urlDtoAux.setDid(Integer.parseInt(CommonsPorperties.getValue("flow.value.url.did.diccionario")));
				urlDtoAux.setTbSiaEmpresa(new TbSiaEmpresa());
				urlDtoAux.getTbSiaEmpresa().setDid(Integer.parseInt(CommonsPorperties.getValue("flow.value.empresa.did.diccionario")));
				setResultadoDTODiccionario(urlDtoAux);
				setUrlDto(urlImpl.findByDid(urlDtoAux));
				urlDTODiccionario.getTbSiaEmpresa().setDid(urlDtoAux.getTbSiaEmpresa().getDid());
				fillSelectores.fillSelectoresCss(urlDTODiccionario, listTodosSelectoresCss);
			}
			
			/**
			 * Se compone la URL en la que se realizará la busqueda de la palabra 
			 * en wordReference.
			 * Se reemplaza el patron '{1}' por la palabra a buscar.
			 */
			urlAux = urlTreatment.replaceWildcardCharacterDiccionario(urlDto.getNomUrl(), arPalabras[i]);
			urlDTODiccionario.setNomUrl(urlAux);
			setTbSiaSelectoresCss(urlDTODiccionario);
			
			/**
			 * Se obtiene una instancia de la clase que realiza el scriping. 
			 * Como parametro tiene la palabra actual de la iteracion y el 
			 * objeto con los datos reacionados con la url en la que se va
			 * a realizar la búsqueda.
			 */
			scrapingDiccionario =  applicationContext
					.getBean(ScrapingDiccionario.class, urlDTODiccionario, arPalabras[i]);
			
			/**
			 * Se obtiene la palabra analizada y se añade en una varible.
			 */
			strResultadoProducto = scrapingDiccionario
					.checkingHtmlDocument();
			
			/**
			 * Si el resultado de la validación es positivo, a partir de este momento 
			 * la palabra resultante será la utilizada para realizar la busqueda.
			 */
			strResultado.append("null"==strResultadoProducto?
					producto:strResultadoProducto)
					.append(" ");
		}
		
		/**
		 * Si la palabra tratada es esta vacía o la palabra es la misma
		 * con tilde y sin tilde, se dará como invalia y se dejará la 
		 * palabra original.
		 */
		if("".contentEquals(strResultado.toString()) ||
				!eliminarTildes(strResultado.toString().trim()).equalsIgnoreCase(producto)) {
			strResultado = new StringBuilder(10).append(producto);
		}
		
		/**
		 * Devuelva la palabra tratada o la original
		 */
		return strResultado.toString().trim();
	}
	
	/**
	 * Método set para añadir un nuevo objeto URL DTO.
	 */
	private void setResultadoDTODiccionario(UrlDTO urlDTODiccionario) {
		Diccionario.urlDTODiccionario = urlDTODiccionario;
	}
	
	/**
	 * Se establece la ruta del diccionario.
	 * 
	 * @param urlDto
	 */
	private void setUrlDto(UrlDTO urlDto) {
		Diccionario.urlDto = urlDto;
		setTbSiaSelectoresCss(Diccionario.urlDto);
	}
	
	/**
	 * Sa añade la URL al objeto al objeto que contiene
	 * los selectores para escrapear la web del diccionario.
	 * 
	 * @param urlDto
	 */
	private static void setTbSiaSelectoresCss(UrlDTO urlDto) {
		if(Objects.nonNull(urlDto) &&
				Objects.nonNull(Diccionario.urlDto.getTbSiaEmpresa())) {
			urlDto.setTbSiaSelectoresCsses(
					Diccionario.urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses());
		}
	}
	
	private String eliminarTildes(final String cadena) {
		
		if(Objects.isNull(cadena)) {
			return "null";
		}
		
		if(cadena.indexOf(CHAR_ENIE_COD) != -1) {
			return cadena;
		}
		
		String resultado = cadena.replace(STRING_ENIE_MAY, UNICODE_ENIE);
		resultado = Normalizer.normalize(resultado.toLowerCase(), Normalizer.Form.NFD);
		resultado = resultado.replaceAll(REEMPLAZABLE_TILDES, "");
		resultado = resultado.replace(UNICODE_ENIE, STRING_ENIE_MIN);
		return Normalizer.normalize(resultado, Normalizer.Form.NFC);
		
	}

}
