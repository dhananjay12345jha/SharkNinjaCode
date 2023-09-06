package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.ProductRegAndWarrantyCreationResponse;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.ProductWarrantyDTO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class GETProdRegAndWarrantyDetails {
    private String baseUri = Props.getProp("warranty.base.url");
    private String id;
    private Response response;
    private static final Logger log = LoggerFactory.getLogger(POSTProductRegAndWarrantyCreation.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {

            String url = baseUri + "/" + id;

            RestAssured.baseURI = url;

            RequestSpecification httpRequest = RestAssured.given();

            // httpRequest.headers("Accept","application/json");

            log.info("Going to hit GET api to fetch prod registration and warranty details with url-->" + url);
            response = httpRequest.request(Method.GET);
            log.info("Successfully hit the api with the response-->" + getResponseCode());

            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hit the request-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        log.info("Response code is-->" + response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public void setId(String id) {
        this.id = id;
        log.info("Setting the value of ID as-->" + this.id + " against which data will be fetched");
    }

    public ProductWarrantyDTO getResponseBodyAsClass() {
        if (getResponseCode() == 200 || getResponseCode() == 201) {
            return response.body().as(ProductWarrantyDTO.class);
        } else {
            return null;
        }
    }

    public ProductWarrantyDTO getResponsesAsClassVariables() {

        if(response.getStatusCode()==200 || response.getStatusCode()==201)
        {
          return response.getBody().as(ProductWarrantyDTO.class);
        }

        else
            {
                return null;
            }
    }

    public int differenceInWarrantyStartAndEndDate() {
        String startDateYear, endDateYear;
        int date = -1;
        ProductWarrantyDTO object = getResponseBodyAsClass();
        startDateYear = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                format(new java.util.Date(object.getWarrantyEffectiveDate() * 1000L));
        endDateYear = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                format(new java.util.Date(object.getWarrantyEndDate() * 1000L));
        SimpleDateFormat s1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date d1 = s1.parse(startDateYear);
            Date d2 = s1.parse(endDateYear);
            date = Math.toIntExact(TimeUnit.DAYS.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS) / 365);
        } catch (Exception e) {
            log.error("Some exception occurred while converting string to date " + e.getMessage());
            e.printStackTrace();
        }

        log.info("Warranty start date year from api response is-->" + startDateYear);
        log.info("Warranty end date year from api response is-->" + endDateYear);
        return date;
    }

    @Test
    public void seffk() {
        setId("000000060");
        hitRequest();
        ProductWarrantyDTO resp = getResponseBodyAsClass();
        System.out.println("abvb");
        differenceInWarrantyStartAndEndDate();
    }
}
