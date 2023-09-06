package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.ProductRegAndWarrantyCreationResponse;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.ProductWarrantyDTO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.xpath.operations.Bool;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PUTWarrantyReplace
{
    private String baseUri = Props.getProp("warranty.base.url") + "/replace";
    private static final Logger log = LoggerFactory.getLogger(PUTWarrantyReplace.class);
    private Response response;

    private String brand=Props.getProp("product.brand");
    private boolean competitions = Boolean.parseBoolean(Props.getProp("product.competitions"));
    private String customerId; ///---Randomly Generating Alphanumeric of length 10
    private String customerLocale=Props.getProp("product.customerLocale");
    private String email=Props.getProp("login.email");
    private String factoryCode=Props.getProp("product.factoryCode");
    private String firstName=Props.getProp("product.firstName");
    private String kitsku=Props.getProp("product.kitsku");
    private String lastName=Props.getProp("product.lastName");
    private boolean offers =Boolean.parseBoolean(Props.getProp("product.offers"));
    private int oldEffectiveDate; //-- same through which warranty has been registered
    private String oldOrderNo;   //-- same through which warranty has been registered
    private String oldSku;   //-- same through which warranty has been registered
    private String oldSystemId ="string";  //--- value not defined in swagger
    private String orderNo;   //-- same through which warranty has been registered
    private String postcode=Props.getProp("product.postcode");
    private String productName=Props.getProp("product.productName");
    private String productionCode=Props.getProp("");
    private String purchaseSourceName=Props.getProp("product.productionCode");
    private int qty=Integer.parseInt(Props.getProp("product.quantity"));
    private String recordSource=Props.getProp("product.recordSource");
    private String regCountry=Props.getProp("product.regCountry");
    private String sku=Props.getProp("product.sku");
    private String smartSerialNumber;      //---it is randomly generate and has been set through step definition file
    private boolean tips =Boolean.parseBoolean(Props.getProp("product.tips"));
    private int warrantyDuration=Integer.parseInt(Props.getProp("product.warrantyDuration"));
    private int warrantyEffectiveDate;   //-- current date in which agent has requested to replace the order
    private String warrantyType=Props.getProp("product.warrantyType");

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.contentType("application/json");
            httpRequest.headers("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("brand",brand);
            jsonObject.put("competitions",competitions);
            jsonObject.put("customerId",customerId);
            jsonObject.put("customerLocale",customerLocale);
            jsonObject.put("email",email);
            jsonObject.put("factoryCode",factoryCode);
            jsonObject.put("firstName",firstName);
            jsonObject.put("kitsku",kitsku);
            jsonObject.put("lastName",lastName);
            jsonObject.put("offers",offers);
            jsonObject.put("oldEffectiveDate",oldEffectiveDate); //--No
            jsonObject.put("oldOrderNo",oldOrderNo); //---No
            jsonObject.put("oldSku",oldSku); //---no
            jsonObject.put("oldSystemId",oldSystemId);
            jsonObject.put("orderNo",orderNo); //--No
            jsonObject.put("postcode",postcode);
            jsonObject.put("productName",productName);
            jsonObject.put("productionCode",productionCode);
            jsonObject.put("purchaseSourceName",purchaseSourceName);
            jsonObject.put("qty",qty);
            jsonObject.put("recordSource",recordSource);
            jsonObject.put("regCountry",regCountry);
            jsonObject.put("sku",sku);
            jsonObject.put("smartSerialNumber",smartSerialNumber);
            jsonObject.put("tips",tips);
            jsonObject.put("warrantyDuration",warrantyDuration);
            jsonObject.put("warrantyEffectiveDate",warrantyEffectiveDate); //---No
            jsonObject.put("warrantyType",warrantyType);


            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the request PUT/warranty/replace API with url-->" + baseUri);
            response = httpRequest.request(Method.PUT);
            log.info("Successfully hit the request with response code-->" + getResponseCode());

            if (getResponseCode() == 200 || getResponseCode() == 201)
            {
                response.getBody().as(ProductRegAndWarrantyCreationResponse.class);
            }

            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the PUT/warranty/replace API-->" + e.getMessage());
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

    public String getMessage() {
        log.info("Message from the response is-->" + response.jsonPath().get("message").toString());
        return response.jsonPath().get("message").toString();
    }

    public ProductRegAndWarrantyCreationResponse getResponsesAsClassVariables()
    {
        if(response.getStatusCode()==200 || response.getStatusCode()==201)
        {
            return response.getBody().as(ProductRegAndWarrantyCreationResponse.class);
        }

        else
        {
            log.error("Response code is-->"+response.getStatusCode()+" returning response class object as null");
            return null;
        }

    }

    //-------------SETTER FOR VARIABLES---------------//


    public void setBrand(String brand) {
        this.brand = brand;
        log.info("Setting the value of brand which is-->"+this.brand);
    }

    public void setCompetitions(boolean competitions) {
        this.competitions = competitions;
        log.info("Setting the value of competitions which is-->"+this.competitions);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        log.info("Setting the value of customerId which is-->"+this.customerId);
    }

    public void setCustomerLocale(String customerLocale) {
        this.customerLocale = customerLocale;
        log.info("Setting the value of customerLocale which is-->"+this.customerLocale);
    }

    public void setEmail(String email) {
        this.email = email;
        log.info("Setting the value of email which is-->"+this.email);
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
        log.info("Setting the value of factoryCode which is-->"+this.factoryCode);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        log.info("Setting the value of firstName which is-->"+this.firstName);
    }

    public void setKitsku(String kitsku) {
        this.kitsku = kitsku;
        log.info("Setting the value of kitsku which is-->"+this.kitsku);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        log.info("Setting the value of lastName which is-->"+this.lastName);
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
        log.info("Setting the value of offers which is-->"+this.offers);
    }

    public void setOldEffectiveDate(int oldEffectiveDate) {
        this.oldEffectiveDate = oldEffectiveDate;
        log.info("Setting the value of oldEffectiveDate which is-->"+this.oldEffectiveDate);
    }

    public void setOldOrderNo(String oldOrderNo) {
        this.oldOrderNo = oldOrderNo;
        log.info("Setting the value of oldOrderNo which is-->"+this.oldOrderNo);
    }

    public void setOldSku(String oldSku) {
        this.oldSku = oldSku;
        log.info("Setting the value of oldSku which is-->"+this.oldSku);
    }

    public void setOldSystemId(String oldSystemId) {
        this.oldSystemId = oldSystemId;
        log.info("Setting the value of oldSystemId which is-->"+this.oldSystemId);
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        log.info("Setting the value of orderNo which is-->"+this.orderNo);
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        log.info("Setting the value of postcode which is-->"+this.postcode);
    }

    public void setProductName(String productName) {
        this.productName = productName;
        log.info("Setting the value of productName which is-->"+this.productName);
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
        log.info("Setting the value of productionCode which is-->"+this.productionCode);
    }

    public void setPurchaseSourceName(String purchaseSourceName) {
        this.purchaseSourceName = purchaseSourceName;
        log.info("Setting the value of purchaseSourceName which is-->"+this.purchaseSourceName);
    }

    public void setQty(int qty) {
        this.qty = qty;
        log.info("Setting the value of qty which is-->"+this.qty);
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
        log.info("Setting the value of recordSource which is-->"+this.recordSource);
    }

    public void setRegCountry(String regCountry) {
        this.regCountry = regCountry;
        log.info("Setting the value of regCountry which is-->"+this.regCountry);
    }

    public void setSku(String sku) {
        this.sku = sku;
        log.info("Setting the value of sku which is-->"+this.sku);
    }

    public void setSmartSerialNumber(String smartSerialNumber) {
        this.smartSerialNumber = smartSerialNumber;
        log.info("Setting the value of smartSerialNumber which is-->"+this.smartSerialNumber);
    }

    public void setTips(boolean tips) {
        this.tips = tips;
        log.info("Setting the value of tips which is-->"+this.tips);
    }

    public void setWarrantyDuration(int warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
        log.info("Setting the value of warrantyDuration which is-->"+this.warrantyDuration);
    }

    public void setWarrantyEffectiveDate(int warrantyEffectiveDate) {
        this.warrantyEffectiveDate = warrantyEffectiveDate;
        log.info("Setting the value of warrantyEffectiveDate which is-->"+this.warrantyEffectiveDate);
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
        log.info("Setting the value of warrantyType which is-->"+this.warrantyType);
    }

    public int getWarrantyEffectiveDate() {
        return warrantyEffectiveDate;
    }

    public int getDifferenceBetweenTwoEpochTime(int t1,int t2)
    {
        String startDateYear, endDateYear;
        int date = -1;
        startDateYear = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                format(new java.util.Date(t1 * 1000L));
        endDateYear = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                format(new java.util.Date(t2 * 1000L));
        SimpleDateFormat s1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date d1 = s1.parse(startDateYear);
            Date d2 = s1.parse(endDateYear);
            date = Math.toIntExact(TimeUnit.DAYS.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            log.error("Some exception occurred while converting string to date " + e.getMessage());
            e.printStackTrace();
        }

        log.info("First epoch time is -->" + startDateYear);
        log.info("Second epoch time is -->" + endDateYear);
        log.info("Difference between above two epoch time is -->"+date);
        return date;
    }
}
