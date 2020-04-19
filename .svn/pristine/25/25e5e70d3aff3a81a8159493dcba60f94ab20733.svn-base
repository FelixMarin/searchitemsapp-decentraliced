package com.searchitemsapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.util.LogsUtils;

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class AbstractDao<R,T> implements IFDao {

	public AbstractDao() {
		super();
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ParserFactory parserFactory;
	

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	protected ParserFactory getParserFactory() {
		return parserFactory;
	}

	public void isEntityManagerOpen(Class clazz) {
		LogsUtils.escribeLogDebug("entityManager is open: ".concat(String.valueOf(entityManager.isOpen())), clazz);
	}
	
	public IFParser<R,T> getParser(String strParser) {
		return ((IFParser<R,T>)getParserFactory().getParser(strParser));
	}
}
