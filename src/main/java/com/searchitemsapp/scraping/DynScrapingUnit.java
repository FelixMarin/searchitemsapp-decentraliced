package com.searchitemsapp.scraping;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;

/**
 * Módulo de web scraping dinámico. Esta clase contiene la
 * lógica necesaria para extraer información de sitios web
 * que se forman de manera dinámica. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class DynScrapingUnit extends Scraping {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynScrapingUnit.class);  
	
	/*
	 * Constantes Globales
	 */
	private final String CONSUM = "CONSUM";
	private final String SCROLL_DOWN = "window.scrollTo(0, document.body.scrollHeight);";	
	
	/*
	 * Variables Globales
	 */
	/**
	 * El web driver es estático porque 
	 * solo se tiene que crear una sola vez.
	 */
	private static WebDriver webDriver;
	
	/*
	 * Constructor
	 */
	private DynScrapingUnit() {
		super();
	}
	
	/**
	 * Método que permite extraer informacíon de una web que 
	 * se construye dinámicamente en el navegador y no existe
	 * una fuente html como tal.
	 * 
	 * @param strUrl
	 * @param didEmpresa
	 * @return String
	 * @throws InterruptedException
	 */
	public String getDynHtmlContent(final String strUrl, final int didEmpresa) throws InterruptedException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		String resultado;	
		
		/**
		 * Se añade el driver a las propiedades globales del sistema.
		 */
		System.getProperties().setProperty(initDriver(0), 
				CommonsPorperties.getValue("flow.value.firefox.driver.path"));
		
		/**
		 * Se inicilaiza y configura el driver.
		 */
		initWebDriver(0);
		
		if(getMapEmpresas().get(CONSUM) == didEmpresa) {			
			resultado = getHtmlContextConsum(webDriver, strUrl);
		} else {
			webDriver.navigate().to(strUrl);
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript(SCROLL_DOWN);
			resultado = webDriver.getPageSource();
		}
		
		if(Objects.nonNull(webDriver)) {
			clieanWindows();
		}
		 
		return resultado;
	}
	
	/**
	 * Genera un WebDriver para controlar el navegador (Chrome o Firefox)
	 * dependiendo del párametro insertado.
	 * 
	 * Hay dos posibilidades:
	 * 
	 * 		- Chrome Web Driver
	 * 			ò
	 * 		- Firefox Web Driver
	 * 
	 * @param selector
	 */
	private void initWebDriver(final int selector) {
		if(selector == 1) {
			setupWebDriverChrome();
		} else {
			setupWebDriverFirefox();
		}
	}
	
	/**
	 * Configuración del web driver de Chrome.
	 * 
	 * Un WebDriver es una herramienta para 
	 * automatizar extraciones de datos de 
	 * aplicaciones Web.
	 */
	private void setupWebDriverChrome() {
		
		if(Objects.isNull(webDriver)) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--incognito");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			ChromeDriverService chromeService = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(CommonsPorperties.getValue("flow.value.firefox.driver.path")))
                    .usingAnyFreePort()
                    .build();
			dc.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(dc);
			webDriver = new ChromeDriver(chromeService, options);
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Configuración del web driver de Firefox.
	 * 
	 * Un WebDriver es una herramienta para 
	 * automatizar extraciones de datos de 
	 * aplicaciones Web.
	 */
	private void setupWebDriverFirefox() {
		
		if(Objects.isNull(webDriver)) {
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(CommonsPorperties.getValue("folw.value.firefox.ejecutable.path"));
			options.addArguments("-headless");
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("moz:firefoxOptions", options);
			options.merge(dc);
			webDriver = new FirefoxDriver(options);
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);			
		}
	}
	
	/**
	 * Funcionalidad para cerrar las ventanas que puedan
	 * quedar abiertas en el buscador headless.
	 */
	private void clieanWindows() {            
        Set<String> windows = webDriver.getWindowHandles();
        Iterator<String> iter = windows.iterator();
        String[] winNames=new String[windows.size()];
        int i=0;
        while (iter.hasNext()) {
            winNames[i]=iter.next();
            i++;
        }

        if(winNames.length > 1) {
            for(i = winNames.length; i > 1; i--) {
            	webDriver.switchTo().window(winNames[i - 1]);
            	webDriver.close();
            }
        }
        webDriver.switchTo().window(winNames[0]);
    }
	
	/**
	 * Devuelve el controlador del web driver.
	 * 
	 * @param selector
	 * @return String
	 */
	private String initDriver(final int selector) {
		if(selector == 1) {
			return CommonsPorperties.getValue("flow.value.chrome.driver");
		} else {
			return CommonsPorperties.getValue("flow.value.firefox.driver");
		}
	}
}

