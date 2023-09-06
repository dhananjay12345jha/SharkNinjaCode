package com.salmon.test.page_objects.gui.SNEX;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class FraudReviewPage extends PageObject {
    private static final By fraudReviewText=By.xpath("//snex-fraud-review/h2");
    private static final By addItem=By.xpath("(//button[@name='add-item'])[1]");
    private static final By selectEmailID=By.xpath("(//select[@data-testing-id='valueType'])[2]");
    private static final By enteremailId=By.xpath("//textarea[@data-testing-id='blacklistValues']");
    private static final By entervalue=By.xpath("(//input[@type=\"search\"])[2]");
    private static final By blacklist=By.xpath("//input[@value='AutomationTest']");
    private static final By selectblackList=By.xpath("(//span[@class='btn-inner-text'])[2]");
    private static final By confirmButton=By.xpath("(//button[@type='submit'])[2]");
    private static final By searchBox=By.xpath("//input[@data-testing-id='searchOrder']");
    private static final By selectEmailID1=By.xpath("(//select[@data-testing-id='valueType'])[1]");
    private static final By searchButton=By.xpath("(//button[text()=' Search '])[1]");
    private static final By deleteIcon=By.xpath("//div[@class='row-action-icon']");
    private static final By deleteConfirmButton=By.xpath("//button[@data-testing-id='confirm']");
    private static final By selectCountry=By.xpath("//select[@data-testing-id='countryCode']");
    private static final By enterCity=By.xpath("//input[@data-testing-id='city']");
    private static final By enterAddress=By.xpath("//input[@data-testing-id='addressLine1']");
    private static final By enterPostalCode=By.xpath("//input[@data-testing-id='postalCode']");
    private static final By addBlacklist=By.xpath("//button[@class='btn btn-primary button-rounded custom-button text-capitalize']");
    private static final By blacklistButton=By.xpath("//button[text()=' Blacklist ']");
    private static final By enterName=By.xpath("//input[@data-testing-id='name']");
    private static final By holdOrder=By.xpath("//input[@id='review']");
    private static final By submitButton=By.xpath("(//button[@type='submit'])[2]");
    private static final By trashIcon=By.xpath("(//div[@class='row-action-icon'])[2]");
    private static final By searchResultText=By.xpath("//div[@class='no-items-container']");
    private static final By cancelOrder=By.xpath("//input[@id='reject']");

    private static final Logger log = LoggerFactory.getLogger(FraudReviewPage.class);

    public String getFraudReviewText() {
        String text = "Unable to get text please check";
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fraudReviewText));
            text = element.getText().trim();
            log.info("Got the text which is--->>" + text);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public boolean clickAddItem() {
        boolean flag=false;
        waitForExpectedElement(addItem).click();
        flag= true;
        return flag;
    }

    public boolean selectEmailId(String key) {
        boolean flag=false;
        String value=Props.getProp(key);
        selectDropDownValue(selectEmailID,"value", value);
        flag= true;
        return flag;
    }

    public boolean enterEmailId(String key) {
        boolean flag=false;
        String emailId=Props.getProp(key);
        waitForExpectedElement(enteremailId).sendKeys(emailId);
        flag= true;
        return flag;
    }

    public boolean slectBlackList(String value) {
        boolean flag=false;
        waitForExpectedElement(selectblackList).click();
        waitForExpectedElement(entervalue).sendKeys(value);
        waitForExpectedElement(blacklist).click();
        flag= true;
        return flag;
    }

    public boolean clickOnConfirmButton() {
        boolean flag=false;
        waitForExpectedElement(confirmButton).click();
        flag= true;
        return flag;
    }

    public boolean enterEmailIdInSearchBox(String key) {
        boolean flag=false;
        String emailId=Props.getProp(key);
        waitForExpectedElement(searchBox).sendKeys(emailId);
        flag= true;
        return flag;
    }
    public boolean selectEmailId1(String key) {
        boolean flag=false;
        String value=Props.getProp(key);
        selectDropDownValue(selectEmailID1,"value",value );
        flag= true;
        return flag;
    }
    public boolean clickOnSearchButton() {
        boolean flag=false;
        waitForExpectedElement(searchButton).click();
        flag= true;
        return flag;
    }
    public boolean clickOnDeleteIcon() {
        boolean flag=false;
        waitForExpectedElement(deleteIcon).click();
        flag= true;
        return flag;
    }
    public boolean clickOnDeleteConfirmButton() {
        boolean flag=false;
        waitForExpectedElement(deleteConfirmButton).click();
        flag= true;
        return flag;
    }

    public boolean selectCountry() {
        boolean flag=false;
        String countryCode=Props.getProp("country.code");
        selectDropDownValue(selectCountry,"value", countryCode);
        flag= true;
        return flag;
    }
    public boolean enterCity(String key) {
        boolean flag=false;
        String city=Props.getProp(key);
        waitForExpectedElement(enterCity).sendKeys(city);
        flag= true;
        return flag;
    }
    public boolean enterAddress(String key) {
        boolean flag=false;
        String address=Props.getProp(key);
        waitForExpectedElement(enterAddress).sendKeys(address);
        flag= true;
        return flag;
    }
    public boolean enterPostalCode(String key) {
        boolean flag=false;
        String postalCode=Props.getProp(key);
        waitForExpectedElement(enterPostalCode).sendKeys(postalCode);
        flag= true;
        return flag;
    }
    public boolean clickBlackList() {
        boolean flag=false;
        waitForExpectedElement(blacklistButton).click();
        flag= true;
        return flag;
    }
    public boolean clickAddBlackList() {
        boolean flag=false;
        waitForExpectedElement(addBlacklist).click();
        flag= true;
        return flag;
    }
    public boolean enterNameBlacklist(String key) {
        boolean flag=false;
        String address=Props.getProp(key);
        waitForExpectedElement(enterName).sendKeys(address);
        flag= true;
        return flag;
    }
    public boolean selectHoldOrder() {
        boolean flag=false;
        waitForExpectedElement(holdOrder).click();
        flag= true;
        return flag;
    }
    public boolean selectCancelOrder() {
        boolean flag=false;
        waitForExpectedElement(cancelOrder).click();
        flag= true;
        return flag;
    }
    public boolean clickOnSubmitButton() {
        boolean flag=false;
        waitForExpectedElement(submitButton).click();
        flag= true;
        return flag;
    }
    public boolean clickOnTrashIcon() {
        boolean flag=false;
        waitForExpectedElement(trashIcon).click();
        flag= true;
        return flag;
    }
    public String searchResultText() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultText)).getText().trim();
            log.info("Order Received Text Extracted From Checkout Page is-->" + text);
        } catch (Exception e) {
            log.info("Order Received text not found");
        }
        return text;
    }
}


