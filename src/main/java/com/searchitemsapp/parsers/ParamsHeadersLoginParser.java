package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsHeadersLogin;



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
	public ParamsLoginDTO toDTO(TbSiaParamsHeadersLogin tbSiaParamsFormLogin) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		ParamsLoginDTO paramsLoginDto = new ParamsLoginDTO();
		
		paramsLoginDto.setDid(tbSiaParamsFormLogin.getDid());
		paramsLoginDto.setParamClave(tbSiaParamsFormLogin.getParamClave());
		paramsLoginDto.setParamValor(tbSiaParamsFormLogin.getParamValor());
		paramsLoginDto.setTbSiaUrl(tbSiaParamsFormLogin.getTbSiaUrl());
		paramsLoginDto.setBolActivo(tbSiaParamsFormLogin.getBolActivo());
		
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
		
		TbSiaParamsHeadersLogin tbSiaParamsFormLogin = new TbSiaParamsHeadersLogin();
		
		tbSiaParamsFormLogin.setDid(paramsLoginDto.getDid());
		tbSiaParamsFormLogin.setParamClave(paramsLoginDto.getParamClave());
		tbSiaParamsFormLogin.setParamValor(paramsLoginDto.getParamValor());
		tbSiaParamsFormLogin.setTbSiaUrl(paramsLoginDto.getTbSiaUrl());
		tbSiaParamsFormLogin.setBolActivo(paramsLoginDto.getBolActivo());
		
		return tbSiaParamsFormLogin;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaParamsFormLogin>
	 * @return List<ParamsLoginDTO>
	 */
	@Override
	public List<ParamsLoginDTO> toListDTO(List<TbSiaParamsHeadersLogin> listTbSiaParamsFormLogin) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(10);
		ParamsLoginDTO paramsLoginDto;
		
		for (TbSiaParamsHeadersLogin tbSiaParamsFormLogin : listTbSiaParamsFormLogin) {
			paramsLoginDto = new ParamsLoginDTO();
			paramsLoginDto.setDid(tbSiaParamsFormLogin.getDid());
			paramsLoginDto.setParamClave(tbSiaParamsFormLogin.getParamClave());
			paramsLoginDto.setParamValor(tbSiaParamsFormLogin.getParamValor());
			paramsLoginDto.setTbSiaUrl(tbSiaParamsFormLogin.getTbSiaUrl());	
			paramsLoginDto.setBolActivo(tbSiaParamsFormLogin.getBolActivo());
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
		
		return new ArrayList<>(10);
	}
}
