package com.searchitemsapp.scraping;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.parsers.CategoriaParserTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-servicefactory-test.xml")
@WebAppConfiguration
public class ScrapingUnitTest {
	
	private static Logger LOGGER = null;
	
	@Autowired
	ScrapingUnit scrapingUnit;
	
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(CategoriaParserTest.class);  
    }

	@Test
	public void testScrapingUnit() {

		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		
	}

	@Test
	public void testCheckHtmlDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testCall() {
		fail("Not yet implemented");
	}

	@Test
	public void testScrapingLoginUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckingHtmlLoginDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractScraping() {
		fail("Not yet implemented");
	}

	@Test
	public void testApplicationData() {
		fail("Not yet implemented");
	}

	@Test
	public void testCargarTodasLasMarcas() {
		fail("Not yet implemented");
	}

	@Test
	public void testListSelectoresCssPorEmpresa() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatusConnectionCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHtmlDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testUrlsPaginacion() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectScrapPattern() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarTildes() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePatternProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testElementoPorCssSelector() {
		fail("Not yet implemented");
	}

	@Test
	public void testFiltroMarca() {
		fail("Not yet implemented");
	}

	@Test
	public void testFillDataResultadoDTO() {
		fail("Not yet implemented");
	}

	@Test
	public void testTratarProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testReeplazarTildesCondis() {
		fail("Not yet implemented");
	}

	@Test
	public void testReeplazarCaracteresCondis() {
		fail("Not yet implemented");
	}

	@Test
	public void testReemplazarCaracteresEroski() {
		fail("Not yet implemented");
	}

	@Test
	public void testReeplazarCaracteresSimply() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractScrapingDyn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDynHtmlContent() {
		fail("Not yet implemented");
	}

}
