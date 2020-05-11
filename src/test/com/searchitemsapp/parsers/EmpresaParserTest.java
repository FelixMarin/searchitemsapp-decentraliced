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
				tbSiaEmpresa.getTbSiaCategoriasEmpresa(), 
				empresaDto.getTbSiaCategoriasEmpresa());		
		assertEquals("getTbSiaPais", 
				tbSiaEmpresa.getTbSiaPais(), 
				empresaDto.getTbSiaPais());		
		assertEquals("getTbSiaSelectoresCsses", 
				tbSiaEmpresa.getTbSiaSelectoresCsses(), 
				empresaDto.getTbSiaSelectoresCsses());
		
		//- Same -//
		assertSame(tbSiaEmpresa.getTbSiaSelectoresCsses(), 
				empresaDto.getTbSiaSelectoresCsses());
		assertSame(tbSiaEmpresa.getTbSiaPais(), 
				empresaDto.getTbSiaPais());
		assertSame(tbSiaEmpresa.getTbSiaCategoriasEmpresa(), 
				empresaDto.getTbSiaCategoriasEmpresa());
		
	}
	
	@Test
	public void toTbSia() {
		
		EmpresaDTO empresaDto = new EmpresaDTO();
		
		empresaDto.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		empresaDto.setTbSiaPais(new TbSiaPais());
		empresaDto.setTbSiaSelectoresCsses(new ArrayList<TbSiaSelectoresCss>());
		
		EmpresaParser parser = new EmpresaParser();
		TbSiaEmpresa tbSiaEmpresa = parser.toTbSia(empresaDto);
		
		//- Equals -//
		assertEquals("getTbSiaCategoriasEmpresa", 
			empresaDto.getTbSiaCategoriasEmpresa(), 
				tbSiaEmpresa.getTbSiaCategoriasEmpresa());
		assertEquals("getTbSiaPais", 
				empresaDto.getTbSiaPais(), 
				tbSiaEmpresa.getTbSiaPais());		
		assertEquals("getTbSiaSelectoresCsses", 
				empresaDto.getTbSiaSelectoresCsses(), 
				tbSiaEmpresa.getTbSiaSelectoresCsses());
		
		//- Same -//
		assertSame(empresaDto.getTbSiaSelectoresCsses(), 
				tbSiaEmpresa.getTbSiaSelectoresCsses());
		assertSame(empresaDto.getTbSiaPais(), 
				tbSiaEmpresa.getTbSiaPais());
		assertSame(empresaDto.getTbSiaCategoriasEmpresa(), 
				tbSiaEmpresa.getTbSiaCategoriasEmpresa());
		
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
		
		assertSame(lsEmpresas.get(0).getTbSiaCategoriasEmpresa(), 
				listEmpresaDTO.get(0).getTbSiaCategoriasEmpresa());		
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
