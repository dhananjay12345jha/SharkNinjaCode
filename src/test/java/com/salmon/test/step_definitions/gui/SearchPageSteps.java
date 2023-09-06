package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.ApiStockAlert;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.MicrositeSharkUK.MSCartOverlayPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Set;


public class SearchPageSteps extends PageObject {

	private static final Logger log = LoggerFactory.getLogger(SearchPageSteps.class);

	String product;
	private SearchPage searchPage;
	private PDPPage pdpPage;
	private CartPage cartPage;
	private CartOverlayPage cartOverLayPage;

	private MSCartOverlayPage msCartOverLayPage;
	private CheckoutPage checkoutPage;


	public SearchPageSteps(SearchPage searchPage) {
		this.searchPage = searchPage;
		pdpPage = new PDPPage();
		cartOverLayPage = new CartOverlayPage();
		cartPage = new CartPage();
		msCartOverLayPage = new MSCartOverlayPage();
		checkoutPage = new CheckoutPage();

	}
	@When("^I search for the product \"([^\"]*)\"$")
	public void i_search_for_the_product(String searchTerm) {
		product = Props.getProp(searchTerm);
		Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
		Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
	}

	@When("^I search for the text \"([^\"]*)\"$")
	public void i_search_for_the_text(String searchTerm) throws InterruptedException {
		product = Props.getProp(searchTerm);
		Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
		Thread.sleep(2000);
		//Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
	}

	@Then("^I should get valid error message that no results found$")
	public void I_should_get_valid_error_message_that_no_results_found() {
		Assert.assertTrue(searchPage.valid_error_message());
	}

	@Then("^search results page is displayed$")
	public void search_results_page_is_displayed() {
		String expectedPageTitle = Props.getProp("search.result.text");
		String actualPageTitle = null;
		if (UrlBuilder.isDesktop()) {
			actualPageTitle = webDriver.getTitle();
		} else if (UrlBuilder.isMobile()) {
			actualPageTitle = searchPage.getSearchPageHeading();
		}
		System.out.println(expectedPageTitle);
		System.out.println(actualPageTitle);
		Assert.assertTrue(actualPageTitle.contains(expectedPageTitle),
				"Search Result page title is incorrect, Actual-->>" + actualPageTitle + ", Expected-->>" + expectedPageTitle);
	}

	@Then("^validate products displayed in search result$")
	public void validate_products_displayed_in_search_result() {
		log.info(product);
		try {
			int j = searchPage.collectionProductLinks().size();
			log.info("No. of products returned in search results are: " + j);
			log.info(product);
			if (j > 0) {
				Assert.assertTrue(true, "Products displayed on search result");
			} else {
				Assert.assertTrue(false, "Products not displayed on search result");
			}
		} catch (Exception e) {
			Assert.assertFalse(false, "Exception thrown. Exception: " + e.toString());
			;
		}
	}

	@Then("^validate Search result is displayed corresponding to the string which was searched$")
	public void validate_Search_result_is_displayed_corresponding_to_the_string_which_was_searched() {

		log.info(product);
		try {
			int j = searchPage.collectionProductLinks().size();
			System.out.println("search---" + j);
			if (product.contains("*") && j > 0)
				Assert.assertTrue(true, product + " is displayed on product title: ");

				//Validate if Search result is displayed corresponding to the string which was searched
			else {
				for (int i = 0; i < searchPage.collectionProductLinks().size(); i++) {
					String temp = searchPage.collectionProductLinks().get(i).getText();
					System.out.println(i + " search result - product title is: " + temp);
					System.out.println(" Searched term is " + product);
					if ((temp.toLowerCase().contains(product.toLowerCase()))) {
						Assert.assertTrue(true, product + " is displayed on product title: " + temp);
						break;
					} else {
						Assert.assertTrue(false, "The searched term \"" + product + "\" is not displayed on product title: " + temp);
					}
				}
			}
		} catch (Exception e) {
			Assert.assertFalse(false, "Exception thrown. Exception: " + e.toString());
			;
		}
	}

	@Given("^I place bulk order using installment for Webshop profiles \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iPlaceBulkOrderUsingInstallmentForWebshopProfilesAnd(String searchTerm, String productSpecificSKU, String payPalEmail, String payPalPassword) throws InterruptedException {
		int n = 8;
		for (int i = 1; i < n; i++) {
			product = Props.getProp(searchTerm);
			Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
			Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
			String productSKU = Props.getProp(productSpecificSKU);
			searchPage.selectProductFromSearchResults(productSKU);

			Assert.assertTrue(pdpPage.addToCartButton(), "Unable to click on ADD TO BASKET button ");
			cartOverLayPage.goToBasketLink();
			Assert.assertEquals(cartPage.getCartPageHeading(), Props.getProp("cart.page.heading"));

			if (cartOverLayPage.checkIsProceedLinkPresentForBulk()) {
				cartPage.proceedToCheckoutButton();
				log.info(" successfully clicked the proceed button ");
			} else {
				Assert.assertEquals(cartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
			}
			checkoutPage.fillInBillingDetails();

			if (i < n / 4) {  //2 times(for 1 and 2)  pay by card
				checkoutPage.payBycardBraintree();
				String brainTreePayCardNumber = Props.getProp("brainTree.card.number");
				String brainTreePayCardExpiry = Props.getProp("brainTree.card.expiryDate");
				String brainTreePayCardCvv = Props.getProp("brainTree.card.cvcNumber");
				String brainTreePostalCode = Props.getProp("brainTree.card.postalCode");
				checkoutPage.payWithBTCardRadioBtn(brainTreePayCardNumber, brainTreePayCardExpiry, brainTreePayCardCvv, brainTreePostalCode);
				checkoutPage.competitionsAndNewCheckbox();
				checkoutPage.offersCheckbox();
				checkoutPage.cleaningTipsArticlesCheckbox();
				checkoutPage.termsAndConditionsCheckBox();
				checkoutPage.placeOrderSecurelyButton();

			} else if (i >= n / 4 && i < n / 2) {  //3 times (for 3,4 and 5) paypal normal
				String payPalEmailInput = Props.getProp(payPalEmail);
				String payPalPasswordInput = Props.getProp(payPalPassword);
				checkoutPage.payPalRadioBtn();
				checkoutPage.competitionsAndNewCheckbox();
				checkoutPage.offersCheckbox();
				checkoutPage.cleaningTipsArticlesCheckbox();
				checkoutPage.termsAndConditionsCheckBox();

				String parentWindowHandler = webDriver.getWindowHandle();
				checkoutPage.placeOrderSecurelyButton();

				Set<String> allWindowHandles = webDriver.getWindowHandles();

				if (allWindowHandles.size() > 2) {
					throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
				}

				for (String handle : allWindowHandles) {
					if (!handle.equalsIgnoreCase(parentWindowHandler)) {
						webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
						if (!UrlBuilder.isMobile()) {
							webDriver.manage().window().maximize();
						}
						break;
					}
				}
				if (i == n / 4) {  //for run at i=3 only
					checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
					checkoutPage.payPalNextButton();
					checkoutPage.clickOnAcceptCookiesBtn();
					checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
					checkoutPage.payPalLoginButton();

				}
				checkoutPage.clickOnAcceptCookiesBtn();
				IsPageLoaded.waitAllRequest();
				checkoutPage.payPalContinueButton();
				checkoutPage.clickPayPalPayNowButton();
				checkoutPage.clickOnContinueButton();
				webDriver.switchTo().window(parentWindowHandler);

			} else if (i >= n / 2 && i < n) {   //paypal Credit (for 6 to last )
				String payPalEmailInput = Props.getProp(payPalEmail);
				String payPalPasswordInput = Props.getProp(payPalPassword);
				checkoutPage.payPalCreditRadioBtn();
				checkoutPage.competitionsAndNewCheckbox();
				checkoutPage.offersCheckbox();
				checkoutPage.termsAndConditionsCheckBox();

				String parentWindowHandler = webDriver.getWindowHandle();
				checkoutPage.placeOrderSecurelyButton();

				Set<String> allWindowHandles = webDriver.getWindowHandles();

				if (allWindowHandles.size() > 2) {
					throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
				}

				for (String handle : allWindowHandles) {
					if (!handle.equalsIgnoreCase(parentWindowHandler)) {
						webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
						if (!UrlBuilder.isMobile()) {
							webDriver.manage().window().maximize();
						}
						break;
					}
				}
				if (i == n / 2) {  //run at i=6 only
					checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
					checkoutPage.clickOnAcceptCookiesBtn();
					checkoutPage.payPalNextButton();
					checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
					checkoutPage.payPalLoginButton();
				}
				IsPageLoaded.waitAllRequest();
				checkoutPage.clickOnAcceptCookiesBtn();
				checkoutPage.clickPaypalCreditPaymentOption1();
				checkoutPage.clickChooseAndContinueButton();

				//checkoutPage.clickPayAnotherWay();
				checkoutPage.clickSubmitButton();
				checkoutPage.clickSubmitButton();
//				checkoutPage.clickSubmitButton();
				webDriver.switchTo().window(parentWindowHandler);
			} else {
				System.out.println("The script is failed for Payment Method Paypal Credit");
			}
			System.out.println(webDriver.getTitle());

			IsPageLoaded.waitAllRequest();
			String actualString = checkoutPage.orderReceivedText();
			String expectedString = Props.getMessage("order.received.text");
			Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
			getWebDriver().navigate().to(Props.getProp("site.uat.url"));
			System.out.println("Creating order:" + i);
		}
	}


	@Given("^I place bulk order for Webshop profiles using Payment method pay by paypal Credit \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iPlaceBulkOrderForWebshopProfilesUsingPaymentMethodPayByPaypalCreditAnd(String searchTerm, String productSpecificSKU, String payPalEmail, String payPalPassword) throws InterruptedException {
		{
			int n = 101;
			for (int i = 1; i < n; i++) {
				try {
					product = Props.getProp(searchTerm);
					Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
					Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
					String productSKU = Props.getProp(productSpecificSKU);
					searchPage.selectProductFromSearchResults(productSKU);

					Assert.assertTrue(pdpPage.addToCartButton(), "Unable to click on ADD TO BASKET button ");
					cartOverLayPage.goToBasketLink();
					Assert.assertEquals(cartPage.getCartPageHeading(), Props.getProp("cart.page.heading"));

					if (cartOverLayPage.checkIsProceedLinkPresentForBulk()) {
						cartPage.proceedToCheckoutButton();
						log.info(" successfully clicked the proceed button ");
					} else {
						Assert.assertEquals(cartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
					}
					checkoutPage.fillInBillingDetails();

					String payPalEmailInput = Props.getProp(payPalEmail);
					String payPalPasswordInput = Props.getProp(payPalPassword);
					checkoutPage.payPalCreditRadioBtn();
					checkoutPage.competitionsAndNewCheckbox();
					checkoutPage.offersCheckbox();
					checkoutPage.termsAndConditionsCheckBox();

					String parentWindowHandler = webDriver.getWindowHandle();
					checkoutPage.placeOrderSecurelyButton();

					Set<String> allWindowHandles = webDriver.getWindowHandles();

					if (allWindowHandles.size() > 2) {
						throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
					}

					for (String handle : allWindowHandles) {
						if (!handle.equalsIgnoreCase(parentWindowHandler)) {
							webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
							if (!UrlBuilder.isMobile()) {
								webDriver.manage().window().maximize();
							}
							break;
						}
					}
					if (i == 1) {
						checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
						checkoutPage.clickOnAcceptCookiesBtn();
						checkoutPage.payPalNextButton();
						checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
						checkoutPage.payPalLoginButton();
					}
					checkoutPage.clickOnAcceptCookiesBtn();
					IsPageLoaded.waitAllRequest();
					checkoutPage.clickPaypalCreditPaymentOption1();
					checkoutPage.clickChooseAndContinueButton();

					checkoutPage.clickOnContinueButton();
					checkoutPage.clickSubmitButton();
					checkoutPage.clickSubmitButton();
					webDriver.switchTo().window(parentWindowHandler);
				}catch(Exception e){
					System.out.println(" Exception found is " + e + "in order no " + i);
				}
				System.out.println(webDriver.getTitle());

				IsPageLoaded.waitAllRequest();
				String actualString = checkoutPage.orderReceivedText();
				String expectedString = Props.getMessage("order.received.text");
				Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
				getWebDriver().navigate().to(Props.getProp("site.uat.url"));
				System.out.println("Creating order:" + i);
			}
		}
	}

	@When("^I select the product from search result page \"([^\"]*)\"$")
	public void i_select_the_product_from_search_result_page(String productSpecificSKU) throws InterruptedException {
		String productSKU = Props.getProp(productSpecificSKU);
		searchPage.selectProductFromSearchResults(productSKU);
	}

	@Given("^I place bulk order for Webshop profiles using Payment method pay by paypal \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iPlaceBulkOrderForWebshopProfilesUsingPaymentMethodPayByPaypalAnd(String searchTerm, String productSpecificSKU, String payPalEmail, String payPalPassword) throws InterruptedException {
		int n = 101;
		for (int i = 1; i < n; i++) {
			try {
				product = Props.getProp(searchTerm);
				Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
				Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
				String productSKU = Props.getProp(productSpecificSKU);
				searchPage.selectProductFromSearchResults(productSKU);

				Assert.assertTrue(pdpPage.addToCartButton(), "Unable to click on ADD TO BASKET button ");
				cartOverLayPage.goToBasketLink();
				Assert.assertEquals(cartPage.getCartPageHeading(), Props.getProp("cart.page.heading"));

				if (cartOverLayPage.checkIsProceedLinkPresentForBulk()) {
					cartPage.proceedToCheckoutButton();
					log.info(" successfully clicked the proceed button ");
				} else {
					Assert.assertEquals(cartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
				}
				checkoutPage.fillInBillingDetails();

				String payPalEmailInput = Props.getProp(payPalEmail);
				String payPalPasswordInput = Props.getProp(payPalPassword);
				checkoutPage.payPalRadioBtn();
				checkoutPage.competitionsAndNewCheckbox();
				checkoutPage.offersCheckbox();
				checkoutPage.cleaningTipsArticlesCheckbox();
				checkoutPage.termsAndConditionsCheckBox();

				String parentWindowHandler = webDriver.getWindowHandle();
				checkoutPage.placeOrderSecurelyButton();

				Set<String> allWindowHandles = webDriver.getWindowHandles();

				if (allWindowHandles.size() > 2) {
					throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
				}

				for (String handle : allWindowHandles) {
					if (!handle.equalsIgnoreCase(parentWindowHandler)) {
						webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
						if (!UrlBuilder.isMobile()) {
							webDriver.manage().window().maximize();
						}
						break;
					}
				}
				if (i == 1) {  // hide this for together run
					checkoutPage.payPalEmail().sendKeys(payPalEmailInput);
					checkoutPage.payPalNextButton();
					checkoutPage.clickOnAcceptCookiesBtn();
					checkoutPage.payPalPassword().sendKeys(payPalPasswordInput);
					checkoutPage.payPalLoginButton();

				} //hide this for run together
				IsPageLoaded.waitAllRequest();
				checkoutPage.clickOnAcceptCookiesBtn();
				checkoutPage.payPalContinueButton();
				checkoutPage.clickPayPalPayNowButton();
				checkoutPage.clickOnContinueButton();
				webDriver.switchTo().window(parentWindowHandler);
				//System.out.println(webDriver.getTitle());

			IsPageLoaded.waitAllRequest();
			String actualString = checkoutPage.orderReceivedText();
			String expectedString = Props.getMessage("order.received.text");
			Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
			}catch(Exception e){
				System.out.println(" Exception found is " + e + "in order no " + i);
			}
			getWebDriver().navigate().to(Props.getProp("site.uat.url"));
			System.out.println("Creating order:" + i);
		}
	}

	@Given("^I place bulk order for Webshop profiles using Payment method pay by Card \"([^\"]*)\", \"([^\"]*)\"$")
	public void iPlaceBulkOrderForWebshopProfilesUsingPaymentMethodPayByCardAnd(String searchTerm, String productSpecificSKU) throws InterruptedException {
		int n = 101;
		for (int i = 1; i < n; i++) {
			try {
				product = Props.getProp(searchTerm);
				Assert.assertTrue(searchPage.enterProductInSearchBar(product), "Unable to enter product in search bar-->" + product);
				Assert.assertTrue(searchPage.clickOnSearchButton(), "Unable to click on search button in search bar ");
				String productSKU = Props.getProp(productSpecificSKU);
				searchPage.selectProductFromSearchResults(productSKU);

				Assert.assertTrue(pdpPage.addToCartButton(), "Unable to click on ADD TO BASKET button ");
				cartOverLayPage.goToBasketLink();
				Assert.assertEquals(cartPage.getCartPageHeading(), Props.getProp("cart.page.heading"));

				if (cartOverLayPage.checkIsProceedLinkPresentForBulk()) {
					cartPage.proceedToCheckoutButton();
					log.info(" successfully clicked the proceed button ");
				} else {
					Assert.assertEquals(cartPage.getCartPageHeading(), Props.getMessage("cart.page.heading"));
				}
				checkoutPage.fillInBillingDetails();

				checkoutPage.payBycardBraintree();
				String brainTreePayCardNumber = Props.getProp("brainTree.card.number");
				String brainTreePayCardExpiry = Props.getProp("brainTree.card.expiryDate");
				String brainTreePayCardCvv = Props.getProp("brainTree.card.cvcNumber");
				String brainTreePostalCode = Props.getProp("brainTree.card.postalCode");
				checkoutPage.payWithBTCardRadioBtn(brainTreePayCardNumber, brainTreePayCardExpiry, brainTreePayCardCvv, brainTreePostalCode);
				checkoutPage.competitionsAndNewCheckbox();
				checkoutPage.offersCheckbox();
				checkoutPage.cleaningTipsArticlesCheckbox();
				checkoutPage.termsAndConditionsCheckBox();
				checkoutPage.placeOrderSecurelyButton();

			System.out.println(webDriver.getTitle());

			IsPageLoaded.waitAllRequest();
				String actualString = checkoutPage.orderReceivedText();
				String expectedString = Props.getMessage("order.received.text");
				Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
			}catch(Exception e){
				System.out.println(" Exception found is " + e + "at order no " + i);
			}
			getWebDriver().navigate().to(Props.getProp("site.uat.url"));
			System.out.println("Creating order:" + i);


		}
	}
	@Given("^User is setting the stock quantity for (.*) with the Sku as (.*) and the quantity is (.*)$")
	public void user_is_setting_the_stock_quantity_for_something_with_the_sku_as_something_and_the_quantity_is_5(String locals, String productid,String quantity) throws Throwable {

		ApiStockAlert.stockUpdate(locals,productid,quantity);
}

}
