@regSharkDEMicroSite
@regSharkUKMicroSite
@regNinjaUKMicroSite
@regNinjaDEMicroSite
@regNinjaDEMicroSiteNexus
@regSharkDEMicroSiteNexus
@regSharkUKMicroSiteNexus
@regNinjaUKMicroSiteNexus
Feature: Verify Homepage - Product tiles, Product comparison block & their links

	Background: Go To Home Page
	Given I navigate to the "Home" page on MicroSite

@verifyHomePage
@regression
Scenario: Verify Homepage - Product tiles, Product comparision block & their links
	Then I should see product tiles
	And I click and navigate to the corresponding tiles
