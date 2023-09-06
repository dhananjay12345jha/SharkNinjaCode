@regSharkUK
@regSharkDE
@regNinjaUK
@regNinjaDE
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeNinjaDE
@smokeSharkIT
@smokeNinjaIT
@smokeSharkES
@smokeNinjaES
@regSharkDESuite2
@regNinjaDESuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkITSuite2
@regNinjaITSuite2
@regSharkESSuite2
@regNinjaESSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Header: Verify links in header, click all header links and verify is redirect to correct page.


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
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeNinjaIT
	@excludeNinjaES
	@excludeNinjaFR
	@excludeSharkFR
	Scenario: Verify Header links Shark
		When I click on customer care link
		Then I should be on customer care page
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		When I click on sign in link
		Then I should be on log in page
		When I click on register link
		Then verify that user is on new registration page
		When I click on cart link
		Then I should see empty cart message


	@excludeSharkUK
	@excludeSharkIT
	@excludeSharkDE
	@excludeNinjaDE
	@excludeSharkES
	@excludeNinjaFR
	@excludeSharkFR
	Scenario: Verify Header links Ninja
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		When I click on sign in link
		Then I should be on log in page
		When I click on register link
		Then verify that user is on new registration page
		When I click on cart link
		Then I should see empty cart message
		When I click on customer care link
		Then I should be on customer care page

	@excludeSharkUK
	@excludeSharkDE
	@excludeNinjaUK
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	@excludeNinjaFR
	@excludeSharkFR
	Scenario: Verify Header Links for Ninja DE
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		When I click on Offers link
		Then I should be on Offers page
		When I click on cart link
		Then I should see empty cart message
		When I click on "Rezepte & Tipps" link
		Then I successfully navigate to recipes and tips page
		When I click on customer care link
		Then I should be on customer care page


#		for ninja DE header need to ask with manual team about validation of offer page
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeNinjaIT
	@excludeSharkUK
	@excludeSharkDE
	@excludeSharkIT
	@excludeNinjaFR
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Header links Shark FR
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		When I click on sign in link
		Then I should be on log in page
		When I click on register link
		Then verify that user is on new registration page
		When I click on cart link
		Then I should see empty cart message

	@excludeSharkUK
	@excludeSharkIT
	@excludeSharkDE
	@excludeNinjaDE
	@excludeNinjaUK
	@excludeNinjaIT
	@excludeSharkFR
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Header links Ninja FR
		When I click on register my guarantee link
		Then I should be on register my guarantee page
		When I click on sign in link
		Then I should be on log in page
		When I click on register link
		Then verify that user is on new registration page
		When I click on cart link
		Then I should see empty cart message