package com.searchitemsapp.scraping;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dao.PaisDao;
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
public class ScrapingEmpFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaisDao.class);  
	
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
	public ScrapingEmpFactory() {
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
		
		/**
		 * Dependiendo del valor de entrada devolverá uno u otro objeto.
		 * Nulo si el parámetro de entrada no coincide con lo esperado.
		 */
		if(this.desformatearEntero(ID_ALCAMPO) == idEmpresa) {
			return scrapingAlcapo;
		} else if(this.desformatearEntero(ID_CAPRABO) == idEmpresa) {
			return scrapingCaprabo;
		} else if(this.desformatearEntero(ID_CARREFOUR) == idEmpresa) {
			return scrapingCarrefour;
		} else if(this.desformatearEntero(ID_CONDIS) == idEmpresa) {
			return scrapingCondis;
		} else if(this.desformatearEntero(ID_CONSUM) == idEmpresa) {
			return scrapingConsum;
		} else if(this.desformatearEntero(ID_DIA) == idEmpresa) {
			return scrapingDia;
		} else if(this.desformatearEntero(ID_ELCORTEINGLES) == idEmpresa) {
			return scrapingECI;
		} else if(this.desformatearEntero(ID_EROSKI) == idEmpresa) {
			return scrapingEroski;
		} else if(this.desformatearEntero(ID_HIPERCOR) == idEmpresa) {
			return scrapingHipercor;
		} else if(this.desformatearEntero(ID_MERCADONA) == idEmpresa) {
			return scrapingMercadona;
		} else if(this.desformatearEntero(ID_SIMPLY) == idEmpresa) {
			return scrapingSimply;
		} else if(this.desformatearEntero(ID_ULABOX) == idEmpresa) {
			return scrapingUlabox;
		} else if(this.desformatearEntero(ID_LIDL) == idEmpresa) {
			return scrapingLidl;
		}
		
		return null;
	}
	
	
	/**
	 * Convierte una cadena a tipo int
	 *
	 * @param pStrCadena
	 * @return int
	 */
	private int desformatearEntero(String pStrCadena) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		int iResultado = 0;
		if (!StringUtils.EMPTY.contentEquals(pStrCadena)) {
			try {
				iResultado = Integer.parseInt(pStrCadena);
			} catch (NumberFormatException nfe) {
				if(LOGGER.isInfoEnabled()) {
					LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),nfe);
				}
			}
		}
		return iResultado;
	}

}
