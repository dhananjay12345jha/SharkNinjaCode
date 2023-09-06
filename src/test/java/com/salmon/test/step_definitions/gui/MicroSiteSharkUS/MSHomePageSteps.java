package com.salmon.test.step_definitions.gui.MicroSiteSharkUS;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.MicrositeSharkUS.MSCartPage;
import com.salmon.test.page_objects.gui.MicrositeSharkUS.MSHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_scouse.An;
import org.testng.Assert;

public class MSHomePageSteps {

    private MSHomePage MSHomePage;
    private MSCartPage MSCartPage;

    public MSHomePageSteps() {
        this.MSHomePage = new MSHomePage();
        this.MSCartPage = new MSCartPage();
    }

    @Then("^I should be on product page$")
    public void verifyPDPPage() {
        String actual = MSHomePage.getPDPPageHeading();
        String expected = Props.getProp("pdp.page.heading");

        Assert.assertEquals(actual, expected);
    }
    @And("^i should see one product selected$")
    public void isProductSelected() {
        Assert.assertTrue(MSHomePage.verifyProductText());
    }

    @Then("^i selected second product positioned at '(.*?)'$")
    public void toVerifyProductSelected(int position) {
        Assert.assertTrue(MSHomePage.clickOnProductButton(position));
        Assert.assertTrue(MSHomePage.verifyNameOfProduct());
    }

    @And("^\"(.*?)\" is visible$")
    public void isTextVisible(String productName){
        Assert.assertTrue(MSHomePage.verifySelectedProduct(Props.getProp(productName)));
    }

    @When("^I click Add to Cart button$")
    public void addProductsToCart() {
        if (MSHomePage.isElementPresent(MSHomePage.toVerifyAddToCartButton())) {
            Assert.assertTrue(MSHomePage.addProductToCart());
        }
    }

    @And("^i clicked on sign in button$")
    public void clickOnSignInButton(){
        Assert.assertTrue(MSHomePage.clickSignInBtn());
    }

    @And("I click on mini bag icon")
    public void clickOnMiniBagIcon() {
        Assert.assertTrue(MSHomePage.clickMiniBagIcon());
    }

    @Then("I should be on Cart Page")
    public void verifyCartPage() {
        Assert.assertEquals(MSCartPage.getCartPageHeadingInText(), Props.getProp("cart.page.heading"));
    }

}
