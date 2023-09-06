package com.salmon.test.page_objects.api.fraud.orderController;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.util.Properties;

public class PostCancelOrRefundPlan
{
    private String baseUri= Props.getProp("get.environment");
    private int nonRefundAmount; //---> 0
    private String orderId; //-----> OrderId to be picked from API_config file
    private int refundAmount; //---> this should be equal to or less than "totalOrderAmount" from get instalment
    private String refundId; //---> any numeric value
    private boolean saveSaleRefund; //--->> in case of refund it will be false
    private String status; //----> CANCEL_RETURNED in case of full refund, incase of partial this param won't be included in JSON body
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(PostCancelOrRefundPlan.class);

    public boolean hitRequest()
    {
        boolean flag=false;
        String url=baseUri+"/cancelOrRefundPlan";

        try
        {
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);


            JSONObject jsonObject=new JSONObject();
            jsonObject.put("nonRefundAmount",nonRefundAmount);
            jsonObject.put("orderId",orderId);
            jsonObject.put("refundAmount",refundAmount);
            jsonObject.put("refundId",refundId);
            jsonObject.put("saveSaleRefund",saveSaleRefund);
            jsonObject.put("status",status);

            httpRequest.body(jsonObject.toJSONString());

            httpRequest.auth().basic("user01","43e027a6f455ac6f0192914a2bac831d");

            log.info("Going to hit the request POST cancelOrRefundPlan Api to POST the data list having the uri-->>"+url);
            response=httpRequest.request(Method.POST);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request POST cancelOrRefundPlan Api-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        return response.getStatusCode();
    }

    public String getMessageFromResponse()
    {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public void setNonRefundAmount(int nonRefundAmount) {
        this.nonRefundAmount = nonRefundAmount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public void setSaveSaleRefund(boolean saveSaleRefund) {
        this.saveSaleRefund = saveSaleRefund;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
