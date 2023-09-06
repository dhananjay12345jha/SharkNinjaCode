@testAll
@regSharkUS
@regSharkUSSuit2
Feature: Login: Validate login using valid credentials

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS

  @loginSNUS
  @smokeSharkUS
  Scenario: Go To Login Page and enter the required Details
    When I enter email as "sanket.jha@wundermanthompson.com" and password as "Welcome@123" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page

  @smokeSharkUS
  @Invalid_credentials_login_SNUS
  Scenario Outline: Validate login using invalid credentials
    When I enter email as "<EmailData>" and password as "<PasswordData>" over SNUS
    And I clicked on sign in button
    Then login error message "<ErrorMessageEN>" or "<ErrorMessageFR>" should be shown depending upon data type send for "<Email>" and "<Password>"

    Examples:

      | Email   | Password | EmailData                        | PasswordData | ErrorMessageEN                                                   | ErrorMessageFR                                                                |
      | empty   | empty    |                                  |              | Please enter an e-mail address.,Please enter a password.         | Veuillez entrer une adresse courriel valide.,Veuillez entrer un mot de passe. |
      | invalid | invalid  | sanket@                          | 000000       | Please enter a valid e-mail address.                             | Veuillez entrer une adresse courriel valide.                                  |
      | valid   | invalid  | sanket.jha@wundermanthompson.com |              | Please enter a password.                                         | Veuillez entrer un mot de passe.                                              |
      | valid   | valid    | xyz3@yopmail.com                 | abc@1234     | Your E-mail/Password combination is incorrect. Please try again. | Some Error message in FR                                                      |

  @ResendLink
  Scenario: Verify 'resend verification email' message on login for unconfirmed account
    When I login with given credentials "nonconfirmed.forgot.email" and "nonconfirmed.forgot.password"
    Then I should get alert with "nonconfirmed_forgot_email_error" error
    When I click on resend email link in SNUSCA
    Then I should get alert with "nonconfirmed_email_resendLink" success message
	