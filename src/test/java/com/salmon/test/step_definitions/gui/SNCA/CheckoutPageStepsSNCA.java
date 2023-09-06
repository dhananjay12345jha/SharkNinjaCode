package com.salmon.test.step_definitions.gui.SNCA;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.framework.helpers.utils.RandomModelGenerator;
import com.salmon.test.page_objects.gui.SNCA.CartPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.PayPalCheckOutPage;
import com.salmon.test.page_objects.gui.SNCA.SecureCheckoutPageSNCA;
import com.salmon.test.step_definitions.gui.CartPageSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.sl.In;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class CheckoutPageStepsSNCA extends PageObject {
    private CartPageSNCA cartPageSNCA;
    private SecureCheckoutPageSNCA secureCheckoutPageSNCA;
    private PayPalCheckOutPage payPalCheckOutPage;

    public CheckoutPageStepsSNCA() {
        cartPageSNCA = new CartPageSNCA();
        payPalCheckOutPage = new PayPalCheckOutPage();
        secureCheckoutPageSNCA = new SecureCheckoutPageSNCA();
    }


    @When("^i click on checkout button at cart page$")
    public void click_on_checkout_button() {
        secureCheckoutPageSNCA = cartPageSNCA.clickCheckOutButton();
        Assert.assertTrue(secureCheckoutPageSNCA != null);
    }

    @Then("^i should navigate to \"(.*?)\" page or \"(.*?)\"$")
    public void validate_message_on_secure_checkout_page(String value1, String value2) throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        String actual = secureCheckoutPageSNCA.getTextSecureCheckOut();
        Assert.assertTrue(actual.equalsIgnoreCase(expected), "Expected-->>" + expected + "\n" + "Actual-->>" + actual);
        Thread.sleep(2000);
    }

    @And("^under order summary discount on items as \"(.*?)\" and discounted price shown should be same which is there on cart page$")
    public void validate_discount_should_be_shown_and_price_on_checkout_page(String promoCode) {
        String code;
        if (webDriver.getCurrentUrl().contains("uat") || webDriver.getCurrentUrl().contains("UAT")) {
            code = Props.getProp(promoCode + ".uat");
        } else {
            code = Props.getProp(promoCode);
            //code=Props.getProp(promoCode);
        }

        SoftAssert softAssert = new SoftAssert();
        for (String k : secureCheckoutPageSNCA.getPromoCodeFromCheckoutPage()) {
            String expected = code.toLowerCase();
            String actual = k.toLowerCase();
            softAssert.assertEquals(actual, expected);
        }

        List<Float> priceAfterDiscountOnCheckoutPage = secureCheckoutPageSNCA.getProductPriceList();
        softAssert.assertEquals(priceAfterDiscountOnCheckoutPage.size(), CartPageStepsSNCA.newPrice.size(),
                "Mismatch in number of items shown over cart page and checkout page");

        for (int i = 0; i < CartPageStepsSNCA.newPrice.size(); i++) {
            softAssert.assertEquals(priceAfterDiscountOnCheckoutPage.get(i), CartPageStepsSNCA.newPrice.get(i), "There is price mismatch at the product shown at position " + i);
        }

        softAssert.assertAll();
    }

    @Then("^i enter my email id as \"(.*?)\"$")
    public void enter_email_id_on_secureCheckout_page(String text) {
        String emailId;
        if (text.equalsIgnoreCase("RandomGenerated")) {
            emailId = RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC) + "@salmon.com";
        } else {
            if (Props.getProp(text) != null) {
                emailId = Props.getProp(text);
            } else {
                emailId = text;
            }
        }
        Assert.assertTrue(secureCheckoutPageSNCA.setEmailId(emailId));
    }


    @And("^I select Yes, I have a password$")
    public void select_option_i_have_a_password() {
        Assert.assertTrue(secureCheckoutPageSNCA.selectRadioButtonIHavePassword());
    }

    @And("^i select Continue as Guest$")
    public void select_option_Continue_as_guest() {
        Assert.assertTrue(secureCheckoutPageSNCA.selectRadioButtonContinueAsGuest());
    }

    @Then("^I should get Error Alert with message \"([^\"]*)\"$")
    public void iShouldGetErrorAlertWithMessage(String errMsg) throws Throwable {
        Assert.assertTrue(secureCheckoutPageSNCA.checkErrorAlertMsg(errMsg), "Correct error message is not displayed in the alert");
    }
    @And("^I enter my password as \"(.*?)\"$")
    public void enter_password(String password) {
        String pass="";
        if(Props.getProp(password)!=null){
            pass=Props.getProp(password);
        }
        else {
            pass=password;
        }

        Assert.assertTrue(secureCheckoutPageSNCA.setPassword(pass));
    }

    @Then("^i click on continue to shipping button$")
    public void click_continue_to_shipping_button() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(secureCheckoutPageSNCA.clickOnContinueShippingButton());
        Thread.sleep(5000);
    }

    @Then("^i uncheck the checkbox billing address same as shipping address$")
    public void uncheck_checkbox_same_as_shipping_address(){
        Assert.assertTrue(secureCheckoutPageSNCA.selectCheckBoxBillingAddressSameAsShipping());
    }

    @And("^if any error occurred try login one more time with \"(.*?)\" and \"(.*?)\"$")
    public void validate_login_has_been_successful(String email, String pass) {
        Assert.assertEquals(secureCheckoutPageSNCA.tryLoginAgainWith(email, pass), "Success");
    }


    @Then("^I select payment option as \"Pay by Card\"$")
    public void select_option_pay_By_card() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(secureCheckoutPageSNCA.selectRadioButtonPayByCard());
    }

    @Then("^I select payment option as \"Pay by PayPal\"$")
    public void select_option_pay_By_payPal() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(secureCheckoutPageSNCA.selectRadioButtonPayByPayPal());
    }

    @And("^i entered all card number, card expiration date and cvv number$")
    public void enter_card_details() {
        String name = Props.getProp("brainTree.name.onCard");
        String number = Props.getProp("brainTree.card.number");
        String date = Props.getProp("brainTree.card.expiryDate");
        String cvv = Props.getProp("brainTree.card.cvcNumber");
        String postalCode = Props.getProp("brainTree.postal.code");
        //Assert.assertTrue(SecureCheckoutPageSNCA.isPageRefreshedAndDetailsShown(), "After selecting payby card radio button, To fill card details, boxes not showing ");
        SecureCheckoutPageSNCA.isPageRefreshedAndDetailsShown();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(secureCheckoutPageSNCA.enterNameOnCard(name));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterCardNumber(number));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterExpirationDate(date));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterCvvNumber(cvv));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterPostalCodeInCardDetails(postalCode));
        softAssert.assertAll();
    }

    @When("^I enter incorrect card name$")
    public void enterIncorrectCardName() {
        Assert.assertTrue(secureCheckoutPageSNCA.enterNameOnCard("1234"));
    }

    @When("^I enter correct card name$")
    public void enterCorrectCardName() {
        String name = Props.getProp("brainTree.name.onCard");
        Assert.assertTrue(secureCheckoutPageSNCA.enterNameOnCard(name));
    }

    @When("^I enter incorrect card number$")
    public void enterIncorrectCardNumber() {
        Assert.assertTrue(secureCheckoutPageSNCA.enterCardNumber("1234 1234 1234 1234"));
    }

    @When("^I enter correct card number$")
    public void enterCorrectCardNumber() {
        Assert.assertTrue(secureCheckoutPageSNCA.enterCardNumber(Props.getProp("brainTree.card.number")));
    }

    @When("^I enter incorrect card expiration date$")
    public void enterIncorrectCardExpiry() {
        Assert.assertTrue(secureCheckoutPageSNCA.enterExpirationDate("0121"));
    }

    @When("^I enter correct card expiration date$")
    public void enterCorrectCardExpiry() {
        Assert.assertTrue(secureCheckoutPageSNCA.enterExpirationDate(Props.getProp("brainTree.card.expiryDate")));
    }

    @And("^i entered card number, expiration date, cvv number and postal code of AMEX card$")
    public void enterAmexCardDetails() {
        String name = Props.getProp("brainTree.name.onCard");
        String number = Props.getProp("stripe.card.number.american");
        String date = Props.getProp("stripe.card.expiryDate");
        String cvv = Props.getProp("stripe.card.cvcNumber");
        String postalCode = Props.getProp("brainTree.postal.code");
        Assert.assertTrue(SecureCheckoutPageSNCA.isPageRefreshedAndDetailsShown(), "After selecting payby card radio button, To fill card details, boxes not showing ");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(secureCheckoutPageSNCA.enterNameOnCard(name));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterCardNumber(number));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterExpirationDate(date));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterCvvNumber(cvv));
        softAssert.assertTrue(secureCheckoutPageSNCA.enterPostalCodeInCardDetails(postalCode));
        softAssert.assertAll();
    }

    @Then("^i clicked on review order button$")
    public void click_on_review_order_button() {
        Assert.assertTrue(secureCheckoutPageSNCA.clickOnReviewOrderButton());
    }

    @Then("^i entered the password as \"(.*?)\" which does not full fill the criteria of creation$")
    public void enter_the_password_on_secure_checkout_page(String password){
        Assert.assertTrue(secureCheckoutPageSNCA.setCreateNewAccountPassword(password));
        String expected=Props.getProp("error.message.password.account.creation").trim();
        if(!expected.contains(", ")){
            expected=expected.replace(",",", ");
        }
        String actual=secureCheckoutPageSNCA.getTextFromErrorMessageOfWrongPasswordAtAccountCreation().trim();
        Assert.assertEquals(actual,expected);
    }


    @Then("^review order button should be shown enabled$")
    public void isReviewButtonEnabled() {
        Assert.assertTrue(secureCheckoutPageSNCA.getReviewOrderButtonStatus().equalsIgnoreCase("enabled"));
    }

    @Then("^review order button should be shown disabled$")
    public void isReviewButtonDisabled() {
        Assert.assertTrue(secureCheckoutPageSNCA.getReviewOrderButtonStatus().equalsIgnoreCase("disabled"));
    }

    @And("^i tick checkbox i agree to terms and condition$")
    public void tickCheckboxIAgreeTermsAndCondition() {
        Assert.assertTrue(secureCheckoutPageSNCA.selectCheckBoxIAgreeToTermsAndCondition());
    }

    @Then("^place order should be disabled and user should not be able to click it$")
    public void validate_placeOrder_button_should_be_disabled(){
        Assert.assertTrue(secureCheckoutPageSNCA.isPlaceOrderButtonDisabled());
    }

    @And("^i click on place order button$")
    public void click_on_place_order_button() {
        Assert.assertTrue(secureCheckoutPageSNCA.clickOnPlaceOrderButton());
    }


    @Then("^I should enter the \"(.*)\" order number$")
    public void i_should_enter_order_number(String orderId) {
        secureCheckoutPageSNCA.EnterOrderNo(Props.getProp(orderId));
    }

    @Then("^I should enter the \"(.*)\" delivery postal code$")
    public void i_should_enter_the_delivery_postal_code(String postalCode) {
        secureCheckoutPageSNCA.enterDeliveryPostalCode(Props.getProp(postalCode));
    }

    @When("^I click on Find my Order Button$")
    public void clickFindmyOrderButton() {
        secureCheckoutPageSNCA.clickFindMyOrderButton();
    }

    @Then("^I should be on OrderDetails page$")
    public void verifyOrderDetailsPage() throws InterruptedException {
//        Assert.assertTrue(offersPage.getTeaserText().contains(Props.getProp("offerpage.teaser.text")));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains(Props.getProp("orderDetails.url.contains"))));
        Thread.sleep(3000);
    }
    @Then("^order get placed and a message \"(.*?)\" should be there or \"(.*?)\"$")
    public void order_get_placed_message(String value1, String value2) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertEquals(secureCheckoutPageSNCA.getTextAfterSuccessfullyOrderPlaced(), expected);
    }

    @And("^get the order id from secure checkout page$")
    public void fetch_order_id_from_secure_checkout_page()
    {
        String text=secureCheckoutPageSNCA.getOrderIdAfterPlacingOrder();
        Assert.assertFalse(text.equalsIgnoreCase("not found"),"Actual Value Of OrderId is-->>"+text);
    }

    @And("^update order id against key \"(.*?)\" in config file$")
    public void save_order_id_in_config_file(String key){
        String orderId=secureCheckoutPageSNCA.getOrderIdAfterPlacingOrder();
        Assert.assertTrue(secureCheckoutPageSNCA.saveOrderIdInConfigFile(key,orderId));
    }

    @Then("^I click on Continue To Payment Option Button$")
    public void click_on_continue_to_payment_option_button() {
        Assert.assertTrue(secureCheckoutPageSNCA.clickOnContinueToPaymentOptionBtn());
    }

    @Then("^I click on PayPal Checkout button$")
    public void click_paypal_checkout_button() {
        SecureCheckoutPageSNCA.isPageRefreshedAndDetailsShown();
        Assert.assertTrue(secureCheckoutPageSNCA.clickPayPalCheckoutButton() != null);
    }

    @And("^I switched over PayPal window$")
    public void switch_to_paypal_window() throws InterruptedException {
        Assert.assertTrue(payPalCheckOutPage.switchToPayPalWindow());
    }


    @Then("^I enter username as \"(.*?)\"$")
    public void enter_email_for_paypal_account(String email) {
        Assert.assertTrue(payPalCheckOutPage.enterEmail(Props.getProp(email)), "Unable to enter the email in paypal");
    }

    @Then("^I click on next button$")
    public void click_on_next_button() {
        Assert.assertTrue(payPalCheckOutPage.clickNextButton());
    }

    @And("^I enter password as \"(.*?)\"$")
    public void enter_password_for_paypal_account(String password) {
        Assert.assertTrue(payPalCheckOutPage.enterPassword(Props.getProp(password)));
    }

    @Then("^I click on Login Button$")
    public void click_on_login_button() {
        Assert.assertTrue(payPalCheckOutPage.clickLoginButton());
    }

    @Then("^verify message that paypal information is incorrect having display text \"(.*?)\"$")
    public void verifyPaypalInfomationIncorrectMessage(String expected) {
        Assert.assertEquals(payPalCheckOutPage.getInvalidInformationPayPal(), expected);
    }

    @Then("^verify message that paypal provided information is incorrect having display text \"(.*?)\" or \"(.*?)\"$")
    public void verifyPaypalInfomationIncorrectMessages(String value1, String value2) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertEquals(payPalCheckOutPage.getInvalidInformationPayPal(), expected);
    }

    @When("^I click on change email link$")
    public void clickChangeEmailLink() {
        Assert.assertTrue(payPalCheckOutPage.clickChangeEmailLink());
    }

    @Then("if accept cookie button shown then click over it$")
    public void accept_cookie_button() {
        payPalCheckOutPage.clickCookieAcceptBtn();
    }

    @Then("^I validate user should be logged into the PayPal account having display text \"(.*?)\"$")
    public void validate_user_should_be_logged_in_successfully(String data) {
        String expected=Props.getProp("validation.text.after.paypal.login.desktop");
        if(Props.getdevice("application.run.mode").equalsIgnoreCase("mobile")){
            expected=Props.getProp("validation.text.after.paypal.login.mobile");
        }
        Assert.assertEquals(payPalCheckOutPage.getTextAfterLoginIntoPayPal(), expected);
    }

    @Then("^I click on continue button$")
    public void click_on_continue_button() {
        Assert.assertTrue(payPalCheckOutPage.clickOnContinueButtonAgainForPayPal());
    }

    @And ("^again i click on continue button$")
    public void again_Click_On_ContinueButton(){
        Assert.assertTrue(payPalCheckOutPage.clickContinueButton());
    }

    @Then("^order get placed and a message \"(.*?)\" should be there$")
    public void order_get_placed_message(String expected) {
        Assert.assertEquals(secureCheckoutPageSNCA.getTextAfterSuccessfullyOrderPlaced(), expected);
    }

    @Then("^i should navigate to \"(.*?)\" page$")
    public void validate_message_on_secure_checkout_page(String expected) {
        String actual = secureCheckoutPageSNCA.getTextSecureCheckOut();
        Assert.assertTrue(actual.equalsIgnoreCase(expected), "Expected-->>" + expected + "\n" + "Actual-->>" + actual);
    }

    @And("^discount price and promoCode shown at checkout page should be same which is there on cart$")
    public void validate_promocode_and_discount_price_on_checkout_page_should_be_same_as_on_cart_page() {
        SoftAssert softAssert = new SoftAssert();
        int promocodePosition = secureCheckoutPageSNCA.getPromoCodeFromCheckoutPage().size() - 1;
        softAssert.assertEquals(secureCheckoutPageSNCA.getPromoCodeFromCheckoutPage().get(promocodePosition), CartPageStepsSNCA.promoCodeShown);
        softAssert.assertEquals(secureCheckoutPageSNCA.getDiscountPriceOnOrder(), CartPageStepsSNCA.discountPriceAtOrder);
        softAssert.assertAll();
    }

    @And("^i validate \"Subtotal value\", \"Estimated Shipping\" and \"Total Value\" should be same as on cart page$")
    public void validate_subtotal_estimatedShipping_Total_Cost() {
        float actualSubtotal = secureCheckoutPageSNCA.getSubTotalPrice();
        float actualEstimatedShippingCost = secureCheckoutPageSNCA.getEstimatedShippingPrice();
        float actualTotalPrice = secureCheckoutPageSNCA.getTotalPrice();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualSubtotal, CartPageStepsSNCA.subTotal);
        softAssert.assertEquals(actualEstimatedShippingCost, CartPageStepsSNCA.estimatedShipping);
        softAssert.assertEquals(actualTotalPrice, CartPageStepsSNCA.totalPrice);

    }
    @And("^i uncheck the checkbox to fill different billing address other than shipping address$")
    public void uncheckCheckboxToFillDifferentBillingAddress() throws InterruptedException {
        Assert.assertTrue(secureCheckoutPageSNCA.uncheckCheckboxToFillDifferentBillingAddress());
    }

    @And("^I check the checkbox for news letter subscription$")
    public void checkCheckboxToSubscribeNewsletter() throws InterruptedException {
        Assert.assertTrue(secureCheckoutPageSNCA.checkCheckboxToSubscribeNewsletter());
    }
}
