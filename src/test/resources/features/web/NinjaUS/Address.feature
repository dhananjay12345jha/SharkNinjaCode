@regNinjaUS
@regNinjaUSSuit2
Feature: Edit Address: I want to edit address and verify if address is edited successfully.

  Background: Go To Login Page and enter the required Details
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I click on sign in link for SNCA
    Then I should be on log in page of SNUS
    And click on accept cookies if visible
    When I enter email as "login.email" and password as "login.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page

  @editAddress
  @smokeNinjaUS
  Scenario: Verify address is successfully edited with correct details
    When I click on Addresses Book
    Then I should be on Addresses Book page
    When I click edit address link for editing a address
    Then edit an address form should be visible
    When I fill the form to add address with information given below
      | FirstName | LastName | StreetAddress  | PostalCode | City     | Province | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | 46538-9195 | Leesburg | Indiana  | 9599108719 |
    And I click on save address button
    Then Address should be edited with given below details
      | FirstName | LastName | StreetAddress  | PostalCode | City     | Phone      |
      | Sanket    | Jha      | 112 Ems T45 Ln | 46538-9195 | Leesburg | 9599108719 |
    When I click edit address link for editing a address
    Then edit an address form should be visible
    When I fill the form to add address with information given below
      | FirstName | LastName | StreetAddress | PostalCode | City        | Province | Phone      |
      | Sumeet    | Kumar    | 1 River Rd    | 12345-6000 | Schenectady | New York | 9599108719 |
    And I click on save address button
    Then Address should be edited with given below details
      | FirstName | LastName | StreetAddress | PostalCode | City        | Phone      |
      | Sumeet    | Kumar    | 1 River Rd    | 12345-6000 | Schenectady | 9599108719 |

  @validateErrorMessage
  Scenario: Validating error messages to be shown corresponding to all mandatory fields
    When I click on Addresses Book
    Then I should be on Addresses Book page
    When I uncheck same as shipping checkbox
    And I click on save address button without filling form
    Then validate that for mandatory field "First Name" or "Prénom" error message should be shown
    Then validate that for mandatory field "Lastname" or "Nom de famille" error message should be shown
    Then validate that for mandatory field "Street Address" or "Ligne d’adresse 1" error message should be shown
    Then validate that for mandatory field "City" or "Ville" error message should be shown
    Then validate that for mandatory field "Zip Code" or "Code p ostal" error message should be shown
    Then validate that for mandatory field "State" or "Ville" error message should be shown