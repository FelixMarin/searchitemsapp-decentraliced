package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import com.searchitemsapp.dao.NomProductoDao;
import com.searchitemsapp.dto.NomProductoDTO;


/**
 * Implementación del dao {@link NomProductoDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class NomProductoImpl  implements IFImplementacion<NomProductoDTO, Object> {
	
	/*
	 * Constructor
	 */
	public NomProductoImpl() {
		super();
	}

	@Override
	public NomProductoDTO findByDid(NomProductoDTO objeto) throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<NomProductoDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<NomProductoDTO> findByTbSia(NomProductoDTO r, Object t) throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}

}
