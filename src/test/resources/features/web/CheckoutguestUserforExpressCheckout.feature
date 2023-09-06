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
Feature: Verify Checkout/Order Placement with Guest user for express checkout

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
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    When I search for the product "product.specificSKU"
    And click on accept email if visible
    And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
	#And I go to cart
    And I go to checkout


  @PaypalBraintree
  Scenario: Place order as Guest user  with Payment method Braintree - PayPal with promo for DE/UK/FR
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I place an order using payment method Braintree PayPal for DE_UK_FR using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    And verify that order is placed successfully


  @PaypalBraintree
  Scenario: Place order as Guest user  with Payment method Braintree - PayPal without promo for DE/UK/FR
    And I place an order using payment method Braintree PayPal for DE_UK_FR using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    And verify that order is placed successfully



  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as guest user  with Payment method Braintree - PayPalCredit with promo code
    When I click on Redeem here link on checkout page
   And I enter item discount coupon code "checkout.item.discount"
   And I click on apply button
   Then I should see message above order summary that promotional code has been applied successfully
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as guest user with Payment method Braintree - PayPalCredit without promo code
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as guest user  with Payment method Braintree - Pay in 3 with promo code for DE
    When I click on Redeem here link on checkout page
   And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout pay in 3
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as guest user  with Payment method Braintree - Pay in 3 without promo code for DE
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout pay in 3
    And verify that order is placed successfully


  @visaBraintreeGuestCheckout
  Scenario: Place order as Guest user with Payment method - VISA card
  Billing Address is same as Shipping Address
  Postal Address is chosen using postal API
    And I click on pay by card button
    And I fill in the billing details
    And shipping address is same as billing address
    #When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully



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

  @Klarna
    @excludeSharkDE
    @excludeNinjaDE
    @excludeNinjaFR
    @excludeSharkFR
    @testingKalrnaFOrUK
  Scenario Outline: Place order as Guest user  with Payment method Klarna express checkout for UK- with Promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
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
  Scenario Outline: Place order as Guest user  with Payment method Klarna express checkout for FR- without Promo
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

  @excludeSharkDE
    @excludeNinjaUK
    @excludeSharkUK
    @excludeNinjaDE
    @testingKalrnaForFr
  Scenario Outline: Place order as Guest user  with Payment method Klarna express checkout for FR- with Promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
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


