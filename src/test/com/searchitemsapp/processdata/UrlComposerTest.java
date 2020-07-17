package com.searchitemsapp.processdata;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Maps;
import com.searchitemsapp.config.CommonsPorperties;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.SelectoresCssImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class UrlComposerTest {
	
	private static Logger LOGGER = null;
	
	@Autowired
	UrlComposer urlComposer;
	
	@Autowired
	SelectoresCssImpl selectores;
	
    @Autowired
    ServletContext context;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(UrlComposerTest.class);  
        
    }
	
	@Test
	public void testReplaceWildcardCharacter() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		Map<String,EmpresaDTO> mapEmpresas = Maps.newHashMap();
		Map<Integer,Boolean> mapDynEmpresas= Maps.newHashMap();
		
		ServletContextEvent sve =  new ServletContextEvent(context);
		List<UrlDTO> url = null;
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\workspace_sts\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\workspace_sts\\properties\\db.properties",
				"db.properties", sve);
		try {
			
			urlComposer.applicationData(mapEmpresas, mapDynEmpresas);
			url = urlComposer.replaceWildcardCharacter(
					"101","101","sal","101",selectores.findAll(), mapEmpresas);
			
		} catch (IOException e) {
			LOGGER.error("ERROR",e);
		}
		
		assertNotNull(url);
	}

}
