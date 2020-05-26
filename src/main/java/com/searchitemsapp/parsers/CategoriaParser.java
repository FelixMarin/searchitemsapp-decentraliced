package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class CategoriaParser implements IFParser<CategoriaDTO, TbSiaCategoriasEmpresa>  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaParser.class);  
	
	/*
	 *  Constructor
	 */
	public CategoriaParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaCategoriasEmpresa
	 * @return CategoriaDTO
	 */
	@Override
	public CategoriaDTO toDTO(TbSiaCategoriasEmpresa tbSiaPCategorias) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		CategoriaDTO categoriaPDto = new CategoriaDTO();
		
		categoriaPDto.setBolActivo(tbSiaPCategorias.getBolActivo());
		categoriaPDto.setDesCatEmpresa(tbSiaPCategorias.getDesCatEmpresa());
		categoriaPDto.setDid(tbSiaPCategorias.getDid());
		categoriaPDto.setNomCatEmpresa(tbSiaPCategorias.getNomCatEmpresa());
		categoriaPDto.setTbSiaEmpresas(tbSiaPCategorias.getTbSiaEmpresas());
		categoriaPDto.setTbSiaMarcas(tbSiaPCategorias.getTbSiaMarcas());
		categoriaPDto.setTbSiaNomProductos(tbSiaPCategorias.getTbSiaNomProductos());
		
		return categoriaPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param CategoriaDTO
	 * @return TbSiaCategoriasEmpresa
	 */
	@Override
	public TbSiaCategoriasEmpresa toTbSia(CategoriaDTO categoriaPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaCategoriasEmpresa tbSiaPCategorias = new TbSiaCategoriasEmpresa();
		
		tbSiaPCategorias.setBolActivo(categoriaPDto.getBolActivo());
		tbSiaPCategorias.setDesCatEmpresa(categoriaPDto.getDesCatEmpresa());
		tbSiaPCategorias.setDid(categoriaPDto.getDid());
		tbSiaPCategorias.setNomCatEmpresa(categoriaPDto.getNomCatEmpresa());
		tbSiaPCategorias.setTbSiaEmpresas(categoriaPDto.getTbSiaEmpresas());
		tbSiaPCategorias.setTbSiaMarcas(categoriaPDto.getTbSiaMarcas());
		tbSiaPCategorias.setTbSiaNomProductos(categoriaPDto.getTbSiaNomProductos());
		
		return tbSiaPCategorias;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaCategoriasEmpresa>
	 * @return List<CategoriaDTO>
	 */
	@Override
	public List<CategoriaDTO> toListDTO(List<TbSiaCategoriasEmpresa> lsCategorias) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<CategoriaDTO> listDto = new ArrayList<>(10); 
		CategoriaDTO categoriaPDto;
		
		for (TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa : lsCategorias) {
			categoriaPDto = new CategoriaDTO();
			categoriaPDto.setBolActivo(tbSiaCategoriasEmpresa.getBolActivo());
			categoriaPDto.setDesCatEmpresa(tbSiaCategoriasEmpresa.getDesCatEmpresa());
			categoriaPDto.setDid(tbSiaCategoriasEmpresa.getDid());
			categoriaPDto.setNomCatEmpresa(tbSiaCategoriasEmpresa.getNomCatEmpresa());
			categoriaPDto.setTbSiaEmpresas(tbSiaCategoriasEmpresa.getTbSiaEmpresas());
			categoriaPDto.setTbSiaMarcas(tbSiaCategoriasEmpresa.getTbSiaMarcas());
			categoriaPDto.setTbSiaNomProductos(tbSiaCategoriasEmpresa.getTbSiaNomProductos());			
			listDto.add(categoriaPDto);
		}
		
		return listDto;
	}

	/**
	 * Método no implementado.
	 */
	@Override
	public List<CategoriaDTO> toListODTO(List<Object[]> objeto) {
		return new ArrayList<>(10);
	}
}
