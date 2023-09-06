@testAll
@Regression
@smokeSharkCAOld
Feature: Login: Validate login using valid credentials

  Background:
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible
    When I click on sign in link for SNCA
    Then I should be on log in page of SNCA

  @login
  @smokeSharkCAOld
  Scenario: Go To Login Page and enter the required Details
    When I enter email as "sanket.jha@wundermanthompson.com" and password as "Welcome@123" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    And language selected should be equal to "select.language.to.test"


  @Invalid_credentials_login
  Scenario Outline: Validate login using invalid credentials
    When I enter email as "<EmailData>" and password as "<PasswordData>" over SNCA
    And I clicked on sign in button
    Then error message "<ErrorMessageEN>" or "<ErrorMessageFR>" should be shown depending upon data type send for "<Email>" and "<Password>"

    Examples:

      | Email   | Password | EmailData                        | PasswordData | ErrorMessageEN                                                   | ErrorMessageFR                                                                |
      | empty   | empty    |                                  |              | Please enter a valid e-mail address.,Please enter a password.    | Veuillez entrer une adresse courriel valide.,Veuillez entrer un mot de passe. |
      | invalid | invalid  | sanket@                          | 000000       | Please enter a valid e-mail address.                             | Veuillez entrer une adresse courriel valide.                                  |
      | valid   | invalid  | sanket.jha@wundermanthompson.com |              | Please enter a password.                                         | Veuillez entrer un mot de passe.                                              |
      | valid   | valid    | sanket@wundermanthompson.com     | abc@123      | Your E-mail/Password combination is incorrect. Please try again. | Some Error message in FR                                                      |

#  @ResendLink
#  Scenario: Verify 'resend verification email' message on login for unconfirmed account
#
#    When I enter valid "unconfirmed.account.email" and "login.password.unconfirmed"
#    Then I should see account is not confirmed yet message
#    And I should see the link to resend the verification email
#    When I click on resend email link
#    Then I should get verification email sent message


	
	
	