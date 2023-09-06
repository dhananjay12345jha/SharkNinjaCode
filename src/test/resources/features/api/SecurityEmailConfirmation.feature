## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate api "snap/security/emailconfirmation"

  Background: Generating authentication token which will used in api header
    Given Uri for Email "icm.emailId.to.generate.token" has been obtained
    And Token has been generated corresponding to uri obtained

  Scenario: To verify that email confirmation should be sent for new email id
    When set the email "testUser@xyz.com" in the request body
    Then request has been hit
    And response should be shown as 201
    Then response should contains "/snap/security/emailconfirmation/"

  Scenario: To verify that email confirmation should not be sent for already verified email id
    When set the email "sanket.jha@wundermanthompson.com" in the request body
    Then request has been hit
    And response should be shown as 422
    Then response should contains "Unprocessable Entity"
@Tr
  Scenario: To verify that sending email id as empty then in response it would show 400 bad request
    When set the email "" in the request body
    Then request has been hit
    And response should be shown as 400
    Then response should contains "{"error":"The following attributes are missing: email"}"

