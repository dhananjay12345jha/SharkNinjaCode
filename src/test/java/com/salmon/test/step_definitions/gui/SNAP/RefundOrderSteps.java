package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class RefundOrderSteps {
    private ZendeskLoginProcessPage zendeskLoginProcessPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public RefundOrderSteps(ZendeskLoginProcessPage zendeskLoginProcessPage, ZendeskAgentDashboardPage zendeskAgentDashboardPage) {
        this.zendeskLoginProcessPage = zendeskLoginProcessPage;
        this.zendeskAgentDashboardPage = zendeskAgentDashboardPage;
    }

    @And("I should click on create refund return option")
    public void i_should_click_on_create_refund_return_option(){
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnCreateRefundReturnOption());
    }

    @Then("^I should select appropriate reason$")
    public void i_should_select_a_valid_failure_reason() {
        Assert.assertTrue(zendeskAgentDashboardPage.selectProperFaultReason());
    }

    @Then("^I should select appropriate reason for refund return$")
    public void i_should_select_a_valid_failure_reason_for_refund_return() {
        Assert.assertTrue(zendeskAgentDashboardPage.selectRefundReturnReason());
    }

    @And("I should click on create express replacement")
    public void i_should_click_on_create_express_replacement_option(){
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnExpressReplacementOption());
    }


}
