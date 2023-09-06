package com.salmon.test.step_definitions.api.fraud;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.warrantyRegistration.PUTProductDeregistration;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import static com.salmon.test.step_definitions.api.fraud.ProductRegAndWarrantyCreationSteps.responseFromPostApi;

public class ProductDeregistrationSteps {

    private PUTProductDeregistration putProductDeregistration;

    public ProductDeregistrationSteps() {
        putProductDeregistration=new PUTProductDeregistration();
    }

    @Then("^hit the PUT api to deregister the warranty against the ID$")
    public void hitRequests() throws InterruptedException{
        putProductDeregistration.setId(responseFromPostApi.getData().get(1).getRegId());
        Thread.sleep(2000);
        Assert.assertTrue(putProductDeregistration.hitRequest(), "Unable to hit the request");
        Thread.sleep(2000);
    }

    @Then("^hit the PUT api to deregister the warranty against the incorrect format Reg ID$")
    public void hitRequestsIdFormatIncorrect() throws InterruptedException{
        putProductDeregistration.setId(Props.getProp("product.regID.Incorrect"));
        Thread.sleep(2000);
        Assert.assertTrue(putProductDeregistration.hitRequestRegIdFormatIncorrect(), "Unable to hit the request");
        Thread.sleep(2000);
    }

    @Then("^response of deregister warranty api should be (\\d+)$")
    public void response_of_the_api_should_be(int code) {
        Assert.assertEquals(putProductDeregistration.getResponseCode(), code);
    }

    @And("^message from response should be \"([^\"]*)\"$")
    public void VerifyMessageResponse(String message) {
        Assert.assertEquals(putProductDeregistration.getMessageFromResponse(),message);
    }
}
