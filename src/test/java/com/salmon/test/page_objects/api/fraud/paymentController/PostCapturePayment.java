package com.salmon.test.page_objects.api.fraud.paymentController;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostCapturePayment
{
    private String baseUri = Props.getProp("get.environment");
    private Response response;
    private String transactionId,orderId;
    private int requestTransactionId;

    private static final Logger log = LoggerFactory.getLogger(PostCapturePayment.class);

    public boolean hitRequest() {
        boolean flag = false;
        String url = baseUri+"/capturePayment";
        try {

            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.headers("Content-Type", "application/json");
            httpRequest.headers("accept","*/*");


            httpRequest.auth().basic(Props.getProp("ipms.auth.service.username"),Props.getProp("ipms.auth.service.password"));

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("orderId",orderId);
            jsonObject.put("requestTransactionId",requestTransactionId);
            jsonObject.put("transactionId",transactionId);


            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the POST capture payment api with url-->" + url);
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the request "+getResponseCode());
            flag=true;

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request which is-->" + e.getMessage());
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
        log.info("Message from the response is-->"+response.getBody().asString());
        return response.getBody().asString();
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        log.info("Setting the value of transaction id which is-->"+this.transactionId);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        log.info("Setting the value of order id which is-->"+this.orderId);

    }

    public void setRequestTransactionId(int requestTransactionId) {
        this.requestTransactionId = requestTransactionId;
        log.info("Setting the value of requestTransaction id which is-->"+this.requestTransactionId);

    }
}
