package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import com.salmon.test.page_objects.gui.SNAP.ZendeskLoginProcessPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class ZendeskLoginProcessSteps {

	private ZendeskLoginProcessPage zendeskLoginProcessPage;
	private ZendeskAgentDashboardPage zendeskAgentDashboardPage;
	
	public ZendeskLoginProcessSteps() {
		this.zendeskLoginProcessPage = new ZendeskLoginProcessPage();
		this.zendeskAgentDashboardPage =new  ZendeskAgentDashboardPage();
	}
		
	@When("^I submit valid zendesk credentials$")
	public void submitValidZendeskLoginCredentials() throws InterruptedException	{
		zendeskLoginProcessPage.submitZendeskLoginCredentials();	
		Thread.sleep(5000);
	}
	
	@Then("^I should be on Zendesk Agent Dashboard$")
	public void verifyZendeskAgentDashboardPage() throws InterruptedException {
		//Thread.sleep(3000);

		Assert.assertTrue(zendeskAgentDashboardPage.checkPageUrlToBe(Props.getProp("zendesk.agent.dashboard.url")));

		//Assert.assertTrue(zendeskAgentDashboardPage.getCurrentUrl().contains(Props.getProp("zendesk.agent.dashboard.url")));

//		Assert.assertTrue(zendeskAgentDashboardPage.checkDashboardButtonIsDisplayed());
//		Assert.assertTrue(zendeskAgentDashboardPage.isElementPresent(zendeskAgentDashboardPage.getAddText()));

		Assert.assertTrue(zendeskAgentDashboardPage.getAddText().contains("Add"));
	}
	
}
