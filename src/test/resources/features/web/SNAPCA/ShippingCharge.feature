Feature: To Validate Shipping Charges for Shark/Ninja CA for orders<75$

  Background:
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And click on create new order button
    When I submit valid Agent credentials

    Scenario: Validate Shipping Charges for Existing Customer for Shark CA for orders<75$
      And Enter the product "shark.order.shipping.charge" in search bar and hit search button
      Given Agent should be able to add product "shark.order.shipping.charge" into the cart
      And click on cart button
      Then verify shipping charge for order less than 75 should not be zero

  Scenario: Validate Shipping Charges for Existing Customer for Ninja CA for orders<75$
    And Enter the product "ninja.order.shipping.charge" in search bar and hit search button
    Given Agent should be able to add product "ninja.order.shipping.charge" into the cart
    And click on cart button
    Then verify shipping charge for order less than 75 should not be zero
