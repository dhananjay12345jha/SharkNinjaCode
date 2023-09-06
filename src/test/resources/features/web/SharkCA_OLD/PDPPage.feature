@testAll
@smokeSharkCAOld
@regressionSharkCAOld
Feature: Verify PDP page and its related tabs

Background: Navigate to Search page based on valid input
  Given I navigate to the "Home" page
  And click on accept cookies if visible
  And select language defined in property file
  When I open search bar and entered the product as "ZU62C" or "ZU62C"
  And I clicked on the search button
  And language selected should be equal to "select.language.to.test"
  Then page title should contains text "Search Result for 'ZU62C'" or "Résultat de recherche pour 'ZU62C'."
  And search result page should display text contains "ZU62C" or "« ZU62C »"
  And validate that number of product tiles shown should be greater than zero
  And language selected should be equal to "select.language.to.test"
  When I click on product which is not out of stock from search list
  Then I should be on PDP page of that product
  And language selected should be equal to "select.language.to.test"

@PDPRelatedTabs

Scenario:  Verify related tab on PDP page
  Then Verify PDP is displayed corresponding to clicked product
  And I should see add to Basket button
  And I should see add quantity field
  And I should see Features link in footer
  And I should see MoreDetails link in footer
  And I should see Tech spec link in footer
  And I should see In The Box link in footer
  And I should see FAQs link in footer
  And I should see Downloads link in footer
 # And I should see Parts/Accessories link in footer
  When I click Features link in footer
  Then Features div should be diplayed
  When I click More Details link in footer
  Then More Details div should be diplayed
  When I click Tech spec link in footer
  Then Tech spec div should be diplayed
  When I click In The Box  link in footer
  Then In The Box  div should be diplayed
  When I click FAQs link in footer
  Then FAQs div should be diplayed
  When I click Downloads  link in footer
  Then Downloads div should be diplayed
  #When I click Parts/Accessories  link in footer
  #Then Parts/Accessories  div should be diplayed


Scenario: Verify add to basket and navigate to it
  And I add the product to cart
 

Scenario: Verify to add more than 1 quanity to basket
  When  I update product quantity with "product.valid.quantity"
  And  I click on Add To Basket Button
  Then I should see the the cart is updated with "product.valid.quantity"


Scenario: Verify quantity field & their validations
  When I update product quantity with "product.negative.quantity"
  Then I should see error message that please enter 1 or more
  When I update product quantity with "product.zero.quantity"
  Then I should see error message that please enter 1 or more
  When I update product quantity with "product.max.quantity"
  Then I should see error message that Please enter 100 or less
  When I update product quantity with "product.valid.quantity"
  Then Add to basket button is enabled