#@regSharkDE
#@regNinjaDE
#@regSharkDESuite1
#@regNinjaDESuite1

  # commenting this feature file as suggested by Dhananjay due to Austria features are in development in progress
#Feature: Verify Checkout/Order Placement with registered user having austria address
#
#  Background: Navigate to checkout page as Guest User
#    Given I navigate to the "Home" page
#    And click on accept cookies if visible
#    And click on accept email if visible
#    Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
#    Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
#    Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
#    Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
#    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
#    And I click on sign in link
#    And I should be on log in page
#    When I enter valid "login.email.austria" and "login.password.austria"
#    Then I am successfully logged in
#    When I click on the cart icon
#    And verify whether the Cart is containing Products or Not
#    When I search for the product "product.specificSKU"
#    And I select the product from search result page "product.specificSKU"
#    And I add the product to cart "product.specificSKU"
#	#And I go to cart
#    And I go to checkout
#
#
#  @VisaBraintree
#  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - VISA card
#    And I click on returning customer login option
#    And I enter valid "login.email.austria" and "login.password.austria"
#    Then verify successful login
#    And I click on pay by card button
#    Then validate phone number is successfully entered
#    When I select pay by card radio button
#    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    Then verify that order is placed successfully
#
#  @3DSecureBraintree
#  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure without Promocode
#    And I click on returning customer login option
#    And I enter valid "login.email.austria" and "login.password.austria"
#    Then verify successful login
#    And I click on pay by card button
#    Then validate phone number is successfully entered
#    When I select pay by card radio button
#    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    Then verify that order is placed successfully
#
#
#
#
#  @3DSecureBraintree
#  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure with promocode
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.order.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I click on returning customer login option
#    And I enter valid "login.email.austria" and "login.password.austria"
#    Then verify successful login
#    And I click on pay by card button
#    Then validate phone number is successfully entered
#    When I select pay by card radio button
#    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    Then verify that order is placed successfully
#
#
#  @PaypalBraintree
#  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal with promo
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.item.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I click on returning customer login option
#    And I enter valid "login.email.austria" and "login.password.austria"
#    Then verify successful login
#    Then validate phone number is successfully entered
#    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
#    And verify that order is placed successfully
#
#  @PaypalBraintree
#  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal without Promo
#    And I click on returning customer login option
#    And I enter valid "login.email.austria" and "login.password.austria"
#    Then verify successful login
#    Then validate phone number is successfully entered
#    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
#    And verify that order is placed successfully