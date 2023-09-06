package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.PendingException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.salmon.test.framework.helpers.Props;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileReader;
import java.util.Properties;

//@AllArgsConstructor
public class MyAccountOverviewSteps extends PageObject {
	
	protected static final Logger LOG = LoggerFactory.getLogger(MyAccountOverviewSteps.class);
	private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");
	private final MyAccountOverviewPage myAccountPage;
	private final OrderHistoryPage orderHistoryPage;
	private final ProductOverviewPage productOverviewPage;
	private final PaymentInstrumentInfoPage paymentInstrumentInfoPage;
	private final OrderDetailsPage orderDetailsPage;
	private final InstalmentsDetailPage instalmentsDetailPage;
	private final HomePage homePage;

	public MyAccountOverviewSteps() {
		this.myAccountPage = new MyAccountOverviewPage();
		this.orderHistoryPage = new OrderHistoryPage();
		this.orderDetailsPage = new OrderDetailsPage();
		this.instalmentsDetailPage = new InstalmentsDetailPage();
		this.homePage = new HomePage();
		this.productOverviewPage = new ProductOverviewPage();
		this.paymentInstrumentInfoPage = new PaymentInstrumentInfoPage();
	}
	
	@When("^I click on My Orders$")
	public void clickMyOrder() {
		myAccountPage.clickMyOrder();
	}

	@Then("^I should be on Order History page$")
	public void verifyOrderHistoryPage() throws InterruptedException {
		if (UrlBuilder.isDesktop() || UrlBuilder.isTablet())
		{
			Assert.assertTrue(orderHistoryPage.getHeading().contentEquals(Props.getProp("order.history.page.heading")));
//			Assert.assertTrue(orderHistoryPage.getOrderDateHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderDate")));
//			Assert.assertTrue(orderHistoryPage.getOrderNumberHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderNumber")));
//			Assert.assertTrue(orderHistoryPage.getItemHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.items")));
//			Assert.assertTrue(orderHistoryPage.getStatusHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.status")));
//			Assert.assertTrue(orderHistoryPage.getDestinationHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.destination")));
//			Assert.assertTrue(orderHistoryPage.getOrdertotalHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderTotal")));
			Thread.sleep(3000);
		}
		else if(UrlBuilder.isMobile()) {
			Assert.assertTrue(orderHistoryPage.getHeading().contentEquals(Props.getProp("order.history.page.heading")));
			Thread.sleep(3000);
		}

	}

	//adding Sumeet code
	@And("^I should see Order list header$")
	public void verifyOrderListHeader() throws InterruptedException {
		Assert.assertTrue(orderHistoryPage.getOrderDateHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderDate")));	
		Assert.assertTrue(orderHistoryPage.getOrderNumberHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderNumber")));
		Assert.assertTrue(orderHistoryPage.getItemHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.items")));
		Assert.assertTrue(orderHistoryPage.getStatusHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.status")));
		Assert.assertTrue(orderHistoryPage.getDestinationHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.destination")));
		Assert.assertTrue(orderHistoryPage.getOrdertotalHeaderText().equalsIgnoreCase(Props.getProp("order.history.page.header.orderTotal")));
		Thread.sleep(3000);
	}

	@When("^I navigate to my Order \"([^\"]*)\"$")
	public void navigateToOrder(String orderNumber) throws InterruptedException {
		String orderID;
		if(orderNumber.equalsIgnoreCase("secureCheckout.orderNumber"))
		{
			 orderID=getUpdatedPropertyValue(propFilePath,orderNumber);
		}else {
			 orderID = Props.getProp(orderNumber);
		}
		Assert.assertTrue(orderHistoryPage.navigateToOrder(orderID),"Unable to find either property file or order ID in my account-->>"+orderID+", username="+Props.getProp("login.email")+", password="+Props.getProp("login.password"));
	}

	@Then("^I should see my Order$")
	public void verifyOrderList() {
		System.out.println("Props.getProp(\"order.history.page.orderDate\")--"+Props.getProp("order.history.page.orderDate"));
		Assert.assertTrue(orderHistoryPage.getOrderDate().contains(Props.getProp("order.history.page.orderDate")));
		Assert.assertTrue(orderHistoryPage.getOrderNumber().contains(Props.getProp("order.history.page.orderNumber")));
		Assert.assertTrue(orderHistoryPage.getItemNumber().contains(Props.getProp("order.history.page.items")));
		Assert.assertTrue(orderHistoryPage.getOrderStatus().contains(Props.getProp("order.history.page.status")));
		//Assert.assertTrue(orderHistoryPage.getOrderStatus().contains(Props.getProp("order.history.page.statusDueDateText")));
		//Assert.assertTrue(orderHistoryPage.getOrderStatus().contains(Props.getProp("order.history.page.statusDueDate")));
		Assert.assertTrue(orderHistoryPage.getOrderDestination().contains(Props.getProp("order.history.page.destinationName")));
		//Assert.assertTrue(orderHistoryPage.getOrderDestination().contains(Props.getProp("order.history.page.destinationAddress")));
		//Assert.assertTrue(orderHistoryPage.getOrderDestination().contains(Props.getProp("order.history.page.destinationCity")));
		//Assert.assertTrue(orderHistoryPage.getOrderDestination().contains(Props.getProp("order.history.page.destinationPostalCode")));
		Assert.assertTrue(orderHistoryPage.getOrderTotal().contains(Props.getProp("order.history.page.orderTotal")));
	}

	@Then("^I should see my installment Order$")
	public void verifyInstallmentOrderList() {
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderDate().contains(Props.getProp("order.history.page.installmentOrderDate")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderNumber().contains(Props.getProp("order.history.page.installmentOrderNumber")));
		Assert.assertTrue(orderHistoryPage.getInstallmentItemNumber().contains(Props.getProp("order.history.page.installmentOrderItems")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderStatus().contains(Props.getProp("order.history.page.installmentOrderStatus")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderDestination().contains(Props.getProp("order.history.page.installmentOrderDestinationName")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderDestination().contains(Props.getProp("order.history.page.installmentOrderDestinationAddress")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderDestination().contains(Props.getProp("order.history.page.installmentOrderDestinationCity")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderDestination().contains(Props.getProp("order.history.page.installmentOrderDestinationPostalCode")));
		Assert.assertTrue(orderHistoryPage.getInstallmentOrderTotal().contains(Props.getProp("order.history.page.installmentOrderTotal")));
	}
	
	@When("^I click to see Order details$")
	public void clickOrderDetails() {
		orderHistoryPage.clickOrderDetails();
	}

/*	@When("^I click on Instalment details$")
	public void clickInstalmentLink() {
		Assert.assertTrue(orderHistoryPage.clickInstalmentLink());
	}*/
	
	@Then("^I should be on Order details page$")
	public void verifyOrderDetailsPage() {
		Assert.assertTrue(orderDetailsPage.checkPageTitle(Props.getProp("order.details.page.title")));
		Assert.assertTrue(orderDetailsPage.getHeading().contentEquals(Props.getProp("order.details.page.heading")));
	}

	@Then("^I should see instalment details$")
	public void verifyInstalmentDetails() {
		Assert.assertTrue(instalmentsDetailPage.getHeading().contentEquals(Props.getMessage("instalment.page.heading")+Props.getProp("order.history.page.installmentOrderNumber")));
		Assert.assertTrue(instalmentsDetailPage.getInstallmentPlanHeaderText().contains(Props.getMessage("instalment.plan.heading")));
		Assert.assertTrue(instalmentsDetailPage.getInstallmentPlanHeaderText().contains(Props.getMessage("instalment.plan.status")));
		Assert.assertTrue(instalmentsDetailPage.getOutstandingBalanceHeader().contains(Props.getMessage("instalment.outstandingBalance.heading")));
		Assert.assertTrue(instalmentsDetailPage.getPaymentHistoryHeader().contains(Props.getMessage("instalment.payment.history.heading")));
	}
//
//	@Then("^I should see instalment page$")
//	public void verifyInstalmentpage() {
//		String orderID= getUpdatedPropertyValue(propFilePath,"order Number");
//		Assert.assertTrue(instalmentsDetailPage.getHeading().contentEquals(Props.getProp("instalment.page.heading")+" "+orderID));
//		}
	
	@And("^I should see Order information details$")
	public void verifyOrderInformationDetails() {
		Assert.assertTrue(orderDetailsPage.getOrderNumber().contains(Props.getProp("order.history.page.orderNumber")));
		Assert.assertTrue(orderDetailsPage.getDateOfOrder().contains(Props.getProp("order.history.page.orderDate")));
		Assert.assertTrue(orderDetailsPage.getOrderStatus().contains(Props.getProp("order.history.page.status")));
		//Assert.assertTrue(orderDetailsPage.getParcelTrackingHeading().contains(Props.getProp("order.details.page.parcelTrackingHeading")));
		//Assert.assertTrue(orderDetailsPage.getParcelProductID().contains(Props.getProp("order.details.page.parcelProductId")));
	}
	
	@And("^I should see Order address details$")
	public void verifyOrderAddressDetails() {
		Assert.assertTrue(orderDetailsPage.getAddress().contains(Props.getProp("order.history.page.destinationName")));
		Assert.assertTrue(orderDetailsPage.getAddress().contains(Props.getProp("order.history.page.destinationAddress")));
		Assert.assertTrue(orderDetailsPage.getAddress().contains(Props.getProp("order.history.page.destinationCity")));
		Assert.assertTrue(orderDetailsPage.getAddress().contains(Props.getProp("order.history.page.destinationPostalCode")));
		Assert.assertTrue(orderDetailsPage.getAddress().contains(Props.getProp("order.details.page.phone")));
	}
	
	@And("^I should see Order Shipping details$")
	public void verifyOrderShippingDetails() {
		Assert.assertTrue(orderDetailsPage.getShippingDetails().contains(Props.getProp("order.details.page.shippingHeading")));
		Assert.assertTrue(orderDetailsPage.getShippingDetails().contains(Props.getProp("order.details.page.shippingType")));
	}
	
	@And("^I should see Order payment details$")
	public void verifyOrderPaymentDetails() {
		Assert.assertTrue(orderDetailsPage.getPaymentDetails().contains(Props.getProp("order.details.page.paymentHeading")));
		Assert.assertTrue(orderDetailsPage.getPaymentDetails().contains(Props.getProp("order.details.page.paymentType")));
		Assert.assertTrue(orderDetailsPage.getPaymentDetails().contains(Props.getProp("order.details.page.paymentStatus")));
		Assert.assertTrue(orderDetailsPage.getPaymentDetails().contains(Props.getProp("order.details.page.paymentInvoiceAmount")));
	}
	
	@And("^I should see Order summary details$")
	public void verifyOrderSummaryDetails() {
		Assert.assertTrue(orderDetailsPage.getOrderSummary().contains(Props.getProp("order.details.page.orderSummaryHeading")));
		Assert.assertTrue(orderDetailsPage.getOrderSummary().contains(Props.getProp("order.details.page.subtotal")));
		Assert.assertTrue(orderDetailsPage.getOrderSummary().contains(Props.getProp("order.details.page.vat")));
		Assert.assertTrue(orderDetailsPage.getTotalPrice().contains(Props.getProp("order.details.page.totalPrice")));
	}
	
	@And("^I should see Ordered Item details$")
	public void verifyOrderedItemDetails() {
		if (UrlBuilder.isDesktop()) {
			System.out.println(orderDetailsPage.getOrderListHeader());
			Assert.assertTrue(orderDetailsPage.getOrderListHeader().contains(Props.getProp("item")));
			Assert.assertTrue(orderDetailsPage.getOrderListHeader().contains(Props.getProp("status")));
			Assert.assertTrue(orderDetailsPage.getOrderListHeader().contains(Props.getProp("price")));
			Assert.assertTrue(orderDetailsPage.getOrderListHeader().contains(Props.getProp("total")));
			//Assert.assertTrue(orderDetailsPage.getOrderedProductName().contains(Props.getProp("order.details.page.orderedProductName")));
			//Assert.assertTrue(orderDetailsPage.getOrderedProductQuantity().contains(Props.getProp("order.details.page.orderedProductQuantity")));
			//Assert.assertTrue(orderDetailsPage.getOrderedProductStatus().contains(Props.getProp("order.details.page.orderedProductStatus")));
			//Assert.assertTrue(orderDetailsPage.getOrderedProductPrice().contains(Props.getProp("order.details.page.orderedProductPrice")));
			//Assert.assertTrue(orderDetailsPage.getOrderedProductTotal().contains(Props.getProp("order.details.page.orderedProductTotal")));
		} else if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			String expected,actual;

			actual=orderDetailsPage.getOrderedProductName();
			expected=Props.getProp("order.details.page.orderedProductName");
			Assert.assertTrue(actual.contains(expected),"Actual=\""+actual+"\" should contains expected=\""+expected+"\"");

			actual=orderDetailsPage.getOrderedProductQuantity();
			expected=Props.getProp("order.details.page.orderedProductQuantity");
			Assert.assertTrue(actual.contains(expected),"Actual=\""+actual+"\" should contains expected=\""+expected+"\"");

			actual=orderDetailsPage.getOrderedProductStatus();
			expected=Props.getProp("order.details.page.orderedProductStatus");
			Assert.assertTrue(actual.contains(expected),"Actual=\""+actual+"\" should contains expected=\""+expected+"\"");

			actual=orderDetailsPage.getOrderedProductPrice();
			expected=Props.getProp("order.details.page.orderedProductPrice");
			Assert.assertTrue(actual.contains(expected),"Actual=\""+actual+"\" should contains expected=\""+expected+"\"");

			actual=orderDetailsPage.getOrderedProductTotal();
			expected=Props.getProp("order.details.page.orderedProductTotal");
			Assert.assertTrue(actual.contains(expected),"Actual=\""+actual+"\" should contains expected=\""+expected+"\"");
		}
	}
		
	
	@When("^I click on Back to orders link$")
	public void clickBackToOrderLink() {
		orderDetailsPage.clickBackToOrderLink();
	}
	
	@When("^I click on continue shopping link$")
	public void clickContinueShoppingLink() {
		orderDetailsPage.clickContinueShoppingLink();
	}
	
	@Then("^I should be on home page$")
	public void verifyHomePage() throws InterruptedException {

//		System.out.println(homePage.getCurrentUrl());
	//Assert.assertTrue(homePage.getCurrentUrl().contentEquals(Props.getProp("home.page.url")));
		Assert.assertTrue(webDriver.getTitle().contains(Props.getProp("home.page")));

//		Assert.assertTrue(productOverviewPage.getHeading().contentEquals(Props.getProp("product.overview.page.heading")));
		Thread.sleep(3000);
	}
	//ProductOverview
	
	@Then("^I Click on My Account$")
	public void clickOnMyAccount() {
		myAccountPage.clickOnMyAccount();
	}

	
	@When("^I click on Product Overview$")
	public void clickProductOverview() {
		myAccountPage.clickProductOverview();
	}
	
	@Then("^I should be on Product Overview page$")
	public void verifyProductOverviewPage() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
//		Assert.assertTrue(productOverviewPage.getCurrentUrl().contains(Props.getProp("product.overview.page.url")));
		LOG.info(productOverviewPage.getHeading());
		Assert.assertTrue(productOverviewPage.getHeading().contentEquals(Props.getProp("product.overview.page.heading")));
		Thread.sleep(3000);
	}
	
	@When("^I click on Payment$")
	public void clickPayment() {
		Assert.assertTrue(myAccountPage.clickPayment());
	}
	
	@Then("^I should be on Payment Instrument Info page$")
	public void verifyPaymentInstrumentInfoPage() throws InterruptedException {
//		Assert.assertTrue(paymentInstrumentInfoPage.getCurrentUrl().contains(Props.getProp("payment.instrument.info.page.url")));
		LOG.info(paymentInstrumentInfoPage.getHeading());
		Assert.assertTrue(paymentInstrumentInfoPage.getHeading().contentEquals(Props.getProp("payment.instrument.info.page.heading")));
	}
	
	@When("^I click on Addresses$")
	public void clickAddress() {
		Assert.assertTrue(myAccountPage.clickAddress());
	}

	@When("^I click on Account Details$")
	public void clickAccountDetails() {
		Assert.assertTrue(myAccountPage.clickAccountDetails());
	}


	@When("^I click on Instalment details for \"([^\"]*)\"$")
	public void iClickOnInstalmentDetailsFor(String orderNumber) {
		String orderID;
		if(orderNumber.equalsIgnoreCase("secureCheckout.orderNumber"))
		{
			 orderID=getUpdatedPropertyValue(propFilePath,orderNumber);
		}else {
			 orderID = Props.getProp(orderNumber);
		}
		Assert.assertTrue(orderHistoryPage.clickInstalmentLink(orderID));
	}


	@Then("^I should see instalment page for \"([^\"]*)\"$")
	public void iShouldSeeInstalmentPageFor(String orderNumber) throws Throwable {
		String orderID;
		if(orderNumber.equalsIgnoreCase("secureCheckout.orderNumber"))
		{
			orderID=getUpdatedPropertyValue(propFilePath,orderNumber);
		}else {
			orderID = Props.getProp(orderNumber);
		}
		String expected=Props.getProp("instalment.page.heading")+" "+orderID;
		String actual=instalmentsDetailPage.getHeading();
		Assert.assertTrue(actual.contentEquals(expected), "Actual= " + actual +" Expected= " +expected);
	}
}
