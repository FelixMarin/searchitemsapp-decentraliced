package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaUrl;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFUrlRepository;




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
public class UrlDao extends AbstractDao<UrlDTO, TbSiaUrl> implements IFUrlRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlDao.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String URL_PARSER = "URL_PARSER";

	/*
	 * Constructor
	 */
	public UrlDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<EmpresaDTO>
	 */
	@Override
	public List<UrlDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<UrlDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(10);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.all"));		

		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en objeto de tipo query
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaUrl.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString());
			}
		}
		
		return resultado;
	}
	
	/**
	 * Método que devuelve un elemento de la 
	 * tabla dependiendo del identificador
	 * 
	 * @return UrlDTO
	 */
	@Override
	public UrlDTO findByDid(Integer did) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (Objects.isNull(did)) {
			return null;
		}
		
		UrlDTO urlDto = null;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = new StringBuilder(10);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(" ");
		debugMessage.append(did);

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			urlDto = getParser(URL_PARSER).toDTO(getEntityManager().find(TbSiaUrl.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString());
			}
		}

		return urlDto;
	}

	/**
	 * Devuelve una lista de URLs correspondientes
	 * a un pais y a una categoria.
	 * 
	 * @param didPais Integer
	 * @param didCategoria Integer
	 * @exception IOException
	 */
	@Override
	public List<UrlDTO> findByDidAndDesUrl(Integer didPais, String didCategoria) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (Objects.isNull(didCategoria)) {
			return null;
		}
		
		List<UrlDTO> listResultadoDto = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(10);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.url.by.nom.categoria"));
		
		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 * Se le asignan los parámetros de entrada.
		 */
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), Integer.parseInt(didCategoria));
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			listResultadoDto = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) query.getSingleResult()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString());
			}
		}
		
		return listResultadoDto;
	}

	/**
	 * Devuelve una lista de URLs correspondientes a una empresa.
	 * 
	 * @param TbSiaEmpresa
	 * @return List<UrlDTO>
	 * @exception IOException
	 */
	@Override
	public List<UrlDTO> findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (Objects.isNull(tbSiaEmpresa)) {
			return null;
		}
		
		List<UrlDTO> listUrlDto = null;

		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		StringBuilder queryBuilder = new StringBuilder(10);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.by.empresa"));

		/**
		 * Se obtiene la query del fichero de propiedades y se
		 * le añade el parametro al objeto query.
		 */
		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setHint(CommonsPorperties.getValue("flow.value.hibernate.dialect"), 
				  CommonsPorperties.getValue("flow.value.hibernate.dialect.PostgreSQLDialect"));
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), tbSiaEmpresa.getDid());

		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			listUrlDto = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) query.getSingleResult()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString());
			}
		}
		
		return listUrlDto;
	}

	/**
	 * Devuelve una lista de URLs correspondientes a un país y auna categoría.
	 * 
	 * @param Integer didPais
	 * @param Integer didCategoria
	 * @return List<UrlDTO>
	 * @exception IOException
	 */
	@Override
	public List<UrlDTO> findByDidAndNomUrl(Integer didPais, String didCategoria) throws IOException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (Objects.isNull(didCategoria)) {
			return null;
		}
		
		List<UrlDTO> urlDto = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(10);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.url.by.bollogin"));
		
		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), Integer.parseInt(didCategoria));
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);

		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			urlDto = getParserObject().toListODTO((List<Object[]>) query.getResultList());
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString());
			}
		}
		
		return urlDto;
	}
	
	private IFParser<UrlDTO, Object[]> getParserObject() {
		return (IFParser<UrlDTO, Object[]>) getParserFactory().getParser(URL_PARSER);
	}	
}
