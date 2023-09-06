@testAll
@regNinjaUS
@regNinjaUSSuit2
Feature: Dashboard: Validate dashboard screen

  Background: Go To Login Page and enter the required Details
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I login with valid credentials
    Then I should be on My Account page

  @accountDashboard
  @smokeNinjaUS
  Scenario: Verify Account Dashboard links
    When I click on Orders&Returns
    Then I should be on Order and Returns page
    When I click on Addresses Book
    Then I should be on Addresses Book page
    When I click on Account Setting
    Then I should be on Account Setting page
    When I click on My ProductsWarranties
    Then I should be on Products and Warranties page
