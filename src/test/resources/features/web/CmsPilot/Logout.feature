@regSharkDECmsPilotSuite1
Feature: Verify Logout Functionality

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I click on sign in link
    Then I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And click on accept email if visible
    And I should be on My Account Overview page

@Logout
@Regression
  Scenario: Validate Logout functionality
    When I click on logout button
    Then I am successfully logout