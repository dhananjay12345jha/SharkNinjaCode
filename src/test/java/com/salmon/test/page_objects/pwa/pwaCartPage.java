package com.salmon.test.page_objects.pwa;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class pwaCartPage extends PageObject {
    private final By proceedToCheckoutButton = By.xpath("//div[@class='quick-cart-link d-md-block mini-cart-active']//button[@id='checkoutBtn']");

    public void proceedToCheckoutButton() {
        //waitForExpectedElement(proceedToCheckoutButton).click();
        clickByElementByQueryJSExecutor(proceedToCheckoutButton);
    }
}
