package com.salmon.test.page_objects.api.fraud.instalmentController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.GetAuthenticationToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class PostSendUpdateCardLinkEmail extends GetAuthenticationToken
{
    private String orderId;
    private String baseUri= Props.getProp("get.environment");
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(PostSendUpdateCardLinkEmail.class);

    public boolean hitRequest()
    {
        //----PLease set orderId before hitting the request-------//

        boolean flag=false;
        String url=baseUri+"/sendUpdateCardLinkEmail/"+orderId;

        try
        {
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.headers("accept", "*/*");
            fetchCustomerDetailsGET(Props.getProp("login.email"));
            httpRequest.headers("authentication-token",generateCustomerToken());

            log.info("Going to hit the request POST SendUpdateCardLink Api to get the data list having the uri-->>"+url);
            response=httpRequest.request(Method.POST);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request POST SendUpdateCardLink-->"+e.getMessage());
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

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
