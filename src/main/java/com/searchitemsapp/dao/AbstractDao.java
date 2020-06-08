package com.searchitemsapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

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
public abstract class AbstractDao implements IFDao {
	
	/*
	 * Variables Globales
	 */
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	protected StringBuilder stringBuilder;

	/*
	 * Constructor
	 */
	public AbstractDao() {
		super();
	}
}
