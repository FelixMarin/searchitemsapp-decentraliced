package com.searchitemsapp.parsers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.model.TbSiaUrl;



/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class EmpresaParser implements IFParser<EmpresaDTO, TbSiaEmpresa> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaParser.class);  
	
	/*
	 * Constructor
	 */
	public EmpresaParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaEmpresa
	 * @return EmpresaDTO
	 */
	@Override
	public EmpresaDTO toDTO(TbSiaEmpresa tbSiaPEmpresas) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		EmpresaDTO empresaPDto = new EmpresaDTO();
				
		empresaPDto.setBolActivo(tbSiaPEmpresas.getBolActivo());
		empresaPDto.setDesEmpresa(tbSiaPEmpresas.getDesEmpresa());
		empresaPDto.setDid(tbSiaPEmpresas.getDid());
		empresaPDto.setNomEmpresa(tbSiaPEmpresas.getNomEmpresa());
		empresaPDto.setBolDynScrap(tbSiaPEmpresas.getBolDynScrap());
		
		empresaPDto.setDidPais(tbSiaPEmpresas.getTbSiaPais().getDid());
		empresaPDto.setNomPais(tbSiaPEmpresas.getTbSiaPais().getNomPais());
		empresaPDto.setDidCatEmpresa(tbSiaPEmpresas.getTbSiaCategoriasEmpresa().getDid());
		empresaPDto.setNomCatEmpresa(tbSiaPEmpresas.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
		
		if(!tbSiaPEmpresas.getTbSiaSelectoresCsses().isEmpty()) {
			
			TbSiaSelectoresCss tbSiaSelectoresCsses = tbSiaPEmpresas
					.getTbSiaSelectoresCsses().get(NumberUtils.INTEGER_ZERO);
			
			LinkedHashMap<String, String> mapSelectores = new LinkedHashMap<String, String>(NumberUtils.INTEGER_ONE);
			mapSelectores.put("SCRAP_PATTERN", tbSiaSelectoresCsses.getScrapPattern());
			mapSelectores.put("SCRAP_NO_PATTERN", tbSiaSelectoresCsses.getScrapNoPattern());
			mapSelectores.put("SEL_IMAGE", tbSiaSelectoresCsses.getSelImage());
			mapSelectores.put("SEL_LINK_PROD", tbSiaSelectoresCsses.getSelLinkProd());
			mapSelectores.put("SEL_PAGINACION", tbSiaSelectoresCsses.getSelPaginacion());
			mapSelectores.put("SEL_PRECIO", tbSiaSelectoresCsses.getSelPrecio());
			mapSelectores.put("SEL_PRECIO_KILO", tbSiaSelectoresCsses.getSelPreKilo());
			mapSelectores.put("SEL_PRODUCTO", tbSiaSelectoresCsses.getSelProducto());
			mapSelectores.put("BOL_ACTIVO", tbSiaSelectoresCsses.getBolActivo().toString());
			mapSelectores.put("DID", tbSiaSelectoresCsses.getDid().toString());
			mapSelectores.put("FEC_MODIFICACION", tbSiaSelectoresCsses.getFecModificacion().toString());
			empresaPDto.setSelectores(mapSelectores);
		}
		
		if(!tbSiaPEmpresas.getTbSiaUrls().isEmpty()) {
			TbSiaUrl tbSiaUrl = tbSiaPEmpresas.getTbSiaUrls().get(NumberUtils.INTEGER_ZERO);
			LinkedHashMap<Integer, String> mapUrls = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
			mapUrls.put(tbSiaUrl.getDid(), tbSiaUrl.getNomUrl());
			empresaPDto.setUrls(mapUrls);
		}
		
		return empresaPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param EmpresaDTO
	 * @return TbSiaEmpresa
	 */
	@Override
	public TbSiaEmpresa toTbSia(EmpresaDTO empresaPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaEmpresa tbSiaPEmpresas = new TbSiaEmpresa();
		
		tbSiaPEmpresas.setBolActivo(empresaPDto.getBolActivo());
		tbSiaPEmpresas.setDesEmpresa(empresaPDto.getDesEmpresa());
		tbSiaPEmpresas.setDid(empresaPDto.getDid());
		tbSiaPEmpresas.setNomEmpresa(empresaPDto.getNomEmpresa());
		tbSiaPEmpresas.setBolDynScrap(empresaPDto.getBolDynScrap());
		tbSiaPEmpresas.setTbSiaUrls(new ArrayList<TbSiaUrl>());
		tbSiaPEmpresas.setTbSiaSelectoresCsses(new ArrayList<TbSiaSelectoresCss>());
		tbSiaPEmpresas.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaPEmpresas.setTbSiaPais(new TbSiaPais());
		
		tbSiaPEmpresas.getTbSiaCategoriasEmpresa().setDid(empresaPDto.getDidCatEmpresa());
		tbSiaPEmpresas.getTbSiaCategoriasEmpresa().setNomCatEmpresa(empresaPDto.getNomCatEmpresa());
		tbSiaPEmpresas.getTbSiaPais().setDid(empresaPDto.getDidPais());
		tbSiaPEmpresas.getTbSiaPais().setNomPais(empresaPDto.getNomPais());
		
		for (Map.Entry<Integer,String> e  : empresaPDto.getUrls().entrySet()) {
			TbSiaUrl tburl = new TbSiaUrl();
			tburl.setDid((int) e.getKey());
			tburl.setNomUrl((String) e.getValue());
			tbSiaPEmpresas.getTbSiaUrls().add(tburl);
		}
		
		LinkedHashMap<String,String> map = empresaPDto.getSelectores();
		TbSiaSelectoresCss tselectores = new TbSiaSelectoresCss();
		tselectores.setDid(Integer.parseInt(map.get("DID")));
		tselectores.setScrapPattern(map.get("SCRAP_PATTERN"));
		tselectores.setScrapNoPattern(map.get("SCRAP_NO_PATTERN"));
		tselectores.setSelImage(map.get("SEL_IMAGE"));
		tselectores.setSelLinkProd(map.get("SEL_LINK_PROD"));
		tselectores.setSelPaginacion(map.get("SEL_PAGINACION"));
		tselectores.setSelPrecio(map.get("SEL_PRECIO"));
		tselectores.setSelPreKilo(map.get("SEL_PRECIO_KILO"));
		tselectores.setSelProducto(map.get("SEL_PRODUCTO"));
		tselectores.setBolActivo(Boolean.parseBoolean(map.get("BOL_ACTIVO")));
		tselectores.setFecModificacion(LocalDate.parse(map.get("FEC_MODIFICACION")));
		tbSiaPEmpresas.getTbSiaSelectoresCsses().add(tselectores);
		
		
		return tbSiaPEmpresas;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaEmpresa>
	 * @return List<EmpresaDTO>
	 */
	@Override
	public List<EmpresaDTO> toListDTO(List<TbSiaEmpresa> lsEmpresas) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<EmpresaDTO> listDto = new ArrayList<>(NumberUtils.INTEGER_ONE); 
		EmpresaDTO empresaPDto;
		
		for (TbSiaEmpresa tbSiaEmpresa : lsEmpresas) {
			empresaPDto = new EmpresaDTO();
			empresaPDto.setBolActivo(tbSiaEmpresa.getBolActivo());
			empresaPDto.setDesEmpresa(tbSiaEmpresa.getDesEmpresa());
			empresaPDto.setDid(tbSiaEmpresa.getDid());
			empresaPDto.setNomEmpresa(tbSiaEmpresa.getNomEmpresa());
			empresaPDto.setBolDynScrap(tbSiaEmpresa.getBolDynScrap());

			empresaPDto.setDidPais(tbSiaEmpresa.getTbSiaPais().getDid());
			empresaPDto.setNomPais(tbSiaEmpresa.getTbSiaPais().getNomPais());
			empresaPDto.setDidCatEmpresa(tbSiaEmpresa.getTbSiaCategoriasEmpresa().getDid());
			empresaPDto.setNomCatEmpresa(tbSiaEmpresa.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
			
			if(!tbSiaEmpresa.getTbSiaSelectoresCsses().isEmpty()) {
				
				TbSiaSelectoresCss tbSiaSelectoresCsses = tbSiaEmpresa
						.getTbSiaSelectoresCsses().get(NumberUtils.INTEGER_ZERO);
				
				LinkedHashMap<String, String> mapSelectores = new LinkedHashMap<String, String>(NumberUtils.INTEGER_ONE);
				mapSelectores.put("SCRAP_PATTERN", tbSiaSelectoresCsses.getScrapPattern());
				mapSelectores.put("SCRAP_NO_PATTERN", tbSiaSelectoresCsses.getScrapNoPattern());
				mapSelectores.put("SEL_IMAGE", tbSiaSelectoresCsses.getSelImage());
				mapSelectores.put("SEL_LINK_PROD", tbSiaSelectoresCsses.getSelLinkProd());
				mapSelectores.put("SEL_PAGINACION", tbSiaSelectoresCsses.getSelPaginacion());
				mapSelectores.put("SEL_PRECIO", tbSiaSelectoresCsses.getSelPrecio());
				mapSelectores.put("SEL_PRECIO_KILO", tbSiaSelectoresCsses.getSelPreKilo());
				mapSelectores.put("SEL_PRODUCTO", tbSiaSelectoresCsses.getSelProducto());
				mapSelectores.put("BOL_ACTIVO", tbSiaSelectoresCsses.getBolActivo().toString());
				mapSelectores.put("DID", tbSiaSelectoresCsses.getDid().toString());
				mapSelectores.put("FEC_MODIFICACION", tbSiaSelectoresCsses.getFecModificacion().toString());
				empresaPDto.setSelectores(mapSelectores);
			}
			
			if(!tbSiaEmpresa.getTbSiaUrls().isEmpty()) {
				TbSiaUrl tbSiaUrl = tbSiaEmpresa.getTbSiaUrls().get(NumberUtils.INTEGER_ZERO);
				LinkedHashMap<Integer, String> mapUrls = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
				mapUrls.put(tbSiaUrl.getDid(), tbSiaUrl.getNomUrl());
				empresaPDto.setUrls(mapUrls);
			}
			
			listDto.add(empresaPDto);
		}
		
		return listDto;
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<EmpresaDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(NumberUtils.INTEGER_ONE);
	}
}
