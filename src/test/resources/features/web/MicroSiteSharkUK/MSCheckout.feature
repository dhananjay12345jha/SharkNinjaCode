@regSharkDEMicroSite
@regSharkUKMicroSite
@regNinjaUKMicroSite
@regNinjaDEMicroSite
@regNinjaDEMicroSiteNexus
@regSharkDEMicroSiteNexus
@regSharkUKMicroSiteNexus
@regNinjaUKMicroSiteNexus

Feature: Verify checkout on MicroSite

	Background: Go To Home Page
	Given I navigate to the "Home" page on MicroSite
	When I select last product tile
	And I add product to cart

@verifyMSCheckoutGuest
@excludeSharkUKMicroSite
@excludeSharkUKMicroSiteNexus
@excludeNinjaUKMicroSite
@excludeNinjaUKMicroSiteNexus
@regression
Scenario: Place order as Guest user with Payment method - Brain tree (Pay by Installment)
	And I fill in the billing details
	And shipping address is same as billing address
	When I select Braintree Installment payment method
	And I place an order by brain tree instalment With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
	And verify that order is placed successfully


@verifyMSCheckoutAfterLogin
@excludeSharkUKMicroSite
@excludeSharkUKMicroSiteNexus
@excludeNinjaUKMicroSite
@excludeNinjaUKMicroSiteNexus
@regression
Scenario: Place order as login user with Payment method - Brain tree (Pay by Installment)
	And I click on returning customer login option
	And I enter valid "login.email" and "login.password"
	Then verify successful login
	When I select Braintree Installment payment method
	And I place an order by brain tree instalment With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
	And verify that order is placed successfully


@MSVisaBraintree
Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - VISA card
	And I click on returning customer login option
	And I enter valid "login.email" and "login.password"
	Then verify successful login
	When I select pay by card radio button
	Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
	And verify that order is placed successfully

@MSPaypalBraintree
Scenario: Place order as Registered user (login during checkout) with Payment method Braintree - PayPal
	And I click on returning customer login option
	And I enter valid "login.email" and "login.password"
	Then verify successful login
	And I place an order using payment method Braintree PayPal using valid "payPalSandbox.email" and "payPalSandbox.password"
	And verify that order is placed successfully
