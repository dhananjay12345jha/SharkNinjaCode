@testAll
@regressionSharkCA
@accountDetails
@regSharkCASuit1
Feature: Account Details: Login as a valid user and verify profile update functionality

  Background: Login with valid user and navigate to Account Setting page
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file
    When I click on sign in link for SNCA
    And I login with valid Update Profile credentials
    Then I should be on My Account page
    When I click on Account Setting
    Then I should be on Account Setting page

  @updateAccountEmail
  Scenario: Verify User can update email
    #invalid new email
    When I enter new email with value "email.update.invalid"
    Then I should get error message "error.email.invalid" while action "Update Email"
    #invalid password
    When I enter new email with value "email.update"
    And I enter password with value "password.incorrect"
    And Click on Update Email button
    Then I should get error Alert with message "error.updateemail"
    #update the email
    When I enter new email with value "email.update"
    When I enter password with value "login.updateProfile.password"
    And Click on Update Email button
    Then I should get Success Alert with message "successMsg.updateemail"
    #revert the changes
    When I enter new email with value "login.updateProfile.userName"
    And I enter password with value "login.updateProfile.password"
    And Click on Update Email button
    Then I should get Success Alert with message "successMsg.updateemail"

#@updateAccountPassword
  Scenario: Verify User can update password
  #incorrect current password
    When I enter current password with value "confirm.password.mismatch"
    And I enter new password with value "password.valid"
    When I enter confirm password with value "password.valid"
    When Click on edit password button
    Then I should get error Alert with message "errorMsg.updatePasssword"
    #incorrect format for new password
    When I enter current password with value "login.updateProfile.password"
    And I enter new password with value "password.invalid"
    Then I should get error message "error.password.invalid" while action "Update Password"
  #mismatch in confirmation password
    When I enter new password with value "password.valid"
    And I enter confirm password with value "confirm.password.mismatch"
    Then I should get error message "error.mismatchNewPassword" while action "Update Password"
  #update correct values
    When I enter confirm password with value "password.valid"
    When Click on edit password button
    Then I should get Success Alert with message "successMsg.updatePasssword"
  #revert changes
    When I click on Account Setting
    Then I should be on Account Setting page
    When I enter current password with value "password.valid"
    And I enter new password with value "login.updateProfile.password"
    And I enter confirm password with value "login.updateProfile.password"
    When Click on edit password button
    Then I should get Success Alert with message "successMsg.updatePasssword"


#@updateAccountProfile
  Scenario: Verify User can update profile
    When I enter first name with value "account.profile.invalid.name"
    Then I should get error message "error.invalid.FirstName" while action "Update Profile"
    When I enter first name with value "account.profile.first.name"
    Then Error message should not be displayed while action "Update Profile"
    When I enter last name with value "account.profile.invalid.name"
    Then I should get error message "error.invalid.LastName" while action "Update Profile"
    When I enter last name with value "account.profile.last.name"
    Then Error message should not be displayed while action "Update Profile"
    When I enter phone number with value "account.profile.phone.invalid"
    Then I should get error message "error.invalid.phone" while action "Update Profile"
    When I enter phone number with value "account.profile.phone.valid"
    Then Error message should not be displayed while action "Update Profile"
    And Click on Update Details
    Then I should get Success Alert with message "successMsg.updateProfile"
    When I click on Account Setting
    Then I should be on Account Setting page
    And I enter first name with value "account.profile.firstname.revertchange"
    And I enter last name with value "account.profile.lastname.revertchange"
    When I enter phone number with value "account.profile.phone.revertchange"
    And Click on Update Details
    Then I should get Success Alert with message "successMsg.updateProfile"

