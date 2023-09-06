/*
 * Sanket
 * */

package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class RegisterMyGuaranteePageSNCA extends PageObject {

    private String propFilePath = System.getProperty("user.dir")+Props.getProp("file.path");
    private static final By registerAProductText = By.xpath("//div[contains(@class,'product-registration')]/h1");
    //private static final By blenderGrillText = By.xpath("//div[contains(@class,'violator-banner-block')]");

    private static final By hv300Description = By.xpath("//div[@class=\"row product-details\"]/div[2]/h1");

    private static final By FaqsText = By.xpath("//div[@class=\"banner-container\"]/h1");

    private static final By WarrantyAndReturns = By.xpath("//div[@class=\"banner-container\"]/h1");

    private static final By productInfoAndFaqs = By.xpath("//div[contains(@class,'banner-container')]/h1");

    private static final By firstName = By.xpath("//input[@data-testing-id=\"firstName\"]");
    private static final By lastName = By.xpath("//input[@data-testing-id=\"lastName\"]");
    private static final By modelNumber = By.xpath("//input[@data-testing-id=\"sku\"]");
    private static final By serialNumber = By.xpath("//input[@id=\"smartSerialNumber\"]");
    private static final By storeOfPurchase = By.cssSelector("select[data-testing-id=\"purchaseSourceName\"]");
    private static final By purchaseDate = By.cssSelector("input#warrantyEffectiveDate");
    private static final By email = By.cssSelector("input[data-testing-id=\"email\"]");
    private static final By country = By.cssSelector("#regCountry");
    private static final By checkBoxTermsAndConditionOld = By.cssSelector("#acceptTerms");
    private static final By checkBoxTermsAndConditionUS = By.cssSelector("#newsletter");
    private static final By checkBoxTermsAndCondition = By.xpath("//input[@id='tips']");
    private static final By registerProductBtn = By.xpath("//div[contains(@class,'d-flex')]/following-sibling::button");
    private static final By productRegistrationSuccessText=By.cssSelector("div.product-registration-confirmation h1");

    private static final By warrantyInfoTextOnPDPAndCart=By.xpath("//*[@id=\"main-content\"]//sn-product-warranty/div/div");

    private static final By warrantyInfoTextOnCartPage=By.xpath("//*[@id=\"main-content\"]//sn-product-warranty/div/div");

    private static final By getModelNumberAfterSuccessGuaranteeRegister=By.xpath("//div[text()=\"Model Number:\"]/following-sibling::div");
    private static final By getModelNumber = By.xpath("//div[@class='gaurantee-info']/div[1]/div[2]");
    private static final By getPurchaseDate = By.xpath("//div[@class='gaurantee-info']/div[2]/div[2]");
    private static final By getStoreOfPurchase = By.xpath("//div[@class='gaurantee-info']/div[3]/div[2]");
    private static final By getWarrantyExpiration = By.xpath("//div[@class='gaurantee-info']/div[4]/div[2]");
    private static final By errorEmptyFirstName = By.xpath("//small[contains(text(),' "+Props.getProp("guaranty.empty.firstname")+"')]");
    private static final By errorEmptyLastName = By.xpath("//small[contains(text(),' "+Props.getProp("guaranty.empty.lastname")+"')]");
    private static final By errorEmptyModelNumber = By.xpath("//small[contains(text(),' "+Props.getProp("guaranty.empty.modelnumber")+"')]");
    private static final By errorEmptyPurchaseStore = By.xpath("//small[contains(text(),\" " +Props.getProp("guaranty.empty.store")+"\""+")]");
    private static final By errorEmptyPurchaseDate = By.xpath("//small[contains(text(),\" " +Props.getProp("guaranty.empty.purchasedate")+"\""+")]");
    private static final By errorEmptyEmailText = By.xpath("//small[contains(text(),' "+Props.getProp("guaranty.empty.email")+"')]");
    private static final By errorInvalidFirstName = By.xpath("//small[contains(text(),' "+Props.getProp("error.guaranty.invalid.firstname")+"')]");
    private static final By errorInvalidLastName = By.xpath("//small[contains(text(),' "+Props.getProp("error.guaranty.invalid.lastname")+"')]");
    private static final By errorInvalidEmailText = By.xpath("//small[contains(text(),' "+Props.getProp("error.guaranty.invalid.email")+"')]");
    private String frstName="Sanket";
    private String lstName="Jha";
    private String model="AZ1002C";
    private String serial="";
    private String purchaseStore="Amazon";
    private String dateOfPurchase="04-05-2021";
    private String emailForOrder="sanket.jha@wundermanthompson.com";
    private String orderCountry="Canada";

    private static final Logger log = LoggerFactory.getLogger(RegisterMyGuaranteePageSNCA.class);


    public boolean enterFirstName(String data) {
        return setTextIntoField(firstName,data);
    }

    public boolean enterLastName(String data) {
        return setTextIntoField(lastName,data);
    }

    public boolean enterModelNumber(String data) {
        return setTextIntoField(modelNumber,data);
    }

    public boolean enterSerialNumber(String data) {
        return setTextIntoField(serialNumber,data);
    }

    public boolean selectStoreOfPurchaseAs(String storeName) {
        boolean flag = false;
        try {
            selectValueFromDropDownByby(storeName, storeOfPurchase);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to select the store from dropdown for register my guarantee on shark CA please check-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterPurchaseDate(String date) {
        boolean flag = false;
        if(date.equalsIgnoreCase("yesterday")) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            //(-1 tends to just previous month wrt current month)
            Date pastDate = calendar.getTime();
            System.out.println("Date format is "+ pastDate);
            SimpleDateFormat crunchifyFormat;
            crunchifyFormat = new SimpleDateFormat("dd-MM-yyyy");
            String yesterday = crunchifyFormat.format(pastDate);
            System.out.println("pastDate is " + yesterday );
            try {
                IsPageLoaded.waitAllRequest();
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(purchaseDate));
                log.info("*********************************************________________*****************--->>MaX PROPERTY IS"+element.getAttribute("max"));
                System.out.println("*********************************************________________*****************--->>MaX PROPERTY IS"+element.getAttribute("max"));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(yesterday);
                waitClearAndEnterText(purchaseDate, yesterday);
                flag = true;
            } catch (Exception e) {
                log.error("Unable to enter purchase date for register my guarantee on shark CA please check-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                IsPageLoaded.waitAllRequest();
                WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(purchaseDate));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(date);
                flag=true;
            } catch (Exception e) {
                log.error("Unable to enter purchase date for register my guarantee on shark CA please check-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public String compareWithPurchaseDateEN() {
        boolean flag = false;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            //(-1 tends to just previous month wrt current month)
            Date pastDate = calendar.getTime();
            System.out.println("Date format is " + pastDate);
            SimpleDateFormat crunchifyFormat;
            crunchifyFormat = new SimpleDateFormat("dd MMM, yyyy");
            String yesterday = crunchifyFormat.format(pastDate);
            System.out.println("pastDate is " + yesterday);
            flag=true;
            return yesterday;
    }

    public String compareWithPurchaseDateFR() {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        //(-1 tends to just previous month wrt current month)
        Date pastDate = calendar.getTime();
        System.out.println("Date format is " + pastDate);
        SimpleDateFormat crunchifyFormat;
        crunchifyFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.FRENCH);
        String yesterday = crunchifyFormat.format(pastDate);
        System.out.println("pastDate is " + yesterday);
        flag=true;
        return yesterday;
    }
            public boolean enterEmail(String email) {
        return setTextIntoField(this.email,email);
    }

    public boolean selectCountryAs(String country) {
        boolean flag = false;
        try {
            selectValueFromDropDownByby(country, this.country);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to select the country from dropdown for register my guarantee on shark CA please check-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean tickAcceptTermsAndConditionCheckbox() {
        boolean flag = false;
        try {
            WebElement element;
            if (webDriver.findElements(checkBoxTermsAndConditionOld).size() != 0) {
                element = webDriver.findElement(checkBoxTermsAndConditionOld);
            }
            element = webDriver.findElement(checkBoxTermsAndCondition);
            if (!element.isSelected()) {
                element.click();
                flag = true;
            } else {
                if (element.isSelected()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to check the accept terms and condition checkbox on register my guarantee page for shark CA please check-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickRegisterProductBtn() {
        boolean flag = false;
        try {
            clickByElementByQueryJSExecutor(registerProductBtn);
            IsPageLoaded.waitAllRequest();
            flag = true;
        }catch (Exception e)
        {
            log.error("Unable to click on Register Product btn on register my guarantee page for shark CA please check-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String getTextRegisterAProduct() {
        String text = "not found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(registerAProductText)).getText().trim();
        } catch (Exception e) {
            log.error("Returning empty text, Unable to find text \"Register a Product\" on Register My guarantee page for shark CA-->" + e.getMessage());
            e.printStackTrace();
        }

        return text;
    }
    public String getTexthv300Description() {
        String text = "not found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(hv300Description)).getText().trim();
            System.out.println("text= " + text);
        } catch (Exception e) {
            log.error("Returning empty text, Unable to find text \"THE #1 BLENDER & ELECTRIC GRILL BRAND IN CANADA**\" on Parts & Accessory page for Ninja CA-->" + e.getMessage());
            e.printStackTrace();
        }
        webDriver.navigate().back();
        return text;
    }

    public String getTextFaqs() {
        String parentWindowHandler = webDriver.getWindowHandle();
        Set<String> allWindowHandles = webDriver.getWindowHandles();

        for (String handle : allWindowHandles) {
            if(!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if(!UrlBuilder.isMobile()){
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        String text = "not found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(FaqsText)).getText().trim();
            log.info("Successfully fetched the text from Faqs Page " + text);
        } catch (Exception e) {
            log.error("Returning empty text, Unable to find text for User Manuals on Parts & Accessory page for Ninja CA-->" + e.getMessage());
            e.printStackTrace();
        }
            webDriver.switchTo().window(parentWindowHandler);
        return text;

    }

    public String getTextWarrantyAndReturns() {
        String parentWindowHandler = webDriver.getWindowHandle();
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        if (allWindowHandles.size() > 2) {
            throw new RuntimeException("More than two windows are getting open when clicked on pay by payPal ");
        }

        for (String handle : allWindowHandles) {
            if(!handle.equalsIgnoreCase(parentWindowHandler)) {
                webDriver.switchTo().window(handle);//Switch to the desired window first and then execute commands using driver
                if(!UrlBuilder.isMobile()){
                    webDriver.manage().window().maximize();
                }
                break;
            }
        }

        String text = "not found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(WarrantyAndReturns)).getText().trim();
            log.info("Successfully fetched the text " + text);
        } catch (Exception e) {
            log.error("Returning empty text, Unable to find text \"THE #1 BLENDER & ELECTRIC GRILL BRAND IN CANADA**\" on Parts & Accessory page for Ninja CA-->" + e.getMessage());
            e.printStackTrace();
        }
        webDriver.switchTo().window(parentWindowHandler);
        return text;
    }

    public String getTextProductInformationAndFAQs() {
        String text = "not found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(productInfoAndFaqs)).getText().trim();
        } catch (Exception e) {
            log.error("Returning empty text, Unable to find text \"THE #1 BLENDER & ELECTRIC GRILL BRAND IN CANADA**\" on Parts & Accessory page for Ninja CA-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean completeRegisterMyGuaranteeForm() {
        updateDriverWaitTimeTo(25);
        boolean flag = false;

        if ((enterFirstName(frstName) &&
                enterLastName(lstName) &&
                enterModelNumber(model) &&
                enterSerialNumber(serial) &&
                selectStoreOfPurchaseAs(purchaseStore) &&
                enterPurchaseDate(dateOfPurchase) &&
                enterEmail(emailForOrder) &&
                selectCountryAs(orderCountry) &&
                tickAcceptTermsAndConditionCheckbox()) == true
        ) {
            flag = true;
        }

        return flag;
    }

    public String getTextGuaranteeRegisterSuccessfully() {
        String text = "Not Found";
        IsPageLoaded.waitAllRequest();
        try{
            WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(productRegistrationSuccessText));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text=wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
            log.info("Successfully fetched text for register my guarantee-->"+text);
        }catch (Exception e){
            log.error("Some exception occurred while fetching registering guarantee text-->>"+ ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String getWarrantyInfoTextSuccessfully() {
        String text = "Not Found";
        IsPageLoaded.waitAllRequest();
        try{
            WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(warrantyInfoTextOnPDPAndCart));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text=wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
            log.info("Successfully fetched text for warranty Information on PDP and Cart Page-->"+text);
        }catch (Exception e){
            log.error("Some exception occurred while fetching warranty Information on PDP and Cart Page-->>"+ ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public String getModelNumberAfterGuaranteeRegister() {
        String text = "Not Found";
        try {
            text = webDriver.findElement(getModelNumber).getText().trim();
            log.info("Successfully fetched the text from guarantee registration page-->>" + text);
        } catch (Exception e) {
            log.error("Unable to get the success text after registering guarantee due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getPurchaseDateGuaranteeRegister() {
        String text = "Not Found";
        try {
            text = webDriver.findElement(getPurchaseDate).getText().trim();
            log.info("Successfully fetched the text from guarantee registration page-->>" + text);
        } catch (Exception e) {
            log.error("Unable to get the success text after registering guarantee due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public String getExpirationDateGuaranteeRegister() {
        String text = "Not Found";
        try {
            text = webDriver.findElement(getWarrantyExpiration).getText().trim();
            System.out.println("year text= " + text);
            log.info("Successfully fetched the text from guarantee registration page-->>" + text);
        } catch (Exception e) {
            log.error("Unable to get the success text after registering guarantee due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean saveGuaranteeInfo()
    {
        String modelNo="Not Found";
        String purchaseDate="Not Found";
        String purchaseStore="Not Found";
        String warrantyExpiration="Not Found";
        boolean flag = false;
        try {
            modelNo=webDriver.findElement(getModelNumber).getText().trim();
            purchaseDate=webDriver.findElement(getPurchaseDate).getText().trim();
            purchaseStore=webDriver.findElement(getStoreOfPurchase).getText().trim();
            warrantyExpiration=webDriver.findElement(getWarrantyExpiration).getText().trim();
            PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
            config.setProperty("register.product.model", modelNo);
            config.setProperty("register.product.purchase.date", purchaseDate);
            config.setProperty("register.product.purchase.store", purchaseStore);
            config.setProperty("register.product.warranty.expiry", warrantyExpiration);
            config.save();
            //config.reload();
            log.info("Successfully updated the config file with value of model number, purchase date, purchase store and warranty expiration");
            log.info("Successfully fetched the info from guarantee registration page");
            flag = true;
        }catch (Exception e)
        {
            log.error("Unable to get the success text after registering guarantee due to-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

//    public boolean validateErrorMessage()
//    {
//        boolean flag=false;
//        try {
//            flag=webDriver.findElement(getModelNumberAfterSuccessGuaranteeRegister).isDisplayed();
//            log.info("Successfully found empty error message for first name");
//        }catch (Exception e)
//        {
//            log.error("Unable to found empty error message for first name-->>"+e.getMessage());
//            e.printStackTrace();
//        }
//        return flag;
//    }

    public Boolean verifyEmptyFirstName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyFirstName).isDisplayed();
            log.info("Successfully found error message for first name");
        } catch (Exception e) {
            log.error("Some exception occurred while finding error message for first name-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyEmptyLastName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyLastName).isDisplayed();
            log.info("Successfully found error message for last name");
        } catch (Exception e) {
            log.error("Some exception occurred while finding empty error message for last name-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyEmptyModelNumber() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyModelNumber).isDisplayed();
            log.info("Successfully found error message for model number");
        } catch (Exception e) {
            log.error("Some exception occurred while finding error message for model number-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyEmptyPurchaseStore() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyPurchaseStore).isDisplayed();
            log.info("Successfully found error message for purchase location");
        } catch (Exception e) {
            log.error("Some exception occurred while finding error message for purchase location-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyEmptyPurchaseDate() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyPurchaseDate).isDisplayed();
            log.info("Successfully found error message for purchase date");
        } catch (Exception e) {
            log.error("Some exception occurred while finding error message for purchase date-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyEmptyErrorMessageEmail() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorEmptyEmailText).isDisplayed();
            log.info("Successfully found empty email message");
        } catch (Exception e) {
            log.error("Some exception occurred while finding empty error message-->>"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyInvalidErrorMessageFirstName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidFirstName).isDisplayed();
            log.info("Successfully found invalid first name message");
        } catch (Exception e) {
            log.error("Some exception occurred while finding invalid error message for first name-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyInvalidErrorMessageLastName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidLastName).isDisplayed();
            log.info("Successfully found invalid first name message");
        } catch (Exception e) {
            log.error("Some exception occurred while finding invalid error message for first name-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyInvalidFirstNameErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidFirstName);
            log.info("Error message for invalid first name is not shown");
        } catch (Exception e) {
            log.error("Error message for invalid first name is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyInvalidLastNameErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidLastName);
            log.info("Error message for invalid last name is not shown");
        } catch (Exception e) {
            log.error("Error message for invalid last name is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyInvalidEmailErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidEmailText);
            log.info("Error message for invalid email is not shown");
        } catch (Exception e) {
            log.error("Error message for invalid email is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyInvalidErrorMessageEmail() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidEmailText).isDisplayed();
            log.info("Successfully found invalid email message");
        } catch (Exception e) {
            log.error("Some exception occurred while finding invalid error message for email-->>"+e.getMessage());
        }
        return flag;
    }
}
