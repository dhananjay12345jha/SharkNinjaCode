package com.salmon.test.framework.helpers.utils;

import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class IcmOrderImportExportService {
    private WebDriverWait wait;
    private WebDriver driver;
    private Actions actions;
    private String parenWindow;
    private boolean isNewTabOpen = false;
    private static final Logger log = LoggerFactory.getLogger(IcmOrderImportExportService.class);


    //-----------locators-------------//
    By userName = By.cssSelector("input#LoginForm_Login");
    By password = By.cssSelector("input#LoginForm_Password");
    By organization = By.cssSelector("input#LoginForm_RegistrationDomain");
    By loginButton = By.cssSelector("input[data-testing-id=\"btn-login\"]");
    By organizationDropDownBox = By.xpath("//select[@id=\"channel-select-application\"]/../button");
    By orders = By.xpath("//li/a[text()=\"Orders\"]");
    By selectOptionImportExport = By.xpath("//td[@class=\"overview_subtitle\"]/a[text()=\"Import & Export\"]");
    By runButton = By.xpath("//input[@name=\"run\"]");
    By selectAll = By.xpath("//div[@id=\"OrderExportForm_A\"] //td/a");
    By checkBoxes = By.xpath("//td[@class=\"e s center\"]/input[@type=\"checkbox\"]");
    By logoutBtn=By.cssSelector("a[title='Logout']");


    private void setDriverAndOpenUrL() {
        if (WebDriverHelper.getWebDriver() != null) {
            driver = WebDriverHelper.getWebDriver();
            parenWindow = driver.getWindowHandle();
            String script = "window.open('about:blank','_blank');";
            ((JavascriptExecutor) driver).executeScript(script);
            Iterator<String> it=driver.getWindowHandles().iterator();
            while(it.hasNext()){
                String window=it.next();
                if(driver.switchTo().window(window).getTitle().equalsIgnoreCase("")){
                    driver.switchTo().window(window);
                    isNewTabOpen=true;
                    break;
                }
            }
        } else {
            driver = WebDriverHelper.getSpecificWebDriver(PermittedSiteMode.DESKTOP);
        }
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        String url = "";
        if (Props.getExecutionEnv().equalsIgnoreCase("inta")) {
            url = Props.getProp("icm.intershop.url.inta");
        } else if (Props.getExecutionEnv().equalsIgnoreCase("intb")) {
            url = Props.getProp("icm.intershop.url.intb");
        } else if (Props.getExecutionEnv().equalsIgnoreCase("uat")) {
            url = Props.getProp("icm.intershop.url.uat");
        }
        driver.get(url);
    }

    private void fillLoginDetails(String usrName, String pass, String orgnization) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(userName));
            element.clear();
            element.sendKeys(usrName);

            element = wait.until(ExpectedConditions.elementToBeClickable(password));
            element.clear();
            element.sendKeys(pass);

            element = wait.until(ExpectedConditions.elementToBeClickable(organization));
            element.clear();
            element.sendKeys(orgnization);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectOrganizationFromDropDownAs(String text) {
        WebElement element;

        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(organizationDropDownBox));
            element.click();

            element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"" + text + "\"]/parent::label/..")));
            element.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectImportAndExportFromOrders() {
        int n = 0;
        while (n < 4) {
            try {

                wait.until(ExpectedConditions.elementToBeClickable(orders)).click();
                wait.until(ExpectedConditions.elementToBeClickable(selectOptionImportExport)).click();
                if (driver.findElement(By.xpath("//td[text()=\"Order Export\"]")).isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                n++;
                e.printStackTrace();
            }
        }
    }

    private void tickSelectAllCheckBoxAndClickRun() {
        wait.until(ExpectedConditions.elementToBeClickable(selectAll)).click();
        wait.until(ExpectedConditions.elementToBeClickable(runButton)).click();
    }

    private void tickCheckBoxShownAtLocation() {
        String jobName = Props.getProp("icm.importExport.job.name");
        String xpath = "//a[text()=\"" + jobName + "\"]/../preceding-sibling::td/input[@type=\"checkbox\"]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
        wait.until(ExpectedConditions.elementToBeClickable(runButton)).click();
    }

    private void clickOnLogoutBtn(){
        try{
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public boolean runOrderImportExportService() {
        boolean flag = false;

        try {

            setDriverAndOpenUrL();

            fillLoginDetails(Props.getProp("icm.login.username"), Props.getProp("icm.login.password"), Props.getProp("icm.login.organization"));

            selectOrganizationFromDropDownAs(Props.getProp("icm.select.organization"));

            selectImportAndExportFromOrders();

            //tickSelectAllCheckBoxAndClickRun();

            tickCheckBoxShownAtLocation();

            clickOnLogoutBtn();

            if (isNewTabOpen) {
                driver.close();
                driver.switchTo().window(parenWindow);
            }
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while running the icm export import service-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

}
