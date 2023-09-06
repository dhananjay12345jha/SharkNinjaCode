Feature: Update subscription in stripe when customer wants to pay full outstanding balance for an order

@api
    #URI = https://payinst-int.sharkninjapps.com
  Scenario: Validate the Json schema for Get instalment plan API
    Given the query param are
      | key | value     |
      | orderId   | INT-101000165511 |
#    When the POST request for pay full balance is triggered
#   Then the JSON schema match with "payFullBalance.json"
