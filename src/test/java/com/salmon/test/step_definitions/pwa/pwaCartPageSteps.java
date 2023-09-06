package com.salmon.test.step_definitions.pwa;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.pwa.pwaCartPage;
import cucumber.api.java.en.When;

public class pwaCartPageSteps extends PageObject {
    private pwaCartPage pwacartPage;
    public pwaCartPageSteps(pwaCartPage pwacartPage) {
        this.pwacartPage = pwacartPage;
    }

    @When("^I go to the checkout$")
    public void i_go_to_checkout() throws InterruptedException {
        pwacartPage.proceedToCheckoutButton();
       // Thread.sleep(5000);
    }

}
