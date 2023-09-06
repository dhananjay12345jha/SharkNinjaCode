@regSNEXUKSuit
@regSNEXEUSuit
Feature: To Create BlackList and Add Customer ID, Email, Address, PostalCode, PhoneNumber and SKU items

  Background: To Open SNEX Application and Select Country And Comes To Order Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file

  Scenario:Add email address to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "Email.Address"
    Then I enter value of "fraud.email.id"
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.email.id" in search box
    Then I select again value type as "Email.Address"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add customer id to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "Customer.Id"
    Then I enter value of "fraud.customer.id"
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.customer.id" in search box
    Then I select again value type as "Customer.Id"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully


  Scenario: Add postal code to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "Postal.Code"
    Then I enter value of "fraud.postal.code"
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.postal.code" in search box
    Then I select again value type as "Postal.Code"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add Phone number to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "Phone.Number"
    Then I enter value of "fraud.phone.number"
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.phone.number" in search box
    Then I select again value type as "Phone.Number"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add SKU Item to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "SKU.Item"
    Then I enter value of "fraud.sku.item"
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.sku.item" in search box
    Then I select again value type as "SKU.Item"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add Address to black list
    When I click on fraud review option on left info pannel
    Then I Click on add Item Button
    Then I Select value type as "User.Address"
    Then I select country from country list
    And  I enter the "fraud.city"
    And I enter the "fraud.address" in address line
    And I enter the "postal.code" in postal code box
    Then search blacklist "auto" and select
    Then click on confirm button
    Then I enter "fraud.address" in search box
    Then I select again value type as "User.Address"
    Then I click on search button
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add blacklist for hold order
    When I click on fraud review option on left info pannel
    Then I click on blacklist button
    Then I click on add blacklist button
    Then I enter the name of "Holdorder.Blacklist"
    Then I select hold order radio button
    Then I click on submit button
    Then I enter "Holdorder.Blacklist" in search box
    Then I click on search button
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully

  Scenario: Add blacklist for cancel order
    When I click on fraud review option on left info pannel
    Then I click on blacklist button
    Then I click on add blacklist button
    Then I enter the name of "cancelOrder.Blacklist"
    Then I select cancel order radio button
    Then I click on submit button
    Then I enter "cancelOrder.Blacklist" in search box
    Then I click on search button
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that blacklist is deleted successfully




