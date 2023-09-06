##################################################################################################################
##################################### API Used of Instalment Controller are given below###########################
   ## PUT FraudHolds-->Update Fraud Holds status for a list of orders ##
   ## GET /getInstalmentPlan-->Get Latest instalment plan details ##
   ## POST /capturePayment-->API to Capture first instalment payment ##
   ## POST /partPayment-->API to send part payment request ##
   ## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_US
Feature: Verify parameters of an order after fetching installment plan and making payment

  Background: creating new order from microsite having instalments and then run import export service
    Given I navigate to the "Home" page on MicroSite
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
    Then explicitly wait for 5 seconds so that instalment plan gets updated at backend


  Scenario: To verify POST capture payment api (API to Capture first instalment payment)
    Then set the parameters of PUT FraudHoldsOrderUpdate api action as "APPROVE",comment as "done by automation" and updatedBy "AutomationTest"
    Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
    Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#    And first instalment status should be "PENDING"
#    And value of "totalPaidInstalments" should be 0
#    And value of "totalAmountPaid" should be 0
#    Then from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request
#    And set the value of order id as "order.number.MS.webFront" and request transaction id as random number
#    Then hit the Post Capture Payment request
#    Then validate in response 200 should be there and message "The capture payment request has been sent successfully"
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate in response plan status should be "ACTIVE"
    And first instalment status should be "PAID"
    And value of "totalPaidInstalments" should be 1
    And value of "totalAmountPaid" should be greater than zero

#  Scenario: To verify POST capture payment api (API to Capture first instalment payment)
#    Then set the parameters of PUT FraudHoldsOrderUpdate api action as "REJECT",comment as "done by automation" and updatedBy "AutomationTest"
#    Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
#    Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#    And first instalment status should be "PENDING"
#    And value of "totalPaidInstalments" should be 0
#    And value of "totalAmountPaid" should be 0
#    Then from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request
#    And set the value of order id as "order.number.MS.webFront" and request transaction id as random number
#    Then hit the Post Capture Payment request
#    Then validate in response 200 should be there and message "The capture payment request has been sent successfully"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "ACTIVE"
#    And first instalment status should be "PAID"
#    And value of "totalPaidInstalments" should be 1
#    And value of "totalAmountPaid" should be greater than zero


  Scenario: To verify POST part payment api (API to send part payment request)
    Then set the parameters of PUT FraudHoldsOrderUpdate api action as "APPROVE",comment as "done by automation" and updatedBy "AutomationTest"
    Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
    Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#    And first instalment status should be "PENDING"
#    And value of "totalPaidInstalments" should be 0
#    And value of "totalAmountPaid" should be 0
#    Then from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request
#    And set the value of order id as "order.number.MS.webFront" and request transaction id as random number
#    Then hit the Post Capture Payment request
#    Then validate in response 200 should be there and message "The capture payment request has been sent successfully"
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate in response plan status should be "ACTIVE"
    And first instalment status should be "PAID"
    And value of "totalPaidInstalments" should be 1
    And value of "totalAmountPaid" should be greater than zero
    And calculating the payment amount depending upon the "nextDueAmount"
    And saving the values of "totalAmountDue" and "totalAmountPaid" for further validation
    Then hit the POST partPayment Api to do partial payment with amount calculated above and order Id "order.number.MS.webFront"
    Then response code of POST partPayment api should be 200 and message shown should be "The part payment request has been sent successfully"
    Then explicitly wait for 3 seconds so that instalment plan gets updated at backend
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate "totalAmountDue" should get reduced by the amount calculated above
    Then validate "totalAmountPaid" should get increased by the amount calculated above


  Scenario: To verify POST part payment api (API to send part payment request) having payment greater than totalAmountDue
    Then set the parameters of PUT FraudHoldsOrderUpdate api action as "APPROVE",comment as "done by automation" and updatedBy "AutomationTest"
    Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
    Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#    And first instalment status should be "PENDING"
#    And value of "totalPaidInstalments" should be 0
#    And value of "totalAmountPaid" should be 0
#    Then from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request
#    And set the value of order id as "order.number.MS.webFront" and request transaction id as random number
#    Then hit the Post Capture Payment request
#    Then validate in response 200 should be there and message "The capture payment request has been sent successfully"
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate in response plan status should be "ACTIVE"
    And first instalment status should be "PAID"
    And value of "totalPaidInstalments" should be 1
    And value of "totalAmountPaid" should be greater than zero
    And calculating the payment amount depending upon the "nextDueAmount"
    And saving the values of "totalAmountDue" and "totalAmountPaid" for further validation
    Then hit the POST partPayment Api to do payment having amount greater than totalAmountDue and order Id "order.number.MS.webFront"
    Then error code 400 should be there along with message "The part payment amount can not be greater than the outstanding amount due"
    Then hit the POST partPayment Api to do payment having amount zero and order Id "order.number.MS.webFront"
    Then error code 400 should be there and message "amount must be greater than 0"
