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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaMarcas;
import com.searchitemsapp.entities.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class MarcasParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaMarcas tbSiaMarcas;
	 
	 @Autowired
	 IFParser<MarcasDTO, TbSiaMarcas> parser;
	 
	 @Autowired
	 MarcasDTO marcasDto;
	 
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

		tbSiaMarcas.setNomMarca("test");
		tbSiaMarcas.setDid(1111);
		tbSiaMarcas.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaMarcas.setTbSiaPais(new TbSiaPais());
		
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
		
		marcasDto.setNomMarca("test");
		marcasDto.setDid(1111);
		
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
		
		List<TbSiaMarcas> lsMarcas = new ArrayList<>();
		tbSiaMarcas.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaMarcas.setTbSiaPais(new TbSiaPais());
		tbSiaMarcas.setDid(101);
		lsMarcas.add(tbSiaMarcas);
		
		List<MarcasDTO> listMarcasDTO = new ArrayList<>();
		listMarcasDTO = parser.toListDTO(lsMarcas);
		
		assertEquals("size", 
				lsMarcas.size(), listMarcasDTO.size());
		
		assertSame(lsMarcas.get(0).getNomMarca(), 
				listMarcasDTO.get(0).getNomMarca());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsMarcas = new ArrayList<>();
		lsMarcas.add(new Object[5]);
		
		List<MarcasDTO> listMarcasDTO = new ArrayList<>();
		listMarcasDTO = parser.toListODTO(lsMarcas);
			
		assertNotEquals(lsMarcas, listMarcasDTO);
	}
}
