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
import com.searchitemsapp.entities.TbSiaParamsFormLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class ParamsFormLoginParserTest {
	
	 private static Logger LOGGER = null;

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

		TbSiaParamsFormLogin tbSiaParamsFormLogin = new TbSiaParamsFormLogin();
		
		tbSiaParamsFormLogin.setParamClave("clave-test");
		tbSiaParamsFormLogin.setParamValor("valor-test");
		
		ParamsFormLoginParser parser = new ParamsFormLoginParser();
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
		
		ParamsLoginDTO nomProductoDto = new ParamsLoginDTO();
		
		nomProductoDto.setParamClave("clave-test");
		nomProductoDto.setParamValor("valor-test");
		
		ParamsFormLoginParser parser = new ParamsFormLoginParser();
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
		
		List<TbSiaParamsFormLogin> lsParamsFormLogin = new ArrayList<TbSiaParamsFormLogin>();
		lsParamsFormLogin.add(new TbSiaParamsFormLogin());
		lsParamsFormLogin.add(new TbSiaParamsFormLogin());
		
		List<ParamsLoginDTO> listParamsFormLoginDTO = new ArrayList<ParamsLoginDTO>();
		ParamsFormLoginParser parser = new ParamsFormLoginParser();
		listParamsFormLoginDTO = parser.toListDTO(lsParamsFormLogin);
		
		assertEquals("size", 
				lsParamsFormLogin.size(), listParamsFormLoginDTO.size());
		
		assertSame(lsParamsFormLogin.get(0).getParamClave(), 
				listParamsFormLoginDTO.get(0).getParamClave());		
		
		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsParamsFormLogin = new ArrayList<Object[]>();
		lsParamsFormLogin.add(new Object[5]);
		lsParamsFormLogin.add(new Object[4]);
		
		List<ParamsLoginDTO> listParamsFormLoginDTO = new ArrayList<ParamsLoginDTO>();
		ParamsFormLoginParser parser = new ParamsFormLoginParser();
		listParamsFormLoginDTO = parser.toListODTO(lsParamsFormLogin);
			
		assertNotEquals(lsParamsFormLogin, listParamsFormLoginDTO);
	}
}
