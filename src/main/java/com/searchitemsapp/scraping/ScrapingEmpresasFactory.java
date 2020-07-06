package com.searchitemsapp.scraping;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.scraping.alcampo.ScrapingAlcampo;
import com.searchitemsapp.scraping.caprabo.ScrapingCaprabo;
import com.searchitemsapp.scraping.carrefour.ScrapingCarrefour;
import com.searchitemsapp.scraping.condis.ScrapingCondis;
import com.searchitemsapp.scraping.consum.ScrapingConsum;
import com.searchitemsapp.scraping.dia.ScrapingDia;
import com.searchitemsapp.scraping.eci.ScrapingECI;
import com.searchitemsapp.scraping.eroski.ScrapingEroski;
import com.searchitemsapp.scraping.hipercor.ScrapingHipercor;
import com.searchitemsapp.scraping.lidl.ScrapingLidl;
import com.searchitemsapp.scraping.mercadona.ScrapingMercadona;
import com.searchitemsapp.scraping.simply.ScrapingSimply;
import com.searchitemsapp.scraping.ulabox.ScrapingUlabox;



/**
 * Clase Factory encargada de gestionar la creación de 
 * objetos de tipo service. Las peticiones a los services 
 * pasarán siempre por esta clase.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingEmpresasFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingEmpresasFactory.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String ID_EROSKI = "107";
	private static final String ID_LIDL = "102";
	private static final String ID_HIPERCOR = "103";
	private static final String ID_ELCORTEINGLES = "111";
	private static final String ID_DIA = "105";
	private static final String ID_SIMPLY = "114";
	private static final String ID_CONDIS = "110";
	private static final String ID_ULABOX = "106";
	private static final String ID_CARREFOUR = "104";
	private static final String ID_ALCAMPO = "108";
	private static final String ID_CAPRABO = "109";
	private static final String ID_MERCADONA = "101";
	private static final String ID_CONSUM = "116";

	/*
	 * Variables Globales
	 */
	@Autowired
	private ScrapingAlcampo scrapingAlcapo;
	
	@Autowired
	private ScrapingCaprabo scrapingCaprabo;
	
	@Autowired
	private ScrapingCarrefour scrapingCarrefour;
	
	@Autowired
	private ScrapingCondis scrapingCondis;
	
	@Autowired
	private ScrapingConsum scrapingConsum;
	
	@Autowired
	private ScrapingDia scrapingDia;
	
	@Autowired
	private ScrapingECI scrapingECI;
	
	@Autowired
	private ScrapingEroski scrapingEroski;
	
	@Autowired
	private ScrapingHipercor scrapingHipercor;
	
	@Autowired
	private ScrapingMercadona scrapingMercadona;
	
	@Autowired
	private ScrapingSimply scrapingSimply;
	
	@Autowired
	private ScrapingUlabox scrapingUlabox;
	
	@Autowired
	private ScrapingLidl scrapingLidl;	
	
	/*
	 * Constructor
	 */
	public ScrapingEmpresasFactory() {
		super();
	}
	
	/**
	 * Método de la clase factory que gestiona la creación 
	 * de instancias de las clases de scraping de empresas.
	 * 
	 * @param idEmpresa
	 * @return IFScrapingEmpresas
	 */
	public IFScrapingEmpresas getScrapingEmpresa(final int idEmpresa) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		IFScrapingEmpresas ifs = null;
		
		/**
		 * Dependiendo del valor de entrada devolverá uno u otro objeto.
		 * Nulo si el parámetro de entrada no coincide con lo esperado.
		 */
		if(NumberUtils.toInt(ID_ALCAMPO) == idEmpresa) {
			ifs = scrapingAlcapo;
		} else if(NumberUtils.toInt(ID_CAPRABO) == idEmpresa) {
			ifs = scrapingCaprabo;
		} else if(NumberUtils.toInt(ID_CARREFOUR) == idEmpresa) {
			ifs = scrapingCarrefour;
		} else if(NumberUtils.toInt(ID_CONDIS) == idEmpresa) {
			ifs = scrapingCondis;
		} else if(NumberUtils.toInt(ID_CONSUM) == idEmpresa) {
			ifs = scrapingConsum;
		} else if(NumberUtils.toInt(ID_DIA) == idEmpresa) {
			ifs = scrapingDia;
		} else if(NumberUtils.toInt(ID_ELCORTEINGLES) == idEmpresa) {
			ifs = scrapingECI;
		} else if(NumberUtils.toInt(ID_EROSKI) == idEmpresa) {
			ifs = scrapingEroski;
		} else if(NumberUtils.toInt(ID_HIPERCOR) == idEmpresa) {
			ifs = scrapingHipercor;
		} else if(NumberUtils.toInt(ID_MERCADONA) == idEmpresa) {
			ifs = scrapingMercadona;
		} else if(NumberUtils.toInt(ID_SIMPLY) == idEmpresa) {
			ifs = scrapingSimply;
		} else if(NumberUtils.toInt(ID_ULABOX) == idEmpresa) {
			ifs = scrapingUlabox;
		} else if(NumberUtils.toInt(ID_LIDL) == idEmpresa) {
			ifs = scrapingLidl;
		}
		
		return ifs;
	}
}
