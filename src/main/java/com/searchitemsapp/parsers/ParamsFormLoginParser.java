package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsFormLogin;
import com.searchitemsapp.entities.TbSiaUrl;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ParamsFormLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsFormLogin> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsFormLoginParser.class);  
	
	@Autowired
	ParamsLoginDTO paramsLoginDto;
	
	@Autowired
	TbSiaParamsFormLogin tbSiaParamsFormLogin;
	
	/*
	 * Constructor
	 */
	public ParamsFormLoginParser() {
		super();
	}

	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaParamsFormLogin
	 * @return ParamsLoginDTO
	 */
	public ParamsLoginDTO toDTO(TbSiaParamsFormLogin tbSiaParamsFormLog) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		paramsLoginDto.setDid(tbSiaParamsFormLog.getDid());
		paramsLoginDto.setParamClave(tbSiaParamsFormLog.getParamClave());
		paramsLoginDto.setParamValor(tbSiaParamsFormLog.getParamValor());
		paramsLoginDto.setDidUrl(tbSiaParamsFormLog.getTbSiaUrl().getDid());
		paramsLoginDto.setNomUrl(tbSiaParamsFormLog.getTbSiaUrl().getNomUrl());
		
		return paramsLoginDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param ParamsLoginDTO
	 * @return TbSiaParamsFormLogin
	 */
	public TbSiaParamsFormLogin toTbSia(ParamsLoginDTO paramsLoginDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		tbSiaParamsFormLogin.setDid(paramsLoginDto.getDid());
		tbSiaParamsFormLogin.setParamClave(paramsLoginDto.getParamClave());
		tbSiaParamsFormLogin.setParamValor(paramsLoginDto.getParamValor());
		tbSiaParamsFormLogin.setTbSiaUrl(new TbSiaUrl());
		tbSiaParamsFormLogin.getTbSiaUrl().setDid(paramsLoginDto.getDidUrl());
		tbSiaParamsFormLogin.getTbSiaUrl().setNomUrl(paramsLoginDto.getNomUrl());
		
		return tbSiaParamsFormLogin;		
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaParamsFormLogin>
	 * @return List<ParamsLoginDTO>
	 */
	public List<ParamsLoginDTO> toListDTO(List<TbSiaParamsFormLogin> listTbSiaParamsFormLogin) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		for (TbSiaParamsFormLogin tbSiaParamsFormLog : listTbSiaParamsFormLogin) {
			paramsLoginDto = new ParamsLoginDTO();
			paramsLoginDto.setDid(tbSiaParamsFormLog.getDid());
			paramsLoginDto.setParamClave(tbSiaParamsFormLog.getParamClave());
			paramsLoginDto.setParamValor(tbSiaParamsFormLog.getParamValor());
			boolean isNull = null == tbSiaParamsFormLog.getTbSiaUrl().getDid();
			paramsLoginDto.setDidUrl(isNull?101:tbSiaParamsFormLog.getTbSiaUrl().getDid());
			paramsLoginDto.setNomUrl(tbSiaParamsFormLog.getTbSiaUrl().getNomUrl());	
			listParamsLoginDto.add(paramsLoginDto);
		}
		
		return listParamsLoginDto;
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<ParamsLoginDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(NumberUtils.INTEGER_ONE);
	}
}
