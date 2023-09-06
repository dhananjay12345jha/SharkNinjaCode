package com.salmon.test.step_definitions.gui;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.Assert;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CartOverlayPage;
import com.salmon.test.page_objects.gui.CartPage;
import com.salmon.test.page_objects.gui.ForgotPasswordPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LoginProcessPage;
import com.salmon.test.page_objects.gui.MyAccountOverviewPage;
import com.salmon.test.page_objects.gui.PDPPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ForgotPasswordSteps extends PageObject{
	
	private ForgotPasswordPage forgotPasswordPage;
	
	public ForgotPasswordSteps() {
		this.forgotPasswordPage = new ForgotPasswordPage();
		
	}
	
	
	@Then("^I should be on Forgot your password page$")
    public void verifyForgotYourPasswordPage() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("-----");
        Assert.assertTrue(forgotPasswordPage.verifyForgotPage());
        Thread.sleep(3000);
    }
	@When("^I enter email \"([^\"]*)\"$")
	public void i_enter_invalid_email(String forgotEmail) throws InterruptedException	{
		String invalid_forgot_email = Props.getProp(forgotEmail);
		forgotPasswordPage.enterinvalidEmail(invalid_forgot_email);
        Thread.sleep(3000);
			    	
	}

	@Then("^Forgot Password Text should be present$")
	public void ForgotPasswordTextShouldBePresent() {
		String actualMessage=forgotPasswordPage.getForgotPasswordTextMessageonForgotPasswordPage();
		String expectedMessage=Props.getProp("forgot.password.text");
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@Then("^I should get Please enter a valid e-mail address error messages$")
    public void verifyErrorMessageForinvalidemail()
    {
        Assert.assertTrue(forgotPasswordPage.isErrorMessageDisplayedforInvalidEmail());
    }
	
	@Then("^I should get Your account is not confirmed yet. Please confirm your account before continuing error message$")
    public void verifyErrorMessageForNonConfirmedemail()
    {
        Assert.assertTrue(forgotPasswordPage.isErrorMessageDisplayedforNonConfirmedEmail());
    }
	@Then("^I should get link has been sent to your registered email message$")
    public void verifylinkhasbeensentMessage()
    {
        Assert.assertTrue(forgotPasswordPage.verifyResetLinkMessage());
    }
	@When("^I click on ok button$")
	public void click_on_OK_button() throws InterruptedException {
		forgotPasswordPage.clickonOkButton();
		Thread.sleep(10000);
	}

}
