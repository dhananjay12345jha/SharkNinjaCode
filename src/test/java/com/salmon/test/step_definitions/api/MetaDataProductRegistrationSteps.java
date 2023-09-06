package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.MetaDataProductRegistration;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class MetaDataProductRegistrationSteps
{
    private MetaDataProductRegistration metaDataProductRegistration;

    public MetaDataProductRegistrationSteps()
    {
        metaDataProductRegistration=new MetaDataProductRegistration();
    }

    @When("^metadata api has been hit$")
    public void metadata_api_has_been_hit() {
        Assert.assertTrue(metaDataProductRegistration.hitRequest(),"Unable to hit request please check");
    }

    @Then("^response should be equal to (\\d+)$")
    public void response_should_be_equal_to(int code) {
        Assert.assertEquals(metaDataProductRegistration.getReposeCode(),code,"Response code is getting mismatch");
    }

    @Then("^validate that \"SharkProductTypes\" should not be empty$")
    public void getSharkProductTypeListSize()
    {
        Assert.assertTrue(metaDataProductRegistration.getSizeOfSharkProductsType()!=0,"Shark Product Type List is empty please check");
    }

    @Then("^validate that \"NinjaProductTypes\" should not be empty$")
    public void getNinjaProductTypeListSize()
    {
        Assert.assertTrue(metaDataProductRegistration.getSizeNinjaProductTypes()!=0,"Ninja Product Type List is empty please check");
    }

    @Then("^validate that \"SharkSellingLocations\" should not be empty$")
    public void getSharkSellingLocation()
    {
        Assert.assertTrue(metaDataProductRegistration.getSizeSharkSellingLocations()!=0,"Shark Selling Location List is empty please check");
    }

    @Then("^validate that \"NinjaSellingLocations\" should not be empty$")
    public void getNinjaSellingLocation()
    {
        Assert.assertTrue(metaDataProductRegistration.getSizeNinjaSellingLocations()!=0,"Ninja Selling Location List is empty please check");
    }

    @Then("^validate that \"SellingLocations\" should not be empty$")
    public void validate_selling_locations_should_not_be_empty(){
        Assert.assertTrue(metaDataProductRegistration.getSizeSellingLocations()!=0,
                "Selling location List is empty please check ");
    }

}
