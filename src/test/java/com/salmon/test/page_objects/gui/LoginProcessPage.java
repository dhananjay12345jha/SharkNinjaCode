package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginProcessPage extends PageObject {


	private final By loginUserForm = By.name("LoginUserForm");
	private final By heading = By.xpath("//*[@class='account-page-title']/h1");

	//Email and Password Form for Returning Users
	private final By emailLogin = By.cssSelector("#ShopLoginForm_Login");
	private final By passwordLogin = By.cssSelector("#ShopLoginForm_Password");

	private static final By OrderNo = By.xpath("//*[@id='OrderStatusForm_OrderNumber']");

	private static final By deliveryPostalCode = By.xpath("//*[@id='OrderStatusForm_DeliveryPostalCode']");

	//private final By signInButton = By.xpath("//button[@class='btn btn-primary']");
	//private final By signInButton = By.xpath("//button[@name='login']");
	private final By signInButton = By.xpath("//button[@name='signIn' or @name='login']");
	private final By findMyOrder = By.xpath("//*[@class='btn btn-primary bottom-margin-default']");

	//private static final By forgotPasswordButton = By.xpath("//a[contains(text(),'Forgot your password?')]");
	private static final Logger log = LoggerFactory.getLogger(ZendeskAgentDashboardPage.class);

	public boolean checkLoginUserForm() {
		return waitForExpectedElement(loginUserForm).isDisplayed();
	}

	public WebElement emailLogin() {
		if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
			return waitForExpectedElement(emailLogin);
		} else if (UrlBuilder.isMobile()) {
			Actions action = new Actions(WebDriverHelper.getWebDriver());
			action.moveToElement(webDriver.findElement(emailLogin));
			return waitForExpectedElement(emailLogin);
		}
		return waitForExpectedElement(emailLogin);
	}

	public WebElement passwordLogin() {
		if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
			return webDriver.findElement(passwordLogin);
		} else if (UrlBuilder.isMobile()) {
			Actions action = new Actions(WebDriverHelper.getWebDriver());
			action.moveToElement(webDriver.findElement(passwordLogin));
			return webDriver.findElement(passwordLogin);
		}
		return webDriver.findElement(passwordLogin);
	}

	public void signInButton() {
		//waitForExpectedElement(signInButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
	}

	public String getHeading() {
		String text = waitForExpectedElement(heading).getText();
		log.info("the heading text extracted from the heading is " + text);
		return text;

	}

	public String EnterOrderNo(String orderId) {

		waitForExpectedElement(OrderNo).sendKeys(orderId);
		log.info("Agent is able to enter the order no");

		return null;
	}

	public String enterDeliveryPostalCode(String postalCode) {

		waitForExpectedElement(deliveryPostalCode).sendKeys(postalCode);
		log.info("Agent is able to enter the delivery postal code");

		return null;
	}

	public void clickFindMyOrderButton() {
		do {
			try {
				if (UrlBuilder.isDesktop()) {
					waitForExpectedElement(findMyOrder).click();
					log.info("Successfully clicked on Find my order Button");

				} else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
					waitForExpectedElement(HomePage.selectAccountForm).click();
					waitForExpectedElement(findMyOrder).click();
				}
			} catch (Exception e) {
				log.info("Some exception occurred while clicking on find My Order button-->>" + e.getMessage());
				break;
			}
		} while (false);
	}
}