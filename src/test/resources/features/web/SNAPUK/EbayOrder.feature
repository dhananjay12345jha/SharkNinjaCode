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
    Then I should select the Sales channel for ebay

  @SNAPSmoke
  Scenario: Search order through order number
    And I should enter "ebay.order.no" the order number
    And I should click on the search button
    Then I should verify the order search number

  Scenario: Search order through phone number
    And I should enter the "ebay.phone.number"
    And I should click on the search button
    And I should click on view orders link
    And I should click on first order id
    Then I should verify the phone number

  Scenario: Search order through email id
    And I should enter the ebay "email.id"
    And I should click on the search button
    And I should click on view orders link
    And I should click on first order id
    Then I should verify the email id

Scenario: Verify Order option button for ebay order
  And I should enter "ebay.order.no" the order number
  And I should click on the search button
  And I should click on first order id
  And I should verify order option button for ebay order

