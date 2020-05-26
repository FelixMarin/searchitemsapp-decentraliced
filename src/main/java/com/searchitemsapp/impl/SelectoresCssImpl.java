package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.SelectoresCssDao;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.parsers.IFParser;

/**
 * Implementación del dao {@link SelectoresCssDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
@Aspect
public class SelectoresCssImpl implements IFImplementacion<SelectoresCssDTO, EmpresaDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectoresCssImpl.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private SelectoresCssDao selectoresCssDao;
	
	@Autowired
	private ParserFactory parserFactory;
	
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
			return null;
		}
		
		/**
		 * Traza de log que escribe todos los valores del objeto selector css.
		 */
		final StringBuilder debugMessage = new StringBuilder(10);
		debugMessage.append(CommonsPorperties.getValue("flow.value.valor.dto"));
		debugMessage.append(" ");
		debugMessage.append(selectorCssDto.toString());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
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
			return null;
		}
		
		/**
		 * Mensaje que se pintará en las trazas de log.
		 */
		final StringBuilder debugMessage = new StringBuilder(10);
		debugMessage.append(CommonsPorperties.getValue("flow.value.activo"));
		debugMessage.append(" ");
		debugMessage.append(empresaDto.getDid());
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Se crea un objeto, se le asigna el identificador y se
		 * usa apara realizar la consulta a bbdd.
		 */
		TbSiaEmpresa tbSiaEmpresa = getParser().toTbSia(empresaDto);
		tbSiaEmpresa.setDid(empresaDto.getDid());
		
		/**
		 * Ejecuta la llamada al dao y devuelve el resultado.
		 */
		return selectoresCssDao.findByTbSiaEmpresa(tbSiaEmpresa);
	}
	
	/**
	 * Recupera el parser encargado de transformar 
	 * un objeto DTO a otro de tipo tabla.
	 * 
	 * @return IFParser<EmpresaDTO, TbSiaEmpresa>
	 */
	private IFParser<EmpresaDTO, TbSiaEmpresa> getParser() {
		return ((IFParser<EmpresaDTO, TbSiaEmpresa>) parserFactory.getParser(SELECTORES_PARSER));
	}
}
