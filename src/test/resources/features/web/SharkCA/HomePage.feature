@testAll
@regressionSharkCA
@regSharkCASuit2
Feature: Validate Header and Footer links

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file

  @verifyHeaderSharkCA
  @smokeSharkCA
  Scenario: Go To Home Page and verify header links
    Then I should see Shark logo
    Then I should see products link
    Then I should see register my warranty link
    Then I should see customer care link
    Then I should see tips and advice link
    And language selected should be equal to "select.language.to.test"

  @verifyFooterSharkCA
  @smokeSharkCA
  Scenario: Go To Home Page and verify footer links
    Then I should see footer links
    And language selected should be equal to "select.language.to.test"