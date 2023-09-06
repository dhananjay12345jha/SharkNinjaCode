@regSNAPSuit
Feature: Agent is able to search order through order number

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    Given I should select channel from dropdown
    Then I should click on Order search button
    When I submit valid Agent credentials
    Then I should select the Sales channel

@SNAPSmoke
  Scenario: Search order through order number
    And I should enter "zendesk.order.no" the order number
    And I should click on the search button
    Then I should verify the order search number

#  Below scenarios steps not implemented as of now
  Scenario: Search order through phone number

    And I should enter the phone number
    And I should click on the search button
    And I should click on view orders link
    And I should click on first order id
    Then I should verify the phone number


  Scenario: Search order through email id

    And I should enter the email id
    And I should click on the search button
    And I should click on view orders link
    And I should click on first order id
    Then I should verify the email id


