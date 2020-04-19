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

@SuppressWarnings({"unchecked"})
@Repository
public class LoginDao extends AbstractDao<LoginDTO, TbSiaLogin> implements IFLoginRepository {

	private static final String LOGIN_PARSER = "LOGIN_PARSER";
	
	public LoginDao() {
		super();
	}
		
	@Override
	public List<LoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<LoginDTO> resultado = (List<LoginDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.select.all"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaLogin.class);
		
		try {
			resultado = getParser(LOGIN_PARSER).toListDTO(((List<TbSiaLogin>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}	
	
	@Override
	public LoginDTO findByDid(Integer did) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(did)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		
		LoginDTO loginDto = (LoginDTO) ClaseUtils.NULL_OBJECT;
		
		LogsUtils.escribeLogDebug(String.valueOf(did),this.getClass());
		
		try {
			loginDto = getParser(LOGIN_PARSER).toDTO(getEntityManager().find(TbSiaLogin.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return loginDto;
	}

	@Override
	public LoginDTO findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa)  throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(tbSiaEmpresa)) {
			return (LoginDTO) ClaseUtils.NULL_OBJECT;
		}
		LogsUtils.escribeLogDebug(String.valueOf(tbSiaEmpresa.toString()),this.getClass());
		
		StringBuilder queryBuilder = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.login.select.by.did.categoria"));
		
		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), tbSiaEmpresa.getDid());
		
		return noResultControl(query);
	}
	
	private LoginDTO noResultControl(Query query) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		LoginDTO resultado = (LoginDTO) ClaseUtils.NULL_OBJECT;
		
		try {
			resultado = getParser(LOGIN_PARSER).toDTO((TbSiaLogin) query.getSingleResult());
		} catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
		
	}
}
