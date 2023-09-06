package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.step_definitions.gui.MyAccountAddressPageSteps;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class MyAccountAddressPage extends PageObject {

    private final By heading = By.xpath("//div[@class='my-account-savedAddresses myAccount-addressBox']/h1");
    private final By addAddressLink = By.xpath("//a[@class='btn btn-default my-account-add-address']");
    private final By firstName = By.id("address_FirstName");
    private final By lastName = By.id("address_LastName");
    private final By street = By.id("address_Address1");
    private final By optional = By.id("address_Address2");
    private final By city = By.id("address_City");
    private final By postalCode = By.id("address_PostalCode");
    private final By phone = By.id("phoneInput");
    private final By preferredInvoice = By.xpath("(//label[@class='control-label'])[1]");
    private final By preferredBilling = By.xpath("(//label[@class='control-label'])[2]");
    private final By saveAddressBtn = By.cssSelector("[name='createAddress']");
    private final By updateAddressBtn = By.cssSelector("[name='update']");
    private final By addressBook = By.xpath("//div[@class='myAccount-addressBook']");
    private final By trashLast = By.xpath("(//span[@class='glyphicon glyphicon-trash'])[last()]");
    private final By deleteBtn = By.xpath("//form[@class='form-horizontal bv-form']/div[@class='modal-footer']/button[@class='btn btn-primary']");
    private final By firstNameEmptyError = By.xpath("//small[@data-bv-for='address_FirstName'][@data-bv-validator='notEmpty']");
    private final By firstNameForbiddenError = By.xpath("//small[@data-bv-for='address_FirstName'][@data-bv-validator='forbiddensymbols']");
    private final By lastNameEmptyError = By.xpath("//small[@data-bv-for='address_LastName'][@data-bv-validator='notEmpty']");
    private final By lastNameForbiddenError = By.xpath("//small[@data-bv-for='address_LastName'][@data-bv-validator='forbiddensymbols']");
    private final By streetNameEmptyError = By.xpath("//small[@data-bv-for='address_Address1'][@data-bv-validator='notEmpty']");
    private final By cityNameEmptyError = By.xpath("//small[@data-bv-for='address_City'][@data-bv-validator='notEmpty']");
    private final By postalCodeEmptyError = By.xpath("//small[@data-bv-for='address_PostalCode'][@data-bv-validator='notEmpty']");
    private final By phoneNumberInvaidError = By.xpath("//small[@data-bv-for='address_PhoneHome'][@data-bv-validator='regexp']");
    private final By postalCodeInvaidError = By.xpath("//small[@data-bv-for='address_PostalCode'][@data-bv-validator='regexp']");
    private final By editAddressPencilIconLast = By.xpath("(//span[@class='glyphicon glyphicon-pencil'])[last()]");
    private final By firstNameUpdateTxtBox = By.xpath("(//input[@id='address_FirstName'])[2]");
    private final By lastNameUpdateTxtBox = By.xpath("(//input[@id='address_LastName'])[2]");
    private final By streetUpdateTxtBox = By.xpath("(//input[@id='address_Address1'])[2]");
    private final By optionalUpdateTxtBox = By.xpath("(//input[@id='address_Address2'])[2]");
    private final By cityUpdateTxtBox = By.xpath("(//input[@id='address_City'])[2]");
    private final By postalCodeUpdateTxtBox = By.xpath("(//input[@id='address_PostalCode'])[2]");
    private final By phoneUpdateTxtBox = By.xpath("(//input[@id='phoneInput'])[2]");
    private final By addressExistMessage = By.xpath("//div[@class='info-message alert alert-info']/p");

    private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPage.class);

    public String getHeading() {
        IsPageLoaded.waitAllRequest();
        return waitForExpectedElement(heading).getText();
    }

    public boolean checkAddAddressLink() {
        IsPageLoaded.waitAllRequest();
        return waitForExpectedElement(addAddressLink).isDisplayed();
    }

    public void clickAddAddressLink() {
        IsPageLoaded.waitAllRequest();
//		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
//		jse.executeScript("arguments[0].scrollIntoView()", waitForExpectedElement(addAddressLink));
        clickByElementByQueryJSExecutor(addAddressLink);
//		waitForExpectedElement(addAddressLink).click();
    }

    public By getFirstName() {
        waitForExpectedElement(firstName);
        return firstName;
    }

    public WebElement FirstName() {
        return webDriver.findElement(firstName);
    }

    public boolean enterFirstName(String fName) {

        boolean flag = false;
        try {
            waitForExpectedElement(firstName).clear();
            waitForExpectedElement(firstName).sendKeys(fName);
            log.info("Successfully entered first name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter first name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getLastName() {
        waitForExpectedElement(lastName);
        return lastName;
    }

    public boolean enterLastName(String lName) {
        boolean flag = false;
        try {
            waitForExpectedElement(lastName).clear();
            waitForExpectedElement(lastName).sendKeys(lName);
            log.info("Successfully entered first name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter first name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getStreet() {
        waitForExpectedElement(street);
        return street;
    }

    public boolean enterStreet(String street1) {
        boolean flag = false;
        try {
            waitForExpectedElement(street).clear();
            waitForExpectedElement(street).sendKeys(street1);
            log.info("Successfully entered street name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter street name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getStreetOptional() {
        waitForExpectedElement(optional);
        return optional;
    }

    public void enterStreetOptional(String optional1) {
        waitForExpectedElement(optional).sendKeys(optional1);
    }

    public By getCity() {
        waitForExpectedElement(city);
        return city;
    }

    public boolean enterCity(String city1) {
        boolean flag = false;
        try {
            waitForExpectedElement(city).clear();
            waitForExpectedElement(city).sendKeys(city1);
            log.info("Successfully entered city name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter city name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getPostalCode() {
        waitForExpectedElement(postalCode);
        return postalCode;
    }

    public boolean enterPostalCode(String postalCode1) {
        boolean flag = false;
        try {
            waitForExpectedElement(postalCode).clear();
            waitForExpectedElement(postalCode).sendKeys(postalCode1);
            log.info("Successfully entered postal code on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter postal code on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getPhone() {
        waitForExpectedElement(phone);
        return phone;
    }

    public boolean enterPhone(String phone1) {
        boolean flag = false;
        try {
            waitForExpectedElement(phone).clear();
            waitForExpectedElement(phone).sendKeys(phone1);
            log.info("Successfully entered phone number on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while enter phone number on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public By getPreferredInvoice() {
        waitForExpectedElement(preferredInvoice);
        return preferredInvoice;
    }

    public void selectPreferredInvoice() {
        waitForExpectedElement(preferredInvoice).click();
    }

    public By getPreferredBilling() {
        waitForExpectedElement(preferredBilling);
        return preferredBilling;
    }

    public void selectPreferredBilling() {
        waitForExpectedElement(preferredBilling).click();
    }

    public void clickSaveAddressBtn() {
        waitForExpectedElement(saveAddressBtn).click();
    }

    public WebElement getAddressBook() {
        return webDriver.findElement(addressBook);
    }

    public void clickTrashIcon(String fName) {
        String xpath = "//address[contains(text(),'" + fName + "')]//parent::div//a[@class='btn-tool remove-address']";
        webDriver.findElement(By.xpath(xpath)).click();
    }

    public void clickTrashIconLastUpdated(String fName) {
        String xPath = "//address[contains(text(),'" + MyAccountAddressPageSteps.reverseString(fName) + "')]//parent::div//a[@class='btn-tool remove-address']";
        webDriver.findElement(By.xpath(xPath)).click();
    }

    public boolean clickPencilIconLastAddress(String fName) {
        boolean flag = false;
        try {
            String xpath = "//address[contains(text(),'" + fName + "')]//parent::div//a[@class='btn-tool update-address']";
            webDriver.findElement(By.xpath(xpath)).click();
            log.info("Successfully clicked last pencil icon to update address");
            flag = true;
        } catch (Exception e) {
            log.info("Error while click on last pencil icon to update address-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean clickUpdateAddress() {
        boolean flag = false;
        try {
            waitForExpectedElement(updateAddressBtn).click();
            log.info("Successfully clicked update address button to update address");
            flag = true;
        } catch (Exception e) {
            log.info("Error while click on update address button to update address-->>" + e.getMessage());
        }
        return flag;
    }

    public void clickDeleteBtn() {
        waitForExpectedElement(deleteBtn).click();
        IsPageLoaded.waitAllRequest();
    }

    public Boolean verifyFirstNameEmptyError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(firstNameEmptyError).getText().contains(Props.getMessage("address.error.empty.firstName"));
            log.info("Successfully found empty error message for first name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding empty error message for first name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyFirstNameInvalidError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(firstNameForbiddenError).getText().contains(Props.getMessage("address.error.invalid.firstName"));
            log.info("Actual message is--> " + waitForExpectedElement(firstNameForbiddenError).getText());
            log.info("Successfully found invalid error message for first name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding invalid error message for first name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyLastNameInvalidError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(lastNameForbiddenError).getText().contains(Props.getMessage("address.error.invalid.lastName"));
            log.info("Successfully found invalid error message for last name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding invalid error message for last name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPhoneInvalidErrorMessage() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(phoneNumberInvaidError).getText().contains(Props.getMessage("address.error.phone.invalid"));
            log.info("Successfully found invalid error message for phone number on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding invalid error message for phone number on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPostalCodeInvalidErrorMessage() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(postalCodeInvaidError).getText().contains(Props.getMessage("address.error.postalCode.invalid"));
            log.info("Successfully found invalid error message for postal code on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding invalid error message for postal code on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyLastNameEmptyError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(lastNameEmptyError).getText().contains(Props.getMessage("address.error.empty.lastName"));
            log.info("Successfully found empty error message for last name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding empty error message for last name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyStreetNameEmptyError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(streetNameEmptyError).getText().contains(Props.getMessage("address.error.empty.streetName"));
            log.info("Successfully found empty error message for street name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding empty error message for street name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyCityNameEmptyError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(cityNameEmptyError).getText().contains(Props.getMessage("address.error.empty.cityName"));
            log.info("Successfully found empty error message for city name on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding empty error message for city name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPostalCodeEmptyError() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(postalCodeEmptyError).getText().contains(Props.getMessage("address.error.empty.postalCode"));
            log.info("Successfully found error message for empty postal code on address page");
        } catch (Exception e) {
            log.info("Some exception occurred while finding error message for empty postal code on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyFirstNameInvalidErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(firstNameForbiddenError);
            log.info("Error message for first name is not shown");
        } catch (Exception e) {
            log.info("Error message for first name is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyLastNameInvalidErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(lastNameForbiddenError);
            log.info("Error message for last name is not shown");
        } catch (Exception e) {
            log.info("Error message for last name is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyStreetNameEmptyErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(streetNameEmptyError);
            log.info("Error message for street name is not shown");
        } catch (Exception e) {
            log.info("Error message for street name is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyCityNameEmptyErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(cityNameEmptyError);
            log.info("Error message for city name is not shown");
        } catch (Exception e) {
            log.info("Error message for city name is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPhoneNumberInvalidErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(phoneNumberInvaidError);
            log.info("Error message for phone number is not shown");
        } catch (Exception e) {
            log.info("Error message for phone number is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPostalCodeInvalidErrorMessageNotShown() {
        boolean flag = false;
        try {
            flag = invisibilityOfElementLocated(postalCodeInvaidError);
            log.info("Error message for postal code is not shown");
        } catch (Exception e) {
            log.info("Error message for postal code is shown-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updateFirstName(String fName) {

        boolean flag = false;
        try {
            waitForExpectedElement(firstNameUpdateTxtBox).clear();
            waitForExpectedElement(firstNameUpdateTxtBox).sendKeys(fName);
            log.info("Successfully updated first name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating first name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updateLastName(String lName) {
        boolean flag = false;
        try {
            waitForExpectedElement(lastNameUpdateTxtBox).clear();
            waitForExpectedElement(lastNameUpdateTxtBox).sendKeys(lName);
            log.info("Successfully updated last name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating last name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updateStreetName(String street1) {
        boolean flag = false;
        try {
            waitForExpectedElement(streetUpdateTxtBox).clear();
            waitForExpectedElement(streetUpdateTxtBox).sendKeys(street1);
            log.info("Successfully updated street name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating street name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updateStreetOptionalName(String street1) {
        boolean flag = false;
        try {
            waitForExpectedElement(optionalUpdateTxtBox).clear();
            waitForExpectedElement(optionalUpdateTxtBox).sendKeys(street1);
            log.info("Successfully updated street optional name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating street optional name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updateCityName(String city1) {
        boolean flag = false;
        try {
            waitForExpectedElement(cityUpdateTxtBox).clear();
            waitForExpectedElement(cityUpdateTxtBox).sendKeys(city1);
            log.info("Successfully updating city name on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating city name on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updatePostalCode(String postalCode1) {
        boolean flag = false;
        try {
            waitForExpectedElement(postalCodeUpdateTxtBox).clear();
            waitForExpectedElement(postalCodeUpdateTxtBox).sendKeys(postalCode1);
            log.info("Successfully updated postal code on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating postal code on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean updatePhoneNumber(String phone1) {
        boolean flag = false;
        try {
            waitForExpectedElement(phoneUpdateTxtBox).clear();
            waitForExpectedElement(phoneUpdateTxtBox).sendKeys(phone1);
            log.info("Successfully updating phone number on address page");
            flag = true;
        } catch (Exception e) {
            log.info("Some exception occurred while updating phone number on address page-->>" + e.getMessage());
        }
        return flag;
    }

    public String verifyAddressAlreadyExistMessage() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addressExistMessage));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text = element.getAttribute("innerText").trim();
            log.info("Text shown in message After adding address message is --->>" + text);
            if (!text.equalsIgnoreCase("not found")) {
                log.info("Message displayed successfully ");
            } else {
                log.error("Message \"Address already exist on address page\" is not displayed ");
                throw new RuntimeException("Message \"Address already exist on address page\" is not displayed ");
            }
        } catch (Exception e) {
            log.info("Some exception occurred while finding message for address already exist on address page-->>" + e.getMessage());
        }
        return text;
    }
}
