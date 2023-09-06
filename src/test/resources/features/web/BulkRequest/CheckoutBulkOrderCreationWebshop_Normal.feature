
Feature: Verify Checkout/Order Placement Braintree method

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I search for the product "product.specificSKU"
    And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
#	And I go to cart
    And I go to checkout

  @PaypalBraintree
  Scenario Outline: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
    And verify that order is placed successfully "<ordercount>"
Examples:
    |ordercount|
    |1          |
    |2        |
    |3         |
    |4         |
    |5        |
    |6          |
    |7         |
    |8         |
    |9          |
    |10        |
    |11         |
    |12        |




