@regSharkUSMicroSite
@regSharkUSMicroSiteNexus
Feature: Verify checkout on MicroSite shark US

  Background: Go To Home Page
    Given I navigate to the "Home" page on MicroSite shark US
    Then I should be on product page
    When I click Add to Cart button
    And I click on mini bag icon
    Then I should be on Cart Page
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page

  Scenario: Place order as Guest user with Payment method - Brain tree (Pay by Installment)
    Then i select Continue as Guest
    Then i enter my email id as "RandomGenerated"
    And i click on continue to shipping button
    When I fill all required field with given data
      | FirstName | LastName | StreetAdd      | City     | Postal     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | Leesburg | 46538-9195 | Indiana  | 9599108719 |
    Then I click on Continue To Payment Option Button
    Then I select payment option as "Pay by Installment"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there

  Scenario: Place order as Guest user with Payment method - Paypal
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



  Scenario: Place order as Guest user with Payment method - Pay By Card
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

  Scenario: Place order as login user with Payment method - Brain tree (Pay by Installment)
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    And i click on continue to shipping button
    Then I select payment option as "Pay by Installment"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there

  Scenario: Place order as login user with Payment method - Pay By Card
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    And i click on continue to shipping button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there

  Scenario: Place order as login user with Payment method - Paypal
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password"
    And i click on continue to shipping button
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
