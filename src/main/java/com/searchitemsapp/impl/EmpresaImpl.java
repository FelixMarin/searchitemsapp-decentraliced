package com.searchitemsapp.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.EmpresaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;

/**
 * Implementación del dao {@link EmpresaDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Aspect
public class EmpresaImpl implements IFImplementacion<EmpresaDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaImpl.class);  
	/*
	 * Variables Globales
	 */
	@Autowired
	private EmpresaDao empresaDao;
	
	/*
	 * Constructor
	 */
	public EmpresaImpl() {
		super();
	}
	
	/**
	 * Recupera todos los datos de tabla {@link TbSiaEmpresa}.
	 * 
	 * @return List<EmpresaDTO>
	 * @exception  IOException
	 */
	@Override
	public List<EmpresaDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		/**
		 * Ejeculta la llamada al dao y devuelve el resultado.
		 */
		return empresaDao.findAll();
	}	
	
	/**
	 * Recupear una empresa a partir de su identificador.
	 * 
	 * @param EmpresaDTO
	 * @return EmpresaDTO
	 * @exception IOException
	 */
	@Override
	public EmpresaDTO findByDid(EmpresaDTO empresaDto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(empresaDto) ||
				Objects.isNull(empresaDto.getDid())) {
			
			return null;
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		final StringBuilder debugMessage = new StringBuilder(10);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(" ");
		debugMessage.append(empresaDto.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return empresaDao.findByDid(empresaDto.getDid());
	}
	
	/**
	 * Recupera una lista de objetos {@link EmpresaDTO} en formato entidad.
	 * 
	 * @param EmpresaDTO empresaDto
	 * @param CategoriaDTO categoriaDto
	 * @return List<EmpresaDTO>
	 * @exception IOException
	 */
	@Override
	public List<EmpresaDTO> findByTbSia(EmpresaDTO empresaDto, CategoriaDTO categoriaDto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(empresaDto) || Objects.isNull(categoriaDto)) {
			return new ArrayList();
		}
		
		TbSiaCategoriasEmpresa ce = new TbSiaCategoriasEmpresa();
		ce.setDid(categoriaDto.getDid());
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return empresaDao.findByDidAndTbSiaCategoriasEmpresa(empresaDto.getDid(), ce);
	}	
}
