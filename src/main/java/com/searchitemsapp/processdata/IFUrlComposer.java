package com.searchitemsapp.processdata;

import java.io.IOException;
import java.util.List;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;

public interface IFUrlComposer {

	abstract public List<UrlDTO> replaceWildcardCharacter(final String didPais, 
			final String didCategoria, 
			final String producto,
			final String empresas,
			final List<SelectoresCssDTO> listTodosSelectoresCss) 
			throws IOException;
	
	abstract public void applicationData();
	
	abstract public List<SelectoresCssDTO> listSelectoresCssPorEmpresa(
			final String didEmpresas, 
			final String didPais,
			final String didCategoria) throws IOException;
	
}
