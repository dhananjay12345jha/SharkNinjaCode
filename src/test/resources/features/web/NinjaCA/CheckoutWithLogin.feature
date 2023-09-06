@testAll
@checkout
@regressionNinjaCA
@regNinjaCASuit1
Feature: Verify Checkout/Order Placement as Login User

  Scenario: Navigate to checkout page as Login User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.checkout.email" and password as "login.checkout.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    And language selected should be equal to "select.language.to.test"
    And select language defined in property file
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
#    And click on accept cookies if visible
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And language selected should be equal to "select.language.to.test"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then I select payment option as "Pay by Card"
    And i entered all card number, card expiration date and cvv number
    Then i clicked on review order button
    And i tick checkbox i agree to terms and condition
    And i click on place order button
    Then order get placed and a message "Thanks for your Order" should be there or "Merci pour votre commande"
