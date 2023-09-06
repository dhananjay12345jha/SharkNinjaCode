   ## @Author: Sanket Jha


   @ApiTest_UK
   @ApiTest_DE
   @ApiTest_CA
   @ApiTest_US
   Feature: To verify that customer should receive password reset link email

     Scenario: to verify that with valid email id and brand select as "Shark" request is getting hit
       When set email id as "sanket.jha@wundermanthompson.com" and brand as "Shark"
       Then hit the security reminder api
       Then response code should be 201
       And in response body uri should contains "/snap/security/reminder/"

     Scenario: to verify that with valid email id and brand select as "Ninja" request is getting hit
       When set email id as "sanket.jha@wundermanthompson.com" and brand as "Ninja"
       Then hit the security reminder api
       Then response code should be 201
       And in response body uri should contains "/snap/security/reminder/"


     Scenario: to verify that with invalid email id and valid brand select as "Shark" request is getting hit
       When set email id as "test@.com" and brand as "Shark"
       Then hit the security reminder api
       Then response code should be 400
#       And in response body should contains message "The following attributes are invalid: test@.com"
#       And in response body should contains message "Bad Request (The following attributes are invalid: email)"
       And in response body should contains message "{"error":"The following attributes are invalid: email"}"


     Scenario: to verify that with valid email id and invalid brand select as "Sharky" request is getting hit
       When set email id as "test@test.com" and brand as "Sharky"
       Then hit the security reminder api
       Then response code should be 400
       And in response body should contains message "invalid email or brand value"