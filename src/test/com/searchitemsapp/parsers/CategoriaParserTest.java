package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.model.TbSiaNomProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class CategoriaParserTest {
	
	 private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LogManager.getRootLogger();
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		TbSiaCategoriasEmpresa tbSiaPCategorias = new TbSiaCategoriasEmpresa();
		//- Se crean las lista-//
		tbSiaPCategorias.setTbSiaEmpresas(new ArrayList<TbSiaEmpresa>());
		tbSiaPCategorias.setTbSiaMarcas(new ArrayList<TbSiaMarcas>());
		tbSiaPCategorias.setTbSiaNomProductos(new ArrayList<TbSiaNomProducto>());
		//- se añaden nuevos objetos-//
		tbSiaPCategorias.addTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaPCategorias.addTbSiaMarca(new TbSiaMarcas());
		tbSiaPCategorias.addTbSiaNomProducto(new TbSiaNomProducto());
		//- se crea el parser y se prueba-//
		CategoriaParser parser = new CategoriaParser();
		CategoriaDTO categoriaPDto = parser.toDTO(tbSiaPCategorias);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresas", 
				tbSiaPCategorias.getTbSiaEmpresas(), 
				categoriaPDto.getTbSiaEmpresas());		
		assertEquals("getTbSiaMarcas", 
				tbSiaPCategorias.getTbSiaMarcas(), 
				categoriaPDto.getTbSiaMarcas());		
		assertEquals("getTbSiaNomProductos", 
				tbSiaPCategorias.getTbSiaNomProductos(), 
				categoriaPDto.getTbSiaNomProductos());
		
		//- Same -//
		assertSame(tbSiaPCategorias.getTbSiaNomProductos(), 
				categoriaPDto.getTbSiaNomProductos());
		assertSame(tbSiaPCategorias.getTbSiaMarcas(), 
				categoriaPDto.getTbSiaMarcas());
		assertSame(tbSiaPCategorias.getTbSiaEmpresas(), 
				categoriaPDto.getTbSiaEmpresas());
		
	}
	
	@Test
	public void toTbSia() {
		
		CategoriaDTO categoriaPDto = new CategoriaDTO();
		//- Se crean las lista-//
		categoriaPDto.setTbSiaEmpresas(new ArrayList<TbSiaEmpresa>());
		categoriaPDto.setTbSiaMarcas(new ArrayList<TbSiaMarcas>());
		categoriaPDto.setTbSiaNomProductos(new ArrayList<TbSiaNomProducto>());
		//- se añaden nuevos objetos-//
		categoriaPDto.getTbSiaEmpresas().add(new TbSiaEmpresa());
		categoriaPDto.getTbSiaMarcas().add(new TbSiaMarcas());
		categoriaPDto.getTbSiaNomProductos().add(new TbSiaNomProducto());
		//- se crea el parser y se prueba-//
		CategoriaParser parser = new CategoriaParser();
		TbSiaCategoriasEmpresa tbSiaPCategorias = parser.toTbSia(categoriaPDto);
		
		assertEquals("getTbSiaEmpresas", 
			categoriaPDto.getTbSiaEmpresas(), 
				tbSiaPCategorias.getTbSiaEmpresas());
		assertEquals("getTbSiaMarcas", 
				categoriaPDto.getTbSiaMarcas(), 
				tbSiaPCategorias.getTbSiaMarcas());		
		assertEquals("getTbSiaNomProductos", 
				categoriaPDto.getTbSiaNomProductos(), 
				tbSiaPCategorias.getTbSiaNomProductos());
		
		//- Same -//
		assertSame(categoriaPDto.getTbSiaNomProductos(), 
				tbSiaPCategorias.getTbSiaNomProductos());
		assertSame(categoriaPDto.getTbSiaMarcas(), 
				tbSiaPCategorias.getTbSiaMarcas());
		assertSame(categoriaPDto.getTbSiaEmpresas(), 
				tbSiaPCategorias.getTbSiaEmpresas());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaCategoriasEmpresa> lsCategorias = new ArrayList<TbSiaCategoriasEmpresa>();
		lsCategorias.add(new TbSiaCategoriasEmpresa());
		lsCategorias.add(new TbSiaCategoriasEmpresa());
		
		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();
		CategoriaParser parser = new CategoriaParser();
		listCategoriaDTO = parser.toListDTO(lsCategorias);
		
		assertEquals("size", 
				lsCategorias.size(), listCategoriaDTO.size());
		
		assertSame(lsCategorias.get(0).getTbSiaEmpresas(), 
				listCategoriaDTO.get(0).getTbSiaEmpresas());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsCategorias = new ArrayList<Object[]>();
		lsCategorias.add(new Object[5]);
		lsCategorias.add(new Object[4]);
		
		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();
		CategoriaParser parser = new CategoriaParser();
		listCategoriaDTO = parser.toListODTO(lsCategorias);
			
		assertNotEquals(lsCategorias, listCategoriaDTO);
	}
}
