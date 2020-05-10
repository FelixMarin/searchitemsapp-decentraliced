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

@SuppressWarnings("unchecked")
@Repository
public class UrlDao extends AbstractDao<UrlDTO, TbSiaUrl> implements IFUrlRepository {

	private static final String URL_PARSER = "URL_PARSER";

	public UrlDao() {
		super();
	}
	
	/**
	 * Método que devuelve un elemento de la 
	 * tabla dependiendo del identificador
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public UrlDTO findByDid(Integer did) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		if (ClaseUtils.isNullObject(did)) {
			return (UrlDTO) ClaseUtils.NULL_OBJECT;
		}
		
		UrlDTO urlDto = (UrlDTO) ClaseUtils.NULL_OBJECT;
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);

		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		try {
			urlDto = getParser(URL_PARSER).toDTO(getEntityManager().find(TbSiaUrl.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}

		return urlDto;
	}

	@Override
	public List<UrlDTO> obtenerUrls(Integer didPais, Integer didCategoria) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		if (ClaseUtils.isNullObject(didCategoria)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> listResultadoDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.url.by.nom.categoria"));
		
		isEntityManagerOpen(this.getClass());
		
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), didCategoria);
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);
		
		try {
			listResultadoDto = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) query.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return listResultadoDto;
	}

	@Override
	public List<UrlDTO> findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		if (ClaseUtils.isNullObject(tbSiaEmpresa)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> listUrlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;

		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.by.empresa"));

		Query query = getEntityManager().createQuery(queryBuilder.toString());
		query.setHint(CommonsPorperties.getValue("flow.value.hibernate.dialect"), 
				  CommonsPorperties.getValue("flow.value.hibernate.dialect.PostgreSQLDialect"));
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didEmpresa.key"), tbSiaEmpresa.getDid());

		try {
			listUrlDto = getParser(URL_PARSER).toListDTO(((List<TbSiaUrl>) query.getSingleResult()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return listUrlDto;
	}

	@Override
	public List<UrlDTO> obtenerUrlsLogin(Integer didPais, Integer didCategoria) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		if (ClaseUtils.isNullObject(didCategoria)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> urlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.url.select.url.by.bollogin"));
		
		isEntityManagerOpen(this.getClass());
		
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
		query.setParameter(CommonsPorperties.getValue("flow.value.empresa.didCategoria.key"), didCategoria);
		query.setParameter(CommonsPorperties.getValue("flow.value.categoria.didPais.key"), didPais);

		try {
			urlDto = getParserObject().toListODTO((List<Object[]>) query.getResultList());
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return urlDto;
	}
	
	@Override
	public List<UrlDTO> obtenerUrlsEmpresa(Integer didPais, 
			Integer didCategoria, 
			List<Integer> listDidEmpresas) 
					throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if (ClaseUtils.isNullObject(listDidEmpresas)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO>listUrlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties
				.getValue("flow.value.url.select.url.by.list.empresa"));
		
		isEntityManagerOpen(this.getClass());
		
		Query query = getEntityManager().createQuery(queryBuilder.toString());
				query.setParameter(CommonsPorperties
						.getValue("flow.value.url.list.did.empresas.var"),listDidEmpresas);

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
