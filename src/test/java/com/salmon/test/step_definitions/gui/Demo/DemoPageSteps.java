package com.salmon.test.step_definitions.gui.Demo;

import com.salmon.test.page_objects.gui.DemoPage.DemoPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DemoPageSteps {

    // Page in place as a demo page
    // Serves as documentation
    // working examples of interactions
    // DEMO PAGE = http://toolsqa.com/automation-practice-form/

    private DemoPage demoPage;

    public DemoPageSteps(DemoPage demoPage) throws InterruptedException {
        this.demoPage = demoPage;
        demoPage.visit(demoPage.url);
        byPassAuthenticaion();
        isPageLoaded();
    }

    public void isPageLoaded(){
        System.out.println("Running page load process");
        demoPage.visit(demoPage.url);
        demoPage.waitForPage();
        System.out.println("Title : " + demoPage.getWebDriver().getTitle());
        if (demoPage.getWebDriver().getTitle().equalsIgnoreCase(demoPage.expectedTitle)){
            System.out.println("Page is loaded");
            demoPage.isLoaded = true;
        }
        assertTrue(demoPage.isLoaded);
    }

    public void byPassAuthenticaion() throws InterruptedException {
        if (demoPage.getCurrentPageTitle().equalsIgnoreCase("Welcome To Zscaler Directory Authentication")){
            System.out.println("We are being authenticated");
            demoPage.highlightElement(demoPage.authUnInput,0);
            demoPage.waitClearAndEnterText(demoPage.authUnInput,"msmith@salmon.com");
            demoPage.clickByElement(demoPage.submitButton);
            demoPage.waitClearAndEnterText(demoPage.authPwInput,"letme1nSalmon7");
            demoPage.clickByElement(demoPage.submitButton);
        }
    }

    @Then("^wait for page to load$")
    public void waitForPageToLoad() throws Throwable {
        demoPage.waitForPage();
    }

    @Then("^I scrape homePage for all available Buttons$")
    public void iScrapeHomePageForAllAvailableButtons() throws Throwable {
        demoPage.getAllButtons();
    }

    @Then("^I scrape homePage for all available links")
    public void iScrapeHomePageForAllAvailableLinks() throws Throwable {
        demoPage.getAllLinks();
    }

    @Then("^I loop thur Navigation list items$")
    public void iLoopThurNavigationListItems() throws Throwable {
        demoPage.isElementPresentByby(demoPage.navigationList);
        for (WebElement link : demoPage.visibilityOfAllElementsLocatedBy(demoPage.navigationList)){
            System.out.println("LINK : " + link.getText());
        }
    }

    @And("^I scrape homePage for all available lists$")
    public void iScrapeHomePageForAllAvailableLists() throws Throwable {
        demoPage.getAllLists();}

    @And("^I scrape homePage for all available images$")
    public void iScrapeHomePageForAllAvailableImages() throws Throwable {
        demoPage.getAllImages();
    }

    @Given("^I assert radio button isn't selected and then click on a radio button$")
    public void clickOnRadioButton() throws Throwable {
        assertFalse("Radio button shouldn't be selected",demoPage.isSelected(demoPage.maleRadio));
        demoPage.clickByElement(demoPage.maleRadio);
    }

    @And("^I click populate the date input field and assert it's populated with expected value$")
    public void iClickPopulateTheDateInputField() throws Throwable {
        demoPage.waitClearAndEnterText(demoPage.inputField,"Testing Testing");
        demoPage.assertTrueWithCustomError(demoPage.getValue(demoPage.inputField),"Testing Testing");
    }

    @And("^assert the radio button is selected$")
    public void assertTheRadioButtonIsSelected() throws Throwable {
        assertTrue(demoPage.getValue(demoPage.maleRadio).equals("Male"));
        assertTrue("Radio button isn't selected",demoPage.isSelected(demoPage.maleRadio));
    }

    @Given("^I assert the select Box is present$")
    public void iAssertTheSelectBoxIsPresent() throws Throwable {
        demoPage.highlightElement(demoPage.firstNameInputBox,3);
        assertTrue(demoPage.isElementPresentByby(demoPage.continentsDropDown));
    }

    List<String> optionsArray;

    @And("^Get all values from selectDropDown and write contents to array$")
    public void getAllValuesFromSelectDropDown() throws Throwable {
        optionsArray = demoPage.getAllSelectOptions(demoPage.continentsDropDown);
        System.out.println("Options Array : " + optionsArray.size());
    }

    @And("^Get current value from dropdown$")
    public void getCurrentValueFromDropdown() throws Throwable {
        System.out.println("Select Dropdown value : "+demoPage.getValue(demoPage.continentsDropDown));
    }

    @And("^select new value via array selection and assert correct$")
    public void selectNewValueViaArraySelection() throws Throwable {
        demoPage.selectValueFromDropDownByby(optionsArray.get(4),demoPage.continentsDropDown);
        demoPage.assertTrueWithCustomError(demoPage.getValue(demoPage.continentsDropDown),"South America");
    }

    @And("^Select value from via manual test selection$")
    public void selectValueFromViaManualTestSelection() throws Throwable {
        demoPage.selectValueFromDropDownByby("Africa",demoPage.continentsDropDown);
    }

    @Given("^I am on the automation practice table page$")
    public void iAmOnTheAutomationPracticeTablePage() throws Throwable {
        demoPage.visit("http://toolsqa.com/automation-practice-table/");
    }

    @And("^I scrape homePage for all available tables$")
    public void iScrapeHomePageForAllAvailableTables() throws Throwable {
        demoPage.getAllTables();
    }


    @And("^I get all the table headings$")
    public void iGetAllTheTableHeadings() throws Throwable {
       // List<WebElement> demoTable = demoPage.getTableHeaders(demoPage.demoPracticeTable);
        for (WebElement title : demoPage.getTableHeaders(demoPage.demoPracticeTable)){
            System.out.println("headers : " + title.getText());
        }
    }

    @And("^I get all the table rows$")
    public void iGetAllTheTableRows() throws Throwable {
        for (WebElement row : demoPage.getTableRows(demoPage.demoPracticeTable)){
            System.out.println("ROWS : " + row.getText());
        }
    }

    @And("^I get all the table columns$")
    public void iGetAllTheTableColumns() throws Throwable {
        for (WebElement col : demoPage.getTableCols(demoPage.demoPracticeTable)){
            System.out.println("COLS : " + col.getText());
        }
    }

    @And("^I interact with element within table$")
    public void iInteractWithElementWithinTable() throws Throwable {
        for (WebElement col : demoPage.getTableCols(demoPage.demoPracticeTable)){
            System.out.println("COLS : " + col.getText());
            if (col.getText().equals("details")){
                System.out.println("HREF VALUE : " + col.findElement(By.linkText("details")).getAttribute("href"));
                col.findElement(By.linkText("details")).click();
            }
        }
    }

    @Given("^I use the getTextFor method expected Text is returned as well as name of By element$")
    public void iUseTheGetTextForMethodExpectedTextIsReturnedAsWellAsNameOfByElement() throws Throwable {
        System.out.println("Testing get text for method : " + demoPage.getTextFor(demoPage.formTitleHeading));
        demoPage.assertTrueWithCustomError("Partial Link Test",demoPage.getTextFor(demoPage.formTitleHeading));
    }

    @Given("^I produce an error I get the expected output$")
    public void iProduceAnErrorIGetTheExpectedOutput() throws Throwable {
        demoPage.assertTrueWithCustomError("Partial Link Mistake",demoPage.getTextFor(demoPage.formTitleHeading));
    }

    @Given("^I am on the website Selenium webDriver can extract cookies$")
    public void iAmOnTheWebsiteSeleniumWebDriverCanExtractCookies() throws Throwable {
        System.out.println("Total number of cookies : " + demoPage.getCookiesViaSelenium().size());
        for (org.openqa.selenium.Cookie cookie : demoPage.getCookiesViaSelenium()){
            System.out.println("Cookie Name: " + cookie.getName());
            System.out.println("Cookie value: "+ cookie.getValue() );
        }
    }

    @Given("^I want Rest Assured to return status code of webpage$")
    public void iWantRestAssuredToReturnStatusCodeOfWebpage() throws Throwable {
        System.out.println("Getting Stauts code of page via Rest Assured : " + demoPage.restAssuredReturnStatusCode());
    }

    @Given("^I want to assert the status code of (\\d+)$")
    public void iWantToAssertTheStatusCodeOf(int expectedStatusCode) throws Throwable {
        demoPage.restAssuredAssertStatusCode(expectedStatusCode);
    }

    @And("^test if else statement$")
    public void testIfElseStatement() {
        Integer a = 1;
        Integer b = 2;
        String max;
        String first = "First message";
        String second = "Second message";
        max = (a>b) ? first : second;
        System.out.println("Max : " + max);
    }

/*
    @Then("^is page loaded with title of \"(.*?)\"$")
    public void isPageLoadedWithTitleOf(String pageTitle) throws Throwable {
        assertTrue(demoPage.isPageLoadedWithPageTitleOf(pageTitle));
    }

    @And("^I scrape homePage for all available links$")
    public void iScrapeHomePageForAllAvailableLinks() throws Throwable {
        demoPage.getAllLinks();
    }

    @And("^I scrape homePage for all available lists$")
    public void iScrapeHomePageForAllAvailableLists() throws Throwable {
        demoPage.getAllLists();
    }

    @And("^I scrape homePage for all available images$")
    public void iScrapeHomePageForAllAvailableImages() throws Throwable {
        demoPage.getAllImages();
    }*/



}