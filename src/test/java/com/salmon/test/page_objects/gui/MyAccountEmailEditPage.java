package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountEmailEditPage extends PageObject {

	private final By heading = By.xpath("//div[@class='col-md-9']/h1");
	private final By invalidEmailerrormsg = By.xpath("//*[@class=\"edit-email-page\"]/div");
	private final By newEmailTxtBox = By.xpath("//input[@id='UpdateEmailForm_Email']");
	private final By repeatNewEmailTxtBox = By.xpath("//input[@id='UpdateEmailForm_EmailConfirmation']");
	private final By passwordTxtBox = By.xpath("//input[@id='UpdateEmailForm_Password']");
	private final By saveChangesBtn = By.xpath("//button[@class='btn btn-primary']");
	private final By errorEmailConfirmation = By.xpath("//small[@data-bv-for='UpdateEmailForm_EmailConfirmation'][@data-bv-result='INVALID']");
	private final By errorPasswordIncorrect = By.xpath("//div[@class='has-error']/div[@class='form-group']/div[@class='col-md-4 col-sm-6']/small[@class='help-block'][1]");
	private final By emailUpdateSuccessMsg = By.xpath("//div[@class='alert alert-success alert-fade']/p");
	private static final By errorMessageWhileUpdatingProfile=By.xpath("//div[@class='alert alert-danger']");

	private static final Logger log = LoggerFactory.getLogger(MyAccountEmailEditPage.class);
	
	public boolean verifyHeading() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(heading).getText().contains(Props.getMessage("profile.email.edit"));
			log.info("Successfully verified heading of edit email page ");
		} catch (Exception e) {
			log.info("Error while verifying heading on edit email page-->>"+e.getMessage());
		}
		return flag;
	  }

	public boolean fillNewEmail(String email) {
		boolean flag=false;
		try {
			waitForExpectedElement(newEmailTxtBox).clear();
			waitForExpectedElement(newEmailTxtBox).sendKeys(email);
			log.info("Successfully entered new email");
			flag = true;
		} catch (Exception e) {
			log.info("Error while fillig new email-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean fillRepeatNewEmail(String email) {
		boolean flag=false;
		try {
			waitForExpectedElement(repeatNewEmailTxtBox).clear();
			waitForExpectedElement(repeatNewEmailTxtBox).sendKeys(email);
			log.info("Successfully entered new email confirmation");
			flag = true;
		} catch (Exception e) {
			log.info("Error while fillig new email-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean enterPassword(String password) {
		boolean flag=false;
		try {
			waitForExpectedElement(passwordTxtBox).clear();
			waitForExpectedElement(passwordTxtBox).sendKeys(password);
			log.info("Successfully entered password on edit email page");
			flag = true;
		} catch (Exception e) {
			log.info("Error while filling password on edit email page-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean clickSaveChanges() {
		IsPageLoaded.waitAllRequest();
		boolean flag=false;
		try {
			WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(saveChangesBtn));
			new Actions(getWebDriver()).moveToElement(element).build().perform();
			element.click();
			log.info("Successfully clicked submit button on edit email page");
			flag = true;
		} catch (Exception e) {
			log.info("Error while click on submit button on edit email page-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean verifyErrorEmailConfirmation() {
		boolean flag=false;

			flag = waitForExpectedElement(errorEmailConfirmation).getText().contains(Props.getMessage("error.repeat.email.invalid"));
			log.info("Successfully clicked submit button on edit email page");
			flag=true;
		return flag;
	}

	public String verifyErrorMessage() {
		String text="some error occurred please check";
		try {
			text = waitForExpectedElement(invalidEmailerrormsg).getText().trim();
			log.info("Successfully got valid email required error message");
		} catch (Exception e) {
			log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
		}
		return text;
	}

	public boolean verifyErrorEmailConfirmationNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmailConfirmation);
			log.info("Error message for email is not shown");
		} catch (Exception e) {
			log.info("Error message for email is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean verifyErrorPasswordIncorrect() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(errorPasswordIncorrect).getText().contains(Props.getMessage("error.password.incorrect"));
			log.info("Successfully clicked submit button on edit email page");
		} catch (Exception e) {
			log.info("Error while click on submit button on edit email page-->>"+e.getMessage());
		}
		return flag;
	}

	public String verifyEmailUpdateSuccessMsg() {
		String text="Not Found";
		try {
			if(isElementPresent(emailUpdateSuccessMsg)){
			text = waitForExpectedElement(emailUpdateSuccessMsg).getText().trim();
			}
			else {
				text=waitForExpectedElement(errorMessageWhileUpdatingProfile).getText().trim();
			}
			log.info("Successfully clicked submit button on edit email page");
		} catch (Exception e) {
			log.info("Error while click on submit button on edit email page-->>"+e.getMessage());
		}
		return text;
	}
	
}
