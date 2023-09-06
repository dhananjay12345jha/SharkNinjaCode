@regSharkUK
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regSharkDE
@regNinjaUK
@regNinjaDE
@smokeSharkUK
@smokeSharkIT
@smokeSharkDE
@smokeNinjaUK
@smokeSharkES
@smokeNinjaES
@smokeNinjaIT
@smokeNinjaDE
@regSharkDESuite2
@regSharkITSuite2
@regNinjaDESuite2
@regSharkESSuite2
@regNinjaESSuite2
@regNinjaITSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Search


#    Scenario: Verify Search with wildcard input
#	    Given I navigate to the "Home" page
#		And click on accept cookies if visible
#	    When I search for the product "search.wildcard"
#	    Then search results page is displayed
#	    And validate Search result is displayed corresponding to the string which was searched


	Scenario: Verify Search based on valid input
	   Given I navigate to the "Home" page
		And click on accept cookies if visible
		And click on accept email if visible
	   When I search for the product "search.validInput"
	   Then search results page is displayed
	   And validate Search result is displayed corresponding to the string which was searched


	@InvalidSearch
	Scenario: Verify Search based on invalid input
		Given I navigate to the "Home" page
		And click on accept cookies if visible
		And click on accept email if visible
		When I search for the product "search.invalidInput"
		Then I should get valid error message that no results found

	@ValidSearchWithNoResult
	Scenario: Verify Search based on valid input but no search result(appropriate messaging)
		Given I navigate to the "Home" page
		And click on accept cookies if visible
		And click on accept email if visible
		When I search for the product "search.validInput.with.NoResult"
		Then I should get valid error message that no results found

