#@testAll
#@regressionSharkCA
#@accountDetails
#@regSharkCASuit1
#Feature: Account Details: Login as a valid user and update the preferences
#
#  Background: Login with valid user and navigate to Account Setting page to update preferences
#    Given I navigate to the "Home" page
#    And click on accept cookies if visible
#    And select language defined in property file
#    When I login with valid credentials
#    Then I should be on My Account page
#    When I click on Account Setting
#    Then I should be on Account Setting page
#
#  @subscribeEmailPreference
#  Scenario: Verify User can subscribe email preferences
#    And I enter first name in preference section
#    And I enter last name in preference section
#    And I check the checkboxes for preference form
#    And Click on Update preferences
#    When I Click on manage preferences
#    And all preferences shown be checked
#
#
#  @UnsubscribeEmailPreference
#  Scenario: Verify User can unsubscribe email preferences
#    And I enter first name in preference section
#    And I enter last name in preference section
#    And I uncheck the checkboxes in preference section
#    And Click on Update preferences
#    When I Click on manage preferences
#    And all preferences shown be unchecked
#
