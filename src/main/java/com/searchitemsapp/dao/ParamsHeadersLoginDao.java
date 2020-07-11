package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.searchitemsapp.config.IFCommonsProperties;
import com.searchitemsapp.dao.repository.IFParamsHeadersLogin;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsHeadersLogin;
import com.searchitemsapp.parsers.IFParser;

/**
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa 
 * de lógica de negocio necesite interactuar con la base de datos, va 
 * a hacerlo a través de la API que le ofrece el DAO.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class ParamsHeadersLoginDao extends AbstractDao implements IFParamsHeadersLogin {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsHeadersLoginDao.class);  
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFParser<ParamsLoginDTO, TbSiaParamsHeadersLogin> parser;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;
	
	/*
	 * Constructor
	 */
	public ParamsHeadersLoginDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<ParamsLoginDTO> findAll() throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<ParamsLoginDTO> resultado = null;
		
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.login.headers.select.all"));
		
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaParamsHeadersLogin.class);
		
		try {
			resultado = parser.toListDTO(((List<TbSiaParamsHeadersLogin>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		return resultado;
	}

	@Override
	public List<ParamsLoginDTO> findByTbSiaUrl(Integer didUrl) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		if(Objects.isNull(didUrl)) {
			return Lists.newArrayList();
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = null;
		
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(iFCommonsProperties.getValue("flow.value.login.header.select.by.url"));
		Query query = entityManager.createQuery(stringBuilder.toString(), TbSiaParamsHeadersLogin.class);
		query.setParameter(iFCommonsProperties.getValue("flow.value.url.did.param.txt"), didUrl);
		
		try {
			listParamsLoginDto = parser.toListDTO((List<TbSiaParamsHeadersLogin>) query.getResultList());
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
			listParamsLoginDto = Lists.newArrayList();
		}
		
		
		
		return listParamsLoginDto;
	}
	
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return ParamsLoginDTO
	 */
	@Override
	public ParamsLoginDTO findByDid(Integer did) throws IOException {
		return parser.toDTO(entityManager.find(TbSiaParamsHeadersLogin.class, did));
	}		
}
