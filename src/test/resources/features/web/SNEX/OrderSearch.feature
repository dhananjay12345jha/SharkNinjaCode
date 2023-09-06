@regSNEXUKSuit
@regSNEXEUSuit

Feature: Verify bulk order search with fraud, backorder and all order

  Background: To Open SNEX Application and Select Country And Comes To Order Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file

  Scenario: Verify Bulk order search - Fraud
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check

  Scenario: Verify Bulk order search - Back-Order
    Then I click on search Back-order
    Then Verify the order status of Back-Order

  Scenario: Verify Bulk order search - All
    Then I click on search Button
    Then Verify the order status of Orders

  Scenario: Verify order search - New Order
    Then I select order type as new order
    Then I click on search Button
    Then Verify the order type of Orders

  Scenario: Verify order search - Order Reason
    Then I select order type as new order
    Then I click on advance search
    And I select order reason
    Then I click on search Button
    Then Verify the order reason of order

  Scenario: Verify order search - Last Name and email id
    Then I click on advance search
    Then I Enter "last.name" and "email.id"
    Then I click on search Button
    Then Verify the order "last.name" and "email.id"

  Scenario: Verify order search - Order Number
    Then I click on advance search
    Then I enter the save order in "order.number"
    Then I click on search Button
    Then Verify the odrer in search result