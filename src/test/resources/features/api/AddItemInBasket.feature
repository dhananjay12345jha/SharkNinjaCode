   ## @Author: Sanket Jha

   @ApiTest_UK
   @ApiTest_DE
   @ApiTest_CA
   @ApiTest_US
   Feature: To verify that user is able to add item in empty basket newly created

     Background: New Basket Has Been Created And Verified
       Given Uri for Email "icm.emailId.to.generate.token" has been obtained
       And Token has been generated corresponding to uri obtained
       When hit post request to create basket
       Then status code should be 200
       And value of key title in response should not be null or empty
       When hit get basket request to fetch basket Key
       Then status code should be 200
       And value of key ID in response should be equal to basketKey

     Scenario: To Verify that User Is Able to add items in basket
       Then hit the get request to fetch basket details
       Then details should be empty as basket is newly created
       When adding an item having item code as "product.specificSKU"
       Then hit the get request to fetch basket details
       Then validating when fetching basket details quantity value should be 1
       When adding an item having item code as "product.specificSKU"
       Then hit the get request to fetch basket details
       Then product quantity should get increase by one