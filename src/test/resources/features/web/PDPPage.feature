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
@regSharkFRSuite2
@regNinjaFRSuite2
@regSharkESSuite2
@regNinjaESSuite2
@regSharkITSuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
Feature: Verify PDP page and its related tabs

  Background: Navigate to Search page based on valid input
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And click on accept email if visible
    And click on accept cookies if visible
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
    When I search for the product "product.specificSKU2"
    And I select the product from search result page "product.specificSKU2"
    And click on accept email if visible

  @PDPRelatedTabs

  Scenario: Verify related tab on PDP page
    Then Verify PDP is displayed corresponding to clicked product
    And I should see add to Basket button
    And I should see add quantity field
    And I should see Features link in footer
    And I should see MoreDetails link in footer
    And I should see Tech spec link in footer
    And I should see In The Box link in footer
    And I should see FAQs link in footer
    And I should see Downloads link in footer
    When I click Features link in footer
    Then Features div should be displayed
    When I click More Details link in footer
    Then More Details div should be displayed
    When I click Tech spec link in footer
    Then Tech spec div should be displayed
    When I click In The Box  link in footer
    Then In The Box  div should be displayed
    When I click FAQs link in footer
    Then FAQs div should be displayed
    When I click Downloads  link in footer
    Then Downloads div should be displayed
  #When I click Parts/Accessories  link in footer
  #Then Parts/Accessories  div should be displayed


  Scenario: Verify add to basket and navigate to it
    And I add the product to cart "product.specificSKU2"


  Scenario: Verify to add more than 1 quantity to basket
    When  I update product quantity with "product.valid.quantity"
    And  I click on Add To Basket Button
    Then I should see the the cart is updated with "product.valid.quantity"

#currently the validation message for this scenario is not showing in spanish language, and informed
  #the same with lead, till then we are using same language for the validation to avoid script failure
  Scenario: Verify quantity field & their validations
    When I update product quantity with "product.negative.quantity"
    Then I should see error message that please enter 1 or more
    When I update product quantity with "product.zero.quantity"
    Then I should see error message that please enter 1 or more
    When I update product quantity with "product.max.quantity"
    Then I should see error message that Please enter 100 or less
    When I update product quantity with "product.valid.quantity"
    Then Add to basket button is enabled

    #these below two features are no more with any of the channels as per slack discussion with Rachit on 1 MAY 2023
  #so we are excluding these scenarios
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify Paypal Pay later text on PDP
    Then Paypal Pay later text should be present

  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkUK
  @excludeNinjaUK
  @excludeSharkFR
  @excludeNinjaFR
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  Scenario: Verify Paypal Credit text on PDP
    Then Paypal Credit text should be present