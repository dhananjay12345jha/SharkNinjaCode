@regSharkUK
@regSharkDE
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regNinjaUK
@regNinjaDE
@regSharkDESuite2
@regSharkITSuite2
@regNinjaITSuite2
@regSharkESSuite2
@regNinjaESSuite2
@regNinjaDESuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
@regSharkESSuite2
@regNinjaESSuite2
Feature: Verify Logout Functionality

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
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    When I click on sign in link
    Then I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I should be on My Account Overview page

@Logout
@Regression
  Scenario: Validate Logout functionality
    When I click on logout button
    Then I am successfully logout