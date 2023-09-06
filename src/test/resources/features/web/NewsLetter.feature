#@regSharkUK
#@regSharkDE
#@regNinjaUK
#@regNinjaDE
#@regSharkDESuit2
#@regNinjaDESuit2
#@regNinjaUKSuit2
#@regSharkUKSuit2
#Feature: Verify News Letter
#
#
#  Background: Go To Home Page
#    Given I navigate to the "Home" page
#    And click on accept cookies if visible
#    And I should see Newsletter Email text box in footer
#    And I should see offers checkbox in footer
#    And I should see newsletter submit button in footer
#    #And I should see unsubscribe link in footer
#
#  Scenario: Verify successful subscription from Join Mailing list in footer
#    When I entered email "login.email.Subscribe" in email textbox
#    And I checked or unchecked offerscheckbox base on "Offers.checkbox.value"
#    And I click on submit button in footer
#    Then I should be successfully subscribed

#due to listrek these are commented
#  Scenario: Verify successful unsubscription from Join Mailing list in footer
#    When I click on unsubscribe link
#    And  I entered valid email "login.email.Unsubscribe" in email textbox on unsubscribe form
#    And I click on submit button on form
#    Then I should see click here link for unsubscribe on iframe
#    When I click on click here link
#    Then I should be successfully unsubscribed

#  Scenario: Verify validation on text box
#    When I entered email "invalid.email.without@" in email textbox
#    Then I should get valid email required error message
#    When I entered email "invalid.email.without." in email textbox
#    And I click on submit button in footer
#    Then I should get Please enter a valid e-mail address error message









