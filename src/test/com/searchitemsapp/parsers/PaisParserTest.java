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
		assertEquals("getDid", 
				tbSiaPais.getDid(), 
				nomProductoDto.getDid());		
		assertEquals("getDid", 
				tbSiaPais.getDid(), 
				nomProductoDto.getDid());
		assertEquals("getetTbSiaNomProductos", 
				tbSiaPais.getDid(), 
				nomProductoDto.getDid());
		
		//- Same -//
		assertSame(tbSiaPais.getDid(), 
				nomProductoDto.getDid());
		assertSame(tbSiaPais.getDid(), 
				nomProductoDto.getDid());
		assertSame(tbSiaPais.getDid(), 
				nomProductoDto.getDid());
		
	}
	
	@Test
	public void toTbSia() {
		
		PaisDTO nomProductoDto = new PaisDTO();
						
		PaisParser parser = new PaisParser();
		TbSiaPais tbSiaPais = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getDid", 
			nomProductoDto.getDid(), 
				tbSiaPais.getDid());	
		assertEquals("getDid", 
				nomProductoDto.getDid(), 
				tbSiaPais.getDid());
		assertEquals("getetTbSiaNomProductos", 
				nomProductoDto.getDid(), 
				tbSiaPais.getDid());
		
		//- Same -//
		assertSame(nomProductoDto.getDid(), 
				tbSiaPais.getDid());
		assertSame(nomProductoDto.getDid(), 
				tbSiaPais.getDid());
		assertSame(nomProductoDto.getDid(), 
				tbSiaPais.getDid());
		
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
