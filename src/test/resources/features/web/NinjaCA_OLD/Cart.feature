@testAll
@regression
Feature: Verify Cart Product Add, update, delete and promo code

  Background: Add Product to cart search with name Ninja and search for product SKU:
    Given I navigate to the "Home" page
    And select language defined in property file
    And click on accept cookies if visible
    When I open search bar and entered the product as "ninja" or "ninja"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    Then page title should contains text "Search Result for 'ninja'" or "Résultat de recherche pour 'ninja'"
    And search result page should display text contains "ninja" or "« ninja »"
    And validate that number of product tiles shown should be greater than zero
    And language selected should be equal to "select.language.to.test"

  @smokeNinjaCAOLD
  @cartProductVerify
  Scenario: Verify products added to cart are visible on cart page
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


#    Then close the product description tab and refersh the window
#    Then cart button should display one
#    And i click on cart button
#    Then i should be on cart page showing text "Cart" or "Panier"
#    And product should be there with the same model number which has been shown on description

#  Scenario: Verify product added in cart have all details matched
	
#@cartProductDelete
#Scenario: Verify cart product deleted
##	When I go to cart
#	Then I should see added product in Cart "product.specificSKU"
#	When I click on delete product from cart
#	Then Added product should be deleted
#
#@cartProductQuantityUpdate
#Scenario: Verify products quantity updated and cart calculation correct
#	Then I should see added product in Cart "product.specificSKU"
#	When I update quantity of cart product with "product.valid.quantity"
##	And I click on update cart
#	Then Product quantity updated
#	And Cart calculation is correct
#
#@cartProductQuantityInvalid
#Scenario: Verify products quantity error messages
#	Then I should see added product in Cart "product.specificSKU"
#	When I update quantity of cart product with "product.negative.quantity"
#	Then I should see error message that quantity is invalid
#	When I update quantity of cart product with "product.max.quantity"
#	Then I should see error message that quantity is too large
#
#@verifyMiniBasket
#Scenario: Verify cart product quantity and price in header mini basket
#	Then I should see added product in Cart "product.specificSKU"
#	And I should see quantity and price in mini cart header
#	When I click on mini cart header
#	Then I should see quantity and price in mini cart
#	Then I close mini cart
#	When I update quantity of cart product with "product.valid.quantity"
##	And I click on update cart
#	Then I should see updated quantity and price in mini cart header
#	When I click on mini cart header
#	Then I can view updated quantity and price in mini cart
#	Then I close mini cart
#	When I search for the product "search.validInput"
#	And I select the product from search result page "product.specificSKU2"
#	And I add the product to cart
#	Then I should see total quantity and price in mini cart header
#	When I click on mini cart header
#	Then I can view total quantity and price in mini cart
#
#@mobileVerifyCartHeader
#Scenario: Verify cart product quantity and price in header mini basket
#	Then I should see added product in Cart "product.specificSKU"
#	And I should see quantity in cart header
#	When I update quantity of cart product with "product.valid.quantity"
#	Then I should see updated quantity in cart header
#
#@verifyItemDiscountCouponCode
#Scenario: Verify Item discount applied when valid promo code applied
#	Then I should see added product in Cart "product.specificSKU"
#	When I click on promo entry link on cart page
#	And I enter discount coupon code "cart.item.discount"
#	And I click on apply promo button
#	Then I should see message above order summary that coupon applied successfully
#	And I should see Item discount in cart summary
#
#@verifyOrderDiscountCouponCode
#Scenario: Verify Order discount applied when valid promo code applied
#	Then I should see added product in Cart "product.specificSKU"
#	When I click on promo entry link on cart page
#	And I enter discount coupon code "cart.order.discount"
#	And I click on apply promo button
#	Then I should see message above order summary that coupon applied successfully
#	And I should see discount details link on order review summary
#	When I click on discount details link
#	Then I should see Promotion applied message