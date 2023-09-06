
@pwasmoke
Feature: Verify Checkout/Order Placement

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And I click on search icon
    And I search for product "search.validInput"
    And I select product from search result page "product.specificSKU"
    And I add product to the cart
    And I go to the checkout

 @pwaVisaBraintree
  Scenario: Place order as Guest user with Payment method Braintree - VISA card
    And I fill in billing details
    When I place an order using Braintree payment method Pay via Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
    Then Verify that order is placed successfully