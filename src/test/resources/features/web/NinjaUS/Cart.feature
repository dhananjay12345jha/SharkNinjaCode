@testAll
@regNinjaUS
@regNinjaUSSuit1
Feature: Verify Cart Product Add, update, delete and promo code

  Background: Add Product to cart search with name Vacuum Cleaner and search for product SKU:
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @smokeNinjaUS
  @cartProductVerifySNUS
  Scenario: Verify products added to cart are visible on cart page
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And click on accept cookies if visible
    Then page title should contains text "Search Result for " or "Résultat de recherche pour "
    And search result page should display text contains "product1"
    And validate that number of product tiles shown should be greater than zero
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And number shown over cart icon should equal to "0"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "1"
    When I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page

  @smokeNinjaUS
  Scenario: Adding multiple products and check they are visible in cart
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And click on accept cookies if visible
    Then page title should contains text "Search Result for " or "Résultat de recherche pour "
    And search result page should display text contains "product1"
    And validate that number of product tiles shown should be greater than zero
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And number shown over cart icon should equal to "0"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "1"
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    Then page title should contains text "Search Result for " or "Résultat de recherche pour "
    And search result page should display text contains "product2"
    And validate that number of product tiles shown should be greater than zero
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And number shown over cart icon should equal to "1"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "2"
    When I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    Then I validate that multiple products which are added should be there in cart

  Scenario: To validate cart having multiple products and delete one product cart updates
    When I open search bar and entered the product as "product1"
    Then I clicked on the search button
    And click on accept cookies if visible
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that multiple products which are added should be there in cart
    Then I delete product by clicking Remove Item button showing at location 1 from the cart
    Then product would get deleted which was there at location 1 and cart get updated


  Scenario: To validate calculation of Final price according to number of products and quantity
    When I open search bar and entered the product as "product1"
    Then I clicked on the search button
    And click on accept cookies if visible
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that multiple products which are added should be there in cart
    Then I calculate the total sum of products cost
    Then sub total value should be equal to sum of products costs
    And I validate that estimated cost should be equal to subtotal plus shipping cost


  Scenario: To validate calculation on cart page when increasing the product quantity
    When I open search bar and entered the product as "product1"
    Then I clicked on the search button
    And click on accept cookies if visible
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that multiple products which are added should be there in cart
    Then I increase the quantity of product to "01" for the product shown at row number 1
    Then I calculate the total sum of products cost
    Then sub total value should be equal to sum of products costs
    And I validate that estimated cost should be equal to subtotal plus shipping cost


  Scenario: Validating calculation after Applying PromoCode over each Item in cart
    When I open search bar and entered the product as "product1"
    Then I clicked on the search button
    And click on accept cookies if visible
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page
    Then I calculate the total sum of products cost
    Then enter promoCode as "cart.item.discount"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "cart.item.discount" should be visible with each item in the basket
    And also new price of each item should be less than the price before discount
    And now Subtotal amount value should be less than before when discount applied


  Scenario: Validating calculation after Applying PromoCode on complete order
    When I open search bar and entered the product as "product1"
    Then I clicked on the search button
    And click on accept cookies if visible
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    When I open search bar and entered the product as "product2"
    And I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page
    Then I calculate the total sum of products cost
    Then enter promoCode as "cart.order.discount"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "cart.order.discount" should be shown under shipping and taxes calculation
    And discounted amount applied at order level should not be zero

