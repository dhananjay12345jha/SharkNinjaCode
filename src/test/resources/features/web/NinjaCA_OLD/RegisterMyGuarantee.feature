@testAll
@regressionNinjaCAOLD
Feature: Product Registration - Register My Guarantee

  Background: Go To Home Page Of SharkNinja CA
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible

  @registerMyGuaranteeWithoutLogin
  @smokeNinjaCAOLD
  Scenario: Verify successful product registration (without sign-in)
    When I click on register my guarantee link of Shark CA
    Then I should be on register my guarantee page having text either "Register a Product" or "Enregistrer un produit"
    And language selected should be equal to "select.language.to.test"
    When I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email                            | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | SS101C  | Amazon          |          | 04-05-2021   | sanket.jha@wundermanthompson.com | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" should be shown
    And Model Number should be displayed as "SS101C"
    And language selected should be equal to "select.language.to.test"
#
#@createAccountWhileRegisteringMyGuarantee
#Scenario: Verify successful account creation while product registration
#	When I click on register my guarantee link
#	Then I should be on register my guarantee page
#	When I click on create account checkbox
#	And I fill already registered email while creating account
#	And fill rest of the form with valid inputs
#	And Click Submit button
#	Then Guarantee should be registered
#	And Message should be shown that account already exist
#	When I click on register my guarantee link
#	Then I should be on register my guarantee page
#	When I click on create account checkbox
#	And I fill email which is not registered already
#	And fill rest of the form with valid inputs
#	And Click Submit button
#	Then Guarantee should be registered
#	And Message should not be shown that account already exist
#
#@registerMyGuaranteeWithLogin
#Scenario: Verify successful product registration (with sign-in)
#	When I click on sign in link
#	Then I should be on log in page
#	When I enter valid "login.email" and "login.password"
#	Then I am successfully logged in
#	And I should be on My Account Overview page
#	When I click on register my guarantee link
#	Then I should be on register my guarantee page
#	And Email, address and name shown prefilled as user already logged in
#	When I fill register my guarantee form after login
#	And Click Submit button
#	Then Guarantee should be registered
#
#
#@registerMyGuaranteeInvalidInputs
#Scenario: Verify product registration with invalid inputs
#	When I click on register my guarantee link
#	Then I should be on register my guarantee page
#	When I Click Submit button without filling any field
#	Then I should see error messages as all fields are empty
#	When I fill invalid email address
#	Then I should see error message for invalid email
#	When I fill valid email address
#	Then Error message for invalid email should be gone
#	When I fill valid postcode
#	Then Error message for postcode should be gone
#	When I fill invalid first name
#	Then I should see error message for First name
#	When I fill valid first name
#	Then Error message for invalid first name should be gone
#	When I fill invalid last name
#	Then I should see error message for last name
#	When I fill valid last name
#	Then Error message for invalid last name should be gone
#	When I Select Product Type
#	Then Error message for empty product type should be gone
#	When I fill valid product model
#	Then Error message for empty Product model should be gone
#	When I select purchase date
#	Then Error message for empty purchase date should be gone
#	When I select Purchase location
#	Then Error message empty purchase location should be gone
