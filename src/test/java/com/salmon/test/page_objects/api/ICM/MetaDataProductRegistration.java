package com.salmon.test.page_objects.api.ICM;


import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaDataProductRegistration
{
    private static final Logger log = LoggerFactory.getLogger(MetaDataProductRegistration.class);

    private String baseUri= Props.getProp("icm.base.uri");
    private Response response;


    public boolean hitRequest()
    {
        boolean flag=false;
        try
        {
            String url=baseUri+"/-/metadata/productregistration";

            RestAssured.baseURI=url;

            RequestSpecification httpRequest= RestAssured.given();

            httpRequest.header("Content-Type","application/json");

            log.info("Going to hit the meta data production api with the url-->"+url);
            response=httpRequest.request(Method.GET);
            log.info("Successfully hit the api, response code is-->"+response.getStatusCode());

            flag=true;
        }catch (Exception e)
        {
            log.error("Some Exception occurred while hitting the metaData product registration api-->"+e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public int getSizeOfSharkProductsType()
    {
        log.info("Size of the Shark Product Types list is-->"+response.jsonPath().getList("SharkProductTypes").size());
        return response.jsonPath().getList("SharkProductTypes").size();
    }


    public int getSizeNinjaProductTypes()
    {
        log.info("Size of the Ninja Product Types list is-->"+response.jsonPath().getList("NinjaProductTypes").size());
        return response.jsonPath().getList("NinjaProductTypes").size();
    }

    public int getSizeSharkSellingLocations()
    {
        log.info("Size of the Shark Selling Location list is-->"+response.jsonPath().getList("SharkSellingLocations").size());
        return response.jsonPath().getList("SharkSellingLocations").size();
    }

    public int getSizeNinjaSellingLocations()
    {
        log.info("Size of the Ninja Selling Location list is-->"+response.jsonPath().getList("NinjaSellingLocations").size());
        return response.jsonPath().getList("NinjaSellingLocations").size();
    }

    public int getSizeSellingLocations()
    {
        log.info("Size of the Selling Location list is-->"+response.jsonPath().getList("SellingLocations").size());
        return response.jsonPath().getList("SellingLocations").size();
    }

    public int getReposeCode()
    {
        log.info("Returning Response code is-->"+response.getStatusCode());
        return response.getStatusCode();
    }
}
