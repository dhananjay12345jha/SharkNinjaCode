package com.salmon.test.page_objects.api.fraud;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBlackListBlId {
    private String baseUri= Props.getProp("blacklist.base.uri");
    private String blId="";
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(GetBlackListBlId.class);

    public boolean hitRequest()
    {
        boolean flag=false;

        try
        {
            RestAssured.baseURI=baseUri;
            RequestSpecification httpRequest=RestAssured.given();

            log.info("Going to hit the request GET/Blacklist/{blId} to get the data list having the uri-->>"+baseUri+"/"+blId);
            response=httpRequest.request(Method.GET,"/"+blId);
            log.info("Request hit successflly response code is-->"+response.getStatusCode());

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request GET/BlackList-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        log.info("Response code is-->"+response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        return response.getBody().asString();
    }

    public void setBlackListId(String id)
    {
        log.info("Setting BlId as-->"+id);
        blId=id;
    }


    public String getBlIdFromResponse()
    {
        log.info("Returning blId from the response of api GET/Blacklist/blid which is-->"+response.jsonPath().getInt("data.blId"));
        return response.jsonPath().get("data.blId").toString();
    }

    public String getBlacklistNameFromResponse()
    {
        log.info("Returning newly created blacklist name from the response of api GET/Blacklist/blid which is-->"+response.jsonPath().getString("data.name"));
        return response.jsonPath().getString("data.name");
    }

    public String getBlacklistActionFromResponse()
    {
        log.info("Returning newly created blacklist action from the response of api GET/Blacklist/blid which is-->"+response.jsonPath().getString("data.action"));
        return response.jsonPath().getString("data.action");
    }

}
