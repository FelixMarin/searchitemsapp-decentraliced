package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsHeadersLogin;
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
public class ParamsHeadersLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsHeadersLogin> {

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParamsHeadersLoginParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParamsHeadersLoginParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParamsHeadersLoginParser.class);
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParamsHeadersLoginParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
