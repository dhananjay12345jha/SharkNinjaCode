package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAccountProductWarrentiesSNCA extends PageObject {
	private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPageSNCA.class);
	private String propFilePath = System.getProperty("user.dir")+Props.getProp("file.path");
	private static final By productWarrentiesPage = By.xpath("//button[@routerlink='./register-warranty']");
	private static final By getModelNumber = By.xpath("(//div[@class='gaurantee-info']/div[1]/div[2])[1]");
	private static final By getPurchaseDate = By.xpath("(//div[@class='gaurantee-info']/div[2]/div[2])[1]");
	private static final By getStoreOfPurchase = By.xpath("(//div[@class='gaurantee-info']/div[3]/div[2])[1]");
	private static final By getWarrantyExpiration = By.xpath("(//div[@class='gaurantee-info']/div[4]/div[2])[1]");

	public boolean checkProductWarrentiesPageFound() {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		try {
			flag = isElementPresentWithWait(productWarrentiesPage);
			log.info("Product-Warranties Page found successfully");
		} catch (Exception e) {
			log.error("ProductWarranties Page not found" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean verifyProductWarrantyInfo() {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
			String modelNo="Not Found";
			String purchaseDate="Not Found";
			String purchaseStore="Not Found";
			String warrantyExpiration="Not Found";
			modelNo = config.getProperty("register.product.model").toString();
			purchaseDate = config.getProperty("register.product.purchase.date").toString();
			purchaseStore = config.getProperty("register.product.purchase.store").toString();
			warrantyExpiration = config.getProperty("register.product.warranty.expiry").toString();
			flag = webDriver.findElement(getModelNumber).getText().trim().replaceAll("\\s+","").contentEquals(modelNo.replaceAll("\\s+","")) && webDriver.findElement(getPurchaseDate).getText().trim().replaceAll("\\s+","").contentEquals((purchaseDate.replaceAll("\\s+","")).replaceAll("\\[|\\]", "")) && webDriver.findElement(getStoreOfPurchase).getText().trim().contentEquals(purchaseStore) && webDriver.findElement(getWarrantyExpiration).getText().trim().replaceAll("\\s+","").contentEquals((warrantyExpiration.replaceAll("\\s+","")).replaceAll("\\[|\\]", ""));
			log.info("Product-Warranties information found successfully");
		} catch (Exception e) {
			log.error("ProductWarranties information not found" + e.getMessage());
			e.printStackTrace();
		}

		return flag;

	}
}