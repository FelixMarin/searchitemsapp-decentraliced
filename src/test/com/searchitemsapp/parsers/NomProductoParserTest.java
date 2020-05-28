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

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.model.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class NomProductoParserTest {
	
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

		TbSiaNomProducto tbSiaNomProducto = new TbSiaNomProducto();
		
		tbSiaNomProducto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(new TbSiaPais());
		tbSiaNomProducto.setNomProducto("test");
		
		NomProductoParser parser = new NomProductoParser();
		NomProductoDTO nomProductoDto = parser.toDTO(tbSiaNomProducto);
		
		//- Equals -//
		assertEquals("getDid", 
				tbSiaNomProducto.getDid(), 
				nomProductoDto.getDid());		
		assertEquals("getDid", 
				tbSiaNomProducto.getDid(), 
				nomProductoDto.getDid());
		assertEquals("getetNomProducto", 
				tbSiaNomProducto.getNomProducto(), 
				nomProductoDto.getNomProducto());
		
		//- Same -//
		assertSame(tbSiaNomProducto.getDid(), 
				nomProductoDto.getDid());
		assertSame(tbSiaNomProducto.getDid(), 
				nomProductoDto.getDid());
		assertSame(tbSiaNomProducto.getNomProducto(), 
				nomProductoDto.getNomProducto());
		
	}
	
	@Test
	public void toTbSia() {
		
		NomProductoDTO nomProductoDto = new NomProductoDTO();
		
		nomProductoDto.setNomProducto("test");
		
		NomProductoParser parser = new NomProductoParser();
		TbSiaNomProducto tbSiaNomProducto = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getDid", 
			nomProductoDto.getDid(), 
				tbSiaNomProducto.getDid());	
		assertEquals("getDid", 
				nomProductoDto.getDid(), 
				tbSiaNomProducto.getDid());
		assertEquals("getetNomProducto", 
				nomProductoDto.getNomProducto(), 
				tbSiaNomProducto.getNomProducto());
		
		//- Same -//
		assertSame(nomProductoDto.getDid(), 
				tbSiaNomProducto.getDid());
		assertSame(nomProductoDto.getDid(), 
				tbSiaNomProducto.getDid());
		assertSame(nomProductoDto.getNomProducto(), 
				tbSiaNomProducto.getNomProducto());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaNomProducto> lsNomProducto = new ArrayList<TbSiaNomProducto>();
		lsNomProducto.add(new TbSiaNomProducto());
		lsNomProducto.add(new TbSiaNomProducto());
		
		List<NomProductoDTO> listNomProductoDTO = new ArrayList<NomProductoDTO>();
		NomProductoParser parser = new NomProductoParser();
		listNomProductoDTO = parser.toListDTO(lsNomProducto);
		
		assertEquals("size", 
				lsNomProducto.size(), listNomProductoDTO.size());
		
		assertSame(lsNomProducto.get(0).getDid(), 
				listNomProductoDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsNomProducto = new ArrayList<Object[]>();
		lsNomProducto.add(new Object[5]);
		lsNomProducto.add(new Object[4]);
		
		List<NomProductoDTO> listNomProductoDTO = new ArrayList<NomProductoDTO>();
		NomProductoParser parser = new NomProductoParser();
		listNomProductoDTO = parser.toListODTO(lsNomProducto);
			
		assertNotEquals(lsNomProducto, listNomProductoDTO);
	}
}
