package com.salmon.test.step_definitions.gui.SNAP;

import com.mysql.jdbc.log.Log;
import com.salmon.test.framework.helpers.utils.IcmOrderImportExportService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;
import org.testng.Assert;
public class ReplaceUnitSteps {
    private ZendeskLoginProcessPage zendeskLoginProcessPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;
    private IcmOrderImportExportService icmOrderImportExportService;

    public ReplaceUnitSteps(ZendeskLoginProcessPage zendeskLoginProcessPage, ZendeskAgentDashboardPage zendeskAgentDashboardPage) {
        this.zendeskLoginProcessPage = zendeskLoginProcessPage;
        this.zendeskAgentDashboardPage = zendeskAgentDashboardPage;
        icmOrderImportExportService = new IcmOrderImportExportService();
    }

    @Then("^I should check the order type$")
    public void i_should_check_the_order_type() {
        zendeskAgentDashboardPage.CheckOrderType();
    }

    @Then("^I should check the order status$")
    public void i_should_check_the_order_status() {
        zendeskAgentDashboardPage.CheckOrderStatus();
    }

    @Then("^the order status should be displayed as \"(.*?)\"$")
    public void i_should_check_the_order_status(String status) {
        String text = zendeskAgentDashboardPage.CheckOrderStatusAsBackOrder();
        Assert.assertEquals(status, text);
    }

    @Then("^I should click on the dispatched Order status with New Order type$")
    public void  i_should_click_on_the_dispatched_order_status_with_New_Order_type(){
        zendeskAgentDashboardPage.clickDispatachedANdNewOrderType();
    }

    @Then("^I should click on the Back Order status with New and Web Order type$")
    public void i_Should_Click_On_The_Back_Order_Status_With_New_And_Web_Order_Type() {
        zendeskAgentDashboardPage.clickBackOrderANDWebOrderANdNewOrderType();
    }
    @Then("^I should click on Order options button$")
    public void i_should_click_on_Order_options_button() throws InterruptedException {
        zendeskAgentDashboardPage.ClickOnOrderOptionsBtn();
    }
    @Then("^I should click on cancel order button$")
    public void iShouldClickOnCancelOrderButton() {
        zendeskAgentDashboardPage.ClickOnCancelOrderBtn();
    }

    @Then("^I should click on continue button if cart is not empty$")
    public void i_should_click_on_continue_button_if_cart_is_not_empty(){
        zendeskAgentDashboardPage.clickOnPopUpContinueButton();
    }

    @Then("^I should click on confirm button of a pop up$")
    public void i_should_click_on_confirm_button_of_a_pop_up(){
        zendeskAgentDashboardPage.clickOnPopUpContinueButton();
    }

    @Then("^I should click on Order History Option$")
    public void i_should_click_on_Order_History_Option() {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnOrderHistory());
    }

    @Then("^run the icm import and export job defined by key \"icm.importExport.job.name\"$")
    public void run_import_export_service_and_select_checkbox() {
        Assert.assertTrue(icmOrderImportExportService.runOrderImportExportService(),
                "Unable to run import and export icm service please check logs");
    }

    @Then("^i click on order id against key \"(.*?)\"$")
    public void i_should_click_on_order_id_in_Order_History_Option(String key) {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnOrderIdOrderHistoryPage(key));
    }

    @Then("^I should click on Replace Unit$")
    public void i_should_click_on_Replace_Unit() {
        zendeskAgentDashboardPage.ClickOnReplaceUnit();
    }

    @Then("^I should select the cancellation reason from the dropdown$")
    public void iShouldSelectTheCancellationReasonFromTheDropdown() throws InterruptedException {
        zendeskAgentDashboardPage.selectValueFromDropDownCancellationReasonByby(Props.getProp("zendesk.select.cancellationReason"),zendeskAgentDashboardPage.SelectCancellationReason);

    }

    @Then("^I should assert the Replace unit order$")
    public void i_should_assert_the_Replace_unit_order() {
        zendeskAgentDashboardPage.AssertReplaceUnitTitle();
    }
//    @Then("^I should enter the quantity no$")
//    public void i_should_enter_the_quantity_no() {
//        Assert.assertTrue(zendeskAgentDashboardPage.EnterTheQuantity());
//
//    }

    @Then("^I should enter the quantity no$")
    public void i_should_enter_the_quantity_no() {
        Assert.assertTrue(zendeskAgentDashboardPage.EnterTheQuantity());

    }
    @Then("^increase the quantity to 1$")
    public void increase_the_quantity_no() {
        Assert.assertTrue(zendeskAgentDashboardPage.enterTheQuantity());

    }

    @Then("^I should assert Return sucessfully title$")
    public void i_should_assert_Return_sucessfully_title() {
        zendeskAgentDashboardPage.AssertReturnSuccessfullyTitle();
    }


    @Then("^I should click on Create button$")
    public void i_should_click_on_Create_button() {
        zendeskAgentDashboardPage.ClickOnCreateBtn();
    }

    @Then("^I should select \"(.*)\"$")
    public void i_should_select_reason(String reason) {
        Assert.assertTrue(zendeskAgentDashboardPage.selectRapidReasonFault());

    }

    @Then("^I should click on create button$")
    public void i_should_click_on_create_button() {
        zendeskAgentDashboardPage.clickOnCreateButton();
    }

    @Then("^I should click on Continue button$")
    public void i_should_click_on_Continue_button() {
   zendeskAgentDashboardPage.ClickOnContinueBtn();
    }

    @Then("^I should assert the Cart header title$")
    public void i_should_assert_the_Cart_header_title() {
        zendeskAgentDashboardPage.CartHeaderTitle();
    }

    @Then("^I should click on Checkout button$")
    public void i_should_click_on_Checkout_button() {
      zendeskAgentDashboardPage.ClickOnCheckoutBtn();
    }

    @Then("^I should assert the Checkout header title$")
    public void i_should_assert_the_Checkout_header_title() {
   zendeskAgentDashboardPage.AssertCheckoutHeader();
    }

    @Then("^I should click on Payment method radio button$")
    public void i_should_click_on_Payment_method_radio_button() {
      zendeskAgentDashboardPage.ClickOnPaymentMethodRadioBtn();
    }

    @Then("^I should click on free of charge payment method")
    public void i_should_click_on_free_Payment_method_radio_button() {
        zendeskAgentDashboardPage.ClickOnFreePaymentMethodRadioBtn();
    }

    @Then("^I should click on Place order button$")
    public void i_should_click_on_Place_order_button() {
     zendeskAgentDashboardPage.ClickOnPlaceOrderBtn();
    }

    @Then("^I should assert the Snap Checkout$")
    public void i_should_assert_the_Snap_Checkout() {
        Assert.assertEquals(zendeskAgentDashboardPage.SnapCheckOutHeader(),"The order has been placed");

//        zendeskAgentDashboardPage.SnapCheckOutHeader();
    }

    @Then("^I should get and print the Place order text$")
    public void i_should_get_and_print_the_Place_order_text() {
   zendeskAgentDashboardPage.OrderPlaced();
    }

    @Then("^I should get \"(.*)\"$")
    public void i_should_check_the_order_type(String errorMessage) {
        Assert.assertEquals(zendeskAgentDashboardPage.getFaultErrorMessage(), Props.getProp(errorMessage));
    }

    @Then("^I should select a valid failure reason$")
    public void i_should_select_a_valid_failure_reason() {
        Assert.assertTrue(zendeskAgentDashboardPage.selectProperFaultReason());
    }


    @And("^I should enter the back order quantity no and select cancellation reason$")
    public void iShouldEnterTheBackOrderQuantityNoAndSelectCancellationReason() throws InterruptedException {
        //zendeskAgentDashboardPage.selectbackOrderQuantity();
        Assert.assertTrue(zendeskAgentDashboardPage.selectbackOrderQuantity());

    }

    @Then("^I should select the confirm button$")
    public void i_Should_Select_The_Confirm_Button() {
        zendeskAgentDashboardPage.clickOnConfirmButton();

    }
}
