@testAll
@checkout
@smokeNinjaCAOLD
Feature: Verify Checkout/Order Placement

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible
    Then I open search bar and entered the product as "*" or "*"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    And click on accept cookies if visible
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And language selected should be equal to "select.language.to.test"


  Scenario: Checkout from cart page using existing account and payment method as Pay By Card
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "sanket.jha@wundermathompson.com"
    And I enter my password as "Welcome@123"
    Then i click on continue to shipping button
#    And if any error occurred try login one more time with "sanket.jha@wundermathompson.com" and "Welcome@123"
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"

  Scenario: Creation of account while placing order - Payment Method Pay By Card
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd   | City      | Postal  | Province | Phone      |
      | Sanket    | Jha      | 4 Queens Rd | Sackville | E4L 4G5 | Quebec   | 9876543210 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"


  Scenario: Creation of account while placing order - Payment Method Pay Pal
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd   | City      | Postal  | Province | Phone      |
      | Sanket    | Jha      | 4 Queens Rd | Sackville | E4L 4G5 | Quebec   | 9876543210 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by PayPal"
    Then I click on PayPal Checkout button
    And I switched over PayPal window
    Then I enter username as "payPalSandbox.email"
    Then I click on next button
    Then if accept cookie button shown then click over it
    And I enter password as "payPalSandbox.password"
    Then I click on Login Button
    Then if accept cookie button shown then click over it
    Then I validate user should be logged into the PayPal account having display text "Pay with"
    Then I click on continue button
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"




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

#  @createAccount
#
#  Scenario: Create Account During Checkout
#    And I fill in the billing details
#    And I choose to create an account
#    Then  I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
#    And verify that order is placed successfully
#
#  @AutoPopulationofAddresses
#  Scenario: Verify auto population of address with valid postal code for both billing and shipping addresses
#    When I fill the valid postal code for billing
#    Then Billing addresses are auto populated
#    When I click on different shipping address
#    And I fill the valid postal code for shipping
#    Then Shipping addresses are auto populated
#
#  @AddAdressesManually
#  Scenario: Verify that user is able to add address manually for both billing and shipping
#    When I click on enter address manually for billing
#    Then I am able to  fill the billing address manually
#    When I click on different shipping address
#    Then Shipping address form is displayed
#    When I click on enter address manually for shipping
#    Then I am able to fill the shipping address manually
#
#
#  @ShippingandBillingAdressesDifferent
#  Scenario: Verify user is able to place an order with option: Shipping Address different from Billing Address
#    And I fill in the billing details
#    And I click on different shipping address
#    And I fill in the shipping details
#    Then  I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
#    And verify that order is placed successfully
#
#  @mandatoryfieldsvalidation
#  Scenario: Verify mandatory fields validation on checkout page
#    When I click on pay by card braintree method
#    And  I click on place order securely button
#    Then I should get error message against all mandatory fields
#
#  @BraintreeInvalidCardDetails
#  Scenario: Verify user is not able to checkout using invalid payment Braintree - invalid card details
#    And I fill in the billing details
#    And I click on pay by card braintree method
#    And I click on termsAndCondition checkbox
#    When  I click on place order securely button
#    Then I should get insert correct credit card data error message
#    When I enter invalid card number "brainTree.invalidcard.number"
#    When  I place an order using Braintree payment method Pay With Card using invalid "brainTree.invalidcard.number" and "brainTree.invalid.expiryDate" and "brainTree.card.cvcNumber"
#    Then verify error message for invalid card details
#
#  @invalidPayPalCredentialsBraintree
#  Scenario: Verify user is not able to checkout using invalid payment Braintree - invalid PayPal credentials
#    And I click on returning customer login option
#    And I enter valid "login.email" and "login.password"
#    Then verify successful login
#    When I place an order using payment method Braintree PayPal using invalid "payPalSandbox.email.invalid" and "payPalSandbox.password.invalid"
#    Then I should get valid error message
#
#  @incorrectPasswordduringcheckout
#  Scenario: Verify that create account feature with incorrect password during checkout
#    And I fill in valid registered email and others billing details
#    And I choose to create an account with incorrect password
#    Then  I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
#    And verify valid error message for create account feature with incorrect password during checkout
#
#
#  @ItemdiscountonCheckoutPage
#  Scenario: Verify applied coupon with Item discount on checkout page
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.item.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I fill in the billing details
#    Then  I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
#    And verify that order is placed successfully
#
#  @OrderdiscountonCheckoutPage
#  Scenario: Verify applied coupon with Order discount on checkout page
#    When I click on Redeem here link on checkout page
#    And I enter item discount coupon code "checkout.order.discount"
#    And I click on apply button
#    Then I should see message above order summary that promotional code has been applied successfully
#    And I fill in the billing details
#    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber"
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
