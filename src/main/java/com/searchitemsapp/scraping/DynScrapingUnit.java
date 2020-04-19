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

public class DynScrapingUnit extends Scraping {
	
	private static final String SCROLL_DOWN = "window.scrollTo(0, document.body.scrollHeight);";
	private static WebDriver webDriver;
	private static final int SELECTOR = 0;
	
	private DynScrapingUnit() {
		super();
	}
	
	public String getDynHtmlContent(final String strUrl, final int didEmpresa) throws InterruptedException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),DynScrapingUnit.class);
		
		String resultado;		
		System.getProperties().setProperty(initDriver(SELECTOR), 
				CommonsPorperties.getValue("flow.value.firefox.driver.path"));
		
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
	
	private void initWebDriver(final int selector) {
		if(selector == 1) {
			setupWebDriverChrome();
		} else {
			setupWebDriverFirefox();
		}
	}
	
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
	
	private String initDriver(final int selector) {
		if(selector == 1) {
			return CommonsPorperties.getValue("flow.value.chrome.driver");
		} else {
			return CommonsPorperties.getValue("flow.value.firefox.driver");
		}
	}

	public static WebDriver getWebDriver() {
		return webDriver;
	}

	public static void setWebDriver(WebDriver webDriver) {
		DynScrapingUnit.webDriver = webDriver;
	}
	
	
}

