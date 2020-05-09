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

/**
 * Implementación de la interfaz de acceso a datos  
 * y persistencia de entidades.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
@Aspect
public class LoginImpl implements IFImplementacion<LoginDTO, EmpresaDTO> {

	/*
	 * Variables Globales
	 */
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private TbSiaEmpresa tbSiaEmpresa;
	
	/*
	 * Constructor
	 */
	public LoginImpl() {
		super();
	}

	
	/**
	 * Recupera todos los elementos de la tabla,
	 * los agrega a una lista y son devueltos.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<LoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return loginDao.findAll();
	}	
	
	/**
	 * Recupera un elemento de la tabla a partir
	 * de su identificador.
	 * 
	 * @param LoginDTO
	 * @return LoginDTO
	 */
	@Override
	public LoginDTO findByDid(LoginDTO loginDTO)  throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(loginDTO)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.login.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(loginDTO.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return loginDao.findByDid(loginDTO.getDid());
	}
	
	/**
	 * Recupera un elemento de la tabla a partir
	 * de su identificador.
	 * 
	 * @param LoginDTO
	 * @return LoginDTO
	 */
	@Override
	public List<LoginDTO> findByTbSia(LoginDTO loginDTO, EmpresaDTO empresaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(loginDTO) || ClaseUtils.isNullObject(empresaDTO)) {
			return (List<LoginDTO>) ClaseUtils.NULL_OBJECT;
		}		
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDTO.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		tbSiaEmpresa.setDid(empresaDTO.getDid());
		
		List<LoginDTO> listLoginDTO = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		listLoginDTO.add(loginDao.findByTbSiaEmpresa(tbSiaEmpresa));
		
		return listLoginDTO;
	}
}
