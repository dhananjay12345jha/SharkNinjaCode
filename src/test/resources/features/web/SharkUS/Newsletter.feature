@testAll

@regSharkUS
@regSharkUSSuit2

Feature: Validate newsletter subscription

  Background: Go To Home Page Of Shark US
    Given I navigate to the "Home" page
    And click on accept cookies if visible

#  @newsLetterSubscriptionThroughRegisterMyWarranty
#  @smokeSharkUS
#  Scenario: Verify newsletter subscription through Register my Warranty
#    When I click on register my guarantee link of Shark CA
#    And click on accept cookies if visible
#    And I fill register my guarantee form of Shark CA with below details
#      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
#      | Sanket    | Jha      | HV300C  | Amazon          |          | 20-08-2021   | login.email | Canada  | Yes                    |
#    And Click Submit button of Shark CA
#    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
#    When I click on sign in link for SNUS
#    Then I should be on log in page of SNUS
#    When I enter email as "sanket.jha@wundermanthompson.com" and password as "Welcome@123" over SNCA
#    And click on accept cookies if visible
#    And I clicked on sign in button
#    Then I should be on My Account page
#    When I click on Account Setting
#    Then I should be on Account Setting page
#    And all preferences shown be checked

#  @newsLetterSubscriptionThroughCheckout
#  Scenario: Verify newsletter subscription through Checkout
#    When I open search bar and entered the product as "product1"
#    And I clicked on the search button
#    And click on accept cookies if visible
#    When I click on product which is not out of stock from search list
#    Then I should be on PDP page of that product
#    When I click on add to cart button
#    And I click on cart icon
#    Then I should be on cart page where text shown is "Cart" or "Panier"
#    When i click on checkout button at cart page
#    Then i should navigate to "Secure Checkout" page
#    And I select Yes, I have a password
#    Then i enter my email id as "login.email"
#    And I enter my password as "login.password"
#    Then i click on continue to shipping button
#    Then I select payment option as "Pay by Card"
#    And i entered all card number, card expiration date and cvv number
#    Then i clicked on review order button
#    And i tick checkbox i agree to terms and condition
#    And I check the checkbox for news letter subscription
#    And i click on place order button
#    Then order get placed and a message "Thanks for your Order" should be there
#    When I click logo
#    And I click on sign in link for SNUS
#    Then I should be on My Account page
#    When I click on Account Setting
#    Then I should be on Account Setting page
#    And all preferences shown be checked
