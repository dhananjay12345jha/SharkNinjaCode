package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerApi {
    private String baseUri= Props.getProp("icm.base.uri");
    private String emailId;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(CustomerApi.class);


    public boolean createdIcmAccountWithoutPassword()
    {
        boolean flag=false;

        try
        {
            String url=baseUri+"/-/customers";
            RestAssured.baseURI=url;

            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.header("Accept","application/json");
            httpRequest.header("Content-Type", "application/json");

            JSONObject body=new JSONObject();
            body.put("email",emailId);

            httpRequest.body(body.toJSONString());

            log.info("Going to hit POST request \"create ICM account without password\" with url-->"+url);
            log.info("Goint to hit POST request with email Id-->"+emailId);
            response=httpRequest.request(Method.POST);
            log.info("Successfully hit the request response code is-->"+response.getStatusCode());
            flag=true;

        }catch (Exception e)
        {
            log.error("Some exception occurred or Request consumes Email ID which might not get generated please use method createEmailId-->"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String createEMailId()
    {
        log.info("Going to generate the Email Id");
        emailId=RandomGenerator.randomEmailAddress(7);
        log.info("Email id has been generated-->"+emailId);
        return emailId;
    }

    public String getUriValueFromTResponse()
    {
        String text="";
        try{
            log.info("Going to fetch URI value from the response");
            text=response.jsonPath().get("uri").toString();
            log.info("Successfully fetched the URI value which is-->"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching the URI value from the response-->"+e.getMessage());
            e.printStackTrace();
        }
        return text;

    }

    public String getResponseAsString()
    {
        String text="";

        try
        {
            log.info("Going to fetch the response ");
            text= response.getBody().asString();
            log.info("Successfully fetched the response which is-->"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching the response-->"+e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public int getResponseCode()
    {
        return response.getStatusCode();
    }


    public int getCustomerNumberFromResponse()
    {
        int text=0;
        try{
            log.info("Going to fetch URI value from the response");
            text=Integer.parseInt(response.jsonPath().get("customerNo").toString().trim());
            log.info("Successfully fetched the URI value which is-->"+text);
        }catch (Exception e)
        {
            log.error("Some exception occurred while fetching the URI value from the response-->"+e.getMessage());
            e.printStackTrace();
        }
        return text;

    }


}
