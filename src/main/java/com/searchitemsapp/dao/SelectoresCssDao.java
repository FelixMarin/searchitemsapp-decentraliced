package com.searchitemsapp.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.entities.TbSiaSelectoresCss;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFSelectoresCssRepository;

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
public class SelectoresCssDao extends AbstractDao implements IFSelectoresCssRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SelectoresCssDao.class);  
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFParser<SelectoresCssDTO, TbSiaSelectoresCss> parser;
	
	/*
	 * Constructor
	 */
	public SelectoresCssDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<SelectoresCssDTO>
	 * @exception IOException
	 */
	@Override
	public List<SelectoresCssDTO> findAll() throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<SelectoresCssDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.selectorescss.select.all"));
				
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		Query q = entityManager.createQuery(stringBuilder.toString(), TbSiaSelectoresCss.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = parser.toListDTO(((List<TbSiaSelectoresCss>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		return resultado;
	}

	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @param Integer
	 * @return SelectoresCssDTO
	 * @exception IOException
	 */
	@Override
	public SelectoresCssDTO findByDid(Integer did) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(did)) {
			return new SelectoresCssDTO();
		}
		
		SelectoresCssDTO resultado = null;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.selectorescss.did.txt"))
		.append(StringUtils.SPACE).append(did);

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(stringBuilder.toString(),this.getClass());
		}
			
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			resultado = parser.toDTO(entityManager.find(TbSiaSelectoresCss.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		return resultado;
	}

	/**
	 * Devuelve una lista de selectores correspondientes a una empresa.
	 * 
	 * @param TbSiaEmpresa
	 * @return List<SelectoresCssDTO>
	 * @exception IOException
	 */
	@Override
	public List<SelectoresCssDTO> findByTbSiaEmpresa(Integer didEmpresa) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (Objects.isNull(didEmpresa)) {
			return new ArrayList<>(NumberUtils.INTEGER_ONE);
		}
		
		List<TbSiaSelectoresCss> selectoresCssList = null;

		/**
		 * Se obtiene la query del fichero de propiedades y se
		 * le añade el parametro al objeto query.
		 */
		StringBuilder stringBuilder = new StringBuilder(1);
		stringBuilder.append(CommonsPorperties.getValue("flow.value.selectorescss.select.by.didEmpresa"));
		Query query = entityManager.createQuery(stringBuilder.toString(), TbSiaSelectoresCss.class);
		query.setParameter("didEmpresa", didEmpresa);

		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			selectoresCssList = query.getResultList();
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		
		
		/**
		 * Se parsea el objeto obtenido a formato DTO y se retorna.
		 */
		return parser.toListDTO((selectoresCssList));
	}	
}
