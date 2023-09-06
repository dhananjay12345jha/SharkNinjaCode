package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.CCPromotionCodes;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class CCPromotionCodesSteps
{
    private CCPromotionCodes ccPromotionCodes;

    public CCPromotionCodesSteps()
    {
        ccPromotionCodes=new CCPromotionCodes();
    }


    @When("^cc promotion api has been hit$")
    public void hit_cc_promotion_code_request()
    {
        Assert.assertTrue(ccPromotionCodes.hitRequest(),"Unable to hit request please check logs");
    }

    @Then("^in response (\\d+) should be there$")
    public void validate_api_response_code(int code)
    {
        Assert.assertEquals(ccPromotionCodes.getResponseCode(),code,"Code mismatche please check logs");
    }

    @And("^in response elements array contains promotion codes and should be greater than zero$")
    public void validate_element_array_size()
    {
        Assert.assertTrue(ccPromotionCodes.getNumberOfElementsInResponse()>0,
                "Size of promotion codes is not greater than zero-->"+ccPromotionCodes.getNumberOfElementsInResponse());
    }
}
