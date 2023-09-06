Feature: To create multiple orders

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
    And Enter the product "product.bulk.order" in search bar and hit search button
    Given Agent should be able to add product "product.bulk.order" into the cart
    And click on cart button
    And click on checkout button
    And select payment method as credit card
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    Then click on place order button
    Then order should be successfully placed

    Scenario: place 50 orders at a time
      Then I place shark uk bulk order using credit card

