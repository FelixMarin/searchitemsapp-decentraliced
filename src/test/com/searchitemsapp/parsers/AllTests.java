package com.searchitemsapp.parsers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		CategoriaParserTest.class, 
		EmpresaParserTest.class, 
		LoginParserTest.class,
		MarcasParserTest.class,
		NomProductoParserTest.class,
		PaisParserTest.class,
		ParamsFormLoginParserTest.class,
		ParamsHeadersLoginParserTest.class,
		SelectoresCssParserTest.class,
		UrlParserTest.class,
		UrlParserTest.class })

public class AllTests {

}
