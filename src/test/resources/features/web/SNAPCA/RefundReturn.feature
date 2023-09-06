@regSNAPCASuit
Feature: Agent is able to replace the order for dispatched and New order

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
#    Then I should select channel from dropdown
#    Then I should click on Order search button
#    When I submit valid Agent credentials
#    Then I should select the Sales channel
#    And I should enter "zendesk.order.no" the order number
#    And I should click on the search button
#    And I should click on Order
#    And I should assert the order
#    Then I should check the order type
    Then I should click on Order History Option
    Then I should click on the dispatched Order status with New Order type
    Then I should check the order status
    Then I should click on Order options button

  Scenario: Agent is able to create refund return
    Then I should click on create refund return option
    Then I should click on continue button if cart is not empty
    Then I should select appropriate reason for refund return
    And I should enter the quantity no
    And I should click on Create button
    Then I should see return successfull dialog box