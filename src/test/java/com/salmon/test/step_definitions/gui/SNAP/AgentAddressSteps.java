package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.page_objects.gui.SNAP.AgentAddressPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class AgentAddressSteps {

    AgentAddressPage agentAddressPage;

    public AgentAddressSteps(){
        agentAddressPage = new AgentAddressPage();
    }

    @Then("^I should select Address option$")
    public void i_should_select_Address_option(){
        Assert.assertTrue(agentAddressPage.clickAddressButton());
    }

//    @Then("^i should see firstName as \"(.?)\"$")
//    public void i_should_select_Address_option(String name){
//        Assert.assertTrue(agentAddressPage.clickAddressButton(), name);
//    }

    @And("^I click on edit link in other address section$")
    public void i_click_on_edit_link(){
        Assert.assertTrue(agentAddressPage.clickEditButton());
    }

    @And("^I click on edit link in shipping address section$")
    public void i_click_on_edit_link_for_shipping_address(){
        Assert.assertTrue(agentAddressPage.clickEditButtonOfShippingAddress());
    }

    @When("^I click on edit link in other's address section$")
    public void i_Click_On_Edit_Link_In_OtherS_Address_Section() {
        Assert.assertTrue(agentAddressPage.clickOtherAddressEditButton());
    }

    @And("^i click on save changes button of other address$")
    public void iClickOnSaveChangesButtonOfOtherAddress() {
        Assert.assertTrue(agentAddressPage.clickSaveChangesButtonForOtherAddress());
    }

    @And("^i click on save changes button of shipping address$")
    public void iClickOnSaveChangesButtonOfShippingAddress() {
        Assert.assertTrue(agentAddressPage.clickSaveChangesButtonForShippingAddress());
    }

    @And("^i click on save changes button$")
    public void i_click_on_save_changes_button(){
        Assert.assertTrue(agentAddressPage.clickSaveChangesButton());
    }

    @When("^I fill my update address form with below details$")
    public void complete_address_edit_form(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> coloumn : row) {
            softAssert.assertTrue(agentAddressPage.enterFirstName(coloumn.get("FirstName")),"Unable to fill FirstName ");
            softAssert.assertTrue(agentAddressPage.enterLastName(coloumn.get("LastName")),"Unable to fill LastName ");
            softAssert.assertTrue(agentAddressPage.enterAddressLine1(coloumn.get("AddressLine1")),"Unable to fill Address Line 1");
            softAssert.assertTrue(agentAddressPage.enterCityName(coloumn.get("City")),"Unable to fill City");
            softAssert.assertTrue(agentAddressPage.selectStateName(coloumn.get("State").trim()),"Unable to fill State");
            softAssert.assertTrue(agentAddressPage.enterZipCode(coloumn.get("ZIP/PostalCode")),"Unable to fill Zip Postal Code");
        }
        softAssert.assertAll();
    }

    @When("^I fill my update address form with below details For Shipping Address$")
    public void complete_address_edit_form_for_shipping_Address(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> coloumn : row) {
            softAssert.assertTrue(agentAddressPage.enterFirstNameForShipping(coloumn.get("FirstName")),"Unable to fill FirstName ");
            softAssert.assertTrue(agentAddressPage.enterLastNameForShipping(coloumn.get("LastName")),"Unable to fill LastName ");
            softAssert.assertTrue(agentAddressPage.enterAddressLine1ForShipping(coloumn.get("AddressLine1")),"Unable to fill Address Line 1");
            softAssert.assertTrue(agentAddressPage.enterCityNameForShipping(coloumn.get("City")),"Unable to fill City");
            softAssert.assertTrue(agentAddressPage.selectStateNameForShipping(coloumn.get("State").trim()),"Unable to fill City");
            softAssert.assertTrue(agentAddressPage.enterZipCodeForShipping(coloumn.get("ZIP/PostalCode")),"Unable to fill Zip Postal Code");
        }
        softAssert.assertAll();
    }

    @When("^i should see firstName as \"(.*)\"$")
    public void complete_address_edit_form_for_CA(String name) {
        Assert.assertEquals(agentAddressPage.getFirstName(), "Megha");
    }


    @Then("^i should see the firstName as \"([^\"]*)\"$")
    public void i_Should_See_The_First_Name_As(String name) {
        Assert.assertEquals(agentAddressPage.getFirstNameInOtherAddress(), "MEGHA");

    }

    @And("^I fill my update address form with below details in other address$")
    public void iFillMyUpdateAddressFormWithBelowDetailsInOtherAddress(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> coloumn : row) {
            softAssert.assertTrue(agentAddressPage.enterFirstNameOtherAddress(coloumn.get("FirstName")),"Unable to fill FirstName ");
            softAssert.assertTrue(agentAddressPage.enterLastNameOtherAddress(coloumn.get("LastName")),"Unable to fill LastName ");
            softAssert.assertTrue(agentAddressPage.enterAddressLine1OtherAddress(coloumn.get("AddressLine1")),"Unable to fill Address Line 1");
            softAssert.assertTrue(agentAddressPage.enterCityNameOtherAddress(coloumn.get("City")),"Unable to fill City");
            //softAssert.assertTrue(agentAddressPage.enterPhoneOtherAddress(coloumn.get("Phone")),"Unable to fill City");
            softAssert.assertTrue(agentAddressPage.enterZipCodeOtherAddress(coloumn.get("ZIP/PostalCode")),"Unable to fill Zip Postal Code");
        }
        softAssert.assertAll();

    }
}
