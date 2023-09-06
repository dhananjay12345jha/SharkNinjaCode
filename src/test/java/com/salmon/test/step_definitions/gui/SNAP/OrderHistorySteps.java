package com.salmon.test.step_definitions.gui.SNAP;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;
import org.testng.Assert;

public class OrderHistorySteps {
    private ZendeskLoginProcessPage zendeskLoginProcessPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public OrderHistorySteps(ZendeskLoginProcessPage zendeskLoginProcessPage, ZendeskAgentDashboardPage zendeskAgentDashboardPage) {
        this.zendeskLoginProcessPage = zendeskLoginProcessPage;
        this.zendeskAgentDashboardPage = zendeskAgentDashboardPage;
    }
    @Given("^I should click on Order$")
    public void i_should_click_on_Order() {
        // zendeskAgentDashboardPage.ColumnData();
        zendeskAgentDashboardPage.ClickOnOrder();
    }
    @Then("^I should check that Order history is enabled$")
    public void i_should_check_that_Order_history_is_enabled() {
        zendeskAgentDashboardPage.OrderHistory();
    }

    @And("^I should click on BackOrder$")
    public void iShouldClickOnBackOrder() throws InterruptedException {
        zendeskAgentDashboardPage.ClickOnBackOrder();
    }

    @And("^I should click on NewBackWebOrder$")
    public void iShouldClickOnNewBackWebOrder() throws InterruptedException {
        zendeskAgentDashboardPage.ClickOnNewBackWebOrder();
    }
    @Then("^I should assert the order$")
    public void i_should_assert_the_order() {
      zendeskAgentDashboardPage.AssertOrderTitle();
    }
    @Then("^I should fetch product link from search results$")
    public void i_should_fetch_product_link_from_search_results() {
zendeskAgentDashboardPage.AssertProductResults();
    }

}
