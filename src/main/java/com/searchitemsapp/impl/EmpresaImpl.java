package com.searchitemsapp.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.EmpresaDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings({"rawtypes", "unchecked"})
@Aspect
public class EmpresaImpl implements IFImplementacion<EmpresaDTO, CategoriaDTO> {
	
	
	public EmpresaImpl() {
		super();
	}
	
	@Autowired
	private EmpresaDao empresaDao;
	
	@Override
	public List<EmpresaDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return empresaDao.findAll();
	}	
	
	@Override
	public EmpresaDTO findByDid(EmpresaDTO empresaDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		if(ClaseUtils.isNullObject(empresaDto) ||
				ClaseUtils.isNullObject(empresaDto.getDid())) {
			
			return (EmpresaDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDto.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return empresaDao.findByDid(empresaDto.getDid());
	}
	
	@Override
	public List<EmpresaDTO> findByTbSia(EmpresaDTO empresaDto, CategoriaDTO categoriaDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(empresaDto) || ClaseUtils.isNullObject(categoriaDto)) {
			return new ArrayList();
		}
				
		return empresaDao.findByTbSiaCategoriasEmpresa(empresaDto.getDid(), categoriaDto.getDid());
	}	
}
