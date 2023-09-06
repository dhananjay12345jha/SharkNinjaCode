@testAll
@checkout
@regressionSharkCA
@regSharkCASuit1
Feature: Verify Order History page after Placing an Order and Order number link to open Order detail page

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product

  @excludeForMobile
  Scenario: Verify Order Number Should display in Account After placing order
    And language selected should be equal to "select.language.to.test"
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And language selected should be equal to "select.language.to.test"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "login.order.email"
    And I enter my password as "login.order.password"
    Then i click on continue to shipping button
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"
    And get the order id from secure checkout page
    And update order id against key "order.id" in config file
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    And again i open the site url
    And select language defined in property file
    When I click on sign in link for SNCA
    Then I should be on My Account page
    When I click on Orders&Returns
    Then I should be on Order and Returns page
    And search for the order number having "order.id" in Orders & Returns


