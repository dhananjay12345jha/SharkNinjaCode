package com.salmon.test.page_objects.gui.SNEX;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class OrderSearchPage extends PageObject {

    private static final By searchBackOrder=By.xpath("//button[@name='back-order']");
    private static final By backOrderStatus=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//snex-order-status");
    private static final By searchButton=By.xpath("//button[@name='order-search']");
    public static final By newOrder=By.xpath("//select[@data-testing-id=\"orderType\"]");
    private static final By orderType=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//td[6]");
    private static final By advanceSearch=By.xpath("//button[@class=\"btn border-0 advance-search-button\"]");
    public static final By selectOrderReason=By.xpath("//select[@type='text']");
    private static final By orderReason=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//td[1]");
    private static final By textOrderReason=By.xpath("(//div[@class='data col-7'])[3]");
    private static final By enterlastName=By.xpath("//input[@data-testing-id='lastName']");
    private static final By enteremailId=By.xpath("//input[@data-testing-id='emailAddress']");
    private static final By textLastName=By.xpath("(//div[@class='data col-7'])[10]");
    private static final By textEmailId=By.xpath("(//div[@class='data col-7'])[17]");
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public boolean clickBackOrderSearch() {
        boolean flag=false;

        waitForExpectedElement(searchBackOrder).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean verifyBackorderStatus() {
        boolean flag=false;
        List<WebElement> OrderStatus = visibilityOfAllElementsLocatedBy(backOrderStatus);

        for (int i = 0; i <OrderStatus.size(); ++i) {

            if (OrderStatus.get(i).getText().equals("Do Approve") || OrderStatus.get(i).getText().equals("Back-order")||OrderStatus.get(i).getText().equals("On Hold Review")) {
                flag=true;

            }
            else
            {
                flag=false;
            }
        }
        return flag;
    }

    public boolean clickOnSearch() {
        boolean flag=false;

        waitForExpectedElement(searchButton).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean verifyOrderStatus() {
        boolean flag=false;
        List<WebElement> OrderStatus = visibilityOfAllElementsLocatedBy(backOrderStatus);

        for (int i = 0; i <OrderStatus.size(); ++i) {

            if (OrderStatus.get(i).getText().equals("Do Approve") || OrderStatus.get(i).getText().equals("Back-order")||OrderStatus.get(i).getText().equals("Not Announced")||OrderStatus.get(i).getText().equals("Commissioned")) {
                flag=true;

            }
            else {
                flag=false;
            }
        }
        return flag;
    }

    public boolean selectOrderType() {
        boolean flag=false;
        selectDropDownValue(newOrder,"visibleText","New Order");
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean verifyOrderType() {
        boolean flag=false;
        try {
            webDriver.manage().timeouts().implicitlyWait(32, TimeUnit.SECONDS);
            List<WebElement> OrderStatus = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderType));
            for (int i = 0; i < OrderStatus.size(); ++i) {

                flag = false;

                if (OrderStatus.get(i).getText().equals("New Order")) {
                    flag = true;

                }
            }
        }catch(Exception e)
        {

        }
        return flag;
    }
    public boolean clickOnAdvanceSearch() {
        boolean flag=false;

        waitForExpectedElement(advanceSearch).click();
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean selectOrderReason() {
        boolean flag=false;
        selectDropDownValue(selectOrderReason,"visibleText","Web Order");
        log.info("Agent is able to enter the order no");
        flag= true;
        return flag;
    }
    public boolean verifyOrderReason() {
        boolean flag=false;
        webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        waitForExpectedElement(orderReason).click();
        waitForExpectedElement(textOrderReason).getText().equals("Web Order");
        flag= true;
        return flag;
    }
    public boolean verifySearchResult() {
        boolean flag=false;
        waitForExpectedElement(orderReason).getText().equals(Props.getProp("order.number"));
        flag= true;
        return flag;
    }

    public boolean enterLastName(String key1, String key2) {
        boolean flag=false;
        String lastName=Props.getProp(key1);
        waitForExpectedElement(enterlastName).sendKeys(lastName);
        String emailId=Props.getProp(key2);
        waitForExpectedElement(enteremailId).sendKeys(emailId);
        flag= true;
        return flag;
    }

    public boolean verifyEmailandLastName(String key1,String key2) {
        boolean flag=false;
        waitForExpectedElement(orderReason).click();
        waitForExpectedElement(textLastName).getText().equals(Props.getProp(key1));
        waitForExpectedElement(textEmailId).getText().equals(Props.getProp(key2));
        flag= true;
        return flag;
    }
}
