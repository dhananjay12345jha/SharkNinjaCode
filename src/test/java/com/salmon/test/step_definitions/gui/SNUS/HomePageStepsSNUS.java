package com.salmon.test.step_definitions.gui.SNUS;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.PartsAndAccessoriesPageSNCA;
import com.salmon.test.page_objects.gui.SNUS.HomePageSNUS;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePageStepsSNUS extends PageObject {

    private HomePageSNUS homePageSNUS;
    private PartsAndAccessoriesPageSNCA partsAndAccessoriesPageSNCA;

    public HomePageStepsSNUS() {
        homePageSNUS = new HomePageSNUS();
        partsAndAccessoriesPageSNCA= new PartsAndAccessoriesPageSNCA();
    }

    @Then("^I should see Shark logo$")
    public void isSharkLogoDisplayed() {
        Assert.assertTrue(homePageSNUS.isLogoDisplayed());
    }

    @When("^I click Shark logo$")
    public void clickSharkLogo() {
        Assert.assertTrue(homePageSNUS.clickSharkCALogo());
    }

    @Then("^I click Shark logo to navigate to home page$")
    public void navigateToSharkCAHomePage() {
        Assert.assertTrue(homePageSNUS.navigateToSharkCAHomePage());
    }

    @Then("^I should see Ninja logo$")
    public void isNinjaLogoDisplayed() {
        Assert.assertTrue(homePageSNUS.isLogoDisplayed());
    }

    @Then("^I should see products link$")
    public void isProductsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isProductsLinkDisplayed());
    }

    @Then("^I should see Technologies link$")
    public void isTechnologiesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isTechnologiesLinkDisplayed());
    }

    @Then("^I should see register my warranty link$")
    public void isRegisterMyWarrantyLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isRegisterMyWarrantyLinkDisplayed());
    }

    @Then("^I should see parts and accessories link$")
    public void isPartsAccessoriesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isPartsAccessoriesLinkDisplayed());
    }

    @Then("^I should see ninja parts and accessories link$")
    public void isNinjaPartsAccessoriesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isNinjaPartsAccessoriesLinkDisplayed());
    }

    @Then("^I should see customer care link$")
    public void isCustomerCareLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isCustomerCareLinkDisplayed());
    }

    @Then("^I should see recipes link$")
    public void isRecipesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isRecipesLinkDisplayed());
    }

    @Then("^I should see support link$")
    public void isSupportLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isSupportLinkDisplayed());
    }

    @Then("^I should see offers link$")
    public void isOffersLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isOffersLinkDisplayed());
    }

    @Then("^I should see tips and advice link$")
    public void isTipsAndAdviceLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isTipsAndAdviceLinkDisplayed());
    }

    @Then("^I should see adriana link$")
    public void isAdrianLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isAdrianLinkDisplayed());
    }

    @When("^I click on products link in header$")
    public void clickProductsLink() {
        Assert.assertTrue(homePageSNUS.clickProductsLink());
    }

    @When("^I click on parts and accessories link in header$")
    public void clickPartsAndAccessoriesLink() {
        Assert.assertTrue(homePageSNUS.clickPartsAndAccessoriesLink());
    }

    @When("^I click on customer care link in header$")
    public void clickCustomerCareLink() {
        Assert.assertTrue(homePageSNUS.clickCustomerCareLink());
    }

    @When("^I click on tips and advice link in header$")
    public void clickTipsAndAdviceLink() {
        Assert.assertTrue(homePageSNUS.clickTipsAndAdviceLink());
    }

    @When("^I click on adriana link in header$")
    public void clickAdrianaLink() {
        Assert.assertTrue(homePageSNUS.clickAdrianaLink());
    }

    @Then("^I should see products page$")
    public void verifyProductsPage() {
        Assert.assertTrue(homePageSNUS.verifyProductsPage());
    }

    @Then("^I should see parts and accessories page$")
    public void verifyPartsAndAccessoriesPage() {
        Assert.assertTrue(homePageSNUS.verifyPartsAndAccessoriesPage());
    }

    @Then("^I should see customer care page$")
    public void verifyCustomerCarePage() {
        Assert.assertTrue(homePageSNUS.verifyCustomerCarePage());
    }

    @Then("^I should see tips and advice page$")
    public void verifyTipsAndAdvicePage() {
        Assert.assertTrue(homePageSNUS.verifyTipsAndAdvicePage());
    }

    @Then("^I should see adriana page$")
    public void verifyAdrianaPage() {
        Assert.assertTrue(homePageSNUS.verifyAdrianaPage());
    }

    @Then("^I should see footer shop heading$")
    public void isFooterShopHeadingDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterShopHeadingDisplayed());
    }

    @Then("^I should see footer vacuum link$")
    public void isFooterVacuumsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterVacuumsLinkDisplayed());
    }

    @Then("^I should see footer Mops and Iron link$")
    public void isFooterMopsAndIronsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterMopsAndIronsLinkDisplayed());
    }

    @Then("^I should see footer links$")
    public void verifyFooterLinks() {
//        Assert.assertTrue(homePageSNUS.verifyFooterLinks());
        homePageSNUS.verifyFooterLinks();
    }

    @Then("^I should see footer Parts and Accessories link$")
    public void isFooterPartsAndAccessoriesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterPartsAndAccessoriesLinkDisplayed());
    }

    @Then("^I should see footer Support heading$")
    public void isFooterSupportHeadingDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterSupportHeadingDisplayed());
    }

    @Then("^I should see footer FAQs link$")
    public void isFooterFAQsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterFAQsLinkDisplayed());
    }

    @Then("^I should see footer Warranty Information link$")
    public void isFooterWarrantyInformationLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterWarrantyInformationLinkDisplayed());
    }

    @Then("^I should see Foodi Family Cooking link$")
    public void isFooterFoodiFamilyCookingLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterFoodiFamilyCookingLinkDisplayed());
    }

    @Then("^I should see Cookware link$")
    public void isFooterCookwareLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterCookwareLinkDisplayed());
    }

    @Then("^I should see Blenders link$")
    public void isFooterBlendersLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterBlendersLinkDisplayed());
    }

    @Then("^I should see Coffee link$")
    public void isFooterCoffeeDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterCoffeeDisplayed());
    }

    @Then("^I should see footer Order Status link$")
    public void isFooterOrderStatusLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterOrderStatusLinkDisplayed());
    }

    @Then("^I should see footer Register my Guarantee link$")
    public void isFooterRegisterMyGuaranteeLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterRegisterMyGuaranteeLinkDisplayed());
    }

    @Then("^I should see footer Register my Warranty link$")
    public void isFooterRegisterMyWarrantyLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterRegisterMyWarrantyLinkDisplayed());
    }

    @Then("^I should see footer My Account link$")
    public void isFooterMyAccountLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterMyAccountLinkDisplayed());
    }

    @Then("^I should see footer Unsubscribe link$")
    public void isFooterUnsubscribeLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterUnsubscribeLinkDisplayed());
    }

    @Then("^I should see footer About heading$")
    public void isFooterAboutHeadingDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterAboutHeadingDisplayed());
    }

    @Then("^I should see footer Technologies link$")
    public void isFooterTechnologiesLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterTechnologiesLinkDisplayed());
    }

    @Then("^I should see footer Shark Brand link$")
    public void isFooterSharkBrandLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterSharkBrandLinkDisplayed());
    }

    @Then("^I should see footer Shark Ninja Corporate link$")
    public void isFooterSharkNinjaCorporateLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterSharkNinjaCorporateLinkDisplayed());
    }

    @Then("^I should see footer Locations link$")
    public void isFooterLocationsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterLocationsLinkDisplayed());
    }

    @Then("^I should see footer Patents link$")
    public void isFooterPatentsLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterPatentsLinkDisplayed());
    }

    @Then("^I should see footer careers link$")
    public void isFooterCareersLinkDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterCareersLinkDisplayed());
    }

    @Then("^I should see footer Social heading displayed$")
    public void isFooterSocialHeadingDisplayed() {
        Assert.assertTrue(homePageSNUS.isFooterSocialHeadingDisplayed());
    }

    @When("^I hover on products link in header$")
    public void hoverProductsLink() {
        Assert.assertTrue(homePageSNUS.hoverProductsLink());
    }

    @When("^click first category in list$")
    @And("^click upright vacuum category$")
    public void clickUprightVacuum() throws InterruptedException {
        Assert.assertTrue(homePageSNUS.clickUprightVacuum());
        Thread.sleep(500);
    }

    @And("^click upright vacuum category for shark$")
    public void clickUprightVacuumForShark() throws InterruptedException {
        Assert.assertTrue(homePageSNUS.clickUprightVacuumForShark());
        Thread.sleep(500);
    }

    @And("^product list should be displayed$")
    public void verifyProductList() {
        Assert.assertTrue(homePageSNUS.verifyProductList());
    }

    @When("^I then click on cart link$")
    public void i_then_click_on_cart_link() {
        Assert.assertTrue(homePageSNUS.toClickCartLink(),"Cart not found/clicked");
    }

    @Then("^I must see empty cart message$")
    public void toVerifyCartMessage() {
        Assert.assertEquals(homePageSNUS.toGetEmptyCartMessage(),Props.getProp("minicart.message.popup"));
    }

    @Then("^I must see empty cart message if no item available$")
    public void toVerifyCartMessageIfNoItemAvailable() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        homePageSNUS.toGetEmptyCartMessageIfNoItemAvailable();
    }

    @Then("^I must be on tips and advice page$")
    public void toVerifyTipsAndAdvicePage() throws Exception {
//        switchBetweenWindowTabs(1);
        IsPageLoaded.waitAllRequest();
        String expected = Props.getProp("site.tips.url");
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains(expected)),"Actual=" + getCurrentUrl() + " expected="+ expected);
//        switchBetweenWindowTabs(0);
    }

    // Verifying through navigation
    @Then("^I must be on customer care page$")
    public void toVerifyCustomerCarePage() throws Exception {
//        switchBetweenWindowTabs(1);
        IsPageLoaded.waitAllRequest();
        String expected = Props.getProp("site.support.url");
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains(expected)),"Actual=" + getCurrentUrl() + " expected="+ expected);
//        switchBetweenWindowTabs(0);
    }

    @When("^I click on Parts and Accessories$")
    public void iClickOnPartsAndAccessories() {
           Assert.assertTrue(homePageSNUS.clickPartsAndAccessoriesLink(),"Parts & Accessories link not found/clicked");
    }


    @Then("^I navigate to chosen parts and accessories page$")
    public void iNavigateToChosenPartsAndAccessoriesPage() {
        Assert.assertTrue(partsAndAccessoriesPageSNCA.checkPartsAndAccessoriesPageFound(),"Parts & Accessories link not found/clicked");
    }
}
