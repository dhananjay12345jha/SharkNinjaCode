@testAll
@header
@smokeSharkCAOld
Feature: Header: Verify links in header, click all header links and verify is redirect to correct page.


Background: Go To Home Page
    Given I navigate to the "Home" page


Scenario: Verify Header links
	When I click on register my guarantee link of Shark CA
	Then I should be on register my guarantee page having text either "Register a Product" or "Enregistrer un produit"
	When I click on customer care link of Shark CA
	Then I should be on customer care page
	When I click on tipsAndAdvice link
    Then I should be on tips and advice page
	When I click on offers link
	Then I should be on offers page
	When I click on cart link
	Then I should see empty cart message
