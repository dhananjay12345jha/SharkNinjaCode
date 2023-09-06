package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.SNEX.DashboardPage;
import com.salmon.test.page_objects.gui.SNEX.FraudReviewPage;
import com.salmon.test.page_objects.gui.SNEX.OrderSearchPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class SnexOrderSearchSteps {

        private OrderSearchPage orderSearchPage;

        public SnexOrderSearchSteps(OrderSearchPage orderSearchPage){
            this.orderSearchPage = orderSearchPage;
        }
    @Then("^I click on search Back-order$")
    public void iClickOnSearchBackOrder() {
        Assert.assertTrue(orderSearchPage.clickBackOrderSearch(), "Unable to click on Back-Order-Search Option ");
    }

    @Then("^Verify the order status of Back-Order$")
    public void verifyTheOrderStatusOfBackOrder() {
        Assert.assertTrue(orderSearchPage.verifyBackorderStatus(), "Unable to click on Fraud-Search Option ");
    }

    @Then("^I click on search Button$")
    public void iClickOnSearchButton() {
        Assert.assertTrue(orderSearchPage.clickOnSearch(), "Unable to click on Back-Order-Search Option ");
    }

    @Then("^Verify the order status of Orders$")
    public void verifyTheOrderStatusOfOrders() {
        Assert.assertTrue(orderSearchPage.verifyOrderStatus(), "Unable to verify order status ");
    }

    @Then("^I select order type as new order$")
    public void iSelectOrderTypeAsNewOrder() {
        Assert.assertTrue(orderSearchPage.selectOrderType(), "Unable to New order as order type ");
        
    }

    @Then("^Verify the order type of Orders$")
    public void verifyTheOrderTypeOfOrders() {
        Assert.assertTrue(orderSearchPage.verifyOrderType(), "Unable to click on Fraud-Search Option ");

    }

    @Then("^I click on advance search$")
    public void iClickOnAdvanceSearch() {
        Assert.assertTrue(orderSearchPage.clickOnAdvanceSearch(), "Unable to click on Advance Search Option ");
    }

    @And("^I select order reason$")
    public void iSelectOrderReason() {
        Assert.assertTrue(orderSearchPage.selectOrderReason(), "Unable to select order reason ");
    }

    @Then("^Verify the order reason of order$")
    public void verifyTheOrderReasonOfOrder() {
        Assert.assertTrue(orderSearchPage.verifyOrderReason(), "Unable to verify order reason ");
    }

    @Then("^Verify the odrer in search result$")
    public void verifyTheOdrerInSearchResult() {
        Assert.assertTrue(orderSearchPage.verifySearchResult(), "Unable to verify order result ");
    }

    @Then("^I Enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterAnd(String key1, String key2)  {
        Assert.assertTrue(orderSearchPage.enterLastName(key1,key2), "Unable to enter order number");

    }

    @Then("^Verify the order \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifyTheOrderAnd(String key1, String key2)  {
        Assert.assertTrue(orderSearchPage.verifyEmailandLastName(key1,key2), "Unable to verify email and last name  ");

    }
}
