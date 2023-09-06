@regSNAPCASuit
Feature: Validate back order creation for SNAP CA

  Background:
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
  Scenario: Validate Back Order Order creation for Existing Customer for Shark/Ninja CA
#    And Enter the product "back.order.product" in search bar and hit search button
##    Then Value of M should be displayed as zero
#    Given Agent should be able to add product "back.order.product" into the cart
#    And click on cart button
#    And click on checkout button
#    And select payment method as credit card for CA
#    And fill card holder name as "stripe.card.holder.name" card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
#    Then click on place order button
#    Then order should be successfully placed
#    And get the order id from successfully placed order
#    And update the value of "order.id" in property file
#    Then run the icm import and export job defined by key "icm.importExport.job.name"
    Then I should click on Order History Option
    When i click on order id against key "order.id"
    Then the order status should be displayed as "Back-order"