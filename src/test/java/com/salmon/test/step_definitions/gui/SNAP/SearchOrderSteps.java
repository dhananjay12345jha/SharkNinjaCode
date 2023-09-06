package com.salmon.test.step_definitions.gui.SNAP;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;


public class SearchOrderSteps {

    private ZendeskLoginProcessPage zendeskLoginProcessPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public SearchOrderSteps(ZendeskLoginProcessPage zendeskLoginProcessPage, ZendeskAgentDashboardPage zendeskAgentDashboardPage) {
        this.zendeskLoginProcessPage = zendeskLoginProcessPage;
        this.zendeskAgentDashboardPage = zendeskAgentDashboardPage;
    }

    @Given("^I should select channel from dropdown$")
    public void i_should_select_channel_from_dropdown() {
        zendeskAgentDashboardPage.addTicket();
        zendeskAgentDashboardPage.ClickOnAppsBtn();
        zendeskAgentDashboardPage.UATSNAP();
        zendeskAgentDashboardPage.selectValueFromDropDownByby(Props.getProp("zendesk.select.channel"),zendeskAgentDashboardPage.SelectChannel);

    }

    @Then("^I should click on Order search button$")
    public void i_should_click_on_Order_search_button() {
        zendeskAgentDashboardPage.ClickOrderSearchbtn();

    }
    @Then("^I should select the Sales channel$")
    public void i_should_select_the_Sales_channel() {
        Assert.assertTrue(zendeskAgentDashboardPage.SelectSalesChannelShark());
    }

    @Then("^I should select the shark as a channel$")
    public void i_should_select_the_shark_as_a_channel() {
        Assert.assertTrue(zendeskAgentDashboardPage.selectSharkChannel());
    }

    @Then("^I should enter \"(.*)\" the order number$")
    public void i_should_enter_the_order_number(String orderId) {
        zendeskAgentDashboardPage.EnterOrderNo(Props.getProp(orderId));
    }

    @And("^I should enter the phone number$")
    public void enter_phone_number(){
        Assert.assertTrue(zendeskAgentDashboardPage.enterPhoneNo());
    }

    @And("^I should enter the email id$")
    public void enter_email_id(){
        Assert.assertTrue(zendeskAgentDashboardPage.enterEmailId());
    }

    @Then("^I should click on the search button$")
    public void i_should_click_on_the_search_button() {
        zendeskAgentDashboardPage.ClickOnSearchBtn();
     //   zendeskAgentDashboardPage.ColumnData();
//if (zendeskAgentDashboardPage.ColumnData()== true)
//{
//    System.out.println( "Agent is able to see the order" );
//}
//else
//{
//    zendeskAgentDashboardPage.SelectSalesChannelShark();
//    zendeskAgentDashboardPage.ClickOnSearchBtn();
//}
    }

    @Then("^I should verify the order search number$")
    public void i_should_verify_the_order_search_number() {
        Assert.assertEquals(zendeskAgentDashboardPage.verifyOrderNo(),Props.getProp("zendesk.order.no"));
    }

    @Then("^I should verify the phone number$")
    public void i_should_verify_the_phone_number() {
        Assert.assertTrue(zendeskAgentDashboardPage.verifyPhoneNo().contains(Props.getProp("zendesk.phone.no")));
    }

    @Then("^I should verify the email id$")
    public void i_should_verify_the_email_id() {
        Assert.assertTrue(zendeskAgentDashboardPage.verifyEmailId().contains(Props.getProp("zendesk.email.id")));
    }

    @And("^I should click on 1st order id$")
    public void i_should_click_on_1st_order_id() {
        zendeskAgentDashboardPage.clickOn1stOrder();
    }

    @And("^I should click on first order id$")
    public void i_should_click_on_first_order_id() {
        zendeskAgentDashboardPage.clickOnFirstOrder();
    }

    @Then("^I should click on view orders link$")
    public void i_should_click_on_view_orders_links() {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnViewOrders());
    }

    @Then("^I should select the Sales channel for ebay$")
    public void iShouldSelectTheSalesChannelForEbay() {
        Assert.assertTrue(zendeskAgentDashboardPage.SelectSalesChannelEbayShark());
    }

    @And("^I should enter the \"([^\"]*)\"$")
    public void iShouldEnterThe(String key) {
        Assert.assertTrue(zendeskAgentDashboardPage.enterPhoneNumber(key));

    }

    @And("^I should enter the ebay \"([^\"]*)\"$")
    public void iShouldEnterTheEbay(String key)  {
        Assert.assertTrue(zendeskAgentDashboardPage.enterEbayEmailId(key));
    }

    @And("^I should verify order option button for ebay order$")
    public void iShouldVerifyOrderOptionButtonForEbayOrder() {
        Assert.assertTrue(zendeskAgentDashboardPage.verifyOrderOptionButton());

    }
}
