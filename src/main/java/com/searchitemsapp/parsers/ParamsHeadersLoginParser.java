package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsHeadersLogin;
import com.searchitemsapp.entities.TbSiaUrl;



/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ParamsHeadersLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsHeadersLogin> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsHeadersLoginParser.class);  
	
	@Autowired
	ParamsLoginDTO paramsLoginDto;
	
	@Autowired
	TbSiaParamsHeadersLogin tbSiaParamsHeadersLogin;
	
	/*
	 * Constructor
	 */
	public ParamsHeadersLoginParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaParamsHeadersLogin
	 * @return ParamsLoginDTO
	 */
	public ParamsLoginDTO toDTO(TbSiaParamsHeadersLogin tbSiaParamsHeadersLog) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		paramsLoginDto.setDid(tbSiaParamsHeadersLog.getDid());
		paramsLoginDto.setParamClave(tbSiaParamsHeadersLog.getParamClave());
		paramsLoginDto.setParamValor(tbSiaParamsHeadersLog.getParamValor());
		paramsLoginDto.setDidUrl(tbSiaParamsHeadersLog.getTbSiaUrl().getDid());
		paramsLoginDto.setNomUrl(tbSiaParamsHeadersLog.getTbSiaUrl().getNomUrl());
		paramsLoginDto.setBolActivo(tbSiaParamsHeadersLog.getBolActivo());
		
		return paramsLoginDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param ParamsLoginDTO
	 * @return TbSiaParamsHeadersLogin
	 */
	@Override
	public TbSiaParamsHeadersLogin toTbSia(ParamsLoginDTO paramsLoginDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		tbSiaParamsHeadersLogin.setDid(paramsLoginDto.getDid());
		tbSiaParamsHeadersLogin.setParamClave(paramsLoginDto.getParamClave());
		tbSiaParamsHeadersLogin.setParamValor(paramsLoginDto.getParamValor());
		tbSiaParamsHeadersLogin.setTbSiaUrl(new TbSiaUrl());
		tbSiaParamsHeadersLogin.getTbSiaUrl().setDid(paramsLoginDto.getDidUrl());
		tbSiaParamsHeadersLogin.getTbSiaUrl().setNomUrl(paramsLoginDto.getNomUrl());
		tbSiaParamsHeadersLogin.setBolActivo(paramsLoginDto.getBolActivo());
		
		return tbSiaParamsHeadersLogin;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaParamsHeadersLogin>
	 * @return List<ParamsLoginDTO>
	 */
	@Override
	public List<ParamsLoginDTO> toListDTO(List<TbSiaParamsHeadersLogin> listTbSiaParamsHeadersLogin) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
		for (TbSiaParamsHeadersLogin tbSiaParamsHeadersLog : listTbSiaParamsHeadersLogin) {
			paramsLoginDto = new ParamsLoginDTO();
			paramsLoginDto.setDid(tbSiaParamsHeadersLog.getDid());
			paramsLoginDto.setParamClave(tbSiaParamsHeadersLog.getParamClave());
			paramsLoginDto.setParamValor(tbSiaParamsHeadersLog.getParamValor());
			paramsLoginDto.setDidUrl(tbSiaParamsHeadersLog.getTbSiaUrl().getDid());
			paramsLoginDto.setNomUrl(tbSiaParamsHeadersLog.getTbSiaUrl().getNomUrl());	
			paramsLoginDto.setBolActivo(tbSiaParamsHeadersLog.getBolActivo());
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
