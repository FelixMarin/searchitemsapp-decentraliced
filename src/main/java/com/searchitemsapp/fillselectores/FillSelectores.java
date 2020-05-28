package com.searchitemsapp.fillselectores;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;



/**
 * Clase utiliazada para obtinen los selectores correspondietes
 * a cada una de las empresas solicitadas en la solicitud 
 * de servicio.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class FillSelectores {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FillSelectores.class);  
			
	/*
	 * Constructor
	 */
	public FillSelectores() {
		super();
	}
	
	/**
	 * Este metodo obtinen los selectores correspondietes
	 * a cada una de las empresas solicitadas en la solicitud 
	 * de servicio.
	 * 
	 * @param resDtoUrls
	 * @param listTodosElementNodes
	 */
	public void fillSelectoresCss(UrlDTO resDtoUrls, List<SelectoresCssDTO> listTodosElementNodes) {
		
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
			List<SelectoresCssDTO> listElementNodesDTOPorEmpresa = new ArrayList<>(10);
			
			/**
			 * Se recorre la lista con todos los selectores.
			 * Si la lista contiene alguno que coincida con el 
			 * identificador de la empresa actua se añadirá a
			 * la lista de nodos por empresa.
			 */
			for (SelectoresCssDTO elementNodesDTO : listTodosElementNodes) {
				if (empDidEnUlrs == elementNodesDTO.getDidEmpresa()) {
					listElementNodesDTOPorEmpresa.add(elementNodesDTO);
				}
			}
			
			resDtoUrls.setSelectores(new ArrayList<LinkedHashMap<String,String>>());
			
			for (SelectoresCssDTO selectoresCssDTO : listElementNodesDTOPorEmpresa) {
				LinkedHashMap<String, String> mapSelectores  = new LinkedHashMap<String, String>();
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
				resDtoUrls.getSelectores().add(mapSelectores);
			}
		}
	}
}
