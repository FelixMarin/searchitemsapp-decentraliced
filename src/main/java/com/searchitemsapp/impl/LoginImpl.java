package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.LoginDao;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
@Aspect
public class LoginImpl implements IFImplementacion<LoginDTO, EmpresaDTO> {

	public LoginImpl() {
		super();
	}
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private TbSiaEmpresa tbSiaEmpresa;
	
	public List<LoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return loginDao.findAll();
	}	
	
	@Override
	public LoginDTO findByDid(LoginDTO loginDTO)  throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(loginDTO)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.login.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(loginDTO.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return loginDao.findByDid(loginDTO.getDid());
	}
	
	@Override
	public List<LoginDTO> findByTbSia(LoginDTO loginDTO, EmpresaDTO empresaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(loginDTO) || ClaseUtils.isNullObject(empresaDTO)) {
			return (List<LoginDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<LoginDTO> listLoginDTO;
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDTO.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		tbSiaEmpresa.setDid(empresaDTO.getDid());
		
		listLoginDTO = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		listLoginDTO.add(loginDao.findByTbSiaEmpresa(tbSiaEmpresa));
		
		return listLoginDTO;

		
	}
}
