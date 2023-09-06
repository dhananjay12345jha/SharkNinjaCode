package com.salmon.test.page_objects.gui.SNEX;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkuConfigurationPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    private static final By skuConfiguration=By.xpath("//a[contains(@href,'sku-management')]");
    private static final By substitution=By.xpath("//button[text()=' Substitutions ']");
    private static final By addSubstitution=By.xpath("//button[text()=' Add Substitutions ']");
    private static final By enterSourceSku=By.xpath("//input[@data-testing-id='sourceSku']");
    private static final By enterTargetSku=By.xpath("//textarea[@data-testing-id='targetSkus']");
    private static final By clickConfirm=By.xpath("//button[text()=' Confirm ']");
    private static final By searchBar=By.xpath("//input[@type='text']");
    private static final By searchButton=By.xpath("//button[@type='submit']");
    private static final By existSkuText=By.xpath("//p[@class=\"error-message\"]");
    private static final By cancelButton=By.xpath("//button[text()=' Cancel ']");
    private static final By  refurbishment =By.xpath("//button[text()=' Refurbishments ']");
    private static final By  addRefurbishments =By.xpath("//button[text()=' Add Refurbishment ']");
    private static final By  enterSku =By.xpath("//input[@data-testing-id='sku']");
    private static final By  rapidResponse =By.xpath("//button[text()=' Rapid Response ']");
    private static final By  addRapidResponse =By.xpath("//button[text()=' Add Rapid Response ']");
    private static final By  selectReason =By.xpath("//select[@data-testing-id='reason']");
    private static final By  warehouse =By.xpath("//button[text()=' Warehouse restrictions ']");
    private static final By  addWarehouse =By.xpath("//button[text()=' Add WH Restriction ']");
    private static final By  selectDSV =By.xpath("//label[@class='c-chk__label c-chk__label--regular']//span[text()='DSV']");
    private static final By  existWarehouseText =By.xpath("//div[text()=' SKU already exists in the list ']");
    private static final By  existInvalidText =By.xpath("//div[@class='col-12']");

    public SkuConfigurationPage clickSkuConfiguration(){
        SkuConfigurationPage skuConfigurationPage=null;
        try {
            getWebDriver().findElement(skuConfiguration).click();
            skuConfigurationPage=new SkuConfigurationPage();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return skuConfigurationPage;
    }

    public boolean clickSubstitutionButton() {
        boolean flag=false;

        waitForExpectedElement(substitution).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean clickAddSubstitutionButton() {
        boolean flag=false;

        waitForExpectedElement(addSubstitution).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }

    public boolean enterSku(String sourceSku,String targetSku) {
        boolean flag=false;
        String sku1= Props.getProp(sourceSku);
        String sku2=Props.getProp(targetSku);
        waitForExpectedElement(enterSourceSku).sendKeys(sku1);
        waitForExpectedElement(enterTargetSku).sendKeys(sku2);
        flag= true;
        return flag;
    }
    public boolean clickConfirm() {
        boolean flag=false;

        waitForExpectedElement(clickConfirm).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean searchSku(String key) {
        boolean flag=false;
        String sku1= Props.getProp(key);
        waitForExpectedElement(searchBar).sendKeys(sku1);
        waitForExpectedElement(searchButton).click();
        log.info("Not able to click on search button");
        flag= true;
        return flag;
    }

    public String existSkuText() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(existSkuText)).getText().trim();
            log.info("Order Received Text Extracted From Checkout Page is-->" + text);
        } catch (Exception e) {
            log.info("Order Received text not found");
        }
        return text;
    }

    public boolean clickOnCancelButton() {
        boolean flag=false;
        waitForExpectedElement(cancelButton).click();
        flag= true;
        return flag;
    }

    public boolean clickRefurbishmentButton() {
        boolean flag=false;

        waitForExpectedElement(refurbishment).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean clickAddRefurbishmentButton() {
        boolean flag=false;

        waitForExpectedElement(addRefurbishments ).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean enterRefurbishmentsSku(String sku) {
        boolean flag=false;
        String sku1= Props.getProp(sku);
        waitForExpectedElement(enterSku).sendKeys(sku1);
        flag= true;
        return flag;
    }
    public boolean clickRapidResponseButton() {
        boolean flag=false;

        waitForExpectedElement(rapidResponse).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }

    public boolean clickAddRapidResponseButton() {
        boolean flag=false;

        waitForExpectedElement(addRapidResponse ).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }

    public boolean selectReason(String value) {
        boolean flag=false;
        selectDropDownValue(selectReason,"value",value );
        flag= true;
        return flag;
    }
    public boolean clickWarehouseButton() {
        boolean flag=false;

        waitForExpectedElement(warehouse).click();
        log.info("Agent is able to click on warehouse tab");
        flag= true;
        return flag;
    }

    public boolean clickaddWarehouseButton() {
        boolean flag=false;

        waitForExpectedElement(addWarehouse).click();
        log.info("Agent is able Click on Add warehouse Button");
        flag= true;
        return flag;
    }

    public boolean selectDSV() {
        boolean flag=false;

        waitForExpectedElement(selectDSV).click();
        log.info("Agent is able Click on Add warehouse Button");
        flag= true;
        return flag;
    }

    public String existWarehouseText() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(existWarehouseText)).getText().trim();
            log.info("Order Received Text Extracted From Checkout Page is-->" + text);
        } catch (Exception e) {
            log.info("Order Received text not found");
        }
        return text;
    }

    public String existInvalidText() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(existInvalidText)).getText().trim();
            log.info("Order Received Text Extracted From Checkout Page is-->" + text);
        } catch (Exception e) {
            log.info("Order Received text not found");
        }
        return text;
    }

}
