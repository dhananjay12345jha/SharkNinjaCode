@regSNAPSuit
Feature: Agent is able to Reship the order for dispatched and New order

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
#    And Enter Last Name as "zendesk.customer.search.valid.lastName" and click search button
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials
#    Then I should select channel from dropdown
#    Then I should click on Order search button
#    When I submit valid Agent credentials
#    Then I should select the Sales channel
#    And I should enter "zendesk.order.no" the order number
#    And I should click on the search button
#    And I should click on Order
#    And I should assert the order
#    Then I should check the order type
    Then I should click on Order History Option
    Then I should click on the dispatched Order status with New Order type
    Then I should check the order status
    Then I should click on Order options button

 @SNAPSmoke1
  Scenario: Agent is able to ReShip the order(Faulty on Delivery)
    And I should click on faulty on delivery
    Then I should click on continue button if cart is not empty
#    And I should assert the ReShip order title
    And I should enter the quantity no
    And I should click on Create button
#    And I should verfiy the Product id
    And I should click on Add To Cart button
    And I should navigate to the cart
#    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
    And I should click on Payment method radio button
   And I click on place order button
   Then order should be successfully placed

#  Scenario: Agent is able to Replace the unit
#    Then I should click on replace unit option
#    Then I should click on continue button if cart is not empty
#    And I should assert the return process option
#    And I should enter the quantity no
#    And I should click on Create button
#    Then I should see return successfull dialog box
#    Then I should select continue button

  @SNAPSmoke1
  Scenario: Agent is able to replace the unit
    And I should click on replace unit option
    Then I should click on continue button if cart is not empty
#    And I should assert the Replace unit order
    And increase the quantity to 1
    And I should click on Create button
    Then I should see return successfull dialog box
    And I should click on Continue button
    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
    And I should click on Payment method radio button
    And I click on place order button
    Then order should be successfully placed

  Scenario: Agent is able to Replace the part
    Then I should click on replace part option
    Then I should click on continue button if cart is not empty
    And increase the quantity to 1
    Then I should select a valid failure reason
    And I should click on create button
    Then I should click on Add To Cart button
    And I should navigate to the cart
    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
    And I should click on Payment method radio button
    And I click on place order button
    Then order should be successfully placed


  Scenario: Agent is able to create express replacement
    Then I should click on create express replacement
    Then I should click on continue button if cart is not empty
    Then I select shark products
    And I click add to cart button to add the item at position 1
    And click on cart button
#    And I should navigate to the cart
    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
    And select payment method as credit card
    #    Execution Remaining for below steps
    And fill card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    And I click on place order button
    Then order should be successfully placed


  Scenario: Agent is able to Reship Order (Lost in Transit)
    Then I should click on Lost in Transit option
    Then I should click on continue button if cart is not empty
#    And I should assert the ReShip order title
    And I should enter the quantity no
    And I should click on Create button
    And I should click on Add To Cart button
    And I should navigate to the cart
    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
##    Steps to be verify for payment gateway
    And I should click on Payment method radio button
    And I click on place order button
    Then order should be successfully placed

  Scenario: Agent is able to Reship Order (Missing from Box)
    Then I should click on Missing from Box option
    Then I should click on continue button if cart is not empty
#    And I should assert the ReShip order title
    And I should enter the quantity no
    And I should click on Create button
    And I should verfiy the Product id
    And I should click on Add To Cart button
    And I should navigate to the cart
    And I should assert the Cart header title
    And I should click on Checkout button
    And I should assert the Checkout header title
    And I should click on Payment method radio button
    And I click on place order button
    Then order should be successfully placed

