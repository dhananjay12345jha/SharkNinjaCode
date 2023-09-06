#@regSharkUK
#@regSharkDE
#@regNinjaUK
#@regNinjaDE
#@regSharkDESuite2
#@regNinjaDESuite2
#@regNinjaUKSuite2
#@regSharkUKSuite2
#Feature: Add Address: I want to add address and verify if address is added successfully.
#
#
#Background: Go To Login Page and enter the required Details
#	Given I navigate to the "Home" page
#	And click on accept cookies if visible
#	And click on accept email if visible
#	And I click on sign in link
##	And click on accept cookies if visible
#	And I should be on log in page
#	When I enter valid "login.email.austria" and "login.password.austria"
#	Then I am successfully logged in
##	And click on accept cookies if visible
#

#	#commenting this feature file as suggested by Dhananjay due to development of Austria in progress
  # And we will uncomment this once development of Austria is done
#

#	@excludeSharkUK
#	@excludeNinjaUK @austriaaddress
#Scenario: Verify address is successfully added with correct details and then delete the added address
#	When I click on Addresses
#	Then I should be on Addresses page
#	When I click add address link
#	Then I Should see form to add address
#	And i select country as Austria from country selection list
#	When I fill the form to add austria address
#	And I click on save address
#	Then Address should be saved
#	When I click to delete last added address
#	And I click on delete button
#
