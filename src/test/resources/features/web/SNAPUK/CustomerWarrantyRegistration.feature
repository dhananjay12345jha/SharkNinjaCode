@SNAPSmoke123
@regSNAPSuit
Feature: To validate Customer Warranty Registration


  Background: User should be logged into zendesk and channel should be UK and customer has been searched
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket

@SNAPSmoke
  Scenario: To check Agent is able to do warranty registration for existing customer
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
#    Then One or more tiles should have customer having last name "zendesk.customer.search.valid.lastName"
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials
    And Clicks on products registrations button and click Add Product Registration button
    And Enter product model number as "warranty.product.model.number" and select that from drop down
    And Enter purchase date as "warranty.purchase.date"
    And Select purchase location as "warranty.purchase.location"
#    And Enter Serial Number As ""
    Given Capture the product model name
    Then Clicks on create button product registration should be done

  @SNAPSmoke
    Scenario: To check agent is able to do warranty registration for anonymous customer
      When Click on App button to open info pannel and select channel as "zendesk.select.channel"
      Then User clicks on create product registration button
      When I submit valid Agent credentials
      And Clicks on products registrations button and click Add Product Registration button
      And set email address
      And set first name as "warranty.user.first.name"
      And set last name as "warranty.user.last.name"
      And select country as "warranty.country"
      And set postal code as "warranty.postal.code" and select first address from dropdown
      And Enter product model number as "warranty.product.model.number" and select that from drop down
      And Enter purchase date as "warranty.purchase.date"
      And Select purchase location as "warranty.purchase.location"
      Then Clicks on create button product registration should be done






