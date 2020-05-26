package com.searchitemsapp.diccionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.parsers.IFParser;



/**
 * Clase utiliazada para obtinen los selectores correspondietes
 * a cada una de las empresas solicitadas en la solicitud 
 * de servicio.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
public class FillSelectores {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FillSelectores.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private ParserFactory parserFactory;
	
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
				Objects.nonNull(resDtoUrls.getTbSiaEmpresa()) &&
				Objects.nonNull(listTodosElementNodes) &&
				!listTodosElementNodes.isEmpty()) {
			
			/**
			 * Se obtiene el identificador de la empresa y
			 * se crean dos lista, una con los selectores y
			 * otra con un objeto tabja de selectores.
			 */
			Integer empDidEnUlrs = resDtoUrls.getTbSiaEmpresa().getDid();
			List<SelectoresCssDTO> listElementNodesDTOPorEmpresa = new ArrayList<>(10);
			List<TbSiaSelectoresCss> listTbSiaSelectoresCssPorEmpresa = new ArrayList<>(10);
			
			/**
			 * Se recorre la lista con todos los selectores.
			 * Si la lista contiene alguno que coincida con el 
			 * identificador de la empresa actua se añadirá a
			 * la lista de nodos por empresa.
			 */
			for (SelectoresCssDTO elementNodesDTO : listTodosElementNodes) {
				if (empDidEnUlrs == elementNodesDTO.getTbSiaEmpresa().getDid()) {
					listElementNodesDTOPorEmpresa.add(elementNodesDTO);
				}
			}
			
			/**
			 * Se recorre la lista de nodos por empresa y se a la lista de tablas
			 * por empresa. Notese el uso del parser de DTO a un objeto que repre
			 * senta la tabla selectores de base de datos.
			 */
			for (SelectoresCssDTO elementNodesDTO : listElementNodesDTOPorEmpresa) {
				listTbSiaSelectoresCssPorEmpresa.add(getParser().toTbSia(elementNodesDTO));
			}
			
			/**
			 * Se añade la lista de objetos tabla de selectores en el objeto 			 * 
			 */
			resDtoUrls.getTbSiaEmpresa().setTbSiaSelectoresCsses(listTbSiaSelectoresCssPorEmpresa);
		}
	}
	
	/**
	 * Recupera el parser encargado de transformar 
	 * un objeto DTO a otro de tipo tabla.
	 * 
	 * @return IFParser<SelectoresCssDTO, TbSiaSelectoresCss>
	 */
	private IFParser<SelectoresCssDTO, TbSiaSelectoresCss> getParser() {
		return ((IFParser<SelectoresCssDTO, TbSiaSelectoresCss>) parserFactory.getParser(SELECTORES_PARSER));
	}
}
