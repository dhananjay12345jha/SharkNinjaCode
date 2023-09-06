package com.salmon.test.page_objects.gui.SNAP;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.composable.Stream;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SNAPProductRegistrationPage extends PageObject
{

    private static final Logger log = LoggerFactory.getLogger(AgentProfilePage.class);

    private String env= Props.getProp("zendesk.testing.env");
    private final By iframe= By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By addProductRegistrationButton=By.xpath("//button[text()=\" Add Product Registration \"]");
    private static final By textRegistrationAddedSuccessfully=By.xpath("//div[@id=\"toast-container\"]/div/div");
    private static final By orderButtonList=By.cssSelector("div.list-body button");

    private static final By orderOptionDropdownItems=By.xpath("//div[@class=\"menu-container is-opened\"]/ul/li/a");
    private static final By warningPopUpContinueButton=By.xpath("//button[@data-testing-id=\"confirm\"]"); //---if item is already added in cart--//
    private FluentWait<WebDriver> fluentWait=new FluentWait<>(webDriver);


    public SNAPNewProductRegistrationPage clickAddProductRegistration()
    {
        SNAPNewProductRegistrationPage object=null;

        try {

            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Moving on to click button \"Add Registration Button\" ");

            waitForExpectedElement(addProductRegistrationButton).click();
            //new Actions(webDriver).moveToElement(waitForExpectedElement(addProductRegistrationButton)).click().build().perform();

            log.info("Successfully clicked on button \"Add Registration Button\" ");
            object=new SNAPNewProductRegistrationPage();


        }catch (Exception e)
        {
            log.info("Some Exception occurred while clicking on \"Add Registration Button\"-->>"+e.getMessage());

        }finally {
            webDriver.switchTo().defaultContent();
        }

        return object;
    }

    public String getTextRegistrationAddedSuccessfully()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to fetch product registration successful message");
            //WebElement element=waitForExpectedElement(textRegistrationAddedSuccessfully);
            text=getTextFor(textRegistrationAddedSuccessfully);

            log.info("Successfully fetched registration confirmation message which is-->>"+text);

        }catch (Exception e)
        {
            log.info("Some exception occurred while fetching the message which is-->>"+e.getMessage());
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public boolean clickOnOrderOptionsButtonPositionAt(int position)
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on order options button positioned at "+position);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(0,1000)");
            List<WebElement> list=visibilityOfAllElementsLocatedBy(orderButtonList).stream().collect(Collectors.toList());

            if((position-1)>=0 && (position-1)<=list.size())
            {
                list.get(position-1).click();
                log.info("Successfully clicked on order option button positioned at "+position);
                flag=true;
            }
            else
                {
                    log.info("Unable to click on Order Option button postion at "+position+" please provide correct position");
                }

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on Order Option button positioned at "+position+"-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public CreateReplacementForProductPage selectOptionShownUnderOrderOptionButton(String optionToSelect)
    {
        CreateReplacementForProductPage obj=null;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            Thread.sleep(3000);

            log.info("Going to select the option "+optionToSelect+" from the order option drop down");
            visibilityOfAllElementsLocatedBy(orderOptionDropdownItems).
                    stream().filter(s->s.getText().toLowerCase().contains(optionToSelect.toLowerCase())).
                    findFirst().get().click();
            log.info("Successfully selected the option "+optionToSelect);
            obj=new CreateReplacementForProductPage();

            try {
                   fluentWait.withTimeout(Duration.ofSeconds(3));
                   fluentWait.pollingEvery(Duration.ofSeconds(1));
                   fluentWait.ignoring(TimeoutException.class, ElementNotFoundException.class);
                   fluentWait.until(driver -> {
                       if(driver.findElements(warningPopUpContinueButton).size()!=0)
                       {
                           driver.findElement(warningPopUpContinueButton).click();
                           log.info("Warning pop up is there i.e item already added in cart, successfully clicked on continue button");
                           return true;
                       }
                       return false;
                   });
            }catch (TimeoutException e)
            {
                log.info("Cart is empty moving towards create replacement for product page");
            }catch (Exception e)
            {
                log.info(e.getMessage());
            }
        }
        catch (Exception e)
        {
            log.error("Some exception occurred while selecting the option "+optionToSelect+"-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {

            webDriver.switchTo().defaultContent();
        }

        return obj;
    }
}
