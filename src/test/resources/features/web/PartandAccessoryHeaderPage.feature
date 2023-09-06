@regSharkUK
@regSharkDE
@regNinjaUK
@regNinjaDE
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regNinjaUKSuite2
@regSharkUKSuite2
Feature: Parts & Accessories Header: Verify links in header, click all header links and verify is redirect to correct page.

	Background:Navigate to part and Accessory
		Given I navigate to the "Home" page
		And click on accept cookies if visible
		And click on accept email if visible
		Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
		When I move cursor over Parts and Accessories
		And I click on "parts.accessory.value"
		Then I successfully navigate to chosen parts and acessories page


#	@excludeNinjaUK
#	@excludeNinjaDE
#	Scenario: Parts & Accessories:Verify Header links Shark
#		When I click on customer care link
#		Then I should be on customer care page
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
##		When I click on tipsAndAdvice link
##		Then I should be on tips and advice page
#		When I click on offers link
#		Then I should be on offers page
#		When I click on cart link
#		Then I should see empty cart message


#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaDE
#	Scenario: Parts & Accessories: Verify Header links Ninja
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
#		When I click on offers link
#		Then I should be on offers page
#		When I click on cart link
#		Then I should see empty cart message
#		When I click on "Recipes & Tips" link
#		Then I successfully navigate to recipes and tips page
#		When I click on customer care link
#		Then I should be on customer care page

#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaUK
#	Scenario: Parts & Accessories: Verify Header Links for Ninja DE
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
#		When I click on offers link
#		Then I should be on offers page
#		When I click on cart link
#		Then I should see empty cart message
#		When I click on "Rezepte & Tipps" link
#		Then I successfully navigate to recipes and tips page
#		When I click on customer care link
#		Then I should be on customer care page


#		for ninja DE header need to ask with manual team about validation of offer page