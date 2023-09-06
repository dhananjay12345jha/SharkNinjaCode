package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNCA.RegisterMyGuaranteePageSNCA;
import com.salmon.test.page_objects.gui.SNUS.HomePageSNUS;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class RegisterMyGuaranteeStepsSNCA {
    private RegisterMyGuaranteePageSNCA registerMyGuaranteePageSNCA;
    private HomePageSNUS homePageSNUS;

    public RegisterMyGuaranteeStepsSNCA() {
        registerMyGuaranteePageSNCA = new RegisterMyGuaranteePageSNCA();
        homePageSNUS = new HomePageSNUS();
    }

    @When("^I click on register my guarantee link of Shark CA$")
    public void click_on_register_my_guarantee_link() throws InterruptedException {
        registerMyGuaranteePageSNCA = new HomePageSNCA().clickRegisterMyGuarantee();
        Thread.sleep(2000);
        Assert.assertTrue(registerMyGuaranteePageSNCA != null, "Unable to click on register my guarantee link please check");
    }

    @When("^I click on register my guarantee link of Shark US$")
    public void click_on_register_my_guarantee_link_SharkUS() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(homePageSNUS.clickRegisterMyGuaranteeFooter(), "Unable to click on register my guarantee footer link please check");
    }

    @When("^I click on customer care link of Shark CA$")
    public void click_on_customer_care_link_for_sharkCA()
    {

    }

    @Then("^I should be on register my guarantee page having text either \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_user_is_on_register_my_guarantee_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTextRegisterAProduct(), expected);
    }

    @Then("^I should be on Parts & Accessories Page page having text either \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_user_is_on_Parts_and_Accessories_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTexthv300Description(), expected);

    }

    @Then("^I should be on User Manuals page having text either \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_user_is_on_User_Manuals_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTextFaqs(), expected);
    }

    @Then("^I should be on Five Year Warranty page having text either \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_user_is_on_Five_Year_Warranty_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTextWarrantyAndReturns(), expected);
    }

    @Then("^I should be on Faqs page having text either \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_user_is_on_Faqs_page(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTextProductInformationAndFAQs(), expected);
    }

    @When("^I fill register my guarantee form of Shark CA with below details$")
    public void complete_register_my_guarantee_from(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> coloumn : row) {
            softAssert.assertTrue(registerMyGuaranteePageSNCA.enterFirstName(coloumn.get("FirstName")),"Unable to fill FirstName in register my guarantee form");
            softAssert.assertTrue(registerMyGuaranteePageSNCA.enterLastName(coloumn.get("LastName")),"Unable to fill LastName in register my guarantee form");
            softAssert.assertTrue(registerMyGuaranteePageSNCA.enterModelNumber(coloumn.get("ModelNo")),"Unable to fill ModelNumber in register my guarantee form");
            if(!coloumn.get("SerialNo").isEmpty()){
                softAssert.assertTrue(registerMyGuaranteePageSNCA.enterSerialNumber(coloumn.get("SerialNo")),"Unable to fill Serial Number in register my guarantee form");
            }
            else {
                registerMyGuaranteePageSNCA.enterSerialNumber(coloumn.get("SerialNo"));
            }
            softAssert.assertTrue(registerMyGuaranteePageSNCA.selectStoreOfPurchaseAs(coloumn.get("StoreOfPurchase")),"Unable to select store from dropdown in register my guarantee form");
            //softAssert.assertTrue(registerMyGuaranteePageSNCA.enterPurchaseDate(coloumn.get("PurchaseDate")),"Unable to fill Purchase date in register my guarantee form");
            registerMyGuaranteePageSNCA.enterPurchaseDate(coloumn.get("PurchaseDate"));
            String emailToSet;
            if(Props.getProp(coloumn.get("Email"))!=null ||!Props.getProp(coloumn.get("Email")).isEmpty()){
                emailToSet=Props.getProp(coloumn.get("Email"));
            }
            else {
                emailToSet=coloumn.get("Email");
            }
            softAssert.assertTrue(registerMyGuaranteePageSNCA.enterEmail(emailToSet),"Unable to fill email in register my guarantee form");
            softAssert.assertTrue(registerMyGuaranteePageSNCA.selectCountryAs(coloumn.get("Country")),"Unable to select country from dropdown in register my guarantee form");
            if (coloumn.get("SpecialOfferPromotions").equalsIgnoreCase("yes")) {
                softAssert.assertTrue(registerMyGuaranteePageSNCA.tickAcceptTermsAndConditionCheckbox(),"Unable to check accept terms and condition checkbox");
            }
        }
        softAssert.assertAll();
    }

    @And("^Click Submit button of Shark CA$")
    @When("^I Click Submit button without filling any information$")
    public void click_on_register_product_button() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.clickRegisterProductBtn());
    }

    @Then("^Guarantee get registered and text \"([^\"]*)\" or \"([^\"]*)\" should be shown$")
    public void validate_text_gurantee_registered_success(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getTextGuaranteeRegisterSuccessfully(), expected);
    }

    @Then("^Warranty Information and text \"([^\"]*)\" or \"([^\"]*)\" should be shown on PDP and Cart Page$")
    public void warranty_information_text_success(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getWarrantyInfoTextSuccessfully(), expected);
    }
    @And("^Model Number should be displayed as \"([^\"]*)\"$")
    public void validate_model_number_should_be_same_for_which_guarantee_registered(String modelNumber) {
        Assert.assertEquals(registerMyGuaranteePageSNCA.getModelNumberAfterGuaranteeRegister(), modelNumber);
    }

//    @And("^Purchase date should be displayed as \"([^\"]*)\" or \"([^\"]*)\"$")
//    public void validate_purchase_date_should_be_same_for_which_guarantee_registered(String dataEN, String dataFR) {
//        String expected;
//        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
//            expected = dataEN;
//        } else {
//            expected = dataFR;
//        }
//        Assert.assertEquals(registerMyGuaranteePageSNCA.getPurchaseDateGuaranteeRegister(),expected);
//    }

    @And("^Purchase date should be one less than the currentDate$")
    public void validate_Purchase_date_should_be_one_less_than_the_currentDate() {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected =registerMyGuaranteePageSNCA.compareWithPurchaseDateEN();
            System.out.println("expected= " +expected);
        } else {
            expected = registerMyGuaranteePageSNCA.compareWithPurchaseDateFR();
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getPurchaseDateGuaranteeRegister(),expected);
    }

    @And("^Warranty Expiration should be displayed as \"([^\"]*)\" or \"([^\"]*)\"$")
    public void validate_warranty_Expiration_should_be_same_for_which_guarantee_registered(String dataEN, String dataFR) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = dataEN;
        } else {
            expected = dataFR;
        }
        Assert.assertEquals(registerMyGuaranteePageSNCA.getExpirationDateGuaranteeRegister(),expected);
    }
    @And("^Save the guarantee information$")
    public void saveGuaranteeInfo() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.saveGuaranteeInfo());
    }

    @Then("^I should see error messages for all mandatory fields$")
    public void validateErrorMessage() throws InterruptedException {
//        Assert.assertTrue(registerMyGuaranteePageSNCA.validateErrorMessage());
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyFirstName());
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyLastName());
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyModelNumber());
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyPurchaseStore());
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyPurchaseDate());
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyEmptyErrorMessageEmail());
        Thread.sleep(3000);
    }

    @When("^I fill invalid first name in register my guaranty$")
    public void fillInvalidFirstName() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.enterFirstName(Props.getProp("register.guarantee.invalid.firstname")));
    }

    @Then("^I should see error message for first name$")
    public void verifyInvalidErrorMessageFirstName() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidErrorMessageFirstName());
        Thread.sleep(1000);
    }

    @Then("^I should see invalid error message for last name$")
    public void verifyInvalidErrorMessageLastName() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidErrorMessageLastName());
        Thread.sleep(1000);
    }

    @When("^I fill valid first name in register my guaranty$")
    public void fillValidFirstName() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.enterFirstName(random(6, ALPHABETS)));
    }

    @When("^I fill invalid last name in register my guaranty$")
    public void fillInvalidLastName() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.enterLastName(Props.getProp("register.guarantee.invalid.lastname")));
    }

    @When("^I fill valid last name in register my guaranty$")
    public void fillValidLastName() {
        Assert.assertTrue(registerMyGuaranteePageSNCA.enterLastName(random(6, ALPHABETS)));
    }

    @Then("^error message for invalid first name should not be shown$")
    public void verifyInvalidFirstNameErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidFirstNameErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @Then("^error message for invalid last name should not be shown$")
    public void verifyInvalidLastNameErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidLastNameErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @Then("^Error message for invalid email should not be shown$")
    public void verifyInvalidEmailErrorMessageNotShown() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidEmailErrorMessageNotShown());
        Thread.sleep(1000);
    }

    @When("^I fill invalid email address in register my guaranty$")
    public void fillInvalidEmail() throws InterruptedException {
        registerMyGuaranteePageSNCA.enterEmail(Props.getProp("register.guarantee.invalid.email"));
        Thread.sleep(1000);
    }

    @Then("^error message for invalid email should be shown$")
    public void verifyInvalidErrorMessageEmail() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.verifyInvalidErrorMessageEmail());
        Thread.sleep(1000);
    }

    @When("^I fill valid email address in register my guaranty$")
    public void fillValidEmail() throws InterruptedException {
        Assert.assertTrue(registerMyGuaranteePageSNCA.enterEmail(Props.getProp("valid.email")));
        Thread.sleep(1000);
    }
}
