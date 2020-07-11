package com.searchitemsapp.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class TbSiaCategoriasEmpresaTest {
	
	@Autowired
	TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;

	@Test
	public void testTbSiaCategoriasEmpresa() {

		tbSiaCategoriasEmpresa.setTbSiaEmpresas(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaEmpresas().add(new TbSiaEmpresa());
		tbSiaCategoriasEmpresa.setTbSiaMarcas(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaMarcas().add(new TbSiaMarcas());
		tbSiaCategoriasEmpresa.setTbSiaNomProductos(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaNomProductos().add(new TbSiaNomProducto());
		tbSiaCategoriasEmpresa.addTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaCategoriasEmpresa.addTbSiaMarca(new TbSiaMarcas());
		tbSiaCategoriasEmpresa.addTbSiaNomProducto(new TbSiaNomProducto());
		tbSiaCategoriasEmpresa.equals(tbSiaCategoriasEmpresa);
		tbSiaCategoriasEmpresa.setBolActivo(true);
		tbSiaCategoriasEmpresa.setDesCatEmpresa("TEST0");
		tbSiaCategoriasEmpresa.setDid(101);
		tbSiaCategoriasEmpresa.setNomCatEmpresa("TEST1");
		
		tbSiaCategoriasEmpresa.getBolActivo();
		tbSiaCategoriasEmpresa.getClass();
		tbSiaCategoriasEmpresa.getDesCatEmpresa();
		tbSiaCategoriasEmpresa.getDid();
		tbSiaCategoriasEmpresa.getNomCatEmpresa();
		tbSiaCategoriasEmpresa.getTbSiaEmpresas();
		tbSiaCategoriasEmpresa.getTbSiaMarcas();
		tbSiaCategoriasEmpresa.getTbSiaNomProductos();
		
		tbSiaCategoriasEmpresa.removeTbSiaEmpresa(tbSiaCategoriasEmpresa.getTbSiaEmpresas().get(0));
		tbSiaCategoriasEmpresa.removeTbSiaMarca(tbSiaCategoriasEmpresa.getTbSiaMarcas().get(0));
		tbSiaCategoriasEmpresa.removeTbSiaNomProducto(tbSiaCategoriasEmpresa.getTbSiaNomProductos().get(0));
	}

}
