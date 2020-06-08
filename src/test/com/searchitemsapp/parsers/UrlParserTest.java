package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
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

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.entities.TbSiaUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class UrlParserTest {
	
	 private static Logger LOGGER = null;

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

		TbSiaUrl tbSiaUrl = new TbSiaUrl();
		
		tbSiaUrl.setNomUrl("test");
		tbSiaUrl.setDid(1111);
		
		UrlParser parser = new UrlParser();
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
		
		UrlDTO urlDto = new UrlDTO();
		
		urlDto.setNomUrl("test");
		urlDto.setDid(1111);
		
		UrlParser parser = new UrlParser();
		TbSiaUrl tbSiaUrl = parser.toTbSia(urlDto);
		
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
		
		List<TbSiaUrl> lsUrl = new ArrayList<>();
		lsUrl.add(new TbSiaUrl());
		lsUrl.add(new TbSiaUrl());
		
		List<UrlDTO> listUrlDTO = new ArrayList<>();
		UrlParser parser = new UrlParser();
		listUrlDTO = parser.toListDTO(lsUrl);
		
		assertEquals("size", 
				lsUrl.size(), listUrlDTO.size());
		
		assertSame(lsUrl.get(0).getNomUrl(), 
				listUrlDTO.get(0).getNomUrl());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsUrl = new ArrayList<>();
		Object[] objTest = {"test","1","2","test","test",
				"test","test","test","test","test"};
		
		lsUrl.add(objTest);
		lsUrl.add(objTest);
		
		UrlParser parser = new UrlParser();
		List<UrlDTO> listUrlDTO = parser.toListODTO(lsUrl);
			
		assertEquals(lsUrl.get(0)[0], "test");
		assertEquals(listUrlDTO.get(0).getNomUrl(), "test");
	}
}
