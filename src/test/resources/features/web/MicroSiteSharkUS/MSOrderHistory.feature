@regSharkUSMicroSite
@regSharkUSMicroSiteNexus
Feature: My Orders : I want to Login as a valid user and verify order history/detail page

  Background: Go To Home Page
    Given I navigate to the "Home" page on MicroSite shark US
    Then I should be on product page
    When I click Add to Cart button
    And I click on mini bag icon
    Then I should be on Cart Page
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page

  @excludeForMobile
  Scenario: Place order as Guest user with Payment method - Brain tree (Pay by Installment)
    Then i select Continue as Guest
    Then i enter my email id as "login.order.email"
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
    And get the order id from secure checkout page
    And update order id against key "order.id" in config file
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    And again i open the mainsite url
    When I click on sign in link for SNCA
    When I enter email as "login.order.email" and password as "login.order.password" over SNCA
    And i clicked on sign in button
    Then I should be on My Account page
    When I click on Orders&Returns
    Then I should be on Order and Returns page
    And search for the order number having "order.id" in Orders & Returns

  @excludeForMobile
  Scenario: Place order as login user with Payment method - Brain tree (Pay by Installment)
    And I select Yes, I have a password
    Then i enter my email id as "login.order.email"
    And I enter my password as "login.order.password"
    And i click on continue to shipping button
    Then I select payment option as "Pay by Installment"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there
    And get the order id from secure checkout page
    And update order id against key "order.id" in config file
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    And again i open the mainsite url
    When I click on sign in link for SNCA
    When I enter email as "login.order.email" and password as "login.order.password" over SNCA
    And i clicked on sign in button
    Then I should be on My Account page
    When I click on Orders&Returns
    Then I should be on Order and Returns page
    And search for the order number having "order.id" in Orders & Returns