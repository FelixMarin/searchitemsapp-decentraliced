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
		assertEquals("getTbSiaCategoriasEmpresa", 
				tbSiaNomProducto.getTbSiaCategoriasEmpresa(), 
				nomProductoDto.getTbSiaCategoriasEmpresa());		
		assertEquals("getTbSiaPais", 
				tbSiaNomProducto.getTbSiaPais(), 
				nomProductoDto.getTbSiaPais());
		assertEquals("getetNomProducto", 
				tbSiaNomProducto.getNomProducto(), 
				nomProductoDto.getNomProducto());
		
		//- Same -//
		assertSame(tbSiaNomProducto.getTbSiaPais(), 
				nomProductoDto.getTbSiaPais());
		assertSame(tbSiaNomProducto.getTbSiaCategoriasEmpresa(), 
				nomProductoDto.getTbSiaCategoriasEmpresa());
		assertSame(tbSiaNomProducto.getNomProducto(), 
				nomProductoDto.getNomProducto());
		
	}
	
	@Test
	public void toTbSia() {
		
		NomProductoDTO nomProductoDto = new NomProductoDTO();
		
		nomProductoDto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		nomProductoDto.setTbSiaPais(new TbSiaPais());
		nomProductoDto.setNomProducto("test");
		
		NomProductoParser parser = new NomProductoParser();
		TbSiaNomProducto tbSiaNomProducto = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getTbSiaCategoriasEmpresa", 
			nomProductoDto.getTbSiaCategoriasEmpresa(), 
				tbSiaNomProducto.getTbSiaCategoriasEmpresa());	
		assertEquals("getTbSiaPais", 
				nomProductoDto.getTbSiaPais(), 
				tbSiaNomProducto.getTbSiaPais());
		assertEquals("getetNomProducto", 
				nomProductoDto.getNomProducto(), 
				tbSiaNomProducto.getNomProducto());
		
		//- Same -//
		assertSame(nomProductoDto.getTbSiaPais(), 
				tbSiaNomProducto.getTbSiaPais());
		assertSame(nomProductoDto.getTbSiaCategoriasEmpresa(), 
				tbSiaNomProducto.getTbSiaCategoriasEmpresa());
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
		
		assertSame(lsNomProducto.get(0).getTbSiaCategoriasEmpresa(), 
				listNomProductoDTO.get(0).getTbSiaCategoriasEmpresa());		
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
