package com.searchitemsapp.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.diccionario.FillSelectores;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.UrlImpl;




/**
 * En esta clase se configuran las URLs de los
 * sitios web a los que posteriormente se extraerá
 * la información.
 *  
 * @author Felix Marin Ramirez
 *
 */
public class UrlTreatment extends Scraping {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlTreatment.class);   
	
	/*
	 * Constantes Globales
	 */
	private static final String EROSKI = "EROSKI";
	private static final String SIMPLY = "SIMPLY";
	private static final String CONDIS = "CONDIS";
	private static final String WILDCARD = "{1}";
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private UrlImpl urlImpl;
	
	@Autowired
	private CategoriaDTO categoriaDto;
	
	@Autowired 
	private PaisDTO paisDto;
	
	@Autowired
	private FillSelectores fillSelectores;
	
	/*
	 * Constructor
	 */
	public UrlTreatment() {
		super();
	}
	
	/**
	 * Método que reemplaza los carateres comodines
	 * por el nombre del producto a buscar. Los comodines
	 * tienen la siguiente forma '{1}'.
	 * 
	 * @param didPais
	 * @param didCategoria
	 * @param producto
	 * @param empresas
	 * @param listTodosSelectoresCss
	 * @return List<UrlDTO>
	 * @throws IOException
	 */
	public List<UrlDTO> replaceWildcardCharacter(final String didPais, 
			final String didCategoria, 
			final String producto,
			final String empresas,
			final List<SelectoresCssDTO> listTodosSelectoresCss) 
			throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Variable locales.
		 */
		List<UrlDTO> listUrlDto;
		String productoTratado;	
		String productoTratadoAux;
		String urlAux;
		
		/**
		 * Se establece el identificador de pais y de categoria.
		 */
		paisDto.setDid(desformatearEntero(didPais));		
		categoriaDto.setDid(desformatearEntero(didCategoria));
		
		/**
		 * De la base de datos se obtienen las URLs asociadas a la empresa. 
		 */
		List<UrlDTO> pListResultadoDto  = urlImpl.obtenerUrlsPorIdEmpresa(paisDto, categoriaDto, empresas);
		
		/**
		 * Se comprueba que el nombre del producto sea una palabra válida.
		 */
		productoTratadoAux= tratarProducto(producto);
		
		/**
		 * Se crea la variable que almacenará el listado de URLs.
		 */
		listUrlDto = new ArrayList<>(10);
		
		/**
		 * Bucle que reemplaza el comodín '{1}' por el nombre del
		 * producto a buscar.  
		 */
		for (UrlDTO urlDto : pListResultadoDto) {
			
			/**
			 * Se extraen los selectores de la lista. Estos selectores 
			 * correspondietes a la página web que va a ser revisada.
			 */
			fillSelectores.fillSelectoresCss(urlDto, listTodosSelectoresCss);
			
			/**
			 * Se comprueba que la URL está activada y se permite
			 * configurarla.
			 * 
			 * Para 'EROSKI','SIMPLY' y 'CONDIS', el tratamiento de sus
			 * URLs es diferente por lo que tiene sus propios métodos.
			 * 
			 * El resto de supermercados tienen todos el mismo tratamiento.
			 */
			if(urlDto.getBolActivo()) {
				if(getMapEmpresas().get(EROSKI) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reemplazarCaracteresEroski(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(getMapEmpresas().get(SIMPLY) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reeplazarCaracteresSimply(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(getMapEmpresas().get(CONDIS) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reeplazarTildesCondis(producto);
					productoTratado = reeplazarCaracteresCondis(productoTratado);
					productoTratado = tratarProducto(productoTratado);
				} else {
					productoTratado = productoTratadoAux;
				}
				
				/**
				 * Se reemplaza el 'wild card' por el nombre del producto.
				 */
				urlAux = urlDto.getNomUrl();
				urlAux = urlAux.replace(WILDCARD, productoTratado);
				urlDto.setNomUrl(urlAux);
				listUrlDto.add(urlDto);
			} else {
				if(LOGGER.isInfoEnabled()) {
					LOGGER.info("La URL: ".
							concat(urlDto.getDid().toString()).
							concat(" esta deshabilitada."));
				}
			}
		}
		return listUrlDto;
	}

	/**
	 * Reemplaza el comodín de la URL del diccionario.
	 * 
	 * @param url
	 * @param producto
	 * @return String
	 * @throws IOException
	 */
	public String replaceWildcardCharacterDiccionario(final String url, 
			final String producto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		String productoTratado= tratarProducto(producto);
		String urlAux = url;
		return urlAux.replace(WILDCARD, productoTratado);
	}
	
	/*
	 * Metodos privados
	 */
	/**
	 * Convierte una cadena a tipo int
	 *
	 * @param pStrCadena
	 * @return
	 */
	private int desformatearEntero(String pStrCadena) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}

		int iResultado = 0;
		if (!"".contentEquals(pStrCadena)) {
			try {
				iResultado = Integer.parseInt(pStrCadena);
			} catch (NumberFormatException nfe) {
				if(LOGGER.isWarnEnabled()) {
					LOGGER.warn(Thread.currentThread().getStackTrace()[1].toString(), nfe);
				}
			}
		}
		return iResultado;
	}
}
