## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate user is able to retrieve email preferences from Bronoto

  Scenario: Hit Api with valid email id
    Given Uri for Email "icm.emailId.to.generate.token" has been obtained
    And Token has been generated corresponding to uri obtained
    Then hit the bronto email subscription api
    Then response of bronto api should be equal to 200
    And in response link should be there against key "BronotoNewsletterURL"
    And hit the link response should be 200

