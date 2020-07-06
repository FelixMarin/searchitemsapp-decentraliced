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

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsFormLogin;
import com.searchitemsapp.entities.TbSiaUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class ParamsFormLoginParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaParamsFormLogin tbSiaParamsFormLogin;
	 
	 @Autowired
	 ParamsFormLoginParser parser;
	 
	 @Autowired
	 ParamsLoginDTO nomProductoDto;
	 
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(ParamsFormLoginParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaParamsFormLogin.setParamClave("clave-test");
		tbSiaParamsFormLogin.setParamValor("valor-test");
		
		ParamsLoginDTO nomProductoDto = parser.toDTO(tbSiaParamsFormLogin);
		
		//- Equals -//
		assertEquals("getParamClave", nomProductoDto.getParamClave(), "clave-test");		
		assertEquals("getParamValor", nomProductoDto.getParamValor(), "valor-test");
		
		//- Same -//
		assertSame(tbSiaParamsFormLogin.getParamValor(), 
				nomProductoDto.getParamValor());
		assertSame(tbSiaParamsFormLogin.getParamClave(), 
				nomProductoDto.getParamClave());		
	}
	
	@Test
	public void toTbSia() {
		
		nomProductoDto.setParamClave("clave-test");
		nomProductoDto.setParamValor("valor-test");
		
		TbSiaParamsFormLogin tbSiaParamsFormLogin = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getParamClave", tbSiaParamsFormLogin.getParamClave(), "clave-test");	
		assertEquals("getParamValor", tbSiaParamsFormLogin.getParamValor(), "valor-test");
		
		//- Same -//
		assertSame(nomProductoDto.getParamValor(), 
				tbSiaParamsFormLogin.getParamValor());
		assertSame(nomProductoDto.getParamClave(), 
				tbSiaParamsFormLogin.getParamClave());		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaParamsFormLogin> lsParamsFormLogin = new ArrayList<>();
		tbSiaParamsFormLogin.setTbSiaUrl(new TbSiaUrl());
		lsParamsFormLogin.add(tbSiaParamsFormLogin);
		
		List<ParamsLoginDTO> listParamsFormLoginDTO = parser.toListDTO(lsParamsFormLogin);
		
		assertEquals("size", 
				lsParamsFormLogin.size(), listParamsFormLoginDTO.size());
		
		assertSame(lsParamsFormLogin.get(0).getParamClave(), 
				listParamsFormLoginDTO.get(0).getParamClave());		
		
		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsParamsFormLogin = new ArrayList<>();
		lsParamsFormLogin.add(new Object[5]);
		
		List<ParamsLoginDTO> listParamsFormLoginDTO = new ArrayList<>();
		listParamsFormLoginDTO = parser.toListODTO(lsParamsFormLogin);
			
		assertNotEquals(lsParamsFormLogin, listParamsFormLoginDTO);
	}
}
