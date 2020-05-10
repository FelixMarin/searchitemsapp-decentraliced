package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.repository.IFCategoriaRepository;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings({"unchecked"})
@Repository
public class CategoriaDao extends AbstractDao<CategoriaDTO, TbSiaCategoriasEmpresa> implements IFCategoriaRepository {

	private static final String CATEGORIA_PARSER = "CATEGORIA_PARSER";
	
	public CategoriaDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<CategoriaDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<CategoriaDTO> resultado = (List<CategoriaDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.categoria.select.all"));		

		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);
		
		try {
			resultado = getParser(CATEGORIA_PARSER).toListDTO(((List<TbSiaCategoriasEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}
		
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return CategoriaDTO
	 */
	@Override
	public CategoriaDTO findByDid(Integer did) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(did)) {
			return (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		LogsUtils.escribeLogDebug(String.valueOf(did),this.getClass());
		
		CategoriaDTO categoriaDTO = (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		
		try {
			categoriaDTO = getParser(CATEGORIA_PARSER).toDTO(getEntityManager().find(TbSiaCategoriasEmpresa.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),CategoriaDao.class);
		}
		
		return categoriaDTO;
	}
	
	
	@Override
	public List<CategoriaDTO> findByBolActivo(Boolean activo) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(activo)) {
			return (List<CategoriaDTO>)ClaseUtils.NULL_OBJECT;
		}
		
		List<CategoriaDTO> listCategoriaDTO = (List<CategoriaDTO>)ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.categoria.select.categoria.by.activo"));
		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.activo"), activo);
		
		try {
			listCategoriaDTO = getParser(CATEGORIA_PARSER).toListDTO(((List<TbSiaCategoriasEmpresa>) query.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return listCategoriaDTO;
	}	
}
