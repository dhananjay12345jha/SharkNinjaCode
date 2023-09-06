package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderDetailsPage extends PageObject {

	private final By heading = By.xpath("//h1[@class='order-details-headline']");
	private final By orderDetails1 = By.xpath("//dl[@class='dl-horizontal dl-horizontal-inline dl-separator']");
	private final By parcelTracking = By.xpath("//dl[@class='dl-horizontal dl-horizontal-inline dl-separator']/dt[4]");
	private final By parcelProductID = By.xpath("//dl[@class='dl-horizontal dl-horizontal-inline dl-separator']/dd[4]/ul/li");
	private final By address = By.xpath("//div[@class='infobox']/address");
	private final By shippingDetails = By.xpath("//div[@class='col-md-9 account-detail-box']/div[@class='equal row'][2]/div[@class='col-xs-12 col-sm-6 col-lg-3 order-detail-box'][1]/div[@class='infobox']");
	private final By paymentDetails = By.xpath("//div[@class='col-md-9 account-detail-box']/div[@class='equal row'][2]/div[@class='col-xs-12 col-sm-6 col-lg-3 order-detail-box'][2]/div[@class='infobox']");
	private final By orderSummary = By.xpath("//div[@class='section cost-summary pull-right']");
	private final By totalPrice = By.xpath("//dd[@class='total-price']");
	private final By orderListHeader = By.xpath("//div[@class='list-header']");
	private final By orderedProductName = By.xpath("//a[@class='product-title']");
	private final By orderedProductQuantity = By.xpath("//div[@class='pli-description']/p[2]");
	private final By orderedProductStatus = By.xpath("//div[@class='col-sm-4 col-xs-12 list-item column-status']/p/span");
	private final By orderedProductPrice = By.xpath("//div[@class='col-sm-2 list-item hidden-xs column-price']");
	private final By mobileOrderedProductPrice = By.xpath("//div[@class='col-sm-2 col-xs-3 list-item column-price']");
	private final By orderedProductTotal = By.xpath("//div[@class='col-sm-2 col-xs-3 list-item column-price']/div[1]");
	private final By backToOrderlink = By.xpath("//div[@class='section']/a");
	private final By continueShoppingLink = By.xpath("//a[@class='pull-right']");
	
	
	
	public String getHeading() {
		return waitForExpectedElement(heading).getText();
	}
	
	public String getOrderNumber() {
		return waitForExpectedElement(orderDetails1).getText();
	}
	
	public String getDateOfOrder() {
		return waitForExpectedElement(orderDetails1).getText();
	}
	
	public String getOrderStatus() {
		return waitForExpectedElement(orderDetails1).getText();
	}
	
	public String getParcelTrackingHeading() {
		return waitForExpectedElement(parcelTracking).getText();
	}
	
	public String getParcelProductID() {
		return waitForExpectedElement(parcelProductID).getText();
	}
	
	public String getAddress() {
		return waitForExpectedElement(address).getText();
	}
	
	public String getShippingDetails() {
		return waitForExpectedElement(shippingDetails).getText();
	}
	
	public String getPaymentDetails() {
		return waitForExpectedElement(paymentDetails).getText();
	}
	
	public String getOrderSummary() {
		return waitForExpectedElement(orderSummary).getText();
	}
	
	public String getTotalPrice() {
		return waitForExpectedElement(totalPrice).getText();
	}
	
	public String getOrderListHeader() {
		return waitForExpectedElement(orderListHeader).getText();
	}
	
	public void clickBackToOrderLink() {
		waitForExpectedElement(backToOrderlink).click();
	}
	
	public void clickContinueShoppingLink() {
		waitForExpectedElement(continueShoppingLink).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(continueShoppingLink));
	}
	
	public String getOrderedProductName() {
		return waitForExpectedElement(orderedProductName).getText();
	}
	
	public String getOrderedProductQuantity() {
		return waitForExpectedElement(orderedProductQuantity).getText();
	}
	
	public String getOrderedProductStatus() {
		return waitForExpectedElement(orderedProductStatus).getText();
	}
	
	public String getOrderedProductPrice() {
		if (UrlBuilder.isDesktop()) {
			return waitForExpectedElement(orderedProductPrice).getText().trim();
		} else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			return waitForExpectedElement(mobileOrderedProductPrice).getText().trim();
		} else {
			return waitForExpectedElement(orderedProductPrice).getText().trim();
		}
	}
	
	public String getOrderedProductTotal() {
		return waitForExpectedElement(orderedProductTotal).getText();
	}
}
