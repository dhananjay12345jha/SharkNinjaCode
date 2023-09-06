package com.salmon.test.page_objects.gui.SNEX;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.CreateReplacementForProductPage;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FraudActivityPage extends PageObject {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    private String propFilePath = System.getProperty("user.dir")+Props.getProp("file.path");
    private static final By orderNumber=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//td[1]");
    private static final By fraudActivity=By.xpath("(//button[@class='c-btn c-btn--sm'])[4]");
    private static final By selectCheckbox=By.xpath("(//input[@type='checkbox'])[2]");
    private static final By orderOptions=By.xpath("//button[@class=\"c-btn\"]");
    private static final By approveFraudCheck=By.xpath("//div[@class='menu-container is-opened']/ul/li/a");
    private static final By enterDecisionNote=By.xpath("//textarea[@data-testing-id='fraudNote']");
    private static final By mainOrder=By.xpath("//a[@class='snex-left-menu active-link']");
    private static final By confirm=By.xpath("//button[@data-testing-id='confirm']");
    private static final By enterOrderNumber=By.xpath("//input[@data-testing-id='orderNumber']");
    private static final By approvedOrderStatus=By.xpath("//div[@class='snex-orders-list ml-4 mr-4']//table/..//td[7]");
    private static final By enterCommentMessage=By.xpath("//textarea[@data-testing-id='fraudActionCommentBox']");
    private static final By confirmButton=By.xpath("(//button[@type='submit'])[2]");
    private static final By backOrderStatus=By.xpath("(//button[@type='submit'])[2]");
    private static final By orderOptionButton=By.xpath("//button[@class='c-btn c-btn--full']");
    private static final By orderStatus=By.xpath("(//button[@type='submit'])[2]");

    public boolean fetchAndSaveOrderNumberIntoApiConfigFileHavingKey(String key) {
        boolean flag = false;
        String orderNumber = fetchOrderNumberFromOrderSummaryPage();
        if (!orderNumber.equalsIgnoreCase("")) {
            try {
                log.info("Value of order Number before is-->>" + Props.getProp(key));
                PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
                config.setProperty(key, orderNumber);
                config.save();
                log.info("Successfully updated the config file with value of orderNumber as-->" + orderNumber);
                flag = true;
            } catch (Exception e) {
                log.error("Some exception occurred while updating the config file with value of order Number--->" + e.getMessage());
                e.printStackTrace();
            }

            Props.loadRunConfigProps("/environment.properties");
        } else {
            log.error("Order number is empty, Unable to set the order number against key in config file");
        }
        return flag;
    }

    public String fetchOrderNumberFromOrderSummaryPage() {
        String text = "";
        int count = 0;
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
                text = element.getText().trim();
                if (!(text.equalsIgnoreCase(""))) {
                    log.info("Found order having order number-->" + text);
                    break;
                }

            } catch (Exception e) {
                log.error("Some exception occurred while getting order number from order summary page-->" + e.getMessage());
                e.printStackTrace();
            }
            count++;
        } while (count < 5);
        return text;
    }

    public boolean clickOnOrder() {
        boolean flag=false;
        waitForExpectedElement(orderNumber).click();
        waitForExpectedElement(orderNumber).click();
        flag= true;
        return flag;
    }

    public boolean fraudActivity() {
        boolean flag=false;
        waitForExpectedElement(fraudActivity).click();
        flag= true;
        return flag;
    }

    public boolean selectCheckbox() {
        boolean flag=false;
        waitForExpectedElement(selectCheckbox).click();
        flag= true;
        return flag;
    }

    public boolean clickApproveFraudCheck() {
        boolean flag=false;
        waitForExpectedElement(orderOptions).click();
        flag= true;
        return flag;
    }

    public boolean enterDecisionNote() {
        boolean flag=false;
        webDriver.switchTo().activeElement();
        waitForExpectedElement(enterDecisionNote).sendKeys("Approved");
        flag= true;
        return flag;
    }
    public boolean selectOptionShownUnderOrderOptionButton(String optionToSelect)
    {
        boolean flag = false;
        try {

            log.info("Going to select the option " + optionToSelect + " from the order option drop down");
            visibilityOfAllElementsLocatedBy(approveFraudCheck).
                    stream().filter(s -> s.getText().toLowerCase().contains(optionToSelect.toLowerCase())).
                    findFirst().get().click();
            flag = true;
        }catch(Exception ee){

        }
        return flag;

    }
    public boolean clickOnMainOrder() {
        boolean flag=false;
        waitForExpectedElement(mainOrder).click();
        flag= true;
        return flag;
    }

    public boolean clickOnConfirmButton() {
        boolean flag=false;
        waitForExpectedElement(confirm).click();
        flag= true;
        return flag;
    }
    public boolean enterOrderNumber(String key) {
        boolean flag=false;
        String orderNumber="";
        try{
            Properties prop=new Properties();
            prop.load(new FileReader(propFilePath));
            orderNumber=prop.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        //String orderNumber=Props.getProp(key);
        waitForExpectedElement(enterOrderNumber).sendKeys(orderNumber);
        flag= true;
        return flag;
    }

    public boolean approvedOrderStatus() {
        boolean flag=false;
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(waitForExpectedElement(approvedOrderStatus).getText());
        if(waitForExpectedElement(approvedOrderStatus).getText().equals("Do Approve")) {

        }
        else {
            flag=true;
        }
        return flag;
    }
    public boolean rejectOrderStatus() {
        boolean flag=false;
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(waitForExpectedElement(approvedOrderStatus).getText());
        if(waitForExpectedElement(approvedOrderStatus).getText().equals("Canceled")) {
            flag=true;

        }
        return flag;
    }
    public boolean enterCommentMessage() {
        boolean flag=false;
        webDriver.switchTo().activeElement();
        waitForExpectedElement(enterCommentMessage).sendKeys("Approved");
        flag= true;
        return flag;
    }

    public boolean clickOnConfirm() {
        boolean flag=false;
        waitForExpectedElement(confirmButton).click();
        flag= true;
        return flag;
    }

    public boolean verifyOrderOptionButton()
    {
        String text="not found";
        text=waitForExpectedElement(backOrderStatus).getText();
        WebElement button = waitForExpectedElement(orderOptionButton);
        return text.equals("Back-order") && button.isEnabled();
    }
    public boolean verifyOrderOptionButton1()
    {
        String text="not found";
        text=waitForExpectedElement(orderStatus).getText();
        WebElement button = waitForExpectedElement(orderOptionButton);
        return !text.equals("Back-order") && !button.isEnabled();
    }

    public boolean clickOrder() {
        boolean flag=false;
        List<WebElement> OrderStatus = visibilityOfAllElementsLocatedBy(backOrderStatus);

        for (int i = 0; i <OrderStatus.size(); ++i) {

            if (OrderStatus.get(i).getText().equals("Back-order")) {
                flag=false;
            }
            else{
                waitForExpectedElement(orderNumber).click();
                flag=true;
                break;
            }

        }
        return flag;
    }

}
