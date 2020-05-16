package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.PaisDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Implementación del dao {@link PaisDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class PaisImpl implements IFImplementacion<PaisDTO, CategoriaDTO> {
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private PaisDao paisDao;
	
	/*
	 * Constructor
	 */
	public PaisImpl() {
		super();
	}
	
	/**
	 * Recupera un elemento de la tabla a partir
	 * de su identificador.
	 * 
	 * @param PaisDTO
	 * @return PaisDTO
	 * @exception IOException
	 */
	@Override
	public PaisDTO findByDid(PaisDTO paisDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(paisDto)) {
			return (PaisDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Traza de log que escribe identificador del pais.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.pais.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(paisDto.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Devuelve un objeto con el valor solicitado.
		 */
		return paisDao.findByDid(paisDto.getDid());
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public List<PaisDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

	/**
	 * Funcionalidad no implementada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public List<PaisDTO> findByTbSia(PaisDTO paisDTO, CategoriaDTO categoriaDTO) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
