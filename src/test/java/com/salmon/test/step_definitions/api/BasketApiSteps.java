package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.BasketApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class BasketApiSteps
{
    private BasketApi basketApi;

    public BasketApiSteps()
    {
        basketApi=new BasketApi();
    }


    @When("^hit post request to create basket$")
    public void hit_post_request_to_create_basket() {
        Assert.assertTrue(basketApi.postCreateBasket(),"There is error while hitting the request");
    }

    @Then("^status code should be 200$")
    public void status_code_should_be()
    {
        int statusCode=basketApi.getStatusCodeOfRequest();

       Assert.assertTrue((statusCode>=200 || statusCode<=201),"Response code is not matched which is-->"+statusCode);

    }

    @Then("^value of key title in response should not be null or empty$")
    public void title_in_response_should_not_be_null_or_empty() {
        Assert.assertTrue(basketApi.fetchBasketKeyFromCreateBasketResponse()!="",
                "Unable to fetch the value of key Title in response of create basket api");
    }

    @When("^hit get basket request to fetch basket Key$")
    public void hit_get_basket_request_to_fetch_basket_for_a_basketKey() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(basketApi.getBasket(),"Unable to hit request please check logs");
        Thread.sleep(1000);
    }

    @Then("^value of key ID in response should be equal to basketKey$")
    public void value_of_key_ID_in_response_should_be_equal_to_basketKey() {
       Assert.assertEquals(basketApi.getBasketKey(),basketApi.fetchIdValueFromGetBasketResponse(),
               "Basket key send in url and one which has been fetched in response are not same");
    }

    //---Background Steps-----//
    @Given("^Uri for Email \"([^\"]*)\" has been obtained$")
    public void uri_for_Email_is_has_been_obtained(String emailId)
    {
        Assert.assertTrue(basketApi.fetchCustomerDetailsGET(Props.getProp(emailId))!="","Unable to Fetch the customer URI for email id-->>"+Props.getProp(emailId));
    }

    @And("^Token has been generated corresponding to uri obtained$")
    public void generating_Token_corresponding_to_uri_obtained()
    {
        Assert.assertTrue(basketApi.generateCustomerToken()!="","Unable to generate token for email Id-->>"+Props.getProp("icm.emailId.to.generate.token"));
    }

    //-------- Add Items In Basket---------------//
    @Then("^hit the get request to fetch basket details$")
    public void hit_the_get_request_to_fetch_basket_details() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(basketApi.getBasketItems(),"Unable to hit getBasketItems api ");
        Thread.sleep(1000);
    }

    @Then("^details should be empty as basket is newly created$")
    public void details_should_be_empty_as_basket_is_newly_created() {
        int size=basketApi.getDataSizeFromTheResponseOfGetBasketItem();
        Assert.assertTrue( size==0,"Data size is-->"+size+" it should be equal to zero");
    }

    @When("^adding an item having item code as \"([^\"]*)\"$")
    public void adding_an_item_having_item_code_as(String product) {

      //---need to provide quantity explicitly also ?? as of now it is one by default
       Assert.assertTrue(basketApi.addItemsInBasket(Props.getProp(product)),"Unable to add product with Product Id-->"+Props.getProp(product)+" and quantity 1");
    }

    @Then("^validating when fetching basket details quantity value should be 1$")
    public void single_product_should_get_add_in_the_basket() {
        Assert.assertEquals(basketApi.getQuantityValueOfAddedItems(),"1",
                "Quantity of product which has been added and which has been returned in response having mismatch");
    }

    @Then("^product quantity should get increase by one$")
    public void product_quantity_should_get_increase_by_one() {
        Assert.assertEquals(basketApi.getQuantityValueOfAddedItems(),"2",
                "Quantity of product which has been added and which has been returned in response having mismatch");
    }


}
