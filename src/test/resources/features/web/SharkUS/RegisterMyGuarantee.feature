@testAll
@regSharkUS
@regSharkUSSuit2
Feature: Product Registration - Register My Guarantee

  Background: Go To Home Page Of Shark US
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @registerMyGuaranteeHeadingSharkUS
  @smokeSharkUS
  Scenario: Verify register my guarantee page heading
    When I click on register my guarantee link of Shark CA
    Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"

  @registerMyGuaranteeWithoutLogin
  @smokeSharkUS
  Scenario: Verify successful product registration (without sign-in)
    When I click on register my guarantee link of Shark CA
    And click on accept cookies if visible
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday   | login.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"


  @registerMyGuaranteeInvalidInputs
  Scenario: Verify product registration with invalid inputs
    When I click on register my guarantee link of Shark CA
    And click on accept cookies if visible
    And I Click Submit button without filling any information
    Then I should see error messages for all mandatory fields
#	Then I should see error messages as all fields are empty
    When I fill invalid first name in register my guaranty
    Then I should see error message for first name
    When I fill valid first name in register my guaranty
    Then error message for invalid first name should not be shown
    When I fill invalid last name in register my guaranty
    Then I should see invalid error message for last name
    When I fill valid last name in register my guaranty
    Then error message for invalid last name should not be shown
    When I fill invalid email address in register my guaranty
    Then error message for invalid email should be shown
    When I fill valid email address in register my guaranty
    Then Error message for invalid email should not be shown

  @registerMyGuaranteeWithLogin
  Scenario: Verify successful product registration (with sign-in)
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "sanket.jha@wundermanthompson.com" and password as "Welcome@123" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    When I click on register my guarantee link of Shark CA
    And click on accept cookies if visible
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday    | login.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"

  @validateProductGuaranty
  Scenario: Validate product registration
    When I click on register my guarantee link of Shark CA
    And click on accept cookies if visible
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday    | login.register.product.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"
    And Save the guarantee information
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.register.product.email" and password as "login.register.product.password" over SNCA
    And click on accept cookies if visible
    And I clicked on sign in button
    Then I should be on My Account page
    When I click on My ProductsWarranties
    Then I should be on Products and Warranties page
    And I should see registered product warranty

#  @RegisterByOrder
#  Scenario: Verify Product warranty associated via order
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
#    Then i enter my email id as "warranty.email"
#    And I enter my password as "warranty.password"
#    Then i click on continue to shipping button
##    And if any error occurred try login one more time with "mbhargava@wundermancommerce.com" and "Intershop@123"
#    Then I select payment option as "Pay by Card"
#    And i entered all card number, card expiration date and cvv number
#    Then i clicked on review order button
#    And i tick checkbox i agree to terms and condition
#    And i click on place order button
#    Then order get placed and a message "Thanks for your Order" should be there