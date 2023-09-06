@regSharkUK
@regNinjaUK
@regSharkDE
@regNinjaDE
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regSharkDESuite1
@regNinjaDESuite1
@regSharkESSuite1
@regNinjaESSuite1
@regSharkITSuite1
@regNinjaITSuite1
@regNinjaUKSuite1
@regSharkUKSuite1
Feature: Verify Checkout/Order Placement Braintree method

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And click on accept email if visible
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
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    When I click on the cart icon
    And verify whether the Cart is containing Products or Not
    When I search for the product "product.specificSKU"
    And click on accept email if visible
    And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
	#And I go to cart
    And I go to checkout

  @visaBraintreeGuestCheckout
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Guest user with Payment method - VISA card
  Billing Address is same as Shipping Address
  Postal Address is chosen using postal API
    And I fill in the billing details
    And shipping address is same as billing address
#	And I choose to create an account
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @VisaBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - VISA card
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @3DSecureBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure without Promocode
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @3DSecureBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - 3D Secure with promocode
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With 3D secure Card using valid "brainTree.card.number.3DSecure" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully

  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal with promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
    And verify that order is placed successfully

  @PaypalBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal without Promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
    And verify that order is placed successfully

#only applicable for Shark DE and Ninja DE
  @PaypalPayLaterBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal PayLater with Promo
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal Paylater using valid "payPalPayLaterSandbox.email" and "payPalPayLaterSandbox.password"
    And verify that order is placed successfully

  @PaypalPayLaterBraintree
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal PayLater without promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal Paylater using valid "payPalPayLaterSandbox.email" and "payPalPayLaterSandbox.password"
    And verify that order is placed successfully
#only applicable for Shark UK and Ninja UK
#only applicable for Shark UK and Ninja UK

  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
#  @excludeNinjaUK
#  @excludeSharkUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPalCredit without Promocode
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    #And I place an order using payment method Braintree PayPal Credit using valid "payPalPayLaterSandbox.email" and "payPalPayLaterSandbox.password"
    And I place an order using payment method Braintree PayPal Credit using valid "payPalPayLaterSandbox.email" and "payPalPayLaterSandbox.password" for sharkNinja UK
    And verify that order is placed successfully


  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
#  @excludeNinjaUK
#  @excludeSharkUK
  Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPalCredit with promo code
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And I place an order using payment method Braintree PayPal Credit using valid "payPalPayLaterSandbox.email" and "payPalPayLaterSandbox.password" for sharkNinja UK
    And verify that order is placed successfully

    # As per Megha, we are commenting this feature due a button disabled i.e. "Pay In 3 or Financing" and Once this is enabled we will uncomment it
#  @Klarna
#  @excludeSharkDE
#  @excludeNinjaDE
#  Scenario: Place order as Registered user (login during checkout) with Payment method Klarna- without Promo
#    And I click on returning customer login option
#    And I enter valid "login.email" and "login.password"
#    Then verify successful login
#    Then validate phone number is successfully entered
#    When I select Klarna radio button
#    And I place an order using payment method Klarna using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
#   #When I select Pay Now Radio Button
#    And verify that order is placed successfully

  @Klarna
    @excludeNinjaDE
    @excludeSharkDE
    @excludeSharkFR
    @excludeNinjaFR
    @excludeSharkUK
    @excludeNinjaUK
    @excludeSharkIT
    @excludeNinjaIT
    @excludeSharkES
    @excludeNinjaES
  Scenario Outline: Place order as Registered user (login during checkout) with Payment method Klarna- without Promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    When I select Klarna radio button for DE
    Then The user is able to pay using "<paymentMethod>" and "Klarna.Phone.Number" and "Klarna.Otp.Number"
    #And I place an order using payment method Klarna for DE using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
   #When I select Pay Now Radio Button
    And verify that order is placed successfully
    Examples:
      | paymentMethod        |
      | Ratenzahlung         |
      | In 30 Tagen bezahlen |
      | Jetzt bezahlen       |

  @Klarna
    @excludeNinjaDE
    @excludeSharkDE
    @excludeSharkFR
    @excludeNinjaFR
    @excludeSharkUK
    @excludeNinjaUK
    @excludeSharkIT
    @excludeNinjaIT
    @excludeSharkES
    @excludeNinjaES
    @testingKalrnaFOrUK
  Scenario Outline: Place order as Registered user (login during checkout) with Payment method Klarna for UK- without Promo
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    When I select Klarna radio button
    And I place an order using payment method Klarna for UK using valid "Klarna.Phone.Number" and "Klarna.Otp.Number"
    Then The user is able to pay using following "<paymentMethod>"
    And verify that order is placed successfully
    Examples:
      | paymentMethod  |
      | Pay by Card    |
      | pay in 30 Days |

  Scenario: -Verify error message when user enter incorrect password while checkout login
    And I click on returning customer login option
    And I enter valid "login.email" and "password.incorrect"
    Then verify login error message

  @ValidateBrandUniformity
  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify user is not allowed to Proceed with Shark Order from Ninja Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the Ninja Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Shark orders cannot be placed on this website


  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify user is not allowed to Proceed with Ninja Order from Shark Website on Storefront
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
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Ninja orders cannot be placed on this website


  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
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
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Shark orders cannot be placed on this website

  @excludeNinjaDE
  @excludeSharkDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify user is not allowed to Proceed with NinjaDE Order from SharkDE Website on Storefront
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    Then validate phone number is successfully entered
    And  I navigate to the SharkDE Homepage
    When I click on sign in link
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
    When I click on mini cart header
    And  I click on View Order Button
    And  I go to checkout
    And I click on pay by card button
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then I should see message above that Ninja orders cannot be placed on this website




