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
import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaNomProducto;
import com.searchitemsapp.entities.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class NomProductoParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaNomProducto tbSiaNomProducto;
	 
	 @Autowired
	 IFParser<NomProductoDTO, TbSiaNomProducto> parser;
	 
	 @Autowired
	 NomProductoDTO nomProductoDto;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(NomProductoParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaNomProducto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(new TbSiaPais());
		tbSiaNomProducto.setNomProducto("test");
		
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
		
		nomProductoDto.setDid(101);
		nomProductoDto.setNomProducto("test");
		
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
		
		List<TbSiaNomProducto> lsNomProducto = Lists.newArrayList();
		tbSiaNomProducto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaNomProducto.setTbSiaPais(new TbSiaPais());
		lsNomProducto.add(tbSiaNomProducto);
		
		List<NomProductoDTO> listNomProductoDTO = Lists.newArrayList();
		listNomProductoDTO = parser.toListDTO(lsNomProducto);
		
		assertEquals("size", 
				lsNomProducto.size(), listNomProductoDTO.size());
		
		assertSame(lsNomProducto.get(0).getDid(), 
				listNomProductoDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsNomProducto = Lists.newArrayList();
		lsNomProducto.add(new Object[5]);
		
		List<NomProductoDTO> listNomProductoDTO = Lists.newArrayList();
		listNomProductoDTO = parser.toListODTO(lsNomProducto);
		
		assertNotEquals(lsNomProducto, listNomProductoDTO);
	}
}
