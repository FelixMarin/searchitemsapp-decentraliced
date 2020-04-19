package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.CategoriaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@Aspect
public class CategoriaImpl implements IFImplementacion<CategoriaDTO, EmpresaDTO> {

	
	public CategoriaImpl() {
		super();
	}
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	public List<CategoriaDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return categoriaDao.findAll();
	}
	
	@Override
	public CategoriaDTO findByDid(CategoriaDTO categoriaDto)  throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(categoriaDto)) {
			return (CategoriaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(categoriaDto.toString());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return categoriaDao.findByDid(categoriaDto.getDid());
	}
	
	@Override
	public List<CategoriaDTO> findByTbSia(CategoriaDTO categoriaDTO, EmpresaDTO empresaDTO) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
