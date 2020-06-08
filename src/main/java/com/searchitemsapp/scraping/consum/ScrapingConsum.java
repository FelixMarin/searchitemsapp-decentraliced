package com.searchitemsapp.scraping.consum;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.UrlDTO;


/**
 * Módulo de scraping especifico diseñado para la 
 * extracción de datos del sitio web de Consum.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingConsum implements IFScrapingConsum {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingConsum.class);   

	/* XPATH_EXPRESSION : /html/body/div[1]/div/div[1]/div/div[2]/div/div/div/div/div/mod-client-catalog/div/mod-catalog/div/lib-grid/div/div/div[2]/div[2]/div[3]/div[2]/button */
	private static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true)";

	private ScrapingConsum() {
		super();
	}
	
	/**
	 * Compone una lista de URLs de la empresa Consum.
	 * Con estas URLs se realizarán las peticiones al
	 * sitio web para extraer los datos. 
	 * 
	 * @param document
	 * @param urlDto
	 * @param selectorCssDto
	 * @return List<String>
	 * @exception MalformedURLException
	 */
	@Override
	public List<String> getListaUrls(Document document, UrlDTO urlDto)
			throws MalformedURLException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Se obtiene la URL y se añade en una lista que
		 * será retornada.
		 */
		String urlBase = urlDto.getNomUrl();
		List<String> listaUrls = new ArrayList<>(NumberUtils.INTEGER_ONE);
		listaUrls.add(urlBase);
		
		return listaUrls;
	}

	/**
	 * Se obtiene el sitio web utilizando el web driver. 
	 * De este modo se puede obtener un sitio web después 
	 * de ejecutar javascript.
	 * 
	 * @param webDriver
	 * @param strUrl
	 * @return String
	 * @throws InterruptedException
	 */
	public String getHtmlContent(final WebDriver webDriver, final String strUrl) throws InterruptedException {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Variables
		 */
		WebElement wButton;
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		boolean isButton = Boolean.TRUE;
		
		/**
		 * se añade la URL en el objeto web driver y se le asigna
		 * un tiempo de espera. Ese tiempo de espera permite que
		 * se carge la página web en el navegador headless.
		 */
		webDriver.get(strUrl);		
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		
		/**
		 * Este bloque simula hacer click el el boton de página siguiente
		 * en la web del producto y mediante scroll down permite que se
		 * carguen todos los elementos. 
		 * Devuelve 
		 */
		try {
			do {				
				Thread.sleep(2000);
				wButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("grid__footer-viewMore")));
				if (Objects.isNull(wButton)) {
					isButton = Boolean.FALSE;
				} else {
					js.executeScript(SCROLL_INTO_VIEW, wButton);
					wButton.click();
				}
			} while (isButton);
		} catch (ElementNotVisibleException | TimeoutException | ElementClickInterceptedException e) {
			if(LOGGER.isWarnEnabled()) {
				LOGGER.warn(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}

		/**
		 * Get the source of the last loaded page.
		 */
		return webDriver.getPageSource();
	}
}
