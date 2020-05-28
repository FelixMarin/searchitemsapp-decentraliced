package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.model.TbSiaPais;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class PaisParser implements IFParser<PaisDTO, TbSiaPais> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaisParser.class);  
	
	/*
	 * Constructor
	 */
	public PaisParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaPais
	 * @return PaisDTO
	 */
	public PaisDTO toDTO(TbSiaPais tbSiaPPais) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		PaisDTO paisPDto = new PaisDTO();
		List<LinkedHashMap<Integer,String>> empresas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> marcas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> productos = new ArrayList<>(10);
		
		paisPDto.setBolActivo(tbSiaPPais.getBolActivo());
		paisPDto.setDesPais(tbSiaPPais.getDesPais());
		paisPDto.setDid(tbSiaPPais.getDid());
		paisPDto.setNomPais(tbSiaPPais.getNomPais());
		
		for (TbSiaEmpresa tbSiaEmpresa : tbSiaPPais.getTbSiaEmpresas()) {
			LinkedHashMap<Integer, String> mapEmpresa = new LinkedHashMap<Integer, String>(10);
			mapEmpresa.put(tbSiaEmpresa.getDid(), tbSiaEmpresa.getNomEmpresa());
			empresas.add(mapEmpresa);
		}
		paisPDto.setEmpresas(empresas);
		
		for (TbSiaMarcas tbSiaMarcas : tbSiaPPais.getTbSiaMarcas()) {
			LinkedHashMap<Integer, String> mapMarcas = new LinkedHashMap<Integer, String>(10);
			mapMarcas.put(tbSiaMarcas.getDid(), tbSiaMarcas.getNomMarca());
			marcas.add(mapMarcas);
		}
		paisPDto.setMarcas(marcas);
		
		for (TbSiaNomProducto tbSiaNomProductos : tbSiaPPais.getTbSiaNomProductos()) {
			LinkedHashMap<Integer, String> mapProductos = new LinkedHashMap<Integer, String>(10);
			mapProductos.put(tbSiaNomProductos.getDid(), tbSiaNomProductos.getNomProducto());
			productos.add(mapProductos);
		}
		paisPDto.setProductos(productos);
		
		return paisPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param PaisDTO
	 * @return TbSiaPais
	 */
	public TbSiaPais toTbSia(PaisDTO paisPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaPais tbSiaPPais = new TbSiaPais();
		
		tbSiaPPais.setBolActivo(paisPDto.getBolActivo());
		tbSiaPPais.setDesPais(paisPDto.getDesPais());
		tbSiaPPais.setDid(paisPDto.getDid());
		tbSiaPPais.setNomPais(paisPDto.getNomPais());

		for (LinkedHashMap<Integer,String> map : paisPDto.getEmpresas()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaEmpresa tbempresa = new TbSiaEmpresa();
				tbempresa.setDid((int) e.getKey());
				tbempresa.setNomEmpresa((String) e.getValue());
				tbSiaPPais.getTbSiaEmpresas().add(tbempresa);
			}
		}
		
		for (LinkedHashMap<Integer,String> map : paisPDto.getMarcas()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaMarcas tbmarcas = new TbSiaMarcas();
				tbmarcas.setDid((int) e.getKey());
				tbmarcas.setNomMarca((String) e.getValue());
				tbSiaPPais.getTbSiaMarcas().add(tbmarcas);
			}
		}
		
		for (LinkedHashMap<Integer,String> map : paisPDto.getProductos()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaNomProducto tbproductos = new TbSiaNomProducto();
				tbproductos.setDid((int) e.getKey());
				tbproductos.setNomProducto((String) e.getValue());
				tbSiaPPais.getTbSiaNomProductos().add(tbproductos);
			}
		}
		
		return tbSiaPPais;
	}

	/**
	 * Método no implementado.
	 */
	@Override
	public List<PaisDTO> toListDTO(List<TbSiaPais> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<PaisDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
}
