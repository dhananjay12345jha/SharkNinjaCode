##################################################################################################################
##################################### API Used From Instalment Controller are given below###########################
   ## POST/sendUpdateCardLinkEmail/{orderId}-->Send Update Card Link Email to customer. ##
   ## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_US
Feature: Verify that whenever cart get updated a link should be sent to customer

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

  Scenario: To validate that email is getting send to the customer
    When set the "orderId" value as "order.number.MS.webFront"
    Then hit the Post SendUpdateCardLink Api
    Then status code in response should be 200
    And Message shown should be "Email has successfully been sent to" "login.email"
