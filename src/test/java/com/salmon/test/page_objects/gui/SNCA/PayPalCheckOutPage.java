package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import cucumber.api.java.en_old.Ac;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public class PayPalCheckOutPage extends PageObject {
    private static final By payWithCreditCardOrDebitCardBtn = By.id("createAccount");
    private static final By enterEmailButton = By.id("email");
    private static final By enterPassword = By.id("password");
    private static final By nextButton = By.id("btnNext");
    private static final By loginButton = By.id("btnLogin");
    private static final By changeEmailLink = By.xpath("//a[@id='backToInputEmailLink']");
    private static final By textInvalidPaypalCred = By.xpath("(//p[@class='notification notification-critical'])[1]");
    private static final By textPayWithAfterLoginSuccess = By.xpath("//section[@data-testid=\"pay-with\"]//h2");
    private static final By textPayWithAfterLoginSuccessMobile=By.xpath("//section[@id='xoSelectFi']//h4");
    private static final By continueBtn = By.xpath("//button[@id=\"payment-submit-btn\"]/..");
    private static final By continueBtnMobile=By.cssSelector("div#button");
    private static final By acceptCookieButton = By.cssSelector("button#acceptAllButton");
    private static final By iframeForPayPalContinueBtn = By.xpath("//iframe[@title=\"PayPal Checkout Overlay\"]");
    private static final By clickContinueBtnPayPal = By.cssSelector("div.paypal-checkout-continue a");
    //"Checkout | Sharkninja PWA"

    private static final Logger log = LoggerFactory.getLogger(PayPalCheckOutPage.class);


    public boolean checkClickOnContinueBtnOnPayPalWindowIsThere() {
        boolean flag = false;
        try {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeForPayPalContinueBtn));
            webDriver.switchTo().frame(iframe);
            if (webDriver.findElements(clickContinueBtnPayPal).size() != 0) {
                webDriver.findElement(clickContinueBtnPayPal).click();
                flag = true;
            }
        } catch (Exception e) {
            log.error("Some exception occured while checking PayPal window is open or not-->>" + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }


    public boolean clickCookieAcceptBtn() {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.ignoring(NotFoundException.class, TimeoutException.class);
        try {
            WebElement element = fluentWait.until(ExpectedConditions.presenceOfElementLocated(acceptCookieButton));
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


    public boolean switchToPayPalWindow() throws InterruptedException {
        boolean flag = false;
        int count = 0;
        String parentWindow = webDriver.getWindowHandle();
        do {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Thread.sleep(1000);
            Set<String> allWindows = webDriver.getWindowHandles();
            Thread.sleep(2000);
            Iterator<String> iterator = allWindows.iterator();
            while (iterator.hasNext()) {
                String childWindow = iterator.next();
                log.info("Child Window---->>>" + childWindow);
                if (!childWindow.equalsIgnoreCase(parentWindow)) {
                    try {
                        webDriver.switchTo().window(childWindow);
                        if (!UrlBuilder.isMobile()) {
                            webDriver.manage().window().maximize();
                        }
                        flag = true;
                        break;
                    } catch (Exception e) {
                        log.error(ExceptionUtils.getStackTrace(e));
                        webDriver.switchTo().window(parentWindow);
                        checkClickOnContinueBtnOnPayPalWindowIsThere();
                        break;
                    }
                }
            }
            if (flag) {
                break;
            }
            count++;
        } while (count < 3);

//        do {
//            try {
//                Thread.sleep(2000);
//                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//                String parentWindow = webDriver.getWindowHandle();
//                log.info("Parent window------------>>>>>>>>>>>> " + parentWindow);
//                Set<String> totalWindow = webDriver.getWindowHandles();
//                String childWindow = totalWindow.stream().filter(s -> !(s.equalsIgnoreCase(parentWindow))).findFirst().get();
//                log.info("Child Window---------------->>>>>>>>>>>>.." + childWindow);
//                webDriver.switchTo().window(childWindow);
//                wait.until(new ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        if (driver.getCurrentUrl().contains("paypal")) {
//                            return true;
//                        }
//                        return false;
//                    }
//                });
//                webDriver.manage().window().maximize();
//                log.info("Successfully switched to the PayPal window");
//                flag = true;
//                break;
//            } catch (TimeoutException e1) {
//                log.error("Number of windows are shown-->" + webDriver.getWindowHandles().size() + " and error message shown-->>" + e1.getMessage());
//                e1.printStackTrace();
//                count++;
//            } catch (Exception e) {
//                log.error("Either Unable to open PayPal window or some other exception arised-->>" + e.getMessage());
//                log.error(ExceptionUtils.getStackTrace(e));
//                count++;
//            }
//        } while (count < 3);
        return flag;
    }

    public boolean clickOnPayWithCreditOrDebitCardBtn() {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(payWithCreditCardOrDebitCardBtn)).click();
            log.info("Successfully clicked on the \"payWithCreditCardOrDebitCardBtn\"");
            flag = true;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(payWithCreditCardOrDebitCardBtn));
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on \"payWithCreditCardOrDebitCardBtn\"" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }


    public boolean enterEmail(String email) {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enterEmailButton)).sendKeys(email);
            log.info("Successfully entered email id ");
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while entering email id" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterPassword(String password) {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enterPassword)).sendKeys(password);
            log.info("Successfully entered password ");
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while entering password " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickNextButton() {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
            log.info("Successfully clicked on the next button");
            flag = true;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(nextButton));
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on next button " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickLoginButton() {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            log.info("Successfully clicked on the login button");
            flag = true;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on login button " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickChangeEmailLink() {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(changeEmailLink)).click();
            log.info("Successfully clicked on the change email link");
            flag = true;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(changeEmailLink));
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on change email " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String getTextAfterLoginIntoPayPal() {
        String text = "Not Found";
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.ignoring(NotFoundException.class, TimeoutException.class);
        By textToGet=textPayWithAfterLoginSuccess;
        if(UrlBuilder.isMobile()){
            textToGet=textPayWithAfterLoginSuccessMobile;
        }
        try {
            text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(textToGet)).getText().trim();
            log.info("Successfully fetched the text after login into the paypal account which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to to fetch the text after login into PayPal due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getInvalidInformationPayPal() {
        String text = "Not Found";
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.ignoring(NotFoundException.class, TimeoutException.class);
        try {
            text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(textInvalidPaypalCred)).getText().trim();
            log.info("Successfully fetched the text for invalid paypal account which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to to fetch the text for invalid PayPal message due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean clickOnContinueButtonAgainForPayPal(){
        boolean flag=false;
        if(UrlBuilder.isMobile()){
            try{
                Thread.sleep(1000);
                ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1000);
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(continueBtnMobile));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                element.click();
                flag=true;
            }catch (Exception e){
                log.error("Some Exception occured while clicking on continue button 2nd time-->>"+ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
        else {
            log.info("In case of PayPal clicking continue button 2nd time only run for mobile device," +
                    "Hence returning true");
            flag=true;
        }
        return flag;
    }


    public boolean clickContinueButton() {
        boolean flag = false;
        By button=continueBtn;
        if(UrlBuilder.isMobile()){
            button=continueBtnMobile;
        }
        try {
            Thread.sleep(1000);
            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(1000);
            webDriver.findElement(button).click();
            log.info("Successfully clicked on the continue button");
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while clicking on continue button " + e.getMessage());
            e.printStackTrace();
        } finally {

            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (driver.getWindowHandles().size() == 1)
                        return true;
                    else
                        return false;
                }
            });
            webDriver.switchTo().window(webDriver.getWindowHandles().iterator().next());
        }
        return flag;
    }


}
