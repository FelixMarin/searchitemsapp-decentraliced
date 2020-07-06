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
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.parsers.IFParser;
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
public class CategoriaDao extends AbstractDao implements IFCategoriaRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaDao.class);   
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFParser<CategoriaDTO, TbSiaCategoriasEmpresa> parser;
	
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
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.categoria.select.all"));		

		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaEmpresa.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = parser.toListDTO(((List<TbSiaCategoriasEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		stringBuilder.setLength(0);
		
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
			return new CategoriaDTO();
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
			categoriaDTO = parser.toDTO(entityManager.find(TbSiaCategoriasEmpresa.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return categoriaDTO;
	}
}
