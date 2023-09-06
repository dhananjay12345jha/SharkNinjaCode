
@ApiTest_UK
@ApiTest_DE
@ApiTest_US
Feature: Verify Post Cancel or refund order api scenarios

  Background:
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


#  Scenario: Verify Initiate the refund for full order when the plan status is"Pending first Payment" and rest are in "PENDING".
#    Then set the parameters of PUT FraudHoldsOrderUpdate api action as "APPROVE",comment as "done by automation" and updatedBy "AutomationTest"
#    Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
#    Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#    Given authentication token and order id has been set and Get Instalment Plan api has been hit
#    Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#    And first instalment status should be "PENDING"
#    And value of "totalPaidInstalments" should be 0
#    And value of "totalAmountPaid" should be 0
#    And fetch the "totalOrderAmount" from the response of GET InstalmentPlan Api and set as "refundAmount" inside body of Post cancelOrRefund Api
#    And set the value of "nonRefundAmount" to 0
#    And set the value of "orderId" as "order.number.MS.webFront"
#    And set the value of "refundId" any numeric value of 6 digits
#    And set the value of "saveSaleRefund" as false
#    And set the value of "status" as "CANCEL_RETURNED"
#    Then hit the Post Cancel Or Refund Plan api
#    Then in response 200 should be there along with message "Refund has been initiated, instalment plan updated and notification email delivered successfully!"
#    //---------------- It is pending at Braintree side to get the response of GET instalment plan api to validate parameters----//


  Scenario: When First Payment has captured then refund the total amount.
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
    And fetch the "totalOrderAmount" from the response of GET InstalmentPlan Api and set as "refundAmount" inside body of Post cancelOrRefund Api
    And set the value of "nonRefundAmount" to 0
    And set the value of "orderId" as "order.number.MS.webFront"
    And set the value of "refundId" any numeric value of 6 digits
    And set the value of "saveSaleRefund" as false
    And set the value of "status" as "CANCEL_RETURNED"
    Then hit the Post Cancel Or Refund Plan api
    Then in response 200 should be there along with message "Refund has been initiated and order cancelled successfully!"
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate in response plan status should be "CANCEL_RETURNED"
    And first instalment status should be "PAID"
    And value of "totalAmountPaid" should be 0
    And value of "nextDueAmount" should be 0
    And value of "totalAmountDue" should be 0
    And capture the "transactionId" where status is "PAID" in invoices list
    And search by the "transactionId" captured above having status as "REFUND-IN-PROGRESS"



  Scenario: When part payment has been done and then refund the total amount
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
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate "totalAmountDue" should get reduced by the amount calculated above
    Then validate "totalAmountPaid" should get increased by the amount calculated above
    And fetch the "totalOrderAmount" from the response of GET InstalmentPlan Api and set as "refundAmount" inside body of Post cancelOrRefund Api
    And set the value of "nonRefundAmount" to 0
    And set the value of "orderId" as "order.number.MS.webFront"
    And set the value of "refundId" any numeric value of 6 digits
    And set the value of "saveSaleRefund" as false
    And set the value of "status" as "CANCEL_RETURNED"
    Then hit the Post Cancel Or Refund Plan api
    Then in response 200 should be there along with message "Refund has been initiated and order cancelled successfully!"
    Given authentication token and order id has been set and Get Instalment Plan api has been hit
    Then validate in response plan status should be "CANCEL_RETURNED"
    And first instalment status should be "PAID"
    And value of "totalAmountPaid" should be 0
    And value of "nextDueAmount" should be 0
    And value of "totalAmountDue" should be 0
    And capture the "transactionId" where status is "PAID" in invoices list
    And search by the "transactionId" captured above having status as "REFUND-IN-PROGRESS"
