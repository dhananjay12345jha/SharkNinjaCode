package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class PrivacyNoticePage extends PageObject{
	
	private final By heading = By.xpath("//div[@class='col-md-12']/div[1]/span");
	
	public String getHeading() {
		  return waitForExpectedElement(heading).getText();
	  }
}
