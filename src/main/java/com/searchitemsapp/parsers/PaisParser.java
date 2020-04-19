package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class PaisParser implements IFParser<PaisDTO, TbSiaPais> {
	
	private PaisParser() {
		super();
	}
	
	public PaisDTO toDTO(TbSiaPais tbSiaPPais) {	
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		PaisDTO paisPDto = new PaisDTO();
		
		paisPDto.setBolActivo(tbSiaPPais.getBolActivo());
		paisPDto.setDesPais(tbSiaPPais.getDesPais());
		paisPDto.setDid(tbSiaPPais.getDid());
		paisPDto.setNomPais(tbSiaPPais.getNomPais());
		paisPDto.setTbSiaEmpresas(tbSiaPPais.getTbSiaEmpresas());
		paisPDto.setTbSiaMarcas(tbSiaPPais.getTbSiaMarcas());
		paisPDto.setTbSiaNomProductos(tbSiaPPais.getTbSiaNomProductos());
		
		return paisPDto;
	}
	
	public TbSiaPais toTbSia(PaisDTO paisPDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		TbSiaPais tbSiaPPais = new TbSiaPais();
		
		tbSiaPPais.setBolActivo(paisPDto.getBolActivo());
		tbSiaPPais.setDesPais(paisPDto.getDesPais());
		tbSiaPPais.setDid(paisPDto.getDid());
		tbSiaPPais.setNomPais(paisPDto.getNomPais());
		tbSiaPPais.setTbSiaEmpresas(paisPDto.getTbSiaEmpresas());
		tbSiaPPais.setTbSiaMarcas(paisPDto.getTbSiaMarcas());
		tbSiaPPais.setTbSiaNomProductos(paisPDto.getTbSiaNomProductos());
		
		return tbSiaPPais;
	}

	@Override
	public List<PaisDTO> toListDTO(List<TbSiaPais> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
	
	@Override
	public List<PaisDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
