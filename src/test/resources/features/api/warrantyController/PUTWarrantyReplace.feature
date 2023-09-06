#Scenario : There should be two records in response later one should have regStatus as "replaced" and former one should have "registered"
#Scenario : In second one replaceRegId should have regId of the first record of response
#Scenario : Warranty duration in first record should be equal  to the warrantyType business rule
#Scenario : warranty effective date when order replace request has been raised and warranty replace effective date should have 14 days difference

@ApiTest_CA
@ApiTest_US
Feature: To validate various scenarios when order has been replaced and PUT warranty replace api has been called

  Scenario: PUT/warranty/replace api has been hit and validate new record has regStatus as "REGISTERED" and old one as "REPLACED"
    # Registering a new warranty for a product before hitting the PUT/warranty/replace api in below steps
    Given Generating a unique customer Id and smart serial number
    Given creating a random order number and set the value
    Given that set the warranty type as "1YRLTD"
    Given that set warranty duration as 12 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    # setting up the body of PUT/warranty/replace api and hit the api in below steps
    Given set "oldEffectiveDate" value which is equal to "warrantyEffectiveDate" of warranty register api
    Given set "oldOrderNumber" value which is equal to "orderNo" of warranty register api
    Given set "oldSku" value which is equal to "sku" of warranty register api
    Given creating a new order number and set the value of "orderNo"
    Given set "warrantyEffectiveDate" value as current date when api is executing
    Given set remaining body parameters values equal to the values used while registering the product warranty api
    Then hit the PUT/warranty/replace api
    Then in response 200 as status code and message "Replace product registration and warranty successfully recorded" should be there
    And only 4 records should be there in data list one is newly created and other one is old
    And in response data "regStatus" should be "REPLACED" where "regId" is equal to the "regId" received in response of warranty register api
    And in response of PUT/warranty/replace api newly created record should have regStatus as "REGISTERED"


  Scenario:  when PUT/warranty/replace api has been hit then from response replaceRegId value should be equal to the regId of the newly created record having regStatus as "REGISTERED"
    # Registering a new warranty for a product before hitting the PUT/warranty/replace api in below steps
    Given Generating a unique customer Id and smart serial number
    Given creating a random order number and set the value
    Given that set the warranty type as "1YRLTD"
    Given that set warranty duration as 12 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    # setting up the body of PUT/warranty/replace api and hit the api in below steps
    Given set "oldEffectiveDate" value which is equal to "warrantyEffectiveDate" of warranty register api
    Given set "oldOrderNumber" value which is equal to "orderNo" of warranty register api
    Given set "oldSku" value which is equal to "sku" of warranty register api
    Given creating a new order number and set the value of "orderNo"
    Given set "warrantyEffectiveDate" value as current date when api is executing
    Given set remaining body parameters values equal to the values used while registering the product warranty api
    Then hit the PUT/warranty/replace api
    Then in response 200 as status code and message "Replace product registration and warranty successfully recorded" should be there
    And only 4 records should be there in data list one is newly created and other one is old
    And in response of PUT/warranty/replace api old record should have value of replaceRegId equal to the regId of newly created record

  Scenario: Validating the business requirement of calculating warranty duration according to the warranty Type
    # Registering a new warranty for a product before hitting the PUT/warranty/replace api in below steps
    Given Generating a unique customer Id and smart serial number
    Given creating a random order number and set the value
    Given that set the warranty type as "5YRVIP"
    Given that set warranty duration as 60 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    # setting up the body of PUT/warranty/replace api and hit the api in below steps
    Given set "oldEffectiveDate" value which is equal to "warrantyEffectiveDate" of warranty register api
    Given set "oldOrderNumber" value which is equal to "orderNo" of warranty register api
    Given set "oldSku" value which is equal to "sku" of warranty register api
    Given creating a new order number and set the value of "orderNo"
    Given set "warrantyEffectiveDate" value as current date when api is executing
    Given set remaining body parameters values equal to the values used while registering the product warranty api
    Then hit the PUT/warranty/replace api
    Then in response 200 as status code and message "Replace product registration and warranty successfully recorded" should be there
    And validate that in new record warrantyDuration should be 24 months


  Scenario: Validating that warrantyEffective date send in PUT/warranty/replace body and which received from response new record should have 14 days difference
       # Registering a new warranty for a product before hitting the PUT/warranty/replace api in below steps
    Given Generating a unique customer Id and smart serial number
    Given creating a random order number and set the value
    Given that set the warranty type as "5YRVIP"
    Given that set warranty duration as 60 months
    Given that set quantity as 1
    Given Product Registration And Warranty Creation api has been hit
    # setting up the body of PUT/warranty/replace api and hit the api in below steps
    Given set "oldEffectiveDate" value which is equal to "warrantyEffectiveDate" of warranty register api
    Given set "oldOrderNumber" value which is equal to "orderNo" of warranty register api
    Given set "oldSku" value which is equal to "sku" of warranty register api
    Given creating a new order number and set the value of "orderNo"
    Given set "warrantyEffectiveDate" value as current date when api is executing
    Given set remaining body parameters values equal to the values used while registering the product warranty api
    Then hit the PUT/warranty/replace api
    Then in response 200 as status code and message "Replace product registration and warranty successfully recorded" should be there
    And validate value of warratyEffective send in api body and which recieved from response new record should have 14 days difference