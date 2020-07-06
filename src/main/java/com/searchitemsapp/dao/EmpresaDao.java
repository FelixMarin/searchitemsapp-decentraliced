package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.repository.IFEmpresaRepository;
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
public class EmpresaDao extends AbstractDao<EmpresaDTO, TbSiaEmpresa> implements IFEmpresaRepository {
	
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<EmpresaDTO> resultado = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
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
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(did)) {
			return (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		EmpresaDTO empresaDto = (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		
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
			empresaDto = getParser(EMPRESA_PARSER).toDTO(getEntityManager().find(TbSiaEmpresa.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return empresaDto;
	}
	
	/**
	 * Devuelve una lista de empresas que pertenecen a una categoria.
	 * 
	 * @param TbSiaCategoriasEmpresa
	 * @return List<EmpresaDTO>
	 * @exception IOException
	 */
	@Override
	public List<EmpresaDTO> findByTbSiaCategoriasEmpresa(TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<EmpresaDTO> resultado = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(tbSiaCategoriasEmpresa)) {
			return resultado;
		}
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.categoria.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(tbSiaCategoriasEmpresa.getDid());	
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.lista.empresas.by.categoria"));
		
		/**
		 * Se comprueba que el entity manager esté activado.
		 */
		isEntityManagerOpen(this.getClass());
		
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);	
		q.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), tbSiaCategoriasEmpresa.getDid());	
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			resultado = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
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
	public List<EmpresaDTO> findByTbSiaCategoriasEmpresa(Integer didEmpresa, Integer didCategoriaEmpresa) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(didEmpresa) || ClaseUtils.isNullObject(didCategoriaEmpresa)) {
			return (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<EmpresaDTO> listEmpresaDto = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se obtiene la query del fichero de propiedades.
		 */
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.lista.empresas.by.empresa.y.categoria"));
			
		/**
		 * Se ejecuta la consulta y se almacena en ubjeto de tipo query.
		 * A la consulta se le pasan como parametros los identificadores.
		 */
		Query q = getEntityManager().createQuery(queryBuilder.toString());		
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), didEmpresa);	
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didCategoriaEmpresa.key"), didCategoriaEmpresa);	
		
		/**
		 * Se recupera el resultado de la query y se mapea a un objeto de tipo DTO.
		 */
		try {
			listEmpresaDto = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return listEmpresaDto;
	}
}
