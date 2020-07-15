package com.searchitemsapp.processdata.empresas;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jsoup.nodes.Document;
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
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ProcessDataAlcampoTest {
	
	private static Logger LOGGER = null;
	
	 @Autowired
	 ServletContext context;
	 	 
	 @Autowired
	 ProcessDataAlcampo pda;
	 
	 @Autowired
	 UrlDTO urlDTO;
	 
	    @BeforeClass
	    public static void setLogger() throws MalformedURLException {
	    	org.apache.log4j.BasicConfigurator.configure();
	        System.setProperty("log4j.properties","log4j.properties");
	        System.setProperty("db.properties","db.properties");
	        System.setProperty("flow.properties","flow.properties");
	        LOGGER = LoggerFactory.getLogger(ProcessDataAlcampoTest.class);  
	    }

	@Test
	public void testGetListaUrls() throws IOException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ServletContextEvent sve =  new ServletContextEvent(context);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\db.properties",
				"db.properties", sve);
		
		Map<String,String> map = Maps.newHashMap(); 
		map.put("SEL_PAGINACION", "div|href");
		urlDTO.setSelectores(new SelectoresCssDTO()); 
		Document document = new Document("<html></html>");
		document.appendElement("div");
		
		List<String> lurl = pda.getListaUrls(document, urlDTO);
		
		assertNotNull(lurl);
	}

}
