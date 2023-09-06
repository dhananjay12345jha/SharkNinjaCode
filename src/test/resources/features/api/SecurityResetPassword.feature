## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate that agent is able to reset the password

  Scenario: To verify new password has been sent to registered email id when reset password
    Given email id "preeti.gupta@wundermanthompson.com" has been set to the request body
    Then hit the request
    Then response code should be equal to 202
    And message in response should be equal to "Accepted"

  Scenario: To verify that no password should be sent to non registered email id
    Given email id "abc@test.com" has been set to the request body
    Then hit the request
    Then response code should be equal to 400
    And message in response should be equal to "Error finding login id"

#  In first scenario unable to find the correct email id please update the same