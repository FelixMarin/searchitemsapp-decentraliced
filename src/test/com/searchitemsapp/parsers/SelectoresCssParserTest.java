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

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaSelectoresCss;
import com.searchitemsapp.entities.TbSiaUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class SelectoresCssParserTest {
	
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

		TbSiaSelectoresCss tbSiaSelectoresCssParser = new TbSiaSelectoresCss();
		
		tbSiaSelectoresCssParser.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaSelectoresCssParser.setTbSiaUrl(new TbSiaUrl());
		
		SelectoresCssParser parser = new SelectoresCssParser();
		SelectoresCssDTO selectoresCssDto = parser.toDTO(tbSiaSelectoresCssParser);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresa", 
				tbSiaSelectoresCssParser.getDid(), 
				selectoresCssDto.getDid());		
		assertEquals("getTbSiaUrl", 
				tbSiaSelectoresCssParser.getDid(), 
				selectoresCssDto.getDid());
		
		//- Same -//
		assertSame(tbSiaSelectoresCssParser.getTbSiaUrl(), 
				selectoresCssDto.getDid());
		assertSame(tbSiaSelectoresCssParser.getTbSiaEmpresa(), 
				selectoresCssDto.getDid());
		
	}
	
	@Test
	public void toTbSia() {
		
		SelectoresCssDTO selectoresCssDto = new SelectoresCssDTO();
		
		SelectoresCssParser parser = new SelectoresCssParser();
		TbSiaSelectoresCss tbSiaSelectoresCssParser = parser.toTbSia(selectoresCssDto);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresa", 
			selectoresCssDto.getDid(), 
				tbSiaSelectoresCssParser.getTbSiaEmpresa());	
		assertEquals("getTbSiaUrl", 
				selectoresCssDto.getDid(), 
				tbSiaSelectoresCssParser.getTbSiaUrl());
		
		//- Same -//
		assertSame(selectoresCssDto.getDid(), 
				tbSiaSelectoresCssParser.getTbSiaUrl());
		assertSame(selectoresCssDto.getDid(), 
				tbSiaSelectoresCssParser.getTbSiaEmpresa());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaSelectoresCss> lsSelectoresCssParser = new ArrayList<TbSiaSelectoresCss>();
		lsSelectoresCssParser.add(new TbSiaSelectoresCss());
		lsSelectoresCssParser.add(new TbSiaSelectoresCss());
		
		List<SelectoresCssDTO> listSelectoresCssParserDTO = new ArrayList<SelectoresCssDTO>();
		SelectoresCssParser parser = new SelectoresCssParser();
		listSelectoresCssParserDTO = parser.toListDTO(lsSelectoresCssParser);
		
		assertEquals("size", 
				lsSelectoresCssParser.size(), listSelectoresCssParserDTO.size());
		
		assertSame(lsSelectoresCssParser.get(0).getTbSiaEmpresa(), 
				listSelectoresCssParserDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsSelectoresCssParser = new ArrayList<Object[]>();
		lsSelectoresCssParser.add(new Object[5]);
		lsSelectoresCssParser.add(new Object[4]);
		
		List<SelectoresCssDTO> listSelectoresCssParserDTO = new ArrayList<SelectoresCssDTO>();
		SelectoresCssParser parser = new SelectoresCssParser();
		listSelectoresCssParserDTO = parser.toListODTO(lsSelectoresCssParser);
			
		assertNotEquals(lsSelectoresCssParser, listSelectoresCssParserDTO);
	}
}
