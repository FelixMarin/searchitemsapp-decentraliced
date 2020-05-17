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
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.model.TbSiaUrl;

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
				tbSiaSelectoresCssParser.getTbSiaEmpresa(), 
				selectoresCssDto.getTbSiaEmpresa());		
		assertEquals("getTbSiaUrl", 
				tbSiaSelectoresCssParser.getTbSiaUrl(), 
				selectoresCssDto.getTbSiaUrl());
		
		//- Same -//
		assertSame(tbSiaSelectoresCssParser.getTbSiaUrl(), 
				selectoresCssDto.getTbSiaUrl());
		assertSame(tbSiaSelectoresCssParser.getTbSiaEmpresa(), 
				selectoresCssDto.getTbSiaEmpresa());
		
	}
	
	@Test
	public void toTbSia() {
		
		SelectoresCssDTO selectoresCssDto = new SelectoresCssDTO();
		
		selectoresCssDto.setTbSiaEmpresa(new TbSiaEmpresa());
		selectoresCssDto.setTbSiaUrl(new TbSiaUrl());
		
		SelectoresCssParser parser = new SelectoresCssParser();
		TbSiaSelectoresCss tbSiaSelectoresCssParser = parser.toTbSia(selectoresCssDto);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresa", 
			selectoresCssDto.getTbSiaEmpresa(), 
				tbSiaSelectoresCssParser.getTbSiaEmpresa());	
		assertEquals("getTbSiaUrl", 
				selectoresCssDto.getTbSiaUrl(), 
				tbSiaSelectoresCssParser.getTbSiaUrl());
		
		//- Same -//
		assertSame(selectoresCssDto.getTbSiaUrl(), 
				tbSiaSelectoresCssParser.getTbSiaUrl());
		assertSame(selectoresCssDto.getTbSiaEmpresa(), 
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
				listSelectoresCssParserDTO.get(0).getTbSiaEmpresa());		
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