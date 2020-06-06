package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.repository.IFEmpresaRepository;

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
public class EmpresaDao extends AbstractDao<EmpresaDTO, TbSiaEmpresa> implements IFEmpresaRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaDao.class);     
	
	private static final String EMPRESA_PARSER = "EMPRESA_PARSER";

	public EmpresaDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<EmpresaDTO>
	 */
	@Override
	public List<EmpresaDTO> findAll() throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<EmpresaDTO> resultado = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(NumberUtils.INTEGER_ONE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.all"));		

		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en objeto de tipo query
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return resultado;
	}
	
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return EmpresaDTO
	 */
	@Override
	public EmpresaDTO findByDid(Integer did) throws IOException {
		
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
		
		EmpresaDTO empresaDto = null;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = new StringBuilder(NumberUtils.INTEGER_ONE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE);
		debugMessage.append(did);	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			empresaDto = getParser(EMPRESA_PARSER).toDTO(getEntityManager().find(TbSiaEmpresa.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return empresaDto;
	}
	
	/**
	 * Devuelve una lista de empresas que pertenecen 
	 * a una categoria y tienen un código de empresa.
	 * 
	 * @param TbSiaCategoriasEmpresa
	 * @param Integer
	 * @return List<EmpresaDTO>
	 * @exception IOException
	 */
	@Override
	public List<EmpresaDTO> findByDidAndTbSiaCategoriasEmpresa(Integer didEmpresa, Integer didCatEmpresa) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(didEmpresa) || Objects.isNull(didCatEmpresa)) {
			return null;
		}
		
		List<EmpresaDTO> listEmpresaDto = null;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = new StringBuilder(NumberUtils.INTEGER_ONE);
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.lista.empresas.by.empresa.y.categoria"));
			
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 * A la consulta se le pasan como parametros los identificadores.
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString());		
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), didEmpresa);	
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didCategoriaEmpresa.key"), didCatEmpresa);	
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			listEmpresaDto = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getSingleResult()));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return listEmpresaDto;
	}
}
