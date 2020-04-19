package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class EmpresaParser implements IFParser<EmpresaDTO, TbSiaEmpresa> {
	
	private EmpresaParser() {
		super();
	}
	
	@Override
	public EmpresaDTO toDTO(TbSiaEmpresa tbSiaPEmpresas) {	
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),EmpresaParser.class);
		
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
	
	@Override
	public TbSiaEmpresa toTbSia(EmpresaDTO empresaPDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),EmpresaParser.class);
		
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
	
	@Override
	public List<EmpresaDTO> toListDTO(List<TbSiaEmpresa> lsEmpresas) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),EmpresaParser.class);
		
		List<EmpresaDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
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
	
	@Override
	public List<EmpresaDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),EmpresaParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
