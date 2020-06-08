package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.entities.TbSiaNomProducto;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFNomProductoRepository;

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
public class NomProductoDao extends AbstractDao implements IFNomProductoRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NomProductoDao.class);     
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFParser<NomProductoDTO, TbSiaNomProducto> parser;
		
	/*
	 * Constructor
	 */
	public NomProductoDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 * @exception IOException
	 */
	@Override
	public List<NomProductoDTO> findAll() throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<NomProductoDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		stringBuilder.append(CommonsPorperties.getValue("flow.value.nomproducto.select.all"));
				
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaNomProducto.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = parser.toListDTO(((List<TbSiaNomProducto>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		stringBuilder.setLength(0);
		
		return resultado;
	}
	
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return NomProductoDTO
	 * @exception IOException
	 */
	@Override
	public NomProductoDTO findByDid(Integer did) throws IOException {
		throw new NotImplementedException();
	}
}
