package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.SNCA.CartPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.PDPPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.SearchPageSNCA;
import com.salmon.test.page_objects.gui.SearchPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class PDPPageStepsSNCA {
    private PDPPageSNCA pdpPageSNCA;
    private CartPageSNCA cartPageSNCA;
    public static List<String> productTitle =new ArrayList<>();
    public static List<String> productModel = new ArrayList<>();
    public static List<Float> productPrice=new ArrayList<>();

    public PDPPageStepsSNCA() {
        this.pdpPageSNCA = new PDPPageSNCA();

    }
    @When("^I click on product which is not out of stock from search list$")
    @And("^I click on any product from product list$")
    public void open_PDP_page_of_product_which_is_not_out_of_stock() {
        IsPageLoaded.waitAllRequest();
        pdpPageSNCA = new SearchPageSNCA().openPDPPageOfProductWhichIsNotOutOfStock();
        Assert.assertTrue(pdpPageSNCA != null, "Unable to open any PDP page check if any product is available or all out of stock");
    }

    @Then("^I should be on PDP page of that product$")
    public void validate_pdp_page_is_there()  {
        Assert.assertTrue(pdpPageSNCA.validate_pdp_page_is_there());
    }

    @When("^I click on add to cart button$")
    public void click_on_add_to_cart_button()  {
        Assert.assertTrue(pdpPageSNCA.clickOnAddToCart());
    }


    @And("^number shown over cart icon should equal to \"(\\d+)\"$")
    public void validate_number_shown_over_cart(int count) {
        Assert.assertEquals(pdpPageSNCA.getAddedItemCountShownOverCartIcon(), count);
    }

    @And("^I note product title,model number and price$")
    public void note_product_title_model_and_price_for_further_validate() {

        String model=pdpPageSNCA.getProductModelNumber();
        String title=pdpPageSNCA.getProductTitle();
        float price=pdpPageSNCA.getProductPrice();

        productModel.add(pdpPageSNCA.getProductModelNumber());
        productPrice.add(pdpPageSNCA.getProductPrice());
        productTitle.add(pdpPageSNCA.getProductTitle());

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(!model.equalsIgnoreCase(""));
        softAssert.assertTrue(price > 0.0);
        softAssert.assertTrue(!title.equalsIgnoreCase(""));

        softAssert.assertAll();
    }


    @When("^I click on cart icon$")
    public void click_on_cart_icon() {
        cartPageSNCA = pdpPageSNCA.clickOnCartIcon();
        Assert.assertTrue(cartPageSNCA != null);
    }

    @When("^I click on cart icon for crossCart$")
    public void click_on_cart_icon_for_crossCart() {
        cartPageSNCA = cartPageSNCA.clickOnCartIconForCrossCart();
        //Assert.assertTrue(cartPageSNCA != null);
    }
    @Then("^I should be on cart page where text shown is \"(.*?)\" or \"(.*?)\"$")
    public void validate_that_i_should_be_on_cart_page(String value1, String value2) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertEquals(cartPageSNCA.getTextFromCartPage(), expected);
    }


    @Then("^all the mandatory components should be present$")
    public void all_the_mandatory_components_should_be_present() throws Throwable {
    Assert.assertTrue(pdpPageSNCA.validateMandatoryPDPComponents(),"Mandatory components are not present");
      }

    @When("^I enter product quantity with \"([^\"]*)\"$")
    public void iEnterProductQuantityWith(String productQuantity) throws Throwable {
        String productQty = Props.getProp(productQuantity);
        pdpPageSNCA.updateProductQuantity(productQty);
    }

    @Then("^I should see error message that \"([^\"]*)\"$")
    public void iShouldSeeErrorMessageThat(String msgKey) throws Throwable {
        Assert.assertTrue(pdpPageSNCA.validateErrorMesg(msgKey));
    }

    @Then("^I should see cart overlay is updated with \"([^\"]*)\"$")
    public void iShouldSeeCartOverlayIsUpdatedWith(String productQuantity) throws Throwable {
        String productQty = Props.getProp(productQuantity);
        Assert.assertEquals(productQty,pdpPageSNCA.getProductQuantityFromOverlay(),"quantity is successfully Updated");

    }

    @Then("^I should see the rating of a product at plp level \"([^\"]*)\"$")
    public void Ishouldseetheratingofaproductatplplevel(String productSpecificSKU) throws Throwable {
        String productSKU = Props.getProp(productSpecificSKU);
        pdpPageSNCA.verifyRatingAtPlpLevel(productSKU);
    }

    @When("^I click on the Rate And Review Button$")
    public void i_click_on_the_review_button() {
        pdpPageSNCA.clickRateAndReviewButton();
    }
    @Then("^I should see Text Rating Snapshot$")
    public void verifyReviewText() {
        if (UrlBuilder.isDesktop()) {
            Assert.assertTrue(pdpPageSNCA.getReviewText().contentEquals(Props.getProp("review.text")));
        } else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
            String expectedResult=Props.getProp("minicart.message.popup");
            String expectedResultalternate=Props.getProp("minicart.message.popup.alternate");
            String actualResult=pdpPageSNCA.getEmptyCartMessage();
            boolean flag=false;
            if(actualResult.equalsIgnoreCase(expectedResult))
                flag=true;
            else if(actualResult.equalsIgnoreCase(expectedResultalternate))
                flag=true;
            Assert.assertTrue(flag,"Actual Result is "+actualResult+" Expected Result is "+ expectedResult +" OR " + expectedResultalternate);
        }
    }
}



