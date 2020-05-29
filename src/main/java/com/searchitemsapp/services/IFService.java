package com.searchitemsapp.services;

import org.springframework.stereotype.Service;

/**
 * Representa de forma abstracta el objeto de tipo service. 
 * Mediante esta interface se definen la estructura que tendrán
 * dichos objetos. 
 * 
 * @author Felix Marin Ramirez
 *
 * @param <R>
 * @param <T>
 */
@SuppressWarnings("unchecked")
@FunctionalInterface
@Service
public interface IFService<R, T> {
	
	/*
	 * Constantes Globales
	 */
	public final String  NO_HAY_RESULTADOS= "No hay resultados";
	
	/*
	 * Métodos implementables
	 */
	public R service(final T... str);
}