package com.searchitemsapp.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.entities.TbSiaCategoriasEmpresa;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaPais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context-parser-test.xml")
@WebAppConfiguration
public class EmpresaParserTest {
	
	 private static Logger LOGGER = null;
	 
	 @Autowired
	 IFParser<EmpresaDTO, TbSiaEmpresa> parser;

	 @Autowired
	 TbSiaEmpresa tbSiaEmpresa;
	 
	 @Autowired
	 EmpresaDTO empresaDto;
	 
    @BeforeClass
    public static void setLogger() throws MalformedURLException {
    	org.apache.log4j.BasicConfigurator.configure();
        System.setProperty("log4j.properties","log4j.properties");
        System.setProperty("db.properties","db.properties");
        System.setProperty("flow.properties","flow.properties");
        LOGGER = LoggerFactory.getLogger(EmpresaParserTest.class);  
    }

	@Test
	public void toDTO() {
		
		LOGGER.debug(Thread.currentThread().getStackTrace()[1].toString());

		tbSiaEmpresa.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaEmpresa.setTbSiaPais(new TbSiaPais());
		tbSiaEmpresa.setTbSiaSelectoresCsses(Lists.newArrayList());
		
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
		
		empresaDto.setDid(101);
		empresaDto.setUrls(Maps.newHashMap());
		Map<String, String> lhm = Maps.newHashMap();
		lhm.put("DID", "101");
		lhm.put("BOL_ACTIVO", "true");
		lhm.put("FEC_MODIFICACION", "2020-06-05");
		empresaDto.setSelectores(lhm);
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
		
		List<TbSiaEmpresa> lsEmpresas = Lists.newArrayList();
		tbSiaEmpresa.setTbSiaCategoriasEmpresa(new TbSiaCategoriasEmpresa());
		tbSiaEmpresa.setTbSiaPais(new TbSiaPais());
		tbSiaEmpresa.setTbSiaSelectoresCsses(Lists.newArrayList());
		tbSiaEmpresa.setTbSiaUrls(Lists.newArrayList());
		lsEmpresas.add(tbSiaEmpresa);
		
		List<EmpresaDTO> listEmpresaDTO = parser.toListDTO(lsEmpresas);
		
		assertEquals("size", 
				lsEmpresas.size(), listEmpresaDTO.size());
		
		assertSame(lsEmpresas.get(0).getDid(), 
				listEmpresaDTO.get(0).getDid());		
	}

	@Test
	public void toListODTO() {
		
		List<Object[]> lsEmpresas = Lists.newArrayList();
		lsEmpresas.add(new Object[5]);
		lsEmpresas.add(new Object[4]);
		
		List<EmpresaDTO> listEmpresaDTO = Lists.newArrayList();
		listEmpresaDTO = parser.toListODTO(lsEmpresas);
			
		assertNotEquals(lsEmpresas, listEmpresaDTO);
	}
}
