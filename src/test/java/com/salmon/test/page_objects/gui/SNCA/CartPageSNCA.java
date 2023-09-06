package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import cucumber.api.java.en_old.Ac;
import cucumber.api.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CartPageSNCA extends PageObject {
    //    private static final By getTextCart = By.xpath("//*[contains(@class,'shopping-cart-title')]");
    private static final By getTextCart = By.xpath("//*[contains(@class,'cart-header-headtitle')]");
    private static final By subTotal = By.xpath("//div[@data-testing-id=\"basket-subtotal\"]");

    private static final By cartIcon = By.xpath("//sn-mini-basket[@data-testing-id=\"mini-basket-desktop\"]//a[contains(@href,'basket')]");

    private static final By estimatedShipping = By.xpath("//div[@class=\"responsive-price shipping\"]/div[2]");
    private static final By estimatedTotal = By.xpath("//div[@class=\"responsive-price total\"]/div[2]");
    private static final By checkOutButton = By.xpath("//button[text()=\" Passer la commande \" or text()=\" Checkout \" ]");
    private static final By promoCodeButton = By.xpath("//button[@class=\"promo-code-wrapper\"]");
    private static final By promoCodeTextBox = By.xpath("//input[@data-testing-id=\"promo-code-form\"]");
    private static final By promoCodeApplyButton = By.xpath("//button[contains(@class,'button-promotion-apply')]");
    private static final By listOfProductTitle = By.xpath("//a[@class=\"product-title\"]");
    private static final By productModelNumber = By.xpath("//ish-product-id/div/span");
    private static final By removeItemButton = By.xpath("//button[contains(@class,'product-remove')]");
    private static final By removeItemButtonMobile = By.xpath("//a[contains(@class,'product-remove')]");
    private static final By productPriceList = By.xpath("//div[@data-testing-id=\"total-price\"]/span");
    private static final By productQuantityList = By.xpath("//div[starts-with(@class,'quantity')]//select[@data-testing-id=\"quantity\"]");
    private static final By productQuantityListMobile = By.xpath("//div[starts-with(@class,'mobile-quantity')]//select[@data-testing-id=\"quantity\"]");
    private static final By promoCodeVisibleWithEachItem = By.xpath("//span[@class=\"promotion-title coupon-title\"]/span[2]");
    private static final By basketRebateAmount = By.xpath("(//div[@data-testing-id='total-price'])[2]//span");

    private static final By productTotalPrice = By.xpath("//div[@data-testing-id=\"product-list-item\"]//div[contains(@class,'single-price')]");
    private static final By textPromoCodeApplied = By.xpath("//button[@class=\"promo-code-wrapper\"] /..//span");
    private static final Logger log = LoggerFactory.getLogger(CartPageSNCA.class);


    private boolean isCartPageStable() {
        IsPageLoaded.waitAllRequest();
        return (wait.until(ExpectedConditions.elementToBeClickable(checkOutButton))).isDisplayed();
    }

    public String getTextPromoCodeApplied() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(textPromoCodeApplied));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
            log.info("Successfully fetched the text after applying promocode which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch text after applying promocode due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getTextFromCartPage() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = getTextFor(getTextCart).trim();
            log.info("Successfully fetched the text from CART page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch text from cart page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
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

    public float getSubTotal() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        String text;
        try {
            WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(subTotal));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(subTotal)).getText().trim();
            if (text.contains("$")) {
                text = text.replace("$", "");
            }
            if (text.contains(",")) {
                text = text.replace(",", ".");
            }
            price = Float.parseFloat(text);
            log.info("Successfully fetched the sub total cost from CART page which is-->>" + price);
        } catch (Exception e) {
            log.error("Unable to fetch sub total cost from cart page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return price;
    }


    public float getEstimatedShippingCost() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        String text;
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(estimatedShipping)).getText().trim();
            if (text.contains("$")) {
                text = text.replace("$", "");
            }
            if (text.contains(",")) {
                text = text.replace(",", ".");
            }
            price = Float.parseFloat(text);
            log.info("Successfully fetched the shipping cost from CART page which is-->>" + price);
        } catch (Exception e) {
            log.error("Unable to fetch shipping cost from cart page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return price;
    }

    public float getEstimatedTotalCost() {
        IsPageLoaded.waitAllRequest();
        float price = -1;
        String text;
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(estimatedTotal)).getText().trim();
            if (text.contains("$")) {
                text = text.replace("$", "");
            }
            if (text.contains(",")) {
                text = text.replace(",", ".");
            }
            price = Float.parseFloat(text);
            log.info("Successfully fetched the estimated total cost from CART page which is-->>" + price);
        } catch (Exception e) {
            log.error("Unable to fetch estimated total cost from cart page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return price;
    }

    public SecureCheckoutPageSNCA clickCheckOutButton() {
        IsPageLoaded.waitAllRequest();
        SecureCheckoutPageSNCA obj = null;
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(checkOutButton)).click();
            obj = new SecureCheckoutPageSNCA();
        } catch (Exception e) {
            log.error("Unable to click on checkout button due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public List<String> getProductTitles() {
        isCartPageStable();
        List<String> titles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfProductTitle))
                .stream().map(s -> s.getText().trim()).collect(Collectors.toList());
        log.info("Successfully fetched the product titles list");
        return titles;
    }

    public List<Float> getProductPriceList() {
        isCartPageStable();
        List<Float> priceList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPriceList))
                .stream().map(s -> {
                            String k = s.getText().trim();
                            if (k.contains("$")) {
                                k = k.replace("$", "");
                            }
                            if (k.contains(",")) {
                                k = k.replace(",", ".");
                            }

                            return Float.parseFloat(k);
                        }
                ).collect(Collectors.toList());
        log.info("Successfully fetched the product titles list");
        return priceList;
    }

    public List<Integer> getProductQuantityList() {
        isCartPageStable();
        List<Integer> quantityList;
        if (UrlBuilder.isMobile()) {
            quantityList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityListMobile))
                    .stream().map(s -> Integer.parseInt(s.getAttribute("value").trim())).collect(Collectors.toList());
            log.info("Successfully fetched the product titles list");
        } else {
            quantityList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityList))
                    .stream().map(s -> Integer.parseInt(s.getAttribute("value").trim())).collect(Collectors.toList());
            log.info("Successfully fetched the product titles list");
        }
        return quantityList;
    }


    public List<String> getProductModelNumber() {
        isCartPageStable();
        List<String> titles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productModelNumber))
                .stream().map(s -> s.getText().trim()).collect(Collectors.toList());
        log.info("Successfully fetched the product model number list");
        return titles;
    }

    public boolean clickRemoveItemButtonLocatedAtPosition(int location) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        location--;
        if (location < 0) {
            throw new RuntimeException("PLease provide location of Remove Item button greater than zero ");
        }

        if (UrlBuilder.isMobile()) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeItemButtonMobile)).get(location);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked on the remove item button showing at location-->" + (++location));
                isCartPageStable();
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on remove item button located at position-->>" + (++location) + " due to-->" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeItemButton)).get(location);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked on the remove item button showing at location-->" + (++location));
                isCartPageStable();
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on remove item button located at position-->>" + (++location) + " due to-->" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean setQuantityOfProduct(String quantity, int rowNumber) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int count = 0;
        if (UrlBuilder.isMobile()) {
            try {
                while (count < 3) {
                    WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productQuantityListMobile)).get(--rowNumber);
                    new Actions(getWebDriver()).moveToElement(element).build().perform();
                    new Select(element).selectByVisibleText(quantity);
                    element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityListMobile)).get(rowNumber);
                    if (quantity.contains(element.getAttribute("value"))) {
                        flag = true;
                        break;
                    } else {
                        count++;
                    }
                }
            } catch (Exception e) {
                log.error("Unabel to select the quantity of product show at rowNumber-->>" + (++rowNumber) + " due to-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                while (count < 3) {
                    WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productQuantityList)).get(--rowNumber);
                    new Actions(getWebDriver()).moveToElement(element).build().perform();
                    new Select(element).selectByVisibleText(quantity);
                    element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityList)).get(rowNumber);
                    if (quantity.contains(element.getAttribute("value"))) {
                        flag = true;
                        break;
                    } else {
                        count++;
                    }
                }
            } catch (Exception e) {
                log.error("Unabel to select the quantity of product show at rowNumber-->>" + (++rowNumber) + " due to-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean applyPromoCode(String promoCode) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            while (webDriver.findElements(promoCodeButton).size() != 0) {
                new Actions(getWebDriver()).moveToElement(webDriver.findElement(promoCodeButton)).click().build().perform();
            }
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(promoCodeTextBox));
            element.clear();
            element.sendKeys(promoCode);
            log.info("Successfully entered the promoCode-->>" + promoCode);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter promocode at cart page due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnApplyPromoCodeButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(promoCodeApplyButton)).click();
            log.info("Successfully clicked on apply promoCode button ");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter promocode at cart page due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public List<String> getPromoCodeAttachedWithEachElement() {
        isCartPageStable();
        List<String> titles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(promoCodeVisibleWithEachItem))
                .stream().map(s -> s.getText().trim()).collect(Collectors.toList());
        log.info("Successfully fetched the promocode attached with each product in cart page");
        return titles;
    }

    public float getDiscountAmountAppliedAtCart() {
        isCartPageStable();
        float priceList;
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(basketRebateAmount)).getText().trim();

        if (text.contains("$")) {
            text = text.replace("$", "");
        }
        if (text.contains(",")) {
            text = text.replace(",", ".");
        }

        priceList = Float.parseFloat(text);
        log.info("Successfully fetched the discounted amount which is-->" + priceList);
        return priceList;
    }
}


