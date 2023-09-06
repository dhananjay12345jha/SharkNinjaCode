package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.SecurityReminder;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class SecurityReminderSteps
{

    private SecurityReminder securityReminder;

    public SecurityReminderSteps()
    {
        securityReminder=new SecurityReminder();
    }



    @When("^set email id as \"([^\"]*)\" and brand as \"([^\"]*)\"$")
    public void set_email_id_as_and_brand_as(String email, String brand) {
       securityReminder.setEmailId(email);
       securityReminder.setBrand(brand);
    }

    @Then("^hit the security reminder api$")
    public void hit_the_security_reminder_api() {
        Assert.assertTrue(securityReminder.hitRequest(),"Unable to hit the security reminder request ");
    }

    @Then("^response code should be (\\d+)$")
    public void response_code_should_be(int code) {
        Assert.assertEquals(securityReminder.getResponseCode(),code,"Response code is getting mismatched");
    }

    @And("^in response body uri should contains \"([^\"]*)\"$")
    public void in_response_body_uri_should_contains(String message) {
        Assert.assertTrue(securityReminder.getResponseBody().contains(message),
                "Response body is-->"+securityReminder.getResponseBody()+" which is not containing message-->"+message);

    }

    @And ("^in response body should contains message \"(.*?)\"$")
    public void in_response_body_should_contains_message(String message)
    {
        Assert.assertTrue(securityReminder.getResponseBody().contains(message),
                "Response body is-->"+securityReminder.getResponseBody()+" and message is-->"+message);
    }

}
