package com.searchitemsapp.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.EmpresaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.repository.IFEmpresaRepository;

/**
 * Implementación del dao {@link EmpresaDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class EmpresaImpl implements IFImplementacion<EmpresaDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaImpl.class);  
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFEmpresaRepository empresaDao;
	
	/*
	 * Constructor
	 */
	public EmpresaImpl() {
		super();
	}
	
	/**
	 * Recupera todos los datos de tabla {@link EmpresaDao}.
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
			
			return new EmpresaDTO();
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"))
		.append(StringUtils.SPACE).append(empresaDto.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
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
			return new ArrayList<>();
		}
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return empresaDao.findByDidAndTbSiaCategoriasEmpresa(empresaDto.getDid(), categoriaDto.getDid());
	}	
}
