@regSNEXUKSuit
@regSNEXEUSuit
Feature: Verify Approval/Reject of Fraud Check orders

  Background: To Open SNEX Application and Select Country And Comes To Order Page
    Given I navigate to the "Home" page
    When I login in into SNEX with "login.email" and "login.password"
    Then I should be on SNEX dashboard page
    And I select SNEX country from dropdown as defined in prop file

  Scenario: Verify Approval of Fraud Check orders from Order Details Tab- Approved
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check
    And Before approval save the order number into the properties file as "order.number"
    Then I click on first order
    Then I click On fraud activity
    Then I select the check-box
    Then I click on order option
    And I select the option "Approve Fraud Check" from options
    Then I enter the comment message
    And I click on confirm button
    Then I click on order tab in main menu
    Then I click on advance search
    Then I enter the save order in "order.number"
    Then I click on search Button
    Then Verify the status of approved order

  Scenario: Verify Reject of Fraud Check orders from Order Details Tab- Reject
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check
    And Before approval save the order number into the properties file as "order.number"
    Then I click on first order
    Then I click On fraud activity
    Then I select the check-box
    Then I click on order option
    And I select the option "Deny Fraud Check" from options
    Then I enter the comment message
    And I click on confirm button
    Then I click on order tab in main menu
    Then I click on advance search
    Then I enter the save order in "order.number"
    Then I click on search Button
    Then Verify the status of Reject order

  Scenario: Verify Approval of Fraud Check orders from Order option Tab- Bulk Approval
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check
    And Before approval save the order number into the properties file as "order.number"
    Then I select the check-box
    Then I click on order option
    And I select the option "Approve Fraud Check" from options
    Then I enter the message in comment box
    And I click on confirm Button
    Then I click on order tab in main menu
    Then I click on advance search
    Then I enter the save order in "order.number"
    Then I click on search Button
    Then Verify the status of approved order

  Scenario: Verify Denial of Fraud Check orders from Order option Tab- Bulk Denial
    Then I Click on  search fraud checks
    Then Verify the order status of fraud check
    And Before approval save the order number into the properties file as "order.number"
    Then I select the check-box
    Then I click on order option
    And I select the option "Deny Fraud Check" from options
    Then I enter the message in comment box
    And I click on confirm Button
    Then I click on order tab in main menu
    Then I click on advance search
    Then I enter the save order in "order.number"
    Then I click on search Button
    Then Verify the status of Reject order
