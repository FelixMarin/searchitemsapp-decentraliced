package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.CategoriaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(categoriaDto)) {
			return (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(categoriaDto.toString());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
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
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
