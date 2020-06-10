package com.searchitemsapp.services;

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

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.parsers.CategoriaParserTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ListadoProductosServiceTest {

	private static Logger LOGGER = null;
	
	@Autowired
	ListadoProductosService listadoProductosService;
	
    @Autowired
    ServletContext context;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(CategoriaParserTest.class);  
    }
	
	@Test
	public void testService() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ServletContextEvent sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\db.properties",
				"db.properties", sve);
		
		String good = listadoProductosService.service("101","101","1","jamon","101");
		String good2 = listadoProductosService.service("101","101","2","miel","101");
		String bad = listadoProductosService.service("101","101","1",null,"101");
		String badPais = listadoProductosService.service(null,"101","1","cebolla","101");
		String badCategoria = listadoProductosService.service("101",null,"1","pimiento","101");
		String badOrdenacion = listadoProductosService.service("101","101",null,"salmon","101");
		String badEmpresas = listadoProductosService.service("101","101","2","te verde",null);
		String badValida = listadoProductosService.service("99","101","2","te verde",null);
		
		assertNotNull(good);
		assertNotNull(good2);
		assertNotNull(bad);
		assertNotNull(badPais);
		assertNotNull(badCategoria);
		assertNotNull(badOrdenacion);
		assertNotNull(badEmpresas);
		assertNotNull(badValida);
	}

}
