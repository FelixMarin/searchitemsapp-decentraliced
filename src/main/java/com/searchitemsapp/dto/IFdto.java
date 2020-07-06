package com.searchitemsapp.dto;

/**
 * Interfaz implementada por todos
 * las clases de tipo Data Transfer 
 * Object (DTO).
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFdto {
	
	@Override
	String toString();
	
	@Override
	int hashCode();
	
	@Override
	boolean equals(Object obj);
}
