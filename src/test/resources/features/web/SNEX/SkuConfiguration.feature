@regSNEXEUSuit
@regSNEXUKSuit

Feature: Sku Configuration for Substitution , Rapid Response and refurbishment

  Background: To Open SNEX Application and Select Country And Comes To SKU Configuration Page Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file


  Scenario: Add Substitution in Substitution tab
    When I click on sku configuration option on left info pannel
    Then I click on substitution button
    Then I click on add substitution button
    Then I enter "source.sku" and "substitute.sku"
    Then click on confirm button
    Then I enter "source.sku" search box and click on search
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully


  Scenario: Add substitution for already added sku
    When I click on sku configuration option on left info pannel
    Then I click on substitution button
    Then I click on add substitution button
    Then I enter "source.sku" and "substitute.sku"
    Then click on confirm button
    Then I click on add substitution button
    Then I enter "source.sku" and "substitute.sku"
    Then click on confirm button
    Then Verify that sku already exist
    Then I click on cancel button
    Then I enter "source.sku" search box and click on search
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  Scenario: Add invalid sku Substitution
    When I click on sku configuration option on left info pannel
    Then I click on substitution button
    Then I click on add substitution button
    Then I enter "source.sku" and "invalid.sku"
    Then click on confirm button
    Then Verify that sku is invalid

  Scenario: Add Refurbishment in Refurbishment tab
    When I click on sku configuration option on left info pannel
    Then I click on refurbishment button
    Then I click on add refurbishment button
    Then I enter "refurbishment.sku"
    Then click on confirm button
    Then I enter "refurbishment.sku" search box and click on search
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  Scenario: Add sku Refurbishment
    When I click on sku configuration option on left info pannel
    Then I click on refurbishment button
    Then I click on add refurbishment button
    Then I enter "refurbishment.sku"
    Then click on confirm button
    Then I click on add refurbishment button
    Then I enter "refurbishment.sku"
    Then click on confirm button
    Then Verify that sku already exist
    Then I click on cancel button
    Then I enter "refurbishment.sku" search box and click on search
    Then I click on delete icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  Scenario: Add invalid sku Refurbishment
    When I click on sku configuration option on left info pannel
    Then I click on refurbishment button
    Then I click on add refurbishment button
    Then I enter "invalid.sku"
    Then click on confirm button
    Then Verify that sku is invalid

  Scenario: Add Rapid Response In Rapid Response tab
    When I click on sku configuration option on left info pannel
    Then I click on Rapid Response button
    Then I click on add Rapid Response button
    Then I enter "rapid.response.sku"
    Then I Select reason as "ASSEMBLY_ISSUES"
    Then click on confirm button
    Then I enter "rapid.response.sku" search box and click on search
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  Scenario: Add Rapid Response for already added sku
    When I click on sku configuration option on left info pannel
    Then I click on Rapid Response button
    Then I click on add Rapid Response button
    Then I enter "rapid.response.sku"
    Then I Select reason as "ASSEMBLY_ISSUES"
    Then click on confirm button
    Then I click on add Rapid Response button
    Then I enter "rapid.response.sku"
    Then I Select reason as "ASSEMBLY_ISSUES"
    Then click on confirm button
    Then Verify that sku already exist
    Then I click on cancel button
    Then I enter "rapid.response.sku" search box and click on search
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  Scenario: Add invalid sku to Rapid Response
    When I click on sku configuration option on left info pannel
    Then I click on Rapid Response button
    Then I click on add Rapid Response button
    Then I enter "invalid.sku"
    Then I Select reason as "ASSEMBLY_ISSUES"
    Then click on confirm button
    Then Verify that sku is invalid

  @excludeSnexUK
  Scenario: Add Warehouse restrictions In Warehouse restriction tab
    When I click on sku configuration option on left info pannel
    Then I click on warehouse restrictions button
    Then I click on add warehouse restriction button
    Then I enter "warehouse.restriction.sku"
    Then I Select checkbox as DSV
    Then click on confirm button
    Then I enter "warehouse.restriction.sku" search box and click on search
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  @excludeSnexUK
  Scenario: Add warehouse restrictions for already added sku
    When I click on sku configuration option on left info pannel
    Then I click on warehouse restrictions button
    Then I click on add warehouse restriction button
    Then I enter "warehouse.restriction.sku"
    Then I Select checkbox as DSV
    Then click on confirm button
    Then I click on add warehouse restriction button
    Then I enter "warehouse.restriction.sku"
    Then I Select checkbox as DSV
    Then click on confirm button
    Then Verify that sku already exist for warehouse restriction
    Then I click on cancel button
    Then I enter "warehouse.restriction.sku" search box and click on search
    Then I click on trash icon
    Then I click on delete confirm button
    Then Verify that sku is deleted successfully

  @excludeSnexUK
  Scenario: Add invalid sku to warehouse restrictions
    When I click on sku configuration option on left info pannel
    Then I click on warehouse restrictions button
    Then I click on add warehouse restriction button
    Then I enter "invalid.sku"
    Then I Select checkbox as DSV
    Then click on confirm button
    Then Verify that sku is invalid for warehouse restriction


