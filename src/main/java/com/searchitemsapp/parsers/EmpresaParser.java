package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaEmpresa;



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
		empresaPDto.setTbSiaCategoriasEmpresa(tbSiaPEmpresas.getTbSiaCategoriasEmpresa());
		empresaPDto.setTbSiaPais(tbSiaPEmpresas.getTbSiaPais());
		empresaPDto.setTbSiaUrls(tbSiaPEmpresas.getTbSiaUrls());
		empresaPDto.setBolDynScrap(tbSiaPEmpresas.getBolDynScrap());
		empresaPDto.setTbSiaSelectoresCsses(tbSiaPEmpresas.getTbSiaSelectoresCsses());
		
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
		tbSiaPEmpresas.setTbSiaCategoriasEmpresa(empresaPDto.getTbSiaCategoriasEmpresa());
		tbSiaPEmpresas.setTbSiaPais(empresaPDto.getTbSiaPais());
		tbSiaPEmpresas.setTbSiaUrls(empresaPDto.getTbSiaUrls());
		tbSiaPEmpresas.setBolDynScrap(empresaPDto.getBolDynScrap());
		tbSiaPEmpresas.setTbSiaSelectoresCsses(empresaPDto.getTbSiaSelectoresCsses());
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
		
		List<EmpresaDTO> listDto = new ArrayList<>(10); 
		EmpresaDTO empresaPDto;
		
		for (TbSiaEmpresa empresaDto : lsEmpresas) {
			empresaPDto = new EmpresaDTO();
			empresaPDto.setBolActivo(empresaDto.getBolActivo());
			empresaPDto.setDesEmpresa(empresaDto.getDesEmpresa());
			empresaPDto.setDid(empresaDto.getDid());
			empresaPDto.setNomEmpresa(empresaDto.getNomEmpresa());
			empresaPDto.setTbSiaCategoriasEmpresa(empresaDto.getTbSiaCategoriasEmpresa());
			empresaPDto.setTbSiaPais(empresaDto.getTbSiaPais());
			empresaPDto.setTbSiaUrls(empresaDto.getTbSiaUrls());
			empresaPDto.setBolDynScrap(empresaDto.getBolDynScrap());
			empresaPDto.setTbSiaSelectoresCsses(empresaDto.getTbSiaSelectoresCsses());
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
