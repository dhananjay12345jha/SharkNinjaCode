package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.AgentProfilePage;
import com.salmon.test.page_objects.gui.SNAP.SNAPNewProductRegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class AgentProfilePageSteps
{

    public AgentProfilePageSteps()
    {
        agentProfilePage=new AgentProfilePage();
    }

    private AgentProfilePage agentProfilePage;
    private SNAPNewProductRegistrationPage newProductRegistrationPage;



    @And("^Clicks on products registrations button and click Add Product Registration button$")
    public void clicksProductsRegistrationAndThenAddRegistration()
    {
        newProductRegistrationPage=agentProfilePage.clickProductsRegistrationsLink().clickAddProductRegistration();
        Assert.assertTrue(newProductRegistrationPage!=null);
    }

    @And("^i click on product registration$")
    public void clickOnProductRegistration(){
        agentProfilePage.clickProductsRegistrationsLink();
    }

    @And("^i should see parts warranty$")
    public void verifyPartsWarranty(){
        String text = agentProfilePage.getPartsWarrantyText();
        Assert.assertEquals(text, Props.getProp("parts.warranty.text"));
    }

    @And("^i should see parts and unit warranty$")
    public void partsAndUnitWarranty(){
        String text = agentProfilePage.getPartsWarrantyText2();
        Assert.assertEquals(text, Props.getProp("parts.warranty.text2"));

        String text1 = agentProfilePage.getUnitWarranty();
        Assert.assertEquals(text1, Props.getProp("units.warranty.text"));
    }

    @Then("^i should see unit warranty on checkout page$")
    @And("^i should see unit warranty on cart page$")
    public void verifyUnitWarranty(){
        String text1 = agentProfilePage.getUnitWarrantyOnCartPage();
        Assert.assertEquals(text1, Props.getProp("units.warranty.text"));
    }

    @And("^updating first name as \"(.*)\"$")
    public void updatingFirstNameAs(String firstName)
    {
        Assert.assertTrue(agentProfilePage.setCustomerFirstName(firstName));
    }

    @And("^I click on Resend confirmation button$")
    public void clickOnResendConfirmationMail(){
        Assert.assertTrue(agentProfilePage.clickResendConfirmationMail());
    }

    @And("^I click on Resend confirmation button again$")
    public void clickOnResendConfirmationMailAgain(){
        Assert.assertTrue(agentProfilePage.clickResendConfirmationMailAgain());
    }

    @Then("^acknowledgement message \"(.*)\" should be displayed$")
    public void getAcknowledgeMessage(String message){
        Assert.assertEquals(agentProfilePage.getAcknowledgementMessageWhenClickOnSave(), message);
    }

    @And("^I click on Reset Password button$")
    public void clickOnResetPasswordButton(){
        Assert.assertTrue(agentProfilePage.clickResetPasswordButton());
    }

    @And("^I select \"shark\" channel for reset password$")
    public void selectSharChannelForResetPassword(){
        Assert.assertTrue(agentProfilePage.selectSharkChannelForResetPassword());
    }

    @And("^I click on send reset password button$")
    public void clickOnSendResetPasswordButton(){
        Assert.assertTrue(agentProfilePage.clickResetEmailButton());
    }

    @And("^I click on Load Email Preference Bronto button$")
    public void clickOnEmailPrefernceOption(){
        Assert.assertTrue(agentProfilePage.clickEmailPrefernceOption());
    }

    @And("^I select \"shark\" channel for confirmation$")
    public void selectSharkAsChannel(){
        Assert.assertTrue(agentProfilePage.clickOnSharkChannel());
    }


    @And("^clicks on save profile button$")
    public void clickOnSaveProfileButton()
    {
        Assert.assertTrue(agentProfilePage.clickSaveProfileButton());
    }

    @Then("^updation acknowledgement message \"(.*)\" should be there$")
    public void validateAcknowledgementMessagePresence(String message)
    {
        Assert.assertTrue(agentProfilePage.getAcknowledgementMessageWhenClickOnSave().contains(message),
                "When clicked on save profile button either message show has mismatched or it is not getting displayed, please check logs");
    }

    @And("^updating last name as \"(.*)\"$")
    public void updatingCustomerLastName(String lastName)
    {
        Assert.assertTrue(agentProfilePage.setCustomerLastName(lastName),"Unable to set customer last name please check logs");
    }

    @And("^customer name should get display as \"(.*)\"$")
    public void validateCustomerNameDisplayShouldGetUpdate(String updatedName)
    {
        Assert.assertEquals(agentProfilePage.getCustomerNameInDisplay(),updatedName,"After updating first and last name and clicked on save profile button " +
                "customer name display is not getting updated.");
    }


}
