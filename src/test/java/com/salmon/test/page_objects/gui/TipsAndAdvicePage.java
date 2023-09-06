package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.GETSearchProdRegisWarrantyRecords;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipsAndAdvicePage extends PageObject {
	
	private final By showMeText = By.xpath("//*[text()='Show me:']");
	private final By showAllFilter = By.xpath("//li[@class='show-all active']/a");
	private final By generalCleaningFilter = By.xpath("//div[@class='wrapper']/ul/li[2]/a");
	private final By lifeHacksFilter = By.xpath("//div[@class='wrapper']/ul/li[3]/a");
	private final By tipsAndAdviceFilter = By.xpath("//div[@class='wrapper']/ul/li[4]/a");
	private final By vacuumTipsFilter = By.xpath("//div[@class='wrapper']/ul/li[5]/a");
	private final By shopFullRangeLink = By.xpath("//div[@class='box navbox easeout alignright']//a");
	private final By recipeTipsHeading = By.xpath("//div[@class='b-latest-recipes']//h2");
	private final By tipsAndAdviceText = By.xpath("(//h2[@class=\"post-title white semibold size-1375 nomargin mobile-size-1\"])[1]");
	private static final Logger log = LoggerFactory.getLogger(TipsAndAdvicePage.class);
	
	public By getShowMeText() {
		return showMeText;
	}
	
	public By getShowAllFilter() {
		return showAllFilter;
	}
	public String TipsAndAdvicePageTitle() {
		return webDriver.getTitle();
	}

	public String verifyRecipeTipsHeading() {
		String text="Unable to get";
		try{
			WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(recipeTipsHeading));
			new Actions(getWebDriver()).moveToElement(element).build().perform();
			text=element.getText().trim();
			log.info("Successfully find the text which is Recipe Tips Heading --->>>"+text);
		}catch (Exception e){
			log.error("Some exception occured while fetching text from recipe tips heading-->>>"+ ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
		return text;
	}
	
	public By getGeneralCleaningFilter() {
		return generalCleaningFilter;
	}
	
	public By getLifeHacksFilter() {
		return lifeHacksFilter;
	}
	
	public By getTipsAndAdviceFilter() {
		return tipsAndAdviceFilter;
	}
	
	public By getVacuumTipsFilter() {
		return vacuumTipsFilter;
	}

	public By getShopFullRangeLink() {
		return shopFullRangeLink;
	}

	public String getTextOfTipsAndAdvicePage(){
		String text = "Not found";
		try{
			text = wait.until(ExpectedConditions.presenceOfElementLocated(tipsAndAdviceText)).getText();
		}
		catch(Exception e) {
			log.info("unable to find text due to" + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}
}
