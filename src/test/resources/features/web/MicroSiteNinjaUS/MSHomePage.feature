@regNinjaUSMicroSiteNexus
@regNinjaUSMicroSite
Feature: Verify Homepage - Product tiles, Product comparison block & their links

  Background: Go To Home Page
    Given I navigate to the "Home" page on MicroSite shark US


    Scenario: Verify Homepage - Product tiles, Product comparision block & their links
      And i should see one product selected
      And "pdp.page.promotext1" is visible
      Then i selected second product positioned at '2'
      And "pdp.page.promotext2" is visible
