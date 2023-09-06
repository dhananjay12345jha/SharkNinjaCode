##############################################################################################
##################API Used of Warranty Controller are given below#############################
## GET/warranty/search-->Search a warranty record in Database ##
## @Author: Sanket Jha

@ApiTest_CA
@ApiTest_US
Feature: To validate scenarios for API GET/Warranty/Search

  Background: Creating New BlackList and Adding Product Creation And warranty Registration
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "1YRLTD"
    Given that set warranty duration as 12 months

  Scenario: to verify that entries should be found when searched with created date(epoch date format)
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the created date returned in response of record creation into Search warranty api
    And set the limit in Search Warranty api as 2
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero

  Scenario: to verify that entries should be found when searched with customer id
    Given that set quantity as 2
    Given Product Registration And Warranty Creation api has been hit
    When set the customer id returned in response of record creation into Search warranty api
    And set the limit in Search Warranty api as 2
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero

  Scenario: to verify that entries should be found when searched with last modified date
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the last modified date returned in response of record creation into Search warranty api
    And set the limit in Search Warranty api as 2
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero


  Scenario: to verify that number of entries in response should be equal to the limit number
    When set only limit as 2
    Then hit the GET/Warranty/Search api
    Then total number of records should be equal to 2


  Scenario: to verify that error message and 400 should be there when setting limit as zero
    When set only limit as 0
    Then hit the GET/Warranty/Search api
    Then response code of SearchProductRegistration api should be 400
    #Then error message should be there "Limit can not be more than 50 or less than 1" error message has changed
    Then error message should be there "At least put one input parameter"

  Scenario: to verify that entries should be found when searched with order number
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the order number returned in response of record creation into Search warranty api
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero


  Scenario: to verify that only one entry should be found when searched with registration id
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the registration id returned in response of record creation into Search warranty api
    Then hit the GET/Warranty/Search api
    Then total number of records should be equal to 1


  Scenario: to verify that entries should be found when searched with Registration as "REGISTERED"
    When set only the registration status as "REGISTERED"
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero


  Scenario: to verify that entries should be found when searched with SKU
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the sku number returned in response of record creation into Search warranty api
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero


  Scenario:to verify that entries should be found when searched with warranty effective date
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    When set the warranty effective date returned in response of record creation into Search warranty api
    Then hit the GET/Warranty/Search api
    Then total number of records should be greater than zero
