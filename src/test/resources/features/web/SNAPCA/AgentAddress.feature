@regSNAPCASuit
Feature: To Update the Address for customers

  Background: User should be logged into zendesk and channel should be UK and customer has been searched
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials

  Scenario: To update Address Foam
    Then I should select Address option
    When I click on edit link in other's address section
    And I fill my update address form with below details
      | Country | FirstName | LastName | AddressLine1       | AddressLine2 | City      | State   | ZIP/PostalCode |
      | Canada  | Megha     | Bhargava | 252 Exportation St |              | Kitchener | Ontario | N2C 2M2        |
    And i click on save changes button
    Then i should see firstName as "Megha"