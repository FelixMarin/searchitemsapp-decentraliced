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
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaSelectoresCss;
import com.searchitemsapp.entities.TbSiaUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class SelectoresCssParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaSelectoresCss tbSiaSelectoresCssParser;
	 
	 @Autowired
	 IFParser<SelectoresCssDTO, TbSiaSelectoresCss> parser;
	 
	 @Autowired
	 SelectoresCssDTO selectoresCssDto;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(SelectoresCssParserTest.class);
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaSelectoresCssParser.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaSelectoresCssParser.setTbSiaUrl(new TbSiaUrl());
		
		SelectoresCssDTO selectoresCssDto = parser.toDTO(tbSiaSelectoresCssParser);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresa", 
				tbSiaSelectoresCssParser.getDid(), 
				selectoresCssDto.getDid());		
		assertEquals("getTbSiaUrl", 
				tbSiaSelectoresCssParser.getDid(), 
				selectoresCssDto.getDid());
		
		//- Same -//
		assertSame(tbSiaSelectoresCssParser.getTbSiaUrl().getDid(), 
				selectoresCssDto.getDidUrl());
		assertSame(tbSiaSelectoresCssParser.getTbSiaEmpresa().getDid(), 
				selectoresCssDto.getDidEmpresa());
		
	}
	
	@Test
	public void toTbSia() {
		
		selectoresCssDto.setDid(101);
		selectoresCssDto.setDidUrl(101);
		selectoresCssDto.setDidEmpresa(101);
		
		//- Equals -//
		assertEquals("getTbSiaEmpresa", 
			selectoresCssDto.getDid().byteValue(), 
				tbSiaSelectoresCssParser.getDid().byteValue());	
		assertEquals("getTbSiaUrl", 
				selectoresCssDto.getDidUrl().byteValue(), 
				tbSiaSelectoresCssParser.getTbSiaUrl().getDid().byteValue());
		
		//- Same -//
		assertSame(selectoresCssDto.getDidUrl().byteValue(), 
				tbSiaSelectoresCssParser.getTbSiaUrl().getDid().byteValue());
		assertSame(selectoresCssDto.getDidEmpresa().byteValue(), 
				tbSiaSelectoresCssParser.getTbSiaEmpresa().getDid().byteValue());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaSelectoresCss> lsSelectoresCssParser = Lists.newArrayList();
		tbSiaSelectoresCssParser.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaSelectoresCssParser.getTbSiaEmpresa().setDid(101);
		tbSiaSelectoresCssParser.setTbSiaUrl(new TbSiaUrl());
		lsSelectoresCssParser.add(tbSiaSelectoresCssParser);
		
		List<SelectoresCssDTO> listSelectoresCssParserDTO = Lists.newArrayList();
		listSelectoresCssParserDTO = parser.toListDTO(lsSelectoresCssParser);
		
		assertEquals("size", 
				lsSelectoresCssParser.size(), listSelectoresCssParserDTO.size());
		
		assertSame(lsSelectoresCssParser.get(0).getTbSiaEmpresa().getDid().byteValue(), 
				listSelectoresCssParserDTO.get(0).getDidEmpresa().byteValue());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsSelectoresCssParser = Lists.newArrayList();
		lsSelectoresCssParser.add(new Object[5]);
		
		List<SelectoresCssDTO> listSelectoresCssParserDTO = Lists.newArrayList();
		listSelectoresCssParserDTO = parser.toListODTO(lsSelectoresCssParser);
			
		assertNotEquals(lsSelectoresCssParser, listSelectoresCssParserDTO);
	}
}
