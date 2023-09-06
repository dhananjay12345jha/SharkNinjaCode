package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class GetAuthenticationToken {
    private static final Logger log = LoggerFactory.getLogger(BasketApi.class);
    private String username = Props.getProp("icm.backend.username");
    private String password = Props.getProp("icm.backend.password");
    private String baseUri = Props.getProp("auth.token.base.uri");
    private static String uriFromCustmDetailResp="";
    private static Response response;
    public static String token="";

    public String fetchCustomerDetailsGET(String emailId)
    {
        String text = "";
        if(uriFromCustmDetailResp.isEmpty())
        {
            log.info("Going to fetch Uri from response of GET APi Fetch Customer Details");
            try {
                RestAssured.baseURI = baseUri + "/ContactCenter/-/customers";

                Map<String, Object> queryParams = new HashMap<>();

                queryParams.put("EMail", emailId);
                queryParams.put("Channel", Props.getProp("icm.channel"));

                Map<String, Object> header = new HashMap<>();
                header.put("UserOrganization", "SharkNinja");
                header.put("Content-Type", "application/json");

                RequestSpecification httpRequest = RestAssured.given();

                httpRequest.queryParams(queryParams);

                httpRequest.auth().basic(username, password);

                httpRequest.headers(header);

                log.info("Going to hit the request GET request to fetchCustomerDetails having uri " + RestAssured.baseURI);

                response = httpRequest.request(Method.GET);

                log.info("Response code from the hit is-->"+getResponseCodeOf());

                text = response.jsonPath().getList("elements.uri").get(0).toString();

                log.info("Successfully fetched URI from response which is URI-->>" + text);

                uriFromCustmDetailResp = text;
            } catch (Exception e) {
                log.info("Returning uri value as empty ");
                log.error("Some exception occurred while hit the request fetchCustomerDetailsGET -->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        else
            {
                log.info("Fetch Customer Details Api is already hit returning Uri-->>"+uriFromCustmDetailResp);
                text=uriFromCustmDetailResp;
            }
        return text;
    }


    public int getResponseCodeOf()
    {
        return response.getStatusCode();
    }


    public String generateCustomerToken()
    {
        String text="";
        if(token.isEmpty())
        {   log.info("Going to generate token which is used in Basket Api");
            try {
                RestAssured.baseURI = baseUri + "/" + uriFromCustmDetailResp + "/tokens";


                //---header value---//
                Map<String, Object> header = new HashMap<>();
                header.put("UserOrganization", "SharkNinja");
                header.put("Content-Type", "application/json");

                RequestSpecification httpRequest = RestAssured.given();

                httpRequest.headers(header);

                httpRequest.auth().basic(username, password);

                log.info("Going to hit POST request Generate Customer Token having uri " + RestAssured.baseURI);
                response = httpRequest.request(Method.POST);
                log.info("Response code of request is " + getResponseCodeOf());

                log.info("Going to fetch token from the response ");
                text = response.jsonPath().get("token").toString();

                log.info("Token has been fetched which is-->>" + text);
                token = text;

            } catch (Exception e) {
                log.error("Some exception occurred while hit the request generate customer token-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        else
            {
                log.info("Token has already been generated returing token-->>"+token);
                log.info("If token is expired please replace the value of variable \"token\" of class \"GetAuthenticationToken\"");
                text=token;
            }
        return text;
    }




}
