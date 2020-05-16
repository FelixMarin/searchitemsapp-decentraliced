package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaNomProducto;
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
public class NomProductoParser implements IFParser<NomProductoDTO, TbSiaNomProducto> {

	/*
	 * Constructor
	 */
	public NomProductoParser() {
		super();
	}

	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaNomProducto
	 * @return NomProductoDTO
	 */
	public NomProductoDTO toDTO(TbSiaNomProducto tbSiaNomProducto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		NomProductoDTO nomProductoDTO = new NomProductoDTO();
		
		nomProductoDTO.setDid(tbSiaNomProducto.getDid());
		nomProductoDTO.setNomProducto(tbSiaNomProducto.getNomProducto());
		nomProductoDTO.setTbSiaCategoriasEmpresa(tbSiaNomProducto.getTbSiaCategoriasEmpresa());
		nomProductoDTO.setTbSiaPais(tbSiaNomProducto.getTbSiaPais());
		
		return nomProductoDTO;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param NomProductoDTO
	 * @return TbSiaNomProducto
	 */
	public TbSiaNomProducto toTbSia(NomProductoDTO nomProductoDTO) { 
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		TbSiaNomProducto tbSiaNomProducto = new TbSiaNomProducto();
		
		tbSiaNomProducto.setDid(nomProductoDTO.getDid());
		tbSiaNomProducto.setNomProducto(nomProductoDTO.getNomProducto());
		tbSiaNomProducto.setTbSiaCategoriasEmpresa(nomProductoDTO.getTbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(nomProductoDTO.getTbSiaPais());
		
		return tbSiaNomProducto;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaNomProducto>
	 * @return List<NomProductoDTO>
	 */
	public List<NomProductoDTO> toListDTO(List<TbSiaNomProducto> lsTbSiaNomProducto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		List<NomProductoDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
		NomProductoDTO nomProductoDTO;		
		
		for (TbSiaNomProducto tbSiaNomProducto : lsTbSiaNomProducto) {
			nomProductoDTO = new NomProductoDTO();
			nomProductoDTO.setDid(tbSiaNomProducto.getDid());
			nomProductoDTO.setNomProducto(tbSiaNomProducto.getNomProducto());
			nomProductoDTO.setTbSiaCategoriasEmpresa(tbSiaNomProducto.getTbSiaCategoriasEmpresa());
			nomProductoDTO.setTbSiaPais(tbSiaNomProducto.getTbSiaPais());
			listDto.add(nomProductoDTO);
		}
		
		return listDto;
		
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<NomProductoDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
