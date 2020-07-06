package com.searchitemsapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.parsers.IFParser;

/**
 * Clase Abstracta que implementan todas las clases
 * DAO. En esta clase es donde se instancia al 
 * Entity Manager usado para interactuar con el 
 * contexto de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 * @param <R>
 * @param <T>
 */
@SuppressWarnings({"rawtypes","unchecked"})
public abstract class AbstractDao<R,T> implements IFDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);     

	/*
	 * Variables Globales
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ParserFactory parserFactory;
	
	/*
	 * Constructor
	 */
	public AbstractDao() {
		super();
	}
	

	/**
	 * Devuelve el Entity Manager configurado.
	 *  
	 * @return EntityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Devuelve un objeto de tipo ParserFactory.
	 * 
	 * @return ParserFactory
	 */
	protected ParserFactory getParserFactory() {
		return parserFactory;
	}

	/**
	 * Traza un log indicando si el Entity Manager 
	 * está abierto.
	 * 
	 * @param clazz
	 */
	public void isEntityManagerOpen(Class clazz) {
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("entityManager is open: ".concat(String.valueOf(entityManager.isOpen())), clazz);
		}
	}
	
	/**
	 * Metodo que convierte una clase DAO en una DTO del mimo tipo
	 * Ejemplo: MarcasDao <=> MarcasDTO
	 * 
	 * @param  String
	 * @return IFParser<R,T>
	 */
	public IFParser<R,T> getParser(String strParser) {
		return ((IFParser<R,T>)getParserFactory().getParser(strParser));
	}
}
