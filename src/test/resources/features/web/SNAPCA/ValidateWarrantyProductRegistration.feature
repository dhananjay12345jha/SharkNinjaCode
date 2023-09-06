@regSNAPCASuit
Feature: Validate Warranty details(Unit+Part Warranty) for Product Registration

  Background:
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
#    Then One or more tiles should have customer having last name "zendesk.customer.search.valid.lastName"
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials
#    And Clicks on products registrations button and click Add Product Registration button
#    And Enter product model number as "warranty.product.model.number" and select that from drop down
#    And Enter purchase date as "warranty.purchase.date"
#    And Select purchase location as "warranty.purchase.location"
##    And Enter Serial Number As ""
#    Given Capture the product model name
#    Then Clicks on create button product registration should be done for CA

    Scenario: To verify Parts warranty Text on Product Registration
      Then i click on product registration
      Then i should see parts warranty

  Scenario: To verify Parts as well as Unit warranty Text on Product Registration
    Then i click on product registration
    Then i should see parts and unit warranty

  Scenario: To verify Unit warranty Text on Cart Page
    And Enter the product "product.warranty" in search bar and hit search button
    Given Agent should be able to add product "product.warranty" into the cart
    And click on cart button
    Then i should see unit warranty on cart page

  Scenario: To verify Unit warranty Text on Checkout Page
    And Enter the product "product.warranty" in search bar and hit search button
    Given Agent should be able to add product "product.warranty" into the cart
    And click on cart button
    And I should click on Checkout button
    Then i should see unit warranty on checkout page

