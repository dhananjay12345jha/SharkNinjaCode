@regSNAPCASuit
Feature: Validate back order cancellation for SNAP CA

  Background:User should be able to login into zendesk and can create a backorder
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
#    And Enter Last Name as "zendesk.customer.search.valid.lastName" and click search button
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials

  @SNAPSmoke
  Scenario: Validate back Order Product Cancellation by clicking the order number in the order history
    Then I should click on Order History Option
    Then I should click on the Back Order status with New and Web Order type
    And I should click on NewBackWebOrder
    #And I should assert the backorder
    #Then I should check the Backorder status
    Then I should click on Order options button
    Then I should click on cancel order button
    #And I should enter the quantity no
    And I should enter the back order quantity no and select cancellation reason
    #Then I should select the cancellation reason from the dropdown
    Then I should select the confirm button
    Then I should click on confirm button of a pop up
    # I should click on continue button if cart is not empty




#    Given Agent should be able to add product "back.order.product" into the cart
#    And click on cart button
#    And click on checkout button
#    And select payment method as credit card for CA
#    And fill card holder name as "stripe.card.holder.name" card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
#    Then click on place order button
#    Then order should be successfully placed
#    And get the order id from secure checkout page
#    And update order id against key "order.id" in config file
#    Then run the icm import and export service select checkbox for job defined by key "icm.importExport.job.name"
#    Then I should click on Order History Option#   When i click on order id against key "order.id"
#    Then the order status should be displayed as "back order"
