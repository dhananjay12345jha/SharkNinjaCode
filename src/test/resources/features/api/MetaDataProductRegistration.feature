## @Author: Sanket Jha

@Regression
@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate metadata product registration response should be 200 and validating product and location category for Shark And Ninja

  Scenario: To validate api response should be 200
    When metadata api has been hit
    Then response should be equal to 200
    When metadata api has been hit
    Then validate that "SharkProductTypes" should not be empty
    Then validate that "NinjaProductTypes" should not be empty
    Then validate that "SellingLocations" should not be empty
#    Then validate that "SharkSellingLocations" should not be empty
#    Then validate that "NinjaSellingLocations" should not be empty


