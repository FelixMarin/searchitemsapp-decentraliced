package com.searchitemsapp.diccionario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.UrlImpl;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.scraping.ScrapingDiccionario;
import com.searchitemsapp.scraping.UrlTreatment;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
	
	/**
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
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Variables de ambito local
		 */
		ScrapingDiccionario scrapingDiccionario;		
		String urlAux;
		String strResultadoProducto;
		UrlDTO urlDtoAux;
		StringBuilder strResultado = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		
		/**
		 * Si el nombre del producto consta de más de una palabra,
		 * se divide y se añaden a un array.
		 */
		String[] arPalabras = producto.split(StringUtils.SPACE_STRING);
		
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
			if(ClaseUtils.isNullObject(urlDTODiccionario)) {
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
			strResultado.append(StringUtils.NULL_STRING==strResultadoProducto?
					producto:strResultadoProducto)
					.append(StringUtils.SPACE_STRING);
		}
		
		/**
		 * Si la palabra tratada es esta vacía o la palabra es la misma
		 * con tilde y sin tilde, se dará como invalia y se dejará la 
		 * palabra original.
		 */
		if(StringUtils.isEmpty(strResultado.toString()) ||
				!StringUtils.eliminarTildes(strResultado.toString().trim()).equalsIgnoreCase(producto)) {
			strResultado = new StringBuilder(producto);
		}
		
		/**
		 * Devuelva la palabra tratada o la original
		 */
		return strResultado.toString().trim();
	}
	
	/**
	 * Método set para añadir un nuevo objeto URL DTO.
	 */
	public static void setResultadoDTODiccionario(UrlDTO urlDTODiccionario) {
		Diccionario.urlDTODiccionario = urlDTODiccionario;
	}
	
	/**
	 * Se establece la ruta del diccionario.
	 * 
	 * @param urlDto
	 */
	public static void setUrlDto(UrlDTO urlDto) {
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
		if(!ClaseUtils.isNullObject(urlDto) &&
				!ClaseUtils.isNullObject(Diccionario.urlDto.getTbSiaEmpresa())) {
			urlDto.setTbSiaSelectoresCsses(
					Diccionario.urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses());
		}
	}

}
