@regSNAPEUSuit
Feature: Agent is able to Login

  @SNAPSmoke
  Scenario: Search order through order number
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    Given I should select channel from dropdown
    Then I should click on Order search button
   # Given I should navigate to the Agent Login page
    When I submit valid Agent credentials