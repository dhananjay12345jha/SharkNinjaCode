######################################################################################################################
######################################API Used of Fraud Controller are given below####################################
## GET FraudHolds/{orderId}##
## PUT FraudHolds/{orderId}##
## @Author: Sanket Jha

#@ApiTest_UK
@ApiTest_DE
@ApiTest_US
@exclude_ApiTest_UK
Feature: Verify parameters of GET FraudHolds/OrderId, PUT FraudHolds and PUT FraudHold/OrderId APIs

  Background:
    Given I navigate to the "Home" page on MicroSite
#    When I select first product tile
    When I select last product tile
    And I add product to cart
    And I click on returning customer login option
    And I enter valid "login.email" and "login.password"
    Then verify successful login
    When I select Braintree Installment payment method
    And I place an order by brain tree instalment With Card using valid "brainTree.card.number" and "brainTree.card.expiryDate" and "brainTree.card.cvcNumber" and "brainTree.card.postalCode"
    And verify that order is placed successfully
    And save the order number into the properties file as "order.number.MS.webFront"
    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
    Then explicitly wait for 4 seconds so that instalment plan gets updated at backend

  Scenario: Status of a Blacklist from Fraud Hold List should be APPROVE from REVIEW by using PUT FraudHoldList/OrderId api is used.
    When set the value of orderId "order.number.MS.webFront" in Get FraudHolds/OrderId api
    Then hit the GET FraudHolds/OrderId api
    And validate that status code should be 200 and message "Success"
    Then picked up the first id where source will be BLACKLIST and status as REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "APPROVE" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
    And validate response should have status as 200 and message "Success"
    Then hit the GET FraudHolds/OrderId api
    And validates that blacklist id send in PUT request body above value of status should be "APPROVED" instead of REVIEW

  Scenario: Status of a Blacklist from Fraud Hold List should be REJECT from REVIEW by using PUT FraudHoldList/OrderId api is used.
    When set the value of orderId "order.number.MS.webFront" in Get FraudHolds/OrderId api
    Then hit the GET FraudHolds/OrderId api
    And validate that status code should be 200 and message "Success"
    Then picked up the first id where source will be BLACKLIST and status as REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "REJECT" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
    And validate response should have status as 200 and message "Success"
    Then hit the GET FraudHolds/OrderId api
    And validates that blacklist id send in PUT request body above value of status should be "REJECTED" instead of REVIEW


  Scenario: Status of a ALL Blacklist from Fraud Hold List should be APPROVE from REVIEW by using PUT FraudHoldList/OrderId api is used.
    When set the value of orderId "order.number.MS.webFront" in Get FraudHolds/OrderId api
    Then hit the GET FraudHolds/OrderId api
    And validate that status code should be 200 and message "Success"
    Then picked up all ids where source will be BLACKLIST and status as REVIEW
    And set the all id values obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "APPROVE" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
    And validate response should have status as 200 and message "Success"
    Then hit the GET FraudHolds/OrderId api
    And validates that all blacklist id send in PUT request body above value of status should be "APPROVED" instead of REVIEW

  Scenario: Again APPROVE the already approved blacklist from Fraud Hold List
    When set the value of orderId "order.number.MS.webFront" in Get FraudHolds/OrderId api
    Then hit the GET FraudHolds/OrderId api
    And validate that status code should be 200 and message "Success"
    Then picked up the first id where source will be BLACKLIST and status as REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "APPROVE" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
    And validate response should have status as 200 and message "Success"
    Then hit the GET FraudHolds/OrderId api
    And validates that blacklist id send in PUT request body above value of status should be "APPROVED" instead of REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "APPROVE" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
#    Then status code should be 400 and error message contains "Given orderId is already approved"
    Then status code should be 200 and error message contains "Fraud Hold List is successfully updated"


  Scenario: REJECTING the already approved blacklist from Fraud Hold List
    When set the value of orderId "order.number.MS.webFront" in Get FraudHolds/OrderId api
    Then hit the GET FraudHolds/OrderId api
    And validate that status code should be 200 and message "Success"
    Then picked up the first id where source will be BLACKLIST and status as REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "APPROVE" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
    And validate response should have status as 200 and message "Success"
    Then hit the GET FraudHolds/OrderId api
    And validates that blacklist id send in PUT request body above value of status should be "APPROVED" instead of REVIEW
    And set the id value obtained above in PUT FraudHolds/OrderApi body
    And set the value of orderId "order.number.MS.webFront" in PUT FraudHolds/OrderId api
    And set the value of action as "REJECT" and comment as "For Automation" and updatedBy as "Automation Suite" in PUT FraudHolds/OrderId api body
    Then hit the PUT FraudHolds/Order api
#    Then status code should be 400 and error message contains "Fraud Hold List is successfully updated"
    Then status code should be 200 and error message contains "Fraud Hold List is successfully updated"