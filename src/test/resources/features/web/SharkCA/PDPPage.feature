@testAll
@regressionSharkCA
@PDP
@regSharkCASuit2
Feature: Verify Product Detail page

  Background: To open home page and select language
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    When I click on any product from product list
    Then I should be on PDP page of that product

  @validatePDPComponent
  Scenario: Verify PDP & related tabs on page
    Then all the mandatory components should be present

  @validatePDPQuantitySelector
  Scenario: Verify to add more than 1 quanity to basket and Verify add to basket and navigate to it
    When  I enter product quantity with "product.valid.quantity"
    When I click on add to cart button
    Then I should see cart overlay is updated with "product.valid.quantity"

  @validatePDPQuantitySelector
  Scenario: Verify quantity field & their validations
    When I enter product quantity with "product.negative.quantity"
    Then I should see error message that "quantity.invalid.error.message"
    When I enter product quantity with "product.zero.quantity"
    Then I should see error message that "quantity.invalid.error.message"
    When I enter product quantity with "product.max.quantity"
    Then I should see error message that "max.quantity.Invalid.Error"
    When I enter product quantity with "product.valid.quantity"
    When I click on add to cart button
    Then I should see cart overlay is updated with "product.valid.quantity"




