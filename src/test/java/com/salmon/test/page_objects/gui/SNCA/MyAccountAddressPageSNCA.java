package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAccountAddressPageSNCA extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPageSNCA.class);
    private static final By addressPage = By.xpath("//h3[text()=\"Shipping & Billing Address\" or text()=\"Shipping Address\"]");
    private static final By addressPageFr = By.xpath("//h3[text()=\"Votre facturation de adresse et de livraison préférée\" or text()=\"Votre adresse de livraison préférée\"]");
    private static final By getTextAddresses = By.xpath("//div[contains(@class,'savedAddresses')]/h1/a");
    private static final By addAddressButton = By.cssSelector("a[data-testing-id=\"create-address-button\"]");
    private static final By addAddressFormContainer = By.cssSelector("form[novalidate=\"novalidate\"]");
    private static final By selectCountryFromDropDown = By.cssSelector("select[data-testing-id=\"countryCodeSwitch\"]");
    private static final By setFirstName = By.cssSelector("input[data-testing-id=\"firstName\"]");
    private static final By setLastName = By.cssSelector("input[data-testing-id=\"lastName\"]");
    private static final By setBillingFirstName = By.xpath("(//input[@data-testing-id='firstName'])[2]");
    private static final By setBillingLastName = By.xpath("(//input[@data-testing-id='lastName'])[2]");
    private static final By setStreetAddress = By.cssSelector("input[data-testing-id=\"addressLine1\"]");
    private static final By setBillingStreetAddress = By.xpath("(//input[@data-testing-id='addressLine1'])[2]");
    private static final By setPostalCode = By.cssSelector("input[data-testing-id=\"postalCode\"]");
    private static final By setBillingPostalCode = By.xpath("(//input[@data-testing-id='postalCode'])[2]");
    private static final By setCity = By.cssSelector("input[data-testing-id=\"city\"]");
    private static final By setBillingCity = By.xpath("(//input[@data-testing-id='city'])[2]");
    private static final By selectProvinceFromDropDown = By.cssSelector("select[data-testing-id=\"mainDivisionCode\"]");
    private static final By selectBillingProvinceFromDropDown = By.xpath("(//select[@data-testing-id='mainDivisionCode'])[2]");
    private static final By setPhoneNumber = By.xpath("//input[@data-testing-id=\"phoneHome\"]");
    private static final By setBillingPhoneNumber = By.xpath("(//input[@data-testing-id='phoneHome'])[2]");
    private static final By saveAddressBtn = By.xpath("//button[@type=\"submit\"]");
    private static final By getSavedAddress = By.xpath("//div[@data-testing-id=\"further-addresses\"] //ish-address");
    private static final By getShippingBillingAddress = By.xpath("//div[@data-testing-id=\"preferred-invoice-and-shipping-address\"]/ish-address");
    private static final By savedAddressTrashButton = By.xpath("//div[@data-testing-id=\"further-addresses\"] //ish-address/preceding-sibling::div");
    private static final By deleteButtonToConfirmAddressDeletion = By.xpath("//button[@data-testing-id=\"confirm\"]");
    private static final By checkboxSameAsBilling = By.cssSelector("input[type='checkbox'][formcontrolname='sameAsBilling']");
    private static final By myAccountIcon = By.cssSelector("sn-login-status a[data-testing-id='link-myaccount']");
    private static final By noAddressFound = By.xpath("//div[@class='infoBar infoActive infoWarning']");
    private static final By autoPopulateAddress = By.xpath("//ul[@class='c2a_results']");
    private static final By hamburger = By.xpath("//button[@class=\"hamburger-menu\"]");
    private static final By myAccountIconMobile = By.xpath("//button[@class=\"tab-btn-login\"]");


    public boolean checkAddressPageFound() {
        IsPageLoaded.waitAllRequest();
        boolean flag = true;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("fr")) {
            if (isElementPresentWithWait(addressPageFr) && (getTextFor(addressPageFr).equals(Props.getProp("myAccount.address.title")) || getTextFor(addressPageFr).equals(Props.getProp("myAccount.address.title1"))))
                log.info("Address Page is loaded");
            else {
                log.error("Address Page is not loaded");
                flag = false;
            }
        } else {
            if (isElementPresentWithWait(addressPage) && (getTextFor(addressPage).equals(Props.getProp("myAccount.address.title")) || getTextFor(addressPage).equals(Props.getProp("myAccount.address.title1"))))
                log.info("Address Page is loaded");
            else {
                log.error("Address Page is not loaded");
                flag = false;
            }
        }

        return flag;
    }

    public String getTextAddresses() {
        IsPageLoaded.waitAllRequest();
        String text = "";

        try {
            text = webDriver.findElement(getTextAddresses).getAttribute("outerText").trim();
            log.info("Successfully found the text over address page which is-->" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text on my address page-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean clickAddAddressButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            clickByElement(addAddressButton);
            flag = true;
            log.error("Successfully clicked on add addresses button on my address page");
        } catch (Exception e) {
            log.error("Unable to clicked on Add Address button on My Address Page-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public WebElement addAnAddressForm() {
        return visibilityOf(webDriver.findElement(addAddressFormContainer));
    }

    public boolean selectCountry(String country) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            selectValueFromDropDownByby(country, selectCountryFromDropDown);
            log.info("Succesfully set the country as-->" + country + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the country as-->" + country + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectProvince(String province) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            selectValueFromDropDownByby(province, selectProvinceFromDropDown);
            log.info("Succesfully set the country as-->" + province + " while adding an address");
            flag = true;
        } catch (NotFoundException e1) {
            log.error("Unable to find the Province Field that might be country is not selected as CANADA-->" + e1.getMessage());
            throw new RuntimeException("Field Not Found Please check Country Type Selected While Adding An ADDRESS");
        } catch (Exception e) {
            log.error("Unable to set the country as-->" + province + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectBillingProvince(String province) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            selectValueFromDropDownByby(province, selectBillingProvinceFromDropDown);
            log.info("Succesfully set the billing country as-->" + province + " while adding an address");
            flag = true;
        } catch (NotFoundException e1) {
            log.error("Unable to find the billing Province Field that might be country is not selected as CANADA-->" + e1.getMessage());
            throw new RuntimeException("Field Not Found Please check Country Type Selected While Adding An ADDRESS");
        } catch (Exception e) {
            log.error("Unable to set the billing country as-->" + province + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }


    public boolean setFirstName(String name) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setFirstName, name);
            log.info("Successfully set the first name as-->" + name + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the first name as-->" + name + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingFirstName(String name) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setBillingFirstName, name);
            log.info("Successfully set the billing first name as-->" + name + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the billing first name as-->" + name + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setLastName(String lastName) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setLastName, lastName);
            log.info("Succesfully set the last name as-->" + lastName + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the last name as-->" + lastName + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingLastName(String lastName) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setBillingLastName, lastName);
            log.info("Succesfully set the billing last name as-->" + lastName + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the billing last name as-->" + lastName + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }


    public boolean setStreetAddress(String streetAddress) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setStreetAddress, streetAddress);
            log.info("Succesfully set the streetAddress name as-->" + streetAddress + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the streetAddress name as-->" + streetAddress + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingStreetAddress(String streetAddress) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setBillingStreetAddress, streetAddress);
            log.info("Succesfully set the streetAddress name as-->" + streetAddress + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the streetAddress name as-->" + streetAddress + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setPostalCode(String postalCode) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
//            clickByElement(setPostalCode);
            waitClearAndEnterText(setPostalCode, postalCode.trim());
//            webDriver.findElement(setPostalCode).sendKeys(postalCode.trim());
            log.info("Succesfully set the postal code  as-->" + postalCode + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the postal code  as-->" + postalCode + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingPostalCode(String postalCode) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
//            clickByElement(setPostalCode);
            waitClearAndEnterText(setBillingPostalCode, postalCode.trim());
//            webDriver.findElement(setPostalCode).sendKeys(postalCode.trim());
            log.info("Succesfully set the billing postal code  as-->" + postalCode + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the billing postal code  as-->" + postalCode + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setCity(String city) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setCity, city);
            log.info("Succesfully set the city as-->" + city + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the city as-->" + city + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingCity(String city) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setBillingCity, city);
            log.info("Succesfully set the billing city as-->" + city + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the billing city as-->" + city + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setPhoneNumber(String number) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setPhoneNumber, number);
            log.info("Succesfully set the phone number as-->" + number + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the phone number as-->" + number + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean setBillingPhoneNumber(String number) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(setBillingPhoneNumber, number);
            log.info("Succesfully set the billing phone number as-->" + number + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to set the billing phone number as-->" + number + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnSaveAddressButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            clickByElement(saveAddressBtn);
            log.info("Succesfully clicked on save address button ");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(saveAddressBtn));
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on save address button due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickSaveBtnWithoutFillingForm() {
        IsPageLoaded.waitAllRequest();
        int count = 0;
        boolean flag = false;
        do {
            try {
                clickByElement(saveAddressBtn);
                log.info("Succesfully clicked on save address button ");
                flag = true;
                break;
            } catch (ElementClickInterceptedException e1) {
                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,150);");
                count++;
            } catch (Exception e) {
                log.error("Unable to click on save address button due to-->" + e.getMessage());
                e.printStackTrace();
            }
        } while (count < 10);
        return flag;
    }

    public Object getAllSavedAddress() {
        IsPageLoaded.waitAllRequest();
        List<String> address = new ArrayList<>();
        try {
            address = visibilityOfAllElementsLocatedBy(getSavedAddress).stream().map(s -> s.getText()).collect(Collectors.toList());
            log.info("Successfully fetched text of all address");
        } catch (Exception e) {
            log.error("Unable to fetch text of all address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        if (address.size() != 0) {
            return address;
        } else {
            return 0;
        }
    }

    public Object getPreferredShippingAndBillingAddress() {
        IsPageLoaded.waitAllRequest();
        List<String> address = new ArrayList<>();
        try {
//            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(getShippingBillingAddress));
            address = visibilityOfAllElementsLocatedBy(getShippingBillingAddress).stream().map(s -> s.getText()).collect(Collectors.toList());
            log.info("Successfully fetched text of all address");
        } catch (Exception e) {
            log.error("Unable to fetch text of all address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        if (address.size() != 0) {
            return address;
        } else {
            return 0;
        }
    }

    public boolean deleteSavedAddressHavingPostalCode(String postalCode) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        int n = 0;
        Object object = getAllSavedAddress();
        if (object instanceof Integer) {
            log.info("No existing Address found on My Account Address thus returing true");
            flag = true;
        } else {
            List<String> address = (List<String>) object;
            for (int i = 0; i < address.size(); i++) {
                String expected = address.get(i).toLowerCase();
                String actual = postalCode.toLowerCase();
                if (expected.contains(actual)) {
                    do {
                        try {

                            WebElement element = webDriver.findElements(savedAddressTrashButton).get(i);
                            new Actions(getWebDriver()).moveToElement(element).click().build().perform();
                            log.info("Successfully clicked on the trash icon for saved address at position-->" + (++i));
                            flag = true;
                            break;
                        } catch (StaleElementReferenceException e) {
                            n++;
                            log.info("Going to try one more time because of-->" + e.getMessage());
                        } catch (Exception e1) {
                            log.error("Some exception while clicking on the trash icon to delete the address-->" + e1.getMessage());
                            e1.printStackTrace();
                        }
                    } while (n < 2);
                    if (flag) {
                        break;
                    }
                }
            }
        }
        return flag;
    }

    public boolean clickOnDeleteToConfirmAddressDeletion() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            WebElement element = webDriver.findElement(deleteButtonToConfirmAddressDeletion);
            new Actions(getWebDriver()).moveToElement(element).click().build().perform();
            flag = true;
            log.info("Clicked on Delete button on pop up confirmation to delete an address");
        } catch (Exception e) {
            log.error("Unable to click on Delete Button on pop up confirmation to delete an address-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String findWebElementAndFetchText(String name) {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {
            String xpath = "//label[contains(text(),'" + name + "')]/following-sibling::div//small";
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText().trim();
            log.info("Successfully fetched the text which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch the text from the xpath created dynamically to get error message " + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean uncheckSameAsShippingCheckbox() {
        IsPageLoaded.waitAllRequest();
        int count = 0;
        boolean flag = false;
        do {
            try {
                if (waitForExpectedElement(checkboxSameAsBilling).isSelected()) {
                    waitForExpectedElement(checkboxSameAsBilling).click();
                }
                log.info("Successfully unchecked same as shipping checkbox");
                flag = true;
                break;
            } catch (ElementClickInterceptedException e1) {
                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,100);");
                count++;
            } catch (Exception e) {
                log.error("Unable to uncheck same as shipping checkbox" + e.getMessage());
                e.printStackTrace();
            }
        } while (count < 10);
        return flag;
    }

    public boolean clickOnMyAccountIcon() {
        boolean flag = false;
        try {
            if(UrlBuilder.isMobile())
            {
                waitForExpectedElement(hamburger).click();
                waitForExpectedElement(myAccountIconMobile).click();
                log.info("Succesfully clicked on My account icon");
                flag = true;
            }
            else
            {
                waitForExpectedElement(myAccountIcon).click();
                log.info("Succesfully clicked on My account icon");
                flag = true;
            }

        } catch (Exception e) {
            log.error("Unable to click on My account icon due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
    public boolean verifyAddressPopulated() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        flag = isElementPresentWithWait(autoPopulateAddress);
        try {
            flag = waitForExpectedElement(autoPopulateAddress).isDisplayed();
            log.info("Successfully found auto populate address-->");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to find auto populate address-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyNoAddressPopulated() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        flag = isElementPresentWithWait(noAddressFound);
        try {
            flag = getTextFor(noAddressFound).equalsIgnoreCase(Props.getProp("checkout.noAddress.text"));
            log.info("Successfully fetched no address found text-->" + Props.getProp("checkout.noAddress.text") + " while adding an address");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to fetch no address text-->" + Props.getProp("checkout.noAddress.text") + " while adding an address due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
}
