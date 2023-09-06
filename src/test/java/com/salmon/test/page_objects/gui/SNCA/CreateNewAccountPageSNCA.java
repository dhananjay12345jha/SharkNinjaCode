package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CreateNewAccountPageSNCA extends MyAccountAddressPageSNCA {
    private static final By createNewAccountText = By.xpath("//ish-registration-form/h1");
    private static final By enterEmailAddress = By.cssSelector("input[data-testing-id=\"login\"]");
    private static final By enterConfirmEmailAddress = By.cssSelector("input[data-testing-id=\"loginConfirmation\"]");
    private static final By enterPassword = By.cssSelector("input[data-testing-id=\"password\"]");
    private static final By enterConfirmPassword = By.cssSelector("input[data-testing-id=\"passwordConfirmation\"]");
    private static final By checkBoxTermsAndCondition = By.cssSelector("input[data-testing-id=\"termsAndConditions\"]");
    private static final By checkBoxTermsAndConditionUS = By.xpath("//input[@id=\"newsletter\"]");
    private static final By createAccountBtnUS = By.xpath("//button[@class='btn btn-primary btn-lg btn-block']");
    //   private static final By errorMessageOnAccountCreationWithExistingEmailIdUS = By.xpath("//div[@class='checkError--sub']");
//    private static final By createAccountBtn = By.cssSelector("form input[type=\"submit\"]");
    private static final By createAccountBtn = By.xpath("//button[contains(@class,'btn-block')]");
    private static final By cancelBtn = By.cssSelector("input[type=\"button\"][value=\"Cancel\"]");
    private static final By accountCreationMessage = By.xpath("(//div[@class=\"description\"])[1]");
    private static final By errorMessageOnAccountCreationWithExistingEmailId = By.xpath("//div[@role=\"alertdialog\" and @aria-live=\"polite\"]");
    private static final By errorMessageOnAccountCreationWithExistingEmailIdUS = By.xpath("//div[@class=\"checkError\"]//div");
    private static final By iframeIAmNotARobot = By.xpath("//iframe[@title=\"reCAPTCHA\"]");
    //    private static final By checkBoxIamNotARobot = By.xpath("//span[@id=\"recaptcha-anchor\"]/div");
    private static final By checkBoxIamNotARobot = By.id("recaptcha-anchor");
    private static final By errorMessages = By.xpath("//small[@class='form-text']");
    private static final Logger log = LoggerFactory.getLogger(CreateNewAccountPageSNCA.class);

    public boolean enterEmailAddress(String email) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterEmailAddress, email);
            flag = true;
            log.info("Successfully entered the email id while registering which is -->" + email);
        } catch (Exception e) {
            log.error("Unable to enter the email which is-->>" + email + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterConfirmEmailAddress(String confirmEmail) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterConfirmEmailAddress, confirmEmail);
            flag = true;
            log.info("Successfully entered the confirmed email id while registering which is -->" + confirmEmail);
        } catch (Exception e) {
            log.error("Unable to enter the confirm email which is-->>" + confirmEmail + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterPassword(String password) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterPassword, password);
            flag = true;
            log.info("Successfully entered the password while registering which is -->" + password);
        } catch (Exception e) {
            log.error("Unable to enter the password while register user which is-->>" + password + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterConfirmPassword(String confirmPassword) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterConfirmPassword, confirmPassword);
            flag = true;
            log.info("Successfully entered the confirmed password id while registering which is -->" + confirmPassword);
        } catch (Exception e) {
            log.error("Unable to enter the confirm password which is-->>" + confirmPassword + " due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean tickCheckBoxTermsAndCondition() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            if (webDriver.findElements(checkBoxTermsAndCondition).size() != 0) {
                clickByElement(checkBoxTermsAndCondition);
                if (elementToBeClickable(checkBoxTermsAndCondition).isSelected()) {
                    flag = true;
                }
            } else {
                clickByElement(checkBoxTermsAndConditionUS);
                if (elementToBeClickable(checkBoxTermsAndConditionUS).isSelected()) {
                    flag = true;
                }
            }
            log.info("Successfully selected Terms and Condition checkbox while registering");
        } catch (Exception e) {
            log.error("Unable to select the checkbox Terms and Condition on User registration page due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickCreateAccountButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            elementToBeClickable(createAccountBtn).click();
            log.info("Successfully click on create account button while registering new user");
            flag = true;
        } catch (ElementClickInterceptedException e1) {
            log.error("ElementClickInterceptedException found trying one more time");
            elementToBeClickable(createAccountBtn).click();
            log.info("2nd time Successfully click on create account button while registering new user");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on create account button while registering for new user due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickCreateAccountButtonSNUS() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            elementToBeClickable(createAccountBtnUS).click();
            log.info("Successfully click on create account button while registering new user");
            flag = true;
        } catch (ElementClickInterceptedException e1) {
            log.error("ElementClickInterceptedException found trying one more time");
            elementToBeClickable(createAccountBtnUS).click();
            log.info("2nd time Successfully click on create account button while registering new user");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on create account button while registering for new user due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String getTextCreateNewAccount() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {
            text = getTextFor(createNewAccountText);
            log.info("Successfully fetched the text from Create New Account page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from Create A new Account page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getTextAfterSuccessfulAccountCreation() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            text = getTextFor(accountCreationMessage).trim();
            log.info("Successfully fetched the text from Create New Account page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from Create A new Account page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageWhenAccountCreatedWithExistingCredentials() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {

            if (webDriver.findElements(errorMessageOnAccountCreationWithExistingEmailIdUS).size() > 0) {
                text = getTextFor(errorMessageOnAccountCreationWithExistingEmailIdUS).trim();
            } else {
                text = getTextFor(errorMessageOnAccountCreationWithExistingEmailId).trim();
            }
            log.info("Successfully fetched the text from Create New Account page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from Create A new Account page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageRegisterWithExistingCredentialsSNUS() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {
            text = getTextFor(errorMessageOnAccountCreationWithExistingEmailIdUS).trim();
            log.info("Successfully fetched the text from Create New Account page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from Create A new Account page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean selectCheckBoxInsideCaptcha() {
        IsPageLoaded.waitAllRequest();
        boolean flag = true;
        if (webDriver.findElements(iframeIAmNotARobot).size() != 0) {
            webDriver.switchTo().frame(webDriver.findElement(iframeIAmNotARobot));
            int count = 0;
            while (count < 2) {
                try {
                    WebElement element = webDriver.findElement(checkBoxIamNotARobot);
                    element.click();
                    wait.until(ExpectedConditions.attributeToBe(element, "aria-checked", "true"));
                    flag = true;
                    log.info("Successfully clicked on the checkbox i am not robot");
                    IsPageLoaded.waitAllRequest();
                    break;
                } catch (Exception e) {
                    log.error(ExceptionUtils.getFullStackTrace(e));
                    e.printStackTrace();
                    count++;
                }
            }
        } else {
            log.info("I am not robot checkbox is not there ");
            flag = true;
        }
        webDriver.switchTo().defaultContent();
        return flag;
    }

    public String findWebElementAndFetchText(String name) {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            String xpath = "(//ish-input [@controlname='" + name + "']//small)[last()]";
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText().trim();
            log.info("Successfully fetched the text which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from the xpath created dynamically to get error message " + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }


}
