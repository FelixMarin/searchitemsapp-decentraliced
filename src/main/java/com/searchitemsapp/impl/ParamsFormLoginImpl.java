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
import com.searchitemsapp.dao.repository.IFParamsFormLogin;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;


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
public class ParamsFormLoginImpl implements IFImplementacion<ParamsLoginDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsFormLoginImpl.class);  
	
	/*
	 * Variable Globales
	 */
	@Autowired
	private IFParamsFormLogin paramsFormLoginDao;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;

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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(paramsLoginDTO) || Objects.isNull(categoriaDTO)) {
			return new ArrayList<>(NumberUtils.INTEGER_ONE);
		}
		
		/**
		 * Traza de log que escribe identificador de la URL.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.url.did.txt"))
		.append(StringUtils.SPACE)
		.append(paramsLoginDTO.getDidUrl());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return paramsFormLoginDao.findByTbSiaUrl(paramsLoginDTO.getDidUrl());
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public ParamsLoginDTO findByDid(ParamsLoginDTO paramsLoginDTO) throws IOException {
		throw new UnsupportedOperationException(Thread
				.currentThread().getStackTrace()[1].toString());
	}
}
