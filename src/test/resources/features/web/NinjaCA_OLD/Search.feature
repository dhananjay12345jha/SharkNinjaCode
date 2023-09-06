@testAll
@search
@smokeNinjaCAOLD
@regressionNinjaCAOLD

Feature: To validate Search Functionality

  Background: To open home page and select language
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible

  Scenario: Verify Search with wildcard input
    When I open search bar and entered the product as "*" or "*"
    And I clicked on the search button
    Then page title should contains text "Search Result for '*'" or "Résultat de recherche pour '*'"
    And language selected should be equal to "select.language.to.test"
    And search result page should display text contains "*" or "« * »"
    And validate that number of product tiles shown should be greater than zero

  Scenario: Verify Search based on valid input
    When I open search bar and entered the product as "coffee" or "ninja"
    And I clicked on the search button
    Then page title should contains text "Search Result for 'coffee'" or "Résultat de recherche pour 'ninja'"
    And language selected should be equal to "select.language.to.test"
    And search result page should display text contains "coffee" or "« ninja »"
    And validate that number of product tiles shown should be greater than zero
    And validate that product tiles title should contains text "ninja foodi pour"



  @InvalidSearch
  Scenario: Verify Search based on invalid input
    When I open search bar and entered the product as "dummy 123" or "dummy 123"
    And I clicked on the search button
    Then I got text on result page as "0 results for "dummy 123"" or "Résultats de recherche pour « dummy 123 » - 0 articles"
    And language selected should be equal to "select.language.to.test"
    And validate that number of product tiles shown should be equal to zero


#  @ValidSearchWithNoResult
#  Scenario: Verify Search based on valid input but no search result(appropriate messaging)
#    Given I navigate to the "Home" page
#    When I search for the product "search.validInput.with.NoResult"
#    Then I should get valid error message that no results found

