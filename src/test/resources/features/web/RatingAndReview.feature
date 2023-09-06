@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkIT
@regNinjaIT
@regNinjaDE
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeSharkIT
@smokeNinjaIT
@smokeNinjaDE
@regSharkDESuite1
@regNinjaDESuite1
@regSharkITSuite1
@regNinjaITSuite1
@regNinjaUKSuite1
@regSharkUKSuite1
Feature: Verify Rating And Review

  Background: Navigate to product List Page
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
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    When I search for the product "product.sku"

  @rateAndReview
  @excludeSharkDE
  @excludeNinjaDE
  @excludeSharkIT
  @excludeNinjaIT

  #this scenario has been covered as a part of automation for Shark-Ninja Italy but it will pass once the rating will reflect on UI
    #that's why we are excluding it for both Shark-Ninja Italy
    #Product for Shark-HZ500EUT and for Ninja-SP101EU
  Scenario: Verify rating and review of a product
    Then I should see rating of a product at plp level "product.sku"
    And I select the product from search result page "product.sku"
    And I click on Rate And Review Button
    Then I should see text Rating Snapshot