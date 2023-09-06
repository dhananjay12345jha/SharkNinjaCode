package com.salmon.test.step_definitions.gui.MicroSiteSharkUK;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSCartPage;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSCartOverlayPage;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class MSHomePageSteps {

	private MSHomePage MSHomePage;
	private MSCartOverlayPage MSCartOverLayPage;
	private MSCartPage MSCartPage;

	public MSHomePageSteps() {
		this.MSHomePage = new MSHomePage();
		this.MSCartOverLayPage = new MSCartOverlayPage();
		this.MSCartPage = new MSCartPage();
	}
	
	
	@Then("^I should see product tiles$")
	public void verifyProductTilesShown() {
		Assert.assertTrue(MSHomePage.verifyProductTilesShown());
	}


	@When("^I select last product tile$")
	public void selectLastProductTile() {
		Assert.assertTrue(MSHomePage.selectTile());
	}


	@When("^I add product to cart$")
	public void addProductToCart() {
		Assert.assertTrue(MSHomePage.addProductToCart());
		if (MSCartOverLayPage.isElementPresent(MSCartOverLayPage.checkIsCartOverLaypagePresent())) {
			if(MSCartOverLayPage.checkIsProceedLinkPresent()) {
				Assert.assertTrue(MSCartOverLayPage.clickProceed());
			}
			if(MSCartOverLayPage.checkIsCheckoutButtonPresent()) {
				Assert.assertTrue(MSCartOverLayPage.goToCheckout());
			}
		}else {
			Assert.assertEquals(MSCartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
		}
	}

	@And("^I click and navigate to the corresponding tiles$")
	public void iClickAndNavigateToTheCorrespondingTiles() throws InterruptedException {
		MSHomePage.verifyClickandNavidate();
	}
}
