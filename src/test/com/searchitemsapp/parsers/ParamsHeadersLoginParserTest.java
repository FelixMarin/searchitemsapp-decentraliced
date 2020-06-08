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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.entities.TbSiaParamsHeadersLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class ParamsHeadersLoginParserTest {
	
	 private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(ParamsHeadersLoginParserTest.class);   
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		TbSiaParamsHeadersLogin tbSiaParamsHeadersLogin = new TbSiaParamsHeadersLogin();
		
		tbSiaParamsHeadersLogin.setParamClave("clave-test");
		tbSiaParamsHeadersLogin.setParamValor("valor-test");
		
		ParamsHeadersLoginParser parser = new ParamsHeadersLoginParser();
		ParamsLoginDTO nomProductoDto = parser.toDTO(tbSiaParamsHeadersLogin);
		
		//- Equals -//
		assertEquals("getParamClave", nomProductoDto.getParamClave(), "clave-test");		
		assertEquals("getParamValor", nomProductoDto.getParamValor(), "valor-test");
		
		//- Same -//
		assertSame(tbSiaParamsHeadersLogin.getParamValor(), 
				nomProductoDto.getParamValor());
		assertSame(tbSiaParamsHeadersLogin.getParamClave(), 
				nomProductoDto.getParamClave());		
	}
	
	@Test
	public void toTbSia() {
		
		ParamsLoginDTO nomProductoDto = new ParamsLoginDTO();
		
		nomProductoDto.setParamClave("clave-test");
		nomProductoDto.setParamValor("valor-test");
		
		ParamsHeadersLoginParser parser = new ParamsHeadersLoginParser();
		TbSiaParamsHeadersLogin tbSiaParamsHeadersLogin = parser.toTbSia(nomProductoDto);
		
		//- Equals -//
		assertEquals("getParamClave", tbSiaParamsHeadersLogin.getParamClave(), "clave-test");	
		assertEquals("getParamValor", tbSiaParamsHeadersLogin.getParamValor(), "valor-test");
		
		//- Same -//
		assertSame(nomProductoDto.getParamValor(), 
				tbSiaParamsHeadersLogin.getParamValor());
		assertSame(nomProductoDto.getParamClave(), 
				tbSiaParamsHeadersLogin.getParamClave());		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaParamsHeadersLogin> lsParamsHeadersLogin = new ArrayList<>();
		lsParamsHeadersLogin.add(new TbSiaParamsHeadersLogin());
		lsParamsHeadersLogin.add(new TbSiaParamsHeadersLogin());
		
		List<ParamsLoginDTO> listParamsHeadersLoginDTO = new ArrayList<>();
		ParamsHeadersLoginParser parser = new ParamsHeadersLoginParser();
		listParamsHeadersLoginDTO = parser.toListDTO(lsParamsHeadersLogin);
		
		assertEquals("size", 
				lsParamsHeadersLogin.size(), listParamsHeadersLoginDTO.size());
		
		assertSame(lsParamsHeadersLogin.get(0).getParamClave(), 
				listParamsHeadersLoginDTO.get(0).getParamClave());		
		
		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsParamsHeadersLogin = new ArrayList<>();
		lsParamsHeadersLogin.add(new Object[5]);
		lsParamsHeadersLogin.add(new Object[4]);
		
		List<ParamsLoginDTO> listParamsHeadersLoginDTO = new ArrayList<>();
		ParamsHeadersLoginParser parser = new ParamsHeadersLoginParser();
		listParamsHeadersLoginDTO = parser.toListODTO(lsParamsHeadersLogin);
			
		assertNotEquals(lsParamsHeadersLogin, listParamsHeadersLoginDTO);
	}
}
