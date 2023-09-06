package com.salmon.test.page_objects.gui;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;

public class ForgotPasswordPage extends PageObject{
	private final By forgotPasswordtitle = By.xpath("//*[@class=\"marketing-area\"]");
	private final By forgotEmailTxt=By.xpath("//*[@id=\"ForgotPasswordStep1Email_Login\"]");
	private final By invalidEmailError=By.xpath("//div[@class='col-sm-6 col-md-4']//small[1]");

	private final By ForgotPasswordTextMessage=By.xpath("//div[@class=\"container main-container\"]//p");

	private final By NonConfirmedEmailError=By.xpath("//div[@class=\"alert alert-danger\"]/p");
	private final By OKButton=By.xpath("//div[@class=\"col-sm-offset-2 col-sm-8\"]//button");
	private final By ResetLinkforForgot=By.xpath(" //div[@class=\"container main-container\"]/p");
	private static final Logger log = LoggerFactory.getLogger(ForgotPasswordPage.class);
	
	
	public Boolean verifyForgotPage() {
		
	boolean flag=false;
				try {
					System.out.println("heading ---"+webDriver.findElement(By.xpath("//*[@class=\"marketing-area\"]")).getText());
					flag=waitForExpectedElement(forgotPasswordtitle).getText().contentEquals(Props.getProp("ForgotyourPasswordPageheading"));
					log.info("Successfully navigated to Forgot your password Page  ");
					
				} catch (Exception e) {
					log.info("Some exception occurred while navigated to Forgot your password Page-->>"+e.getMessage());
				}
				return flag;
			}
public void enterinvalidEmail(String email) {
		
		try {
			System.out.println("email--"+email);
			waitForExpectedElement(forgotEmailTxt).clear();
			waitForExpectedElement(forgotEmailTxt).sendKeys(email);
			log.info("Successfully entered email on email text box");
		} catch (Exception e) {
			log.info("Some exception occurred while entering email text box-->>"+e.getMessage());
		}
		
	}
public Boolean isErrorMessageDisplayedforInvalidEmail() {
	boolean flag=false;
	try {
		flag = waitForExpectedElement(invalidEmailError).getText().contentEquals(Props.getProp("invalid_forgot_email_error"));
		log.info("Successfully found error message Please enter a valid e-mail address.");
	} catch (Exception e) {
		log.info("Some exception occurred while finding invalid error message-->>"+e.getMessage());
	}
	return flag;
}

	public String getForgotPasswordTextMessageonForgotPasswordPage(){
		String message="";
		if (isElementPresent(ForgotPasswordTextMessage)) {
			log.info("successfully found the element for forgotTextMessage--> " + ForgotPasswordTextMessage);
			message = waitForExpectedElement(ForgotPasswordTextMessage).getText();
			log.info("The actualMessage for ForgotPasswordText is--> " + message);
		}
		return message;
	}

public Boolean isErrorMessageDisplayedforNonConfirmedEmail() {
	boolean flag=false;
	try {
		flag = waitForExpectedElement(NonConfirmedEmailError).getText().contentEquals(Props.getProp("nonconfirmed_forgot_email_error"));
		log.info("Successfully found error message Please confirm your account before continuing.--"+ flag);
	} catch (Exception e) {
		log.info("Some exception occurred while finding invalid error message-->>"+e.getMessage());
	}
	return flag;
}
public Boolean verifyResetLinkMessage() {
	boolean flag=false;
	try {
		flag = waitForExpectedElement(ResetLinkforForgot).getText().contentEquals(Props.getProp("ResetLink_forgot_email"));
		log.info("Successfully found message that Reset Link has been sent to your registered email."+ flag);
	} catch (Exception e) {
		log.info("Some exception occurred while sending the reset link-->>"+e.getMessage());
	}
	return flag;
}
public void clickonOkButton() {
	try {
	waitForExpectedElement(OKButton).click();
	log.info("Successfully clicked OK Button");
	}
	catch (Exception e) {
		log.info("Some exception occurred while clicking OK Button-->>"+e.getMessage());
	}
	
}

}
