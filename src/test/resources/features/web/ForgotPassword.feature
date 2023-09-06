@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkIT
@regNinjaIT
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regSharkITSuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Verify Forgot Password Functionality

Background: Navigate to Forgot your password page
Given I navigate to the "Home" page
 And click on accept cookies if visible
 And click on accept email if visible
 Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
 Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
 Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
 Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
 Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
 And I click on sign in link
 And I click on Forgot your password link

@ForgotPassword123

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
 
 
 
