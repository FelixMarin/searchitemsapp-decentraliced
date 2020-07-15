package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
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
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class UrlParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaUrl tbSiaUrl;
	 
	 @Autowired
	 IFParser<UrlDTO, TbSiaUrl> parser;
	 
	 @Autowired
	 UrlDTO urlDto;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(UrlParserTest.class);
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaUrl.setNomUrl("test");
		tbSiaUrl.setDid(1111);
		
		UrlDTO urlDto = parser.toDTO(tbSiaUrl);
		
		//- Equals -//
		assertEquals("getNomUrl", 
				tbSiaUrl.getNomUrl(), 
				urlDto.getNomUrl());		
		assertEquals("getDid", 
				tbSiaUrl.getDid(), 
				urlDto.getDid());
		
		//- Same -//
		assertSame(tbSiaUrl.getDid(), 
				urlDto.getDid());
		assertSame(tbSiaUrl.getNomUrl(), 
				urlDto.getNomUrl());
		
	}
	
	@Test
	public void toTbSia() {
		
		urlDto.setNomUrl("test");
		urlDto.setDid(1111);
		
		//- Equals -//
		assertEquals("getNomUrl", 
			urlDto.getNomUrl(), 
				tbSiaUrl.getNomUrl());	
		assertEquals("getDid", 
				urlDto.getDid(), 
				tbSiaUrl.getDid());
		
		//- Same -//
		assertSame(urlDto.getDid(), 
				tbSiaUrl.getDid());
		assertSame(urlDto.getNomUrl(), 
				tbSiaUrl.getNomUrl());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaUrl> lsUrl = Lists.newArrayList();
		tbSiaUrl.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaUrl.setTbSiaSelectoresCsses(Lists.newArrayList());
		lsUrl.add(tbSiaUrl);
		
		List<UrlDTO> listUrlDTO = Lists.newArrayList();
		listUrlDTO = parser.toListDTO(lsUrl);
		
		assertEquals("size", 
				lsUrl.size(), listUrlDTO.size());
		
		assertSame(lsUrl.get(0).getNomUrl(), 
				listUrlDTO.get(0).getNomUrl());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsUrl = Lists.newArrayList();
		Object[] objTest = {"test","1","2","test","test",
				"test","test","test","test","test"};
		
		lsUrl.add(objTest);
		
		List<UrlDTO> listUrlDTO = parser.toListODTO(lsUrl);
			
		assertEquals(lsUrl.get(0)[0], "test");
		assertEquals(listUrlDTO.get(0).getNomUrl(), "test");
	}
}
