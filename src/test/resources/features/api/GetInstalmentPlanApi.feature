# Dummy
Feature: Get Latest instalment plan details for an Order placed by Customer

#  @api
#    #Base URI = https://payinst-int.sharkninjapps.com/
#  Scenario: Get instalment plan for a particular order
#    Given the order id
#    When the user send GET instalment plan request
#    Then Json response is received with status code "200"
#    And the get response match with the aspects
#      | aspects  |
#      | title    |
#      | lifecyle |

#request.header("authentication-token","encryption0@PBEWithMD5AndTripleDES:qbcdDj/gFFs=|xVJcHL6RWGiMGOkYr8rp8aSnCF0asUjhEkZ4JmCq018rr9n3C9YZ4g==");
@api
    #URI = https://payinst-int.sharkninjapps.com
  Scenario: Validate the Json schema for Get instalment plan API
    Given the query param are
      | key | value     |
      | orderId   | INT-101000165511 |
    When the GET instalment plan request is triggered
   Then the JSON schema match with "getinstalmentplan.json"
