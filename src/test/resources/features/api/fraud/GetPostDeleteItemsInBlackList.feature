#################################################################################################################
#################################API Used of BLACKLIST Controller are given below################################
##POST/blacklists-->Create New Black List##
##POST/blacklists/{blId}/blacklistitems-->Add single/multiple Blacklist Items in a BlackList##
##GET/blacklists/blacklistitems-->>Get Black list item Details##
##DELETE/blacklists/{blId}/blacklistitems--> Delete single/multiple Blacklist Items in a BlackList##
## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate deleting multiple items from a blacklist

  Scenario: For CUSTOMER_ID Creating a new Blacklist, adding customer_id, fetching blacklist item, deleting the blacklist item
  #      Generating new blacklist first then adding the item and then deleting the same
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
  #      Customer Id having value 9001 has been added
    Then set the value of valueType as "CUSTOMER_ID" and valueList as "9001"
    Then hit the POST api to add items in black list having blacklist id "blId.captured.from.newly.created.request"
    Then status in response should be 200 and message "ok"
  #     hit the get to fetch the details that customer id has been float into blacklist
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "9001" in GET BlackList items api
    And setting the value type as "CUSTOMER_ID"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And verify value returned in response should be "9001" and note the id corresponding to that in config file
  #    deleting the particular id and check again if it there in response
    Then set the blId in delete black list items api as "blId.captured.from.newly.created.request"
    Then set the value type as "CUSTOMER_ID" and id as "id.of.black.list.item.type"
    Then hit the delete items from black list api
    Then response status should be 200 and message should be "Items deleted successfully from Black List" of delete items from blacklist
  #     again hitting the get black list items api to fetch the details
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "9001" in GET BlackList items api
    And setting the value type as "CUSTOMER_ID"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "totalBlackListRecords" returned in response should be zero

  Scenario: For POSTAL_CODE Creating a new Blacklist, adding POSTAL CODE, fetching blacklist item, deleting the blacklist item
    #      Generating new blacklist first then adding the item and then deleting the same
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
  #      Customer Id having value 9001 has been added
    Then set the value of valueType as "POSTAL_CODE" and valueList as "110088"
    Then hit the POST api to add items in black list having blacklist id "blId.captured.from.newly.created.request"
    Then status in response should be 200 and message "ok"
  #     hit the get to fetch the details that customer id has been float into blacklist
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "110088" in GET BlackList items api
    And setting the value type as "POSTAL_CODE"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And verify value returned in response should be "110088" and note the id corresponding to that in config file
  #    deleting the particular id and check again if it there in response
    Then set the blId in delete black list items api as "blId.captured.from.newly.created.request"
    Then set the value type as "POSTAL_CODE" and id as "id.of.black.list.item.type"
    Then hit the delete items from black list api
    Then response status should be 200 and message should be "Items deleted successfully from Black List" of delete items from blacklist
  #     again hitting the get black list items api to fetch the details
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "110088" in GET BlackList items api
    And setting the value type as "POSTAL_CODE"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "totalBlackListRecords" returned in response should be zero

  Scenario: For EMAIL Creating a new Blacklist, adding EMAIL, fetching blacklist item, deleting the blacklist item
    #      Generating new blacklist first then adding the item and then deleting the same
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
  #      Customer Id having value 9001 has been added
    Then set the value of valueType as "EMAIL" and valueList as "sanket.jha@wundermanthmopson.com"
    Then hit the POST api to add items in black list having blacklist id "blId.captured.from.newly.created.request"
    Then status in response should be 200 and message "ok"
  #     hit the get to fetch the details that customer id has been float into blacklist
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "sanket.jha@wundermanthmopson.com" in GET BlackList items api
    And setting the value type as "EMAIL"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And verify value returned in response should be "sanket.jha@wundermanthmopson.com" and note the id corresponding to that in config file
  #    deleting the particular id and check again if it there in response
    Then set the blId in delete black list items api as "blId.captured.from.newly.created.request"
    Then set the value type as "EMAIL" and id as "id.of.black.list.item.type"
    Then hit the delete items from black list api
    Then response status should be 200 and message should be "Items deleted successfully from Black List" of delete items from blacklist
  #     again hitting the get black list items api to fetch the details
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "sanket.jha@wundermanthmopson.com" in GET BlackList items api
    And setting the value type as "EMAIL"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "totalBlackListRecords" returned in response should be zero


  Scenario: For PHONE Creating a new Blacklist, adding PHONE NUMBER, fetching blacklist items, deleting the blacklist item
  #      Generating new blacklist first then adding the item and then deleting the same
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
  #      Customer Id having value 9001 has been added
    Then set the value of valueType as "PHONE" and valueList as "9876543210"
    Then hit the POST api to add items in black list having blacklist id "blId.captured.from.newly.created.request"
    Then status in response should be 200 and message "ok"
  #     hit the get to fetch the details that customer id has been float into blacklist
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "9876543210" in GET BlackList items api
    And setting the value type as "PHONE"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And verify value returned in response should be "9876543210" and note the id corresponding to that in config file
  #    deleting the particular id and check again if it there in response
    Then set the blId in delete black list items api as "blId.captured.from.newly.created.request"
    Then set the value type as "PHONE" and id as "id.of.black.list.item.type"
    Then hit the delete items from black list api
    Then response status should be 200 and message should be "Items deleted successfully from Black List" of delete items from blacklist
  #     again hitting the get black list items api to fetch the details
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "9876543210" in GET BlackList items api
    And setting the value type as "PHONE"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "totalBlackListRecords" returned in response should be zero


  Scenario: For ADDRESS Creating a new Blacklist, adding ADDRESS, fetching blacklist item, deleting the blacklist item
  #      Generating new blacklist first then adding the item and then deleting the same
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
  #      Customer Id having value 9001 has been added
    Then set the value of valueType as "ADDRESS"
    And set the value of city as "NOIDA"
    And set the value of country as "INDIAA"
    And set the value of pincode as "201309"
    And set the value of state as "UTTAR PRADESH"
    And set the value of street as "BLOCK B"
    And set the value of street2 as "INDUSTRIAL AREA"
    Then hit the POST api to add items in black list having blacklist id "blId.captured.from.newly.created.request"
    Then status in response should be 200 and message "ok"
  #     hit the get to fetch the details that customer id has been float into blacklist
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "INDIAA" in GET BlackList items api
    And setting the value type as "ADDRESS"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And verify value of country returned in response should be "indiaa" and note the id corresponding to that in config file
  #    deleting the particular id and check again if it there in response
    Then set the blId in delete black list items api as "blId.captured.from.newly.created.request"
    Then set the value type as "ADDRESS" and id as "id.of.black.list.item.type"
    Then hit the delete items from black list api
    Then response status should be 200 and message should be "Items deleted successfully from Black List" of delete items from blacklist
  #     again hitting the get black list items api to fetch the details
    Given set the value of limit as 100 and page numbers as 0
    And setting value to search as "INDIAA" in GET BlackList items api
    And setting the value type as "ADDRESS"
    And setting the value of blId in GET BlackList items api as "blId.captured.from.newly.created.request"
    Then hit the GET Blacklist items api
    Then response code of GET Blacklist api should be 200
    And "totalBlackListRecords" returned in response should be zero
