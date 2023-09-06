##########################################################################################################
###############################API Used of BLACKLIST Controller are given below###########################
## GET /blacklists/blacklistitems-->Get Black list item Details##
## @Author: Sanket Jha


@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate different scenarios of GET Blacklist items api

  Scenario: Verify that GET API returns multiple records for all blacklisted customer_id
    Given set the value of limit as 100 and page numbers as 0
    And setting the value type as "CUSTOMER_ID"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "blackListItemType" returned in response should be "CUSTOMER_ID"
    And "totalBlackListRecords" returned in response should be greater that zero

  Scenario: Verify that GET API returns multiple records for all blacklisted Postal_code
    Given set the value of limit as 100 and page numbers as 0
    And setting the value type as "POSTAL_CODE"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "blackListItemType" returned in response should be "POSTAL_CODE"
    And "totalBlackListRecords" returned in response should be greater that zero

  Scenario: Verify that GET API returns multiple records for all blacklisted Phone
    Given set the value of limit as 100 and page numbers as 0
    And setting the value type as "PHONE"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "blackListItemType" returned in response should be "PHONE"
    And "totalBlackListRecords" returned in response should be greater that zero


  Scenario: Verify that GET API returns multiple records for all blacklisted Email
    Given set the value of limit as 100 and page numbers as 0
    And setting the value type as "EMAIL"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "blackListItemType" returned in response should be "EMAIL"
    And "totalBlackListRecords" returned in response should be greater that zero

  Scenario: Verify that GET API returns multiple records for all blacklisted Address
    Given set the value of limit as 100 and page numbers as 0
    And setting the value type as "ADDRESS"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "blackListItemType" returned in response should be "ADDRESS"
    And "totalBlackListRecords" returned in response should be greater that zero


# adding up combination of value and valueType,



