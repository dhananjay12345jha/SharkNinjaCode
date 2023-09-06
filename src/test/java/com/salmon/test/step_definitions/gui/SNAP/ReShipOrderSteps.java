package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ReShipOrderSteps {


    private ZendeskLoginProcessPage zendeskLoginProcessPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public ReShipOrderSteps(ZendeskLoginProcessPage zendeskLoginProcessPage, ZendeskAgentDashboardPage zendeskAgentDashboardPage) {
        this.zendeskLoginProcessPage = zendeskLoginProcessPage;
        this.zendeskAgentDashboardPage = zendeskAgentDashboardPage;
    }

    @Then("^I should click on faulty on delivery$")
    public void i_should_click_on_faulty_on_delivery() {
     zendeskAgentDashboardPage.ClickOnFaultyOnDeliveryOptn();
    }

    @Then("^I should click on replace unit option$")
    public void i_should_click_on_replace_unit() {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnReplaceUnitOption());
    }

    @Then("^I should click on replace part option$")
    public void i_should_click_on_replace_part() {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnReplacePartOption());
    }


    @And("^I should assert the return process option$")
    public void i_should_assert_the_return_process_option(){
        Assert.assertTrue(zendeskAgentDashboardPage.getReturnRequestTitle().contains("Create return request for order number"));
//        Assert.assertEquals(zendeskAgentDashboardPage.getReturnRequestTitle(),"Create return request for order number " +Props.getProp("zendesk.order.no"));
    }

    @And("^I should assert the Replacement Order title$")
    public void i_should_assert_the_replacement_order_title(){
        Assert.assertEquals(zendeskAgentDashboardPage.getReturnRequestTitle(),"Create replacement for order number " +Props.getProp("zendesk.order.no"));
    }

    @And("^I should see return successfull dialog box$")
    public void i_should_see_the_return_successfull_dialog_box(){
        Assert.assertEquals(zendeskAgentDashboardPage.getReturnSuccessfulTitle(),"Return successfully created");
    }

    @And("^I should select continue button$")
    public void i_should_select_continue_button(){
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnContinueButton());
    }

    @Then("^I should assert \"(.*)\" and \"(.*)\" on cart page$")
    public void i_should_check_message_on_cart_page(String message, String orderId){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(zendeskAgentDashboardPage.getRapidResponse(), Props.getProp(message));
        softAssert.assertFalse(zendeskAgentDashboardPage.getRMANumber().contains(" "));
        softAssert.assertEquals(zendeskAgentDashboardPage.getOrderId(), Props.getProp(orderId));

//        softAssert.assertEquals(zendeskAgentDashboardPage.getRMANumber(),"100727");

        softAssert.assertAll();
    }

    @Then("^I should assert the ReShip order title$")
    public void i_should_assert_the_ReShip_order_title() {
      zendeskAgentDashboardPage.AssertReShipTitle();
    }

    @Then("^I should verfiy the Product id$")
    public void i_should_verfiy_the_Product_id() {
       zendeskAgentDashboardPage.VerfiyProductID();
    }

    @Then("^I should click on Add To Cart button$")
    public void i_should_click_on_Add_To_Cart_button() {
    zendeskAgentDashboardPage.ClickOnAddToCart();
    }

    @Then("^I should navigate to the cart$")
    public void i_should_navigate_to_the_cart() {
        zendeskAgentDashboardPage.NavigateToCart();
    }

    @Then("I should click on Lost in Transit option")
    public void i_should_click_on_navigate_to_the_cart() {
        zendeskAgentDashboardPage.clickLostInTransit();
    }

    @Then("I should click on Missing from Box option")
    public void i_should_click_on_missing_from_box_option() {
        zendeskAgentDashboardPage.clickMissingInBoxOption();
    }

}
