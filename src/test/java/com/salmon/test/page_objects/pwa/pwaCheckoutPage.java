package com.salmon.test.page_objects.pwa;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class pwaCheckoutPage extends PageObject {

    private static final Logger log = LoggerFactory.getLogger(pwaCheckoutPage.class);

    //Billing Details
    private static final By billingEmailText = By.xpath("//div[@class='col-md-8']//input[@data-testing-id='email']");
    private static final By continueToShippingButton=By.xpath("//*[@id=\"collapseBasic\"]/form/div[4]/div/button");
    private final By country = By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/fieldset/ish-select-country/div/div/select");
    ////select[@class='form-control ng-pristine ng-invalid ng-touched']
    private final By state=By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/ish-address-form-default/div/fieldset[3]/ish-select-region/div/div/select");

    private static final By billingFirstNameText = By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/ish-address-form-default/div/fieldset[1]/ish-input[1]/div/div/input");
    private static final By billingLastNameText = By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/ish-address-form-default/div/fieldset[1]/ish-input[2]/div/div/input");
    private static final By addressLine1=By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/ish-address-form-default/div/fieldset[2]/ish-input[1]/div/div/input");
    private static final By billingYourPostCodeSearchBox = By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/ish-address-form-container/ish-address-form/div/ish-address-form-default/div/fieldset[3]/ish-input[1]/div/div/input");
    private static final By continueToPaymentOptions=By.xpath("/html/body/ish-root/div/div/div/sn-checkout-page/sn-checkout-order-page/div/div[1]/div/div[2]/div[2]/div/ish-basket-shipping-address-widget/div[2]/ish-customer-address-form/form/div/div/button[1]");
    private static final By payWithCardRadioBtnBT = By.xpath("//*[@id=\"payment-accordion\"]/li[1]/div/label");

    //Pay with card(Braintree) element locator
    private static final By iFrameCardNumber = By.xpath("//iframe[@id='braintree-hosted-field-number']");
    private static final By getiFrameCardNumberTxtBox = By.xpath("//input[@id='credit-card-number']");
    private static final By iFrameExpirationDate = By.xpath("//iframe[@id='braintree-hosted-field-expirationDate']");
    private static final By getiFrameExpirationDateTxtBox = By.xpath("//input[@id='expiration']");
    private static final By iFrameCvv = By.xpath("//iframe[@id='braintree-hosted-field-cvv']");
    private static final By getiFrameCvvTxtBox = By.xpath("//input[@id='cvv']");
    private static final By submitOrderButton=By.xpath("//*[@data-testing-id=\"termsAndConditions\"]");
    private static final By orderReceivedText = By.xpath("//div[@data-testing-id='checkout-receipt-page']//div[1]/span[1]/div[2]");

    private static final By billingPostalcode=By.xpath("//*[@id=\"billing_PostalCode\"]");
    private static final By shippingPostalCode=By.xpath("//*[@id=\"shipping_PostalCode\"]");
    private static final By shippingYourPostCodeSearchBox=By.xpath("//*[@id=\"shipping-search-bar\"]");

    private String billingFirstNameData = random(6, ALPHABETS);
    private String billingLastNameData = random(6, ALPHABETS);
    private String addressLine1Data=random(6,ALPHABETS);
    private String billingYourPostCodeSearchBoxData=Props.getProp("country.postal.code");
    private String shippingYourPostCodeSearchBoxData=Props.getProp("country.postal.code.shipping");
    private String shippingFirstNameData = random(6, ALPHABETS);
    private String shippingLastNameData = random(6, ALPHABETS);

    private String billingEmailAddressData = RandomGenerator.randomEmailAddress(6);

    public void fillInBillingDetails() throws InterruptedException {

        waitForExpectedElement(billingEmailText).sendKeys(billingEmailAddressData);
        wait.until(ExpectedConditions.elementToBeClickable(continueToShippingButton)).click();
        selectValueFromDropDownByby(Props.getProp("country.name"),country);
        waitForExpectedElement(billingFirstNameText).sendKeys(billingFirstNameData);
        waitForExpectedElement(billingLastNameText).sendKeys(billingLastNameData);
        waitForExpectedElement(addressLine1).sendKeys(addressLine1Data);
        wait.until(ExpectedConditions.elementToBeClickable(billingYourPostCodeSearchBox)).sendKeys(billingYourPostCodeSearchBoxData);
        List<WebElement> myList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='c2a_results']")));

        for (WebElement element:myList)
            if(element.getText().contains(Props.getProp("postaltext")))
            {
                element.click();
                  Thread.sleep(1000);
                    wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.xpath("li[1]")))).click();
                    System.out.println("element to be clicked-->"+element.getText());
            }
        Thread.sleep(13000);
        selectValueFromDropDownByby(Props.getProp("state.name"),state);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(continueToPaymentOptions)).click();
    }

    public void payWithBTCardRadioBtn(String cardNumber, String expiryDate, String cvcNumber) throws InterruptedException {
        String BTPayCardNumber = cardNumber;
        String BTCardExpiry = expiryDate;
        String BTPayCardCvc = cvcNumber;
        wait.until(ExpectedConditions.elementToBeClickable(payWithCardRadioBtnBT)).click();
        Thread.sleep(5000);
        webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCardNumber)));
        wait.until(ExpectedConditions.elementToBeClickable(getiFrameCardNumberTxtBox)).sendKeys(BTPayCardNumber);
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameExpirationDate)));
        wait.until(ExpectedConditions.elementToBeClickable(getiFrameExpirationDateTxtBox)).sendKeys(BTCardExpiry);
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameCvv)));
        wait.until(ExpectedConditions.elementToBeClickable(getiFrameCvvTxtBox)).sendKeys(BTPayCardCvc);
        webDriver.switchTo().defaultContent();
    }
    public void termsAndConditionsCheckBox() {
            Actions builder1=new Actions(webDriver);
            builder1.moveToElement(webDriver.findElement(By.xpath("//*[@data-testing-id=\"termsAndConditions\"]")), -150,0).click().build().perform();


    }
    public void submitOrderButton() {
        Actions act=new Actions(getWebDriver());
        act.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(submitOrderButton))).click().build().perform();

    }
    public Boolean orderReceivedText() {
        Boolean flag = false;
        try {
            flag = wait.until(ExpectedConditions.visibilityOfElementLocated(orderReceivedText)).getText().contains(Props.getMessage("order.received.text"));
        } catch (Exception e) {
            log.info("Order Received Text Extracted From Checkout Page error is-->" + e.getMessage());

        }
        return flag;
    }
}

