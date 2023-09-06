@Demo
Feature: DEMOPAGE: demo'ing the ability of the framework to interact with web pages

  Background: Ensure the expectedPage is loaded correctly
    #Given demo page is loaded

  Scenario: Test scraping for webElements
    Given I scrape homePage for all available Buttons
    Then I scrape homePage for all available links
    And I scrape homePage for all available lists
    And I scrape homePage for all available images
    Then I loop thur Navigation list items

  @tinyTest
  Scenario: Radio Button and Input box interaction
    Given I assert radio button isn't selected and then click on a radio button
    And assert the radio button is selected
    And I click populate the date input field and assert it's populated with expected value

  Scenario: interact with dropdown
    Given I assert the select Box is present
    And Get all values from selectDropDown and write contents to array
    And Get current value from dropdown
    And Select value from via manual test selection
    And select new value via array selection and assert correct

  Scenario: interact with table
    Given I am on the automation practice table page
    And I scrape homePage for all available tables
    And I get all the table headings
    And I get all the table rows
    And I get all the table columns
    And I interact with element within table

  Scenario: getTextFor method
    Given I use the getTextFor method expected Text is returned as well as name of By element

  Scenario: test the custom failure message
    Given I produce an error I get the expected output

  Scenario: get Cookies via Selenium
    Given I am on the website Selenium webDriver can extract cookies

  Scenario: getRestAssuredStatusCode
    Given I want Rest Assured to return status code of webpage
    Given I want to assert the status code of 200
    And test if else statement

