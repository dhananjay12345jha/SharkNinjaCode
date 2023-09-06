package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.salmon.test.page_objects.gui.MyAccountAddressPage;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class MyAccountAddressPageSteps {

    private final MyAccountAddressPage myAccountAddressPage;
    protected static final Logger LOG = LoggerFactory.getLogger(MyAccountAddressPageSteps.class);
    public MyAccountAddressPageSteps() {

        this.myAccountAddressPage = new MyAccountAddressPage();
    }

    private final String fName = random(6, ALPHABETS);
    private final String lastNameData = random(6, ALPHABETS);
    private final String address1Data = random(6, ALPHABETS);
    private final String address2Data = random(6, ALPHABETS);
    private final String townOrCityData = random(6, ALPHABETS);
    private final String phoneData = random(10, NUMERIC);
    private final String phoneDataInvalid = random(3, NUMERIC);
    private final String postalCodeInvalid = random(2, NUMERIC);

    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    @Then("^I should be on Addresses page$")
    public void verifyAddressPage() throws InterruptedException {
//		Assert.assertTrue(myAccountAddressListPage.getCurrentUrl().contains(Props.getProp("address.page.url")));
        LOG.info(myAccountAddressPage.getHeading());
        Assert.assertTrue(myAccountAddressPage.getHeading().trim().contains(Props.getProp("address.page.heading").trim()));
        Assert.assertTrue(myAccountAddressPage.checkAddAddressLink());
    }

    @When("^I click add address link$")
    public void clickAddAddress() {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.clickAddAddressLink();
    }

    @Then("^I Should see form to add address$")
    public void verifyAddAddressFields() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getFirstName()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getLastName()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getStreet()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getStreetOptional()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getCity()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getPostalCode()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getPhone()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getPreferredInvoice()));
        Assert.assertTrue(myAccountAddressPage.isElementPresent(myAccountAddressPage.getPreferredBilling()));
    }

    @When("^I fill the form to add address$")
    public void fillFormAddAddress() {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.FirstName().sendKeys(fName);
        myAccountAddressPage.enterLastName(lastNameData);
        myAccountAddressPage.enterStreet(address1Data);
        myAccountAddressPage.enterStreetOptional(address2Data);
        myAccountAddressPage.enterCity(townOrCityData);
//        if (Props.getProp("selectedCountry").contains("Austria")) {
//            myAccountAddressPage.enterPostalCode(Props.getProp("country.postal.code.Austria"));
//        }
            myAccountAddressPage.enterPostalCode(Props.getProp("country.postal.code"));

            myAccountAddressPage.enterPhone(phoneData);
        }

    @When("^I fill the form to add austria address$")
    public void fillFormAddAustriaAddress() {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.FirstName().sendKeys(fName);
        myAccountAddressPage.enterLastName(lastNameData);
        myAccountAddressPage.enterStreet(address1Data);
        myAccountAddressPage.enterStreetOptional(address2Data);
        myAccountAddressPage.enterCity(townOrCityData);
        myAccountAddressPage.enterPostalCode(Props.getProp("country.postal.code.Austria"));
        myAccountAddressPage.enterPhone(phoneData);
    }

    @When("^I make changes in saved address$")
    public void updateAddressForm() {
        myAccountAddressPage.updateFirstName(reverseString(fName));
        myAccountAddressPage.updateLastName(reverseString(lastNameData));
        myAccountAddressPage.updateStreetName(reverseString(address1Data));
        myAccountAddressPage.updateStreetOptionalName(reverseString(address2Data));
        myAccountAddressPage.updateCityName(reverseString(townOrCityData));
        myAccountAddressPage.updatePostalCode(reverseString(Props.getProp("country.postal.code")));
        myAccountAddressPage.updatePhoneNumber(reverseString(phoneData));
    }

    @And("^I click on save address$")
    public void clickSaveAddress() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.clickSaveAddressBtn();
    }

    @Then("^Address should be saved$")
    public void verifyAddressSaved() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(fName));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(lastNameData));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(address1Data));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(address2Data));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(townOrCityData));
//        if (Props.getProp("selectedCountry").contains("Austria")){
//            Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(Props.getProp("country.postal.code.Austria")));
//        }
//        else {
            Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(Props.getProp("country.postal.code")));
//        }
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(phoneData));
    }

    @Then("^Address should be updated$")
    public void verifyAddressUpdated() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(fName)),"First name not found");

        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(lastNameData)),"Last name not found");
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(address1Data)),"Address1 details not found");
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(address2Data)),"Address2 details not found");
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(townOrCityData)),"City not found");
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(Props.getProp("country.postal.code"))),"Postal code not found");
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(reverseString(phoneData)));
    }

    @When("^I click to delete last added address$")
    public void clickTrashIcon() {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.clickTrashIcon(fName);
    }

    @When("^I click to delete last updated address$")
    public void clickTrashIconLastUpdated() {
        myAccountAddressPage.clickTrashIconLastUpdated(fName);
    }

    @And("^I click on delete button$")
    public void clickDeleteBtn() {
        IsPageLoaded.waitAllRequest();
        myAccountAddressPage.clickDeleteBtn();
    }

    @Then("^Address should be deleted$")
    public void verifyAddressDeleted() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(fName));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(lastNameData));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(address1Data));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(address2Data));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(townOrCityData));
        Assert.assertTrue(myAccountAddressPage.getAddressBook().getText().contains(phoneData));
    }


    @Then("^I should see error message for mandatory fields.$")
    public void verifyEmptyErrorMessages() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(myAccountAddressPage.verifyFirstNameEmptyError());
        Assert.assertTrue(myAccountAddressPage.verifyLastNameEmptyError());
        Assert.assertTrue(myAccountAddressPage.verifyStreetNameEmptyError());
        Assert.assertTrue(myAccountAddressPage.verifyCityNameEmptyError());
        Assert.assertTrue(myAccountAddressPage.verifyPostalCodeEmptyError());
    }

    @When("^I enter first name with \"([^\"]*)\"$")
    public void fillFirstName(String fName) {
        String firstName = Props.getProp(fName);
        Assert.assertTrue(myAccountAddressPage.enterFirstName(firstName));
    }
    @Then("^I should see error message for invalid first name on add address page$")
    public void verifyFirstNameInvalidInputErrorMessages() {
        Assert.assertTrue(myAccountAddressPage.verifyFirstNameInvalidError());
    }

    @When("^I enter last name with \"([^\"]*)\"$")
    public void fillLastName(String lName) {
        String lastName = Props.getProp(lName);
        Assert.assertTrue(myAccountAddressPage.enterLastName(lastName));
    }

    @Then("^I should see error message for invalid last name on add address page$")
    public void verifyLastNameInvalidInputErrorMessages() {
        Assert.assertTrue(myAccountAddressPage.verifyLastNameInvalidError());
    }

    @Then("^Error message for invalid first name should be gone from address page$")
    public void verifyFirstNameErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyFirstNameInvalidErrorMessageNotShown());
    }

    @Then("^Error message for invalid last name should be gone from address page$")
    public void verifyLastNameErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyLastNameInvalidErrorMessageNotShown());
    }

    @When("^I enter street name on add address page$")
    public void enterStreetNameAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterStreet(address1Data));
    }

    @Then("^Error message for empty street name should be gone from address page$")
    public void verifyStreetNameEmptyErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyStreetNameEmptyErrorMessageNotShown());
    }

    @When("^I enter city name on add address page$")
    public void enterCityNameAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterCity(townOrCityData));
    }

    @Then("^Error message for empty city name should be gone from address page$")
    public void verifyCityNameEmptyErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyCityNameEmptyErrorMessageNotShown());
    }

    @When("^I enter invalid phone number on add address page$")
    public void enterInvalidPhoneNumberAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterPhone(phoneDataInvalid));
    }

    @When("^I enter invalid postal code on add address page$")
    public void enterInvalidPostalCodeAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterPostalCode(postalCodeInvalid));
    }

    @Then("^I should see error message for invalid phone number on add address age$")
    public void verifyPhoneInvalidErrorMessage() {
        Assert.assertTrue(myAccountAddressPage.verifyPhoneInvalidErrorMessage());
    }

    @Then("^I should see error message for invalid postal code on add address age$")
    public void verifyPostalCodeInvalidErrorMessage() {
        Assert.assertTrue(myAccountAddressPage.verifyPostalCodeInvalidErrorMessage());
    }

    @When("^I enter valid phone number on add address page$")
    public void enterValidPhoneNumberAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterPhone(phoneData));
    }

    @When("^I enter valid postal code on add address page$")
    public void enterValidPostalCodeAddAddressPage() {
        Assert.assertTrue(myAccountAddressPage.enterPostalCode(Props.getProp("country.postal.code")));
    }

    @Then("^Error message for invalid phone number should be gone from address page$")
    public void verifyPhoneNumberInvalidErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyPhoneNumberInvalidErrorMessageNotShown());
    }

    @Then("^Error message for invalid postal code should be gone from address page$")
    public void verifyPostalCodeInvalidErrorMessageNotShown() {
        Assert.assertTrue(myAccountAddressPage.verifyPostalCodeInvalidErrorMessageNotShown());
    }

    @When("^I click pencil Icon to update the saved address$")
    public void clickPencilIconLastAddress() {
        Assert.assertTrue(myAccountAddressPage.clickPencilIconLastAddress(fName));
    }

    @When("^I click update address button$")
    public void clickUpdateAddress() {
        Assert.assertTrue(myAccountAddressPage.clickUpdateAddress());
    }

    @Then("^I should see message that address already exist$")
    public void verifyAddressAlreadyExistMessage() {
        String expected = Props.getMessage("address.already.exist");
        String actual = myAccountAddressPage.verifyAddressAlreadyExistMessage();
        Assert.assertTrue(actual.contains(expected), "Actual-->>" + actual + ", Expected-->>" + expected);
    }
}


