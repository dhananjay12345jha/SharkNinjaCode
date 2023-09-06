package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.PDPPage;
import com.salmon.test.page_objects.gui.SNCA.CartPageSNCA;
import com.salmon.test.step_definitions.gui.PDPPageSteps;
import cucumber.api.java.en.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CartPageStepsSNCA extends PageObject {
    private CartPageSNCA cartPageSNCA;
    private float totalPriceOfAllProducts=0;
    public static List<Float> newPrice = new ArrayList<>();
    public static float discountPriceAtOrder = 0;
    public static String promoCodeShown = "";
    public static float subTotal, estimatedShipping, totalPrice;

    public CartPageStepsSNCA() {
        cartPageSNCA = new CartPageSNCA();
    }

    @Then("^I validate that multiple products which are added should be there in cart$")
    public void validate_multiple_added_products_should_be_shown_in_cart() {
        SoftAssert softAssert = new SoftAssert();
        for (String expected : PDPPageStepsSNCA.productTitle) {
            softAssert.assertTrue(cartPageSNCA.getProductTitles().contains(expected),
                    "Product title is either not shown or it has not been added to cart having title-->>" + expected);
        }
        for (String expected : PDPPageStepsSNCA.productModel) {
            softAssert.assertTrue(cartPageSNCA.getProductModelNumber().contains(expected),
                    "Product Model is either not shown or it has not been added to cart having title-->>" + expected);
        }
        softAssert.assertAll();
    }

    @Then("^I delete product by clicking Remove Item button showing at location (\\d+) from the cart$")
    public void validate_cart_gets_update_when_delete_a_product(int location) throws InterruptedException {
        Assert.assertTrue(cartPageSNCA.clickRemoveItemButtonLocatedAtPosition(location));
        Thread.sleep(2000);
    }

    @Then("^product would get deleted which was there at location (\\d+) and cart get updated$")
    public void validate_product_get_deleted_and_cart_gets_update(int location) {
        location--;
        String title = PDPPageStepsSNCA.productTitle.get(location);
        String model = PDPPageStepsSNCA.productModel.get(location);

        int actualListOfProductShownOnCart = cartPageSNCA.getProductTitles().size();

        Assert.assertEquals(actualListOfProductShownOnCart, PDPPageStepsSNCA.productTitle.size() - 1,
                "After deleting a product from cart product title list size is not getting reduced by 1");

        Assert.assertTrue(!cartPageSNCA.getProductTitles().contains(title));
        Assert.assertTrue(!cartPageSNCA.getProductModelNumber().contains(model));
    }

    @Then("^I calculate the total sum of products cost$")
    public void calculate_all_product_cost_on_cart_page() {
        List<Float> price = cartPageSNCA.getProductPriceList();
        List<Integer> quantity = cartPageSNCA.getProductQuantityList();
        Assert.assertEquals(price.size(), quantity.size(), "Please check if either price or quantity is not showing against product in cart");

        for (int i = 0; i < price.size(); i++) {
            totalPriceOfAllProducts += (price.get(i) * quantity.get(i));
        }
        totalPriceOfAllProducts=new BigDecimal(totalPriceOfAllProducts).setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    @Then("^sub total value should be equal to sum of products costs$")
    public void compare_subTotal_value_and_productTotalCost() {
        Assert.assertEquals(cartPageSNCA.getSubTotal(), totalPriceOfAllProducts);
    }

    @And("^I validate that estimated cost should be equal to subtotal plus shipping cost$")
    public void validate_estimated_cost_should_be_equal_to_subtotal_plus_shipping_cost() {
        float expected = cartPageSNCA.getEstimatedShippingCost() + cartPageSNCA.getSubTotal();
        Assert.assertEquals(cartPageSNCA.getEstimatedTotalCost(), expected);
    }

    @Then("^I increase the quantity of product to \"([^\"]*)\" for the product shown at row number (\\d+)$")
    public void increase_quantity_of_product_shown_at_location(String quantity, int rowNumber) {
        Assert.assertTrue(cartPageSNCA.setQuantityOfProduct(quantity, rowNumber));
    }

    @And("^I validate that product title,model number and product price shown should be same as that on pdp page$")
    public void validate_product_title_model_price_should_be_as_on_pdp_page() {
        SoftAssert softAssert = new SoftAssert();

        Assert.assertEquals(cartPageSNCA.getProductTitles().size(), PDPPageStepsSNCA.productTitle.size(),
                "Number of products added and number of product displayed on cart page are not matching some product/products are missing");

        for (int i = 0; i < cartPageSNCA.getProductTitles().size(); i++) {
            softAssert.assertEquals(cartPageSNCA.getProductTitles().get(i), PDPPageStepsSNCA.productTitle.get(i));
            softAssert.assertEquals(cartPageSNCA.getProductModelNumber().get(i), PDPPageStepsSNCA.productModel.get(i));
            softAssert.assertEquals(cartPageSNCA.getProductPriceList().get(i), PDPPageStepsSNCA.productPrice.get(i));
        }
        softAssert.assertAll();
    }

    @Then("^enter promoCode as \"([^\"]*)\"$")
    public void enter_promoCode(String code) {
        String promoCode = "";
        if (webDriver.getCurrentUrl().contains("uat") || webDriver.getCurrentUrl().contains("UAT")) {
            promoCode = Props.getProp(code + ".uat");
        } else {
            promoCode = Props.getProp(code);
        }
        Assert.assertTrue(cartPageSNCA.applyPromoCode(promoCode));
    }

    @And("^clicks on apply promoCode button$")
    public void click_on_apply_promocode_button() {
        Assert.assertTrue(cartPageSNCA.clickOnApplyPromoCodeButton());
    }

    @Then("^text \"([^\"]*)\" or \"([^\"]*)\" should be shown$")
    public void text_DiscountApplied_should_be_shown_after_applying_promoCode(String value1, String value2) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertEquals(cartPageSNCA.getTextPromoCodeApplied(),expected);
    }

    @And("^now Subtotal amount value should be less than before when discount applied$")
    public void validate_subTotal_before_and_after_value_when_applied_promocode() {
        Assert.assertTrue(cartPageSNCA.getSubTotal() < totalPriceOfAllProducts, "" +
                "Before promocod subtoatal value-->>" + totalPriceOfAllProducts + "\n After applying promocode subtotal value-->>" + cartPageSNCA.getSubTotal());
    }

    @And("^applied promocode \"([^\"]*)\" should be visible with each item in the basket$")
    public void validate_over_each_product_in_cart_promocode_should_be_applicable(String code) {
        String promo;
        if (webDriver.getCurrentUrl().contains("uat") || webDriver.getCurrentUrl().contains("UAT")) {
            promo = Props.getProp(code + ".uat");
        } else {
            promo = Props.getProp(code);
        }

        Assert.assertEquals(PDPPageStepsSNCA.productPrice.size(), cartPageSNCA.getPromoCodeAttachedWithEachElement().size(),
                "With some of the items in the cart promocode is not applied or showing");

        SoftAssert softAssert = new SoftAssert();

        List<String> promoCode = cartPageSNCA.getPromoCodeAttachedWithEachElement();
        for (int i = 0; i < promoCode.size(); i++) {
            int k = i;
            softAssert.assertEquals(promo, promoCode.get(i), "Not showing promoCode for the item located at-->>" + (++k) + " location");
        }
        softAssert.assertAll();
    }

    @And("^also new price of each item should be less than the price before discount$")
    public void validate_new_price_after_applying_promo_should_be_less_than_before() {
        newPrice = cartPageSNCA.getProductPriceList();
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < newPrice.size(); i++) {
            softAssert.assertTrue(newPrice.get(i) < PDPPageStepsSNCA.productPrice.get(i));
        }
        softAssert.assertAll();
    }

    @And("^applied promocode \"([^\"]*)\" should be shown under shipping and taxes calculation$")
    public void visibility_of_applied_promoCode_under_taxes_and_calculation_heading(String promo) {
        String promoCode = "";
        if (webDriver.getCurrentUrl().contains("uat") || webDriver.getCurrentUrl().contains("UAT")) {
            promoCode = Props.getProp(promo + ".uat");
        } else {
            promoCode = Props.getProp(promo);
        }

        // last promocode shown in the list is the promocode which is shown under Shipping and Taxes calculated section
        int lastPromoCode = cartPageSNCA.getPromoCodeAttachedWithEachElement().size() - 1;

        promoCodeShown = cartPageSNCA.getPromoCodeAttachedWithEachElement().get(lastPromoCode);

        Assert.assertEquals(promoCodeShown, promoCode);
    }

    @And("^discounted amount applied at order level should not be zero$")
    public void discounted_amount_applied_at_cart_level_should_not_be_zero() {
        discountPriceAtOrder = cartPageSNCA.getDiscountAmountAppliedAtCart();
        Assert.assertTrue(discountPriceAtOrder != 0,
                "Expected value-->> discounted amount !=0 but " + "\n Actual value found-->>" + cartPageSNCA.getDiscountAmountAppliedAtCart());
    }

    @And("^I note subtotal, estimated shipping and total price on cart page$")
    public void note_subtotal_estimatedShipping_and_totalPrice_At_cartPage(){
        estimatedShipping=cartPageSNCA.getEstimatedShippingCost();
        totalPrice=cartPageSNCA.getEstimatedTotalCost();
        subTotal=cartPageSNCA.getSubTotal();
    }

}
