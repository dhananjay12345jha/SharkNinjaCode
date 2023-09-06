@apiTest
Feature: Stock set feature
	Background: Go To PDP Page
		Given I navigate to the "Home" page


	Scenario: Setting the stock for Products
		Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as CB350EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for NA with the Sku as AF101C and the quantity is 200
		Given User is setting the stock quantity for NA with the Sku as SP101C and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as OL750EU and the quantity is 200


