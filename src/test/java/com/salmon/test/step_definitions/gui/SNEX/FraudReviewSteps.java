package com.salmon.test.step_definitions.gui.SNEX;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNEX.DashboardPage;
import com.salmon.test.page_objects.gui.SNEX.FraudReviewPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class FraudReviewSteps {
    private FraudReviewPage fraudReviewPage;
    private DashboardPage dashboardPage;

    public FraudReviewSteps(FraudReviewPage fraudReviewPage,DashboardPage dashboardPage){
        this.fraudReviewPage = fraudReviewPage;
        this.dashboardPage = dashboardPage;
    }

    @When("^I click on fraud review option on left info pannel$")
    public void click_on_fraud_review_option(){
        Assert.assertTrue(dashboardPage.clickFraudReview()!=null, "Unable to click on Fraud-Review Option ");
    }

    @Then("^I Click on  search fraud checks$")
    public void iClickOnSearchFraudChecks() {
        Assert.assertTrue(dashboardPage.clickFraudSearch(), "Unable to click on Fraud-Search Option ");
    }

    @Then("^Verify the order status of fraud check$")
    public void verifyTheOrderStatusOfFraudCheck() {
        Assert.assertTrue(dashboardPage.verifyFraudStatus(), "Unable to click on Fraud-Search Option ");

    }


    @Then("^I Click on add Item Button$")
    public void iClickOnAddItemButton() {
        Assert.assertTrue(fraudReviewPage.clickAddItem(), "Unable to click on Add Item Button ");

    }

    @Then("^I enter value of \"([^\"]*)\"$")
    public void iEnterValueOf(String key) {
        Assert.assertTrue(fraudReviewPage.enterEmailId(key), "Unable to enter email id");
    }

    @Then("^search blacklist \"([^\"]*)\" and select$")
    public void searchBlacklistAndSelect(String value)  {
        Assert.assertTrue(fraudReviewPage.slectBlackList(value), "Unable to enter email id");

    }

    @Then("^click on confirm button$")
    public void clickOnConfirmButton() {
        Assert.assertTrue(fraudReviewPage.clickOnConfirmButton(), "Unable to click on Click on Confirm Button ");
    }

    @Then("^I enter \"([^\"]*)\" in search box$")
    public void iEnterInSearchBox(String key)  {
        Assert.assertTrue(fraudReviewPage.enterEmailIdInSearchBox(key), "Unable to enter email id in search box");


    }

    @Then("^I select again value type as \"([^\"]*)\"$")
    public void selectAgainValueTypeAsEmail(String key) throws Throwable {
        Assert.assertTrue(fraudReviewPage.selectEmailId1(key), "Unable to select email id ");

    }

    @Then("^I click on search button$")
    public void iClickOnSearchButton() {
        Assert.assertTrue(fraudReviewPage.clickOnSearchButton(), "Unable to click on Search Button ");
    }

    @Then("^I click on delete icon$")
    public void iClickOnDeleteIcon() {
        Assert.assertTrue(fraudReviewPage.clickOnDeleteIcon(), "Unable to click on Search Button ");
    }

    @Then("^I click on delete confirm button$")
    public void iClickOnDeleteConfirmButton() {
        Assert.assertTrue(fraudReviewPage.clickOnDeleteConfirmButton(), "Unable to click on Search Button ");
    }

    @Then("^I Select value type as \"([^\"]*)\"$")
    public void iSelectValueTypeAs(String key)  {
        Assert.assertTrue(fraudReviewPage.selectEmailId(key), "Unable to select email id ");
    }


    @Then("^I select country from country list$")
    public void iSelectCountryFromCountryList() {
        Assert.assertTrue(fraudReviewPage.selectCountry(), "Unable to select email id ");

    }

    @And("^I enter the \"([^\"]*)\"$")
    public void iEnterThe(String key)  {
        Assert.assertTrue(fraudReviewPage.enterCity(key), "Unable to enter City");

    }

    @And("^I enter the \"([^\"]*)\" in address line$")
    public void iEnterTheInAddressLine(String key)  {
        Assert.assertTrue(fraudReviewPage.enterAddress(key), "Unable to enter Address");

    }

    @And("^I enter the \"([^\"]*)\" in postal code box$")
    public void iEnterTheInPostalCodeBox(String key) {
        Assert.assertTrue(fraudReviewPage.enterPostalCode(key), "Unable to enter postal code");

    }

    @Then("^I click on blacklist button$")
    public void iClickOnBlacklistButton() {
        Assert.assertTrue(fraudReviewPage.clickBlackList(), "Unable to click on Add Blacklist Button ");
    }

    @Then("^I click on add blacklist button$")
    public void iClickOnAddBlacklistButton() {
        Assert.assertTrue(fraudReviewPage.clickAddBlackList(), "Unable to click on Add Blacklist Button ");
    }

    @Then("^I enter the name of \"([^\"]*)\"$")
    public void iEnterTheNameOf(String key) {
        Assert.assertTrue(fraudReviewPage.enterNameBlacklist(key), "Unable to enter Name of Blacklist");

    }

    @Then("^I select hold order radio button$")
    public void iSelectHoldOrderRadioButton() {
        Assert.assertTrue(fraudReviewPage.selectHoldOrder(), "Unable to select hold order");
    }

    @Then("^I click on submit button$")
    public void iClickOnSubmitButton() {
        Assert.assertTrue(fraudReviewPage.clickOnSubmitButton(), "Unable to click on Search Button ");
    }

    @Then("^I click on trash icon$")
    public void iClickOnTrashIcon() {
       Assert.assertTrue(fraudReviewPage.clickOnTrashIcon(), "Unable to click on delete Button ");
    }

    @Then("^Verify that blacklist is deleted successfully$")
    public void verifyThatBlacklistIsDeletedSuccessfully() {
        IsPageLoaded.waitAllRequest();
        String actualString = fraudReviewPage.searchResultText();
        String expectedString = Props.getProp("serach.result.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }

    @Then("^I select cancel order radio button$")
    public void iSelectCancelOrderRadioButton() {
        Assert.assertTrue(fraudReviewPage.selectCancelOrder(), "Unable to select cancel order");
    }

    @Then("^Verify that sku is deleted successfully$")
    public void verifyThatSkuIsDeletedSuccessfully() {
        IsPageLoaded.waitAllRequest();
        String actualString = fraudReviewPage.searchResultText();
        String expectedString = Props.getProp("serach.result.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }
}
