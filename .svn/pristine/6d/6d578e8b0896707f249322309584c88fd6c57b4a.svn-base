package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.SelectoresCssDao;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
@Aspect
public class SelectoresCssImpl implements IFImplementacion<SelectoresCssDTO, EmpresaDTO> {

	public SelectoresCssImpl() {
		super();
	}
	
	@Autowired
	private SelectoresCssDao selectoresCssDao;
	
	@Override
	public SelectoresCssDTO findByDid(SelectoresCssDTO selectorCssDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(selectorCssDto)) {
			return (SelectoresCssDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.valor.dto"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(selectorCssDto.toString());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return selectoresCssDao.findByDid(selectorCssDto.getDid());
	}	
	
	public List<SelectoresCssDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return selectoresCssDao.findAll();
	}	
	
	@Override
	public List<SelectoresCssDTO> findByTbSia(SelectoresCssDTO selectoresCssDto, EmpresaDTO empresaDto) throws IOException {
			
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(empresaDto)) {
			return (List<SelectoresCssDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.activo"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(empresaDto.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		TbSiaEmpresa tbSiaEmpresa = new TbSiaEmpresa();
		tbSiaEmpresa.setDid(empresaDto.getDid());
		
		return selectoresCssDao.findByTbSiaEmpresa(tbSiaEmpresa);
	}
}
