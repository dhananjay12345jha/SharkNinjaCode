package com.salmon.test.page_objects.gui;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.log.Log;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.PDPPageSNCA;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.thoughtworks.selenium.webdriven.commands.GetAllFields;

public class PDPPage extends PageObject {
	private final By productTile = By.xpath("//*[@class='main-navigation-level1-item']/a");
	private final By PArtsAccessoriesALLPages = By.xpath("//*[@class=\"category-list-item col-md-4 col-xs-6\"]/div/a/img");
	private final By AutoSuggestiveDropdownValues = By.xpath("//li[@class=\"classic\"]/button");
	private final By AutoSuggestiveDropdownWindow = By.xpath("//ul[@class=\"search-suggest-results\"]");
	private final By PArtsAccessoriesALLPage3 = By.xpath("//*[@class=\"product-list-item col-md-3 col-sm-4 col-xs-6 grid-view\"]");

	private final By productCategoryAllLinksCA = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul/li/a");
	private final By productCategoriesCaALLLinks = By.xpath("//*[@class='defer-load']/img");

	private final By addToCartButton = By.xpath("//div[@class='product-quantity']/..//button[@name='addProduct']");
	//private final By addToCartButton = By.cssSelector(".product-form .btn-primary");
	private final By PDP_Search_product = By.xpath("//h1[@class='js-product-title js-make-bold']");
	private final By addQuantityField = By.xpath("//input[contains(@class,'input-quantity')]");
	private final By addQuantityFieldPWA = By.xpath("//input[@data-testing-id=\"quantity\"]");
	private final By FeatureLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[1]//a");
	private final By mobileFeatureLink = By.xpath("(//a[@data-parent='#feature-accordion'])[1]");
	private final By techSpecLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[3]//a");
	private final By mobileTechSpecLink = By.xpath("(//a[@data-parent='#feature-accordion'])[3]");
	private final By InTheBoxLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[4]//a");
	private final By mobileInTheBoxLink = By.xpath("(//a[@data-parent='#feature-accordion'])[4]");
	private final By FAQLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[5]//a");
	private final By mobileFAQLink = By.xpath("(//a[@data-parent='#feature-accordion'])[5]");
	private final By downloadLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[6]//a");
	private final By mobileDownloadLink = By.xpath("(//a[@data-parent='#feature-accordion'])[6]");
	private final By price = By.xpath("//div[@data-testing-id='current-price']");

	//private final By price = By.cssSelector("div[class='price-container'] div[data-testing-id='current-price']");
	private final By partsAccessoriesLink = By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/ul/li[7]/a");
	private final By mobilePartsAccessoriesLink = By.xpath("(//a[@data-parent='#feature-accordion'])[7]");
	private final By moreDetailsLink = By.xpath("//div[@class=\"nav-tabs-wrapper fullsize\"]//ul/li[2]//a");
	private final By mobileMoreDetailsLink = By.xpath("(//a[@data-parent='#feature-accordion'])[2]");
	private final By featureDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By TechSpecDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By MoreDetailsDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By InTheBoxDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By FAQsDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By downloadsDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]//div[@class=\"tab-pane js-product-tab active\"]");
	private final By partsDiv = By.xpath("//div[@class=\"tab-content product-thumbnail\"]/div[@class='tab-pane js-product-tab active']");
	private final By mobileFeatureDiv = By.cssSelector("div[id=accordion_feature-xs] div[class~=panel-body]");
	private final By mobileTechSpecDiv = By.xpath("//div[@id='accordion_techspec-xs']");
	private final By mobileMoreDetailsDiv = By.cssSelector("div[id=accordion_more_detail-xs] div[class~=panel-body]");
	private final By mobileInTheBoxDiv = By.xpath("//div[@id='accordion_inbox-xs']/div");
	private final By mobileFAQsDiv = By.xpath("//div[@id='accordion_faq-xs']/div");
	private final By mobileDownloadsDiv = By.xpath("//div[@id='accordion_download-xs']/div");
	private final By mobilePartsDiv = By.xpath("//div[@id='accordion_accessories-xs']");
	private final By productQuantityTxtBox = By.xpath("//input[starts-with(@id,'Quantity_')]");
	private final By quantityInvalidError = By.xpath("//*[starts-with(@id,'err-greaterthan_Quantity_')]");
	private final By quantityInvalidErrorPWA = By.xpath("//*[@class='ng-fa-icon form-control-feedback']/following-sibling::small");
	private final By maxquantityInvalidError = By.xpath("//*[starts-with(@id,'err-lessthan_Quantity_')]");
	private final By goBackFromOverlay = By.xpath("//a[contains(@class,'view-cart') and contains(@class,'overlay')]");
	private static final By payPalPayLaterMessageIframe = By.cssSelector("iframe[name*=paypal_message]");
	private static final By payPalCredit = By.cssSelector("span[class*=paypalCredit]");
	private static final By payPalPayLaterMessage = By.cssSelector("div.message span.br");
	private By qtySelector, quantityInvalidErrorField, maxquantityInvalidErrorField;
	private SearchPage searchpage;

	private static final Logger log = LoggerFactory.getLogger(PDPPage.class);

	public PDPPage() {
		String country = Props.getProp("country");
		if (country.equals("US") || country.equals("CA")) {
			qtySelector = addQuantityFieldPWA;
			quantityInvalidErrorField = quantityInvalidErrorPWA;
			maxquantityInvalidErrorField = quantityInvalidErrorPWA;
		} else {
			qtySelector = addQuantityField;
			quantityInvalidErrorField = quantityInvalidError;
			maxquantityInvalidErrorField = maxquantityInvalidError;
		}
	}

	public void updateProductQuantity(String quantity) {

		try {
			waitForExpectedElement(qtySelector).clear();
			waitForExpectedElement(qtySelector).sendKeys(quantity);
			log.info("Successfully updated the product quantity");
		} catch (Exception e) {
			log.error("Some exception occurred while updating the product quantity-->>" + e.getMessage());
		}
	}

	public Boolean VerifyPDPPageCorrespondingToSearchForNinjaDE(String PDPPageLink) {
		boolean flag = false;
		if (SearchPage.SearchProduct == null)
			SearchPage.SearchProduct = Props.getProp("product.specificSKU");
		try {
			if (PDPPageLink.contains(SearchPage.SearchProduct)) {
				flag = true;
				log.info("PDP page corresponding to search Product is displayed");
			}

		} catch (Exception e) {
			log.error("Some exception occurred while comparing the PDP page header-->>" + e.getMessage());
		}
		return flag;
	}

	public Boolean VerifyPDPPageCorrespondingToSearch(String PDPPageLink) {
		boolean flag = false;
		if (SearchPage.SearchProduct == null)
			SearchPage.SearchProduct = Props.getProp("product.specificSKU");
		try {
			if (PDPPageLink.contains(SearchPage.SearchProduct)) {
				flag = true;
				log.info("PDP page corresponding to search Product is displayed");
			}

		} catch (Exception e) {
			log.error("Some exception occurred while comparing the PDP page header-->>" + e.getMessage());
		}
		return flag;
	}

	public Boolean VerifyPLPPageImages() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = true;
		waitForPage();
		List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTile));
		System.out.println(" Total no of categories Products are " + productList.size());
		for (int j = 0; j <productList.size(); j++) {
			if (productList.get(j).isDisplayed()) {
				String temp = productList.get(j).getAttribute("src");
				if (temp.contains("not_available.png")) {
					System.out.println("temp is " + temp);
					log.info("The image is not showing at position " + j);
					flag = false;
					break;
				}
			} else {
				String temp = productList.get(j).getText();
				flag = true;
			}
			log.info("All images are available on this page");
		}
		return flag;
	}


	public Boolean VerifyPLPPageImagesDE() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = true;
		waitForPage();
		List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTile));
		System.out.println(" Total no of categories Products are " + productList.size());
		for (int j = 0; j <productList.size(); j++) {
				if (productList.get(j).isDisplayed()) {
					String temp = productList.get(j).getAttribute("src");
					if (temp.contains("not_available.png")) {
						System.out.println("temp is " + temp);
						log.info("The image is not showing at position " + j);
						flag = false;
						break;
					}
				} else {
					String temp = productList.get(j).getText();
					flag = true;
				}
				log.info("All images are available on this page");
			}
			return flag;
		}

	public Boolean VerifyProductAccessoriesPage0_Images() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = true;
		waitForPage();
		List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PArtsAccessoriesALLPages));
		System.out.println(" Total no of categories Products are " + productList.size());
		for (int j = 0; j <productList.size(); j++) {
			if (productList.get(j).isDisplayed()) {
				String temp = productList.get(j).getAttribute("src");
				if (temp.contains("not_available.png")) {
					System.out.println("temp is " + temp);
					log.info("The image is not showing at position " + j);
					flag = false;
					break;
				}
			} else {
				String temp = productList.get(j).getText();
				flag = true;
			}
			log.info("All images are available on this page");
		}
		return flag;
	}

	public Boolean VerifySharkTextInAutoSuggestiveDropDown() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		waitForPage();
		List<WebElement> autoSuggestivedropDownList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AutoSuggestiveDropdownValues));
		System.out.println(" Total no of values are " + autoSuggestivedropDownList.size());
		for (int j = 0; j < autoSuggestivedropDownList.size(); j++) {
			String temp = autoSuggestivedropDownList.get(j).getAttribute("data-search-result");
			//System.out.println("temp vaues are " + temp);
			if (temp.contains("shark")) {
				System.out.println("temp value is " + temp);
				log.info("The AutoSuggestive value is Containing shark at index " + j);
				flag = true;

			} else {
				temp = autoSuggestivedropDownList.get(j).getText();
				log.info("Temp value is " + temp);
				log.info("The AutoSuggestive value is not Containing shark at index " + j);
				flag = false;
			}
		}
				Thread.sleep(3000);
				webDriver.navigate().refresh();
		return flag;
	}

	public Boolean VerifyNinjaTextInAutoSuggestiveDropDown() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		waitForPage();
		List<WebElement> autoSuggestivedropDownList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AutoSuggestiveDropdownValues));
		System.out.println(" Total no of values are " + autoSuggestivedropDownList.size());
		for (int j = 0; j < autoSuggestivedropDownList.size(); j++) {
			String temp = autoSuggestivedropDownList.get(j).getAttribute("data-search-result");
			//System.out.println("temp vaues are " + temp);
			if (temp.contains("ninja")) {
				System.out.println("temp value is " + temp);
				log.info("The AutoSuggestive value is Containing ninja at index " + j);
				flag = true;

			} else {
				temp = autoSuggestivedropDownList.get(j).getText();
				log.info("Temp value is " + temp);
				log.info("The AutoSuggestive value is not Containing ninja at index " + j);
				flag = false;
			}
		}
				Thread.sleep(3000);
				webDriver.navigate().refresh();
		return flag;
	}


	public Boolean AutoSuggestiveDropDownWindow() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		waitForPage();
		WebElement autoSuggestivedropDownWindow = wait.until(ExpectedConditions.presenceOfElementLocated(AutoSuggestiveDropdownWindow));
			if (autoSuggestivedropDownWindow.isDisplayed()) {
				log.info(" The AutoSuggestive dropdown is displayed and therefore it is not working properly ");
				flag = true;
			} else {
				log.info(" The AutoSuggestive dropdown is not displayed, hence it is working fine ");
				flag = false;
			}
		return flag;
	}


	public Boolean VerifyProductCategoryAllPages_Images_CA() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = true;
		waitForPage();
		List<WebElement> productListCA = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoriesCaALLLinks));
		System.out.println(" Total no of categories Products are " + productListCA.size());
		for (int j = 0; j<productListCA.size(); j++) {
			if (productListCA.get(j).isDisplayed()) {
				String temp = productListCA.get(j).getAttribute("src");
				if (temp.contains("not_available.png")) {
					System.out.println("temp is " + temp);
					log.info("The image is not showing at position " + j);
					flag = false;
					break;
				}
			} else {
				String temp = productListCA.get(j).getText();
				flag = true;
			}
			log.info("All images are available on this page");
		}
		return flag;
	}

	public Boolean VerifyProductAccessoriesPage3_Images() throws InterruptedException {
		IsPageLoaded.waitAllRequest();
		boolean flag = false;
		waitForPage();
		List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PArtsAccessoriesALLPage3));
		System.out.println(" no of Products images are " + productList.size());
		for (int j = 0; j<productList.size(); j++) {
			System.out.println("All images are" + productList);
			if (productList.get(j).isDisplayed()) {
				log.info("Image for " + productList.get(j) + " is showing ");
				flag=true;
			}
			else{
				log.info("All the images are not displaying");
				flag=false;
			}
		}
		return flag;
	}

	public Boolean isErrorMessageDisplayedPleaseEnter_1_or_more() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(quantityInvalidErrorField).getText().contentEquals(Props.getProp("quantity.invalid.error.message"));
			log.info("Successfully found error message Please enter 1 or more.");
		} catch (Exception e) {
			log.error("Some exception occurred while finding invalid error message-->>"+e.getMessage());
				}
		return flag;
	}
	public Boolean isAddToBasketEnabled() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(addToCartButton).isEnabled();
			log.info("Successfully found that Add to Basket button is enabled");
		} catch (Exception e) {
			log.error("Some exception occurred while checking Addto Button is enabled-->>"+e.getMessage());
		}
		return flag;
	}
	public Boolean isErrorMessageDisplayedPleaseEnter_100_or_less() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(maxquantityInvalidErrorField).getText().contentEquals(Props.getProp("max.quantity.Invalid.Error"));
			log.info("Successfully found error message Please enter 100 or less");
		} catch (Exception e) {
			log.error("Some exception occurred while finding invalid error message-->>"+e.getMessage());
		}
		return flag;
	}

	public WebElement productPrice() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(price));
	}

	public float getProductPrice() {
		IsPageLoaded.waitAllRequest();
		String k = wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText().trim();
		if (k.contains("£")) {
			k = k.replace("£", "").trim();
		}
		if (k.contains("€")) {
			k = k.replace("€", "").trim();
		}
		//check whether string contains "," at multiple position
		if (k.contains(",")) {
			int pos= k.length()-3;
			if(k.charAt(pos)==',')
				k = k.substring(0, pos) + "." + k.substring(pos + 1);

			if(k.contains(","))
				k = k.replace(",", "").trim();
		}
		return Float.parseFloat(k);
	}

	public boolean addToCartButton() {
		IsPageLoaded.waitAllRequest();
		boolean flag=false;
		wait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
		try {
			WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
			new Actions(getWebDriver()).moveToElement(element).build().perform();
			wait.until(ExpectedConditions.visibilityOf(element)).click();
			flag=true;
			log.info("Successfully clicked on add to Cart Button");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on add to Cart Button-->>"+e.getMessage());
		}
		return flag;
	}
	public void clickFeaturesLink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				if(waitForExpectedElement(mobileFeatureLink).getAttribute("aria-expanded").contentEquals("false")) {
				//waitForExpectedElement(mobileFeatureLink).click();
				}
			} else {
				waitForExpectedElement(FeatureLink).click();
			}
			log.info("Successfully clicked on feature link");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on feature link-->>"+e.getMessage());
		}
	}
	public void clickTechSpecLink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				waitForExpectedElement(mobileTechSpecLink).click();
			} else {
				waitForExpectedElement(techSpecLink).click();
			}
			log.info("Successfully clicked on TechSpec link");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on TechSpecLink-->>"+e.getMessage());
		}
	}
	public void clickMoreDetailsLink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				waitForExpectedElement(mobileMoreDetailsLink).click();
			} else {
				waitForExpectedElement(moreDetailsLink).click();
			}
			log.info("Successfully clicked on moreDetailsLink");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on moreDetailsLink-->>"+e.getMessage());
		}
	}
	public void clickInTheBoxink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				waitForExpectedElement(mobileInTheBoxLink).click();
			} else {
				waitForExpectedElement(InTheBoxLink).click();
			}
			log.info("Successfully clicked on InTheBoxLink");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on InTheBoxLink-->>"+e.getMessage());
		}
	}
	public void clickFAQsLink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				waitForExpectedElement(mobileFAQLink).click();
			} else {
				waitForExpectedElement(FAQLink).click();
			}
			log.info("Successfully clicked on FAQLink");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on FAQLink-->>"+e.getMessage());
		}
	}
	public void clickPartsLink() {
		try {
			if(UrlBuilder.isMobile()) {
				waitForExpectedElement(mobilePartsAccessoriesLink).click();
			} else {
				waitForExpectedElement(partsAccessoriesLink).click();
			}
			log.info("Successfully clicked on partsAccessoriesLink");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on partsAccessoriesLink-->>"+e.getMessage());
		}
	}
	public void clickDownloadsLink() {
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
//				waitForExpectedElement(mobileDownloadLink).click();
				clickByElementByQueryJSExecutor(mobileDownloadLink);
			} else {
				waitForExpectedElement(downloadLink).click();
			}
			log.info("Successfully clicked on downloadLink");
		}
		catch (Exception e) {
			log.error("Some exception occurred while clicking on downloadLink-->>"+e.getMessage());
		}
	}

	public WebElement PDPSearchProductlink() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(PDP_Search_product));
	}

	public By getAddToBasketButton() {
		IsPageLoaded.waitAllRequest();
		waitForExpectedElement(addToCartButton);
		return addToCartButton;
	}
	public By getAddQuantityField() {
		waitForExpectedElement(addQuantityField);
		return addQuantityField;
	}
	public By getFeatureLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileFeatureLink);
			return mobileFeatureLink;
		} else {
			waitForExpectedElement(FeatureLink);
			return FeatureLink;
		}
	}
	public By getMoreDetailsLink() {
		if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileMoreDetailsLink);
			return mobileMoreDetailsLink;
		} else {
			waitForExpectedElement(moreDetailsLink);
			return moreDetailsLink;
		}
	}

	public By getpartsAccessoriesLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobilePartsAccessoriesLink);
			return mobilePartsAccessoriesLink;
		} else {
			waitForExpectedElement(partsAccessoriesLink);
			return partsAccessoriesLink;
		}
	}

	public By getTechSpecLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileTechSpecLink);
			return mobileTechSpecLink;
		} else {
			waitForExpectedElement(techSpecLink);
			return techSpecLink;
		}
	}
	public By getInTheBoxLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileInTheBoxLink);
			return mobileInTheBoxLink;
		} else {
			waitForExpectedElement(InTheBoxLink);
			return InTheBoxLink;
		}
	}
	public By getFAQLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileFAQLink);
			return mobileFAQLink;
		} else {
			waitForExpectedElement(FAQLink);
			return FAQLink;
		}
	}
	public By getDownloadsLink() {
		if(UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
			waitForExpectedElement(mobileDownloadLink);
			return mobileDownloadLink;
		} else {
			waitForExpectedElement(downloadLink);
			return downloadLink;
		}
	}



	public boolean isfeatureDivDisplayed()
	{
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileFeatureDiv).isDisplayed();
			} else {
				flag = webDriver.findElement(featureDiv).isDisplayed();
			}
			log.info("Feature Div is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the feature div-->>"+e.getMessage());
		}
		return flag;
	}

	public boolean isPartsDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()) {
				flag = webDriver.findElement(mobilePartsDiv).isDisplayed();
			} else {
				flag= webDriver.findElement(partsDiv).isDisplayed();
			}
			log.info("partsDiv is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the partsDiv-->>"+e.getMessage());
		}
		return flag;
	}
	public boolean isDownloadsDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileDownloadsDiv).isDisplayed();
			} else {
				flag= webDriver.findElement(downloadsDiv).isDisplayed();
			}
			log.info("downloadsDiv is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the downloadsDiv-->>"+e.getMessage());
		}
		return flag;


	}
	public boolean isFAQsDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileFAQsDiv).isEnabled();
			} else {
				flag= webDriver.findElement(FAQsDiv).isDisplayed();
			}
			log.info("FAQsDiv is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the FAQsDiv-->>"+e.getMessage());
		}
		return flag;
	}
	public boolean isTechSpecDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileTechSpecDiv).isDisplayed();
			} else {
				flag= webDriver.findElement(TechSpecDiv).isDisplayed();
			}
			log.info("TechSpec Div is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the TechSpec div-->>"+e.getMessage());
		}
		return flag;
	}
	public boolean isMoreDetailsDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileMoreDetailsDiv).isDisplayed();
			} else {
				flag= webDriver.findElement(MoreDetailsDiv).isDisplayed();
			}
			log.info("MoreDetails Div is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the MoreDetails div-->>"+e.getMessage());
		}
		return flag;
	}
	public boolean isInTheBoxDivDisplayed() {
		boolean flag=false;
		try {
			if(UrlBuilder.isMobile()|| UrlBuilder.isTablet()) {
				flag = webDriver.findElement(mobileInTheBoxDiv).isEnabled();
			} else {
				flag= webDriver.findElement(InTheBoxDiv).isDisplayed();
			}
			log.info("InTheBoxDiv is Displayed");
		}
		catch (Exception e) {
			log.error("Some exception occurred while displaying the InTheBoxDiv-->>"+e.getMessage());
		}
		return flag;
	}

	public String getPayPalPayLaterMessageonPDP(){
		String message="No message found";
		frameToBeAvailableAndSwitchToIt(payPalPayLaterMessageIframe);
		if (isElementPresent(payPalPayLaterMessage)) {
			message = waitForExpectedElement(payPalPayLaterMessage).getText();
		}
		webDriver.switchTo().defaultContent();
		return message;
	}

	public String getPayPalCreditMessageonPDP(){
		String message="No message found";
		if (isElementPresent(payPalCredit)) {
			message = waitForExpectedElement(payPalCredit).getText();
		}
		webDriver.switchTo().defaultContent();
		return message;
	}
}
