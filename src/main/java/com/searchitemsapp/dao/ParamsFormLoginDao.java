package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsFormLogin;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFParamsFormLogin;

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
public class ParamsFormLoginDao extends AbstractDao implements IFParamsFormLogin {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsFormLoginDao.class);     
	
	/*
	 * Variables Globales.
	 */
	@Autowired
	private IFParser<ParamsLoginDTO, TbSiaParamsFormLogin> parser;
	
	/*
	 * Constructor
	 */
	public ParamsFormLoginDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<ParamsLoginDTO> findAll() throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<ParamsLoginDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		stringBuilder.append(CommonsPorperties.getValue("flow.value.login.form.select.all"));
				
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaParamsFormLogin.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = parser.toListDTO(((List<TbSiaParamsFormLogin>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		stringBuilder.setLength(0);
		
		return resultado;		
	}

	/**
	 *  Devuelve una lista de parametros correspondientes a una URL.
	 *  
	 * @param TbSiaUrl
	 * @return List<ParamsLoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<ParamsLoginDTO> findByTbSiaUrl(Integer didUrl) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(didUrl)) {
			return new ArrayList<>(NumberUtils.INTEGER_ONE);
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades y se
		 * le añade el parametro al objeto query.
		 */
		stringBuilder.append(CommonsPorperties.getValue("flow.value.login.form.select.by.url"));
		Query query = entityManager.createQuery(stringBuilder.toString(), TbSiaParamsFormLogin.class);
		query.setParameter(CommonsPorperties.getValue("flow.value.url.did.param.txt"), didUrl);
		List<TbSiaParamsFormLogin> formLoginList = query.getResultList();	
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			listParamsLoginDto = parser.toListDTO(formLoginList);
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		stringBuilder.setLength(0);
		
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
		throw new NotImplementedException();
	}	
}
