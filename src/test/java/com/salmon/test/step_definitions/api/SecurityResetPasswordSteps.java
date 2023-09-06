package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.SecurityResetPassword;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class SecurityResetPasswordSteps
{

    private SecurityResetPassword securityResetPassword;

    public SecurityResetPasswordSteps()
    {
        securityResetPassword=new SecurityResetPassword();
    }

    @Given("^email id \"([^\"]*)\" has been set to the request body$")
    public void email_id_has_been_set_to_the_request_body(String email) {
        securityResetPassword.setEmailId(email);
    }

    @Then("^hit the request$")
    public void hit_the_request() {
        Assert.assertTrue(securityResetPassword.hitRequest(),"Unable to hit the request please check ");
    }

    @Then("^response code should be equal to (\\d+)$")
    public void response_code_should_be_equal_to(int code) {
        Assert.assertEquals(securityResetPassword.getResponseCode(),code,"Response code is getting mismatched and response message is-->"+securityResetPassword.getResponseMessage());
    }

    @Then("^message in response should be equal to \"([^\"]*)\"$")
    public void message_in_response_should_be_equal_to(String message) {
       Assert.assertTrue(securityResetPassword.getResponseMessage().contains(message),"Response body is not containing the message, response body is-->"+securityResetPassword.getResponseMessage());
    }

}
