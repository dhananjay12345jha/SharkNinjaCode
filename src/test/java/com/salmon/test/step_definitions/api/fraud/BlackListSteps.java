package com.salmon.test.step_definitions.api.fraud;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.api.fraud.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.FileReader;
import java.util.Properties;

public class BlackListSteps
{

    private GetBlacklist getBlacklist;
    private GetBlackListBlId getBlackListBlId;
    private POSTBlacklist postBlacklist;
    private PUTBlacklist putBlacklist;
    private DELETEBlacklist deleteBlacklist;
    private FileReader reader;
    private Properties properties=new Properties();

    public BlackListSteps()
    {
        getBlacklist=new GetBlacklist();
        getBlackListBlId=new GetBlackListBlId();
        postBlacklist=new POSTBlacklist();
        putBlacklist=new PUTBlacklist();
        deleteBlacklist=new DELETEBlacklist();
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
//------------GET BlackList------and----GET/Blacklist/{BlID}------------//
    @When("^GET blacklists api has been hit$")
    public void get_blacklists_api_has_been_hit()
    {
        Assert.assertTrue(getBlacklist.hitRequest(),"Unable to hit request GET BlackList");
    }

    @Then("^response of the api should be (\\d+)$")
    public void response_of_the_api_should_be(int code) {
       Assert.assertEquals(getBlacklist.getResponseCode(),code);
    }

    @And("^data list returned in response should be greater than zero$")
    public void data_list_returned_in_response_should_be_greater_than_zero() {
        int size=getBlacklist.getDataListSize();
       Assert.assertTrue(size>0,"Data List size is not greater than zero which is-->"+size);
    }

    @Then("^value of the key \"([^\"]*)\" should be greater than zero$")
    public void value_of_the_key_should_be_greater_than_zero(String key) {
       int value=getBlacklist.getValueOfKeyTotalBlackListRecords();
       Assert.assertTrue(value>0,"Value size is not greater than zero which is-->"+value);
    }

    @Then("^save the first black list id against key \"([^\"]*)\" into config file for later use$")
    public void save_blId_value_in_config_file(String key){
        Assert.assertTrue(getBlacklist.saveBlackListIdInConfigFile(key));
    }

    @When("^GET particular blacklist details api with id \"([^\"]*)\" has been hit$")
    public void get_particular_blacklist_details_api_with_id_has_been_hit(String id) {
        refreshPropertyFile();
        getBlackListBlId.setBlackListId(properties.getProperty(id));
       Assert.assertTrue(getBlackListBlId.hitRequest(),"Unable to hit request please check logs");
    }

    @When("^set the blacklist id as \"([^\"]*)\" and then hit GET/blacklist/blId request$")
    public void set_blacklist_id_which_not_exist_then_hit_GETBlacklistBlId_request(String id)
    {
        getBlackListBlId.setBlackListId(id);
        Assert.assertTrue(getBlackListBlId.hitRequest(),"Unable to hit request please check logs");
    }


    @Then("^response code of api should be (\\d+)$")
    public void response_code_of_api_should_be(int code) {
       Assert.assertEquals(getBlackListBlId.getResponseCode(),code);
    }

    @And("^blId returned in response should be equal to \"([^\"]*)\"$")
    public void list_size_returned_in_response_should_be_equal_to(String blID)
    {
        refreshPropertyFile();
       Assert.assertEquals(getBlackListBlId.getBlIdFromResponse(),properties.getProperty(blID));
    }

    @And("^message in response should be \"([^\"]*)\"$")
    public void message_in_response(String message)
    {
        Assert.assertTrue(getBlackListBlId.getResponseBody().contains(message),
                "Response Body is-->"+getBlackListBlId.getResponseBody()+" and message to find is-->"+message);
    }


    //-------------------POST BlackList------------------------//

   @When("^providing value of action as \"([^\"]*)\" and name \"Randomly Generated\"$")
    public void provide_value_of_action_and_name(String action)
   {
       postBlacklist.setValueName(RandomGenerator.randomAlphabetic(5));
       postBlacklist.setValueAction(action);
   }
   @Then("^hit the POST/Blacklist api$")
    public void hit_request()
   {
       Assert.assertTrue(postBlacklist.hitRequest(),"unable to hit request please check logs");
   }

   @Then("^response of the POST/Blacklist api should be (\\d+)$")
    public void validate_response(int code)
   {
       Assert.assertEquals(postBlacklist.getResponseCode(),code);
   }

   @And("^message shown in response should be \"([^\"]*)\"$")
    public void validate_message_in_response(String message)
   {
       Assert.assertEquals(postBlacklist.getMessageFromResponse(),message);
   }

   @Then("^capture and store the blId of newly created request into config file$")
    public void store_blid_in_config_file()
   {
       Assert.assertTrue(postBlacklist.storeBlIdIntoTheConfigFile(),"Unable to store the blid in config file please check");
   }

//----------------------PUT BlackList-----------------------------//

    @When("^setting the variables action as \"([^\"]*)\" and name as \"([^\"]*)\"$")
    public void setting_variables_action_and_name(String action,String name)
    {
        putBlacklist.setValueAction(action);
        putBlacklist.setValueName(name);

    }

    @Then ("^hit PUT api which is having blId as \"([^\"]*)\" to update the action and name$")
    public void set_blid_and_hit_put_request(String blId)
    {
        refreshPropertyFile();
        putBlacklist.setBlIdValue(properties.getProperty(blId));
        Assert.assertTrue(putBlacklist.hitRequest(),"Unable to hit PUT request to change parameter's value");
    }

    @And("^values of variable action should be \"([^\"]*)\" and name as \"([^\"]*)\" from response$")
    public void validate_action_name_from_response(String action,String name)
    {
        Assert.assertEquals(getBlackListBlId.getBlacklistActionFromResponse(),action,"Action value is not matched");
        Assert.assertEquals(getBlackListBlId.getBlacklistNameFromResponse(),name,"Name vakues are not gettig matched");
    }

    @Then("^in response code should be (\\d+) and message should be \"([^\"]*)\"$")
    public void validate_response_code_and_message(int code,String message)
    {
        Assert.assertEquals(putBlacklist.getResponseCode(),code,"Code is not getting matched ");
        Assert.assertEquals(putBlacklist.getMessageFromResponse(),message,"Message is not getting matched ");
    }

//-----------------DELETE/Blacklist/blId-------------------------//

    @Then("^set up the value of blid as \"([^\"]*)\" and hit the request$")
    public void set_value_of_blid_and_hit_delete_request(String id)
    {
        refreshPropertyFile();
        deleteBlacklist.setBlId(properties.getProperty(id));
        Assert.assertTrue(deleteBlacklist.hitRequest(),"Unable to hit the request please check logs");
    }

    @Then("^response code should be (\\d+) and message should be \"([^\"]*)\"$")
    public void check_response_code_and_message(int code,String message)
    {
        Assert.assertEquals(deleteBlacklist.getResponseCode(),code,"Response code is not correct please check logs");
        Assert.assertEquals(deleteBlacklist.getMessageFromResponse(),message,"Messages are not matched");
    }

    @And("^message in response will be \"([^\"]*)\"$")
    public void check_message_from_response(String message)
    {

    }

}
