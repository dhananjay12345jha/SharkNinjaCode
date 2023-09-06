package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountProfilePage extends PageObject {

    private final By heading = By.xpath("//div[@class='row account-main']/div/h1");
    private final By emailPencilIcon = By.xpath("(//span[@class='glyphicon glyphicon-pencil'])[1]");
    private final By pwdPencilIcon = By.xpath("(//span[@class='glyphicon glyphicon-pencil'])[2]");
    private final By profilePencilIcon = By.xpath("(//span[@class='glyphicon glyphicon-pencil'])[3]");
    private final By profileEmail = By.xpath("(//div[@class='col-sm-12']/dl[@class='dl-horizontal dl-separator']/dd[1])[1]");
    private final By profileName = By.xpath("(//div[@class='col-sm-12']/dl[@class='dl-horizontal dl-separator']/dd[1])[3]");
    private final By profilePhone = By.xpath("//div[@class='col-sm-12']/dl[@class='dl-horizontal dl-separator']/dd[2]");
    private final By profileLanguage = By.xpath("//div[@class='col-sm-12']/dl[@class='dl-horizontal dl-separator']/dd[3]");

    private static final Logger log = LoggerFactory.getLogger(MyAccountProfilePage.class);

    public String getHeading() {
        return waitForExpectedElement(heading).getText().trim().replace("\n", "");
    }

    public Boolean clickEmailPencilIcon() {
        boolean flag = false;
        try {
            waitForExpectedElement(emailPencilIcon).click();
            log.info("Successfully clicked email pencil icon");
            flag = true;
        } catch (Exception e) {
            log.info("Error while click on email pencil icon-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean clickPwdPencilIcon() {
        boolean flag = false;
        try {
            waitForExpectedElement(pwdPencilIcon).click();
            log.info("Successfully clicked password pencil icon");
            flag = true;
        } catch (Exception e) {
            log.info("Error while click on password pencil icon-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean clickProfilePencilIcon() {
        boolean flag = false;
        try {
            waitForExpectedElement(profilePencilIcon).click();
            log.info("Successfully clicked profile pencil icon");
            flag = true;
        } catch (Exception e) {
            log.info("Error while click on profile pencil icon-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProfileUpdateChanges() {
        boolean flag = false;
        try {
            flag = this.verifyProfileNameUpdated() && this.verifyProfilePhoneUpdated() && this.verifyProfileLanguageUpdated();
            log.info("Successfully verified profile updated");
        } catch (Exception e) {
            log.info("Error while verifying profile update-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProfileEmailUpdated() {
        boolean flag = false;
        try {
            IsPageLoaded.waitAllRequest();
            flag = waitForExpectedElement(profileEmail).getText().equalsIgnoreCase(Props.getProp("email.update"));
            log.info("Successfully verifies updated email");
        } catch (Exception e) {
            log.info("Error while verifying updated email-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProfileNameUpdated() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(profileName).getText().contains(Props.getProp("account.salutation.dr.visible") + " " + Props.getProp("account.profile.first.name") + " " + Props.getProp("account.profile.last.name"));
            log.info("Successfully verifies updated name");
        } catch (Exception e) {
            log.info("Error while verifying updated name-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProfilePhoneUpdated() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(profilePhone).getText().contains(Props.getProp("account.profile.phone.valid"));
            log.info("Successfully verifies updated phone");
        } catch (Exception e) {
            log.info("Error while verifying updated phone-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyProfileLanguageUpdated() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(profileLanguage).getText().contains(Props.getMessage("profile.updated.language"));
            log.info("Successfully verifies updated language");
        } catch (Exception e) {
            log.info("Error while verifying updated language-->>" + e.getMessage());
        }
        return flag;
    }

}
