package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.step_definitions.gui.MyAccountOverviewSteps;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderHistoryPage extends PageObject {
	protected static final Logger LOG = LoggerFactory.getLogger(MyAccountOverviewSteps.class);
	private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");
	private final By heading = By.xpath("//div[@class='row account-main']/div/h1");
	private final By listHeadingOrderDate = By.xpath("//div[@class='list-header-item col-sm-2'][1]");
	private final By listHeadingOrderNumber = By.xpath("//div[@class='list-header-item col-sm-2'][2]");
	private final By listHeadingItem = By.xpath("//div[@class='list-header-item col-sm-1']");
	private final By listHeadingStatus = By.xpath("//div[@class='list-header-item col-sm-3']");
	private final By listHeadingDestination = By.xpath("//div[@class='list-header-item col-sm-2'][3]");
	private final By listHeadingOrderTotal = By.xpath("//div[@class='list-header-item col-sm-2 column-total text-right']");
	private final By orderDate = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[1]");
	private final By orderNumber = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']");
	private final By orderItems = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[5]");
	private final By orderStatus = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[6]");
	private final By orderDestination = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[8]/address");
	private final By orderTotal = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[9]/div[@class='list-item']");
	private final By orderDetails = By.xpath("//a[text()='"+Props.getProp("order.history.page.orderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[9]/div[@class='list-item float-left-xs']/a");
	private final By ordersList = By.xpath("//div[@class='col-sm-2 list-item']/a");
	private final By paginationNextList = By.xpath("//li[@class='pagination-list-next']/a");
	private final By instalmentLink =  By.xpath("//a[contains(@href,'"+Props.getProp("secureCheckout.orderNumber")+"')]//ancestor::div[contains(@class,'iomorderhistory')]//div/a[contains(@href,'ViewInstallments')]");
	private final By installmentOrderDate = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[1]");
	private final By installmentOrderNumber = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']");
	private final By installmentOrderItems = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[5]");
	private final By installmentOrderStatus = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[6]");
	private final By installmentOrderDestination = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[8]/address");
	private final By installmentOrderTotal = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[9]/div[@class='list-item']");
	private final By installmentOrderDetails = By.xpath("//a[text()='"+Props.getProp("order.history.page.installmentOrderNumber")+"']//ancestor::div[@class='iomorderhistory list-item-row-big list-item-row']/div[9]/div[@class='list-item float-left-xs']/a");
	private static String dynamicOrderNumber;
	private static final Logger log = LoggerFactory.getLogger(OrderHistoryPage.class);

	public static void setDynamicOrderNumber(String OrderNumber)
	{
		dynamicOrderNumber=OrderNumber;
	}

	public String getDynamicOrderNumber()
	{
		return dynamicOrderNumber;
	}
	public String getHeading() {
		return waitForExpectedElement(heading).getText();
	}

	public String getOrderDateHeaderText() {
		return waitForExpectedElement(listHeadingOrderDate).getText();
	}
	
	public String getOrderNumberHeaderText() {
		return waitForExpectedElement(listHeadingOrderNumber).getText();
	}
	
	public String getItemHeaderText() {
		return waitForExpectedElement(listHeadingItem).getText();
	}
	
	public String getStatusHeaderText() {
		return waitForExpectedElement(listHeadingStatus).getText();
	}
	
	public String getDestinationHeaderText() {
		return waitForExpectedElement(listHeadingDestination).getText();
	}
	
	public String getOrdertotalHeaderText() {
		return waitForExpectedElement(listHeadingOrderTotal).getText();
	}
	
	public String getOrderDate() {
		log.info("waitForExpectedElement(orderDate).getText()---"+waitForExpectedElement(orderDate).getText());
		return waitForExpectedElement(orderDate).getText();
	}

	public String getInstallmentOrderDate() {
		return waitForExpectedElement(installmentOrderDate).getText();
	}
	
	public String getOrderNumber() {
		log.info("waitForExpectedElement(orderNumber).getText()---"+waitForExpectedElement(orderNumber).getText());
		return waitForExpectedElement(orderNumber).getText();
	}

	public String getInstallmentOrderNumber() {
		return waitForExpectedElement(installmentOrderNumber).getText();
	}
	
	public String getItemNumber() {
		log.info("waitForExpectedElement(orderItems).getText()---"+waitForExpectedElement(orderItems).getText());
		return waitForExpectedElement(orderItems).getText();
	}

	public String getInstallmentItemNumber() {
		return waitForExpectedElement(installmentOrderItems).getText();
	}
	
	public String getOrderStatus() {
		log.info("waitForExpectedElement(orderStatus).getText()---"+waitForExpectedElement(orderStatus).getText());
		return waitForExpectedElement(orderStatus).getText();
	}

	public String getInstallmentOrderStatus() {
		return waitForExpectedElement(installmentOrderStatus).getText();
	}
	
	public String getOrderDestination() {
		return waitForExpectedElement(orderDestination).getText();
	}

	public String getInstallmentOrderDestination() {
		return waitForExpectedElement(installmentOrderDestination).getText();
	}
	
	public String getOrderTotal() {
		return waitForExpectedElement(orderTotal).getText();
	}

	public String getInstallmentOrderTotal() {
		return waitForExpectedElement(installmentOrderTotal).getText();
	}
	
	public void clickOrderDetails() {
		waitForExpectedElement(orderDetails).click();
	}

	public void clickInstallmentOrderDetails() {
		waitForExpectedElement(installmentOrderDetails).click();
	}

	public boolean navigateToOrder(String orderID) throws InterruptedException {
		boolean flag=false;
		int orderCount;
		System.out.println("---"+orderID);
		IsPageLoaded.waitAllRequest();
		Thread.sleep(20000);
		do {
			orderCount =1;
			List<WebElement> orders = webDriver.findElements(ordersList);
			for (WebElement order : orders) {
				System.out.println("---"+order);
				if (order.getText().contentEquals(orderID)) {
					System.out.println("Element fount at: " + orderCount + " position and order number is" + order.getText());
					flag = true;
					break;
				}
				orderCount++;
			}
			if(!flag && isElementClickable(paginationNextList)) {
					waitForExpectedElement(paginationNextList).click();
			} else {
				break;
			}
		}while(!flag);
		return flag;
	}

	public Boolean clickInstalmentLink() {
		boolean flag=false;
		String orderID="Not found";
	By instalment =  By.xpath("//a[contains(@href,'"+orderID+"')]//ancestor::div[contains(@class,'iomorderhistory')]//div/a[contains(@href,'ViewInstallments')]");
		try {
				waitForExpectedElement(instalment).click();
				log.info("Successfully clicked installment link");
				flag=true;
			}
		catch (Exception e) {
			log.error("Some exception occurred while click on installment link-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean clickInstalmentLink(String orderNumber) {
		boolean flag=false;
		//By instalment =  By.xpath("//a[contains(@href,'"+orderNumber+"')]//ancestor::div[contains(@class,'iomorderhistory')]//div/a[contains(@href,'ViewInstallments-Details')]");
		By instalment =  By.xpath("//a[contains(@href,'"+orderNumber+"')]//ancestor::div[contains(@class,'iomorderhistory')]//div/a[contains(@href,'ViewOrders-Detail')]");

		try {
			waitForExpectedElement(instalment).click();
			log.info("Successfully clicked installment link");
			flag=true;
		}
		catch (Exception e) {
			log.error("Some exception occurred while click on installment link-->>"+e.getMessage());
		}
		return flag;
	}
}
