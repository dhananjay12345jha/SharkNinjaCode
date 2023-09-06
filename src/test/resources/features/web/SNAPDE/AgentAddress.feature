@regSNAPEUSuit
Feature: To Update the Address for customers

  Background: User should be logged into zendesk and channel should be UK and customer has been searched
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials

  Scenario: To update Address Form
    Then I should select Address option
    When I click on edit link in other's address section
    And I fill my update address form with below details in other address
      | Country        | FirstName | LastName | AddressLine1  | AddressLine2 | City  | ZIP/PostalCode |
      | United Kingdom | Mukul    | Anand    | Eixer Weg 12A |              | Peine | 31228          |
    And i click on save changes button of other address
    Then i should see the firstName as "Mukul"
