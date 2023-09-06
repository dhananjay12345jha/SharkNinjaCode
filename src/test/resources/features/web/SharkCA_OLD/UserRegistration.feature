@testAll
@registration
@regressionSharkCAOld
@smokeSharkCAOld
Feature: User Registration

  Background:
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible
    When I click on sign in link for SNCA
    Then I should be on log in page of SNCA
    When I clicked on Register Now Button
    Then I should be on User Registration Page having text "Create a New Account" or "Créer un nouveau compte"
    And language selected should be equal to "select.language.to.test"


  Scenario: User successfully registered on SharkNinja CA Channel
    And I fill all required field with given data
      | Email           | ConfirmEmail | Password    | ConfirmPassword | FirstName | LastName | StreetAdd   | City      | Postal  | Province | Phone      |
      | RandomGenerated | Same         | Welcome@123 | Welcome@123     | Sanket    | Jha      | 4 Queens Rd | Sackville | E4L 4G5 | Quebec   | 9876543210 |
    Then I select Terms And Condition Checkbox
    And I clicked on Create Account Button
    Then message either "Thank you for creating an account!" or "Merci d’avoir créé un compte!" should be there
    And language selected should be equal to "select.language.to.test"



  Scenario: Verify user is not able to register with same email-id again
    And I fill all required field with given data
      | Email                            | ConfirmEmail                     | Password    | ConfirmPassword | FirstName | LastName | StreetAdd   | City      | Postal  | Province | Phone      |
      | sanket.jha@wundermanthompson.com | sanket.jha@wundermanthompson.com | Welcome@123 | Welcome@123     | Sanket    | Jha      | 4 Queens Rd | Sackville | E4L 4G5 | Quebec   | 9876543210 |
    Then I select Terms And Condition Checkbox
    And I clicked on Create Account Button
    And language selected should be equal to "select.language.to.test"
    Then an error message "A customer with this email address already exists. Please login or enter a different email address" or "Il existe déjà un client ayant cette adresse courriel. Veuillez ouvrir une session ou entrer une autre adresse courriel" should be there






#	Scenario: User successfully registers on Shark Ninja UK Channel
#		When I click on register link
#		Then verify that user is on new registration page
#		When I enter all required registration field
#		And I checked the communication preferences checkbox
#		And I click on create account button
#		Then user account is created and gets successfully registered
#
#
#	Scenario: Verify user is not able to register with same email-id again
#		When I click on register link
#		Then verify that user is on new registration page
#		When I enter all required registration field with existing email-id
#		And I checked the communication preferences checkbox
#		And I click on create account button
#		Then  I should get A user with that email address already exists error message
#
#
#	Scenario: Validate if communication preferences is selected while registration
#		When I click on register link
#		Then verify that user is on new registration page
#		When I enter all required registration field
#		And I checked the communication preferences checkbox
#		And I click on create account button
#		Then user account is created and gets successfully registered
#
#
#	Scenario: Validate if communication preferences is not selected while registration
#		When I click on register link
#		Then verify that user is on new registration page
#		When I enter all required registration field
#		And I click on create account button
#		Then user account is created and gets successfully registered
#
#	Scenario: New User Registration from Login Page
#		And I click on sign in link
#		And I click on create account link
#		When I enter all required registration field
#		And I checked the communication preferences checkbox
#		And I click on create account button
#		Then user account is created and gets successfully registered
#
#
#	Scenario: Verify mandatory fields validation on registration page
#		When I click on register link
#		Then verify that user is on new registration page
#		When I enter email and password
#		And I click on create account button
#		Then I should get valid error message against mandatory fields






