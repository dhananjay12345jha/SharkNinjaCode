@testAll
@regNinjaUS
@regNinjaUSSuit2
Feature: Product Registration - Register My Guarantee

  Background: Go To Home Page Of Ninja US
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @registerMyGuaranteeHeadingNinjaUS
  @smokeNinjaUS
  Scenario: Verify register my guarantee page heading
    When I click on register my guarantee link of Shark US
    Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"


  @registerMyGuaranteeWithoutLoginSharkUS
  @smokeNinjaUS
  Scenario: Verify successful product registration (without sign-in)
    When I click on register my guarantee link of Shark US
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
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday   | login.email | Canada  | Yes                    |
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
    And I clicked on sign in button
    Then I should be on My Account page
    When I click on My ProductsWarranties
    Then I should be on Products and Warranties page
    And I should see registered product warranty