@regSharkDECmsPilotSuite1
Feature: Verify Forgot Password Functionality

Background: Navigate to Forgot your password page
Given I navigate to the "Home" page
 And click on accept cookies if visible
 And I click on sign in link
 And I click on Forgot your password link
 And click on accept email if visible

@ForgotPassword

Scenario: Verify email validation on forgot password page
 
 When I enter email "invalid.forgot.email" 
 Then I should get Please enter a valid e-mail address error messages
  When I enter email "Nonconfirmed.forgot.email" 
  And  I click on ok button
  Then I should get Your account is not confirmed yet. Please confirm your account before continuing error message

 Scenario: Verify successfully reset password
 
 When I enter email "valid.forgot.email" 
 And  I click on ok button
 Then I should get link has been sent to your registered email message

 Scenario: Verify Forgot Password Header Text on Forgot Password Page
  Then Forgot Password Text should be present
 
 
 
