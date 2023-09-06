package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.SNEX.FraudActivityPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class FraudActivitySteps {

    private FraudActivityPage fraudActivityPage;

    public FraudActivitySteps(FraudActivityPage fraudActivityPage){
        this.fraudActivityPage = fraudActivityPage;
    }
    @And("^Before approval save the order number into the properties file as \"([^\"]*)\"$")
    public void beforeApprovalSaveTheOrderNumberIntoThePropertiesFileAs(String key) {
        Assert.assertTrue(fraudActivityPage.fetchAndSaveOrderNumberIntoApiConfigFileHavingKey(key),
                "Unable to fetch and save the order number from order result page please check logs");
    }

    @Then("^I click on first order$")
    public void iClickOnFirstOrder() {
        Assert.assertTrue(fraudActivityPage.clickOnOrder(), "Unable to click on first order ");
    }

    @Then("^I click On fraud activity$")
    public void iClickOnFraudActivity() {
        Assert.assertTrue(fraudActivityPage.fraudActivity(), "Unable to click on first order ");
    }

    @Then("^I select the check-box$")
    public void iSelectTheCheckBox() {
        Assert.assertTrue(fraudActivityPage.selectCheckbox(), "Unable to select Checkbox ");
    }

    @Then("^I click on order option$")
    public void iClickOnOrderOptionAndThenClickOnApproveFraudCheckButton() {
        Assert.assertTrue(fraudActivityPage.clickApproveFraudCheck(), "Unable to click on approve fraud check ");
    }

    @Then("^I enter the comment message$")
    public void iEnterTheCommentMessage() {
        Assert.assertTrue(fraudActivityPage.enterDecisionNote(), "Unable to click on approve fraud check ");
    }

    @And("^I select the option \"([^\"]*)\" from options$")
    public void iSelectTheOptionFromOptions(String option) {
        Assert.assertTrue(fraudActivityPage.selectOptionShownUnderOrderOptionButton(option), "Unable to click option ");

    }

    @Then("^I click on order tab in main menu$")
    public void iClickOnOrderTabInMainMenu() {
        Assert.assertTrue(fraudActivityPage.clickOnMainOrder(), "Unable to click on main menu order ");
    }

    @And("^I click on confirm button$")
    public void iClickOnConfirmButton() {
        Assert.assertTrue(fraudActivityPage.clickOnConfirmButton(), "Unable to click on confirm button");
    }

    @Then("^I enter the save order in \"([^\"]*)\"$")
    public void iEnterTheSaveOrderIn(String key) {
        Assert.assertTrue(fraudActivityPage.enterOrderNumber(key), "Unable to enter order number");
    }

    @Then("^Verify the status of approved order$")
    public void verifyTheStatusOfApprovedOrder() {
        Assert.assertTrue(fraudActivityPage.approvedOrderStatus(), "Order status is not correct");

    }

    @Then("^Verify the status of Reject order$")
    public void verifyTheStatusOfRejectOrder() {
        Assert.assertTrue(fraudActivityPage.rejectOrderStatus(), "Order status is not correct");
    }

    @Then("^I enter the message in comment box$")
    public void iEnterTheMessageInCommentBox() {
        Assert.assertTrue(fraudActivityPage.enterCommentMessage(), "Unable to click on approve fraud check ");
    }

    @And("^I click on confirm Button$")
    public void i_Click_On_Confirm_Button() {
        Assert.assertTrue(fraudActivityPage.clickOnConfirm(), "Unable to click on confirm button");
    }

    @Then("^Verify order option button should be enabled$")
    public void verifyOrderOptionButtonShouldBeEnabled() {
        Assert.assertTrue(fraudActivityPage.verifyOrderOptionButton());
    }

    @Then("^Verify order option button should be disabled$")
    public void verifyOrderOptionButtonShouldBeDisabled() {
        Assert.assertTrue(fraudActivityPage.verifyOrderOptionButton1());

    }

    @Then("^I click on first order and order status should not be back-order$")
    public void iClickOnFirstOrderAndOrderStatusShouldNotBeBackOrder() {
        Assert.assertTrue(fraudActivityPage.clickOrder());

    }
}
