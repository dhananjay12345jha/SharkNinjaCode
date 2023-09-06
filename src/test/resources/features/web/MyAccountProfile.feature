@regSharkUK
@regSharkDE
@regNinjaUK
@regNinjaDE
@regSharkDESuite1
@regNinjaDESuite1
@regNinjaUKSuite1
@regSharkUKSuite1
Feature: Login: I want to Login as a valid user so that I can perform the operation on my profile

#Background: Go To Login Page and enter the required Details
#    Given I navigate to the "Home" page
#	And click on accept cookies if visible
#    And I click on sign in link
#    And I should be on log in page
#	When I enter valid "login.update.email" and "login.update.password"
#	Then I am successfully logged in
#	When I click on Account Details
#	Then I should be on Profile page

	# commenting this as this will be done manually as changing username/password again and again clocks the users
#@updateAccountPassword
#Scenario: Verify User can update password
#	Given I navigate to the "Home" page
#	And click on accept cookies if visible
#	And I click on sign in link
#	And I should be on log in page
#	When I enter valid "login.updatedetail.email" and "login.updatedetail.password"
#	Then I am successfully logged in
#	When I click on Account Details
#	Then I should be on Profile page
#	When I click on pencil icon next to password
#	Then I should be on edit password page
#	When I fill current password with "login.updatedetail.password"
#	And I fill new password with "password.invalid"
#	Then I should see error message for password
#	When I fill new password with "password.valid"
#	Then Error message for password should be gone
#	When I fill confirm password with "confirm.password.mismatch"
#	Then I should get error message as password do not match
#	When I fill confirm password with "password.valid"
#	Then Error message for confirm password should be gone
#	When Click on save changes
#	Then password should be updated
#	When I click on pencil icon next to password
#	Then I should be on edit password page
#	When I fill current password with "password.valid"
#	And I fill new password with "login.updatedetail.password"
#	And I fill confirm password with "login.updatedetail.password"
#	And Click on save changes
#	Then password should be updated

@updateAccountProfile
@excludeSharkIT
@excludeNinjaIT
@excludeSharkES
@excludeNinjaES

#we are excluding this scenario for IT and ES as we are facing validation message issues for the First name and Last Name that are not getting converted into Specific languages, once it is fixed will include it
Scenario: Verify User can update profile
	Given I navigate to the "Home" page
	And click on accept cookies if visible
	And click on accept email if visible
	Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200
	And I click on sign in link
	And click on accept cookies if visible
	And I should be on log in page
	#When I enter valid "login.update.email" and "login.update.password"
	When I enter valid "login.email" and "login.password"
	Then I am successfully logged in
	And click on accept email if visible
	When I click on Account Details
	Then I should be on Profile page
	When I click on pencil icon next to profile
	Then I should be on edit profile page
	When I select salutation by value "account.salutation.dr"
	And I fill first name with "account.profile.invalid.name"
	Then I should see error message for invalid first name
	When I fill first name with "account.profile.first.name"
	Then Error message for first name invalid gone
	When I fill last name with "account.profile.invalid.name"
	Then I should see error message for invalid last name
	When I fill last name with "account.profile.last.name"
	Then Error message for last name invalid gone
	When I fill phone number with "account.profile.phone.invalid"
	Then I should see error message for invalid phone number
	When I fill phone number with "account.profile.phone.valid"
	Then Error message for phone number invalid gone
	When I select Preferred Language
	And Click on save changes
	Then profile should be updated
	And Changes reflected on profile page
	When I click on pencil icon next to profile
	Then I should be on edit profile page
	When I select salutation by value "account.salutation.mr"
	And I fill first name with "account.profile.firstname.revertchange"
	And I fill last name with "account.profile.lastname.revertchange"
	When I fill phone number with "account.profile.phone.revertchange"
	And Click on save changes
	Then profile should be updated
# Due to listrack changes below test cases are not applicable

#@subscribeEmailPreference
#Scenario: Verify User can subscribe email preferences
#	Given I navigate to the "Home" page
#	And click on accept cookies if visible
#	And I click on sign in link
#	And I should be on log in page
#	When I enter valid "login.update.email" and "login.update.password"
#	Then I am successfully logged in
#	When I click on Account Details
#	Then I should be on Profile page
#	When I click on pencil icon next to profile
#	Then I should be on edit profile page
#	#When I enter email in preference form
##	And I enter first name in preference form
##	And I enter last name in preference form
#	And I check the checkbox for offers from shark ninja in preference form
#	And I check the checkbox for cleaning tips and articles from shark in preference form
#	And I check the checkbox for recipes and inspiration from ninja in preference form
#	And I check the checkbox for competition and whats new at shark in preference form
#	And I check the checkbox for competition and whats new at ninja in preference form
#	And I check the checkbox for black friday shark offers in preference form
#	And I check the checkbox for black friday ninja offers in preference form
#	And Click on save preferences
#	Then preferences should be saved
#	When Click on save changes
#	And I click on pencil icon next to profile
#	Then I should be on edit profile page
#	And all preferences shown checked
#
#@unsubscribeEmailPreference
#Scenario: Verify User can unsubscribe email preferences
#	Given I navigate to the "Home" page
#	And click on accept cookies if visible
#	And I click on sign in link
#	And I should be on log in page
#	When I enter valid "login.update.email" and "login.update.password"
#	Then I am successfully logged in
#	When I click on Account Details
#	Then I should be on Profile page
#	When I click on pencil icon next to profile
#	Then I should be on edit profile page
#	#When I enter email in preference form
##	And I enter first name in preference form
##	And I enter last name in preference form
#	And I uncheck the checkbox for offers from shark ninja in preference form
#	And I uncheck the checkbox for cleaning tips and articles from shark in preference form
#	And I uncheck the checkbox for recipes and inspiration from ninja in preference form
#	And I uncheck the checkbox for competition and whats new at shark in preference form
#	And I uncheck the checkbox for competition and whats new at ninja in preference form
#	And I uncheck the checkbox for black friday shark offers in preference form
#	And I uncheck the checkbox for black friday ninja offers in preference form
#	And Click on save preferences
#	Then preferences should be saved
#	When Click on save changes
#	And I click on pencil icon next to profile
#	Then I should be on edit profile page
#	And all preferences shown unchecked


# commenting this as this will be done manually as changing username/password again and again clocks the users

#@updateAccountEmail
#Scenario: Verify User can update email
#	Given I navigate to the "Home" page
#	And click on accept cookies if visible
#	And I click on sign in link
#	And I should be on log in page
#	When I enter valid "login.updatedetail.email" and "login.updatedetail.password"
#	Then I am successfully logged in
#	When I click on Account Details
#	Then I should be on Profile page
#	When I click on pencil icon next to email
#	Then I should be on edit email preference page
#	When I enter new email with "email.update"
#	#And I fill repeat new email with "email.update.confirmation.incorrect"
#	#Then I should get error message for invalid email.
#	When I fill repeat new email with "email.update"
#	Then error message for invalid email should be gone
#	When I enter password with "password.incorrect"
#	And Click on save changes
#	Then I should get error message for password incorrect
#	When I enter password with "login.updatedetail.password"
#	And Click on save changes
#	Then email should be updated
#	And updated email shown on profile page
#	When I click on pencil icon next to email
#	And I enter new email with "login.updatedetail.email"
#	And I fill repeat new email with "login.updatedetail.email"
#	And I enter password with "login.updatedetail.password"
#	And Click on save changes
#	Then email should be updated