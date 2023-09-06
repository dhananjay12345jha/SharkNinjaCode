package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.CartOverlayPage;

import cucumber.api.java.en.When;

public class CartOverlayPageSteps {

	private CartOverlayPage cartOverlayPage;
	
	public CartOverlayPageSteps(CartOverlayPage cartOverlayPage) {
		
		this.cartOverlayPage = cartOverlayPage;		
	}
	
	
	@When("^I go to cart$")
	public void i_go_to_cart() throws InterruptedException {
		cartOverlayPage.goToBasketLink();
		Thread.sleep(10000);
	}
}
