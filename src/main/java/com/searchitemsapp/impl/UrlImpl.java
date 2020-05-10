package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.UrlDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
@Aspect
public class UrlImpl implements IFImplementacion<UrlDTO, CategoriaDTO> {
	
	private static final String ALL = "ALL";

	public UrlImpl() {
		super();
	}
	
	@Autowired
	private UrlDao urlDao;

	@Override
	public UrlDTO findByDid(UrlDTO urlDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(urlDTO)) {
			return (UrlDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(urlDTO.getDid());
		
			LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
			
		return urlDao.findByDid(urlDTO.getDid());
	}
	
	public List<UrlDTO> obtenerUrls(PaisDTO paisDto, CategoriaDTO categoriaDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		return urlDao.obtenerUrls(paisDto.getDid(), categoriaDto.getDid());
	}
	
	public List<UrlDTO> obtenerUrlsLogin(PaisDTO paisDto, CategoriaDTO categoriaDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		return urlDao.obtenerUrlsLogin(paisDto.getDid(), categoriaDto.getDid());
	}
	
	public List<UrlDTO> obtenerUrlsPorIdEmpresa(PaisDTO paisDto, 
			CategoriaDTO categoriaDto,
			String strIdsEmpresas) 
			throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(paisDto) ||
				ClaseUtils.isNullObject(categoriaDto) ||
				StringUtils.isEmpty(strIdsEmpresas)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		if(ALL.equalsIgnoreCase(strIdsEmpresas)) {
			strIdsEmpresas = CommonsPorperties.getValue("flow.value.all.id.empresa");
		}
		
		String[] arIdsEpresas = StringUtils.tokenizeString(strIdsEmpresas, StringUtils.COMMA_STRING);
		List<Integer> lsIdsEmpresas = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		
		for (int i = ClaseUtils.ZERO_INT; i < arIdsEpresas.length; i++) {
			lsIdsEmpresas.add(Integer.parseInt(arIdsEpresas[i]));
		}
		
		return urlDao.obtenerUrlsEmpresa(paisDto.getDid(), categoriaDto.getDid(), lsIdsEmpresas);
	}

	@Override
	public List<UrlDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<UrlDTO> findByTbSia(UrlDTO r, CategoriaDTO t) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
