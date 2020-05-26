package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;

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
	public ParamsLoginDTO toDTO(TbSiaParamsFormLogin tbSiaParamsFormLogin) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		ParamsLoginDTO paramsLoginDto = new ParamsLoginDTO();
		
		paramsLoginDto.setDid(tbSiaParamsFormLogin.getDid());
		paramsLoginDto.setParamClave(tbSiaParamsFormLogin.getParamClave());
		paramsLoginDto.setParamValor(tbSiaParamsFormLogin.getParamValor());
		paramsLoginDto.setTbSiaUrl(tbSiaParamsFormLogin.getTbSiaUrl());
		
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
		
		TbSiaParamsFormLogin tbSiaParamsFormLogin = new TbSiaParamsFormLogin();
		
		tbSiaParamsFormLogin.setDid(paramsLoginDto.getDid());
		tbSiaParamsFormLogin.setParamClave(paramsLoginDto.getParamClave());
		tbSiaParamsFormLogin.setParamValor(paramsLoginDto.getParamValor());
		tbSiaParamsFormLogin.setTbSiaUrl(paramsLoginDto.getTbSiaUrl());
		
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
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(10);
		ParamsLoginDTO paramsLoginDto;
		
		for (TbSiaParamsFormLogin tbSiaParamsFormLogin : listTbSiaParamsFormLogin) {
			paramsLoginDto = new ParamsLoginDTO();
			paramsLoginDto.setDid(tbSiaParamsFormLogin.getDid());
			paramsLoginDto.setParamClave(tbSiaParamsFormLogin.getParamClave());
			paramsLoginDto.setParamValor(tbSiaParamsFormLogin.getParamValor());
			paramsLoginDto.setTbSiaUrl(tbSiaParamsFormLogin.getTbSiaUrl());			
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
