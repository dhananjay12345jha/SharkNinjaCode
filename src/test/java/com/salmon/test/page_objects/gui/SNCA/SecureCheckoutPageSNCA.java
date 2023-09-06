package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.HomePage;
import cucumber.api.java.es.E;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jboss.netty.channel.ExceptionEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecureCheckoutPageSNCA extends MyAccountAddressPageSNCA {
    //    private static final By textSecureCheckOut = By.xpath("//*[contains(@class,'checkout-title')]");
    private static final By textSecureCheckOut = By.xpath("//*[contains(@class,'checkout-breadcrumb')]");
    private static final By errorMessageInAlertText = By.xpath("//div[@class='alert alert-danger']");
    private static final By OrderNo = By.xpath("//*[@data-testing-id='ordernumber']");
    private static final By deliveryPostalCode = By.xpath("//*[@data-testing-id='zipcode']");
    private final By findMyOrder = By.xpath("//*[@class='btn btn-primary btn-all btn-lg-rounded btn-primary-black']");
    static By selectAccountForm = By.xpath("//select[@class='form-control']");
    private static final By thankForYourOrderText = By.xpath("//sn-checkout-receipt//span/div[2]");
    private static final By contactInfoEmail = By.xpath("//input[@data-testing-id=\"email\"]");
    private static final By contactInfoUsername = By.xpath("//input[@data-testing-id=\"login\"]");
    private static final By errorMessageShown = By.xpath("//ish-error-message/div");
    private static final By contactInfoAccountPassword = By.xpath("//input[@data-testing-id=\"password\"]");
    private static final By radioButtonIhavePassword = By.xpath("//input[@id=\"signin\"]");
    private static final By radioButtonContinueAsAGuest = By.xpath("//input[@id=\"guest\"]");
    private static final By continueToShipping = By.xpath("(//button[@type=\"submit\" and contains(@class,'btn-primary')])[1]");
    private static final By continueToPaymentOptionBtn = By.xpath("//div[contains(@class,'checkout-save-address-btn')]/button[@type=\"submit\"]");
    private static final By radioButtonPayByCard = By.xpath("//input[@id=\"BRAINTREE_CREDITCARD\"]");
    private static final By radioButtonPayByPayPal = By.xpath("//input[@id=\"BRAINTREE_PAYPAL\"]");
    private static final By iframeForPayPal = By.xpath("//iframe[@title=\"PayPal\"]");
    private static final By clickPayPalCheckoutBtn = By.id("paypal-animation-content");
    private static final By iframeNameOnCard = By.id("braintree-hosted-field-cardholderName");
    private static final By nameOnCard = By.id("cardholder-name");
    private static final By iframeCardNumber = By.id("braintree-hosted-field-number");
    private static final By iframeExpirationDate = By.id("braintree-hosted-field-expirationDate");
    private static final By iframeCvvNumber = By.id("braintree-hosted-field-cvv");
    private static final By iframePostalCode = By.cssSelector("iframe#braintree-hosted-field-postalCode");
    private static final By cardNumber = By.id("credit-card-number");
    private static final By expirationDate = By.id("expiration");
    private static final By cvvNumber = By.id("cvv");
    private static final By postalCodeForCard = By.id("postal-code");
    private static final By reviewOrderButton = By.xpath("//button[@type='button' and contains(@class,'submit-order')]");
    private static final By checkBoxIAgreeToTermsAndCondition = By.xpath("//input[@data-testing-id=\"termsAndConditions\"]");
    private static final By estimatedTotal = By.xpath("//div[@class=\"responsive-price total\"]/div[2]");
    private static final By orderNumber = By.xpath("//a[starts-with(@href,'/account/orders')]");
    private static final By emailIdShown = By.xpath("//div[contains(@class,'order-shipping-summary')]//p[2]/strong");
    private static final By shippingDetails = By.xpath("(//ish-address/address)[1]");
    private static final By billingDetails = By.xpath("(//ish-address/address)[2]");
    private static final By paymentMethod = By.xpath("//ish-info-box[contains(@heading,'payment')]//p");
    private static final By promoCodeOnItems = By.xpath("//span[@class=\"promo-text\"]/following-sibling::span");
    private static final By discountedPriceShownWithItem = By.xpath("//p[@class=\"discount-price\"]");
    private static final By promoCodeDiscountPrice = By.xpath("(//*[@class=\"ch-summary-product-price\"])[2]//p");
    private static final By getSubTotalValue = By.xpath("//div[@data-testing-id=\"basket-subtotal\"]");
    private static final By getEstimatedShippingCost = By.cssSelector("div.responsive-price.shipping div:nth-child(2)");
    private static final By billingAddressSameAsShipping = By.xpath("//input[@id=\"checksippingbox\"]/../label");
    private static final By createNewAccountPassword = By.xpath("//input[@data-testing-id=\"createAccountPassword\"]");
    //    private static final By getPasswordErrorMessageWhileAccountCreation = By.xpath("//div[@aria-live=\"assertive\"]/small"); // ---xpath changed
    private static final By getPasswordErrorMessageWhileAccountCreation = By.xpath("//div[@aria-live=\"polite\"]/small");
    private static final By chkboxDifferentBillingAddress = By.xpath("//label[@for='checksippingbox']");
    private static final By chkboxSubscribeForOffers = By.xpath("//div[@data-testing-id='sameAsShipping']/input[@type='checkbox']");
    private static final By orderId = By.cssSelector("strong[data-testing-id=\"order-document-number\"]");
    private static final Logger log = LoggerFactory.getLogger(SecureCheckoutPageSNCA.class);
    private static By isPaymentMethodDetailsShown = By.xpath("//div[@class=\"loading\"]");
    private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");

    public static boolean isPageRefreshedAndDetailsShown() {
        boolean flag = false;
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(25));
        fluentWait.pollingEvery(Duration.ofSeconds(1));
        fluentWait.ignoring(Exception.class);
        try {
            boolean isElementPresent = fluentWait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    if (driver.findElements(isPaymentMethodDetailsShown).size() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            if (isElementPresent) {
                fluentWait.until(ExpectedConditions.invisibilityOf(getWebDriver().findElement(isPaymentMethodDetailsShown)));
                flag = true;
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
        }
        return flag;
    }

    public float getSubTotalPrice() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getSubTotalValue));
            String k = element.getText().trim();
            if (k.contains("$")) {
                k = k.replace("$", "");
            }
            if (k.contains(",")) {
                k = k.replace(",", ".");
            }
            price = Float.parseFloat(k);
            log.info("Successfully fetched the subtotal price on checkout page which is-->" + price);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return price;
    }

    public float getEstimatedShippingPrice() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getEstimatedShippingCost));
            String k = element.getText().trim();
            if (k.contains("$")) {
                k = k.replace("$", "");
            }
            if (k.contains(",")) {
                k = k.replace(",", ".");
            }
            price = Float.parseFloat(k);
            log.info("Successfully fetched the estimated shipping price on checkout page which is-->" + price);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return price;
    }

    public float getTotalPrice() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(estimatedTotal));
            String k = element.getText().trim();
            if (k.contains("$")) {
                k = k.replace("$", "");
            }
            if (k.contains(",")) {
                k = k.replace(",", ".");
            }
            price = Float.parseFloat(k);
            log.info("Successfully fetched the Total price on checkout page which is-->" + price);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return price;
    }

    public boolean checkErrorAlertMsg(String error) {
        boolean flag = false;
        String expectedErrorMsg = Props.getProp(error);
        try {
            String actualErrorMsg = getTextFor(errorMessageInAlertText);
            if (actualErrorMsg.equals(expectedErrorMsg)) {
                flag = true;
                log.info("Error message is correct");
            } else
                log.error("Actual message is " + actualErrorMsg + ".Though expected message is " + expectedErrorMsg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }


    public List<Float> getProductPriceList() {
        IsPageLoaded.waitAllRequest();
        List<Float> priceList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(discountedPriceShownWithItem))
                .stream().map(s -> {
                            String k = s.getText().trim();
                            if (k.contains("$")) {
                                k = k.replace("$", "");
                            }
                            if (k.contains(",")) {
                                k = k.replace(",", ".");
                            }

                            return Float.parseFloat(k);
                        }
                ).collect(Collectors.toList());
        log.info("Successfully fetched the product titles list");
        return priceList;
    }

    public float getDiscountPriceOnOrder() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(promoCodeDiscountPrice));
            ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,400)");
            String k = element.getText().trim();
            if (k.contains("$")) {
                k = k.replace("$", "");
            }
            if (k.contains(",")) {
                k = k.replace(",", ".");
            }
            price = Float.parseFloat(k);
            log.info("Successfully fetched the discount price on checkout page which is-->" + price);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return price;
    }

    public List<String> getPromoCodeFromCheckoutPage() {
        IsPageLoaded.waitAllRequest();
        List<String> promoCode = new ArrayList<>();
        try {
            Thread.sleep(1000);
            promoCode = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(promoCodeOnItems))
                    .stream().map(s -> {
                                String k = s.getText().trim();
                                return k;
                            }
                    ).collect(Collectors.toList());
            log.info("Successfully fetched the product titles list");
        } catch (Exception e) {

        }
        return promoCode;
    }

    public String getTextSecureCheckOut() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(textSecureCheckOut)).getText().trim();
            log.info("Successfully fetched the text which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getOrderIdAfterPlacingOrder() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(orderId)).getText().trim();
            log.info("Successfully fetched the order number which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the order number due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean saveOrderIdInConfigFile(String key, String id) {
        boolean flag = false;
        if (!id.equalsIgnoreCase("")) {
            try {
                log.info("Value of order Number before is-->>" + Props.getProp(key));
                PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
                config.setProperty(key, id);
                config.save();
                log.info("Successfully updated the config file with value of id as-->" + id);
                flag = true;
            } catch (Exception e) {
                log.error("Some exception occurred while updating the config file with value of order Number--->" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public String getTextFromErrorMessageOfWrongPasswordAtAccountCreation() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(getPasswordErrorMessageWhileAccountCreation)).getText().trim();
            log.info("Successfully fetched the text which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getTextAfterSuccessfullyOrderPlaced() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(90));
        fluentWait.pollingEvery(Duration.ofSeconds(5));
        fluentWait.ignoring(Exception.class);
        try {
            text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(thankForYourOrderText)).getText().trim();
            log.info("Successfully fetched the text which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text due to-->>" + ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public boolean setEmailId(String email) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element;
            if (webDriver.findElements(contactInfoUsername).size() != 0) {
                element = webDriver.findElement(contactInfoUsername);
            } else {
                element = webDriver.findElement(contactInfoEmail);
            }
            Actions actions = new Actions(getWebDriver());
            actions.moveToElement(element).perform();
            element.clear();
            element.sendKeys(email);
            actions.moveToElement(webDriver.findElement(estimatedTotal)).click().build().perform();

            log.info("Successfully enter the email id-->" + email);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter the email id-->>" + email + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public void clickFindMyOrderButton() {
        do {
            try {
                if (UrlBuilder.isDesktop()) {
                    waitForExpectedElement(findMyOrder).click();
                    log.info("Successfully clicked on Find my order Button");

                } else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
                   // waitForExpectedElement(HomePage.selectAccountForm).click();
                    waitForExpectedElement(findMyOrder).click();
                }
            } catch (Exception e) {
                log.info("Some exception occurred while clicking on find My Order button-->>" + e.getMessage());
                break;
            }
        } while (false);
    }
    public boolean setPassword(String password) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = webDriver.findElement(contactInfoAccountPassword);
            Actions actions = new Actions(getWebDriver());
            actions.moveToElement(element).click().build().perform();
            element.clear();
            element.sendKeys(password);
            actions.moveToElement(webDriver.findElement(estimatedTotal)).click().build().perform();
            log.info("Successfully enter the password-->" + password);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter the password-->>" + password + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setCreateNewAccountPassword(String password) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = webDriver.findElement(createNewAccountPassword);
            Actions actions = new Actions(getWebDriver());
            actions.moveToElement(element).click().build().perform();
            element.clear();
            element.sendKeys(password);
            log.info("Successfully enter the password while creating new account-->" + password);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter the password while creating new account-->>" + password + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectRadioButtonIHavePassword() {
        //IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(radioButtonIhavePassword));
            element.findElement(By.xpath("../label")).click();
            //element.click();
            if (webDriver.findElement(radioButtonIhavePassword).isSelected()) {
                flag = true;
                log.info("Successfully selected the radio button \"I have a password\"");
            } else {
                log.info("Unable to select the radio button I have a password or may be unable to get the status if it selected");
            }
        } catch (Exception e) {
            log.error("Unable to select the radio button \"I have a password\" dur to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectRadioButtonContinueAsGuest() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(radioButtonContinueAsAGuest));
            element.findElement(By.xpath("../label")).click();
            if (webDriver.findElement(radioButtonContinueAsAGuest).isSelected()) {
                flag = true;
                log.info("Successfully selected the radio button \"ContinueAsGuest\"");
            } else {
                log.info("Unable to select the radio button ContinueAsGuest or may be unable to get the status if it selected");
            }
        } catch (Exception e) {
            log.error("Unable to select the radio button \"ContinueAsGuest\" dur to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnContinueShippingButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueToShipping)).click();
            log.info("Successfully click on continue shipping button");
            flag = true;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(continueToShipping));
        } catch (Exception e) {
            log.error("Unable to click on continue shipping button due to-->>" + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnContinueToPaymentOptionBtn() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        while (count < 2) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(continueToPaymentOptionBtn));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                if (element.isDisplayed() && element.isEnabled()) {
                    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                    log.info("Successfully click on continue to payment button");
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                log.error("Unable to click on continue to payment button due to-->>" + e.getMessage() + "\n");
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean selectRadioButtonPayByCard() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        waitForPage();
        int count = 0;
        while (count < 6) {
            try {
                WebElement element = getWebDriver().findElement(radioButtonPayByCard);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                //isPageRefreshedAndDetailsShown();
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully selected the radio button \"PayByCard\"");
                    break;
                } else {
                    log.info("Unable to select the radio button PayByCard going to try one more time");
                    count++;
                }
            } catch (StaleElementReferenceException e1) {
                log.error(e1.getMessage() + " going to try one more time");
            } catch (NotFoundException e1) {
                log.error("Unbale to find element now may be page is getting load trying again-->>" + ExceptionUtils.getStackTrace(e1));
            } catch (Exception e) {
                log.error("Unable to select the radio button \"PayByCard\" due to-->" + e.getMessage());
                e.printStackTrace();
                break;
            }
            count++;
        }
        return flag;
    }

    public boolean selectCheckBoxBillingAddressSameAsShipping() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        while (count < 6) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(billingAddressSameAsShipping));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                if (!element.isSelected()) {
                    flag = true;
                    log.info("Successfully selected the radio button \"checkbox\"");
                    break;
                } else {
                    log.info("Unable to select the radio button checkbox going to try one more time");
                    count++;
                }
            } catch (StaleElementReferenceException e1) {
                log.error(e1.getMessage() + " going to try one more time");
                count++;
            } catch (Exception e) {
                log.error("Unable to select the radio button \"PayByCard\" due to-->" + e.getMessage());
                e.printStackTrace();
                break;
            }
            count++;

        }
        return flag;
    }


    public PayPalCheckOutPage clickPayPalCheckoutButton() {
        IsPageLoaded.waitAllRequest();
        PayPalCheckOutPage obj = null;
        int count = 0;
        do {
            try {
                count++;
                webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeForPayPal)));
                new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(clickPayPalCheckoutBtn))).build().perform();
                webDriver.findElement(clickPayPalCheckoutBtn).click();
                log.info("Successfully clicked on paypal checkout button ");
                obj = new PayPalCheckOutPage();
            } catch (Exception e) {
                log.error("Unable to click on paypal checkout button due to-->>" + e.getMessage());
                e.printStackTrace();
            } finally {
                webDriver.switchTo().defaultContent();
                if (obj.checkClickOnContinueBtnOnPayPalWindowIsThere()) {
                    break;
                }
                count++;
            }
        } while (count < 3);
        return obj;
    }

    public boolean selectRadioButtonPayByPayPal() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        while (count < 15) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(radioButtonPayByPayPal));
                element.click();
                flag = true;
                log.info("Successfully selected the radio button \"PayBy PayPal\"");
                break;
            } catch (StaleElementReferenceException e1) {
                log.error("StaleElementReferenceException occurred trying-->>" + count + " times");
                count++;
            } catch (Exception e) {
                log.error("Unable to select the radio button \"PayBy PayPal\" due to-->" + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        return flag;
    }

    public boolean enterNameOnCard(String name) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeNameOnCard)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nameOnCard));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.clear();
            element.click();
            element.sendKeys(name);
            log.info("Successfully set the name on card");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the name on card due to-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterCardNumber(String number) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(2000);
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeCardNumber)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
            element.clear();
            element.click();
            element.sendKeys(number);
            log.info("Successfully set the card number");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the card number due to-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterExpirationDate(String date) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeExpirationDate)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(expirationDate));
            element.clear();
            element.click();
            element.sendKeys(date);
            log.info("Successfully set the expiration date");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the expiration date due to-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterCvvNumber(String cvv) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeCvvNumber)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cvvNumber));
            element.clear();
            element.click();
            element.sendKeys(cvv);
            log.info("Successfully set the CVV Number");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the CVV Number due to-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterPostalCodeInCardDetails(String code) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframePostalCode)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(postalCodeForCard));
            element.clear();
            element.click();
            element.sendKeys(code);
            log.info("Successfully set the CVV Number");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the CVV Number due to-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean isPlaceOrderButtonDisabled() {
        IsPageLoaded.waitAllRequest();
        if (webDriver.findElement(reviewOrderButton).isEnabled()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean clickOnReviewOrderButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        waitForPage();
        do {
            try {
                WebElement element = getWebDriver().findElement(reviewOrderButton);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                flag = true;
                break;
            } catch (TimeoutException timeoutException) {
                log.error("TimeOut occured due to review order button is either not clickable or not visible-->>" + ExceptionUtils.getStackTrace(timeoutException));
                timeoutException.printStackTrace();
                count++;
            } catch (NotFoundException e1) {
                log.error("review order button not found please check strategy-->>" + ExceptionUtils.getStackTrace(e1));
                e1.printStackTrace();
                break;
            } catch (Exception e) {
                log.error("Unable to click on review order button due to-->>" + e.getMessage() + "\n");
                log.error(ExceptionUtils.getStackTrace(e));
                break;
            }
        } while (count < 3);
        return flag;
    }

    public String getReviewOrderButtonStatus() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(reviewOrderButton));
            if (element.isEnabled())
                text = "enabled";
            else
                text = "disabled";
            log.info("Successfully fetched status of review order button-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch status of review order button-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean clickOnPlaceOrderButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        while (count < 5) {
            try {
                waitForPage();
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(reviewOrderButton));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully click on place order button");
                flag = true;
                break;
            } catch (NoSuchElementException e1) {
                log.error(ExceptionUtils.getFullStackTrace(e1));
                break;
            } catch (Exception e) {
                log.error("Unable to click on place order button due to-->>" + e.getMessage() + "\n");
                log.error(ExceptionUtils.getStackTrace(e));
                count++;
            }
        }
        return flag;
    }

    public String EnterOrderNo(String orderId) {

        waitForExpectedElement(OrderNo).sendKeys(orderId);
        log.info("Agent is able to enter the order no");

        return null;
    }

    public String enterDeliveryPostalCode(String postalCode) {

        waitForExpectedElement(deliveryPostalCode).sendKeys(postalCode);
        log.info("Agent is able to enter the delivery postal code");

        return null;
    }

    public boolean selectCheckBoxIAgreeToTermsAndCondition() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        waitForPage();
        int count = 0;
        do {
            try {
                Thread.sleep(3000);
                WebElement element = getWebDriver().findElement(checkBoxIAgreeToTermsAndCondition);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully selected the checkbox \"CheckBox I Agree To Terms And Condition\"");
                    break;
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                    count++;
                }
            } catch (NotFoundException notFoundException) {
                log.error("ELement not found trying one more time-->>" + ExceptionUtils.getStackTrace(notFoundException));
                notFoundException.printStackTrace();
                count++;
            } catch (Exception e) {
                log.error("Trying one more time, Unable to select the \"CheckBox I Agree To Terms And Condition\" due to-->" + e.getMessage());
                e.printStackTrace();
                break;
            }
        } while (count < 3);
        return flag;
    }

    public String tryLoginAgainWith(String email, String pass) {
        IsPageLoaded.waitAllRequest();
        String text = "Failed To Login";
        int count = 0;
        try {
            while (count < 3) {
                if (webDriver.findElements(errorMessageShown).size() != 0) {
                    String message = webDriver.findElement(errorMessageShown).getText();
                    if (message.equalsIgnoreCase("bad request")) {
                        setEmailId(email);
                        setPassword(pass);
                        clickOnContinueShippingButton();
                        count++;
                    } else {
                        text = message;
                        break;
                    }
                } else {
                    text = "Success";
                    break;
                }
            }
        } catch (Exception e) {
            log.info("Unable to login into the existing account to continue to place order");
        }
        return text;
    }

    public boolean uncheckCheckboxToFillDifferentBillingAddress() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        do {
            try {
                WebElement checkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(chkboxDifferentBillingAddress));
                if (!checkBox.isSelected()) {
                    wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
                    log.info("Successfully click on checkbox for different billing address");
                    flag = true;
                }
            } catch (Exception e) {
                log.error("Unable to click on checkbox for different billing address-->>" + e.getMessage() + "\n");
                e.printStackTrace();
            }
            if (flag) {
                break;
            } else {
                count++;
            }
        } while (count < 3);
        return flag;
    }

    public boolean checkCheckboxToSubscribeNewsletter() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(chkboxSubscribeForOffers)).click();
            log.info("Successfully click on checkbox for newsletter subscription");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on checkbox for newsletter subscription-->>" + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return flag;
    }

}
