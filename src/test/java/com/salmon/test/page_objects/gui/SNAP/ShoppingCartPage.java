package com.salmon.test.page_objects.gui.SNAP;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ShoppingCartPage extends PageObject
{
    private String env= Props.getProp("zendesk.testing.env");
    private  final By iframe=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By checkoutButton=By.xpath("//button[text()=' Checkout ']");
    private static final By nameOfDisplayedProduct=By.xpath("//div[@data-testing-id=\"product-list-item\"] //div[contains(@class,'name')]/a");
    private static final By totalPriceOfProductDisplayed=By.xpath("//div[@data-testing-id=\"product-list-item\"] //div[@data-testing-id=\"total-price\"]");
    private static final By promotionTitle=By.xpath("//div[@class=\"promotion-short-title c-tag c-tag--red\"]");
    private static final By checkoutContinueButton = By.xpath("//button[text()=' Continue checkout ']");
    private static final By tickEmailId = By.xpath("//label[@for=\"no-email-checkbox\"]");
    private static final By salesTax = By.xpath("//dd[@data-testing-id=\"basket-tax\"]");
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartPage.class);
    private static final By valueOfM = By.xpath("(//span[@class=\"product-in-stock c-tag c-tag--grey-200 u-regular\"])[1]");

    public CheckoutProductPage clicksOnCheckoutButton()
    {
        CheckoutProductPage object=null;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            Thread.sleep(5000);

            log.info("Going to click on checkout button on shopping cart page ");

            //we used this to scroll down the scroll bar (comment this code if it affects the another code)

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(0,1000)");

            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(checkoutButton)).click().build().perform();
            log.info("Successfully clicked on checkout button");
            //Thread.sleep(4000);

            object=new CheckoutProductPage();

        }catch (Exception e)
        {
            log.info("Some exception occurred while clicking on checkout button please check-->>"+e.getMessage());
            e.printStackTrace();
        }
//        finally {
////            webDriver.switchTo().defaultContent();
////        }

        return object;
    }

    public String getSalesTaxOnCartPage()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            text=getTextFor(salesTax).trim();
            log.info("Successfully fetched the Acknowledgement Message-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Acknowledgement Message-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public String getValueOfM()
    {
        IsPageLoaded.waitAllRequest();
        String text="";
        try
        {
            Thread.sleep(3000);
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            text=getTextFor(valueOfM).trim();
            log.info("Successfully fetched the Acknowledgement Message-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Acknowledgement Message-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public boolean tickOnNoEmailId(){
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on continue with checkout button");
            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(tickEmailId)).click().build().perform();
            log.info("Successfully clicked on continue with checkout button");
            flag=true;

        }catch (Exception e)
        {
            log.info("Some exception occurred while clicking on checkout button please check-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }


    public boolean clicksOnContinueWithCheckoutButton()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on continue with checkout button");
            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(checkoutContinueButton)).click().build().perform();
            log.info("Successfully clicked on continue with checkout button");
            flag=true;

        }catch (Exception e)
        {
            log.info("Some exception occurred while clicking on checkout button please check-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }


    public boolean getNameOfTheDisplayedProduct()
    {
        String text="";
        boolean flag = false;

        try
        {
            log.info("Going to fetch the name of the product which displays on checkout page");
            text=waitForExpectedElement(nameOfDisplayedProduct).getText().split("\n")[0].trim();
            log.info("Successfully fetched the name of the product displayed which is-->>"+text);
            flag = true;
        }
        catch (Exception e)
        {
            log.error("Some exception occurred while fetching the displayed item name-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public float getPriceOfTheDisplayedProduct()
    {
        float price=-1;

        if(isPromotionTitleDislpayed())
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to fetch the name of the product which displays on checkout page");
            String text=waitForExpectedElement(totalPriceOfProductDisplayed).getText().split("\n")[0].trim();
            price=Float.parseFloat(text.replaceAll("[^\\d.]", ""));
            log.info("Successfully fetched the price of the product displayed which is-->>"+text);
        }
        catch (Exception e)
        {
            log.error("Some exception occurred while fetching the displayed item name-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return price;
    }

    private boolean isPromotionTitleDislpayed()
    {
        boolean flag=false;
        FluentWait<WebDriver> fluentWait=new FluentWait<>(webDriver);
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            fluentWait.withTimeout(Duration.ofSeconds(15));
            fluentWait.pollingEvery(Duration.ofSeconds(1));
            fluentWait.ignoring(ElementNotVisibleException.class, TimeoutException.class);

            log.info("Waiting for promotional message to come ");
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(promotionTitle));
            String text=waitForExpectedElement(promotionTitle).getText().trim();
            if(text!=null && text!="")
            {
                log.info("Text from promotional message is "+text);
                flag=true;
            }

        }catch (Exception e)
        {
            log.error("Some exception occurred please check if Intershop is down unable to get the promotional message please check-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }
}
