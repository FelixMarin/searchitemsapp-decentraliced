package com.searchitemsapp.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class CommonsPorpertiesTest {
	
	private static Logger LOGGER = null;
    
	@Autowired
    private ServletContext context;
	
	@Autowired
	private IFCommonsProperties iFCommonsProperties;

	@BeforeClass
	public static void setLogger() throws MalformedURLException {
	    	org.apache.log4j.BasicConfigurator.configure();
	        System.setProperty("log4j.properties","log4j.properties");
	        System.setProperty("db.properties","db.properties");
	        System.setProperty("flow.properties","flow.properties");
	        LOGGER = LoggerFactory.getLogger(CommonsPorpertiesTest.class);
	}

	@Test
	public void testLoadPropertiesFile() throws IOException {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ServletContextEvent sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\workspace_sts\\properties\\flow.properties",
				"flow.properties", sve);
		
		assertNotNull(sve.getServletContext().getAttribute("flow.properties"));
	}

	@Test
	public void testGetValue() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		testLoadPropertiesFile();
		String test = iFCommonsProperties.getValue("flow.value.activo");
		String salida = "activo";

		assertEquals(test, salida);
	}

}
