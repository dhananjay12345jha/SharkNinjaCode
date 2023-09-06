package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecurityResetPassword {

    private String baseUri = Props.getProp("icm.reset.password.uri");
    private Response response;
    private String emailId = "";

    private static final Logger log = LoggerFactory.getLogger(SecurityResetPassword.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            String url = baseUri + "/-/security/resetpassword";
            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("UserOrganization", "SharkNinja");

            JSONObject body = new JSONObject();
            body.put("login", emailId);

            httpRequest.body(body.toJSONString());

            log.info("Going to hit request with url-->" + url);
            response = httpRequest.request(Method.POST);
            log.info("Request has been hit with email Id-->" + emailId + "and response code is-->" + response.getStatusCode());
            flag = true;
        } catch (Exception e) {
            log.error("Some exception has been occurred while hitting the request /security/password which is-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }


    public int getResponseCode() {
        return response.getStatusCode();
    }

    public void setEmailId(String email) {
        if (!emailId.equalsIgnoreCase(email)) {
            emailId = email;
            log.info("Successfully set the email id " + emailId);
        } else {
            log.info("This email id has already been set ");
        }
    }

    public String getResponseMessage() {
        String text = "";

        text = response.getBody().asString();

        return text;
    }
}
