package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.SecurityReminder;
import com.salmon.test.page_objects.gui.SNCA.MyAccountAddressPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountOrdersPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountProductWarrentiesSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountSettingPageSNCA;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.sl.In;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.util.*;

public class MyAccountPageStepsSNCA {

    public MyAccountPageStepsSNCA() {
        myAccountPageSNCA = new MyAccountPageSNCA();
    }

    private String propFilePath = System.getProperty("user.dir")+Props.getProp("file.path");
    private MyAccountPageSNCA myAccountPageSNCA;
    private MyAccountAddressPageSNCA myAccountAddressPageSNCA;
    private MyAccountOrdersPageSNCA myAccountOrdersPageSNCA;
    private MyAccountProductWarrentiesSNCA myAccountProductWarrentiesSNCA;
    private MyAccountSettingPageSNCA myAccountSettingPageSNCA;


    @When("^I Click on My Account icon$")
    public void iClickmyAccountSettingPageSNCAOnMyAccountIcon() {
        myAccountAddressPageSNCA = new MyAccountAddressPageSNCA();
        Assert.assertTrue(myAccountAddressPageSNCA.clickOnMyAccountIcon(), "Unable to click on my account icon");

            }
    
    @When("^I click on Addresses Book$")
    public void click_on_address_book_button() {
        myAccountAddressPageSNCA = myAccountPageSNCA.clickOnAddressBookBtn();
        Assert.assertTrue(myAccountAddressPageSNCA != null, "Unable to click on address book button on my account");
    }

    @When("^I click on Orders&Returns$")
    public void i_click_on_ordersreturns() throws Throwable {
    	myAccountOrdersPageSNCA = myAccountPageSNCA.clickOnOrderandReturn();
        Assert.assertTrue(myAccountOrdersPageSNCA != null, "Unable to click on Orders & Returns button on my account");
    }

     @When("^I click on Account Setting$")
    public void i_click_on_account_setting() throws Throwable {
    	 myAccountSettingPageSNCA = myAccountPageSNCA.clickOnAccountSetting();
         Assert.assertTrue(myAccountSettingPageSNCA != null, "Unable to click on Account Setting button on my account");
    }
    @When("^I click on My ProductsWarranties$")
    public void i_click_on_my_productswarranties() {
    	myAccountProductWarrentiesSNCA = myAccountPageSNCA.clickOnMyProductWarranties();
        Assert.assertTrue(myAccountProductWarrentiesSNCA != null, "Unable to click on products & Warranties button on my account");
   
    }

    @When("^I click on My Parts Accessories On Product Registration Confirmation Page$")
    public void i_click_on_my_Parts_Accessories() {
        myAccountProductWarrentiesSNCA = myAccountPageSNCA.clickOnPartsAccessories();
        Assert.assertTrue(myAccountProductWarrentiesSNCA != null, "Unable to click on Parts Accessories on Product Registration Confirmation Page");

    }

    @When("^I click on User Manuals On Product Registration Confirmation Page$")
    public void i_click_on_user_manuals() {
        myAccountProductWarrentiesSNCA = myAccountPageSNCA.clickOnUserManuals();
        Assert.assertTrue(myAccountProductWarrentiesSNCA != null, "Unable to click on User Manuals on Product Registration Confirmation Page");
    }

    @When("^I click on Five Year Ltd Warranty On Product Registration Confirmation Page$")
    public void i_click_on_Five_Year_Ltd_Warranty() {
        myAccountProductWarrentiesSNCA = myAccountPageSNCA.clickOnFiveYearLimitedWarranty();
        Assert.assertTrue(myAccountProductWarrentiesSNCA != null, "Unable to click on Five Year Ltd Warranty on Product Registration Confirmation Page");
    }

    @When("^I click on Faqs On Product Registration Confirmation Page$")
    public void i_click_on_Faqs() {
        myAccountProductWarrentiesSNCA = myAccountPageSNCA.clickOnFaqs();
        Assert.assertTrue(myAccountProductWarrentiesSNCA != null, "Unable to click on Faqs on Product Registration Confirmation Page");
    }
    @Then("^I should be on Addresses page showing text \"([^\"]*)\"$")
    public void i_should_be_on_address_page_for_SNCA(String expected) {
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR"))
            Assert.assertEquals(myAccountAddressPageSNCA.getTextAddresses(), "Ajouter une adresse", "Unable to match text on my addresses page");
        else
            Assert.assertEquals(myAccountAddressPageSNCA.getTextAddresses(), expected, "Unable to match text on my addresses page");
    }

    @And("^I click edit address link for editing a address$")
    @When("^I click add address link for adding a address$")
    public void click_on_add_address_link_to_add_new_address() {
        Assert.assertTrue(myAccountAddressPageSNCA.clickAddAddressButton(), "Unable to click on add address button");
    }

    @And("^edit an address form should be visible$")
    @Then("^add an address form should be visible$")
    public void add_an_address_form_visibility() {
        Assert.assertTrue(myAccountAddressPageSNCA.addAnAddressForm().isDisplayed(), "To add an address form is not visibile on the page");
    }

    @When("^I fill the form to add address with information given below$")
    public void fill_address_form_with_given_information(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> coloumn : rows) {
//            softAssert.assertTrue(myAccountAddressPageSNCA.selectCountry(coloumn.get("Country")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setFirstName(coloumn.get("FirstName")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setLastName(coloumn.get("LastName")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setStreetAddress(coloumn.get("StreetAddress")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setPostalCode(coloumn.get("PostalCode")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setCity(coloumn.get("City")));
            softAssert.assertTrue(myAccountAddressPageSNCA.selectProvince(coloumn.get("Province")));
            softAssert.assertTrue(myAccountAddressPageSNCA.setPhoneNumber(coloumn.get("Phone")));

        }
        softAssert.assertAll();
    }

    @And("^I click on save address button$")
    public void click_on_save_address_button() {
        Assert.assertTrue(myAccountAddressPageSNCA.clickOnSaveAddressButton(),
                "Unable to click on save address button over add my address page");
    }

    @And("^I click on save address button without filling form$")
    public void clickSaveBtn() {
        Assert.assertTrue(myAccountAddressPageSNCA.clickSaveBtnWithoutFillingForm(),
                "Unable to click on save address button over add my address page");
    }

    @Then("^newly added Address should be saved given below details$")
    public void validate_newly_added_address_is_saved_successfully(DataTable dataTable) {
        String expected, actual;
        boolean flag = false;
        Object obj = myAccountAddressPageSNCA.getAllSavedAddress();
        if (obj instanceof Integer) {
            Assert.assertTrue(flag, "Saved address is not getting displayed, when fetching list of address saved, found empty list");
        } else {
            Collection<String> data = dataTable.asMaps(String.class, String.class).get(0).values();
            List<String> address = (List<String>) obj;
            for (String i : address) {
                flag = true;
                actual = i.toLowerCase();
                for (String j : data) {
                    expected = j.toLowerCase();
                    if (!actual.contains(expected)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            Assert.assertTrue(flag, "Newly added address is not saved");
        }
    }

    @Then("^Address should be edited with given below details$")
    public void validate_address_is_edited_successfully(DataTable dataTable) {
        String expected, actual;
        boolean flag = false;
        Object obj = myAccountAddressPageSNCA.getPreferredShippingAndBillingAddress();
        if (obj instanceof Integer) {
            Assert.assertTrue(flag, "Saved address is not getting displayed, when fetching list of address saved, found empty list");
        } else {
            Collection<String> data = dataTable.asMaps(String.class, String.class).get(0).values();
            List<String> address = (List<String>) obj;
            for (String i : address) {
                flag = true;
                actual = i.toLowerCase();
                for (String j : data) {
                    expected = j.toLowerCase();
                    if (!actual.contains(expected)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            Assert.assertTrue(flag, "Address is not edited");
        }
    }

    @When("^I click on trash button where pincode is \"([^\"]*)\" to delete the address$")
    public void delete_address_having_pincode(String pincode) {
        Assert.assertTrue(myAccountAddressPageSNCA.deleteSavedAddressHavingPostalCode(pincode));
    }

    @Then("^I click on Delete button on confirmation pop up$")
    public void click_delete_button_on_confirmation_pop_up_to_delete_address() {
        Assert.assertTrue(myAccountAddressPageSNCA.clickOnDeleteToConfirmAddressDeletion());
    }

    @Then("^Address having pincode \"([^\"]*)\" should get deleted$")
    public void validate_that_address_with_given_Pincode_is_deleted(String pincode) throws InterruptedException {
        Thread.sleep(1000);
        String expected, actual;
        Object obj = myAccountAddressPageSNCA.getAllSavedAddress();
        boolean flag = true;
        if (obj instanceof Integer) {
            Assert.assertTrue(flag,
                    "One or More Existing address are there instead of returning those address in list it returning empty list");
        } else {
            expected = pincode.toLowerCase();
            List<String> address = (List<String>) obj;
            for (int i = 0; i < address.size(); i++) {
                actual = address.get(i).toLowerCase();
                if (actual.contains(expected)) {
                    flag = false;
                    break;
                }
            }
            Assert.assertTrue(flag,
                    "Unable to delete the address having pincode-->" + pincode + " or may be multiple address are there having same pincode");
        }

    }

    @Then("^validate that against mandatory field \"([^\"]*)\" or \"([^\"]*)\" error message should be shown$")
    public void validate_message_against_the_mandatory_label(String value1,String value2)
    {
        String actualMessage;
        String expected;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            actualMessage=myAccountAddressPageSNCA.findWebElementAndFetchText(value1);

            if(value1.contains(" ")){
                value1=value1.replace(" ","");
            }
            String message="addNewAddress.error.message.for."+value1;
            expected=Props.getMessage(message);
        }
        else
            {
                actualMessage=myAccountAddressPageSNCA.findWebElementAndFetchText(value2);

                if(value2.contains(" ")){
                    value2=value2.replace(" ","");
                }
                String message="addNewAddress.error.message.for."+value2;
                expected=Props.getMessage(message);
            }

        Assert.assertEquals(actualMessage,expected);

    }

    @Then("^validate that for mandatory field \"([^\"]*)\" or \"([^\"]*)\" error message should be shown$")
    public void validate_message_for_the_mandatory_label(String value1,String value2)
    {
        String actualMessage;
        String expected;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            actualMessage=myAccountAddressPageSNCA.findWebElementAndFetchText(value1);

            if(value1.contains(" ")){
                value1=value1.replace(" ","");
            }
            String message="addNewAddress.error.message.for."+value1;
            expected=Props.getProp(message);
        }
        else
        {
            actualMessage=myAccountAddressPageSNCA.findWebElementAndFetchText(value2);

            if(value2.contains(" ")){
                value2=value2.replace(" ","");
            }
            String message="addNewAddress.error.message.for."+value2;
            expected=Props.getProp(message);
        }

        Assert.assertEquals(actualMessage,expected);

    }

    @When("^I click on Sign Out button$")
    public void i_click_on_logout_button() {
        Assert.assertTrue(myAccountPageSNCA.clickSignoutButton());
    }
    
    @Then("^I should be on Order and Returns page$")
    public void i_should_be_on_order_and_returns_page() throws Throwable {
    	Assert.assertTrue(myAccountOrdersPageSNCA.checkOrderPageFound(), "Order Page not found");
    }

    @And("^search for the order number having \"([^\"]*)\" in Orders & Returns$")
    public void validate_order_number_is_there_in_my_account(String orderId){
        boolean flag=false;
        String orderid="";
               try{
                   Properties prop=new Properties();
                   prop.load(new FileReader(propFilePath));
                   orderid=prop.getProperty("order.id");
               }catch (Exception e){
                   e.printStackTrace();
               }
        Assert.assertTrue(myAccountOrdersPageSNCA.getListOfOrderIds().contains(orderid),"Order Id-->>"+orderid+" not found into my account after placing order");

    }

  
    @Then("^I should be on Account Setting page$")
    public void i_should_be_on_account_setting_page() throws Throwable {
    	Assert.assertTrue(myAccountSettingPageSNCA.checkAccountSettingPageFound(), "Account Setting Page not found");
    }

    @Then("^I should be on Products and Warranties page$")
    public void i_should_be_on_products_and_warranties_page() throws Throwable {
        Assert.assertTrue(myAccountProductWarrentiesSNCA.checkProductWarrentiesPageFound(), "Product & Warrenties Page not found");
    }

    @Then("^I should be on Addresses Book page$")
    public void i_should_be_on_addresses_book_page() throws Throwable {
    	 Assert.assertTrue(myAccountAddressPageSNCA.checkAddressPageFound(), "My Address Book Page not found");
    }

    @Then("^I should see registered product warranty$")
    public void verifyProductWarrantyInfo() throws Throwable {
        Assert.assertTrue(myAccountProductWarrentiesSNCA.verifyProductWarrantyInfo(), "Product & Warrenties information not found");
    }

    @Then("^I uncheck same as shipping checkbox$")
    public void uncheckSameAsShippingCheckbox() throws Throwable {
        Assert.assertTrue(myAccountAddressPageSNCA.uncheckSameAsShippingCheckbox(), "Product & Warrenties information not found");
    }

}
