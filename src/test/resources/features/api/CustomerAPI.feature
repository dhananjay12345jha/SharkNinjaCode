## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To verify customer related api

  Scenario: To verify user is able to create ICM Account without password
    Given email id has been generated against which account will be created
    Then hit the account creation api request
#    Then in response it should return uri value as "SharkNinja-GB-Site/-/customers/-"
    Then response code should be shown as 201
    And in response customer number should be there
    Then again hit the account creation api with same email id
#    Then in response it should return "customer already exists"
    Then in response same customer number should be there