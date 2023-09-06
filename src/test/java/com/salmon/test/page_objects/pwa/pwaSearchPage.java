package com.salmon.test.page_objects.pwa;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.gui.ForgotPasswordPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class pwaSearchPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(ForgotPasswordPage.class);
    private final By SearchButton=By.xpath("//div[@class=\"ng-fa-icon header-icon header-search-icon\"]");
    private final By searchTxtbx=By.xpath("//input[@class=\"form-control searchTerm\"]");
    private final By searchButton=By.xpath("//button[@name=\"search\"]");
    private final By selectProductDynamic=By.xpath("//div[@class=\"product-title\"]");
    public  static String SearchProduct;


    public void clickonSearchButton() {
        try {
            waitForExpectedElement(SearchButton).click();
            log.info("Successfully clicked search Button");
        }
        catch (Exception e) {
            log.info("Some exception occurred while clicking search Button-->>"+e.getMessage());
        }

    }
    public WebElement searchTxtbx() {
        return webDriver.findElement(searchTxtbx);
    }
    public WebElement searchButton() {
        return webDriver.findElement(searchButton);
    }
    //This need to be updated when we will have more than 1 product as part of search results
    public void selectProductFromSearchResults(String productSpecificSKU) throws InterruptedException {
       // selectProductDynamic = By.xpath("//div[@class='search-product-list']//div[@data-tracking-product-sku='"+productSpecificSKU+"']");
        //SearchProduct=webDriver.findElement(By.xpath("//div[@class='search-product-list']//div[@data-tracking-product-sku='"+productSpecificSKU+"']")).getText();
        waitForExpectedElement(selectProductDynamic).click();

    }
}
