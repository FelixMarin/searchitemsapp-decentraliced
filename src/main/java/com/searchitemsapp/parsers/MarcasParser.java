package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.entities.TbSiaMarcas;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class MarcasParser implements IFParser<MarcasDTO, TbSiaMarcas> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarcasParser.class);  
	
	@Autowired
	MarcasDTO marcasDto;
	
	@Autowired
	TbSiaMarcas tbSiaMarcas;
	
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		marcasDto.setDid(tbSiaMarcas.getDid());
		marcasDto.setNomMarca(tbSiaMarcas.getNomMarca());
		boolean isNull = null == tbSiaMarcas.getTbSiaCategoriasEmpresa().getDid();
		marcasDto.setDidCatEmpresas(isNull?101:tbSiaMarcas.getTbSiaCategoriasEmpresa().getDid());
		marcasDto.setNomCatEmpresas(tbSiaMarcas.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
		isNull = null == tbSiaMarcas.getTbSiaPais().getDid();
		marcasDto.setDidPais(isNull?101:tbSiaMarcas.getTbSiaPais().getDid());
		marcasDto.setNomPais(tbSiaMarcas.getTbSiaPais().getNomPais());
		
		return marcasDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param MaracasDTO
	 * @return TbSiaMarcas
	 */
	public TbSiaMarcas toTbSia(MarcasDTO marcasDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<MarcasDTO> listDto = new ArrayList<>(NumberUtils.INTEGER_ONE); 
		
		for (TbSiaMarcas tbSiaMarc : lsTbSiaMarcas) {
			marcasDto = new MarcasDTO();
			marcasDto.setDid(tbSiaMarc.getDid());
			marcasDto.setNomMarca(tbSiaMarc.getNomMarca());
			boolean isNull =  null == tbSiaMarc.getTbSiaCategoriasEmpresa().getDid();
			marcasDto.setDidCatEmpresas(isNull?101:tbSiaMarc.getTbSiaCategoriasEmpresa().getDid());
			marcasDto.setNomCatEmpresas(tbSiaMarc.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
			isNull = null == tbSiaMarc.getTbSiaPais().getDid();
			marcasDto.setDidPais(isNull?101:tbSiaMarc.getTbSiaPais().getDid());
			marcasDto.setNomPais(tbSiaMarc.getTbSiaPais().getNomPais());
			
			listDto.add(marcasDto);
		}
		
		return listDto;
	}

	/**
	 * Método no implementado.
	 */
	@Override
	public List<MarcasDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(NumberUtils.INTEGER_ONE);
	}
}
