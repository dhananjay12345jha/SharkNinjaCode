Feature: Validate Blacklist Order creation for Customer in SNAP EU


  Background:
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"


  Scenario: Validate Blacklist Order creation for New Customer for Shark/Ninja EU
    And click on create new order button
    When I submit valid Agent credentials
    And Enter the product "blacklist.order" in search bar and hit search button
    Given Agent should be able to add product "blacklist.order" into the cart
    And click on cart button
    And click on checkout button
    And set first name as Random name
    And set last name as "blacklist.last.name"
    And i click on check box for email Id
    And set postal code as "blacklist.postal.code" and select first address from dropdown
    And i click on continue with checkout button
    And select payment method as credit card for DE
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    And I click on place order button
    Then verify that order is process through Express Replacement successfully
    And get the order id from successful order place page for SNAP CA
    And update order id against key "blacklist.order.id" in config file for SNAP CA
    Then I navigate to the SNEX page
#    When i click on search button
#    Then i should see "blacklist.order.id" in Order Number section

  Scenario: Validate Blacklist Order creation for Existing Customer for Shark/Ninja EU
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials
    And Enter the product "blacklist.order" in search bar and hit search button
    Given Agent should be able to add product "blacklist.order" into the cart
    And click on cart button
    And click on checkout button
    And select payment method as credit card
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    And I click on place order button
    Then verify that order is process through Express Replacement successfully

