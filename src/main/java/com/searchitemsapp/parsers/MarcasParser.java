package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.model.TbSiaMarcas;
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
public class MarcasParser implements IFParser<MarcasDTO, TbSiaMarcas> {

	/*
	 * Constructor
	 */
	public MarcasParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaMarcas
	 * @return MaracasDTO
	 */
	public MarcasDTO toDTO(TbSiaMarcas tbSiaMarcas) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		MarcasDTO marcasDto = new MarcasDTO();
		
		marcasDto.setDid(tbSiaMarcas.getDid());
		marcasDto.setNomMarca(tbSiaMarcas.getNomMarca());
		
		return marcasDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param MaracasDTO
	 * @return TbSiaMarcas
	 */
	public TbSiaMarcas toTbSia(MarcasDTO marcasDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		TbSiaMarcas tbSiaMarcas = new TbSiaMarcas();
		
		tbSiaMarcas.setDid(marcasDto.getDid());
		tbSiaMarcas.setNomMarca(marcasDto.getNomMarca());
		
		return tbSiaMarcas;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaMarcas>
	 * @return List<MaracasDTO>
	 */
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

	/**
	 * Método no implementado.
	 */
	@Override
	public List<MarcasDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),MarcasParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
