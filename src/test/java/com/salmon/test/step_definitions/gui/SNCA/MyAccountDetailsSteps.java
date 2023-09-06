package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNCA.MyAccountSettingPageSNCA;
import com.salmon.test.step_definitions.gui.HomePageSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class MyAccountDetailsSteps extends PageObject {
    protected static final Logger LOG = LoggerFactory.getLogger(HomePageSteps.class);
    MyAccountSettingPageSNCA myAccountSettingPageSNCA;

    public MyAccountDetailsSteps() {
        myAccountSettingPageSNCA = new MyAccountSettingPageSNCA();
    }

    @When("^I enter new email with value \"([^\"]*)\"$")
    public void iEnterNewEmailWithValue(String email) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterNewEmail(email), "New mail field not found");
    }


    @And("^I enter password with value \"([^\"]*)\"$")
    public void iEnterPasswordWithValue(String password) {
        Assert.assertTrue(myAccountSettingPageSNCA.enterVerifyPassword(password), "Verify Password field not found");
    }

    @And("^Click on Update Email button$")
    public void clickOnUpdateEmailButton() {
        myAccountSettingPageSNCA.clickOnUpdateEmail();
    }




    @Then("^I should get error Alert with message \"([^\"]*)\"$")
    public void iShouldGetErrorAlertWithMessage(String errMsg) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.checkErrorAlertMsg(errMsg), "Correct error message is not displayed in the alert");
    }

    @Then("^I should get Success Alert with message \"([^\"]*)\"$")
    public void iShouldGetSuccessAlertWithMessage(String msg) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.checkSuccessAlertMsg(msg), "Correct success message is not displayed");
    }

     @And("^I enter new password with value \"([^\"]*)\"$")
    public void iEnterNewPasswordWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterNewPassword(arg0), "New Password field is not displayed");
    }

    @When("^I enter current password with value \"([^\"]*)\"$")
    public void iEnterCurrentPasswordWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterCurrentPassword(arg0), "Current Password field is not displayed");
    }

    @And("^I enter confirm password with value \"([^\"]*)\"$")
    public void iEnterConfirmPasswordWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterConfirmationPassword(arg0), "confirmation Password field is not displayed");
    }
    @Then("^I should get error message \"([^\"]*)\"$")
    public void iShouldGetErrorMessage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Click on edit password button$")
    public void clickOnEditPasswordButton() {
        myAccountSettingPageSNCA.clickOnUpdatePassword();
    }

    @Then("^I should get error message \"([^\"]*)\" while action \"([^\"]*)\"$")
    public void iShouldGetErrorMessageWhileAction(String errMsg, String action) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.checkErrorMsg(errMsg,action), "Correct error message is not displayed");
    }

    @When("^I enter first name with value \"([^\"]*)\"$")
    public void iEnterFirstNameWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterFirstName(arg0), "First Name field is not displayed");

    }

    @Then("^Error message should not be displayed while action \"([^\"]*)\"$")
    public void errorMessageShouldNotBeDisplayedWhileAction(String arg0) throws Throwable {
        Assert.assertFalse(myAccountSettingPageSNCA.checkErrorMsgGone(arg0), "Error message is displayed");
    }

    @When("^I enter last name with value \"([^\"]*)\"$")
    public void iEnterLastNameWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterLastName(arg0), "Last Name field is not displayed");
    }

    @When("^I enter phone number with value \"([^\"]*)\"$")
    public void iEnterPhoneNumberWithValue(String arg0) throws Throwable {
        Assert.assertTrue(myAccountSettingPageSNCA.enterPhone(arg0), "Phone number field is not displayed");
    }

    @And("^Click on Update Details$")
    public void clickOnUpdateDetails() {
        myAccountSettingPageSNCA.clickOnUpdateProfile();
    }


    @When("^I enter preference Email$")
    public void iEnterPreferenceEmail() {
    Assert.assertTrue(myAccountSettingPageSNCA.setPreferenceEmail("XYZ@yopmail.com"),"Preference Component->Email field not found or the Preference Component not loaded");
    }

    @And("^I enter first name in preference section$")
    public void iEnterFirstNameInPreferenceSection() {
        Assert.assertTrue(myAccountSettingPageSNCA.setPreferenceFirstName(Props.getProp("account.profile.first.name")),"Preference Component->Firstname field not found");

    }

    @And("^I enter last name in preference section$")
    public void iEnterLastNameInPreferenceSection() {
        Assert.assertTrue(myAccountSettingPageSNCA.setPreferenceLastName(Props.getProp("account.profile.last.name")),"Preference Component->Lastname field not found");
    }

    @And("^I check the checkboxes for preference form$")
    public void iCheckTheCheckboxesForPreferenceForm() {
        Assert.assertTrue(myAccountSettingPageSNCA.checkPreferenceCheckboxes(),"Preference Component->checkboxes not found");
    }

    @And("^Click on Update preferences$")
    public void clickOnUpdatePreferences() {
        Assert.assertTrue(myAccountSettingPageSNCA.UpdatePreference(),"Preference Component->Update Preference button not found");
    }

    @When("^I Click on manage preferences$")
    public void iClickOnManagePreferences() {
        Assert.assertTrue(myAccountSettingPageSNCA.managePreference(),"Preference Component->Manage Preference button not found");
    }

    @And("^all preferences shown be checked$")
    public void allPreferencesShownBeChecked() {
        Assert.assertTrue(myAccountSettingPageSNCA.VerifyPreferenceSelected(),"Preferences are not selected");
    }


    @And("^all preferences shown be unchecked$")
    public void allPreferencesShownBeUnchecked() {
        Assert.assertTrue(myAccountSettingPageSNCA.VerifyPreferencedeSelected(),"Preferences are still selected");
    }

    @And("^I uncheck the checkboxes in preference section$")
    public void iUncheckTheCheckboxesInPreferenceSection() {
        Assert.assertTrue(myAccountSettingPageSNCA.unCheckPreferenceCheckboxes(),"Preferences are de-selected");
    }
}

