@regSNAPEUSuit
Feature: Agent is able to check the order History details

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
#    And Enter Last Name as "zendesk.customer.search.valid.lastName" and click search button
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials

  @SNAPSmoke
  Scenario: Check Order History details
    Then I should click on Order History Option
    Then I should click on the dispatched Order status with New Order type
    Then I should check the order status


