package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.searchitemsapp.config.IFCommonsProperties;
import com.searchitemsapp.dao.repository.IFUrlRepository;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.sun.istack.NotNull;


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
public class UrlImpl implements IFUrlImpl, IFImplementacion<UrlDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlImpl.class);  
	
	/*
	 * Constante Globales
	 */
	private static final String COMA = ",";
	private static final String ALL = "ALL";

	/*
	 * Variables Globales
	 */
	@Autowired
	private IFUrlRepository urlDao;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;
	
	/*
	 * Constructor
	 */
	public UrlImpl() {
		super();
	}

	/**
	 * Recupera un elemento de la tabla a partir de su identificador.
	 * 
	 * @param UrlDTO
	 * @return UrlDTO
	 * @exception IOException
	 */
	@Override
	public UrlDTO findByDid(UrlDTO urlDTO) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		if(Objects.isNull(urlDTO)) {
			return new UrlDTO();
		}
		
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.empresa.did.txt"))
		.append(StringUtils.SPACE)
		.append(urlDTO.getDid());
		
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(stringBuilder.toString(),this.getClass());
			}
			
		return urlDao.findByDid(urlDTO.getDid());
	}
	
	public List<UrlDTO> obtenerUrls(PaisDTO paisDto, CategoriaDTO categoriaDto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return urlDao.findByDidAndDesUrl(paisDto.getDid(), String.valueOf(categoriaDto.getDid()));
	}
	
	public List<UrlDTO> obtenerUrlsLogin(PaisDTO paisDto, CategoriaDTO categoriaDto) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return urlDao.findByDidAndNomUrl(paisDto.getDid(), String.valueOf(categoriaDto.getDid()));
	}
	
	public List<UrlDTO> obtenerUrlsPorIdEmpresa(@NotNull final PaisDTO paisDto, 
			@NotNull final CategoriaDTO categoriaDto,
			@NotNull final String idsEmpresas) 
			throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		String strIdsEmpresas;
		
		if(ALL.equalsIgnoreCase(idsEmpresas)) {
			strIdsEmpresas = iFCommonsProperties.getValue("flow.value.all.id.empresa");
		} else {
			strIdsEmpresas = idsEmpresas;
		}
		
		String[] arIdsEpresas = tokenizeString(strIdsEmpresas, COMA);
		List<UrlDTO> lsIdsEmpresas = Lists.newArrayList();
				
		List<UrlDTO> listUrlDTO = urlDao.findByDidAndDesUrl(paisDto.getDid(), String.valueOf(categoriaDto.getDid()));
		
		if(Objects.isNull(listUrlDTO)) {
			return lsIdsEmpresas;
		}
				
		for (String id : arIdsEpresas) {
			listUrlDTO.forEach(urlDTO -> {
				if(Integer.parseInt(id) == urlDTO.getDidEmpresa()) {
					lsIdsEmpresas.add(urlDTO);
				}
			});
		}
		
		return lsIdsEmpresas;
	}

	@Override
	public List<UrlDTO> findAll() throws IOException {
		return Lists.newArrayList();
	}

	@Override
	public List<UrlDTO> findByTbSia(UrlDTO r, CategoriaDTO t) throws IOException {
		return Lists.newArrayList();
	}
	
	/*
	 * Métodos privados
	 */
	
	private String[] tokenizeString(final String cadena, final String token) {
		
		StringTokenizer st = new StringTokenizer(cadena, token); 		
		List<String> listaAux = Lists.newArrayList();
		
		while (st.hasMoreElements()) {
			listaAux.add((String) st.nextElement());
			
		}
		
		return listaAux.toArray(new String[0]);
	}
}
