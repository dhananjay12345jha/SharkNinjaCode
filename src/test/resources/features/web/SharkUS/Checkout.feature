@testAll
@regSharkUS
@regSharkUSSuit1
Feature: Verify Checkout/Order Placement

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And click on accept cookies if visible
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product

  @smokeSharkUS
  @checkoutExistingUserSNUS
  Scenario: Checkout from cart page using existing account
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    Then i click on continue to shipping button
#    And if any error occurred try login one more time with "mbhargava@wundermancommerce.com" and "Intershop@123"
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there

  @smokeSharkUS
  @checkoutGuestUserSNUSCC
  Scenario: Creation of account while placing order - Payment Method Pay By Card
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there

  Scenario: Place order as Guest user with Payment method - American Express card
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by Card"
    And i entered card number, expiration date, cvv number and postal code of AMEX card
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"

  @checkoutGuestUserSNUSPayPal
  Scenario: Creation of account while placing order - Payment Method Pay Pal
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
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
    Then I validate user should be logged into the PayPal account having display text "validation.text.after.paypal.login"
    Then I click on continue button
    And again i click on continue button
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"

  @smokeSharkUS
  Scenario: Place order as Registered user (login during checkout) with Payment method - PayPal
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    Then i click on continue to shipping button
    Then I select payment option as "Pay by PayPal"
    Then I click on PayPal Checkout button
    And I switched over PayPal window
    Then I enter username as "payPalSandbox.email"
    Then I click on next button
    Then if accept cookie button shown then click over it
    And I enter password as "payPalSandbox.password"
    Then I click on Login Button
    Then if accept cookie button shown then click over it
    Then I validate user should be logged into the PayPal account having display text "validation.text.after.paypal.login"
    Then I click on continue button
    And again i click on continue button
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"


  Scenario: Verify applied coupon with Item discount on checkout page
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page
    Then I calculate the total sum of products cost
    Then enter promoCode as "cart.item.discount"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "cart.item.discount" should be visible with each item in the basket
    And also new price of each item should be less than the price before discount
    And now Subtotal amount value should be less than before when discount applied
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And under order summary discount on items as "cart.item.discount" and discounted price shown should be same which is there on cart page

  Scenario: Verify applied coupon with Order discount on checkout page
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page
    Then I calculate the total sum of products cost
    Then enter promoCode as "cart.order.discount"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "cart.order.discount" should be shown under shipping and taxes calculation
    And discounted amount applied at order level should not be zero
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And discount price and promoCode shown at checkout page should be same which is there on cart

  @checkoutExistingUserInvalidCardDetails
  Scenario: Verify user unable to order with incorrect card details
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    Then i click on continue to shipping button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then review order button should be shown enabled
    When I enter incorrect card name
    Then review order button should be shown disabled
    When I enter correct card name
    Then review order button should be shown enabled
    When I enter incorrect card number
    Then review order button should be shown disabled
    When I enter correct card number
    Then review order button should be shown enabled
    When I enter incorrect card expiration date
    Then review order button should be shown disabled
    When I enter correct card expiration date
    Then review order button should be shown enabled

  @invalidPaypalCredentials
  Scenario: Verify user unable to checkout with invalid PayPal credentials
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    Then i click on continue to shipping button
    Then I select payment option as "Pay by PayPal"
    Then I click on PayPal Checkout button
    And I switched over PayPal window
    Then I enter username as "payPalSandbox.email.invalid"
    Then I click on next button
    Then if accept cookie button shown then click over it
    And I enter password as "payPalSandbox.password"
    Then I click on Login Button
    Then if accept cookie button shown then click over it
    Then verify message that paypal information is incorrect having display text "Some of your info isn't correct. Please try again."
    When I click on change email link
    And I enter username as "payPalSandbox.email"
    Then I click on next button
    Then if accept cookie button shown then click over it
    And I enter password as "payPalSandbox.password.invalid"
    Then I click on Login Button
    Then if accept cookie button shown then click over it
    Then verify message that paypal information is incorrect having display text "Some of your info isn't correct. Please try again."

  Scenario: Verify Mandatory fields validation at checkout page
    And number shown over cart icon should equal to "0"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "1"
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    Then page title should contains text "Search Result for " or "Résultat de recherche pour "
    And search result page should display text contains "product2"
    And validate that number of product tiles shown should be greater than zero
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And number shown over cart icon should equal to "1"
    And I note product title,model number and price
    And I note subtotal, estimated shipping and total price on cart page
    When I click on add to cart button
    Then number shown over cart icon should equal to "2"
    When I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    Then I validate that multiple products which are added should be there in cart
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And i validate "Subtotal value", "Estimated Shipping" and "Total Value" should be same as on cart page

  Scenario: Verify user is able to place an order with option: Shipping Address different from Billing Address
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "diffbillingshippadd1@yopmail.com"
    And I enter my password as "Newuser@123"
    Then i click on continue to shipping button
    Then i uncheck the checkbox billing address same as shipping address
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"

  Scenario: Verify that create account feature with incorrect password during checkout
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    Then i entered the password as "123456789" which does not full fill the criteria of creation
    And i tick checkbox i agree to terms and condition
    Then place order should be disabled and user should not be able to click it




  @smokeSharkUS
  @addressAutoPopulateOnValidPostcode
  Scenario: Verify auto population of address with valid PostCode
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When i fill Street address with postal code "zzzzz"
    Then no address is auto populated as postal code is invalid
    When i fill Street address with postal code "46538-9195"
    Then address is auto populated as postal code is valid
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    And I click on Continue To Payment Option Button
    When i uncheck the checkbox to fill different billing address other than shipping address
    When i fill billing Street address with postal code "zzzzz"
    Then no address is auto populated as postal code is invalid
    When i fill billing Street address with postal code "46538-9195"
    Then address is auto populated as postal code is valid

  @smokeSharkUS
  @fillBillingAndShippingAddress
  Scenario: Verify user can manually fill shipping and billing address on checkout page
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    And I click on Continue To Payment Option Button
    When i uncheck the checkbox to fill different billing address other than shipping address
    And I fill all billing required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |

