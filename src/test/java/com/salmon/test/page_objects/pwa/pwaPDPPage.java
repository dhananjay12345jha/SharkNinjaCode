package com.salmon.test.page_objects.pwa;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.gui.PDPPage;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class pwaPDPPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(PDPPage.class);
    // By.xpath("//button[@name='addProduct']");
    private final By addToCartButton = By.xpath("//*[@id=\"main-content\"]/ish-product-page/div/sn-product-detail/div/div[2]/div/form/ish-product-add-to-basket/div/button");

    public void addToCartButton() {
        try {

            //waitForExpectedElement(addToCartButton).click();
            clickByElementByQueryJSExecutor(addToCartButton);
            log.info("Successfully clicked on add to Cart Button");
        }
        catch (Exception e) {
            log.error("Some exception occurred while clicking on add to Cart Button-->>"+e.getMessage());
        }

    }
}
