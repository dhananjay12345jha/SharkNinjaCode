@testAll
@regSharkUS
@regSharkUSSuit2
Feature: Verify Logout Functionality

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.email" and password as "login.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page

  @logoutSNUS
  Scenario: Validate Logout functionality
    When I click on Sign Out button
    Then I am logout