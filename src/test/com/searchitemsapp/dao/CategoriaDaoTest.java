package com.searchitemsapp.dao;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class CategoriaDaoTest {
	
	private static Logger LOGGER = null;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
    @Autowired
    private ServletContext context;
	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(CategoriaDao.class);  
    }
    
    @Before
    public void init() throws IOException {
    	ServletContextEvent sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\workspace_sts\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\workspace_sts\\properties\\db.properties",
				"db.properties", sve);
    }

	@Test
	public void testFindAll() throws IOException {


		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		List<CategoriaDTO> lcdto = categoriaDao.findAll();
		
		assertNotNull(lcdto);
	}

	@Test
	public void testFindByDid() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		CategoriaDTO dto = categoriaDao.findByDid(101);
		
		assertNotNull(dto);
	}

}
