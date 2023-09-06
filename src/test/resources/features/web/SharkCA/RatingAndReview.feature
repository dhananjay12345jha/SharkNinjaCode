@testAll
@checkout
@regressionSharkCA
@regSharkCASuit1
Feature: Verify Rating And Review

  Background: Navigate to product List Page
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I open search bar and entered the product as "product3"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"

  Scenario: Verify rating and review of a product
    Then I should see the rating of a product at plp level "product3"
    When I click on product which is not out of stock from search list
    And I click on the Rate And Review Button
    Then I should see Text Rating Snapshot