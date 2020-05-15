package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaLogin;
import com.searchitemsapp.repository.IFLoginRepository;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
public class LoginDao extends AbstractDao<LoginDTO, TbSiaLogin> implements IFLoginRepository {

	/*
	 * Variables Globales
	 */
	private static final String LOGIN_PARSER = "LOGIN_PARSER";
	
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<LoginDTO> resultado = (List<LoginDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.select.all"));
		
		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaLogin.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = getParser(LOGIN_PARSER).toListDTO(((List<TbSiaLogin>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(did)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		
		LoginDTO loginDto = (LoginDTO) ClaseUtils.NULL_OBJECT;
		
		LogsUtils.escribeLogDebug(String.valueOf(did),this.getClass());
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			loginDto = getParser(LOGIN_PARSER).toDTO(getEntityManager().find(TbSiaLogin.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
	public LoginDTO findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa)  throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(tbSiaEmpresa)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		LogsUtils.escribeLogDebug(String.valueOf(tbSiaEmpresa.toString()),this.getClass());
		
		LoginDTO resultado = (LoginDTO) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.select.by.did.categoria"));
		
		/**
		 * Se crea el objeto query y se le 
		 * añade el parametro al objeto query.
		 */
		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), tbSiaEmpresa.getDid());
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			resultado = getParser(LOGIN_PARSER).toDTO((TbSiaLogin) query.getSingleResult());
		} catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}
}
