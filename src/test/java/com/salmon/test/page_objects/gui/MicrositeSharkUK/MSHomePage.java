package com.salmon.test.page_objects.gui.MicrositeSharkUK;

import com.salmon.test.framework.PageObject;

import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import javax.swing.*;
import javax.validation.constraints.AssertTrue;
import java.util.List;


public class MSHomePage extends PageObject {

    private static final Logger log = LoggerFactory.getLogger(MSHomePage.class);
    private final By productTiles = By.xpath("//a[starts-with(@class,\"linked-item product-title js-linked-thumbnail\")]");
    private final By productTileFirst = By.xpath("//a[starts-with(@class,\"linked-item product-title js-linked-thumbnail\")][1]");
    private final By productTileThumbnailFirst = By.xpath("//a[@id='prod-section6-currentProd'][1]");
    private final By nextArrowThumbnail = By.xpath("//button[@class='slick-next slick-arrow']");
    private final By productTileLast = By.xpath("(//a[starts-with(@class,\"linked-item product-title js-linked-thumbnail\")])[last()]");
    private final By productTileThumbnailLast = By.xpath("(//a[@id='prod-section6-currentProd'])[last()]");
    private final By selectedProductName = By.xpath("(//h3[@class='product-name'])[2]");
    private final By buyButton = By.xpath("//button[@type='submit'][@name='addProduct']");
    private final By acceptCookiesBtn = By.cssSelector("button#onetrust-accept-btn-handler");
    // Header Links
    private WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
    private List<WebElement> Tileelements;

    public Boolean verifyProductTilesShown() {
        boolean flag = false;
        try {
            flag = this.verifyProductTilesCount();
            log.info("Successfully verified product tiles");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying product tiles-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProductTilesCount() {
        boolean flag = false;
        int count = 0;
        Tileelements = webDriver.findElements(productTiles);
        if (Tileelements.size() > 0) {
            flag = true;
        }
        return flag;
    }

    public List<WebElement> getProductTiles() {
        List<WebElement> elements = webDriver.findElements(productTiles);
        return elements;
    }

    public Boolean addProductToCart() {
        boolean flag = false;
        int n = 0;
        while (n < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(buyButton));
                clickByElementByQueryJSExecutor(buyButton);
                log.info("Successfully clicked on Buy Now Button On HomePage and added product to cart");
                flag = true;
                break;
            } catch (StaleElementReferenceException e1) {
                log.error("Stale Element reference exception is there going to try for-->" + (n + 1) + " time");
                n++;
            } catch (Exception e) {
                log.error("Some exception occurred while clicking Buy Now Button on HomePage Which is-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean selectTile() {
        boolean flag = false;
        List<WebElement> elements = getProductTiles();
        for (int i = elements.size() - 1; i >= 0; i--) {
            WebElement element = elements.get(i);
            if (!((element.getText().equalsIgnoreCase("OUT OF STOCK")) || (element.getText().equalsIgnoreCase("Nicht verfügbar")))) {
                while (!element.isDisplayed()) {
                    waitForExpectedElement(nextArrowThumbnail).click();
                    IsPageLoaded.waitAllRequest();
                }
                Actions action = new Actions(webDriver);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                action.moveToElement(element);
                try {
                    element.click();
                    log.info("Product Tile has been clicked");
                    flag = true;
                    break;
                } catch (ElementClickInterceptedException e) {
                    log.info("Product Tile is not clicked");
                }
                IsPageLoaded.waitAllRequest();
            }
        }
        return flag;
    }

    public void verifyClickandNavidate() throws InterruptedException {
        String sku, expectedProductInformation, actualProductInformation;
        int count = 0;
        boolean flag = false;
        By elementLocator;
        for (WebElement element : Tileelements) {
            System.out.println(element.getText());
            if (!((element.getText().equalsIgnoreCase("OUT OF STOCK")) || (element.getText().equalsIgnoreCase("Nicht verfügbar")))) {
                if (!element.isDisplayed())
                    waitForExpectedElement(nextArrowThumbnail).click();
                Actions action = new Actions(webDriver);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                action.moveToElement(element);
                sku = element.getAttribute("data-attr-sku");
                try {
                    element.click();
                } catch (ElementClickInterceptedException e) {
                    Thread.sleep(10000);
                    element.click();
                }

                IsPageLoaded.waitAllRequest();
                Assert.assertTrue(webDriver.getCurrentUrl().contains(sku), "Actual= " + webDriver.getCurrentUrl() + "    " + " Expected =" + sku);
            }
        }
    }
}
