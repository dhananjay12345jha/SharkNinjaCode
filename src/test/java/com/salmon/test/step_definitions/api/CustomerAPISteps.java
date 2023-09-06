package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.CustomerApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class CustomerAPISteps
{
    private CustomerApi customerApi;
    private int customerNumber;

    public CustomerAPISteps(){
        customerApi=new CustomerApi();
    }

    @Given("^email id has been generated against which account will be created$")
    public void email_id_has_been_generated_against_which_account_will_be_created() {
        String text="";
        text=customerApi.createEMailId();
        Assert.assertTrue(!text.equals(""),"Unable to create Email ID please check ");
    }

    @Then("^hit the account creation api request$")
    public void hit_the_account_creation_api_request() {
        Assert.assertTrue(customerApi.createdIcmAccountWithoutPassword(),"Unable to hit api please check logs");
    }

    @Then("^in response it should return uri value as \"([^\"]*)\"$")
    public void in_response_it_should_return_uri_value_as(String expected) {
        Assert.assertEquals(customerApi.getUriValueFromTResponse(),expected,"URI values are not getting matched ");
    }
    @Then("^response code should be shown as (\\d+)$")
    public void validate_response_code(int code)
    {
        Assert.assertEquals(customerApi.getResponseCode(),code);
    }

    @And("^in response customer number should be there$")
    public void validate_in_response_customer_number_should_be_present()
    {
        customerNumber=customerApi.getCustomerNumberFromResponse();
        Assert.assertTrue(customerNumber>0);
    }

    @Then("^again hit the account creation api with same email id$")
    public void again_hit_the_account_creation_api_with_same_email_id() {
        Assert.assertTrue(customerApi.createdIcmAccountWithoutPassword(),"Unable to hit api please check logs");
    }

    @Then("^in response it should return \"([^\"]*)\"$")
    public void in_response_it_should_return(String expected) {
        Assert.assertTrue(customerApi.getResponseAsString().contains(expected),"Actual value is \""+customerApi.getResponseAsString()+"\" and expected was \""+expected+"\"");

    }

    @Then("^in response same customer number should be there$")
    public void again_hit_api_and_validate_customer_number_in_response()
    {
        Assert.assertEquals(customerApi.getCustomerNumberFromResponse(),customerNumber);
    }
}
