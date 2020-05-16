package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class CategoriaParser implements IFParser<CategoriaDTO, TbSiaCategoriasEmpresa>  {
	
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),CategoriaParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),CategoriaParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),CategoriaParser.class);
		
		List<CategoriaDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
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
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
