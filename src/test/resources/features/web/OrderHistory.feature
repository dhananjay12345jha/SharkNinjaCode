@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkES
@regNinjaES
@regSharkFR
@regNinjaFR
@regSharkIT
@regNinjaIT
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regSharkESSuite2
@regNinjaESSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
Feature: My Orders : I want to Login as a valid user and verify order history/detail page


  Background: Go To Login Page and enter the required Details
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


#  @orderHistory
#  @smokeSharkUK
#  @smokeSharkDE
#  @smokeNinjaUK
#  @smokeNinjaDE
  @excludeSharkUK
  @excludeNinjaUK
  @excludeNinjaDE
  @excludeSharkDE
  @excludeNinjaIT
  @excludeSharkIT
  @excludeNinjaES
  @excludeSharkES
  @excludeNinjaFR
  @excludeSharkFR

  Scenario: Verify order history and order detail page
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.order.email" and "login.order.password"
    Then I am successfully logged in
    When I click on My Orders
    Then I should be on Order History page
#    And click on accept cookies if visible
    When I navigate to my Order "order.history.page.orderNumber"
    Then I should see my Order
    When I click to see Order details
    Then I should be on Order details page
    And I should see Order information details
    And I should see Ordered Item details
	#And I should see Order address details
	#And I should see Order Shipping details
	#And I should see Order payment details
	#And I should see Order summary details
    When I click on Back to orders link
    Then I should be on Order History page
    When I navigate to my Order "order.history.page.orderNumber"
    And I click to see Order details
    Then I should be on Order details page
    When I click on continue shopping link
    Then I should be on home page

  @excludeForMobile
  @excludeSharkUK
  @excludeNinjaUK
  @excludeNinjaDE
  @excludeSharkDE
  @excludeNinjaIT
  @excludeSharkIT
  @excludeNinjaES
  @excludeSharkES
  @excludeNinjaFR
  @excludeSharkFR

  Scenario: validate order number show in order history
    When I search for the product "product.specificSKU"
    And I select the product from search result page "product.specificSKU"
    And I add the product to cart "product.specificSKU"
    And I go to checkout
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    When I select pay by card radio button
    When I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    Then verify that order is placed successfully
    And save the order number into the properties file as "secureCheckout.orderNumber"
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    And again i open the site url
    And I click on My Account button on home page
    When I click on My Orders
    Then I should be on Order History page
    And I navigate to my Order "secureCheckout.orderNumber"

  @PayByInstallment
  @excludeSharkUK
  @excludeNinjaUK
  @excludeNinjaDE
  @excludeSharkDE
  @excludeNinjaIT
  @excludeSharkIT
  @excludeNinjaES
  @excludeSharkES
  @excludeNinjaFR
  @excludeSharkFR

  Scenario: Place order as login user with Payment method - Brain tree (Pay by Installment)
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.order.email" and "login.order.password"
    Then I am successfully logged in
    When I click on My Orders
    Then I should be on Order History page
    When I navigate to my Order "order.history.page.installmentOrderNumber"
    When I click on Instalment details for "order.history.page.installmentOrderNumber"
    Then I should see instalment page for "order.history.page.installmentOrderNumber"

  @OrderStatusPage
  Scenario: Validate Order Status Page
    And  I navigate to the Order Status Page
    And click on accept cookies if visible
    And click on accept email if visible
    And I should enter "order.no" order number
    And I should enter "delivery.postal.code" the delivery postal code
    When I click on Find My Order Button
    Then I should be on Order Details page
