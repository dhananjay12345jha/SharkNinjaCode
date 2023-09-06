package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCPromotionCodes {

    private String username = Props.getProp("icm.backend.username");
    private String baseUri = Props.getProp("icm.reset.password.uri");
    private String password = Props.getProp("icm.backend.password");
    private Response response;
    private static final Logger log = LoggerFactory.getLogger(CCPromotionCodes.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {

            String url = baseUri + "/-/channels/SharkNinja-GB/promotions";
            RestAssured.baseURI = url;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.auth().basic(username, password);
            httpRequest.headers("UserOrganization", "SharkNinja");

            log.info("Going to hit the CC Get Promotion Codes request with the url-->"+url);
            response=httpRequest.request(Method.GET);
            log.info("Request has been hit successfully, response code is-->"+response.getStatusCode());

            flag=true;

        } catch (Exception e) {
            log.error("Some exception occurred while hit the CC GET Promotion Codes-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        return response.getBody().asString();
    }

    public int getNumberOfElementsInResponse()
    {
        int size=response.jsonPath().getList("elements.").size();
        log.info("Number of promotion codes shown under element list are-->>"+size);
        return size;
    }

}
