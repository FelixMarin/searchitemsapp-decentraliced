package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaMarcas;
import com.searchitemsapp.entities.TbSiaNomProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class CategoriaParserTest {
	
	 private static Logger LOGGER = null;
		
		@Autowired
		IFParser<CategoriaDTO, TbSiaCategoriasEmpresa> parser;
		
		@Autowired
		TbSiaCategoriasEmpresa tbSiaPCategorias;
		
		@Autowired
		TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(CategoriaParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaPCategorias.setDid(101);
		CategoriaDTO categoriaPDto = parser.toDTO(tbSiaPCategorias);
		
		//- Equals -//
		assertEquals("tbSiaPCategorias", 
				tbSiaPCategorias.getDid(), 
				categoriaPDto.getDid());
		
		//- Same -//
		assertSame(tbSiaPCategorias.getDid(), 
				categoriaPDto.getDid());
	}
	
	@Test
	public void toTbSia() {
		
	
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaCategoriasEmpresa> lsCategorias = Lists.newArrayList();
		tbSiaCategoriasEmpresa.setTbSiaEmpresas(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaEmpresas().add(new TbSiaEmpresa());
		tbSiaCategoriasEmpresa.setTbSiaMarcas(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaMarcas().add(new TbSiaMarcas());
		tbSiaCategoriasEmpresa.setTbSiaNomProductos(Lists.newArrayList());
		tbSiaCategoriasEmpresa.getTbSiaNomProductos().add(new TbSiaNomProducto());
		lsCategorias.add(tbSiaCategoriasEmpresa);
		
		List<CategoriaDTO> listCategoriaDTO = parser.toListDTO(lsCategorias);
		
		assertEquals("size", 
				lsCategorias.size(), listCategoriaDTO.size());
		
		assertSame(lsCategorias.size(), 
				listCategoriaDTO.size());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsCategorias = Lists.newArrayList();
		lsCategorias.add(new Object[0]);
		
		List<CategoriaDTO> listCategoriaDTO = Lists.newArrayList();
		listCategoriaDTO = parser.toListODTO(lsCategorias);
			
		assertNotEquals(lsCategorias.size(), listCategoriaDTO.size());
	}
}
