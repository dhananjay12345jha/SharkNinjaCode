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
Feature: Verify Checkout/Order Placement with registered user for express checkout

  Background: Navigate to checkout page as Registered user
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And click on accept email if visible
    And click on accept cookies if visible
    Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
    Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL750EU and the quantity is 200
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
    And I go to checkout


  @PaypalBraintree
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal with promo for DE/UK/FR
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And I place an order using payment method Braintree PayPal for DE_UK_FR using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    And verify that order is placed successfully


  @PaypalBraintree
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal without promo for DE/UK/FR
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And I place an order using payment method Braintree PayPal for DE_UK_FR using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    And verify that order is placed successfully



  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPalCredit with promo code
    When I click on Redeem here link on checkout page
   And I enter item discount coupon code "checkout.item.discount"
   And I click on apply button
   Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPalCredit without promo code
   And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
   Then verify successful login
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout paypal credit
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - Pay in 3 with promo code for DE
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout pay in 3
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - Pay in 3 without promo code for DE
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout pay in 3
    And verify that order is placed successfully



  @VisaBraintree
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - VISA card without promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    #And I click on pay by card button
    Then validate phone number is successfully entered
    #When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @VisaBraintree

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - VISA card with promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    #And I click on pay by card button
    Then validate phone number is successfully entered
    #When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @3DSecureBraintree

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure without Promocode
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    #And I click on pay by card button
    Then validate phone number is successfully entered
    #When I select pay by card radio button
    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @3DSecureBraintree

  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure with promocode
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    #And I click on pay by card button
    Then validate phone number is successfully entered
    #When I select pay by card radio button
    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully


  @Klarna
    @excludeSharkDE
    @excludeNinjaDE
    @excludeNinjaFR
    @excludeSharkFR
    @testingKalrnaFOrUK
  Scenario Outline: Place order as Registered user  with Payment method Klarna express checkout for UK- without Promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
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
  Scenario Outline: Place order as Registered user  with Payment method Klarna express checkout for UK- with Promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
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


  #--------------------------------commenting klarna de feature --------------#
  #Scenario: Place order as Registered user  with Payment method Klarna express checkout for DE- without Promo
    #Then I place an order using payment method Klarna express checkout for DE using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    #Then I select newsletter and term and condition checkbox
    #Then I click on securely place order
    #And verify that order is placed successfully

    @excludeSharkDE
    @excludeNinjaUK
    @excludeSharkUK
    @excludeNinjaDE
    @testingKalrnaForFr
  Scenario Outline: Place order as Registered user  with Payment method Klarna express checkout for FR- without Promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
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
  Scenario Outline: Place order as Registered user  with Payment method Klarna express checkout for FR- with Promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
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

  @ValidateBrandUniformity @paybycardcrossproduct
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkDE
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with SharkUK Order from NinjaUk Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And  I navigate to the Ninja Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Shark orders cannot be placed on this website


  @excludeSharkDE @paybycardcrossproduct
  @excludeNinjaDE
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with NinjaUK Order from SharkUK Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Shark Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Ninja orders cannot be placed on this website

  @excludeNinjaDE @paybycardcrossproduct
  @excludeNinjaUK
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkDE
  Scenario: Verify user is not allowed to Proceed with SharkDE Order from NinjaDE Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the NinjaDE Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    And I click on pay by card button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Shark orders cannot be placed on this website
  @excludeSharkDE @paybycardcrossproduct
  @excludeNinjaUK
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with NinjaDE Order from SharkDE Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Shark Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Ninja orders cannot be placed on this website


  @ValidateBrandUniformity @paybypaypal
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkDE
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with SharkUK Order from NinjaUk Website on Storefront with paypal payment
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And  I navigate to the Ninja Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    Then I should see message above that Shark orders cannot be placed on this website


  @ValidateBrandUniformity @klarna
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkDE
  @excludeSharkIT
  @excludeNinjaIT
  Scenario Outline: Verify user is not allowed to Proceed with SharkUK Order from NinjaUk Website on Storefront with klarna payment
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    And  I navigate to the Ninja Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    Then I place an order using payment method Klarna express checkout for UK using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    Then The user is able to select following "<paymentMethod>"
    Then I select newsletter and term and condition checkbox
    Then I click on securely place order
    Then I should see message above that Shark orders cannot be placed on this website
    Examples:
      | paymentMethod         |
      | Pay now               |


  @excludeSharkDE @paybypaypal
  @excludeNinjaDE
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with NinjaUK Order from SharkUK Website on Storefront from paypal payment
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Shark Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    Then I should see message above that Ninja orders cannot be placed on this website


  @excludeSharkDE @paybyklarna
  @excludeNinjaDE
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  Scenario Outline: Verify user is not allowed to Proceed with NinjaUK Order from SharkUK Website on Storefront from klarna payment
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Shark Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    Then I place an order using payment method Klarna express checkout for UK using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    Then The user is able to select following "<paymentMethod>"
    Then I select newsletter and term and condition checkbox
    Then I click on securely place order
    Then I should see message above that Ninja orders cannot be placed on this website
    Examples:
      | paymentMethod         |
      | Pay now               |



  @excludeSharkDE @paybypaypal
  @excludeNinjaUK
  @excludeSharkUK
  @excludeSharkIT
  @excludeNinjaIT
  Scenario: Verify user is not allowed to Proceed with NinjaDE Order from SharkDE Website on Storefront for paypal payment
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Shark Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password" for express checkout
    Then I should see message above that Ninja orders cannot be placed on this website