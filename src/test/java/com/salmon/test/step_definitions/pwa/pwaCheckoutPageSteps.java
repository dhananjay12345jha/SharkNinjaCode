package com.salmon.test.step_definitions.pwa;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.pwa.pwaCheckoutPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class pwaCheckoutPageSteps extends PageObject {

    protected static final Logger LOG = LoggerFactory.getLogger(pwaCheckoutPageSteps.class);
    private pwaCheckoutPage pwacheckoutPage;

    public pwaCheckoutPageSteps(pwaCheckoutPage pwacheckoutPage)
    {
        this.pwacheckoutPage = pwacheckoutPage;
    }

    @When("^I fill in billing details$")
    public void i_fill_in_the_billing_details() throws InterruptedException {
        pwacheckoutPage.fillInBillingDetails();
    }
    @Then("^I place an order using Braintree payment method Pay via Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_Braintree_payment_method_Pay_via_Card_using_valid_and(String BraintreeCardNumber, String BraintreeCardExpiry, String BraintreeCardCvc) throws InterruptedException {
        String BTPayCardNumber = Props.getProp(BraintreeCardNumber);
        String BTPayCardExpiry = Props.getProp(BraintreeCardExpiry);
        String BTPayCardCvc = Props.getProp(BraintreeCardCvc);
        pwacheckoutPage.payWithBTCardRadioBtn(BTPayCardNumber,BTPayCardExpiry,BTPayCardCvc);
        pwacheckoutPage.termsAndConditionsCheckBox();
        pwacheckoutPage.submitOrderButton();
        Thread.sleep(10000);
    }
    @Then("^Verify that order is placed successfully$")
    public void verify_that_order_is_placed_successfully() throws InterruptedException
    {
        Assert.assertTrue(pwacheckoutPage.orderReceivedText());
    }
}
