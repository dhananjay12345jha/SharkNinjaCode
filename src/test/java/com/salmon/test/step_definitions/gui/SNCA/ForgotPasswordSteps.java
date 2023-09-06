package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNCA.CreateNewAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.ForgotPasswordPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNUS.HomePageSNUS;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ForgotPasswordSteps {

    private HomePageSNCA homePageSNCA;
    private HomePageSNUS homePageSNUS;
    private ForgotPasswordPageSNCA forgotPasswordPageSNCA;
    private CreateNewAccountPageSNCA createNewAccountPageSNCA;

    public ForgotPasswordSteps() {
        homePageSNCA = new HomePageSNCA();
        homePageSNUS = new HomePageSNUS();
        createNewAccountPageSNCA=new CreateNewAccountPageSNCA();
        forgotPasswordPageSNCA= new ForgotPasswordPageSNCA();
    }

    @And("^I click on Forgot your password link for SNUS$")
    public void iClickOnForgotYourPasswordLinkForSNUS() {
        forgotPasswordPageSNCA = homePageSNCA.clickForgotPasswordLink();
        Assert.assertTrue(forgotPasswordPageSNCA != null, "Unable to click Forgot Password link");
    }

    @Then("^I should get \"([^\"]*)\" message$")
    public void iShouldGetMessage(String message) throws Throwable {
        Assert.assertTrue(forgotPasswordPageSNCA.checkErrorMsg(message), message + " Message is not displayed");
    }

    @When("^I enter email as \"([^\"]*)\"$")
    public void iEnterEmailAs(String email) throws Throwable {
        Assert.assertTrue(forgotPasswordPageSNCA.enterUserName(email), "UserName field not found");
    }

    @And("^I check I am not a Robot checkbox if available$")
    public void tick_checkbox_i_am_not_robot(){

        Assert.assertTrue(createNewAccountPageSNCA.selectCheckBoxInsideCaptcha());
    }


    @And("^I click on Send Recover Link button$")
    public void iClickOnSendRecoverLinkButton() throws InterruptedException {
        forgotPasswordPageSNCA.clickSendRecoverLink();
    }

    @Then("^I should get alert with \"([^\"]*)\" success message$")
    public void iShouldGetAlertWithSuccessMessage(String successMessageKey) {
//        String expected=Props.getProp(successMessageKey);
        Assert.assertEquals(forgotPasswordPageSNCA.checkAlertMsgSuccess(), Props.getProp(successMessageKey));

    }

    @Then("^I should get alert with \"([^\"]*)\" error$")
    public void iShouldGetAlertWithError(String errorMessageKey) {
        String expected=Props.getProp(errorMessageKey);
        Assert.assertEquals(forgotPasswordPageSNCA.checkAlertMsgError(),expected);
    }

}
