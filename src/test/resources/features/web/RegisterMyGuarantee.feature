@regSharkUK
@regSharkDE
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regSharkFR
@regNinjaFR
@regNinjaUK
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regSharkESSuite2
@regNinjaESSuite2
@regSharkITSuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regNinjaFRSuite2
@regSharkFRSuite2
Feature: Product Registration - Register My Guarantee

Background: Go To Home Page
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

@registerMyGuaranteeWithoutLogin
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeSharkIT
@smokeNinjaIT
@smokeSharkES
@smokeNinjaES
@smokeSharkFR
@smokeNinjaFR
@smokeNinjaDE
Scenario: Verify successful product registration (without sign-in)
	When I click on register my guarantee link
	Then I should be on register my guarantee page
	And click on accept email if visible
	When I fill register my guarantee form
	And Click Submit button
	Then Guarantee should be registered

@createAccountWhileRegisteringMyGuarantee
Scenario: Verify successful account creation while product registration
#	When I click on register my guarantee link
#	Then I should be on register my guarantee page
#	When I click on create account checkbox
#	And I fill already registered email while creating account
#	And fill rest of the form with valid inputs
#	And Click Submit button
#	Then Guarantee should be registered
#	And Message should be shown that account already exist
	When I click on register my guarantee link
	Then I should be on register my guarantee page
	When I click on create account checkbox
	And I fill email which is not registered already
	And click on accept email if visible
	And fill rest of the form with valid inputs
	And Click Submit button
	Then Guarantee should be registered
	And Message should not be shown that account already exist

@registerMyGuaranteeWithLogin
Scenario: Verify successful product registration (with sign-in)
	When I click on sign in link
	Then I should be on log in page
	When I enter valid "warranty.verify.email" and "warranty.verify.password"
	Then I am successfully logged in
	And I should be on My Account Overview page
	When I click on register my guarantee link
	Then I should be on register my guarantee page
	And Email, address and name shown prefilled as user already logged in
	And click on accept email if visible
	When I fill register my guarantee form after login
	And Click Submit button
	Then Guarantee should be registered


@registerMyGuaranteeInvalidInputs
@excludeSharkIT
@excludeNinjaIT
@excludeSharkES
@excludeNinjaES
	#commenting this scenario for Italy and Spain due to below reason and will update later once Rachit confirmed with update
	#The two validation messages are not showing as in Italian and Spanish language while
 # we are putting the "incorrect First name and Last name" while "Registering My Guarantee",
Scenario: Verify product registration with invalid inputs
	When I click on register my guarantee link
	Then I should be on register my guarantee page
	When I Click Submit button without filling any field
	And click on accept email if visible
	Then I should see error messages as all fields are empty
	When I fill invalid email address
	Then I should see error message for invalid email
	When I fill valid email address
	Then Error message for invalid email should be gone
	When I fill valid postcode
	Then Error message for postcode should be gone
	When I fill invalid first name
	Then I should see error message for First name
	When I fill valid first name
	Then Error message for invalid first name should be gone
	When I fill invalid last name
	Then I should see error message for last name
	When I fill valid last name
	Then Error message for invalid last name should be gone
	When I Select Product Type
	Then Error message for empty product type should be gone
	When I fill valid product model
	Then Error message for empty Product model should be gone
	When I select purchase date
	Then Error message for empty purchase date should be gone
	When I select Purchase location
	Then Error message empty purchase location should be gone

	Scenario: Verify Register my Warranty with URL validation for each Warranty sections (without sign-in)
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		And click on accept email if visible
		When I fill register my guarantee form
		And Click Submit button
		Then Guarantee should be registered


#	@excludeSharkUK
#	@excludeNinjaUK @AustriaAddress
#	Scenario: Verify Register my Warranty with URL validation for each Warranty sections (without sign-in) for Austria Address
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
#		And i select country as Austria from country selection list
#		And click on accept email if visible
#		When I fill register my guarantee form for Austria
#		And Click Submit button
#		Then Guarantee should be registered