@testAll
@regSharkUS
@ForgotPassword
@regSharkUSSuit2
Feature: Verify Forgot Password Functionality

  Background: Navigate to Forgot your password page
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    When I click on sign in link for SNUS
    And I click on Forgot your password link for SNUS


  Scenario: Verify email validation on forgot password page
    When I enter email as "invalid.forgot.email"
    Then I should get "invalid_forgot_email_error" message
    When I enter email as "nonconfirmed.forgot.email"
    Then I check I am not a Robot checkbox if available
    And  I click on Send Recover Link button
    Then I should get alert with "nonconfirmed_forgot_email_error" error


  Scenario: Verify successfully reset password
    When I enter email as "valid.forgot.email"
    Then I check I am not a Robot checkbox if available
    And  I click on Send Recover Link button
    Then I should get alert with "ResetLink_forgot_email" success message

 
 
 
