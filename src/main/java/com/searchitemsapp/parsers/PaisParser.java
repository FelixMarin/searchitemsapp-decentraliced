package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaPais;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class PaisParser implements IFParser<PaisDTO, TbSiaPais> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaisParser.class);  
	
	/*
	 * Constructor
	 */
	public PaisParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaPais
	 * @return PaisDTO
	 */
	public PaisDTO toDTO(TbSiaPais tbSiaPPais) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param PaisDTO
	 * @return TbSiaPais
	 */
	public TbSiaPais toTbSia(PaisDTO paisPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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

	/**
	 * Método no implementado.
	 */
	@Override
	public List<PaisDTO> toListDTO(List<TbSiaPais> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<PaisDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
}
