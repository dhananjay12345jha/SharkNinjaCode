@regSNAPCASuit
Feature: Validate Avalara Tax  calculation Customer for Shark/Ninja CA

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
#    Then One or more tiles should have customer having last name "zendesk.customer.search.valid.lastName"
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials

    Scenario: To validate sales Tax for Kitchener region of canada
      Then I should select Address option
      When I click on edit link in shipping address section
      And I fill my update address form with below details For Shipping Address
        | Country | FirstName | LastName | AddressLine1       | AddressLine2 | City      | State   | ZIP/PostalCode |
        | Canada  | Megha     | Bhargava | 252 Exportation St |              | Kitchener | Ontario | N2C 2M2        |
      And i click on save changes button of shipping address
      And Enter the product "alavara.tax.product" in search bar and hit search button
      Given Agent should be able to add product "alavara.tax.product" into the cart
      And click on cart button
      Then i check the sales tax for product on cart page
      And I should click on Checkout button
      Then i check te sales tax for product on checkout page
      And select payment method as credit card for NA
      And fill card holder name as "stripe.card.holder.name" card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
      Then click on place order button
      Then order should be successfully placed


  Scenario: To validate sales Tax for Victoria region of canada
    Then I should select Address option
    When I click on edit link in shipping address section
    And I fill my update address form with below details For Shipping Address
      | Country | FirstName | LastName | AddressLine1  | AddressLine2 | City     | State            | ZIP/PostalCode |
      | Canada  | Megha     | Bhargava | 1 park street |              | Victoria | British Columbia | V8P 1A1        |
    And i click on save changes button of shipping address
    And Enter the product "alavara.tax.product" in search bar and hit search button
    Given Agent should be able to add product "alavara.tax.product" into the cart
    And click on cart button
    Then i check the sales tax for product on cart page for victoria city
    And I should click on Checkout button
    Then i check te sales tax for product on checkout page for victoria city
    And select payment method as credit card for NA
    And fill card holder name as "stripe.card.holder.name" card number as "stripe.card.number" and validity as "stripe.card.expiryDate" and cvc number as "stripe.card.cvcNumber" and postal code as "strip.card.postalCode"
    Then click on place order button
    Then order should be successfully placed




