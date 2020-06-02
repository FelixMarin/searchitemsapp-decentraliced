package com.searchitemsapp.commons;

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
@ContextConfiguration("file:src/main/webapp/WEB-INF/context-servicefactory-test.xml")
@WebAppConfiguration
public class CommonsPorpertiesTest {
	
    @Autowired
    ServletContext context;

	 private static Logger LOGGER = null;

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
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		assertNotNull(sve.getServletContext().getAttribute("flow.properties"));
	}

	@Test
	public void testGetValue() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		testLoadPropertiesFile();
		String test = CommonsPorperties.getValue("flow.value.activo");
		String salida = "activo";

		assertEquals(test, salida);
	}

}
