package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class SecurityReminder
{

    private String baseUri = Props.getProp("icm.base.uri");
    private Response response;
    private String emailId,brand;
    private static final Logger log = LoggerFactory.getLogger(SecurityReminder.class);

    public boolean hitRequest()
    {
        boolean flag=false;

        try
        {
            String url=baseUri+"/snap/security/reminder";
            RestAssured.baseURI=url;

            RequestSpecification httpRequest= given();

            httpRequest.header("Accept","application/json");
            httpRequest.header("Content-Type","application/json");

            JSONObject body=new JSONObject();
            body.put("email",emailId);
            body.put("brand",brand);

            httpRequest.body(body.toJSONString());

            log.info("Going to hit the request security reminder with the URL-->"+url);
            response=httpRequest.request(Method.POST);
            log.info("Successfully hit the request, response code is-->"+response.getStatusCode());
            flag=true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while hit the security reminder api-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public void setEmailId(String email)
    {
        emailId=email;
    }

    public void setBrand(String bran)
    {
        brand=bran;
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

}
