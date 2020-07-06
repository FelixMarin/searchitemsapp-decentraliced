package com.searchitemsapp.scraping.consum;

import org.openqa.selenium.WebDriver;

import com.searchitemsapp.scraping.IFScrapingEmpresas;

public interface IFScrapingConsum extends IFScrapingEmpresas {
	public String getHtmlContent(final WebDriver webDriver, final String strUrl) throws InterruptedException;
}
