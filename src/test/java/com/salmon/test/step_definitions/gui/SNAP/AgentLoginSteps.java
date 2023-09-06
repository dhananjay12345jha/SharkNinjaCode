package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.page_objects.gui.SNAP.AgentLogin;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class AgentLoginSteps {

    private AgentLogin agentLogin;

    public AgentLoginSteps() {
        this.agentLogin =new AgentLogin();

    }

//
//    @Given("^I should navigate to the Agent Login page$")
//    public void i_should_navigate_to_the_Agent_Login_page() {
//
//    }

    @When("^I submit valid Agent credentials$")
    public void i_submit_valid_Agent_credentials()
    {
        Assert.assertTrue(agentLogin.submitAgentLoginCredentials()!=null,"Unable to login as Agent");
    }
}
