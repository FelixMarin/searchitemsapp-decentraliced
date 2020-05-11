package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class ParamsFormLoginParser implements IFParser<ParamsLoginDTO, TbSiaParamsFormLogin> {

	public ParamsFormLoginParser() {
		super();
	}

	public ParamsLoginDTO toDTO(TbSiaParamsFormLogin tbSiaParamsFormLogin) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		ParamsLoginDTO paramsLoginDto = new ParamsLoginDTO();
		
		paramsLoginDto.setDid(tbSiaParamsFormLogin.getDid());
		paramsLoginDto.setParamClave(tbSiaParamsFormLogin.getParamClave());
		paramsLoginDto.setParamValor(tbSiaParamsFormLogin.getParamValor());
		paramsLoginDto.setTbSiaUrl(tbSiaParamsFormLogin.getTbSiaUrl());
		
		return paramsLoginDto;
	}
	
	public TbSiaParamsFormLogin toTbSia(ParamsLoginDTO paramsLoginDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		TbSiaParamsFormLogin tbSiaParamsFormLogin = new TbSiaParamsFormLogin();
		
		tbSiaParamsFormLogin.setDid(paramsLoginDto.getDid());
		tbSiaParamsFormLogin.setParamClave(paramsLoginDto.getParamClave());
		tbSiaParamsFormLogin.setParamValor(paramsLoginDto.getParamValor());
		tbSiaParamsFormLogin.setTbSiaUrl(paramsLoginDto.getTbSiaUrl());
		
		return tbSiaParamsFormLogin;		
	}
	
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
	
	@Override
	public List<ParamsLoginDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),PaisParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
