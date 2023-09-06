package com.salmon.test.step_definitions.gui;

import com.mysql.jdbc.log.Log;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CartPage;
import com.salmon.test.page_objects.gui.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartPageSteps {

    private CartPage cartPage;
    private HomePage homePage;

    public CartPageSteps(CartPage cartPage, HomePage homePage) {
        this.cartPage = cartPage;
        this.homePage = homePage;
    }


    @When("^I go to checkout$")
    public void i_go_to_checkout() throws InterruptedException {
        cartPage.proceedToCheckoutButton();
    }

    @Then("^I should see added product in Cart \"([^\"]*)\"$")
    public void verifyCartProduct(String productSpecificSKU) throws InterruptedException {
        String productSKU = Props.getProp(productSpecificSKU);
        boolean flag = false;
        for (String productId : cartPage.getCartProductID()) {
            if (productId.contains(productSKU)) {
                flag = true;
            }
        }
        Assert.assertTrue(flag, "Product having Product SKU " + productSKU + " is not getting display on cart page");
    }

    @When("^I click on delete product from cart$")
    public void clickDeleteProduct() {
        Assert.assertTrue(cartPage.clickDeleteProduct());
    }

    @Then("^Added product should be deleted$")
    public void verifyCartProductDeleted() throws InterruptedException{
        Thread.sleep(2000);
        Assert.assertFalse(cartPage.isElementPresent(cartPage.getCartProduct()));
    }

    @When("^I update quantity of cart product having sku \"([^\"]*)\" to \"([^\"]*)\"$")
    public void updateProductQuantity(String sku, String productQuantity) {
        String productSku = Props.getProp(sku);
        String productQuant= Props.getProp(productQuantity);
        Assert.assertTrue(cartPage.updateProductQuantity(productSku, productQuant));
    }

    @Then("^I wait for the loader so that price would get refreshed$")
    public void waitForLoaderToAppearAndPriceGotRefreshed(){
        Assert.assertTrue(cartPage.waitForTheLoaderToAppearAndDisappear());
    }

    @And("^I click on update cart$")
    public void clickUpdateCart() throws InterruptedException {
        cartPage.clickUpdateCartBtn();
    }

    @Then("^Product quantity updated$")
    public void verifyproductQuantity() {
        System.out.println("Quantity is: " + cartPage.getProductQuantity());

//		Assert.assertTrue(cartPage.getProductQuantity().contentEquals(Props.getProp("product.valid.quantity")));
    }

    @And("^verify quantity of product multiply single unit price should equal to total price$")
    public void  verify_quantity_multiply_singlePrice_equals_to_totalPrice() {
        //Assert.assertTrue(cartPage.getCartSubtotal().contains(Props.getProp("product.specificSKU.subtotal")));
        SoftAssert softAssert = new SoftAssert();
        List<String> productQuantity = cartPage.getProductQuantity();
        List<Float> productTotalPrice = cartPage.getTotalPriceOfProducts();
        List<String> productID = cartPage.getCartProductID();

        if (UrlBuilder.isMobile()){
            for (int i = 0; i < productQuantity.size(); i++) {
                float totalPrice = 0;
                totalPrice = Integer.parseInt(productQuantity.get(i)) * PDPPageSteps.productPrice.get(i);
                totalPrice=new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
                softAssert.assertEquals(productTotalPrice.get(i), totalPrice,
                        "On Cart page " + productID.get(i) + ",quantity->" + productQuantity.get(i) +
                                ",single Price->" + PDPPageSteps.productPrice.get(i) +
                                ",total expected price should be->" + totalPrice + " but actual showing->" + productTotalPrice.get(i));
            }

        }else{
            List<Float> productSinglePrice = cartPage.getSinglePriceOfProduct();
            for (int i = 0; i < productQuantity.size(); i++) {
                float totalPrice = 0;
                totalPrice = Integer.parseInt(productQuantity.get(i)) * productSinglePrice.get(i);
                totalPrice=new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
                softAssert.assertEquals(productTotalPrice.get(i), totalPrice,
                        "On Cart page " + productID.get(i) + ",quantity->" + productQuantity.get(i) +
                                ",single Price->" + productSinglePrice.get(i) +
                                ",total expected price should be->" + totalPrice + " but actual showing->" + productTotalPrice.get(i));
            }
        }
        softAssert.assertAll();
    }

    @And("^sum of total price of all products should be equal to subtotal value$")
    public void sum_of_total_price_equals_to_subtotal() {
        float sumOfTotalPrice = 0;
        for (float k : cartPage.getTotalPriceOfProducts()) {
            sumOfTotalPrice += k;
        }
        sumOfTotalPrice=new BigDecimal(sumOfTotalPrice).setScale(2, RoundingMode.HALF_UP).floatValue();
        Assert.assertEquals(cartPage.getCartSubtotal(), sumOfTotalPrice, "Subtotal and sum of total price of all product not matched");
    }

    @Then("^I should see error message that quantity is invalid$")
    public void verifyInvalidErrorMessage() {
        String expected = Props.getProp("error.invalid.input");
        String actual = cartPage.isErrorMessageDisplayedInvalidQuantity();
        Assert.assertEquals(actual, expected);
    }

    @Then("^I should see error message that quantity is too large$")
    public void verifyTooLargeErrorMessage() {
        String expected = Props.getProp("error.max.input");
        String actual = cartPage.isErrorMessageDisplayedTooLarge();
        Assert.assertEquals(actual, expected);
    }

    @And("^I should see quantity and price in mini cart header$")
    public void verifyMiniCartHeader() {
        Assert.assertTrue(homePage.verifyMiniCartHeader());
    }

    @And("^I should see quantity in cart header$")
    public void verifyMobileCartHeader() {
        Assert.assertTrue(homePage.verifyMobileCartHeader());
    }

    @And("^I should see updated quantity in cart header$")
    public void verifyCartHeaderQuantityUpdated() {
        Assert.assertTrue(homePage.verifyCartHeaderQuantityUpdated());
    }


    @When("^I click on mini cart header$")
    public void clickMiniCartHeader() throws InterruptedException {
        Assert.assertTrue(homePage.clickMiniCartHeader("open"));
    }

    @Then("^I close mini cart$")
    public void close_mini_cart() throws InterruptedException {
        Assert.assertTrue(homePage.clickMiniCartHeader("close"));
    }

    @Then("^I should see quantity and price in mini cart$")
    public void verifyMiniCart() {
        Assert.assertTrue(homePage.verifyMiniCart());
    }

    @Then("^I should see updated quantity and price in mini cart header$")
    public void verifyMiniCartHeaderAfterCartProductQtyUpdate() {

        SoftAssert softAssert = new SoftAssert();
        float sumOFAllQuantity = 0, totalPrice = 0;
        for (int i = 0; i < cartPage.getProductQuantity().size(); i++) {
            float f = Float.parseFloat(cartPage.getProductQuantity().get(i));
            sumOFAllQuantity += f;
            totalPrice += f * cartPage.getSinglePriceOfProduct().get(i);

        }
        softAssert.assertEquals(homePage.getCartHeaderQuantity(), sumOFAllQuantity);
        softAssert.assertEquals(homePage.getCartHeaderPrice(), totalPrice);
    }

    @Then("^I can view updated quantity and price in mini cart$")
    public void verifyMiniCartAfterCartProductQtyUpdate() {
        SoftAssert softAssert = new SoftAssert();

        List<String> productsInMiniCart = homePage.getListOfProductDisplayedInMiniCart();
        System.out.println("mini Cart ids are " + productsInMiniCart);


        for (String id : cartPage.getCartProductID()) {
            boolean flag = false;
            for (int i = 0; i < productsInMiniCart.size(); i++) {
                if (productsInMiniCart.get(i).contains(id)) {
                    flag = true;
                    break;
                }
            }
            softAssert.assertTrue(flag, "Product ID " + id + " is not there in mini cart");
        }


        for (String qty : cartPage.getProductQuantity()) {
            boolean flag = false;
            for (int i = 0; i < productsInMiniCart.size(); i++) {
                if (productsInMiniCart.get(i).contains(qty)) {
                    flag = true;
                    break;
                }
            }
            softAssert.assertTrue(flag, "Product quantity " + qty + " is not there in mini cart");
        }
        softAssert.assertAll();

//		Assert.assertTrue(homePage.verifyMiniCartAfterCartProductQtyUpdate());
    }

    @Then("^I should see total quantity and price in mini cart header$")
    public void verifyMiniCartHeaderAfterAddingAnotherProduct() {
        Assert.assertTrue(homePage.verifyMiniCartHeaderAfterAddingAnotherProduct());
    }

    @Then("^I can view total quantity and price in mini cart$")
    public void verifyMiniCartAfterAddingAnotherProduct() {
        Assert.assertTrue(homePage.verifyMiniCartAfterAddingAnotherProduct());
    }

    @When("^I click on promo entry link on cart page$")
    public void clickPromoEntryLink() throws InterruptedException {
        Assert.assertTrue(cartPage.clickPromoEntryLink());
    }

    @And("^I enter discount coupon code \"([^\"]*)\"$")
    public void enterPromoCode(String promoCode) throws InterruptedException {
        String promoCode1 = Props.getProp(promoCode);
        Assert.assertTrue(cartPage.enterPromoCode(promoCode1));
    }

    @And("^I click on apply promo button$")
    public void clickApplyPromo() throws InterruptedException {
        Assert.assertTrue(cartPage.clickApplyPromo(),"Unable to click on Apply Promo Button please check logs");
    }

    @And("^I click on View Order Button$")
    public void clickViewOrderButton() throws InterruptedException {
        Assert.assertTrue(cartPage.clickViewOrderButton(),"Unable to click on View Order Button please check logs");
    }

    @Then("^I should see message above order summary that coupon applied successfully$")
    public void verifyPromoAppliedSuccessMessage() {
        Assert.assertEquals(cartPage.verifyPromoAppliedSuccessMessage(),Props.getProp("cart.promo.discount.applied"));
    }

    @Then("^I should see message above that Shark orders cannot be placed on this website$")
    public void verifySharkOrderCannotPlaceMessage() {
        IsPageLoaded.waitAllRequest();
        Assert.assertEquals(cartPage.verifyOrderCantPlacedMessage(),Props.getMessage("shark.order.cant.placed.text"));
    }

    @Then("^I should see message above that Ninja orders cannot be placed on this website$")
    public void verifyNinjaOrderCannotPlaceMessage() {
        IsPageLoaded.waitAllRequest();
        Assert.assertEquals(cartPage.verifyOrderCantPlacedMessage(),Props.getMessage("ninja.order.cant.placed.text"));
    }

    @And("^I should see Item discount in cart summary$")
    public void verifyItemDiscountInCartSummary() throws InterruptedException {
        Assert.assertTrue(cartPage.verifyItemDiscountInCartSummary());
    }

    @And("^I should see discount details link on order review summary$")
    public void verifyDiscountLinkInCartSummary() throws InterruptedException {
        Assert.assertTrue(cartPage.verifyDiscountDetailsLinkInCartSummary());
    }

    @When("^I click on discount details link$")
    public void clickDiscountDetailsLink() throws InterruptedException {
        Assert.assertTrue(cartPage.clickDiscountDetailsLink());
    }

    @Then("^I should see Promotion applied message$")
    public void verifyPromoAppliedPopupMessage() {
        String message = Props.getMessage("cart.promotion.dialogue.heading");
        System.out.println("message is:" + message);
        Assert.assertTrue(cartPage.verifyPromoAppliedPopupMessage(message));
    }
    @Then("^I update quantity of cart product having sku \"([^\"]*)\" to have incorrect order amount$")
    public void iUpdateQuantityOfCartProductHavingSkuToHaveIncorrectOrderAmount(String productSku) throws Throwable {
       int productQty=1;
       boolean flag=false;
        IsPageLoaded.waitAllRequest();
        List<Float> pricelist=PDPPageSteps.productPrice;
        Float price=pricelist.get(0);
       for(int i=2;i<=40;i++)
       {
           if(price*i> 2000 & price*i < 3000) {
               productQty = i;
               flag=true;
               break;
           }
           else if(flag==false & i==40)
               Assert.assertTrue(flag,"Order amount is not between 2000-3000");
       }
       Assert.assertTrue(cartPage.updateProductQuantity(Props.getProp(productSku),Integer.toString(productQty)));;
    }
}
