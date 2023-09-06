@testAll
@regression
@regressionSharkCAOld
Feature: Verify Cart Product Add, update, delete and promo code

  Background: Add Product to cart search with name Vacuum Cleaner and search for product SKU:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file

  @smokeSharkCAOld
  @cartProductVerify
  Scenario: Verify products added to cart are visible on cart page
    When I open search bar and entered the product as "vacuum cleaner" or "aspirateur"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    Then page title should contains text "Search Result for 'vacuum cleaner'" or "Résultat de recherche pour 'aspirateur'."
    And search result page should display text contains "vacuum cleaner" or "« aspirateur »"
    And validate that number of product tiles shown should be greater than zero
    And language selected should be equal to "select.language.to.test"
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"
    And number shown over cart icon should equal to "0"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "1"
    When I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    And I validate that product title,model number and product price shown should be same as that on pdp page
    And language selected should be equal to "select.language.to.test"

  @smokeSharkCAOld
  Scenario: Adding multiple products and check they are visible in cart
    When I open search bar and entered the product as "ZU62C" or "ZU62C"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    Then page title should contains text "Search Result for 'ZU62C'" or "Résultat de recherche pour 'ZU62C'."
    And search result page should display text contains "ZU62C" or "« ZU62C »"
    And validate that number of product tiles shown should be greater than zero
    And language selected should be equal to "select.language.to.test"
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"
    And number shown over cart icon should equal to "0"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "1"
    When I open search bar and entered the product as "AZ1002C" or "AZ1002C"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    Then page title should contains text "Search Result for 'AZ1002C'" or "Résultat de recherche pour 'AZ1002C'."
    And search result page should display text contains "AZ1002C" or "« AZ1002C »"
    And validate that number of product tiles shown should be greater than zero
    And language selected should be equal to "select.language.to.test"
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And language selected should be equal to "select.language.to.test"
    And number shown over cart icon should equal to "1"
    And I note product title,model number and price
    When I click on add to cart button
    Then number shown over cart icon should equal to "2"
    When I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    Then I validate that multiple products which are added should be there in cart

  Scenario: To validate cart having multiple products and delete one product cart updates
    When I open search bar and entered the product as "ZU62C" or "ZU62C"
    Then I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    Then I open search bar and entered the product as "AZ1002C" or "AZ1002C"
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
    When I open search bar and entered the product as "ZU62C" or "ZU62C"
    Then I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    Then I open search bar and entered the product as "AZ1002C" or "AZ1002C"
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
    When I open search bar and entered the product as "ZU62C" or "ZU62C"
    Then I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    Then I open search bar and entered the product as "AZ1002C" or "AZ1002C"
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
    When I open search bar and entered the product as "AZ1002C" or "ZU62C"
    Then I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
    Then I open search bar and entered the product as "HZ400UKT" or "HZ400UKT"
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
    Then enter promoCode as "Sale25"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "Sale25" should be visible with each item in the basket
    And also new price of each item should be less than the price before discount
    And now Subtotal amount value should be less than before when discount applied

  Scenario: Validating calculation after Applying PromoCode on complete order
#    When I open search bar and entered the product as "AZ1002C" or "ZU62C"
    When I open search bar and entered the product as "HV300C" or "HV300C"
    Then I clicked on the search button
    And validate that number of product tiles shown should be greater than zero
    Then I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    And I note product title,model number and price
    Then I click on add to cart button
#    Then I open search bar and entered the product as "HZ400UKT" or "HZ400UKT"
    Then I open search bar and entered the product as "LA322" or "LA322"
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
    Then enter promoCode as "Sale20"
    And clicks on apply promoCode button
    Then text "Discount applied" or "Rabais appliqué" should be shown
    And applied promocode "Sale20" should be shown under shipping and taxes calculation
    And discounted amount applied at order level should not be zero

