package com.searchitemsapp.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.scraping.IFScrapingEmpresas;
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
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.StringUtils;

public class ScrapingEmpFactory {

	public ScrapingEmpFactory() {
		super();
	}
	
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
	
	public IFScrapingEmpresas getScrapingEmpresa(final int idEmpresa) {
		
		if(StringUtils.desformatearEntero(StringUtils.ID_ALCAMPO) == idEmpresa) {
			return scrapingAlcapo;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_CAPRABO) == idEmpresa) {
			return scrapingCaprabo;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_CARREFOUR) == idEmpresa) {
			return scrapingCarrefour;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_CONDIS) == idEmpresa) {
			return scrapingCondis;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_CONSUM) == idEmpresa) {
			return scrapingConsum;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_DIA) == idEmpresa) {
			return scrapingDia;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_ELCORTEINGLES) == idEmpresa) {
			return scrapingECI;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_EROSKI) == idEmpresa) {
			return scrapingEroski;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_HIPERCOR) == idEmpresa) {
			return scrapingHipercor;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_MERCADONA) == idEmpresa) {
			return scrapingMercadona;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_SIMPLY) == idEmpresa) {
			return scrapingSimply;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_ULABOX) == idEmpresa) {
			return scrapingUlabox;
		} else if(StringUtils.desformatearEntero(StringUtils.ID_LIDL) == idEmpresa) {
			return scrapingLidl;
		}
		
		return (IFScrapingEmpresas) ClaseUtils.NULL_OBJECT;
	}

}
