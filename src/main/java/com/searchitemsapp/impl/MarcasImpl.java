package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.MarcasDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private MarcasDao marcasDao;

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());		
		
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

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(marcasDTO)) {
			return (MarcasDTO) ClaseUtils.NULL_OBJECT;
		}		
		
		/**
		 * Traza de log que escribe identificador de la empresa.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.marcas.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(marcasDTO.getDid());

		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
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
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
