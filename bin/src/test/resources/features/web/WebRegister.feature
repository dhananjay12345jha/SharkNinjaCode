#This is a sample Feature file with an example of a single scenario.
#Every feature file starts with "Feature:" Keyword which describes what Feature is under test.
#tags are used for running entire Feature, a single Scenario or group of Feature or Scenarios.
#Tags are annotated with "@" symbol
@gui @desktop
Feature: Browse: As a new user I want to know more about Salmon and what they do
  so that I can make decisions working with Salmon

  #This is a typical scenario Example. Every Scenario starts with "Scenario:" keyword.
  #Scenarios are represented in Given When Then syntax
  @mytest
  Scenario: Browse main navigation - What we do
    Given i navigate to the Salmon "Home" page
    When i click what we do on home page
    Then i can see feature projects

  Scenario: Basic test to flex the master page Object
    Given i navigate to the Salmon "Home" page
    Then wait for page to load
    #Then is page loaded with title of "We're a Top 5 Digital Agency - Salmon"
    #Then I scrape homePage for all available Buttons
    #And I scrape homePage for all available links
    #And I scrape homePage for all available lists
    #And I scrape homePage for all available images


 # Scenario Outline: I want to sign up for the newsletter
 #   Given i navigate to the Salmon "Home" page
   # When I enter my email id "<emailId>" in newsletter field
   # And I click on SIGN UP
   # Then i should see "<ThankYouMessage>"
   # Examples:
   #   |emailId|ThankYouMessage|
   #   |varma@gmail.com|newsletter.thankyou|
  #    |jasper@gmail.com|newsletter.thankyou                  |