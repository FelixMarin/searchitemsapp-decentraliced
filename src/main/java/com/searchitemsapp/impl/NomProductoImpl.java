package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.searchitemsapp.dao.repository.IFNomProductoRepository;
import com.searchitemsapp.dto.NomProductoDTO;


/**
 * Implementación del dao.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Component
public class NomProductoImpl  implements IFImplementacion<NomProductoDTO, Object> {
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFNomProductoRepository nomProductoDao;
	
	/*
	 * Constructor
	 */
	public NomProductoImpl() {
		super();
	}

	@Override
	public NomProductoDTO findByDid(NomProductoDTO objeto) throws IOException {
		return nomProductoDao.findByDid(objeto.getDid());
	}

	@Override
	public List<NomProductoDTO> findAll() throws IOException {
		return nomProductoDao.findAll();
	}

	@Override
	public List<NomProductoDTO> findByTbSia(NomProductoDTO r, Object t) throws IOException {
		return Lists.newArrayList();
	}

}
