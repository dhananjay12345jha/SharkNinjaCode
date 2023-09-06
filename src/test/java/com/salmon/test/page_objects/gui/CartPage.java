package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends PageObject {

//  private final By cartPageHeading = By.xpath("//div[@class='cart-header col-xs-12']/h1/span[1]");
    private final By cartPageHeading = By.cssSelector(".cart-header-title");
    private final By mobileEmptyCartMessage = By.xpath("//ul[@class='list-unstyled alert alert-danger']");
    private final By proceedToCheckoutButton = By.xpath("//button[@class='btn btn-lg btn-block btn-primary']");
    private final By productID = By.xpath("//p[@class='product-id']");
    //private final By deleteIcon = By.xpath("//span[@class='glyphicon glyphicon-trash']");
//    private final By deleteIcon = By.xpath("//div[contains(@class,'price-wrapper')]//span[@class='glyphicon glyphicon-trash']");
    private static final By deleteIcon = By.xpath("//span[@class='glyphicon glyphicon-trash']/ancestor::div[@class='remove-pli']/a");
    private static final By deleteIconDesktop = By.xpath("//span[@class='glyphicon glyphicon-trash']/ancestor::div[contains(@class,'remove-pli hidden-xs')]/a");
    private static final By singlePriceOfProduct = By.xpath("//div[contains(@class,'column-price single-price')]");

    private static final By productQuantityList = By.xpath("//div[starts-with(@class,'quantity')]//select[@data-testing-id=\"quantity\"]");

    private static final By productQuantityListMobile = By.xpath("//div[starts-with(@class,'mobile-quantity')]//select[@data-testing-id=\"quantity\"]");
    private static final By totalPriceOfProduct = By.xpath("//div[@class=\"row\"]/div[contains(@class,'pull-right')]");
    private final By productQuantityTxtBox = By.xpath("//input[starts-with(@id,'quantity_')]");
    private final By productQuantityTxtBoxPWA = By.xpath("//input[@data-testing-id=\"quantity\"]");
    private final By mobileProductQuantityTxtBox = By.xpath("//select[@name='quantity']");
    private final By updateCartBtn = By.xpath("//button[@class='btn btn-primary update-cart pull-left']");
    private final By cartSubtotal = By.xpath("//dl[@class='dl-horizontal']/dd[1]");
    private final By quantityInvalidError = By.xpath("//div[@class='form-group has-error']");
    private final By quantityLargeError = By.xpath("//div[@class='form-group has-error']");
    private final By promoEntryLinkDesktop = By.xpath("(//a[@class='js-promoentry'])[2]");
    private static final By promoEntryLinkMobile = By.xpath("(//a[@class='js-promoentry'])[1]");
    //    private final By promoEntryTextBox = By.xpath("(//input[@id='promotionCode'])[2]");
    private static final By promoEntryTextBoxDesktop = By.xpath("(//input[contains(@id,'PromotionCode')])[2]");
    private static final By promoEntryTextBoxMobile = By.xpath("(//input[contains(@id,'PromotionCode')])[1]");
    private static final By promoApplyBtnDesktop = By.xpath("(//button[@id='applyPromotion'])[2]");
    private static final By promoApplyBtnMobile = By.xpath("(//button[@id='applyPromotion'])[2]");

    private static final By viewOrderButtonDesktop = By.xpath("//*[@id=\"miniCart\"]/a");

    //private static final By viewOrderButtonDesktop = By.xpath("(//button[@id='applyPromotion'])[2]");

    private static final By OrderCantPlacedMessageDesktop = By.xpath("//ul[@class='list-unstyled alert alert-danger']");
    private static final By promoAppliedMessageDesktop = By.cssSelector(".coupon-wrapper-bottom-view .alert");
    private static final By promoAppliedMessageMobile = By.xpath("//div[starts-with(@class,'coupon-wrapper-top-view')]");

    //private final By totalSavingsCartSummary = By.xpath("//dd[@class='total-saving']");
    private final By totalSavingsCartSummary = By.xpath("//dl[@class='dl-horizontal']/dd[2]");
    private final By discountDetailsLink = By.xpath("//*[contains(text(),'Details')]");
    private final By promoAppliedDailogueMessage = By.xpath("//h2[@class='modal-title']");
    //private static final By loader = By.xpath("//footer//div[@class='loader']");
    private static final By loader = By.xpath("//div[@class='loader']");
    private  By qtySelector;
    private static final Logger log = LoggerFactory.getLogger(CartPage.class);

    public CartPage() {
        String country = Props.getProp("country");
        if (country.equals("US") || country.equals("CA"))
            qtySelector = productQuantityTxtBoxPWA;
        else
            qtySelector = productQuantityTxtBox;
    }

    public String getCartPageHeading() {
        IsPageLoaded.waitAllRequest();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageHeading)).getText().trim();
    }

    public String getEmptyCartMessage() {
        IsPageLoaded.waitAllRequest();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mobileEmptyCartMessage)).getText().trim();
    }

    public void proceedToCheckoutButton() {
        IsPageLoaded.waitAllRequest();
        WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(proceedToCheckoutButton));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        if (UrlBuilder.isBsedge() || UrlBuilder.isLocalChrome() || UrlBuilder.isNexus()) {
            String url = getWebDriver().getCurrentUrl();
            url = "https://shrkn-uat:aiwu2Mae5u@" + url.substring(8);
            getWebDriver().get(url);
        }
    }

    public By getCartProduct() {
        return productID;
    }

    public List<String> getCartProductID() {
        IsPageLoaded.waitAllRequest();
        List<String> product = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productID))
                .stream().map(s -> s.getText().split(":")[1].trim())
                .collect(Collectors.toList());
        return product;
    }

    public List<Float> getSinglePriceOfProduct() {
        IsPageLoaded.waitAllRequest();
        int count = 0;
        List<Float> product = new ArrayList<>();
        while (count < 2) {
            product = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(singlePriceOfProduct)).
                    stream().map(s -> {
                String k = s.getText().trim();
                if (k.contains("£")) {
                    k = k.replace("£", "").trim();
                }
                if (k.contains("€")) {
                    k = k.replace("€", "").trim();
                }
                return Float.parseFloat(removeCommaAndDot(k));
            }).collect(Collectors.toList());
            count++;
        }
        return product;
    }

    public List<Float> getTotalPriceOfProducts() {
        IsPageLoaded.waitAllRequest();
        int count = 0;
        List<Float> product = new ArrayList<>();
        IsPageLoaded.waitAllRequest();
        while (count < 2) {
            product = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(totalPriceOfProduct)).
                    stream().map(s -> {
                String k = s.getText().trim();
                if (k.contains("£")) {
                    k = k.replace("£", "").trim();
                }
                if (k.contains("€")) {
                    k = k.replace("€", "").trim();
                }
                //check whether string contains "," at multiple position
                return Float.parseFloat(removeCommaAndDot(k));
            }).collect(Collectors.toList());
            count++;
        }
        return product;
    }


    public boolean clickDeleteProduct() {
        boolean flag = false;
        try {

            if (UrlBuilder.isDesktop()) {
                WebElement element = webDriver.findElement(deleteIconDesktop);
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                log.info("Successfully clicked on delete icon for desktop ");
                flag = true;
            } else {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(deleteIcon));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                log.info("Successfully clicked on delete icon for mobile ");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Unable to click on delete button---->>>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateProductQuantity(String productSku, String quantity) {
        boolean flag = false;
        WebElement element = null;
        IsPageLoaded.waitAllRequest();
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            try {
                List<WebElement> changeQuantity = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productQuantityTxtBox));
                for (int j = 0; j < getCartProductID().size(); j++) {
                    if (getCartProductID().get(j).contains(productSku)) {
                        element = changeQuantity.get(j);
                        new Actions(getWebDriver()).moveToElement(element).build().perform();
                        element.clear();
                        element.sendKeys(quantity);
                        flag = true;
                        log.info("Successfully set the quantity of product-->" + productSku + " to-->" + quantity);

                    }
                }
            } catch (Exception e) {
                log.error("Unable to set the quantity of product on desktop-->>" + ExceptionUtils.getStackTrace(e));
            }

        } else if (UrlBuilder.isMobile()) {
            try {
                List<WebElement> changeQuantity = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mobileProductQuantityTxtBox));
                Thread.sleep(2000);
                for (int j = 0; j < getCartProductID().size(); j++) {
                    if (getCartProductID().get(j).contains(productSku)) {
                        element = changeQuantity.get(j);
                        new Actions(getWebDriver()).moveToElement(element).build().perform();
                        selectValueFromDropDownByWebElement(element, quantity);
                        flag = true;
                        log.info("Successfully set the quantity of product-->" + productSku + " to-->" + quantity);
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("Unable to set the quantity of product on desktop-->>" + ExceptionUtils.getStackTrace(e));
            }
        }
        return flag;
    }

    public boolean waitForTheLoaderToAppearAndDisappear() {
        boolean flag = false;
        try {
            log.info("After updating quantity waiting for the loader to come ");
            //WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(loader));
            log.info("Loader has appeared now waiting for the loader to disappear so that price would get updated ");
            wait.until(ExpectedConditions.invisibilityOf(getWebDriver().findElement(loader)));
            log.info("Loader has disappeared moving to next step ");
            IsPageLoaded.waitAllRequest();
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while checking for loader after updating quantity-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public List<String> getProductQuantity() {
        IsPageLoaded.waitAllRequest();
        List<String> list = new ArrayList<>();
        if (UrlBuilder.isDesktop()) {
            list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(qtySelector))
                    .stream().map(s -> s.getAttribute("value").trim()).collect(Collectors.toList());
        } else if (UrlBuilder.isMobile()) {
            if (webDriver.findElements(mobileProductQuantityTxtBox).size() > 0) {
                return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mobileProductQuantityTxtBox)).
                        stream().map(s -> s.getAttribute("value").trim()).collect(Collectors.toList());
            } else {
                list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(qtySelector)).
                        stream().map(s -> s.getAttribute("value").trim()).collect(Collectors.toList());
            }
        }
        return list;
    }

    public void clickUpdateCartBtn() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(updateCartBtn).click();
        }
    }

    public Float getCartSubtotal() {
        String k = wait.until(ExpectedConditions.visibilityOfElementLocated(cartSubtotal)).getText().trim();
        if (k.contains("£")) {
            k = k.replace("£", "").trim();
        }
        if (k.contains("€")) {
            k = k.replace("€", "").trim();
        }
        //check whether string contains "," at multiple position
        return Float.parseFloat(removeCommaAndDot(k));
    }

    public String isErrorMessageDisplayedInvalidQuantity() {
        String text = "";
        try {
            text = waitForExpectedElement(quantityInvalidError).getText().trim();
            log.info("Successfully found error message invalid-->>" + text);
        } catch (Exception e) {
            log.error("Some exception occurred while finding invalid error message-->>" + e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public String isErrorMessageDisplayedTooLarge() {
        String text = "";
        try {
            text = waitForExpectedElement(quantityLargeError).getText().trim();
            log.info("Successfully found error message too large--->>" + text);
        } catch (Exception e) {
            log.error("Some exception occurred while finding too large error message-->>" + e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public Boolean clickPromoEntryLink() {
        boolean flag = false;
        try {
            if (UrlBuilder.isMobile()) {
                waitForExpectedElement(promoEntryLinkMobile).click();
                flag = true;
            } else {
                waitForExpectedElement(promoEntryLinkDesktop).click();
                log.info("Successfully found error message too large");
                flag = true;
            }
        } catch (Exception e) {
            log.info("Some exception occurred while click on promo entry link-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean enterPromoCode(String promoCode) {
        boolean flag = false;
        try {
            if (UrlBuilder.isMobile()) {
                waitForExpectedElement(promoEntryTextBoxMobile).sendKeys(promoCode);
                log.info("Successfully entered promo code on mobile");
                flag = true;
            } else {
                waitForExpectedElement(promoEntryTextBoxDesktop).sendKeys(promoCode);
                log.info("Successfully entered promo code on desktop");
                flag = true;
            }
        } catch (Exception e) {
            log.info("Some exception occurred while promo code entry-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean clickApplyPromo() {
        boolean flag = false;
        try {
            if (UrlBuilder.isMobile()) {
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(promoApplyBtnMobile));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked promo apply button on Mobile");
                flag = true;
            } else {
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(promoApplyBtnDesktop));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked view Order Button on Desktop");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Some exception occurred while click on view Order Button-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }
    public boolean clickViewOrderButton() {
        boolean flag = false;
        try {
            if (UrlBuilder.isMobile()) {
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(promoApplyBtnMobile));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked promo apply button on Mobile");
                flag = true;
            } else {
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(viewOrderButtonDesktop));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully clicked view order button on Desktop");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Some exception occurred while click on view order button-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public String verifyPromoAppliedSuccessMessage() {
        String text = " ";
        int count = 0;
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        By promoCodeMessage = promoAppliedMessageDesktop;
        if (UrlBuilder.isMobile()) {
            promoCodeMessage = promoAppliedMessageMobile;
        }
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(promoCodeMessage)).findElement(By.tagName("p"));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                text = element.getText().trim();
                log.info("Successfully found promo applied success message which is--->>>" + text);
                break;
            } catch (TimeoutException e1) {
                log.error("TimeOut Exception is there might be success message not visible-->>" + ExceptionUtils.getStackTrace(e1));
                text = "Might be success message not displayed please check";
                e1.printStackTrace();
                break;
            } catch (StaleElementReferenceException e1) {
                log.error("Stale Element is there tyring--->>" + count + " time");
                count++;
            } catch (Exception e) {
                log.info("Some exception occurred while finding applied promo message-->>" + e.getMessage());
                e.printStackTrace();
                break;
            }
        } while (count < 3);
        return text;
    }

    public String verifyOrderCantPlacedMessage() {
        String text = "Not Found/Some Exception is there";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(OrderCantPlacedMessageDesktop));
            text = element.getText();
            log.info(" Successfully found Shark order cant placed on this website message ----->>>>>>" + text);
        } catch (Exception e) {
            log.error("some exception occurs while validating Shark order cant placed message--->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public boolean verifyItemDiscountInCartSummary() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(totalSavingsCartSummary).isDisplayed() && !waitForExpectedElement(totalSavingsCartSummary).getText().isEmpty();
            System.out.println(flag);
            log.info("Successfully found item discount in cart summary");
        } catch (Exception e) {
            log.info("Some exception occurred while finding item discount in cart summary-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyDiscountDetailsLinkInCartSummary() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(discountDetailsLink).isDisplayed();
            log.info("Successfully found item discount in cart summary");
        } catch (Exception e) {
            log.info("Some exception occurred while finding item discount in cart summary-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean clickDiscountDetailsLink() {
        boolean flag = false;
        try {
            waitForExpectedElement(discountDetailsLink).click();
            log.info("Successfully clicked discount details link");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while click on discount details link-->>" + e.getMessage());
        }
        return flag;
    }

//    public boolean setQuantityOfProduct(String quantity, int rowNumber) {
//        IsPageLoaded.waitAllRequest();
//        boolean flag = false;
//        int count = 0;
//        do {
//            try {
//                text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyTextForUK)).getText().trim();
//                if (text.contains(Props.getMessage("your.cart.is.empty.text"))) {
//                    log.info("cart Empty Text Extracted From homePage Page is-->" + text);
//                    break;
//                } else {
//                    try {
//                        WebElement element = getWebDriver().findElement(viewBasketButton);
//                        new Actions(getWebDriver()).moveToElement(element).build().perform();
//                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//
//                        if (UrlBuilder.isMobile()) {
//            try {
//                while (count < 3) {
//                    WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productQuantityListMobile)).get(--rowNumber);
//                    new Actions(getWebDriver()).moveToElement(element).build().perform();
//                    new Select(element).selectByVisibleText(quantity);
//                    element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityListMobile)).get(rowNumber);
//                    if (quantity.contains(element.getAttribute("value"))) {
//                        flag = true;
//                        break;
//                    } else {
//                        count++;
//                    }
//                }
//            } catch (Exception e) {
//                log.error("Unable to select the quantity of product show at rowNumber-->>" + (++rowNumber) + " due to-->>" + e.getMessage());
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                while (count < 3) {
//                    WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productQuantityList)).get(--rowNumber);
//                    new Actions(getWebDriver()).moveToElement(element).build().perform();
//                    new Select(element).selectByVisibleText(quantity);
//                    element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantityList)).get(rowNumber);
//                    if (quantity.contains(element.getAttribute("value"))) {
//                        flag = true;
//                        break;
//                    } else {
//                        count++;
//                    }
//                }
//            } catch (Exception e) {
//                log.error("Unabel to select the quantity of product show at rowNumber-->>" + (++rowNumber) + " due to-->>" + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }

    public Boolean verifyPromoAppliedPopupMessage(String message) {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(promoAppliedDailogueMessage).getText().contentEquals(message);
            log.info("Successfully found promo applied success message");
        } catch (Exception e) {
            log.info("Some exception occurred while finding applied promo message-->>" + e.getMessage());
        }
        return flag;
    }

    public static String removeCommaAndDot(String k) {
        StringBuilder rev = new StringBuilder(k);
        rev.reverse();
        if (k.contains(",")) {
            do {
                if (!(rev.indexOf(",") == rev.lastIndexOf(","))) {
                    rev.deleteCharAt(rev.lastIndexOf(","));
                }
                else {
                    break;
                }
            } while (rev.indexOf(",") != rev.lastIndexOf(","));
            int index=rev.indexOf(",");
            rev.deleteCharAt(index);
            rev.replace(index,index,".");
        }
        if(k.contains(".")){
            do {
                if (!(rev.indexOf(".") == rev.lastIndexOf("."))) {
                    rev.deleteCharAt(rev.lastIndexOf("."));
                }
                else {
                    break;
                }
            } while (rev.indexOf(".") != rev.lastIndexOf("."));
        }
        return rev.reverse().toString();
    }
}
