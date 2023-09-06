package com.salmon.test.page_objects.gui;

import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;

public class PaymentInstrumentInfoPage extends PageObject {

	private final By heading = By.xpath("//div[@class='row account-main']/div/h1");
	
	public String getHeading() {
		  return waitForExpectedElement(heading).getText();
	  }

	
}
