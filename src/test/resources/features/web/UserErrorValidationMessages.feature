@regSharkUK
@regNinjaUK
@regSharkFR
@regNinjaFR
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regSharkDE
@regNinjaDE
@regSharkDESuite1
@regNinjaDESuite1
@regSharkESSuite1
@regNinjaESSuite1
@regSharkITSuite1
@regNinjaITSuite1
@regNinjaUKSuite1
@regSharkUKSuite1
@regNinjaFRSuite2
@regSharkFRSuite2
Feature: Verify User Error Validation Messages

  Background: Navigate to home page as Guest User
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And click on accept email if visible
    And click on accept cookies if visible
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

  @VisaBraintree
  Scenario: Verify User Error Validation Message (login during checkout)
    When I search for the product "product.specificSKU"
    And I select the product from search result page "product.specificSKU"
    And click on accept email if visible
    And I add the product to cart "product.specificSKU"
    And I go to checkout
    And I click on returning customer login option
    When I enter valid "login.email" and "password.incorrect"
    Then I Should get your email or password is incorrect error message

  @VisaBraintree
  @excludeNinjaUK
  @excludeNinjaDE
  @excludeNinjaIT
  @excludeNinjaES
  @excludeNinjaFR
  Scenario: Verify User Error Validation Message While Editing the Email for SharkUK/DE/FR
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    When I click on Account Details
    Then I should be on Profile page
    When I click on pencil icon next to email
	Then I should be on edit email preference page
	When I enter new email with "new.email"
    And I fill repeat new email with "login.email"
	When I enter password with "password.incorrect"
	And Click on save changes
    Then I Should get email or password is incorrect error message

  @VisaBraintree
  @excludeSharkUK
  @excludeSharkDE
  @excludeSharkIT
  @excludeSharkES
  @excludeSharkFR
  Scenario: Verify User Error Validation Message While Editing the Email for NinjaUK/DE/FR
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And click on accept email if visible
    When I click on Account Details
    Then I should be on Profile page
    When I click on pencil icon next to email
    Then I should be on edit email preference page
    When I enter new email with "New.email.ID"
    And I fill repeat new email with "login.email"
    When I enter password with "invalid.Password"
    And Click on save changes
    Then I Should get incorrect validation error message for email or password

  @VisaBraintree
  @excludeNinjaUK
  @excludeNinjaDE
  @excludeNinjaIT
  @excludeNinjaES
  @excludeNinjaFR
  Scenario: Verify User Error Validation Message While Registering the User (SharkUK/DE/FR)
    When I click on register link
    Then verify that user is on new registration page
    When I fill all the required details for the User Registration
    #And I checked the email preference checkbox
    #And I click on create account button
    Then I should get valid error message against Username and Password

  @VisaBraintree
  @excludeSharkUK
  @excludeSharkDE
  @excludeSharkIT
  @excludeSharkES
  @excludeSharkFR
  Scenario: Verify User Error Validation Message While Registering the User (NinjaUK/DE/FR)
    When I click on register link
    Then verify that user is on new registration page
    When I fill all the required details for the User Registration
    And I checked the email preference checkbox
    And I click on create account button
    Then I should get valid error message against Username and Password