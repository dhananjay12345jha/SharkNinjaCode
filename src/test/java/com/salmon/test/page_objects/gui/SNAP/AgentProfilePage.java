package com.salmon.test.page_objects.gui.SNAP;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AgentProfilePage extends PageObject
{
    private String env= Props.getProp("zendesk.testing.env");
    private  final By iframe=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By firstName=By.xpath("//input[@data-testing-id=\"firstName\"]");
    private static final By lastName=By.xpath("//input[@data-testing-id=\"lastName\"]");
    private static final By email=By.xpath("//input[@data-testing-id=\"email\"]");
    private static final By phoneNumber=By.xpath("//input[@data-testing-id=\"phoneHome\"]");
    private static final By customerId=By.xpath("//h2[contains(text(),'ID:')]/..");
    private static final By customerName=By.xpath("(//span[@class=\"login-name\"])[1]");
    private static final By agentName=By.xpath("//a[@data-testing-id=\"link-logout\"]/../span");
    private static final By resendConfirmationMailButton=By.xpath("//button[text()=\" Resend confirmation mail \"]");
    private static final By resetPasswordButton=By.xpath("//button[text()=\" Reset Password \"]");
    private static final By loadEmailPreferences = By.xpath("//button[text()=\" Load Email Preferences \"]");
    private static final By profileButton=By.xpath("//li[@class=\"nav-item\"]/a[text()='Profile']");
    private static final By addressButton=By.xpath("//a[text()=\"Addresses\"]");
    private static final By orderHistoryButton=By.xpath("//a[text()=\"Order History\"]");
    private static final By productsRegistrationsButton=By.xpath("//a[contains(text(),'Registrations')]");
    private static final By saveProfileButton=By.xpath("//button[text()=\" Save Profile \"]");
    private static final By acknowledgementMessage=By.xpath("//div[@id=\"toast-container\"]/div/div");
    private static final By searchBar=By.xpath("//form[@name=\"SearchBox_Header\"]/div/input");
    private static final By searchButton=By.xpath("//div[@name=\"search\" and @type=\"submit\"]");
    private static final By cartBasketButton=By.xpath("//ish-mini-basket/a");
    private static final By numberOfItemsAddedInCart=By.xpath("//span[@class=\"badge badge-pill\"]");
    private static final By sharkChannel = By.xpath("//label[@for=\"brand-shark-activation-email\"]");
    private static final By resendConfirmationButtonAgain = By.xpath("(//button[text()=\" Resend confirmation mail \"])[2]");
    private static final By sendResetEmailButton = By.xpath("//button[text()=\" Send Reset Email \"]");
    private static final By sharkChannelForResetPassword = By.xpath("//label[@for=\"brand-shark\"]");
    private static final By partsWarrantyText1 = By.xpath("(//b[@class=\"warranty-text\"])[1]");
    private static final By unitsWarrantyText1 = By.xpath("(//b[@class=\"warranty-text\"])[2]");
    private static final By partsWarrantyText2 = By.xpath("(//b[@class=\"warranty-text\"])[3]");
    private static final By unitsWarrantyCartPage = By.xpath("//div[@class=\"col-12 p-0\"]/p/b");

    private FluentWait<WebDriver> fluentWait=new FluentWait<>(webDriver);

    private static final Logger log = LoggerFactory.getLogger(AgentProfilePage.class);

    public AgentProfilePage()
    {
        isPageLoaded();
    }


    private boolean isPageLoaded()
    {
        boolean flag=false;

        fluentWait.withTimeout(Duration.ofSeconds(5));
        fluentWait.pollingEvery(Duration.ofSeconds(1));
        fluentWait.ignoring(ElementNotVisibleException.class, ElementNotFoundException.class);
        try
        {   webDriver.switchTo().frame(waitForExpectedElement(iframe));
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(addressButton));
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(orderHistoryButton));
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(productsRegistrationsButton));
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(orderHistoryButton));
            log.info("Agent Profile page is loaded successfully");
            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while loading the Agent Profile page or it is taking more time to load");
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public String getCustomerId()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to find Customer Id on agent dashboard ");
            text=waitForExpectedElement(customerId).getText().trim();

            log.info("Customer ID has been found successfully which is-->> "+text);

            webDriver.switchTo().defaultContent();

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Customer ID from Agent page-->>"+e.getMessage());
            webDriver.switchTo().defaultContent();
            e.printStackTrace();
        }
        return text;
    }

    public String getAgentName()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to find Customer Id on agent dashboard ");
            text=waitForExpectedElement(customerId).getText().trim();

            log.info("Customer ID has been found successfully which is-->> "+text);

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Customer ID from Agent page-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public String getPartsWarrantyText()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to find Customer Id on agent dashboard ");
            text=waitForExpectedElement(partsWarrantyText1).getText().trim();

            log.info("Parts warranty text successfully found which is-->> "+text);

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Parts warranty text due to-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public String getPartsWarrantyText2()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to Parts warranty text 2");
            text=waitForExpectedElement(partsWarrantyText2).getText().trim();

            log.info("Parts warranty text successfully found which is-->> "+text);

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Parts warranty text due to-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public String getUnitWarranty()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to Warranty of Unit text ");
            text=waitForExpectedElement(unitsWarrantyText1).getText().trim();

            log.info("Warranty of Unit text successfully found which is-->> "+text);

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Parts warranty text due to-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public String getUnitWarrantyOnCartPage()
    {
        String text="";
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to Warranty of Unit text ");
            text=waitForExpectedElement(unitsWarrantyCartPage).getText().trim();

            log.info("Warranty of Unit text successfully found which is-->> "+text);

        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Parts warranty text due to-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return text;
    }

    public SNAPProductRegistrationPage clickProductsRegistrationsLink()
    {
        SNAPProductRegistrationPage object=null;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click link \"Products Registrations\" on Agent Profile Page");
            new Actions(webDriver).moveToElement(waitForExpectedElement(productsRegistrationsButton)).click().build().perform();
            log.info("Successfully clicked on Products Registrations Button");
            object=new SNAPProductRegistrationPage();

        }catch (Exception e)
        {
            log.error("Some Exception occurred while clicking product registration button-->>"+e.getMessage());
            e.printStackTrace();

        }finally {
            webDriver.switchTo().defaultContent();
        }

        return object;
    }

    public String getCustomerFirstName()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Successfully switched into Iframe and going to fetch customer first Name");

            text=getTextFor(firstName).trim();
            log.info("Successfully fetched the first name which is-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching customer first name-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public boolean setCustomerFirstName(String name)
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitForExpectedElement(profileButton).click();
            log.info("Successfully switched into Iframe and going to set customer's First Name");
            waitClearAndEnterText(firstName,name);
            log.info("Successfully set the First Name which is-->>"+name);

        }catch (Exception e)
        {
            log.error("Some exception occurred while Setting customer's First Name-->> "+e.getMessage());
            e.printStackTrace();
        }

        if(getValue(firstName).trim().equalsIgnoreCase(name))
        {
            flag=true;
        }
        else
            {
                log.info("Mismatch occurred while setting First Name, value fetched from First Name field " +
                        "is not same which has been set");
            }
        webDriver.switchTo().defaultContent();
        return flag;
    }

    public boolean setCustomerLastName(String name)
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Successfully switched into Iframe and going to set customer's Last Name");
            waitClearAndEnterText(lastName,name);
            log.info("Successfully set the Last Name which is-->>"+name);

        }catch (Exception e)
        {
            log.error("Some exception occurred while Setting customer's Last Name-->> "+e.getMessage());
            e.printStackTrace();
        }

        if(getValue(lastName).trim().equalsIgnoreCase(name))
        {
            flag=true;
        }
        else
        {
            log.info("Mismatch occurred while setting Last Name, value fetched from First Name field " +
                    "is not same which has been set");
        }
        webDriver.switchTo().defaultContent();
        return flag;
    }


    public String getCustomerLastName()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Successfully switched into Iframe and going to fetch customer last Name");

            text=getTextFor(lastName).trim();
            log.info("Successfully fetched the last name which is-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching customer last name-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public String getCustomerNameInDisplay()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Successfully switched into Iframe and going to fetch customer Displayed Name");

            text=getTextFor(customerName).trim();
            log.info("Successfully fetched the Displayed Customer Name which is-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Displayed Customer Name-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public boolean clickResendConfirmationMail()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on resend confirmation mail button");
            waitForExpectedElement(resendConfirmationMailButton).click();
            log.info("Successfully clicked on resend confirmation mail button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on resend Confirmation Mail Button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }

    public boolean clickResendConfirmationMailAgain()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on resend confirmation mail button");
            waitForExpectedElement(resendConfirmationButtonAgain).click();
            log.info("Successfully clicked on resend confirmation mail button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on resend Confirmation Mail Button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }

    public boolean clickResetEmailButton()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on send reset email button");
            waitForExpectedElement(sendResetEmailButton).click();
            log.info("Successfully clicked on send reset email button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on send reset email button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }


    public boolean clickResetPasswordButton()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on reset password button");
            waitForExpectedElement(resetPasswordButton).click();
            log.info("Successfully clicked on reset password button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on resend Confirmation Mail Button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }

    public boolean clickOnSharkChannel(){
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitForExpectedElement(sharkChannel).click();
            log.info("Successfully clicked on shark channel button option");

            bool = true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on email preference button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return bool;
    }

    public boolean selectSharkChannelForResetPassword(){
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitForExpectedElement(sharkChannelForResetPassword).click();
            log.info("Successfully clicked on shark channel button option");

            bool = true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on email preference button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return bool;
    }

    public boolean clickEmailPrefernceOption()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on email preference button");
            waitForExpectedElement(loadEmailPreferences).click();
            log.info("Successfully clicked on email preference button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on email preference button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }


    public boolean clickSaveProfileButton()
    {
        boolean bool=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            log.info("Going to click on save profile button");
            waitForExpectedElement(saveProfileButton).click();
            log.info("Successfully clicked on save profile button");
            bool=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on save profile button-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return bool;
    }

    public String getAcknowledgementMessageWhenClickOnSave()
    {
        String text="";

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            text=getTextFor(acknowledgementMessage).trim();
            log.info("Successfully fetched the Acknowledgement Message-->>"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching Acknowledgement Message-->> "+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        return text;
    }

    public SearchResultPage findItemThroughSearchBar(String item)
    {
        SearchResultPage obj=null;

        try
        {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            waitClearAndEnterText(searchBar,item);
            log.info("Successfully entered item in the search bar");

            waitForExpectedElement(searchButton).click();
            log.info("Successfully clicked on search button");

            obj=new SearchResultPage();
        }catch (Exception e)
        {
            log.error("Some exception occurred while setting text in search and clicking on search button-->>"+e.getMessage());
            e.printStackTrace();
        }finally
        {
            webDriver.switchTo().defaultContent();
        }

        return obj;
    }

    public ShoppingCartPage clickOnCartBasket()
    {
        boolean flag=false;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));

            elementToBeClickable(cartBasketButton).click();
            log.info("Successfully clicked on add to cart button");
            flag=true;
            Thread.sleep(5000);

        }catch (Exception e)
        {
            log.error("Some exception occurred while clicking on add to cart button-->>"+e.getMessage());
            e.printStackTrace();
        }finally {
            webDriver.switchTo().defaultContent();
        }

        if(flag)
        {
            return new ShoppingCartPage();
        }
        else
            {
                log.info("Unable to click on add to cart button please check ");
                return null;
            }
    }

    public int returnNumberOfItemsShownAboveCartButton()
    {
        int number=0;

        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Going to wait till the number shown over cart button");
            fluentWait.until(driver ->
            {
                if(Integer.parseInt(driver.findElement(numberOfItemsAddedInCart).getText().trim())>0)
                {
                  return true;
                }
                return false;
            });

            number=Integer.parseInt(waitForExpectedElement(numberOfItemsAddedInCart).getText().trim());
            log.info("Successfully found number of items added in cart-->>"+number);

        }catch (Exception e)
        {
            log.error("Unable to read number shown above cart button which tells about number of items in cart-->>"+e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            webDriver.switchTo().defaultContent();
        }
        return number;
    }
}
