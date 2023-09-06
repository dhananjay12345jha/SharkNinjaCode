@regSharkDEMicroSite
@regSharkUKMicroSite
@regNinjaUKMicroSite
@regNinjaDEMicroSite

Feature: My Orders : I want to Login as a valid user and verify order history/detail page

  Background: Go To Home Page
   Given I navigate to the "Home" page on MicroSite
    When I select last product tile
    And I add product to cart

  @excludeSharkUKMicroSite
  @excludeNinjaUKMicroSite
  @excludeNinjaUKMicroSiteNexus
  @excludeSharkUKMicroSiteNexus

  Scenario: Verify Order Id By Placing order as login user with Payment method - Brain tree (Pay by Installment)
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    When I select Braintree Installment payment method
    And I place an order by brain tree instalment With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully
    And save the order number into the properties file as "secureCheckout.orderNumber"
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    And again i open the mainsite url
    And click on accept cookies if visible
    And I click on sign in link
    And I should be on log in page
    When I enter valid "login.email" and "login.password"
    Then I am successfully logged in
    And I click on My Account button on home page
    When I click on My Orders
    Then I should be on Order History page
    And I navigate to my Order "secureCheckout.orderNumber"
    When I click on Instalment details for "secureCheckout.orderNumber"
    Then I should see instalment page for "secureCheckout.orderNumber"

