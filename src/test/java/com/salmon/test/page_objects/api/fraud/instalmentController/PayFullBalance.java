package com.salmon.test.page_objects.api.fraud.instalmentController;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.GetAuthenticationToken;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.util.Properties;

public class PayFullBalance extends GetAuthenticationToken{

    private final Properties properties=new Properties();
    private String orderId;
    private Response response;
    private final String baseUri = Props.getProp("get.environment");
    private final String emailId=Props.getProp("login.email");

    private static final Logger log = LoggerFactory.getLogger(GetInstalmentPlanDetails.class);

    public boolean hitRequest()
    {
        boolean flag=false;
        setOrderId();
        String url=baseUri+"/payFullBalance/"+orderId;
        try {
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();
            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);

            fetchCustomerDetailsGET(emailId);
            generateCustomerToken();

            httpRequest.headers("authentication-token",token);

            log.info("Going to hit the request Post Pay full balance-->>"+url);
          //  Response response = httpRequest.request(Method.POST); ---response was not intialize
            response=httpRequest.request(Method.POST); // done by sanket
            log.info("Request hit successfully response code is-->"+ response.getStatusCode());
            flag=true;

        }catch (Exception e)
        {
            log.error("Unable to hit the GET/instalmentPlansDetails api-->"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public void setOrderId() {
        refreshPropertyFile();
        orderId=properties.getProperty("order.number.MS.webFront");
    }

    private void refreshPropertyFile()
    {
        try{
            FileReader reader = new FileReader(System.getProperty("user.dir") + Props.getProp("file.path"));
            properties.load(reader);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        log.info("Message from the response is-->"+response.jsonPath().getString("message").trim());
        return response.jsonPath().getString("message").trim();
    }
}
