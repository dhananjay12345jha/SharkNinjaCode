package com.salmon.test.step_definitions.gui;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.ALPHANUMERIC;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.assertj.core.api.Assertions.assertThat;

import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.UserRegistrationPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

// extended the PageObject class, so we could use "webDriver"
public class UserRegistrationSteps extends PageObject {

	protected static final Logger LOG = LoggerFactory.getLogger(UserRegistrationSteps.class);

	private UserRegistrationPage userRegistrationPage;

	//Added Rita Singh
	public UserRegistrationSteps() {
		this.userRegistrationPage = new UserRegistrationPage();
	}

	private String lastNameData = random(6, ALPHABETS);
	//private String postCodeData = "UB10 9DW";
	private String postCodeData = Props.getProp("country.postal.code");
	private String address1Data = random(6, ALPHABETS);
	private String address2Data = random(6, ALPHABETS);
	private String townOrCityData = random(6, ALPHABETS);
	private String phoneData = random(10, NUMERIC);
	private String emailAddressData = RandomGenerator.randomEmailAddress(6);

	private String firstNameData = random(6, ALPHABETS);


	//Modify by Rita Singh
	@Given("^verify that user is on new registration page$")
	public void verify_that_user_is_on_new_registration_page() {
		//Assert.assertEquals(webDriver.getTitle(), "Registration");
		Assert.assertTrue(userRegistrationPage.verifyRegistrationPage());
	}



	@When("^I enter all required registration field$")
	public void i_enter_all_required_registration_field() throws InterruptedException {

		assertThat(userRegistrationPage.checkNewUserRegistrationForm()).isTrue();

		userRegistrationPage.emailText().sendKeys(emailAddressData);
		userRegistrationPage.confirmEmailText().sendKeys(emailAddressData);
		userRegistrationPage.passwordText().sendKeys("Password123");
		userRegistrationPage.confirmPasswordText().sendKeys("Password123");

		userRegistrationPage.firstNameText().sendKeys(firstNameData);
		userRegistrationPage.lastNameText().sendKeys(lastNameData);
		wait.until(ExpectedConditions.elementToBeClickable(userRegistrationPage.addressLine1Text()));
		userRegistrationPage.addressLine1Text().sendKeys(address1Data);
		userRegistrationPage.addressLine2Text().sendKeys(address2Data);
		userRegistrationPage.cityText().sendKeys(townOrCityData);
		userRegistrationPage.postalCodeText().sendKeys(postCodeData);
		userRegistrationPage.phoneText().sendKeys(phoneData);


	}
	//Rita Singh for User Registration
	@Then("^I checked the communication preferences checkbox$")
	public void check_communication_preferences()
	{
		userRegistrationPage.checkCommunicationPreferenceCheckbox();
	}

	@Then("^I checked the email preference checkbox$")
	public void check_email_preferences()
	{
		userRegistrationPage.checkEmailPreferenceCheckbox();
	}

	@Then("^I click on create account button$")
	public void Click_Create_Account_Button()
	{
		userRegistrationPage.clickCreateAccountButton();

	}


	@When("^I enter all required registration field with existing email-id$")
	public void i_enter_all_required_registration_field_with_existing_email() throws InterruptedException {
		assertThat(userRegistrationPage.checkNewUserRegistrationForm()).isTrue();

		userRegistrationPage.emailText().sendKeys(Props.getProp("registration.email"));
		userRegistrationPage.confirmEmailText().sendKeys(Props.getProp("registration.email"));
		userRegistrationPage.passwordText().sendKeys(Props.getProp("registration.password"));
		userRegistrationPage.confirmPasswordText().sendKeys(Props.getProp("registration.password"));

		userRegistrationPage.firstNameText().sendKeys(firstNameData);
		userRegistrationPage.lastNameText().sendKeys(lastNameData);
		Thread.sleep(5000);
		userRegistrationPage.addressLine1Text().sendKeys(address1Data);
		userRegistrationPage.addressLine2Text().sendKeys(address2Data);
		userRegistrationPage.cityText().sendKeys(townOrCityData);
		userRegistrationPage.postalCodeText().sendKeys(postCodeData);
		userRegistrationPage.phoneText().sendKeys(phoneData);


	}


	@Then("^user account is created and gets successfully registered$")
	public void user_account_is_created_and_gets_successfully_registered() {

		Assert.assertTrue(userRegistrationPage.verifyUserRegistration(emailAddressData));

	}

	@Then("^I should get A user with that email address already exists error message$")
	public void user_get_account_already_exists_error_message() {

		Assert.assertTrue(userRegistrationPage.AccountAlreadyRegisteredErrorMessage());

	}

	@Then("^I enter email and password$")
	public void I_enter_email_and_password_and_click() {
		userRegistrationPage = new UserRegistrationPage();

		assertThat(userRegistrationPage.checkNewUserRegistrationForm()).isTrue();
		userRegistrationPage.emailText().sendKeys(emailAddressData);
		userRegistrationPage.passwordText().sendKeys("Password123");


	}
	@Then("^I should get valid error message against mandatory fields$")
	public void valid_error_message_against_mandatory_field()
	{
		Assert.assertTrue(userRegistrationPage.VerifyErrorMessage());
	}

	@Then("^I should get valid error message against Username and Password$")
	public void valid_error_message_against_Username_and_Password()
	{
//		String expected=Props.getProp("User_Registration.valid.email.password.error.message").trim();
//		String actual=userRegistrationPage.verifyErrorMessageforUsernameAndPassword();
//		Assert.assertEquals(actual, expected);
//		LOG.info("Actual mesg is "+ actual,"Expected msg is " + expected);
		Assert.assertTrue(userRegistrationPage.verifyErrorMessageforUsernameAndPassword());

	}


	@When("^I fill all the required details for the User Registration$")
	public void i_fill_all_the_required_details_for_the_user_Registration() throws InterruptedException {

		assertThat(userRegistrationPage.checkNewUserRegistrationForm()).isTrue();

		userRegistrationPage.emailText().sendKeys("mukul.anand@wundermanthompson.com");
		userRegistrationPage.confirmEmailText().sendKeys("mukul.anand@wundermanthompson.comm");
		userRegistrationPage.passwordText().sendKeys("Password123");
		userRegistrationPage.confirmPasswordText().sendKeys("Password1234");
		userRegistrationPage.firstNameText().sendKeys(firstNameData);
		userRegistrationPage.lastNameText().sendKeys(lastNameData);
		userRegistrationPage.addressLine1Text().sendKeys(address1Data);
		userRegistrationPage.addressLine2Text().sendKeys(address2Data);
		userRegistrationPage.cityText().sendKeys(townOrCityData);
		userRegistrationPage.postalCodeText().sendKeys(postCodeData);
		userRegistrationPage.phoneText().sendKeys(phoneData);

	}
}
