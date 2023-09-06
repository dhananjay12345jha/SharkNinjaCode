package com.salmon.test.page_objects.gui;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.CartPage.removeCommaAndDot;
import static org.testng.Assert.assertFalse;

public class CheckoutPage extends PageObject {

    private static final By phoneNumberBillingDesktop = By.cssSelector("input[name='billing_PhoneHome']");
    private static final By phoneNumberShippingDesktop = By.cssSelector("input[name='shipping_PhoneHome']");
    private final By invalidEmailerrormsg = By.xpath("//*[@id=\"collapse-login\"]/div");
    private static final By acceptCookiesBtn = By.cssSelector("#acceptAllButton");
    private static final By payPalContinueButtonUKMobile = By.xpath("//div[@id='button']/button");
    private static final By payPalPayNowBtnMobile = By.xpath("//input[@id='confirmButtonTop']");
    private static final By payPalPayLaterPayNowBtnMobile = By.cssSelector("button#payment-submit-btn");
    //private static final By payPalContinueButton = By.xpath("//button[@id=\"submitButton\"]");
    private static final By payPalContinueButton = By.xpath("//button[@id=\"payment-submit-btn\"]");
    private static final By payPalContinueButtonForDE = By.xpath("//button[@id=\"payment-submit-btn\"]");

    private static final By ClickToContinueButtonBlackScreen = By.xpath("//*[@class=\"paypal-checkout-continue\"]");
    private static final By blackContinueButton = By.xpath("//*[@id=\"paypal-overlay-uid_17cf015753_mdk6mzy6nda\"]/div[1]/div[3]/a");

    private static final By klarnaContinueButton = By.xpath("//*[@id=\"onContinue\"]/div/div[1]");

    private static final By klarnaContinueButton2 = By.xpath("//button[@data-testid='select-payment-category']/div/div[1]");
    private static final By klarnaContinueButtonBestatigen = By.xpath("//*[@id=\"invoice_kp-purchase-review-continue-button\"]/div/div[1]");
    private static final By klarnaContinueButtonZehle = By.xpath("//*[@id=\"dd-confirmation-dialog__footer-button-wrapper\"]/div/button/div/div[1]");

    private static final By klarnaContinueButton3 = By.xpath("//button[@data-testid='pick-plan']/div/div[1]");
    //private static final By KlarnaDEEinkauf = By.xpath("//*[@id=\"fixedsumcredit_kp%%7b172679-21b0-42ad-a73a-c84da75c5448_6_1890_fixed_sum_credit-purchase-review-continue-button\"]/div/div[1]");

    private static final By KlarnaDEEinkauf = By.xpath("(//button[contains(@id,'fixedsumcredit_kp')])[2]");

    private static final By FrameKlarnaPaymentDE = By.xpath("//iframe[@id=\"klarna-payments-main\"]");
    private static final By FrameKlarnaEnterPhoneNoForDE = By.xpath("//iframe[@id=\"klarna-payments-fullscreen\"]");
    private static final By payWithKButton = By.xpath("//button[@data-testid='confirm-and-pay']/div/div[1]");

    private static final By payWithKButtonFOr30Days = By.xpath("//*[@data-testid=\"confirm-and-pay\"]");

    private static final By chooseFasterCheckoutButton = By.xpath("//button[@data-testid='SmoothCheckoutPopUp:enable']");

    private static final By klarnaOTP = By.xpath("//*[@id=\"otp_field\"]");

    private static final By paypalCreditButton = By.xpath("//label[@for=\"BC-QV5LMHMPJ4LZ4-funding-option\"]");

    private static final By PaypalCreditPaymentOption1 = By.xpath("//span[text()='PayPal Credit']");
    private static final By PaypalCreditEmiOption1 = By.xpath("//label[@for='control_N5ZWEFEH3V']");
    ////button[@id="payment-submit-btn"]
    private static final By chooseAndContinueButton = By.xpath("//button[@data-automation-id=\"continue\"]");
    private static final By payAnotherWay = By.xpath("//span[text()=\"Pay another way\"]");
    private static final By paypalSubmitButton = By.xpath("//input[@type=\"submit\"]");
    //-----Total Price, VAT, Shipping Charges------///
    private static final By totalPrice = By.xpath("//dd[@class='total-price']");
    private static final By shippingCost = By.xpath("//dd[@class='shipping-amount']");
    private static final By vat = By.xpath("//dd[2]");
    private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);
    private static final String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");
    private static final By returningCustomerLoginHereLink = By.xpath("//div[@class=\"col-xs-12 col-md-6 sharkninja-login\"]//a");

    //private static final By returningCustomerLoginHereLink = By.xpath(".col-xs-12.col-md-6.sharkninja-login a");

    private static final By returningCustomerLoginUserForm = By.name("LoginUserForm");
    private static final By stripeInstallmentLabel = By.xpath("//li[@class='panel payment-id-stripeinstallment payment-id-class']/div[@class='row']/div/label");
    //Login Email and Password Form for Returning Users
    private static final By returningCustomerEmailLogin = By.cssSelector("#ShopLoginForm_Login");
    private static final By returningCustomerPasswordLogin = By.cssSelector("#ShopLoginForm_Password");
    private static final By returningCustomerSignInButton = By.xpath("//button[contains(text(),'Sign In')]");
    private static final By brainTreeInstallmentRadioBtn = By.xpath("//input[@data-testing-id='payment-BraintreeInstallment-element']");
    private static final By payWithGiropayRadioBtn = By.xpath("//div[@class=\"col-xs-9 col-sm-9 radio \"]//label[contains(text(),\"Pay by Giropay\")]");
    //Post Successful Login - Logout link
    private static final By returningCustomerLogoutLink = By.xpath("//div[@class=\"col-xs-12 col-md-6\"]/p/a");
    //Pay with card(Braintree) element locator
    private static final By iFrameCardNumber = By.xpath("//iframe[@id='braintree-hosted-field-number']");
    private static final By getiFrameCardNumberTxtBox = By.xpath("//input[@id='credit-card-number']");
    private static final By iFrameExpirationDate = By.xpath("//iframe[@id='braintree-hosted-field-expirationDate']");
    private static final By getiFrameExpirationDateTxtBox = By.xpath("//input[@id='expiration']");
    private static final By iFrameCvv = By.xpath("//iframe[@id='braintree-hosted-field-cvv']");
    private static final By iFramePostalCode = By.id("braintree-hosted-field-postalCode");
    private static final By getiFramePostalCode = By.id("postal-code");
    private static final By getiFrameCvvTxtBox = By.xpath("//input[@id='cvv']");
    private static final By purchaseBtn = By.xpath("//button[@id='submit-button']");
    //Billing Details
    private static final By billingFirstNameText = By.xpath("//input[@id='billing_FirstName']");

    private static final By KlarnaPhoneNo = By.xpath("//*[@id=\"phone\"]");

    private static final By paypalExpressCheckoutFromCart = By.xpath("//div[@data-funding-source='paypal']");
    private static final By billingLastNameText = By.xpath("//input[@id='billing_LastName']");
    private static final By billingEmailText = By.xpath("//input[@id='email_Email']");
    private static final By billingYourPostCodeSearchBox = By.xpath("//input[@id='billing-search-bar']");
    private static final By billingPostalcode = By.xpath("//*[@id=\"billing_PostalCode\"]");
    private static final By shippingPostalCode = By.xpath("//*[@id=\"shipping_PostalCode\"]");
    private static final By shippingYourPostCodeSearchBox = By.xpath("//*[@id=\"shipping-search-bar\"]");
    //Shiiping Details
    //private static final By differentShippingAddressBtn = By.xpath("//label[contains(text(),'Different shipping address?')]");
    private static final By differentShippingAddressBtn = By.xpath("//label[@for='shipOption1Default']");
    private static final By shippingFirstNameText = By.xpath("//input[@id='shipping_FirstName']");
    private static final By shippingLastNameText = By.xpath("//*[@id=\"shipping_LastName\"]");
    //Create Account
    private static final By createAccountCheckbox = By.xpath("//label[@for=\"create-account-checkbox\"]");
    private static final By createAccountPasswordText = By.xpath("//input[@id='create-account-password']");
    //Payment Options
//	private static final By payWithCardRadioBtn = By.xpath("//li[@class='panel payment-id-stripe payment-id-class']//input[@type='radio']");
//	private static final By payWithCardRadioBtn = By.xpath("//label[contains(text(),'Pay with card')]/preceding-sibling::input");
    private static final By payWithCardRadioBtnBT = By.xpath("//input[@data-testing-id='payment-Braintree-element']");
    //private static final By payWithCardRadioBtnBT = By.xpath("//label[@for='paymentOption_FiAKAQMmajYAAAF4RT0aKaq0']");
    private static final By placeOrderSecurelyButtonExpress = By.xpath("//button[@id='btn-send-Order']");

    //PayWithCard elements locator
    //	private static final By payPalRadioBtn = By.xpath("//label[contains(text(),'PayPal')]");
    //private static final By payPalRadioBtn = By.xpath("//*[@class=\"col-xs-9 col-sm-9 radio col-xs-7\"]/label");

    private static final By klarnaRadioBtn = By.xpath("//input[@data-testing-id='payment-Pay with Klarna-element']");
    //private static final By klarnaRadioBtnDE = By.xpath("//*[@class=\"sharkninja-payment\"]//*[@data-testing-id='payment-Klarna-element']/../label");
    private static final By klarnaRadioBtnDE = By.xpath("//*[@data-testing-id='payment-Klarna-element']/../label");
    private static final By payIn3OrFinancing = By.xpath("//div[@id='pay_over_time-pay_over_time__container']");
    private static final By RatenzahlungRadioButtonKlarna = By.xpath("//*[@id=\"radio-pay_over_time__label\"]");
    private static final By PayIn30DaysRadioButtonKlarna = By.xpath("//*[@id=\"radio-pay_later__label\"]");
    //private static final By payIn3OrFinancing = By.xpath("//*[@id=\"pay_over_time-pay_over_time__label\"]");

    private static final By PayNowRadioButtonKlarna = By.xpath("//*[@id=\"radio-pay_now__label\"]");

    private static final By LatschriftRadioButtonKlarna = By.xpath("//input[contains(@id,'directdebit_kp')]");

    private static final By payNowKlarnaSelectionButton = By.xpath("//*[@id=\"root\"]/..//div[6]/div/div/button/div[3]/p");
    private static final By payNowKlarnaButton = By.xpath("//*[@value='pay_now']");

    private static final By payIn30DaysKlarnaButton = By.xpath("//*[@value='pay_later']");
    private static final By payViaFianncing = By.xpath("//*[@value='pay_over_time']");
    private static final By payPalRadioBtn = By.xpath("//label[normalize-space()='Pay by PayPal']");

    private static final By payPalRadioBtnIT = By.xpath("(//div[@class=\"col-xs-9 col-sm-9 radio \"]//label)[1]");
    private static final By payPalRadioBtnDE = By.xpath("//label[normalize-space()='PayPal']");
    private static final By payPalPayLaterRadioBtn = By.xpath("//label[normalize-space()='Pay by PayPal PayLater']");
    private static final By paypalPayCreditRadioBtn = By.xpath("//label[@for=\"paymentOption_kHUKAQMpEIoAAAF-Yr12HLnh\"]");
    private static final By payPalPayLaterMessageIframe = By.cssSelector("iframe[name*=paypal_message]");
    private static final By payPalPayLaterMessage = By.cssSelector("div.message span.br");
    private static final By brainTreeRadioBtn = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[@class='sharkninja-payment']/ul[1]/li[3]/div[1]/div[1]/label[1]");
    //Subscriptions
    private static final By competitionsAndNewCheckbox = By.xpath("//div[@class=\"information-checkboxes\"]//div[1]//label[1]");
    private static final By offersCheckbox = By.xpath("//div[@class=\"information-checkboxes\"]//div[2]//label[1]");
    private static final By cleaningTipsArticlesCheckbox = By.xpath("//div[@class=\"information-checkboxes\"]//div[3]//label[1]");
    //Place an Order
    // private static final By placeOrderSecurelyButton = By.xpath("//input[@id='termsAndConditions']/../../following-sibling::button[1]");
    private static final By placeOrderSecurelyButton = By.xpath("//form[@id='one-page-checkout-form']/button[@data-testing-id='btn-sendOrder']");
    // private static final By placeOrderSecurelyButtonBT = By.cssSelector("button[id='submit-button--braintree']");
    private static final By placeOrderSecurelyButtonBT = By.xpath("//form[@id='one-page-checkout-form']//button[@data-testing-id='btn-sendOrder']");
    private static final By BTPayCardNumberText = By.xpath("//input[@data-braintree-name=\"number\"]");
    private static final By stripepaycard = By.xpath("//div[@class=\"CardNumberField CardNumberField--ltr\"]//div[@class=\"CardNumberField-input-wrapper\"]");
    private static final By BTPayCardExpiryText = By.xpath("//input[@aria-label='Credit or debit card expiration date']");
    private static final By stripePayCardCvcText = By.xpath("//input[@aria-label='Credit or debit card CVC/CVV']");
    private static final By secure3DCardnumberText = By.xpath("//*[@id=\"credit-card-number\"]");
    //locators for Valid Postalcode:Rita Singh
    private static final By streetAddress = By.xpath("//input[@id='billing_Address1']");
    private static final By city = By.xpath("//*[@id=\"billing_City\"]");
    private static final By shippingAddressHeading = By.xpath("//*[@id=\"one-page-checkout-form\"]/div[2]/div/div[2]/h2/strong");
    private static final By strretAddressShipping = By.xpath("//*[@id=\"shipping_Address1\"]");
    private static final By CityForShipping = By.xpath("//*[@id=\"shipping_City\"]");
    //private static final By createaccountErrorText=By.xpath("//*[@id=\"collapse-login\"]/div");
    private static final By createaccountErrorTextUK = By.xpath("(//div[@class=\"alert alert-danger\"])[1]");
    private static final By createaccounterrorTextUKalternate = By.cssSelector("div.create-account-content div.alert-danger");
    private static final By weakPasswordCreatAccountErrorTextUk = By.xpath("//span[@class=\"create_account-password weak\"]");
    private static final By createaccountErrorTextDE = By.xpath("//div[contains(@class,'create-account')]//div[contains(@class,'alert')]/p");
    //PayPal elements locator
    private static final By payPalEmail = By.xpath("//input[@id='email']");

    private static final By klarnaPhoneNo = By.xpath("//input[@data-testid='kaf-field']");

    private static final By klarnaPhoneNoForDE = By.xpath("//input[@data-testid='kaf-field']");
    private static final By payPalNextButton = By.xpath("//button[@id='btnNext']");
    private static final By payPalPassword = By.xpath("//input[@id='password']");
    private static final By payPalLoginButton = By.xpath("//button[@id='btnLogin']");
    //    private static final By payPalContinueButton = By.xpath("//input[@id='confirmButtonTop']");
    private static final By payPalPayLaterSelectUK = By.cssSelector("div[data-testid=\"first-fis\"] div");
    private static final By payPalPayLaterSelectDE = By.cssSelector("section#pay-later div div div");
    private static final By payPalCreditSelectOption = By.cssSelector("li.offerHeading1");
    //    private static final By payPalContinueButton = By.xpath("//button[@id='payment-submit-btn']");
    private static final By payPalContinueButtonUK = By.cssSelector("input[type='submit']");
    private static final By payPalPayLaterIframe = By.xpath("//iframe[@data-testid='cap-iframe']");
    private static final By enterPaypalLaterPhoneNo = By.xpath("//input[@data-testid='phoneNumberInput']");
    private static final By payPalPayLaterSubmitButton = By.cssSelector("button#submitButton");
    private static final By payPalPayLaterVisaCard = By.cssSelector("div[data-testid='repaymentsFI'] img[alt='The Bank Card Platinum Rewards']");
    private static final By payPalPayLaterTermsCondition = By.cssSelector("label.ppvx_checkbox__label");
    //checkout Confirmation Page Elements
    private static final By orderReceivedText = By.xpath("//div[@class=\"cart-header\"]/h1");
    private static final By noThanksButton = By.xpath("//div[@class='_hj-2gXsT__SurveyInvitation__content']//button");
    private static final By manualBillingAddressButton = By.xpath("//*[@id=\"btn-address-billing\"]");
    private static final By DifferentShippingAddressButton = By.xpath("//*[@id=\"one-page-checkout-form\"]/div[2]/div/div[1]/label[2]");
    private static final By manualShippingAddressButton = By.xpath("//*[@id=\"btn-address-shipping\"]");
    //Checkout mandatory field error messages-Rita Singh
    private static final By incorrectPasswordMsgLogin = By.cssSelector("div[id~='collapse-login'] div[class~='alert-danger']");
    private static final By firstNameErrorMsg = By.xpath("//*[@id=\"one-page-checkout-form\"]/div[1]/div[1]/fieldset[1]/div[1]/div/small[1]");
    private static final By lastNameErrorMsg = By.xpath("//*[@id=\"one-page-checkout-form\"]/div[1]/div[1]/fieldset[1]/div[2]/div/small[1]");
    private static final By postalCodeErrorMsg = By.xpath("//*[@id=\"crafty-clicks-billing-container\"]/div[1]/div/small[@data-bv-validator='notEmpty']");
    private static final By creditCardErrorMsg = By.xpath("//*[@id=\"stripe-payment-data\"]/div[1]/small");
    private static final By termsAndConditionErrorMsg = By.xpath("//small[@data-bv-for=\"termsAndConditions\"]");
    private static final By invalidCardDetails = By.xpath("//div[@data-braintree-id=\"number-field-error\"]");
    private static final By InsertCorrectDataMessage = By.xpath("//*[@id=\"brainTree-error\"]");
    private static final By CardExpirationMessage = By.xpath("//div[@data-braintree-id=\"expiration-date-field-error\"]");
    private static final By SecurityCodeIncompleteDataMessage = By.xpath("//div[@class=\"stripepay-error alert alert-danger visible\"]");
    private static final By InvalidPaypalCredentialsMessage = By.xpath("//*[@id=\"content\"]/div[1]/p");
    private static final By stripePayCardNumberText = By.xpath("//input[@aria-label='Credit or debit card number']");
    private static final By stripePayCardExpiryText = By.xpath("//input[@aria-label='Credit or debit card expiration date']");
    //private static final By stripePayCardCvcText = By.xpath("//input[@aria-label='Credit or debit card CVC/CVV']");
    private static final By payWithCardRadioBtn = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[@class='sharkninja-payment']/ul[1]/li[1]/div[1]/div[1]/label[1]");
    //promo code--rita singh
    private static final By redeemHereLinkDesktop = By.xpath("//div[contains(@class,'order-summary')]//a[@class='js-promoentry']");
    private static final By redeemHereLinkMobile = By.xpath("//div[@class='shopping-cart-container']//a[@class='js-promoentry']");
    private static final By promoEntryTextBox = By.xpath("//input[contains(@id,'js-PromotionCode')]");
    private static final By promoApplyBtn = By.xpath("//button[@id='applyPromotion']");
    private static final By promoAppliedMessage = By.xpath("//div[@class='alert alert-success']/p");
    private static final By secure3DExpirationText = By.xpath("//*[@id='expiration']");
    private static final By secure3DCVVText = By.xpath("//*[@id=\"cvv\"]");
    private static final By purchaseButton = By.xpath("//*[@id=\"submit-button\"]");
    private static final By payWithCardRadioBtnfor3DSecure = By.xpath("//div[@class='sharkninja-payment']/ul[@class=\"list-unstyled\"]//li[starts-with(@class,'panel payment-id-stripe payment-id-class')]//label[1]");
    private static final By enterSecureCode = By.xpath("//*[@id=\"content\"]/div[2]/form[1]/input[1]");
    private static final By clickOnSubmitButton = By.xpath("//*[@id=\"content\"]/div[2]/form[1]/input[2]");
    //Order Number
    private static final By orderNumber = By.xpath("//span[@data-testing-id=\"order-document-number\"]");
    private static final By stripePayCardExpiry1 = By.xpath("//input[@name=\"exp-date\"]");
    private static final By stripePayCardCvc1 = By.xpath("//input[@name=\"cvc\"]");
    private static final By payWithAmericanCardRadioBtn = By.xpath("//div[@class='sharkninja-payment']/ul[@class=\"list-unstyled\"]//li[starts-with(@class,'panel payment-id-stripe payment-id-class')]//label[1]");
    private static final By payPalLink = By.xpath("//div[@class=\"paypal-button-label-container\"]");
    private static final By secureButtonIframe = By.xpath("//iframe[starts-with(@name,'__zoid__paypal_buttons__')]");
    private static final By SuccessButton = By.xpath("//*[@id=\"successSubmit\"]");
    private static final By errorText = By.cssSelector("div.one-page-checkout ul[class~=alert-danger] li");
    private static final By CountrySelection = By.id("CountrySelection");
    private static final By paypalExpressCheckoutIframe = By.xpath("//iframe[@id=\"jsx-iframe-588f24b20a\"]");
    private static final By paypalExpressCheckout = By.xpath("//*[@id='buttons-container']");
    //private static final By PaypalPayInThree = By.xpath("//span[text()='Pay in 3']");
    private static final By PaypalPayInThree = By.xpath("//label[@for='credit-offer-6966369624365472134']//span[@class='ppvx_radio__check-icon-container___2-11-1']");
    private static final By paypalPayInThreeContione = By.cssSelector("#submitButton");
    private static final By agreeContinue = By.cssSelector(".ppvx_checkbox__label");
    private static final By selectcardinpayinthree = By.xpath("//p[text()='The Bank Card']");
    //Terms and Conditions
    private final By termsAndConditionsCheckBox = By.xpath("//*[@class=\"checkbox terms-and-conditions col-sm-12\"]//label");

    private final By termsAndConditionsCheckBoxForPaylaterDE = By.xpath(" //label[@class='ppvx_checkbox__label']");

    private final By agreeAndContinueButtonForPaylaterDE = By.xpath(" //button[@id=\"submitButton\"]");
    private final By klarnaExpressCheckout = By.xpath("//div[@class='klarna-pay-button btn-other-country']");

    //private final By termsAndConditionsCheckBoxForKlarnaDERatenzahlung = By.xpath("//*[contains(@id,'fixedsumcredit_kp%%7b172679-21b0-42ad-a73a-c84da75c5448_6_1890_fixed_sum_credit-purchase-review-secci-toggle__box')]");
    private final By termsAndConditionsCheckBoxForKlarnaDERatenzahlung = By.xpath("//*[contains(@id,'fixedsumcredit')]/..//div[7]/div/label/div[2]");
    private final By klarnaExpressCheckoutButton = By.xpath("//button[@id='start-button']");
    private final By klarnaExpressCheckoutButtonAgain = By.xpath("//button[@id='onContinue']");
    private final By placeOrderSecurelyButton2 = By.xpath("//*[@id='btn-send-Order']");
    private final By termsAndConditionsCheckBoxForKlarnaFR = By.xpath("//*[contains(@type,'checkbox')]/..//label/div[2]");
    private final By clickOnPayByCard = By.xpath("(//button[@type='button']//span)[2]");
    private final By redeemHereLinkMobileDE = By.xpath("//div[contains(@class,'cart-summary')]//a[@class='js-promoentry']");
    private final By changePaypalAddress = By.xpath("//button[@data-testid='change-shipping']");
    private final By loaderElement = By.xpath("//*[@class='loader-text place-order checkout-loader']");

    //For filling Billing details on Checkout page - we are using Random method from Random Generator class in helpers.utils
    private String billingFirstNameData = random(6, ALPHABETS);
    private String billingLastNameData = random(6, ALPHABETS);
    //private String billingYourPostCodeSearchBoxData = "RG1 3BY";
    private String billingYourPostCodeSearchBoxData = Props.getProp("country.postal.code");
    private String billingYourPostCodeSearchBoxDataAUSTRIA = Props.getProp("country.postal.code.austria");
    private String shippingYourPostCodeSearchBoxData = Props.getProp("country.postal.code.shipping");
    private String shippingFirstNameData = random(6, ALPHABETS);
    private String shippingLastNameData = random(6, ALPHABETS);
    private String billingEmailAddressData = RandomGenerator.randomEmailAddress(6);
    String type = "value";
    String value = "Österreich";

    public float getShippingCost() {
        IsPageLoaded.waitAllRequest();
        String k = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCost)).getText().trim();
        if (k.equalsIgnoreCase("free") || k.equalsIgnoreCase("Frei")) {
            return 0;
        } else {
            if (k.contains("£")) {
                k = k.replace("£", "").trim();
            }
            if (k.contains("€")) {
                k = k.replace("€", "").trim();
            }
            //check whether string contains "," at multiple position
        }
        return Float.parseFloat(removeCommaAndDot(k));
    }

    public float getVATCost() {
        IsPageLoaded.waitAllRequest();
        String k = wait.until(ExpectedConditions.visibilityOfElementLocated(vat)).getText().trim();
        if (k.contains("£")) {
            k = k.replace("£", "").trim();
        }
        if (k.contains("€")) {
            k = k.replace("€", "").trim();
        }
        //check whether string contains "," at multiple position
        return Float.parseFloat(removeCommaAndDot(k));
    }

    public float getTotalCost() {
        IsPageLoaded.waitAllRequest();
        String k = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText().trim();
        if (k.contains("£")) {
            k = k.replace("£", "").trim();
        }
        if (k.contains("€")) {
            k = k.replace("€", "").trim();
        }
        //check whether string contains "," at multiple position
        return Float.parseFloat(removeCommaAndDot(k));
    }

    //Action Methods
    public boolean returningCustomerLoginHereLink() {
        IsPageLoaded.waitAllRequest();
        WebElement element;
        boolean flag = false;
        wait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(3)).ignoring(Exception.class);
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(returningCustomerLoginHereLink));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
           clickUsingJS(returningCustomerLoginHereLink);
            flag = true;
            log.info("Successfully clicked on the link Returning Customer Login Here ");
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on link Returning Customer Login Here-->>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean checkReturningCustomerLoginUserForm() {
        return waitForExpectedElement(returningCustomerLoginUserForm).isDisplayed();
    }

    public String orderReceivedText() {
        String text = "Not Found";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        try {
            if (getWebDriver().findElements(noThanksButton).size() > 0) {
                waitForExpectedElement(noThanksButton).click();
            }
            int count = 0;
            do {
                try {
                    text = wait.until(ExpectedConditions.visibilityOfElementLocated(orderReceivedText)).getText().trim();
                    log.info("Order Received Text Extracted From Checkout Page is-->" + text);
                    break;
                } catch (Exception e) {
                    log.info("Order Received text not found");
                }
                count++;
            } while (count < 5);
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(orderReceivedText)).getText().trim();
            log.info("Order Received Text Extracted From Checkout Page is-->" + text);
        } catch (Exception e) {
            log.error("Successfully placed order page is not there " + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public void returningCustomerEmailLogin(String emailLogin) {
        if (isElementPresent(returningCustomerEmailLogin)) {
            waitForExpectedElement(returningCustomerEmailLogin).clear();
            waitForExpectedElement(returningCustomerEmailLogin).sendKeys(emailLogin);
        } else {
            assertFalse(true, "Failed to locate element: [ " + returningCustomerEmailLogin + " ]");
        }


//		return waitForExpectedElement(returningCustomerEmailLogin);
    }

    public WebElement returningCustomerPasswordLogin() {
        return waitForExpectedElement(returningCustomerPasswordLogin);
    }

    public void returningCustomerSignInButton() {
        waitForExpectedElement(returningCustomerSignInButton, 5).click();
    }

    public String returningCustomerLogoutLink() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(returningCustomerLogoutLink));
            text = element.getText().trim();
            log.info("Successfully fetched text from checkout page which is--->>>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch text Logout after sign in due to--->>>" + ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public String verifyErrorMessage() {
        String text = "some error occurred please check";
        try {
            text = waitForExpectedElement(invalidEmailerrormsg).getText().trim();
            log.info("Successfully got valid email required error message ");
        } catch (Exception e) {
            log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
        }
        return text;
    }

    public void fillValidPostalCode() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(billingYourPostCodeSearchBox));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.clear();
        element.sendKeys(billingYourPostCodeSearchBoxData);
        if (Props.getProp("locale").contains("DE")||Props.getProp("locale").contains("IT")||Props.getProp("locale").contains("ES")) {
            int count = 0;
            do {
                try {
                    List<WebElement> myList = getWebDriver().findElements(By.xpath("//ul[@class='c2a_results']/li"));
                    if (myList.size() == 0) {
                        break;
                    }
                    new Actions(getWebDriver()).moveToElement(myList.get(1)).perform();
                    wait.until(ExpectedConditions.elementToBeClickable(myList.get(1))).click();
                } catch (StaleElementReferenceException e1) {
                    log.error("Stale Element is there trying one more time ");
                    getWebDriver().findElement(By.tagName("body")).sendKeys(Keys.ARROW_DOWN);
                    count++;
                } catch (Exception e) {
                    log.error("Some exception occurred while selecting pincode from list " + ExceptionUtils.getStackTrace(e));
                    e.printStackTrace();
                    count++;
                }
            } while (count < 10);
        }
            else if (Props.getProp("locale").contains("IT")) {
                int count = 0;
                do {
                    try {
                        List<WebElement> myList = getWebDriver().findElements(By.xpath("//ul[@class='c2a_results']/li"));
                        if (myList.size() == 0) {
                            break;
                        }
                        new Actions(getWebDriver()).moveToElement(myList.get(1)).perform();
                        wait.until(ExpectedConditions.elementToBeClickable(myList.get(1))).click();
                    } catch (StaleElementReferenceException e1) {
                        log.error("Stale Element is there trying one more time ");
                        getWebDriver().findElement(By.tagName("body")).sendKeys(Keys.ARROW_DOWN);
                        count++;
                    } catch (Exception e) {
                        log.error("Some exception occurred while selecting pincode from list " + ExceptionUtils.getStackTrace(e));
                        e.printStackTrace();
                        count++;
                    }
                } while (count < 10);

        } else {
            WebElement webelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='c2a_results']")));
            wait.until(ExpectedConditions.elementToBeClickable(webelement)).click();
        }
    }

    public void fillValidPostalCodeforShipping() throws InterruptedException {
        //waitForExpectedElement(shippingYourPostCodeSearchBox).sendKeys(billingYourPostCodeSearchBoxData);
        wait.until(ExpectedConditions.elementToBeClickable(shippingYourPostCodeSearchBox)).sendKeys(billingYourPostCodeSearchBoxData);
		/*//Thread.sleep(5000);
		//List <WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']"));
		List <WebElement> myList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']")));
		for (WebElement element:myList)
			if(element.getText().contains(Props.getProp("postaltext"))) {
				if(Props.getProp("locale").contains("DE"))
				{
					element.click();
					//Thread.sleep(1000);
					//element.findElement(By.xpath("li[1]")).click();
					wait.until(ExpectedConditions.visibilityOf(element.findElement(By.xpath("li[1]")))).click();
				}
				else {
					element.click();
				}*/
        if (Props.getProp("locale").contains("DE")) {
            List<WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']/li"));
            myList.get(1).click();
            Thread.sleep(1000);
//            WebElement webelement = webDriver.findElement(By.xpath("//ul[@class='c2a_results']/li[1]"));
//            webelement.click();
        }
        else if (Props.getProp("locale").contains("IT")) {
            List<WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']/li"));
            myList.get(1).click();
            Thread.sleep(1000);
        }
        else {
            WebElement webelement = webDriver.findElement(By.xpath("//ul[@class='c2a_results']"));
            webelement.click();
        }
        Thread.sleep(1000);

        //Thread.sleep(15000);
    }

    public boolean VerifyAutoPopulateAddresses() {
        Boolean flag = false;
        try {
//if(!(waitForExpectedElement(streetAddress).getAttribute("value").isEmpty()) && !(waitForExpectedElement(city).getAttribute("value").isEmpty()) )

            if (!(wait.until(ExpectedConditions.visibilityOfElementLocated(streetAddress))).getAttribute("name").isEmpty()
                    && !(wait.until(ExpectedConditions.visibilityOfElementLocated(city)).getAttribute("name").isEmpty()))

                flag = true;
        } catch (Exception e) {
            log.info("some exception occurs while validating auto population of addresses for billing");
        }
        return flag;
    }

    public boolean VerifyAutoPopulateAddressesforShipping() {
        Boolean flag = false;
        try {
            //if(!(waitForExpectedElement(strretAddressShipping).getAttribute("value").isEmpty())&& !(waitForExpectedElement(CityForShipping).getAttribute("value").isEmpty()) )
            if (!(wait.until(ExpectedConditions.visibilityOfElementLocated(strretAddressShipping)).getAttribute("value").isEmpty())
                    && !(wait.until(ExpectedConditions.visibilityOfElementLocated(CityForShipping)).getAttribute("value").isEmpty()))

                flag = true;
        } catch (Exception e) {
            log.info("some exception occurs while validating auto population of addresses for shipping");
        }
        return flag;
    }

    public String verifyShippingAddressForm() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingAddressHeading)).getText().trim();
            log.info("Successfully fetched text for the shipping address heading--->>" + text);
        } catch (Exception e) {
            log.error("Some exception occurs while shipping addresses form is displayed-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public void ClickEnterAddressManually() {
        try {
            //waitForExpectedElement(manualBillingAddressButton).click();
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(manualBillingAddressButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            log.info("some exception occurs while clicking on enter address manually button");
        }
    }

    public void ClickEnterAddressManuallyForShipping() {
        try {
            //waitForExpectedElement(manualShippingAddressButton).click();
            wait.until(ExpectedConditions.elementToBeClickable(manualShippingAddressButton)).click();
        } catch (Exception e) {
            log.info("some exception occurs while clicking on enter address manually button for shippping");
        }
    }

    public void ClickonDifferentShippingAddressButton() {
        try {
            //waitForExpectedElement(DifferentShippingAddressButton).click();
            wait.until(ExpectedConditions.elementToBeClickable(DifferentShippingAddressButton)).click();

        } catch (Exception e) {
            log.info("some exception occurs while clicking on different shipping address button");
        }
    }

    public boolean fillBillingAddressManually() {
        boolean flag = true;
        Actions actions;
        try {
            actions = new Actions(getWebDriver());
            WebElement element;

            element = wait.until(ExpectedConditions.presenceOfElementLocated(streetAddress));
            actions.moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(Props.getProp("streetAddress"));

            element = wait.until(ExpectedConditions.presenceOfElementLocated(city));
            actions.moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(Props.getProp("city"));

            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingPostalcode));
            actions.moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingYourPostCodeSearchBoxData);

        } catch (Exception e) {
            flag = false;
            log.info("some exception occurs while entering address manually");
        }
        return flag;
    }

    public boolean fillShippingAddressManually() {
        boolean flag = true;
        try {
//			waitForExpectedElement(strretAddressShipping).sendKeys(Props.getProp("streetAddressshipping"));
//			waitForExpectedElement(CityForShipping).sendKeys(Props.getProp("CityForShipping"));
//			waitForExpectedElement(shippingPostalCode).sendKeys(billingYourPostCodeSearchBoxData);
            wait.until(ExpectedConditions.elementToBeClickable(strretAddressShipping)).sendKeys(Props.getProp("streetAddressshipping"));
            wait.until(ExpectedConditions.elementToBeClickable(CityForShipping)).sendKeys(Props.getProp("CityForShipping"));
            wait.until(ExpectedConditions.elementToBeClickable(shippingPostalCode)).sendKeys(billingYourPostCodeSearchBoxData);

        } catch (Exception e) {
            flag = false;
            log.info("some exception occurs while entering address manually");
        }
        return flag;
    }

    public void fillvalidDetails() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(billingFirstNameText)).sendKeys(billingFirstNameData);
        wait.until(ExpectedConditions.elementToBeClickable(billingLastNameText)).sendKeys(billingLastNameData);
        wait.until(ExpectedConditions.elementToBeClickable(billingEmailText)).sendKeys(Props.getProp("valid.email"));
        wait.until(ExpectedConditions.elementToBeClickable(billingYourPostCodeSearchBox)).sendKeys(billingYourPostCodeSearchBoxData);

        if (Props.getProp("locale").contains("DE")) {
            List<WebElement> myList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']/li")));
            myList.get(1).click();
            Thread.sleep(1000);
            WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='c2a_results']/li[1]")));
            webelement.click();
            wait.until(ExpectedConditions.invisibilityOf(webelement));
        } else {
            WebElement webelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='c2a_results']/parent::div")));
            new Actions(getWebDriver()).moveToElement(webelement).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(webelement)).click();
            try {
                wait.until(ExpectedConditions.invisibilityOf(webelement));
            } catch (TimeoutException ex) {
                WebElement webelementnext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='c2a_results']/li[1]")));
                webelementnext.click();
            }
            wait.until(ExpectedConditions.invisibilityOf(webelement));
        }
    }

    public void countrySelection() {
        selectDropDownValue(CountrySelection, type, value);
    }

    public void fillInBillingDetails() throws InterruptedException {
        Actions actions = new Actions(getWebDriver());
        WebElement element;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        } catch (Exception ex) {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        }
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingFirstNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingLastNameText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingLastNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingEmailText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingEmailAddressData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingYourPostCodeSearchBox));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingYourPostCodeSearchBoxData);

        if (Props.getProp("locale").contains("DE")||Props.getProp("locale").contains("FR")||Props.getProp("locale").contains("IT")||Props.getProp("locale").contains("ES")) {
            IsPageLoaded.waitAllRequest();
            List<WebElement> myList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']/li")));
            myList.get(1).click();
            IsPageLoaded.waitAllRequest();
            WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='c2a_results']/li[1]")));
            webelement.click();
        } else {
            WebElement webelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='c2a_results']")));
            webelement.click();
        }
        enterPhoneNumberBilling();
    }

    public void fillInBillingsDetailsWithoutZipCode() throws InterruptedException {
        Actions actions = new Actions(getWebDriver());
        WebElement element;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        } catch (Exception ex) {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        }
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingFirstNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingLastNameText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingLastNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingEmailText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingEmailAddressData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingYourPostCodeSearchBox));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingYourPostCodeSearchBoxData);

        if (Props.getProp("locale").contains("DE")||Props.getProp("locale").contains("FR")||Props.getProp("locale").contains("IT")||Props.getProp("locale").contains("ES")) {
            IsPageLoaded.waitAllRequest();
            List<WebElement> myList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']/li")));
            myList.get(1).click();
            IsPageLoaded.waitAllRequest();
            WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='c2a_results']/li[1]")));
            webelement.click();
        } else {
            WebElement webelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='c2a_results']")));
            webelement.click();
        }
        IsPageLoaded.waitAllRequest();
        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingPostalcode));
        actions.moveToElement(element).build().perform();
        waitAndClear( billingPostalcode);
        enterPhoneNumberBilling();
    }


    public void fillInBillingDetailsAustria() throws InterruptedException {
        Actions actions = new Actions(getWebDriver());
        WebElement element;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        } catch (Exception ex) {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        }
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingFirstNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingLastNameText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingLastNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingEmailText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingEmailAddressData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingYourPostCodeSearchBox));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingYourPostCodeSearchBoxDataAUSTRIA);

        if (Props.getProp("locale").contains("DE")) {
            IsPageLoaded.waitAllRequest();
            List<WebElement> myList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']/li")));
            myList.get(1).click();
            IsPageLoaded.waitAllRequest();

        } else {
            WebElement webelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='c2a_results']")));
            webelement.click();
        }
        enterPhoneNumberBilling();
    }

    public void fillInBillingDetailsWithoutZipcode() throws InterruptedException {
        Actions actions = new Actions(getWebDriver());
        WebElement element;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        } catch (Exception ex) {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(billingFirstNameText));
        }
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingFirstNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingLastNameText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingLastNameData);

        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingEmailText));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingEmailAddressData);

//        element = wait.until(ExpectedConditions.presenceOfElementLocated(billingYourPostCodeSearchBox));
//        actions.moveToElement(element).build().perform();
//        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(billingYourPostCodeSearchBoxData);
        enterPhoneNumberBilling();
    }

    public boolean enterPhoneNumberBilling() {
        boolean flag = false;
        WebElement element;
        element = wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberBillingDesktop));
        try {
            try {
                new Actions(getWebDriver()).moveToElement(element).build().perform();
            } catch (Exception e) {
                log.error("Unable to move to element-->>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
            element.clear();
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(RandomGenerator.random(10, PermittedCharacters.NUMERIC));
            flag = true;
            log.info("Successfully entered the billing phone number");
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(RandomGenerator.random(10, PermittedCharacters.NUMERIC));
            //log.error("Some Exception occured while filling up billing phone number" + ExceptionUtils.getStackTrace(e));
            //e.printStackTrace();
        }
        return flag;
    }

    public boolean enterPhoneNumberShipping() {
        boolean flag = false;
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberShippingDesktop));
            try {
                new Actions(getWebDriver()).moveToElement(element).build().perform();
            } catch (Exception e) {
                log.error("Unable to move to element-->>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
            element.clear();
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(RandomGenerator.random(10, PermittedCharacters.NUMERIC));
            flag = true;
            log.info("Successfully entered the shipping phone number");
        } catch (Exception e) {
            log.error("Some Exception occured while filling up phone number in shipping address" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public void fillInShippingDetails() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(shippingFirstNameText)).sendKeys(shippingFirstNameData);
        wait.until(ExpectedConditions.elementToBeClickable(shippingLastNameText)).sendKeys(shippingLastNameData);
        wait.until(ExpectedConditions.elementToBeClickable(shippingYourPostCodeSearchBox)).sendKeys(shippingYourPostCodeSearchBoxData);
        if (Props.getProp("locale").contains("DE")||Props.getProp("locale").contains("IT")||Props.getProp("locale").contains("ES")) {
            List<WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']/li"));
            myList.get(1).click();
            Thread.sleep(1000);
//            WebElement webelement = webDriver.findElement(By.xpath("//ul[@class='c2a_results']/li"));
//            webelement.click();
        } else {
            WebElement webelement = webDriver.findElement(By.xpath("//ul[@class='c2a_results']"));
            webelement.click();
        }
        enterPhoneNumberShipping();
    }

    public boolean validateShippingAddessSameAsBilling() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(differentShippingAddressBtn)).isDisplayed();

    }

    public String verifyerrorMessageforCreateAccount() {
        IsPageLoaded.waitAllRequest();
        String text = "Some error in fetching text";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        WebElement element;
        try {
            switch (Props.getProp("locale").toUpperCase()) {
                case "DE":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(createaccountErrorTextUK));
                    new Actions(getWebDriver()).moveToElement(element).build().perform();
                    text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                    break;
                case "UK":
                    try {
                        text = waitForExpectedElement(createaccountErrorTextUK).getText().trim();
                        text = getTextFor(createaccountErrorTextUK).trim();
//                        element = wait.until(ExpectedConditions.presenceOfElementLocated(createaccountErrorTextUK));
//                        new Actions(getWebDriver()).moveToElement(element).build().perform();
//                        text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                        break;
                    } catch (ElementNotInteractableException ex) {
                        element = wait.until(ExpectedConditions.presenceOfElementLocated(createaccounterrorTextUKalternate));
                        new Actions(getWebDriver()).moveToElement(element).build().perform();
                        text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                    }
            }
            log.info("Error message found while create account-->>>" + text);
        } catch (Exception e) {
            log.info("some exception occurs while validating error messages for create account" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String verifyerrorMessageforWeakPassword() {
        IsPageLoaded.waitAllRequest();
        String text = "Some error in fetching text";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        WebElement element;
        try {
            switch (Props.getProp("locale").toUpperCase()) {
                case "DE":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(weakPasswordCreatAccountErrorTextUk));
                    new Actions(getWebDriver()).moveToElement(element).build().perform();
                    text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                    break;
                case "UK":
                    try {
                        text = waitForExpectedElement(weakPasswordCreatAccountErrorTextUk).getText().trim();
                        text = getTextFor(weakPasswordCreatAccountErrorTextUk).trim();
//                        element = wait.until(ExpectedConditions.presenceOfElementLocated(createaccountErrorTextUK));
//                        new Actions(getWebDriver()).moveToElement(element).build().perform();
//                        text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                        break;
                    } catch (ElementNotInteractableException ex) {
                        element = wait.until(ExpectedConditions.presenceOfElementLocated(weakPasswordCreatAccountErrorTextUk));
                        new Actions(getWebDriver()).moveToElement(element).build().perform();
                        text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
                    }
            }
            log.info("Error message found while create account-->>>" + text);
        } catch (Exception e) {
            log.info("some exception occurs while validating error messages for create account" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public boolean clickRedeemHereLink() {
        boolean flag = false;
        By redeemHereLink = redeemHereLinkMobileDE;
//        By redeemHereLink = redeemHereLinkDesktop;
//        if(UrlBuilder.isMobile()){
//            redeemHereLink = redeemHereLinkMobile;
//            if(getWebDriver().findElements(redeemHereLink).size()==0){
//                redeemHereLink=redeemHereLinkMobileDE;
//            }
//        }
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(redeemHereLink));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(redeemHereLink)).click();
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while click on redeem here link-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean enterPromoCode(String promoCode) {
        boolean flag = false;
        try {
//			waitForExpectedElement(promoEntryTextBox).sendKeys(promoCode);
            wait.until(ExpectedConditions.elementToBeClickable(promoEntryTextBox)).sendKeys(promoCode);

            log.info("Successfully entered promo code");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while promo code entry-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean clickApplyButton() {
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(promoApplyBtn));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            log.info("Successfully clicked apply button");
            Thread.sleep(2000);
            flag = true;

        } catch (Exception e) {
            log.info("Some exception occurred while click on apply button-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPromoAppliedSuccessMessage() {
        boolean flag = false;
        try {
            //flag = waitForExpectedElement(promoAppliedMessage).getText().contains(Props.getMessage("item.discount.promo.applied.message"));
            flag = wait.until(ExpectedConditions.visibilityOfElementLocated(promoAppliedMessage)).
                    getText().contains(Props.getMessage("item.discount.promo.applied.message"));

            log.info("Successfully found promo applied success message");
        } catch (Exception e) {
            log.info("Some exception occurred while finding applied promo message-->>" + e.getMessage());
        }
        return flag;
    }
	/*
	public void payWithCardRadioBtn(String cardNumber, String expiryDate, String cvcNumber) throws InterruptedException {
		String stripePayCardNumber = cardNumber;
		String stripePayCardExpiry = expiryDate;
		String stripePayCardCvc = cvcNumber;

		System.out.println(stripePayCardNumber);
		System.out.println(stripePayCardExpiry);
		System.out.println(stripePayCardCvc);

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("window.scrollBy(0,1000)");
		waitForExpectedElement(payWithAmericanCardRadioBtn).click();

		Thread.sleep(15000);

		webDriver.switchTo().frame(0);
		waitForExpectedElement(stripepaycard).sendKeys(stripePayCardNumber);
		webDriver.switchTo().defaultContent();

		webDriver.switchTo().frame(1);
		waitForExpectedElement(stripePayCardExpiry1).sendKeys(stripePayCardExpiry);
		webDriver.switchTo().defaultContent();

		webDriver.switchTo().frame(2);
		waitForExpectedElement(stripePayCardCvc1).sendKeys(stripePayCardCvc);
		webDriver.switchTo().defaultContent();

	}

	 */

    public void createAccountwithIncorrectPassword() throws InterruptedException {
        WebElement elementCreateAccountCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(createAccountCheckbox));
        new Actions(getWebDriver()).moveToElement(elementCreateAccountCheckbox).build().perform();
        if (elementCreateAccountCheckbox.isDisplayed()) {
            if (elementCreateAccountCheckbox.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(elementCreateAccountCheckbox)).click();
                wait.until(ExpectedConditions.elementToBeClickable(createAccountPasswordText)).
                        sendKeys(Props.getProp("IncorrectCreateAccountPassword"));
            } else {
                assertFalse(true, "element: [ " + elementCreateAccountCheckbox + " ] is not enabled");
            }
        } else {
            assertFalse(true, "element: [ " + elementCreateAccountCheckbox + " ] is not displayed");
        }
    }

    public void createAccount() {

        WebElement elementCreateAccountCheckbox = webDriver.findElement(createAccountCheckbox);

        if (elementCreateAccountCheckbox.isDisplayed()) {
            if (elementCreateAccountCheckbox.isEnabled()) {
                elementCreateAccountCheckbox.click();
            } else {
                assertFalse(true, "element: [ " + elementCreateAccountCheckbox + " ] is not enabled");
            }
        } else {
            assertFalse(true, "element: [ " + elementCreateAccountCheckbox + " ] is not displayed");
        }

//		waitForExpectedElement(createAccountPasswordText).sendKeys("Password123");
        wait.until(ExpectedConditions.elementToBeClickable(createAccountPasswordText)).sendKeys("Password123");


    }

    public void InvalidCardExpiry(String cardNumber, String InValidexpiryDate) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
        //waitForExpectedElement(payWithCardRadioBtn).click();
        //Thread.sleep(5000);

        webDriver.switchTo().frame("braintree-hosted-field-number");
//		waitForExpectedElement(BTPayCardNumberText).clear();
//		waitForExpectedElement(BTPayCardNumberText).sendKeys(cardNumber);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(BTPayCardNumberText));
        element.clear();
        element.sendKeys(cardNumber);
        webDriver.switchTo().defaultContent();

        webDriver.switchTo().frame("braintree-hosted-field-expirationDate");
//		waitForExpectedElement(BTPayCardExpiryText).clear();
//		waitForExpectedElement(BTPayCardExpiryText).sendKeys(InValidexpiryDate);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(BTPayCardExpiryText));
        element1.clear();
        element1.sendKeys(InValidexpiryDate);
        webDriver.switchTo().defaultContent();

    }

    public void payWithCardRadioBtn(String cardNumber, String expiryDate, String cvcNumber) throws InterruptedException {
        String stripePayCardNumber = cardNumber;
        String stripePayCardExpiry = expiryDate;
        String stripePayCardCvc = cvcNumber;
        System.out.println(stripePayCardNumber);
        System.out.println(stripePayCardExpiry);
        System.out.println(stripePayCardCvc);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
//		waitForExpectedElement(payWithCardRadioBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(payWithCardRadioBtn)).click();
//		Thread.sleep(5000);
        webDriver.switchTo().frame(0);
//		waitForExpectedElement(stripePayCardNumberText).sendKeys(stripePayCardNumber);
        wait.until(ExpectedConditions.elementToBeClickable(stripePayCardNumberText)).sendKeys(stripePayCardNumber);
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(1);
//		waitForExpectedElement(stripePayCardExpiryText).sendKeys(stripePayCardExpiry);
        wait.until(ExpectedConditions.elementToBeClickable(stripePayCardExpiryText)).sendKeys(stripePayCardExpiry);
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(2);
//		waitForExpectedElement(stripePayCardCvcText).sendKeys(stripePayCardCvc);
        wait.until(ExpectedConditions.elementToBeClickable(stripePayCardCvcText)).sendKeys(stripePayCardCvc);
        webDriver.switchTo().defaultContent();
    }

    public void InvalidCardNumber(String invalidCardNumber) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
        //waitForExpectedElement(payWithCardRadioBtn).click();
        //Thread.sleep(5000);
        webDriver.switchTo().frame("braintree-hosted-field-number");
//		waitForExpectedElement(BTPayCardNumberText).clear();
//		waitForExpectedElement(BTPayCardNumberText).sendKeys(invalidCardNumber);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(BTPayCardNumberText));
        element.clear();
        element.sendKeys(invalidCardNumber);
        webDriver.switchTo().defaultContent();
    }

    //Braintree-Rita Singh
    public boolean payBycardBraintree() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payWithCardRadioBtnBT));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on radio button pay by card on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("../label")).click();
                count++;
                Thread.sleep(2000);
            } catch (NoSuchElementException e1) {
                log.error("Again try to find the element-->>" + payWithCardRadioBtnBT);
                count++;
            } catch (Exception e) {
                log.error("Unable to click on radio button pay by card on checkout page-->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            }
        } while (count < 5);
        return flag;
    }

    /**
     * public boolean payByKlarna() throws InterruptedException {
     * boolean flag = false;
     * if (Props.getProp("locale").contains("DE")) {
     * int count = 0;
     * do {
     * try {
     * WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaRadioBtnDE));
     * if (element.isSelected()) {
     * flag = true;
     * log.info("Successfully clicked on radio button pay by card on checkout page ");
     * break;
     * }
     * new Actions(getWebDriver()).moveToElement(element).build().perform();
     * element.findElement(By.xpath("../label")).click();
     * count++;
     * Thread.sleep(2000);
     * } catch (NoSuchElementException e1) {
     * log.error("Again try to find the element-->>" + klarnaRadioBtnDE);
     * count++;
     * } catch (Exception e) {
     * log.error("Unable to click on radio button pay by card on checkout page-->>>" + ExceptionUtils.getStackTrace(e));
     * e.printStackTrace();
     * break;
     * }
     * } while (count < 5);
     * <p>
     * }else{
     * try{
     * WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaRadioBtnUK));
     * if (element.isSelected()) {
     * flag = true;
     * log.info("Successfully clicked on Klarna radio buttonUK on checkout page ");
     * }
     * new Actions(getWebDriver()).moveToElement(element).build().perform();
     * element.findElement(By.xpath("../label")).click();
     * Thread.sleep(2000);
     * }catch (NoSuchElementException e1){
     * log.error("Again try to find the element-->>"+klarnaRadioBtnUK);
     * <p>
     * }
     * catch (Exception e) {
     * log.error("Unable to click on Klarna radio buttonUK  on checkout page-->>>" + ExceptionUtils.getStackTrace(e));
     * e.printStackTrace();
     * }
     * }
     * return flag;
     * }
     **/


    public boolean payByKlarna() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaRadioBtn));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on radio button pay by card on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("../label")).click();
                count++;
                Thread.sleep(2000);
            } catch (NoSuchElementException e1) {
                log.error("Again try to find the element-->>" + klarnaRadioBtn);
                count++;
            } catch (Exception e) {
                log.error("Unable to click on radio button pay by card on checkout page-->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            }
        } while (count < 5);
        return flag;
    }

    public boolean payByKlarnaForDE() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaRadioBtnDE));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on Klarna radio button  on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("../label")).click();
                flag = true;
                log.info("Successfully clicked on Klarna radio button  on checkout page ");
                count++;
                Thread.sleep(2000);
            } catch (NoSuchElementException e1) {
                log.error("Again try to find the element-->>" + klarnaRadioBtnDE);
                count++;
            } catch (Exception e) {
                log.error("Unable to click on Klarna radio button on checkout page-->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            }
        } while (count < 5);
        return flag;
    }


    public boolean payIn3FinancingButtonKlarna() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                Thread.sleep(3000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payIn3OrFinancing));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on payIn3OrFinancing radio button on checkout page ");
                    break;
                }
            }
//                new Actions(getWebDriver()).moveToElement(element).build().perform();
//                element.findElement(By.xpath("../label")).click();
//                count++;
//                Thread.sleep(2000);
//            }
//            }catch (NoSuchElementException e1){
//                log.error("Again try to find the element-->>"+payIn3OrFinancing);
//                count++;
//            }
            catch (Exception e) {
                log.error("Unable to click on payIn3OrFinancing radio button -->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            }
        } while (count < 5);
        return flag;
    }

    public boolean RatenzahlungRadioButtonKlarnaDE() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                webDriver.switchTo().frame(waitForExpectedElement(FrameKlarnaPaymentDE));
                log.info("Successfully switch into the frame" + FrameKlarnaPaymentDE);
                Thread.sleep(3000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(RatenzahlungRadioButtonKlarna));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on Ratenzahlung radio button at first on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("//*[@id=\"radio-pay_over_time__label\"]")).click();
                flag = true;
                log.info("Successfully clicked on Ratenzahlung radio button at 2nd on checkout page ");
                count++;
                Thread.sleep(2000);
                break;
            } catch (Exception e) {
                log.error("Unable to click on Ratenzahlung radio button -->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            } finally {
                webDriver.switchTo().defaultContent();
            }
        } while (count < 5);

        return flag;
    }

    public boolean PayIn30DaysRadioButtonKlarnaDE() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                webDriver.switchTo().frame(waitForExpectedElement(FrameKlarnaPaymentDE));
                log.info("Successfully switch into the frame" + FrameKlarnaPaymentDE);
                Thread.sleep(3000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(PayIn30DaysRadioButtonKlarna));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on Pay in 30Days radio button at first on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("//*[@id=\"radio-pay_later__label\"]")).click();
                flag = true;
                log.info("Successfully clicked on Pay in 30Days radio button at 2nd on checkout page ");
                count++;
                Thread.sleep(2000);
                break;
            } catch (Exception e) {
                log.error("Unable to click on Pay in 30Days radio button -->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            } finally {
                webDriver.switchTo().defaultContent();
            }
        } while (count < 5);

        return flag;
    }

    public boolean PayNowRadioButtonKlarnaDE() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                webDriver.switchTo().frame(waitForExpectedElement(FrameKlarnaPaymentDE));
                flag = true;
                log.info("Successfully switch into the frame" + FrameKlarnaPaymentDE);
                Thread.sleep(3000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(PayNowRadioButtonKlarna));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on Pay Now radio button at first on checkout page ");
                    break;
                }
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.findElement(By.xpath("//*[@id=\"radio-pay_now__label\"]")).click();
                flag = true;
                log.info("Successfully clicked on Pay Now radio button at 2nd on checkout page ");
                count++;
                Thread.sleep(2000);
                break;
            } catch (Exception e) {
                log.error("Unable to click on Pay Now radio button -->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            } finally {
                webDriver.switchTo().defaultContent();
            }
        } while (count < 5);

        return flag;
    }

    public boolean LatschriftRadioButtonKlarnaDE() {
        boolean flag = false;
        int count = 0;
        do {
            try {
                webDriver.switchTo().frame(waitForExpectedElement(FrameKlarnaPaymentDE));
                log.info("Successfully switch into the frame" + FrameKlarnaPaymentDE);
                Thread.sleep(3000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(LatschriftRadioButtonKlarna));
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully clicked on Latschrift radio button at first on checkout page ");
                    break;
                }
//                new Actions(getWebDriver()).moveToElement(element).build().perform();
//                element.findElement(By.xpath("//*[@id=\"radio-pay_now__label\"]")).click();
//                flag = true;
//                log.info("Successfully clicked on Pay Now radio button at 2nd on checkout page ");
//                count++;
//                Thread.sleep(2000);
//                break;
            } catch (Exception e) {
                log.error("Unable to click on Latschrift radio button -->>>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            } finally {
                webDriver.switchTo().defaultContent();
            }
        } while (count < 5);

        return flag;
    }


    public void payWithBTCardRadioBtn(String cardNumber, String expiryDate, String cvcNumber, String postalCode) throws InterruptedException {
        String BTPayCardNumber = cardNumber;
        String BTCardExpiry = expiryDate;
        String BTPayCardCvc = cvcNumber;
        String BTPostalCode = postalCode;
        IsPageLoaded.waitAllRequest();
        wait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);
        if (UrlBuilder.isIpad() || UrlBuilder.isIphone()) {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(getiFrameCardNumberTxtBox));
            js.executeScript("arguments[0].scrollIntoView();", webElement);
            js.executeScript("arguments[0].setAttribute('" + BTPayCardNumber + "')", webElement);

            WebElement webElementDate = wait.until(ExpectedConditions.presenceOfElementLocated(getiFrameExpirationDateTxtBox));
            js.executeScript("arguments[0].scrollIntoView();", webElementDate);
            js.executeScript("arguments[0].setAttribute('" + BTCardExpiry + "')", webElementDate);

            WebElement webElementCvv = wait.until(ExpectedConditions.presenceOfElementLocated(getiFrameCvvTxtBox));
            js.executeScript("arguments[0].scrollIntoView();", webElementCvv);
            js.executeScript("arguments[0].setAttribute('" + BTPayCardCvc + "')", webElementCvv);

            WebElement webElementPostalCode = wait.until(ExpectedConditions.presenceOfElementLocated(getiFramePostalCode));
            js.executeScript("arguments[0].scrollIntoView();", webElementPostalCode);
            js.executeScript("arguments[0].setAttribute('" + BTPostalCode + "')", webElementPostalCode);

        } else {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(iFrameCardNumber)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameCardNumberTxtBox)).sendKeys(BTPayCardNumber);
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(iFrameExpirationDate)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameExpirationDateTxtBox)).sendKeys(BTCardExpiry);
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(iFrameCvv)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameCvvTxtBox)).sendKeys(BTPayCardCvc);
            webDriver.switchTo().defaultContent();
//            webDriver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(iFramePostalCode)));
//            wait.until(ExpectedConditions.elementToBeClickable(getiFramePostalCode)).sendKeys(BTPostalCode);
//            webDriver.switchTo().defaultContent();
        }
    }

    public void payWithBTGiropayRadioBtn() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
//		waitForExpectedElement(payWithGiropayRadioBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(payWithGiropayRadioBtn)).click();
        //Thread.sleep(5000);
    }

    public void entersecureCodeandClickSubmit() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(0,1000)");
            //Thread.sleep(5000);
            webDriver.switchTo().frame("Cardinal-CCA-IFrame");
            //Thread.sleep(5000);
//			waitForExpectedElement(enterSecureCode).sendKeys("1234");
            wait.until(ExpectedConditions.elementToBeClickable(enterSecureCode)).sendKeys("1234");

//			waitForExpectedElement(clickOnSubmitButton).click();
            wait.until(ExpectedConditions.elementToBeClickable(clickOnSubmitButton)).click();
            webDriver.switchTo().defaultContent();
            //	Thread.sleep(5000);

        } catch (Exception e) {
            log.info("exception occuresd while entering secure code " + e.getMessage());
        }
    }

    public void enterInvalidCardNumber(String cardnumber) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
        //waitForExpectedElement(payWithCardRadioBtn).click();
        //Thread.sleep(5000);

        //webDriver.switchTo().frame(waitForExpectedElement(iFrameCardNumber));
        webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCardNumber)));
        //waitForExpectedElement(getiFrameCardNumberTxtBox).sendKeys(cardnumber);
        wait.until(ExpectedConditions.elementToBeClickable(getiFrameCardNumberTxtBox)).sendKeys(cardnumber);
        webDriver.switchTo().defaultContent();

    }

    public void payPalRadioBtn() throws InterruptedException {
        if (Props.getProp("locale").contains("DE")) {
            IsPageLoaded.waitAllRequest();
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalRadioBtnDE));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
        } else if (Props.getProp("locale").contains("IT")||(Props.getProp("locale").contains("ES"))) {
            IsPageLoaded.waitAllRequest();
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalRadioBtnIT));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
        }
        else{
            IsPageLoaded.waitAllRequest();
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalRadioBtn));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
        }
    }

    public void paypalExpressCheckout() throws InterruptedException {
       // IsPageLoaded.waitAllRequest();

        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector(".component-frame.visible")));
        log.info("successfully Switched in to a frame");
        Thread.sleep(1000);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(paypalExpressCheckout));
       // new Actions(getWebDriver()).moveToElement(element).build().perform();
        element.click();
        log.info("successfully clicked on the paypal button");
        webDriver.switchTo().defaultContent();
    }
    public void paypalExpressCheckoutFromCart() throws InterruptedException {
        // IsPageLoaded.waitAllRequest();

        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector(".component-frame.visible")));
        log.info("successfully Switched in to a frame");
        Thread.sleep(1000);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(paypalExpressCheckoutFromCart));
        // new Actions(getWebDriver()).moveToElement(element).build().perform();
        element.click();
        log.info("successfully clicked on the paypal button");
        webDriver.switchTo().defaultContent();
    }


    public void payPalPaylaterRadioBtn() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalPayLaterRadioBtn));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        element.click();
        frameToBeAvailableAndSwitchToIt(payPalPayLaterMessageIframe);
        if (isElementPresent(payPalPayLaterMessage)) {
            Assert.assertTrue(waitForExpectedElement(payPalPayLaterMessage).getText().contains(Props.getProp("paypalPayLater.message.checkout")));
        }
        webDriver.switchTo().defaultContent();

    }

    public void payPalCreditRadioBtn() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(paypalPayCreditRadioBtn));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        element.click();
    }

    public void PayPalLink() {
        waitForExpectedElement(payPalLink).click();
    }

    public void brainTreeRadioBtn() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
//		waitForExpectedElement(brainTreeRadioBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(brainTreeRadioBtn)).click();

        //Thread.sleep(5000);
    }

    public void competitionsAndNewCheckbox() {
        try {
            waitForExpectedElement(competitionsAndNewCheckbox);
            clickUsingJS(competitionsAndNewCheckbox);
        } catch (Exception e) {
            waitForExpectedElement(competitionsAndNewCheckbox);
            clickUsingJS(competitionsAndNewCheckbox);
        }
    }
    public void offersCheckbox() {
        waitForExpectedElement(offersCheckbox).click();
    }

    public void cleaningTipsArticlesCheckbox() {
        waitForExpectedElement(cleaningTipsArticlesCheckbox).click();
    }

    public boolean placeOrderSecurelyButton() {
        boolean flag = false;

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderSecurelyButtonBT));

        if (UrlBuilder.isMobile()) {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.click();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                log.error("Some exception occurred while clicking on Order Place button trying one more time ");
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        } else {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).click().build().perform();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        }
        return flag;
    }

    public boolean placeOrderSecurelyButtonExpressCheckout() {
        boolean flag = false;

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderSecurelyButtonExpress));

        if (UrlBuilder.isMobile()) {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.click();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                log.error("Some exception occurred while clicking on Order Place button trying one more time ");
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        } else {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).click().build().perform();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        }
        return flag;
    }

    public void termsAndConditionsCheckBox() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            try {
                waitForExpectedElement(termsAndConditionsCheckBox, 2);
                clickByElementByQueryJSExecutor(termsAndConditionsCheckBox);
                log.info("Successfully clicked on Terms and conditions checkbox--> " + termsAndConditionsCheckBox);
            }catch(Exception e){
                clickByElementByQueryJSExecutor(termsAndConditionsCheckBox);
                log.info("Some exception occurred while clicking on Terms and conditions checkbox--> " + termsAndConditionsCheckBox);
            }
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBox);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
    }

    public void termsAndConditionsCheckBoxForPayLaterDE() {
        IsPageLoaded.waitAllRequest();
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            try {
                webDriver.switchTo().frame(0);
                log.info("successfully switched in to frame ");
                clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForPaylaterDE);
                log.info("Successfully clicked on Terms and conditions checkbox--> " + termsAndConditionsCheckBoxForPaylaterDE);
            }catch(Exception e){
                clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForPaylaterDE);
                log.info("Some exception occurred while clicking on Terms and conditions checkbox--> " + termsAndConditionsCheckBoxForPaylaterDE);
            }
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBox);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
        webDriver.switchTo().defaultContent();
    }

    public void agreeAndContinueButtonForPayLaterDE() {
        IsPageLoaded.waitAllRequest();
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            try {
                webDriver.switchTo().frame(0);
                log.info("successfully switched in to frame ");
                clickByElementByQueryJSExecutor(agreeAndContinueButtonForPaylaterDE);
                log.info("Successfully clicked on AgreeAndContinueButton--> " + agreeAndContinueButtonForPaylaterDE);
            }catch(Exception e){
                clickByElementByQueryJSExecutor(agreeAndContinueButtonForPaylaterDE);
                log.info("Some exception occurred while clicking on AgreeAndContinueButton--> " + agreeAndContinueButtonForPaylaterDE);
            }
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBox);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
        webDriver.switchTo().defaultContent();
    }

    public void termsAndConditionsCheckBoxForRatenzahlungKlarna() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForKlarnaDERatenzahlung);
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForKlarnaDERatenzahlung);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
    }

    public WebElement payPalEmail() {
        return waitForExpectedElement(payPalEmail, 10);
    }

//    public WebElement KlarnacontactNo() {
//        waitForExpectedElement(klarnaPhoneNo).click();
//        waitClearAndEnterText(klarnaPhoneNo,phoneNumber);
//        return waitForExpectedElement(klarnaPhoneNo, 10);
//    }

    public boolean enterKlarnaPhoneNo(String ContactNumber) {
        boolean bool = false;
        try {
            WebElement element = waitForExpectedElement(klarnaPhoneNo,3);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            element.sendKeys(ContactNumber);
//          waitClearAndEnterText(klarnaPhoneNo,ContactNumber);
            log.info("Successfully set the Klarna Phone No ");

            bool = true;

        } catch (Exception e) {
            log.info("Some exception occurred while entering the Klarna Contact Number -->>" + e.getMessage());
        }
//        finally
//        {
//            webDriver.switchTo().defaultContent();
//        }

        return bool;
    }


    public boolean payNowKlarnaSelectMethod() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = waitForExpectedElement(payNowKlarnaSelectionButton, 5);
            clickByElementByQueryJSExecutor(payNowKlarnaSelectionButton);
            flag = true;
            log.info("Successfully clicked on Klarna Select Method before selecting radio button options ");
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on Klarna Select Method before selecting radio button options-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }


    public boolean payViaPayByCardMethod() {
        boolean flag = false;
        try {
            IsPageLoaded.waitAllRequest();
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payNowKlarnaButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Pay Now Klarna Radio Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Pay Now Klarna Radio Button-->>>" + ExceptionUtils.getStackTrace(e));
            Assert.fail("Some exception occured while clicking on Pay Now Klarna Radio Button-->>>");
        }
        return flag;
    }

    public boolean payVia30DaysMethod() {
        boolean flag=false;
        try {
            IsPageLoaded.waitAllRequest();
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payIn30DaysKlarnaButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            log.info("Successfully clicked on Pay in 30Days Klarna Radio Button ");
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occured while clicking on Pay in 30Days Klarna Radio Button-->>>" + ExceptionUtils.getStackTrace(e));
            Assert.fail("Some exception occured while clicking on Pay in 30Days Klarna Radio Button-->>>");
        }
        return flag;
    }


    public boolean payViaFianncing() {
        boolean flag = false;
        try {
            IsPageLoaded.waitAllRequest();
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payViaFianncing));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Pay via Financing Klarna Radio Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Pay via Financing Klarna Radio Button-->>>" + ExceptionUtils.getStackTrace(e));
            webDriver.quit();
        }
        return flag;
    }

    public boolean enterKlarnaPhoneNoForDE(String ContactNumber) {
        boolean bool = false;
        try {
            IsPageLoaded.waitAllRequest();
            webDriver.switchTo().frame(waitForExpectedElement(FrameKlarnaEnterPhoneNoForDE));
            log.info("Successfully switch into the frame" + FrameKlarnaEnterPhoneNoForDE);
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaPhoneNoForDE));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            Thread.sleep(6000);
            element.sendKeys(ContactNumber);
//          waitClearAndEnterText(klarnaPhoneNo,ContactNumber);
            log.info("Successfully set the Klarna Phone No ");

            bool = true;

        } catch (Exception e) {
            log.info("Some exception occurred while entering the Klarna Contact Number -->>" + e.getMessage());
        }
//        finally
//        {
//            webDriver.switchTo().defaultContent();
//        }

        return bool;
    }

    public void payPalNextButton() {
        waitForExpectedElement(payPalNextButton).click();
    }

    public WebElement payPalPassword() {
        return waitForExpectedElement(payPalPassword, 5);
    }

    public void payPalLoginButton() {
        clickByElementByQueryJSExecutor(payPalLoginButton);
//		waitForExpectedElement(payPalLoginButton).click();
    }

    public void changeCountryOnLocalsBasis() {
        if (Props.getProp("locale").contains("DE")) {
            wait.until(ExpectedConditions.elementToBeClickable(changePaypalAddress));
            clickUsingJS(changePaypalAddress);
            Select sc = new Select(webDriver.findElement(By.xpath("//select[@id='shippingDropdown']")));
            List<WebElement> l1 = sc.getOptions();
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i).getText().contains("Pusignan")) {
                    l1.get(i).click();
                    break;
                }
            }
        } else if (Props.getProp("locale").contains("UK")) {
            wait.until(ExpectedConditions.elementToBeClickable(changePaypalAddress));
            clickUsingJS(changePaypalAddress);
            Select sc = new Select(webDriver.findElement(By.xpath("//select[@id='shippingDropdown']")));
            List<WebElement> l1 = sc.getOptions();
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i).getText().contains("HAMPSHIRE")) {
                    l1.get(i).click();
                    break;
                }
            }


        } else if (Props.getProp("locale").contains("FR")) {
            wait.until(ExpectedConditions.elementToBeClickable(changePaypalAddress));
            clickUsingJS(changePaypalAddress);
            Select sc = new Select(webDriver.findElement(By.xpath("//select[@id='shippingDropdown']")));
            List<WebElement> l1 = sc.getOptions();
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i).getText().contains("PARIS")) {
                    l1.get(i).click();
                    break;
                }
            }


        }

    }





    public boolean clickOnAcceptCookiesBtn() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.ignoring(NotFoundException.class, TimeoutException.class);
        try {
            WebElement element = fluentWait.until(ExpectedConditions.presenceOfElementLocated(acceptCookiesBtn));
            fluentWait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            fluentWait.until(ExpectedConditions.invisibilityOf(element));
            flag = true;
        } catch (NotFoundException e1) {
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while finding the accept cookie button-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectPayLaterMethod() {
        boolean flag = false;
        WebElement element = null;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(payPalPayLaterSelectDE));
            element.click();
            flag = true;
        } catch (NotFoundException e1) {
            log.error("Paypal Pay Later radio button is not present" + ExceptionUtils.getFullStackTrace(e1));
            e1.printStackTrace();
        }
        return flag;
    }

    public boolean selectPayPalCreditMethod() {
        boolean flag = false;
        WebElement element = null;
        IsPageLoaded.waitAllRequest();
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(payPalCreditSelectOption));
            element.click();
            flag = true;
        } catch (NotFoundException e1) {
            log.error("Paypal Credit options are not present on paypal service" + ExceptionUtils.getFullStackTrace(e1));
            e1.printStackTrace();
        }
        return flag;
    }

    public boolean payPalContinueButton() throws InterruptedException {
        boolean flag = false;
        if (Props.getProp("locale").contains("DE")) {
            IsPageLoaded.waitAllRequest();
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            int count = 0;
            do {
                try {
                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(1000);
                    getWebDriver().findElement(payPalContinueButtonForDE).click();
                    flag = true;
                    break;
                } catch (NotFoundException e1) {
                    log.error("Paypal Continue button is not there going to try for pay now button " + ExceptionUtils.getFullStackTrace(e1));
                    e1.printStackTrace();
                } catch (Exception e) {
                    log.error("Some Exception occured while cliking on paypal continue button-->>" + ExceptionUtils.getFullStackTrace(e));
                    e.printStackTrace();
                }
                count++;
            } while (count < 3);

        } else {
            IsPageLoaded.waitAllRequest();
            By payPalContinueBtn = null;
            if (UrlBuilder.isMobile()) {
                payPalContinueBtn = payPalContinueButtonUKMobile;
            } else {
                payPalContinueBtn = payPalContinueButtonUK;
            }
            try {
                Thread.sleep(1000);
                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalContinueButton));
                element.click();
                flag = true;
            } catch (NotFoundException e1) {
                log.error("Paypal Continue button is not there going to try for pay now button " + ExceptionUtils.getFullStackTrace(e1));
                e1.printStackTrace();
            } catch (Exception e) {
                log.error("Some Exception occured while cliking on paypal continue button-->>" + ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean klarnaContinueButton() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaContinueButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;

    }

    public boolean klarnaContinueButton2() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = waitForExpectedElement(klarnaContinueButton2,3);
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button2 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button2-->>>" + ExceptionUtils.getStackTrace(e));
            //webDriver.quit();

        }
        return flag;
    }

    public boolean klarnaContinueButtonBestatigen() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaContinueButtonBestatigen));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button2 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button2-->>>" + ExceptionUtils.getStackTrace(e));
            //webDriver.quit();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean klarnaContinueButtonZehle() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaContinueButtonZehle));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button Zehle ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button Zehle-->>>" + ExceptionUtils.getStackTrace(e));
            //webDriver.quit();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }


    public boolean klarnaDEEinkaufButtonContinue() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(KlarnaDEEinkauf));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna klarnaDEEinkaufButtonContinue  ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on klarnaDEEinkaufButtonContinue-->>>" + ExceptionUtils.getStackTrace(e));
            webDriver.quit();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean klarnaContinueButton3() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaContinueButton3));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button3 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button3-->>>" + ExceptionUtils.getStackTrace(e));
            //webDriver.quit();
        }
        return flag;
    }

    public boolean payWithKlarnaButton() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = waitForExpectedElement(payWithKButton, 3);
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on pay with Klarna Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on pay with Klarna Button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean payWithKlarnaButtonFor30Days() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payWithKButtonFOr30Days));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on pay with K KlarnaButtonFor30Days Klarna Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on pay with K KlarnaButtonFor30Days Klarna Button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean chooseFasterCheckoutButton() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = waitForExpectedElement(chooseFasterCheckoutButton,3);
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on ChooseFasterCheckoutButton ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button2-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;

    }

    public boolean enterKlarnaOTPNumber(String klarnaOTPNumber) {
        boolean flag = false;
        try {
            //           webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCardNumber)));
            wait.until(ExpectedConditions.elementToBeClickable(klarnaOTP)).sendKeys(klarnaOTPNumber);
            log.info("Successfully entered Klarna OTP number");
            Thread.sleep(6000);
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting Klarna OTP number-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean enterKlarnaOTPNumberDE(String klarnaOTPNumber) {
        boolean flag = false;
        try {
            //           webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCardNumber)));
            wait.until(ExpectedConditions.elementToBeClickable(klarnaOTP)).sendKeys(klarnaOTPNumber);
            log.info("Successfully entered Klarna OTP number");
            Thread.sleep(3000);
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting Klarna OTP number-->>" + e.getMessage());
        }
        return flag;
    }


    public boolean payPalPayLaterContinueButton() throws InterruptedException {
        boolean flag = false;
        if (Props.getProp("locale").contains("DE")) {
            IsPageLoaded.waitAllRequest();
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            try {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1000);
                getWebDriver().findElement(payPalContinueButton).click();
                flag = true;
            } catch (NotFoundException e1) {
                log.error("Paypal Continue button is not there going to try for pay now button " + ExceptionUtils.getFullStackTrace(e1));
                e1.printStackTrace();
            } catch (Exception e) {
                log.error("Some Exception occured while cliking on paypal continue button-->>" + ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            }
        } else {
            IsPageLoaded.waitAllRequest();
            By payPalContinueBtn = null;
            if (UrlBuilder.isMobile()) {
                payPalContinueBtn = payPalContinueButtonUKMobile;
            } else {
                payPalContinueBtn = payPalContinueButtonUK;
            }
            try {
                Thread.sleep(1000);
                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1000);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalContinueBtn));
                element.click();
                flag = true;
            } catch (NotFoundException e1) {
                log.error("Paypal Continue button is not there going to try for pay now button " + ExceptionUtils.getFullStackTrace(e1));
                e1.printStackTrace();
            } catch (Exception e) {
                log.error("Some Exception occured while cliking on paypal continue button-->>" + ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            }
        }
        List<WebElement> elementcontinueIframe = webDriver.findElements(payPalPayLaterIframe);
        if (elementcontinueIframe.size() > 0) {
            webDriver.switchTo().frame(elementcontinueIframe.get(0));
            wait.until(ExpectedConditions.elementToBeClickable(payPalPayLaterSubmitButton)).click();
            int count = 0;
            while (count < 3) {
                List<WebElement> webelements = webDriver.findElements(payPalPayLaterVisaCard);
                if (webelements.size() == 0) {
                    waitForExpectedElement(payPalPayLaterSubmitButton).click();
                    IsPageLoaded.waitAllRequest();
                } else {
                    webelements.get(0).click();
                    waitForExpectedElement(payPalPayLaterTermsCondition).click();
                    waitForExpectedElement(payPalPayLaterSubmitButton).click();
                    break;
                }
                count++;
            }

            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean clickPayPalPayNowButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalPayNowBtnMobile));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickOnContinueButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(7000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalContinueButtonForDE));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
           // element.click();
            clickUsingJS(payPalContinueButtonForDE);
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickToContinueButtonBlackScreen() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = waitForExpectedElement(ClickToContinueButtonBlackScreen, 2);
//            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on continue button of black screen ");
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on continue button of black screen-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickOnBindingApplicationButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(7000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalContinueButtonForDE));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Binding Application Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Binding Application Button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickOnBlackContinueButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(blackContinueButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Black Continue Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Black Continue Button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickSubmitButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(paypalSubmitButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickPayPalPayCreditButton() {
        IsPageLoaded.waitAllRequest();

        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(paypalCreditButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickPaypalCreditPaymentOption1() {
        IsPageLoaded.waitAllRequest();

        boolean flag = false;
        try {
            Thread.sleep(1000);
            String text=webDriver.findElement(PaypalCreditPaymentOption1).getText();
            log.info("paypal credit text received " +text);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(PaypalCreditPaymentOption1));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
         clickUsingJS(PaypalCreditPaymentOption1);
            flag = true;
            log.info("Successfully clicked on PaypalCreditPaymentOption1 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PaypalCreditPaymentOption1-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickPaypalpayinThree() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
//            Thread.sleep(7000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(PaypalPayInThree));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            clickByElementByQueryJSExecutor(PaypalPayInThree);
            flag = true;
            log.info("Successfully clicked on PaypallaterRadio Button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PaypallaterRadio Button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }




    public boolean clickPaypalCreditEmiOption1() {
        IsPageLoaded.waitAllRequest();

        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(PaypalCreditEmiOption1));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on PaypalCreditEmiOption1 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PaypalCreditEmiOption1-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }
    public void clickPaypalpayinThreeContinueButton() {
        webDriver.switchTo().frame(0);
        log.info("successfully Switched in to a frame");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(paypalPayInThreeContione));
        // new Actions(getWebDriver()).moveToElement(element).build().perform();
       clickUsingJS(paypalPayInThreeContione);
        log.info("successfully clicked on the paypal button");
        webDriver.switchTo().defaultContent();


    }








    public boolean clickChooseAndContinueButton() {
        IsPageLoaded.waitAllRequest();

        boolean flag = false;
        try {
            Thread.sleep(1000);
            log.info("hi getting click"+ webDriver.findElement(chooseAndContinueButton).getText());
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(chooseAndContinueButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
           clickUsingJS(chooseAndContinueButton);
            flag = true;
            log.info("Successfully clicked on PaypalCreditPaymentOption1 ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PaypalCreditPaymentOption1-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickPayAnotherWay() {
        IsPageLoaded.waitAllRequest();

        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payAnotherWay));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public boolean clickPayPalPayPayLaterNowButton() throws InterruptedException {
        Thread.sleep(10000);
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalPayLaterPayNowBtnMobile));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            flag = true;
            log.info("Successfully clicked on PayNow Button on PayPal window ");
        } catch (Exception e) {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(payPalPayLaterPayNowBtnMobile));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            log.error("Some exception occured while clicking on PayNow button on PayPal window-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public void clickSuccessButton() {
        waitForExpectedElement(SuccessButton).click();
    }
    //Created by Rita Singh
	/*public Boolean VerifyOrder()
	{
		Boolean flag=false;
		try
		{
//flag=waitForExpectedElement(orderReceivedText).getText().contains(Props.getProp("Order.Verify.Message"));
log.info("Order placed successfully");
		}
		catch (Exception e) {
			log.info("some exception occurs while validating that order places successfully");

		}
		return flag;
	}
	*/

    public String getErrorMessageForFirstName() {
        String text = "Not Found";
        try {
            Thread.sleep(5000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(firstNameErrorMsg));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                text = element.getText().trim();
                log.info(text + " Error message for first Name is displayed on the page");
            } else {
                text = "Error message not displayed on the page ";
            }
        } catch (Exception e) {
            log.error("Some Exception Occured while getting error message on checkout page-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageForLastName() {
        String text = "Not Found";
        try {
            Thread.sleep(5000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(lastNameErrorMsg));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                text = element.getText().trim();
                log.info(text + " Error message for first Name is displayed on the page");
            } else {
                text = "Error message not displayed on the page ";
            }
        } catch (Exception e) {
            log.error("Some Exception Occured while getting error message on checkout page-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageForPostalCode() {
        String text = "Not Found";
        try {
            Thread.sleep(5000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(postalCodeErrorMsg));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                text = element.getText().trim();
                log.info(text + " Error message for first Name is displayed on the page");
            } else {
                text = "Error message not displayed on the page ";
            }
        } catch (Exception e) {
            log.error("Some Exception Occured while getting error message on checkout page-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageForTermsAndConditions() {
        String text = "Not Found";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(termsAndConditionErrorMsg));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                text = element.getText().trim();
            } else {
                text = "Error message not displayed on the page ";
            }
        } catch (Exception e) {
            log.error("Some Exception Occured while getting error message on checkout page-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public boolean VerifyCardIsInvalid() {
        Boolean flag = false;
        try {

            flag = waitForExpectedElement(invalidCardDetails).getText().contains(Props.getMessage("invalid.card.error.message"));

            flag = waitForExpectedElement(CardExpirationMessage).getText().contains(Props.getMessage("card.expiration.message"));

        } catch (Exception e) {
            log.info("some exception occurs while validating error messages for invalid card details");
        }
        return flag;
    }

    public String verifyInsertCorrectCreditDataMessage() {
        String text = "Not Found/Some Exception is there";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(InsertCorrectDataMessage));
            text = element.getText().trim();
            log.info("Text Found which is----->>>>>>" + text);
        } catch (Exception e) {
            log.error("some exception occurs while validating enter correct card details message--->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public boolean VerifyerrorMessageforInvalidPaypalCredentials() {
        Boolean flag = false;
        try {
            flag = waitForExpectedElement(InvalidPaypalCredentialsMessage).getText().contains(Props.getMessage("invalid.paypal.credentials.message"));
        } catch (Exception e) {
            log.info("some exception occurs while validating message for invalid paypal credentials");
        }
        return flag;

    }

    public boolean VerifySecurityCodeIsIncomplete() {
        Boolean flag = false;
        try {
            flag = waitForExpectedElement(SecurityCodeIncompleteDataMessage).getText().contains(Props.getMessage("security.code.incomplete.message"));
        } catch (Exception e) {
            log.info("some exception occurs while validating security code incomplete error message");
        }
        return flag;

    }

    public boolean VerifyCardExpirationMessage() {
        Boolean flag = false;
        try {
            flag = waitForExpectedElement(CardExpirationMessage).getText().contains(Props.getMessage("card.expiration.message"));
        } catch (Exception e) {
            log.info("some exception occurs while validating card expiration message");
        }
        return flag;

    }

    public boolean enterCardNumber(String cardNumber) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCardNumber)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameCardNumberTxtBox)).sendKeys(cardNumber);
            log.info("Successfully entered card number on checkout page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting card number on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterCardExpiry(String cardExpiry) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameExpirationDate)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameExpirationDateTxtBox)).sendKeys(cardExpiry);
            log.info("Successfully entered card expiry date");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while expiry date on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterCvv(String cvv) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCvv)));
            wait.until(ExpectedConditions.elementToBeClickable(getiFrameCvvTxtBox)).sendKeys(cvv);
            log.info("Successfully entered card cvv");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting cvv on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean clickPurchaseButton() {
        boolean flag = false;
        try {
//			webDriver.switchTo().frame(waitForExpectedElement(iFrameCvv));
            waitForExpectedElement(purchaseBtn).click();
            log.info("Successfully entered card cvv");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting cvv on checkout page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean enterStripeCardNumber(String stripePayCardNumber) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(0);
            waitForExpectedElement(stripePayCardNumberText).sendKeys(stripePayCardNumber);
            log.info("Successfully entered card number on checkout page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting card number on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterStripeCardExpiry(String stripePayCardExpiry) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(1);
            waitForExpectedElement(stripePayCardExpiryText).sendKeys(stripePayCardExpiry);
            log.info("Successfully entered card expiry date");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while expiry date on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterStripeCvc(String stripePayCardCvc) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(2);
            waitForExpectedElement(stripePayCardCvcText).sendKeys(stripePayCardCvc);
            log.info("Successfully entered card cvv");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while putting cvv on checkout page-->>" + e.getMessage());
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean clickStripeInstallmentLabel() throws InterruptedException {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(0,1000)");
//			waitForExpectedElement(stripeInstallmentLabel).click();
            wait.until(ExpectedConditions.elementToBeClickable(stripeInstallmentLabel)).click();
            //Thread.sleep(5000);
            log.info("Successfully clicked stripe instalment label");
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred click on stripe instalment label-->>" + e.getMessage());
        }
        return flag;

    }

    public void brainTreeInstallmentRadioBtn() throws InterruptedException {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(brainTreeInstallmentRadioBtn));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        int count = 0;
        do {
            element.findElement(By.xpath("parent::div/label")).click();
            count++;
        } while (!element.isSelected() && count < 5);
    }


    //----Sanket---//
    public String fetchOrderNumberFromOrderSummaryPage() {
        String text = "";
        int count = 0;
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
                text = element.getText().trim();
                if (!(text.equalsIgnoreCase(""))) {
                    log.info("Found order having order number-->" + text);
                    break;
                }

            } catch (Exception e) {
                log.error("Some exception occurred while getting order number from order summary page-->" + e.getMessage());
                e.printStackTrace();
            }
            count++;
        } while (count < 5);
        return text;
    }

    public String getErrorIncorrectOrderAmount() {
        String msg = "Order amount is incorrect or error message not found";
        IsPageLoaded.waitAllRequest();
        wait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(4)).ignoring(Exception.class);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(errorText));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        msg = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
        return msg;
    }

    public boolean fetchAndSaveOrderNumberIntoApiConfigFileHavingKey(String key) {
        boolean flag = false;
        String orderNumber = fetchOrderNumberFromOrderSummaryPage();
        if (!orderNumber.equalsIgnoreCase("")) {
            try {
                log.info("Value of order Number before is-->>" + Props.getProp(key));
                PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
                config.setProperty(key, orderNumber);
                config.save();
                log.info("Successfully updated the config file with value of orderNumber as-->" + orderNumber);
                flag = true;
            } catch (Exception e) {
                log.error("Some exception occurred while updating the config file with value of order Number--->" + e.getMessage());
                e.printStackTrace();
            }

            Props.loadRunConfigProps("/environment.properties");
        } else {
            log.error("Order number is empty, Unable to set the order number against key in config file");
        }
        return flag;
    }

    public boolean verifyPlaceOrderButtonisEnabled() {
        boolean flag = false;
        WebElement webelement = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderSecurelyButtonBT));
        flag = webelement.isEnabled();
        System.out.println("flag= " + flag);
        return flag;

    }

    public String getErrorMessageForIncorrectPasswordLogin() {
        String text = "";
        IsPageLoaded.waitAllRequest();
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(incorrectPasswordMsgLogin));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                text = element.getText().trim();
            } else {
                text = "Error message not displayed on the page ";
            }
        } catch (Exception e) {
            log.error("Some Exception Occured while getting error message on checkout page-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }
public void paypalPayInthreeCntinueButton() {

        webDriver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(payPalPayLaterSubmitButton));
        clickUsingJS(payPalPayLaterSubmitButton);
    webDriver.switchTo()
            .defaultContent();
    }
    public void paypalPayMobileNumber() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        webDriver.switchTo().frame(0);
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        log.info("successfully switched in to a frame ");
        IsPageLoaded.waitAllRequest();
        WebElement wb = waitForExpectedElement(enterPaypalLaterPhoneNo, 5);
//        Thread.sleep(10000);
        wb.sendKeys(Keys.chord(Keys.CONTROL, "a"), "+4901761428434");
        log.info("Successfully Entered the Telephone Number");
        webDriver.switchTo().defaultContent();

    }

    public boolean clickOnExpressCheckout() {
        boolean flag = false;

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaExpressCheckout));

        if (UrlBuilder.isMobile()) {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.click();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                log.error("Some exception occurred while clicking on Order Place button trying one more time ");
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        } else {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).click().build().perform();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        }
        return flag;
    }
    public boolean klarnaExpressContinueButton()  {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaExpressCheckoutButton));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;

    }

    public boolean klarnaExpressContinueButtonAgain()  {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(3000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(klarnaExpressCheckoutButtonAgain));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            flag = true;
            log.info("Successfully clicked on Kalrna Continue button ");
        } catch (Exception e) {
            log.error("Some exception occured while clicking on Klarna Continue button-->>>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;

    }

    public boolean placeOrderSecurelyButton2() {
        boolean flag = false;

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderSecurelyButton2));

        if (UrlBuilder.isMobile()) {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.click();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                log.error("Some exception occurred while clicking on Order Place button trying one more time ");
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element");
                    } catch (Exception e1) {
                        log.error("Some exception occurred while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        } else {
            try {
                webDriver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                Thread.sleep(1000);
                new Actions(getWebDriver()).moveToElement(element).click().build().perform();
                flag = true;
                log.info("Successfully clicked on Place Order button");
            } catch (Exception e) {
                List<WebElement> list = getWebDriver().findElements(By.xpath("//iframe[@title='PayPal']"));
                for (WebElement ele : list) {
                    try {
                        ele.click();
                        flag = true;
                        log.info("Successfully clicked on Place Order button on desktop");
                        break;
                    } catch (ElementNotVisibleException e2) {
                        log.error("Element is not visible trying for other element on desktop");
                    } catch (Exception e1) {
                        log.error("Some exception occured while clicking on pay by pal button ");
                        break;
                    }
                }
            }
        }
        return flag;
    }




    public boolean checkboxDisplayed()
    {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(termsAndConditionsCheckBoxForKlarnaDERatenzahlung));
        return element.isDisplayed();
    }

    public void termsAndConditionsCheckBoxForKlarnaFR() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForKlarnaFR);
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(termsAndConditionsCheckBoxForKlarnaFR);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
    }

    public void clickOnPayByCard() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            clickByElementByQueryJSExecutor(clickOnPayByCard);
        } else if (UrlBuilder.isMobile()) {
            clickByElementByQueryJSExecutor(clickOnPayByCard);
            //builder1.moveToElement(webDriver.findElement(termsAndConditionsCheckBox), -100,0).click().build().perform();
        }
    }



public void setupPaymentForpayinThree() {

        webDriver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(selectcardinpayinthree));
        clickUsingJS(selectcardinpayinthree);
        wait.until(ExpectedConditions.elementToBeClickable(agreeContinue));
    clickUsingJS(agreeContinue);
        wait.until(ExpectedConditions.elementToBeClickable(payPalPayLaterSubmitButton));
        clickUsingJS(payPalPayLaterSubmitButton);

        webDriver.switchTo()
                .defaultContent();
    }


    public void disappearProcessingElement()
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderElement));
    }


}