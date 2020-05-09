package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz de acceso a datos y persistencia de entidades. 
 * 
 * @author Felix Marin Ramirez
 *
 * @param <R>
 * @param <T>
 */
public interface IFImplementacion<R,T> {
	
	public R findByDid(R r) throws IOException;
	public List<R> findAll() throws IOException;
	public List<R> findByTbSia(R r, T t) throws IOException;
	
}
