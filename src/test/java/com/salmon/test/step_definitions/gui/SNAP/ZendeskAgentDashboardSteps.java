package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.SNAP.ZendeskAgentDashboardPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Iterator;

public class ZendeskAgentDashboardSteps
{

    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public ZendeskAgentDashboardSteps()
    {
        zendeskAgentDashboardPage=new ZendeskAgentDashboardPage();
    }

    @And("^I should be able to Add a new Ticket$")
    public void addingNewTicket()
    {
        Assert.assertTrue(zendeskAgentDashboardPage.addTicket().equalsIgnoreCase("New Ticket"),
                "Unable to find text \"New Ticket\" after hovering on Add button and clicking on Ticket Option");
    }

    @When("^Click on App button to open info pannel and select channel as \"([^\"]*)\"$")
    public void openRightInfoPane(String channel)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.openSNAPCustomerSearchAndSelectChannelAs(Props.getProp(channel)),
                "Unable to set channel as "+channel);
    }

    @And("click on create new order button")
    public void createNewOrderButton(){
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnNewCustomerOrder());
    }

    @And("^Enter Last Name as \"([^\"]*)\" and click search button$")
    public void enterLastNameAndClickOnSearchButton(String name)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.enterLastNameAndClickSearch(Props.getProp(name)),
                "Unable to enter last name in the field and click on search");
    }

    @Then("^One or more tiles should have customer having last name \"([^\"]*)\"$")
    public void searchLastNameShowingOverTiles(String lastName)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.validateCustomerWithLastName(Props.getProp(lastName)),
                "None of tiles having last name "+Props.getProp(lastName)+" check if user is registered ?");
    }

    @And("^Enter Customer Id as \"([^\"]*)\" and click search button$")
    public void enterCustomerIdAndClickSearch(String id)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.enterCustomerNumberAndClickSearch(Props.getProp(id)),
                "Unable to enter customer number and search");
    }

    @Then("^I navigate to the SNEX page$")
    public void i_navigate_to_the_SNEX_page(){
        WebDriver driver;
        String parenWindow;
        boolean isNewTabOpen;
        if (WebDriverHelper.getWebDriver() != null) {
            driver = WebDriverHelper.getWebDriver();
            parenWindow = driver.getWindowHandle();
            String script = "window.open('about:blank','_blank');";
            ((JavascriptExecutor) driver).executeScript(script);
            Iterator<String> it=driver.getWindowHandles().iterator();
            while(it.hasNext()){
                String window=it.next();
                if(driver.switchTo().window(window).getTitle().equalsIgnoreCase("")){
                    driver.switchTo().window(window);
                    isNewTabOpen=true;
                    break;
                }
            }
        } else {
            driver = WebDriverHelper.getSpecificWebDriver(PermittedSiteMode.DESKTOP);
        }
        driver.get(Props.getProp("snex.url"));
    }
    @Then("^A Tile containing Customer Id \"([^\"]*)\" should be present$")
    public void validatingTileAndCustomerNumber(String number)
    {
        Assert.assertEquals(zendeskAgentDashboardPage.numberOfTilesHavingCustomerNumber(),1,
                "Showing more than one tile when searching with customer number "+Props.getProp(number));

        Assert.assertEquals(zendeskAgentDashboardPage.validateCustomerNumberIsShowingOnTileAs(),Props.getProp(number),
                "Customer number not matched ");
    }


    @And("^Enter Email Id as \"([^\"]*)\" and click on search button$")
    public void enterEmailIdandClickSearch(String email)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.enterEmailIdAndClickSearch(Props.getProp(email)),
                "Unable to enter the email id please check");
    }

    @Then("^One or more tiles should have customer having email id as \"([^\"]*)\"$")
    public void validateOneOrMoreTileConsistCustomerHavingEmailID(String email)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.getEmailIdShownInTiles(Props.getProp(email)),
                "Unable to find tile with the email id "+Props.getProp(email));
    }

    @Then("^Error message \"([^\"]*)\" should get display$")
    public void validateErrorMessage(String message)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.getErrorMessage().equalsIgnoreCase(message),
                "Unable to match error message or error message is not getting displayed");
    }

    @Then("^Click on the select button where email is \"([^\"]*)\"$")
    public void clickSelectButtonWhereEmailIdIs(String emailId)
    {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnSelectButtonWhereEmailIdIs(Props.getProp(emailId)));
    }
}
