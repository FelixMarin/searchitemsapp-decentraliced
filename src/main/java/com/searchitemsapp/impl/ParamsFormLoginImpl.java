package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.ParamsFormLoginDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Implementación del dao {@link ParamsFormLoginDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
@Aspect
public class ParamsFormLoginImpl implements IFImplementacion<ParamsLoginDTO, CategoriaDTO> {
	
	/*
	 * Variable Globales
	 */
	@Autowired
	private ParamsFormLoginDao paramsFormLoginDao;
	
	/*
	 * Constructor
	 */
	public ParamsFormLoginImpl() {
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
		return paramsFormLoginDao.findAll();
	}
	
	/**
	 * Devuelve una lista de parametros por categoría.
	 * 
	 * @param CategoriaDTO categoriaDTO
	 * @param CategoriaDTO categoriaDTO
	 * @return List<ParamsLoginDTO> 
	 * @exception IOException
	 */
	public List<ParamsLoginDTO> findByTbSia(ParamsLoginDTO paramsLoginDTO, 
			CategoriaDTO categoriaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(paramsLoginDTO) || ClaseUtils.isNullObject(categoriaDTO)) {
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
		return paramsFormLoginDao.findByTbSiaUrl(paramsLoginDTO.getTbSiaUrl());
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public ParamsLoginDTO findByDid(ParamsLoginDTO paramsLoginDTO) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
