package com.searchitemsapp.initcache;

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
import com.searchitemsapp.parsers.CategoriaParserTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class InitCacheTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaParserTest.class);  
	
    @Autowired
    private ServletContext context;
    
    @Autowired
    private InitCache initCache;
    
    private ServletContextEvent sve;
	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
    }
    
    @Before
    public void init() throws IOException {
    	sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\db.properties",
				"db.properties", sve);
    }

	@Test
	public void testContextInitialized() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		initCache.contextInitialized(sve);
		
	}

	@Test
	public void testContextDestroyed() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		initCache.contextDestroyed(sve);
	}

}
