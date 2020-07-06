package com.searchitemsapp.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.EmpresaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(empresaDto) ||
				ClaseUtils.isNullObject(empresaDto.getDid())) {
			
			return (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDto.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si los parámetros de entrada son nulos, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(empresaDto) || ClaseUtils.isNullObject(categoriaDto)) {
			return new ArrayList();
		}
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return empresaDao.findByTbSiaCategoriasEmpresa(empresaDto.getDid(), categoriaDto.getDid());
	}	
}
