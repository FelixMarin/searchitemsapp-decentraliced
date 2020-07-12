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
import com.searchitemsapp.dto.MarcasDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class MarcasImplTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MarcasImplTest.class); 
	
    @Autowired
    private ServletContext context;
    
    @Autowired
    MarcasImpl marcasImpl;
	
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
	public void testFindAll() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		List<MarcasDTO> cdto = marcasImpl.findAll();
		
		assertNotNull(cdto);
	}

	@Test
	public void testFindByDid() throws IOException {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		MarcasDTO marcasDTO = new MarcasDTO();
		marcasDTO.setDid(101);
		MarcasDTO cdto = marcasImpl.findByDid(marcasDTO);
		
		assertNotNull(cdto);
		
	}

	@Test
	public void testFindByTbSia() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		MarcasDTO marcasDTO = new MarcasDTO();
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		marcasDTO.setDid(101);
		List<MarcasDTO> cdto = marcasImpl.findByTbSia(marcasDTO, categoriaDTO);
		
		assertNotNull(cdto);
		
	}

}
