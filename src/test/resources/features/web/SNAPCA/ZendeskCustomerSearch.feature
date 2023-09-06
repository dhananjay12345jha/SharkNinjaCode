@regSNAPCASuit
Feature: To validate user is able to access Zendesk App and Click on Add Ticket and able to search customer.

  Background: User should be on Zendesk Dashbord page with new ticket open
    Given I navigate to the zendesk "Home" page
#	When I enter valid "zendesk.login.email" and "zendesk.login.password"
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket

@SNAPSmoke
  Scenario: Agent should be able to Search for Registered Customer via Last Name
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Last Name as "zendesk.customer.search.valid.lastName1" and click search button
    Then One or more tiles should have customer having last name "zendesk.customer.search.valid.lastName1"

@SNAPSmoke
  Scenario: Agent should be able to Search for Registered Customer Via Customer ID
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then A Tile containing Customer Id "zendesk.customer.search.valid.customerId" should be present

@SNAPSmoke
  Scenario: Agent should be able to Search for Registered Customer via Email ID
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Email Id as "zendesk.customer.search.valid.emailId" and click on search button
    Then One or more tiles should have customer having email id as "zendesk.customer.search.valid.emailId"

@SNAPSmoke
  Scenario: Error message should be there when search Unregistered user via Last Name
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Last Name as "zendesk.customer.search.invalid.lastName" and click search button
    Then Error message "No customers found." should get display

@SNAPSmoke
  Scenario: Error message should be there when search Unregistered user via Customer Id
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Customer Id as "zendesk.customer.search.invalid.customerId" and click search button
    Then Error message "No customers found." should get display

@SNAPSmoke
  Scenario: Error message should be there when search Unregistered user via Email Id
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
    And Enter Email Id as "zendesk.customer.search.invalid.emailId" and click on search button
    Then Error message "No customers found." should get display

