package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.repository.IFCategoriaRepository;

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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaDao.class);   
	
	/*
	 * Constantes Globales
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
	 * @return List<CategoriaDTO>
	 * @exception IOException
	 */
	@Override
	public List<CategoriaDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<CategoriaDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(NumberUtils.INTEGER_ONE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.categoria.select.all"));		

		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = getParser(CATEGORIA_PARSER).toListDTO(((List<TbSiaCategoriasEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(did)) {
			return null;
		}
		
		/**
		 * Se traza el identificador de la categoría.
		 */
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(String.valueOf(did),this.getClass());
		}
		
		CategoriaDTO categoriaDTO = null;
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			categoriaDTO = getParser(CATEGORIA_PARSER).toDTO(getEntityManager().find(TbSiaCategoriasEmpresa.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return categoriaDTO;
	}
}
