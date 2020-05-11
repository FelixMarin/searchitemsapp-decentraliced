package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class NomProductoParser implements IFParser<NomProductoDTO, TbSiaNomProducto> {

	public NomProductoParser() {
		super();
	}

	public NomProductoDTO toDTO(TbSiaNomProducto tbSiaNomProducto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		NomProductoDTO nomProductoDTO = new NomProductoDTO();
		
		nomProductoDTO.setDid(tbSiaNomProducto.getDid());
		nomProductoDTO.setNomProducto(tbSiaNomProducto.getNomProducto());
		nomProductoDTO.setTbSiaCategoriasEmpresa(tbSiaNomProducto.getTbSiaCategoriasEmpresa());
		nomProductoDTO.setTbSiaPais(tbSiaNomProducto.getTbSiaPais());
		
		return nomProductoDTO;
	}
	
	public TbSiaNomProducto toTbSia(NomProductoDTO nomProductoDTO) { 
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		TbSiaNomProducto tbSiaNomProducto = new TbSiaNomProducto();
		
		tbSiaNomProducto.setDid(nomProductoDTO.getDid());
		tbSiaNomProducto.setNomProducto(nomProductoDTO.getNomProducto());
		tbSiaNomProducto.setTbSiaCategoriasEmpresa(nomProductoDTO.getTbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(nomProductoDTO.getTbSiaPais());
		
		return tbSiaNomProducto;
	}
	
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
	
	@Override
	public List<NomProductoDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),NomProductoParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
