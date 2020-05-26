package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;
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
		
		urlPDto.setBolActivo(tbSiaPUrl.getBolActivo());
		urlPDto.setDesUrl(tbSiaPUrl.getDesUrl());
		urlPDto.setDid(tbSiaPUrl.getDid());
		urlPDto.setNomUrl(tbSiaPUrl.getNomUrl());
		urlPDto.setBolStatus(tbSiaPUrl.getBolStatus());
		urlPDto.setBolLogin(tbSiaPUrl.getBolLogin());
		urlPDto.setTbSiaSelectoresCsses(tbSiaPUrl.getTbSiaSelectoresCsses());
		
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
		tbSiaPUrl.setTbSiaEmpresa(urlPDto.getTbSiaEmpresa());
		tbSiaPUrl.setTbSiaSelectoresCsses(urlPDto.getTbSiaSelectoresCsses());
		
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
		UrlDTO urlPDto;
		
		for (TbSiaUrl urlDto : lsUrls) {
			urlPDto = new UrlDTO();
			urlPDto.setBolActivo(urlDto.getBolActivo());
			urlPDto.setDesUrl(urlDto.getDesUrl());
			urlPDto.setDid(urlDto.getDid());
			urlPDto.setNomUrl(urlDto.getNomUrl());
			urlPDto.setBolStatus(urlDto.getBolStatus());
			urlPDto.setBolLogin(urlDto.getBolLogin());
			urlPDto.setTbSiaEmpresa(urlDto.getTbSiaEmpresa());
			urlPDto.setTbSiaSelectoresCsses(urlDto.getTbSiaSelectoresCsses());
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
				urlDto.getTbSiaEmpresa().setDid(Integer.parseInt(String.valueOf(objects[1])));
				urlDto.setDid(Integer.parseInt(String.valueOf(objects[2])));
				urlDto.setBolActivo(Boolean.parseBoolean(null!=objects[3]?String.valueOf(objects[3]):null));
				urlDto.getTbSiaEmpresa().setNomEmpresa(String.valueOf(objects[4]));
				urlDto.setBolStatus(Boolean.parseBoolean(null!=objects[5]?String.valueOf(objects[5]):null));
				urlDto.setBolLogin(Boolean.parseBoolean(null!=objects[6]?String.valueOf(objects[6]):null));
				urlDto.setDesUrl(String.valueOf(objects[7]));
				listUrlDto.add(urlDto);
			}
		}
		return listUrlDto;
	}
}
