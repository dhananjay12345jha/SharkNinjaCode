package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAllProducts
{
    private String baseuri= Props.getProp("icm.base.uri");
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(GetAllProducts.class);

    public boolean hitRequest(int amount)
    {
        boolean flag=false;

        try
        {
            RestAssured.baseURI = baseuri;

            RequestSpecification httpRequest = RestAssured.given();

            String url="?offset=0&amount="+(amount)+"&returnSortKeys=false&view=defaultcts";

            log.info("Going to hit the URL-->>"+baseuri+"/-/products"+url);

            response = httpRequest.request(Method.GET, "/-/products"+url);

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

    public int getResponseCode()
    {
        log.info("Returning Response code which is "+response.getStatusCode());
        return response.getStatusCode();
    }

    public int getTotalNumberOfItems()
    {
        log.info("Total number of items returned by api is-->>"+response.jsonPath().get("total"));
        return response.jsonPath().get("total");
    }

    public int getTotalNumbeOfProducts()
    {
        Object [] list=response.jsonPath().getList("elements.uri").toArray();
        log.info("List of elements found in JSON Path \"elements.uri\" are "+list.length);
        return list.length;
    }

}
