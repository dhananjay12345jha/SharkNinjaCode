package com.salmon.test.page_objects.api.fraud.instalmentController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.GetAuthenticationToken;
import com.salmon.test.page_objects.api.fraud.pojo.instalmentController.ResponsePayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileReader;
import java.util.Properties;

public class GetInstalmentPlan extends GetAuthenticationToken {

    private String baseUri= Props.getProp("get.environment");
    private Response response;
    private String orderId;
    private String emailId=Props.getProp("login.email");
    private FileReader reader;
    private Properties properties=new Properties();

    private static final Logger log = LoggerFactory.getLogger(GetInstalmentPlan.class);

    private void refreshPropertyFile()
    {
        try{
            reader=new FileReader(System.getProperty("user.dir")+ Props.getProp("file.path"));
            properties.load(reader);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean hitRequest()
    {
        boolean flag=false;
        String url=baseUri+"/getInstalmentPlan";

        try
        {
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);

            fetchCustomerDetailsGET(emailId);
            generateCustomerToken();

            httpRequest.headers("authentication-token",token);

            setOrderId();
            httpRequest.queryParams("orderId",orderId);

            log.info("Going to hit the request Get Instalment Plan Api to get the data list having the uri-->>"+url);
            httpRequest.request(Method.GET);
            Thread.sleep(2000);
            response=httpRequest.request(Method.GET);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());

            if(response.getStatusCode()==404)
            {
                log.info("Instalment Plan not found throwing 404 please check has instalment plan is not creating at backend");
                Assert.fail("Unable to create instalment plan showing 404");
            }

            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while hitting the request Get Instalment Plan-->"+e.getMessage());
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

    public ResponsePayload getResponseAsAClass()
    {
        if(getResponseCode()==200 || getResponseCode()==201)
        {
            return response.getBody().as(ResponsePayload.class);
        }

        else {
            return null;
        }
    }

    public void setOrderId() {
        refreshPropertyFile();
        orderId=properties.getProperty("order.number.MS.webFront");
    }
}

