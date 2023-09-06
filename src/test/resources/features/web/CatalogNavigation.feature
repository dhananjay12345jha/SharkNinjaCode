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

Feature: Catalog Navigation: Navigate to Product List and Product Detail Page

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



#   Scenario: Navigate to Product List using Catalog
#	    And I move cursor over Products
#	    And I click on products category level 1 option "products.categorylevel1.option2"
#	    Then I successfully navigate to chosen product list "products.categorylevel1.option2.pageUrl"
#
#   Scenario: Navigate to Product Detail Page using Catalog
#	    And I move cursor over Products
#	    And I click on products category level 1 option "products.categorylevel1.option2"
#	    Then I successfully navigate to chosen product list "products.categorylevel1.option2.pageUrl"
##	   	And click on accept cookies if visible
#	    And I select the product from the listed category page "products.categorylevel1.option2.specificSKU"
#	    And I add the product to cart "products.categorylevel1.option2.specificSKU"
#	    And I go to checkout
#
#	@partsAndAccessoriesa
#	Scenario: Navigate to part and Accessory
#		When I move cursor over Parts and Accessories
#		And I click on "parts.accessory.value"
#		Then I successfully navigate to chosen parts and acessories page

	@tipsAndAdvice
	@excludeNinjaUK
	@excludeNinjaDE
	Scenario: Navigate to Tips & Advice for Shark UK and Shark DE
		When I click on tips And Advice link
		Then I successfully navigate to tips and Advice page

#	@recipesAndTips
#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaDE
#	Scenario: Navigate to Recipes & Tips Ninja UK
#		When I click on "Recipes & Tips" link
#		Then I successfully navigate to recipes and tips page

	@recipesAndTips
	@excludeSharkUK
	@excludeSharkDE
	@excludeNinjaUK
	Scenario: Navigate to Recipes & Tips for Ninja DE
		When I click on "Rezepte & Tipps" link
		Then I successfully navigate to recipes and tips page

#	@excludeNinjaUK
#	@excludeNinjaDE
#	Scenario: Validate Auto-Suggestive Functionality for Shark Profiles(UK and DE)
#		When I search for the text "product.specificTextShark"
#		Then I should verify the text Shark in the Auto Suggestive Dropdown List
#		When I search for the text "product.specificTextNinja"
#		Then I Should Verify That The Auto Suggestive Dropdown Should Not Be Displayed
#
#	@excludeSharkUK
#	@excludeSharkDE
#	Scenario: Validate Auto-Suggestive Functionality for Ninja Profiles(UK and DE)
#		When I search for the text "product.specificTextNinja"
#		Then I should verify the text Ninja in the Auto Suggestive Dropdown List
#		When I search for the text "product.specificTextShark"
#		Then I Should Verify That The Auto Suggestive Dropdown Should Not Be Displayed

	