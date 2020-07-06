package com.searchitemsapp.dto;

import java.util.LinkedHashMap;

import org.junit.Test;

public class CategoriaDTOTest {

	@Test
	public void testCategoriaDTO() {
		CategoriaDTO cdto = new CategoriaDTO();
		CategoriaDTO cdto2 = new CategoriaDTO();
		
		cdto.setBolActivo(true);
		cdto.setDesCatEmpresa("catEmpresa");
		cdto.setDid(101);
		cdto.setEmpresas(new LinkedHashMap<>());
		cdto.setProductos(new LinkedHashMap<>());
		cdto.setMarcas(new LinkedHashMap<>());
		cdto.setNomCatEmpresa("nombre");
		
		cdto.getBolActivo();
		cdto.getDesCatEmpresa();
		cdto.getDid();
		cdto.getEmpresas();
		cdto.getMarcas();
		cdto.getNomCatEmpresa();
		cdto.getProductos();
		
		cdto.hashCode();
		cdto2.hashCode();
		
		cdto.toString();
	}

}
