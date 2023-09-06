package com.salmon.test.step_definitions.gui.SNEX;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNEX.DashboardPage;
import com.salmon.test.page_objects.gui.SNEX.SkuConfigurationPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class SkuConfigurationSteps {
    private SkuConfigurationPage skuConfigurationPage;
    private DashboardPage dashboardPage;

    public SkuConfigurationSteps(SkuConfigurationPage fraudReviewPage, DashboardPage dashboardPage) {
        this.skuConfigurationPage = fraudReviewPage;
        this.dashboardPage = dashboardPage;
    }

    @When("^I click on sku configuration option on left info pannel$")
    public void iClickOnSkuConfigurationOptionOnLeftInfoPannel() {
        Assert.assertTrue(skuConfigurationPage.clickSkuConfiguration()!=null, "Unable to click on sku configuration Option ");

    }

    @Then("^I click on substitution button$")
    public void iClickOnSubstitutionButton() {
        Assert.assertTrue(skuConfigurationPage.clickSubstitutionButton(), "Unable to click on Substitution button ");
    }

    @Then("^I click on add substitution button$")
    public void iClickOnAddSubstitutionButton() {
        Assert.assertTrue(skuConfigurationPage.clickAddSubstitutionButton(), "Unable to click on Substitution button ");
    }

    @Then("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterAnd(String sourceSku, String targetSku)  {
        Assert.assertTrue(skuConfigurationPage.enterSku(sourceSku,targetSku), "Unable to enter sku number");
    }
    @Then("^I enter \"([^\"]*)\" search box and click on search$")
    public void iEnterSearchBoxAndClickOnSearch(String key)  {
        Assert.assertTrue(skuConfigurationPage.searchSku(key), "Unable to click on Substitution button ");

    }

    @Then("^I click on cancel button$")
    public void iClickOnCancelButton() {
        Assert.assertTrue(skuConfigurationPage.clickOnCancelButton(), "Unable to click on Click on Confirm Button ");
    }

    @Then("^I click on refurbishment button$")
    public void iClickOnRefurbishmentButton() {
        Assert.assertTrue(skuConfigurationPage.clickRefurbishmentButton(), "Unable to click on Refurbishment button ");
    }

    @Then("^I click on add refurbishment button$")
    public void iClickOnAddRefurbishmentButton() {
        Assert.assertTrue(skuConfigurationPage.clickAddRefurbishmentButton(), "Unable to click on  Add Refurbishment  button ");
    }

    @Then("^I enter \"([^\"]*)\"$")
    public void iEnter(String sku) throws Throwable {
        Assert.assertTrue(skuConfigurationPage.enterRefurbishmentsSku(sku), "Unable to enter sku number");
    }

    @Then("^Verify that sku is invalid$")
    public void verifyThatSkuIsInvalid() {
        String actualString = skuConfigurationPage.existSkuText();
        String expectedString = Props.getProp("invalid.sku.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }

    @Then("^I click on Rapid Response button$")
    public void iClickOnRapidResponseButton() {
        Assert.assertTrue(skuConfigurationPage.clickRapidResponseButton(), "Unable to click on rapid response button ");
    }

    @Then("^I click on add Rapid Response button$")
    public void iClickOnAddRapidResponseButton() {
        Assert.assertTrue(skuConfigurationPage.clickAddRapidResponseButton(), "Unable to click on  Add Rapid Response  button ");
    }

    @Then("^I Select reason as \"([^\"]*)\"$")
    public void iSelectReasonAs(String value)  {
        Assert.assertTrue(skuConfigurationPage.selectReason(value), "Unable to click option ");


    }

    @Then("^Verify that sku already exist$")
    public void verifyThatSkuAlreadyExist() {
        String actualString = skuConfigurationPage.existSkuText();
        String expectedString = Props.getProp("existing.sku.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }

    @Then("^I click on warehouse restrictions button$")
    public void iClickOnWarehouseRestrictionsButton() {
        Assert.assertTrue(skuConfigurationPage.clickWarehouseButton(), "Unable to click on warehouse button ");
    }

    @Then("^I click on add warehouse restriction button$")
    public void iClickOnAddWarehouseRestrictionButton() {
        Assert.assertTrue(skuConfigurationPage.clickaddWarehouseButton(), "Unable to click on add warehouse button ");
    }

    @Then("^I Select checkbox as DSV$")
    public void iSelectCheckboxAsDSV() {
        Assert.assertTrue(skuConfigurationPage.selectDSV(), "Unable to click on add warehouse button ");

    }

    @Then("^Verify that sku already exist for warehouse restriction$")
    public void verifyThatSkuAlreadyExistForWarehouseRestriction() {
        String actualString = skuConfigurationPage.existWarehouseText();
        String expectedString = Props.getProp("existing.sku.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }

    @Then("^Verify that sku is invalid for warehouse restriction$")
    public void verifyThatSkuIsInvalidForWarehouseRestriction() {
        String actualString = skuConfigurationPage.existInvalidText();
        String expectedString = Props.getProp("invalid.warehouse.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);

    }
}