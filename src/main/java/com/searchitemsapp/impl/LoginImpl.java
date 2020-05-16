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
 * Implementación del dao {@link LoginDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
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
	 * @exception IOException
	 */
	@Override
	public List<LoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(loginDTO)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Traza de log que escribe identificador del login.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
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
	 * Devuelve una lista de Logins a partir de un objeto login y una empresa.
	 * 
	 * @param LoginDTO
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<LoginDTO> findByTbSia(LoginDTO loginDTO, EmpresaDTO empresaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(loginDTO) || ClaseUtils.isNullObject(empresaDTO)) {
			return (List<LoginDTO>) ClaseUtils.NULL_OBJECT;
		}		
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDTO.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Se establce el id en el objeto.
		 */
		tbSiaEmpresa.setDid(empresaDTO.getDid());
		
		/**
		 * Se ejecuta la consulta y el resultado 
		 * se asigna a la lista que será retornada.
		 */
		List<LoginDTO> listLoginDTO = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		listLoginDTO.add(loginDao.findByTbSiaEmpresa(tbSiaEmpresa));
		
		return listLoginDTO;
	}
}
