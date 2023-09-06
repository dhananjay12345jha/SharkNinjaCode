package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
//import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class CartOverlayPage extends PageObject {

    private final By isCartOverlayPagePresent = By.cssSelector("[id='overlay-page']");
    //    private final By goToBasketLink = By.xpath("(//a[@class='view-cart btn-lg btn-block overlay-white-btn'])[1]");
    private final By goToBasketLinkDesktop = By.xpath("//div[contains(@class,'overlay-container')]//a[@role=\"button\" and contains(@href,'cart')]");
    private static final By goToBasketLinkMobile = By.xpath("//div[contains(@class,'visible-xs')]//a[@role=\"button\" and contains(@href,'cart')]");
    private final By goToCheckoutButton = By.xpath("//div[@class='row cost-summary cart-checkout-buttons']/div/button");

    private final By proceedToCheckoutButton = By.xpath("//button[@class='btn btn-lg btn-block btn-primary']");

    private static final Logger log = LoggerFactory.getLogger(CartOverlayPage.class);


    public By checkIsCartOverlayPagePresent() {
        return isCartOverlayPagePresent;
    }

    public boolean checkIsProceedLinkPresentForBulk() {
        return isElementClickable(proceedToCheckoutButton);
    }
    public boolean goToBasketLink() {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        By gotoBasketBtn;
        if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
            gotoBasketBtn = goToBasketLinkMobile;
        } else {
            gotoBasketBtn = goToBasketLinkDesktop;
        }
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(gotoBasketBtn));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            flag = true;
            log.info("After clicking on add to basket button Successfully clicked on the GOTO Basket Button ");
        }catch (TimeoutException e1){
            log.error("Unable to find the GO TO BASKET link it might not present for the product");
        }
        catch (Exception e) {
            log.error("Unable to click on GO TO Basket button please check----->>>>>>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public void goToCheckoutButton() {
        waitForExpectedElement(goToCheckoutButton).click();
    }

}
