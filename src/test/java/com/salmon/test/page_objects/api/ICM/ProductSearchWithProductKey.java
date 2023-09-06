package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductSearchWithProductKey
{

    private String baseuri= Props.getProp("icm.base.uri");
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(ProductSearchWithProductKey.class);

    public boolean hitRequestWithProductKey(String productKey)
    {

        boolean flag=false;

        try
        {
            RestAssured.baseURI = baseuri;

            RequestSpecification httpRequest = RestAssured.given();

            log.info("Going to hit the URL which is-->>"+baseuri+"/-/products/"+productKey);

            response = httpRequest.request(Method.GET, "/-/products/"+productKey);

            log.info("Successfully hit the api and received response ");

            log.info("Status Line is : " + response.getStatusLine());

            flag=true;

        }catch (Exception e)
        {
            log.error("Some Exception occurred while hit the request-->>"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public String getSkuId()
    {

        return response.jsonPath().getString("sku");
    }

    public boolean isResponseContainsMultipleProduct()
    {
        if(response.jsonPath().get("sku").getClass().toString().contains("String"))
        {
            log.info("SKU key is having instance of class which is-->>"+response.jsonPath().get("sku").getClass().toString());
            return true;
        }

        else
        {
            log.info("SKU key is having instance of class which is-->>"+response.jsonPath().get("sku").getClass().toString());
            return false;
        }
    }

    public int getResponseCode()
    {
        return response.getStatusCode();
    }
}
