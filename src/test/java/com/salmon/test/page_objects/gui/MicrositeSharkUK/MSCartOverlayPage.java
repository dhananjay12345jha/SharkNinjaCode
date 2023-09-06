package com.salmon.test.page_objects.gui.MicrositeSharkUK;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSCartOverlayPage extends PageObject {
	
	private static final By isCartOverLaypagePresent = By.cssSelector("[id='overlay-page']");
	private static final By proceedLink = By.xpath("//a[@class='btn btn-lg btn-block btn-primary']");
	private static final By goToCheckoutButton = By.xpath("//button[@type='submit'][@name='checkout']");

	private static final Logger log = LoggerFactory.getLogger(MSHomePage.class);
	
	public By checkIsCartOverLaypagePresent() {
		return isCartOverLaypagePresent;
	}

	public boolean checkIsProceedLinkPresent() {
		return isElementClickable(proceedLink);
	}

	public boolean checkIsCheckoutButtonPresent() {
		return isElementClickable(goToCheckoutButton);
	}
	
	public boolean goToCheckout() {
		boolean flag=false;
		try {
			waitForExpectedElement(goToCheckoutButton).click();
			log.info("Successfully verified last product ID in link");
			flag = true;
		} catch (Exception e) {
			log.info("Some exception occurred while verifying last product ID in link-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean clickProceed() {
		boolean flag=false;
		try {
			waitForExpectedElement(proceedLink).click();
			log.info("Successfully clicked proceed link");
			flag = true;
		} catch (Exception e) {
			log.info("Some exception occurred while click on proceed link-->>"+e.getMessage());
		}
		return flag;
	}
	}