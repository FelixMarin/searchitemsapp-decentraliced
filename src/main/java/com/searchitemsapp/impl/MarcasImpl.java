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
import com.searchitemsapp.dao.MarcasDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.repository.IFMarcasRepository;


/**
 * Implementación del dao {@link MarcasDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class MarcasImpl implements IFImplementacion<MarcasDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MarcasImpl.class);  
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFMarcasRepository marcasDao;

	/*
	 * Constructor
	 */
	public MarcasImpl() {
		super();
	}
	
	/**
	 * Recupera todos los elementos de la tabla,
	 * los agrega a una lista y son devueltos.
	 * 
	 * @return List<MarcasDTO>
	 * @exception IOException
	 */

	public List<MarcasDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Ejeculta la llamada al dao y devuelve el resultado.
		 */
		return marcasDao.findAll();
	}
	
	/**
	 * Recupera un elemento de la tabla a partir
	 * de su identificador.
	 * 
	 * @param MarcasDTO
	 * @return MarcasDTO
	 * @exception IOException
	 */
	@Override
	public MarcasDTO findByDid(MarcasDTO marcasDTO) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(marcasDTO)) {
			return null;
		}		
		
		/**
		 * Traza de log que escribe identificador de la marca.
		 */
		final StringBuilder debugMessage = new StringBuilder(NumberUtils.INTEGER_ONE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.marcas.dto.txt"));
		debugMessage.append(StringUtils.SPACE);
		debugMessage.append(marcasDTO.getDid());

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return marcasDao.findByDid(marcasDTO.getDid());		
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public List<MarcasDTO> findByTbSia(MarcasDTO r, CategoriaDTO t) throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}
}
