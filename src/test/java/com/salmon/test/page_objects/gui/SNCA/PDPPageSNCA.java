package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PDPPageSNCA extends PageObject {
    private static final By productTitle = By.xpath("//span[@itemprop=\"name\"]");
    private static final By productModel = By.xpath("//span[@itemprop=\"sku\"]");
    private final By mobileEmptyCartMessage = By.xpath("//ul[@class='list-unstyled alert alert-danger']");

    private final By rateAndReviewButton = By.xpath("//*[@id=\"ratings-summary\"]/div");
    private final By reviewText = By.xpath("//*[@class=\"bv-section-summary\"]//p");
    private static final By LeftTogglebtn = By.xpath("//button[starts-with(@class,'hamburger-menu')]");
    private static final By mobileHeaderSignInLink = By.cssSelector("button[class='tab-btn-login']");

    private static final By addToCartButton = By.cssSelector("button[type='submit'][class$='btn-block']");
    private static final By stickAddToCartButton = By.cssSelector("button[type='submit'][class$='stick-cart--btn']");
    private static final By quantityOfProduct = By.xpath("//input[@data-testing-id=\"quantity\"]");
    private static final By priceOfProduct = By.xpath("//ish-product-price[@class=\"d-flex\"]/div");
    private static final By isProductOutOfStock = By.cssSelector("span.product-out-of-stock");
    private static final By cartIcon = By.xpath("//sn-mini-basket[@data-testing-id=\"mini-basket-desktop\"]//a[contains(@href,'basket')]");

    private static final By cartItemCount = By.xpath("//sn-mini-basket[@data-testing-id=\"mini-basket-desktop\"]//a[contains(@href,'basket')]/span");
    private static final By checkOutButtonOnMiniBasket = By.cssSelector("sn-mini-basket[data-testing-id=\"mini-basket-desktop\"] button[id=checkoutBtn]");
    private  By selectProductDynamic;
    public static String SearchProduct;
    private static final By componentsPDP = By.xpath("//button[@class='accordion']");
    private static final By cartOverlayQTYselector = By.xpath("//ish-select//select");

    private final By quantityInvalidError = By.xpath("//*[@class='ng-fa-icon form-control-feedback']/following-sibling::small");

    private static final Logger log = LoggerFactory.getLogger(PDPPageSNCA.class);

    public String getProductTitle() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = getTextFor(productTitle);
            log.info("Successfully fetched the product title which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the product title due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean isProductOutOfStockPresent() {
        return isElementPresent(isProductOutOfStock);
    }

    public boolean isAddToCartButtonPresent() {
        return isElementPresent(addToCartButton);
    }

    public boolean validate_pdp_page_is_there() {
        IsPageLoaded.waitAllRequest();
        return isElementPresent(addToCartButton) || isElementPresent(isProductOutOfStock);
    }

    public boolean clickOnAddToCart() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(30));
        fluentWait.pollingEvery(Duration.ofSeconds(3));
        fluentWait.ignoring(Exception.class);
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        WebElement element;
        if (webDriver.findElements(stickAddToCartButton).size() > 0) {
            log.info("Stick Add to Cart button has been found going to click on it");
            try {
                fluentWait.until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        if (webDriver.findElement(stickAddToCartButton).isDisplayed()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                fluentWait.until(ExpectedConditions.elementToBeClickable(stickAddToCartButton)).click();
                log.info("Successfully clicked on Stick Add to Cart Button ");
                fluentWait.until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        if (driver.findElement(stickAddToCartButton).getAttribute("disabled") != null) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                });
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on Stick Add to cart button-->>" + e.getMessage());
                log.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            }
        }
        if (!flag) {
            do {
                try {
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        log.info("Successfully clicked on the add to cart button ");
                        flag = true;
                        break;
                    }
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,400);");
                    count++;
                } catch (ElementClickInterceptedException e2) {
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,400);");
                    count++;
                } catch (Exception e3) {
                    log.error("Unable to click on add to cart button due to-->>" + e3.getMessage());
                    log.error(ExceptionUtils.getFullStackTrace(e3));
                    e3.printStackTrace();
                    break;
                }
            } while (count < 40);
        }

        // Only for mobile part because of minicart icon bug
        if(UrlBuilder.isMobile())
            return flag;

        count = 0;
        if (flag) {
            while (count < 5) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutButtonOnMiniBasket));
                    log.info("Mini Basket Checkout button is showing ");
                    flag = true;
                    break;
                } catch (TimeoutException e) {
                    log.error("Mini Basket Checkout button is not showing ");
                    log.error(ExceptionUtils.getStackTrace(e));
                    flag = false;
                    count++;
                } catch (Exception e1) {
                    log.error("Mini Basket Checkout button is not showing ");
                    log.error(ExceptionUtils.getFullStackTrace(e1));
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public CartPageSNCA clickOnCartIcon() {
        IsPageLoaded.waitAllRequest();
        CartPageSNCA obj = null;
        try {
            new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(cartIcon)))
                    .click().build().perform();
            log.info("Successfully clicked on the cart icon button ");
            obj = new CartPageSNCA();
        } catch (Exception e) {
            log.error("Unable to click on cart icon due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public CartPageSNCA clickOnCartIconForCrossCart() {
        IsPageLoaded.waitAllRequest();
        CartPageSNCA obj = null;
        try {
            new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(cartIcon)))
                    .click().build().perform();
            log.info("Successfully clicked on the cart icon button ");
               obj = new CartPageSNCA();
        } catch (Exception e) {
            log.error("Unable to click on cart icon due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return obj;

    }
    public int getAddedItemCountShownOverCartIcon() {
        IsPageLoaded.waitAllRequest();
        int count = -1;
        try {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
            count = Integer.parseInt(
                    wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCount)).getText().trim());
            log.info("Successfully fetched the number of items shown over cart icon which is-->>" + count);
        } catch (Exception e) {
            log.error("Unable to get the number shown over cart icon due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    public float getProductPrice() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        String text = "";
        try {
            text = getTextFor(priceOfProduct).trim();
            if (text.contains("$")) {
                text = text.replace("$", "");
            }
            if (text.contains(",")) {
                text = text.replace(",", ".");
            }
            log.info("Successfully fetched the price of product which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch sub product price due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return Float.parseFloat(text);
    }

    public String getProductModelNumber() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = getTextFor(productModel).replace("$", "").trim();
            log.info("Successfully fetched product model which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch product model due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean validateMandatoryPDPComponents() {
        IsPageLoaded.waitAllRequest();
        boolean flag = true;
        ;
        String availableComponentString = "";
        // Get all components from the page
        List<WebElement> availableComponentList = getAllElements(componentsPDP);
        for (WebElement availablecomponent : availableComponentList)
            availableComponentString = availableComponentString + "|" + availablecomponent.getText();

//Read the mandatory component from the property file
        String mandatorycomponentString = Props.getProp("products.mandatoryComponent");
        String[] mandatoryComponentarray = mandatorycomponentString.split(";");
//Compare the value
        for (String mandatorycomponent : mandatoryComponentarray) {
            if (availableComponentString.toLowerCase().contains(mandatorycomponent.toLowerCase())) {
                log.info("Mandatory Component found " + mandatorycomponent);
                System.out.println("Mandatory Component found " + mandatorycomponent);
            } else {
                log.error("Mandatory Component found " + mandatorycomponent);
                flag = false;
            }

        }

        return flag;
    }

    public void updateProductQuantity(String productQty) {
        IsPageLoaded.waitAllRequest();
        try {
            waitForExpectedElement(quantityOfProduct).clear();
            waitForExpectedElement(quantityOfProduct).sendKeys(productQty);
            log.info("Successfully updated the product quantity");
        } catch (Exception e) {
            log.error("Some exception occurred while updating the product quantity-->>" + e.getMessage());
        }
    }

    public Boolean validateErrorMesg(String msgKey) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        String expectedMessage = Props.getProp(msgKey);

        try {
            String actualMessage = waitForExpectedElement(quantityInvalidError).getText();
            flag = expectedMessage.contentEquals(actualMessage);
            if (flag)
                log.info("Successfully found error message " + expectedMessage);
            else
                log.error("Expected error message=" + expectedMessage + " Actual message=" + actualMessage);
        } catch (Exception e) {
            log.error("Some exception occurred while finding invalid error message-->>" + e.getMessage());
        }
        return flag;
    }

    public String getProductQuantityFromOverlay() {
        IsPageLoaded.waitAllRequest();
        return waitForExpectedElement(quantityOfProduct).getAttribute("value");
    }

    public String getEmptyCartMessage() {
        IsPageLoaded.waitAllRequest();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mobileEmptyCartMessage)).getText().trim();
    }
    public void verifyRatingAtPlpLevel(String productSpecificSku) {
        IsPageLoaded.waitAllRequest();
        selectProductDynamic=By.xpath("//*[@data-bv-product-id='" + productSpecificSku + "']/div/div");
        log.info("Dyanmic xpath of the product has been created which is--->>" + selectProductDynamic);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamic));
        SearchProduct = element.getText().trim();
        if(SearchProduct.contains("0.0")){
            log.info("The Rating of the product is still zero which is-->>" + SearchProduct);
        }
        else{
            log.info("Successfully found the rating the product which is-->>" + SearchProduct);
        }
    }

    public void clickRateAndReviewButton() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(rateAndReviewButton).click();
        } else if (UrlBuilder.isMobile()) {
            Actions actions = new Actions(getWebDriver());
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                actions.moveToElement(element).build().perform();
                element.click();
            }
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileHeaderSignInLink));
            actions.moveToElement(element).build().perform();
            element.click();
        }
    }
    public String getReviewText() {
        return waitForExpectedElement(reviewText).getText();
    }
}
