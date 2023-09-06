package com.salmon.test.page_objects.gui.SNAP;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CreateReplacementForProductPage extends PageObject
{
    private static final Logger log = LoggerFactory.getLogger(CreateReplacementForProductPage.class);

    private String env= Props.getProp("zendesk.testing.env");
    private  final By iframe= By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    //private static final By proofOfPurchasePending=By.id("pending");
    private static final By proofOfPurchasePending=By.xpath("//input[@id='pending']");
    private static final By proofOfPurchaseVerified=By.id("verified");
    private static final By createButton=By.xpath("//button[@name=\"Create\"]");
    private static final By addToCartButton=By.xpath("//button[@data-testing-id=\"addToCartButton\"]");
    private static final By listOfItemsShowingUnderReplacement=By.xpath("//snap-product-links-item/div/div");
    private static final By faultReasonDropBox = By.xpath("//button[@id=\"dropdownForm1\"]");
    private static final By faultReasonOption = By.xpath("//input[@id=\"4_STEPS_BUTTONHOLE_NOT_EVEN_ON_BOTH_SIDE\"]");
    private static final By sharkProductListForCanada = By.xpath("(//a[@class=\"mt-2 c-btn c-btn--primary\"])[1]");
    private static final By handheldVaccumeLink = By.xpath("(//span[@class=\"filter-item-name\"])[4]");
    private static final By sharkProducts = By.xpath("(//a[text()='Shark Products'])");
    private static final By selectReplacementButton = By.xpath("//button[text()=' Select Replacement ']");
    private static final By confirmReplacementButton = By.xpath("//button[@class=\"c-btn c-btn--primary\"]");
    private static final By confirmReplacementButton1 = By.xpath("(//button[@class=\"c-btn c-btn--primary\"])[3]");
    private static final By createButtonBelow = By.xpath("//button[text()=' Create ']");

    public String textOfItemSelectedUnderReplacement="";

    public boolean selectProofOfPurchaseAsPending()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click select proof of purchase as \"Pending\"");
            if(!waitForExpectedElement(proofOfPurchasePending).isSelected())
            {
                waitForExpectedElement(proofOfPurchasePending).findElement(By.xpath("../label")).click();
                log.info("Successfully select proof of purchase as \"Pending\"");
                flag=true;
            }

            if(waitForExpectedElement(proofOfPurchasePending).isSelected())
            {
                flag=true;
            }

        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting proof of purchase as \"Pending\""+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean selectFaultReason()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to click a valid Fault Reason");

            wait.until(ExpectedConditions.presenceOfElementLocated(faultReasonDropBox)).click();
            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(faultReasonOption)).click().build().perform();

            //Select sl = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(faultReasonDropBox)));
            //sl.selectByValue("");
            log.info("Successfully selected a valid fault reason");
            flag = true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on a valid Fault Reason"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean selectProductOfHandheldVaccum()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to click on a handheld Vaccume Link");

            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(handheldVaccumeLink)).click().build().perform();
            log.info("Successfully click on a handheld Vaccume Link");
            flag = true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on a handheld Vaccume Link"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean selectSharkProductsForCanada()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to click on a list of shark products");

            wait.until(ExpectedConditions.presenceOfElementLocated(sharkProductListForCanada)).click();
            log.info("Successfully shark Product List");
            flag = true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on shark list button"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean selectSharkProducts()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to click on a list of shark products");

            wait.until(ExpectedConditions.presenceOfElementLocated(sharkProducts)).click();
            log.info("Successfully shark Product List");
            flag = true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on shark list button"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean selectProofOfPurchaseAsVerified()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click select proof of purchase as \"Verified\"");
            if(!waitForExpectedElement(proofOfPurchaseVerified).isSelected())
            {
                waitForExpectedElement(proofOfPurchaseVerified).findElement(By.xpath("../label")).click();
                log.info("Successfully select proof of purchase as \"Verified\"");
                flag=true;
            }

            if(waitForExpectedElement(proofOfPurchaseVerified).isSelected())
            {
                flag=true;
            }

        }catch (Exception e)
        {
            log.error("Some exception occurred while selecting proof of purchase as \"Verified\""+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean clickCreate()
    {
        boolean flag=false;

        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on create button");
            clickByElement(createButton);
            log.info("Successfully clicked on create button");
            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on Create Button "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean selectReplacementOption(){
        boolean flag = false;

        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            wait.until(ExpectedConditions.elementToBeClickable(selectReplacementButton)).click();
            log.info("Going to find number of items shown under Replacements ");

            wait.until(ExpectedConditions.elementToBeClickable(confirmReplacementButton1)).click();

            flag = true;
            log.info("Successfully click on select replacement option");
        } catch (Exception e) {
            log.info("Some exception occurred when selecting first item shown under replacement " + e.getMessage());
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;

    }

    public boolean selectFirstItemShownUnderReplacement()
    {
        boolean flag=false;

        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            wait.until(ExpectedConditions.elementToBeClickable(selectReplacementButton)).click();

            log.info("Going to find number of items shown under Replacements ");

            wait.until(ExpectedConditions.elementToBeClickable(confirmReplacementButton)).click();
//            WebElement element= getWebDriver().findElement(confirmReplacementButton);
//            element.click();
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred when selecting first item shown under replacement "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean clickOnBelowCreateButton()
    {

        boolean flag=false;

        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(0,1000)");

            wait.until(ExpectedConditions.elementToBeClickable(createButtonBelow)).click();
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred when selecting first item shown under replacement "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean clickOnAddToCartButton()
    {
        boolean flag=true;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on add to cart");
            clickByElement(addToCartButton);
//            visibilityOfAllElementsLocatedBy(addToCartButton);
            log.info("Successfully clicked on Add to cart button");
            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while adding item to cart-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean clickOnAddToCartAtFirstPosition(int position)
    {
        boolean flag=true;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on add to cart options button positioned at "+position);
            List<WebElement> list=visibilityOfAllElementsLocatedBy(addToCartButton).stream().collect(Collectors.toList());

            if((position-1)>=0 && (position-1)<=list.size())
            {
                list.get(position-1).click();
                log.info("Successfully clicked on add to cart options button positioned at "+position);
                flag=true;
            }
            else
            {
                log.info("Unable click on add to cart options button postion at "+position+" please provide correct position");
            }


        }catch (Exception e)
        {
            log.error("Some exception occurred while adding item to cart-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

}
