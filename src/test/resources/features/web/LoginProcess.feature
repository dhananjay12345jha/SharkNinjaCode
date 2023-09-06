@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
@regSharkITSuite2
@regNinjaITSuite2
@regSharkESSuite2
@regNinjaESSuite2
Feature: Login: Validate login using valid credentials

  Background:
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
    When I click on sign in link
    Then I should be on log in page

  @login
  @smokeSharkUK
  @smokeSharkDE
  @smokeNinjaUK
  @smokeSharkES
  @smokeNinjaES
  @smokeNinjaDE
  Scenario: Go To Login Page and enter the required Details
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page
	
#rita singh
  @Invalid_credentials_login_without_@
  Scenario: Validate login using invalid credentials without @
    When I enter valid "invalid.email.without@" and "login.password"
    Then I should get Please enter a valid e-mail address error message for login

  @Invalid_credentials_login
  Scenario: Validate login using invalid email ID
    When I enter valid "invalid.email" and "login.password"
    Then I should get your email or password is incorrect error message

  @ResendLink
  Scenario: Verify 'resend verification email' message on login for unconfirmed account
    When I enter valid "unconfirmed.account.email" and "login.password.unconfirmed"
    Then I should see account is not confirmed yet message
    And I should see the link to resend the verification email
    When I click on resend email link
    Then I should get verification email sent message

  @Invalid_credentials_login
  Scenario: Validate login using invalid password
    When I enter valid "login.email" and "password.incorrect"
    Then I should get your email or password is incorrect error message