package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.instalmentController.ResponsePayload;
import gherkin.deps.com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class POSTProductRefurbish {
    private String baseUri = Props.getProp("warranty.base.url") + "/refurbish";
    private List<String> smartSerialNumbers;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(POSTProductRefurbish.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.contentType("application/json");
            httpRequest.headers("Accept", "application/json");

//            JSONObject jsonObject = new JSONObject(smartSerialNumbers);
            String body=new Gson().toJson(smartSerialNumbers);
            httpRequest.body(body);

//            jsonObject.put("Smart Serial Numbers", smartSerialNumbers);
//
//            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the request POST Product refurbish with URL-->" + baseUri);
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the request with response code-->" + getResponseCode());
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the api-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        log.info("Response code is-->" + response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        return response.getBody().asString();
    }

    public String getMessageFromResponse() {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public String getResponseBodyA()
    {
        return response.getBody().asString();
    }

    public void setSmartSerialNumber(List<String> smartSerialNumbers) {
        this.smartSerialNumbers = smartSerialNumbers;
    }

    public String getUpdatedSSN() {
        return response.getBody().jsonPath().getList("data.successfullyUpdated").get(0).toString();
    }

    public String getNotFoundSSN() {
        return response.getBody().jsonPath().getList("data.notFound").get(0).toString();
    }

    public String getAlreadyRefurbishedSSN() {
        return response.getBody().jsonPath().getList("data.alreadyRefurbished").get(0).toString();
    }

}
