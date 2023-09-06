package com.salmon.test.page_objects.gui.SNAP;

import com.gargoylesoftware.htmlunit.Page;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentAddressPage extends PageObject {

    private String env= Props.getProp("zendesk.testing.env");
    private static final By addressButton =By.xpath("//a[@data-testing-id=\"addresses-link\"]");
    private static final By editButton = By.xpath("(//button[text()=' Edit '])[1]");
    private static final By otherAddresseditButton = By.xpath("(//button[text()=' Edit '])[2]");
    private static final By firstName = By.xpath("(//input[@placeholder=\"First Name\"])[2]");
    private static final By lastName = By.xpath("(//input[@placeholder=\"Last Name\"])[2]");
    private static final By editButtonForShippingAdress = By.xpath("//button[@data-testing-id=\"edit-shipping-address-button\"]");
    private static final By firstNameOtherAddress = By.xpath("(//input[@placeholder=\"First Name\"])[2]");

    private static final By lastNameOtherAddress = By.xpath("(//input[@placeholder=\"Last Name\"])[2]");

    private static final By addressLine1 = By.xpath("(//input[@placeholder=\"Address Line 1\"])[2]");
    private static final By addressLine1ForShipping = By.xpath("(//input[@placeholder=\"Address Line 1\"])[1]");

    private static final By addressLine2 = By.xpath("//input[@placeholder=\"Address Line 2\"]");

    private static final By addressLine1OtherAddress = By.xpath("(//input[@placeholder=\"Address Line 1\"])[2]");

    private static final By cityOtherAddress = By.xpath("(//input[@placeholder=\"City\"])[2]");

    private static final By zipCodeOtherAddress = By.xpath("(//input[@placeholder=\"ZIP/Postal Code\"])[2]");

    //private static final By zipCode = By.xpath("//input[@placeholder=\"ZIP/Postal Code\"]");
    private static final By city = By.xpath("(//input[@placeholder=\"City\"])[2]");
    private static final By cityForShipping = By.xpath("(//input[@placeholder=\"City\"])[1]");

    //private static final By firstNameForShipping = By.xpath("(//input[@placeholder=\"First Name\"])[2]");
    private static final By firstNameForShipping = By.xpath("//input[@data-testing-id=\"firstName\"]");

    private static final By lastNameForShipping = By.xpath("(//input[@placeholder=\"Last Name\"])[1]");
    private static final By zipCode = By.xpath("(//input[@placeholder=\"ZIP/Postal Code\"])[2]");
    private static final By zipCodeForShipping = By.xpath("(//input[@placeholder=\"ZIP/Postal Code\"])[1]");

    private static final By saveChangesButton = By.xpath("(//div[@class=\"address-buttons-container\"]//button[@type=\"submit\"])[3]");

    private static final By saveChangesButtonForOtherAddress = By.xpath("//div[@id='collapseBasic']//div[@class=\"address-buttons-container\"]//button[@type=\"submit\"]");
    private static final By saveChangesButtonForShippingAddress = By.xpath("(//button[@class=\"c-btn c-btn--primary\"])[1]");
    private static final By firstNameAfterChange = By.xpath("//div[@class=\"snap-preffered-address\"]/ish-address/address");

    private static final By firstNameAfterChangeInOtherAddress = By.xpath("//div[@class=\"snap-address-row\"]/ish-address/address");
    private static final By stateName = By.xpath("//select[@class=\'c-txt__input c-txt__input--select ng-pristine ng-valid ng-touched\']");
    private static final By stateNameForShipping = By.xpath("(//select[@class=\"c-txt__input c-txt__input--select ng-untouched ng-pristine ng-valid\"])[3]");
    private final By iframe3=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");

    private static final Logger log = LoggerFactory.getLogger(ZendeskAgentDashboardPage.class);


    public boolean clickAddressButton(){
        boolean flag = false;

        try{
            webDriver.switchTo().frame(waitForExpectedElement(iframe3));
            log.info("Successfully switch into the frame");

            waitForExpectedElement(addressButton).click();
            log.info("successfully click on Address Option Button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on Address Option Button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickEditButton(){
        boolean flag = false;

        try{
            waitForExpectedElement(editButton).click();
            log.info("successfully click on Address Option Button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on Address Option Button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickEditButtonOfShippingAddress(){
        boolean flag = false;

        try{
//            webDriver.switchTo().frame(waitForExpectedElement(iframe3));
//            log.info("Successfully switch into the frame");

            waitForExpectedElement(editButtonForShippingAdress).click();
            log.info("successfully click on Address Option Button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on Address Option Button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOtherAddressEditButton(){
        boolean flag = false;

        try{
            waitForExpectedElement(otherAddresseditButton).click();
            log.info("successfully click on Address Option Button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on other Address Option Button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickSaveChangesButton(){
        boolean flag = false;

        try{
            waitForExpectedElement(saveChangesButton).click();
            log.info("successfully click on save changes button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on save changes button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickSaveChangesButtonForOtherAddress(){
        boolean flag = false;

        try{
            waitForExpectedElement(saveChangesButtonForOtherAddress).click();
            log.info("successfully click on save changes button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on save changes button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickSaveChangesButtonForShippingAddress(){
        boolean flag = false;

        try{
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe3));
            log.info("Successfully switch into the frame");

            waitForExpectedElement(saveChangesButtonForShippingAddress).click();
            log.info("successfully click on save changes button");
            flag = true;
        }
        catch (Exception e)
        {
            log.info("Unable to click on save changes button due to --" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterFirstName(String data) {
        return setTextIntoField(firstName,data);
    }

    public boolean enterFirstNameForShipping(String data) {
        return setTextIntoField(firstNameForShipping,data);
    }



    public boolean enterFirstNameOtherAddress(String data) {
        return setTextIntoField(firstNameOtherAddress,data);
    }

    public boolean enterLastNameOtherAddress(String data) {
        return setTextIntoField(lastNameOtherAddress,data);
    }

    public boolean enterAddressLine1OtherAddress(String data) {
        return setTextIntoField(addressLine1OtherAddress,data);
    }

    public boolean enterCityNameOtherAddress(String data) {

        return setTextIntoField(cityOtherAddress,data);
    }

    public boolean enterZipCodeOtherAddress(String data) {

        return setTextIntoField(zipCodeOtherAddress,data);
    }

    public String getFirstName()
    {
        return getTextFor(firstNameAfterChange).split("\n")[0].split(" ")[0].trim();
    }

    public String getFirstNameInOtherAddress()
    {
        return getTextFor(firstNameAfterChangeInOtherAddress).split("\n")[0].split(" ")[0].trim();
    }
    public boolean enterLastName(String data) {
        return setTextIntoField(lastName,data);
    }

    public boolean enterLastNameForShipping(String data) {
        return setTextIntoField(lastNameForShipping,data);
    }



    public boolean enterAddressLine1(String data) {
        return setTextIntoField(addressLine1,data);
    }

    public boolean enterAddressLine1ForShipping(String data) {
        return setTextIntoField(addressLine1ForShipping,data);
    }

    public boolean enterCityName(String data) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1000)");
        return setTextIntoField(city,data);
    }

    public boolean enterCityNameForShipping(String data) {
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(waitForExpectedElement(iframe3));
        log.info("Successfully switch into the frame");

        return setTextIntoField(cityForShipping,data);
    }

    public boolean selectStateName(String data) {
       boolean flag = false;
        try{
            Thread.sleep(2000);
            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(stateName)).build().perform();
            selectValueFromDropDownByby(data, stateName);
            //            selectByIndexFromDropDownByBy(9,stateName);
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectStateNameForShipping(String data) {
        boolean flag = false;
        try{
            Thread.sleep(2000);
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe3));

            new Actions(getWebDriver()).moveToElement(waitForExpectedElement(stateNameForShipping)).build().perform();
            selectValueFromDropDownByby(data, stateNameForShipping);
//            selectByIndexFromDropDownByBy(9,stateName);
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }


    public boolean enterZipCode(String data) {
        return setTextIntoField(zipCode,data);
    }

    public boolean enterZipCodeForShipping(String data) {
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(waitForExpectedElement(iframe3));
        log.info("Successfully switch into the frame");
        return setTextIntoField(zipCodeForShipping,data);
    }
}
