package com.salmon.test.page_objects.gui.MicrositeSharkUS;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSCartPage extends PageObject {

    private static final By cartPageHeadingText = By.xpath("//h1[@class=\"shopping-cart-title\"]");
    private static final Logger log = LoggerFactory.getLogger(MSHomePage.class);

    public String getCartPageHeadingInText() {
        IsPageLoaded.waitAllRequest();
        String text = "";

        try {
            text = webDriver.findElement(cartPageHeadingText).getText().trim();
            log.info("Text found over Cart Page is -->" + text);
        } catch (Exception e) {
            log.error("Unable to get text \"Cart\" from the Cart page");
        }

        return text;
    }

}
