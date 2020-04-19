package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class SelectoresCssParser implements IFParser<SelectoresCssDTO, TbSiaSelectoresCss> {

	private SelectoresCssParser() {
		super();
	}
	
	public SelectoresCssDTO toDTO(TbSiaSelectoresCss tbSiaSelectoresCss) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),SelectoresCssParser.class);
		
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
	
	public TbSiaSelectoresCss toTbSia(SelectoresCssDTO selectoresCssDTO) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),SelectoresCssParser.class);
		
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
	
	public List<SelectoresCssDTO> toListDTO(List<TbSiaSelectoresCss> lsTbSiaSelectoresCss) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),SelectoresCssParser.class);
		
		List<SelectoresCssDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
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
	
	@Override
	public List<SelectoresCssDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),SelectoresCssParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
