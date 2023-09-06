package com.salmon.test.page_objects.gui;


import java.time.Duration;
import java.util.List;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.CartPageSNCA;
import cucumber.api.java.en_old.Ac;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchPage extends PageObject {

    //private static final By headerSearchTxtbx = By.xpath("//input[@id='SearchBox_Header']");

    private static final By headerSearchTxtbx = By.cssSelector(".search-login-container input:first-child");
    //private static final By submitSearchBtn = By.xpath("//button[@name='search']");
    private static final By submitSearchBtn = By.cssSelector(".search-container button[type=\"submit\"]");
    private static final By collectionProductLinks = By.xpath("//a[contains(@class, 'product-title')]");
    //	private static final By selectProduct = By.xpath("//div[@class='search-product-list']//div[@data-tracking-product-sku='NV801UKT']");
    private By selectProductDynamicUK;
    private By selectProductDynamic;
    private static final By searchResultPage = By.id("product-search-result");
    private static final By validMessageforInvalidInput = By.xpath("//div[@class=\"no-search-result\"]//h1");
    private static final By searchHeading = By.xpath("//h1");
    public static String SearchProduct;
    private static final Logger log = LoggerFactory.getLogger(SearchPage.class);

    public String getSearchPageHeading() {
        IsPageLoaded.waitAllRequest();
        String heading = null;
        try {
            heading = waitForExpectedElement(searchHeading).getText();
            log.info("Successfully get search page heading ");
        } catch (Exception e) {
            log.error("Some exception arised while getting search page heading-->>" + ExceptionUtils.getStackTrace(e));
        }
        return heading;
    }

    public boolean isSearchResultPageVisible() {
        log.info("Going to find Search Result Page is visible or not");
        boolean flag = false;
        int count = 0;
        do {
            if (getWebDriver().findElements(searchResultPage).size() > 0) {
                log.info("Search Page is visible ");
                flag = true;
                break;
            }
            count++;
        } while (count < 4);
        if (!flag) {
            log.info("Search result page is not visible");
            //return false;
        }
        return flag;
    }

    public boolean clickOnSearchButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(submitSearchBtn));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            flag = true;
            log.info("Successfully clicked on search button in search bar ");
        } catch (Exception e) {
            log.error("Some exception arised while clicking on search button after enter the product in search bar-->>" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }


    public boolean enterProductInSearchBar(String product) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(headerSearchTxtbx));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            element.click();
            element.clear();
            element.sendKeys(product);
            Thread.sleep(3000);
            flag = true;
            log.info("Successfully set text-->>" + product + " in search bar ");
        } catch (Exception e) {
            log.error("Some exception arised while enter the product in search bar-->" + ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }


    public boolean valid_error_message() {
        Boolean flag = false;
        try {
            flag = waitForExpectedElement(validMessageforInvalidInput).getText().contains(Props.getProp("message.invalid.input"));
        } catch (Exception e) {
            log.error("Some exception occured while validating the error message" + e.getMessage());
        }
        return flag;
    }

    //Fetch all the search results links Title
    public List<WebElement> collectionProductLinks() {
        return webDriver.findElements(collectionProductLinks);
    }

    public void selectProductFromSearchResults(String productSpecificSKU) {
        if (Props.getProp("profile").contains("Uk")) {
            IsPageLoaded.waitAllRequest();
            if (isSearchResultPageVisible()) {
                selectProductDynamicUK = By.xpath("//div[@class='search-product-list']//div[@data-tracking-product-sku='" + productSpecificSKU + "']/div[4]/a");
                log.info("Dyanmic xpath of the product has been created which is--->>" + selectProductDynamicUK);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamicUK));
                SearchProduct = element.getText().trim();
                log.info("Successfully fetched text of product on which going to click-->>" + SearchProduct);
                wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
                try {
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamicUK));
                    new Actions((getWebDriver())).moveToElement(element).build().perform();
                    element.click();
                    log.info("With Dynamic xpath product has been found going to click on the product");
                } catch (Exception e) {
                    log.error("Unable to click product after search--->>" + ExceptionUtils.getStackTrace(e));
                    e.printStackTrace();
                }
            }

        } else {
            IsPageLoaded.waitAllRequest();
            if (isSearchResultPageVisible()) {
                selectProductDynamic = By.xpath("//div[@class='search-product-list']//div[@data-tracking-product-sku='" + productSpecificSKU + "']/div[3]/a");
                log.info("Dyanmic xpath of the product has been created which is--->>" + selectProductDynamic);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamic));
                SearchProduct = element.getText().trim();
                log.info("Successfully fetched text of product on which going to click-->>" + SearchProduct);
                wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
                try {
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamic));
                    new Actions((getWebDriver())).moveToElement(element).build().perform();
                    element.click();
                    log.info("With Dynamic xpath product has been found going to click on the product");
                } catch (Exception e) {
                    log.error("Unable to click product after search--->>" + ExceptionUtils.getStackTrace(e));
                    e.printStackTrace();
                }
            }

        }
    }
}
