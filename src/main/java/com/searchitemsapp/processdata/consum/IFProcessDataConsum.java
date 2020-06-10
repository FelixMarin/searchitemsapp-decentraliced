package com.searchitemsapp.processdata.consum;

import org.openqa.selenium.WebDriver;

import com.searchitemsapp.processdata.IFProcessDataEmpresas;

public interface IFProcessDataConsum extends IFProcessDataEmpresas {
	public String getHtmlContent(final WebDriver webDriver, final String strUrl) throws InterruptedException;
}
