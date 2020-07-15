package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaMarcas;
import com.searchitemsapp.entities.TbSiaNomProducto;
import com.searchitemsapp.entities.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class PaisParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaPais tbSiaPais;
	 
	 @Autowired
	 PaisDTO nomProductoDto;
	 
	 @Autowired
	 IFParser<PaisDTO, TbSiaPais> parser;
	 
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(PaisParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaPais.setTbSiaEmpresas(Lists.newArrayList());
		tbSiaPais.setTbSiaMarcas(Lists.newArrayList());
		tbSiaPais.setTbSiaNomProductos(Lists.newArrayList());
		
		tbSiaPais.addTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaPais.addTbSiaMarca(new TbSiaMarcas());
		tbSiaPais.addTbSiaNomProducto(new TbSiaNomProducto());
		
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
		
		nomProductoDto.setDid(101);
		nomProductoDto.setEmpresas(new EmpresaDTO());
		nomProductoDto.setMarcas(new MarcasDTO());
		nomProductoDto.setProductos(new NomProductoDTO());
		
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
		
		List<TbSiaPais> lsPais = Lists.newArrayList();
		tbSiaPais.setTbSiaEmpresas(Lists.newArrayList());
		tbSiaPais.addTbSiaEmpresa(new TbSiaEmpresa());
		lsPais.add(tbSiaPais);
		
		List<PaisDTO> listPaisDTO = Lists.newArrayList();
		listPaisDTO = parser.toListDTO(lsPais);
		
		assertTrue(listPaisDTO.isEmpty());	
		
		assertNotEquals("size", 
				lsPais.size(), listPaisDTO.size());
		
		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsPais = Lists.newArrayList();
		lsPais.add(new Object[5]);
		
		List<PaisDTO> listPaisDTO = Lists.newArrayList();
		listPaisDTO = parser.toListODTO(lsPais);
			
		assertNotEquals(lsPais, listPaisDTO);
	}
}
