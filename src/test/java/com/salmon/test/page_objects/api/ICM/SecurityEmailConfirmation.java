package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SecurityEmailConfirmation extends GetAuthenticationToken {
    private String baseUri = Props.getProp("icm.base.uri");
    private Response response;
    private String emailIdToPassInRequestBody = "sanket.jha@wundermanthompson.com";

    private static final Logger log = LoggerFactory.getLogger(SecurityEmailConfirmation.class);


    public boolean hitRequest() {
        boolean flag = false;

        try {
            String url = baseUri + "/snap/security/emailconfirmation";
            log.info("Going to hit the emailconfirmation api with the URL-->" + url);

            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            Map<String,Object> header=new HashMap<>();
            header.put("Accept", "application/json");
            header.put("authentication-token", token);
            header.put("Content-Type","application/json");
            httpRequest.headers(header);

            JSONObject body = new JSONObject();
            body.put("email", emailIdToPassInRequestBody);
            body.put("brand", Props.getProp("icm.brand"));
            httpRequest.body(body.toJSONString());

            log.info("Going to hit the api Security/EmailConfirmation ");
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the api, response is-->" + response.getStatusCode());
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while processing the request POST/Security/EmailConfirmation-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public void setEmailToForWhichRequestHit(String email) {
        emailIdToPassInRequestBody = email;
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }
}
