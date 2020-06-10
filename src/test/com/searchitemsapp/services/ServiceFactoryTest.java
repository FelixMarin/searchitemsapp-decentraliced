package com.searchitemsapp.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.services.IFService;
import com.searchitemsapp.services.ServiceFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/context-servicefactory-test.xml"})
@WebAppConfiguration
public class ServiceFactoryTest {
	
	@Autowired
	ServiceFactory serviceFactory;

	@Before
	public void setUp() throws Exception {
		System.setProperty("log4j.properties", "log4j.properties");
		System.setProperty("db.properties", "db.properties");
		System.setProperty("flow.properties", "flow.properties");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetService() {
		IFService<String, String> ifs = (IFService<String, String>) serviceFactory.getService("LISTA_PRODUCTOS");
		assertNotNull(ifs);
	}

}
