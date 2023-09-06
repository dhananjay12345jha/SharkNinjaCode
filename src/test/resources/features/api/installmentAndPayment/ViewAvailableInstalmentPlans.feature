##################################################################################################################
##################################### API Used of Instalment Controller are given below###########################
   ## GET /instalmentPlansDetails-->View a list of available instalment plans
   ## @Author: Sumeet Kumar


@ApiTest_UK
@ApiTest_DE
@ApiTest_US
Feature: Verify GET instalment plans details API (View a list of available instalment plans)

  Background: Setting parameters for Instalment Plan details
    Given The plan modified starting date is set
    And the plan modified ending date is set
    And Page number is set to 0
    And sorting order is set to "asc"

#  @GetInstalmentPlanDetails
  Scenario: Fetch available instalment plan details through GET instalment plan details API
    When GET Instalment Plan details api has been hit
    Then response of Instalment plan details api should be 200
    And data list returned in response of instalment plan details should be greater than zero
    And value of the key "totalRecords" in instalment details API should be greater than zero

#  @DateFromAndDateToIncorrect
  Scenario: Validate for error code 400 when date range is incorrect while fetching instalment plan details
    When GET Instalment Plan details api has been hit with incorrect Date
    Then response of Instalment plan details api should be 400
