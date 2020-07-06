package com.searchitemsapp.processprice;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.ResultadoDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ProcessPriceTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProcessPriceTest.class); 
	    	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
    }

	@Test
	public void testProcessPrice() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
		ResultadoDTO a = new ResultadoDTO();
		ResultadoDTO b = new ResultadoDTO();
		
		a.setOrdenacion(1);
		a.setPrecio("0,00");
		b.setPrecio("1,00");
		
		int i = ProcessPrice.processPrice(a, b);
		
		assertEquals(-1, i);
		
		a.setPrecio("0,00");
		b.setPrecio("0,00");
		
		i = ProcessPrice.processPrice(a, b);
		
		assertEquals(0, i);
		
		a.setPrecio("1,00");
		b.setPrecio("0,00");
		
		i = ProcessPrice.processPrice(a, b);
		
		assertEquals(1, i);
	}

}
