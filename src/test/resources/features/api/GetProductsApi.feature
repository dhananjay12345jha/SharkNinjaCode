## @Author: Sanket Jha

@ApiTest
@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate ICM Product APIs

  ###Cateogy which is used in this case having total 8 entries verified manually from backend###
  Scenario: API--> "GET /categories/{categoriesKey}/products" and search with particular category key then limited amount of products will get display
    Given api has been hit with category key "product.category.key"
    When response code is 200
    Then validate that number of products returned in response should be 8
    And also print the returned product lists


  Scenario: API-->"GET /categories/{categoriesKey}/products/{productKey}"search with product key and category key
    Given api has been hit with category key "product.category.key" and product key "product.key"
    When response code is equals to 200
    Then validating response contains only single product
    And having sku id "product.key"


  Scenario: API-->"GET /products/{key of item}", Search product with product item key.
    Given api has been hit with product key "product.key"
    When response code is equals 200
    Then validate response should contains only single product
    And response having sku id "product.key"


  Scenario: API-->"GET /products" It will returns the list of products
    Given api has been hit with amount 10
    When response comes out to be 200
    Then validating total number of items returned should not be equal to zero
    And number of product returned should be 10
