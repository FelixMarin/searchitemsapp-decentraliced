package com.searchitemsapp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchitemsapp.parsers.CategoriaParser;
import com.searchitemsapp.parsers.EmpresaParser;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.parsers.LoginParser;
import com.searchitemsapp.parsers.MarcasParser;
import com.searchitemsapp.parsers.NomProductoParser;
import com.searchitemsapp.parsers.PaisParser;
import com.searchitemsapp.parsers.ParamsFormLoginParser;
import com.searchitemsapp.parsers.ParamsHeadersLoginParser;
import com.searchitemsapp.parsers.SelectoresCssParser;
import com.searchitemsapp.parsers.UrlParser;


/**
 * Clase Factory encargada de gestionar la creación de 
 * objetos de tipo service. Las peticiones a los services 
 * pasarán siempre por esta clase.
 * 
 * @author Felix Marin Ramirez
 *
 */
@Component
public class ParserFactory {

	/*
	 * Constantes Globales 
	 */
	private static final String URL_PARSER = "URL_PARSER";
	private static final String PARAMS_HEADERS_PARSER = "PARAMS_HEADERS_PARSER";
	private static final String PARAMS_FORM_PARSER = "PARAMS_FORM_PARSER";
	private static final String PAIS_PARSER = "PAIS_PARSER";
	private static final String NOM_PRODUCTO_PARSER = "NOM_PRODUCTO_PARSER";
	private static final String MARCAS_PARSER = "MARCAS_PARSER";
	private static final String LOGIN_PARSER = "LOGIN_PARSER";
	private static final String EMPRESA_PARSER = "EMPRESA_PARSER";
	private static final String CATEGORIA_PARSER = "CATEGORIA_PARSER";
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";

	/*
	 * Variables Globales
	 */
	@Autowired
	private UrlParser urlParser;
	
	@Autowired
	private SelectoresCssParser selectoresCssParser;
	
	@Autowired
	private CategoriaParser categoriaParser;
	
	@Autowired
	private EmpresaParser empresaParser;
	
	@Autowired
	private LoginParser loginParser;
	
	@Autowired
	private MarcasParser marcasParser;
	
	@Autowired
	private ParamsFormLoginParser paramsFormLoginParser;
	
	@Autowired
	private ParamsHeadersLoginParser paramsHeadersParser;
	
	@Autowired
	private NomProductoParser nomProductoParser;
	
	@Autowired
	private PaisParser paisParser;
	
	/*
	 * Constructor
	 */
	public ParserFactory() {
		super();
	}
		
	/**
	 * Método de la clase factory que gestiona la creación 
	 * de instancias de las clases de tipo parser.
	 * 
	 * @param nomParser
	 * @return IFParser<?, ?>
	 */
	public IFParser<?, ?> getParser(String nomParser) {
		
		if("".contentEquals(nomParser)) {
			return null;
		}
		
		if(URL_PARSER.equals(nomParser)) {
			return urlParser;
		} else if(SELECTORES_PARSER.equals(nomParser)) {
			return selectoresCssParser;
		} else if(CATEGORIA_PARSER.equals(nomParser)) {
			return categoriaParser;
		} else if(EMPRESA_PARSER.equals(nomParser)) {
			return empresaParser;
		} else if(LOGIN_PARSER.equals(nomParser)) {
			return loginParser;
		} else if(MARCAS_PARSER.equals(nomParser)) {
			return marcasParser;
		} else if(NOM_PRODUCTO_PARSER.equals(nomParser)) {
			return nomProductoParser;
		} else if(PAIS_PARSER.equals(nomParser)) {
			return paisParser;
		} else if(PARAMS_FORM_PARSER.equals(nomParser)) {
			return paramsFormLoginParser;
		} else if(PARAMS_HEADERS_PARSER.equals(nomParser)) {
			return paramsHeadersParser;
		}
		
		return null;
	}
}
