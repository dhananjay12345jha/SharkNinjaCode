package com.salmon.test.step_definitions.api.fraud;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.POSTProductRefurbish;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import static com.salmon.test.step_definitions.api.fraud.ProductRegAndWarrantyCreationSteps.responseFromPostApi;

public class ProductRefurbishSteps {

    private POSTProductRefurbish postProductRefurbish;

    public ProductRefurbishSteps() {
        postProductRefurbish = new POSTProductRefurbish();
    }

    @Then("^response of refurbish warranty api should be (\\d+)$")
    public void response_of_refurbish_api_should_be(int code) {
        Assert.assertEquals(postProductRefurbish.getResponseCode(), code);
    }

    @And("^message from refurbish API response should be \"([^\"]*)\"$")
    public void VerifyRefurbishApiMessageResponse(String message) {
        Assert.assertEquals(postProductRefurbish.getMessageFromResponse(),message);
    }

    @And("^Verify provided smart serial number shown in \"successfullyUpdated\" in response body")
    public void VerifySSNInSuccessfullyUpdated() {
        Assert.assertEquals(postProductRefurbish.getUpdatedSSN(),responseFromPostApi.getData().get(0).getSmartSerialNumber());
    }

    @And("^Invalid smart serial number shown in \"notFound\" in response body")
    public void VerifySSNInNotFound() {
        Assert.assertEquals(postProductRefurbish.getNotFoundSSN(), Props.getProp("product.invalid.smartserialNumber"));
    }

    @And("^Verify provided smart serial number shown in \"alreadyRefurbished\" in response body")
    public void VerifySSNInAlreadyRefurbished() {
        Assert.assertEquals(postProductRefurbish.getAlreadyRefurbishedSSN(),responseFromPostApi.getData().get(0).getSmartSerialNumber());
    }

    @Then("^hit the POST api to refurbish the product in warranty registration by providing smart serial numbers$")
    public void hit_Product_Refurbish_Post_api() throws InterruptedException {
        List<String> serialNumbers = new ArrayList<>();
        serialNumbers.add(responseFromPostApi.getData().get(0).getSmartSerialNumber());
        serialNumbers.add(Props.getProp("product.invalid.smartserialNumber"));
        postProductRefurbish.setSmartSerialNumber(serialNumbers);
        Thread.sleep(2000);
        Assert.assertTrue(postProductRefurbish.hitRequest(), "Unable to hit refurbish request");
        Thread.sleep(2000);
    }
}
