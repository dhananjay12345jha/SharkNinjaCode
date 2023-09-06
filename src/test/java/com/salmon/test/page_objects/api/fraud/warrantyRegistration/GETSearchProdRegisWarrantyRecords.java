package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.SearchProdRegisWarrantyResponse;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class GETSearchProdRegisWarrantyRecords {

    private String baseUri= Props.getProp("warranty.base.url");
    private String  createdDate;
    private String customerId;
    private String lastModifiedDate;
    private int limit;
    private int offset;
    private String orderNo;
    private String regId;
    private String regStatus;
    private String sku;
    private String warrantyEffectiveDate;
    private Response response;
    private static final Logger log = LoggerFactory.getLogger(GETSearchProdRegisWarrantyRecords.class);

    public boolean hitRequest()
    {
        boolean flag=false;
        try {

            String url=baseUri+"/search";

            RestAssured.baseURI=url;

            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.headers("Accept","application/json");

            if(createdDate!=null && !createdDate.trim().isEmpty())
            {
                httpRequest.queryParams("createdDate",createdDate);
            }

            if(customerId!=null && !customerId.trim().isEmpty())
            {
                httpRequest.queryParams("customerId",customerId);
            }
            if(lastModifiedDate!=null && !lastModifiedDate.trim().isEmpty())
            {
                httpRequest.queryParams("lastModifiedDate",lastModifiedDate);
            }
            if(orderNo!=null && !orderNo.trim().isEmpty())
            {
                httpRequest.queryParams("orderNo",orderNo);
            }
            if(regId!=null && !regId.trim().isEmpty())
            {
                httpRequest.queryParams("regId",regId);
            }
            if(regStatus!=null && !regStatus.trim().isEmpty())
            {
                httpRequest.queryParams("regStatus",regStatus);
            }
            if(sku!=null && !sku.trim().isEmpty())
            {
                httpRequest.queryParams("sku",sku);
            }
            if(warrantyEffectiveDate!=null && !warrantyEffectiveDate.trim().isEmpty())
            {
                httpRequest.queryParams("warrantyEffectiveDate",warrantyEffectiveDate);
            }

            if(limit>0)
            {
                httpRequest.queryParams("limit",limit);
            }
            if(offset>0)
            {
                httpRequest.queryParams("offset",offset);
            }

            log.info("Going to hit GET api to fetch prod registration and warranty details with url-->"+url);
            response=httpRequest.request(Method.GET);
            log.info("Successfully hit the api with the response-->"+getResponseCode());

            flag=true;

        }catch (Exception e)
        {
            log.error("Some exception occurred while hit the request-->"+e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode()
    {
        log.info("Response code is-->"+response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()

    {
        return response.getBody().asString();
    }

    public SearchProdRegisWarrantyResponse getResponseAsClass()
    {
        if(getResponseCode()==200 || getResponseCode()==201)
        {
            return response.getBody().as(SearchProdRegisWarrantyResponse.class);
        }
        return null;
    }


    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        log.info("Setting created Date as "+this.createdDate);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        log.info("Setting customer id "+this.customerId);
    }

    public void setLastModifiedDate(String lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
        log.info("Setting last modified date as "+this.lastModifiedDate);
    }

    public void setLimit(int limit) {
        this.limit = limit;
        log.info("Setting limit as "+this.limit);
    }

    public void setOffset(int offset) {
        this.offset = offset;
        log.info("Setting offset as "+this.offset);
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        log.info("Setting order no as "+orderNo);
    }

    public void setRegId(String regId) {
        this.regId = regId;
        log.info("Setting up the reg id as "+this.regId);
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
        log.info("Setting reg status as "+this.regStatus);
    }

    public void setSku(String sku) {
        this.sku = sku;
        log.info("Setting sku as "+this.sku);
    }

    public void setWarrantyEffectiveDate(String warrantyEffectiveDate) {
        this.warrantyEffectiveDate = warrantyEffectiveDate;
        log.info("Setting warranty effective date as "+this.warrantyEffectiveDate);
    }
}
