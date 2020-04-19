package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.PaisDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@Aspect
public class PaisImpl implements IFImplementacion<PaisDTO, CategoriaDTO> {
	
	public PaisImpl() {
		super();
	}
	
	@Autowired
	private PaisDao paisDao;
	
	@Override
	public PaisDTO findByDid(PaisDTO paisDto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(paisDto)) {
			return (PaisDTO) ClaseUtils.NULL_OBJECT;
		}
		
		final StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.pais.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(paisDto.getDid());
		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return paisDao.findByDid(paisDto.getDid());
	}

	@Override
	public List<PaisDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<PaisDTO> findByTbSia(PaisDTO paisDTO, CategoriaDTO categoriaDTO) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
