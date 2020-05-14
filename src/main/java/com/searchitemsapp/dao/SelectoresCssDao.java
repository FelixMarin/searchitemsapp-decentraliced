package com.searchitemsapp.dao;
import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.repository.IFSelectoresCssRepository;
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
public class SelectoresCssDao extends AbstractDao<SelectoresCssDTO, TbSiaSelectoresCss> implements IFSelectoresCssRepository {
	
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";

	public SelectoresCssDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<SelectoresCssDTO> findAll() throws IOException {
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<SelectoresCssDTO> resultado = (List<SelectoresCssDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.selectorescss.select.all"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaSelectoresCss.class);
		
		try {
			resultado = getParser(SELECTORES_PARSER).toListDTO(((List<TbSiaSelectoresCss>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}

	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return SelectoresCssDTO
	 */
	@Override
	public SelectoresCssDTO findByDid(Integer did) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(did)) {
			return (SelectoresCssDTO) ClaseUtils.NULL_OBJECT;
		}
		
		SelectoresCssDTO resultado = (SelectoresCssDTO) ClaseUtils.NULL_OBJECT;
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.selectorescss.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
			
		try {
			resultado = getParser(SELECTORES_PARSER).toDTO(getEntityManager().find(TbSiaSelectoresCss.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}

	@Override
	public List<SelectoresCssDTO> findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if (ClaseUtils.isNullObject(tbSiaEmpresa)) {
			return (List<SelectoresCssDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<TbSiaSelectoresCss> selectoresCssList = (List<TbSiaSelectoresCss>) ClaseUtils.NULL_OBJECT;

		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.selectorescss.select.by.didEmpresa"));
		Query query = getEntityManager().createQuery(queryBuilder.toString(), TbSiaSelectoresCss.class);
		query.setParameter("didEmpresa", tbSiaEmpresa.getDid());

		try {
			selectoresCssList = query.getResultList();
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return getParser(SELECTORES_PARSER).toListDTO((selectoresCssList));
	}	
}
