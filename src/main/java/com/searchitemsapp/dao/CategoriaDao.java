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

/**
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa 
 * de lógica de negocio necesite interactuar con la base de datos, va 
 * a hacerlo a través de la API que le ofrece el DAO.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings({"unchecked"})
@Repository
public class CategoriaDao extends AbstractDao<CategoriaDTO, TbSiaCategoriasEmpresa> implements IFCategoriaRepository {

	/*
	 * Constantes
	 */
	private static final String CATEGORIA_PARSER = "CATEGORIA_PARSER";
	
	/*
	 * Constructor 
	 */
	public CategoriaDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de la tabla {@link TbSiaCategoriasEmpresa}.
	 * 
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<CategoriaDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<CategoriaDTO> resultado = (List<CategoriaDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.categoria.select.all"));		

		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);
		
		try {
			/**
			 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
			 */
			resultado = getParser(CATEGORIA_PARSER).toListDTO(((List<TbSiaCategoriasEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}
		
	/**
	 * A partir de un indentifcador se obtiene un elemento de la tabla.
	 * 
	 * @param id
	 * @return CategoriaDTO
	 */
	@Override
	public CategoriaDTO findByDid(Integer did) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(did)) {
			return (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		/**
		 * Se traza el identificador de la categoría.
		 */
		LogsUtils.escribeLogDebug(String.valueOf(did),this.getClass());
		
		CategoriaDTO categoriaDTO = (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			categoriaDTO = getParser(CATEGORIA_PARSER).toDTO(getEntityManager().find(TbSiaCategoriasEmpresa.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),CategoriaDao.class);
		}
		
		return categoriaDTO;
	}
	
	/**
	 * Devuelve las categorías que estén activadas.
	 * 
	 * @param activo
	 * @return List<CategoriaDTO>
	 * @exception IOException
	 */
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
