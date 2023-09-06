@regSNAPCASuit
Feature: Validate Replacement with RMA# for New Customer for Shark/Ninja CA -Order History

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    Then I should select channel from dropdown
    Then I should click on Order search button
    When I submit valid Agent credentials
    Then I should select the shark as a channel



  Scenario: Normal Replace Unit
    And I should enter "zendesk.order.no" the order number
    And I should click on the search button
    And I should click on Order
    And I should assert the order
    Then I should check the order type
    Then I should click on Order History Option
    Then I should click on the dispatched Order status with New Order type
    Then I should check the order status
    Then I should click on Order options button
    Then I should click on replace unit option
    Then I should click on continue button if cart is not empty
    And I should assert the return process option
    And I should enter the quantity no
    Then I should select a valid failure reason
    And I should click on Create button
    Then I should see return successfull dialog box
    Then I should select continue button
    Then I should assert "normal.response" and "zendesk.order.no" on cart page

  Scenario: Rapid Response
    And I should enter "normal.response.order.no" the order number
    And I should click on the search button
    And I should click on Order
    Then I should check the order type
    Then I should check the order status
    Then I should click on Order options button
    Then I should click on replace unit option
    Then I should click on continue button if cart is not empty
    And I should assert the return process option
    And I should enter the quantity no
#    Then I should select a valid failure reason
    And I should select "rapid.response.reason"
    And I should click on Create button
    Then I should see return successfull dialog box
    Then I should select continue button
    Then I should assert "rapid.response" and "normal.response.order.no" on cart page


  Scenario: Refurbishment Response
    And I should enter "normal.response.order.no" the order number
    And I should click on the search button
    And I should click on Order
    Then I should check the order type
    Then I should check the order status
    Then I should click on Order options button
    Then I should click on replace unit option
    Then I should click on continue button if cart is not empty
    And I should assert the return process option
    And I should enter the quantity no
    Then I should select a valid failure reason
#    And I should select "refurbishment.response.reason"
    And I should click on Create button
    Then I should see return successfull dialog box
    Then I should select continue button
    Then I should assert "refurbishment.response" and "normal.response.order.no" on cart page