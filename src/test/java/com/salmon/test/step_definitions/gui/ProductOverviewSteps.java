package com.salmon.test.step_definitions.gui;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.PDPPage;
import com.salmon.test.page_objects.gui.ProductOverviewPage;
import com.salmon.test.page_objects.gui.RegisterMyGuaranteePage;

import cucumber.api.java.en.Then;

public class ProductOverviewSteps extends PageObject{
	private ProductOverviewPage productOverviewPage;
	
	public ProductOverviewSteps() {
		this.productOverviewPage = new ProductOverviewPage();
	}
	
	
	@Then("^I can successfully view the registered guarantee$")
	public void verifyRegisteredGuarantee() throws InterruptedException {
//		Assert.assertTrue(productOverviewPage.getCurrentUrl().contains(Props.getProp("product.overview.page.url")));
		Assert.assertTrue(productOverviewPage.verifyRegisteredGuarantee());
		Thread.sleep(3000);
	}
}
