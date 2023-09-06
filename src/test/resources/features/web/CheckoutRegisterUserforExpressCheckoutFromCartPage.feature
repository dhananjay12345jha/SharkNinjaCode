@regSharkUK
@regNinjaUK
@regSharkDE
@regNinjaDE
@regSharkDESuite1
@regNinjaDESuite1
@regNinjaUKSuite1
@regSharkUKSuite1
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Verify Checkout/Order Placement with Register user for express checkout from cart page

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And click on accept email if visible
    Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    And I click on sign in link
    And click on accept cookies if visible
    And I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And click on accept email if visible
    When I click on the cart icon
    And verify whether the Cart is containing Products or Not
    When I search for the product "product.specificSKU"
    And click on accept email if visible
    And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
	#And I go to cart


  @PaypalBraintree
  Scenario: Place order as Guest user  with Payment method Braintree

    And I place an order using payment method Braintree PayPal for DE_UK_FR using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as guest user  with Payment method Braintree - PayPalCredit with promo code
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK

  Scenario: Place order as guest user with Payment method Braintree - Pay in 3  for DE
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout pay in 3
    And verify that order is placed successfully


  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as guest user with Payment method Braintree - PayPalCredit without promo code
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully






  @Klarna
    @excludeSharkDE
    @excludeNinjaDE
    @excludeNinjaFR
    @excludeSharkFR
    @testingKalrnaFOrUK
  Scenario Outline: Place order as Guest user  with Payment method Klarna express checkout for UK- without Promo
    Then I place an order using payment method Klarna express checkout for UK using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    Then The user is able to select following "<paymentMethod>"
    Then I select newsletter and term and condition checkbox
    Then I click on securely place order
    And verify that order is placed successfully
    Examples:
      | paymentMethod         |
      | Pay now               |
      | pay in 30 Days        |
     | Pay in 3 or Financing |

  @excludeSharkDE
    @excludeNinjaUK
    @excludeSharkUK
    @excludeNinjaDE
    @testingKalrnaForFr
  Scenario Outline: Place order as Guest user  with Payment method Klarna express checkout for FR without promo
    Then I place an order using payment method Klarna express checkout for UK using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    Then The user is able to select following "<paymentMethod>" for FR
    Then I select newsletter and term and condition checkbox
    Then I click on securely place order
    And verify that order is placed successfully
    Examples:
      | paymentMethod         |
      | Pay now               |
      | pay in 30 Days        |
      | Pay in 3 or Financing |