package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.RegisterMyGuaranteePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.By;
import org.testng.Assert;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class RegisterMyGuaranteePageSteps extends PageObject {
    private static final By registeredGuarantee = By.xpath("//div[@class=\"product-registration-left\"]/p[1]");
    private static final By registeredGuaranteeIphone = By.xpath("//div[@class=\"product-registration-left\"]/p");
    public static String Registered_Guarantee_number;

    private RegisterMyGuaranteePage registerGuarantee;

    public RegisterMyGuaranteePageSteps() {
        this.registerGuarantee = new RegisterMyGuaranteePage();
    }

    private static final Logger log = LoggerFactory.getLogger(RegisterMyGuaranteePageSteps.class);

    @Then("^I should be on register my guarantee page$")
    public void verifyRegisterMyGuaranteePage() throws InterruptedException {
        log.info("getting page heading" + registerGuarantee.getRegisterProductHeading());
        Assert.assertTrue(registerGuarantee.getRegisterProductHeading().contentEquals(Props.getProp("register.guarantee.page.heading")));
        Assert.assertTrue(registerGuarantee.isElementPresent(registerGuarantee.getRegistrationForm()));
        Thread.sleep(3000);
    }

    @When("^I fill register my guarantee form$")
    public void fillRegisterMyGuarantee() throws InterruptedException {
        registerGuarantee.fillRegisterMyGuaranteeForm();
    }

    @When("^I fill register my guarantee form for Austria$")
    public void fillRegisterMyGuaranteeAustria() throws InterruptedException {
        registerGuarantee.fillRegisterMyGuaranteeFormAustria();
        Thread.sleep(15000);
    }

    @When("^I Click Submit button without filling any field$")
    @And("^Click Submit button$")
    public void clickSubmitButton() throws InterruptedException {
        registerGuarantee.clickSubmitBtn();
    }

    @Then("^Guarantee should be registered$")
    public void verifyGuaranteeRegistered() throws InterruptedException {

        IsPageLoaded.waitAllRequest();
        if (UrlBuilder.isIphone())
            Registered_Guarantee_number = webDriver.findElement(registeredGuaranteeIphone).getText();
        else
            Registered_Guarantee_number = webDriver.findElement(registeredGuarantee).getText();
        log.info("printing register" + registerGuarantee.GetWarrantyRegisteredText());
        Assert.assertTrue(registerGuarantee.GetWarrantyRegisteredText().contains(Props.getProp("guarantee.confirm.message1")));
        log.info(registerGuarantee.GetWarrantyRegisteredText());
        Assert.assertTrue(registerGuarantee.GetWarrantyInfoMailText().contains(Props.getProp("guarantee.confirm.message2")));
        log.info(registerGuarantee.GetWarrantyInfoMailText());
        log.info(registerGuarantee.GetWarrantyInfoMailText());

    }

    @And("^Email, address and name shown prefilled as user already logged in$")
    public void verifyPrefilledDetails() {
        Assert.assertTrue(registerGuarantee.verifyPrefilledDetails());
    }

    @When("^I fill register my guarantee form after login$")
    public void fillRegisterMyGuaranteeAfterLogin() throws InterruptedException {
        registerGuarantee.fillRegisterMyGuaranteeFormAfterLogin();
    }

    @Then("^I should see error messages as all fields are empty$")
    public void verifyEmptyErrorMessages() throws InterruptedException {
        Assert.assertEquals(registerGuarantee.verifyEmptyErrorMessageEmail(), Props.getMessage("guarantee.error.empty.email"), "Email is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyPostalCode(), Props.getMessage("guarantee.error.empty.postcode"), "PostalCode is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyFirstName(), Props.getMessage("guarantee.error.empty.firstname"), "FirstName is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyLastName(), Props.getMessage("guarantee.error.empty.lastname"), "Lastname is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyProductType(), Props.getMessage("guarantee.error.empty.product.type"), "ProductType is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyModelNumber(), Props.getMessage("guarantee.error.empty.model"), "Model Number is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyPurchaseDate(), Props.getMessage("guarantee.error.empty.date"), "Purchase date is incorrect");
        Assert.assertEquals(registerGuarantee.verifyEmptyPurchaseLocation(), Props.getMessage("guarantee.error.empty.buy.from"), "Purchase location is incorrect");
        Thread.sleep(3000);
    }

    @When("^I fill invalid email address$")
    public void fillInvalidEmail() throws InterruptedException {
        registerGuarantee.enterEmail(Props.getProp("register.guarantee.invalid.email"));
        Thread.sleep(1000);
    }

    @When("^I fill invalid first name$")
    public void fillInvalidFirstName() throws InterruptedException {
        registerGuarantee.enterFirstName(Props.getProp("invalid.firstname"));
        Thread.sleep(1000);
    }

    @When("^I fill invalid last name$")
    public void fillInvalidLast() throws InterruptedException {
        registerGuarantee.enterLastName(Props.getProp("invalid.lastname"));
        Thread.sleep(1000);
    }

    @Then("^I should see error message for invalid email$")
    public void verifyInvalidErrorMessageEmail() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidErrorMessageEmail());
        Thread.sleep(1000);
    }

    @Then("^I should see error message for First name$")
    public void verifyInvalidErrorMessageFirstName() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidErrorMessageFirstName());
        Thread.sleep(1000);
    }

    @Then("^I should see error message for last name$")
    public void verifyInvalidErrorMessageLastName() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidErrorMessageLastName());
        Thread.sleep(1000);
    }

    @When("^I fill valid email address$")
    public void fillValidEmail() throws InterruptedException {
        registerGuarantee.enterEmail(Props.getProp("login.email"));
        Thread.sleep(1000);
    }

    @Then("^Error message for invalid email should be gone$")
    public void verifyInvalidEmailErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidEmailErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I fill valid postcode$")
    public void fillValidPostcode() throws InterruptedException {
        registerGuarantee.enterPostcode(Props.getProp("register.guarantee.valid.postcode"));
        Thread.sleep(1000);
    }

    @Then("^Error message for postcode should be gone$")
    public void verifyInvalidPostcodeErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyEmptyPostcodeErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I fill valid first name$")
    public void fillValidFirstName() throws InterruptedException {
        registerGuarantee.enterFirstName(random(6, ALPHABETS));
        Thread.sleep(1000);
    }

    @When("^I fill valid last name$")
    public void fillValidLastName() throws InterruptedException {
        registerGuarantee.enterLastName(random(6, ALPHABETS));
        Thread.sleep(1000);
    }

    @Then("^Error message for invalid first name should be gone$")
    public void verifyInvalidFirstNameErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidFirstNameErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @Then("^Error message for invalid last name should be gone$")
    public void verifyInvalidLastNameErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyInvalidLastNameErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I Select Product Type$")
    public void selectProductType() throws InterruptedException {
        registerGuarantee.selectProductType();
        Thread.sleep(1000);
    }

    @Then("^Error message for empty product type should be gone$")
    public void verifyEmptyProductTypeErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyEmptyProductTypeErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I fill valid product model$")
    public void fillValidProductModel() throws InterruptedException {
        registerGuarantee.enterModelNumber(Props.getProp("product.specificSKU"));
        Thread.sleep(1000);
    }

    @Then("^Error message for empty Product model should be gone$")
    public void verifyEmptyProductModelErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyEmptyProductModelErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I select purchase date$")
    public void fillPurchaseDate() throws InterruptedException {
        registerGuarantee.selectPurchaseDate();
        Thread.sleep(1000);
    }

    @Then("^Error message for empty purchase date should be gone$")
    public void verifyEmptyPurchaseDateErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyEmptyPurchaseDateErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I select Purchase location$")
    public void selectPurchaseFrom() throws InterruptedException {
        registerGuarantee.selectPurchaseLocation();
        Thread.sleep(1000);
    }

    @Then("^Error message empty purchase location should be gone$")
    public void verifyEmptyPurchaseFromErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyEmptyPurchaseFromErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I click on create account checkbox$")
    public void clickCreateAccountCheckbox() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.clickCreateAccountCheckbox());
        Thread.sleep(1000);
    }

    @And("^I fill already registered email while creating account$")
    public void fillRegisteredEmail() throws InterruptedException {
        registerGuarantee.enterEmail(Props.getProp("login.email"));
        Thread.sleep(1000);
    }

    @And("^I fill email which is not registered already$")
    public void fillNotRegisteredEmail() throws InterruptedException {
        String newMail = random(6, ALPHABETS) + System.currentTimeMillis() + "@wundermanthompson.com";
        registerGuarantee.enterEmail(newMail);
        Thread.sleep(1000);
    }

    @And("^fill rest of the form with valid inputs$")
    public void fillGuaranteeFormWithPassword() throws InterruptedException {
        registerGuarantee.fillGuaranteeFormWithPassword();
//        Thread.sleep(1000);
    }

    @And("^Message should be shown that account already exist$")
    public void verifyAccountAlreadyExistMessageShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyAccountExistMessage());
        Thread.sleep(1000);
    }

    @And("^Message should not be shown that account already exist$")
    public void verifyAccountAlreadyExistMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerGuarantee.verifyAccountExistMessageNotShown());
        Thread.sleep(1000);
    }
}
