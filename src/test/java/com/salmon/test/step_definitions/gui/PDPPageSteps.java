package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.java.en.And;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.salmon.test.framework.helpers.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class PDPPageSteps extends PageObject {

	private PDPPage pdpPage;
	//private SearchPage searchpage;
	private CartPage cartPage;
	private SearchPage searchPage;
	private CartOverlayPage cartOverLayPage;

	String first_search_product;
	public static List<String> productTitle =new ArrayList<>();
	public static List<String> productModel = new ArrayList<>();
	public static List<Float> productPrice=new ArrayList<>();



	public PDPPageSteps() {
		this.pdpPage = new PDPPage();
		this.cartOverLayPage = new CartOverlayPage();
		this.cartPage = new CartPage();
		this.searchPage=new SearchPage();

	}

	@Then("^I should see add to Basket button$")
	public void verifyAddToBasketButton() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getAddToBasketButton())));
	}
	@Then("^I should see add quantity field$")
	public void verifyAddquantityField() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getAddQuantityField())));
	}
	@Then("^I should see Features link in footer$")
	public void verifyFeatureLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getFeatureLink())));
	}
	@Then("^I should see Tech spec link in footer$")
	public void verifyTechSpecLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getTechSpecLink())));
	}
	@Then("^I should see In The Box link in footer$")
	public void verifyInTheBoxLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getInTheBoxLink())));
	}
	@Then("^I should see FAQs link in footer$")
	public void verifyFAQLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getFAQLink())));
	}
	@Then("^I should see Downloads link in footer$")
	public void verifyDownloadsLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getDownloadsLink())));
	}
	
	@Then("^I should see Parts/Accessories link in footer$")
	public void verifyPartsAccessoriesLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getpartsAccessoriesLink())));
	}
	
	@Then("^I should see MoreDetails link in footer$")
	public void verifyMoreDetailsLink() throws InterruptedException {
		Assert.assertTrue((pdpPage.isElementPresent(pdpPage.getMoreDetailsLink())));
	}


	@Then("^Verify PDP is displayed corresponding to clicked product$")
	public void verifyPDPIsdisplayed() throws InterruptedException {
		String info = Props.getProp("pdp.moreinfo");
		Thread.sleep(2000);
		String PDPlink=pdpPage.PDPSearchProductlink().getText()+'\n'+pdpPage.productPrice().getText()+'\n'+info;
		System.out.println("PDPlink ---"+PDPlink);
		Assert.assertTrue(pdpPage.VerifyPDPPageCorrespondingToSearch(PDPlink));
	}

	@When("^I should verify all the images in PLP page$")
	public void iShouldVerifyAllTheImagesInPLPPage() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyPLPPageImages());
	}

	@When("^I should verify all the images in PLP page for DE$")
	public void iShouldVerifyAllTheImagesInPLPPageForDE() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyPLPPageImagesDE());
	}

	@Then("^I should verify all the images in Parts & Accessories page_0$")
	public void iShouldVerifyAllTheImagesInPartsAccessoriespage0() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyProductAccessoriesPage0_Images());
	}

	@Then("^I should verify the text Shark in the Auto Suggestive Dropdown List$")
	public void IshouldverifythetextSharkintheAutoSuggestiveDropdownList() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifySharkTextInAutoSuggestiveDropDown());
	}

	@Then("^I should verify the text Ninja in the Auto Suggestive Dropdown List$")
	public void IshouldverifythetextNinjaintheAutoSuggestiveDropdownList() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyNinjaTextInAutoSuggestiveDropDown());
	}

	@Then("^I Should Verify That The Auto Suggestive Dropdown Should Not Be Displayed$")
	public void IShouldVerifyThatTheAutoSuggestiveDropdownShouldNotBeDisplayed() throws InterruptedException {
		Assert.assertFalse(pdpPage.AutoSuggestiveDropDownWindow());
	}
	@Then("^I should verify all the images in Products Category for all pages for CA$")
	public void I_should_verify_all_the_images_in_Products_Category_for_all_pages_for_CA() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyProductCategoryAllPages_Images_CA());

	}

	//VerifyProductCategoryAllPages_Images()
	@Then("^I should verify all the images in Parts & Accessories page_3$")
	public void iShouldVerifyAllTheImagesInPartsAccessoriespage3() throws InterruptedException {
		Assert.assertTrue(pdpPage.VerifyProductAccessoriesPage3_Images());
	}
	@Then("^Verify PDP is displayed corresponding to clicked product for Ninja DE$")
	public void verifyPDPIsdisplayedForNinjaDE() throws InterruptedException {
		String info = Props.getProp("pdp.moreinfo");
		Thread.sleep(2000);
		String PDPlink=pdpPage.PDPSearchProductlink().getText()+'\n'+pdpPage.productPrice().getText()+'\n'+info;
		System.out.println("PDPlink ---"+PDPlink);
		Assert.assertTrue(pdpPage.VerifyPDPPageCorrespondingToSearchForNinjaDE(PDPlink));
	}
	
	@When("^I click Features link in footer$")
	public void clickFeaturesLink() {
		pdpPage.clickFeaturesLink();
	}
	@When("^I click Tech spec link in footer$")
	public void clickTechSpecLink() {
		pdpPage.clickTechSpecLink();
	}
	@When("^I click More Details link in footer$")
	public void clickMoreDetailsLink() {
		pdpPage.clickMoreDetailsLink();
	}
	@When("^I click In The Box  link in footer$")
	public void clickInTheBoxink() {
		pdpPage.clickInTheBoxink();
	}
	@When("^I click FAQs link in footer$")
	public void clickFAQsLink() {
		pdpPage.clickFAQsLink();
	}
	@When("^I click Parts/Accessories  link in footer$")
	public void clickPartsLink() {
		pdpPage.clickPartsLink();
	}
	
	@When("^I click Downloads  link in footer$")
	public void clickDownloadsLink() {
		pdpPage.clickDownloadsLink();
	}
	
	@Then("^Features div should be displayed$")
	public void verifyFeatureDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue((pdpPage.isfeatureDivDisplayed()));

	}
	@Then("^Parts/Accessories  div should be diplayed$")
	public void verifyPartsDiv() throws InterruptedException {
		Assert.assertTrue(pdpPage.isPartsDivDisplayed());
		Thread.sleep(3000);
	}
	@Then("^Downloads div should be displayed$")
	public void verifyDownloadsDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue(pdpPage.isDownloadsDivDisplayed());
	}
	
	@Then("^FAQs div should be displayed$")
	public void verifyFAQsDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue(pdpPage.isFAQsDivDisplayed());
	}
	
	@Then("^Tech spec div should be displayed$")
	public void verifyTechSpecDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue(pdpPage.isTechSpecDivDisplayed());

	}
	@Then("^More Details div should be displayed$")
	public void verifyMoreDetailsDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue(pdpPage.isMoreDetailsDivDisplayed());

	}
	@Then("^In The Box  div should be displayed$")
	public void verifyInTheBoxDiv() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		Assert.assertTrue(pdpPage.isInTheBoxDivDisplayed());
	}
	
	@When("^I update product quantity with \"([^\"]*)\"$")
	public void updateProductQuantity(String productQuantity) throws InterruptedException {
		String productQty = Props.getProp(productQuantity);
		pdpPage.updateProductQuantity(productQty);
		Thread.sleep(1000);
	}
	
	@When("^I should see the the cart is updated with \"([^\"]*)\"$")
	public void cartupdateProductQuantity(String productQuantity) throws InterruptedException {
		String productQty1 = Props.getProp(productQuantity);
		System.out.println("Quantity is: " +cartPage.getProductQuantity());
		Assert.assertEquals(cartPage.getProductQuantity().get(0),productQty1,"quantity is successfully Updated");
	}
	
	@Then("^I should see error message that please enter 1 or more$")
    public void verifyErrorMessageForNegativeQuantity()
    {
        Assert.assertTrue(pdpPage.isErrorMessageDisplayedPleaseEnter_1_or_more());
    }
	@Then("^Add to basket button is enabled$")
    public void verifyAddToBasketButtonisEnabled()
    {
        Assert.assertTrue(pdpPage.isAddToBasketEnabled());
    }
	@Then("^I should see error message that Please enter 100 or less$")
    public void verifyErrorMessageForMaximumQuantity()
    {
        Assert.assertTrue(pdpPage.isErrorMessageDisplayedPleaseEnter_100_or_less());
    }

	
	@When("^I add the product to cart \"([^\"]*)\"$")
	public void i_add_the_product_to_cart(String productSpecificSKU) {
		Assert.assertTrue(pdpPage.addToCartButton(),"Unable to click on ADD TO BASKET button ");
		cartOverLayPage.goToBasketLink();
		Assert.assertEquals(cartPage.getCartPageHeading(),Props.getProp("cart.page.heading"));
	}

	@When("^I click on Add To Basket Button$")
	public void click_add_to_Basket() {
//		pdpPage.addToCartButton();
//		try{
//			Assert.assertEquals(cartPage.getCartPageHeading(),Props.getProp("cart.page.heading"));
//		}catch (Throwable e)
//		{
//			cartOverLayPage.goToBasketLink();
//			Assert.assertEquals(cartPage.getCartPageHeading(),Props.getProp("cart.page.heading"));
//		}

		Assert.assertTrue(pdpPage.addToCartButton(),"Unable to click on ADD TO BASKET button ");
		cartOverLayPage.goToBasketLink();
		Assert.assertEquals(cartPage.getCartPageHeading(),Props.getProp("cart.page.heading"));
	}

	@And("^I note the price of the product$")
	public void noteThePriceOfTheProduct(){
		SoftAssert softAssert=new SoftAssert();
		float price=pdpPage.getProductPrice();
		productPrice.add(price);
		softAssert.assertTrue(price > 0.0);
		softAssert.assertAll();
	}

	@Then("^Paypal Pay later text should be present$")
	public void paypalPayLaterTextShouldBePresent() {
		String message=pdpPage.getPayPalPayLaterMessageonPDP();
		String expectedMessage=Props.getProp("paypalPayLater.message.checkout");
		Assert.assertTrue(message.contains(expectedMessage),"Pay Pal Pay Later message is not present on PDP page. Expected message="+expectedMessage+" Actual Message ="+message);

	}

	@Then("^Paypal Credit text should be present$")
	public void paypalCreditTextShouldBePresent() {
		String message=pdpPage.getPayPalCreditMessageonPDP();
		String expectedMessage=Props.getProp("paypalCredit.message.checkout");
		Assert.assertTrue(message.contains(expectedMessage),"Pay Pal Credit message is not present on PDP page. Expected message="+expectedMessage+" Actual Message ="+message);

	}
}


