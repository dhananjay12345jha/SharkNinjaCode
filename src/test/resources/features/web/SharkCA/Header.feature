@testAll
@header
@smokeSharkCA
@regSharkCASuit2
Feature: Header: Verify links in header, click all header links and verify is redirect to correct page.


Background: Go To Home Page
    Given I navigate to the "Home" page
	And click on accept cookies if visible
	And select language defined in property file

Scenario: Verify Header links
	When I click on register my guarantee link of Shark CA
#	And select language defined in property file
	Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"
	When I click on customer care link in header
	Then I must be on customer care page
	Then I click Shark logo
	And click on accept cookies if visible
	When I then click on cart link
	Then I must see empty cart message
	Then I click Shark logo to navigate to home page
	When I click on tips and advice link in header
    Then I must be on tips and advice page
#	When I click on offers link
#	Then I should be on offers page

