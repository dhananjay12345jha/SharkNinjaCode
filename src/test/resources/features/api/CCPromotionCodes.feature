## @Author: Sanket Jha

@ApiTest_UK
@ApiTest_DE
@ApiTest_CA
@ApiTest_US
  Feature: To validate that promotions should be there in api response

    Scenario: To verify that when user hit the cc promotion api and validate response
      When cc promotion api has been hit
      Then in response 200 should be there
      And in response elements array contains promotion codes and should be greater than zero