package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.repository.IFMarcasRepository;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
@Repository
public class MarcasDao extends AbstractDao<MarcasDTO, TbSiaMarcas> implements IFMarcasRepository {
	
	private static final String MARCAS_PARSER = "MARCAS_PARSER";

	public MarcasDao() {
		super();
	}
		
	@Override
	public List<MarcasDTO> findAll() throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<MarcasDTO> resultado = (List<MarcasDTO>) ClaseUtils.NULL_OBJECT;	
		
		StringBuilder queryBuilder = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.marcas.select.all"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaMarcas.class);
		
		try {
			resultado = getParser(MARCAS_PARSER).toListDTO(((List<TbSiaMarcas>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}		
		
		return resultado;
	}

	@Override
	public MarcasDTO findByDid(Integer did) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(did)) {
			return (MarcasDTO) ClaseUtils.NULL_OBJECT;
		}		
		
		MarcasDTO resultado = (MarcasDTO) ClaseUtils.NULL_OBJECT;
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.marcas.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);	
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		try {
			resultado = getParser(MARCAS_PARSER).toDTO(getEntityManager().find(TbSiaMarcas.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}	
}
