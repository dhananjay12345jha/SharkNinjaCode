package com.salmon.test.step_definitions.pwa;

import com.salmon.test.framework.PageObject;
import cucumber.api.java.en.When;
import com.salmon.test.page_objects.pwa.pwaPDPPage;

public class pwaPDPPageSteps extends PageObject {

    private pwaPDPPage pwapdpPage;
    public pwaPDPPageSteps() {
        this.pwapdpPage = new pwaPDPPage();
    }

    @When("^I add product to the cart$")
    public void i_add_product_to_the_cart() throws InterruptedException {
        pwapdpPage.addToCartButton();
        //Thread.sleep(10000);

    }
}
