package com.searchitemsapp.impl;

import java.io.IOException;
import java.util.List;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.util.StringUtils;

public class NomProductoImpl  implements IFImplementacion<NomProductoDTO, Object> {
	
	public NomProductoImpl() {
		super();
	}

	@Override
	public NomProductoDTO findByDid(NomProductoDTO objeto) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<NomProductoDTO> findAll() throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

	@Override
	public List<NomProductoDTO> findByTbSia(NomProductoDTO r, Object t) throws IOException {
		throw new UnsupportedOperationException(StringUtils.OPERACION_NO_SOPORTADA);
	}

}
