@testAll
@checkout
@regressionSharkCA
@regSharkCASuit1
Feature: Verify User Error Validation Messages

  Background: Navigate to checkout page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file

  Scenario: Verify User Error Validation Messages (login during checkout)
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
#    And click on accept cookies if visible
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"
    When I click on add to cart button
    And I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And language selected should be equal to "select.language.to.test"
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    And I select Yes, I have a password
    Then i enter my email id as "login.email"
    And I enter my password as "login.password.incorrect"
    Then i click on continue to shipping button
    Then I should get Error Alert with message "errorMsg.updatePasssword"