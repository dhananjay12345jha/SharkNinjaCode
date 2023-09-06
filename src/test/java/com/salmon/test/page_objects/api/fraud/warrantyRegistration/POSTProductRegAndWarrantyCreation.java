package com.salmon.test.page_objects.api.fraud.warrantyRegistration;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.ProductRegAndWarrantyCreationResponse;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.ProductWarrantyDTO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POSTProductRegAndWarrantyCreation {
    private String baseUri = Props.getProp("warranty.base.url") + "/register";

    //--------------Response Body Data---------------//
    private String brand=Props.getProp("product.brand");
    private String  email =Props.getProp("login.email");
    private String firstName=Props.getProp("product.firstName");
    private String lastName=Props.getProp("product.lastName");
    private String customerLocale= Props.getProp("product.customerLocale");
    private String productName=Props.getProp("product.productName");
    private String postcode=Props.getProp("product.postcode");
    private boolean offers= Boolean.parseBoolean(Props.getProp("product.offers"));
    private boolean tips= Boolean.parseBoolean(Props.getProp("product.tips"));
    private boolean competitions= Boolean.parseBoolean(Props.getProp("product.competitions"));
    private String regCountry = Props.getProp("product.regCountry");
    private String recordSource = Props.getProp("product.recordSource");
    private String purchaseSourceName = Props.getProp("product.purchaseSourceName");
    private String orderNo = Props.getProp("product.orderNo");
    private String customerId;
    private String sku = Props.getProp("product.sku");
    private int qty= Integer.parseInt(Props.getProp("product.quantity"));
    private String kitsku = Props.getProp("product.kitsku");
    private String factoryCode = Props.getProp("product.factoryCode");
    private String productionCode = Props.getProp("product.productionCode");
    private String smartSerialNumber; //---it is randomly generate and has been set through step definition file
    private String regStatus = Props.getProp("product.regStatus");
    private String replaceRegId = Props.getProp("product.replaceRegId");
    //private String warrantyType = Props.getProp("product.warrantyType");

    private String warrantyType ;
    private int warrantyEffectiveDate = Integer.parseInt(Props.getProp("product.warrantyEffectiveDate"));
    //private int warrantyDuration = Integer.parseInt(Props.getProp("product.warrantyDuration"));

    private int warrantyDuration ;
    private Response response;

    private static final Logger log = LoggerFactory.getLogger(POSTProductRegAndWarrantyCreation.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri;

            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.contentType("application/json");
            httpRequest.headers("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("brand",brand);
            jsonObject.put("email", email);
            jsonObject.put("firstName", firstName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("customerLocale", customerLocale);
            jsonObject.put("productName", productName);
            jsonObject.put("postcode", postcode);
            jsonObject.put("offers", offers);
            jsonObject.put("tips", tips);
            jsonObject.put("competitions", competitions);
            jsonObject.put("regCountry", regCountry);
            jsonObject.put("recordSource", recordSource);
            jsonObject.put("purchaseSourceName", purchaseSourceName);
            jsonObject.put("orderNo", orderNo);
            jsonObject.put("customerId", customerId);
            jsonObject.put("sku", sku);
            jsonObject.put("qty",qty);
            jsonObject.put("kitsku", kitsku);
            jsonObject.put("factoryCode", factoryCode);
            jsonObject.put("productionCode", productionCode);
            jsonObject.put("smartSerialNumber", smartSerialNumber);
            jsonObject.put("regStatus", regStatus);
           // jsonObject.put("replaceRegId", replaceRegId);
            jsonObject.put("warrantyType", warrantyType);
            jsonObject.put("warrantyEffectiveDate", warrantyEffectiveDate);
            jsonObject.put("warrantyDuration", warrantyDuration);

            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the request POST Product Registration and warranty creation with url-->" + baseUri);
            response = httpRequest.request(Method.POST);
            log.info("POST Product Regi and Warranty creation api request body-->>"+jsonObject.toJSONString());
            log.info("Successfully hit the request with response code-->" + getResponseCode());

            if (getResponseCode() == 200 || getResponseCode() == 201)
            {
                response.getBody().as(ProductRegAndWarrantyCreationResponse.class);
            }

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
//------------------------Getter and Setter-----------------------//
    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
        log.info("Setting the warranty Type as-->" + this.warrantyType);
    }

    public void setWarrantyDuration(int warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
        log.info("Setting the warranty duration as-->"+this.warrantyDuration+" months");
    }

    public void setQuantityAs(int quantity) {
        this.qty = quantity;
        log.info("Setting the quantity as-->"+this.qty);
    }

    public void setCustomerId() {
        this.customerId = RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC);
        log.info("Setting the customer id which is-->" + customerId);
    }

    public void setSmartSerialNumber() {
        this.smartSerialNumber = RandomGenerator.random(10,PermittedCharacters.ALPHANUMERIC);
        log.info("Setting the smart serial in request body as-->"+this.smartSerialNumber);
    }

    public String getBrand() {
        log.info("Returning the value of brand which is-->"+brand);
        return brand;
    }

    public String getEmail() {
        log.info("Returning the value of email which is-->"+email);
        return email;
    }

    public String getFirstName() {
        log.info("Returning the value of firstName which is-->"+firstName);
        return firstName;
    }

    public String getLastName() {
        log.info("Returning the value of lastName which is-->"+lastName);
        return lastName;
    }

    public String getCustomerLocale() {
        log.info("Returning the value of customerLocale which is-->"+customerLocale);
        return customerLocale;
    }

    public String getProductName() {
        log.info("Returning the value of productName which is-->"+productName);
        return productName;
    }

    public String getPostcode() {
        log.info("Returning the value of postcode which is-->"+postcode);
        return postcode;
    }

    public boolean getOffers() {
        log.info("Returning the value of offers which is-->"+offers);
        return offers;
    }

    public boolean getTips() {
        log.info("Returning the value of tips which is-->"+tips);
        return tips;
    }

    public boolean getCompetitions() {
        log.info("Returning the value of competitions which is-->"+competitions);
        return competitions;
    }

    public String getRegCountry() {
        log.info("Returning the value of regCountry which is-->"+regCountry);
        return regCountry;
    }

    public String getRecordSource() {
        log.info("Returning the value of recordSource which is-->"+recordSource);
        return recordSource;
    }

    public String getPurchaseSourceName() {
        log.info("Returning the value of purchaseSourceName which is-->"+purchaseSourceName);
        return purchaseSourceName;
    }

    public String getOrderNo() {
        log.info("Returning the value of orderNo which is-->"+orderNo);
        return orderNo;
    }

    public String getCustomerId() {
        log.info("Returning the value of customerId which is-->"+customerId);
        return customerId;
    }

    public String getSku() {
        log.info("Returning the value of sku which is-->"+sku);
        return sku;
    }

    public int getQty() {
        log.info("Returning the value of qty which is-->"+qty);
        return qty;
    }

    public String getKitsku() {
        log.info("Returning the value of kitsku which is-->"+kitsku);
        return kitsku;
    }

    public String getFactoryCode() {
        log.info("Returning the value of factoryCode which is-->"+factoryCode);
        return factoryCode;
    }

    public String getProductionCode() {
        log.info("Returning the value of productionCode which is-->"+productionCode);
        return productionCode;
    }

    public String getSmartSerialNumber() {
        log.info("Returning the value of smartSerialNumber which is-->"+smartSerialNumber);
        return smartSerialNumber;
    }

    public String getRegStatus() {
        log.info("Returning the value of regStatus which is-->"+regStatus);
        return regStatus;
    }

    public String getReplaceRegId() {
        log.info("Returning the value of replaceRegId which is-->"+replaceRegId);
        return replaceRegId;
    }

    public String getWarrantyType() {
        log.info("Returning the value of warrantyType which is-->"+warrantyType);
        return warrantyType;
    }

    public int getWarrantyEffectiveDate() {
        log.info("Returning the value of warrantyEffectiveDate which is-->"+warrantyEffectiveDate);
        return warrantyEffectiveDate;
    }

    public int getWarrantyDuration() {
        log.info("Returning the value of warrantyDuration which is-->"+warrantyDuration);
        return warrantyDuration;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
