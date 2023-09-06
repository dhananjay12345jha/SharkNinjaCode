package com.salmon.test.page_objects.gui.SNAP;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import io.restassured.RestAssured;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutProductPage extends PageObject
{
    private String env= Props.getProp("zendesk.testing.env");
    private final By iframe=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By paymentMethodStripe=By.xpath("//input[@id=\"paymentOption_Stripe\"]");
    private static final By cardNumber=By.xpath("//input[@inputmode=\"numeric\" and @name=\"cardnumber\"]");
    private static final By cvcNumber=By.xpath("//input[@inputmode=\"numeric\" and @name=\"cvc\" ]");
    private static final By cardValidity=By.xpath("//input[@inputmode=\"numeric\" and @name=\"exp-date\"]");
    private static final By placeOrderButton=By.xpath("//button[text()=\" Place order \"]");
    private static final By acknowledgementMessage=By.xpath("//div[contains(@class,'callout--success')]");
    private static final By paymentMethodFreeOfCharge=By.xpath("//input[@id=\"paymentOption_ZeroPayment\"]");
    private static final By paymentMethodCreditCard = By.xpath("//input[@id=\"paymentOption_BRAINTREE_CREDIT_CARD_CA\"]");
    private static final By cardHolderName = By.xpath("//input[@data-braintree-name=\"cardholderName\"]");
    private static final By creditCardNumber = By.xpath("//input[@data-braintree-name=\"number\"]");
    private static final By creditCardExpirationDate = By.xpath("//input[@data-braintree-name=\"expirationDate\"]");
    private static final By creditCardCvvNumber = By.xpath("//input[@data-braintree-name=\"cvv\"]");
    private static final By creditCardPostalCode = By.xpath("//input[@data-braintree-name=\"postalCode\"]");
    private static final By iframeForCreditCard = By.xpath("(//iframe)[9]");
    private static final By payByCreditCard = By.xpath("//label[@for=\"paymentOption_Braintree\"]");
    private static final By iframeForCreditCardNumber = By.xpath("//iframe[@name=\"braintree-hosted-field-number\"]");
    private static final By iframeForCreditCardExpiration = By.xpath("//iframe[@name=\"braintree-hosted-field-expirationDate\"]");
    private static final By iframeForCreditCardCvv = By.xpath("//iframe[@name=\"braintree-hosted-field-cvv\"]");
    private static final By iframeForCreditCardPostal = By.xpath("//iframe[@name=\"braintree-hosted-field-postalCode\"]");
    private static final By payByCreditCardForDe = By.xpath("//label[@for=\"paymentOption_BRAINTREE_CREDIT_CARD_DE\"]");
    private static final By payByCreditCardForCa = By.xpath("//label[@for=\"paymentOption_BRAINTREE_CREDIT_CARD_CA\"]");

    private static final By priceForCa = By.xpath("//div[@class='price-base']");
    private static final By creditCardHolderName = By.xpath("//input[@data-braintree-name=\"cardholderName\"]");
    private static final By iframeForCreditCardHolderName = By.xpath("//iframe[@name=\"braintree-hosted-field-cardholderName\"]");
    private static final By shippingCharge = By.xpath("//div[@class=\"clearfix\"]//dd[@class=\"col-6\"][2]");
    private static final By orderId = By.xpath("//span[@data-testing-id=\"order-document-number\"]//strong");
    private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");

    List<WebDriver> list=new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(CheckoutProductPage.class);

    public boolean enterCardholderName(String name) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeForCreditCardHolderName)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(creditCardHolderName));
            element.clear();
            element.click();
            element.sendKeys(name);
            log.info("Successfully set the card holder name");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the card holder name due to-->>" + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }
        return flag;
    }

    public boolean enterCardNumber(String number) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframeForCreditCardNumber)));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(creditCardNumber));
            element.clear();
            element.click();
            element.sendKeys(number);
            log.info("Successfully set the card Number");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the card Number name due to-->>" + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }
        return flag;
    }

    public boolean enterExpirationDate(String date) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            webDriver.switchTo().frame(waitForExpectedElement(iframeForCreditCardExpiration));

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(creditCardExpirationDate));
            element.clear();
            element.click();
            element.sendKeys(date);
            log.info("Successfully set the expiration date");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the the expiration date due to-->>" + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }
        return flag;
    }

    public boolean enterCvvNumber(String cvv) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframe)));
            webDriver.switchTo().frame(waitForExpectedElement(iframeForCreditCardCvv));
            WebElement element = waitForExpectedElement(creditCardCvvNumber);
            element.clear();
            element.click();
            element.sendKeys(cvv);
            log.info("Successfully set the Cvv number");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the Cvv number name due to-->>" + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }
        return flag;
    }

    public boolean enterPostalCodeInCardDetails(String postalCode) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframe)));
            webDriver.switchTo().frame(waitForExpectedElement(iframeForCreditCardPostal));
            WebElement element = waitForExpectedElement(creditCardPostalCode);
            element.clear();
            element.click();
            element.sendKeys(postalCode);
            log.info("Successfully set postal Code");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the postal Code due to-->>" + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }
        return flag;
    }

    public boolean checkShippingCharge() {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Successfully switched to the iframe");

            String tax = waitForExpectedElement(shippingCharge).getText();
            if(tax.equals("0"))
            {
                log.info("Some error in calculating tax it should not be zero");
            }
            else
            {
                flag = true;
            }

        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box stripe"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean setPaymentMethodToStrip() {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(5);
            WebElement radioButton=waitForExpectedElement(paymentMethodStripe);
            if (!radioButton.isSelected())
            {
                radioButton.findElement(By.xpath("../label")).click();
                log.info("Successfully select payment method as stripe ");
                flag=true;
            }
            else
                {
                    log.info("Payment method as stripe has already been selected");
                    flag=true;
                }

        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box stripe"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean creditCardPayment(String cardHolderName, String number, String date, String cvv, String postalCode){
        boolean flag = false;

        try{
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(enterCardholderName(cardHolderName));
            softAssert.assertTrue(enterCardNumber(number));
            softAssert.assertTrue(enterExpirationDate(date));
            softAssert.assertTrue(enterCvvNumber(cvv));
            softAssert.assertTrue(enterPostalCodeInCardDetails(postalCode));
            softAssert.assertAll();
            flag =true;
        }
        catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box Credit Card"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean setPaymentMethodToCreditCard() {
        boolean flag=false;

        try
        {
            int size=webDriver.findElements(By.tagName("iframe")).size();

            for (int i=0;i<size;i++)
            {
                webDriver.switchTo().defaultContent();
                webDriver.switchTo().frame(i);
                if(webDriver.findElements(paymentMethodCreditCard).size()!=0)
                {
                    WebElement radioButton=waitForExpectedElement(paymentMethodCreditCard);
                    if (!radioButton.isSelected())
                    {
                        radioButton.findElement(By.xpath("../label")).click();
                        log.info("Successfully select payment method as Pay by credit card ");
                    }
                    else
                    {
                        log.info("Payment method as Credit Card has already been selected");
                    }
                    flag=true;
                    break;
                }
            }
        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box Credit Card"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;

    }

    public boolean setPaymentMethodToFreeOfCharge()
    {
        boolean flag=false;

        try
        {
            int size=webDriver.findElements(By.tagName("iframe")).size();

            for (int i=0;i<size;i++)
            {
                webDriver.switchTo().defaultContent();
                webDriver.switchTo().frame(i);
                if(webDriver.findElements(paymentMethodFreeOfCharge).size()!=0)
                {
                    WebElement radioButton=waitForExpectedElement(paymentMethodFreeOfCharge);
                    if (!radioButton.isSelected())
                    {
                        radioButton.findElement(By.xpath("../label")).click();
                        log.info("Successfully select payment method as Free Of Charge ");
                    }
                    else
                    {
                        log.info("Payment method as Free Of Charge has already been selected");
                    }
                    flag=true;
                    break;
                }
            }
        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box Free Of Charge"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean fillCreditCardDetails(String number,String date,String cvv, String postalCode)
    {
        boolean flag = false;

        try{
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(enterCardNumber(number));
            softAssert.assertTrue(enterExpirationDate(date));
            softAssert.assertTrue(enterCvvNumber(cvv));
//            softAssert.assertTrue(enterPostalCodeInCardDetails(postalCode));
            softAssert.assertAll();
            flag = true;
        }
        catch (Exception e)
        {
            log.error("Some exception occurred while selecting radio box Credit Card"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }


    public boolean fillCardDetails(String cardNumber,String validity,String cvcNumber)
    {
        boolean flag=false;

       if(enterCardNumberAs(cardNumber))
       {
           if(enterValidityOfCardAs(validity))
           {
               if(enterCVCNumberAs(cvcNumber))
               {
                   log.info("Card number, validity and CVC number set successfully ");
                   flag=true;
               }
                   else
                   {
                       log.info("Successfully set the card number and validity but unable to set cvc number");
                   }
           }
           else {log.info("Successfully set the card number but unable to set the card validity");}
       }
       else
       {
          log.info("Unable to set the card number please check");
       }

        return flag;

    }

    public boolean enterCardNumberAs(String CardNumber)
    {
        boolean flag=false;
        try
        {
                webDriver.switchTo().frame(waitForExpectedElement(iframe));
                log.info("Successfully switched to the iframe");

                webDriver.switchTo().frame(waitForExpectedElement(By.xpath("//iframe[@title=\"Secure card number input frame\"]")));
                log.info("Successfully switched child iframe now moving to set the card number");

                waitClearAndEnterText(cardNumber,CardNumber);
                log.info("Successfully set the card number "+CardNumber+" into the field");

                flag=true;
        }
        catch (Exception e)
        {
            log.error("Unable to set the card number into the field please check-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;


    }


    public boolean enterValidityOfCardAs(String Validity)
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Successfully switched to the iframe");

            webDriver.switchTo().frame(waitForExpectedElement(By.xpath("//iframe[@title=\"Secure expiration date input frame\"]")));
            log.info("Successfully switched child iframe now moving to set the card validity");

            waitClearAndEnterText(cardValidity,Validity);
            log.info("Successfully set the card validity "+Validity+" into the field");
            flag=true;


        }catch (Exception e)
        {
            log.error("Unable to set the card validity into the field please check-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;


    }

    public boolean enterCVCNumberAs(String cvc)
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Successfully switched to the iframe");

            webDriver.switchTo().frame(waitForExpectedElement(By.xpath("//iframe[@title=\"Secure CVC input frame\"]")));
            log.info("Successfully switched child iframe now moving to set the card cvc number");


            waitClearAndEnterText(cvcNumber,cvc);
            log.info("Successfully set the card cvc number "+cvc+" into the field");
            flag=true;

        }catch (Exception e)
        {
            log.error("Unable to set the card cvc number into the field please check-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;


    }

    public boolean clickOnPlaceOrderButton()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on place order button ");
            waitForExpectedElement(placeOrderButton).click();
            log.info("Successfully clicked on place order button");

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on place order button "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean selectPayByCreditCard()
    {
        boolean flag=false;

        try
        {
            //webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on credit card option button ");
//            waitForExpectedElement(payByCreditCard).click();

            wait.until(ExpectedConditions.elementToBeClickable(payByCreditCard)).click();
            log.info("Successfully clicked on credit card option button");

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on credit card option button "+e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }

        return flag;

    }

    public boolean selectPayByCreditCardForDe()
    {
        boolean flag=false;

        try
        {
            //webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on credit card option button ");
//            waitForExpectedElement(payByCreditCard).click();

            wait.until(ExpectedConditions.elementToBeClickable(payByCreditCardForDe)).click();
            log.info("Successfully clicked on credit card option button");

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on credit card option button "+e.getMessage());
            e.printStackTrace();
        }

        return flag;

    }

    public boolean selectPayByCreditCardForCa()
    {
        boolean flag=false;

        try
        {
            log.info("Going to click on credit card option button ");
            wait.until(ExpectedConditions.elementToBeClickable(payByCreditCardForCa)).click();
            log.info("Successfully clicked on credit card option button");

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on credit card option button "+e.getMessage());
            e.printStackTrace();
        }

        return flag;

    }
    public boolean selectPayByCreditCardForNa()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to click on credit card option button ");
            wait.until(ExpectedConditions.elementToBeClickable(payByCreditCardForCa)).click();
            log.info("Successfully clicked on credit card option button");

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on credit card option button "+e.getMessage());
            e.printStackTrace();
        }

        return flag;

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

    public String getAcknowledgementMessage()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to find the acknowledgement message after placing order ");
            Thread.sleep(3000);
            text=waitForExpectedElement(acknowledgementMessage).getText().trim();
            log.info("Successfully found acknowledgement message which is-->"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching the message-->>"+e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }

        return text;
    }

    public String getOrderId()
    {
        String text="";

        try
        {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to find the acknowledgement message after placing order ");
            Thread.sleep(3000);
            text=waitForExpectedElement(orderId).getText().trim();
            log.info("Successfully found acknowledgement message which is-->"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching the message-->>"+e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            webDriver.switchTo().defaultContent();
//        }

        return text;
    }


//    public List<String> getIframes()
//    {
//        List<String>xpaths=webDriver.findElements(By.xpath("//iframe")).stream().map(
//                s->{
//
//                    if(!s.getAttribute("id").equalsIgnoreCase(""))
//                    {
//                        return "//iframe[@id=\""+s.getAttribute("id")+"\"]";
//                    }else if(!s.getAttribute("name").equalsIgnoreCase(""))
//                    {
//                        return "//iframe[@name=\""+s.getAttribute("name")+"\"]";
//                    }
//                    else if(!s.getAttribute("src").equalsIgnoreCase(""))
//                    {
//                        return "//iframe[@src=\""+s.getAttribute("src")+"\"]";
//                    }
//
//                    return "//iframe";
//                }).collect(Collectors.toList());
//
//        return xpaths;
//
//    }
//
//    private boolean setElementInIframe(By element,String data)
//    {
//        List<String> list=getIframes();
//        boolean flag=false;
//        for(String s:list)
//        {
//            webDriver.switchTo().frame(webDriver.findElement(By.xpath(s)));
//
//            if(webDriver.findElements(element).size()!=0)
//            {
//                waitClearAndEnterText(element,data);
//                webDriver.switchTo().defaultContent();
//                flag=true;
//                return true;
//            }
//            if(webDriver.findElements(By.xpath("//iframe")).size()!=0)
//            {
//                setElementInIframe(element,data);
//            }
//
//            webDriver.switchTo().parentFrame();
//
//        }
//        return flag;
//
//    }

}
