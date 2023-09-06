package com.salmon.test.page_objects.api.fraud.fraudController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.fraudController.ResponseFromGetFraudHoldsOrderId;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetFraudHoldsOrderId {
    private String uri;
    private Response response;
    private String orderId;


    private static final Logger log = LoggerFactory.getLogger(GetFraudHoldsOrderId.class);

    public boolean hitRequest() {
        boolean flag = false;
        if(Props.getExecutionEnv().equalsIgnoreCase("uat")){
            uri=Props.getProp("fraud.controller.base.uri.uat");
        }
        else if(Props.getExecutionEnv().equalsIgnoreCase("int")){
            uri=Props.getProp("fraud.controller.base.uri.int");
        }
        String url = uri+"fraudholds"+"/"+orderId;
//        url=url.replace("inst","fraud");
        try {

            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.headers("Accept", "application/json");
            httpRequest.headers("Content-Type","application/json");

            log.info("Going to hit the GET FraudHoldsOrderId api with url-->" + url);
            response = httpRequest.request(Method.GET);
            log.info("Successfully hit the request "+getResponseCode());
            flag=true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the GET FraudHoldsOrderId request which is-->" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    public int getResponseCode() {
        log.info("Returning Response code-->" + response.getStatusCode());
        return response.getStatusCode();
    }
    public String getMessageFromResponse()
    {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        log.info("Setting the order id which is-->"+this.orderId);
    }

    public ResponseFromGetFraudHoldsOrderId getResponseAsClass()
    {
        if(getResponseCode()==200 || getResponseCode()==201)
        {
            return response.getBody().as(ResponseFromGetFraudHoldsOrderId.class);
        }

        else
            {
                log.error("response code is-->"+getResponseCode()+" returning response class object as NULL");
                return null;
            }
    }
}
