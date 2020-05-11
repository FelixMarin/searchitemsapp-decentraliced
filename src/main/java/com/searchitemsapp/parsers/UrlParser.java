package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaUrl;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

@SuppressWarnings("unchecked")
public class UrlParser implements IFParser<UrlDTO, TbSiaUrl> {
	
	public UrlParser() {
		super();
	}
	
	public UrlDTO toDTO(TbSiaUrl tbSiaPUrl) {	
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),UrlParser.class);
		
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
	
	public TbSiaUrl toTbSia(UrlDTO urlPDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),UrlParser.class);
		
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
	
	public List<UrlDTO> toListDTO(List<TbSiaUrl> lsUrls) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),UrlParser.class);
		
		List<UrlDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
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
	
	public List<UrlDTO> toListODTO(List<Object[]> urlList) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),UrlParser.class);
		
		if(ClaseUtils.isNullObject(urlList)) {
			return (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<UrlDTO> listUrlDto = (List<UrlDTO>) ClaseUtils.NULL_OBJECT;
		UrlDTO urlDto;
		
		if (!urlList.isEmpty()){ 
			listUrlDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
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
