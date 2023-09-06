@regSharkUK
@regSharkDE
@regSharkFR
@regNinjaFR
@regNinjaUK
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regSharkFRSuite2
@regNinjaFRSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
Feature: Verify My Product Overview

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
  	When I click on sign in link
	Then I should be on log in page
	When I enter valid "warranty.verify.email" and "warranty.verify.password"
	Then I am successfully logged in
	And click on accept email if visible

@ProductOverview
Scenario: Verify Product associated via Product registration
	And I should be on My Account Overview page
	When I click on register my guarantee link
	Then I should be on register my guarantee page
	And Email, address and name shown prefilled as user already logged in
	When I fill register my guarantee form after login
	And Click Submit button
	Then Guarantee should be registered
#	When I Click on My Account
#	And I click on Product Overview
#	Then I can successfully view the registered guarantee

#@ProductAssociationViaOrder
#@regression
#This scenario to be covered as part of manual regression as order takes 5-10 min to reflect on My Order page
#Scenario:  Verify Product associated via order
#	And I search for the product "search.validInput"
#	And I select the product from search result page "product.specificSKU"
#	And I add the product to cart "product.specificSKU"
#	And I go to checkout
#	And I place an order using payment method PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
#	And verify that order is placed successfully
	
	