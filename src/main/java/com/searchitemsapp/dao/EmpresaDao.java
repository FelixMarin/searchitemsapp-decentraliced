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
import com.searchitemsapp.model.TbSiaPais;
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
	 * @return List<LoginDTO>
	 */
	@Override
	public List<EmpresaDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<EmpresaDTO> resultado = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.all"));		

		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);
		
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
		
		if(ClaseUtils.isNullObject(did)) {
			return (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		EmpresaDTO empresaDto = (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		try {
			empresaDto = getParser(EMPRESA_PARSER).toDTO(getEntityManager().find(TbSiaEmpresa.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return empresaDto;
	}
	
	@Override
	public List<EmpresaDTO> findByTbSiaCategoriasEmpresa(TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<EmpresaDTO> resultado = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		//returns true if the object is null
		if(ClaseUtils.isNullObject(tbSiaCategoriasEmpresa)) {
			return resultado;
		}
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.categoria.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(tbSiaCategoriasEmpresa.getDid());	
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.lista.empresas.by.categoria"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaEmpresa.class);	
		q.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), tbSiaCategoriasEmpresa.getDid());	
		
		try {
			resultado = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}
	
	@Override
	public List<EmpresaDTO> findByTbSiaCategoriasEmpresa(Integer didEmpresa, Integer didCategoriaEmpresa) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(didEmpresa) || ClaseUtils.isNullObject(didCategoriaEmpresa)) {
			return (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<EmpresaDTO> listEmpresaDto = (List<EmpresaDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.lista.empresas.by.empresa.y.categoria"));
			
		Query q = getEntityManager().createQuery(queryBuilder.toString());		
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), didEmpresa);	
		q.setParameter(CommonsPorperties.getValue("flow.value.categoria.didCategoriaEmpresa.key"), didCategoriaEmpresa);	
		
		try {
			listEmpresaDto = getParser(EMPRESA_PARSER).toListDTO(((List<TbSiaEmpresa>) q.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return listEmpresaDto;
	}

	@Override
	public EmpresaDTO findByTbSiaPais(TbSiaPais tbSiaPais) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(tbSiaPais)) {
			return (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		EmpresaDTO empresaDto = (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.empresa.select.empresa.by.pais"));
		
		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), tbSiaPais.getDid());	
		
		try {
			empresaDto = getParser(EMPRESA_PARSER).toDTO((TbSiaEmpresa)query.getSingleResult());
		} catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return empresaDto;
	}	
}
