@testAll
@regressionSharkCA
@regSharkCASuit2

Feature:  Verify all the images in the Parts & Accessories and Product page for Shark CA

Background: Go To Home Page
	Given I navigate to the "Home" page
	And click on accept cookies if visible
	And select language defined in property file

	Scenario: Verify all the images in Products for all pages for Shark CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category SharkCA Vaccum "products.value1"
#		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category SharkCA Vaccum "products.value2"
#		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category SharkCA Vaccum "products.value3"
#		Then I should verify all the images in Products Category for all pages for CA
		When I move cursor over Product Category For CA
		And I clicked on CA product Category SharkCA Vaccum "products.value4"
		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category SharkCA Vaccum "products.value5"
#		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category Air Purifiers "products.value6"
#		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category Hair Care "products.value7"
#		Then I should verify all the images in Products Category for all pages for CA
		When I move cursor over Product Category For CA
		And I clicked on CA product Category Mops & Irons "products.value8"
		Then I should verify all the images in Products Category for all pages for CA
#		When I move cursor over Product Category For CA
#		And I clicked on CA product Category Mops & Irons "products.value9"
#		Then I should verify all the images in Products Category for all pages for CA







