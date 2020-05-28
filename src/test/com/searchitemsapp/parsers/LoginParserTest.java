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

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class LoginParserTest {
	
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

		TbSiaLogin tbSiaLogin = new TbSiaLogin();
		
		tbSiaLogin.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaLogin.setCodPostal(30430);
		tbSiaLogin.setDid(1111);
		
		LoginParser parser = new LoginParser();
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
		
		LoginDTO loginDto = new LoginDTO();
				
		LoginParser parser = new LoginParser();
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
		
		List<TbSiaLogin> lsLogin = new ArrayList<TbSiaLogin>();
		lsLogin.add(new TbSiaLogin());
		lsLogin.add(new TbSiaLogin());
		
		List<LoginDTO> listLoginDTO = new ArrayList<LoginDTO>();
		LoginParser parser = new LoginParser();
		listLoginDTO = parser.toListDTO(lsLogin);
		
		assertEquals("size", 
				lsLogin.size(), listLoginDTO.size());
		
		assertSame(lsLogin.get(0).getDid(), 
				listLoginDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsLogin = new ArrayList<Object[]>();
		lsLogin.add(new Object[5]);
		lsLogin.add(new Object[4]);
		
		List<LoginDTO> listLoginDTO = new ArrayList<LoginDTO>();
		LoginParser parser = new LoginParser();
		listLoginDTO = parser.toListODTO(lsLogin);
			
		assertNotEquals(lsLogin, listLoginDTO);
	}
}
