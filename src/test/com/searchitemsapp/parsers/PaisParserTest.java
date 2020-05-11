package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.model.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class PaisParserTest {
	
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

		TbSiaPais tbSiaPais = new TbSiaPais();
		
		tbSiaPais.setTbSiaEmpresas(new ArrayList<TbSiaEmpresa>());
		tbSiaPais.setTbSiaMarcas(new ArrayList<TbSiaMarcas>());
		tbSiaPais.setTbSiaNomProductos(new ArrayList<TbSiaNomProducto>());
		
		tbSiaPais.addTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaPais.addTbSiaMarca(new TbSiaMarcas());
		tbSiaPais.addTbSiaNomProducto(new TbSiaNomProducto());
		
		PaisParser parser = new PaisParser();
		PaisDTO nomProductoDto = parser.toDTO(tbSiaPais);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresas", 
				tbSiaPais.getTbSiaEmpresas(), 
				nomProductoDto.getTbSiaEmpresas());		
		assertEquals("getTbSiaMarcas", 
				tbSiaPais.getTbSiaMarcas(), 
				nomProductoDto.getTbSiaMarcas());
		assertEquals("getetTbSiaNomProductos", 
				tbSiaPais.getTbSiaNomProductos(), 
				nomProductoDto.getTbSiaNomProductos());
		
		//- Same -//
		assertSame(tbSiaPais.getTbSiaMarcas(), 
				nomProductoDto.getTbSiaMarcas());
		assertSame(tbSiaPais.getTbSiaEmpresas(), 
				nomProductoDto.getTbSiaEmpresas());
		assertSame(tbSiaPais.getTbSiaNomProductos(), 
				nomProductoDto.getTbSiaNomProductos());
		
	}
	
	@Test
	public void toTbSia() {
		
		PaisDTO nomProductoDto = new PaisDTO();
		
		nomProductoDto.setTbSiaEmpresas(new ArrayList<TbSiaEmpresa>());
		nomProductoDto.setTbSiaMarcas(new ArrayList<TbSiaMarcas>());
		nomProductoDto.setTbSiaNomProductos(new ArrayList<TbSiaNomProducto>());
		
		nomProductoDto.getTbSiaEmpresas().add(new TbSiaEmpresa());
		nomProductoDto.getTbSiaMarcas().add(new TbSiaMarcas());
		nomProductoDto.getTbSiaNomProductos().add(new TbSiaNomProducto());
		
		PaisParser parser = new PaisParser();
		TbSiaPais tbSiaPais = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresas", 
			nomProductoDto.getTbSiaEmpresas(), 
				tbSiaPais.getTbSiaEmpresas());	
		assertEquals("getTbSiaMarcas", 
				nomProductoDto.getTbSiaMarcas(), 
				tbSiaPais.getTbSiaMarcas());
		assertEquals("getetTbSiaNomProductos", 
				nomProductoDto.getTbSiaNomProductos(), 
				tbSiaPais.getTbSiaNomProductos());
		
		//- Same -//
		assertSame(nomProductoDto.getTbSiaMarcas(), 
				tbSiaPais.getTbSiaMarcas());
		assertSame(nomProductoDto.getTbSiaEmpresas(), 
				tbSiaPais.getTbSiaEmpresas());
		assertSame(nomProductoDto.getTbSiaNomProductos(), 
				tbSiaPais.getTbSiaNomProductos());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaPais> lsPais = new ArrayList<TbSiaPais>();
		lsPais.add(new TbSiaPais());
		lsPais.add(new TbSiaPais());
		
		List<PaisDTO> listPaisDTO = new ArrayList<PaisDTO>();
		PaisParser parser = new PaisParser();
		listPaisDTO = parser.toListDTO(lsPais);
		
		assertTrue(listPaisDTO.isEmpty());	
		
		assertNotEquals("size", 
				lsPais.size(), listPaisDTO.size());
		
		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsPais = new ArrayList<Object[]>();
		lsPais.add(new Object[5]);
		lsPais.add(new Object[4]);
		
		List<PaisDTO> listPaisDTO = new ArrayList<PaisDTO>();
		PaisParser parser = new PaisParser();
		listPaisDTO = parser.toListODTO(lsPais);
			
		assertNotEquals(lsPais, listPaisDTO);
	}
}
