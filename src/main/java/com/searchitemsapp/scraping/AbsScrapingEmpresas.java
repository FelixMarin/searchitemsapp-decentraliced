package com.searchitemsapp.scraping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.scraping.dia.ScrapingDia;

public abstract class AbsScrapingEmpresas {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingDia.class); 
	
	/*
	 * Constructor
	 */
	public AbsScrapingEmpresas() {
		super();
	}
	
	/**
	 * Convierte una cadena a tipo int
	 *
	 * @param pStrCadena
	 * @return int
	 */
	protected int desformatearEntero(String pStrCadena) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		int iResultado = 0;
		if (!"".contentEquals(pStrCadena)) {
			try {
				iResultado = Integer.parseInt(pStrCadena);
			} catch (NumberFormatException nfe) {
				if(LOGGER.isWarnEnabled()) {
					LOGGER.warn(Thread.currentThread().getStackTrace()[1].toString(),nfe);
				}
			}
		}
		return iResultado;
	}
}
