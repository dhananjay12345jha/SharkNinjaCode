@testAll
@regression
Feature: Add Address: I want to add address and verify if address is added successfully.


  Background: Go To Login Page and enter the required Details
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible
    When I click on sign in link for SNCA
    Then I should be on log in page of SNCA
#    Then I should be on log in page of SNUS
    And click on accept cookies if visible
    When I enter email as "sanket.jha@wundermanthompson.com" and password as "Welcome@123" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    And language selected should be equal to "select.language.to.test"

  @addAndDeleteAddress
  @smokeSharkCAOld
  @regression
  Scenario: Verify address is successfully added with correct details and then delete the added address
    When I click on Addresses Book
    Then I should be on Addresses page showing text "Add Address"
    And language selected should be equal to "select.language.to.test"
    When I click add address link for adding a address
    Then add an address form should be visible
    When I fill the form to add address with information given below
      | Country | FirstName | LastName | StreetAddress | PostalCode | City      | Province | Phone      |
      | Canada  | Sanket    | Jha      | 4 Queens Rd   | E4L 4G5    | Sackville | Quebec   | 9876543210 |
    And I click on save address button
    Then newly added Address should be saved given below details
      | Country | FirstName | LastName | StreetAddress | PostalCode | City      | Phone      |
      | Canada  | Sanket    | Jha      | 4 Queens Rd   | E4L 4G5    | Sackville | 9876543210 |
    When I click on trash button where pincode is "E4L 4G5" to delete the address
    Then I click on Delete button on confirmation pop up
    Then Address having pincode "E4L 4G5" should get deleted
    And language selected should be equal to "select.language.to.test"

  @smokeSharkCAOld
  Scenario: Validating error messages to be shown corresponding to all mandatory fields
    When I click on Addresses Book
    Then I should be on Addresses page showing text "Add Address"
    And language selected should be equal to "select.language.to.test"
    When I click add address link for adding a address
    Then add an address form should be visible
    And I click on save address button
    Then validate that against mandatory field "Country" or "Paiements" error message should be shown
    Then validate that against mandatory field "First Name" or "Prénom" error message should be shown
    Then validate that against mandatory field "Last Name" or "Nom de famille" error message should be shown
    Then validate that against mandatory field "Street Address" or "Ligne d’adresse 1" error message should be shown
    Then validate that against mandatory field "Postal Code" or "Code p ostal" error message should be shown
    Then validate that against mandatory field "City" or "Ville" error message should be shown






#  @errorValidationAddressPage
#  @regression
#  Scenario: Validate error message while adding new address
#    When I click on Addresses
#    Then I should be on Addresses page
#    When I click add address link
#    Then I Should see form to add address
#    When I click on save address
#    Then I should see error message for mandatory fields.
#    When I enter first name with "invalid.firstname"
#    Then I should see error message for invalid first name on add address page
#    When I enter first name with "valid.firstName"
#    Then Error message for invalid first name should be gone from address page
#    When I enter last name with "invalid.lastname"
#    Then I should see error message for invalid last name on add address page
#    When I enter last name with "valid.lastName"
#    Then Error message for invalid last name should be gone from address page
#    When I enter street name on add address page
#    Then Error message for empty street name should be gone from address page
#    When I enter city name on add address page
#    Then Error message for empty city name should be gone from address page
#    When I enter invalid postal code on add address page
#    Then I should see error message for invalid postal code on add address age
#    When I enter valid postal code on add address page
#    Then Error message for invalid postal code should be gone from address page
#    When I enter invalid phone number on add address page
#    Then I should see error message for invalid phone number on add address age
#    When I enter valid phone number on add address page
#    Then Error message for invalid phone number should be gone from address page
#
#  @updateAddress
#  @regression
#  Scenario: Verify address is successfully updated with correct details and then delete the updated address
#    When I click on Addresses
#    Then I should be on Addresses page
#    When I click add address link
#    Then I Should see form to add address
#    When I fill the form to add address
#    And I click on save address
#    Then Address should be saved
#    When I click pencil Icon to update the saved address
#    And I make changes in saved address
#    And I click update address button
#    Then Address should be updated
#    When I click to delete last added address
#    And I click on delete button
#    Then Address should be deleted
#
#  @AddDuplicateAddress
#  @regression
#  Scenario: Verify address is successfully updated with correct details and then delete the updated address
#    When I click on Addresses
#    Then I should be on Addresses page
#    When I click add address link
#    Then I Should see form to add address
#    When I fill the form to add address
#    And I click on save address
#    Then Address should be saved
#    When I click add address link
#    Then I Should see form to add address
#    When I fill the form to add address
#    And I click on save address
#    Then I should see message that address already exist
#    When I click to delete last added address
#    And I click on delete button
#    Then Address should be deleted