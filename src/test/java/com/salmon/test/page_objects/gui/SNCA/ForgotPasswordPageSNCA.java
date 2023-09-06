package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordPageSNCA extends PageObject {
    private static final By forgotPasswordText = By.xpath("//h1");
    private static final By emailAddressTextBox = By.cssSelector("input[data-testing-id=\"email\"]");
    private static final By sendBtn = By.cssSelector("button[type=\"submit\"]");
    private static final By emailSendConfirmationMessage = By.xpath("//h1/../p");
    private static final By errorMessageEmail = By.xpath("//small");
    private static final By errorMessageInAlertText = By.xpath("//div[@class=\"checkError--sub\"]");
    private static final By successMessageInAlertTest = By.xpath("//div[@class=\"checkSuccess--sub\"]");
    private static final By iamNotRobotChkbox = By.xpath("//re-captcha");
    String te = "An e-mail with a link has been sent to your e-mail address. Please follow this link to change your password.";

    private static final Logger log = LoggerFactory.getLogger(ForgotPasswordPageSNCA.class);

    public String getTextForgotPassword() {
        String text = "";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordText)).getText().trim();
            log.info("Successfully fetched the text-->" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text please check logs-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean checkErrorMsg(String error) {
        boolean flag = false;
        String expectedErrorMsg = Props.getProp(error);
        try {
            String actualErrorMsg = getTextFor(errorMessageEmail);
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

    public String checkAlertMsgError() {
        String text = "Unable to Find";
        IsPageLoaded.waitAllRequest();
        try {
            text= getTextFor(errorMessageInAlertText).trim();
            log.info("Actual Error Message shown is-->>"+text);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String checkAlertMsgSuccess() {
        String text ="Not Found PLease check";
//        IsPageLoaded.waitAllRequest();
        try {

            if(webDriver.findElements(successMessageInAlertTest).size()>0){
                text = getTextFor(successMessageInAlertTest).trim();
            }
            else{
                text=getTextFor(errorMessageInAlertText).trim();
            }
            log.info("Message Found-->>"+text);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return text;
    }

    public boolean enterUserName(String email) {
        boolean flag = false;
        String emailValue = Props.getProp(email);
        try {
            waitClearAndEnterText(emailAddressTextBox, emailValue);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter email after clicking on sign in button for sharkCA-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public void clickSendRecoverLink() throws InterruptedException {
        //Click on I am not Robot if present
        clickByElementByQueryJSExecutor(sendBtn);
    }
}
