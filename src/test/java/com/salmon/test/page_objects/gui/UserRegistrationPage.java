package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers
					.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class UserRegistrationPage extends PageObject {
	private static final Logger log = LoggerFactory.getLogger(UserRegistrationPage.class);

	private static final By newUserRegistrationForm = By.name("RegisterUser");

	//Email and Password Form
	private static final By emailText = By.xpath("//input[@id='RegisterUserFullEmail_Login']");
	//private final By invalidEmailerrormsg = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[2]/div[1]/small[3]");
	private final By invalidEmailerrormsg = By.xpath("//*[@id=\"UserRegistrationForm\"]/div[1]/div/div/div[2]/div/small[3]");
	private static final By confirmEmailText = By.xpath("//input[@id='RegisterUserFullEmail_EmailConfirmation']");
	private static final By passwordText = By.xpath("//input[@id='RegisterUserFullEmail_Password']");
	private static final By confirmPasswordText = By.xpath("//input[@id='RegisterUserFullEmail_PasswordConfirmation']");

	//User Registration Locators

	private static final By email_exists_error = By.xpath("//div[@class=\"alert alert-danger\"]//p[1]");

	//Address Form
	private static final By firstNameText = By.xpath("//input[@id='AddressForm_FirstName']");
	private static final By lastNameText = By.xpath("//input[@id='AddressForm_LastName']");
	private static final By addressLine1Text = By.name("AddressForm_Address1");
	private static final By addressLine2Text = By.name("AddressForm_Address2");
	private static final By cityText = By.name("AddressForm_City");
	private static final By postalCodeText = By.name("AddressForm_PostalCode");
	private static final By phoneText = By.name("AddressForm_PhoneHome");

	//Subscriptions
	private static final By competitionsAndNewCheckbox = By.xpath("//*[@id=\"UserRegistrationForm\"]/div[3]/div/div/div[2]/div/div[1]/label");
	private static final By offersCheckbox = By.xpath("//label[@for='AddressForm_NewsletterSubscriptionOffers']");
	private static final By cleaningTipsArticlesCheckbox = By.xpath("//label[@for='AddressForm_NewsletterSubscriptionTips']");

	private static final By emailPreferenceBox = By.xpath("//label[@for='AddressForm_EmailNewsletterSubscription']");


	//CreateAccount
	private static final By createAccountButton = By.xpath("//button[@name='CreateAccount']");

	private static final By userAccountCreationConfirmationText = By.xpath("//div[@class='container main-container']");

	//User Registration Locator
	private static final By confirmEmailError = By.xpath("//small[@data-bv-for='RegisterUserFullEmail_EmailConfirmation'][@data-bv-validator=\"identical\"]");
	private static final By confirmPassword = By.xpath("//small[@data-bv-for='RegisterUserFullEmail_PasswordConfirmation'][@data-bv-validator=\"identical\"]");
	private static final By confirmFirstName = By.xpath("//small[@data-bv-for='AddressForm_FirstName'][@data-bv-validator=\"notEmpty\"]");
	private static final By confirmLastName = By.xpath("//small[@data-bv-for='AddressForm_LastName'][@data-bv-validator=\"notEmpty\"]");
	private static final By confirmStreetAddress = By.xpath("//small[@data-bv-for='AddressForm_Address1'][@data-bv-validator=\"notEmpty\"]");
	private static final By confirmCity = By.xpath("//small[@data-bv-for='AddressForm_City'][@data-bv-validator=\"notEmpty\"]");
	private static final By confirmPostalCode = By.xpath("//small[@data-bv-for='AddressForm_PostalCode'][@data-bv-validator=\"notEmpty\"]");

	// Actions on this page

	public boolean checkNewUserRegistrationForm() {
		return waitForExpectedElement(newUserRegistrationForm).isDisplayed();
	}

	public Boolean verifyRegistrationPage() {
		boolean flag = false;
		IsPageLoaded.waitAllRequest();
		try {
			flag = webDriver.getTitle().contains(Props.getProp("registration_page_title"));
			System.out.println("Actual Title is " + webDriver.getTitle());
			System.out.println(Props.getProp("registration_page_title"));
			log.info("Successfully found that user is on registration page");
		} catch (Exception e) {
			log.info("Some exception occurred while validating that user is on registration page-->>" + e.getMessage());
		}
		return flag;
	}


	public WebElement emailText() {

		return waitForExpectedElement(emailText);
	}

	public WebElement confirmEmailText() {
		return waitForExpectedElement(confirmEmailText);
	}

	public WebElement passwordText() {
		return waitForExpectedElement(passwordText);
	}

	public WebElement confirmPasswordText() {
		return waitForExpectedElement(confirmPasswordText);
	}

	public WebElement firstNameText() {
		return waitForExpectedElement(firstNameText);
	}

	public WebElement lastNameText() {
		return waitForExpectedElement(lastNameText);
	}

	public WebElement addressLine1Text() {
		return waitForExpectedElement(addressLine1Text);
	}

	public WebElement addressLine2Text() {
		return waitForExpectedElement(addressLine2Text);
	}

	public WebElement cityText() {
		return waitForExpectedElement(cityText);
	}

	public WebElement postalCodeText() {
		return waitForExpectedElement(postalCodeText);
	}

	public WebElement phoneText() {
		return waitForExpectedElement(phoneText);
	}

	public void checkCommunicationPreferenceCheckbox() {

		waitForExpectedElement(competitionsAndNewCheckbox).click();
		//waitForExpectedElement(offersCheckbox).click();
		//waitForExpectedElement(cleaningTipsArticlesCheckbox).click();
	}

	public void checkEmailPreferenceCheckbox() {

		waitForExpectedElement(emailPreferenceBox).click();
	}

	public void clickCreateAccountButton() {
		try {
			waitForExpectedElement(createAccountButton).click();
			log.info("Successfully clicked the button " + createAccountButton);
		} catch (Exception e) {
			log.info("Some exception occurred while clicking Create account Button-->>" + e.getMessage());
		}

	}


	public Boolean verifyUserRegistration(String emailAddressData) {
		boolean flag = false;
		try {
			IsPageLoaded.waitAllRequest();
			String actualResult = waitForExpectedElement(userAccountCreationConfirmationText).getText();
			String expectedResult = Props.getProp("user_registration_confirmation_message");
			expectedResult = expectedResult.replace("|email|", emailAddressData);
			flag = actualResult.contains(expectedResult);
			if (flag)
				log.info("Successfully registered the user");
			else {
				log.error("Actual message:" + actualResult);
				log.error("Expected message:" + expectedResult);
			}
		} catch (Exception e) {
			log.info("Some exception occurred while registering the user-->>" + e.getMessage());
		}
		return flag;
	}

	public Boolean AccountAlreadyRegisteredErrorMessage() {
		boolean flag = false;
		try {
			flag = waitForExpectedElement(email_exists_error).getText().contains(Props.getProp("email.exists.error"));
			log.info("Successfully found A user with that email address already exists. Please login to your account to continue.");
		} catch (Exception e) {
			log.info("Some exception occurred while finding invalid error messageA user with that email address already exists. Please login to your account to continue.-->>" + e.getMessage());
		}
		return flag;
	}

	public Boolean VerifyErrorMessage() {
		boolean flag = false;
		try {
			if (waitForExpectedElement(confirmEmailError).getText().contains(Props.getProp("confirm_email_error")) &&
					waitForExpectedElement(confirmPassword).getText().contains(Props.getProp("confirm_Password_error")) &&
					waitForExpectedElement(confirmFirstName).getText().contains(Props.getProp("confirm_FirstName_error")) &&
					waitForExpectedElement(confirmLastName).getText().contains(Props.getProp("confirm_LastName_error")) &&
					waitForExpectedElement(confirmStreetAddress).getText().contains(Props.getProp("confirm_streetAddress_error")) &&
					waitForExpectedElement(confirmCity).getText().contains(Props.getProp("confirm_City_error")) &&
					waitForExpectedElement(confirmPostalCode).getText().contains(Props.getProp("confirm_PostalCode_error"))

			)

				flag = true;


			//log.info("Successfully found A user with that email address already exists. Please login to your account to continue.");
		} catch (Exception e) {
			log.info("Some exception occurred while finding invalid error messageA user with that email address already exists. Please login to your account to continue.-->>" + e.getMessage());
		}
		return flag;
	}

	public Boolean verifyErrorMessageforUsernameAndPassword() {
		boolean flag = false;
//		try {
//			fla = waitForExpectedElement(invalidEmailerrormsg).getText().trim();
//			log.info("Successfully got valid email required error message");
//		} catch (Exception e) {
//			log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
//		}
//			return flag;

		try {
			if (waitForExpectedElement(confirmEmailError).getText().contains(Props.getProp("confirm_email_error")) &&
					waitForExpectedElement(confirmPassword).getText().contains(Props.getProp("confirm_Password_error"))
			)

				flag = true;

		} catch (Exception e) {
			log.info("Some exception occurred while finding invalid error message against username and password-->>" + e.getMessage());
		}
		return flag;
	}
}
