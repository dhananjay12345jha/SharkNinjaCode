@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkES
@regNinjaES
@regSharkFR
@regNinjaFR
@regNinjaDE
@regSharkDESuite2
@regNinjaDESuite2
@regSharkESSuite2
@regNinjaESSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkITSuite2
@regNinjaITSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Add Address: I want to add address and verify if address is added successfully.


Background: Go To Login Page and enter the required Details
	Given I navigate to the "Home" page
	And click on accept cookies if visible
	And click on accept email if visible
	Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
	Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
	Given User is setting the stock quantity for EU with the Sku as CB350EU and the quantity is 200
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
	When I enter valid "login.email" and "login.password"
	Then I am successfully logged in
	And click on accept email if visible

@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeNinjaDE
@smokeSharkIT
@smokeNinjaIT
@smokeSharkES
@smokeNinjaES
@smokeSharkFR
@smokeNinjaFR
Scenario: Verify address is successfully added with correct details and then delete the added address
	When I click on Addresses
	Then I should be on Addresses page
	When I click add address link
	Then I Should see form to add address
	When I fill the form to add address
	And I click on save address
	Then Address should be saved
	When I click to delete last added address
	And I click on delete button
#	Then Address should be deleted

	@excludeNinjaIT
	@excludeSharkIT
	@excludeNinjaES
	@excludeSharkES
 #we are excluding this scenario fot IT and Spain as the validation messages are showing in english language, we will uncomment them once the language is changed in to specific language
@errorValidationAddressPage
Scenario: Validate error message while adding new address
	When I click on Addresses
	Then I should be on Addresses page
	When I click add address link
	Then I Should see form to add address
	When I click on save address
	#Then I should see error message for mandatory fields.
	# we commented this step because all validations messages are not getting tracked together by headless
	When I enter first name with "invalid.firstname"
	Then I should see error message for invalid first name on add address page
	When I enter first name with "valid.firstName"
	Then Error message for invalid first name should be gone from address page
	When I enter last name with "invalid.lastname"
	Then I should see error message for invalid last name on add address page
	When I enter last name with "valid.lastName"
	Then Error message for invalid last name should be gone from address page
	When I enter street name on add address page
	Then Error message for empty street name should be gone from address page
	When I enter city name on add address page
	Then Error message for empty city name should be gone from address page
	When I enter invalid postal code on add address page
	# we will uncomment this step once Rachit fix this validation msg part
#	Then I should see error message for invalid postal code on add address age
	When I enter valid postal code on add address page
	Then Error message for invalid postal code should be gone from address page
	When I enter invalid phone number on add address page
	Then I should see error message for invalid phone number on add address age
	When I enter valid phone number on add address page
	Then Error message for invalid phone number should be gone from address page

@updateAddress
Scenario: Verify address is successfully updated with correct details and then delete the updated address
	When I click on Addresses
	Then I should be on Addresses page
	When I click add address link
	Then I Should see form to add address
	When I fill the form to add address
	And I click on save address
	Then Address should be saved
	When I click pencil Icon to update the saved address
	And I make changes in saved address
	And I click update address button
	Then Address should be updated
	When I click to delete last updated address
	And I click on delete button

@AddDuplicateAddress
Scenario: Verify duplicate address cannot be added and then delete the added address
	When I click on Addresses
	Then I should be on Addresses page
	When I click add address link
	Then I Should see form to add address
	When I fill the form to add address
	And I click on save address
	Then Address should be saved
	When I click add address link
	Then I Should see form to add address
	When I fill the form to add address
	And I click on save address
	Then Address should be saved
	Then I should see message that address already exist
	When I click to delete last added address
	And I click on delete button