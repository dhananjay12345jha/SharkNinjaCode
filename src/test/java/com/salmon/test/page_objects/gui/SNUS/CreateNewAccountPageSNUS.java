package com.salmon.test.page_objects.gui.SNUS;

import com.salmon.test.page_objects.gui.SNCA.CreateNewAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountAddressPageSNCA;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNewAccountPageSNUS extends MyAccountAddressPageSNCA {
    private static final By createAccountText = By.xpath("//div[@class='rectangle']/h1");
    private static final By enterEmailAddress = By.xpath("//ish-input[@controlname='email']/div/div/input");
    private static final By enterPassword = By.cssSelector("input[data-testing-id=\"password\"]");

    private static final Logger log = LoggerFactory.getLogger(CreateNewAccountPageSNUS.class);

    public String getTextCreateNewAccount() {
        String text = "";
        try {
            text = getTextFor(createAccountText);
            log.info("Successfully fetched the text from Create New Account page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from Create A new Account page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean enterEmailAddress(String email) {
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

    public boolean enterPassword(String password) {
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
}
