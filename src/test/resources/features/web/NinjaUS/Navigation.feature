@testAll
@regNinjaUS
@regNinjaUSSuit2
Feature: Verify navigation

  Background: Navigate to home page
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @smokeNinjaUS
  Scenario: Navigate to product list page
    When I hover on products link in header
    And click upright vacuum category
    Then product list should be displayed

  @smokeNinjaUS
  Scenario: Navigate to product details page
    When I hover on products link in header
    And click first category in list
    Then product list should be displayed
    When I click on any product from product list
    Then I should be on PDP page of that product

  @partsAndAccessories
  Scenario: Navigate to part and Accessory
    When I click on Parts and Accessories
    Then I navigate to chosen parts and accessories page

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