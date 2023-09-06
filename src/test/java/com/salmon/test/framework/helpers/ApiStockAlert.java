package com.salmon.test.framework.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class ApiStockAlert {


   public static void stockUpdate(String locals,String sku, String quantity) {
       String env= Props.getExecutionEnv();
       String subscriptionKey="";

        String endpoint = "https://sharkninja-"+env+".azure-api.net/icm/bo/"+locals+"/importstock";
       if(Props.getExecutionEnv().equalsIgnoreCase("UAT")) {
           subscriptionKey  = "f7a1c4daf0b0442e81539810c8a7f40f";
       }
       else{
           subscriptionKey = "a407f967df8d4653a71ea5e35f72ffe9";
       }
        String authorization = "Basic c3RvY2tpbXBvcnR1c2VyOiFJbnRlclNob3AwMCE=";

        String requestBody = "[{\"shopArticleNo\": \"$productID\", \"atp\":$quantity}]";

        RestAssured.baseURI = endpoint;

        RestAssured.   given()
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .header("Authorization", authorization)
                .contentType("application/json")
                .body(requestBody.replace("$productID",sku).replace("$quantity",quantity)).log().all()
                .when()
                .post()
                .then()
                .statusCode(204);
    }
}





