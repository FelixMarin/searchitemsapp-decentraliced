package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class CategoriaParser implements IFParser<CategoriaDTO, TbSiaCategoriasEmpresa>  {
	
	public CategoriaParser() {
		super();
	}
	
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

	@Override
	public List<CategoriaDTO> toListODTO(List<Object[]> objeto) {
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
