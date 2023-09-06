@testAll
@regressionNinjaCA
@regNinjaCASuit2
Feature: Verify navigation

  Background: Navigate to home page
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file

  @NavigateProductListSNUS
  @smokeNinjaCA
  Scenario: Navigate to product list page
    When I hover on products link in header
    And click upright vacuum category
    Then product list should be displayed
    And language selected should be equal to "select.language.to.test"

  @NavigateProductDetailsSNUS
  @smokeNinjaCA
  Scenario: Navigate to product details page
    When I hover on products link in header
    And click upright vacuum category
    Then product list should be displayed
    When I click on any product from product list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"

  @NavigateAddtoCart
  Scenario: Navigate to pdp and check add to cart
    When I hover on products link in header
    And click upright vacuum category
    Then product list should be displayed
    When I click on any product from product list
    Then I should be on PDP page of that product
    When  I enter product quantity with "product.valid.quantity"
    When I click on add to cart button
    Then I should see cart overlay is updated with "product.valid.quantity"