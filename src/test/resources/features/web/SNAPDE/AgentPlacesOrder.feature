@regSNAPEUSuit
Feature: To validate agent should be able to place order on behalf of a customer

  Background: User should be logged into zendesk and channel should be UK and customer has been searched
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
  Scenario: Agent Placing order on behalf of a existing customer
    And Enter the product "product.specificSKU" in search bar and hit search button
    Given Agent should be able to add product "product.specificSKU" into the cart
    And click on cart button
    And click on checkout button
    And select payment method as credit card for DE
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    Then click on place order button
    Then order should be successfully placed

  @SNAPSmoke
  Scenario: Check the add to cart on PDP
    And Enter the product "product.specificSKU" in search bar and hit search button
    Then Agent should be able to add product "product.specificSKU" into the cart

#  @SNAPSmoke
#  Scenario: Check the add to cart on PDP
#    And Enter the product "product.specificSKU" in search bar and hit search button
#    Then list of products with "product.specificSKU" is visible