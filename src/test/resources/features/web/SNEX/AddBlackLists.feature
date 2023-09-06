Feature: To Create BlackList and Add Customer ID, Email, Address, PostalCode, PhoneNumber and SKU items

  Background: To Open SNEX Application and Select Country And Comes To Fraud Review Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file

    Scenario:
      When I click on fraud review option on left info pannel
      Then I should be on fraud review page