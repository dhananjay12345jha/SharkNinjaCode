package com.salmon.test.page_objects.api.fraud.fraudController;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class PutFraudHoldsOrderId
{
    private String uri;
    private Response response;
    private String orderId;
    private String action,comment,updatedBy;
    private List<String> fraudHoldIdList;


    private static final Logger log = LoggerFactory.getLogger(PutFraudHoldsOrderId.class);

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

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("action",action);
            jsonObject.put("comment",comment);
            jsonObject.put("updatedBy",updatedBy);
            jsonObject.put("fraudHoldIdList",fraudHoldIdList);


            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the PUT FraudHoldsOrderId api with url-->" + url);
            response = httpRequest.request(Method.PUT);
            log.info("Successfully hit the request "+getResponseCode());
            flag=true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the PUT FraudHoldsOrderId request which is-->" + e.getMessage());
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
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

    public String getErrorMessage()
    {
        return response.getBody().jsonPath().getString("data.message");
        //return response.getBody().jsonPath().getList("error").get(0).toString();
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setFraudHoldIdList(List<String> fraudHoldIdList) {
        this.fraudHoldIdList = fraudHoldIdList;
    }
}
