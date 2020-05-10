package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.ParamsFormLoginDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
@Aspect
public class ParamsFormLoginImpl implements IFImplementacion<ParamsLoginDTO, CategoriaDTO> {
	

	public ParamsFormLoginImpl() {
		super();
	}

	@Autowired
	private ParamsFormLoginDao paramsFormLoginDao;
	
	public List<ParamsLoginDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		return paramsFormLoginDao.findAll();
	}
	
	public List<ParamsLoginDTO> findByTbSia(ParamsLoginDTO paramsLoginDTO, CategoriaDTO categoriaDTO) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(paramsLoginDTO) || ClaseUtils.isNullObject(categoriaDTO)) {
			return (List<ParamsLoginDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.url.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(paramsLoginDTO.getTbSiaUrl().getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return paramsFormLoginDao.findByTbSiaUrl(paramsLoginDTO.getTbSiaUrl());
	}

	@Override
	public ParamsLoginDTO findByDid(ParamsLoginDTO paramsLoginDTO) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
