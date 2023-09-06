package com.salmon.test.page_objects.gui.MicrositeSharkUK;


import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSCartPage extends PageObject {

    private static final By cartPageHeading = By.xpath("//div[@class='secure-checkout-message-wrapper']/h2/strong");

    private static final Logger log = LoggerFactory.getLogger(com.salmon.test.page_objects.gui.CartPage.class);

    public String getCartPageHeading() {
        return waitForExpectedElement(cartPageHeading).getText();
    }
}
