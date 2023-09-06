package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tools.ant.taskdefs.Exit;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.reporters.ExitCodeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAccountSettingPageSNCA extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPageSNCA.class);
    private static final By errorMessageInAlertText = By.xpath("//div[@class='checkError--sub']");
    private static final By successMessageInAlertTest = By.xpath("//div[@class='checkSuccess--sub']");
    private static final By currentEmailTextbox = By.cssSelector("input[type='email']");
    private static final By preferenceFirstName = By.xpath("(//div[@class='field']//input[@class='text field'])[1]");
    private static final By preferenceLastName = By.xpath("(//div[@class='field']//input[@class='text field'])[2]");
    private static final By preferenceEmail = By.xpath("//div[@class='field']//input[contains(@class,'fb-email')]");
    private static final By preferenceCheckbox = By.xpath("//div[@class='container']//input[@type='checkbox']");
    private static final By preferenceUpdatebtn = By.xpath("//input[@type='submit']");
    private static final By preferenceIframe = By.xpath("//div[contains(@class,'iframe-wrapper')]/iframe");
    private static final By preferenceUpdatedText = By.xpath("(//div[@class='section'])//strong/span");
    private static final By preferenceManagebtn = By.xpath("(//div[@class='section']//a)[4]");
    public By accountSettingPage = By.xpath("//sn-account-profile-user/h4");
    //Update Email locator
    public By newEmailTextbox = By.cssSelector("input[data-testing-id='email']");
    public By verifyPasswordTextBox = By.cssSelector("sn-account-profile-email-form input[data-testing-id='password']");
    public By updateEmailButton = By.cssSelector("sn-account-profile-email-form button");
    public By errorMessageUpdateEmailText = By.cssSelector("sn-account-profile-email-form small");
    //Update password locator
    public By newPasswordTextbox = By.cssSelector("sn-account-profile-password [data-testing-id=password]");
    public By confirmationPasswordTextBox = By.cssSelector("[data-testing-id=passwordConfirmation]");
    public By currentPasswordTextBox = By.cssSelector("[data-testing-id=currentPassword]");
    public By updatePasswordButton = By.cssSelector("sn-account-profile-password button");
    public By errorMessageUpdatePasswordText = By.cssSelector("sn-account-profile-password ish-form-control-feedback small");


    //Update Profile locator
    public By errorMessageUpdateProfileText = By.cssSelector("sn-account-profile-user-form ish-form-control-feedback small");
    public By firstNameTextbox = By.cssSelector("[data-testing-id='firstName']");
    public By lastNameTextbox = By.cssSelector("[data-testing-id='lastName']");
    public By phoneTextbox = By.cssSelector("[data-testing-id='phoneHome']");
    public By updateProfileButton = By.cssSelector("sn-account-profile-user-form button");


    public boolean checkAccountSettingPageFound() {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        flag = isElementPresentWithWait(accountSettingPage);
        String actualTitle = getTextFor(accountSettingPage);
        String expectedTitle = Props.getProp("myAccount.AccountDetails.title");
        if (flag) {
            if (actualTitle.equalsIgnoreCase(expectedTitle))
                log.info("Account Setting Page is loaded");
            else {
                log.error("Account Setting Page is not loaded, Expected Title=" + expectedTitle + " and Actual Title is= " + expectedTitle);
                flag = false;
            }
        }
        return flag;
    }

    public boolean enterNewEmail(String email) {
        String emailValue = Props.getProp(email);
        boolean flag = false;
        try {
            waitClearAndEnterText(newEmailTextbox, emailValue);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find new email field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterCurrentPassword(String value) {
        String expectedResult = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(currentPasswordTextBox, expectedResult);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find current password field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterConfirmationPassword(String value) {
        String expectedResult = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(confirmationPasswordTextBox, expectedResult);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find confirmation password field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterNewPassword(String value) {
        String expectedResult = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(newPasswordTextbox, expectedResult);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find new password field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterVerifyPassword(String password) {
        String passwordValue = Props.getProp(password);
        boolean flag = false;
        try {
            waitClearAndEnterText(verifyPasswordTextBox, passwordValue);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find verify password field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterFirstName(String value) {
        String inputData = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(firstNameTextbox, inputData);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find First field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterLastName(String value) {
        String inputData = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(lastNameTextbox, inputData);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find Last Name field" + e.getMessage());
        }
        return flag;
    }

    public boolean enterPhone(String value) {
        String inputData = Props.getProp(value);
        boolean flag = false;
        try {
            waitClearAndEnterText(phoneTextbox, inputData);
            flag = true;
        } catch (Exception e) {
            log.error("Not able to find Phone field" + e.getMessage());
        }
        return flag;
    }

    public void clickOnUpdateEmail() {
        clickByElement(updateEmailButton);
    }

    public void clickOnUpdatePassword() {
        clickByElement(updatePasswordButton);
    }

    public void clickOnUpdateProfile() {
        clickByElement(updateProfileButton);
    }


    public boolean checkErrorMsg(String error, String action) {
        boolean flag = false;
        String actualErrorMsg = "";
        String expectedErrorMsg = Props.getProp(error);
        if (!expectedErrorMsg.contains(", ")) {
            expectedErrorMsg = expectedErrorMsg.replace(",", ", ");
        }
        try {
            if (action.equalsIgnoreCase("Update Email")) {
                actualErrorMsg = getTextFor(errorMessageUpdateEmailText);
            } else if (action.equalsIgnoreCase("Update Password")) {
                actualErrorMsg = getTextFor(errorMessageUpdatePasswordText);
            } else if (action.equalsIgnoreCase("Update Profile")) {
                actualErrorMsg = getTextFor(errorMessageUpdateProfileText);
            }
            if (actualErrorMsg.equals(expectedErrorMsg)) {
                flag = true;
                log.info("Error message is correct");
            } else
                log.error("Actual message is " + actualErrorMsg + ".Though expected message is " + expectedErrorMsg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean checkErrorMsgGone(String action) {
        boolean flag = false;
        try {
            if (action.equalsIgnoreCase("Update Email")) {
                flag = isElementPresentByby(errorMessageUpdateEmailText);
            } else if (action.equalsIgnoreCase("Update Password")) {
                flag = isElementPresentByby(errorMessageUpdatePasswordText);
            } else if (action.equalsIgnoreCase("Update Profile")) {
                flag = isElementPresentByby(errorMessageUpdateProfileText);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean checkErrorAlertMsg(String error) {
        boolean flag = false;
        String expectedErrorMsg = Props.getProp(error);
        try {
            String actualErrorMsg = getTextFor(errorMessageInAlertText);
            if (actualErrorMsg.equals(expectedErrorMsg)) {
                flag = true;
                log.info("Error message is correct");
            } else
                log.error("Actual message is " + actualErrorMsg + ".Though expected message is " + expectedErrorMsg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean checkSuccessAlertMsg(String msg) {
        boolean flag = false;
        String expectedMsg = Props.getProp(msg);
        try {
            String actualMsg = getTextFor(successMessageInAlertTest);
            if (actualMsg.equals(expectedMsg)) {
                flag = true;
                log.info("Success message is correct");
            } else
                log.error("Actual message is " + actualMsg + ".Though expected message is " + expectedMsg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean checkProfileEmail(String email) {
        boolean flag = false;
        String expectedEmail = Props.getProp(email);
        try {
            String actualEmail = getTextFor(currentEmailTextbox);
            if (actualEmail.equals(expectedEmail)) {
                flag = true;
                log.info("Email is correct");
            } else
                log.error("Actual Email is " + actualEmail + ".Though expected Email is " + expectedEmail);
        } catch (Exception e) {
            log.error(e.getMessage());
            flag = true;
        }
        return flag;

    }


    public boolean setPreferenceEmail(String value) {
        boolean flag = false;
        frameToBeAvailableAndSwitchToIt(preferenceIframe);
        if (isElementPresentByby(preferenceEmail)) {
            waitClearAndEnterText(preferenceEmail, value);
            flag = true;

        }
        webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean setPreferenceFirstName(String value) {
        boolean flag = false;
        frameToBeAvailableAndSwitchToIt(preferenceIframe);
        IsPageLoaded.waitAllRequest();
        if (isElementPresentByby(preferenceFirstName)) {
            waitClearAndEnterText(preferenceFirstName, value);
            flag = true;

        }
        //  webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean setPreferenceLastName(String value) {
        boolean flag = false;
        //frameToBeAvailableAndSwitchToIt(preferenceIframe);
        if (isElementPresentByby(preferenceLastName)) {
            waitClearAndEnterText(preferenceLastName, value);
            flag = true;

        }
        //webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean checkPreferenceCheckboxes() {
        boolean flag = false;
        Actions actions = new Actions(getWebDriver());
        List<WebElement> element = presenceOfAllElementsLocatedBy(preferenceCheckbox);
        if (element.size() > 0) {
            flag = true;
        }
        for (WebElement ele : element) {
            int count = 0;
            do {
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                actions.moveToElement(ele).build().perform();
                if (ele.isSelected()) {
                    break;
                }
                ele.click();
                count++;
            } while (count < 3);
        }
        return flag;
    }

    public boolean unCheckPreferenceCheckboxes() {
        boolean flag = false;
        //frameToBeAvailableAndSwitchToIt(preferenceIframe);
        Actions actions = new Actions(getWebDriver());
        List<WebElement> element = presenceOfAllElementsLocatedBy(preferenceCheckbox);
        if (element.size() > 0)
            flag = true;
        for (WebElement ele : element) {
            int count = 0;
            do {
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                actions.moveToElement(ele).build().perform();
                if (!ele.isSelected()) {
                    break;
                }
                ele.click();
                count++;
            } while (count < 3);
        }
        //webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean UpdatePreference() {
        boolean flag = false;
        try {
            if (isElementPresentWithWait(preferenceUpdatebtn)) {
                if (UrlBuilder.isMobile()) {
                    int count = 0;
                    do {
                        if (getWebDriver().findElements(preferenceUpdatebtn).size() > 0) {
                            WebElement element = getWebDriver().findElement(preferenceUpdatebtn);
                            new Actions(getWebDriver()).moveToElement(element).build().perform();
                            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                        } else if (getWebDriver().findElements(preferenceUpdatebtn).size() == 0) {
                            break;
                        }
                        count++;
                    } while (count < 4);
                } else {
                    clickByElement(preferenceUpdatebtn);
                }
                if (isElementPresentWithWait(preferenceUpdatedText)) {
                    flag = true;
                }
            }
        } catch (NotFoundException e1) {
            log.error("Unable to find preference updated btn please check-->>" + ExceptionUtils.getStackTrace(e1));
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("Some exception occurred-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        } finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean VerifyPreferenceSelected() {
        frameToBeAvailableAndSwitchToIt(preferenceIframe);
        boolean flag = true;
        Actions actions = new Actions(getWebDriver());
        List<WebElement> element = getAllElements(preferenceCheckbox);
        for (WebElement ele : element) {
            actions.moveToElement(ele).build().perform();
            if (!ele.isSelected()) {
                flag = false;
                break;
            }
        }
        webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean VerifyPreferencedeSelected() {
        boolean flag = true;
        frameToBeAvailableAndSwitchToIt(preferenceIframe);
        List<WebElement> element = getAllElements(preferenceCheckbox);
        for (WebElement ele : element) {
            if (ele.isSelected()) {
                flag = false;
                break;
            }
        }
        webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean managePreference() {
        boolean flag = false;
        frameToBeAvailableAndSwitchToIt(preferenceIframe);
        if (isElementPresentByby(preferenceManagebtn)) {
            clickByElement(preferenceManagebtn);
            if (isElementPresentByby(preferenceEmail))
                flag = true;
        }
        webDriver.switchTo().defaultContent();
        return flag;
    }
}
