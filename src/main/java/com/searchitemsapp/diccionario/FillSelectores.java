package com.searchitemsapp.diccionario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

@SuppressWarnings("unchecked")
public class FillSelectores {
	
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";

	public FillSelectores() {
		super();
	}
	
	@Autowired
	private ParserFactory parserFactory;
	
	public void fillSelectoresCss(UrlDTO resDtoUrls, List<SelectoresCssDTO> listTodosElementNodes) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		Integer empDidEnUlrs;
		
		if(!ClaseUtils.isNullObject(resDtoUrls) && 
				!ClaseUtils.isNullObject(resDtoUrls.getTbSiaEmpresa()) &&
				!ClaseUtils.isNullObject(listTodosElementNodes) &&
				!listTodosElementNodes.isEmpty()) {
			
			empDidEnUlrs = resDtoUrls.getTbSiaEmpresa().getDid();
			List<SelectoresCssDTO> listElementNodesDTOPorEmpresa = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
			List<TbSiaSelectoresCss> listTbSiaSelectoresCssPorEmpresa = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
			
			for (SelectoresCssDTO elementNodesDTO : listTodosElementNodes) {
				if (empDidEnUlrs == elementNodesDTO.getTbSiaEmpresa().getDid()) {
					listElementNodesDTOPorEmpresa.add(elementNodesDTO);
				}
			}
			
			for (SelectoresCssDTO elementNodesDTO : listElementNodesDTOPorEmpresa) {
				listTbSiaSelectoresCssPorEmpresa.add(getParser().toTbSia(elementNodesDTO));
			}
			
			resDtoUrls.getTbSiaEmpresa().setTbSiaSelectoresCsses(listTbSiaSelectoresCssPorEmpresa);
		}
	}
	
	private IFParser<SelectoresCssDTO, TbSiaSelectoresCss> getParser() {
		return ((IFParser<SelectoresCssDTO, TbSiaSelectoresCss>) parserFactory.getParser(SELECTORES_PARSER));
	}

}
