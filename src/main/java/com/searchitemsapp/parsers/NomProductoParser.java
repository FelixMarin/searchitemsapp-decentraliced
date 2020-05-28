package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
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
public class NomProductoParser implements IFParser<NomProductoDTO, TbSiaNomProducto> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NomProductoParser.class);  
	
	/*
	 * Constructor
	 */
	public NomProductoParser() {
		super();
	}

	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaNomProducto
	 * @return NomProductoDTO
	 */
	public NomProductoDTO toDTO(TbSiaNomProducto tbSiaNomProducto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		NomProductoDTO nomProductoDTO = new NomProductoDTO();
		
		nomProductoDTO.setDid(tbSiaNomProducto.getDid());
		nomProductoDTO.setNomProducto(tbSiaNomProducto.getNomProducto());		
		nomProductoDTO.setDidCatEmpresas(tbSiaNomProducto.getTbSiaCategoriasEmpresa().getDid());
		nomProductoDTO.setNomCatEmpresas(tbSiaNomProducto.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
		nomProductoDTO.setDidPais(tbSiaNomProducto.getTbSiaPais().getDid());
		nomProductoDTO.setNomPais(tbSiaNomProducto.getTbSiaPais().getNomPais());
		
		return nomProductoDTO;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param NomProductoDTO
	 * @return TbSiaNomProducto
	 */
	public TbSiaNomProducto toTbSia(NomProductoDTO nomProductoDTO) { 
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaNomProducto tbSiaNomProducto = new TbSiaNomProducto();
		
		tbSiaNomProducto.setDid(nomProductoDTO.getDid());
		tbSiaNomProducto.setNomProducto(nomProductoDTO.getNomProducto());
		tbSiaNomProducto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(new TbSiaPais());		
		tbSiaNomProducto.getTbSiaCategoriasEmpresa().setDid(nomProductoDTO.getDid());
		tbSiaNomProducto.getTbSiaCategoriasEmpresa().setNomCatEmpresa(nomProductoDTO.getNomCatEmpresas());
		tbSiaNomProducto.getTbSiaPais().setDid(nomProductoDTO.getDid());
		tbSiaNomProducto.getTbSiaPais().setNomPais(nomProductoDTO.getNomPais());
		
		return tbSiaNomProducto;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaNomProducto>
	 * @return List<NomProductoDTO>
	 */
	public List<NomProductoDTO> toListDTO(List<TbSiaNomProducto> lsTbSiaNomProducto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<NomProductoDTO> listDto = new ArrayList<>(10); 
		NomProductoDTO nomProductoDTO;		
		
		for (TbSiaNomProducto tbSiaNomProducto : lsTbSiaNomProducto) {
			nomProductoDTO = new NomProductoDTO();
			nomProductoDTO.setDid(tbSiaNomProducto.getDid());
			nomProductoDTO.setNomProducto(tbSiaNomProducto.getNomProducto());
			nomProductoDTO.setDidCatEmpresas(tbSiaNomProducto.getTbSiaCategoriasEmpresa().getDid());
			nomProductoDTO.setNomCatEmpresas(tbSiaNomProducto.getTbSiaCategoriasEmpresa().getNomCatEmpresa());
			nomProductoDTO.setDidPais(tbSiaNomProducto.getTbSiaPais().getDid());
			nomProductoDTO.setNomPais(tbSiaNomProducto.getTbSiaPais().getNomPais());
			listDto.add(nomProductoDTO);
		}
		
		return listDto;
		
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<NomProductoDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(10);
	}
}
