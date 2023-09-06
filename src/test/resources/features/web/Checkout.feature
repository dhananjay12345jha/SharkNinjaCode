@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regNinjaDE
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeSharkIT
@smokeNinjaIT
@smokeSharkES
@smokeNinjaES
@smokeNinjaDE
@regSharkDESuite1
@regNinjaDESuite1
@regSharkITSuite1
@regNinjaITSuite1
@regSharkESSuite1
@regNinjaESSuite1
@regNinjaUKSuite1
@regSharkUKSuite1
Feature: Verify Checkout/Order Placement

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
    Given User is setting the stock quantity for EU with the Sku as WV270EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as CV100EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    When I search for the product "product.specificSKU"
    And click on accept email if visible
	And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
#	And I go to cart
    And I go to checkout


  @createAccount
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkIT
  @excludeNinjaIT
  @excludeNinjaUK
  @excludeSharkES
  @excludeNinjaES

  Scenario: Create Account During Checkout
    And I fill in the billing details
    And I choose to create an account
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully


  @AutoPopulationofAddresses
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify auto population of address with valid postal code for both billing and shipping addresses
    When I fill the valid postal code for billing
    Then Billing addresses are auto populated
    When I click on different shipping address
    And I fill the valid postal code for shipping
    Then Shipping addresses are auto populated

  @AddAdressesManually
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify that user is able to add address manually for both billing and shipping
    When I click on enter address manually for billing
    Then I am able to fill the billing address manually
    When I click on different shipping address
    Then Shipping address form is displayed
    When I click on enter address manually for shipping
    Then I am able to fill the shipping address manually


  @ShippingandBillingAdressesDifferent
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify user is able to place an order with option: Shipping Address different from Billing Address
    And I fill in the billing details
    And I click on different shipping address
    And I fill in the shipping details
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully

  @excludeSharkIT
  @excludeNinjaIT
  @mandatoryfieldsvalidation
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify mandatory fields error messages and place order button is disabled in case mandatory information is missing on checkout page
    When I select pay by card radio button
    #When I click on pay by card braintree method
    And  I click on place order securely button
    Then I should get error message against all mandatory fields
    And place order securely button should be disabled

  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  @mandatoryfieldsvalidation
  Scenario: Verify mandatory fields error messages and place order button is disabled in case mandatory information is missing on checkout page for IT
    #And I click on pay by card button
    When I click on pay by card braintree method
    And  I click on place order securely button
    Then I should get error message against all mandatory fields
    And place order securely button should be disabled


  @mandatoryZipCodeFieldvalidation
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify Zipcode is mandatory on checkout page and place order button is disabled in case Zipcode
    When I select pay by card radio button
    And I fill in the billing details without Zipcode
    #And I click on pay by card braintree method
    And I click on termsAndCondition checkbox
    When I click on place order securely button
    And place order securely button should be disabled

  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify Zipcode is mandatory on checkout page and place order button is disabled in case Zipcode for IT and ES
#    And I click on pay by card button
    #And I fill in the billing details without Zipcode
    And I fill in the billings details without zipcode
    And I click on pay by card braintree method
    And I click on termsAndCondition checkbox
    When I click on place order securely button
    And place order securely button should be disabled

  @excludeSharkIT
  @excludeNinjaIT
  @BraintreeInvalidCardDetails
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify user is not able to checkout using invalid payment Braintree - invalid card details
    And I click on pay by card button
    And I fill in the billing details
    #And I click on pay by card braintree method
    And I click on termsAndCondition checkbox
    When I click on place order securely button
    Then I should get insert correct credit card data error message
    When I enter invalid card number "brainTree.invalidcard.number"
    When  I place an order using Braintree payment method Pay With Card using invalid "brainTree.invalidcard.number" and "brainTree.invalid.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify error message for invalid card details

  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkFR
  @excludeNinjaFR
  @BraintreeInvalidCardDetails
  Scenario: Verify user is not able to checkout using invalid payment Braintree - invalid card details for IT and ES
#    And I click on pay by card button
    And I fill in the billing details
    And I click on pay by card braintree method
    And I click on termsAndCondition checkbox
    When I click on place order securely button
    Then I should get insert correct credit card data error message
    When I enter invalid card number "brainTree.invalidcard.number"
    When  I place an order using Braintree payment method Pay With Card using invalid "brainTree.invalidcard.number" and "brainTree.invalid.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify error message for invalid card details

#  @invalidPayPalCredentialsBraintree
#  Scenario: Verify user is not able to checkout using invalid payment Braintree - invalid PayPal credentials
#    And I click on returning customer login option
#    And I enter valid "login.email" and "login.password"
#    Then verify successful login
#    When I place an order using payment method Braintree PayPal using invalid "payPalSandbox.email.invalid" and "payPalSandbox.password.invalid"
#    Then I should get valid error message


#    Need to revisit this test case as incorrect password functionality is changed
  @incorrectPasswordduringcheckout
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkIT
  @excludeNinjaIT
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify that create account feature with incorrect password during checkout
    And I click on pay by card button
    And I fill in valid registered email and others billing details
    And I choose to create an account with incorrect password
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify valid error message for create account feature with weak password during checkout
    And place order securely button should be disabled


  @excludeSharkIT
  @excludeNinjaIT
  @ItemdiscountonCheckoutPage
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify applied coupon with Item discount on checkout page
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    #And I click on pay by card button
    And I fill in the billing details
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully

  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify applied coupon with Item discount on checkout page for IT & ES
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.item.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    #When I select pay by card radio button
    And I fill in the billing details
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully

  @excludeSharkIT
  @excludeNinjaIT
  @OrderdiscountonCheckoutPage
  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify applied coupon with Order discount on checkout page and pay by card
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    And I click on pay by card button
    And I fill in the billing details
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully

  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  Scenario: Verify applied coupon with Order discount on checkout page and pay by card for IT & ES
    When I click on Redeem here link on checkout page
    And I enter item discount coupon code "checkout.order.discount"
    And I click on apply button
    Then I should see message above order summary that promotional code has been applied successfully
    When I select pay by card radio button
    And I fill in the billing details
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully


#Team is checking for valid promocode
#  @OrderBluelightdiscountonCheckoutPage
#  Scenario: Verify applied coupon with blue light discount on checkout page
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.bluelight.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I fill in the billing details
#    When I select pay by card radio button
#    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    And verify that order is placed successfully



	#@AmericanExpressStripe
	#Scenario: Place order as Guest user with Payment method Stripe - American Express card
#	Billing Address is same as Shipping Address
	#Postal Address is chosen using postal API
	#	 And I fill in the billing details
		# Then I place an order using payment method Pay With Card using valid "stripe.card.number.american" and "stripe.card.expiryDate" and "stripe.card.cvcNumber"
		# And verify that order is placed successfully

   #@3DSecureStripe
   #Scenario:Place order as Registered user (login before checkout) with Payment method - 3D secure card
	#	And I click on returning customer login option
	#	And I enter valid "login.email" and "login.password"
		#Then verify successful login
	   # When I place an order using payment method Pay With Card using "3D.secure.card.number" and "stripe.card.expiryDate" and "stripe.card.cvcNumber"
	   # Then verify that order is placed successfully
# @paypalStripe
	#Scenario: Place order as Registered user (login during checkout) with Payment method Stripe - PayPal
	#	And I click on returning customer login option
	#	And I enter valid "login.email" and "login.password"
		#Then verify successful login
	#	And I place an order using payment method PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
	#	And verify that order is placed successfully

     #@visaStripe
     #Scenario: Place order as Guest user with Payment method Stripe - VISA card
		#	Billing Address is same as Shipping Address
		#	Postal Address is chosen using postal API
		# And I fill in the billing details
	   #  And shipping address is same as billing address
	   #  And I choose to create an account
	   #  Then I place an order using payment method Pay With Card using valid "stripe.card.number" and "stripe.card.expiryDate" and "stripe.card.cvcNumber"
	    # And verify that order is placed successfully

  @excludeSharkUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkIT
  @excludeNinjaIT
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify User should not be able to create account during checkout using duplicate email
    And I click on pay by card button
    And I fill in valid registered email and others billing details
    And I choose to create an account
    When I select pay by card radio button
    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify valid error message for create account feature with incorrect password during checkout


    #commenting Austria cases as suggested by Dhananjay due to development of Austria in progress
#  @excludeSharkUK
#  @excludeNinjaUK
#  @ShippingandBillingAdressesDifferent @AustriaAddress
#
#  Scenario: Verify user is able to place an order with option: Shipping Address different from Billing Address for austria
#    And I click on pay by card button
#    And i select country as Austria from country selection list
#    And I fill in the billing details for autria
#    And I click on different shipping address
#    And I fill in the shipping details
#    When I select pay by card radio button
#    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    And verify that order is placed successfully
#
#  @excludeSharkUK
#  @excludeNinjaUK
#  @ShippingandBillingAdressesDifferent @AustriaAddress
#  Scenario: Verify applied coupon with Item discount on checkout page for austria address
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.item.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I click on pay by card button
#    And i select country as Austria from country selection list
#    And I fill in the billing details for autria
#    When I select pay by card radio button
#    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    And verify that order is placed successfully
#
#  @excludeSharkUK
#  @excludeNinjaUK
#  @ShippingandBillingAdressesDifferent @AustriaAddress
#  Scenario: Verify applied coupon with Item discount on checkout page for austria address with Payment method Braintree - PayPal without Promo
#    And i select country as Austria from country selection list
#    And I fill in the billing details for autria
#    And I click on different shipping address
#    And I fill in the shipping details
#    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
#    And verify that order is placed successfully
#
#  @excludeSharkUK
#  @excludeNinjaUK
#  @ShippingandBillingAdressesDifferent @AustriaAddress
#  Scenario: Verify applied coupon with Item discount on checkout page for austria address with Payment method Braintree - PayPal with Promo
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.item.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And i select country as Austria from country selection list
#    And I fill in the billing details for autria
#    And I click on different shipping address
#    And I fill in the shipping details
#    And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
#    And verify that order is placed successfully