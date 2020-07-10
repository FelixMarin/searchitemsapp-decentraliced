package com.searchitemsapp.controller;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ListaProductosValidatorTest {

	private static Logger LOGGER = LoggerFactory.getLogger(ListaProductosValidatorTest.class); 
		
    @Autowired
    private ServletContext context;
    
    @Autowired
    ListaProductosValidator listaProductosValidator;
	
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
	public void testIsParams() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		boolean b = listaProductosValidator.isParams("101","101","1","miel","ALL");
		
		assertTrue(b);
		
	}

	@Test
	public void testIsNumeric() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		boolean b = listaProductosValidator.isNumeric("101");
		
		assertTrue(b);
	}

	@Test
	public void testIsOrdenacion() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		boolean b = listaProductosValidator.isOrdenacion("101");
		
		assertTrue(b);
	}

	@Test
	public void testIsEmpresa() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		boolean b = listaProductosValidator.isEmpresa("101");
				
		assertTrue(b);
	}

}
