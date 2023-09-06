@testAll
@regNinjaUS
@regNinjaUSSuit2
Feature: Validate Header and Footer links

  Background:
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @smokeNinjaUS
  Scenario: Go To Home Page and verify header links
    Then I should see Shark logo
    Then I should see products link
    Then I should see ninja parts and accessories link
    Then I should see recipes link
    Then I should see support link

  @smokeNinjaUS
  Scenario: Go To Home Page and verify footer links
    Then I should see footer links