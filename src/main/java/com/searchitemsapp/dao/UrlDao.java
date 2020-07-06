package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaUrl;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.repository.IFUrlRepository;
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
public class UrlDao extends AbstractDao<UrlDTO, TbSiaUrl> implements IFUrlRepository {

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
	 * Método que devuelve un elemento de la 
	 * tabla dependiendo del identificador
	 * 
	 * @return UrlDTO
	 */
	@Override
	public UrlDTO findByDid(Integer did) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (ClaseUtils.isNullObject(did)) {
			return (UrlDTO) ClaseUtils.NULL_OBJECT;
		}
		
		UrlDTO urlDto = (UrlDTO) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);

		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			urlDto = getParser(URL_PARSER).toDTO(getEntityManager().find(TbSiaUrl.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
	public List<UrlDTO> obtenerUrls(Integer didPais, Integer didCategoria) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (ClaseUtils.isNullObject(didCategoria)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> listResultadoDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
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
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), didCategoria);
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			listResultadoDto = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) query.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (ClaseUtils.isNullObject(tbSiaEmpresa)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> listUrlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;

		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
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
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
	public List<UrlDTO> obtenerUrlsLogin(Integer didPais, Integer didCategoria) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (ClaseUtils.isNullObject(didCategoria)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> urlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.url.by.bollogin"));
		
		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), didCategoria);
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);

		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			urlDto = getParserObject().toListODTO((List<Object[]>) query.getResultList());
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return urlDto;
	}
	
	/**
	 * Devuelve un listado de URLs correspondientes 
	 * a un subgrupo de empresas dentro de un mimo 
	 * país de la misma categoría.
	 * 
	 * @param Integer didPais
	 * @param Integer didCategoria
	 * @param List<Integer> listDidEmpresas
	 * @return List<UrlDTO>
	 * @exception IOException
	 */
	@Override
	public List<UrlDTO> obtenerUrlsEmpresa(Integer didPais, 
			Integer didCategoria, 
			List<Integer> listDidEmpresas) 
					throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if (ClaseUtils.isNullObject(listDidEmpresas)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO>listUrlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties
				.getValue("flow.value.url.select.url.by.list.empresa"));
		
		/**
		 * Se obtiene la query del fichero de propiedades y se
		 * le añade el parametro al objeto query.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 */
		Query query = getEntityManager().createQuery(queryBuilder.toString());
				query.setParameter(CommonsPorperties
						.getValue("flow.value.url.list.did.empresas.var"),listDidEmpresas);

		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			listUrlDto = getParser(URL_PARSER).toListDTO((List<TbSiaUrl>) query.getResultList());
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
				
		return listUrlDto;
	}
	
	private IFParser<UrlDTO, Object[]> getParserObject() {
		return (IFParser<UrlDTO, Object[]>) getParserFactory().getParser(URL_PARSER);
	}	
}
