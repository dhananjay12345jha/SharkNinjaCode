package com.salmon.test.page_objects.api.fraud;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DELETEBlacklist
{
    private String baseUri= Props.getProp("blacklist.base.uri");
    private Response response;

    private String blId;

    private static final Logger log = LoggerFactory.getLogger(DELETEBlacklist.class);

    public boolean hitRequest()
    {
        boolean flag=false;

        try
        {
            RestAssured.baseURI=baseUri;
            RequestSpecification httpRequest=RestAssured.given();

            Map<String,Object> header=new HashMap<>();
            header.put("Accept","application/json");
            header.put("Content-Type","application/json");

            httpRequest.headers(header);

            log.info("Going to hit the request DELETE/Blacklist/blid/blId to get the data list having the uri-->>"+baseUri);
            response=httpRequest.request(Method.DELETE,"/"+blId);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request DELETE/BlackList/blId-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        log.info("Returning response code of api DELETE/Blacklist/blid which is-->"+response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        log.info("Returning response body of api DELETE/Blacklist/blid which is-->"+response.getBody().asString());
        return response.getBody().asString();
    }

    public String getMessageFromResponse()
    {
        log.info("Returning response message of api DELETE/Blacklist/blid which is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public int getBlIdFromResponse()
    {
        log.info("Returning blId from the response of api DELETE/Blacklist/blid which is-->"+response.jsonPath().getInt("data.blId"));
        return response.jsonPath().getInt("data.blId");
    }

    public String getBlacklistNameFromResponse()
    {
        log.info("Returning newly created blacklist name from the response of api DELETE/Blacklist/blid which is-->"+response.jsonPath().getString("data.name"));
        return response.jsonPath().getString("data.name");
    }
    
    public void setBlId(String blId1)
    {
        blId=blId1;
        log.info("Successfully set the blid as-->"+blId);
    }
}
