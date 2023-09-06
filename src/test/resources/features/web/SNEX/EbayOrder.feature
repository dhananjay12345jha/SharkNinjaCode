@regSNEXUKSuit
Feature: Verify bulk order search with fraud, backorder and all order

  Background: To Open SNEX Application and Select Country And Comes To Order Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file
    And I select Ebay checkbox

  Scenario: Verify Ebay order search - Fraud
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check

  Scenario: Verify Ebay order search - Back-Order
    Then I click on search Back-order
    Then Verify the order status of Back-Order

  Scenario: Verify Ebay order search - All
    Then I click on search Button
    Then Verify the order status of Orders

  Scenario: Verify Ebay order search - New Order
    Then I select order type as new order
    Then I click on search Button
    Then Verify the order type of Orders

  Scenario: Verify Ebay order search - Order Reason
    Then I select order type as new order
    Then I click on advance search
    And I select order reason
    Then I click on search Button
    Then Verify the order reason of order

  Scenario: Verify order search - Last Name and email id
    Then I click on advance search
    Then I Enter "ebay.last.name" and "ebay.email.id"
    Then I click on search Button
    Then Verify the order "ebay.last.name" and "ebay.email.id"

  Scenario: Verify order search - Order Number
    Then I click on advance search
    Then I enter the save order in "ebay.order.number"
    Then I click on search Button
    Then Verify the odrer in search result

  Scenario: Verify order option button for back-order
    Then I click on search Back-order
    Then Verify the order status of Back-Order
    Then I click on first order
    Then Verify order option button should be enabled

  Scenario: Verify order option button for other then back-order
    Then I click on search Button
    Then I click on first order and order status should not be back-order
    Then Verify order option button should be disabled


