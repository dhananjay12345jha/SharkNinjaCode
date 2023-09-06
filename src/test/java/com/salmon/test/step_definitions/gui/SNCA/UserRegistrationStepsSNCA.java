package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.SNCA.CreateNewAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountAddressPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.SecureCheckoutPageSNCA;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class UserRegistrationStepsSNCA {
    private HomePageSNCA homePageSNCA;
    private CreateNewAccountPageSNCA createNewAccountPageSNCA;
    private MyAccountAddressPageSNCA myAccountAddressPageSNCA;
    private SecureCheckoutPageSNCA secureCheckoutPageSNCA;

    public UserRegistrationStepsSNCA() {
        createNewAccountPageSNCA = new CreateNewAccountPageSNCA();
        homePageSNCA = new HomePageSNCA();
        myAccountAddressPageSNCA = new MyAccountAddressPageSNCA();
        secureCheckoutPageSNCA = new SecureCheckoutPageSNCA();
    }


    @When("^I clicked on Register Now Button$")
    public void clicked_on_registerNow_button() {
        Assert.assertTrue(homePageSNCA.clickRegisterNowButton() != null, "Unable to click on \"Register Now\" button over Sign In Pop up");
    }

    @Then("^I should be on User Registration Page having text \"(.*?)\" or \"(.*?)\"$")
    public void validate_user_should_be_on_create_my_account_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("fr")) {
            expected = dataFR;
        } else {
            expected = dataEN;
        }
        String actual = createNewAccountPageSNCA.getTextCreateNewAccount();
        Assert.assertEquals(actual, expected);
    }

    @And("^I fill all required field with given data$")
    public void fill_user_registration_from(DataTable dataTable) {
        //Assert.assertTrue(secureCheckoutPageSNCA.isPageRefreshedAndDetailsShown(),"Unable to Enter the user data for creation of account page loader is there");
        secureCheckoutPageSNCA.isPageRefreshedAndDetailsShown();
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> coloumn : row) {

            if (coloumn.containsKey("Email")) {
                if (coloumn.get("Email").equalsIgnoreCase("RandomGenerated")) {
                    String randomEmail = RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC) + "@wunderman.com";
                    softAssert.assertTrue(createNewAccountPageSNCA.enterEmailAddress(randomEmail));
                    softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmEmailAddress(randomEmail));
                } else {
                    softAssert.assertTrue(createNewAccountPageSNCA.enterEmailAddress(coloumn.get("Email")));
                    softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmEmailAddress(coloumn.get("ConfirmEmail")));
                }
            }
            if (coloumn.containsKey("Password"))
                softAssert.assertTrue(createNewAccountPageSNCA.enterPassword(coloumn.get("Password")));
            if (coloumn.containsKey("ConfirmPassword"))
                softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmPassword(coloumn.get("ConfirmPassword")));
            if (coloumn.containsKey("FirstName"))
                softAssert.assertTrue(createNewAccountPageSNCA.setFirstName(coloumn.get("FirstName")));
            if (coloumn.containsKey("LastName"))
                softAssert.assertTrue(createNewAccountPageSNCA.setLastName(coloumn.get("LastName")));
            if (coloumn.containsKey("StreetAdd"))
                softAssert.assertTrue(createNewAccountPageSNCA.setStreetAddress(coloumn.get("StreetAdd")));
            if (coloumn.containsKey("City"))
                softAssert.assertTrue(createNewAccountPageSNCA.setCity(coloumn.get("City")));
            if (coloumn.containsKey("Postal"))
                softAssert.assertTrue(createNewAccountPageSNCA.setPostalCode(coloumn.get("Postal")));
            if (coloumn.containsKey("Province"))
                softAssert.assertTrue(createNewAccountPageSNCA.selectProvince(coloumn.get("Province")));
            if (coloumn.containsKey("Phone"))
                softAssert.assertTrue(createNewAccountPageSNCA.setPhoneNumber(coloumn.get("Phone")));
            createNewAccountPageSNCA.selectCheckBoxInsideCaptcha();
        }
        softAssert.assertAll();
    }

    @And("^I fill all billing required field with given data$")
    public void fillBillingUserRegistrationForm(DataTable dataTable) {
//        Assert.assertTrue(secureCheckoutPageSNCA.isPageRefreshedAndDetailsShown(),"Unable to Enter the user data for creation of account page loader is there");
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> coloumn : row) {

            if (coloumn.containsKey("Email")) {
                if (coloumn.get("Email").equalsIgnoreCase("RandomGenerated")) {
                    String randomEmail = RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC) + "@wunderman.com";
                    softAssert.assertTrue(createNewAccountPageSNCA.enterEmailAddress(randomEmail));
                    softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmEmailAddress(randomEmail));
                } else {
                    softAssert.assertTrue(createNewAccountPageSNCA.enterEmailAddress(coloumn.get("Email")));
                    softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmEmailAddress(coloumn.get("ConfirmEmail")));
                }
            }
            if (coloumn.containsKey("Password"))
                softAssert.assertTrue(createNewAccountPageSNCA.enterPassword(coloumn.get("Password")));
            if (coloumn.containsKey("ConfirmPassword"))
                softAssert.assertTrue(createNewAccountPageSNCA.enterConfirmPassword(coloumn.get("ConfirmPassword")));
            if (coloumn.containsKey("FirstName"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingFirstName(coloumn.get("FirstName")));
            if (coloumn.containsKey("LastName"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingLastName(coloumn.get("LastName")));
            if (coloumn.containsKey("StreetAdd"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingStreetAddress(coloumn.get("StreetAdd")));
            if (coloumn.containsKey("City"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingCity(coloumn.get("City")));
            if (coloumn.containsKey("Postal"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingPostalCode(coloumn.get("Postal")));
            if (coloumn.containsKey("Province"))
                softAssert.assertTrue(createNewAccountPageSNCA.selectBillingProvince(coloumn.get("Province")));
            if (coloumn.containsKey("Phone"))
                softAssert.assertTrue(createNewAccountPageSNCA.setBillingPhoneNumber(coloumn.get("Phone")));
            createNewAccountPageSNCA.selectCheckBoxInsideCaptcha();
        }
        softAssert.assertAll();
    }

    @When("^i fill Street address with postal code \"(.*?)\"$")
    public void setStreetAddress(String postCode) {
        Assert.assertTrue(createNewAccountPageSNCA.setStreetAddress(postCode));
    }

    @When("^i fill billing Street address with postal code \"(.*?)\"$")
    public void setBillingStreetAddress(String postCode) {
        Assert.assertTrue(createNewAccountPageSNCA.setBillingStreetAddress(postCode));
    }

    @Then("^no address is auto populated as postal code is invalid$")
    public void verifyNoAddressFound() {
        Assert.assertTrue(createNewAccountPageSNCA.verifyNoAddressPopulated());
    }

    @Then("^address is auto populated as postal code is valid$")
    public void verifyAddressPopulated() {
        Assert.assertTrue(createNewAccountPageSNCA.verifyAddressPopulated());
    }

    @Then("^I select Terms And Condition Checkbox$")
    public void select_checkBox_terms_and_condition() {
        Assert.assertTrue(createNewAccountPageSNCA.tickCheckBoxTermsAndCondition(), "Unable to tick checkbox terms and condition");
    }

    @And("^I clicked on Create Account Button$")
    public void click_on_create_account_button() throws InterruptedException {
        Assert.assertTrue(createNewAccountPageSNCA.clickCreateAccountButton());
        Thread.sleep(8000);
    }

    @And("^click Create Account Button$")
    public void click_create_account_button() throws InterruptedException {
        Assert.assertTrue(createNewAccountPageSNCA.clickCreateAccountButtonSNUS());
        Thread.sleep(2000);
    }

    @Then("^message either \"(.*?)\" or \"(.*?)\" should be there$")
    public void validate_message_after_successfully_register_new_user(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("fr")) {
            expected = dataFR;
        } else {
            expected = dataEN;
        }
        String actual = createNewAccountPageSNCA.getTextAfterSuccessfulAccountCreation().trim();
        Assert.assertTrue(actual.contains(expected), "Expected-->" + expected + "\n" + "Actual-->" + actual);
    }

    @Then("^an error message \"(.*?)\" or \"(.*?)\" should be there$")
    public void validate_error_message_when_try_to_registered_user_with_existing_credentials(String value1,String value2) {
        String expected;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            expected=value1;
        }
        else {
            expected=value2;
        }
        String actual = createNewAccountPageSNCA.getErrorMessageWhenAccountCreatedWithExistingCredentials();
        Assert.assertEquals(actual, expected);

    }

    @Then("^an error message \"(.*?)\" or \"(.*?)\" should be there for existing account$")
    public void getErrorMessageRegisterWithExistingCredentialsSNUS(String value1,String value2) {
        String expected;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            expected=value1;
        }
        else {
            expected=value2;
        }
        String actual = createNewAccountPageSNCA.getErrorMessageRegisterWithExistingCredentialsSNUS();
        Assert.assertEquals(actual, expected);

    }


    @Then("^validate that \"([^\"]*)\" error message for mandatory field \"([^\"]*)\" should be shown$")
    public void validate_message_for_the_mandatory_label(String value1,String value2)
    {
        String actualMessage;
        String expected;
        actualMessage=createNewAccountPageSNCA.findWebElementAndFetchText(value2);

        if(value2.contains(" ")){
            value2=value2.replace(" ","");
        }
        String message="createNewUser."+value1+".error.message.for."+value2;
        expected=Props.getProp(message).trim();
        Assert.assertTrue(actualMessage.contains(expected));
    }
}
