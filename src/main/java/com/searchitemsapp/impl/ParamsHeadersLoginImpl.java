package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.ParamsHeadersLoginDao;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Implementación del dao {@link ParamsHeadersLoginDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
@Aspect
public class ParamsHeadersLoginImpl implements IFImplementacion<ParamsLoginDTO, EmpresaDTO> {

	/*
	 * Variable Globales
	 */
	@Autowired
	private ParamsHeadersLoginDao paramsHeadersLoginDao;
	
	/*
	 * Constructor
	 */
	public ParamsHeadersLoginImpl() {
		super();
	}
	
	/**
	 * Recupera todos los elementos de la tabla,
	 * los agrega a una lista y son devueltos.
	 * 
	 * @return List<ParamsLoginDTO>
	 * @exception IOException
	 */
	public List<ParamsLoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Ejeculta la llamada al dao y devuelve el resultado.
		 */
		return paramsHeadersLoginDao.findAll();
	}	
	
	/**
	 * Devuelve una lista de parametros por categoría.
	 * 
	 * @param CategoriaDTO categoriaDTO
	 * @param CategoriaDTO categoriaDTO
	 * @return List<ParamsLoginDTO> 
	 * @exception IOException
	 */
	public List<ParamsLoginDTO> findByTbSia(ParamsLoginDTO paramsLoginDTO, EmpresaDTO empresaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(paramsLoginDTO) || ClaseUtils.isNullObject(empresaDTO)) {
			return (List<ParamsLoginDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.url.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(paramsLoginDTO.getTbSiaUrl().getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());	
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return paramsHeadersLoginDao.findByTbSiaUrl(paramsLoginDTO.getTbSiaUrl());
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public ParamsLoginDTO findByDid(ParamsLoginDTO objeto) throws IOException {
		return null;
	}	
}
