package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class BasketApi extends GetAuthenticationToken {
    private String baseUri = Props.getProp("icm.base.uri");
    private static String basketKey;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(BasketApi.class);

    public boolean postCreateBasket() {
        boolean flag = false;

        try {
            String url=baseUri+"/-/baskets";
            RestAssured.baseURI = url;
            log.info("Going to hit the create Basket api with url-->>" +url);
            RequestSpecification httpRequest = RestAssured.given();

            Map<String, String> map = new HashMap<>();
            map.put("authentication-token", token);
            map.put("UserOrganization", "SharkNinja");
            map.put("Content-Type", "application/json");
//            map.put("accept","application/vnd.intershop.basket.v1+json");
//            map.put("Ocp-Apim-Subscription-Key","f7a1c4daf0b0442e81539810c8a7f40f");
            map.put("accept","*/*");
            httpRequest.headers(map);

            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the Create Basket Request");
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request-->>" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public String fetchBasketKeyFromCreateBasketResponse() {
        String text = "";

        if (getStatusCodeOfRequest() >= 200 || getStatusCodeOfRequest() <= 201) {
            log.info("Going to fetch the value of key \"title\" from the response");
            text = response.jsonPath().get("title");
            if (text != null || !text.isEmpty()) {
                log.info("Successfully fetched the value of the key Title which is-->" + text);
                basketKey = text;
            } else {
                log.info("Please check if value of Title coming in response is empty or null " + text);
            }

        } else {
            log.error("Response code instead of 200 found-->" + getStatusCodeOfRequest());
        }
        return text;
    }

    public int getStatusCodeOfRequest() {
        return response.getStatusCode();
    }

    public boolean getBasket() {
        boolean flag = false;

        if (basketKey == null) {
            log.info("Basket Key is need and it needs to be generated by hitting createBasket api");
            postCreateBasket();
            basketKey = fetchBasketKeyFromCreateBasketResponse();
            log.info("Using basket key as-->>" + basketKey);
        } else {
            log.info("Basket Key Is already generated using the same which is-->>" + basketKey);
        }

        try {
            String url=baseUri+"/-/baskets/" + basketKey;
            RestAssured.baseURI = url;
            log.info("Going to hit the GET Basket api with url-->>" +url);
            RequestSpecification httpRequest = RestAssured.given();

            Map<String, String> map = new HashMap<>();
            map.put("authentication-token", token);
            map.put("Content-Type", "application/json");
            httpRequest.headers(map);

            response = httpRequest.request(Method.GET);
            log.info("Successfully hit the GET Basket Request");
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request-->>" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public String fetchIdValueFromGetBasketResponse() {
        String text = "";

        if (getStatusCodeOfRequest() >= 200 || getStatusCodeOfRequest() <= 201) {
            log.info("Going to fetch the value of key \"Id\" from the response");
            text = response.jsonPath().get("id");
            if (!text.isEmpty()) {
                log.info("Successfully fetched the value of the key ID which is-->" + text);
            } else {
                log.info("Please check if value of ID coming in response is empty or null " + text);
            }

        } else {
            log.error("Response code instead of 200 found-->" + getStatusCodeOfRequest());
        }
        return text;
    }

    public String getBasketKey() {
        if (basketKey == null) {
            fetchBasketKeyFromCreateBasketResponse();
        }
        return basketKey;
    }

    public boolean getBasketItems() {
        boolean flag = false;
        try {

            String url = baseUri + "/-/baskets/" + basketKey + "/items";
            log.info("Going to hit get basket items api with url-->" + url);

            RestAssured.baseURI = url;
            RequestSpecification httpRequest = RestAssured.given();

            Map<String, String> map = new HashMap<>();
            map.put("authentication-token", token);
            map.put("Content-Type", "application/json");
            map.put("Accept", "application/vnd.intershop.basket.v1+json");
            httpRequest.headers(map);

            log.info("Going to hit the api request GetBasketItems");
            response = httpRequest.request(Method.GET);
            log.info("Successfully hit the api having response code-->" + response.getStatusCode());
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while hit the request GetBasketItems Api-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getDataSizeFromTheResponseOfGetBasketItem() {
        log.info("Data size from the response of get basket item api is-->" + response.jsonPath().getList("data").size());
        return response.jsonPath().getList("data").size();
    }

    public boolean addItemsInBasket(String product) {
        boolean flag = false;
        try {
            String url = baseUri + "/-/baskets/" + basketKey + "/items";
            log.info("Going to hit get basket items api with url-->" + url);

            RestAssured.baseURI = url;
            RequestSpecification httpRequest = RestAssured.given();

            Map<String, String> headersParam = new HashMap<>();
            headersParam.put("authentication-token", token);
            headersParam.put("Content-Type", "application/json");
            headersParam.put("Accept", "application/vnd.intershop.basket.v1+json");
            httpRequest.headers(headersParam);

            httpRequest.body("[{\"product\":\"" + product + "\",\"quantity\":{\"value\": 1} }]");

            log.info("Going to hit api \"Add Items In Basket\"");
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the request, Response code is " + response.getStatusCode());
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while hit \"add Items in basket\" api-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public String getQuantityValueOfAddedItems() {
        log.info("From the response of get basket details, quantity value is-->"+response.jsonPath().getList("data.quantity.value").get(0).toString());
        return response.jsonPath().getList("data.quantity.value").get(0).toString();
    }


}
