package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.CategoriaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;

/**
 * Implementación del dao {@link CategoriaDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class CategoriaImpl implements IFImplementacion<CategoriaDTO, EmpresaDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaImpl.class);  
	
	/*
	 * Variables Globales. 
	 */
	@Autowired
	private CategoriaDao categoriaDao;
	
	/*
	 * Constructor
	 */
	public CategoriaImpl() {
		super();
	}
	
	/**
	 * Recupera todos los datos de tabla {@link TbSiaCategoriasEmpresa}.
	 * 
	 * @return List<CategoriaDTO>
	 * @exception IOException
	 */
	@Override
	public List<CategoriaDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return categoriaDao.findAll();
	}
	
	/**
	 * Recupear una categoría a partir de su identificador.
	 * 
	 * @param CategoriaDTO
	 * @return CategoriaDTO
	 * @exception IOException
	 */
	@Override
	public CategoriaDTO findByDid(CategoriaDTO categoriaDto)  throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(categoriaDto)) {
			return null;
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		final StringBuilder debugMessage = new StringBuilder(NumberUtils.INTEGER_ONE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.dto.txt"));
		debugMessage.append(StringUtils.SPACE);
		debugMessage.append(categoriaDto.toString());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Realiza la consulta y devuelve el resultado.
		 */
		return categoriaDao.findByDid(categoriaDto.getDid());
	}
	
	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException 
	 */
	@Override
	public List<CategoriaDTO> findByTbSia(CategoriaDTO categoriaDTO, EmpresaDTO empresaDTO) throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}
}
