package com.salmon.test.page_objects.gui.SNEX;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DashboardPage extends PageObject {
    private static final By snexTitle = By.className("snex-title");
    private static final By countryList = By.xpath("//a[contains(@href,'orders?channel')]/div");
    private static final By countryDropdown = By.xpath("//div[contains(@class,'snex-channel')]");
    private static final By fraudReview=By.xpath("//a[contains(@href,'fraud-review')]");

    private static final By SearchFraud=By.xpath("//button[@name='fraud-check']");
    private static final By fraudStatus=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//snex-order-status");
    private static final By selectCheckbox=By.xpath("//label[@for='brand-Ebay']");
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public String getSnexTitle() {
        String text = "Unable to get text snex please check";
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(snexTitle));
            text = element.getText().trim();
            log.info("Got the text which is--->>" + text);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public boolean setDropDown(String value) {
        boolean flag = false;
        try {
            WebElement countryDropDown = getWebDriver().findElement(countryDropdown);
            List<WebElement> countryList = getWebDriver().findElements(this.countryList);
            if (countryDropDown.getAttribute("innerText").equalsIgnoreCase(value))
            {
                flag=true;
            } else {
                countryDropDown.click();
                countryList.stream().filter(s -> s.getText().trim().equalsIgnoreCase(value)).findFirst().get().click();
                wait.until(ExpectedConditions.stalenessOf(countryDropDown));
                flag = true;
            }
        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return flag;
    }

    public FraudReviewPage clickFraudReview(){
        FraudReviewPage fraudReviewPage=null;
        try {
            getWebDriver().findElement(fraudReview).click();
            fraudReviewPage=new FraudReviewPage();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return fraudReviewPage;
    }
    public boolean clickFraudSearch() {
        boolean flag=false;

        waitForExpectedElement(SearchFraud).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }

    public boolean verifyFraudStatus() {
        boolean flag=false;
        List<WebElement> OrderStatus = visibilityOfAllElementsLocatedBy(fraudStatus);


        for (int i = 0; i <OrderStatus.size(); ++i) {


            if (OrderStatus.get(i).getText().equals("Do Approve")||OrderStatus.get(i).getText().equals("On Hold Review")) {
                flag=true;

            }
            else {
                flag=false;
            }
        }
        return flag;
    }
    public boolean selectCheckbox() {
        boolean flag=false;
        waitForExpectedElement(selectCheckbox).click();
        flag= true;
        return flag;
    }
}
