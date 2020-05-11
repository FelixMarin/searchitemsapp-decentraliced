package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class MarcasParser implements IFParser<MarcasDTO, TbSiaMarcas> {

	public MarcasParser() {
		super();
	}
	
	public MarcasDTO toDTO(TbSiaMarcas tbSiaMarcas) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		MarcasDTO marcasDto = new MarcasDTO();
		
		marcasDto.setDid(tbSiaMarcas.getDid());
		marcasDto.setNomMarca(tbSiaMarcas.getNomMarca());
		
		return marcasDto;
	}
	
	public TbSiaMarcas toTbSia(MarcasDTO marcasDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		TbSiaMarcas tbSiaMarcas = new TbSiaMarcas();
		
		tbSiaMarcas.setDid(marcasDto.getDid());
		tbSiaMarcas.setNomMarca(marcasDto.getNomMarca());
		
		return tbSiaMarcas;
	}
	
	public List<MarcasDTO> toListDTO(List<TbSiaMarcas> lsTbSiaMarcas) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		List<MarcasDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
		MarcasDTO marcasDto;
		
		for (TbSiaMarcas tbSiaMarcas : lsTbSiaMarcas) {
			marcasDto = new MarcasDTO();
			marcasDto.setDid(tbSiaMarcas.getDid());
			marcasDto.setNomMarca(tbSiaMarcas.getNomMarca());
			listDto.add(marcasDto);
		}
		
		return listDto;
	}

	@Override
	public List<MarcasDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
