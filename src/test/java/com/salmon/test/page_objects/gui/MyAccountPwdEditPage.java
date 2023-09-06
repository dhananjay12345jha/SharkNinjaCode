package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPwdEditPage extends PageObject{

    private final By heading = By.xpath("//div[@class='col-md-9']/h1");
    private final By currentPasswordTxtBox = By.xpath("//input[@id='UpdatePasswordForm_Password']");
    private final By newpasswordTxtBox = By.xpath("//input[@id='UpdatePasswordForm_NewPassword']");
    private final By confirmPasswordTxtBox = By.xpath("//input[@id='UpdatePasswordForm_NewPasswordConfirmation']");
    private final By passwordErrorEmpty = By.xpath("//small[@data-bv-for='UpdatePasswordForm_NewPassword'][1]");
    private final By passwordErrorInvalid = By.xpath("//small[@data-bv-for='UpdatePasswordForm_NewPassword'][2]");
    private final By passwordErrorLength = By.xpath("//small[@data-bv-for='UpdatePasswordForm_NewPassword'][3]");
    private final By confirmPasswordMismatchError = By.xpath("//small[@data-bv-validator='identical']");
    private final By confirmPasswordEmptyError = By.xpath("//small[@data-bv-validator='notEmpty'][@data-bv-for='UpdatePasswordForm_NewPasswordConfirmation']");
    private final By pwdUpdateSuccessMessage = By.xpath("//div[@class='alert alert-success alert-fade']/p");

    private static final Logger log = LoggerFactory.getLogger(MyAccountPwdEditPage.class);

    public Boolean verifyHeading() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(heading).getText().contains(Props.getMessage("profile.password.edit"));
            log.info("Successfully verified heading of edit password page");
        } catch (Exception e) {
            log.info("Error while verifying heading on edit password page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean fillCurrentPassword(String password) {
        boolean flag=false;
        try {
            waitForExpectedElement(currentPasswordTxtBox).clear();
            waitForExpectedElement(currentPasswordTxtBox).sendKeys(password);
            log.info("Successfully entered password on edit password page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling password on edit password page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean fillNewPassword(String password) {
        boolean flag=false;
        try {
            waitForExpectedElement(newpasswordTxtBox).clear();
            waitForExpectedElement(newpasswordTxtBox).sendKeys(password);
            log.info("Successfully entered password on edit password page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling password on edit password page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorPasswordInvalid() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(passwordErrorInvalid).getText().contains(Props.getMessage("error.password.invalid")) && waitForExpectedElement(passwordErrorLength).getText().contains(Props.getMessage("error.password.length"));
            log.info("Successfully verified password invalid and length error messages");
        } catch (Exception e) {
            log.info("Error while verifying password invalid and length error messages-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPasswordErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(passwordErrorEmpty) && invisibilityOfElementLocated(passwordErrorInvalid) && invisibilityOfElementLocated(passwordErrorLength);
            log.info("Error message for password is not shown");
        } catch (Exception e) {
            log.info("Error message for password is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean fillConfirmPassword(String password) {
        boolean flag=false;
        try {
            waitForExpectedElement(confirmPasswordTxtBox).clear();
            waitForExpectedElement(confirmPasswordTxtBox).sendKeys(password);
            log.info("Successfully entered password on edit password page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling password on edit password page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyConfirmPasswordMismatchError() {
        boolean flag = false;
        try {
            waitForExpectedElement(confirmPasswordMismatchError).getText().contains(Props.getMessage("error.confirm.password.mismatch"));
            log.info("Successfully verified confirm password mismatch error messages");
            flag = true;
        } catch (Exception e) {
            log.info("Error while verifying confirm password mismatch error messages-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyConfirmPasswordErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(confirmPasswordEmptyError) && invisibilityOfElementLocated(confirmPasswordMismatchError);
            log.info("Error message for confirm password is not shown");
        } catch (Exception e) {
            log.info("Error message for confirm password is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPasswordUpdateSuccessMsg() {
        boolean flag=false;
        try {
            //System.out.println("site--"+waitForExpectedElement(pwdUpdateSuccessMessage).getText());
            //System.out.println("Props.getMessage(\"password.update.success.message\")---"+Props.getMessage("password.update.success.message"));
            flag = waitForExpectedElement(pwdUpdateSuccessMessage).getText().contains(Props.getMessage("password.update.success.message"));
            log.info("Successfully updated password");
        } catch (Exception e) {
            log.info("Error while updating password-->>"+e.getMessage());
        }
        return flag;
    }
}
