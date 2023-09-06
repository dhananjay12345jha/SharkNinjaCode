package com.salmon.test.step_definitions.gui.SNEX;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNEX.DashboardPage;
import cucumber.api.java.en.*;
import org.testng.Assert;

public class DashboardPageSteps {

    private DashboardPage dashboardPage;
    public DashboardPageSteps(DashboardPage dashboardPage){
        this.dashboardPage=dashboardPage;
    }

    @Then("^I should be on SNEX dashboard page$")
    public void i_should_be_on_SNEX_dashboard_Page(){
        Assert.assertEquals(dashboardPage.getSnexTitle(),"SNEX");
    }

    @And("^I select SNEX country from dropdown as defined in prop file$")
    public void select_country_from_dropdown(){
        Assert.assertTrue(dashboardPage.setDropDown(Props.getProp("select.snex.channel")),"Unable to set the dropdown with the country specified in props file");
    }

    @And("^I select Ebay checkbox$")
    public void iSelectEbayCheckbox() {
        Assert.assertTrue(dashboardPage.selectCheckbox(), "Unable to select Checkbox ");
    }
}
