## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate Basket APIs "GET Basket","Create Basket"

  Background: Getting Uri for a particular Customer EmailId and Generating Token
    Given Uri for Email "icm.emailId.to.generate.token" has been obtained
    And Token has been generated corresponding to uri obtained

  Scenario: To verify that user is successfully able to create basket
    When hit post request to create basket
    Then status code should be 200
    And value of key title in response should not be null or empty


  Scenario: To verify that user is able to get basket with basket key
    When hit get basket request to fetch basket Key
    Then status code should be 200
    And value of key ID in response should be equal to basketKey

