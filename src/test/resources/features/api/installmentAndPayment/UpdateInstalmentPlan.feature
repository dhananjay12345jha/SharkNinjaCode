##################################################################################################################
##################################### API Used of Instalment Controller are given below###########################
   ## GET /getInstalmentPlan-->Get Latest instalment plan details ##
   ## POST /updateInstalmentPlan-->Change instalment plan.
   ## @Author: Sumeet Kumar

@ApiTest_UK
@ApiTest_DE
@ApiTest_US
Feature: Verify POST updateInstalmentPlan API (Change instalment plan)

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
  Then explicitly wait for 4 seconds so that instalment plan gets updated at backend
  Then set the parameters of PUT FraudHoldsOrderUpdate api action as "APPROVE",comment as "done by automation" and updatedBy "AutomationTest"
  Then hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order "order.number.MS.webFront"
  Then validate status code should be 200 and response message should be "Fraud Hold List is successfuly updated"
#  Given authentication token and order id has been set and Get Instalment Plan api has been hit
#  Then validate in response plan status should be "PENDING_FIRST_PAYMENT"
#  And first instalment status should be "PENDING"
#  And value of "totalPaidInstalments" should be 0
#  And value of "totalAmountPaid" should be 0
#  Then from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request
#  And set the value of order id as "order.number.MS.webFront" and request transaction id as random number
#  Then hit the Post Capture Payment request
#  Then validate in response 200 should be there and message "The capture payment request has been sent successfully"
  Given authentication token and order id has been set and Get Instalment Plan api has been hit
  Then validate in response plan status should be "ACTIVE"
  And first instalment status should be "PAID"
  And value of "totalPaidInstalments" should be 1
  And value of "totalAmountPaid" should be greater than zero


#@UpdateInstalmentPlan
Scenario: To update instalment plan through POST API
  Then set the value of first instalment amount to "instalment.first.amount"
  And set the value of number of instalments to "instalment.total.number"
  And set the value of subsequent instalment amount to "instalment.subsequent.amount"
  Then hit POST Update Instalment Plan to update instalment plan
  Then response of update Instalment plan should be 200
  And verify success message of instalment update "instalment.update.response.message"
  Given authentication token and order id has been set and Get Instalment Plan api has been hit
  Then verify instalment plan gets updated