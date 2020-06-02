package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.entities.TbSiaMarcas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class MarcasParserTest {
	
	 private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(MarcasParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		TbSiaMarcas tbSiaMarcas = new TbSiaMarcas();
		
		tbSiaMarcas.setNomMarca("test");
		tbSiaMarcas.setDid(1111);
		
		MarcasParser parser = new MarcasParser();
		MarcasDTO marcasDto = parser.toDTO(tbSiaMarcas);
		
		//- Equals -//
		assertEquals("getNomMarca", 
				tbSiaMarcas.getNomMarca(), 
				marcasDto.getNomMarca());		
		assertEquals("getDid", 
				tbSiaMarcas.getDid(), 
				marcasDto.getDid());
		
		//- Same -//
		assertSame(tbSiaMarcas.getDid(), 
				marcasDto.getDid());
		assertSame(tbSiaMarcas.getNomMarca(), 
				marcasDto.getNomMarca());
		
	}
	
	@Test
	public void toTbSia() {
		
		MarcasDTO marcasDto = new MarcasDTO();
		
		marcasDto.setNomMarca("test");
		marcasDto.setDid(1111);
		
		MarcasParser parser = new MarcasParser();
		TbSiaMarcas tbSiaMarcas = parser.toTbSia(marcasDto);
		
		//- Equals -//
		assertEquals("getNomMarca", 
			marcasDto.getNomMarca(), 
				tbSiaMarcas.getNomMarca());	
		assertEquals("getDid", 
				marcasDto.getDid(), 
				tbSiaMarcas.getDid());
		
		//- Same -//
		assertSame(marcasDto.getDid(), 
				tbSiaMarcas.getDid());
		assertSame(marcasDto.getNomMarca(), 
				tbSiaMarcas.getNomMarca());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaMarcas> lsMarcas = new ArrayList<TbSiaMarcas>();
		lsMarcas.add(new TbSiaMarcas());
		lsMarcas.add(new TbSiaMarcas());
		
		List<MarcasDTO> listMarcasDTO = new ArrayList<MarcasDTO>();
		MarcasParser parser = new MarcasParser();
		listMarcasDTO = parser.toListDTO(lsMarcas);
		
		assertEquals("size", 
				lsMarcas.size(), listMarcasDTO.size());
		
		assertSame(lsMarcas.get(0).getNomMarca(), 
				listMarcasDTO.get(0).getNomMarca());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsMarcas = new ArrayList<Object[]>();
		lsMarcas.add(new Object[5]);
		lsMarcas.add(new Object[4]);
		
		List<MarcasDTO> listMarcasDTO = new ArrayList<MarcasDTO>();
		MarcasParser parser = new MarcasParser();
		listMarcasDTO = parser.toListODTO(lsMarcas);
			
		assertNotEquals(lsMarcas, listMarcasDTO);
	}
}
