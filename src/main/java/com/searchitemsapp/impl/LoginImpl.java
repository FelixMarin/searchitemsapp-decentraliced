package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.IFCommonsProperties;
import com.searchitemsapp.dao.repository.IFLoginRepository;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.LoginDTO;

/**
 * Implementación del dao.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class LoginImpl implements IFImplementacion<LoginDTO, EmpresaDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginImpl.class);  
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFLoginRepository loginDao;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;
	
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
	 * @exception IOException
	 */
	@Override
	public List<LoginDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Ejeculta la llamada al dao y devuelve el resultado.
		 */
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(loginDTO)) {
			return new LoginDTO();
		}
		
		/**
		 * Traza de log que escribe identificador del login.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.login.dto.txt"))
		.append(StringUtils.SPACE).append(loginDTO.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return loginDao.findByDid(loginDTO.getDid());
	}
	
	/**
	 * Devuelve una lista de Logins a partir de un objeto login y una empresa.
	 * 
	 * @param LoginDTO
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<LoginDTO> findByTbSia(LoginDTO loginDTO, EmpresaDTO empresaDTO) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(loginDTO) || Objects.isNull(empresaDTO)) {
			return new ArrayList<>(NumberUtils.INTEGER_ONE);
		}		
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.empresa.did.txt"))
		.append(StringUtils.SPACE)
		.append(empresaDTO.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
		
		/**
		 * Se ejecuta la consulta y el resultado 
		 * se asigna a la lista que será retornada.
		 */
		List<LoginDTO> listLoginDTO = new ArrayList<>(NumberUtils.INTEGER_ONE);
		listLoginDTO.add(loginDao.findByTbSiaEmpresa(empresaDTO.getDid()));
		
		return listLoginDTO;
	}
}
