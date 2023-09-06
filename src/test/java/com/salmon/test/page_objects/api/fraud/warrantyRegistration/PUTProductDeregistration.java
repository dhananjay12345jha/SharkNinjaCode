package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.ProductRegAndWarrantyCreationResponse;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PUTProductDeregistration {
    private String baseUri = Props.getProp("warranty.base.url") + "/deregister";
    private String Id;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(PUTProductDeregistration.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri + "/" +Id;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.contentType("application/json");
            httpRequest.headers("Accept", "application/json");

            log.info("Going to hit the request POST Product Registration and warranty creation with url-->" + baseUri);
            response = httpRequest.request(Method.PUT);
            log.info("Successfully hit the request with response code-->" + getResponseCode());
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the api-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public boolean hitRequestRegIdFormatIncorrect() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri + "/" +Id;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.contentType("application/json");
            httpRequest.headers("Accept", "application/json");

            log.info("Going to hit the request POST Product Registration and warranty creation with url-->" + baseUri);
            response = httpRequest.request(Method.PUT);
            log.info("Successfully hit the request with response code-->" + getResponseCode());
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the api-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        log.info("Response code is-->" + response.getStatusCode());
        return response.getStatusCode();
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getMessageFromResponse() {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public String getResponseBody()
    {
        return response.getBody().asString();
    }
}
