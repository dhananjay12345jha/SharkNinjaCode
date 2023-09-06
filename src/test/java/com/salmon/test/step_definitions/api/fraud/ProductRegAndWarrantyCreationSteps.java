package com.salmon.test.step_definitions.api.fraud;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.Data;
import com.salmon.test.page_objects.api.fraud.pojo.createWarranty.ProductRegAndWarrantyCreationResponse;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.ProductWarrantyDTO;
import com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration.SearchProdRegisWarrantyResponse;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.*;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.GETProdRegAndWarrantyDetails;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.GETSearchProdRegisWarrantyRecords;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.POSTProductRegAndWarrantyCreation;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.PUTWarrantyReplace;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ProductRegAndWarrantyCreationSteps {

    private POSTProductRegAndWarrantyCreation postProductRegAndWarrantyCreation;
    private GETProdRegAndWarrantyDetails getProdRegAndWarrantyDetails;
    public  static ProductRegAndWarrantyCreationResponse responseFromPostApi;
    private ProductWarrantyDTO response;
    private GETSearchProdRegisWarrantyRecords getSearchProdRegisWarrantyRecords;
    private SearchProdRegisWarrantyResponse searchProdRegisWarrantyResponse;
    private PUTWarrantyReplace putWarrantyReplace;
    private ProductRegAndWarrantyCreationResponse putWarrantyReplaceApiResponse;

    public ProductRegAndWarrantyCreationSteps()
    {
        postProductRegAndWarrantyCreation=new POSTProductRegAndWarrantyCreation();
        getProdRegAndWarrantyDetails=new GETProdRegAndWarrantyDetails();
        getSearchProdRegisWarrantyRecords=new GETSearchProdRegisWarrantyRecords();
        searchProdRegisWarrantyResponse=new SearchProdRegisWarrantyResponse();
        putWarrantyReplace=new PUTWarrantyReplace();

    }


    @Given("^Generating a unique customer Id and smart serial number$")
    public void generate_unique_customer_id()
    {
        postProductRegAndWarrantyCreation.setCustomerId();
        postProductRegAndWarrantyCreation.setSmartSerialNumber();
    }

    @Given("^creating a random order number and set the value$")
    public void generate_unique_orderNumber_while_registering_warranty()
    {
        postProductRegAndWarrantyCreation.setOrderNo("AutoTest-"+RandomGenerator.random(10, PermittedCharacters.NUMERIC));
    }
    @Given("^Product Registration And Warranty Creation api has been hit$")
    public void hit_Product_Registration_Post_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(postProductRegAndWarrantyCreation.hitRequest(), "Unable to hit the request");
        Thread.sleep(2000);
        responseFromPostApi= postProductRegAndWarrantyCreation.getResponsesAsClassVariables();
    }

    @Then("^response of product registration api should be (\\d+)$")
    public void response_of_Product_Registartion_api_should_be(int code) {
        Assert.assertEquals(postProductRegAndWarrantyCreation.getResponseCode(), code);
    }

    @And("^again Product Registration And Warranty Creation api has been hit$")
    public void again_hit_Product_Registration_Post_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(postProductRegAndWarrantyCreation.hitRequest(), "Unable to hit the request");
        Thread.sleep(2000);
        responseFromPostApi= postProductRegAndWarrantyCreation.getResponsesAsClassVariables();
    }

    @Then("^validate that in response field \"regId\" should be there and not empty$")
    public void validate_regId() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getRegId().isEmpty(),
                "regId field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getRegId());
    }

    @Then("^validate that in response field \"regCountry\" should be there and not empty$")
    public void validate_regCountry() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getRegCountry().isEmpty(),
                "regCountry field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getRegCountry());
    }

    @Then("^validate that in response field \"recordSource\" should be there and not empty$")
    public void validate_recordSource() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getRecordSource().isEmpty(),
                "recordSource field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getRecordSource());
    }

    @Then("^validate that in response field \"purchaseSourceName\" should be there and not empty$")
    public void validate_purchaseSourceName() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getPurchaseSourceName().isEmpty(),
                "purchaseSourceName field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getPurchaseSourceName());
    }

    @Then("^validate that in response field \"orderNo\" should be there and not empty$")
    public void validate_orderNo() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getOrderNo().isEmpty(),
                "orderNo field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getOrderNo());
    }

    @Then("^validate that in response field \"customerId\" should be there and not empty$")
    public void validate_customerId() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getCustomerId().isEmpty(),
                "customerId field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getCustomerId());
    }

    @Then("^validate that in response field \"sku\" should be there and not empty$")
    public void validate_sku() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getSku().isEmpty(),
                "sku field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getSku());
    }

    @Then("^validate that in response field \"kitsku\" should be there and not empty$")
    public void validate_kitsku() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getKitsku().isEmpty(),
                "kitsku field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getKitsku());
    }

    @Then("^validate that in response field \"factoryCode\" should be there and not empty$")
    public void validate_factoryCode() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getFactoryCode().isEmpty(),
                "factoryCode field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getFactoryCode());
    }

    @Then("^validate that in response field \"productionCode\" should be there and not empty$")
    public void validate_productionCode() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getProductionCode().isEmpty(),
                "productionCode field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getProductionCode());
    }

    @Then("^validate that in response field \"smartSerialNumber\" should be there and not empty$")
    public void validate_smartSerialNumber() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getSmartSerialNumber().isEmpty(),
                "smartSerialNumber field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getSmartSerialNumber());
    }

    @Then("^validate that in response field \"regStatus\" should be there and not empty$")
    public void validate_regStatus()
    {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getRegStatus().isEmpty(),
                "regStatus field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getRegStatus());
    }

    @And("^validate that in response field \"regStatus\" should be shown \"([^\"]*)\"$$")
    public void validate_getRegStatus(String regStatus)
    {
        String registrationStatus = regStatus;
        Assert.assertTrue(response.getRegStatus().contains(registrationStatus),
                "regStatus field is empty in response or not there-->" + response.getRegStatus());
    }

    @Then("^validate that in response field \"warrantyType\" should be there and not empty$")
    public void validate_warrantyType() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getRegStatus().isEmpty(),
                "regStatus field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getRegStatus());
    }

    @Then("^validate that in response field \"warrantyEffectiveDate\" should be there and not empty$")
    public void validate_warrantyEffectiveDate() {
        Assert.assertTrue(responseFromPostApi.getData().get(0).getWarrantyEffectiveDate()>0,
                "warrantyEffectiveDate field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getWarrantyEffectiveDate());
    }

    @Then("^validate that in response field \"replaceRegId\" should be there and not empty$")
    public void validate_replaceRegId() {
        Assert.assertTrue(!responseFromPostApi.getData().get(0).getReplaceRegId().isEmpty(),
                "replaceRegId field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getReplaceRegId());
    }

    @Then("^validate that in response field \"warrantyDuration\" should be there and not empty$")
    public void validate_warrantyDuration() {
        Assert.assertTrue(responseFromPostApi.getData().get(1).getWarrantyDuration()>0,
                "warrantyDuration field is either zero in response or not there-->" + responseFromPostApi.getData().get(0).getWarrantyDuration());
    }

    @Then("^validate that in response field \"warrantyEndDate\" should be there and not empty$")
    public void validate_warrantyEndDate() {
        Assert.assertTrue(responseFromPostApi.getData().get(1).getWarrantyEndDate()>0,
                "warrantyEndDate field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getWarrantyEndDate());
    }

    @Then("^validate that in response field \"createdDate\" should be there and not empty$")
    public void validate_createdDate() {
        Assert.assertTrue(responseFromPostApi.getData().get(1).getCreatedDate()>0,
                "createdDate field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getCreatedDate());
    }

    @Then("^validate that in response field \"lastModifiedDate\" should be there and not empty$")
    public void validate_lastModifiedDate() {
        Assert.assertTrue(responseFromPostApi.getData().get(1).getLastModifiedDate()>0,
                "lastModifiedDate field is empty in response or not there-->" + responseFromPostApi.getData().get(0).getLastModifiedDate());
    }


    @Given("^that set the warranty type as \"([^\"]*)\"$")
    public void set_warranty_type(String warranty_type)
    {
        postProductRegAndWarrantyCreation.setWarrantyType(warranty_type);
    }

    @Given("^that set warranty duration as (\\d+) months$")
    public void set_warranty_duration(int duration)
    {
        postProductRegAndWarrantyCreation.setWarrantyDuration(duration);
    }


    @Given("^that set quantity as (\\d+)$")
    public void set_quantity_value_as(int quantity)
    {
        postProductRegAndWarrantyCreation.setQuantityAs(quantity);
    }

// changes
    @Then("^set the regId as ID which we get in response of above post api$")
    public void set_ID_value_as_regId()
    {
        getProdRegAndWarrantyDetails.setId(responseFromPostApi.getData().get(1).getRegId());
//        responseFromPostApi.getData().get(0).getSmartSerialNumber()
    }

    @Then("^hit the GET api to fetch detail against the ID for validation$")
    public void hit_get_api_to_fetch_details() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(getProdRegAndWarrantyDetails.hitRequest(),"Unable to hit the GET having id-->"+responseFromPostApi.getData().get(0).getRegId());
        Thread.sleep(2000);
        response=getProdRegAndWarrantyDetails.getResponsesAsClassVariables();
    }

    @And("^difference between warrantyEffectiveDate and warrantyEndDate should be (\\d+)$")
    public void validate_duration_between_year(int diff)
    {
        Assert.assertEquals(getProdRegAndWarrantyDetails.differenceInWarrantyStartAndEndDate(),diff,
                "Difference between warranty start date and end date is having mismatch ");
    }

    @Then("^in response error code should be (\\d+)$")
    public void validate_error_code_from_response(int code)
    {
        Assert.assertEquals(postProductRegAndWarrantyCreation.getResponseCode(),code,
                "Response code is getting mismatched");

    }

    @And("^in response message should be shown as \"([^\"]*)\"$")
    public void validate_message_from_response(String message)
    {
        Assert.assertEquals(postProductRegAndWarrantyCreation.getMessage(),message,
                "Message from response is not equal to the mentioned one");
    }

//------------------------Search Product Registration And warranty records-------------------//

    @Then("^hit the GET/Warranty/Search api$")
    public void hitGETWarrantySearchApi() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(getSearchProdRegisWarrantyRecords.hitRequest(),"Unable to hit request please check logs");
        Thread.sleep(2000);
    }

    @Then("^total number of records should be greater than zero$")
    public void get_number_of_record_returned_in_response()
    {
        searchProdRegisWarrantyResponse=getSearchProdRegisWarrantyRecords.getResponseAsClass();
        int totalNumberOfRecords=searchProdRegisWarrantyResponse.getTotalRecords();
        Assert.assertTrue(totalNumberOfRecords>0,"Total Number of records returned are-->"+totalNumberOfRecords);
    }

    @Then("^response code of SearchProductRegistration api should be (\\d+)$")
    public void validate_response_code(int code)
    {
        Assert.assertEquals(getSearchProdRegisWarrantyRecords.getResponseCode(),code);
    }

    @Then("^error message should be there \"([^\"]*)\"$")
    public void validate_error_message(String message)
    {
        String fromResponse=getSearchProdRegisWarrantyRecords.getResponseBody();
        Assert.assertTrue(fromResponse.contains(message),
                "Message is getting mismatch, message received from response is-->"+fromResponse);
    }


    @When("^set the created date returned in response of record creation into Search warranty api$")
    public void set_created_date()
    {
       String date=String.valueOf(responseFromPostApi.getData().get(0).getCreatedDate());
        getSearchProdRegisWarrantyRecords.setCreatedDate(date);
    }

    @And ("^set the limit in Search Warranty api as (\\d+)$")
    public void set_Limit_Value(int limit){
        getSearchProdRegisWarrantyRecords.setLimit(limit);
    }

    @When("^set the customer id returned in response of record creation into Search warranty api$")
    public void set_customer_id()
    {
        String id=responseFromPostApi.getData().get(0).getCustomerId();
        getSearchProdRegisWarrantyRecords.setCustomerId(id);
    }

    @When("^set the last modified date returned in response of record creation into Search warranty api$")
    public void set_last_modified()
    {   String date=String.valueOf(responseFromPostApi.getData().get(0).getLastModifiedDate());
        getSearchProdRegisWarrantyRecords.setLastModifiedDate(date);
    }

    @When("^set only limit as (\\d+)$")
    public void set_limit(int limit)
    {
        getSearchProdRegisWarrantyRecords.setLimit(limit);
    }


    @When("^set only offset as (\\d+)$")
    public void set_offset_value(int offset)
    {
        getSearchProdRegisWarrantyRecords.setOffset(offset);
    }

    @When("^set the order number returned in response of record creation into Search warranty api$")
    public void set_order_number_as()
    {
        getSearchProdRegisWarrantyRecords.setOrderNo(responseFromPostApi.getData().get(0).getOrderNo());
    }

    @When("^set the registration id returned in response of record creation into Search warranty api$")
    public void set_registration_id()
    {
        getSearchProdRegisWarrantyRecords.setRegId(responseFromPostApi.getData().get(0).getRegId());
    }

    @When("^set only the registration status as \"([^\"]*)\"$")
    public void set_registration_status(String regStatus)
    {
        getSearchProdRegisWarrantyRecords.setRegStatus(regStatus);
    }

    @When("^set the sku number returned in response of record creation into Search warranty api$")
    public void set_sku()
    {
        getSearchProdRegisWarrantyRecords.setSku(responseFromPostApi.getData().get(0).getSku());
    }

    @When("^set the warranty effective date returned in response of record creation into Search warranty api$")
    public void set_warranty_effective_date()
    {
        getSearchProdRegisWarrantyRecords.setWarrantyEffectiveDate(String.valueOf(responseFromPostApi.getData().get(0).getWarrantyEffectiveDate()));
    }

    @Then("^total number of records should be equal to (\\d+)$")
    public void   get_number_of_records(int records)
    {
        int numberOfRecords=getSearchProdRegisWarrantyRecords.getResponseAsClass().getProductWarrantyDTO().size();
        Assert.assertEquals(numberOfRecords,records,"Records found in response is "+numberOfRecords);
    }

//    @And("^last modified date in response should be equal to (\\d+)$")
//    public void validate_last_modified_date_in_response(int lastModifiedDate)
//    {
//        boolean flag=false;
//        String k="";
//        SearchProdRegisWarrantyResponse response=getSearchProdRegisWarrantyRecords.getResponseAsClass();
//        for(int i=0;i<responseFromPostApi.getData().get(0).getProductWarrantyDTO().size();i++)
//        {
//            if(responseFromPostApi.getData().get(0).getProductWarrantyDTO().get(i).getLastModifiedDate()!=lastModifiedDate)
//            {
//                flag=true;
//                k=k+","+i;
//            }
//        }
//
//        Assert.assertFalse(flag,"From the response list one of last modified date is not matched which is at position-->"+k);
//    }

    //----------------------PUT Warranty REPLACE API STEPS-----------------------------//

    @Given("^set \"oldEffectiveDate\" value which is equal to \"warrantyEffectiveDate\" of warranty register api$")
    public void set_oldEffectiveDate_value_equals_to_warrantyEffectiveDate_of_warrantyRegisterApi()
    {
        putWarrantyReplace.setOldEffectiveDate(postProductRegAndWarrantyCreation.getWarrantyEffectiveDate());
    }

    @Given("^set \"oldOrderNumber\" value which is equal to \"orderNo\" of warranty register api$")
    public void set_oldOrderNumber_value_equals_to_orderNo_of_warrantyRegisterApi()
    {
        putWarrantyReplace.setOldOrderNo(postProductRegAndWarrantyCreation.getOrderNo());
    }

    @Given("^set \"oldSku\" value which is equal to \"sku\" of warranty register api$")
    public void  set_oldSku_value_equals_to_sku_of_warrantyRegisterApi()
    {
        putWarrantyReplace.setOldSku(postProductRegAndWarrantyCreation.getSku());
    }

    @Given("^creating a new order number and set the value of \"orderNo\"$")
    public void creating_random_orderNumber_for_putWarrantyReplaceApi()
    {
        putWarrantyReplace.setOrderNo(RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC));
    }


    @Given("^set \"warrantyEffectiveDate\" value as current date when api is executing$")
    public void set_currentDate_in_warrantEffectiveDate_of_putWarrantyReplaceApi_in_epochFormat()
    {
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        String currentTime=dateFormat.format(today);
        try{
            Date date=dateFormat.parse(currentTime);

            long epochTime=date.getTime()/1000;

            putWarrantyReplace.setWarrantyEffectiveDate((int)epochTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
            Assert.fail("Unable to set the warrantyEffectiveTime inside PUT/warranty/replace API body");
        }
        catch (Exception e1)
        {
            e1.getMessage();
            Assert.fail("Unable to set the warrantyEffectiveTime inside PUT/warranty/replace API body");
        }
    }

    @Given("^set remaining body parameters values equal to the values used while registering the product warranty api$")
    public void set_remaining_body_parameters_of_putWarrantyReplaceApi_equal_to_parameters_of_warrantyCreation()
    {
        putWarrantyReplace.setBrand(postProductRegAndWarrantyCreation.getBrand());
        putWarrantyReplace.setCompetitions(postProductRegAndWarrantyCreation.getCompetitions());
        putWarrantyReplace.setCustomerId(postProductRegAndWarrantyCreation.getCustomerId());
        putWarrantyReplace.setCustomerLocale(postProductRegAndWarrantyCreation.getCustomerLocale());
        putWarrantyReplace.setEmail(postProductRegAndWarrantyCreation.getEmail());
        putWarrantyReplace.setFactoryCode(postProductRegAndWarrantyCreation.getFactoryCode());
        putWarrantyReplace.setFirstName(postProductRegAndWarrantyCreation.getFirstName());
        putWarrantyReplace.setKitsku(postProductRegAndWarrantyCreation.getKitsku());
        putWarrantyReplace.setLastName(postProductRegAndWarrantyCreation.getLastName());
        putWarrantyReplace.setOffers(postProductRegAndWarrantyCreation.getOffers());
        putWarrantyReplace.setPostcode(postProductRegAndWarrantyCreation.getPostcode());
        putWarrantyReplace.setProductName(postProductRegAndWarrantyCreation.getProductName());
        putWarrantyReplace.setProductionCode(postProductRegAndWarrantyCreation.getProductionCode());
        putWarrantyReplace.setPurchaseSourceName(postProductRegAndWarrantyCreation.getPurchaseSourceName());
        putWarrantyReplace.setQty(postProductRegAndWarrantyCreation.getQty());
        putWarrantyReplace.setRecordSource(postProductRegAndWarrantyCreation.getRecordSource());
        putWarrantyReplace.setRegCountry(postProductRegAndWarrantyCreation.getRegCountry());
        putWarrantyReplace.setSku(postProductRegAndWarrantyCreation.getSku());
        putWarrantyReplace.setSmartSerialNumber(postProductRegAndWarrantyCreation.getSmartSerialNumber());
        putWarrantyReplace.setTips(postProductRegAndWarrantyCreation.getTips());
        putWarrantyReplace.setWarrantyDuration(postProductRegAndWarrantyCreation.getWarrantyDuration());
        putWarrantyReplace.setWarrantyType(postProductRegAndWarrantyCreation.getWarrantyType());

    }

    @Then("^hit the PUT/warranty/replace api$")
    public void hit_PUT_warranty_replace_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(putWarrantyReplace.hitRequest(),"Unable to hit request please check logs");
        Thread.sleep(2000);
    }

    @Then("^in response (\\d+) as status code and message \"([^\"]*)\" should be there$")
    public void validate_status_code_and_message_from_PUTWarrantyReplace_api_response(int code,String message)
    {
        putWarrantyReplaceApiResponse=putWarrantyReplace.getResponsesAsClassVariables();

        Assert.assertEquals(putWarrantyReplaceApiResponse.getStatus(),code);
        Assert.assertEquals(putWarrantyReplaceApiResponse.getMessage(),message);
    }

    @And("^in response data \"regStatus\" should be \"([^\"]*)\" where \"regId\" is equal to the \"regId\" received in response of warranty register api$")
    public void validate_regStatus_shouldBe_REPLACED_against_regId_for_which_request_generated(String regStatus)
    {
       String regId=responseFromPostApi.getData().get(0).getRegId();
       boolean flag=false;
       for(Data obj:putWarrantyReplaceApiResponse.getData())
       {
           if(obj.getRegId().equalsIgnoreCase(regId))
           {
               if(obj.getRegStatus().equalsIgnoreCase(regStatus))
               {
                   flag=true;
               }
           }
       }

       Assert.assertTrue(flag,"Unable to get the RegStatus as REPLACED against regID number-->"+regId);
    }

    @And("^only (\\d+) records should be there in data list one is newly created and other one is old$")
    public void validate_response_should_contain_only_two_records(int recordNumber)
    {
        Assert.assertEquals(putWarrantyReplaceApiResponse.getData().size(),recordNumber);
        String regId=responseFromPostApi.getData().get(0).getRegId();
        boolean flag=false;
        for(Data obj:putWarrantyReplaceApiResponse.getData())
        {
            if(obj.getRegId().equalsIgnoreCase(regId))
            {
                flag=true;
            }
        }

        Assert.assertTrue(flag,"Response not containing a record having old regId, or regId through which a new warranty has been registered");
    }




    @And("^in response of PUT/warranty/replace api newly created record should have regStatus as \"([^\"]*)\"$")
    public void validate_newly_created_record_have_regStatus_as_REGISTERED(String status)
    {
        String regId=responseFromPostApi.getData().get(1).getRegId();
        boolean flag=false;
        for(Data obj:putWarrantyReplaceApiResponse.getData())
        {
            if(!obj.getRegId().equalsIgnoreCase(regId))
            {
                if(obj.getRegStatus().equalsIgnoreCase(status));
                {
                    flag=true;
                }
            }
        }
        Assert.assertTrue(flag,"Unable to find regStatus as REGISTERED of newly created record of PUT/warranty/replace api");
    }


    @And("^in response of PUT/warranty/replace api old record should have value of replaceRegId equal to the regId of newly created record$")
    public void regId_of_newly_created_record_should_be_equal_to_replaceRegId_of_old_record()
    {
        String oldRegId=responseFromPostApi.getData().get(0).getRegId();
        String newRegId=putWarrantyReplaceApiResponse.getData().stream().filter(s->!s.getRegId().equalsIgnoreCase(oldRegId)).findFirst().get().getRegId();
        boolean flag=false;
        for(Data obj:putWarrantyReplaceApiResponse.getData())
        {
           if(obj.getRegId().equalsIgnoreCase(oldRegId))
           {
               if(obj.getReplaceRegId().equalsIgnoreCase(newRegId));
               {
                   flag=true;
                   break;
               }
           }
        }

        Assert.assertTrue(flag,"Unable to find a field replaceRegId having value equal to the-->>"+oldRegId);
    }

    @And("^validate that in new record warrantyDuration should be (\\d+) months$")
    public void validate_warrantyDuration_should_be_corresponding_to_warrantyType_selected(int warrantyMonths)
    {
        String oldRegId=responseFromPostApi.getData().get(1).getRegId();
        String newRegId=putWarrantyReplaceApiResponse.getData().stream().filter(s->!s.getRegId().equalsIgnoreCase(oldRegId)).findFirst().get().getRegId();

        boolean flag=false;

        for(Data obj:putWarrantyReplaceApiResponse.getData())
        {
            if(obj.getRegId().equalsIgnoreCase(newRegId))
            {
                if(obj.getWarrantyDuration()==warrantyMonths);
                {
                    flag=true;
                    break;
                }
            }
        }

        Assert.assertTrue(flag,"There is mis-match in warranty duration which obtained from response and business rule");
    }

    @And("^validate value of warratyEffective send in api body and which recieved from response new record should have (\\d+) days difference$")
    public void validate_difference_between_warrantyEffective_send_in_request_and_which_received_in_response_new_record(int duration)
    {
        String oldRegId=responseFromPostApi.getData().get(1).getRegId();
        String newRegId=putWarrantyReplaceApiResponse.getData().stream().filter(s->!s.getRegId().equalsIgnoreCase(oldRegId)).findFirst().get().getRegId();

        int warrantyEffectiveDateSendInRequestbody=putWarrantyReplace.getWarrantyEffectiveDate();
        int warrantyEffectiveDateReceivedFromResonseNewRecord=-1;

        boolean flag=false;
        for(Data obj:putWarrantyReplaceApiResponse.getData())
        {
            if(obj.getRegId().equalsIgnoreCase(newRegId))
            {
                warrantyEffectiveDateReceivedFromResonseNewRecord=obj.getWarrantyEffectiveDate();
            }
        }

        int expected=duration;
        int actual=putWarrantyReplace.getDifferenceBetweenTwoEpochTime(warrantyEffectiveDateSendInRequestbody,warrantyEffectiveDateReceivedFromResonseNewRecord);

        Assert.assertEquals(expected,actual,"Difference between two epoch times of warrantyEffectiveDate send in " +
                "request and received in new record of response is not matched, difference between two is-->"+actual);
    }
}
