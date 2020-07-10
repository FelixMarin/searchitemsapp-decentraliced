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

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class LoginParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 TbSiaLogin tbSiaLogin;
	 
	 @Autowired
	 IFParser<LoginDTO, TbSiaLogin> parser;
	 
	 @Autowired
	 LoginDTO loginDto;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(LoginParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaLogin.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaLogin.setCodPostal(30430);
		tbSiaLogin.setDid(1111);
		
		LoginDTO loginDto = parser.toDTO(tbSiaLogin);
		
		//- Equals -//
		assertEquals("getDid", 
				tbSiaLogin.getDid(), 
				loginDto.getDid());		
		assertEquals("getCodPostal", 
				tbSiaLogin.getCodPostal(), 
				loginDto.getCodPostal());		
		assertEquals("getDid", 
				tbSiaLogin.getDid(), 
				loginDto.getDid());
		
		//- Same -//
		assertSame(tbSiaLogin.getDid(), 
				loginDto.getDid());
		assertSame(tbSiaLogin.getCodPostal(), 
				loginDto.getCodPostal());
		assertSame(tbSiaLogin.getDid(), 
				loginDto.getDid());
		
	}
	
	@Test
	public void toTbSia() {
		
		loginDto.setDid(101);
		TbSiaLogin tbSiaLogin = parser.toTbSia(loginDto);
		
		assertEquals("getDid", 
			loginDto.getDid(), 
				tbSiaLogin.getDid());
		assertEquals("getCodPostal", 
				loginDto.getCodPostal(), 
				tbSiaLogin.getCodPostal());		
		assertEquals("getDid", 
				loginDto.getDid(), 
				tbSiaLogin.getDid());
		
		//- Same -//
		assertSame(loginDto.getDid(), 
				tbSiaLogin.getDid());
		assertSame(loginDto.getCodPostal(), 
				tbSiaLogin.getCodPostal());
		assertSame(loginDto.getDid(), 
				tbSiaLogin.getDid());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaLogin> lsLogin = new ArrayList<>();
		tbSiaLogin.setTbSiaEmpresa(new TbSiaEmpresa());
		lsLogin.add(tbSiaLogin);
		
		List<LoginDTO> listLoginDTO = new ArrayList<>();
		listLoginDTO = parser.toListDTO(lsLogin);
		
		assertEquals("size", 
				lsLogin.size(), listLoginDTO.size());
		
		assertSame(lsLogin.get(0).getDid(), 
				listLoginDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsLogin = new ArrayList<>();
		lsLogin.add(new Object[5]);
		lsLogin.add(new Object[4]);
		
		List<LoginDTO> listLoginDTO = new ArrayList<>();
		listLoginDTO = parser.toListODTO(lsLogin);
			
		assertNotEquals(lsLogin, listLoginDTO);
	}
}
