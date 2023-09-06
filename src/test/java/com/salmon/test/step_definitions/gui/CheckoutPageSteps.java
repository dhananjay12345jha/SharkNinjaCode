package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSCartOverlayPage;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSCartPage;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSHomePage;
import com.salmon.test.page_objects.gui.SNCA.PayPalCheckOutPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPageSteps extends PageObject {

    protected static final Logger LOG = LoggerFactory.getLogger(CheckoutPageSteps.class);
    private CheckoutPage checkoutPage;
    private PayPalCheckOutPage payPalCheckOutPage;
    private CartPage cartPage;
    private MSHomePage msHomepage;
    private MSCartOverlayPage msCartOverLayPage;
    private MSCartPage mscartPage;

    private HomePage homePage;

    private static String parentWindowHandler;

    private CartOverlayPage cartOverlayPage;


    public CheckoutPageSteps() {
        checkoutPage = new CheckoutPage();
        payPalCheckOutPage = new PayPalCheckOutPage();
        cartPage = new CartPage();
        msHomepage = new MSHomePage();
        mscartPage = new MSCartPage();
        msCartOverLayPage = new MSCartOverlayPage();
        homePage = new HomePage();
        cartOverlayPage = new CartOverlayPage();
    }


    @When("^I click on returning customer login option$")
    public void i_click_on_returning_customer_login_option() {
        Assert.assertTrue(checkoutPage.returningCustomerLoginHereLink());
    }
    @When("^I sign in using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_sign_in_using_valid_and(String loginEmail, String loginPassword) throws InterruptedException {

//		String returningCustomerEmailLogin = Props.getProp(loginEmail);
        String returningCustomerPasswordLogin = Props.getProp(loginPassword);

        assertThat(checkoutPage.checkReturningCustomerLoginUserForm()).isTrue();

//		checkoutPage.returningCustomerEmailLogin().sendKeys(returningCustomerEmailLogin);
        checkoutPage.returningCustomerEmailLogin(Props.getProp(loginEmail));
        checkoutPage.returningCustomerPasswordLogin().sendKeys(returningCustomerPasswordLogin);
//		Thread.sleep(5000);
        checkoutPage.returningCustomerSignInButton();
    }

    @When("^I fill in the billing details without Zipcode$")
    public void i_fill_in_the_billing_details_without_Zipcode() throws InterruptedException {
        checkoutPage.fillInBillingDetailsWithoutZipcode();
    }

    @When("^I fill in the billing details$")
    public void i_fill_in_the_billing_details() throws InterruptedException {
        checkoutPage.fillInBillingDetails();
    }

    @When("^I fill in the billings details without zipcode$")
    public void i_fill_in_the_billings_details_without_zipcode() throws InterruptedException {
        checkoutPage.fillInBillingsDetailsWithoutZipCode();
    }
    @And("^I fill in the billing details for autria$")
    public void i_fill_in_the_billing_details_for_autria() throws Throwable {
        checkoutPage.fillInBillingDetailsAustria();
    }
    @And("^i select country as Austria from country selection list$")
    public void i_select_country_as_austria_from_country_selection_list() throws Throwable {
        checkoutPage.countrySelection();
    }

    @Then("^validate phone number is successfully entered$")
    public void validate_Phone_Number_Updated_Successfully() {
        boolean flag = false;
        flag = checkoutPage.enterPhoneNumberBilling();
        if (!flag) {
            flag = checkoutPage.enterPhoneNumberShipping();
        }
        Assert.assertTrue(flag, "Unable to enter phone number please check ");
    }

    @When("^I fill in valid registered email and others billing details$")
    public void I_fill_in_valid_registered_email_and_others_billing_details() throws InterruptedException {
        checkoutPage.fillvalidDetails();
    }

    @Then("^I fill in the shipping details$")
    public void I_fill_in_the_shipping_details() throws InterruptedException {
        checkoutPage.fillInShippingDetails();
    }

    //Valid Postal code :Rita Singh
    @When("^I fill the valid postal code for billing$")
    public void I_fill_the_valid_postal_code_for_billing() throws InterruptedException {
        checkoutPage.fillValidPostalCode();
    }

    @When("^I fill the valid postal code for shipping$")
    public void I_fill_the_valid_postal_code_for_shipping() throws InterruptedException {
        checkoutPage.fillValidPostalCodeforShipping();
    }

    @Then("^Billing addresses are auto populated$")
    public void Verify_Billing_Addresses_are_AutoPopulated() {
        Assert.assertTrue(checkoutPage.VerifyAutoPopulateAddresses());
    }

    @Then("^Shipping addresses are auto populated$")
    public void Verify_Shipping_Addresses_are_AutoPopulated() {
        Assert.assertTrue(checkoutPage.VerifyAutoPopulateAddressesforShipping());
    }

    @When("^shipping address is same as billing address$")
    public void shipping_address_is_same_as_billing_address() {
        Assert.assertTrue(checkoutPage.validateShippingAddessSameAsBilling(), "there is an assertion error.");
    }

    @When("^I click on enter address manually for billing$")
    public void I_click_on_enter_address_manually_for_billing() {
        checkoutPage.ClickEnterAddressManually();
    }

    @When("^I click on enter address manually for shipping$")
    public void I_click_on_enter_address_manually_for_shipping() {
        checkoutPage.ClickEnterAddressManuallyForShipping();
    }

    @When("^I click on different shipping address$")
    public void I_click_on_different_shipping_address() {
        checkoutPage.ClickonDifferentShippingAddressButton();
    }

    @Then("^I am able to fill the billing address manually$")
    public void manually_fill_billing_address() {
        Assert.assertTrue(checkoutPage.fillBillingAddressManually());
    }

    @Then("^I am able to fill the shipping address manually$")
    public void manually_fill_shipping_address() {
        Assert.assertTrue(checkoutPage.fillShippingAddressManually());
    }

    @Then("^Shipping address form is displayed$")
    public void Verify_shipping_address_form() {
        Assert.assertEquals(checkoutPage.verifyShippingAddressForm(), Props.getProp("shipping.address.heading"));
    }


    @When("^I choose to create an account$")
    public void i_choose_to_create_an_account() {
        checkoutPage.createAccount();
    }

    //Rita Singh
    @When("^I choose to create an account with incorrect password$")
    public void I_choose_to_create_an_account_with_incorrect_password() throws InterruptedException {
        checkoutPage.createAccountwithIncorrectPassword();
    }


    @Then("^verify successful login$")
    public void verify_successful_login() {
        String actual = checkoutPage.returningCustomerLogoutLink();
        String expected = Props.getMessage("logouttext");
        Assert.assertTrue(actual.contains(expected), "Actual text-->>" + actual + ", Expected-->>" + expected);

        if (UrlBuilder.isBsedge() || UrlBuilder.isLocalChrome() || UrlBuilder.isNexus()) {
            String url = getWebDriver().getCurrentUrl();
            url = "https://shrkn-uat:aiwu2Mae5u@" + url.substring(8);
            getWebDriver().get(url);
        }
    }

    @Then("^I Should get your email or password is incorrect error message$")
    public void verifyInValidEmailErrorMessage() throws InterruptedException {
        String expected = Props.getProp("valid.email.required_forLogin").trim();
        String actual = checkoutPage.verifyErrorMessage();
        Assert.assertTrue(actual.contains(expected));
        LOG.error("Actual Message is--> " + actual, "Expected message is---> "+ expected);

    }

    @Then("^I place an order using payment method PayPal using invalid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_PayPal_using_invalid_and(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);

        checkoutPage.payPalRadioBtn();
//		Thread.sleep(5000);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();
//		Thread.sleep(5000);
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButton();
//		Thread.sleep(5000);
        System.out.println(webDriver.getCurrentUrl());
//		Thread.sleep(5000);
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
//		Thread.sleep(5000);
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
//		Thread.sleep(5000);
        checkoutPage.payPalLoginButton();
        ;
        Thread.sleep(15000);


    }


    @Then("^I place an order using payment method PayPal using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_PayPal_using_valid_and(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);

        checkoutPage.payPalRadioBtn();
        Thread.sleep(5000);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();
//		Thread.sleep(5000);
        checkoutPage.termsAndConditionsCheckBox();
        Thread.sleep(5000);
        checkoutPage.placeOrderSecurelyButton();
        Thread.sleep(5000);
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        //checkoutPage.payPalNextButton();
//		Thread.sleep(5000);
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
//		Thread.sleep(5000);
        checkoutPage.payPalLoginButton();
        ;
        Thread.sleep(15000);
        checkoutPage.payPalContinueButton();
        ;
        Thread.sleep(15000);
        System.out.println(webDriver.getTitle());

        System.out.println(webDriver.getCurrentUrl());
//		Thread.sleep(5000);


    }

    //Braintree-Paypal-Rita
    @Then("^I place an order using payment method Braintree PayPal using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_using_valid_and(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.payPalRadioBtn();
        checkoutPage.competitionsAndNewCheckbox();
        //checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();

        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
        //checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.payPalContinueButton();
        // checkoutPage.clickPayPalPayNowButton();
        // checkoutPage.clickOnContinueButton();
        // checkoutPage.clickOnContinueButton();
        // checkoutPage.clickOnBlackContinueButton();
        webDriver.switchTo().window(parentWindowHandler);
    }
    @Then("^I place an order using payment method Braintree PayPal using valid \"([^\"]*)\" and \"([^\"]*)\" for express checkout$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_using_valid_express(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.paypalExpressCheckout();


        String parentWindowHandler = webDriver.getWindowHandle();
       // checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
        checkoutPage.changeCountryOnLocalsBasis();
        checkoutPage.payPalContinueButton();

        webDriver.switchTo().window(parentWindowHandler);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButtonExpressCheckout();
    }

    @Then("^I place an order using payment method Braintree PayPal for DE_UK_FR using valid \"([^\"]*)\" and \"([^\"]*)\" for express checkout$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_for_DE_UK_FR_using_valid_express(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.paypalExpressCheckout();


        String parentWindowHandler = webDriver.getWindowHandle();
        // checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
//        checkoutPage.changeCountryOnLocalsBasis();
        checkoutPage.payPalContinueButton();

        webDriver.switchTo().window(parentWindowHandler);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButtonExpressCheckout();
    }

    @Then("^I place an order using payment method Braintree PayPal using valid \"([^\"]*)\" and \"([^\"]*)\" for express checkout paypal credit$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_using_valid_express_paypalcredit(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.paypalExpressCheckout();

        String parentWindowHandler = webDriver.getWindowHandle();


        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
        checkoutPage.changeCountryOnLocalsBasis();
        checkoutPage.clickPaypalCreditPaymentOption1();

        checkoutPage.clickOnContinueButton();
        checkoutPage.clickPaypalCreditEmiOption1();
        checkoutPage.clickChooseAndContinueButton();
        checkoutPage.clickOnContinueButton();
        webDriver.switchTo().window(parentWindowHandler);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButtonExpressCheckout();
    }
    @Then("^I place an order using payment method Braintree PayPal using valid \"([^\"]*)\" and \"([^\"]*)\" for express checkout pay in 3$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_using_valid_express_PayInThree(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.paypalExpressCheckout();

        String parentWindowHandler = webDriver.getWindowHandle();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        checkoutPage.changeCountryOnLocalsBasis();
        IsPageLoaded.waitAllRequest();
        checkoutPage.clickPaypalpayinThree();
        checkoutPage.clickOnContinueButton();
        checkoutPage. paypalPayMobileNumber();
        checkoutPage.termsAndConditionsCheckBoxForPayLaterDE();
        checkoutPage.agreeAndContinueButtonForPayLaterDE();
        checkoutPage.clickToContinueButtonBlackScreen();
        checkoutPage.clickOnBindingApplicationButton();
        webDriver.switchTo().window(parentWindowHandler);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButtonExpressCheckout();
    }

    @Then("^I place an order using payment method Klarna using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Klarna_using_valid_and(String ContactNumber, String klarnaOTPNumber) throws InterruptedException {
        checkoutPage.competitionsAndNewCheckbox();
        //checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.enterKlarnaPhoneNo(Props.getProp(ContactNumber));
        checkoutPage.klarnaContinueButton();
        checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
        //checkoutPage.payIn3FinancingButtonKlarna();
        checkoutPage.klarnaContinueButton2();
        checkoutPage.klarnaContinueButton3();
        checkoutPage.payWithKlarnaButton();
        checkoutPage.chooseFasterCheckoutButton();
        //checkoutPage.clickPayPalPayNowButton();
        //checkoutPage.clickOnContinueButton();
        webDriver.switchTo().window(parentWindowHandler);
    }

    @Then("^I place an order using payment method Klarna for UK using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Klarna_for_UK_using_valid_and(String ContactNumber, String klarnaOTPNumber) throws InterruptedException {
        checkoutPage.competitionsAndNewCheckbox();
        //checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.enterKlarnaPhoneNo(Props.getProp(ContactNumber));
        //  IsPageLoaded.waitAllRequest();
        // checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.klarnaContinueButton();
        checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
        checkoutPage.payNowKlarnaSelectMethod();
        //checkoutPage.payIn3FinancingButtonKlarna();
        //webDriver.switchTo().window(parentWindowHandler);
    }


    @Then("^The user is able to pay using \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void The_User_is_able_to_pay_using_following(String paymentMethod, String ContactNumber, String klarnaOTPNumber) throws Throwable {
        IsPageLoaded.waitAllRequest();
        switch (paymentMethod) {
            case "Ratenzahlung":
                checkoutPage.RatenzahlungRadioButtonKlarnaDE();
                checkoutPage.competitionsAndNewCheckbox();
                checkoutPage.termsAndConditionsCheckBox();
                String parentWindowHandler = webDriver.getWindowHandle();
                checkoutPage.placeOrderSecurelyButton();

                Set<String> allWindowHandles = webDriver.getWindowHandles();

                if (allWindowHandles.size() > 2) {
                    throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
                }

                for (String handle : allWindowHandles) {
                    if(!handle.equalsIgnoreCase(parentWindowHandler)) {
                        webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                        if(!UrlBuilder.isMobile()){
                            webDriver.manage().window().maximize();
                        }
                        break;
                    }
                }

                checkoutPage.enterKlarnaPhoneNoForDE(Props.getProp(ContactNumber));
                checkoutPage.klarnaContinueButton();
                checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
//                checkoutPage.klarnaContinueButton2();
                checkoutPage.termsAndConditionsCheckBoxForRatenzahlungKlarna();
                checkoutPage.klarnaDEEinkaufButtonContinue();
                break;


            case "In 30 Tagen bezahlen":
                checkoutPage.PayIn30DaysRadioButtonKlarnaDE();
                checkoutPage.competitionsAndNewCheckbox();
                checkoutPage.termsAndConditionsCheckBox();
                parentWindowHandler = webDriver.getWindowHandle();
                checkoutPage.placeOrderSecurelyButton();

                allWindowHandles = webDriver.getWindowHandles();

                if (allWindowHandles.size() > 2) {
                    throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
                }

                for (String handle : allWindowHandles) {
                    if(!handle.equalsIgnoreCase(parentWindowHandler)) {
                        webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                        if(!UrlBuilder.isMobile()){
                            webDriver.manage().window().maximize();
                        }
                        break;
                    }
                }

                checkoutPage.enterKlarnaPhoneNoForDE(Props.getProp(ContactNumber));
                checkoutPage.klarnaContinueButton();
                checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
                checkoutPage.klarnaContinueButtonBestatigen();

                break;

            case "Jetzt bezahlen":
                checkoutPage.PayNowRadioButtonKlarnaDE();
                checkoutPage.LatschriftRadioButtonKlarnaDE();
                checkoutPage.competitionsAndNewCheckbox();
                checkoutPage.termsAndConditionsCheckBox();
                parentWindowHandler = webDriver.getWindowHandle();
                checkoutPage.placeOrderSecurelyButton();

                allWindowHandles = webDriver.getWindowHandles();

                if (allWindowHandles.size() > 2) {
                    throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
                }

                for (String handle : allWindowHandles) {
                    if(!handle.equalsIgnoreCase(parentWindowHandler)) {
                        webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                        if(!UrlBuilder.isMobile()){
                            webDriver.manage().window().maximize();
                        }
                        break;
                    }
                }

                checkoutPage.enterKlarnaPhoneNoForDE(Props.getProp(ContactNumber));
                checkoutPage.klarnaContinueButton();
                checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
                checkoutPage.klarnaContinueButtonZehle();

                break;

            default:
                LOG.info("All Payment Methods have covered");
        }
    }

    @Then("^I place an order using payment method Klarna for DE using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Klarna_for_DE_using_valid_and(String ContactNumber, String klarnaOTPNumber) throws InterruptedException {
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.enterKlarnaPhoneNoForDE(Props.getProp(ContactNumber));
        checkoutPage.klarnaContinueButton();
        checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
        //checkoutPage.payIn3FinancingButtonKlarna();
        checkoutPage.klarnaContinueButton2();
        checkoutPage.klarnaContinueButton3();
        checkoutPage.payWithKlarnaButton();
        checkoutPage.chooseFasterCheckoutButton();
        webDriver.switchTo().window(parentWindowHandler);
    }



    @Then("^I place an order using payment method Braintree PayPal using invalid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Braintree_PayPal_using_invalid_and(String payPalEmail, String payPalPassword) throws InterruptedException {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);

        checkoutPage.payPalRadioBtn();

        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();

        checkoutPage.termsAndConditionsCheckBox();

        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }
        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                break;
            }
        }
        if (!(UrlBuilder.isMobile())) {
            webDriver.manage().window().maximize();
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.payPalNextButton();

        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);

        checkoutPage.payPalLoginButton();


    }

    @When("^I enter valid card number \"([^\"]*)\" and expiry date \"([^\"]*)\"$")
    public void enter_invalid_expiry_date(String stripeCardNumber, String stripeInValidCardExpiry) throws InterruptedException {
        String stripePayCardNumber = Props.getProp(stripeCardNumber);
        String stripePayInvalidCardExpiry = Props.getProp(stripeInValidCardExpiry);
        checkoutPage.InvalidCardExpiry(stripePayCardNumber, stripePayInvalidCardExpiry);

    }

    @When("^I enter invalid card number \"([^\"]*)\"$")
    public void enter_invalid_card_number(String BTCardNumber) throws InterruptedException {
        String BTPayCardNumber = Props.getProp(BTCardNumber);
        checkoutPage.InvalidCardNumber(BTPayCardNumber);
    }

    //Rita Singh
    @Then("^verify valid error message for create account feature with incorrect password during checkout$")
    public void verify_valid_error_message_for_create_account_feature_with_incorrect_password_during_checkout() {
        boolean flag = false;
        String actualMsg = checkoutPage.verifyerrorMessageforCreateAccount();
        String expectedMsg1 = Props.getMessage("create.Account.incorrect.password.error.text");
        String expectedMsg2 = Props.getMessage("create.Account.incorrect.password.error.textalternate");

        if (actualMsg.contains(expectedMsg1))
            flag = true;
        else if (actualMsg.contains(expectedMsg2))
            flag = true;

        Assert.assertTrue(flag, "Actual message = \"" + actualMsg + "\" \n Expected Message = \"" + expectedMsg1 + "\" \nOr Expected message = \"" + expectedMsg2 + "\"");
    }

    @Then("^verify valid error message for create account feature with weak password during checkout$")
    public void verify_valid_error_message_for_create_account_feature_with_weak_password_during_checkout() {
        boolean flag = false;
        String actualMsg = checkoutPage.verifyerrorMessageforWeakPassword();
        String expectedMsg1 = Props.getMessage("create.Account.weak.password.error.text");
        String expectedMsg2 = Props.getMessage("create.Account.weak.password.error.textalternate");

        if (actualMsg.contains(expectedMsg1))
            flag = true;
        else if (actualMsg.contains(expectedMsg2))
            flag = true;

        Assert.assertTrue(flag, "Actual message = \"" + actualMsg + "\" \n Expected Message = \"" + expectedMsg1 + "\" \nOr Expected message = \"" + expectedMsg2 + "\"");
    }

    @When("^I click on Redeem here link on checkout page$")
    public void clickPromoEntryLink() {
        Assert.assertTrue(checkoutPage.clickRedeemHereLink());
    }

    @Then("^I enter item discount coupon code \"([^\"]*)\"$")
    public void enterPromoCode(String promoCode) throws InterruptedException {
        String promoCode1 = Props.getProp(promoCode);
        Assert.assertTrue(checkoutPage.enterPromoCode(promoCode1));
        Thread.sleep(2000);
    }

    @Then("^I click on apply button$")
    public void clickApplyPromo() throws InterruptedException {
        Assert.assertTrue(checkoutPage.clickApplyButton(), "Unable to click on apply promo button");
        Thread.sleep(2000);
    }

    @Then("^I should see message above order summary that promotional code has been applied successfully$")
    public void verifyPromoAppliedSuccessMessage() {
        Assert.assertTrue(checkoutPage.verifyPromoAppliedSuccessMessage());
    }

    @And("^sum of Final price should be equal to sum of subtotal, VAT and Shipping$")
    public void validateFinalPriceShouldBeEqualToSubtotalVATandShippingCharges() {
        float calculated = checkoutPage.getShippingCost() + checkoutPage.getVATCost() + cartPage.getCartSubtotal();
        calculated = new BigDecimal(calculated).setScale(2, RoundingMode.HALF_UP).floatValue();
        Assert.assertEquals(checkoutPage.getTotalCost(), calculated, "Sum of VAT, Shipping and SubTotal is not equal to Final price ");
    }

    /*
        @Then("^I place an order using payment method Pay With Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
        public void i_place_an_order_using_payment_method_Pay_With_Card_using_valid_and(String stripeCardNumber, String stripeCardExpiry, String stripeCardCvc) throws InterruptedException {
            String stripePayCardNumber = Props.getProp(stripeCardNumber);
            String stripePayCardExpiry = Props.getProp(stripeCardExpiry);
            String stripePayCardCvc = Props.getProp(stripeCardCvc);

            checkoutPage.payWithCardRadioBtn(stripePayCardNumber, stripePayCardExpiry, stripePayCardCvc);

            checkoutPage.competitionsAndNewCheckbox();
            checkoutPage.offersCheckbox();
            checkoutPage.cleaningTipsArticlesCheckbox();

            checkoutPage.termsAndConditionsCheckBox();

            checkoutPage.placeOrderSecurelyButton();
            Thread.sleep(15000);
            System.out.println(webDriver.getTitle());

        }

     */
    @Then("^I place an order using payment method Pay With Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_payment_method_Pay_With_Card_using_valid_and(String stripeCardNumber, String stripeCardExpiry, String stripeCardCvc) throws InterruptedException {
        String stripePayCardNumber = Props.getProp(stripeCardNumber);
        String stripePayCardExpiry = Props.getProp(stripeCardExpiry);
        String stripePayCardCvc = Props.getProp(stripeCardCvc);

        checkoutPage.payWithCardRadioBtn(stripePayCardNumber, stripePayCardExpiry, stripePayCardCvc);

        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();
        checkoutPage.termsAndConditionsCheckBox();

        checkoutPage.placeOrderSecurelyButton();
        Thread.sleep(15000);
        System.out.println(webDriver.getTitle());

    }

    @When("^I place an order using payment method Pay With Card using \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")

    public void payment_via_3D_secure_card(String stripeCardNumber, String stripeCardExpiry, String stripeCardCvc) throws InterruptedException {
        String stripePayCardNumber = Props.getProp(stripeCardNumber);
        String stripePayCardExpiry = Props.getProp(stripeCardExpiry);
        String stripePayCardCvc = Props.getProp(stripeCardCvc);

        checkoutPage.payWithCardRadioBtn(stripePayCardNumber, stripePayCardExpiry, stripePayCardCvc);

        //Thread.sleep(15000);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButton();
        checkoutPage.entersecureCodeandClickSubmit();
        Thread.sleep(15000);
        System.out.println(webDriver.getTitle());
    }

    @Then("^I place an order by brain tree instalment With Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_by_paying_With_Card_using_valid_Details(String cardNumber, String cardExpiry, String cardCvv, String BraintreePostalCode) throws InterruptedException {

        String brainTreePayCardNumber = Props.getProp(cardNumber);
        String brainTreePayCardExpiry = Props.getProp(cardExpiry);
        String brainTreePayCardCvv = Props.getProp(cardCvv);
        String brainTreePostalCode = Props.getProp(BraintreePostalCode);

//		checkoutPage.payWithCardRadioBtn(stripePayCardNumber, stripePayCardExpiry, stripePayCardCvc);
        checkoutPage.payWithBTCardRadioBtn(brainTreePayCardNumber, brainTreePayCardExpiry, brainTreePayCardCvv, brainTreePostalCode);
//		Assert.assertTrue(checkoutPage.enterCardNumber(brainTreePayCardNumber));
//		Assert.assertTrue(checkoutPage.enterCardExpiry(brainTreePayCardExpiry));
//		Assert.assertTrue(checkoutPage.enterCvv(brainTreePayCardCvv));
        //Assert.assertTrue(checkoutPage.clickPurchaseButton());
//		Thread.sleep(5000);
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();

        checkoutPage.termsAndConditionsCheckBox();

        checkoutPage.placeOrderSecurelyButton();
        //.sleep(15000);
        System.out.println(webDriver.getTitle());

    }

    @Then("^I place an order by stripe instalment With Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_by_stripe_instalment_With_Card_using_valid_Details(String cardNumber, String cardExpiry, String cardCvv) throws InterruptedException {
        String stripePayCardNumber = Props.getProp(cardNumber);
        String stripePayCardExpiry = Props.getProp(cardExpiry);
        String stripePayCardCvv = Props.getProp(cardCvv);
        Assert.assertTrue(checkoutPage.enterStripeCardNumber(stripePayCardNumber));
        Assert.assertTrue(checkoutPage.enterStripeCardExpiry(stripePayCardExpiry));
        Assert.assertTrue(checkoutPage.enterStripeCvc(stripePayCardCvv));
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButton();
        System.out.println(webDriver.getTitle());
    }

    //Braintree-Rita Singh
    @Then("^I place an order using Braintree payment method Pay With Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_Braintree_payment_method_Pay_With_Card_using_valid_and(String BraintreeCardNumber, String BraintreeCardExpiry, String BraintreeCardCvc, String BraintreePostalCode) throws InterruptedException {
        String BTPayCardNumber = Props.getProp(BraintreeCardNumber);
        String BTPayCardExpiry = Props.getProp(BraintreeCardExpiry);
        String BTPayCardCvc = Props.getProp(BraintreeCardCvc);
        String BTPostalCode = Props.getProp(BraintreePostalCode);
        checkoutPage.payWithBTCardRadioBtn(BTPayCardNumber, BTPayCardExpiry, BTPayCardCvc, BTPostalCode);
        checkoutPage.competitionsAndNewCheckbox();
        // checkoutPage.offersCheckbox();
        // checkoutPage.cleaningTipsArticlesCheckbox();
        checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.placeOrderSecurelyButton();
        System.out.println(webDriver.getTitle());
    }

    @When("^I select pay by card radio button$")
    public void selectRadioButtonPayByCard() {
        Assert.assertTrue(checkoutPage.payBycardBraintree(), "Unable to click Pay By Card Radio button on checkout page");
    }

    @When("^I select Klarna radio button$")
    public void selectRadioButtonKlarna() throws InterruptedException {
        Assert.assertTrue(checkoutPage.payByKlarna(), "Unable to click on Klarna Radio button on checkout page");
        //checkoutPage.payByKlarna();
    }

    @When("^I select Klarna radio button for DE$")
    public void selectRadioButtonKlarnaDE() throws InterruptedException {
        Assert.assertTrue(checkoutPage.payByKlarnaForDE(), "Unable to click on Klarna Radio button on checkout page");
        //checkoutPage.payByKlarna();
    }

    @When("^I select Pay Now Radio Button$")
    public void selectPayNowRadioButton() {
        Assert.assertTrue(checkoutPage.payIn3FinancingButtonKlarna(), "Unable to click on pay now Radio button on checkout page");
    }


    @Then("^I place an order using Braintree payment method Pay With Card using invalid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_Braintree_payment_method_Pay_With_Card_using_invalid_and(String BraintreeCardNumber, String BraintreeCardExpiry, String BraintreeCardCvc, String BraintreePostalCode) throws InterruptedException {
        String BTPayCardNumber = Props.getProp(BraintreeCardNumber);
        String BTPayCardExpiry = Props.getProp(BraintreeCardExpiry);
        String BTPayCardCvc = Props.getProp(BraintreeCardCvc);
        String BTPostalCode = Props.getProp(BraintreePostalCode);
        checkoutPage.payWithBTCardRadioBtn(BTPayCardNumber, BTPayCardExpiry, BTPayCardCvc, BTPostalCode);

    }

    @Then("^I place an order using Braintree payment method Pay With Giropay$")
    public void i_place_an_order_using_Braintree_payment_method_Pay_With_Giropay() throws InterruptedException {

        checkoutPage.payWithBTGiropayRadioBtn();

        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.cleaningTipsArticlesCheckbox();

        checkoutPage.termsAndConditionsCheckBox();
        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();
        Thread.sleep(15000);
        System.out.println(webDriver.getTitle());
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        for (String handle : allWindowHandles) {
            webDriver.switchTo().window(handle); //Switch to the desired window first and then execute commands using driver
        }
        checkoutPage.clickSuccessButton();
        webDriver.switchTo().window(parentWindowHandler);
    }

    @Then("^I place an order using Braintree payment method Pay With 3D secure Card using valid \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_place_an_order_using_Braintree_payment_method_Pay_With_3DSecureCard_using_valid_and(String BraintreeCardNumber, String BraintreeCardExpiry, String BraintreeCardCvc, String BraintreePostalCode) throws InterruptedException {
        String BTPayCardNumber = Props.getProp(BraintreeCardNumber);
        String BTPayCardExpiry = Props.getProp(BraintreeCardExpiry);
        String BTPayCardCvc = Props.getProp(BraintreeCardCvc);
        String BTPostalCode = Props.getProp(BraintreePostalCode);
        checkoutPage.payWithBTCardRadioBtn(BTPayCardNumber, BTPayCardExpiry, BTPayCardCvc, BTPostalCode);

        checkoutPage.competitionsAndNewCheckbox();
        // checkoutPage.offersCheckbox();
        // checkoutPage.cleaningTipsArticlesCheckbox();

        checkoutPage.termsAndConditionsCheckBox();

        checkoutPage.placeOrderSecurelyButton();
        Thread.sleep(15000);
        checkoutPage.entersecureCodeandClickSubmit();
        System.out.println(webDriver.getTitle());

    }

    @Then("^verify error message for invalid card details$")
    public void verify_error_for_invalid_card_details() {
        Assert.assertTrue(checkoutPage.VerifyCardIsInvalid());
    }

    @When("^I click on place order securely button$")
    public void I_click_on_place_order_securely_button() {
        Assert.assertTrue(checkoutPage.placeOrderSecurelyButton(), "Unable to click on Place order Button");
    }

    @When("^I click on pay by card braintree method$")
    public void I_click_on_pay_by_card_braintree_method() {
        Assert.assertTrue(checkoutPage.payBycardBraintree(), "Unable to select radio button pay by card ");
    }

    @Then("^I should get error message against all mandatory fields$")
    public void I_should_get_error_message_against_all_mandatory_fields() {
        IsPageLoaded.waitAllRequest();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(checkoutPage.getErrorMessageForFirstName(), Props.getMessage("firstname.error.message"));
        softAssert.assertEquals(checkoutPage.getErrorMessageForLastName(), Props.getMessage("lastname.error.message"));
        String actualPostalErrorMessage = checkoutPage.getErrorMessageForPostalCode();
        boolean flag = false;
        if (actualPostalErrorMessage.equalsIgnoreCase(Props.getMessage("postalcode.error.message"))) {
            flag = true;
        } else if (actualPostalErrorMessage.equalsIgnoreCase(Props.getMessage("postalcode.error.message1"))) {
            flag = true;
        }
        softAssert.assertTrue(flag, "Actual Message-->>" + actualPostalErrorMessage + ",Expected-->>Either \"" + Props.getMessage("postalcode.error.message") + "\" OR \"" + Props.getMessage("postalcode.error.message1") + "\"");
        softAssert.assertEquals(checkoutPage.getErrorMessageForTermsAndConditions(), Props.getMessage("termsAndCondition.error.message"));
        softAssert.assertAll();
    }

    @Then("^I click on termsAndCondition checkbox$")
    public void checktermsAndCondition() {
        checkoutPage.termsAndConditionsCheckBox();
    }

    @Then("^I should get insert correct credit card data error message$")
    public void verify_insert_correct_credit_data_message() {
        Assert.assertEquals(checkoutPage.verifyInsertCorrectCreditDataMessage(), Props.getMessage("insert.correct.card.data"));
    }

    @Then("^I should get valid error message$")
    public void verify_error_message_for_invalid_paypal_credentials() {
        Assert.assertTrue(checkoutPage.VerifyerrorMessageforInvalidPaypalCredentials());
    }

    @Then("^I should get card expiration is in past error message$")
    public void verify_card_expiration_invalid_error_message() {
        Assert.assertTrue(checkoutPage.VerifyCardExpirationMessage());
    }

    @Then("^I place an order using payment method Braintree$")
    public void I_place_an_order_using_payment_method_Braintree() throws InterruptedException {
//		String stripePayCardNumber = Props.getProp(stripeCardNumber);
//		String stripePayCardExpiry = Props.getProp(stripeCardExpiry);
//		String stripePayCardCvc = Props.getProp(stripeCardCvc);

//		checkoutPage.payWithCardRadioBtn(stripePayCardNumber, stripePayCardExpiry, stripePayCardCvc);

//		checkoutPage.competitionsAndNewCheckbox();
//		checkoutPage.offersCheckbox();
//		checkoutPage.cleaningTipsArticlesCheckbox();
//
//		checkoutPage.termsAndConditionsCheckBox();
//
//		checkoutPage.placeOrderSecurelyButton();
//		Thread.sleep(15000);
//		System.out.println(webDriver.getTitle());
        checkoutPage.brainTreeRadioBtn();
    }

    //Modified by Rita Singh

    @Then("^verify that order is placed successfully$")
    public void verify_that_order_is_placed_successfully() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        String actualString = checkoutPage.orderReceivedText();
        String expectedString = Props.getMessage("order.received.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
    }


    @Then("^I select Braintree Installment payment method$")
    public void I_place_an_order_using_payment_method_Braintree_Installment() throws InterruptedException {
        checkoutPage.brainTreeInstallmentRadioBtn();
    }


    @Then("^I select stripe Installment payment method$")
    public void clickStripeInstallmentLabel() throws InterruptedException {
        Assert.assertTrue(checkoutPage.clickStripeInstallmentLabel());
    }

    //Written by Sanket
    @And("^save the order number into the properties file as \"([^\"]*)\"$")
    public void fetch_order_number_and_save_in_config_file(String key) {
        Assert.assertTrue(checkoutPage.fetchAndSaveOrderNumberIntoApiConfigFileHavingKey(key),
                "Unable to fetch and save the order number from order summary page please check logs");
    }

    @And("^verify error message for incorrect order amount$")
    public void verifyErrorMessageForIncorrectOrderAmount() {
        IsPageLoaded.waitAllRequest();
        String actualString = checkoutPage.getErrorIncorrectOrderAmount();
        String expectedString = Props.getMessage("order.invalidamount.text");
//        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
        Assert.assertEquals(actualString, expectedString);
    }

    @And("^I place an order using payment method Braintree PayPal Paylater using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPlaceAnOrderUsingPaymentMethodBraintreePayPalPaylaterUsingValidAnd(String payPalEmail, String payPalPassword) throws Throwable {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.payPalPaylaterRadioBtn();
        checkoutPage.competitionsAndNewCheckbox();
        //checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();

        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
        checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.selectPayLaterMethod();
        checkoutPage.payPalPayLaterContinueButton();
        checkoutPage.clickPayPalPayPayLaterNowButton();
        webDriver.switchTo().window(parentWindowHandler);
    }

    @And("^I place an order using payment method Braintree PayPal Credit using valid \"([^\"]*)\" and \"([^\"]*)\" for sharkNinja UK$")
    public void iPlaceAnOrderUsingPaymentMethodBraintreePayPalCreditUsingValidAndForUK(String payPalEmail, String payPalPassword) throws Throwable {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.payPalCreditRadioBtn();
        checkoutPage.competitionsAndNewCheckbox();
        //checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();

        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        //  checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();
        IsPageLoaded.waitAllRequest();
        checkoutPage.clickPaypalCreditPaymentOption1();

        checkoutPage.clickOnContinueButton();
        checkoutPage.clickPaypalCreditEmiOption1();
        checkoutPage.clickChooseAndContinueButton();
        // checkoutPage.clickSubmitButton();
        //checkoutPage.clickSubmitButt.on();

//        checkoutPage.clickOnAcceptCookiesBtn();
//        checkoutPage.clickPayPalPayCreditButton();
        checkoutPage.clickOnContinueButton();
        webDriver.switchTo().window(parentWindowHandler);

    }

    @And("^I place an order using payment method Braintree PayPal Credit using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPlaceAnOrderUsingPaymentMethodBraintreePayPalCreditUsingValidAnd(String payPalEmail, String payPalPassword) throws Throwable {
        String payPalEmailInput = Props.getProp(payPalEmail);
        String payPalPasswordInput = Props.getProp(payPalPassword);
        checkoutPage.payPalCreditRadioBtn();
        checkoutPage.competitionsAndNewCheckbox();
        checkoutPage.offersCheckbox();
        checkoutPage.termsAndConditionsCheckBox();

        String parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.placeOrderSecurelyButton();

        Set<String> allWindowHandles = webDriver.getWindowHandles();

        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
        checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.payPalNextButton();
        checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
        checkoutPage.payPalLoginButton();

//        IsPageLoaded.waitAllRequest();
//        checkoutPage.clickOnAcceptCookiesBtn();
//        IsPageLoaded.waitAllRequest();
//        checkoutPage.clickPaypalCreditPaymentOption1();
//        checkoutPage.clickChooseAndContinueButton();
//        checkoutPage.clickOnContinueButton();
//        checkoutPage.clickSubmitButton();
//        checkoutPage.clickSubmitButton();
//        webDriver.switchTo().window(parentWindowHandler);

        checkoutPage.clickOnAcceptCookiesBtn();
        checkoutPage.clickPayAnotherWay();
        checkoutPage.selectPayPalCreditMethod();
        checkoutPage.payPalPayLaterContinueButton();
        checkoutPage.clickPayPalPayPayLaterNowButton();
        checkoutPage.clickPayPalPayCreditButton();
        checkoutPage.clickOnContinueButton();
        //checkoutPage.clickChooseAndContinueButton();
        checkoutPage.clickSubmitButton();
        checkoutPage.clickSubmitButton();
        webDriver.switchTo().window(parentWindowHandler);
    }

    @And("^place order securely button should be disabled$")
    public void placeOrderSecurelyButtonShouldBeDisabled() {
        boolean flag = checkoutPage.verifyPlaceOrderButtonisEnabled();
        Assert.assertFalse(flag, "Place Order button is not disabled");
    }

    @Given("^I place bulk order using installment$")
    public void iPlaceBulkOrderUsingInstallment() throws InterruptedException {
        for (int i = 1; i < 100; i++) {
            Assert.assertTrue(msHomepage.selectTile());
            Assert.assertTrue(msHomepage.addProductToCart());
            if (msCartOverLayPage.isElementPresent(msCartOverLayPage.checkIsCartOverLaypagePresent())) {
                if (msCartOverLayPage.checkIsProceedLinkPresent()) {
                    Assert.assertTrue(msCartOverLayPage.clickProceed());
                }
                if (msCartOverLayPage.checkIsCheckoutButtonPresent()) {
                    Assert.assertTrue(msCartOverLayPage.goToCheckout());
                }
            } else {
                Assert.assertEquals(mscartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
            }
            checkoutPage.fillInBillingDetails();
            Assert.assertTrue(checkoutPage.validateShippingAddessSameAsBilling(), "there is an assertion error.");
            checkoutPage.brainTreeInstallmentRadioBtn();
            String brainTreePayCardNumber = Props.getProp("brainTree.card.number");
            String brainTreePayCardExpiry = Props.getProp("brainTree.card.expiryDate");
            String brainTreePayCardCvv = Props.getProp("brainTree.card.cvcNumber");
            String brainTreePostalCode = Props.getProp("brainTree.card.postalCode");
            checkoutPage.payWithBTCardRadioBtn(brainTreePayCardNumber, brainTreePayCardExpiry, brainTreePayCardCvv, brainTreePostalCode);
            checkoutPage.competitionsAndNewCheckbox();
            checkoutPage.offersCheckbox();
            checkoutPage.cleaningTipsArticlesCheckbox();

            checkoutPage.termsAndConditionsCheckBox();

            checkoutPage.placeOrderSecurelyButton();
            //.sleep(15000);
            System.out.println(webDriver.getTitle());

            IsPageLoaded.waitAllRequest();
            String actualString = checkoutPage.orderReceivedText();
            String expectedString = Props.getMessage("order.received.text");
            Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
            getWebDriver().navigate().to(Props.getProp("site.uat.url"));
            System.out.println("Creating order:" + i);
        }

    }

    @And("^verify that order is placed successfully \"([^\"]*)\"$")
    public void verifyThatOrderIsPlacedSuccessfully(String orderCount) throws Throwable {
        IsPageLoaded.waitAllRequest();
        String actualString = checkoutPage.orderReceivedText();
        String expectedString = Props.getMessage("order.received.text");
        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
        LOG.info("Order Created:" + orderCount);
    }

    @Then("^The user is able to pay using following \"([^\"]*)\"$")
    public void The_User_is_able_to_pay_using_following(String paymentMethod) throws Throwable {
        IsPageLoaded.waitAllRequest();
        switch (paymentMethod) {
            case "Pay by Card":
                checkoutPage.payViaPayByCardMethod();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButton();
                checkoutPage.chooseFasterCheckoutButton();
                webDriver.switchTo().window(parentWindowHandler);
                break;

            case "pay in 30 Days":
                checkoutPage.payVia30DaysMethod();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButtonFor30Days();
                checkoutPage.chooseFasterCheckoutButton();
                webDriver.switchTo().window(parentWindowHandler);
                break;
            default:
                LOG.info("All Payment Methods have covered");
        }
    }


    @Then("^verify login error message$")
    public void verifyLoginErrorMessage() {
        String actualResult = checkoutPage.getErrorMessageForIncorrectPasswordLogin();
        String expectedResult = Props.getProp("login.incorrectpassword.message");
        Assert.assertTrue(actualResult.contains(expectedResult), "Actual Result=" + actualResult + " expectedResult=" + expectedResult);
    }

    @When("^I click on klarna express checkout button$")
    public void iClickOnKlarnaExpressCheckoutButton() {
        checkoutPage.clickOnExpressCheckout();

    }

    @Then("^I place an order using payment method Klarna express checkout for UK using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPlaceAnOrderUsingPaymentMethodKlarnaExpressCheckoutForUKUsingValidAnd(String ContactNumber, String klarnaOTPNumber)  {

        parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.clickOnExpressCheckout();
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on Klarna Express ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.klarnaExpressContinueButton();
        checkoutPage.enterKlarnaPhoneNo(Props.getProp(ContactNumber));
        checkoutPage.klarnaExpressContinueButtonAgain();
        checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));
        allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on Klarna Express ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.payNowKlarnaSelectMethod();
    }

    @Then("^The user is able to select following \"([^\"]*)\"$")
    public void theUserIsAbleToSelectFollowing(String paymentMethod) throws Throwable {
        IsPageLoaded.waitAllRequest();
        switch (paymentMethod) {
            case "Pay now":
                checkoutPage.payViaPayByCardMethod();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButton();
                checkoutPage.chooseFasterCheckoutButton();
                checkoutPage.disappearProcessingElement();
                webDriver.switchTo().window(parentWindowHandler);
                break;

            case "pay in 30 Days":
                checkoutPage.payVia30DaysMethod();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButton();
                checkoutPage.chooseFasterCheckoutButton();
                checkoutPage.disappearProcessingElement();
                webDriver.switchTo().window(parentWindowHandler);
                break;

            case "Pay in 3 or Financing":
                checkoutPage.payViaFianncing();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButtonFor30Days();
                checkoutPage.chooseFasterCheckoutButton();
                checkoutPage.disappearProcessingElement();
                webDriver.switchTo().window(parentWindowHandler);
                break;
            default:
                LOG.info("All Payment Methods have covered");
        }
    }

    @Then("^I select newsletter and term and condition checkbox$")
    public void iSelectNewsletterAndTermAndConditionCheckbox() {
       // checkoutPage.termsAndConditionsCheckBox();
        checkoutPage.termsAndConditionsCheckBox();
    }


    @Then("^I click on securely place order$")
    public void iClickOnSecurelyPlaceOrder() {
        checkoutPage.placeOrderSecurelyButton2();
    }

    @Then("^I place an order using payment method Klarna express checkout for DE using valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPlaceAnOrderUsingPaymentMethodKlarnaExpressCheckoutForDEUsingValidAnd(String ContactNumber, String klarnaOTPNumber) throws Throwable {
        parentWindowHandler = webDriver.getWindowHandle();
        checkoutPage.clickOnExpressCheckout();
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on Klarna Express ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }
        checkoutPage.klarnaExpressContinueButton();
        checkoutPage.enterKlarnaPhoneNo(Props.getProp(ContactNumber));
        checkoutPage.klarnaExpressContinueButtonAgain();
        checkoutPage.enterKlarnaOTPNumber(Props.getProp(klarnaOTPNumber));

        allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on Klarna Express ");
        }

        for (String handle : allWindowHandles) {
            if (!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if (!UrlBuilder.isMobile()) {
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

            checkoutPage.klarnaContinueButtonZehle();

    }

    @Then("^The user is able to select following \"([^\"]*)\" for FR$")
    public void theUserIsAbleToSelectFollowingForFR(String paymentMethod) throws Throwable {
        IsPageLoaded.waitAllRequest();
        switch (paymentMethod) {
            case "Pay now":
                checkoutPage.payViaPayByCardMethod();
                checkoutPage.klarnaContinueButton2();
                checkoutPage.payWithKlarnaButton();
                checkoutPage.chooseFasterCheckoutButton();
                Thread.sleep(6000);
                webDriver.switchTo().window(parentWindowHandler);
                break;

            case "pay in 30 Days":
                checkoutPage.payVia30DaysMethod();
                checkoutPage.klarnaContinueButton2();
//                checkoutPage.termsAndConditionsCheckBoxForKlarnaFR();
//                checkoutPage.payWithKlarnaButtonFor30Days();
                checkoutPage.payWithKlarnaButton();
                checkoutPage.chooseFasterCheckoutButton();
                Thread.sleep(6000);
                webDriver.switchTo().window(parentWindowHandler);
                break;

            case "Pay in 3 or Financing":
                checkoutPage.payViaFianncing();
                checkoutPage.klarnaContinueButton2();
//                checkoutPage.termsAndConditionsCheckBoxForKlarnaFR();
                checkoutPage.payWithKlarnaButtonFor30Days();
                checkoutPage.chooseFasterCheckoutButton();
                Thread.sleep(6000);
                webDriver.switchTo().window(parentWindowHandler);
                break;
            default:
                LOG.info("All Payment Methods have covered");
        }
    }

    @And("^I click on pay by card button$")
    public void iClickOnPayByCardButton() {
        checkoutPage.clickOnPayByCard();
    }
}

