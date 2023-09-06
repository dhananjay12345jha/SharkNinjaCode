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
Feature: PDP Footer: Verify links in footer, click all footer links and verify is redirect to correct page.


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


#	@excludeNinjaUK
#	@excludeNinjaDE
#	@excludeSharkDE
#	Scenario: PDP Verify Shark UK footer links
#		Then Verify PDP is displayed corresponding to clicked product
#		Then I should see About Us link in footer
#		Then I should see Reviews link in footer
#		Then I should see Cleaning Tips & Advice link in footer
#		Then I should see Terms and Policies link in footer
#		Then I should see Privacy Notice link in footer
#		Then I should see Uk Modern Slavery Statement link in footer
#		Then I should see Online Dispute Resolution Link in footer
#		Then I should see Independent Bazaar Voice Reviews link in footer
#		Then I should see Shark Ninja Warranty Fair Processing Notice link in footer
#		Then I should see Shark Ninja Checkout Fair Processing Notice link in footer
#		Then I should see Shark Ninja Careers link in footer
#		Then I should see Register My Guarantee link in footer
#		Then I should see Log In link in footer
#		Then I should see Facebook Icon in footer
#		Then I should see Twitter Icon in footer
#		Then I should see Instagram Icon in footer
#		Then I should see Pinterest Icon in footer
#		Then I should see Youtube Icon in footer
#		Then I should see Newsletter Email text box in footer
#		Then I should see offers checkbox in footer
#		Then I should see newsletter submit button in footer
#		Then I should see unsubscribe link in footer
#		When I click about shark link in footer
#		Then I should be on about shark page
#		When I click Register My Guarantee link in footer
#		Then I should be on register my guarantee page
#		When I click Log In link in footer
#		Then I should be on log in page
#		When I click terms and policies link in footer
#		Then I should be on Terms and Policies page
#		When I click privacy notice link in footer
#		Then I should be on Privacy Notice page


#		When I click tips and advice link in footer
#		Then I should be on tips and advice page in new tab
#		When I click customer care link in footer
#		Then I should be on customer care footer page

#	@excludeSharkUK
#	@excludeSharkDE
#	@excludeNinjaDE
#	Scenario: PDP: Verify Ninja UK footer links
#		Then Verify PDP is displayed corresponding to clicked product
#		Then I should see "About Ninja" link in footer
#		Then I should see "Recipes & Tips" link in footer
#		Then I should see "Customer Care" link in footer
#		Then I should see "Terms and Policies" link in footer
#		Then I should see "Privacy Notice" link in footer
#		Then I should see "UK Modern Slavery Statement" link in footer
#		Then I should see "Online Dispute Resolution" link in footer
#		Then I should see "Our Independent Bazaarvoice Reviews" link in footer
#		Then I should see "SharkNinja Warranty Fair Processing Notice" link in footer
#		Then I should see "SharkNinja Checkout Fair Processing Notice" link in footer
#		Then I should see "SharkNinja Careers" link in footer
#		Then I should see "Register My Guarantee" link in footer
#		Then I should see "Log in" link in footer
#		Then I should see "Click Here" link in footer
#		Then I should see "Unsubscribe" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	Scenario: PDP: Verify Shark DE footer links
		Then Verify PDP is displayed corresponding to clicked product
		Then I should see "Impressum" link in footer
		Then I should see "Kundensupport" link in footer
		Then I should see "Datenschutzerklärung" link in footer
		Then I should see "allgemeine geschäftsbedingungen" link in footer
		Then I should see "Cookie-Hinweis" link in footer
		Then I should see "aktivieren sie ihre garantie" link in footer
		Then I should see "Anmelden" link in footer

#	@excludeSharkUK
#	@excludeNinjaUK
#	@excludeSharkDE
#	Scenario: PDP: Verify Ninja DE footer links
#		Then Verify PDP is displayed corresponding to clicked product for Ninja DE
#		Then I should see "Impressum" link in footer
#		Then I should see "Kundensupport" link in footer
#		Then I should see "Datenschutzerklärung" link in footer
#		Then I should see "Allgemeine Geschäftsbedingungen" link in footer
#		Then I should see "Cookie-Hinweis" link in footer
#		Then I should see "Aktivieren Sie Ihre Garantie" link in footer
#		Then I should see "Anmelden" link in footer


