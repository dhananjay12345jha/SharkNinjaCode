@regSharkUK
@regSharkIT
@regSharkDE
@regNinjaUK
@regNinjaIT
@regNinjaDE
@regSharkDESuite2
@regSharkITSuite2
@regNinjaDESuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Login: I want to Login as a valid user so that I can perform the operation that requires authentication


Background: Go To Login Page and enter the required Details
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
    And I should be on log in page
	When I enter valid "login.email" and "login.password"
	Then I am successfully logged in
	And click on accept email if visible

@accountDashboard
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeNinjaDE
Scenario: Verify Account Dashboard links
	When I click on My Orders
	Then I should be on Order History page
	When I click on Product Overview
	Then I should be on Product Overview page
	When I click on Payment
	Then I should be on Payment Instrument Info page
	When I click on Addresses
	Then I should be on Addresses page
	When I click on Account Details
	Then I should be on Profile page
#	When I click on Logout