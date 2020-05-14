package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;
import com.searchitemsapp.model.TbSiaUrl;
import com.searchitemsapp.repository.IFParamsFormLogin;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

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
public class ParamsFormLoginDao extends AbstractDao<ParamsLoginDTO, TbSiaParamsFormLogin> implements IFParamsFormLogin {
	
	private static final String PARAMS_FORM_PARSER = "PARAMS_FORM_PARSER";

	public ParamsFormLoginDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<ParamsLoginDTO> findAll() throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<ParamsLoginDTO> resultado = (List<ParamsLoginDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.form.select.all"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaParamsFormLogin.class);
		
		try {
			resultado = getParser(PARAMS_FORM_PARSER).toListDTO(((List<TbSiaParamsFormLogin>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;		
	}

	@Override
	public List<ParamsLoginDTO> findByTbSiaUrl(TbSiaUrl tbSiaUrl) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(tbSiaUrl)) {
			return (List<ParamsLoginDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<ParamsLoginDTO> listParamsLoginDto = (List<ParamsLoginDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.form.select.by.url"));
		Query query = getEntityManager().createQuery(queryBuilder.toString(), TbSiaParamsFormLogin.class);
		query.setParameter(CommonsPorperties.getValue("flow.value.url.did.param.txt"), tbSiaUrl.getDid());
		List<TbSiaParamsFormLogin> formLoginList = query.getResultList();	
		
		try {
			listParamsLoginDto = getParser(PARAMS_FORM_PARSER).toListDTO(formLoginList);
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
		return getParser(PARAMS_FORM_PARSER).toDTO(getEntityManager().find(TbSiaParamsFormLogin.class, did));
	}	
}
