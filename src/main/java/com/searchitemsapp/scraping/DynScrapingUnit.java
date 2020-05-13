package com.searchitemsapp.scraping;

import java.io.File;
import java.util.Iterator;
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

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Módulo de web scraping dinámico. Esta clase contiene la
 * lógica necesaria para extraer información de sitios web
 * que se forman de manera dinámica. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class DynScrapingUnit extends Scraping {
	
	
	/*
	 * Variables Globales
	 */
	private static WebDriver webDriver;
	
	/*
	 * Constantes Globales
	 */
	private static final String SCROLL_DOWN = "window.scrollTo(0, document.body.scrollHeight);";	
	private static final int SELECTOR = 0;
	
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),DynScrapingUnit.class);
		
		String resultado;	
		
		/**
		 * Se añade el driver a las propiedades globales del sistema.
		 */
		System.getProperties().setProperty(initDriver(SELECTOR), 
				CommonsPorperties.getValue("flow.value.firefox.driver.path"));
		
		/**
		 * Se inicilaiza y configura el driver.
		 */
		initWebDriver(SELECTOR);
		
		if(getMapEmpresas().get(StringUtils.CONSUM) == didEmpresa) {			
			resultado = getHtmlContextConsum(webDriver, strUrl);
		} else {
			webDriver.navigate().to(strUrl);
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript(SCROLL_DOWN);
			resultado = webDriver.getPageSource();
		}
		
		if(!ClaseUtils.isNullObject(webDriver)) {
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
		
		if(ClaseUtils.isNullObject(getWebDriver())) {
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
			setWebDriver(new ChromeDriver(chromeService, options));	
			getWebDriver().manage().window().maximize();
			getWebDriver().manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			getWebDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
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
		
		if(ClaseUtils.isNullObject(getWebDriver())) {
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(CommonsPorperties.getValue("folw.value.firefox.ejecutable.path"));
			options.addArguments("-headless");
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("moz:firefoxOptions", options);
			options.merge(dc);
			setWebDriver(new FirefoxDriver(options));
			getWebDriver().manage().window().maximize();
			getWebDriver().manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			getWebDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);			
		}
	}
	
	/**
	 * Funcionalidad para cerrar las ventanas que puedan
	 * quedar abiertas en el buscador headless.
	 */
	private void clieanWindows() {            
        Set<String> windows = getWebDriver().getWindowHandles();
        Iterator<String> iter = windows.iterator();
        String[] winNames=new String[windows.size()];
        int i=ClaseUtils.ZERO_INT;
        while (iter.hasNext()) {
            winNames[i]=iter.next();
            i++;
        }

        if(winNames.length > ClaseUtils.ONE_INT) {
            for(i = winNames.length; i > ClaseUtils.ONE_INT; i--) {
            	getWebDriver().switchTo().window(winNames[i - ClaseUtils.ONE_INT]);
            	getWebDriver().close();
            }
        }
        getWebDriver().switchTo().window(winNames[ClaseUtils.ZERO_INT]);
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

	/**
	 * 
	 * @return {@link WebDriver}
	 */
	public static WebDriver getWebDriver() {
		return webDriver;
	}
	public static void setWebDriver(WebDriver webDriver) {
		DynScrapingUnit.webDriver = webDriver;
	}	
}

