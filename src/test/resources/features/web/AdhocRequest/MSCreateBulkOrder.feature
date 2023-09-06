@createbulkorder
Feature: Verify checkout on MicroSite
	Background: Go To Home Page
		Given I navigate to the "Home" page on MicroSite
	Scenario: Place 100 orders as Guest user with Payment method - Brain tree (Pay by Installment)
		Given I place bulk order using installment
