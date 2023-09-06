package com.salmon.test.page_objects.gui.SNEX;

import com.google.gson.Gson;
import com.salmon.test.framework.PageObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends PageObject {
    private static final By userName = By.xpath("//input[@data-testing-id=\"login\"]");
    private static final By password = By.xpath("//input[@data-testing-id=\"password\"]");
    private static final By loginBtn = By.name("login");
    private WebDriver driver = getWebDriver();
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);


    public DashboardPage performSNEXLoginWith(String userName, String password){
        DashboardPage dashboardPage=null;
        try {
          driver.findElement(this.userName).sendKeys(userName);
          driver.findElement(this.password).sendKeys(password);
          driver.findElement(loginBtn).click();
          dashboardPage=new DashboardPage();
          log.info("Entered Username and password and clicked on login button for SNEX");
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return dashboardPage;
    }
}
