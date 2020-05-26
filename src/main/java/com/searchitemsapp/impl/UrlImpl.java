package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.UrlDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.UrlDTO;


/**
 * Implementación del dao {@link UrlDao}.
 * 
 * Esta clase ofrece los métodos que permiten interactuar con
 * la capa de persistencia. 
 * 
 * @author Felix Marin Ramirez
 *
 */
@Aspect
public class UrlImpl implements IFImplementacion<UrlDTO, CategoriaDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlImpl.class);  
	
	/*
	 * Constantes Globales 
	 */
	private static final String ALL = "ALL";

	/*
	 * VAriables Globales
	 */
	@Autowired
	private UrlDao urlDao;
	
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
			return null;
		}
		
		final StringBuilder debugMessage = new StringBuilder(10);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(" ");
		debugMessage.append(urlDTO.getDid());
		
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(debugMessage.toString(),this.getClass());
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
	
	public List<UrlDTO> obtenerUrlsPorIdEmpresa(PaisDTO paisDto, 
			CategoriaDTO categoriaDto,
			String strIdsEmpresas) 
			throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		if(Objects.isNull(paisDto) ||
				Objects.isNull(categoriaDto) ||
				"".contentEquals(strIdsEmpresas)) {
			return null;
		}
		
		if(ALL.equalsIgnoreCase(strIdsEmpresas)) {
			strIdsEmpresas = CommonsPorperties.getValue("flow.value.all.id.empresa");
		}
		
		String[] arIdsEpresas = tokenizeString(strIdsEmpresas, ",");
		List<UrlDTO> lsIdsEmpresas = new ArrayList<>(10);
				
		List<UrlDTO> listUrlDTO = urlDao.findAll();
		
		for (String id : arIdsEpresas) {
			for (UrlDTO urlDTO : listUrlDTO) {
				if(Integer.parseInt(id) == urlDTO.getTbSiaEmpresa().getDid()) {
					lsIdsEmpresas.add(urlDTO);
				}
			}
		}
		
		return lsIdsEmpresas;
	}

	@Override
	public List<UrlDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<UrlDTO> findByTbSia(UrlDTO r, CategoriaDTO t) throws IOException {
		throw new UnsupportedOperationException(OPERACION_NO_SOPORTADA);
	}
	
	/*
	 * Métodos privados
	 */
	
	private String[] tokenizeString(final String cadena, final String token) {
		
		StringTokenizer st = new StringTokenizer(cadena, token); 
		
		if(Objects.isNull(st)) {
			return null;
		}
		
		List<String> listaAux = new ArrayList<>(10);
		
		while (st.hasMoreElements()) {
			listaAux.add((String) st.nextElement());
			
		}
		
		return listaAux.toArray(new String[0]);
	}
}
