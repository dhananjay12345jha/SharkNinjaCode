@createbulkorder
Feature: Verify checkout for webshop Profiles
	Background: Go To Home Page
		Given I navigate to the "Home" page
		And click on accept cookies if visible
	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Cart And pay by Paypal)
		Given I place bulk order using installment for Webshop profiles "search.specificSKU", "product.specificSKU", "payPalEmail" and "payPalPassword"

	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Paypal Credit, Pay by Card and Pay by paypal)
		Given I place bulk order for Webshop profiles using Payment method pay by paypal Credit "search.specificSKU", "product.specificSKU", "payPalEmail" and "payPalPassword"
		Given I place bulk order for Webshop profiles using Payment method pay by Card "search.specificSKU", "product.specificSKU"
		Given I place bulk order for Webshop profiles using Payment method pay by paypal "search.specificSKU", "product.specificSKU", "payPalEmail" and "payPalPassword"

	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Card)
		Given I place bulk order for Webshop profiles using Payment method pay by Card "search.specificSKU", "product.specificSKU"

	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Normal Paypal)
		Given I place bulk order for Webshop profiles using Payment method pay by paypal "search.specificSKU", "product.specificSKU", "payPalEmail" and "payPalPassword"

	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Paypal Credit)
		Given I place bulk order for Webshop profiles using Payment method pay by paypal Credit "search.specificSKU", "product.specificSKU", "payPalEmail" and "payPalPassword"
