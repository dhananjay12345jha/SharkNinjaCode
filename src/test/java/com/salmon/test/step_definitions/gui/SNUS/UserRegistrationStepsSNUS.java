package com.salmon.test.step_definitions.gui.SNUS;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.SNCA.CreateNewAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNUS.CreateNewAccountPageSNUS;
import com.salmon.test.page_objects.gui.SNUS.HomePageSNUS;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class UserRegistrationStepsSNUS {
    private HomePageSNUS homePageSNUS;
    private CreateNewAccountPageSNUS createNewAccountPageSNUS;

    public UserRegistrationStepsSNUS() {
        createNewAccountPageSNUS = new CreateNewAccountPageSNUS();
        homePageSNUS = new HomePageSNUS();
    }

    @When("^I clicked on Create Account link$")
    public void clicked_on_registerNow_button() {
        Assert.assertTrue(homePageSNUS.clickRegisterNowButton() != null, "Unable to click on \"Register Now\" button over Sign In Pop up");
    }

    @Then("^I should be on Shark User Registration Page having text \"(.*?)\" or \"(.*?)\"$")
    public void validate_user_should_be_on_create_my_account_page(String dataEN, String dataFR) {
        String expected;
     if (Props.getProp("select.language.to.test").equalsIgnoreCase("fr")) {
            expected = dataFR;
        } else {
            expected = dataEN;
        }
        String actual = createNewAccountPageSNUS.getTextCreateNewAccount();
        Assert.assertEquals(actual, expected);
    }

    @And("^I fill all required field with given data while creating account$")
    public void fill_user_registration_from(DataTable dataTable) {
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> coloumn : row) {

            if (coloumn.containsKey("FirstName"))
                softAssert.assertTrue(createNewAccountPageSNUS.setFirstName(coloumn.get("FirstName")));
            if (coloumn.containsKey("LastName"))
                softAssert.assertTrue(createNewAccountPageSNUS.setLastName(coloumn.get("LastName")));
            if (coloumn.containsKey("Email")) {
                if (coloumn.get("Email").equalsIgnoreCase("RandomGenerated")) {
                    String randomEmail = RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC) + "@wunderman.com";
                    softAssert.assertTrue(createNewAccountPageSNUS.enterEmailAddress(randomEmail));
                } else {
                    softAssert.assertTrue(createNewAccountPageSNUS.enterEmailAddress(coloumn.get("Email")));
                }
            }
            if (coloumn.containsKey("Password"))
                softAssert.assertTrue(createNewAccountPageSNUS.enterPassword(coloumn.get("Password")));
        }
        softAssert.assertAll();
    }
}
