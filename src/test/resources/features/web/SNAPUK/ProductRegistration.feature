@regSNAPSuit
Feature: To validate Agent is able to product warranty registration.

  Background: User should be logged into zendesk and channel should be UK and customer has been searched
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
#   And Enter Last Name as "zendesk.customer.search.valid.lastName" and click search button
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials
    And I Clicks on Products Registrations

  @SNAPSmoke
  Scenario: Check that agent is able to process Warranty registration - Replace Part(Proof of purchase)
    Then I click on order option button at postion 2
    And I select the option "Replace Part" from the dropdown
    Then I select proof of purchase as "Pending"
#    Then I select first item shown under replacement
    And I should click on Create button given at below
    Then I click add to cart button to add the item
    And I click on cart button
#    Then Same item should be shown in cart and price should be zero
    And I click on checkout button
    And select payment method as "Free of Charge"
    And I click on place order button
    Then verify that order of replacement part done successfully

  Scenario: Check that agent is able to process Warranty registration - Replace Unit
    Then I click on order option button at postion 2
    And I select the option "Replace Unit" from the dropdown
    Then I select proof of purchase as "Pending"
#    And I select valid fault reason
    And I select replacement product option
    And I click on create button
    Then Same item should be shown in cart and price should be zero
    And I click on checkout button
    And select payment method as "Free of Charge"
    And I click on place order button
    Then verify that order of replacement part done successfully

  Scenario: Check that agent is able to process Warranty registration - Express Replacement
    Then I click on order option button at postion 2
    And I select the option "Express Replacement" from the dropdown
    Then I select shark products
    Then I select first item shown under Handheld vaccums
    Then I click add to cart button to add the item at position 1
    And I click on cart button
    And I click on checkout button
    And select payment method as credit card
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    And I click on place order button
    Then verify that order is process through Express Replacement successfully
