package com.salmon.test.step_definitions.gui.MicroSiteSharkUS;

import com.salmon.test.page_objects.gui.MicrositeSharkUS.MSSecureCheckoutPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class MSCheckoutPageSteps {

    private MSSecureCheckoutPage MSSecureCheckoutPage;

    public MSCheckoutPageSteps() {
        this.MSSecureCheckoutPage = new MSSecureCheckoutPage();
    }

    @Then("^I select payment option as \"Pay by Installment\"$")
    public void select_option_pay_By_card() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(MSSecureCheckoutPage.selectPayByInstallmentOption());
    }


}
