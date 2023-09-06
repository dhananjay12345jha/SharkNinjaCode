package com.salmon.test.step_definitions.api.fraud;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.DELETEBlacklistItems;
import com.salmon.test.page_objects.api.fraud.GETBlackListItems;
import com.salmon.test.page_objects.api.fraud.POSTAddItemsInBlackList;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.hu.De;
import org.testng.Assert;

import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

public class AddDeletePostBlackListItemsSteps {

    private GETBlackListItems getBlackListItems;
    private POSTAddItemsInBlackList postAddItemsInBlackList;
    private DELETEBlacklistItems deleteBlacklistItems;
    private FileReader reader;
    private Properties properties=new Properties();

    public AddDeletePostBlackListItemsSteps() {
        getBlackListItems = new GETBlackListItems();
        postAddItemsInBlackList=new POSTAddItemsInBlackList();
        deleteBlacklistItems=new DELETEBlacklistItems();
    }

    private void refreshPropertyFile()
    {
        try{
            reader=new FileReader(System.getProperty("user.dir")+Props.getProp("file.path"));
            properties.load(reader);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //------------GET Black List Item Api-----------------//

    @Given("^set the value of limit as (\\d+) and page numbers as (\\d+)$")
    public void set_the_value_of_limit_as_and_page_numbers_as(int limit, int pageNumber) {
        getBlackListItems.setLimit(limit);
        getBlackListItems.setPageNumber(pageNumber);
    }

    @And("^setting the value type as \"([^\"]*)\"$")
    public void setting_the_value_type_as(String valueType) {
        getBlackListItems.setValueType(valueType);
    }

    @Then("^hit the GET Blacklist items api$")
    public void hit_the_GET_Blacklist_items_api() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(getBlackListItems.hitRequest(), "Unable to hit the request please check logs");
        Thread.sleep(2000);
    }

    @Then("^response code of GET Blacklist api should be (\\d+)$")
    public void response_code_of_GET_Blacklist_api_should_be(int rCode) {
        Assert.assertEquals(getBlackListItems.getResponseCode(), rCode, "Response code mismatch please check logs");
    }

    @And("\"blackListItemType\" returned in response should be \"([^\"]*)\"$")
    public void returned_in_response_should_be(String message) {
        Assert.assertEquals(getBlackListItems.getBlackListItemType(), message, "Values are getting mismatched");
    }

    @And("^\"totalBlackListRecords\" returned in response should be greater that zero$")
    public void returned_in_response_should_be_greater_that_zero() {
        Assert.assertTrue(getBlackListItems.getBlackListRecordsSize() > 0, "Record size returned in response in not greater than zero");
    }

    @And("^setting value to search as \"([^\"]*)\" in GET BlackList items api$")
    public void set_value_to_search(String value)
    {
        getBlackListItems.setValueType(value);
    }

    @And("^setting the value of blId in GET BlackList items api as \"([^\"]*)\"$")
    public void set_blId_in_GET_blacklist_items_api(String blid)
    {
        refreshPropertyFile();
        getBlackListItems.setBlId(Integer.parseInt(properties.getProperty(blid)));
    }

    @And("^verify value returned in response should be \"([^\"]*)\" and note the id corresponding to that in config file$")
    public void validate_value_and_save_in_config_file(String value)
    {
        Map<String ,Object > map=getBlackListItems.getVariablesFromValueList();
        Assert.assertEquals(map.get("value"),value,"Values are not getting matched please check is response having item in list more than 1");

        getBlackListItems.storeIdOfABlackListItemType();
    }

    @And("^verify value of country returned in response should be \"([^\"]*)\" and note the id corresponding to that in config file$")
    public void validate_country_save_id(String country)
    {
        Map<String ,Object > map=getBlackListItems.getVariablesFromValueList();
        Assert.assertEquals(map.get("country"),country);

        getBlackListItems.storeIdOfABlackListItemType();
    }

    @And("^\"totalBlackListRecords\" returned in response should be zero$")
    public void vaidate_record_returned_in_response()
    {
        Assert.assertTrue(getBlackListItems.getBlackListRecordsSize()==0);
    }

    //---------------POST Add Items in Black List--------------------//

    @Then("^set the value of valueType as \"([^\"]*)\" and valueList as \"([^\"]*)\"$")
    public void set_valueType_and_valueListAs(String valueType,String valueList)
    {
        postAddItemsInBlackList.setValueList(valueList);
        postAddItemsInBlackList.setValueType(valueType);
    }

    @Then("^set the value of valueType as \"([^\"]*)\"$")
    public void set_valueType_In_PostAddItems(String valueType)
    {
        postAddItemsInBlackList.setValueType(valueType);
    }

    @And("^set the value of city as \"([^\"]*)\"$")
    public void set_city(String city)
    {
        postAddItemsInBlackList.setCity(city);
    }

    @And("^set the value of country as \"([^\"]*)\"$")
    public void set_country(String country)
    {
        postAddItemsInBlackList.setCountry(country);
    }

    @And("^set the value of pincode as \"([^\"]*)\"$")
    public void set_pincode(String pincode)
    {
        postAddItemsInBlackList.setPostalCode(pincode);
    }


    @And("^set the value of state as \"([^\"]*)\"$")
    public void set_state(String state)
    {
        postAddItemsInBlackList.setState(state);
    }

    @And("^set the value of street as \"([^\"]*)\"$")
    public void set_street(String street)
    {
        postAddItemsInBlackList.setStreet(street);
    }

    @And("^set the value of street2 as \"([^\"]*)\"$")
    public void set_street2(String street2)
    {
        postAddItemsInBlackList.setStreet2(street2);
    }



    @Then("^hit the POST api to add items in black list having blacklist id \"([^\"]*)\"$")
    public void set_blId_and_hit_Post_add_item_api(String blId)
    {
        refreshPropertyFile();
        postAddItemsInBlackList.setBlId(Integer.parseInt(properties.getProperty(blId)));
        postAddItemsInBlackList.hitRequest();
    }

    @Then("^status in response should be (\\d+) and message \"([^\"]*)\"$")
    public void validate_response_code_and_message(int code, String message)
    {
        Assert.assertEquals(postAddItemsInBlackList.getResponseCode(),code,"Response code is mismatched");
        Assert.assertTrue(postAddItemsInBlackList.getMessageFromResponse().equalsIgnoreCase(message));
    }

//-----------------DELETE API---------------------//

    @Then("^set the blId in delete black list items api as \"([^\"]*)\"$")
    public void set_blid_in_delete_request(String blid)
    {
        deleteBlacklistItems.setBlId(properties.getProperty(blid));
    }

    @Then("^set the value type as \"([^\"]*)\" and id as \"([^\"]*)\"$")
    public void set_the_value_type(String value_type,String id)
    {
        refreshPropertyFile();
        deleteBlacklistItems.setIds(properties.getProperty(id));
        deleteBlacklistItems.setValueType(value_type);
    }

    @Then("^hit the delete items from black list api$")
    public void hit_delete_items_from_blacklist()
    {
        Assert.assertTrue(deleteBlacklistItems.hitRequest(),"Unable to hit the request please check logs");
    }

    @Then("^response status should be (\\d+) and message should be \"([^\"]*)\" of delete items from blacklist$")
    public void verify_response_code_and_message(int code, String message)
    {
        Assert.assertEquals(deleteBlacklistItems.getResponseCode(),code);
        Assert.assertEquals(deleteBlacklistItems.getMessageFromResponse(),message);
    }





}
