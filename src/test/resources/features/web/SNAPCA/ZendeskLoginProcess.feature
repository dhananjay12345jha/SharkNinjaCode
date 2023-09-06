@regSNAPCASuit
Feature: Zendesk Login: Validate login using valid credentials 

@SNAPSmoke
Scenario: Go To Zendesk Login Page and enter the required Details 
	Given I navigate to the zendesk "Home" page
	When I submit valid zendesk credentials
#	Then I should be on Zendesk Agent Dashboard
	And I should be able to Add a new Ticket
	