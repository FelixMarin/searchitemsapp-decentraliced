package com.searchitemsapp.parsers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
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
public class UrlParser implements IFParser<UrlDTO, TbSiaUrl> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlParser.class);  
	
	/*
	 * Constructor
	 */
	public UrlParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaUrl
	 * @return UrlDTO
	 */
	public UrlDTO toDTO(TbSiaUrl tbSiaPUrl) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		UrlDTO urlPDto = new UrlDTO();
		List<LinkedHashMap<String, String>> selectores = new ArrayList<>(10);
		
		urlPDto.setBolActivo(tbSiaPUrl.getBolActivo());
		urlPDto.setDesUrl(tbSiaPUrl.getDesUrl());
		urlPDto.setDid(tbSiaPUrl.getDid());
		urlPDto.setNomUrl(tbSiaPUrl.getNomUrl());
		urlPDto.setBolStatus(tbSiaPUrl.getBolStatus());
		urlPDto.setBolLogin(tbSiaPUrl.getBolLogin());
		urlPDto.setNomEmpresa(tbSiaPUrl.getTbSiaEmpresa().getNomEmpresa());
		urlPDto.setDidEmpresa(tbSiaPUrl.getTbSiaEmpresa().getDid());
		
		for (TbSiaSelectoresCss tbSiaSelectoresCsses : tbSiaPUrl.getTbSiaSelectoresCsses()) {
			LinkedHashMap<String, String> mapSelectores = new LinkedHashMap<String, String>(10);
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
			selectores.add(mapSelectores);
		}
		urlPDto.setSelectores(selectores);
		
		return urlPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param UrlDTO
	 * @return TbSiaUrl
	 */
	public TbSiaUrl toTbSia(UrlDTO urlPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaUrl tbSiaPUrl = new TbSiaUrl();
		
		tbSiaPUrl.setBolActivo(urlPDto.getBolActivo());
		tbSiaPUrl.setDesUrl(urlPDto.getDesUrl());
		tbSiaPUrl.setDid(urlPDto.getDid());
		tbSiaPUrl.setNomUrl(urlPDto.getNomUrl());
		tbSiaPUrl.setBolStatus(urlPDto.getBolStatus());
		tbSiaPUrl.setBolLogin(urlPDto.getBolLogin());	
		tbSiaPUrl.setTbSiaSelectoresCsses(new ArrayList<TbSiaSelectoresCss>());
		tbSiaPUrl.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaPUrl.getTbSiaEmpresa().setDid(urlPDto.getDid());
		tbSiaPUrl.getTbSiaEmpresa().setNomEmpresa(urlPDto.getNomEmpresa());
		
		for (LinkedHashMap<String,String> map : urlPDto.getSelectores()) {
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
			tbSiaPUrl.getTbSiaSelectoresCsses().add(tselectores);
		}
		
		return tbSiaPUrl;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaUrl>
	 * @return List<UrlDTO>
	 */
	public List<UrlDTO> toListDTO(List<TbSiaUrl> lsUrls) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<UrlDTO> listDto = new ArrayList<>(10); 
		List<LinkedHashMap<String, String>> selectores = new ArrayList<>(10);
		UrlDTO urlPDto;
		
		for (TbSiaUrl urlDto : lsUrls) {
			urlPDto = new UrlDTO();
			urlPDto.setBolActivo(urlDto.getBolActivo());
			urlPDto.setDesUrl(urlDto.getDesUrl());
			urlPDto.setDid(urlDto.getDid());
			urlPDto.setNomUrl(urlDto.getNomUrl());
			urlPDto.setBolStatus(urlDto.getBolStatus());
			urlPDto.setBolLogin(urlDto.getBolLogin());
			urlPDto.setNomEmpresa(urlDto.getTbSiaEmpresa().getNomEmpresa());
			urlPDto.setDidEmpresa(urlDto.getTbSiaEmpresa().getDid());
			
			for (TbSiaSelectoresCss tbSiaSelectoresCsses : urlDto.getTbSiaSelectoresCsses()) {
				LinkedHashMap<String, String> mapSelectores = new LinkedHashMap<String, String>(10);
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
				selectores.add(mapSelectores);
			}
			urlPDto.setSelectores(selectores);
			
			listDto.add(urlPDto);
		}
		
		return listDto;
	}
	
	/**
	 * Convierte una lista de arrays de objetos a una lista de URLs.
	 * 
	 * @param List<Object[]>
	 * @return List<UrlDTO>
	 */
	public List<UrlDTO> toListODTO(List<Object[]> urlList) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		UrlDTO urlDto;
		
		if(Objects.isNull(urlList)) {
			return (List<UrlDTO>) null;
		}
		
		List<UrlDTO> listUrlDto = null;
		
		if (!urlList.isEmpty()){ 
			listUrlDto = new ArrayList<>(10);
			for (Object[] objects : urlList) {
				urlDto = new UrlDTO();
				urlDto.setNomUrl(String.valueOf(objects[0]));
				urlDto.setDidEmpresa(Integer.parseInt(String.valueOf(objects[1])));
				urlDto.setDid(Integer.parseInt(String.valueOf(objects[2])));
				urlDto.setBolActivo(Boolean.parseBoolean(null!=objects[3]?String.valueOf(objects[3]):null));
				urlDto.setNomEmpresa(String.valueOf(objects[4]));
				urlDto.setBolStatus(Boolean.parseBoolean(null!=objects[5]?String.valueOf(objects[5]):null));
				urlDto.setBolLogin(Boolean.parseBoolean(null!=objects[6]?String.valueOf(objects[6]):null));
				urlDto.setDesUrl(String.valueOf(objects[7]));
				listUrlDto.add(urlDto);
			}
		}
		return listUrlDto;
	}
}
