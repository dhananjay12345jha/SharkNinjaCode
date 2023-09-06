@regSharkUK
@regSharkDE
@regNinjaUK
@regNinjaDE
@regNinjaES
@regNinjaES
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeSharkES
@smokeNinjaES
@smokeNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regNinjaDESuite2
@regSharkDESuite2
Feature: PDP Header: Verify links in header, click all header links and verify is redirect to correct page.

	Background: Go To PDP Page
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
		When I search for the product "product.specificSKU"
		And I select the product from search result page "product.specificSKU"
		And click on accept email if visible


	@excludeNinjaUK
	@excludeNinjaDE
	Scenario: PDP:Verify Header links Shark
		Then Verify PDP is displayed corresponding to clicked product
		When I click on customer care link
		Then I should be on customer care page
		When I click on register my guarantee link
		Then I should be on register my guarantee page
##		When I click on tipsAndAdvice link
##		Then I should be on tips and advice page
#		When I click on BlackFriday link
#		Then I should be on BlackFriday page
		When I click on sign in link
		Then I should be on log in page
		When I click on register link
		Then verify that user is on new registration page
		When I click on cart link
		Then I should see empty cart message


#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaDE
#	Scenario: PDP:Verify Header links Ninja
#		Then Verify PDP is displayed corresponding to clicked product
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
#		When I click on BlackFriday link
#		Then I should be on BlackFriday page
#		When I click on cart link
#		Then I should see empty cart message
#		When I click on "Recipes & Tips" link
#		Then I successfully navigate to recipes and tips page
#		When I click on customer care link
#		Then I should be on customer care page

#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaUK
#	Scenario: PDP: Verify Header Links for Ninja DE
#		Then Verify PDP is displayed corresponding to clicked product for Ninja DE
#		When I click on register my guarantee link
#		Then I should be on register my guarantee page
#		When I click on BlackFriday link
#		Then I should be on BlackFriday page
#		When I click on cart link
#		Then I should see empty cart message
#		When I click on "Rezepte & Tipps" link
#		Then I successfully navigate to recipes and tips page
#		When I click on customer care link
#		Then I should be on customer care page


#		for ninja DE header need to ask with manual team about validation of offer page