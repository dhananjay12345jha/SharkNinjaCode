package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;

public class OffersPage extends PageObject{

	private final By teaserText = By.xpath("//div[@class='marketing-area']//strong");
	//private final By teserTextDE = By.xpath("//div[@class='eventTimer-heading']");
	
	public String getTeaserText() {
//		String profile=System.getProperty("profileId").toLowerCase();
//		if(profile.contains("ninjade")){
//			String text=waitForExpectedElement(teserTextDE).getText().trim();
//			text=text.replace("\n"," ");
//			return text;
//		}
//		else {
		return waitForExpectedElement(teaserText).getText().trim();
	//	}
	}
}
