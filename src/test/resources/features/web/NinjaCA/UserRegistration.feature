@testAll
@registration
@regressionNinjaCA
@regNinjaCASuit2
Feature: User Registration

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I clicked on Create Account link
    Then I should be on Shark User Registration Page having text "Create Account" or "Créer un compte"
    And language selected should be equal to "select.language.to.test"

  @smokeNinjaCA
  Scenario: User successfully registered on SharkNinja CA Channel
    And I fill all required field with given data while creating account
      | FirstName | LastName | Email           | Password    |
      | Sanket    | Jha      | RandomGenerated | Welcome@123 |
    Then I select Terms And Condition Checkbox
    And I clicked on Create Account Button
    Then message either "Thank you for creating an account!" or "Merci d’avoir créé un compte!" should be there
    And language selected should be equal to "select.language.to.test"


  Scenario: Verify user is not able to register with same email-id again
    And I fill all required field with given data while creating account
      | FirstName | LastName | Email                           | Password      |
      | Megha     | Bhargava | mbhargava@wundermancommerce.com | Intershop@123 |
    And I clicked on Create Account Button
    Then an error message "Username/password is incorrect" or "Le nom d’utilisateur/mot de passe est incorrect." should be there
    And language selected should be equal to "select.language.to.test"

  Scenario: Verify mandatory fields validation on create account page
    And click on accept cookies if visible
    And I clicked on Create Account Button
    Then validate that "empty" error message for mandatory field "firstName" should be shown
    Then validate that "empty" error message for mandatory field "lastName" should be shown
    Then validate that "empty" error message for mandatory field "email" should be shown
    Then validate that "empty" error message for mandatory field "password" should be shown
    When I fill all required field with given data while creating account
      | FirstName | LastName | Email | Password |
      | @         | @        | @     | *        |
    Then validate that "invalid" error message for mandatory field "firstName" should be shown
    Then validate that "invalid" error message for mandatory field "lastName" should be shown
    Then validate that "invalid" error message for mandatory field "email" should be shown
    Then validate that "invalid" error message for mandatory field "password" should be shown
