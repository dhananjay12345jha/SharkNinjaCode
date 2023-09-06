Feature: As a Financial Times developer I would like to know existing articles so that I can write new ones

  @api
    #URI = https://api.ft.com
  Scenario: Retrieve search results based on search filters
    Given the search content "ftSearchContent.json" is created
    When the user send GET POST search content request
    Then the valid Json response is received with status code "200"
#    And the post response match with the aspects
#      | aspects  |
#      | title    |
#      | lifecyle |

