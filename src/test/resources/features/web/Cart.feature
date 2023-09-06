@regSharkUK
@regSharkIT
@regSharkDE
@regNinjaUK
@regSharkES
@regNinjaES
@regNinjaDE
@regNinjaIT
@regSharkDESuite1
@regSharkITSuite1
@regNinjaDESuite1
@regSharkESSuite1
@regNinjaESSuite1
@regNinjaITSuite1
@regNinjaUKSuite1
@regSharkUKSuite1
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Verify Cart Product Add, update, delete and promo code

  Background: Add Product to cart
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
    Given User is setting the stock quantity for EU with the Sku as CB350EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
    Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
    When I search for the product "product.specificSKU"
    And click on accept email if visible
	And I select the product from search result page "product.specificSKU"
    And I note the price of the product
    And I add the product to cart "product.specificSKU"

  @cartProductVerify
  @smokeSharkUK
  @smokeSharkIT
  @smokeSharkDE
  @smokeNinjaUK
  @smokeSharkES
  @smokeNinjaES
  @smokeNinjaIT
  @smokeNinjaDE

  Scenario: Verify products added to cart are visible on cart page
    Then I should see added product in Cart "product.specificSKU"

  @cartProductDelete
  Scenario: Verify cart product deleted
#	When I go to cart
    Then I should see added product in Cart "product.specificSKU"
    When I click on delete product from cart
    Then Added product should be deleted

  @cartProductQuantityUpdate
  Scenario: Verify products quantity updated and cart calculation correct
    Then I should see added product in Cart "product.specificSKU"
    When I search for the product "product.specificSKU2"
    And I select the product from search result page "product.specificSKU2"
    And I note the price of the product
    And I add the product to cart "product.specificSKU2"
    Then I should see added product in Cart "product.specificSKU2"
    Then I update quantity of cart product having sku "product.specificSKU" to "product.valid.quantity"
    Then I wait for the loader so that price would get refreshed
    And I update quantity of cart product having sku "product.specificSKU2" to "product.valid.quantity"
    Then I wait for the loader so that price would get refreshed
#	And I click on update cart
#	Then Product quantity updated
    And verify quantity of product multiply single unit price should equal to total price
    And sum of total price of all products should be equal to subtotal value
#    And sum of Final price should be equal to sum of subtotal, VAT and Shipping


  Scenario: Verify products with single quantity added in and cart calculation correct
    Then I should see added product in Cart "product.specificSKU"
    When I search for the product "product.specificSKU2"
    And I select the product from search result page "product.specificSKU2"
    And I note the price of the product
    And I add the product to cart "product.specificSKU2"
    Then I should see added product in Cart "product.specificSKU2"
    Then I update quantity of cart product having sku "product.specificSKU" to "product.valid.quantity"
    And I update quantity of cart product having sku "product.specificSKU2" to "product.valid.quantity"
#	And I click on update cart
#	Then Product quantity updated
    And verify quantity of product multiply single unit price should equal to total price
    And sum of total price of all products should be equal to subtotal value
#    And sum of Final price should be equal to sum of subtotal, VAT and Shipping

  @cartProductQuantityInvalid
  @excludeForMobile
  Scenario: Verify products quantity error messages
    Then I should see added product in Cart "product.specificSKU"
    When I update quantity of cart product having sku "product.specificSKU" to "product.negative.quantity"
    Then I should see error message that quantity is invalid
    When I update quantity of cart product having sku "product.specificSKU" to "product.max.quantity"
    Then I should see error message that quantity is too large

  @verifyMiniBasket
  @excludeForMobile
  Scenario: Verify cart product quantity and price in header mini basket
    Then I should see added product in Cart "product.specificSKU"
    And I should see quantity and price in mini cart header
    When I click on mini cart header
#	Then I should see quantity and price in mini cart
    Then I should see updated quantity and price in mini cart header
    Then I close mini cart
    When I update quantity of cart product having sku "product.specificSKU" to "product.valid.quantity"
#	And I click on update cart
    Then I should see updated quantity and price in mini cart header
    When I click on mini cart header
    Then I can view updated quantity and price in mini cart
    Then I close mini cart
    When I search for the product "product.specificSKU2"
    And I select the product from search result page "product.specificSKU2"
#    And I select the product from search result page "product.specificSKU2"
    And I add the product to cart "product.specificSKU2"
#	Then I should see total quantity and price in mini cart header
    Then I should see updated quantity and price in mini cart header
    When I click on mini cart header
#	Then I can view total quantity and price in mini cart
    Then I can view updated quantity and price in mini cart

#  @mobileVerifyCartHeader   commenting this scenario as it is specefic to mobile
#  Scenario: Verify cart product quantity and price on mobile header mini basket
#    Then I should see added product in Cart "product.specificSKU"
#    And I should see quantity in cart header
#    When I update quantity of cart product having sku "product.specificSKU" to "product.valid.quantity"
#    Then I should see updated quantity in cart header

  @verifyItemDiscountCouponCode
  Scenario: Verify Item discount applied when valid promo code applied
    Then I should see added product in Cart "product.specificSKU"
    When I click on promo entry link on cart page
    And I enter discount coupon code "cart.item.discount"
    And I click on apply promo button
    Then I should see message above order summary that coupon applied successfully
    And I should see Item discount in cart summary


  @verifyOrderDiscountCouponCode
  @excludeSharkIT
  @excludeNinjaIT
  @excludeSharkES
  @excludeNinjaES
  @excludeSharkFR
  @excludeNinjaFR
# we are excluding this scenario for Shark-Ninja-IT/ES/FR, we need the details link at a common place for both UK and IT, after discuss with Rachit, will include
  Scenario: Verify Order discount applied when valid promo code applied
    Then I should see added product in Cart "product.specificSKU"
    When I click on promo entry link on cart page
    And I enter discount coupon code "cart.order.discount"
    And I click on apply promo button
    Then I should see message above order summary that coupon applied successfully
    And I should see discount details link on order review summary
    When I click on discount details link
    Then I should see Promotion applied message


#Team is checking for valid promocode
#  @verifyOrderBluelightPromotioncode
#  Scenario: Verify cart discount applied when valid bluelight promo code applied
#    Then I should see added product in Cart "product.specificSKU"
#    When I click on promo entry link on cart page
#    And I enter discount coupon code "cart.bluelight.discount"
#    And I click on apply promo button
#    Then I should see message above order summary that coupon applied successfully
#    And I should see discount details link on order review summary
#    When I click on discount details link
#    Then I should see Promotion applied message

#  @OrderwithincorrectAmount
#  Scenario: Verify order cannot be placed between 2000 to 2999
#    Then I should see added product in Cart "product.specificSKU"
#    Then I update quantity of cart product having sku "product.specificSKU" to have incorrect order amount
#    Then I wait for the loader so that price would get refreshed
#    And I go to checkout
#    And I fill in the billing details
#    When I select pay by card radio button
#    Then I place an order using Braintree payment method Pay With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
#    And verify error message for incorrect order amount