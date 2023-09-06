package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountOverviewPage extends PageObject{
	
	private final By welcomeText = By.xpath("//h1[@class='account-welcome-text']");
	private final By myOrder = By.xpath("//span[@class='text-orderHistory']");
	private final By mobileMyOrder = By.xpath("//select[@class='form-control']/option[2]");
	private final By mobileProductOverview = By.xpath("//select[@class='form-control']/option[3]");
	private final By mobilePayment = By.xpath("//select[@class='form-control']/option[4]");
	private final By mobileAddress = By.xpath("//select[@class='form-control']/option[5]");
	private final By mobileAccountDetails = By.xpath("//select[@class='form-control']/option[6]");
	private final By productOverview = By.xpath("(//span[@class='account_payment_text'])[1]");
	private final By payment = By.xpath("(//span[@class='account_payment_text'])[2]");
	private final By address = By.xpath("//span[@class='account-text-adresses']");
	private final By accountDetails = By.xpath("//span[@class='text-accountDetails']");
	private final By logout = By.xpath("//a[@class='navigation-logout-button']");
	//product overview
	private final By myAccount= By.xpath("//span[@class='header-loginStatus-container hidden-xs']//a[@class='my-account-link']");
	private final By mobileMyAccount = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[6]/a[1]");
	private static final Logger log = LoggerFactory.getLogger(MyAccountOverviewPage.class);

	public String getHeading() {
		  return waitForExpectedElement(welcomeText).getText();
	  }
	
	public void clickMyOrder() {
		try
		{
			if (UrlBuilder.isDesktop())
			{
				waitForExpectedElement(myOrder).click();
			}
			else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
				waitForExpectedElement(HomePage.selectAccountForm).click();
				waitForExpectedElement(mobileMyOrder).click();
			}
		} catch (Exception e) {
			log.info("Some exception occurred while clicking on logout button-->>"+e.getMessage());
		}
	}
	
	public void clickProductOverview() {
		try
		{
			if (UrlBuilder.isDesktop())
			{
				waitForExpectedElement(productOverview).click();
			}
			else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
				if (!isElementClickable(mobileProductOverview)) {
					waitForExpectedElement(HomePage.selectAccountForm).click();
				}
				waitForExpectedElement(mobileProductOverview).click();
			}
		} catch (Exception e) {
			log.error("Some exception occurred while click on mobile product overview-->>"+e.getMessage());
		}
	}
	
	public void clickOnMyAccount() {
		if (UrlBuilder.isDesktop())
		{
			waitForExpectedElement(myAccount).click();
		}
		else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(HomePage.LeftTogglebtn).click();
			waitForExpectedElement(mobileMyAccount).click();
		}
	  }
	
	public boolean clickPayment() {
		boolean flag = false;
		try
		{
			if (UrlBuilder.isDesktop())
			{
				waitForExpectedElement(payment).click();
				log.info("Successfully clicked payment link on my account overview page on desktop");
				flag = true;
			}

			else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			wait.until(ExpectedConditions.elementToBeClickable(HomePage.selectAccountForm)).click();
				wait.until(ExpectedConditions.elementToBeClickable(mobilePayment)).click();
				log.info("Successfully clicked payment link on my account overview page on mobile");
				flag = true;
			}
		} catch (Exception e) {
			log.error("Some exception occurred while click on payment link-->>"+e.getMessage());
			flag = false;
		}
		return flag;
	}
	
	public boolean clickAddress() {
		boolean flag = false;
		try
		{
			if (UrlBuilder.isDesktop())
			{
				waitForExpectedElement(address).click();
				log.info("Successfully clicked address link on my account overview page on desktop");
				flag = true;
			}
			else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
				waitForExpectedElement(HomePage.selectAccountForm).click();
				waitForExpectedElement(mobileAddress).click();
				log.info("Successfully clicked address link on my account overview page on mobile");
				flag = true;
			}
		} catch (Exception e) {
			log.error("Some exception occurred while click on address link-->>"+e.getMessage());
			flag = false;
		}
		return flag;
	  }
	
	public boolean clickAccountDetails() {
		boolean flag = false;
		try
		{
			if (UrlBuilder.isDesktop())
			{
				WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(accountDetails));
				new Actions(getWebDriver()).moveToElement(element).build().perform();
				element.click();
				log.info("Successfully clicked account details link on my account overview page on desktop");
				flag = true;
			}
			else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
//				waitForExpectedElement(HomePage.selectAccountForm).click();
//				waitForExpectedElement(mobileAccountDetails).click();
				Select select=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.selectAccountForm)));
				select.selectByVisibleText(Props.getProp("account.details"));
				flag=true;
			}
		} catch (Exception e) {
			log.error("Some exception occurred while click on address link-->>"+e.getMessage());
		}
		return flag;
	}
	
	public void clickLogout() {
		 waitForExpectedElement(logout).click();
	  }
}
