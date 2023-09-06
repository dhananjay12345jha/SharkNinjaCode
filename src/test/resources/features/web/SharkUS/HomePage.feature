@testAll
@regSharkUS
@regSharkUSSuit2
Feature: Validate Header and Footer links

  Background: Go To Home Page Of Shark US
    Given I navigate to the "Home" page
    And click on accept cookies if visible

  @verifyHeaderSHarkUS
  @smokeSharkUS
  Scenario: Go To Home Page and verify header links
    Then I should see Shark logo
    Then I should see products link
    Then I should see Technologies link
    Then I should see parts and accessories link
    Then I should see offers link
    Then I should see support link

  @verifyFooterSharkUS
  @smokeSharkUS
  Scenario: Go To Home Page and verify footer links
    Then I should see footer links