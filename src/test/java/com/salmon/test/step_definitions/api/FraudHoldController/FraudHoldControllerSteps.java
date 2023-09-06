package com.salmon.test.step_definitions.api.FraudHoldController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.fraudController.GetFraudHoldsOrderId;
import com.salmon.test.page_objects.api.fraud.fraudController.PutFraudHoldsOrderId;
import com.salmon.test.page_objects.api.fraud.pojo.fraudController.FraudHoldOrder;
import com.salmon.test.page_objects.api.fraud.pojo.fraudController.ResponseFromGetFraudHoldsOrderId;
import cucumber.api.java.en.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class FraudHoldControllerSteps {
    private GetFraudHoldsOrderId getFraudHoldsOrderId;
    private PutFraudHoldsOrderId putFraudHoldsOrderId;
    private ResponseFromGetFraudHoldsOrderId responseFromGetFraudHoldsOrderId;
    private List<String> idOfBlacklist = new ArrayList<>();
    private FileReader reader;
    private Properties properties = new Properties();

    public FraudHoldControllerSteps() {
        getFraudHoldsOrderId = new GetFraudHoldsOrderId();
        putFraudHoldsOrderId = new PutFraudHoldsOrderId();
    }

    private void refreshPropertyFile() {
        try {
            reader = new FileReader(System.getProperty("user.dir") + Props.getProp("file.path"));
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("^set the value of orderId \"([^\"]*)\" in Get FraudHolds/OrderId api$")
    public void set_value_of_orderId_in_GETFraudHoldsOrderList(String orderId) {
        refreshPropertyFile();
        getFraudHoldsOrderId.setOrderId(properties.getProperty(orderId));
    }

    @Then("^hit the GET FraudHolds/OrderId api$")
    public void hit_the_GET_FraudHolds_OrderId_api() {
        Assert.assertTrue(getFraudHoldsOrderId.hitRequest(), "Unable to hit the API");
    }


    @And("^validate that status code should be (\\d+) and message \"([^\"]*)\"$")
    public void validate_that_status_code_should_be_and_message(int code, String message) {
        Assert.assertEquals(getFraudHoldsOrderId.getResponseCode(), code);
        Assert.assertEquals(getFraudHoldsOrderId.getMessageFromResponse(), message);
    }

    @Then("^picked up the first id where source will be BLACKLIST and status as REVIEW$")
    public void picked_up_the_first_id_where_source_will_be_BLACKLIST_and_status_as_REVIEW() {

        Iterator<FraudHoldOrder> iterator = getFraudHoldsOrderId.getResponseAsClass().getData().getFraudHoldOrder().iterator();

        while (iterator.hasNext())
        {  FraudHoldOrder obj=iterator.next();
            if (obj.getSource().equalsIgnoreCase("blacklist")
                    && obj.getStatus().equalsIgnoreCase("REVIEW"))
            {
                idOfBlacklist.add(String.valueOf(obj.getId()));
                break;
            }
        }

        Assert.assertTrue(idOfBlacklist.size()==1, "Unable to find blacklist where source is BLACKLIST and Status as REVIEW from the getFraudHolds/OrderId api response");
    }

    @And("^set the id value obtained above in PUT FraudHolds/OrderApi body$")
    public void set_the_id_value_obtained_above_in_PUT_FraudHolds_OrderApi_body()
    {

        putFraudHoldsOrderId.setFraudHoldIdList(idOfBlacklist);
    }

    @And("^set the value of orderId \"([^\"]*)\" in PUT FraudHolds/OrderId api$")
    public void set_value_orderId_in_putHoldsOrderId_api(String orderId) {
        refreshPropertyFile();
        putFraudHoldsOrderId.setOrderId(properties.getProperty(orderId));
    }

    @And("^set the value of action as \"([^\"]*)\" and comment as \"([^\"]*)\" and updatedBy as \"([^\"]*)\" in PUT FraudHolds/OrderId api body$")
    public void set_action_comment_updateBy_fraudHoldList_in_put_fraudHoldsOrderList(String action, String comments, String updatedBy) {
        putFraudHoldsOrderId.setComment(comments);
        putFraudHoldsOrderId.setUpdatedBy(updatedBy);
        putFraudHoldsOrderId.setAction(action);
    }

    @Then("^hit the PUT FraudHolds/Order api$")
    public void hit_put_fraudHoldsOrderId_api() {
        Assert.assertTrue(putFraudHoldsOrderId.hitRequest());
    }

    @And("^validate response should have status as (\\d+) and message \"([^\"]*)\"$")
    public void validate_response_of_put_fraudHoldsOrderId(int code, String message) {
        Assert.assertEquals(putFraudHoldsOrderId.getResponseCode(), code);
        Assert.assertEquals(putFraudHoldsOrderId.getMessageFromResponse(), message);
    }


    @And("^validates that blacklist id send in PUT request body above value of status should be \"([^\"]*)\" instead of REVIEW$")
    public void validate_get_fraudHoldOrderList_response(String expected) {
        Iterator<FraudHoldOrder> iterator = getFraudHoldsOrderId.getResponseAsClass().getData().getFraudHoldOrder().iterator();
        boolean flag = false;
        while (iterator.hasNext())
        {   FraudHoldOrder obj=iterator.next();
            if (obj.getId()==Integer.parseInt(idOfBlacklist.get(0)))
            {
                if (obj.getStatus().equalsIgnoreCase(expected)) {
                    flag = true;
                    break;
                }
            }
        }

        Assert.assertTrue(flag, "After hitting PUT FraudHold/OrderId api to" + expected + " Fraud Hold id-->" + idOfBlacklist + " status is not changed to " + expected);
    }

    @Then("^picked up all ids where source will be BLACKLIST and status as REVIEW$")
    public void get_all_id_values_of_blacklist_under_fraudHold()
    {
        Iterator<FraudHoldOrder>iterator=getFraudHoldsOrderId.getResponseAsClass().getData().getFraudHoldOrder().iterator();
        while (iterator.hasNext())
        {
            FraudHoldOrder obj=iterator.next();
            if(obj.getStatus().equalsIgnoreCase("REVIEW") &&
            obj.getSource().equalsIgnoreCase("BLACKLIST"))
            {
                idOfBlacklist.add(String.valueOf(obj.getId()));
            }
        }

        Assert.assertTrue(idOfBlacklist.size()>0,"Multiple ids of blacklist is not present in fraud hold list unable to continue");
    }

    @And("^set the all id values obtained above in PUT FraudHolds/OrderApi body$")
    public void set_all_values_of_id_into_PUT_FraudHolds()
    {
        putFraudHoldsOrderId.setFraudHoldIdList(idOfBlacklist);
    }

    @And("^validates that all blacklist id send in PUT request body above value of status should be \"([^\"]*)\" instead of REVIEW$")
    public void status_of_all_blackList_id_should_be_changes_from_review_to(String expected)
    {
        List<FraudHoldOrder> list=getFraudHoldsOrderId.getResponseAsClass().getData().getFraudHoldOrder();
        SoftAssert softAssert=new SoftAssert();
        for (String e:idOfBlacklist)
        {
            for(int i=0;i<list.size();i++)
            {
                if(e.equalsIgnoreCase(String.valueOf(list.get(i).getId())))
                {
                    String actual=list.get(i).getStatus();
                    softAssert.assertEquals(actual,expected,"For id-->"+list.get(i).getId()+" actual value of status is-->"+
                            actual+" and expected is-->"+expected);
                }
            }
        }

        softAssert.assertAll();
    }

    @Then("^status code should be (\\d+) and error message contains \"([^\"]*)\"$")
    public void validate_error_code_and_message(int code,String message)
    {
        Assert.assertEquals(putFraudHoldsOrderId.getResponseCode(),code);
        String expected=putFraudHoldsOrderId.getErrorMessage().replace("[","").replace("]","")
                .toLowerCase().trim();
        String actualMessage[]=message.split(" ");
        boolean flag=true;
        for(String e:actualMessage)
        {
            if(!expected.contains(e.toLowerCase()))
            {
                flag=false;
            }
        }

        Assert.assertTrue(flag,"Error message mismatch, actual message is-->"+putFraudHoldsOrderId.getErrorMessage()+" expected is-->"+expected);
    }
}
