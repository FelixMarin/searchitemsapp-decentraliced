package com.searchitemsapp.parsers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaSelectoresCss;
import com.searchitemsapp.entities.TbSiaUrl;

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
	
	private static final String FEC_MODIFICACION = "FEC_MODIFICACION";
	private static final String BOL_ACTIVO = "BOL_ACTIVO";
	private static final String SEL_PRODUCTO = "SEL_PRODUCTO";
	private static final String SEL_PRECIO_KILO = "SEL_PRECIO_KILO";
	private static final String SEL_PRECIO = "SEL_PRECIO";
	private static final String SEL_PAGINACION = "SEL_PAGINACION";
	private static final String SEL_LINK_PROD = "SEL_LINK_PROD";
	private static final String SEL_IMAGE = "SEL_IMAGE";
	private static final String SCRAP_NO_PATTERN = "SCRAP_NO_PATTERN";
	private static final String SCRAP_PATTERN = "SCRAP_PATTERN";
	private static final String DID = "DID";
	
	@Autowired
	UrlDTO urlPDto;
	
	@Autowired
	TbSiaUrl tbSiaPUrl;
	
	@Autowired
	TbSiaSelectoresCss tselectores;
	
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
		
		Map<String, String> selectores = new LinkedHashMap<>(NumberUtils.INTEGER_ONE);
		
		urlPDto.setBolActivo(tbSiaPUrl.getBolActivo());
		urlPDto.setDesUrl(tbSiaPUrl.getDesUrl());
		urlPDto.setDid(tbSiaPUrl.getDid());
		urlPDto.setNomUrl(tbSiaPUrl.getNomUrl());
		urlPDto.setBolStatus(tbSiaPUrl.getBolStatus());
		urlPDto.setBolLogin(tbSiaPUrl.getBolLogin());
		urlPDto.setNomEmpresa(tbSiaPUrl.getTbSiaEmpresa().getNomEmpresa());
		urlPDto.setDidEmpresa(tbSiaPUrl.getTbSiaEmpresa().getDid());
		
		if(!tbSiaPUrl.getTbSiaSelectoresCsses().isEmpty()) {
			TbSiaSelectoresCss tbSiaSelectoresCsses = tbSiaPUrl
					.getTbSiaSelectoresCsses().get(NumberUtils.INTEGER_ZERO);
			
			Map<String, String> mapSelectores = new LinkedHashMap<>(NumberUtils.INTEGER_ONE);
			mapSelectores.put(SCRAP_PATTERN, tbSiaSelectoresCsses.getScrapPattern());
			mapSelectores.put(SCRAP_NO_PATTERN, tbSiaSelectoresCsses.getScrapNoPattern());
			mapSelectores.put(SEL_IMAGE, tbSiaSelectoresCsses.getSelImage());
			mapSelectores.put(SEL_LINK_PROD, tbSiaSelectoresCsses.getSelLinkProd());
			mapSelectores.put(SEL_PAGINACION, tbSiaSelectoresCsses.getSelPaginacion());
			mapSelectores.put(SEL_PRECIO, tbSiaSelectoresCsses.getSelPrecio());
			mapSelectores.put(SEL_PRECIO_KILO, tbSiaSelectoresCsses.getSelPreKilo());
			mapSelectores.put(SEL_PRODUCTO, tbSiaSelectoresCsses.getSelProducto());
			mapSelectores.put(BOL_ACTIVO, tbSiaSelectoresCsses.getBolActivo().toString());
			mapSelectores.put(DID, tbSiaSelectoresCsses.getDid().toString());
			mapSelectores.put(FEC_MODIFICACION, tbSiaSelectoresCsses.getFecModificacion().toString());
			urlPDto.setSelectores(selectores);
		}
		
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
		
		tbSiaPUrl.setBolActivo(urlPDto.getBolActivo());
		tbSiaPUrl.setDesUrl(urlPDto.getDesUrl());
		tbSiaPUrl.setDid(urlPDto.getDid());
		tbSiaPUrl.setNomUrl(urlPDto.getNomUrl());
		tbSiaPUrl.setBolStatus(urlPDto.getBolStatus());
		tbSiaPUrl.setBolLogin(urlPDto.getBolLogin());	
		tbSiaPUrl.setTbSiaSelectoresCsses(new ArrayList<>());
		tbSiaPUrl.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaPUrl.getTbSiaEmpresa().setDid(urlPDto.getDid());
		tbSiaPUrl.getTbSiaEmpresa().setNomEmpresa(urlPDto.getNomEmpresa());
		
		tselectores.setDid(Integer.parseInt(urlPDto.getSelectores().get(DID)));
		tselectores.setScrapPattern(urlPDto.getSelectores().get(SCRAP_PATTERN));
		tselectores.setScrapNoPattern(urlPDto.getSelectores().get(SCRAP_NO_PATTERN));
		tselectores.setSelImage(urlPDto.getSelectores().get(SEL_IMAGE));
		tselectores.setSelLinkProd(urlPDto.getSelectores().get(SEL_LINK_PROD));
		tselectores.setSelPaginacion(urlPDto.getSelectores().get(SEL_PAGINACION));
		tselectores.setSelPrecio(urlPDto.getSelectores().get(SEL_PRECIO));
		tselectores.setSelPreKilo(urlPDto.getSelectores().get(SEL_PRECIO_KILO));
		tselectores.setSelProducto(urlPDto.getSelectores().get(SEL_PRODUCTO));
		tselectores.setBolActivo(Boolean.parseBoolean(urlPDto.getSelectores().get(BOL_ACTIVO)));
		tselectores.setFecModificacion(LocalDate.parse(urlPDto.getSelectores().get(FEC_MODIFICACION)));
		tbSiaPUrl.getTbSiaSelectoresCsses().add(tselectores);
		
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
		
		List<UrlDTO> listDto = new ArrayList<>(NumberUtils.INTEGER_ONE); 
		Map<String, String> selectores = new LinkedHashMap<>(NumberUtils.INTEGER_ONE);
		
		for (TbSiaUrl tbSiaUrl : lsUrls) {
			urlPDto = new UrlDTO();
			urlPDto.setBolActivo(tbSiaUrl.getBolActivo());
			urlPDto.setDesUrl(tbSiaUrl.getDesUrl());
			urlPDto.setDid(tbSiaUrl.getDid());
			urlPDto.setNomUrl(tbSiaUrl.getNomUrl());
			urlPDto.setBolStatus(tbSiaUrl.getBolStatus());
			urlPDto.setBolLogin(tbSiaUrl.getBolLogin());
			urlPDto.setNomEmpresa(tbSiaUrl.getTbSiaEmpresa().getNomEmpresa());
			urlPDto.setDidEmpresa(tbSiaUrl.getTbSiaEmpresa().getDid());
			
			if(!tbSiaUrl.getTbSiaSelectoresCsses().isEmpty()) {
				TbSiaSelectoresCss tbSiaSelectoresCsses = tbSiaUrl
						.getTbSiaSelectoresCsses().get(NumberUtils.INTEGER_ZERO);
			
				Map<String, String> mapSelectores = new LinkedHashMap<>(NumberUtils.INTEGER_ONE);
				mapSelectores.put(SCRAP_PATTERN, tbSiaSelectoresCsses.getScrapPattern());
				mapSelectores.put(SCRAP_NO_PATTERN, tbSiaSelectoresCsses.getScrapNoPattern());
				mapSelectores.put(SEL_IMAGE, tbSiaSelectoresCsses.getSelImage());
				mapSelectores.put(SEL_LINK_PROD, tbSiaSelectoresCsses.getSelLinkProd());
				mapSelectores.put(SEL_PAGINACION, tbSiaSelectoresCsses.getSelPaginacion());
				mapSelectores.put(SEL_PRECIO, tbSiaSelectoresCsses.getSelPrecio());
				mapSelectores.put(SEL_PRECIO_KILO, tbSiaSelectoresCsses.getSelPreKilo());
				mapSelectores.put(SEL_PRODUCTO, tbSiaSelectoresCsses.getSelProducto());
				mapSelectores.put(BOL_ACTIVO, tbSiaSelectoresCsses.getBolActivo().toString());
				mapSelectores.put(DID, tbSiaSelectoresCsses.getDid().toString());
				mapSelectores.put(FEC_MODIFICACION, tbSiaSelectoresCsses.getFecModificacion().toString());
				urlPDto.setSelectores(selectores);
			}
			
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
		
		if(Objects.isNull(urlList)) {
			return new ArrayList<>();
		}
		
		List<UrlDTO> listUrlDto = null;
		
		if (!urlList.isEmpty()){ 
			listUrlDto = new ArrayList<>(NumberUtils.INTEGER_ONE);
			for (Object[] objects : urlList) {
				urlPDto = new UrlDTO();
				urlPDto.setNomUrl(String.valueOf(objects[0]));
				urlPDto.setDidEmpresa(Integer.parseInt(String.valueOf(objects[1])));
				urlPDto.setDid(Integer.parseInt(String.valueOf(objects[2])));
				urlPDto.setBolActivo(Boolean.parseBoolean(null!=objects[3]?String.valueOf(objects[3]):null));
				urlPDto.setNomEmpresa(String.valueOf(objects[4]));
				urlPDto.setBolStatus(Boolean.parseBoolean(null!=objects[5]?String.valueOf(objects[5]):null));
				urlPDto.setBolLogin(Boolean.parseBoolean(null!=objects[6]?String.valueOf(objects[6]):null));
				urlPDto.setDesUrl(String.valueOf(objects[7]));
				listUrlDto.add(urlPDto);
			}
		}
		return listUrlDto;
	}
}
