package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import cucumber.api.java.en_old.Ac;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.time.Duration;

public class MyAccountProfileEditPage extends PageObject {

    private final By heading = By.xpath("//div[@class='col-md-9']/h1");
    private final By salutation = By.xpath("//select[@id='UpdateProfileForm_Title']");
    private final By firstName = By.xpath("//input[@id='UpdateProfileForm_FirstName']");
    private final By lastName = By.xpath("//input[@id='UpdateProfileForm_LastName']");
    private final By phone = By.xpath("//input[@id='UpdateProfileForm_Phone']");
    private final By preferredLanguage = By.xpath("//select[@id='UpdateProfileForm_LocaleID']");
    private final By errorInvalidFirstName = By.xpath("//small[@data-bv-for='UpdateProfileForm_FirstName'][@data-bv-validator='forbiddensymbols']");
    private final By errorInvalidLastName = By.xpath("//small[@data-bv-for='UpdateProfileForm_LastName'][@data-bv-validator='forbiddensymbols']");
    private final By errorInvalidPhone = By.xpath("//small[@data-bv-for='UpdateProfileForm_Phone'][@data-bv-validator='regexp']");
    private final By profileUpdateSuccessMessage = By.xpath("//div[@class='alert alert-success alert-fade']/p");
    private final By iframe=By.xpath("//iframe[@id='brontoNewsletter']");
    private final By preferenceEmailTxtBox  =By.xpath("//input[@class='text field fb-email']");
    private final By preferenceFirstName  =By.xpath("(//input[@class='text field'][@type='text'][@size='35'])[1]");
    private final By preferenceLastName  =By.xpath("(//input[@class='text field'][@type='text'][@size='35'])[2]");
    private final By preferenceOffers  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[1]");
    private final By preferenceTipsAndArticles  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[2]");
    private final By preferenceRecipeAndInspiration  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[3]");
    private final By preferenceCompetitionsShark  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[4]");
    private final By preferenceCompetitionsNinja  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[5]");
    private final By preferenceBlackFridayShark  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[6]");
    private final By preferenceBlackFridayNinja  =By.xpath("(//input[@type='checkbox'][@class='checkbox'][starts-with(@id,'field_')])[7]");
    private final By savePreferencesBtn  =By.xpath("//input[@type='submit']");
//    private final By preferenceUpdateSuccessMsg  =By.xpath("/html/body/form/div/div[1]/span");
    private final By preferenceUpdateSuccessMsg  =By.xpath("//div[@class='container'][starts-with(@id,'column_')]/span");

    private static final Logger log = LoggerFactory.getLogger(MyAccountPwdEditPage.class);

    public boolean enterEmailInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            waitForExpectedElement(preferenceEmailTxtBox).clear();
            waitForExpectedElement(preferenceEmailTxtBox).sendKeys(Props.getProp("login.update.email"));
            log.info("Successfully entered email for preference");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while filling email for preference-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterFirstNameInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            waitForExpectedElement(preferenceFirstName).clear();
            waitForExpectedElement(preferenceFirstName).sendKeys(Props.getProp("account.profile.firstname.revertchange"));
            log.info("Successfully entered first name for preference");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while filling first name for preference-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean enterLastNameInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            waitForExpectedElement(preferenceLastName).clear();
            waitForExpectedElement(preferenceLastName).sendKeys(Props.getProp("account.profile.lastname.revertchange"));
            log.info("Successfully entered first name for preference");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while filling first name for preference-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkOffersInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceOffers).isSelected()) {
                waitForExpectedElement(preferenceOffers).click();
            }
            log.info("Successfully checked offers in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking offers in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkTipsAndArticlesInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceTipsAndArticles).isSelected()) {
                waitForExpectedElement(preferenceTipsAndArticles).click();
            }
            log.info("Successfully checked tips and articles in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking tips and articles in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkRecipeAndInspirationInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceRecipeAndInspiration).isSelected()) {
                waitForExpectedElement(preferenceRecipeAndInspiration).click();
            }
            log.info("Successfully checked recipe and inspiration in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking recipe and inspiration in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkCompetitionsSharkInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceCompetitionsShark).isSelected()) {
                waitForExpectedElement(preferenceCompetitionsShark).click();
            }
            log.info("Successfully checked competitions Shark in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking competitions shark in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkCompetitionsNinjaInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceCompetitionsNinja).isSelected()) {
                waitForExpectedElement(preferenceCompetitionsNinja).click();
            }
            log.info("Successfully checked competitions ninja in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking competitions ninja in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkBlackFridaySharkInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceBlackFridayShark).isSelected()) {
                waitForExpectedElement(preferenceBlackFridayShark).click();
            }
            log.info("Successfully checked black friday shark in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking black friday shark in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean checkBlackFridayNinjaInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(!waitForExpectedElement(preferenceBlackFridayNinja).isSelected()) {
                waitForExpectedElement(preferenceBlackFridayNinja).click();
            }
            log.info("Successfully checked black friday ninja in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while checking black friday ninja in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckOffersInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceOffers).isSelected()) {
                waitForExpectedElement(preferenceOffers).click();
            }
            log.info("Successfully unchecked offers in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking offers in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckTipsAndArticlesInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceTipsAndArticles).isSelected()) {
                waitForExpectedElement(preferenceTipsAndArticles).click();
            }
            log.info("Successfully unchecked tips and articles in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking tips and articles in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckRecipeAndInspirationInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceRecipeAndInspiration).isSelected()) {
                waitForExpectedElement(preferenceRecipeAndInspiration).click();
            }
            log.info("Successfully unchecked recipe and inspiration in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking recipe and inspiration in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckCompetitionsSharkInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceCompetitionsShark).isSelected()) {
                waitForExpectedElement(preferenceCompetitionsShark).click();
            }
            log.info("Successfully unchecked competitions Shark in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking competitions shark in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckCompetitionsNinjaInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceCompetitionsNinja).isSelected()) {
                waitForExpectedElement(preferenceCompetitionsNinja).click();
            }
            log.info("Successfully unchecked competitions ninja in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking competitions ninja in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckBlackFridaySharkInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceBlackFridayShark).isSelected()) {
                waitForExpectedElement(preferenceBlackFridayShark).click();
            }
            log.info("Successfully unchecked black friday shark in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking black friday shark in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean uncheckBlackFridayNinjaInPreferenceForm()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            if(waitForExpectedElement(preferenceBlackFridayNinja).isSelected()) {
                waitForExpectedElement(preferenceBlackFridayNinja).click();
            }
            log.info("Successfully unchecked black friday ninja in preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while unchecking black friday ninja in preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public boolean clickSavePreferences()
    {
        boolean flag=false;
        try
        {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            waitForExpectedElement(savePreferencesBtn).click();
            log.info("Successfully saved preference form");
            flag=true;
        }catch (Exception e)
        {
            log.info("Some exception occurred while saving preference form-->>" + e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public Boolean verifyHeading() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(heading).getText().contains(Props.getMessage("account.profile.edit"));
            log.info("Successfully verified heading of edit password page");
        } catch (Exception e) {
            log.info("Error while verifying heading on edit password page-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean selectSalutationByValue(String salutationValue) {
        boolean flag = false;
        try {
            selectByValueFromDropDownByBy(salutationValue,salutation);
            flag = true;
            log.info("Successfully selected value of salutation");
        } catch (Exception e) {
            log.info("Error while selecting salutation-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean fillFirstName(String fName) {
        boolean flag=false;
        try {
            waitForExpectedElement(firstName).clear();
            waitForExpectedElement(firstName).sendKeys(fName);
            log.info("Successfully entered first name on edit profile page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling first name on edit profile page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean fillLastName(String lName) {
        boolean flag=false;
        try {
            waitForExpectedElement(lastName).clear();
            waitForExpectedElement(lastName).sendKeys(lName);
            log.info("Successfully entered last name on edit profile page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling last name on edit profile page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean fillPhoneNumber(String phoneNo) {
        boolean flag=false;
        try {
            waitForExpectedElement(phone).clear();
            waitForExpectedElement(phone).sendKeys(phoneNo);
            log.info("Successfully entered phone number on edit password page");
            flag = true;
        } catch (Exception e) {
            log.info("Error while filling phone number on edit password page-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorInvalidFirstName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidFirstName).getText().contains(Props.getMessage("error.first.name.forbidden.symbols"));
            log.info("Successfully verified password invalid and length error messages");
        } catch (Exception e) {
            log.info("Error while verifying password invalid and length error messages-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyFirstNameErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidFirstName);
            log.info("Error message for first name is not shown");
        } catch (Exception e) {
            log.info("Error message for first name is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorInvalidLastName() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidLastName).getText().contains(Props.getMessage("error.last.name.forbidden.symbols"));
            log.info("Successfully verified password invalid and length error messages");
        } catch (Exception e) {
            log.info("Error while verifying password invalid and length error messages-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyLastNameErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidLastName);
            log.info("Error message for last name is not shown");
        } catch (Exception e) {
            log.info("Error message for last name is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorInvalidPhone() {
        boolean flag=false;
        try {
            flag = waitForExpectedElement(errorInvalidPhone).getText().contains(Props.getMessage("error.invalid.phone.number"));
            log.info("Successfully verified password invalid and length error messages");
        } catch (Exception e) {
            log.info("Error while verifying password invalid and length error messages-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean verifyPhoneNumberErrorMessageNotShown() {
        boolean flag=false;
        try {
            flag = invisibilityOfElementLocated(errorInvalidPhone);
            log.info("Error message for phone number is not shown");
        } catch (Exception e) {
            log.info("Error message for phone number is shown-->>"+e.getMessage());
        }
        return flag;
    }

    public Boolean selectPreferredLanguage(int index) {
        boolean flag = false;
        try {
            selectByIndexFromDropDownByBy(index,preferredLanguage);
            flag = true;
            log.info("Successfully selected preferred language");
        } catch (Exception e) {
            log.info("Error while selecting preferred language-->>" + e.getMessage());
        }
        return flag;
    }

    public String verifyProfileUpdateSuccessMsg() {
        String text="Unable to fetch text";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(3)).ignoring(Exception.class);
        try {
            WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(profileUpdateSuccessMessage));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            text = wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
            log.info("Successfully updated profile Message is there which is---->>>"+text);
        } catch (Exception e) {
            log.info("Error while updating profile-->>"+ExceptionUtils.getStackTrace(e));
        }
        return text;
    }

    public Boolean verifyPreferenceUpdateSuccessMsg() {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            flag = waitForExpectedElement(preferenceUpdateSuccessMsg).getText().contains(Props.getMessage("preference.update.success.message"));
            if(!(Props.getMessage("preference.update.success.message.eng") == null) && flag == false)
                flag = waitForExpectedElement(preferenceUpdateSuccessMsg).getText().contains(Props.getMessage("preference.update.success.message.eng"));
            log.info("Successfully updated preferences");
        } catch (Exception e) {
            log.info("Error while updating preferences-->>"+e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
            JavascriptExecutor jse = (JavascriptExecutor) webDriver;
            jse.executeScript("arguments[0].scrollIntoView()", waitForExpectedElement(heading));
        }
        return flag;
    }

    public Boolean verifyAllPreferencesChecked() {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            flag = waitForExpectedElement(preferenceOffers).isSelected() && waitForExpectedElement(preferenceTipsAndArticles).isSelected() && waitForExpectedElement(preferenceRecipeAndInspiration).isSelected() && waitForExpectedElement(preferenceCompetitionsShark).isSelected() && waitForExpectedElement(preferenceCompetitionsNinja).isSelected() && waitForExpectedElement(preferenceBlackFridayShark).isSelected() && waitForExpectedElement(preferenceBlackFridayNinja).isSelected();
            log.info("Successfully verified all preferences checked");
        } catch (Exception e) {
            log.info("Error while verifying all preferences checked-->>"+e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }

    public Boolean verifyAllPreferencesUnchecked() {
        boolean flag=false;
        try {
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            flag = waitForExpectedElement(preferenceOffers).isSelected() || waitForExpectedElement(preferenceTipsAndArticles).isSelected() || waitForExpectedElement(preferenceRecipeAndInspiration).isSelected() || waitForExpectedElement(preferenceCompetitionsShark).isSelected() || waitForExpectedElement(preferenceCompetitionsNinja).isSelected() || waitForExpectedElement(preferenceBlackFridayShark).isSelected() || waitForExpectedElement(preferenceBlackFridayNinja).isSelected();
            log.info("Successfully verified all preferences checked");
        } catch (Exception e) {
            log.info("Error while verifying all preferences checked-->>"+e.getMessage());
        }finally {
            webDriver.switchTo().defaultContent();
        }
        return flag;
    }
}