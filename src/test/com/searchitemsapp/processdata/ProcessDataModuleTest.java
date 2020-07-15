package com.searchitemsapp.processdata;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.config.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ProcessDataModuleTest {
	
	private static Logger LOGGER = null;
		
    @Autowired
    ServletContext context;
    
	@Autowired
	private ApplicationContext applicationContext;
	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(ProcessDataModuleTest.class);  
    }

	@Test
	public void testCheckHtmlDocument() throws IOException, URISyntaxException, InterruptedException {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ServletContextEvent sve =  new ServletContextEvent(context);
		List<IFProcessPrice> ls;
		UrlDTO urlDto = new UrlDTO();
		urlDto.setNomUrl("https://www.alcampo.es/compra-online/alimentacion/caldos-pasta-arroz-legumbres-pure/arroz/arroz-para-cocinar/la-cigala-arroz-redondo-1-kg/p/882610");
		urlDto.setDidEmpresa(108);
		urlDto.setSelectores(new SelectoresCssDTO());
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\flow.properties",
				"flow.properties", sve);
		
		CommonsPorperties.loadPropertiesFile(
				"E:\\eclipse-workspace\\properties\\db.properties",
				"db.properties", sve);		
		
		ProcessDataModule scrapingUnit = applicationContext.getBean(ProcessDataModule.class,urlDto,"sal","101","101","1");
		ls = scrapingUnit.call();
		
		assertNotNull(ls);
		
		scrapingUnit = applicationContext.getBean(ProcessDataModule.class,null,"sal","101","101","1");
		ls = scrapingUnit.call();
		
		assertNotNull(ls);
	}
}
