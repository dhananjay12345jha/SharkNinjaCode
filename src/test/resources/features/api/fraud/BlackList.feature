##########################################################################################################
###############################API Used of BLACKLIST Controller are given below###########################
## GET/blacklists-->Get a list of all Black List##
## GET/blacklists/{blId}-->Get Black List Details##
## POST/blacklists-->Create New Black List ##
## PUT/blacklists/{blId}-->Update status and name of a Black List
## DELETE/blacklists/{blId}-->Delete Blacklist
## @Author: Sanket Jha
@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
Feature: To validate GET/BlackList and GET/BlackList/{blId} apis, records should be present in DataBase.


  Scenario: Verify that GET /blacklists api returns list of all available black lists
    When GET blacklists api has been hit
    Then response of the api should be 200
    And data list returned in response should be greater than zero
    And value of the key "totalBlackListRecords" should be greater than zero
    Then save the first black list id against key "blId.to.test" into config file for later use


  Scenario: Verify that GET /blacklists/{blId} api returns details for a requested black list id
    When GET particular blacklist details api with id "blId.to.test" has been hit
    Then response code of api should be 200
    And blId returned in response should be equal to "blId.to.test"


  Scenario: Verify that GET /blacklists/{blId} api returns 404 when invalid black list id
    When set the blacklist id as "12312412" and then hit GET/blacklist/blId request
    Then response code of api should be 404
    And message in response should be "Not Found"



 ##---------------------------POST BlackList---------------------------##


  Scenario: Verify that user is able to create a new blacklist with action as REVIEW
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
    When GET particular blacklist details api with id "blId.captured.from.newly.created.request" has been hit
    Then response code of api should be 200
    And blId returned in response should be equal to "blId.captured.from.newly.created.request"


  Scenario: Verify that user is able to create a new blacklist with action as REJECT
    When providing value of action as "REJECT" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
    When GET particular blacklist details api with id "blId.captured.from.newly.created.request" has been hit
    Then response code of api should be 200
    And blId returned in response should be equal to "blId.captured.from.newly.created.request"


  Scenario: Verify that error message should be shown when hit blacklist api to create blacklist with invalid data
    When providing value of action as "NULL" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 400
    And message shown in response should be "Bad Request"

##------------------PUT BlackList--------------##

  Scenario: To verify that user is able to update the parameters against blacklist id
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
    When setting the variables action as "REJECT" and name as "TEST"
    Then hit PUT api which is having blId as "blId.captured.from.newly.created.request" to update the action and name
    When GET particular blacklist details api with id "blId.captured.from.newly.created.request" has been hit
    Then response code of api should be 200
    And blId returned in response should be equal to "blId.captured.from.newly.created.request"
    And values of variable action should be "REJECT" and name as "TEST" from response


  Scenario: To verify that PUT api should not update the blacklist when we provide parameters action and name as empty against blacklist id
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
    When setting the variables action as " " and name as " "
    Then hit PUT api which is having blId as "blId.captured.from.newly.created.request" to update the action and name
    Then in response code should be 400 and message should be "Bad Request"


  ###-------------------DELETE Blacklist/blId---------------------###


  Scenario: To verify that user should be able to remove the blacklist having valid blId
    When providing value of action as "REVIEW" and name "Randomly Generated"
    Then hit the POST/Blacklist api
    Then response of the POST/Blacklist api should be 200
    And message shown in response should be "Black List is successfully created"
    Then capture and store the blId of newly created request into config file
    Then set up the value of blid as "blId.captured.from.newly.created.request" and hit the request
    Then response code should be 200 and message should be "Black List is successfully removed"
##---using GET blacklist api to validate that blacklist has been removed-----##
    When GET particular blacklist details api with id "blId.captured.from.newly.created.request" has been hit
    Then response code of api should be 404
    And message in response should be "Not Found"
