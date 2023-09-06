package com.salmon.test.page_objects.gui.MicrositeSharkUS;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MSHomePage extends PageObject {

    private static final By pdpPageHeading = By.xpath("//div[@class=\"enhanced-image-heading left\"]/h1");
    private static final By addToCartButton = By.xpath("//div[@class=\"col-md-3 col-sm-12\"]//div[@class=\"add-to-cart\"]//button[@data-testing-id=\"addToCartButton\"]");
    private static final By miniBagIconMobile = By.xpath("//sn-mini-basket[@view=\"small\"]//sn-mini-basket[@class=\"d-lg-none\"]");
    private static final By miniBagIcon = By.xpath("//a[@aria-label=\"basket icon\"]");
    private static final By closeButton = By.xpath("//div[@aria-label=\"mini cart close button\"]");
    private static final By selectedProductIcon = By.xpath("//a[@queryparamshandling=\"merge\" and @class=\"pod-anchor variant-details product-variant-clickable selected\"]");
    private static final By promoText = By.xpath("//div[@class=\"promo-text\"]");
    private static final By ProductText = By.xpath("//h1[@class=\"pdp-title\"]/span");
    private static final By highlightedProductTileMobileOne = By.xpath("(//sn-product-bundle-two-or-more-variants)[1]//div[@class=\"d-flex flex-nowrap\"][2]/div[@class=\"product-bundle-part-title\"][1]");
    private static final By highlightedProductTileOne = By.xpath("(//sn-product-bundle-variants-table-header-col)[2]//div[@class=\"d-flex flex-nowrap\"][2]/div[1]");
    private static final By productTiles = By.xpath("//sn-product-variant-display//a");
    private static final By clickSignInBtn = By.name("login");
    private static final By highlightedProductTileMobileSecond = By.xpath("(//sn-product-bundle-two-or-more-variants)[1]//div[@class=\"product-bundle-part-title\"][2]");
    private static final By highlightedProductTileSecond = By.xpath("(//sn-product-bundle-variants-table-header-col)[2]//div[@class=\"product-bundle-part-title\"][2]");

    private static final Logger log = LoggerFactory.getLogger(MSHomePage.class);

    public By toVerifyAddToCartButton() {
        return addToCartButton;
    }

    public String getPDPPageHeading() {
        return waitForExpectedElement(pdpPageHeading).getText();
    }

    public List<WebElement> getProductTiles() {
        List<WebElement> list = null;
        try {
            list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTiles));
            log.info("Product tiles have been found size is --->>" + list.size());
        } catch (Exception e) {
            log.error("Unable to find product tiles list please check logs-->" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return list;
    }


    public boolean clickSignInBtn() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = getWebDriver().findElement(clickSignInBtn);
            new Actions(getWebDriver()).moveToElement(element).click().build().perform();

            flag = true;
            log.info("Successfully Sign In");
        } catch (Exception e) {
            log.error("Unable to click on SignIn btn for sharkCA thus returing object as NULL for MyAccountPageSNCA-->"
                    + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public boolean clickMiniBagIcon() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            if (UrlBuilder.isMobile()) {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(miniBagIconMobile));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.visibilityOf(element)).click();
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
                new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(miniBagIcon))).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(miniBagIcon)).click();
            }

            flag = true;
            log.info("Successfully clicked on the cart icon button ");

        } catch (Exception e) {
            log.error("Unable to click on cart icon due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean addProductToCart() {
        boolean flag = false;
        int n = 0;
        while (n < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
                clickByElementByQueryJSExecutor(addToCartButton);
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

    public boolean verifySelectedProduct(String text) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = waitForExpectedElement(selectedProductIcon);
            wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(promoText)));
            String promocodeText = getTextFor(promoText).trim();
            if (element.isDisplayed() && promocodeText.contains(text)) {
                flag = true;
                log.info("Product is successfully selected");
            }
        } catch (Exception e) {
            log.error("Some exception occurred while Verifying Selected Product Which is-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnProductButton(int position) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        WebElement element;
        JavascriptExecutor j = (JavascriptExecutor) getWebDriver();
        if (--position < 0) {
            log.error("Invalid index/position -->>" + position);
            return false;
        }
        element = getProductTiles().get(position);
        j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
        try {
            Thread.sleep(1000);
            element.click();
            flag = true;
        } catch (ElementClickInterceptedException e1) {
            log.error("Element click intercepted is there trying one more time-->>" + ExceptionUtils.getStackTrace(e1));
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("Some exception is occured unbale to click on the product tile located at position-->>" + (position + 1) + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyNameOfProduct() {
        boolean flag = false;
        waitForPage();
        String highlightedText = "";
        try {

            if (UrlBuilder.isMobile()) {
                highlightedText = wait.until(ExpectedConditions.presenceOfElementLocated(highlightedProductTileMobileSecond)).getText();
            } else {
                highlightedText = wait.until(ExpectedConditions.presenceOfElementLocated(highlightedProductTileSecond)).getText();
            }
            String selectedProductText = getTextFor(ProductText);

            if (selectedProductText.contains(highlightedText)) {
                flag = true;
                log.info("Product is successfully selected");
            }
        } catch (Exception e) {
            log.error("Some exception occurred while Verifying Selected Product Which is-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyProductText() {
        boolean flag = false;
        String highlightedText = "";

        try {
            waitForPage();

            if (UrlBuilder.isMobile()) {
                highlightedText = wait.until(ExpectedConditions.presenceOfElementLocated(highlightedProductTileMobileOne)).getText();
            } else {
                highlightedText = wait.until(ExpectedConditions.presenceOfElementLocated(highlightedProductTileOne)).getText();
            }
            String selectedProductText = getTextFor(ProductText);

            if (selectedProductText.contains(highlightedText)) {
                flag = true;
                log.info("Product is successfully selected");
            }
        } catch (Exception e) {
            log.error("Some exception occurred while Verifying Selected Product Which is-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

}
