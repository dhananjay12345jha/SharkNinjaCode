package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.step_definitions.api.CustomerAPISteps;
import com.salmon.test.step_definitions.gui.HomePageSteps;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerCarePage extends PageObject{
	
	private final By heading = By.xpath("//div[@class='col-md-12']/h1");
	private final By introTitle = By.xpath("//div[@class='intro-title']");
	protected static final Logger LOG = LoggerFactory.getLogger(CustomerCarePage.class);
	
	public String getCustomerCareHeading() {
		  return waitForExpectedElement(heading).getText();
	  }
	
	public String getCustomerCareIntroTitle() {
		String text = "Not Found";
		try{
			IsPageLoaded.waitAllRequest();
			text=getCurrentUrl();
			LOG.info("Successfully found text on customer care page which is---->>>"+text);
		}catch (Exception e){
			LOG.error("Unable to fetch text from customer care page--->>>"+ ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
		return text;
	  }

}
