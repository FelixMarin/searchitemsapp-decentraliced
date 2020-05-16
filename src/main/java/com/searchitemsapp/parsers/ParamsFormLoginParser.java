package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;
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
public class ParamsFormLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsFormLogin> {

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		List<ParamsLoginDTO> listParamsLoginDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
