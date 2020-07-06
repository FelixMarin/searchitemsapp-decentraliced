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

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ParamsFormLoginImplTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ParamsFormLoginImplTest.class); 
	
    @Autowired
    private ServletContext context;
    
    @Autowired
    ParamsFormLoginImpl paramsFormLoginImpl;
	
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
		
		List<ParamsLoginDTO> cdto = paramsFormLoginImpl.findAll();
		
		assertNotNull(cdto);
	}

	@Test
	public void testFindByDid() throws IOException {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ParamsLoginDTO paramsLoginDTO = new ParamsLoginDTO();
		paramsLoginDTO.setDid(101);
		ParamsLoginDTO cdto = paramsFormLoginImpl.findByDid(paramsLoginDTO);
		
		assertNotNull(cdto);
		
	}

	@Test
	public void testFindByTbSia() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ParamsLoginDTO paramsLoginDTO = new ParamsLoginDTO();
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setDid(101);
		paramsLoginDTO.setDid(101);
		List<ParamsLoginDTO> cdto = paramsFormLoginImpl.findByTbSia(paramsLoginDTO, categoriaDTO);
		
		assertNotNull(cdto);
		
	}

}
