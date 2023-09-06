package com.salmon.test.page_objects.gui.SNAP;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SNAPNewProductRegistrationPage extends PageObject
{


    private static final Logger log = LoggerFactory.getLogger(AgentProfilePage.class);

    private String env= Props.getProp("zendesk.testing.env");
    private final By iframe= By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By email=By.xpath("//label[text()=\"Email\"]/../following-sibling::div/div");
    private static final By firstName=By.xpath("//label[text()=\"First Name\"]/../following-sibling::div/div");
    private static final By lastName=By.xpath("//label[text()=\"Last Name\"]/../following-sibling::div/div");
    private static final By postCode=By.xpath("//label[text()=\"Postcode\"]/../following-sibling::div/div");
    private static final By productModelNumber=By.xpath("//label[text()=\"Product Model Number\"]/../input");
    private static final By serialNumber=By.xpath("//label[text()=\"Serial Number(if available)\" ]/../input");
    private static final By purchaseDate=By.xpath("//label[text()=\"Purchase Date\" ]/../input");
    private static final By purchaseLocation=By.xpath("//label[text()=\"Purchase Location\" ]/../select");
    private static final By subscribeCompetitions_CheckBox=By.id("product-registration__checkbox--competition");
    private static final By subscribeOffers_CheckBox=By.id("product-registration__checkbox--offers");
    private static final By createButton=By.xpath("//button[text()=\" Create \"]");
    private static final By cancelButton=By.xpath("//button[text()=\" Cancel \"]");
    private static final By productModelName=By.xpath("//label[text()=\"Product Model Name\"]/../div");
    private static final By setEmailAddress=By.xpath("//label[text()=\"Email Address \"]/../input[1]");
    private static final By checkBoxCustomerDoesntHaveEmail=By.xpath("//label[text()=\"Email Address \"]/../label[2]");
    private static final By setFirstName=By.xpath("(//label[text()=\"First Name\"]/../input)[1]");
    private static final By setLastName=By.xpath("(//label[text()=\"Last Name\"]/../input)[1]");
    private static final By setCountry=By.xpath("//label[text()=\"Country\"]/following-sibling::select");
    private static final By setPostalCode=By.xpath("//input[@data-testing-id=\"postalCode\"]");
    private static final By setPostalCodeDropdown=By.xpath("//ul[@class=\"c2a_results\"]/li/div");
    private static final By productNumberSelect = By.xpath("(//button[@class=\"search-result\"])[1]");


    public String getEmailId()
    {
        String text="";
        try{
            text=waitForExpectedElement(email).getText().trim();
            log.info("Successfully fetched email id which is -->>"+text);

        }catch (Exception e){
            log.info("Some exception occurred while fetching email id which is-->>"+e.getMessage());
        }

        return text;
    }

    public String getFirstName()
    {
        String text="";
        try{
            text=waitForExpectedElement(firstName).getText().trim();
            log.info("Successfully fetched FirstName id which is -->>"+text);

        }catch (Exception e){
            log.info("Some exception occurred while fetching FirstName id which is-->>"+e.getMessage());
        }

        return text;
    }

    public String getLastName()
    {
        String text="";
        try{
            text=waitForExpectedElement(lastName).getText().trim();
            log.info("Successfully fetched LastName id which is -->>"+text);

        }catch (Exception e){
            log.info("Some exception occurred while fetching LastName id which is-->>"+e.getMessage());
        }

        return text;
    }

    public String getPostCode()
    {
        String text="";
        try{
            text=waitForExpectedElement(postCode).getText().trim();
            log.info("Successfully fetched PostCode id which is -->>"+text);

        }catch (Exception e){
            log.info("Some exception occurred while fetching PostCode id which is-->>"+e.getMessage());
        }

        return text;
    }

    public boolean setProductModelNumber(String modelNumber)
    {
        boolean flag=false;

        try{


            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to set Model number--->>"+modelNumber);
            WebElement element=waitForExpectedElement(productModelNumber);
            element.clear();
            element.sendKeys(modelNumber);
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(productNumberSelect)).click();

            log.info("Successfully set the product model number-->>"+modelNumber);
            flag=true;
        }catch (Exception e)
        {
            log.info("Some Exception while setting the product mode number which is -->>"+e.getMessage());
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean setPurchaseDate(String date)
    {
        boolean flag=false;

        try{
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to set Model purchase date--->>"+date);
            WebElement element=waitForExpectedElement(purchaseDate);
            element.clear();
            element.sendKeys(date);

            log.info("Successfully set the product purchase date-->>"+date);
            flag=true;
        }catch (Exception e)
        {
            log.info("Some Exception while setting the product purchase date which is -->>"+e.getMessage());
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public boolean setPurchaseLocation(String location)
    {
        boolean flag=false;

        try{
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            Select select=new Select(waitForExpectedElement(purchaseLocation));

            select.selectByVisibleText(location);

            log.info("Successfully set the product purchase location as-->>"+location);
            flag=true;
        }catch (Exception e)
        {
            log.info("Some Exception while setting the product purchase location which is -->>"+e.getMessage());
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return flag;
    }

    public String getProducModelName()
    {
        String text="";

        try{
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            WebElement element=waitForExpectedElement(productModelName);

            text=element.getText().trim();

            log.info("Successfully set the product model name which is-->>"+text);

        }catch (Exception e)
        {
            log.info("Some Exception while fetching  product model name which is -->>"+e.getMessage());
        }
        finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public SNAPProductRegistrationPage clickOnCreateButton()
    {

        webDriver.switchTo().frame(waitForExpectedElement(iframe));
        log.info("Going to click on create button on new product registration page");
        waitForExpectedElement(createButton).click();
        log.info("Successfully clicks on create button");

        webDriver.switchTo().defaultContent();

        return new SNAPProductRegistrationPage();

    }

    public boolean setEmailAddress(String email)
    {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitClearAndEnterText(setEmailAddress,email);

            log.info("Successfully set email id-->>"+email);
            flag=true;
        }catch (Exception e){
            log.info("Some exception occurred while setting email address "+email);
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean setFirstName(String firstName)
    {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitClearAndEnterText(setFirstName,firstName);

            log.info("Successfully set first name-->>"+firstName);
            flag=true;
        }catch (Exception e){
            log.info("Some exception occurred while setting first name "+firstName);
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean setFirstName()
    {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            String firstName = RandomGenerator.randomAlphabetic(7);
            waitClearAndEnterText(setFirstName,firstName);

            log.info("Successfully set first name-->>"+firstName);
            flag=true;
        }catch (Exception e){
            log.info("Some exception occurred while setting first name "+firstName);
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean setLastName(String lastName)
    {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitClearAndEnterText(setLastName,lastName);

            log.info("Successfully set lastName-->>"+lastName);
            flag=true;
        }catch (Exception e){
            log.info("Some exception occurred while setting lastName "+lastName);
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean setCountry(String country)
    {
        boolean flag=false;

        try{
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            selectValueFromDropDownByby(country,setCountry);
            flag=true;
    }catch (Exception e){
            log.info("Some exception occurred while selecting country-->>"+country);
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean setPostalCode(String postalCode)
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            waitClearAndEnterText(setPostalCode,postalCode);
            log.info("Postal Code has been set successfully-->"+postalCode);
            List<WebElement> list=webDriver.findElements(setPostalCodeDropdown);
            list.get(0).click();
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while setting postal code-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }


}
