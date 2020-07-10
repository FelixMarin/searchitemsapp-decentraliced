package com.searchitemsapp.processdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.IFUrlImpl;

/**
 * En esta clase se configuran las URLs de los
 * sitios web a los que posteriormente se extraerá
 * la información.
 *  
 * @author Felix Marin Ramirez
 *
 */
public class UrlComposer extends ProcessDataLogin implements IFUrlComposer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlComposer.class);   
	
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
	private IFUrlImpl urlImpl;
	
	@Autowired
	private CategoriaDTO categoriaDto;
	
	@Autowired 
	private PaisDTO paisDto;
	
	/*
	 * Constructor
	 */
	public UrlComposer() {
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
		 * Se establece el identificador de pais y de categoria.
		 */
		paisDto.setDid(NumberUtils.toInt(didPais));		
		categoriaDto.setDid(NumberUtils.toInt(didCategoria));
		
		/**
		 * De la base de datos se obtienen las URLs asociadas a la empresa. 
		 */
		List<UrlDTO> pListResultadoDto  = urlImpl.obtenerUrlsPorIdEmpresa(paisDto, categoriaDto, empresas);
		
		/**
		 * Se comprueba que el nombre del producto sea una palabra válida.
		 */
		String productoTratadoAux = tratarProducto(producto);
		
		/**
		 * Se crea la variable que almacenará el listado de URLs.
		 */
		List<UrlDTO> listUrlDto = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		/**
		 * Bucle que reemplaza el comodín '{1}' por el nombre del
		 * producto a buscar.  
		 */
		for (UrlDTO urlDto : pListResultadoDto) {
			
			/**
			 * Se extraen los selectores de la lista. Estos selectores 
			 * correspondietes a la página web que va a ser revisada.
			 */
			cargaSelectoresCss(urlDto, listTodosSelectoresCss);
			
			/**
			 * Se comprueba que la URL está activada y se permite
			 * configurarla.
			 * 
			 * Para 'EROSKI','SIMPLY' y 'CONDIS', el tratamiento de sus
			 * URLs es diferente por lo que tiene sus propios métodos.
			 * 
			 * El resto de supermercados tienen todos el mismo tratamiento.
			 */
			String productoTratado;	
			if(urlDto.getBolActivo().booleanValue()) {
				if(mapEmpresas.get(EROSKI).getDid().equals(urlDto.getDidEmpresa())) {
					productoTratado = reemplazarCaracteresEroski(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(mapEmpresas.get(SIMPLY).getDid().equals(urlDto.getDidEmpresa())) {
					productoTratado = reeplazarCaracteresSimply(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(mapEmpresas.get(CONDIS).getDid().equals(urlDto.getDidEmpresa())) {
					productoTratado = reeplazarTildesCondis(producto);
					productoTratado = reeplazarCaracteresCondis(productoTratado);
					productoTratado = tratarProducto(productoTratado);
				} else {
					productoTratado = productoTratadoAux;
				}
				
				/**
				 * Se reemplaza el 'wild card' por el nombre del producto.
				 */
				String urlAux = urlDto.getNomUrl();
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
	
	/*
	 * Métodos privados 
	 */
	
	/**
	 * Este método obtinen los selectores correspondietes
	 * a cada una de las empresas solicitadas en la solicitud 
	 * de servicio.
	 * 
	 * @param resDtoUrls
	 * @param listTodosElementNodes
	 */
	private void cargaSelectoresCss(UrlDTO resDtoUrls, List<SelectoresCssDTO> listTodosElementNodes) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Se comprueba que los parametros no sean nulos.
		 */
		if(Objects.nonNull(resDtoUrls) && 
				Objects.nonNull(resDtoUrls.getDidEmpresa()) &&
				Objects.nonNull(listTodosElementNodes) &&
				!listTodosElementNodes.isEmpty()) {
			
			/**
			 * Se obtiene el identificador de la empresa y
			 * se crean dos lista, una con los selectores y
			 * otra con un objeto tabja de selectores.
			 */
			Integer empDidEnUlrs = resDtoUrls.getDidEmpresa();
			
			/**
			 * Se recorre la lista con todos los selectores.
			 * Si la lista contiene alguno que coincida con el 
			 * identificador de la empresa actua se añadirá a
			 * la lista de nodos por empresa.
			 */
			SelectoresCssDTO selectoresCssDTO = null;
			
			for (SelectoresCssDTO elementNodesDTO : listTodosElementNodes) {
				if (elementNodesDTO.getDidEmpresa().equals(empDidEnUlrs)) {
					selectoresCssDTO = elementNodesDTO;
				}
			}
			
			if(Objects.nonNull(selectoresCssDTO)) {	
				Map<String, String> mapSelectores  = new LinkedHashMap<>();
				mapSelectores.put("SCRAP_PATTERN", selectoresCssDTO.getScrapPattern());
				mapSelectores.put("SCRAP_NO_PATTERN", selectoresCssDTO.getScrapNoPattern());
				mapSelectores.put("SEL_IMAGE", selectoresCssDTO.getSelImage());
				mapSelectores.put("SEL_LINK_PROD", selectoresCssDTO.getSelLinkProd());
				mapSelectores.put("SEL_PAGINACION", selectoresCssDTO.getSelPaginacion());
				mapSelectores.put("SEL_PRECIO", selectoresCssDTO.getSelPrecio());
				mapSelectores.put("SEL_PRECIO_KILO", selectoresCssDTO.getSelPreKilo());
				mapSelectores.put("SEL_PRODUCTO", selectoresCssDTO.getSelProducto());
				mapSelectores.put("BOOL_ACTIVO", selectoresCssDTO.getBolActivo().toString());
				mapSelectores.put("DID", selectoresCssDTO.getDid().toString());
				mapSelectores.put("FEC_MODIFICACION", selectoresCssDTO.getFecModificacion().toString());
				resDtoUrls.setSelectores(mapSelectores);
			}
		}
	}
}
