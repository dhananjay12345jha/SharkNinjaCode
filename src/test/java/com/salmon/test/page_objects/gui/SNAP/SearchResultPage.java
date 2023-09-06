package com.salmon.test.page_objects.gui.SNAP;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import gherkin.Func;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SearchResultPage extends PageObject
{
    private String env= Props.getProp("zendesk.testing.env");
    private  final By iframe=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By numberOfDataRowsDisplay=By.xpath("//div[@class=\"product-tile-list row\"]");

    //private static final By productNumber=By.xpath("//ish-product-id/div/span");

    private static final By productNumber=By.xpath("//ish-product-row/div/div[2]/div/div[1]/a[2]");
    private static final By addToCartButton=By.xpath("//div[@class=\"product-list-actions-container\"] //button");

    private FluentWait<WebDriver> fluentWait=new FluentWait<WebDriver>(webDriver);

    private static final Logger log = LoggerFactory.getLogger(SearchResultPage.class);

    public boolean clickOnAddToCartWhereProductIdIs(String productId)
    {
        boolean flag=false;
        waitForPage();
        IsPageLoaded.waitAllRequest();
        try
        {
            Thread.sleep(3000);
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            List<WebElement> container=visibilityOfAllElementsLocatedBy(numberOfDataRowsDisplay);

            for(int i=0;i<container.size();i++)
            {

                if(container.get(i).findElement(productNumber).getText().trim().contains(productId)  &&
                        container.get(i).findElement(addToCartButton).getText().trim().contains("Add to Cart")) {


                    container.get(i).findElement(addToCartButton).click();
                    log.info("Successfully clicked on add to cart button for product "+productId);
                    i++;
                    log.info("Clicked on add to cart button which is at position-->>"+i);
                    flag=true;
                    break;
                }
            }
        }catch (Exception e)
        {
            log.info("Some exception occurred while choosing product \""+productId+"\" and clicking on add to cart button-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        if(flag)
        {
            log.info("Successfully add product into the cart ");
            return true;
        }
        else
            {

                log.info("Unable to add product into the cart please there might be product unavailable");
                return false;
            }
    }

}
