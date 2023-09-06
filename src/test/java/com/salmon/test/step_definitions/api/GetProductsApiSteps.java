package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.GetAllProducts;
import com.salmon.test.page_objects.api.ICM.ProductSearchWithCategoryAndProductKey;
import com.salmon.test.page_objects.api.ICM.ProductSearchWithProductKey;
import com.salmon.test.page_objects.api.ICM.ProductsWithCategoryKey;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class GetProductsApiSteps
{
    private ProductsWithCategoryKey productsWithCategoryKey;
    private ProductSearchWithCategoryAndProductKey productSearchWithCategoryAndProductKey;
    private ProductSearchWithProductKey productSearchWithProductKey;
    private GetAllProducts getAllProducts;

    public GetProductsApiSteps()
    {
        productsWithCategoryKey=new ProductsWithCategoryKey();
        productSearchWithCategoryAndProductKey=new ProductSearchWithCategoryAndProductKey();
        productSearchWithProductKey=new ProductSearchWithProductKey();
        getAllProducts=new GetAllProducts();
    }


    //---------GET /categories/{key of item}/products---------//

    @Given("^api has been hit with category key \"([^\"]*)\"$")
    public void api_has_been_hit_with_category_key(String key)
    {
       Assert.assertTrue(productsWithCategoryKey.hitRequestWithCategoryKeyAs(Props.getProp(key)));
    }

    @When("^response code is (\\d+)$")
    public void response_code_is(int resCode) {
        Assert.assertEquals(productsWithCategoryKey.getResponseCode(),resCode,"Response is not 200");
    }

    @Then("^validate that number of products returned in response should be (\\d+)$")
    public void validate_that_number_of_products_returned_in_response_should_be(int numbOfProd) {
        Assert.assertEquals(productsWithCategoryKey.getNumberOfProductsReturned(),numbOfProd,"There is mismatch in product list ");
    }

    @Then("^also print the returned product lists$")
    public void also_print_the_returned_product_lists()
    {

        Assert.assertTrue(productsWithCategoryKey.getAllProductNames()!=null || !productsWithCategoryKey.getAllProductNames().isEmpty());
        for (String  product:productsWithCategoryKey.getAllProductNames())
        {
            System.out.println(product);
        }
    }


    //----------------GET /categories/{key of item}/products/{key of item}--------------//

    @Given("^api has been hit with category key \"([^\"]*)\" and product key \"([^\"]*)\"$")
    public void api_has_been_hit_with_category_key_and_product_key(String category, String product)
    {
        Assert.assertTrue(productSearchWithCategoryAndProductKey.
                hitRequestWithCategoryAndProductKey(Props.getProp(category),Props.getProp(product)));
    }

    @When("^response code is equals to (\\d+)$")
    public void response_code_is_equals_to(int code)
    {
        Assert.assertEquals(productSearchWithCategoryAndProductKey.getResponseCode(),code,"Mismatch in response code");
    }

    @Then("^validating response contains only single product$")
    public void validating_response_contains_only_single_product() {
        Assert.assertTrue(productSearchWithCategoryAndProductKey.isResponseContainsMultipleProduct(),
                "Response containing multiple products please check");
    }

    @Then("^having sku id \"([^\"]*)\"$")
    public void having_sku_id(String skuId)
    {
        Assert.assertEquals(productSearchWithCategoryAndProductKey.
                getSkuId(),Props.getProp(skuId),"SKU IDs are not getting matched for the product");
    }


    //---------GET /products/{key of item}--------//

    @Given("^api has been hit with product key \"([^\"]*)\"$")
    public void api_has_been_hit_with_product_key(String productKey)
    {
        Assert.assertTrue(productSearchWithProductKey.
                hitRequestWithProductKey(Props.getProp(productKey)),"Unable to hit the request Please check");
    }

    @When("^response code is equals (\\d+)$")
    public void response_code_is_equals(int arg1) {
        Assert.assertEquals(productSearchWithProductKey.getResponseCode(),200,"Response code is not matched ");
    }

    @Then("^validate response should contains only single product$")
    public void validate_response_should_contains_only_single_product() {
       Assert.assertTrue(productSearchWithProductKey.isResponseContainsMultipleProduct(),"Multiple Products found ");
    }

    @Then("^response having sku id \"([^\"]*)\"$")
    public void response_having_sku_id(String skuId) {
        Assert.assertEquals(productSearchWithProductKey.
                getSkuId(),Props.getProp(skuId),"SKU id is not matched ");
    }

//------------- GET /products ----------------//
    @Given("^api has been hit with amount (\\d+)$")
    public void api_has_been_hit_with_amount(int amount)
    {
        Assert.assertTrue(getAllProducts.hitRequest(amount),"Unable to hit request with given amount");
    }

    @When("^response comes out to be (\\d+)$")
    public void response_comes_out_to_be(int code)
    {
        Assert.assertEquals(getAllProducts.getResponseCode(),code,"Response code is not same ");
    }

    @Then("^validating total number of items returned should not be equal to zero$")
    public void validating_total_number_of_items_returned_should_not_be_equal_to()
    {
        Assert.assertTrue(getAllProducts.getTotalNumberOfItems()!=0,"Returned total items list in response is equal to zero");
    }

    @Then("^number of product returned should be (\\d+)$")
    public void number_of_product_returned_should_be(int noOfProducts)
    {
        Assert.assertEquals(getAllProducts.getTotalNumbeOfProducts(),noOfProducts,"Number of Products returned in the api is not same ");
    }

}
