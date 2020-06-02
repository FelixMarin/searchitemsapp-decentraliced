package com.searchitemsapp.controller;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/context-servicefactory-test.xml")
@WebAppConfiguration
public class ListaProductosControllerTest {
	
	 private static Logger LOGGER = null;

	@Autowired
	private WebApplicationContext wac;
	    
    private MockMvc mockMvc;
    
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(ListaProductosControllerTest.class);  
    }
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        
    }
    
	@Test
	public void testListaProductos() throws Exception {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());
				
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/search/")
			      .header("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.status().isOk())
			      .andReturn();
	}
}
