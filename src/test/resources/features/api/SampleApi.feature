Feature: Sample CRUD operations to test a RESTful Service

  Scenario: Get the list of colours
    When I get the list of colours
    Then the Items are "retrieved"
    And the colour collections contain colour name


  @api
    #URI = https://community-open-weather-map.p.rapidapi.com/weather
  Scenario: Validate the correct location weather is received
    Given the query params are
      | key | value     |
      | q   | London,UK |
    When the GET current weather request is triggered
    Then  the "country" value is "GB" in the response
    And "name" is "London"

  @api
    # URI = https://recipe-puppy.p.rapidapi.com/
  Scenario: Validate the nested node data
    Given the query params are
      | key | value  |
      | q   | omelet |
    When the GET recipe request is sent
    Then ingredients of "Mild Curry Omelet" is received
    And list of titles is displayed

  @api
    #URI = https://community-open-weather-map.p.rapidapi.com/weather
  Scenario: Validate the Json schema for weather
    Given the query params are
      | key | value     |
      | q   | London,UK |
    When the GET current weather request is triggered
    Then the JSON schema matches with "weatherschema.json"

    # Test with @dummy tags  will fail as there is no real Service to test with.
  @dummy
  Scenario: Create an Item
    When I create an Item
      | itemType | itemName |
      | TABLET   | ipad     |
      | MOBILE   | iphone   |

    Then the Item is "created"

  @dummy
  Scenario: Update an Item
    When I update an Item
      | itemType | itemName |
      | TABLET   | tab pro  |
      | MOBILE   | note 5   |
    Then the Item is "updated"

  @dummy
  Scenario: Delete an Item
    When I delete an Item "tablet"
    Then the Item is "deleted"