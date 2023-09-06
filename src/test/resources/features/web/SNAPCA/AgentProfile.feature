@regSNAPCASuit
Feature: Validate agent is able to update user first name and last name

  Background: User should be logged into zendesk and channel should be UK and customer has been searched
    Given I navigate to the zendesk "Home" page
    When I submit valid zendesk credentials
#    Then I should be on Zendesk Agent Dashboard
    And I should be able to Add a new Ticket
    When Click on App button to open info pannel and select channel as "zendesk.select.channel"
#    And Enter Last Name as "zendesk.customer.search.valid.lastName" and click search button
    And Enter Customer Id as "zendesk.customer.search.valid.customerId" and click search button
    Then Click on the select button where email is "zendesk.customer.search.valid.emailId"
    When I submit valid Agent credentials


  @SNAPSmoke1
    Scenario: Updating existing user's first name and last name
      And updating first name as "Test"
      And updating last name as "Test"
      And clicks on save profile button
      Then updation acknowledgement message "Your profile information has been updated." should be there
     # And no result should be returned when searching for the customer with previous last name
      And customer name should get display as "Test Test"
      And updating first name as "Mukul"
      And updating last name as "Anand"
      And clicks on save profile button

  @SNAPSmoke1
  Scenario: agent is able to intiate the Resend Confirmation mail for a customer
    Then I click on Resend confirmation button
    Then I select "shark" channel for confirmation
    And I click on Resend confirmation button again
    Then acknowledgement message "No email has been sent as customer is already active" should be displayed

  @SNAPSmoke1
  Scenario: agent is able to intiate the Reset Password for a customer
    Then I click on Reset Password button
    Then I select "shark" channel for reset password
    And I click on send reset password button
    Then acknowledgement message "Password Reset mail is sent" should be displayed

  @SNAPSmoke1
  Scenario: agent is able to intiate loading of Email Preferences for a customer
    Then I click on Load Email Preference Bronto button
    Then I click on Resend confirmation button
    Then I select "shark" channel for confirmation
    And I click on Resend confirmation button again
    Then acknowledgement message "No email has been sent as customer is already active" should be displayed
