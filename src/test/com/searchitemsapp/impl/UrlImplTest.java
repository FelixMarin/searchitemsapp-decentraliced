package com.searchitemsapp.impl;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.config.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.UrlDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class UrlImplTest {

	private static Logger LOGGER = LoggerFactory.getLogger(UrlImplTest.class); 
	
    @Autowired
    private ServletContext context;
    
    @Autowired
    UrlImpl urlImpl;
	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
    }
    
    @Before
    public void init() throws IOException {
    	
    	ServletContextEvent sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\db.properties",
				"db.properties", sve);
    }
	
	@Test
	public void testObtenerUrls() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		PaisDTO paisDTO = new PaisDTO();
		paisDTO.setDid(101);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		
		List<UrlDTO> lurlDto = urlImpl.obtenerUrls(paisDTO, categoriaDTO);
		
		assertNotNull(lurlDto);
	}

	@Test
	public void testObtenerUrlsLogin() throws IOException {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		PaisDTO paisDTO = new PaisDTO();
		paisDTO.setDid(101);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		
		List<UrlDTO> lurlDto = urlImpl.obtenerUrlsLogin(paisDTO, categoriaDTO);
		
		assertNotNull(lurlDto);
	}

	@Test
	public void testObtenerUrlsPorIdEmpresa() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		PaisDTO paisDTO = new PaisDTO();
		paisDTO.setDid(101);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		
		List<UrlDTO> lurlDto = urlImpl.obtenerUrlsPorIdEmpresa(paisDTO, categoriaDTO, "101");
		
		assertNotNull(lurlDto);
	}

	@Test
	public void testFindAll() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		List<UrlDTO> cdto = urlImpl.findAll();
		
		assertNotNull(cdto);
	}

	@Test
	public void testFindByDid() throws IOException {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		UrlDTO urlDTO = new UrlDTO();
		urlDTO.setDid(101);
		UrlDTO cdto = urlImpl.findByDid(urlDTO);
		
		assertNotNull(cdto);
	}

	@Test
	public void testFindByTbSia() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		UrlDTO urlDTO = new UrlDTO();
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		urlDTO.setDid(101);
		List<UrlDTO> cdto = urlImpl.findByTbSia(urlDTO, categoriaDTO);
		
		assertNotNull(cdto);
	}

}
