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

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.entities.TbSiaLogin;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFLoginRepository;

/**
 * Método que DAO proporcionará los métodos necesarios para 
 * insertar, actualizar, borrar y consultar la información
 * de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings({"unchecked"})
@Repository
public class LoginDao extends AbstractDao implements IFLoginRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);     
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFParser<LoginDTO, TbSiaLogin> parser;

	/*
	 * Constructor
	 */
	public LoginDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<LoginDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<LoginDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.login.select.all"));
				
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaLogin.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = parser.toListDTO(((List<TbSiaLogin>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		return resultado;
	}	
	
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return findByDidDTO
	 */
	@Override
	public LoginDTO findByDid(Integer did) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(did)) {
			return new LoginDTO();
		}
		
		LoginDTO loginDto = null;
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(String.valueOf(did),this.getClass());
		}
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			loginDto = parser.toDTO(entityManager.find(TbSiaLogin.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return loginDto;
	}

	/**
	 * Devuelve los datos de login a la web de una empresa.
	 * 
	 * @param TbSiaEmpresa tbSiaEmpresa
	 * @return LoginDTO
	 * @exception IOException
	 */
	@Override
	public LoginDTO findByTbSiaEmpresa(Integer didEmpresa)  throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(didEmpresa)) {
			return new LoginDTO();
		}

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(String.valueOf(didEmpresa.toString()),this.getClass());
		}
		
		LoginDTO resultado = null;
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.login.select.by.did.categoria"));
		
		/**
		 * Se crea el objeto query y se le 
		 * añade el parametro al objeto query.
		 */
		Query query = entityManager.createQuery(stringBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), didEmpresa);
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			resultado = parser.toDTO((TbSiaLogin) query.getSingleResult());
		} catch(NoResultException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		return resultado;
	}
}
