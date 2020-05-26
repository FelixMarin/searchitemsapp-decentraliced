package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.model.TbSiaSelectoresCss;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class SelectoresCssParser implements IFParser<SelectoresCssDTO, TbSiaSelectoresCss> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectoresCssParser.class);  
	
	/*
	 * Constructor
	 */
	public SelectoresCssParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaSelectoresCss
	 * @return SelectoresCssDTO
	 */
	@Override
	public SelectoresCssDTO toDTO(TbSiaSelectoresCss tbSiaSelectoresCss) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		SelectoresCssDTO selectoresCssDTO = new SelectoresCssDTO();
		
		selectoresCssDTO.setDid(tbSiaSelectoresCss.getDid());
		selectoresCssDTO.setBolActivo(tbSiaSelectoresCss.getBolActivo());
		selectoresCssDTO.setFecModificacion(tbSiaSelectoresCss.getFecModificacion());
		selectoresCssDTO.setScrapNoPattern(tbSiaSelectoresCss.getScrapNoPattern());
		selectoresCssDTO.setScrapPattern(tbSiaSelectoresCss.getScrapPattern());
		selectoresCssDTO.setSelImage(tbSiaSelectoresCss.getSelImage());
		selectoresCssDTO.setSelLinkProd(tbSiaSelectoresCss.getSelLinkProd());
		selectoresCssDTO.setSelPrecio(tbSiaSelectoresCss.getSelPrecio());
		selectoresCssDTO.setSelPreKilo(tbSiaSelectoresCss.getSelPreKilo());
		selectoresCssDTO.setSelProducto(tbSiaSelectoresCss.getSelProducto());
		selectoresCssDTO.setTbSiaEmpresa(tbSiaSelectoresCss.getTbSiaEmpresa());
		selectoresCssDTO.setTbSiaUrl(tbSiaSelectoresCss.getTbSiaUrl());
		selectoresCssDTO.setSelPaginacion(tbSiaSelectoresCss.getSelPaginacion());
		
		return selectoresCssDTO;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param SelectoresCssDTO
	 * @return TbSiaSelectoresCss
	 */
	@Override
	public TbSiaSelectoresCss toTbSia(SelectoresCssDTO selectoresCssDTO) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaSelectoresCss tbSiaSelectoresCss = new TbSiaSelectoresCss();

		tbSiaSelectoresCss.setDid(selectoresCssDTO.getDid());
		tbSiaSelectoresCss.setBolActivo(selectoresCssDTO.getBolActivo());
		tbSiaSelectoresCss.setFecModificacion(selectoresCssDTO.getFecModificacion());
		tbSiaSelectoresCss.setScrapNoPattern(selectoresCssDTO.getScrapNoPattern());
		tbSiaSelectoresCss.setScrapPattern(selectoresCssDTO.getScrapPattern());
		tbSiaSelectoresCss.setSelImage(selectoresCssDTO.getSelImage());
		tbSiaSelectoresCss.setSelLinkProd(selectoresCssDTO.getSelLinkProd());
		tbSiaSelectoresCss.setSelPrecio(selectoresCssDTO.getSelPrecio());
		tbSiaSelectoresCss.setSelPreKilo(selectoresCssDTO.getSelPreKilo());
		tbSiaSelectoresCss.setSelProducto(selectoresCssDTO.getSelProducto());
		tbSiaSelectoresCss.setTbSiaEmpresa(selectoresCssDTO.getTbSiaEmpresa());
		tbSiaSelectoresCss.setTbSiaUrl(selectoresCssDTO.getTbSiaUrl());
		tbSiaSelectoresCss.setSelPaginacion(selectoresCssDTO.getSelPaginacion());
		
		return tbSiaSelectoresCss;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaSelectoresCss>
	 * @return List<SelectoresCssDTO>
	 */
	public List<SelectoresCssDTO> toListDTO(List<TbSiaSelectoresCss> lsTbSiaSelectoresCss) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<SelectoresCssDTO> listDto = new ArrayList<>(10); 
		SelectoresCssDTO selectoresCssDTO;
		
		for (TbSiaSelectoresCss tbSiaSelectoresCss : lsTbSiaSelectoresCss) {
			selectoresCssDTO = new SelectoresCssDTO();
			selectoresCssDTO.setDid(tbSiaSelectoresCss.getDid());
			selectoresCssDTO.setBolActivo(tbSiaSelectoresCss.getBolActivo());
			selectoresCssDTO.setFecModificacion(tbSiaSelectoresCss.getFecModificacion());
			selectoresCssDTO.setScrapNoPattern(tbSiaSelectoresCss.getScrapNoPattern());
			selectoresCssDTO.setScrapPattern(tbSiaSelectoresCss.getScrapPattern());
			selectoresCssDTO.setSelImage(tbSiaSelectoresCss.getSelImage());
			selectoresCssDTO.setSelLinkProd(tbSiaSelectoresCss.getSelLinkProd());
			selectoresCssDTO.setSelPrecio(tbSiaSelectoresCss.getSelPrecio());
			selectoresCssDTO.setSelPreKilo(tbSiaSelectoresCss.getSelPreKilo());
			selectoresCssDTO.setSelProducto(tbSiaSelectoresCss.getSelProducto());
			selectoresCssDTO.setTbSiaEmpresa(tbSiaSelectoresCss.getTbSiaEmpresa());
			selectoresCssDTO.setTbSiaUrl(tbSiaSelectoresCss.getTbSiaUrl());	
			selectoresCssDTO.setSelPaginacion(tbSiaSelectoresCss.getSelPaginacion());
			listDto.add(selectoresCssDTO);
		}
		return listDto;
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<SelectoresCssDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
}
