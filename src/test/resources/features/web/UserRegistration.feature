@regSharkUK
@regSharkIT
@regSharkDE
@regNinjaUK
@regSharkES
@regNinjaES
@regNinjaIT
@regNinjaDE
@smokeSharkUK
@smokeSharkIT
@smokeSharkDE
@smokeNinjaUK
@smokeSharkES
@smokeNinjaES
@smokeNinjaIT
@smokeNinjaDE
@regSharkDESuite2
@regSharkITSuite2
@regNinjaDESuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regNinjaESSuite2
@regSharkESSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: User Registration

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

	Scenario: User successfully registers on Shark Ninja UK Channel
		When I click on register link
		Then verify that user is on new registration page
		When I enter all required registration field
		And I checked the communication preferences checkbox
		And I click on create account button
		Then user account is created and gets successfully registered


	Scenario: Verify user is not able to register with same email-id again
		When I click on register link
		Then verify that user is on new registration page
		When I enter all required registration field with existing email-id
		And I checked the communication preferences checkbox
		And I click on create account button
		Then  I should get A user with that email address already exists error message


	Scenario: Validate if communication preferences is selected while registration
		When I click on register link
		Then verify that user is on new registration page
		When I enter all required registration field
		And I checked the communication preferences checkbox
		And I click on create account button
		Then user account is created and gets successfully registered


	Scenario: Validate if communication preferences is not selected while registration
		When I click on register link
		Then verify that user is on new registration page
		When I enter all required registration field
		And I click on create account button
		Then user account is created and gets successfully registered

	Scenario: New User Registration from Login Page
		And I click on sign in link
		And I click on create account link
		When I enter all required registration field
		And I checked the communication preferences checkbox
		And I click on create account button
		Then user account is created and gets successfully registered


	Scenario: Verify mandatory fields validation on registration page
		When I click on register link
		Then verify that user is on new registration page
		When I enter email and password
		And I checked the communication preferences checkbox
		And I click on create account button
		Then I should get valid error message against Username and Password


	@excludeSharkUK
	@excludeNinjaUK @austriaaddress
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Validate if communication preferences is selected while registration for austria address
		When I click on register link
		Then verify that user is on new registration page
		And i select country as Austria from country selection list
		When I enter all required registration field
		And I checked the communication preferences checkbox
		And I click on create account button
		Then user account is created and gets successfully registered

	@excludeSharkUK
	@excludeNinjaUK @austriaaddress @excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Validate if communication preferences is not selected while registration for austria address
		When I click on register link
		Then verify that user is on new registration page
		And i select country as Austria from country selection list
		When I enter all required registration field
		And I click on create account button
		Then user account is created and gets successfully registered

	@excludeSharkUK
	@excludeNinjaUK @austriaaddress @excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: New User Registration from Login Page for austria address
		And I click on sign in link
		And I click on create account link
		And i select country as Austria from country selection list
		When I enter all required registration field
		And I checked the communication preferences checkbox
		And I click on create account button
		Then user account is created and gets successfully registered