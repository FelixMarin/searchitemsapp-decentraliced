package com.searchitemsapp.parsers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		
		List<LinkedHashMap<String, String>> selectores = new ArrayList<>(10);
		List<LinkedHashMap<Integer, String>> urls = new ArrayList<>(10);
		
		empresaPDto.setBolActivo(tbSiaPEmpresas.getBolActivo());
		empresaPDto.setDesEmpresa(tbSiaPEmpresas.getDesEmpresa());
		empresaPDto.setDid(tbSiaPEmpresas.getDid());
		empresaPDto.setNomEmpresa(tbSiaPEmpresas.getNomEmpresa());
		empresaPDto.setBolDynScrap(tbSiaPEmpresas.getBolDynScrap());
		
		empresaPDto.setDidPais(tbSiaPEmpresas.getTbSiaPais().getDid());
		empresaPDto.setNomPais(tbSiaPEmpresas.getTbSiaPais().getNomPais());
		empresaPDto.setDidCatEmpresa(tbSiaPEmpresas.getTbSiaCategoriasEmpresa().getDid());
		empresaPDto.setNomCatEmpresa(tbSiaPEmpresas.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
		
		for (TbSiaSelectoresCss tbSiaSelectoresCsses : tbSiaPEmpresas.getTbSiaSelectoresCsses()) {
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
		empresaPDto.setSelectores(selectores);
		
		for (TbSiaUrl tbSiaUrl : tbSiaPEmpresas.getTbSiaUrls()) {
			LinkedHashMap<Integer, String> mapUrls = new LinkedHashMap<Integer, String>(10);
			mapUrls.put(tbSiaUrl.getDid(), tbSiaUrl.getNomUrl());
			urls.add(mapUrls);
		}
		empresaPDto.setUrls(urls);
		
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
		
		for (LinkedHashMap<Integer,String> map : empresaPDto.getUrls()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaUrl tburl = new TbSiaUrl();
				tburl.setDid((int) e.getKey());
				tburl.setNomUrl((String) e.getValue());
				tbSiaPEmpresas.getTbSiaUrls().add(tburl);
			}
		}
		
		for (LinkedHashMap<String,String> map : empresaPDto.getSelectores()) {
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
		}
		
		
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
		
		List<LinkedHashMap<String, String>> selectores = new ArrayList<>(10);
		List<LinkedHashMap<Integer, String>> urls = new ArrayList<>(10);
		List<EmpresaDTO> listDto = new ArrayList<>(10); 
		EmpresaDTO empresaPDto;
		
		for (TbSiaEmpresa empresaDto : lsEmpresas) {
			empresaPDto = new EmpresaDTO();
			empresaPDto.setBolActivo(empresaDto.getBolActivo());
			empresaPDto.setDesEmpresa(empresaDto.getDesEmpresa());
			empresaPDto.setDid(empresaDto.getDid());
			empresaPDto.setNomEmpresa(empresaDto.getNomEmpresa());
			empresaPDto.setBolDynScrap(empresaDto.getBolDynScrap());

			empresaPDto.setDidPais(empresaDto.getTbSiaPais().getDid());
			empresaPDto.setNomPais(empresaDto.getTbSiaPais().getNomPais());
			empresaPDto.setDidCatEmpresa(empresaDto.getTbSiaCategoriasEmpresa().getDid());
			empresaPDto.setNomCatEmpresa(empresaDto.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
			
			for (TbSiaSelectoresCss tbSiaSelectoresCsses : empresaDto.getTbSiaSelectoresCsses()) {
				LinkedHashMap<String, String> mapSelectores = new LinkedHashMap<String, String>(10);
				mapSelectores.put("SCRAP_PATTERN", tbSiaSelectoresCsses.getScrapPattern());
				mapSelectores.put("SCRAP_NO_PATTERN", tbSiaSelectoresCsses.getScrapNoPattern());
				mapSelectores.put("SEL_IMAGE", tbSiaSelectoresCsses.getSelImage());
				mapSelectores.put("SEL_LINK_PROD", tbSiaSelectoresCsses.getSelLinkProd());
				mapSelectores.put("SEL_PAGINACION", tbSiaSelectoresCsses.getSelPaginacion());
				mapSelectores.put("SEL_PRECIO", tbSiaSelectoresCsses.getSelPrecio());
				mapSelectores.put("SEL_PRECIO_KILO", tbSiaSelectoresCsses.getSelPreKilo());
				mapSelectores.put("SEL_PRODUCTO", tbSiaSelectoresCsses.getSelProducto());
				mapSelectores.put("BOOL_ACTIVO", tbSiaSelectoresCsses.getBolActivo().toString());
				mapSelectores.put("DID", tbSiaSelectoresCsses.getDid().toString());
				mapSelectores.put("FEC_MODIFICACION", tbSiaSelectoresCsses.getFecModificacion().toString());
				selectores.add(mapSelectores);
			}
			empresaPDto.setSelectores(selectores);
			
			for (TbSiaUrl tbSiaUrl : empresaDto.getTbSiaUrls()) {
				LinkedHashMap<Integer, String> mapUrls = new LinkedHashMap<Integer, String>(10);
				mapUrls.put(tbSiaUrl.getDid(), tbSiaUrl.getNomUrl());
				urls.add(mapUrls);
			}
			empresaPDto.setUrls(urls);
			
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
		
		return new ArrayList<>(10);
	}
}
