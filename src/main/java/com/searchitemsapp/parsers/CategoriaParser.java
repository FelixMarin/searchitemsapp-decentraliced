package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
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
		
		categoriaPDto.setBolActivo(tbSiaPCategorias.getBolActivo());
		categoriaPDto.setDesCatEmpresa(tbSiaPCategorias.getDesCatEmpresa());
		categoriaPDto.setDid(tbSiaPCategorias.getDid());
		categoriaPDto.setNomCatEmpresa(tbSiaPCategorias.getNomCatEmpresa());
		
		TbSiaEmpresa tbSiaEmpresa = tbSiaPCategorias.getTbSiaEmpresas().get(NumberUtils.INTEGER_ZERO);
		LinkedHashMap<Integer, String> mapEmpresa = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
		mapEmpresa.put(tbSiaEmpresa.getDid(), tbSiaEmpresa.getNomEmpresa());
		categoriaPDto.setEmpresas(mapEmpresa);
		
		TbSiaMarcas tbSiaMarcas = tbSiaPCategorias.getTbSiaMarcas().get(NumberUtils.INTEGER_ZERO);
		LinkedHashMap<Integer, String> mapMarcas = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
		mapMarcas.put(tbSiaMarcas.getDid(), tbSiaMarcas.getNomMarca());
		categoriaPDto.setMarcas(mapMarcas);
		
		
		TbSiaNomProducto tbSiaNomProductos = tbSiaPCategorias.getTbSiaNomProductos().get(NumberUtils.INTEGER_ZERO);
		LinkedHashMap<Integer, String> mapProductos = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
		mapProductos.put(tbSiaNomProductos.getDid(), tbSiaNomProductos.getNomProducto());
		categoriaPDto.setProductos(mapProductos);
		
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
		
		for (Map.Entry<Integer,String> e  : categoriaPDto.getEmpresas().entrySet()) {
			TbSiaEmpresa tbempresa = new TbSiaEmpresa();
			tbempresa.setDid((int) e.getKey());
			tbempresa.setNomEmpresa((String) e.getValue());
			tbSiaPCategorias.getTbSiaEmpresas().add(tbempresa);
		}
		
		for (Map.Entry<Integer,String> e  : categoriaPDto.getMarcas().entrySet()) {
			TbSiaMarcas tbmarcas = new TbSiaMarcas();
			tbmarcas.setDid((int) e.getKey());
			tbmarcas.setNomMarca((String) e.getValue());
			tbSiaPCategorias.getTbSiaMarcas().add(tbmarcas);
		}
		
		for (Map.Entry<Integer,String> e  : categoriaPDto.getProductos().entrySet()) {
			TbSiaNomProducto tbproductos = new TbSiaNomProducto();
			tbproductos.setDid((int) e.getKey());
			tbproductos.setNomProducto((String) e.getValue());
			tbSiaPCategorias.getTbSiaNomProductos().add(tbproductos);
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
		
		List<CategoriaDTO> listDto = new ArrayList<>(NumberUtils.INTEGER_ONE); 
		CategoriaDTO categoriaPDto;
		
		for (TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa : lsCategorias) {
			categoriaPDto = new CategoriaDTO();
			categoriaPDto.setBolActivo(tbSiaCategoriasEmpresa.getBolActivo());
			categoriaPDto.setDesCatEmpresa(tbSiaCategoriasEmpresa.getDesCatEmpresa());
			categoriaPDto.setDid(tbSiaCategoriasEmpresa.getDid());
			categoriaPDto.setNomCatEmpresa(tbSiaCategoriasEmpresa.getNomCatEmpresa());
			
			TbSiaEmpresa tbSiaEmpresa = tbSiaCategoriasEmpresa.getTbSiaEmpresas().get(NumberUtils.INTEGER_ZERO);
			LinkedHashMap<Integer, String> mapEmpresa = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
			mapEmpresa.put(tbSiaEmpresa.getDid(), tbSiaEmpresa.getNomEmpresa());
			categoriaPDto.setEmpresas(mapEmpresa);
			
			TbSiaMarcas tbSiaMarcas = tbSiaCategoriasEmpresa.getTbSiaMarcas().get(NumberUtils.INTEGER_ZERO);
			LinkedHashMap<Integer, String> mapMarcas = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
			mapMarcas.put(tbSiaMarcas.getDid(), tbSiaMarcas.getNomMarca());
			categoriaPDto.setMarcas(mapMarcas);
			
			TbSiaNomProducto tbSiaNomProductos = tbSiaCategoriasEmpresa.getTbSiaNomProductos().get(NumberUtils.INTEGER_ZERO);
			LinkedHashMap<Integer, String> mapProductos = new LinkedHashMap<Integer, String>(NumberUtils.INTEGER_ONE);
			mapProductos.put(tbSiaNomProductos.getDid(), tbSiaNomProductos.getNomProducto());
			categoriaPDto.setProductos(mapProductos);
			
			listDto.add(categoriaPDto);
		}
		
		return listDto;
	}

	/**
	 * Método no implementado.
	 */
	@Override
	public List<CategoriaDTO> toListODTO(List<Object[]> objeto) {
		return new ArrayList<>(NumberUtils.INTEGER_ONE);
	}
}
