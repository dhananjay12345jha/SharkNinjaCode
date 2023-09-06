

####--------API's used: POST register, POST refurbished and GET Warranty Apis for product refurbish-------###

@ApiTest_CA
@ApiTest_US
Feature: To verify that user is able to refurbished existing warranty

  Background:
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "1YRLTD"
    Given that set warranty duration as 12 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    Then set the regId as ID which we get in response of above post api
    Then hit the GET api to fetch detail against the ID for validation
    Then validate that in response field "warrantyEndDate" should be there and not empty
    And validate that in response field "regStatus" should be shown "REGISTERED"

#@verifyRefurbishAPI
Scenario: To verify user can hit refurbish API of warranty product providing valid smart serial number
    Then hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then response of refurbish warranty api should be 200
    And message from refurbish API response should be "Matching warranty record(s) of SSN are refurbished."
    And Verify provided smart serial number shown in "successfullyUpdated" in response body

#@verifyNotFoundSSN
Scenario: To verify invalid smart serial number is shown in not found list
    When hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then Invalid smart serial number shown in "notFound" in response body

#@verifyAlreadyRefurbishedSSN
Scenario: To verify already refurbished SSN numbers shown in already refurbished list
    Then hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then response of refurbish warranty api should be 200
    And message from refurbish API response should be "Matching warranty record(s) of SSN are refurbished."
    And Verify provided smart serial number shown in "alreadyRefurbished" in response body
    And Invalid smart serial number shown in "notFound" in response body

#@verifyRegistrationStatusRefurbished
Scenario: To verify registration status of refurbished product
    Then hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then hit the GET api to fetch detail against the ID for validation
    And validate that in response field "regStatus" should be shown "REFURBISHED"

#@verifyUnableToRegisterWithRefurbishedSSN
Scenario: To verify unable to register product with refurbished SSN
    Then hit the POST api to refurbish the product in warranty registration by providing smart serial numbers
    Then Product Registration And Warranty Creation api has been hit
    Then response of product registration api should be 400