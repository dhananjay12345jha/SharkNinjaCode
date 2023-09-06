

####--------API's Used: POST, PUT and GET Apis to verify product deregistration-------###

@ApiTest_CA
@ApiTest_US
Feature: To verify that user is able to deregister Product and Warranty Creation

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


#@productDeregistration
Scenario: Verify that warranty deregistered against valid registration ID
    Then hit the PUT api to deregister the warranty against the ID
    Then response of deregister warranty api should be 200
    And message from response should be "Successfully De-registered."
    Then hit the GET api to fetch detail against the ID for validation
    And validate that in response field "regStatus" should be shown "DEREGISTERED"

#@productDeregisterRegIDFormatIncorrect
Scenario: Verify when registration Id format incorrect then deregister warranty api should be 400
    Then hit the PUT api to deregister the warranty against the incorrect format Reg ID
    Then response of deregister warranty api should be 400
    And message from response should be "Bad request - Registration Id should be Mininum 6 characters and in proper format"

#@productDeregisterRegIDInactive
Scenario: Verify when registration Id format inactive then deregister warranty api should be 404
    Then hit the PUT api to deregister the warranty against the ID
    Then hit the PUT api to deregister the warranty against the ID
    Then response of deregister warranty api should be 404
    And message from response should be "No active warranty registration found for regId"
