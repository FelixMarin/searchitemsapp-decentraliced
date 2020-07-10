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
import com.searchitemsapp.dao.repository.IFSelectoresCssRepository;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;

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
public class SelectoresCssImpl implements IFImplementacion<SelectoresCssDTO, EmpresaDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectoresCssImpl.class);  
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFSelectoresCssRepository selectoresCssDao;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;
	
	/*
	 * Controlador
	 */
	public SelectoresCssImpl() {
		super();
	}
	
	/**
	 * Recupera todos los elementos de la tabla,
	 * los agrega a una lista y son devueltos.
	 *  
	 * @return List<SelectoresCssDTO>
	 * @exception IOException
	 */
	@Override
	public List<SelectoresCssDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Ejeculta la llamada al dao y devuelve el resultado.
		 */
		return selectoresCssDao.findAll();
	}	
	
	/**
	 * Recupera un elemento de la tabla a partir
	 * de su identificador.
	 * 
	 * @param SelectoresCssDTO
	 * @return SelectoresCssDTO
	 * @exception IOException
	 */
	@Override
	public SelectoresCssDTO findByDid(SelectoresCssDTO selectorCssDto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(selectorCssDto)) {
			return new SelectoresCssDTO();
		}
		
		/**
		 * Traza de log que escribe todos los valores del objeto selector css.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.valor.dto"))
		.append(StringUtils.SPACE)
		.append(selectorCssDto.toString());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return selectoresCssDao.findByDid(selectorCssDto.getDid());
	}	
	
	/**
	 * Recupera una lista de objetos {@link SelectoresCssDTO} en formato entidad.
	 * 
	 * @param SelectoresCssDTO
	 * @param EmpresaDTO
	 * @return List<SelectoresCssDTO>
	 * @exception IOException
	 */
	@Override
	public List<SelectoresCssDTO> findByTbSia(SelectoresCssDTO selectoresCssDto, EmpresaDTO empresaDto) throws IOException {
			
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(empresaDto)) {
			return new ArrayList<>(NumberUtils.INTEGER_ONE);
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.activo"))
		.append(StringUtils.SPACE)
		.append(empresaDto.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return selectoresCssDao.findByTbSiaEmpresa(empresaDto.getDid());
	}
}
