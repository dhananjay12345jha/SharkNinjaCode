package com.salmon.test.page_objects.gui.SNAP;

import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;

public class ZendeskLoginProcessPage extends PageObject {

	private static final By zendeskLoginEmail = By.cssSelector("#user_email");
	private static final By zendeskLoginPassword = By.xpath("//input[@id='user_password']");
	
	private static final By zendeskSignInButton = By.id("sign-in-submit-button");
	

	public void submitZendeskLoginCredentials() {
		//webDriver.switchTo().frame(0);
		waitForExpectedElement(zendeskLoginEmail).sendKeys(Props.getProp("zendesk.login.email"));
		waitForExpectedElement(zendeskLoginPassword).sendKeys(Props.getProp("zendesk.login.password"));
		waitForExpectedElement(zendeskSignInButton).click();
	}	
	
}
