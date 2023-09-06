################################################################################################
##################API Used of WARRANTY Controller are given below#############################
## POST/warranty/register-->Add a warranty record in Database
## GET/warranty/{regId}-->Retrieve warranty record from the repository
## @Author: Sanket Jha

@ApiTest_CA
@ApiTest_US
Feature: To verify that user is able to create Product Registration and Warranty Creation


  Scenario: To Validate POST AND GET apis for product registration and warranty creation working as expected.
    Given Generating a unique customer Id and smart serial number
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    Then validate that in response field "regId" should be there and not empty
    Then validate that in response field "regCountry" should be there and not empty
    Then validate that in response field "recordSource" should be there and not empty
    Then validate that in response field "purchaseSourceName" should be there and not empty
    Then validate that in response field "orderNo" should be there and not empty
    Then validate that in response field "customerId" should be there and not empty
    Then validate that in response field "sku" should be there and not empty
    Then validate that in response field "kitsku" should be there and not empty
    Then validate that in response field "factoryCode" should be there and not empty
    Then validate that in response field "productionCode" should be there and not empty
    Then validate that in response field "smartSerialNumber" should be there and not empty
    Then validate that in response field "regStatus" should be there and not empty
    Then validate that in response field "warrantyType" should be there and not empty
    Then validate that in response field "warrantyEffectiveDate" should be there and not empty
#    Then validate that in response field "replaceRegId" should be there and not empty
    Then validate that in response field "warrantyDuration" should be there and not empty
    Then validate that in response field "warrantyEndDate" should be there and not empty
    Then validate that in response field "createdDate" should be there and not empty
    Then validate that in response field "lastModifiedDate" should be there and not empty

  Scenario: Verify that "Warranty end date" is calculated when "warrantyType" is set to "ONE (1) YEAR LIMITED WARRANTY"
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "1YRLTD"
    Given that set warranty duration as 12 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    Then set the regId as ID which we get in response of above post api
    Then hit the GET api to fetch detail against the ID for validation
    Then validate that in response field "warrantyEndDate" should be there and not empty
    And difference between warrantyEffectiveDate and warrantyEndDate should be 1


  Scenario: Verify that "Warranty end date" is calculated when "warrantyType" is set to "ONE (1) YEAR VIP LIMITED WARRANTY"
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "1YRVIP"
    Given that set warranty duration as 12 months
    Given Product Registration And Warranty Creation api has been hit
    Then set the regId as ID which we get in response of above post api
    Then hit the GET api to fetch detail against the ID for validation
    Then validate that in response field "warrantyEndDate" should be there and not empty
    And difference between warrantyEffectiveDate and warrantyEndDate should be 1


  Scenario: Verify that "Warranty end date" is calculated when "warrantyType" is set to "ONE (5) YEAR LIMITED WARRANTY"
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "5YRLTD"
    Given that set warranty duration as 60 months
    Given Product Registration And Warranty Creation api has been hit
    Then set the regId as ID which we get in response of above post api
    Then hit the GET api to fetch detail against the ID for validation
    Then validate that in response field "warrantyEndDate" should be there and not empty
    And difference between warrantyEffectiveDate and warrantyEndDate should be 5


  Scenario: Verify that "Warranty end date" is calculated when "warrantyType" is set to "ONE (5) YEAR VIP LIMITED WARRANTY"
    Given Generating a unique customer Id and smart serial number
    Given that set the warranty type as "5YRVIP"
    Given that set warranty duration as 60 months
    Given Product Registration And Warranty Creation api has been hit
    Then set the regId as ID which we get in response of above post api
    Then hit the GET api to fetch detail against the ID for validation
    Then validate that in response field "warrantyEndDate" should be there and not empty
    And difference between warrantyEffectiveDate and warrantyEndDate should be 5


#   This scenario will not work as always new records will get created #
#  Scenario: To verify response when POST api hit again with same set of data in body.
#    Given Generating a unique customer Id
#    Given Product Registration And Warranty Creation api has been hit
#    And again Product Registration And Warranty Creation api has been hit
#    Then in response error code should be 409
#    And in response message should be shown as "Product Warranty already exists."

#  Scenario:Verify that a warranty record is not created in the WMS database reflecting
#           the product registered when the mandatory fields are missing
#    Given setting the regCountry, recordSource, purchaseDate, customerId, sku, regStatus, replaceRegId,warrantyEffectiveDate and warrantyDuration as empty and other fields populated with valida data
#    Given Product Registration And Warranty Creation api has been hit
#    Then error message "Bad Request" and error code 400 should be shown

