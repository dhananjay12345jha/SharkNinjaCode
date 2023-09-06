package com.salmon.test.page_objects.api.fraud.paymentController;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class PostPartPayment {
    private String baseUri = Props.getProp("get.environment");
    private Response response;
    private String orderId;
    private int amount;

    private static final Logger log = LoggerFactory.getLogger(PostCapturePayment.class);

    public boolean hitRequest() {
        boolean flag = false;
        String url = baseUri + "/partPayment";
        try {

            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.headers("Content-Type", "application/json");
            httpRequest.headers("accept", "*/*");


            httpRequest.auth().basic(Props.getProp("ipms.auth.service.username"), Props.getProp("ipms.auth.service.password"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", orderId);
            jsonObject.put("amount", amount);


            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the POST Part payment api with url-->" + url);
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the request " + getResponseCode());
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request POST Part payment which is-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        log.info("Returning Response code of POST PartPayment APi-->" + response.getStatusCode());
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        log.info("Message from the response POST PartPayment APi-->" + response.getBody().jsonPath().get("message"));
        return response.getBody().jsonPath().get("message").toString().trim();
    }

    public String getResponseBody() {
        log.info("Response body is-->" + response.getBody().asString());
        return response.getBody().asString();
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
        log.info("Setting the value of order id for POST PartPayment APi which is-->" + this.orderId);

    }

    public void setAmount(int amount) {
        this.amount = amount;
        log.info("Setting the value of amount for POST PartPayment APi which is-->" + this.amount);
    }

    public int calculateHowManyDigitsAreThereInNumber(int number) {
        int numb = number;
        int count = 0;
        if (number == 0) {
            log.info("Number is zero unable to calculate the number of digits-->" + number);
            return 0;
        } else {
            while (numb != 0) {
                numb = numb / 10;
                ++count;
            }

            log.info("Total number of digits in number are-->" + count);
            return count;
        }
    }

}
