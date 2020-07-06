package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.model.TbSiaSelectoresCss;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context.xml")
@WebAppConfiguration
public class EmpresaParserTest {
	
	 private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LogManager.getRootLogger();
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		TbSiaEmpresa tbSiaEmpresa = new TbSiaEmpresa();
		
		tbSiaEmpresa.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaEmpresa.setTbSiaPais(new TbSiaPais());
		tbSiaEmpresa.setTbSiaSelectoresCsses(new ArrayList<TbSiaSelectoresCss>());
		
		EmpresaParser parser = new EmpresaParser();
		EmpresaDTO empresaDto = parser.toDTO(tbSiaEmpresa);
		
		//- Equals -//
		assertEquals("getTbSiaCategoriasEmpresa", 
				tbSiaEmpresa.getDid(), 
				empresaDto.getDid());		
		assertEquals("getTbSiaPais", 
				tbSiaEmpresa.getDid(), 
				empresaDto.getDid());		
		assertEquals("getTbSiaSelectoresCsses", 
				tbSiaEmpresa.getDid(), 
				empresaDto.getDid());
		
		//- Same -//
		assertSame(tbSiaEmpresa.getDid(), 
				empresaDto.getDid());
		assertSame(tbSiaEmpresa.getDid(), 
				empresaDto.getDid());
		assertSame(tbSiaEmpresa.getDid(), 
				empresaDto.getDid());
		
	}
	
	@Test
	public void toTbSia() {
		
		EmpresaDTO empresaDto = new EmpresaDTO();
				
		EmpresaParser parser = new EmpresaParser();
		TbSiaEmpresa tbSiaEmpresa = parser.toTbSia(empresaDto);
		
		//- Equals -//
		assertEquals("getTbSiaCategoriasEmpresa", 
			empresaDto.getDid(), 
				tbSiaEmpresa.getDid());
		assertEquals("getTbSiaPais", 
				empresaDto.getDid(), 
				tbSiaEmpresa.getDid());		
		assertEquals("getTbSiaSelectoresCsses", 
				empresaDto.getDid(), 
				tbSiaEmpresa.getDid());
		
		//- Same -//
		assertSame(empresaDto.getDid(), 
				tbSiaEmpresa.getDid());
		assertSame(empresaDto.getDid(), 
				tbSiaEmpresa.getDid());
		assertSame(empresaDto.getDid(), 
				tbSiaEmpresa.getDid());
		
	}
	
	@Test
	public void toListDTO() {
		
		List<TbSiaEmpresa> lsEmpresas = new ArrayList<TbSiaEmpresa>();
		lsEmpresas.add(new TbSiaEmpresa());
		lsEmpresas.add(new TbSiaEmpresa());
		
		List<EmpresaDTO> listEmpresaDTO = new ArrayList<EmpresaDTO>();
		EmpresaParser parser = new EmpresaParser();
		listEmpresaDTO = parser.toListDTO(lsEmpresas);
		
		assertEquals("size", 
				lsEmpresas.size(), listEmpresaDTO.size());
		
		assertSame(lsEmpresas.get(0).getDid(), 
				listEmpresaDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsEmpresas = new ArrayList<Object[]>();
		lsEmpresas.add(new Object[5]);
		lsEmpresas.add(new Object[4]);
		
		List<EmpresaDTO> listEmpresaDTO = new ArrayList<EmpresaDTO>();
		EmpresaParser parser = new EmpresaParser();
		listEmpresaDTO = parser.toListODTO(lsEmpresas);
			
		assertNotEquals(lsEmpresas, listEmpresaDTO);
	}
}
