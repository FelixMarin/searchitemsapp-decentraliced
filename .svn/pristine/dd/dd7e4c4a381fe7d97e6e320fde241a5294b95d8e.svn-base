package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsHeadersLogin;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class ParamsHeadersLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsHeadersLogin> {

	private ParamsHeadersLoginParser() {
		super();
	}
	
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

	@Override
	public List<ParamsLoginDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParamsHeadersLoginParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
