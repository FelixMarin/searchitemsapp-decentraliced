package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dao.MarcasDao;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@Aspect
public class MarcasImpl implements IFImplementacion<MarcasDTO, CategoriaDTO> {
	

	public MarcasImpl() {
		super();
	}
	
	@Autowired
	private MarcasDao marcasDao;
	
	public List<MarcasDTO> findAll() throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());		
		
		return marcasDao.findAll();
	}
	
	@Override
	public MarcasDTO findByDid(MarcasDTO marcasDTO) throws IOException {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(marcasDTO)) {
			return (MarcasDTO) ClaseUtils.NULL_OBJECT;
		}		
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.marcas.dto.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(marcasDTO.getDid());

		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		return marcasDao.findByDid(marcasDTO.getDid());		
	}

	@Override
	public List<MarcasDTO> findByTbSia(MarcasDTO r, CategoriaDTO t) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}
}
