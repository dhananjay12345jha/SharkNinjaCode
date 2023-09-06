package com.salmon.test.page_objects.api.fraud;

import com.google.gson.Gson;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.AddItemsInBlackList.AddressList;
import com.salmon.test.page_objects.api.fraud.pojo.AddItemsInBlackList.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;


public class POSTAddItemsInBlackList {
    private String url = Props.getProp("blacklist.base.uri");
    private Response response;
    private String valueType, valueList;
    private int blId;
    private String city="",country="", postalCode="", state="", street="", street2="";

    private static final Logger log = LoggerFactory.getLogger(POSTAddItemsInBlackList.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            String uri = url + "/" + blId + "/blacklistitems";
            RestAssured.baseURI = uri;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.headers("Accept", "application/json");
            httpRequest.contentType("application/json");
            httpRequest.contentType(ContentType.JSON);

            httpRequest.body(createBodyOfRequest());

            log.info("Going to hit the POST add items in blacklist with url-->" + uri);
            log.info("Body of the request is-->"+createBodyOfRequest());
            response = httpRequest.request(Method.POST);
            log.info("Successfully hit the request having response code-->" + getResponseCode());

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request which is-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    private String createBodyOfRequest()
    {
        List<String> vl=new ArrayList<>();
        vl.add(valueList);

        AddressList add=new AddressList();
        add.setCity(city);
        add.setCountry(country);
        add.setPostalCode(postalCode);
        add.setState(state);
        add.setStreet(street);
        add.setStreet2(street2);

        List<AddressList> addressList=new ArrayList<>();
        addressList.add(add);

        Payload payload=new Payload();
        payload.setValueType(valueType);
        payload.setValuesList(vl);
        payload.setAddressList(addressList);

        List<Payload> payloadList=new ArrayList<>();
        payloadList.add(payload);

        String body=new Gson().toJson(payloadList);

        return body;
    }

    public int getResponseCode() {
        log.info("Response code is-->" + response.getStatusCode());
        return response.getStatusCode();
    }
    public String getMessageFromResponse()
    {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public void setBlId(int blId) {
        this.blId = blId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

}
