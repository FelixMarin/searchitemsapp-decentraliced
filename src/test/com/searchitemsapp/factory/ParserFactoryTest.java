package com.searchitemsapp.factory;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaUrl;
import com.searchitemsapp.parsers.IFParser;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/resources/context-parserfactory-test.xml"})
@WebAppConfiguration
public class ParserFactoryTest {

	private static Logger LOGGER = null;
	
	@Autowired
	private ParserFactory parserFactory;
	
	@BeforeClass
	public static void setLogger() throws MalformedURLException {
		org.apache.log4j.BasicConfigurator.configure();
		System.setProperty("log4j.properties", "log4j.properties");
		System.setProperty("db.properties", "db.properties");
		System.setProperty("flow.properties", "flow.properties");
		LOGGER = LogManager.getRootLogger();
	}

	@Test
	public void test() {
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
		IFParser<UrlDTO, TbSiaUrl> ife = (IFParser<UrlDTO, TbSiaUrl>) parserFactory.getParser("URL_PARSER");
		assertNotNull(ife.toDTO(new TbSiaUrl()));
	}

}
