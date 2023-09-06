package com.salmon.test.page_objects.api.fraud;


import com.google.gson.Gson;
import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class DELETEBlacklistItems
{
    private String baseUri= Props.getProp("blacklist.base.uri");
    private String blId=Props.getProp("blId.captured.from.newly.created.request");
    private String ids;
    private String valueType;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(DELETEBlacklistItems.class);

    public boolean hitRequest()
    {
        boolean flag=false;

        try
        {
            String url=baseUri+"/"+blId+"/blacklistitems";
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();


            httpRequest.header("Accept","application/json");
            httpRequest.header("Content-Type","application/json");

            String object="{\"ids\":["+ids+"],\"valueType\": \""+valueType+"\"}";

            Gson gson=new Gson();
            String body=gson.toJson(object);

            log.info("Body of the response is-->"+object);

            httpRequest.body(object);

            log.info("Going to hit the request DELETE Blacklist Items to get the data list having the uri-->>"+url);
            response=httpRequest.request(Method.DELETE);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());

            flag=true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request DELETE BlackList Items-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        log.info("Returning response code of api DELETE Blacklist Items which is-->"+response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        log.info("Returning response body of api DELETE Blacklist Items which is-->"+response.getBody().asString());
        return response.getBody().asString();
    }

    public String getMessageFromResponse()
    {
        log.info("Returning response message of api DELETE/Blacklist/blid which is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public void setBlId(String blId) {
        this.blId = blId;
    }

}
