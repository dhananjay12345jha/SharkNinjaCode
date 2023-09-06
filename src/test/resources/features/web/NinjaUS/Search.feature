@testAll
@regNinjaUS
@regNinjaUSSuit2
Feature: To validate Search Functionality

  Background: To open home page and select language
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @smokeNinjaUS
  Scenario: Verify Search with wildcard input
    When I open search bar and entered the keyword as "*" or "*"
    And I clicked on the search button
    Then page title should contains text with keyword "Search Result for '*'" or "Search Result for '*'"
    And search result page should display text contains keyword ""*"" or ""*""
    And validate that number of product tiles shown should be greater than zero

  @smokeNinjaUS
  Scenario: Verify Search based on valid input
    When I open search bar and entered the keyword as "lifters" or "lifters"
    And I clicked on the search button
    Then page title should contains text with keyword "Search Result for 'lifters'" or "Search Result for 'lifters'"
    And search result page should display text contains keyword ""lifters"" or ""lifters""
    And validate that number of product tiles shown should be greater than zero
    And validate that product tiles title should contains text "lifter"


  @smokeNinjaUS
  Scenario: Verify Search based on invalid input
    When I open search bar and entered the keyword as "dummy 123" or "dummy 123"
    And I clicked on the search button
    Then I got text on result page as "0 results for "dummy 123"" or "0 results for "dummy 123""
    And validate that number of product tiles shown should be equal to zero


#  @ValidSearchWithNoResult
#  Scenario: Verify Search based on valid input but no search result(appropriate messaging)
#    Given I navigate to the "Home" page
#    When I search for the product "search.validInput.with.NoResult"
#    Then I should get valid error message that no results found

