package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.model.TbSiaNomProducto;

/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class CategoriaParser implements IFParser<CategoriaDTO, TbSiaCategoriasEmpresa>  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaParser.class);  
	
	/*
	 *  Constructor
	 */
	public CategoriaParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaCategoriasEmpresa
	 * @return CategoriaDTO
	 */
	@Override
	public CategoriaDTO toDTO(TbSiaCategoriasEmpresa tbSiaPCategorias) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		CategoriaDTO categoriaPDto = new CategoriaDTO();
		List<LinkedHashMap<Integer,String>> empresas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> marcas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> productos = new ArrayList<>(10);
		
		categoriaPDto.setBolActivo(tbSiaPCategorias.getBolActivo());
		categoriaPDto.setDesCatEmpresa(tbSiaPCategorias.getDesCatEmpresa());
		categoriaPDto.setDid(tbSiaPCategorias.getDid());
		categoriaPDto.setNomCatEmpresa(tbSiaPCategorias.getNomCatEmpresa());
		
		for (TbSiaEmpresa tbSiaEmpresa : tbSiaPCategorias.getTbSiaEmpresas()) {
			LinkedHashMap<Integer, String> mapEmpresa = new LinkedHashMap<Integer, String>(10);
			mapEmpresa.put(tbSiaEmpresa.getDid(), tbSiaEmpresa.getNomEmpresa());
			empresas.add(mapEmpresa);
		}
		categoriaPDto.setEmpresas(empresas);
		
		for (TbSiaMarcas tbSiaMarcas : tbSiaPCategorias.getTbSiaMarcas()) {
			LinkedHashMap<Integer, String> mapMarcas = new LinkedHashMap<Integer, String>(10);
			mapMarcas.put(tbSiaMarcas.getDid(), tbSiaMarcas.getNomMarca());
			marcas.add(mapMarcas);
		}
		categoriaPDto.setMarcas(marcas);
		
		for (TbSiaNomProducto tbSiaNomProductos : tbSiaPCategorias.getTbSiaNomProductos()) {
			LinkedHashMap<Integer, String> mapProductos = new LinkedHashMap<Integer, String>(10);
			mapProductos.put(tbSiaNomProductos.getDid(), tbSiaNomProductos.getNomProducto());
			productos.add(mapProductos);
		}
		categoriaPDto.setProductos(productos);
		
		return categoriaPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param CategoriaDTO
	 * @return TbSiaCategoriasEmpresa
	 */
	@Override
	public TbSiaCategoriasEmpresa toTbSia(CategoriaDTO categoriaPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaCategoriasEmpresa tbSiaPCategorias = new TbSiaCategoriasEmpresa();
		
		tbSiaPCategorias.setBolActivo(categoriaPDto.getBolActivo());
		tbSiaPCategorias.setDesCatEmpresa(categoriaPDto.getDesCatEmpresa());
		tbSiaPCategorias.setDid(categoriaPDto.getDid());
		tbSiaPCategorias.setNomCatEmpresa(categoriaPDto.getNomCatEmpresa());
		tbSiaPCategorias.setTbSiaEmpresas(new ArrayList<TbSiaEmpresa>());
		tbSiaPCategorias.setTbSiaMarcas(new ArrayList<TbSiaMarcas>());
		tbSiaPCategorias.setTbSiaNomProductos(new ArrayList<TbSiaNomProducto>());
		
		for (LinkedHashMap<Integer,String> map : categoriaPDto.getEmpresas()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaEmpresa tbempresa = new TbSiaEmpresa();
				tbempresa.setDid((int) e.getKey());
				tbempresa.setNomEmpresa((String) e.getValue());
				tbSiaPCategorias.getTbSiaEmpresas().add(tbempresa);
			}
		}
		
		for (LinkedHashMap<Integer,String> map : categoriaPDto.getMarcas()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaMarcas tbmarcas = new TbSiaMarcas();
				tbmarcas.setDid((int) e.getKey());
				tbmarcas.setNomMarca((String) e.getValue());
				tbSiaPCategorias.getTbSiaMarcas().add(tbmarcas);
			}
		}
		
		for (LinkedHashMap<Integer,String> map : categoriaPDto.getProductos()) {
			for (Map.Entry<Integer,String> e  : map.entrySet()) {
				TbSiaNomProducto tbproductos = new TbSiaNomProducto();
				tbproductos.setDid((int) e.getKey());
				tbproductos.setNomProducto((String) e.getValue());
				tbSiaPCategorias.getTbSiaNomProductos().add(tbproductos);
			}
		}
		
		return tbSiaPCategorias;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaCategoriasEmpresa>
	 * @return List<CategoriaDTO>
	 */
	@Override
	public List<CategoriaDTO> toListDTO(List<TbSiaCategoriasEmpresa> lsCategorias) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<CategoriaDTO> listDto = new ArrayList<>(10); 
		List<LinkedHashMap<Integer,String>> empresas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> marcas = new ArrayList<>(10);
		List<LinkedHashMap<Integer,String>> productos = new ArrayList<>(10);
		CategoriaDTO categoriaPDto;
		
		for (TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa : lsCategorias) {
			categoriaPDto = new CategoriaDTO();
			categoriaPDto.setBolActivo(tbSiaCategoriasEmpresa.getBolActivo());
			categoriaPDto.setDesCatEmpresa(tbSiaCategoriasEmpresa.getDesCatEmpresa());
			categoriaPDto.setDid(tbSiaCategoriasEmpresa.getDid());
			categoriaPDto.setNomCatEmpresa(tbSiaCategoriasEmpresa.getNomCatEmpresa());
			
			for (TbSiaEmpresa tbSiaEmpresa : tbSiaCategoriasEmpresa.getTbSiaEmpresas()) {
				LinkedHashMap<Integer, String> mapEmpresa = new LinkedHashMap<Integer, String>(10);
				mapEmpresa.put(tbSiaEmpresa.getDid(), tbSiaEmpresa.getNomEmpresa());
				empresas.add(mapEmpresa);
			}
			categoriaPDto.setEmpresas(empresas);
			
			for (TbSiaMarcas tbSiaMarcas : tbSiaCategoriasEmpresa.getTbSiaMarcas()) {
				LinkedHashMap<Integer, String> mapMarcas = new LinkedHashMap<Integer, String>(10);
				mapMarcas.put(tbSiaMarcas.getDid(), tbSiaMarcas.getNomMarca());
				marcas.add(mapMarcas);
			}
			categoriaPDto.setMarcas(marcas);
			
			for (TbSiaNomProducto tbSiaNomProductos : tbSiaCategoriasEmpresa.getTbSiaNomProductos()) {
				LinkedHashMap<Integer, String> mapProductos = new LinkedHashMap<Integer, String>(10);
				mapProductos.put(tbSiaNomProductos.getDid(), tbSiaNomProductos.getNomProducto());
				productos.add(mapProductos);
			}
			categoriaPDto.setProductos(productos);
			
			listDto.add(categoriaPDto);
		}
		
		return listDto;
	}

	/**
	 * Método no implementado.
	 */
	@Override
	public List<CategoriaDTO> toListODTO(List<Object[]> objeto) {
		return new ArrayList<>(10);
	}
}
