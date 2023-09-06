package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.SecurityEmailConfirmation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class SecurityEmailConfirmationSteps
{
    private SecurityEmailConfirmation securityEmailConfirmation;

    public SecurityEmailConfirmationSteps()
    {
        securityEmailConfirmation=new SecurityEmailConfirmation();
    }

    @When("^set the email \"([^\"]*)\" in the request body$")
    public void set_the_email_in_the_request_body(String email)
    {
        securityEmailConfirmation.setEmailToForWhichRequestHit(email);
    }

    @Then("^request has been hit$")
    public void request_has_been_hit() {
        Assert.assertTrue(securityEmailConfirmation.hitRequest(),"Unable to hit request please check ");
    }

    @And("^response should be shown as (\\d+)$")
    public void validate_response(int code)
    {
        Assert.assertEquals(securityEmailConfirmation.getResponseCode(),code,"Response is mismatched");
    }

    @Then("^response should contains \"(.*?)\"$")
    public void response_should_contains(String message)
    {
        Assert.assertTrue(securityEmailConfirmation.getResponseBody().contains(message),message+"is not there in Response which is-->"+securityEmailConfirmation.getResponseBody());
    }

}
